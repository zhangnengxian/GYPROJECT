package cc.dfsoft.project.biz.base.station.enums;

public enum StationDataTypeEnum {


	ACCEPT("1","立项资料"),
	PROCEDURES("2","建审资料"),
	CONTRUCTION("3","施工资料"),
	SETTLEMENT("4","结算资料"),
	FINAL_ACCOUNT("5","决算资料");

	private final String value;

    private final String message;


    StationDataTypeEnum(String value, String message){
        this.value = value;
        this.message = message;
    }

    public String getValue() {
        return value;
    }
    
    public String getMessage(){
    	return message;
    }
    
	public static StationDataTypeEnum valueof(String value) {
    	for(StationDataTypeEnum e: StationDataTypeEnum.values()) {
    		if(e.getValue().equals(value)) {
    			return e;
    		}
    	}
    	return null;
    }	

}
