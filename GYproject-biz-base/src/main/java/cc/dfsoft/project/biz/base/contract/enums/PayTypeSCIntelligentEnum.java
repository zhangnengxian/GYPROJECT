package cc.dfsoft.project.biz.base.contract.enums;
/**
 * 
 * 描述:智能表分包合同付款比例
 * @author liaoyq
 * @createTime 2017年10月16日
 */
public enum PayTypeSCIntelligentEnum {
	SC_INTELLIGENT_SEC("1","首付款90%"),
	SC_INTELLIGENT_TRE("2","首付款85%");
	
	
	private final String value;//支付方式
    
    private final String message;//首付款比例
    
    PayTypeSCIntelligentEnum(String value, String message){
        this.value = value;
        this.message = message;
    }

    public String getValue() {
        return value;
    }
    
    public String getMessage(){
    	return message;
    }
    
    public static PayTypeSCIntelligentEnum valueof(String value) {
    	for(PayTypeSCIntelligentEnum e: PayTypeSCIntelligentEnum.values()) {
    		if(e.getValue().equals(value)) {
    			return e;
    		}
    	}
    	return null;
    }
}
