package cc.dfsoft.project.biz.base.inspection.enums;
/**
 * 除锈防腐检查工序
 * @author Administrator
 *
 */
public enum DpCheckRequireTypeEnum {
	
	SPRAY("01","喷射除锈"),
	LABOUR("02","人工除锈"),
	COAT("03","防腐涂层"),
	SWATHING_BAND("04","缠带检查"),
	OVERLAP_WIDTH("05","搭接宽度"),
	PEEL_STRENGTH("06","剥离强度");
	
	private final String value;
    
    private final String message;
    
    DpCheckRequireTypeEnum(String value, String message){
        this.value = value;
        this.message = message;
    }

    public String getValue() {
        return value;
    }
    
    public String getMessage(){
    	return message;
    }
    
    public static DpCheckRequireTypeEnum valueof(String value) {
    	if(null!=value){
    	for(DpCheckRequireTypeEnum e: DpCheckRequireTypeEnum.values()) {
    		if(e.getValue().equals(value)) {
    			return e;
    		}
    	}}
    	return null;
    }	
}
