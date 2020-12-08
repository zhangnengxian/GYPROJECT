package cc.dfsoft.project.biz.base.constructmanage.enums;

public enum MaterialChangeTypeEnum {
	
	DESIGN_CHANGE("0", "变更材料"),
	ENGINEER_CHANGE("1", "签证材料"),
	MAIN_MATERIAL_TABLE("2", "主材表"); 
	
	private final String value;

	private final String message;


	MaterialChangeTypeEnum(String value, String message) {
		this.value = value;
		this.message = message;
	}

	public String getValue() {
		return value;
	}

	public String getMessage() {
		return message;
	}

	public static MaterialChangeTypeEnum valueof(String value) {
		for (MaterialChangeTypeEnum e : MaterialChangeTypeEnum.values()) {
			if (e.getValue().equals(value)) {
				return e;
			}
		}
		return null;
	}
	
}
