package cc.dfsoft.project.biz.base.baseinfo.enums;

public enum ExternalUnitTypeEnum {

	 SUPERVISION_UNIT("01", "监理单位"),
	 CONSTRUCTION_UNIT("02", "分包单位"),
	 TEST_UNIT("03", "检测单位");

    private final String value;
    
    private final String message;
    
    ExternalUnitTypeEnum(String value, String message){
        this.value = value;
        this.message = message;
    }

    public String getValue() {
        return value;
    }
    
    public String getMessage(){
    	return message;
    }
    
    public static ExternalUnitTypeEnum valueof(String value) {
    	for(ExternalUnitTypeEnum e: ExternalUnitTypeEnum.values()) {
    		if(e.getValue().equals(value)) {
    			return e;
    		}
    	}
    	return null;
    }
}
