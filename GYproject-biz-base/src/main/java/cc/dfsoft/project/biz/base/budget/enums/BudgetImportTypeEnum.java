package cc.dfsoft.project.biz.base.budget.enums;

public enum BudgetImportTypeEnum {
	
	SUBITEM_IMPORT("0","分项导入"),
	TOTAL_TABLE_IMPORT("1","总表导入");

	
	private final String value;
    
    private final String message;
    
    BudgetImportTypeEnum(String value, String message){
        this.value = value;
        this.message = message;
    }

    public String getValue() {
        return value;
    }
    
    public String getMessage(){
    	return message;
    }
    
    public static BudgetImportTypeEnum valueof(String value) {
    	for(BudgetImportTypeEnum e: BudgetImportTypeEnum.values()) {
    		if(e.getValue().equals(value)) {
    			return e;
    		}
    	}
    	return null;
    }
}
