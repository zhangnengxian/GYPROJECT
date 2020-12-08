package cc.dfsoft.project.biz.base.budget.enums;


public enum BudgetAdjustEnum {
	
	ADJUSTED("1","调整"),
	NOT_ADJUST("0","正常");

	
	private final String value;
    
    private final String message;
    
    BudgetAdjustEnum(String value, String message){
        this.value = value;
        this.message = message;
    }

    public String getValue() {
        return value;
    }
    
    public String getMessage(){
    	return message;
    }
    
    public static BudgetAdjustEnum valueof(String value) {
    	for(BudgetAdjustEnum e: BudgetAdjustEnum.values()) {
    		if(e.getValue().equals(value)) {
    			return e;
    		}
    	}
    	return null;
    }
}
