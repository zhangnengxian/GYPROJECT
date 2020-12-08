package cc.dfsoft.project.biz.base.common.enums;

public enum ButtonLimitsEnum {

	CHANGE_DISPATCH("1","勘察改派","11020201"),
	CHANGE_DESIGNER("2","设计改派","11020401"),
	CONFORM_DESIGNER("3","设计确认","11020402"),
	IMPORT_DESIGNER("4","导入图纸","11020403");
	
	private final String value;
    
    private final String message;
    
    private final String type;
    
    ButtonLimitsEnum(String value, String message,String type){
        this.value = value;
        this.message = message;
        this.type = type;
    }

    public String getValue() {
        return value;
    }
    
    public String getMessage(){
    	return message;
    }
    
    public static ButtonLimitsEnum valueof(String value) {
    	for(ButtonLimitsEnum e: ButtonLimitsEnum.values()) {
    		if(e.getValue().equals(value)) {
    			return e;
    		}
    	}
    	return null;
    }	
    
    public String getType() {
		return type;
	}
}