package cc.dfsoft.project.biz.base.baseinfo.enums;
/**
 * 流程类型
 * @author fulw
 *
 */
public enum WorkFlowTypeEnum {
	
	MAIN_PROGRESS("1", "主流程"),
	ASSIST_PROGRESS("2", "辅助流程");


    private final String value;
    
    private final String message;
    
    WorkFlowTypeEnum(String value, String message){
        this.value = value;
        this.message = message;
    }

    public String getValue() {
        return value;
    }
    
    public String getMessage(){
    	return message;
    }
    
    public static WorkFlowTypeEnum valueof(String value) {
    	for(WorkFlowTypeEnum e: WorkFlowTypeEnum.values()) {
    		if(e.getValue().equals(value)) {
    			return e;
    		}
    	}
    	return null;
    }
}
