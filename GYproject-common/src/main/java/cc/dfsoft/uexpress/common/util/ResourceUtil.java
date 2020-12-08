package cc.dfsoft.uexpress.common.util;

import java.io.File;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 获取指定的properties资源信息 默认为CONFIG_ROOT_STRING（=“config")下的properties
 * 
 * @author:yoyo yan
 * @class info:smartMeter-server-cc.dfsoft.util-ResourceUtil.java
 * @Desc:
 * @createTime:2015Mar 17, 2015
 * @modifier:2015Mar 17, 2015
 * @modifyTime:2015Mar 17, 2015
 * @modifyDesc:2015Mar 17, 2015
 * @version:
 */
public class ResourceUtil {
	private static final Logger logger = LoggerFactory
			.getLogger(ResourceUtil.class);

	private static final String CONFIG_ROOT_STRING = "config";
	private static final String JDBC_CONFIG = CONFIG_ROOT_STRING
			+ File.separator + "jdbc";
	private static final String APP_CONFIG = CONFIG_ROOT_STRING
			+ File.separator + "app";
	private static final String EVN_CONFIG = CONFIG_ROOT_STRING
			+ File.separator + "evn";
	private static final String WEB_CONFIG = CONFIG_ROOT_STRING
			+ File.separator + "web";
	private static  ResourceBundle JDBC_RESOURCE_BUNDLE;
	private static  ResourceBundle APP_RESOURCE_BUNDLE;
	private static  ResourceBundle EVN_RESOURCE_BUNDLE;
	private static  ResourceBundle WEB_RESOURCE_BUNDLE;
	static {
		try {
			JDBC_RESOURCE_BUNDLE = ResourceBundle.getBundle(JDBC_CONFIG);
		} catch (Exception e) {
			logger.warn("未找到JDBC配置文件{}", JDBC_CONFIG);
		}
		try {
			APP_RESOURCE_BUNDLE = ResourceBundle.getBundle(APP_CONFIG);
		} catch (Exception e) {
			logger.warn("未找到APP配置文件{}", APP_CONFIG);
		}
		try {
			EVN_RESOURCE_BUNDLE = ResourceBundle.getBundle(EVN_CONFIG);
		} catch (Exception e) {
			logger.warn("未找到EVN配置文件{}", EVN_CONFIG);
		}

		try {
			WEB_RESOURCE_BUNDLE = ResourceBundle.getBundle(WEB_CONFIG);
		} catch (Exception e) {
			logger.warn("未找到WEB配置文件{}", WEB_CONFIG);
		}
	}

	/**
	 * 获取数据库资源信息
	 * 
	 * @param key
	 * @return
	 */
	public static final String getJdbcPropertiesValue(String key) {
		if (JDBC_RESOURCE_BUNDLE == null) {
			return "";
		}
		return JDBC_RESOURCE_BUNDLE.getString(key);
	}

	/**
	 * 获取应用程序资源信息
	 * 
	 * @param key
	 * @return
	 */
	public static final String getAppPropertiesValue(String key) {
		if (APP_RESOURCE_BUNDLE == null) {
			return "";
		}
		return APP_RESOURCE_BUNDLE.getString(key);
	}

	/**
	 * 获取环境配置资源信息
	 * 
	 * @param key
	 * @return
	 */
	public static final String getEvnPropertiesValue(String key) {
		if (EVN_RESOURCE_BUNDLE == null) {
			return "";
		}
		return EVN_RESOURCE_BUNDLE.getString(key);
	}

	/**
	 * 获取WEB配置资源信息
	 * 
	 * @param key
	 * @return
	 */
	public static final String getWebPropertiesValue(String key) {
		if (WEB_RESOURCE_BUNDLE == null) {
			return "";
		}
		return WEB_RESOURCE_BUNDLE.getString(key);
	}

	/**
	 * 获取指定的配置资源信息
	 * 
	 * @param resourceName
	 * @param key
	 * @return
	 */
	public static final String getResourcePropertiesValue(String resourceName,
			String key) {
		return EVN_RESOURCE_BUNDLE.getString(key);
	}

	static{
		System.out.println("haha");
	}
	public static void main(String[] args) {
		
	}
}
