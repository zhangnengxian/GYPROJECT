package cc.dfsoft.project.biz.base.plan.enums;

public enum GoodsCompleteStatusEnum {
	ALREADY_FINISHIED("0","已发完"),
	HAVE_NOT_FINISHIED("1","未发完");
	
	private final String value;
	private final String message;
	
	GoodsCompleteStatusEnum(String value,String message){
		this.value=value;
		this.message=message;
	}
	
	public String getValue(){
		return value;
	}
	public String getMessage(){
		return message;
	}
	
	public static GoodsCompleteStatusEnum valueof(String value) {
    	for(GoodsCompleteStatusEnum e: GoodsCompleteStatusEnum.values()) {
    		if(e.getValue().equals(value)) {
    			return e;
    		}
    	}
    	return null;
    }
}
