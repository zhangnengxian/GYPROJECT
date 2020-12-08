package cc.dfsoft.project.biz.base.constructmanage.enums;

public enum InspectionListTypeEnum {

	SECURITY("0", "安全检查"),
	QUALITY("1", "质量保证");

	private final String value;

	private final String message;


	InspectionListTypeEnum(String value, String message) {
		this.value = value;
		this.message = message;
	}

	public String getValue() {
		return value;
	}

	public String getMessage() {
		return message;
	}

	public static InspectionListTypeEnum valueof(String value) {
		for (InspectionListTypeEnum e : InspectionListTypeEnum.values()) {
			if (e.getValue().equals(value)) {
				return e;
			}
		}
		return null;
	}
}
