package cc.dfsoft.project.biz.base.project.enums;

public enum CostTypeEnum {
	
	PUBLIC_CIVIL("11","庭院工程"),
	PUBLIC_COURTYARD_PROJ("12","通用项目工程 庭院部分"),
	ABNORMAL("13","非正常施工和专项施工工程 ");
	
	private final String value;
    
    private final String message;
    
    CostTypeEnum(String value, String message){
        this.value = value;
        this.message = message;
    }

    public String getValue() {
        return value;
    }
    
    public String getMessage(){
    	return message;
    }
    
    public static CostTypeEnum valueof(String value) {
    	for(CostTypeEnum e: CostTypeEnum.values()) {
    		if(e.getValue().equals(value)) {
    			return e;
    		}
    	}
    	return null;
    }	
}
