package cc.dfsoft.project.biz.base.project.enums;

/**
 * 操作流状态
 * @author fulw
 *
 */
public enum OperateWorkFlowEnum {
	
	NOT_DONE("0","未办"),
	HAVE_DONE("1","已办"),
	WAIT_DONE("2","待办");
	
	private final String value;
    
    private final String message;
    
    OperateWorkFlowEnum(String value, String message){
        this.value = value;
        this.message = message;
    }

    public String getValue() {
        return value;
    }
    
    public String getMessage(){
    	return message;
    }
    
    public static OperateWorkFlowEnum valueof(String value) {
    	for(OperateWorkFlowEnum e: OperateWorkFlowEnum.values()) {
    		if(e.getValue().equals(value)) {
    			return e;
    		}
    	}
    	return null;
    }	
}
