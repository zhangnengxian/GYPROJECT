package cc.dfsoft.project.biz.base.project.enums;

import java.util.Map;

/**
 * 用于甘特图查询  color属性用于项目管理分析屏
 * @author pengtt
 * @createTime 2016-08-09
 *
 */
public enum StepLtypeEnum {
	
	PROJECT_ACCEPT("11","立项","#ff7e50"),
	PROJECT_DESIGN("12","设计","#8bb1f6"),
	PROJECT_BUDGET("13","预算","#a38ff7"),
	PROJECT_CONTRACT("14","合同","#ffc65c"),
	PROJECT_PLAN("15","计划","#6494ed"),
	SUB_CONTRACT("16","分包","#54ebdb"),
	CONSTRUCTION("17","施工","#feb59a"),
	CONSTRUCTION_END("18","竣工","#b9aaf7"),
	STATEMENT("19","结算","#ffdfa5"),
	STATION_ACCEPT("70","场站立项","#ff7e50"),
	AUDIT_PROCEDURES("71","建审手续","#8bb1f6"),
	STATION_CONSTRUCTION("72","工程施工","#a38ff7"),
	GETSTATION_SETTLEMENT("73","工程结算","#ffc65c");
	
	private final String value;
    
    private final String message;
    
    private final String color;
    
    
    StepLtypeEnum(String value, String message,String color){
        this.value = value;
        this.message = message;
        this.color = color;
    }

    public String getValue() {
        return value;
    }
    
    public String getMessage(){
    	return message;
    }
    
    public String getColor(){
    	return color;
    }

	public static StepLtypeEnum valueof(String value) {
    	for(StepLtypeEnum e: StepLtypeEnum.values()) {
    		if(e.getValue().equals(value)) {
    			return e;
    		}
    	}
    	return null;
    }	
	
	public static String[] getMessages() {
		String[] messages=new String[StepLtypeEnum.values().length];
		for(int i=0;i<StepLtypeEnum.values().length;i++){
			messages[i] = StepLtypeEnum.values()[i].getMessage();
		}
    	return messages;
    }
	
}
