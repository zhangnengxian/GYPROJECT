package cc.dfsoft.project.biz.base.budget.enums;

import cc.dfsoft.project.biz.base.project.enums.StepEnum;
/**
 * 
 * 描述:预算方式
 * @author liaoyq
 * @createTime 2018年9月8日
 */
public enum BudgetMethodEnum {

	THREE_BUDGET("0","第三方做预算",StepEnum.BUDGET_DISPATCH.getValue()),
	SELF_BUDGET("1","子公司做预算",StepEnum.BUDGET_CONFIRM.getValue()),
	CORP_BUDGET("2","集团做预算",StepEnum.BUDGET_CONFIRM.getValue());

	
	private final String value;
    
    private final String message;
    private final String stepId;
    
    BudgetMethodEnum(String value, String message, String stepId){
        this.value = value;
        this.message = message;
        this.stepId = stepId;
    }

    public String getValue() {
        return value;
    }
    
    public String getMessage(){
    	return message;
    }
    
    public String getStepId(){
    	return stepId;
    }
    
    public static BudgetMethodEnum valueof(String value) {
    	for(BudgetMethodEnum e: BudgetMethodEnum.values()) {
    		if(e.getValue().equals(value)) {
    			return e;
    		}
    	}
    	return null;
    }
}
