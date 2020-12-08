package cc.dfsoft.project.biz.base.project.enums;

public enum SignPostEnum {
	GENERAL_MANAGER(",11,", "总经理"),
	VICE_GENERAL_MANAGER(",12,", "副总经理"), 
	SUCSE(",13,","总监"),
	VICE_MINISTER(",15,","副部长"),
	CONTRACT_MANAGER(",26,","合同管理员"),
	BUDGET_MEMBER (",41,", "预结算员"),
	DESIGNER(",43,", "设计员"),
	TREASURER(",46,","财务员"),
	CU_PM(",53,", "项目经理"),
	BUILDER(",54,","现场管理员"),
	SUJGJ(",55,","现场监理员"),
	TECHNICIAN(",56,","质量安全员"),//质量安全组
	SAFTYOFFICER(",58,","安全员"),
	QUALITATIVE_CHECK_MEMBER (",59,","质检员"),
	CONSTRUCTION(",61,","施工员"),
	WELDER(",63,","焊工"),
	TEST_LEADER(",64,","班(组)长"),
	DATA_HANDLE(",75,","数据处理员"),//管网数据中心
	CHECKER(",115,","检测员"),//安发
	PROJECT_LEADER(",116,","项目负责人"),//组长
	CENERAL_ENGINEER(",121,","总工程师"),
	PROCESS_TECHNICIAN(",122,","工艺技术员"),//输配
	INSPECTOR(",126,","验收员"),//客服中心
	EQUIPMENT_TECHNICIAN(",124,","技术装备员"),//技术装备部
	BUDGET_GROUP_LEADER(",214,","预结算组组长"),
	GROUP_LEADER(",215,","组长"),//技术组组长
	PRODUCTION_STATISTICIANS(",430,", "技术员"),//计量所
	MARKET(",79,", "市场营销员");//市场营销员
	private final String value;
    
    private final String message;
    
    SignPostEnum(String value, String message){
        this.value = value;
        this.message = message;
    }

    public String getValue() {
        return value;
    }
    
    public String getMessage(){
    	return message;
    }
    
    public static SignPostEnum valueof(String value) {
    	for(SignPostEnum e: SignPostEnum.values()) {
    		if(e.getValue().equals(value)) {
    			return e;
    		}
    	}
    	return null;
    }
}
