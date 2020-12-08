package cc.dfsoft.project.biz.base.common.enums;

public enum AssociateTypeEnum {
	COLLECT("collection","采集"),
	CUSTOMER("customer","客户"),
	DISPATCH("dispatch","计划"),
	APPLYDELAY("applydelay","延期申请"),
	/*SETTLEMENTCHANGE("settlementchange","变更明细"),*/
	AUDITINFORMATION("auditInformation","审核信息"),
	/*GRADE("grade","评分"),
	MODIFY("modify","更正"),*/
	FALL_BACK("fallBack","回退"),
	RECTIFY("rectify","整改"),
	OPERATE_WORK("operateWorkFlow","操作历史");
	
	
	private final String value;
    
    private final String message;
    
    
    AssociateTypeEnum(String value, String message){
        this.value = value;
        this.message = message;
      
    }

    public String getValue() {
        return value;
    }
    
    public String getMessage(){
    	return message;
    }
    
    public static AssociateTypeEnum valueof(String value) {
    	for(AssociateTypeEnum e: AssociateTypeEnum.values()) {
    		if(e.getValue().equals(value)) {
    			return e;
    		}
    	}
    	return null;
    }	
    
  
}
