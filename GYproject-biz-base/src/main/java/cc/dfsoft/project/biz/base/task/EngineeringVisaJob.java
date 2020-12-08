package cc.dfsoft.project.biz.base.task;

import javax.annotation.Resource;

import cc.dfsoft.project.biz.base.common.service.DataFilerSetUpService;
import cc.dfsoft.project.biz.base.constructmanage.dao.BusinessCommunicationDao;
import cc.dfsoft.project.biz.base.constructmanage.entity.BusinessCommunication;
import cc.dfsoft.project.biz.base.mapper.entity.SendTaskLog;
import cc.dfsoft.project.biz.base.mapper.service.SendTaskLogService;
import cc.dfsoft.project.biz.base.project.dao.OperateRecordDao;
import cc.dfsoft.project.biz.base.project.dao.SignNoticeDao;
import cc.dfsoft.project.biz.base.project.entity.OperateRecord;
import cc.dfsoft.project.biz.base.project.entity.SignNotice;
import cc.dfsoft.project.biz.base.project.enums.OperateWorkFlowEnum;
import cc.dfsoft.project.biz.base.project.enums.ProjStatusEnum;
import cc.dfsoft.project.biz.base.project.service.SignNoticeService;
import cc.dfsoft.uexpress.biz.sys.dept.dao.StaffDao;
import cc.dfsoft.uexpress.biz.sys.dept.service.StaffService;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.util.IPUtil;
import cc.dfsoft.uexpress.common.util.JpushUtils;
import cc.dfsoft.uexpress.common.util.StringUtil;

import com.fr.stable.core.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import cc.dfsoft.project.biz.base.common.service.SysConfigService;
import cc.dfsoft.project.biz.base.constructmanage.service.EngineeringVisaService;
import cc.dfsoft.uexpress.common.util.LoggerUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 签证记录时效性 跑批程序
 * @author pengtt
 * @createTime 2016-08-26
 */
public class EngineeringVisaJob {
	
	private static final Logger logger = LoggerFactory.getLogger(EngineeringVisaJob.class);
	
	@Resource
	EngineeringVisaService engineeringVisaService;
	
	@Autowired
    SysConfigService sysConfigService;
	@Autowired
	DataFilerSetUpService dataFilerSetUpService;

	@Autowired
	OperateRecordDao operateRecordDao;

	@Autowired
	SendTaskLogService sendTaskLogService;

	@Autowired
	StaffDao staffDao;

	@Resource
	BusinessCommunicationDao businessCommunicationDao;

	@Resource
	SignNoticeDao signNoticeDao;

	@Resource
	StaffService staffService;

	@Resource
	SignNoticeService signNoticeService;
	//作废
	public void updateEngineeringVisaState(){
		try {
			engineeringVisaService.updateEngineeringVisaState();
		} catch (Exception e) {
			LoggerUtil.error(logger, e, "【签证记录时效性验证定时任务】发生异常");
		}finally{
			LoggerUtil.info(logger, "=====================  【签证记录时效性验证定时任务】执行完毕  =====================");
		}
	}
	//系统自动根据超期，自动进行签证审核一级-作废
	public void engineeringVisaCrontab(){
		try {
			engineeringVisaService.engineeringVisaCrontab();
		} catch (Exception e) {
			LoggerUtil.error(logger, e, "【签证记录时效性验证定时任务】发生异常");
		}finally{
			LoggerUtil.info(logger, "=====================  【签证记录时效性验证定时任务】执行完毕  =====================");
		}
	}
	//系统自动根据超期，将签证审核一级操作待办人追加集团预结算员，可手动审核签证一级
	public void engineeringVisaOverdueTreatment(){
		try {
			engineeringVisaService.engineeringVisaOverdueTreatment();
		} catch (Exception e) {
			LoggerUtil.error(logger, e, "【签证记录时效性验证定时任务】发生异常");
		}finally{
			LoggerUtil.info(logger, "=====================  【签证记录时效性验证定时任务】执行完毕  =====================");
		}
	}
	/**刷新配置表信息，每五分钟执行一次
	 * 
	 */
	public void initSys(){
        try {
			logger.info("============ 开始初始化参数表信息================");
			String src = sysConfigService.setSysConfigMap();
			logger.info("============ 初始化参数表信息完成结果为================"+src);
		}catch (Exception e){
        	e.printStackTrace();
		}
		try {
			logger.info("============ 开始初始化常量表信息================");
			String src2 = sysConfigService.setConstants();
			logger.info("============ 初始化常量表信息完成结果为================"+src2);
		}catch (Exception e){
			e.printStackTrace();
		}
		try {
			logger.info("============ 开始初始化过滤器表信息================");
			dataFilerSetUpService.setDataFiler();
			logger.info("============ 初始化过滤器表器信息完成================");
		}catch (Exception e){
			e.printStackTrace();
		}

	}

	/**
	 * 发极光推送消息
	 * @author fuliwei
	 * @date 2019/8/26
	 * @param
	 * @return
	*/
	public void jPushTask(){

		Object obj = Constants.getSysConfigByKey(Constants.SEND_TASK_OPEN);
		//判断是否开启消息推送设置 on开始 off关闭
		if(obj!=null && Constants.SEND_OPEN_STATUS.equals(String.valueOf(obj))){
			//开启操作记录通知
			sendOperateNotice();
		}


		Object objBus = Constants.getSysConfigByKey(Constants.SEND_BUS_TASK_OPEN);
		//判断是否开启消息推送设置 on开始 off关闭
		if(objBus!=null && Constants.SEND_OPEN_STATUS.equals(String.valueOf(objBus))){
			//开启业务沟通通知
			sendBusNotice();
		}


		Object objPlanSign = Constants.getSysConfigByKey(Constants.SEND_PLAN_SIGN_OPEN);
		//判断是否开启消息推送设置 on开始 off关闭
		if(objPlanSign!=null && Constants.SEND_OPEN_STATUS.equals(String.valueOf(objPlanSign))){
			//签字通知-计划中包含人员id，如施工员、监理
			sendPlanSignNotice();
		}


		Object objWelderSign = Constants.getSysConfigByKey(Constants.SEND_WELDER_OPEN);
		//判断是否开启消息推送设置 on开始 off关闭
		if(objWelderSign!=null && Constants.SEND_OPEN_STATUS.equals(String.valueOf(objWelderSign))){
			//班组签字通知
			sendWelderSignNotice();
		}

		Object objDesignSign = Constants.getSysConfigByKey(Constants.SEND_DESIGNER_OPEN);
		//判断是否开启消息推送设置 on开始 off关闭
		if(objDesignSign!=null && Constants.SEND_OPEN_STATUS.equals(String.valueOf(objDesignSign))){
			//设计员通知
			sendDesignSignNotice();
		}

		Object objJointSign = Constants.getSysConfigByKey(Constants.SEND_JOINT_OPEN);
		//判断是否开启消息推送设置 on开始 off关闭
		if(objJointSign!=null && Constants.SEND_OPEN_STATUS.equals(String.valueOf(objJointSign))){
			//查询联合验收相关通知-需判断是否有权限
			sendJointSignNotice();
		}



		Object objEngineerSign = Constants.getSysConfigByKey(Constants.SEND_ENGINEER_OPEN);
		//判断是否开启消息推送设置 on开始 off关闭
		if(objEngineerSign!=null && Constants.SEND_OPEN_STATUS.equals(String.valueOf(objEngineerSign))){
			//燃气公司总工程师
			sendEngineerSignNotice();
		}

		Object objCuEngineerSign = Constants.getSysConfigByKey(Constants.SEND_CU_ENGINEER_OPEN);
		//判断是否开启消息推送设置 on开始 off关闭
		if(objCuEngineerSign!=null && Constants.SEND_OPEN_STATUS.equals(String.valueOf(objCuEngineerSign))){
			//施工单位总工程师
			sendCuEngineerSignNotice();
		}


		Object objGroupLeaderSign = Constants.getSysConfigByKey(Constants.SEND_GROUP_LEADER_OPEN);
		//判断是否开启消息推送设置 on开始 off关闭
		if(objGroupLeaderSign!=null && Constants.SEND_OPEN_STATUS.equals(String.valueOf(objGroupLeaderSign))){
			//集团组长
			sendGroupLeaderSignNotice();
		}


		Object objMinisterSign = Constants.getSysConfigByKey(Constants.SEND_GROUP_MINISTER_OPEN);
		//判断是否开启消息推送设置 on开始 off关闭
		if(objMinisterSign!=null && Constants.SEND_OPEN_STATUS.equals(String.valueOf(objMinisterSign))){
			//分公司部长
			sendMinisterSignNotice();
		}
	}

	/*****************************************************操作记录发通知开始*****************************************/
	public void sendOperateNotice(){

		Object createLogStatus = Constants.getSysConfigByKey(Constants.CREATE_LOG_CODE);

		//发操作记录通知-工作通知
		String messages = "";

		//数据库是否配置格式
		Object sendtaskMessage = Constants.getSysConfigByKey(Constants.SEND_TASK_MESSAGE);

		//查操作记录的人
		List<Map<String, Object>> orr = operateRecordDao.queryOperateRecordTodo();

		List<OperateRecord> orList = new ArrayList<>();
		List<String> orIds = new ArrayList<>();

		//操作记录发待办

		if(orr!=null && orr.size()>0) {
			for (Map<String, Object> map : orr) {
				if(sendtaskMessage!=null){
					messages = sendtaskMessage.toString();
				}else{
					messages = "您好:【%u】,【%p】有【%s】的工作通知,请及时处理。";
				}

				String registrationid = String.valueOf(map.get("REGISTRATIONID"));//设备id
				String orId = String.valueOf(map.get("OR_ID"));

				messages = messages.replaceAll("%u", String.valueOf(map.get("STAFF_NAME"))).replaceAll("%p",String.valueOf(map.get("PROJ_NO")))
						.replaceAll("%s", String.valueOf(map.get("STEP_NAME")));
				String rul = JpushUtils.jpush(registrationid,messages);



				//是否生成日志
				if(createLogStatus!=null && Constants.SEND_OPEN_STATUS.equals(createLogStatus.toString())){
					this.createLog(registrationid,String.valueOf(map.get("STAFF_ID")),String.valueOf(map.get("STAFF_NAME")),
							rul,messages,"操作记录",String.valueOf(map.get("PROJ_NO")),orId);
				}

				if(!orIds.contains(orId)){
					//一条记录同时发送给多个人，只能更新一条记录
					orIds.add(orId);
					//不包含orId ，表示之前未处理，需处理通知
					OperateRecord or = operateRecordDao.get(orId);
					if(or !=null && !OperateWorkFlowEnum.HAVE_DONE.getValue().equals(or.getSendJpushStatus())){
						//更新操作记录为已发极光推送通知
						or.setSendJpushStatus(OperateWorkFlowEnum.HAVE_DONE.getValue());
						orList.add(or);
					}

				}
			}
		}


		if(orList != null && orList.size() > 0){
			//批量更新
			operateRecordDao.batchUpdateObjects(orList);
		}
	}
	/*****************************************************操作记录发通知结束*****************************************/


	/*****************************************************业务沟通发通知开始*****************************************/
	public void sendBusNotice(){
		Object createLogStatus = Constants.getSysConfigByKey(Constants.CREATE_LOG_CODE);

		//发操作记录通知-工作通知
		String busMessages = "";

		Object sendbusMessage = Constants.getSysConfigByKey(Constants.SEND_BUS_MESSAGE);


		//查操作记录的人
		List<Map<String, Object>> orrBus = businessCommunicationDao.queryBcNotice();
		List<BusinessCommunication> busList = new ArrayList<>();
		if(orrBus!=null && orrBus.size()>0) {
			for (Map<String, Object> map : orrBus) {
				String registrationid = String.valueOf(map.get("REGISTRATIONID"));//设备id
				if(sendbusMessage == ""){
					//消息默认形式
					//busMessages = "您好:【%u】,工程系统【%p】工程有待处理的业务沟通通知,请及时处理。";
					busMessages = "您好:【%u】,【%p】有业务沟通的任务,请及时处理。";
				}else{
					busMessages = sendbusMessage.toString();
				}

				String orId = String.valueOf(map.get("BC_ID"));
				busMessages = busMessages.replaceAll("%u", String.valueOf(map.get("STAFF_NAME"))).
						replaceAll("%p",String.valueOf(map.get("PROJ_NO")));
				String rul = JpushUtils.jpush(registrationid,busMessages);

				//是否生成日志
				if(createLogStatus!=null && Constants.SEND_OPEN_STATUS.equals(createLogStatus.toString())){
					this.createLog(registrationid,String.valueOf(map.get("STAFF_ID")),String.valueOf(map.get("STAFF_NAME")),
							rul,busMessages,"业务沟通",String.valueOf(map.get("PROJ_NO")),orId);
				}

				BusinessCommunication businessCommunication =businessCommunicationDao.get(orId);
				if(businessCommunication !=null && !OperateWorkFlowEnum.HAVE_DONE.getValue().equals(businessCommunication.getSendJpushStatus())){
					//更新沟通记录为已发极光推送通知
					businessCommunication.setSendJpushStatus(OperateWorkFlowEnum.HAVE_DONE.getValue());
					busList.add(businessCommunication);
				}
			}
		}

		if(busList != null && busList.size() > 0){
			//批量更新
			businessCommunicationDao.batchUpdateObjects(busList);
		}
	}
	/*****************************************************业务沟通发通知开始*****************************************/



	/*****************************************************签字发通知开始*****************************************/
	public void sendPlanSignNotice(){
		Object createLogStatus = Constants.getSysConfigByKey(Constants.CREATE_LOG_CODE);
		String planMessages = "";

		Object sendPlanMessage = Constants.getSysConfigByKey(Constants.SEND_SIGN_MESSAGE);

		//查操作记录的人
		List<Map<String, Object>> signMessageList = signNoticeDao.queryPlanNotice();
		List<SignNotice> signNoticeList = new ArrayList<>();
		if(signMessageList!=null && signMessageList.size()>0) {
			for (Map<String, Object> map : signMessageList) {
				String registrationid = String.valueOf(map.get("REGISTRATIONID"));//设备id
				if(sendPlanMessage == ""){
					//消息默认形式
					//planMessages = "您好:【%u】,工程系统【%p】工程有待处理的业务沟通通知,请及时处理。";
					planMessages = "您好:【%u】,【%p】有待签字的任务,请及时处理。";
				}else{
					planMessages = sendPlanMessage.toString();
				}

				String orId = String.valueOf(map.get("SIGN_NOTICE_ID"));
				planMessages = planMessages.replaceAll("%u", String.valueOf(map.get("STAFF_NAME"))).
						replaceAll("%p",String.valueOf(map.get("PROJ_NO")));
				String rul = JpushUtils.jpush(registrationid,planMessages);

				//是否生成日志
				if(createLogStatus!=null && Constants.SEND_OPEN_STATUS.equals(createLogStatus.toString())){
					this.createLog(registrationid,String.valueOf(map.get("STAFF_ID")),String.valueOf(map.get("STAFF_NAME")),
							rul,planMessages,"计划相关人员签字",String.valueOf(map.get("PROJ_NO")),orId);
				}

				SignNotice signNotice =signNoticeDao.get(orId);
				if(signNotice !=null && !OperateWorkFlowEnum.HAVE_DONE.getValue().equals(signNotice.getSendJpushStatus())){
					//更新沟通记录为已发极光推送通知
					signNotice.setSendJpushStatus(OperateWorkFlowEnum.HAVE_DONE.getValue());
					signNoticeList.add(signNotice);
				}
			}
		}

		if(signNoticeList != null && signNoticeList.size() > 0){
			//批量更新
			signNoticeDao.batchUpdateObjects(signNoticeList);
		}
	}
	/*****************************************************签字发通知结束*****************************************/

	/*****************************************************班组签字发通知开始*****************************************/
	public void sendWelderSignNotice(){
		Object createLogStatus = Constants.getSysConfigByKey(Constants.CREATE_LOG_CODE);
		String planMessages = "";
		Object sendPlanMessage = Constants.getSysConfigByKey(Constants.SEND_SIGN_MESSAGE);

		//查操作记录的人
		List<Map<String, Object>> signMessageList = signNoticeDao.queryWelderNotice();

		List<SignNotice> signNoticeList = new ArrayList<>();
		List<String> orIds = new ArrayList<>();
		if(signMessageList!=null && signMessageList.size()>0) {
			for (Map<String, Object> map : signMessageList) {
				String registrationid = String.valueOf(map.get("REGISTRATIONID"));//设备id
				if(sendPlanMessage == ""){
					//消息默认形式
					//planMessages = "您好:【%u】,工程系统【%p】工程有待处理的业务沟通通知,请及时处理。";
					planMessages = "您好:【%u】,【%p】有待签字的任务,请及时处理。";
				}else{
					planMessages = sendPlanMessage.toString();
				}

				String orId = String.valueOf(map.get("SIGN_NOTICE_ID"));
				planMessages = planMessages.replaceAll("%u", String.valueOf(map.get("STAFF_NAME"))).
						replaceAll("%p",String.valueOf(map.get("PROJ_NO")));
				String rul = JpushUtils.jpush(registrationid,planMessages);

				//班组生成日志
				if(createLogStatus!=null && Constants.SEND_OPEN_STATUS.equals(createLogStatus.toString())){
					this.createLog(registrationid,String.valueOf(map.get("STAFF_ID")),String.valueOf(map.get("STAFF_NAME")),
							rul,planMessages,"班组长签字",String.valueOf(map.get("PROJ_NO")),orId);
				}


				if(!orIds.contains(orId)){
					//一条记录同时发送给多个人，只能更新一条记录
					orIds.add(orId);
					//不包含orId ，表示之前未处理，需处理通知
					SignNotice signNotice =signNoticeDao.get(orId);
					if(signNotice !=null && !OperateWorkFlowEnum.HAVE_DONE.getValue().equals(signNotice.getSendJpushStatus())){
						signNotice.setSendJpushStatus(OperateWorkFlowEnum.HAVE_DONE.getValue());
						signNoticeList.add(signNotice);
					}
				}
			}
		}

		if(signNoticeList != null && signNoticeList.size() > 0){
			//批量更新
			signNoticeDao.batchUpdateObjects(signNoticeList);
		}
	}
	/*****************************************************班组签字发通知结束*****************************************/

	/*****************************************************联合人员签字发通知开始*****************************************/
	public void sendJointSignNotice(){
		Object createLogStatus = Constants.getSysConfigByKey(Constants.CREATE_LOG_CODE);
		String jointSignMessages = "";
		Object sendPlanMessage = Constants.getSysConfigByKey(Constants.SEND_SIGN_MESSAGE);

		//查操作记录的人

		List<SignNotice> signNoticeList = new ArrayList<>();
		List<String> orIds = new ArrayList<>();//用来判断是否记录该id

   		//客服服务中心
		//输配分公司
		//计量所
		//官网数据中心,75,
		//技装部,124,
		//质量安全组,56,
		//会计,125,
		//出纳,47,
		//副部长,15,
		//预结算组长,214,
		String [] posts=new String[]{",126,",",122,",",430,",",75,",",124,",",56,",",125,",",47,",",15,",",214,"};

		//循环给各部门发通知
        for(int i=0;i<posts.length;i++){
			List<Map<String, Object>> signMessageList = signNoticeService.queryJointNotice(posts[i]);
			if(signMessageList!=null && signMessageList.size()>0) {
				for (Map<String, Object> map : signMessageList) {
					String registrationid = String.valueOf(map.get("REGISTRATIONID"));//设备id
					if(sendPlanMessage == ""){
						//消息默认形式
						//jointSignMessages = "您好:【%u】,工程系统【%p】工程有待处理的业务沟通通知,请及时处理。";
						jointSignMessages = "您好:【%u】,【%p】有待签字的任务,请及时处理。";
					}else{
						jointSignMessages = sendPlanMessage.toString();
					}


					String orId = String.valueOf(map.get("SIGN_NOTICE_ID"));
					jointSignMessages = jointSignMessages.replaceAll("%u", String.valueOf(map.get("STAFF_NAME"))).
							replaceAll("%p",String.valueOf(map.get("PROJ_NO")));
					String rul = JpushUtils.jpush(registrationid,jointSignMessages);

					//班组生成日志
					if(createLogStatus!=null && Constants.SEND_OPEN_STATUS.equals(createLogStatus.toString())){
						this.createLog(registrationid,String.valueOf(map.get("STAFF_ID")),String.valueOf(map.get("STAFF_NAME")),
								rul,jointSignMessages,"联合验收/结算汇签签字",String.valueOf(map.get("PROJ_NO")),orId);
					}

					if(!orIds.contains(orId)){
						//一条记录同时发送给多个人，只能更新一条记录
						orIds.add(orId);
						//不包含orId ，表示之前未处理，需处理通知
						SignNotice signNotice =signNoticeDao.get(orId);
						if(signNotice !=null && !OperateWorkFlowEnum.HAVE_DONE.getValue().equals(signNotice.getSendJpushStatus())){
							signNotice.setSendJpushStatus(OperateWorkFlowEnum.HAVE_DONE.getValue());
							signNoticeList.add(signNotice);
						}
					}
				}
			}
		}

		if(signNoticeList != null && signNoticeList.size() > 0){
			//批量更新
			signNoticeDao.batchUpdateObjects(signNoticeList);
		}
	}
	/*****************************************************联合人员签字发通知结束*****************************************/

	/*****************************************************设计员签字发通知开始*****************************************/
	public void sendDesignSignNotice(){
		Object createLogStatus = Constants.getSysConfigByKey(Constants.CREATE_LOG_CODE);
		String designMessages = "";
		Object sendPlanMessage = Constants.getSysConfigByKey(Constants.SEND_SIGN_MESSAGE);

		//查操作记录的人
		List<Map<String, Object>> signMessageList = signNoticeDao.queryDesignerNotice();

		List<SignNotice> signNoticeList = new ArrayList<>();

		if(signMessageList!=null && signMessageList.size()>0) {
			for (Map<String, Object> map : signMessageList) {
				String registrationid = String.valueOf(map.get("REGISTRATIONID"));//设备id
				if(sendPlanMessage == ""){
					//消息默认形式
					//designMessages = "您好:【%u】,工程系统【%p】工程有待处理的业务沟通通知,请及时处理。";
					designMessages = "您好:【%u】,【%p】有待签字的任务,请及时处理。";
				}else{
					designMessages = sendPlanMessage.toString();
				}

				String orId = String.valueOf(map.get("SIGN_NOTICE_ID"));
				designMessages = designMessages.replaceAll("%u", String.valueOf(map.get("STAFF_NAME"))).
						replaceAll("%p",String.valueOf(map.get("PROJ_NO")));
				String rul = JpushUtils.jpush(registrationid,designMessages);

				//生成发送日志 后期系统稳定后删除该代码
				if(createLogStatus!=null && Constants.SEND_OPEN_STATUS.equals(createLogStatus.toString())){
					this.createLog(registrationid,String.valueOf(map.get("STAFF_ID")),String.valueOf(map.get("STAFF_NAME")),
							rul,designMessages,"设计员签字",String.valueOf(map.get("PROJ_NO")),orId);
				}


				//不包含orId ，表示之前未处理，需处理通知
				SignNotice signNotice =signNoticeDao.get(orId);
				if(signNotice !=null && !OperateWorkFlowEnum.HAVE_DONE.getValue().equals(signNotice.getSendJpushStatus())){
					signNotice.setSendJpushStatus(OperateWorkFlowEnum.HAVE_DONE.getValue());
					signNoticeList.add(signNotice);
				}
			}
		}

		if(signNoticeList != null && signNoticeList.size() > 0){
			//批量更新
			signNoticeDao.batchUpdateObjects(signNoticeList);
		}
	}
	/*****************************************************设计员签字发通知结束*****************************************/



	/*****************************************************集团组长发通知结束*****************************************/
	public void sendGroupLeaderSignNotice(){
		Object createLogStatus = Constants.getSysConfigByKey(Constants.CREATE_LOG_CODE);
		String designMessages = "";
		Object sendPlanMessage = Constants.getSysConfigByKey(Constants.SEND_SIGN_MESSAGE);

		//查操作记录的人
		List<Map<String, Object>> signMessageList = signNoticeDao.queryGroupLeaderNotice();

		List<SignNotice> signNoticeList = new ArrayList<>();

		if(signMessageList!=null && signMessageList.size()>0) {
			for (Map<String, Object> map : signMessageList) {
				String registrationid = String.valueOf(map.get("REGISTRATIONID"));//设备id
				if(sendPlanMessage == ""){
					//消息默认形式
					//designMessages = "您好:【%u】,工程系统【%p】工程有待处理的业务沟通通知,请及时处理。";
					designMessages = "您好:【%u】,【%p】有待签字的任务,请及时处理。";
				}else{
					designMessages = sendPlanMessage.toString();
				}

				String orId = String.valueOf(map.get("SIGN_NOTICE_ID"));
				designMessages = designMessages.replaceAll("%u", String.valueOf(map.get("STAFF_NAME"))).
						replaceAll("%p",String.valueOf(map.get("PROJ_NO")));
				String rul = JpushUtils.jpush(registrationid,designMessages);

				//生成发送日志 后期系统稳定后删除该代码
				if(createLogStatus!=null && Constants.SEND_OPEN_STATUS.equals(createLogStatus.toString())){
					this.createLog(registrationid,String.valueOf(map.get("STAFF_ID")),String.valueOf(map.get("STAFF_NAME")),
							rul,designMessages,"集团组长签字",String.valueOf(map.get("PROJ_NO")),orId);
				}


				//不包含orId ，表示之前未处理，需处理通知
				SignNotice signNotice =signNoticeDao.get(orId);
				if(signNotice !=null && !OperateWorkFlowEnum.HAVE_DONE.getValue().equals(signNotice.getSendJpushStatus())){
					signNotice.setSendJpushStatus(OperateWorkFlowEnum.HAVE_DONE.getValue());
					signNoticeList.add(signNotice);
				}
			}
		}

		if(signNoticeList != null && signNoticeList.size() > 0){
			//批量更新
			signNoticeDao.batchUpdateObjects(signNoticeList);
		}
	}
	/*****************************************************集团组长发通知结束*****************************************/


	/*****************************************************分公司部长发通知结束*****************************************/
	public void sendMinisterSignNotice(){
		Object createLogStatus = Constants.getSysConfigByKey(Constants.CREATE_LOG_CODE);
		String designMessages = "";
		Object sendPlanMessage = Constants.getSysConfigByKey(Constants.SEND_SIGN_MESSAGE);

		//查操作记录的人
		List<Map<String, Object>> signMessageList = signNoticeService.queryMinisterNotice();

		List<SignNotice> signNoticeList = new ArrayList<>();

		if(signMessageList!=null && signMessageList.size()>0) {
			for (Map<String, Object> map : signMessageList) {
				String registrationid = String.valueOf(map.get("REGISTRATIONID"));//设备id
				if(sendPlanMessage == ""){
					//消息默认形式
					//designMessages = "您好:【%u】,工程系统【%p】工程有待处理的业务沟通通知,请及时处理。";
					designMessages = "您好:【%u】,【%p】有待签字的任务,请及时处理。";
				}else{
					designMessages = sendPlanMessage.toString();
				}

				String orId = String.valueOf(map.get("SIGN_NOTICE_ID"));
				designMessages = designMessages.replaceAll("%u", String.valueOf(map.get("STAFF_NAME"))).
						replaceAll("%p",String.valueOf(map.get("PROJ_NO")));
				String rul = JpushUtils.jpush(registrationid,designMessages);

				//生成发送日志 后期系统稳定后删除该代码
				if(createLogStatus!=null && Constants.SEND_OPEN_STATUS.equals(createLogStatus.toString())){
					this.createLog(registrationid,String.valueOf(map.get("STAFF_ID")),String.valueOf(map.get("STAFF_NAME")),
							rul,designMessages,"分公司部长签字",String.valueOf(map.get("PROJ_NO")),orId);
				}


				SignNotice signNotice =signNoticeDao.get(orId);
				if(signNotice !=null && !OperateWorkFlowEnum.HAVE_DONE.getValue().equals(signNotice.getSendJpushStatus())){
					signNotice.setSendJpushStatus(OperateWorkFlowEnum.HAVE_DONE.getValue());
					signNoticeList.add(signNotice);
				}
			}
		}

		if(signNoticeList != null && signNoticeList.size() > 0){
			//批量更新
			signNoticeDao.batchUpdateObjects(signNoticeList);
		}
	}


	/*****************************************************分公司部长发通知结束*****************************************/



	/*****************************************************燃气公司总工程师发通知开始*****************************************/

	public void sendEngineerSignNotice(){
		Object createLogStatus = Constants.getSysConfigByKey(Constants.CREATE_LOG_CODE);
		String engineerMessages = "";
		Object sendPlanMessage = Constants.getSysConfigByKey(Constants.SEND_SIGN_MESSAGE);

		//查操作记录的人
		List<Map<String, Object>> signMessageList = signNoticeService.queryCeneralEngineerNotice();

		List<SignNotice> signNoticeList = new ArrayList<>();

		if(signMessageList!=null && signMessageList.size()>0) {
			for (Map<String, Object> map : signMessageList) {
				String registrationid = String.valueOf(map.get("REGISTRATIONID"));//设备id
				if(sendPlanMessage == ""){
					//消息默认形式
					//engineerMessages = "您好:【%u】,工程系统【%p】工程有待处理的业务沟通通知,请及时处理。";
					engineerMessages = "您好:【%u】,【%p】有待签字的任务,请及时处理。";
				}else{
					engineerMessages = sendPlanMessage.toString();
				}

				String orId = String.valueOf(map.get("SIGN_NOTICE_ID"));
				engineerMessages = engineerMessages.replaceAll("%u", String.valueOf(map.get("STAFF_NAME"))).
						replaceAll("%p",String.valueOf(map.get("PROJ_NO")));
				String rul = JpushUtils.jpush(registrationid,engineerMessages);

				//生成发送日志 后期系统稳定后删除该代码
				if(createLogStatus!=null && Constants.SEND_OPEN_STATUS.equals(createLogStatus.toString())){
					this.createLog(registrationid,String.valueOf(map.get("STAFF_ID")),String.valueOf(map.get("STAFF_NAME")),
							rul,engineerMessages,"燃气公司总工签字",String.valueOf(map.get("PROJ_NO")),orId);
				}


				//不包含orId ，表示之前未处理，需处理通知
				SignNotice signNotice =signNoticeDao.get(orId);
				if(signNotice !=null && !OperateWorkFlowEnum.HAVE_DONE.getValue().equals(signNotice.getSendJpushStatus())){
					signNotice.setSendJpushStatus(OperateWorkFlowEnum.HAVE_DONE.getValue());
					signNoticeList.add(signNotice);
				}
			}
		}

		if(signNoticeList != null && signNoticeList.size() > 0){
			//批量更新
			signNoticeDao.batchUpdateObjects(signNoticeList);
		}
	}

	/*****************************************************燃气公司总工程师发通知结束*****************************************/


	/*****************************************************施工单位总工程师发通知开始*****************************************/

	public void sendCuEngineerSignNotice(){
		Object createLogStatus = Constants.getSysConfigByKey(Constants.CREATE_LOG_CODE);
		String engineerMessages = "";
		Object sendPlanMessage = Constants.getSysConfigByKey(Constants.SEND_SIGN_MESSAGE);

		//查操作记录的人
		List<Map<String, Object>> signMessageList = signNoticeService.queryCuCeneralEngineerNotice();

		List<SignNotice> signNoticeList = new ArrayList<>();

		if(signMessageList!=null && signMessageList.size()>0) {
			for (Map<String, Object> map : signMessageList) {
				String registrationid = String.valueOf(map.get("REGISTRATIONID"));//设备id
				if(sendPlanMessage == ""){
					//消息默认形式
					//engineerMessages = "您好:【%u】,工程系统【%p】工程有待处理的业务沟通通知,请及时处理。";
					engineerMessages = "您好:【%u】,【%p】有待签字的任务,请及时处理。";
				}else{
					engineerMessages = sendPlanMessage.toString();
				}

				String orId = String.valueOf(map.get("SIGN_NOTICE_ID"));
				engineerMessages = engineerMessages.replaceAll("%u", String.valueOf(map.get("STAFF_NAME"))).
						replaceAll("%p",String.valueOf(map.get("PROJ_NO")));
				String rul = JpushUtils.jpush(registrationid,engineerMessages);

				//生成发送日志 后期系统稳定后删除该代码
				if(createLogStatus!=null && Constants.SEND_OPEN_STATUS.equals(createLogStatus.toString())){
					this.createLog(registrationid,String.valueOf(map.get("STAFF_ID")),String.valueOf(map.get("STAFF_NAME")),
							rul,engineerMessages,"施工单位总工签字",String.valueOf(map.get("PROJ_NO")),orId);
				}


				//不包含orId ，表示之前未处理，需处理通知
				SignNotice signNotice =signNoticeDao.get(orId);
				if(signNotice !=null && !OperateWorkFlowEnum.HAVE_DONE.getValue().equals(signNotice.getSendJpushStatus())){
					signNotice.setSendJpushStatus(OperateWorkFlowEnum.HAVE_DONE.getValue());
					signNoticeList.add(signNotice);
				}
			}
		}

		if(signNoticeList != null && signNoticeList.size() > 0){
			//批量更新
			signNoticeDao.batchUpdateObjects(signNoticeList);
		}
	}

	/*****************************************************施工单位总工程师发通知结束*****************************************/


	//生成发送日志 后期系统稳定后删除该代码
	public void createLog(String registrationid,String StaffId,String staffName,
						  String rul,String messages,String menuName,String projNo,String Id) {
		SendTaskLog sendTaskLog = new SendTaskLog();
		sendTaskLog.setId(UUID.randomUUID().toString().replaceAll("-",""));
		sendTaskLog.setUri(registrationid);//设备id
		sendTaskLog.setUser(StaffId);
		sendTaskLog.setUsername(staffName);
		if("success".equals(rul)){
			sendTaskLog.setResults("success");
		}else{
			sendTaskLog.setResults("error");
			sendTaskLog.setError(rul);
		}
		sendTaskLog.setMessage(messages);
		sendTaskLog.setCreatetime(new Date());
		sendTaskLog.setLocalhostip(IPUtil.getLinuxLocalIp());
		sendTaskLog.setMenuname(menuName);
		sendTaskLog.setDescription(String.valueOf(projNo)+",BUSINESS_ORDER_ID:"+Id);//业务单id
		sendTaskLogService.saveLog(sendTaskLog);
	}




}
