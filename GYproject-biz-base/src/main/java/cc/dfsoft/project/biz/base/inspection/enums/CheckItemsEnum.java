package cc.dfsoft.project.biz.base.inspection.enums;

public enum CheckItemsEnum {
	DESIGN_CHECKING("1","设计检验"),
	FAN_CHECKING("2","风机动作"),
	SOLENOID_VALVE_CHECKING("4","电磁阀动作"),
	CONTROLLER_CHECKING("5","控制器功能"),
	SIGNAL_MODULE_BOX_CHECKING("3","信号模块箱"),
	GANG_CONTROL_BOX_CHECKING("6","联动控制箱"),
	DETECTOR_ALARM_CHECKING("7","探测器报警");
	private final String value;
    
    private final String message;
    
    CheckItemsEnum(String value, String message){
        this.value = value;
        this.message = message;
    }

    public String getValue() {
        return value;
    }
    
    public String getMessage(){
    	return message;
    }
    
    public static CheckItemsEnum valueof(String value) {
    	for(CheckItemsEnum e: CheckItemsEnum.values()) {
    		if(e.getValue().equals(value)) {
    			return e;
    		}
    	}
    	return null;
    }	
}
