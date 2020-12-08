package cc.dfsoft.project.biz.base.inspection.enums;
/**
 * 防腐记录类型枚举
 * @author liaoyq
 *
 */
public enum PreservativeTypeEnum {
	JOINT_PRESERVATIVE("1","钢管及接头"),
	PIPE_PRESERVATIVE("2","金属管道");
	
	private final String value;
    
    private final String message;
    
    PreservativeTypeEnum(String value, String message){
        this.value = value;
        this.message = message;
    }

    public String getValue() {
        return value;
    }
    
    public String getMessage(){
    	return message;
    }
    
    public static PreservativeTypeEnum valueof(String value) {
    	if(null!=value){
    	for(PreservativeTypeEnum e: PreservativeTypeEnum.values()) {
    		if(e.getValue().equals(value)) {
    			return e;
    		}
    	}}
    	return null;
    }
}
