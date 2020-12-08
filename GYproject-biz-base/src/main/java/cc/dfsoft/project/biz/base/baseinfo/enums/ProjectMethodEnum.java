package cc.dfsoft.project.biz.base.baseinfo.enums;

/**
 * 立项方式
 * @author Yuanyx
 *
 */
public enum ProjectMethodEnum {

	PROJECT_APPLY("1", "立项申请"),
	PLAN_PROJECT("2", "计划立项"),
	STATION_PROJECT("3", "场站立项");


    private final String value;
    
    private final String message;
    
    ProjectMethodEnum(String value, String message){
        this.value = value;
        this.message = message;
    }

    public String getValue() {
        return value;
    }
    
    public String getMessage(){
    	return message;
    }
    
    public static ProjectMethodEnum valueof(String value) {
    	for(ProjectMethodEnum e: ProjectMethodEnum.values()) {
    		if(e.getValue().equals(value)) {
    			return e;
    		}
    	}
    	return null;
    }
}
