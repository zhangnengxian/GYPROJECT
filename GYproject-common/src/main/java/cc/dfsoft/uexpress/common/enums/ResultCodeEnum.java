package cc.dfsoft.uexpress.common.enums;

/**
 * 结果枚举
 * @author 1919
 */
public enum ResultCodeEnum {
	
	/** ===================== 公共返回码 ===================== */
	SUCCESS("0000", "成功"), 
	FAIL("9999", "操作失败，请重试！"),
	PARAM_EMPTY("0001", "{0}参数为空！"),
	DATA_ERROR("0002", "{0}，数据异常！"),
	TABLE_INIT_FAILED("0003", "{0}初始化失败，请重试！");
	
	/** ===================== 抄表模块返回码开始 ===================== */
		
	private final String value;

	private final String message;

	ResultCodeEnum(String value, String message) {
		this.value = value;
		this.message = message;
	}

	public String getValue() {
		return value;
	}

	public String getMessage() {
		return message;
	}

	public static ResultCodeEnum valueof(String value) {
		for (ResultCodeEnum e : ResultCodeEnum.values()) {
			if (e.getValue().equals(value)) {
				return e;
			}
		}
		return null;
	}
}
