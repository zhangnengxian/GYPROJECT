package cc.dfsoft.project.biz.base.settlement.enums;
/**
 * 描述:结算审批枚举类
 * @author liaoyq
 * @createTime 2017年8月9日
 */
public enum SettlementAuditResultEnum {
	
	RE_AUDIT(-1,"重新审批"),
	NOT_AUDIT(0,"未审批"),
	WAITING_AUDIT(1,"审批中"),
	NOT_PASSED(2,"审批未通过"),
	PASSED(3,"审批通过");
	
	
	private final Integer value;
    
    private final String message;
    
    SettlementAuditResultEnum(Integer value, String message){
        this.value = value;
        this.message = message;
    }

    public Integer getValue() {
        return value;
    }
    
    public String getMessage(){
    	return message;
    }
    
    public static SettlementAuditResultEnum valueof(String value) {
    	for(SettlementAuditResultEnum e: SettlementAuditResultEnum.values()) {
    		if(e.getValue().equals(value)) {
    			return e;
    		}
    	}
    	return null;
    }	

}
