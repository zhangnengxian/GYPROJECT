package cc.dfsoft.project.biz.base.baseinfo.enums;

/**
 * 启用、禁用枚举类
 * @author liaoyq
 * @createTime 2018年5月8日
 */
public enum IsValidEnum {

	USED("1","启用"),
	UN_USED("0","禁用");
	
	private final String value;
    
    private final String message;
    
    IsValidEnum(String value, String message){
        this.value = value;
        this.message = message;
    }

    public String getValue() {
        return value;
    }
    
    public String getMessage(){
    	return message;
    }
    
    public static IsValidEnum valueof(String value) {
    	for(IsValidEnum e: IsValidEnum.values()) {
    		if(e.getValue().equals(value)) {
    			return e;
    		}
    	}
    	return null;
    }	
}
