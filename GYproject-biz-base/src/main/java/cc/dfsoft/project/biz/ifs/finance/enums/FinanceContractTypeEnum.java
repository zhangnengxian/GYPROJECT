package cc.dfsoft.project.biz.ifs.finance.enums;
/**
 * 
 * 描述:合同类型
 * @author liaoyq
 * @createTime 2018年1月16日
 */
public enum FinanceContractTypeEnum {

	CONTRACT("1","主合同合同"),
	SUPPLE_CONTRACT("2","补充协议"),
	SUPPLE_MODIFY_CONTRACT("3","补充协议修改");
private final String value;
    
    private final String message;
    
    FinanceContractTypeEnum(String value, String message){
        this.value = value;
        this.message = message;
    }

    public String getValue() {
        return value;
    }
    
    public String getMessage(){
    	return message;
    }
    
    public static FinanceContractTypeEnum valueof(String value) {
    	for(FinanceContractTypeEnum e: FinanceContractTypeEnum.values()) {
    		if(e.getValue().equals(value)) {
    			return e;
    		}
    	}
    	return null;
    }
}
