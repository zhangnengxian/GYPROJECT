package cc.dfsoft.uexpress.biz.sys.dept.enums;



/**
 *业务类型enum
 * @author fuliwei
 *
 */
public enum BusinessTypeEnum {
	
	PROJECT_GROUP("1","工程部门"),
	BUSINESS_GROUP("2","业务部门");
	
	private final String value;
    
    private final String message;
    
    BusinessTypeEnum(String value, String message){
        this.value = value;
        this.message = message;
    }

    public String getValue() {
        return value;
    }
    
    public String getMessage(){
    	return message;
    }
    
    public static BusinessTypeEnum valueof(String value) {
    	for(BusinessTypeEnum e: BusinessTypeEnum.values()) {
    		if(e.getValue().equals(value)) {
    			return e;
    		}
    	}
    	return null;
    }	
}
