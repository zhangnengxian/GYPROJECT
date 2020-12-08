package cc.dfsoft.uexpress.biz.sys.dept.enums;

import java.util.ArrayList;
import java.util.List;



/**
 * 职位类型枚举
 * @author pengtt
 *
 */
public enum PostTypeEnum {

    ADMIN(",999,", "管理员"),
    CHAIRMAN(",10,", "董事长"),

	GENERAL_MANAGER(",11,", "总经理"), 
	GENERAL_MANAGER_ASSISTANT(",111,", "总经理助理"), 
	VICE_GENERAL_MANAGER(",12,", "副总经理"), 
	SUCSE(",13,","总监"),
	
	MINISTER(",14,","部长"),
	VICE_MINISTER(",15,","副部长"),
	CIVIL_HEAD(",151,","民用负责人"),
	MARKETING_CENTER_HEAD(",152,","公建负责人"),
	MODIFICATION_HEAD(",153,","改管负责人"),
	TRUNK_HEAD(",154,","干线负责人"),
	BUDGET_HEAD(",155,","预算负责人"),

	CIVIL_GROUP_LEADER(",210,","民用组长"),
	MARKETING_CENTER_LEADER(",211,","营销组长"),
	MARKETING_CENTER_LEADER_DEPUTY(",216,","营销副组长"),
	SALESMA(",79,","市场营销员"), //造价员
	MODIFICATION_GROUP_LEADER(",212,","改管组长"),
	TRUNK_GROUP_LEADER(",213,","干线组长"),
	BUDGET_GROUP_LEADER(",214,","预结算组组长"),
	GROUP_LEADER(",215,","组长"),
	DEPUTY_LEADER(",217,","副组长"),
	CUSTOMER_SERVICER(",22,", "接待受理员"),
	SURVEYER(",23,","踏勘员"),
	DIRECTOR(",24,", "客户经理"), 
	STATISTICAL_PLANNER (",25,", "计划统计员"), 
	CONTRACT_MANAGER(",26,","合同管理员"),
	BUSINESSASSISTANT(",27,","资料员"),
	DRAWING_MANAGER(",28,","档案管理员"),
	PRODUCTION_STATISTICIANS(",430,", "技术员"),//计量所
	COMPREHENSIVE_MEMBER(",30,","内勤"),
	COMPREHENSIVE_MEMBER_OUT(",31,","外勤"),
	REGULAT_CLASS(",86,","巡线调压工"),
	
	BUDGET_MEMBER (",41,", "预结算员"),
	/*SETTLEMENT_CLERK("42", "结算员"), */
	
	DESIGNER(",43,", "设计员"),
	DESIGN_DIRECTOR(",44,","设计所长"),
	
	TOLL_COLLECTOR(",45,","收费员"),
	TREASURER(",46,","财务员"),
	CASHIER(",47,","出纳员"),
	WEBSITE_ENGINEER (",48,","资金办理员"),
	
	
	SUB_PRINCIPAL(",51,","分包负责人"),
	SUB_FIELDPRINCIPAL(",52,","分包现场负责人"),
	CU_PM(",53,", "项目经理"),
	BUILDER(",54,","现场管理员"),
	SUJGJ(",55,","现场监理员"),
	TECHNICIAN(",56,","质量安全员"),//质量安全组
	MANAGEMENTQAE(",57,","质保师"),
	SAFTYOFFICER(",58,","安全员"),
	QUALITATIVE_CHECK_MEMBER (",59,","质检员"),
	MANAGEMENTWACF(",60,","材料员"),
	CONSTRUCTION(",61,","施工员"),
	SURVEYOR(",62,","测量员"),
	WELDER(",63,","焊工"),
	TEST_LEADER(",64,","班(组)长"),
	PROFESSIONAL_SUPERVISION(",65,","专业监理师"),
	SUCSE_REPRESENTATIVE(",66,","总监代表"),
	
	DEPUTY_DIRECTOR(",67,", "支部书记"), 
	MANAGER(",68,", "部长助理"), 
	COST_MANAGER(",102,","经理"),
	AREAMANAGER(",69,","项目管理员"),
	/*BUILDER(",104,","现场管理员"),*/
	COMPREHENSIVE_STATISTICS(",70,","内业管理员"),
	DISTRIBUTIONER(",71,","处长"),
	DESIGN_DEAN(",72,","副处长"),
	/*SUPERVISOR(",107,", "监理"), */
	CHIEF_ENGINEER(",73,","主任"),
	RECORDER(",74,","副主任"),
	DATA_HANDLE(",75,","数据处理员"),//管网数据中心
	AUDITOR(",76,","审计员"),
	
	SUB_BUDGETER(",110,","分包预算员"),
	REVIEWER(",112,","复核人"),
	TECHNIAL_LEADER(",113,","技术负责人"),
	AUDIT_OFFICER(",114,","审核负责人"),
	CHECKER(",115,","检测员"),
	PROJECT_LEADER(",116,","项目负责人"),
	CU_MANAGER(",117,","施工经理"),
	CUST_REPRESENT(",118,","甲方代表"),
	WELD_LEADER(",119,","焊接责任人"),
	OPERATOR(",120,","操作员"),
	CENERAL_ENGINEER(",121,","总工程师"),
	PROCESS_TECHNICIAN(",122,","工艺技术员"),//输配
	INSPECTOR(",126,","验收员"),//客服中心
	EQUIPMENT_TECHNICIAN(",124,","技术装备员"),//技术装备部
	USER_SIGN(",166,","用户"),
	DATAMANAGER(",93,","数据管理员"),
	ACCOUNTING(",125,","会计");
	
	
	private final String value;

	private final String message;
	

	PostTypeEnum(String value, String message) {
		this.value = value;
		this.message = message;
	}

	public String getValue() {
		return value;
	}

	public String getMessage() {
		return message;
	}
	

	public static PostTypeEnum valueof(String value) {
		for (PostTypeEnum e : PostTypeEnum.values()) {
			if (e.getValue().equals(value)) {
				return e;
			}
		}
		return null;
	}
	
	public static List<PostTypeEnum> getObj() {
  		List<PostTypeEnum> enums=new ArrayList<PostTypeEnum>();
  		for (PostTypeEnum e : PostTypeEnum.values()) {
  				enums.add(e);
  		}
  		return enums;
  	}
}
