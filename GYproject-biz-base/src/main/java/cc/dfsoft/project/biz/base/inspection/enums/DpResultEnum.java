package cc.dfsoft.project.biz.base.inspection.enums;
/**
 * 检查结果
 * @author Administrator
 *
 */
public enum DpResultEnum {
	QUALIFIED("1","合格"),
	DISQUALIFICATION("2","不合格");
	
	private final String value;
    
    private final String message;
    
    DpResultEnum(String value, String message){
        this.value = value;
        this.message = message;
    }

    public String getValue() {
        return value;
    }
    
    public String getMessage(){
    	return message;
    }
    
    public static DpResultEnum valueof(String value) {
    	if(null!=value){
    	for(DpResultEnum e: DpResultEnum.values()) {
    		if(e.getValue().equals(value)) {
    			return e;
    		}
    	}}
    	return null;
    }	
}
