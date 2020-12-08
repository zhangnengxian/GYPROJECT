package cc.dfsoft.project.biz.base.inspection.enums;
/**
 * 除锈防腐检查方式
 * @author Administrator
 *
 */
public enum DpCkeckMethodEnum {
	
	OBSERVATION("1","逐根观察检查"),
	METERING("2","尺量"),
	SPRING("3","用弹簧秤测试");
	private final String value;
    
    private final String message;
    
    DpCkeckMethodEnum(String value, String message){
        this.value = value;
        this.message = message;
    }

    public String getValue() {
        return value;
    }
    
    public String getMessage(){
    	return message;
    }
    
    public static DpCkeckMethodEnum valueof(String value) {
    	if(null!=value){
    	for(DpCkeckMethodEnum e: DpCkeckMethodEnum.values()) {
    		if(e.getValue().equals(value)) {
    			return e;
    		}
    	}}
    	return null;
    }	
}
