package cc.dfsoft.project.biz.base.project.enums;
/**
 * 回退是否需要审核枚举
 * @author liaoyq
 * @createTime 2018年4月16日
 */
public enum IsAuditEnum {
	IS_Aidut("1","需要审核"),
	NO_Aidut("0","不需要审核");
	private final String value;
    
    private final String message;
    
    IsAuditEnum(String value, String message){
        this.value = value;
        this.message = message;
    }

    public String getValue() {
        return value;
    }
    
    public String getMessage(){
    	return message;
    }
    
	public static IsAuditEnum valueof(String value) {
    	for(IsAuditEnum e: IsAuditEnum.values()) {
    		if(e.getValue().equals(value)) {
    			return e;
    		}
    	}
    	return null;
    }
}
