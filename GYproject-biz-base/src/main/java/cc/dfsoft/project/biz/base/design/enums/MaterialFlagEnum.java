package cc.dfsoft.project.biz.base.design.enums;
/**
 * @author liaoyq
 * @createTime 2018年3月28日
 */
public enum MaterialFlagEnum {
	YES("1","是"),
	NO("0","否");
	private final String value;
    private final String message;

    MaterialFlagEnum(String value, String message){
        this.value = value;
        this.message = message;
    }
    public String getValue() {
        return value;
    }
    
    public String getMessage(){
    	return message;
    }
    
    public static MaterialFlagEnum valueof(String value) {
    	for(MaterialFlagEnum e: MaterialFlagEnum.values()) {
    		if(e.getValue().equals(value)) {
    			return e;
    		}
    	}
    	return null;
    }
}
