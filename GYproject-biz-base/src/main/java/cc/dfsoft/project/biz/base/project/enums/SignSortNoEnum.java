package cc.dfsoft.project.biz.base.project.enums;

public enum SignSortNoEnum {
	ONE("1","第一个签字"),
	TWO("2","第二个签字"),
	THREE("3","第三个签字"),
	FOUR("4","第四个签字"),
	FIVE("5","第五个签字"),
	SIX("6","第六个签字"),
	SEVEN("7","第七个签字"),
	EIGHT("8","第八个签字"),
	NINE("9","第九个签字"),
	TEN("10","第十个签字");
	
	private final String value;
    
    private final String message;
    
    SignSortNoEnum(String value, String message){
        this.value = value;
        this.message = message;
    }

    public String getValue() {
        return value;
    }
    
    public String getMessage(){
    	return message;
    }
    
    public static SignSortNoEnum valueof(String value) {
    	for(SignSortNoEnum e: SignSortNoEnum.values()) {
    		if(e.getValue().equals(value)) {
    			return e;
    		}
    	}
    	return null;
    }
}
