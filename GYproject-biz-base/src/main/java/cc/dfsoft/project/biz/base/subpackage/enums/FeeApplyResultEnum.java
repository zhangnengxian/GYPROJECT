package cc.dfsoft.project.biz.base.subpackage.enums;

public enum FeeApplyResultEnum {

	HAVE_NOT_PROJ("1","无此工程"),
	HAVE_NOT_PLAN("2","未下工程计划"),
	HAVE_NOT_SETTLEMENT("3","未结算"),
	HAVE_NOT_NDE("4","无探伤委托"),
	HAVE_NOT_PROJ_NO("5","工程编号为空"),
	HAVE_NOT_AUDIT_UNIT("6","审核部门类型不对");
	
	private final String value;
    
    private final String message;
    
    FeeApplyResultEnum(String value, String message){
        this.value = value;
        this.message = message;
    }

    public String getValue() {
        return value;
    }
    
    public String getMessage(){
    	return message;
    }
    public static FeeApplyResultEnum valueof(String value) {
    	for(FeeApplyResultEnum e: FeeApplyResultEnum.values()) {
    		if(e.getValue().equals(value)) {
    			return e;
    		}
    	}
    	return null;
    }
}
