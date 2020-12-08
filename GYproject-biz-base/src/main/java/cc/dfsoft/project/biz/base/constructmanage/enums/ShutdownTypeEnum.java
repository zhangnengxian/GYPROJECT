package cc.dfsoft.project.biz.base.constructmanage.enums;
/**
 * 停复工类型
 * @author liaoyq
 *
 */
public enum ShutdownTypeEnum {

	SHUTDOWN("1", "停工令"),
	REWORK("2", "复工令"); 


	private final String value;

	private final String message;


	ShutdownTypeEnum(String value, String message) {
		this.value = value;
		this.message = message;
	}

	public String getValue() {
		return value;
	}

	public String getMessage() {
		return message;
	}

	public static ShutdownTypeEnum valueof(String value) {
		for (ShutdownTypeEnum e : ShutdownTypeEnum.values()) {
			if (e.getValue().equals(value)) {
				return e;
			}
		}
		return null;
	}
}
