package cc.dfsoft.project.biz.base.project.enums;

public enum NoticeTypeEnum {
	PWD_NOTICE("-1","密码修改通知"),
	NOTICE("1","消息通知"),
	WORK_NOTICE("2","工作通知"),
	BUS_NOTICE("3","沟通通知"),
	PLAN_NOTICE("4","任务单通知");
	
	private final String value;
    
    private final String message;
    
    NoticeTypeEnum(String value, String message){
        this.value = value;
        this.message = message;
    }

    public String getValue() {
        return value;
    }
    
    public String getMessage(){
    	return message;
    }
    
    public static NoticeTypeEnum valueof(String value) {
    	for(NoticeTypeEnum e: NoticeTypeEnum.values()) {
    		if(e.getValue().equals(value)) {
    			return e;
    		}
    	}
    	return null;
    }	
}
