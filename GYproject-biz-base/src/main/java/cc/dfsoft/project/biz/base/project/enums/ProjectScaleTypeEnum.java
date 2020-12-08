package cc.dfsoft.project.biz.base.project.enums;

public enum ProjectScaleTypeEnum {
	
	
	CAULDRON("1", "大锅灶"),
	FURNACE("2", "工业燃气炉/锅炉");

    private final String value;
    
    private final String message;
    
    ProjectScaleTypeEnum(String value, String message){
        this.value = value;
        this.message = message;
    }

    public String getValue() {
        return value;
    }
    
    public String getMessage(){
    	return message;
    }
    
    public static ProjectScaleTypeEnum valueof(String value) {
    	for(ProjectScaleTypeEnum e: ProjectScaleTypeEnum.values()) {
    		if(e.getValue().equals(value)) {
    			return e;
    		}
    	}
    	return null;
    }
}
