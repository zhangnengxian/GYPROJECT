package cc.dfsoft.project.biz.base.constructmanage.enums;

public enum RoadTypeEnum {

	ASPHALT_ROAD("1", "沥青路"),
	DIRT_ROAD("2", "土路"), 
	CONCRETE_ROAD("3", "砼路"), 
	GREEN_ROAD("4", "绿土路"), 
	TILE_ROAD("5", "花砖路");

	private final String value;

	private final String message;


	RoadTypeEnum(String value, String message) {
		this.value = value;
		this.message = message;
	}

	public String getValue() {
		return value;
	}

	public String getMessage() {
		return message;
	}

	public static RoadTypeEnum valueof(String value) {
		for (RoadTypeEnum e : RoadTypeEnum.values()) {
			if (e.getValue().equals(value)) {
				return e;
			}
		}
		return null;
	}
}
