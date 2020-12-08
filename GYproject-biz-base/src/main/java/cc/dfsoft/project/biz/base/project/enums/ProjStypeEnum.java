package cc.dfsoft.project.biz.base.project.enums;

public enum ProjStypeEnum {

	MULTILAYER("1101", "多层(户)", "11"),
	HIGH_RISE("1102", "高层(户)", "11"), 
	MULTILAYER_WALL_HANGING("1103", "多层壁挂(户)","11"), 
	HIGH_RISE_WALL_HANGING("1104", "高层壁挂(户)", "11"), 
	VILLA("1105", "别墅(户)", "11"), 
	PRIVATE_RESIDENCE("1106","私宅(户)", "11"),
	RESTAURANT("1201", "餐厅(座)", "12"), 
	HEATING_BOILER("1202", "采暖锅炉(座)","12"), 
	NO_HEATING_BOILER("1203", "非采暖锅炉(座)", "12"), 
	INDUSTRY("1204", "工业(座)", "12"), 
	FACADE_ROOM("1205", "门面房(座)", "12"), 
	OTHERS("1206", "其他", "12"),
	VEHICLE_USED("1301", "车用", "13");

	private final String value;

	private final String message;

	private final String parentID;

	ProjStypeEnum(String value, String message, String parentID) {
		this.value = value;
		this.message = message;
		this.parentID = parentID;
	}

	public String getValue() {
		return value;
	}

	public String getMessage() {
		return message;
	}

	public String getParentID() {
		return parentID;
	}

	public static ProjStypeEnum valueof(String value) {
		for (ProjStypeEnum e : ProjStypeEnum.values()) {
			if (e.getValue().equals(value)) {
				return e;
			}
		}
		return null;
	}
}
