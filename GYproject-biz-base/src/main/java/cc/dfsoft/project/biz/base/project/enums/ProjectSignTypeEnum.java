package cc.dfsoft.project.biz.base.project.enums;
/**
 * 
 * 描述:工程标记类型枚举类-主要用于工程标记：工程存在枚举中的情况，会统一标记为红色
 * @author liaoyq
 * @createTime 2017年12月11日
 */
public enum ProjectSignTypeEnum {
	SPECIAL("1","特殊工程"),
	UNPAID("2","未交钱工程或首付款未交清"),
	INVOICE("3","虚拟发票"),
	INCOMPLETE_COST("4","款项未交清"),
	NO_SUP_MONEY("5","补充协议未缴款");
	
	private final String value;
    
    private final String message;
    
    ProjectSignTypeEnum(String value, String message){
        this.value = value;
        this.message = message;
    }

    public String getValue() {
        return value;
    }
    
    public String getMessage(){
    	return message;
    }
    
    public static ProjectSignTypeEnum valueof(String value) {
    	for(ProjectSignTypeEnum e: ProjectSignTypeEnum.values()) {
    		if(e.getValue().equals(value)) {
    			return e;
    		}
    	}
    	return null;
    }	
    
    public static String[] getMessages(){
    	String[] messages = new String[AreaEnum.values().length];
    	for(int i=0;i<AreaEnum.values().length;i++){
    		messages[i]=AreaEnum.values()[i].getMessage();
    	}
    	return messages;
    }

}
