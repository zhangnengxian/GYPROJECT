package cc.dfsoft.project.biz.base.budget.enums;

public enum BudgetTypeEnum {
	ADJUSTED("1","调整"),
	NOT_ADJUST("0","正常");

	
	private final String value;
    
    private final String message;
    
    BudgetTypeEnum(String value, String message){
        this.value = value;
        this.message = message;
    }

    public String getValue() {
        return value;
    }
    
    public String getMessage(){
    	return message;
    }
    
    public static BudgetTypeEnum valueof(String value) {
    	for(BudgetTypeEnum e: BudgetTypeEnum.values()) {
    		if(e.getValue().equals(value)) {
    			return e;
    		}
    	}
    	return null;
    }
}
