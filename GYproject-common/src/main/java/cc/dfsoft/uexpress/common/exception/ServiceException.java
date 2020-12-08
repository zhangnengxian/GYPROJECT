package cc.dfsoft.uexpress.common.exception;

import java.io.Serializable;

/**
 * service层自定义异常
 * 
 * @author Administrator
 * @version 2019年9月29日
 */
public class ServiceException extends Exception implements Serializable{
	/**序列化id*/
	private static final long SerialVersionUID = 0L;
	/** 自定义错误码 */
	private final String code;
	/** 自定义异常信息 */
	private final String message;

	/** 构造函数 */
	public ServiceException(String code, String message) {
		// TODO Auto-generated constructor stub
		this.code = code;
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

	@Override
	public String toString() {
		return "ServiceException [code=" + code + ", message=" + message + "]";
	}

}
