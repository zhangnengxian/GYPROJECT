package cc.dfsoft.project.biz.base.constructmanage.enums;

public enum ShutdownPushStatusEnum {

	PUSHED(1, "已推送"),
	NO_PUSH(0, "未推送"); 

	private final Integer value;

	private final String message;


	ShutdownPushStatusEnum(Integer value, String message) {
		this.value = value;
		this.message = message;
	}

	public Integer getValue() {
		return value;
	}

	public String getMessage() {
		return message;
	}

	public static ShutdownPushStatusEnum valueof(Integer value) {
		for (ShutdownPushStatusEnum e : ShutdownPushStatusEnum.values()) {
			if (e.getValue().equals(value)) {
				return e;
			}
		}
		return null;
	}
}
