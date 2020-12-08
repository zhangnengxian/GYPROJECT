package cc.dfsoft.uexpress.common.exception;

import java.text.MessageFormat;
import cc.dfsoft.uexpress.common.enums.ResultCodeEnum;

/**
 * 统一系统运行时异常
 * 用于封装express业务类错误代码和错误描述，同时区别于系统其他非检查类异常
 * 错误代码和错误描述由结果枚举类封装，也可以自定义输入，建议由枚举统一定义，便于集中分类管理
 */
public class ExpressException extends RuntimeException {

    /** serialVersionUID */
	private static final long serialVersionUID = 1777501034240262894L;

    /** 错误代码 */
    private String errorCode;
    
    /** 错误描述 */
    private String errorMsg;

    /** 结果枚举 */
    private ResultCodeEnum resultCodeEnum;

    /**
     * 构造函数
     * @param errorCode
     * @param errorMsg
     */
    public ExpressException(String errorCode, String errorMsg) {
        super(errorMsg);
        this.errorCode = errorCode;
    }

    /**
     * 构造函数
     * @param resultCodeEnum
     * @param args 可变参数
     * 			         如果对应结果码描述定义有占位符，则根据实际需要传参，否则可不传
     */
    public ExpressException(ResultCodeEnum resultCodeEnum, Object... args) {
    	super(args.length == 0 ? resultCodeEnum.getMessage() 
    			: MessageFormat.format(resultCodeEnum.getMessage(), args));
        this.resultCodeEnum = resultCodeEnum;
        this.errorCode = resultCodeEnum.getValue();
    }

	public String getErrorCode() {
		return errorCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public ResultCodeEnum getResultCodeEnum() {
		return resultCodeEnum;
	}

}
