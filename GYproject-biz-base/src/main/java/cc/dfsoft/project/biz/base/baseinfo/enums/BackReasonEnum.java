package cc.dfsoft.project.biz.base.baseinfo.enums;

/**
 * 退单原因
 * @author pengtt
 * @createTime 2016-07-25
 */
public enum BackReasonEnum {

	 NO_GASSOURCE("1", "无气源"),
	 NO_GASSOURCE_UNIT("2", "气源单位不同意"),
	 HIGH_COST("3", "造价高"),
	 INITIATIVE_GIVE_UP("4", "用户放弃"),
	 OTHERS("5", "其它");

    private final String value;
    
    private final String message;
    
    BackReasonEnum(String value, String message){
        this.value = value;
        this.message = message;
    }

    public String getValue() {
        return value;
    }
    
    public String getMessage(){
    	return message;
    }
    
    public static BackReasonEnum valueof(String value) {
    	for(BackReasonEnum e: BackReasonEnum.values()) {
    		if(e.getValue().equals(value)) {
    			return e;
    		}
    	}
    	return null;
    }
    public static String[] getMessages() {
		String[] messages=new String[BackReasonEnum.values().length];
		for(int i=0;i<BackReasonEnum.values().length;i++){
			messages[i] = BackReasonEnum.values()[i].getMessage();
		}
    	return messages;
    }
}
