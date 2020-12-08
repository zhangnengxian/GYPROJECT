package cc.dfsoft.project.biz.base.complete.enums;

public enum InspectionOpinionEnum {
	
	INSPECTION_TABLE("1"," 报验表格填写不符合要求，现与退回，请重新填写报验。"),
	INSPECTION_MATERAIL("2"," 工程质量控制资料达不到验收要求，现与退回，请按审查附件要求抓紧完善后再行填表报验。"),
	INSPECTION_QUALITY("3"," 工程实物达不到验收质量要求，请继续做好质量整改合成品维护，准备参加建设单位组织的工程竣工验收。");
	
	private final String value;
    
    private final String message;
    
    InspectionOpinionEnum(String value, String message){
        this.value = value;
        this.message = message;
    }

    public String getValue() {
        return value;
    }
    
    public String getMessage(){
    	return message;
    }
    
    public static InspectionOpinionEnum valueof(String value) {
    	for(InspectionOpinionEnum e: InspectionOpinionEnum.values()) {
    		if(e.getValue().equals(value)) {
    			return e;
    		}
    	}
    	return null;
    }	

}
