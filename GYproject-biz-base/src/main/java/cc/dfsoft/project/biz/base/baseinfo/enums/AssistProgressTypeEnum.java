package cc.dfsoft.project.biz.base.baseinfo.enums;
/**
 * 辅助流程类型
 * 主流程类型为1：辅助流程从2开始
 * @author fulw
 *
 */
public enum AssistProgressTypeEnum {
	
	GAS_PROGRESS("2", "通气流程"),
	ENGINEER_PROGRESS("3", "签证流程"),
	CHANGE_PROGRESS("4", "用户变更流程"),
	DESIGN_CHANGE_PROGRESS("5", "施工变更流程"),
	PAYMENT("6", "付款流程"),
    RECTIFY_NOTICE("7", "整改流程"),
    CONTRAT_UPDATE("8", "安装合同修改"),
    INTE_CONTRAT_UPDATE("9", "智能表合同修改"),
    SUB_CONTRAT_UPDATE("10", "分包合同修改"),
    SUPPL_CONTRAT_UPDATE("11", "补充协议修改"),
    FALL_BACK("12", "回退审核"),
    DIVISIONAL_ACCEPT("13", "分部验收");


    private final String value;
    
    private final String message;
    
    AssistProgressTypeEnum(String value, String message){
        this.value = value;
        this.message = message;
    }

    public String getValue() {
        return value;
    }
    
    public String getMessage(){
    	return message;
    }
    
    public static AssistProgressTypeEnum valueof(String value) {
    	for(AssistProgressTypeEnum e: AssistProgressTypeEnum.values()) {
    		if(e.getValue().equals(value)) {
    			return e;
    		}
    	}
    	return null;
    }
}
