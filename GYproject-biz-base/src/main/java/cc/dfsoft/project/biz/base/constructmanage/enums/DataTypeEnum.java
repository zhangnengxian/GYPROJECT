package cc.dfsoft.project.biz.base.constructmanage.enums;

/**
 * 资料类型
 * @author fuliwei
 *
 */
public enum DataTypeEnum {
	
	CRAFT_WORK("1", "工艺"),
	ALARM_PROJ("2", "报警");
	
	private final String value;

	private final String message;


	DataTypeEnum(String value, String message) {
		this.value = value;
		this.message = message;
	}

	public String getValue() {
		return value;
	}

	public String getMessage() {
		return message;
	}

	public static DataTypeEnum valueof(String value) {
		for (DataTypeEnum e : DataTypeEnum.values()) {
			if (e.getValue().equals(value)) {
				return e;
			}
		}
		return null;
	}
}
