package cc.dfsoft.project.biz.base.inspection.enums;
/**
 * 分项工程基本项目检查类型
 * @author fuliwei
 *
 */
public enum BasicCheckItemTypeEnum {
	THREADING_PIPE_BASIC("1","管内穿线基本项目"),
	THREADING_PIPE_ALLOWABLE_ERROR("2","管内穿线允许偏差项目"),
	LOW_VOLTAGE_INSTALLATION_BASIC("3","低压安装基本项目");
	
	private final String value;
    
    private final String message;
    
    BasicCheckItemTypeEnum(String value, String message){
        this.value = value;
        this.message = message;
    }

    public String getValue() {
        return value;
    }
    
    public String getMessage(){
    	return message;
    }
    
    public static BasicCheckItemTypeEnum valueof(String value) {
    	if(null!=value){
    	for(BasicCheckItemTypeEnum e: BasicCheckItemTypeEnum.values()) {
    		if(e.getValue().equals(value)) {
    			return e;
    		}
    	}}
    	return null;
    }	
}
