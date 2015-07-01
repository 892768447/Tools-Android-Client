package zce.moyan.tools;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

import zce.moyan.tools.util.Constants;
import zce.moyan.tools.util.HttpUtil;
import zce.moyan.tools.util.Log;
import zce.moyan.tools.util.Tools;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.devspark.appmsg.AppMsg;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

/**
 * 
 * Created on 2015年6月24日
 * 
 * @author: ヽoo悾絔℅o。
 * @email: 892768447@qq.com
 * @description:
 * 
 */
public class LoginActivity extends Activity {

	private LinearLayout uLayout, pLayout;
	private EditText username, password;
	private Button login;
	private Animation shake;

	private JSONObject jObject;// 这部分是服务器的表单
	private Editor editor;
	private MsgHandler msgHandler;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		// 账号布局
		uLayout = (LinearLayout) findViewById(R.id.username_layout);
		// 密码布局
		pLayout = (LinearLayout) findViewById(R.id.password_layout);
		// 账号输入框
		username = (EditText) findViewById(R.id.login_username_edittext);
		// 密码输入框
		password = (EditText) findViewById(R.id.login_password_edittext);
		// 登录按钮
		login = (Button) findViewById(R.id.login_button);
		login.setOnClickListener(new LoginBtnOnClickListener());
		// 晃动动画
		shake = AnimationUtils.loadAnimation(this, R.anim.shake);

		SharedPreferences sp = getSharedPreferences(getPackageName(),
				MODE_PRIVATE);
		username.setText(sp.getString("username", ""));
		password.setText(sp.getString("password", ""));
		editor = sp.edit();

		jObject = new JSONObject();
		msgHandler = new MsgHandler();
	}

	private String getLoginParams(String username, String password) {
		try {
			editor.putString("username", username);
			editor.putString("password", password);
			editor.commit();
			jObject.put("url", Constants.TestLoginUrl);// 真实的提交地址
			jObject.put("method", Constants.POST);// post提交(注意大小写)
			// --------------自定义n多headers--------------
			JSONObject hObject = new JSONObject();
			hObject.put("Connection", "keep-alive");// 保持alive(可以省略)
			hObject.put("Q-UA", "this is Q-UA");// 你懂得ua(可以省略)
			hObject.put(
					"User-Agent",
					"Mozilla/5.0 (Windows NT 6.2; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.59 QQ/7.2.14810.201 Safari/537.36");
			hObject.put("Referer", "http://www.baidu.com");// referer(可以省略)
			hObject.put("Cookie", "");// cookie(可以省略)
			// 把headers添加到jObject的headers字段里面
			jObject.put("headers", hObject);/////////////////////添加头
			// --------------------------------------------
			// --------------真实的表单内容(json格式)--------------
			JSONObject bObject = new JSONObject();
			bObject.put("username", username);
			bObject.put("password", password);
			jObject.put("body", bObject);///////////////////////添加表单
			// --------------------------------------------
			Log.d(jObject.toString());// 打印完整的表单
			// 加密
			String enData = Tools.getTools().enData(jObject.toString());
			Log.d(enData);// 打印加密的表单
			return enData;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			Message msg = Message.obtain();
			msg.what = Constants.ERROR;
			msg.obj = e.getMessage();
			msgHandler.sendMessage(msg);
			e.printStackTrace();
		}
		return "";
	}

	private void login(String username, String password) {
		HttpUtil.post(Constants.HOST,
				new RequestParams("data", getLoginParams(username, password)),
				new AsyncHttpResponseHandler(true) {

					@Override
					public void onSuccess(int statusCode, Header[] headers,
							byte[] responseBody) {
						// TODO Auto-generated method stub
						String html;
						html = new String(responseBody);
						Log.d(html);// 得到服务器返回的加密数据
						html = Tools.getTools().deData(html);// 界面服务器数据
						Log.d(html);
						Message msg = Message.obtain();
						msg.obj = html;
						msg.what = Constants.DATA;
						msgHandler.sendMessage(msg);
					}

					@Override
					public void onFailure(int statusCode, Header[] headers,
							byte[] responseBody, Throwable error) {
						// TODO Auto-generated method stub
						Message msg = Message.obtain();
						msg.what = Constants.ERROR;
						msg.obj = error.getMessage();
						msgHandler.sendMessage(msg);
						error.printStackTrace();
					}
				});
	}

	@SuppressLint("HandlerLeak")
	class MsgHandler extends Handler {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			switch (msg.what) {
			case Constants.DATA:
				Intent intent = new Intent(LoginActivity.this,
						ResultActivity.class);
				intent.putExtra("data", (String) msg.obj);
				startActivity(intent);
				break;
			case Constants.ERROR:
				AppMsg.cancelAll(LoginActivity.this);// 取消之前的所有提示队列
				AppMsg.makeText(LoginActivity.this, (String) msg.obj,
						AppMsg.STYLE_ALERT).show();
				break;
			}
			super.handleMessage(msg);
		}

	}

	class LoginBtnOnClickListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			String uname = username.getText().toString().trim();
			String upass = password.getText().toString().trim();
			if (uname == null || uname.length() == 0) {
				AppMsg.cancelAll(LoginActivity.this);// 取消之前的所有提示队列
				AppMsg.makeText(LoginActivity.this, "请输入账号", AppMsg.STYLE_ALERT)
						.show();
				uLayout.startAnimation(shake);
				return;
			}
			if (upass == null || upass.length() == 0) {
				AppMsg.cancelAll(LoginActivity.this);
				AppMsg.makeText(LoginActivity.this, "请输入密码", AppMsg.STYLE_ALERT)
						.show();
				pLayout.startAnimation(shake);
				return;
			}
			login(uname, upass);
		}

	}

}
