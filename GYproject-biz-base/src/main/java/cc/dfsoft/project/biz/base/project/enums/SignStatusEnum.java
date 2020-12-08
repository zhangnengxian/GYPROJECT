package cc.dfsoft.project.biz.base.project.enums;


public enum SignStatusEnum {
	SUCSE("1","总监未签字",",13,"),
	SUJGJ("2","现场监理员未签字",",55,"),
	PRINCIPAL("3","组长未签字",""),
	BUILDER("4","现场代表未签字",""),
	CENERAL_ENGINEER("5","总工程师未签字",""),
	CU_PM("6","项目经理未签字",",53,"),
	CONSTRUCTION("7","施工员未签字",",61,"),
	SAFTYOFFICER("8","安全员未签字",",58,"),
	QUALITATIVE_CHECK_MEMBER("9","质检员未签字",",59,"),
	WELDER("10","焊工未签字",""),
	TEST_LEADER("11","班组长未签字",""),
	PROJECT_LEADER("12","项目负责人未签字","");
	
	
	private final String value;
    
    private final String message;
    
    private final String post;
    
    SignStatusEnum(String value, String message,String post){
        this.value = value;
        this.message = message;
        this.post = post;
    }

    public String getValue() {
        return value;
    }
    
    public String getMessage(){
    	return message;
    }
    
    
    public String getPost(){
    	return post;
    }
    
    public static SignStatusEnum valueof(String value) {
    	for(SignStatusEnum e: SignStatusEnum.values()) {
    		if(e.getValue().equals(value)) {
    			return e;
    		}
    	}
    	return null;
    }	
    
    
    public static SignStatusEnum byPost(String post) {
		for (SignStatusEnum e : SignStatusEnum.values()) {
			if (e.getPost().equals(post)) {
				return e;
			}
		}
		return null;
	}
}
