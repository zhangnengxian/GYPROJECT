package cc.dfsoft.project.biz.base.project.enums;

/**
 * 通知活动枚举
 * @author sqn
 *
 */
public enum ActionEnum {

	DRAWING_REVIEW("1","图纸会审"),
	CONSTRUCTION_WORK("2","施工交底"),
	TOUCH("3","碰口"),
	JOINT_ACCEPTANCE("4","联合验收"),
	GAS_FEEDBACK("5","通气反馈"),
	ENGINEERING("6","工程签证"),
	ORGANIZATION("7","施工组织"),
	WORK_REPORT("8","开工报告"),
	COMPLETE_REPORT("9","竣工报告");
	
	private final String value;
    
    private final String message;
    
    ActionEnum(String value, String message){
        this.value = value;
        this.message = message;
    }

    public String getValue() {
        return value;
    }
    
    public String getMessage(){
    	return message;
    }
    
    public static ActionEnum valueof(String value) {
    	for(ActionEnum e: ActionEnum.values()) {
    		if(e.getValue().equals(value)) {
    			return e;
    		}
    	}
    	return null;
    }	
}
