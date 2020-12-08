package cc.dfsoft.uexpress.common.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 配置文件读取工具
 * @author 1919
 *
 */
public class ConfigReader {

	private static final Logger logger = LoggerFactory.getLogger(ConfigReader.class);
	
	 /** 租户管理平台服务地址 */
    public static final String TENANT_SERVICE_URL = getProperties("config/application.properties").getProperty("tenant_service_url");
	
	private static ConfigReader configreader = null;

	private ConfigReader() {

	}

	public static ConfigReader initConfigreader() {
		if (configreader == null) {
			configreader = new ConfigReader();
		}
		return configreader;
	}

	private Properties initProperties(String path) {
		InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(path);
		Properties p = new Properties();
		try {
			p.load(inputStream);
		} 
		catch (IOException e) {
			logger.error("读取配置文件出错，错误信息为：" + e.getMessage());
		} 
		finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} 
				catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return p;
	}

	public static Properties getProperties(String path) {
		ConfigReader reader = new ConfigReader();
		return reader.initProperties(path);
	}
}
