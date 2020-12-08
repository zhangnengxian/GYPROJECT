package cc.dfsoft.project.biz.ifs.material.enums;

/**
 * 
 * 描述:材料表类型
 * @author liaoyq
 * @createTime 2017年11月22日
 */
public enum MaterialTableTypeEnum {
	MATERIAL("1","主材表"),
	CHANGE_MATERIAL("2","变更材料表");
	private final String value;
    
    private final String message;
    
    MaterialTableTypeEnum(String value, String message){
        this.value = value;
        this.message = message;
    }

    public String getValue() {
        return value;
    }
    
    public String getMessage(){
    	return message;
    }
    
    public static MaterialTableTypeEnum valueof(String value) {
    	for(MaterialTableTypeEnum e: MaterialTableTypeEnum.values()) {
    		if(e.getValue().equals(value)) {
    			return e;
    		}
    	}
    	return null;
    }
}
