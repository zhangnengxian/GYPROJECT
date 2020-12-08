package cc.dfsoft.uexpress.common.util;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.Map;

/**
 * Http请求公用类
 * @author 1919
 *
 */
public class HttpUtil {

	private static Logger logger = LoggerFactory.getLogger(HttpUtil.class); // 日志记录

	/**
	 * post请求
	 * @param url 路径
	 * @param paramMap 参数
	 * @return
	 */
	public static String postMethodURL(String url, Map<String, Object> paramMap) {
		String response = null;
		// 参数设置
		NameValuePair[] paramValues = new NameValuePair[paramMap.size()];
		int i = 0;
		for (Map.Entry<String, Object> entry : paramMap.entrySet()) {
			String key = entry.getKey();
			String value = (String) entry.getValue();
			NameValuePair pair = new NameValuePair(key, value);
			paramValues[i] = pair;
			i++;
		}

		HttpClient client = new HttpClient();
		// 使用POST方法
		PostMethod method = new PostMethod(url);
		method.setRequestHeader("Content-Type",
				"application/x-www-form-urlencoded;charset=utf-8");
		method.setRequestBody(paramValues);
		method.releaseConnection();
		try {
			client.executeMethod(method);
			return method.getResponseBodyAsString();
		} 
		catch (Exception e) {
			e.printStackTrace();
			return response;
		} 
	}

	/**
	 * httpPost
	 * @param url 路径
	 * @param jsonParam 参数
	 * @return
	 * @throws
	 */
	public static JSONObject httpPost(String url, JSONObject jsonParam)
			throws Exception {
		return httpPost(url, jsonParam, false);
	}

	/**
	 * post请求
	 * @param url 路径
	 * @param jsonParam 参数
	 * @param noNeedResponse 是否需要返回结果
	 * @return
	 * @throws
	 */
	public static JSONObject httpPost(String url, JSONObject jsonParam,
			boolean noNeedResponse) throws Exception {
		// post请求返回结果
		DefaultHttpClient httpClient = new DefaultHttpClient();
		JSONObject jsonResult = null;
		HttpPost method = new HttpPost(url);
		if (null != jsonParam) {
			// 解决中文乱码问题
			StringEntity entity = new StringEntity(jsonParam.toString(),
					"utf-8");
			entity.setContentEncoding("UTF-8");
			entity.setContentType("application/json");
			method.setEntity(entity);
		}
		HttpResponse result = httpClient.execute(method);
		url = URLDecoder.decode(url, "UTF-8");
		/** 请求发送成功，并得到响应 **/
		if (result.getStatusLine().getStatusCode() == 200) {
			String str = "";
			/** 读取服务器返回过来的json字符串数据 **/
			str = EntityUtils.toString(result.getEntity());
			if (noNeedResponse) {
				return null;
			}
			/** 把json字符串转换成json对象 **/
			jsonResult = JSONObject.parseObject(str);
		}
		return jsonResult;
	}

	/**
	 * 发送get请求
	 * @param url 路径
	 * @return
	 */
	public static JSONObject httpGet(String url) {
		// get请求返回结果
		JSONObject jsonResult = null;
		try {
			DefaultHttpClient client = new DefaultHttpClient();
			// 发送get请求
			HttpGet request = new HttpGet(url);
			HttpResponse response = client.execute(request);

			/** 请求发送成功，并得到响应 **/
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				/** 读取服务器返回过来的json字符串数据 **/
				String strResult = EntityUtils.toString(response.getEntity());
				/** 把json字符串转换成json对象 **/
				jsonResult = JSONObject.parseObject(strResult);
				url = URLDecoder.decode(url, "UTF-8");
			} else {
				logger.error("get请求提交失败:" + url);
			}
		} catch (IOException e) {
			logger.error("get请求提交失败:" + url, e);
		}
		return jsonResult;
	}
}
