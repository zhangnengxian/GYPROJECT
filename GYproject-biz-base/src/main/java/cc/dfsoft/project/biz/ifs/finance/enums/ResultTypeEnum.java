package cc.dfsoft.project.biz.ifs.finance.enums;
/**
 * 
 * 描述:返回类型描述枚举类
 * @author liaoyq
 * @createTime 2017年11月9日
 */
public enum ResultTypeEnum {

	SUCCUSS("0","成功"),
	FAIL("1","失败"),
	OTHER("2","其他");
	private final String value;
    
    private final String message;
    
    ResultTypeEnum(String value, String message){
        this.value = value;
        this.message = message;
    }

    public String getValue() {
        return value;
    }
    
    public String getMessage(){
    	return message;
    }
    
    public static ResultTypeEnum valueof(String value) {
    	for(ResultTypeEnum e: ResultTypeEnum.values()) {
    		if(e.getValue().equals(value)) {
    			return e;
    		}
    	}
    	return null;
    }
}
