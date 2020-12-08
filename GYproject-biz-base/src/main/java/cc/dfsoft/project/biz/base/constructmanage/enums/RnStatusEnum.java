package cc.dfsoft.project.biz.base.constructmanage.enums;

public enum RnStatusEnum {

	
	TO_PUSH("1", "待推送"),
	TO_BACK("2", "待回复"),
	TO_CHECK("4", "待复查"),
	BACKED("3", "已回复"); 


	private final String value;

	private final String message;


	RnStatusEnum(String value, String message) {
		this.value = value;
		this.message = message;
	}

	public String getValue() {
		return value;
	}

	public String getMessage() {
		return message;
	}

	public static RnStatusEnum valueof(String value) {
		for (RnStatusEnum e : RnStatusEnum.values()) {
			if (e.getValue().equals(value)) {
				return e;
			}
		}
		return null;
	}
}
