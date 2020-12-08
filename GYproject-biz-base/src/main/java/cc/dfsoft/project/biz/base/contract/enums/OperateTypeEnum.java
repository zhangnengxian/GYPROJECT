package cc.dfsoft.project.biz.base.contract.enums;

public enum OperateTypeEnum {
	CONTRACT_MODIFY("1","合同修改"),
	PROJECT_MODIFY("2","工程基本信息修改"),
	IMCCONTRACT_MODIFY("3","智能表合同修改"),
	SC_MODIFY("4","分合同修改"),
	SUPPLECON_MODIFY("5","补充协议修改");
	private final String value;
    
    private final String message;
    
    OperateTypeEnum(String value, String message){
        this.value = value;
        this.message = message;
    }

    public String getValue() {
        return value;
    }
    
    public String getMessage(){
    	return message;
    }
    
    public static OperateTypeEnum valueof(String value) {
    	for(OperateTypeEnum e: OperateTypeEnum.values()) {
    		if(e.getValue().equals(value)) {
    			return e;
    		}
    	}
    	return null;
    }
	
	
}
