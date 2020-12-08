package cc.dfsoft.project.biz.base.settlement.enums;
/**
 * 
 * 描述:工程量状态枚举
 * @author liaoyq
 * @createTime 2017年8月10日
 */
public enum SubQuantitiesStatusEnum {
	BUDGET(1,"施工预算"),
	SETTLEMENT(2,"结算");
	
	
	private final Integer value;
    
    private final String message;
    
    SubQuantitiesStatusEnum(Integer value, String message){
        this.value = value;
        this.message = message;
    }

    public Integer getValue() {
        return value;
    }
    
    public String getMessage(){
    	return message;
    }
    
    public static SubQuantitiesStatusEnum valueof(String value) {
    	for(SubQuantitiesStatusEnum e: SubQuantitiesStatusEnum.values()) {
    		if(e.getValue().equals(value)) {
    			return e;
    		}
    	}
    	return null;
    }
}
