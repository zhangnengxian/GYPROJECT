package cc.dfsoft.project.biz.base.baseinfo.enums;

/**
 * 工程类型
 * @author Yuanyx
 *
 */
public enum ProjectTypeEnum {

	FIXED_PRICE("1", "固定价"),
	SUPPORT_EXPENSE("2", "配套费"),
	PROJECT_BUDGET("3", "工程概算"),
	MEDIUM_PRESSURE("4","中压管网");


    private final String value;
    
    private final String message;
    
    ProjectTypeEnum(String value, String message){
        this.value = value;
        this.message = message;
    }

    public String getValue() {
        return value;
    }
    
    public String getMessage(){
    	return message;
    }
    
    public static ProjectTypeEnum valueof(String value) {
    	for(ProjectTypeEnum e: ProjectTypeEnum.values()) {
    		if(e.getValue().equals(value)) {
    			return e;
    		}
    	}
    	return null;
    }
}
