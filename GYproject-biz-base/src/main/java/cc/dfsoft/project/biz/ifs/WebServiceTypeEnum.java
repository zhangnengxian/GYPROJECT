package cc.dfsoft.project.biz.ifs;

/**
 * 接口类型枚举
 * @author liaoyq
 * @createTime 2018年4月22日
 */
public enum WebServiceTypeEnum {

	PROJ_ACCEPT_INSERT("J-A","立项存在借料工程新增"),
	PROJ_ACCEPT_UPDATE("J-B","立项存在借料工程修改"),
	PROJ_ACCEPT_DELETE("J-C","立项存在借料工程删除"),
	CONTRACT_SIGN("0-A","安装合同新增"),
	CONTRACT_UPDATE("0-B","安装合同变更"),
	CONTRACT_DELETE("0-C","安装合同删除"),
	SUBCONTRACT_SIGN("1-A","施工合同新增"),
	SUBCONTRACT_UPDATE("1-B","施工合同变更"),
	SUBCONTRACT_DELETE("1-C","施工合同删除"),
	PROJECT_SETTLEMENT("2-A","工程结算"),
	PROJECT_SETTLEMENT_UPDATE("2-B","工程结算信息修改"),
	MATERIAL("3-A","物料计划单接口"),
	MATERIAL_UPDATE("3-B","物料变更接口"),
	MATERAIL_COLLOR("4-A","领料单接口"),
	RECIVE_MONEY("5-A","收款登记"),
	RECIVE_MONEY_UPDATE("5-B","收款修改"),
	RECIVE_MONEY_DELETE("5-C","收款删除"),
	PAY_MONEY("6-A","付款登记"),
	PAY_MONEY_UPDATE("6-B","付款修改"),
	PAY_MONEY_DELETE("6-C","付款删除"),
	PAY_MONEY_SUCCESS("7-A","付款成功接口"), 
	ACCEPT_INSERT("AC-1","报装增加工程"), 
	ACCEPT_UPDATE("AC-3","报装修改工程"), 
	ACCEPT_DEL("AC-4","报装删除工程"), 
	ACCEPT_QUERY("AC-2","报装查询工程"),

	CONSTRUCTION_TASK("SC-1","施工任务单新增/修改"),
	CONSTRUCTION_TASK_DEL("SC-2","施工任务单删除"),

	CONSTRUCTION_WORK("SC-CW-1","会审交底信息新增/修改"),
	CONSTRUCTION_WORK_DEL("SC-CW-2","会审交底信息删除"),

	WORK_REPORT("SC-WR-1","开工报告信息新增/修改"),
	WORK_REPORT_DEL("SC-WR-2","开工报告信息删除"),

	PRE_SETTLEMENT("SC-BS-1","施工预结算信息新增/修改"),
	PRE_SETTLEMENT_DEL("SC-BS-2","施工预结算信息删除"),

	SUB_CONTRACT("SC-C-1","施工合同信息新增/修改"),
	SUB_CONTRACT_DEL("SC-C-2","施工合同信息删除"),

	SELF_CHECK("SC-SR-1","自检信息新增/修改"),
	SELF_CHECK_DEL("SC-SR-2","自检信息删除"),

	PREINSPECTION("SC-PR-1","预验收信息新增/修改"),
	PREINSPECTION_DEL("SC-PR-2","预验收信息删除"),

	JOINT_ACCEPTANCE("SC-JA-1","联合验收信息新增/修改"),
	JOINT_ACCEPTANCE_DEL("SC-JA-1","联合验收信息删除");


	private final String value;
    
    private final String message;
    
    WebServiceTypeEnum(String value, String message){
        this.value = value;
        this.message = message;
    }

    public String getValue() {
        return value;
    }
    
    public String getMessage(){
    	return message;
    }
    
    public static WebServiceTypeEnum valueof(String value) {
    	for(WebServiceTypeEnum e: WebServiceTypeEnum.values()) {
    		if(e.getValue().equals(value)) {
    			return e;
    		}
    	}
    	return null;
    }
}
