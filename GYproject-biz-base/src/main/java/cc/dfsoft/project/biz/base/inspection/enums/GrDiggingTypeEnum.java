package cc.dfsoft.project.biz.base.inspection.enums;
/**
 * 开挖方式
 * @author Administrator
 *
 */
public enum GrDiggingTypeEnum {
	LABOUR("1","人工开挖"),
	MACHINE("2","机械开挖");
	
	private final String value;
    
    private final String message;
    
    GrDiggingTypeEnum(String value, String message){
        this.value = value;
        this.message = message;
    }

    public String getValue() {
        return value;
    }
    
    public String getMessage(){
    	return message;
    }
    
    public static GrDiggingTypeEnum valueof(String value) {
    	if(null!=value){
    	for(GrDiggingTypeEnum e: GrDiggingTypeEnum.values()) {
    		if(e.getValue().equals(value)) {
    			return e;
    		}
    	}}
    	return null;
    }	
}
