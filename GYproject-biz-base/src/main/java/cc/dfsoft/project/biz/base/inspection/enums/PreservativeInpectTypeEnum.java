package cc.dfsoft.project.biz.base.inspection.enums;
/**
 * 防腐检查类型
 * @author liaoyq
 *
 */
public enum PreservativeInpectTypeEnum {
	JOINT_PRESERVATIVE("0","3PE防腐"),
	PRIMER_PRESERVATIVE("1","油漆防腐");
	
	private final String value;
    
    private final String message;
    
    PreservativeInpectTypeEnum(String value, String message){
        this.value = value;
        this.message = message;
    }

    public String getValue() {
        return value;
    }
    
    public String getMessage(){
    	return message;
    }
    
    public static PreservativeInpectTypeEnum valueof(String value) {
    	if(null!=value){
    	for(PreservativeInpectTypeEnum e: PreservativeInpectTypeEnum.values()) {
    		if(e.getValue().equals(value)) {
    			return e;
    		}
    	}}
    	return null;
    }
}
