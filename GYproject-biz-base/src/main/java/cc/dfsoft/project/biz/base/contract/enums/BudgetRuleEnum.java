package cc.dfsoft.project.biz.base.contract.enums;
/**
 * 预算包干、预结算制度
 * @author liaoyq
 * @createTime 2018年8月15日
 */
public enum BudgetRuleEnum {

	BUDGET("1","预算包干制"),
	SETTLEMENT ("2","预决算制");
	
	private final String value;
    
    private final String message;
    
    BudgetRuleEnum(String value, String message){
        this.value = value;
        this.message = message;
    }

    public String getValue() {
        return value;
    }
    
    public String getMessage(){
    	return message;
    }
    
    public static BudgetRuleEnum valueof(String value) {
    	for(BudgetRuleEnum e: BudgetRuleEnum.values()) {
    		if(e.getValue().equals(value)) {
    			return e;
    		}
    	}
    	return null;
    }
}
