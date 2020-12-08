package cc.dfsoft.uexpress.common.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.aspectj.weaver.patterns.ThisOrTargetAnnotationPointcut;

import com.sun.org.glassfish.external.statistics.impl.StatisticImpl;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

/**
 * 对象转化为json对象时，格式化时间格式
 * @author wang.hui.jun
 * createTime 2019-05-17
 *
 */
public class JsonDateValueProcessor implements JsonValueProcessor {
	private  String  format = "yyyy-MM-dd";

	public JsonDateValueProcessor() {
		super();
	}

	public JsonDateValueProcessor(String format) {
		super();
		this.format = format;
	}

	@Override
	public Object processArrayValue(Object paramObject, JsonConfig paramJsonConfig) {
		return process(paramObject);
	}

	@Override
	public Object processObjectValue(String paramString, Object paramObject, JsonConfig paramJsonConfig) {
		return process(paramObject);
	}

	private Object process(Object value) {
		if (value instanceof Date) {
			SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.CHINA);
			return sdf.format(value);
		}
		return value == null ? "" : value.toString();
	}
	
	/**
	 * 对象转化为json对象时，格式化时间格式
	 * 王会军 2019-05-17
	 * params  object,formatdate
	 * @return jsonObject
	 */
	public static JSONObject JsonFomatDate(Object object,String formatdate) {
		JSONObject  jsonObject = new JSONObject();
		JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.registerJsonValueProcessor(Date.class,new JsonDateValueProcessor(formatdate));
        String jsonStr = jsonObject.fromObject(object, jsonConfig).toString();
        jsonObject = JSONObject.fromObject(jsonStr);
		return jsonObject;
		
	}
}
