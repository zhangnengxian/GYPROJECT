package cc.dfsoft.project.biz.base.inspection.enums;
/**
 * 检查结果
 * @author Administrator
 *
 */
public enum CheckResultEnum {
	NOTHING("1","/"),
	DESIGN_QUALIFIED("2","符合设计规定"),
	NORMAL("3","正常");
	
	private final String value;
    
    private final String message;
    
    CheckResultEnum(String value, String message){
        this.value = value;
        this.message = message;
    }

    public String getValue() {
        return value;
    }
    
    public String getMessage(){
    	return message;
    }
    
    public static CheckResultEnum valueof(String value) {
    	if(null!=value){
    	for(CheckResultEnum e: CheckResultEnum.values()) {
    		if(e.getValue().equals(value)) {
    			return e;
    		}
    	}}
    	return null;
    }	
}
