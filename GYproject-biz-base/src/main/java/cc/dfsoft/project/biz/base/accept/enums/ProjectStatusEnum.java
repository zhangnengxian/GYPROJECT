package cc.dfsoft.project.biz.base.accept.enums;


public enum ProjectStatusEnum {

	TO_SURVEY("1", "待勘察一级派遣"),
	//PROJECT_CONFIRMED("2", "已立项确认"),
	 BACK_NO_INFORM("3", "退单未通知");

    private final String value;
    
    private final String message;
    
    ProjectStatusEnum(String value, String message){
        this.value = value;
        this.message = message;
    }

    public String getValue() {
        return value;
    }
    
    public String getMessage(){
    	return message;
    }
    
    public static ProjectStatusEnum valueof(String value) {
    	for(ProjectStatusEnum e: ProjectStatusEnum.values()) {
    		if(e.getValue().equals(value)) {
    			return e;
    		}
    	}
    	return null;
    }
    public static String[] getMessages() {
		String[] messages=new String[ProjectStatusEnum.values().length];
		for(int i=0;i<ProjectStatusEnum.values().length;i++){
			messages[i] = ProjectStatusEnum.values()[i].getMessage();
		}
    	return messages;
    }
}
