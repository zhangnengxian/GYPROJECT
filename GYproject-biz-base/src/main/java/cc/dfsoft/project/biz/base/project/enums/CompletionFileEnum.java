package cc.dfsoft.project.biz.base.project.enums;
/**
 * 
 * 描述:竣工资料附件
 * @author liaoyq
 * @createTime 2018年1月17日
 */
public enum CompletionFileEnum {
	CON_ORG("120104","施工组织"),
	DESIGN_ALTER("120205","变更记录"),
	ENGINEER("120206","签证记录"),
	NDE_RECORD("120214","无损检测"),
	PURGE_RECORD("130111","吹扫记录"),
	INDOOR_WATCH("130132","户内挂表"),
	CLEAR_RECORD("130136","清扫记录"),
	PIPE_WELDING_RECORD("130137","焊口记录");
	
	private final String value;
    private final String message;
    CompletionFileEnum(String value, String message){
        this.value = value;
        this.message = message;
    }

    public String getValue() {
        return value;
    }
    
    public String getMessage(){
    	return message;
    }
    
    public static CompletionFileEnum valueof(String value) {
    	for(CompletionFileEnum e: CompletionFileEnum.values()) {
    		if(e.getValue().equals(value)) {
    			return e;
    		}
    	}
    	return null;
    }	
}
