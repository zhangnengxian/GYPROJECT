package cc.dfsoft.project.biz.base.project.enums;

public enum ProjLtypeEnum {
	
	
	 CIVILIAN("11", "民用"),
	 PUBLIC("12", "公建"),
	 PIPE("13", "改管"),
	 TRUNK("14", "干线"),
	 STATION("16", "场站");

    private final String value;
    
    private final String message;
    
    ProjLtypeEnum(String value, String message){
        this.value = value;
        this.message = message;
    }

    public String getValue() {
        return value;
    }
    
    public String getMessage(){
    	return message;
    }
    
    public static ProjLtypeEnum valueof(String value) {
    	for(ProjLtypeEnum e: ProjLtypeEnum.values()) {
    		if(e.getValue().equals(value)) {
    			return e;
    		}
    	}
    	return null;
    }
}
