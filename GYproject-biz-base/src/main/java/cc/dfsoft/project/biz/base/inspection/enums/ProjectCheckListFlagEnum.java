package cc.dfsoft.project.biz.base.inspection.enums;
/**
 * 
 * 描述:报验单完成标记枚举
 * @author liaoyq
 * @createTime 2017年9月15日
 */
public enum ProjectCheckListFlagEnum {
	NO_COMPLETED(0,"待推送"),
	COMPLETED(1,"已完成"),
	AUDIT(2,"审核中"),
	NO_PASS(3,"未通过");
	
	private final Integer value;
    
    private final String message;
    
    ProjectCheckListFlagEnum(Integer value, String message){
        this.value = value;
        this.message = message;
    }

    public Integer getValue() {
        return value;
    }
    
    public String getMessage(){
    	return message;
    }
    
    public static ProjectCheckListFlagEnum valueof(Integer value) {
    	for(ProjectCheckListFlagEnum e: ProjectCheckListFlagEnum.values()) {
    		if(e.getValue().equals(value)) {
    			return e;
    		}
    	}
    	return null;
    }
}
