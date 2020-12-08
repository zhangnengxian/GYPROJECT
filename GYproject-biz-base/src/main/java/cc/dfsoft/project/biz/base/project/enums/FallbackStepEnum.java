
package cc.dfsoft.project.biz.base.project.enums;

public enum FallbackStepEnum {
	
	DESIGN_DRAWING("1201","设计出图"),
	BUDGET_RESULT_REGISTER("1302","预算记录"),
	QUALITIES_DECLARATION("1601","施工预算"),
	SETTLEMENT_REPORT("1901","结算报审");

	private final String value;
    
    private final String message;
    
    FallbackStepEnum(String value, String message){
        this.value = value;
        this.message = message;
    }

    public String getValue() {
        return value;
    }
    
    public String getMessage(){
    	return message;
    }
    
	public static FallbackStepEnum valueof(String value) {
    	for(FallbackStepEnum e: FallbackStepEnum.values()) {
    		if(e.getValue().equals(value)) {
    			return e;
    		}
    	}
    	return null;
    }	

}
