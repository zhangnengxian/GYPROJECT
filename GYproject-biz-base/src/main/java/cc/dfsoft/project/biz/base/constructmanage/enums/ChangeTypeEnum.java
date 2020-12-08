package cc.dfsoft.project.biz.base.constructmanage.enums;

public enum ChangeTypeEnum {

	USER_CHANGE("2", "用户变更"),
	CONSTRUCTION_CHANGE("1", "施工变更"); 


	private final String value;

	private final String message;


	ChangeTypeEnum(String value, String message) {
		this.value = value;
		this.message = message;
	}

	public String getValue() {
		return value;
	}

	public String getMessage() {
		return message;
	}

	public static ChangeTypeEnum valueof(String value) {
		for (ChangeTypeEnum e : ChangeTypeEnum.values()) {
			if (e.getValue().equals(value)) {
				return e;
			}
		}
		return null;
	}
}
