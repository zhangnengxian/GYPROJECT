package cc.dfsoft.project.biz.base.baseinfo.enums;

public enum AcceptTypeEnum {
	INNER_PLAN("1", "计划内立项"),
	OUT_PLAN("2", "计划外立项");
	
	private final String value;
    
    private final String message;
    
    AcceptTypeEnum(String value, String message){
        this.value = value;
        this.message = message;
    }

    public String getValue() {
        return value;
    }
    
    public String getMessage(){
    	return message;
    }
    
    public static AcceptTypeEnum valueof(String value) {
    	for(AcceptTypeEnum e: AcceptTypeEnum.values()) {
    		if(e.getValue().equals(value)) {
    			return e;
    		}
    	}
    	return null;
    }
}
