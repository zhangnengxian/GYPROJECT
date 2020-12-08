package cc.dfsoft.project.biz.base.baseinfo.enums;

public enum TimeLimitTypeEnum {
	TIME_LIMIT1("1", "≤100户"),
	TIME_LIMIT2("2", "100~500户"),
	TIME_LIMIT3("3", "500~2000户"),
	TIME_LIMIT4("4", "≥2000户"),
	TIME_LIMIT5("5", "炉灶≤10台"),
	TIME_LIMIT6("6", "炉灶﹥10台"),
	TIME_LIMIT7("7", "工业燃气炉/锅炉≤4台"),
	TIME_LIMIT8("8", "工业燃气炉/锅炉﹥4台"),
	TIME_LIMIT9("9", "≤1.5千米"),
	TIME_LIMIT10("10", "1.5~4千米"),
	TIME_LIMIT11("11", "4~8千米"),
	TIME_LIMIT12("12", "≥8千米"),
	TIME_LIMIT13("13", "0~5万"),
	TIME_LIMIT14("14", "5~10万"),
	TIME_LIMIT15("15", "10~50万"),
	TIME_LIMIT16("16", "50~100万"),
	TIME_LIMIT17("17", "100万以上");
	
	private final String value;
    
    private final String message;
    
    TimeLimitTypeEnum(String value, String message){
        this.value = value;
        this.message = message;
    }

    public String getValue() {
        return value;
    }
    
    public String getMessage(){
    	return message;
    }
    
    public static TimeLimitTypeEnum valueof(String value) {
    	for(TimeLimitTypeEnum e: TimeLimitTypeEnum.values()) {
    		if(e.getValue().equals(value)) {
    			return e;
    		}
    	}
    	return null;
    }
}
