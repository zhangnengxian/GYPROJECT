package cc.dfsoft.project.biz.base.constructmanage.enums;


public enum StageProjectApplicationEnum {
	
	TO_AUDIT("1","待审核"),
	TO_PUSH("2","待推送"),
	NO_PASSING("3","未通过"),
	PASSED("4","已通过"),
	TO_BUDGET("5","待预算调整"),
	RE_PUSH("6","待调整签证"),
	QUANTY_AUDIT("7","待签证量审核"),
	TO_CANCEL("-1","作废");
	
	private final String value;
    
    private final String message;
    
    StageProjectApplicationEnum(String value, String message){
        this.value = value;
        this.message = message;
    }

    public String getValue() {
        return value;
    }
    
    public String getMessage(){
    	return message;
    }
    
    public static StageProjectApplicationEnum valueof(String value) {
    	for(StageProjectApplicationEnum e: StageProjectApplicationEnum.values()) {
    		if(e.getValue().equals(value)) {
    			return e;
    		}
    	}
    	return null;
    }	

}
