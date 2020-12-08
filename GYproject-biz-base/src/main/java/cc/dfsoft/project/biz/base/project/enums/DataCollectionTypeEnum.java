package cc.dfsoft.project.biz.base.project.enums;

/**
 * 资料类型
 * @author fuliwei
 *
 */
public enum DataCollectionTypeEnum {
	
	DESIGN_DATA("1","设计资料"),
	PROJECT_MANAGE_DATA("2","工程管理资料"),
	PROJECT_TECHNOLOGY_DATA("3","工程技术资料"),
	SURVEY_RECORD("4","工程测量记录"),
	CONSTRUCT_DATA("5","工程施工记录"),
	TEST_DATA("6","工程试验检验记录"),
	ACCEPTANCE_DATA("7","施工验收资料");
	
	private final String value;
    
    private final String message;
    
    DataCollectionTypeEnum(String value, String message){
        this.value = value;
        this.message = message;
    }

    public String getValue() {
        return value;
    }
    
    public String getMessage(){
    	return message;
    }
    
    public static DataCollectionTypeEnum valueof(String value) {
    	for(DataCollectionTypeEnum e: DataCollectionTypeEnum.values()) {
    		if(e.getValue().equals(value)) {
    			return e;
    		}
    	}
    	return null;
    }	
	
	
}
