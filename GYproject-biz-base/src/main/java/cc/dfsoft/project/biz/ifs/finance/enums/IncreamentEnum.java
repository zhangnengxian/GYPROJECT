package cc.dfsoft.project.biz.ifs.finance.enums;
/**
 * 
 * 描述:税率枚举
 * @author liaoyq
 * @createTime 2017年11月15日
 */
public enum IncreamentEnum {
	INCREAMENT_THR("3.00","B03"),
	INCREAMENT_ELE("11.00","B11");
	//INTELLIGENT_CONTRACT_SIGN("5","智能表合同签订"),
	//INTELLIGENT_CONTRACT_UPDATE("6","智能表合同修改"),
	//SUB_INTELLIGENT_CONTRACT_SIGN("7","智能表分合同签订");
	
	private final String value;
    
    private final String message;
    
    IncreamentEnum(String value, String message){
        this.value = value;
        this.message = message;
    }

    public String getValue() {
        return value;
    }
    
    public String getMessage(){
    	return message;
    }
    
    public static IncreamentEnum valueof(String value) {
    	for(IncreamentEnum e: IncreamentEnum.values()) {
    		if(e.getValue().equals(value)) {
    			return e;
    		}
    	}
    	return null;
    }
}
