package cc.dfsoft.project.biz.base.project.enums;

public enum QualifiedEnum {
	
	PASSED("1","合格"),
	NOT_PASSED("0","不合格"),
    NULL("2","无");
	
	private final String value;
    
    private final String message;
    
    QualifiedEnum(String value, String message){
        this.value = value;
        this.message = message;
    }

    public String getValue() {
        return value;
    }
    
    public String getMessage(){
    	return message;
    }
    
    public static QualifiedEnum valueof(String value) {
    	for(QualifiedEnum e: QualifiedEnum.values()) {
    		if(e.getValue().equals(value)) {
    			return e;
    		}
    	}
    	return null;
    }	

}
