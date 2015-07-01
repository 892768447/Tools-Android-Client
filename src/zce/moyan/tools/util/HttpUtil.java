package zce.moyan.tools.util;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.BinaryHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
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
public class HttpUtil {

	private static AsyncHttpClient client = new AsyncHttpClient(); // 实例话对象

	static {
		client.setTimeout(20000); // 设置链接超时，如果不设置，默认为10s
	}

	/**
	 * 设置超时时间
	 * 
	 * @param value
	 */
	public static void setTimeout(int value) {
		client.setTimeout(value);
	}

	/**
	 * 不带参数的get请求
	 * 
	 * @param url
	 * @param res
	 */
	public static void get(String url, AsyncHttpResponseHandler res) {
		client.get(url, res);
	}

	/**
	 * 带参数的get请求
	 * 
	 * @param url
	 * @param params
	 * @param res
	 */
	public static void get(String url, RequestParams params,
			AsyncHttpResponseHandler res) {
		client.get(url, params, res);
	}

	/**
	 * 带参数的post请求
	 * 
	 * @param url
	 * @param params
	 * @param res
	 */
	public static void post(String url, RequestParams params,
			AsyncHttpResponseHandler res) {
		client.post(url, params, res);
	}

	/**
	 * 不带参数的json get请求
	 * 
	 * @param url
	 * @param res
	 */
	public static void get(String url, JsonHttpResponseHandler res) {
		client.get(url, res);
	}

	/**
	 * 带参数的json get请求
	 * 
	 * @param url
	 * @param params
	 * @param res
	 */
	public static void get(String url, RequestParams params,
			JsonHttpResponseHandler res) {
		client.get(url, params, res);
	}

	/**
	 * 带参数的json post请求
	 * 
	 * @param url
	 * @param params
	 * @param res
	 */
	public static void post(String url, RequestParams params,
			JsonHttpResponseHandler res) {
		client.post(url, params, res);
	}

	/**
	 * 下载数据使用，会返回byte数据
	 * 
	 * @param uString
	 * @param bHandler
	 */
	public static void get(String uString, BinaryHttpResponseHandler bHandler) {
		client.get(uString, bHandler);
	}

	public static AsyncHttpClient getClient() {
		return client;
	}
}
