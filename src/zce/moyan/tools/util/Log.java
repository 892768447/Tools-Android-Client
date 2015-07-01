package zce.moyan.tools.util;

/**
 * 
 * Created on 2015年6月24日
 * 
 * @author: ヽoo悾絔℅o。
 * @email: 892768447@qq.com
 * @description:
 * 
 */
public class Log {

	private Log() {
	}

	/**
	 * 获取类名和行号
	 * 
	 * @return
	 */
	private static String getTag() {
		StackTraceElement[] stackTraceElements = Thread.currentThread()
				.getStackTrace();
		String fullClassName = stackTraceElements[4].getClassName();
		String className = fullClassName.substring(fullClassName
				.lastIndexOf(".") + 1);
		int lineNumber = stackTraceElements[4].getLineNumber();
		return className + ":" + lineNumber;
	}

	/**
	 * verbose
	 * 
	 * @param message
	 */
	public static void v(String message) {
		android.util.Log.v(getTag(), message);
	}

	/**
	 * error
	 * 
	 * @param message
	 */
	public static void e(String message) {
		android.util.Log.e(getTag(), message);
	}

	/**
	 * waring
	 * 
	 * @param message
	 */
	public static void w(String message) {
		android.util.Log.w(getTag(), message);
	}

	/**
	 * debug
	 * 
	 * @param message
	 */
	public static void d(String message) {
		android.util.Log.d(getTag(), message);
	}

	/**
	 * info
	 * 
	 * @param message
	 */
	public static void i(String message) {
		android.util.Log.i(getTag(), message);
	}
}
