package cc.dfsoft.uexpress.biz.sys.dept.enums;

/**
 * 单位类型枚举
 * @author pengtt
 *
 */
public enum UnitTypeEnum {
	
	GAS_GROUP("1", "燃气集团","0"), 
	
	/*DESIGN_UNIT("2", "设计单位","0"), */
	
	DETECTION_UNIT("3", "检测单位","1"), 
	
	CONSTRUCTION_CONTROL_UNIT("4", "监理单位","1"), 
	
	SUBCONTRACTING_UNIT("5", "分包单位","1");
	
	private final String value;

	private final String message;
	
	private final String type;
	
	UnitTypeEnum(String value, String message,String type) {
		this.value = value;
		this.message = message;
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public String getMessage() {
		return message;
	}
	
	public String getType() {
		return type;
	}
	public static UnitTypeEnum valueof(String value) {
		for (UnitTypeEnum e : UnitTypeEnum.values()) {
			if (e.getValue().equals(value)) {
				return e;
			}
		}
		return null;
	}

}
