package cc.dfsoft.uexpress.common.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class CoordinatesUtil {

	public static Map<String, Object> coordinatesConversion(String x, String y){
		Map<String, Object> map = new HashMap<>();
		BufferedReader in = null;
		try {
			if(StringUtils.isNotBlank(x) && StringUtils.isNotBlank(y)){
				String url = "http://api.map.baidu.com/geoconv/v1/?coords="+x+","+y+"&from=1&to=5&ak=N0XHsLsP2i4CYgtWhOTkulASZ27CqGwB";
				URL realUrl = new URL(url);
				//打开和URL之间的链接
				URLConnection connection = realUrl.openConnection();
				//建立实际链接
				connection.connect();
				//定义 BufferredReader输入流来读取URL的响应
				in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				String line = in.readLine();
				JSONObject json = JSONObject.fromObject(line); 
				String result = json.getString("result");
				JSONArray ja = JSONArray.fromObject(result);
				//遍历jsonArray
				Iterator<Object> it = ja.iterator();
				while(it.hasNext()){
					JSONObject obj = (JSONObject) it.next();
					if(obj.getString("x")!=null){
						map.put("x", obj.getString("x"));
					}
					if(obj.getString("y")!=null){
						map.put("y", obj.getString("y"));
					}
				}
				return map;
			}
		} catch (Exception e) {
			//System.out.println("发送GET请求出现异常！" + e);
			e.printStackTrace();
		} finally {
			try {
				if(in!=null){
					in.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return null;
	}
	
	//测试使用
	/*public static void main(String[] args) {
		Map<String, Object> map = coordinatesConversion("114.21892734521", "29.575429778924");
	}*/
	
}
