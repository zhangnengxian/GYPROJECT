package cc.dfsoft.uexpress.biz.sys.dept.enums;

public enum DeptDivideEnum {
	COMPREHENSIVE_GROUP("1","综合组"),
	TECHNOLOGY_GROUP("2","技术组"),
	CIVIL_GROUP("3","民用组"),
	MARKETING_CENTER("4","营销中心"),
	TRUNK_GROUP("5","干线组"),
	MODIFICATION_GROUP("6","改管组"),
	QUALITY_SAFETY_GROUP("7","质量安全组"),
	PRE_SETTLEMENT_GROUP("8","预结算组"),
	CUSTOMER_SERVICE_CENTER("9","客服中心"),
	ENGINEERING_CONSTRUCTION_CENTER("10","工程建设中心"),
	DESIGN_INSTITUTE("11","设计院"),
	MARKET_DEVELOPMENT_DEPARTMENT("12","市场发展部"),
	GAS_COMPANY("13","燃气公司"),
	INTELLIGENT_METER("14","智能表组");
	private final String value;
    
    private final String message;
    
    DeptDivideEnum(String value, String message){
        this.value = value;
        this.message = message;
    }

    public String getValue() {
        return value;
    }
    
    public String getMessage(){
    	return message;
    }
    
    public static DeptDivideEnum valueof(String value) {
    	for(DeptDivideEnum e: DeptDivideEnum.values()) {
    		if(e.getValue().equals(value)) {
    			return e;
    		}
    	}
    	return null;
    }	
}
