package zce.moyan.tools;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ScrollView;
import android.widget.TextView;

/**
 * 
 * Created on 2015年6月24日
 * 
 * @author: ヽoo悾絔℅o。
 * @email: 892768447@qq.com
 * @description:
 * 
 */
public class ResultActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ScrollView scrollView = new ScrollView(this);
		TextView textView = new TextView(this);
		scrollView.addView(textView);

		String data = getIntent().getStringExtra("data");
		if (data != null) {
			textView.setText(data);
		}
		setContentView(scrollView);
	}
}
