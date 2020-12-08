package cc.dfsoft.project.biz.ifs.project.enums;

import cc.dfsoft.project.biz.ifs.finance.enums.UpdateTypeEnum;

public enum OperateTypeEnum {

	INSERT("1","新增"),
	QUERY("2","查询"),
	MODIFY("3","修改"),
	DELETE("4","删除");
	
	private final String value;
    
    private final String message;
    
    OperateTypeEnum(String value, String message){
        this.value = value;
        this.message = message;
    }

    public String getValue() {
        return value;
    }
    
    public String getMessage(){
    	return message;
    }
    
    public static OperateTypeEnum valueof(String value) {
    	for(OperateTypeEnum e: OperateTypeEnum.values()) {
    		if(e.getValue().equals(value)) {
    			return e;
    		}
    	}
    	return null;
    }
}
