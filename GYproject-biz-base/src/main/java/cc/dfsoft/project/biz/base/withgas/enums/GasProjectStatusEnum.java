package cc.dfsoft.project.biz.base.withgas.enums;

public enum GasProjectStatusEnum {
    OPEN_PROJECT("1","待开通"),
    GAS_PLAN("2","待编计划"),
	GAS_OPEN("3","待确认"),
    GAS_AUDIT("4","待审核"),
    GAS_AUDIT_DONE("5","已审核通过"),
    GAS_AUDIT_NO_PASS("0","审核未通过"),
    IGNITE("6","点火"),
    GAS_DATE_REGISTER("7","待登记通气时间"),
    DELOVER_DATE_REGISTER("8","待登记交付时间"),
	WHETHER_OPEN("-1","无需开通");

	private final String value;

    private final String message;

    GasProjectStatusEnum(String value, String message){
        this.value = value;
        this.message = message;
    }

    public String getValue() {
        return value;
    }
    
    public String getMessage(){
    	return message;
    }
    public static GasProjectStatusEnum valueof(String value) {
    	for(GasProjectStatusEnum e: GasProjectStatusEnum.values()) {
    		if(e.getValue().equals(value)) {
    			return e;
    		}
    	}
    	return null;
    }
}
