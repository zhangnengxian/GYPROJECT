package cc.dfsoft.project.biz.base.project.enums;

public enum ProjSourceEnum {
	
	HALL("1","大厅"),
	WX("2","微信公众号"),
	ZFB("3","支付宝"),
	WT("4","网厅");
	
	private final String value;
    
    private final String message;
    
    ProjSourceEnum(String value, String message){
        this.value = value;
        this.message = message;
    }

    public String getValue() {
        return value;
    }
    
    public String getMessage(){
    	return message;
    }
    
    public static ProjSourceEnum valueof(String value) {
    	for(ProjSourceEnum e: ProjSourceEnum.values()) {
    		if(e.getValue().equals(value)) {
    			return e;
    		}
    	}
    	return null;
    }	

}
