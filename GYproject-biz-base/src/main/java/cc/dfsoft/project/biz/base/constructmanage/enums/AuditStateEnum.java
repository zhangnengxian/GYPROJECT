package cc.dfsoft.project.biz.base.constructmanage.enums;

public enum AuditStateEnum {

	NOT_PASS("1", "未通过"),
	TO_AUDIT("2", "待审核"),
	PUSHED("4", "已推送"),
	PASSED("3", "通过"); 


	private final String value;

	private final String message;


	AuditStateEnum(String value, String message) {
		this.value = value;
		this.message = message;
	}

	public String getValue() {
		return value;
	}

	public String getMessage() {
		return message;
	}

	public static AuditStateEnum valueof(String value) {
		for (AuditStateEnum e : AuditStateEnum.values()) {
			if (e.getValue().equals(value)) {
				return e;
			}
		}
		return null;
	}
}
