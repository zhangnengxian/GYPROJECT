package cc.dfsoft.project.biz.base.subpackage.enums;

public enum AuditStatusEnum {
	
	DECLARATION("1","报审",1),
	START("2","初审",2),
	DONE_FIRST("3","一级终审",3),
	DONE_SECOND("4","二级终审",4),
	DONE_THIED("5","三级终审",5);
	
	private final String value;
    
    private final String message;
    
    private final int step;
    
    AuditStatusEnum(String value, String message,int step){
        this.value = value;
        this.message = message;
        this.step = step;
    }

    public String getValue() {
        return value;
    }
    
    public String getMessage(){
    	return message;
    }
    
    public int getStep(){
    	return step;
    }
    
    public static AuditStatusEnum valueof(String value) {
    	for(AuditStatusEnum e: AuditStatusEnum.values()) {
    		if(e.getValue().equals(value)) {
    			return e;
    		}
    	}
    	
    	return null;
    }
    public static AuditStatusEnum stepof(int step) {
    	for(AuditStatusEnum e: AuditStatusEnum.values()) {
    		if(e.getStep()==step) {
    			return e;
    		}
    	}
    	
    	return null;
    }	

}
