package cc.dfsoft.project.biz.base.plan.enums;

public enum ProjectOperateEnum {
	
	
	PROJECT_PLAN("1","工程计划"),
	CHARGE("2","付款"),
	MATERIAL_CHANGE("3","材料变更"),
	MATERIAL_VISA("4","工程签证"),
	TECHNOLOGY_TELL("5","工程交底"),
	DESIGN_DRAWING("6","设计出图");
	
	private final String value;
    
    private final String message;
    
    
    ProjectOperateEnum(String value, String message){
        this.value = value;
        this.message = message;
    }

    public String getValue() {
        return value;
    }
    
    public String getMessage(){
    	return message;
    }
    
	public static ProjectOperateEnum valueof(String value) {
    	for(ProjectOperateEnum e: ProjectOperateEnum.values()) {
    		if(e.getValue().equals(value)) {
    			return e;
    		}
    	}
    	return null;
    }	

}
