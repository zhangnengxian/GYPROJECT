package cc.dfsoft.project.biz.base.constructmanage.enums;
/**
 * 停复工令状态枚举
 * @author liaoyq
 *
 */
public enum ShutdownStatusEnum {

	SHUTDOWN_ORDER("1", "待下达停工令"),
	REWORK_APPROVAL("2", "待复工报审"),
	APPROVALING("3","复工报审中"), 
	REWORK_ORDER("4","待复工"),
	PUSH_REWORK_ORDER("5","待下达复工令"),
	REWORK_ORDERED("6","已复工"); 


	private final String value;

	private final String message;


	ShutdownStatusEnum(String value, String message) {
		this.value = value;
		this.message = message;
	}

	public String getValue() {
		return value;
	}

	public String getMessage() {
		return message;
	}

	public static ShutdownStatusEnum valueof(String value) {
		for (ShutdownStatusEnum e : ShutdownStatusEnum.values()) {
			if (e.getValue().equals(value)) {
				return e;
			}
		}
		return null;
	}
}
