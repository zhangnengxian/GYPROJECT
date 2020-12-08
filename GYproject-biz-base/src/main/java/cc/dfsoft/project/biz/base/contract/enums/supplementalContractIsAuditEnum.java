package cc.dfsoft.project.biz.base.contract.enums;

public enum supplementalContractIsAuditEnum {
	
	ALREADY_SIGN("1","已签订"),
	HAVE_PASS("2","已通过"),
	NOT_HAVE_PASS("3","未通过");
	
	private final String value;
	private final String message;
	
	supplementalContractIsAuditEnum(String value,String message){
		this.value=value;
		this.message=message;
	}
	
	public String getValue(){
		return value;
	}
	public String getMessage(){
		return message;
	}
	
	public static supplementalContractIsAuditEnum valueof(String value) {
    	for(supplementalContractIsAuditEnum e: supplementalContractIsAuditEnum.values()) {
    		if(e.getValue().equals(value)) {
    			return e;
    		}
    	}
    	return null;
    }
}
