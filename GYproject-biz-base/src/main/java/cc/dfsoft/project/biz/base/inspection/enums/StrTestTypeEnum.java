package cc.dfsoft.project.biz.base.inspection.enums;
/**
 * 试验类型枚举类
 * @author liaoyq
 *
 */
public enum StrTestTypeEnum {
	STRENGTH_TEST("1","强度试验"),
	TEST("2","气密性试验");
	
	private final String value;
	private final String message;
	
	StrTestTypeEnum(String value, String message){
	        this.value = value;
	        this.message = message;
	    }

	    public String getValue() {
	        return value;
	    }
	    
	    public String getMessage(){
	    	return message;
	    }
	    
	    public static StrTestTypeEnum valueof(String value) {
	    	for(StrTestTypeEnum e: StrTestTypeEnum.values()) {
	    		if(e.getValue().equals(value)) {
	    			return e;
	    		}
	    	}
	    	return null;
	    }
}
