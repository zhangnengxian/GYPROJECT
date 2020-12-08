package cc.dfsoft.project.biz.base.constructmanage.enums;

public enum FinishStateEnum {
	NOT_FINISH("0", "未完成"),
	ALREADY_FINISHED("1", "已完成");

	private final String value;

	private final String message;


	FinishStateEnum(String value, String message) {
		this.value = value;
		this.message = message;
	}

	public String getValue() {
		return value;
	}

	public String getMessage() {
		return message;
	}

	public static FinishStateEnum valueof(String value) {
		for (FinishStateEnum e : FinishStateEnum.values()) {
			if (e.getValue().equals(value)) {
				return e;
			}
		}
		return null;
	}
}
