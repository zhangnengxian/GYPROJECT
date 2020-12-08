package cc.dfsoft.uexpress.common.util;

import java.text.MessageFormat;

import org.slf4j.Logger;

/**
 * 日志工具类
 * @author 1918
 */
public class LoggerUtil {

	/**
	 * 记录错误日志
	 * @param logger
	 * @param e 异常
	 * @param msg 自定义错误信息
	 * @param param 可变参数，当错误信息包含占位符时传入
	 */
	public static void error(Logger logger, Throwable e, String msg, Object... param) {
		if(logger.isErrorEnabled()) {
			logger.error(MessageFormat.format(msg, param), e);
		}
	}
	
	/**
	 * 记录警告日志
	 * @param logger
	 * @param msg 自定义警告信息
	 * @param param 可变参数，当警告信息包含占位符时传入
	 */
	public static void warn(Logger logger, String msg, Object... param) {
		if(logger.isWarnEnabled()) {
			logger.warn(MessageFormat.format(msg, param));
		}
	}
	
	/**
	 * 记录信息日志
	 * @param logger
	 * @param msg 自定义信息
	 * @param param 可变参数，当信息包含占位符时传入
	 */
	public static void info(Logger logger, String msg, Object... param) {
		if(logger.isInfoEnabled()) {
			logger.info(MessageFormat.format(msg, param));
		}
	}
	
}
