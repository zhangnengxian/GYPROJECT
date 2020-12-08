package cc.dfsoft.project.biz.base.contract.enums;
/**
 * 
 * 描述:工程造价审核类型枚举
 * @author liaoyq
 * @createTime 2017年8月16日
 */
public enum ProjCostAuditTypeEnum {
	LEVEL_ONE("1","减免带气作业费、不可预见费","1"),
	LEVEL_TWO("2","减免带气作业费、不可预见费、土石方工程","2"),
	LEVEL_THREE("3","不可预见费","3");
    private final String value;
    
    private final String message;
    
    private final String auditLevel;//审批级别
    
    ProjCostAuditTypeEnum(String value, String message,String auditLevel){
        this.value = value;
        this.message = message;
        this.auditLevel = auditLevel;
    }

    public String getValue() {
        return value;
    }
    
    public String getMessage(){
    	return message;
    }
    public String getAuditLevel(){
    	return auditLevel;
    }
    
    public static ProjCostAuditTypeEnum valueof(String value) {
    	for(ProjCostAuditTypeEnum e: ProjCostAuditTypeEnum.values()) {
    		if(e.getValue().equals(value)) {
    			return e;
    		}
    	}
    	return null;
    }
	
}
