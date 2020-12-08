package cc.dfsoft.project.biz.base.baseinfo.enums;
/**
 * 节假日、补班类型
 * @author liaoyq
 * @createTime 2018年5月8日
 */
public enum FestivalTypeEnum {

	HOLIDAY("1","节假日"),
	SUP_CLASS("2","补班"),
	OTHER("3","其他");
	
	private final String value;
    
    private final String message;
    
    FestivalTypeEnum(String value, String message){
        this.value = value;
        this.message = message;
    }

    public String getValue() {
        return value;
    }
    
    public String getMessage(){
    	return message;
    }
    
    public static FestivalTypeEnum valueof(String value) {
    	for(FestivalTypeEnum e: FestivalTypeEnum.values()) {
    		if(e.getValue().equals(value)) {
    			return e;
    		}
    	}
    	return null;
    }
}
