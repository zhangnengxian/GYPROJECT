package cc.dfsoft.project.biz.base.inspection.enums;
/**
 * 强度试验管道类型枚举类
 * @author liaoyq
 *
 */
public enum StrTestPipeTypeEnum {

	COURTYARD_PIPE("1","干线、改管、庭院、公建管道"),
	INDOOR_PIPE("2","户内管道");
	private final String value;
    
    private final String message;
    
    StrTestPipeTypeEnum(String value, String message){
        this.value = value;
        this.message = message;
    }

    public String getValue() {
        return value;
    }
    
    public String getMessage(){
    	return message;
    }
    
    public static StrTestPipeTypeEnum valueof(String value) {
    	for(StrTestPipeTypeEnum e: StrTestPipeTypeEnum.values()) {
    		if(e.getValue().equals(value)) {
    			return e;
    		}
    	}
    	return null;
    }	
}
