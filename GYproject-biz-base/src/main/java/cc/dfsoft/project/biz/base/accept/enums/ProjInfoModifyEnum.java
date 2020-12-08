package cc.dfsoft.project.biz.base.accept.enums;
/**
 * 是否更正工程
 * @author fuliwei
 *
 */
public enum ProjInfoModifyEnum {
	
	HAVE_NOT_MODIFY("1", "未更正"),
	NEED_MODIFY("0", "需要更正");
	
	private final String value;
    
    private final String message;
    
    ProjInfoModifyEnum(String value, String message){
        this.value = value;
        this.message = message;
    }

    public String getValue() {
        return value;
    }
    
    public String getMessage(){
    	return message;
    }
    
    public static ProjInfoModifyEnum valueof(String value) {
    	for(ProjInfoModifyEnum e: ProjInfoModifyEnum.values()) {
    		if(e.getValue().equals(value)) {
    			return e;
    		}
    	}
    	return null;
    }
	
	
}
