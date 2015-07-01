package zce.moyan.tools.util;

import java.io.UnsupportedEncodingException;

import android.util.Base64;

/**
 * 
 * Created on 2015年6月24日
 * 
 * @author: ヽoo悾絔℅o。
 * @email: 892768447@qq.com
 * @description:
 * 
 */
public class Tools {

	private volatile static Tools tools;

	static {
		try {
			Log.d("loadLibrary");
			System.loadLibrary("tools");
		} catch (UnsatisfiedLinkError ule) {
			ule.printStackTrace();
		}
	}

	private Tools() {
	}

	public static Tools getTools() {
		if (tools == null) {
			synchronized (Tools.class) {
				if (tools == null) {
					tools = new Tools();
				}
			}
		}
		return tools;
	}

	public native String encrypt(String data);

	public native String decrypt(String data);

	public String deData(String data) {
		// 对服务器返回的数据解密
		Log.d("deData");
		String datas = null;
		try {
			datas = decrypt(data);
			datas = new String(Base64.decode(datas, Base64.DEFAULT));
		} catch (Exception e) {
			e.printStackTrace();
			datas = "";
		}
		return datas;
	}

	public String enData(String data) {
		// 对字符串先base64编码
		Log.d("enData");
		String datas = null;
		try {
			datas = Base64.encodeToString(data.getBytes("utf-8"),
					Base64.DEFAULT);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			datas = Base64.encodeToString(data.getBytes(), Base64.DEFAULT);
		}
		try {
			return encrypt(datas.replace("\r\n", "").replace("\n", ""));
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

}
