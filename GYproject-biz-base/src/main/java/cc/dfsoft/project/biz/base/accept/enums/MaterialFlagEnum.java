package cc.dfsoft.project.biz.base.accept.enums;
/**
 * 
 * 描述:
 * @author liaoyq
 * @createTime 2018年1月15日
 */
public enum MaterialFlagEnum {
	
	YES("1", "有借料"),
	NO("0", "无借料");

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
    public static String[] getMessages() {
		String[] messages=new String[MaterialFlagEnum.values().length];
		for(int i=0;i<MaterialFlagEnum.values().length;i++){
			messages[i] = MaterialFlagEnum.values()[i].getMessage();
		}
    	return messages;
    }
}
