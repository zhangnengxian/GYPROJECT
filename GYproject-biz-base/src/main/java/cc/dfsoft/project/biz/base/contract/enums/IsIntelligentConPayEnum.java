package cc.dfsoft.project.biz.base.contract.enums;
/**
 * 
 * 描述:智能表和其他合同枚举类收付款
 * 智能表和非智能表标记
 * @author liaoyq
 * @createTime 2017年10月17日
 */
public enum IsIntelligentConPayEnum {

	INTELLIGENT_CON_PAY("1","智能表付款"),
	OTHER_CON_PAY("0","非智能表付款");
	private final String value;
    
    private final String message;
    
    IsIntelligentConPayEnum(String value, String message){
        this.value = value;
        this.message = message;
    }

    public String getValue() {
        return value;
    }
    
    public String getMessage(){
    	return message;
    }
    
    public static IsIntelligentConPayEnum valueof(String value) {
    	for(IsIntelligentConPayEnum e: IsIntelligentConPayEnum.values()) {
    		if(e.getValue().equals(value)) {
    			return e;
    		}
    	}
    	return null;
    }	
	
}
