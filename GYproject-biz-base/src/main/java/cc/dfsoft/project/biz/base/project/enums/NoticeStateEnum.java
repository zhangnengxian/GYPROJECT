package cc.dfsoft.project.biz.base.project.enums;

/**
 * 通知状态枚举
 * @author sqn
 *
 */
public enum NoticeStateEnum {

	EFFECTIVE("1","有效"),
	NOT_EFFECTIVE("-1","无效");
	
	private final String value;
    
    private final String message;
    
    NoticeStateEnum(String value, String message){
        this.value = value;
        this.message = message;
    }

    public String getValue() {
        return value;
    }
    
    public String getMessage(){
    	return message;
    }
    
    public static NoticeStateEnum valueof(String value) {
    	for(NoticeStateEnum e: NoticeStateEnum.values()) {
    		if(e.getValue().equals(value)) {
    			return e;
    		}
    	}
    	return null;
    }	


}
