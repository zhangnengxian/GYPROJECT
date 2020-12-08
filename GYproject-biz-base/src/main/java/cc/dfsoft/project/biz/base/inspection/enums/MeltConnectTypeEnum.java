package cc.dfsoft.project.biz.base.inspection.enums;
/**
 * pe管焊缝检查连接类型枚举
 * @author liaoyq
 *
 */
public enum MeltConnectTypeEnum {
	ELECTRIC_MELT("1","电熔连接"),
	HOT_MELT("2","热熔连接");
	
	private final String value;
	private final String message;
	
	MeltConnectTypeEnum(String value, String message){
	        this.value = value;
	        this.message = message;
	    }

	    public String getValue() {
	        return value;
	    }
	    
	    public String getMessage(){
	    	return message;
	    }
	    
	    public static MeltConnectTypeEnum valueof(String value) {
	    	for(MeltConnectTypeEnum e: MeltConnectTypeEnum.values()) {
	    		if(e.getValue().equals(value)) {
	    			return e;
	    		}
	    	}
	    	return null;
	    }
}
