package cc.dfsoft.project.biz.base.inspection.enums;
/**
 * 土壤类别
 * @author Administrator
 *
 */
public enum GrSoilTypeEnum {
	
	FIRST_CLASS_SOIL("1","一类"),
	SECOND_CLASS_SOIL("2","二类"),
	THIRD_CLASS_SOIL("3","三类"),
	FOURTH_CLASS_SOIL("4","四类");
	
	private final String value;
    
    private final String message;
    
    GrSoilTypeEnum(String value, String message){
        this.value = value;
        this.message = message;
    }

    public String getValue() {
        return value;
    }
    
    public String getMessage(){
    	return message;
    }
    
    public static GrSoilTypeEnum valueof(String value) {
    	if(null!=value){
    	for(GrSoilTypeEnum e: GrSoilTypeEnum.values()) {
    		if(e.getValue().equals(value)) {
    			return e;
    		}
    	}}
    	return null;
    }	
}
