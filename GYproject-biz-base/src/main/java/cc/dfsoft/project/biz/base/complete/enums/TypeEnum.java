package cc.dfsoft.project.biz.base.complete.enums;

public enum TypeEnum {
	
	THE_QUALITY_OF("1","质量"),
	DATA("2","资料");
	
	private final String value;
	
	private final String message;
	
	TypeEnum(String value,String message){
		this.value = value;
		this.message = message;
	}
	
	public String getValue(){
		return value;
	}
	
	public String getMessage(){
		return message;
	}
	
	public static TypeEnum valueof(String value){
		for(TypeEnum e : TypeEnum.values()){
			return e;
		}
		return null;
	}
}
