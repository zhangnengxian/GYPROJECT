package cc.dfsoft.project.biz.base.constructmanage.enums;

public enum NdeWeldMethodEnum {

	ARC_WELD("1", "电弧焊"),
	ARGON_WELD("2", "氩弧焊"),
	TIG_WELD("3", "氩电联焊"),
	AUTO_WELD("4", "自动焊");


	private final String value;

	private final String message;


	NdeWeldMethodEnum(String value, String message) {
		this.value = value;
		this.message = message;
	}

	public String getValue() {
		return value;
	}

	public String getMessage() {
		return message;
	}

	public static NdeWeldMethodEnum valueof(String value) {
		for (NdeWeldMethodEnum e : NdeWeldMethodEnum.values()) {
			if (e.getValue().equals(value)) {
				return e;
			}
		}
		return null;
	}
}
