package cc.dfsoft.project.biz.base.baseinfo.enums;

/**
 * 关联类型
 * @author Yuanyx
 *
 */
public enum CorrelationTypeEnum {

	PROJECT_METHOD("1", "立项方式-业务组"),
	DEPT_PROJTYPE("2", "业务组-工程类型"),
	PROJTYPE_CONTRIBUTION("3", "工程类型-出资方式");


    private final String value;
    
    private final String message;
    
    CorrelationTypeEnum(String value, String message){
        this.value = value;
        this.message = message;
    }

    public String getValue() {
        return value;
    }
    
    public String getMessage(){
    	return message;
    }
    
    public static CorrelationTypeEnum valueof(String value) {
    	for(CorrelationTypeEnum e: CorrelationTypeEnum.values()) {
    		if(e.getValue().equals(value)) {
    			return e;
    		}
    	}
    	return null;
    }
}
