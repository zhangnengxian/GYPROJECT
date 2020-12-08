package cc.dfsoft.uexpress.common.constant;

import java.util.Date;

/**
 * 公共常量类
 * @author van.zheng
 *
 */
public class Constants extends ConstantsMap{
	
	/** 租户ID默认值，临时使用，后续去掉 */
	public static final String TENANT_ID_VALUE = "1000000";
	/** 会话登录信息对象 */
	public static final String SESSION_LOGININFO = "session_loginInfo";
	
	/** 结果返回码 */
	public static final String RESULT_CODE = "resultCode";
	/** 结果返回信息 */
	public static final String RESULT_MSG = "resultMsg";
	/** 结果 */
	public static final String RESULT = "result";
	
	/** uexpress-biz-sys模块-00开头 */
	/** 部门相关模块号 */
	public static final String MODULE_CODE_DEPT = "0001";
	/** 权限相关模块号 */
	public static final String MODULE_CODE_AUTH = "0002";

	
	/**project-biz-base模块 工程项目-01 开头*/
	
	/**立项*/
	public static final String MODULE_CODE_PROJECTAPPROVAL = "0101";
	
	/**设计*/
	public static final String MODULE_CODE_DESIGN = "0102";
	
	/**审核*/
	public static final String MODULE_CODE_AUDIT = "0108";

	/*税率*/
	public static final String MODULE_CODE_INCRE = "0101";
	
	/**预算*/
	public static final String MODULE_CODE_BUDGET="0103";

	/**
	 * 预算方式
	 */
	public static final  String MODULE_CODE_BUDGET_TYPE="010301";
	/**合同*/
	public static final String MODULE_CODE_CONTRACT="0104";
	
	/**计划*/
	public static final String MODULE_CODE_CONSTRUCTIONPLAN="0105";
	
	/**分包*/
	public static final String MODULE_CODE_SUBCONTRACT="0107";
	
	/**竣工*/
	public static final String MODULE_CODE_COMPLETE="0110";
	
	/**结算*/
	public static final String MODULE_CODE_SETTLEMENT="0111";
	
	/**结算初审、施工预算初审签字职务*/
	public static final String FIRST_SETTLEMENT_POST="firstSettlement";
	//报验审核签字
	public static final String AUDIT_INS_POST = "auditInsPost";
	
	/**结算终审签字职务*/
	public static final String End_SETTLEMENT_POST="suPrincipal";
	
	
	/**收费*/
	public static final String MODULE_CODE_COST="0106";
	
	/**变更记录*/
	public static final String MODULE_CODE_CHANGE="0109";

	/**project-biz-base模块 工程施工-02 开头*/
	
	/**开工*/
	public static final String MODULE_CODE_PROJECTSTART="0201";
	
	/**过程*/
	public static final String MODULE_CODE_PROCESS="0202";
	
	/**材料*/
	public static final String MODULE_CODE_MATERIAL="0203";

	/**碰口*/
	public static final String MODULE_CODE_PROJECTTOUCH="0204";
	
	/**project-biz-base模块 工程报验-03 开头*/
	
	/**焊条领用*/
	public static final String MODULE_CODE_ELECTRODE_RECORD="0301";
	/**焊条烘烤*/
	public static final String MODULE_CODE_ELECTRODE_BAKING="0302";
	/**沟槽开挖*/
	public static final String MODULE_CODE_GROOVE_RECORD="0303";
	/**管道安装*/
	public static final String MODULE_CODE_PIPELINE_INSTALL="0304";
	/**钢管焊接*/
	public static final String MODULE_CODE_PIPEWELDING="0305";
	/**PE管焊接*/
	public static final String MODULE_CODE_PEPIPEWELDING="0306";
	/**防腐记录*/
	//public static final String MODULE_CODE_PRESERVATIVE_RECORD="0307";
	/**除锈工序  -防腐记录*/
	public static final String MODULE_CODE_DERUSTING="0307";
	/**防腐检查*/
	public static final String MODULE_CODE_PRESERVATIVE_INPECT="0308";
	/**隐蔽工程*/
	public static final String MODULE_CODE_HIDDEN_WORKS="0309";
	/**吹扫记录*/
	public static final String MODULE_CODE_PURGE="0310";
	/**埋地检查*/
	public static final String MODULE_CODE_UNDER_GROUND_INPECT="0311";
	/**沟槽回填*/
	public static final String MODULE_CODE_TRENCH_BACK_FILL="0312";
	/**阀门试验*/
	public static final String MODULE_CODE_VALVE_TEST="0313";
	/**安装汇总*/
	public static final String MODULE_CODE_INSTALL_SUMMARY="0314";
	/**通球记录*/
	public static final String MODULE_CODE_BALL_RECORD="0315";
	/**强度试验*/
	public static final String MODULE_CODE_STRENGTH_TEST="0316";
	/**户内挂表*/
	public static final String MODULE_CODE_INDOOR_POCKET_WATCH="0317";
	/**设备安装*/
	public static final String MODULE_CODE_EQUIPMENT_INSTALL="0318";
	/**热熔对接*/
	public static final String MODULE_CODE_HOT_MELT_DOCKING="0319";
	/**阳极安装*/
	public static final String MODULE_CODE_ANODE_INSTALL="0320";
	/**管道焊缝检查*/
	public static final String MODULE_CODE_WELD_LINE_INPECT="0321";
	/**PE管焊缝检查*/
	public static final String MODULE_CODE_PE_WELD_LINE_INPECT="0322";
	/**清扫记录*/
	public static final String MODULE_CODE_CLEAR_RECORD = "0323";
	/**焊口记录*/
	public static final String MODULE_CODE_PIPE_WELD_RECORD = "0324";
	/**计量记录*/
	public static final String MODULE_CODE_MEASUREMENT = "0325";
	
	/**测量放线*/
	public static final String MODULE_CODE_SURVEY_LINING="0301";
	
	/**高程测量*/
	public static final String MODULE_CODE_ALTIMETRIC_SURVEY="0302";
	
	/**防腐工序*/
	public static final String MODULE_CODE_PRESERVATIVE="0305";
	/**沟槽回填*/
	public static final String MODULE_CODE_TRENCHBACKFILL="0308";

	/**隐蔽工程*/
	//public static final String MODULE_CODE_HIDDEN_WORKS="0309";

	/**吹扫记录*/
	//public static final String MODULE_CODE_PURGE="0310";
	
	/**试压记录*/
	public static final String MODULE_CODE_PRESSURE="0311";
	
	/**管内穿线*/
	public static final String THREADING_PIPE="0314";
	
	/**低压安装*/
	public static final String LOW_VOLTAGE_INSTALLATION="0315";
	
	/**电器绝缘电阻*/
	public static final String INSULATION_RESISTANCE_TEST="0316";
	
	/**接地电阻测试*/
	public static final String GROUND_RESISTANCE_TEST="0317";
	
	/**控制系统调试*/
	public static final String CONTROL_DEBUGGING="0318";
	/**报验资料控制*/
	public static final String CHECK_LIST="0325";

	/**复压记录*/
	public static final String PRESSURE_RECORDS="0326";
	
	
	/**通知记录*/
	public static final String MODULE_CODE_NOTICE = "0410";
	
	/**延期申请*/
	public static final String MODULE_CODE_DELAY = "0411";
	
	/**回退申请*/
	public static final String FALLBACK_APPLY = "0412";
	
	/**拍照*/
	public static final String MOBILE_CODE = "0502";
	/**附件*/
	public static final String FILE_CODE_ACCESS = "0501";
	
	/**基础*/
	public static final String BASIS="0602";
	
	/**标准*/
	public static final String STANDARD="0601";
	
	/** 操作结果*/
	/** 成功 */
	public static final String OPERATE_RESULT_SUCCESS= "true";
	/** 失败 */
	public static final String OPERATE_RESULT_FAILURE = "false";
	/**数据不完整*/
	public static final String OPERATE_RESULT_DATA_INCOMPLETE="incompleteData";
	
	/**工作流操作ID 数组*/
	//public static final String[] WORKFLOW_ACTION_CODE = {"1101","1201","1202","1203","1102","1204","1301","1302","1205","1401","1103","1402","1403","1501","1502","1601","1602","1603","1701","1702","1801","1802","1803","1901","1902","1903","1904","2001"};
	public static final String[] WORKFLOW_ACTION_CODE = {"1101","1201","12011","1202","1203","1204","1301","1302","1205","1401","1103","1402","1403","1501","1502","1601","16011","1602","1603","1604","1701","1702","1801","1803","18041","1901","19011","19012","1902","19021","19022","19031","1804","1805","1806","1904","2001"};
	
	/**签字顺序*/
	public static final String[] SIGN_ACTION_CODE = {"1","2","3","4","5","6","7","8","9","10"};
	
	/** 上传方式为1时上传的地址前缀，和面可加自定义路径*/
	//public static final String DISK_PATH = "E:/upload/";
	public static final String DISK_PATH = "/opt/gcglgas/upload/";
	public static final String FIRST_DISK_PATH = "collect/";

	/**签字路径*/
	public static final String SIGN_DISK_PATH = "sign/";
	/**人员头像路径*/
	public static final String PHOTO_PATH = "photo/";
	/**工程图片路径*/
	public static final String PICTURE_PATH = "picture/";
	/**简图路径*/
	public static final String DIAGRAM_DISK_PATH = "diagram/";
	/**拍照路径*/
	public static final String MOBILE_DISK_PATH = "mobile/";
	/**电子签名路径*/
	public static final String SIGN_PCTURE_PATH = "signPicture/";
	
	
	/** 上传方式为1时表示上传到本地磁盘，不为1时上传到工程下*/
	public static final String UPLOAD_TYPE = "2";
	/** datatables错误key */
	public static final String DATATABLES_ERROR_KEY = "error";
	
	/**停工令编号的前缀*/
	public static final String SHUTDOWN_NO_CODE = "S-";
	/**整改编号前缀*/
	public static final String RECTIFY_NOTICE_NO_CODE = "ZG-";
	/**客户编码前缀*/
	public static final String CUSTOMER_CODE = "C";
	/**虚拟发票前缀*/
	public static final String FAPIAO_CODE = "xn";
	
	public static final String CALL_ERROR_CODE="1";//接口失败code
	public static final String SUCCESS_CODE="0";
	public static final String FAIL_CODE="-1";
	public static final String NO_OPERATE="-2";


	public static final String WORKDAYS="workdays";
	public static final String HOLIDAYS="holidays";
	public static final String SNSCACHEMAP="sNsCacheMap";//签字标准配置Map缓存
	public static final String HIDDEN_CONFIG="hidden";//隐藏数据配置


	//签订、修改、审核使用同一个详细页面，保存推送时使用下面标志区分
	public static final String PUBLIC_SIGN ="sign";//签订
	public static final String PUBLIC_MODIFY ="modify";//修改


	public static final String DA_APPLY_SIGN="daApply";//分部验收审核电子签名
	public static final String JA_APPLY_SIGN="jaApply";//联合验收审核电子签名
	public static final String PAYMENT_ADIT="paymentAudit";//付款审核电子签名



	//菜单ID
	public static final String PREINSPECTIONID_MENUID="110702";//预验收菜单ID
	public static final String TECHNOLOGYTELL_MENUID="120102";//会审交底菜单ID
	public static final String STARTREPORT_MENUID="120103";//开工报告菜单ID
	
	public static final String ZUNYI_XINPU_PROJNO_PREX="新";//遵义新蒲新区工程编码前缀

	public static final String AYDIT_INS="1000";//报验审核

	//增加报验审核升级时间
	public static final String UPGRADE_DATE = "2019-01-02";
	
	public static final String AUDIT_INS_LEVEL = "2";//报验单审核默认级别

	public static final String SEND_TASK_MESSAGE = "10021";//操作记录推送消息
	public static final String SEND_BUS_MESSAGE  = "10022";//业务沟通推送消息
	public static final String SEND_SIGN_MESSAGE = "10023";//签字推送消息

	public static final String SEND_OPEN_STATUS = "on";//签字推送消息


	public static final String SEND_TASK_OPEN 	   = "10101";//操作记录推送状态
	public static final String SEND_BUS_TASK_OPEN  = "10102";//业务沟通推送状态
	public static final String SEND_PLAN_SIGN_OPEN = "10103";//计划人员签字推送状态
	public static final String SEND_WELDER_OPEN    = "10104";//班组推送状态
	public static final String SEND_DESIGNER_OPEN  = "10105";//设计员推送状态
	public static final String SEND_JOINT_OPEN     = "10106";//联合验收推送状态
	public static final String SEND_GROUP_LEADER_OPEN    = "10107";//集团组长
	public static final String SEND_GROUP_MINISTER_OPEN  = "10108";//分公司部长
	public static final String SEND_ENGINEER_OPEN 		 = "10109";//燃气公司总工签字通知
	public static final String SEND_CU_ENGINEER_OPEN  	 = "10110";//施工单位总工签字通知

	public static final String CREATE_LOG_CODE			 ="1110";	//通知日志配置

	public static final String CHECK_STATUS = "1003";//报验单状态
	
	public static final String NC_HOST = "http://58.42.237.96:64";//NC测试接口地址
	//public static final String NC_HOST = "http://cqlcweb.zicp.vip:24510";//NC测试接口地址
	//public static final String NC_HOST = "http://222.85.141.19:63";//NC正式接口地址

	public static final String ENGINEERING_VISA = "1004";//签证审核默认
	public static final String ENGINEERING_VISA_CRONTAB = "1004-crontab";//签证一级审核时效性

	public static final String CONTRACT_AUDIT = "1005";//合同修改后是否需要审核

	public static final String INTELLIFENT_METER_AUDIT = "1006";//智能表合同修改后是否需要审核

    public static final String AGREEMENT_MODIFY_AUDIT = "1007";//补充协议修改后是否需要审核

    public static final String SUPPLEMENTAL_CONTRACT_AUDIT = "1008";//补充协议是否需要审核

    public static final String SUB_CONTRACT_MODIFY_AUDIT = "1010";//施工合同修改是否需要审核
    public static final String SPLIT_CODE = ",";//组合分隔符


	public static final String COMPLETION_DATA = "1009";//竣工资料打印

    public static final String CON_TASK = "110509";//施工任务单
    public static final String CON_SU_TASK = "110510";//监理任务单

	public static final String CONTRACT = "100";//安装合同

	public static final String SUB_CONTRACT="110604";//施工合同

	public static final String SU_BUDGET="110608";//施工预算

	public static final String SETTLEMENT="110804";//工程结算书

	public static final String TECHNOLOGY_TELL = "120102";//会审交底

	public static final String CU_ORGANIZATION = "120104";//施工组织

	public static final String STARTING_REPORT = "120103";//开工报告

	public static final String ONE_STOP_ACCEPT = "110724";//一站式打印

	public static final String SELF_CHECK = "110711"; //自检表

	public static final String JOINT_ACCEPT = "110704"; //联合验收单

	public static final String PRE_INSPECTION = "110722"; //预验收单

	public static final String CU_DAIRY = "120201"; //施工日志

	public static final String CHANGE_MENT = "110208"; //设计变更

	public static final String VSIA_RECORD = "120206"; //签证记录

	public static final String COMPLETE_REPORT = "120502"; //竣工报告

	public static final String DIVISIONAL_ACCEPT = "110713"; //分部验收单

	public static final String MEASUREMENT_RECORD = "130138"; //计量记录

	public static final String ELECTRODE_RECORD = "130121"; //焊条领用

	public static final String ELECTRODE_BACKING = "130122"; //焊条烘烤

	public static final String GROOVE_RECORD = "130103"; //管沟开挖

	public static final String PIPELINE_INSTALL = "130123"; //管道安装

	public static final String PIPE_WELDING = "130106"; //钢管焊接

	public static final String PEPIPEWELDING = "130107"; //PE管焊接

	public static final String PRESERVATIVE = "130105"; //防腐记录

	public static final String PRESERVATIVE_INPECT = "130126"; //防腐检查

	public static final String HIDDEN_WORKS = "130110"; //隐蔽工程

	public static final String PURGE = "130111"; //吹扫记录

	public static final String UNDERGROUND_INPECT = "130127"; //埋地检查

	public static final String TRENCH_BACKFILL = "130109"; //沟槽回填

	public static final String BALL_RECORD = "130130"; //通球记录


	public static final String INDOOR_POCKET_WATCH = "130132"; //户内挂表
	public static final String EQUIPMENT_INSTALL = "130133"; //设备安装

	public static final String HOTMELT_DOCKING = "130134"; //热熔对接

	public static final String ANODE_INSTALL = "130135"; //阳极安装

	public static final String WELD_LINE_INPECT = "130124"; //管道焊缝检查

	public static final String PE_WELD_LINE_INPECT = "130125"; //PE管焊缝检查

	public static final String PIPE_WELD_RECORD = "130137"; //焊口记录

	public static final String CLEAR_RECORD = "130136"; //清扫记录

	public static final String STRENTH_TEST = "120405"; //试压记录
	
	public static final String BUS_CON = "120213"; //业务沟通
	
	public static final String NDE_RECORD = "120214"; //无损检测

	public static final String FALL_BACK = "fallBack"; //回退配置


	public static final String VERSION_TIME = "2018-09-01";//版本时间
	public static final String PROJECT_TYPE = "0";//sign_notice_standard表默认工程类型
	public static final String CORP_ID = "0";//sign_notice_standard表默认分公司
	public static final String CONTRIBUTION_MODE = "0";//sign_notice_standard表默认出资方式
	public static final String START_REPORT_CPT_CORP_MODE = "1101";//开工报告默认版本公司ID-贵阳

	/**常用常量*/
	public static final String NOT="0";	//否
	public static final String YES="1";	//是
	
	/**工程施工中的辅助状态*/
	public static final String CONSTRUCTION_WORK="constructionWorks";	//会审交底
	public static final String CONSTRUCTION_ORGANIZATION="constructionOrganizations";	//施工组织
	public static final String WORK_REPORT="workReport";	//开工报告
	public static final String COMPLETE_WORK_REPORT="work_report";	//已完成开工报告
	public static final String SHUTDONN_RECORD="shutdownRecord";	//停工令
	public static final String DISPATCH_BUDGETER="dispatch";              //预算派工选择预算员规则常量
	public static final String PROJ_MODIFY_MENUID = "201904";//工程基本信息修改菜单ID



	/************************鸿巨接口****************/
	public static final String HONGJU_ID = "201607281027412810104983910912";//鸿巨ID

	public static final String HONGJU_HOST = "http://120.77.244.8:8077/intelligent-factory";//鸿巨接口测试IP地址

	//public static final String HONGJU_CONSTRUCTION_TASK = "/api/constructionTaskOrder/synchronizedConstructionTaskOrder";  	//鸿巨施工任务单URL
	//public static final String HONGJU_CONSTRUCTION_WORK_URL="/api/constructionWork/synchronizedConstructionWork" ;      	//会审交底URL
	//public static final String HONGJU_WORK_REPORT_URL="/api/workReport/synchronizedWorkReport";								//开工报告同步URL
	//public static final String HONGJU_PRE_SETTLEMENT_URL="/api/constructionBudget/synchronizedConstructionBudget" ;     	//施工预结算URL
	//public static final String HONGJU_CONSTRUCTION_CONTRACT_URL="/api/constructionContract/synchronizedConstructionContract";	//施工合同接口URL
	//public static final String HONGJU_SELF_CHECKING_URL="/api/check/synchronizedSelfInspectionCheckingRecord";					//工程自检接口URL
	//public static final String HONGJU_PREINSPECTION_URL="/api/check/synchronizedGetReadySelfInspectionRecord";     				//工程预验收接口URL
	//public static final String HONGJU_JOINT_ACCEPTANCE_URL="/api/check/synchronizedSelfInspectionRecord" ;             			//工程联合验收接口URL



}
