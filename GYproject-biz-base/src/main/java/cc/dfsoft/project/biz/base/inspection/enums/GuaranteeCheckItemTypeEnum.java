package cc.dfsoft.project.biz.base.inspection.enums;
/**
 * 分项工程保证项目检查类型
 * @author fuliwei
 *
 */
public enum GuaranteeCheckItemTypeEnum {
	THREADING_PIPE_GUARANTEE("1","管内穿线保证项目"),
	LOW_VOLTAGE_INSTALLATION_GUARANTEE("2","低压安装保证项目");
	
	private final String value;
    
    private final String message;
    
    GuaranteeCheckItemTypeEnum(String value, String message){
        this.value = value;
        this.message = message;
    }

    public String getValue() {
        return value;
    }
    
    public String getMessage(){
    	return message;
    }
    
    public static GuaranteeCheckItemTypeEnum valueof(String value) {
    	if(null!=value){
    	for(GuaranteeCheckItemTypeEnum e: GuaranteeCheckItemTypeEnum.values()) {
    		if(e.getValue().equals(value)) {
    			return e;
    		}
    	}}
    	return null;
    }	
}
