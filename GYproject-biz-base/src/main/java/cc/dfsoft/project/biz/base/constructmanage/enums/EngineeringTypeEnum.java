package cc.dfsoft.project.biz.base.constructmanage.enums;

public enum EngineeringTypeEnum {

	TRENCH("1", "管沟填砂"),
	ROCK("2", "土石方外运"),
	VEHICLE_LANE("3", "混凝土路面恢复、垫层、包封和沥青路面恢复"),
	BRICK("4", "砖砌管沟及包封"),
	RUBBLE("5", "毛石基础、管沟及堡坎恢复"),
	SIDEWALK("6", "人行道板恢复、道沿恢复、绿化苗木植被恢复"),
	WASTE_DITCH("7", "非正常施工产生的废沟开挖、恢复及外运"),
	RE_PRESSURE("8", "二次复压、气密性试验"),
	OTHER("9", "其他"),
	GROOVE_RECORD("10", "沟槽开挖"); 


	private final String value;

	private final String message;


	EngineeringTypeEnum(String value, String message) {
		this.value = value;
		this.message = message;
	}

	public String getValue() {
		return value;
	}

	public String getMessage() {
		return message;
	}

	public static EngineeringTypeEnum valueof(String value) {
		for (EngineeringTypeEnum e : EngineeringTypeEnum.values()) {
			if (e.getValue().equals(value)) {
				return e;
			}
		}
		return null;
	}
}
