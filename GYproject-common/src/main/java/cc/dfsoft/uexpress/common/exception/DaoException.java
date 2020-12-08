package cc.dfsoft.uexpress.common.exception;

import java.io.Serializable;

import javassist.SerialVersionUID;

/**
 * Dao层自定义异常
 * 
 * @author Administrator
 * @version 2019年9月29日
 */
public class DaoException extends Exception implements Serializable{
	/**序列化id*/
    private static final long SerialVersionUID = 0L;
	/** 错误码 */
	private final String code;
	/** 错误信息 */
	private final String message;

	/**
	 * 构造函数
	 * @param code
	 * @param message
	 */
	public DaoException(String code, String message) {
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
		return "DaoException [code=" + code + ", message=" + message + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}

}
