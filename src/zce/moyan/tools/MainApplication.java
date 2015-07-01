package zce.moyan.tools;

import android.annotation.SuppressLint;
import android.app.Application;
import android.os.StrictMode;

/**
 * 
 * Created on 2015年6月24日
 * 
 * @author: ヽoo悾絔℅o。
 * @email: 892768447@qq.com
 * @description:
 * 
 */
public class MainApplication extends Application {

	private static final boolean DEVELOPER_MODE = true;

	@SuppressLint("NewApi")
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		// 发现调试器退出
		// if ((getApplicationInfo().flags &= ApplicationInfo.FLAG_DEBUGGABLE)
		// != 0) {
		// android.os.Process.killProcess(android.os.Process.myPid());
		// }
		// 本机测试
		if (DEVELOPER_MODE) {
			StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
					.detectDiskReads().detectDiskWrites().detectNetwork()
					.penaltyLog().build());
		}
	}
}
