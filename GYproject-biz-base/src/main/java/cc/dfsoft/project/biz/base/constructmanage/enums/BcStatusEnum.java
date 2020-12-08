package cc.dfsoft.project.biz.base.constructmanage.enums;

/**
 * 通知状态
 * @author Yuanyx
 *
 */
public enum BcStatusEnum {
	 NO_PUSHED("0","待推送"),
	 TO_REPLY("1", "待回复"),
	 REPLIED("2", "已回复");

    private final String value;
    
    private final String message;
    
    BcStatusEnum(String value, String message){
        this.value = value;
        this.message = message;
    }

    public String getValue() {
        return value;
    }
    
    public String getMessage(){
    	return message;
    }
    
    public static BcStatusEnum valueof(String value) {
    	for(BcStatusEnum e: BcStatusEnum.values()) {
    		if(e.getValue().equals(value)) {
    			return e;
    		}
    	}
    	return null;
    }
    public static String[] getMessages() {
		String[] messages=new String[BcStatusEnum.values().length];
		for(int i=0;i<BcStatusEnum.values().length;i++){
			messages[i] = BcStatusEnum.values()[i].getMessage();
		}
    	return messages;
    }
}
