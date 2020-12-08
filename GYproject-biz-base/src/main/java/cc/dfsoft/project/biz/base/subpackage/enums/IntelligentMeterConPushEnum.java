package cc.dfsoft.project.biz.base.subpackage.enums;
/**
 * 
 * 描述:智能表推送标记枚举类
 * @author liaoyq
 * @createTime 2017年9月20日
 */
public enum IntelligentMeterConPushEnum {
	NO_PUSH("0","未推送"),
	PUSHED("1","已推送");
	
	private final String value;
	private final String message;
	
	IntelligentMeterConPushEnum(String value,String message){
		this.value=value;
		this.message=message;
	}
	
	public String getValue(){
		return value;
	}
	public String getMessage(){
		return message;
	}
	
	public static IntelligentMeterConPushEnum valueof(String value) {
    	for(IntelligentMeterConPushEnum e: IntelligentMeterConPushEnum.values()) {
    		if(e.getValue().equals(value)) {
    			return e;
    		}
    	}
    	return null;
    }
}
