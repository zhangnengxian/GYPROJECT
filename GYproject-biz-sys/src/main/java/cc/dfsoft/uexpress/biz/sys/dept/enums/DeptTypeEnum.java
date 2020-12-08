package cc.dfsoft.uexpress.biz.sys.dept.enums;

/**
 * 部门类型枚举
 * @author pengtt
 *
 */
public enum DeptTypeEnum {
	
	
	GROUP_COMPANY("1", "集团", "11"), 
	
	SUBCOMPANY("2", "分公司", "01"), 
	
	MANAGE_DEPT("3", "部门", "01"),
	
	BUSINESS_GROUPS("4", "业务组", "01");
	
	
	private final String value;

	private final String message;
	
	/** 部门编码初始值 */
	private final String initVal;

	DeptTypeEnum(String value, String message, String initVal) {
		this.value = value;
		this.message = message;
		this.initVal = initVal;
	}

	public String getValue() {
		return value;
	}

	public String getMessage() {
		return message;
	}
	
	public String getInitVal() {
		return initVal;
	}

	public static DeptTypeEnum valueof(String value) {
		for (DeptTypeEnum e : DeptTypeEnum.values()) {
			if (e.getValue().equals(value)) {
				return e;
			}
		}
		return null;
	}
}
