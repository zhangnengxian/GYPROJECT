package cc.dfsoft.project.biz.base.subpackage.enums;
/**
 * 
 * 描述:智能表分包合同额每户金额枚举类
 * @author liaoyq
 * @createTime 2017年10月17日
 */
public enum SubConIntelligentCashEnum {
	FIRST_CASH("270.00","270.00"),
	SECOND_CASH("460.00","460.00");
	
	private final String value;
	private final String message;
	
	SubConIntelligentCashEnum(String value,String message){
		this.value=value;
		this.message=message;
	}
	
	public String getValue(){
		return value;
	}
	public String getMessage(){
		return message;
	}
	
	public static SubConIntelligentCashEnum valueof(String value) {
    	for(SubConIntelligentCashEnum e: SubConIntelligentCashEnum.values()) {
    		if(e.getValue().equals(value)) {
    			return e;
    		}
    	}
    	return null;
    }
}
