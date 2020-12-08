package cc.dfsoft.project.biz.base.contract.enums;

public enum ChangeReasonEnum {
	
	LEADER_PREFERENTIAL("1","用户协商"),
	GOVERNMENT_PREFERENTIAL("2","政府定价"),
	/*CHANGE_REASON("3","变更原因"),
	AUDIT_CONFIRM("4","审核确定"),
	OHTER_REASON("5","其他原因"),*/
	CORP_CONFIRM("6","公司政策");
	
	private final String value;
    
    private final String message;
    
    ChangeReasonEnum(String value, String message){
        this.value = value;
        this.message = message;
    }

    public String getValue() {
        return value;
    }
    
    public String getMessage(){
    	return message;
    }
    
    public static ChangeReasonEnum valueof(String value) {
    	for(ChangeReasonEnum e: ChangeReasonEnum.values()) {
    		if(e.getValue().equals(value)) {
    			return e;
    		}
    	}
    	return null;
    }
}
