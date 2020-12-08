package cc.dfsoft.project.biz.ifs.finance.enums;
/**
 * 
 * 描述:更新类型枚举
 * @author liaoyq
 * @createTime 2017年11月7日
 */
public enum UpdateTypeEnum {
	INSERT("0","新增"),
	UPDATE("1","修改"),
	MODIFY("2","变更"),
	DELETE("3","删除"),
	BACK_MATERIAL("4","退料"),
	RE_MONEY_DEL("2","删除");
	
	private final String value;
    
    private final String message;
    
    UpdateTypeEnum(String value, String message){
        this.value = value;
        this.message = message;
    }

    public String getValue() {
        return value;
    }
    
    public String getMessage(){
    	return message;
    }
    
    public static UpdateTypeEnum valueof(String value) {
    	for(UpdateTypeEnum e: UpdateTypeEnum.values()) {
    		if(e.getValue().equals(value)) {
    			return e;
    		}
    	}
    	return null;
    }
}
