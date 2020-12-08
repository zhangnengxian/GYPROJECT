package cc.dfsoft.project.biz.base.contract.enums;

public enum ContractTypeEnum {
	
	RESIDENT("11","居民户"),
	PUBLIC_BUILDING("21","公建"),
	CHANGE("31","改管"),
    TRUNK("41","干线");
	
	private final String value;
    
    private final String message;
    
    ContractTypeEnum(String value, String message){
        this.value = value;
        this.message = message;
    }

    public String getValue() {
        return value;
    }
    
    public String getMessage(){
    	return message;
    }
    
    public static ContractTypeEnum valueof(String value) {
    	for(ContractTypeEnum e: ContractTypeEnum.values()) {
    		if(e.getValue().equals(value)) {
    			return e;
    		}
    	}
    	return null;
    }	
	

}
