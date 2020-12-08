package cc.dfsoft.project.biz.ifs.material.enums;
/**
 * 
 * 描述：操作类型枚举描述
 * @author liaoyq
 * @createTime 2017年11月8日
 */
public enum MaterialOperateTypeEnum {
	INSERT("0","新增材料清单"),
	CHANGE("1","变更材料清单"),
	CHANGE_INSERT("3","变更增加材料");//弃用
	
	private final String value;
    
    private final String message;
    
    MaterialOperateTypeEnum(String value, String message){
        this.value = value;
        this.message = message;
    }

    public String getValue() {
        return value;
    }
    
    public String getMessage(){
    	return message;
    }
    
    public static MaterialOperateTypeEnum valueof(String value) {
    	for(MaterialOperateTypeEnum e: MaterialOperateTypeEnum.values()) {
    		if(e.getValue().equals(value)) {
    			return e;
    		}
    	}
    	return null;
    }
}
