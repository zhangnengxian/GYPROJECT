package cc.dfsoft.project.biz.base.constructmanage.enums;

/**
 * 通知发送状态
 * @author Yuanyx
 *
 */
public enum BcSendTypeEnum {

	SENDER("1", "发送"),
	RECIPIENT("2", "接收");

    private final String value;
    
    private final String message;
    
    BcSendTypeEnum(String value, String message){
        this.value = value;
        this.message = message;
    }

    public String getValue() {
        return value;
    }
    
    public String getMessage(){
    	return message;
    }
    
    public static BcSendTypeEnum valueof(String value) {
    	for(BcSendTypeEnum e: BcSendTypeEnum.values()) {
    		if(e.getValue().equals(value)) {
    			return e;
    		}
    	}
    	return null;
    }
    public static String[] getMessages() {
		String[] messages=new String[BcSendTypeEnum.values().length];
		for(int i=0;i<BcSendTypeEnum.values().length;i++){
			messages[i] = BcSendTypeEnum.values()[i].getMessage();
		}
    	return messages;
    }
}
