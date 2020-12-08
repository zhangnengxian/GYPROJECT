package cc.dfsoft.project.biz.base.complete.service.impl;

import cc.dfsoft.project.biz.base.baseinfo.service.WorkFlowService;
import cc.dfsoft.project.biz.base.complete.dao.JointAcceptanceDao;
import cc.dfsoft.project.biz.base.complete.dto.JointAcceptanceReq;
import cc.dfsoft.project.biz.base.complete.entity.JointAcceptance;
import cc.dfsoft.project.biz.base.complete.enums.AcceptanceTypeEnum;
import cc.dfsoft.project.biz.base.complete.enums.CompletionDataPrintEnum;
import cc.dfsoft.project.biz.base.complete.service.JointAcceptanceService;
import cc.dfsoft.project.biz.base.constructmanage.dao.CompleteReportDao;
import cc.dfsoft.project.biz.base.constructmanage.dto.CompletionDataPrintDto;
import cc.dfsoft.project.biz.base.constructmanage.entity.CompleteReport;
import cc.dfsoft.project.biz.base.constructmanage.enums.DataTypeEnum;
import cc.dfsoft.project.biz.base.contract.dao.ContractDao;
import cc.dfsoft.project.biz.base.contract.entity.Contract;
import cc.dfsoft.project.biz.base.contract.enums.ContractIsPrintEnum;
import cc.dfsoft.project.biz.base.maintain.service.AbandonedRecordService;
import cc.dfsoft.project.biz.base.messagesync.pojo.ResultMsg;
import cc.dfsoft.project.biz.base.messagesync.service.SynchronizedService;
import cc.dfsoft.project.biz.base.plan.dao.ConstructionPlanDao;
import cc.dfsoft.project.biz.base.plan.entity.ConstructionPlan;
import cc.dfsoft.project.biz.base.project.dao.ContributionModeDao;
import cc.dfsoft.project.biz.base.project.dao.OperateRecordDao;
import cc.dfsoft.project.biz.base.project.dao.ProjectDao;
import cc.dfsoft.project.biz.base.project.dao.SignNoticeDao;
import cc.dfsoft.project.biz.base.project.entity.*;
import cc.dfsoft.project.biz.base.project.enums.*;
import cc.dfsoft.project.biz.base.project.service.OperateRecordService;
import cc.dfsoft.project.biz.base.project.service.SignNoticeService;
import cc.dfsoft.project.biz.base.project.service.SignNoticeStandardService;
import cc.dfsoft.project.biz.base.project.service.SignatureService;
import cc.dfsoft.project.biz.base.subpackage.dao.SubContractDao;
import cc.dfsoft.project.biz.base.subpackage.entity.SubContract;
import cc.dfsoft.project.biz.ifs.WebServiceTypeEnum;
import cc.dfsoft.uexpress.biz.sys.auth.dao.MenuDao;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.biz.sys.dept.enums.PostTypeEnum;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.constant.DataFilerSetUpDto;
import cc.dfsoft.uexpress.common.enums.WorkFlowActionEnum;
import cc.dfsoft.uexpress.common.exception.ExpressException;
import cc.dfsoft.uexpress.common.util.Annotations;
import cc.dfsoft.uexpress.common.util.IDUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional(readOnly = true,propagation=Propagation.REQUIRED)
public class JointAcceptanceServiceImpl implements JointAcceptanceService {

	@Resource
	ContractDao contractDao;
	
	@Autowired
	MenuDao menuDao;
	
	@Autowired
	OperateRecordDao operateRecordDao;
	
	@Autowired
	SignNoticeDao signNoticeDao;
	
	@Autowired
	SignNoticeStandardService signNoticeStandardService;

	@Resource
	ContributionModeDao contributionModeDao;
	
	@Resource
	SubContractDao subContractDao;

	@Resource
	ProjectDao projectDao;
	
	@Resource
	JointAcceptanceDao jointAcceptanceDao;
	
	@Resource
	ConstructionPlanDao constructionPlanDao;
	
	/**业务操作记录服务接口*/
	@Resource
	OperateRecordService operateRecordService;
	
	@Resource
	SignatureService signatureService;
	
	/**工作流设置*/
	@Resource
	WorkFlowService workFlowService;
	
	/**签字通知服务*/
	@Resource
	SignNoticeService signNoticeService;
	
	@Resource
	CompleteReportDao completeReportDao;
	@Resource
	AbandonedRecordService abandonedRecordService;
	@Resource
	SynchronizedService synchronizedService;

	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public String saveJoint(JointAcceptance jointAcceptance) throws Exception {
		boolean flag = false;
		boolean jaIdIsBlank= StringUtils.isBlank(jointAcceptance.getJaId());



		Project project = projectDao.get(jointAcceptance.getProjId());
		String key=(project!=null?project.getCorpId():"")+"_110703";
		List<DataFilerSetUpDto> list = Constants.getDataFilterMapByKey(key);
		if (list==null||list.size()<0) {
			int count = jointAcceptanceDao.totalByProjId(jointAcceptance.getProjId());
			boolean isExist = count > 0 ? true : false;
			if (jaIdIsBlank && isExist){//只允许保存一条数据
				return "already";
			}
		}



		if(jaIdIsBlank){
			flag = true;
			jointAcceptance.setJaId(IDUtil.getUniqueId(Constants.MODULE_CODE_COMPLETE));
			//激活或者生成第一签字顺序的签字通知
			signNoticeService.createFirstNotice(jointAcceptance.getJaId(),SignDataTypeEnum.JOINT_ACCEPTANCE,project);
		}

		jointAcceptance.setIsPrint(ContractIsPrintEnum.HAVE_NOT_PRINT.getValue());	   //未打印
		jointAcceptance.setAcceptanceType(AcceptanceTypeEnum.JOINT_ACCEPTANCE.getValue());//联合验收
		if("2".equals(jointAcceptance.getMarketDevDevice())){
			jointAcceptance.setJaClum("整改合格");
		}
		if("0".equals(jointAcceptance.getMarketDevDevice())){
			jointAcceptance.setJaClum("整改");
		}
		//若质安组未签字
		if("1".equals(jointAcceptance.getMarketDevDevice()) && StringUtils.isBlank(jointAcceptance.getMarketDevSign())){ //若验收状态是合格且签字为空则状态为验收中
			jointAcceptance.setMarketDevDevice("3");
			jointAcceptance.setJaClum("验收中");
		}else if("1".equals(jointAcceptance.getMarketDevDevice()) && StringUtils.isNotBlank(jointAcceptance.getMarketDevSign())){ //以验收合格
			jointAcceptance.setMarketDevDevice("1");
			jointAcceptance.setJaClum("合格");
		}
		
		//技装部 管网
		if(StringUtils.isNotBlank(jointAcceptance.getFieldPrincipalSign())
				&& StringUtils.isNotBlank(jointAcceptance.getPdUnitSign())
				&& StringUtils.isNotBlank(jointAcceptance.getSuNameSign())
				&& StringUtils.isNotBlank(jointAcceptance.getMarketDevSign())
				&& StringUtils.isNotBlank(jointAcceptance.getCustCenterSign())
				&& StringUtils.isNotBlank(jointAcceptance.getTransCompanySign())
				&& StringUtils.isNotBlank(jointAcceptance.getMeasurementSign())
				&& StringUtils.isNotBlank(jointAcceptance.getInformCenterSign())
				&& StringUtils.isNotBlank(jointAcceptance.getTechEquipmentSign())){
				//所有通知置为已签字
				signNoticeService.updateAllSignState(SignDataTypeEnum.JOINT_ACCEPTANCE.getValue(),jointAcceptance.getJaId());
		 }
		 //  保存
			  jointAcceptanceDao.saveOrUpdate(jointAcceptance);
		
		List<Signature> signs=jointAcceptance.getSign();
		if(signs!=null && signs.size()>0){
			for(Signature sign:signs){
				sign.setDataType(SignDataTypeEnum.JOINT_ACCEPTANCE.getValue());
			}
			jointAcceptance.setSign(signs);
		}
		
		//保存签字
		signatureService.saveOrUpdateSign("menuId+menuNane+4",jointAcceptance.getSign(), jointAcceptance.getProjId(), jointAcceptance.getJaId(), jointAcceptance.getClass().getName(), flag);

		//更新工程信息
		Project pro=projectDao.get(jointAcceptance.getProjId());         //通过工程id查找Project
////		//生成工程状态
////		String statusId=WorkFlowUtil.workFlowStatus(WorkFlowActionEnum.UNION_PRE_INSPECTION.getActionCode());
//		//取得登录人信息
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		pro.setAcceptanceDate(jointAcceptance.getJaDate());
		pro.setAcceptanceResult(jointAcceptance.getJaClum());
		pro.setAcceptanceDirector(loginInfo.getStaffName());
		if("1".equals(jointAcceptance.getMarketDevDevice()) && StringUtils.isBlank(jointAcceptance.getMarketDevSign())){ //若验收状态是合格且签字为空则状态为验收中
			pro.setProjChanges("3");//正在验收
		}else{
			pro.setProjChanges(jointAcceptance.getMarketDevDevice());//质安组验收结果
		}
		// 若输配中心不参与验收，则删除签字通知
		if ("0".equals(jointAcceptance.getWhetherAcceptance())) {
			signNoticeService.updateSignNotice(PostTypeEnum.PROCESS_TECHNICIAN.getValue(), SignDataTypeEnum.JOINT_ACCEPTANCE.getValue(), jointAcceptance.getJaId(), jointAcceptance.getProjId());
		}
		
		projectDao.saveOrUpdate(pro);
		return Constants.OPERATE_RESULT_SUCCESS;

	}
	
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void entJoint(JointAcceptance jointAcceptance) throws Exception{
		boolean flag = false;
		if(StringUtils.isBlank(jointAcceptance.getJaId())){
			flag = true;
		}
		//更新工程信息
		Project pro=projectDao.get(jointAcceptance.getProjId());         //通过工程id查找Project
		//生成工程状态
		String statusId=workFlowService.queryProjStatusId(pro.getCorpId(), pro.getProjectType(),pro.getContributionMode(), WorkFlowActionEnum.UNION_PRE_INSPECTION.getActionCode(), true);
		//取得登录人信息
		pro.setAcceptanceDate(jointAcceptance.getJaDate());	//验收日期
		pro.setProjStatusId(statusId); 					    //设置工程状态
		pro.setIsGas(IsGasEnum.NOT_GAS.getValue());         //未带气


		//形成操作记录
		String orId = IDUtil.getUniqueId(Constants.MODULE_CODE_COMPLETE);
		String todoer=operateRecordService.createOperateRecord(orId, jointAcceptance.getProjId(), pro.getProjNo(), StepEnum.UNION_PRE_INSPECTION.getValue(), StepEnum.UNION_PRE_INSPECTION.getMessage(), "");
		
		pro.setToDoer(todoer);//待办人
		projectDao.saveOrUpdate(pro);

		//保存签字
		signatureService.saveOrUpdateSign("menuId+menuNane+4",jointAcceptance.getSign(), jointAcceptance.getProjId(), jointAcceptance.getJaId(), jointAcceptance.getClass().getName(), flag);
		
		//处理联合验收单未办签字通知
		//todo:极光推送通知如何处理
		signNoticeService.signNoticeSetInvalid(jointAcceptance.getJaId(), jointAcceptance.getProjId(), SignDataTypeEnum.JOINT_ACCEPTANCE.getValue(), null, SignStateEnum.ALREADY_SIGN.getValue());


		//推送时调用鸿巨接口（联合验收信息新增/修改）返回信息
		ResultMsg resultMsg = synchronizedService.callSynchronizedWorkReport(jointAcceptance.getProjId(), WebServiceTypeEnum.JOINT_ACCEPTANCE.getValue());
		if (resultMsg!=null && resultMsg.getCode()!=0){
			throw new ExpressException(resultMsg.getCode()+"",resultMsg.getMsg());
		}
	}

	
	@Override
	public JointAcceptance jointDetail(String projId) {
		JointAcceptance jointAcceptance = new JointAcceptance();
		Project project = projectDao.get(projId);
		List<JointAcceptance> jointAcceptances = jointAcceptanceDao.findById(projId);
		if(jointAcceptances!=null && jointAcceptances.size()!=0){
			jointAcceptance = jointAcceptances.get(0);
			jointAcceptance.setCompletedDate(project.getCompletedDate());
		}else{
			jointAcceptance.setProjId(projId);
			jointAcceptance.setProjNo(project.getProjNo());
			jointAcceptance.setProjName(project.getProjName());
			jointAcceptance.setCustName(project.getCustName());
			jointAcceptance.setProjAddr(project.getProjAddr());
			jointAcceptance.setProjScaleDes(project.getProjScaleDes());
			
			List<ConstructionPlan> constructionPlans = constructionPlanDao.findByProjNo(project.getProjNo());
			if(constructionPlans!=null && constructionPlans.size()!=0){
				ConstructionPlan constructionPlan = constructionPlans.get(0);
				jointAcceptance.setSuName(constructionPlan.getSuName());// 监理单位
			}
			Contract contract = contractDao.viewContractByprojId(projId);
			if(contract!=null){
				jointAcceptance.setConNo(contract.getConNo());//合同编号
			}
		}
		return jointAcceptance;
	}

	/**
	 * 联合验收条件查询
	 */
	@Override
	public Map<String, Object> queryJointAcceptance(JointAcceptanceReq jointAcceptanceReq)throws ParseException {
		Map<String, Object> map = jointAcceptanceDao.queryJointAcceptance(jointAcceptanceReq);
		List<JointAcceptance> jointAcceptances = (List<JointAcceptance>) map.get("data");

		if(null!=jointAcceptances&&jointAcceptances.size()>0){
			for(JointAcceptance ja :jointAcceptances){
					Project project=projectDao.get(ja.getProjId());
					if(project!=null){
						ja.setProjectType(project.getProjectType());
						ja.setProjectTypeDes(project.getProjectTypeDes());
						ja.setCorpId(project.getCorpId());    
					}
				
				//分包合同
				SubContract sc = subContractDao.findSubContractByprojId(ja.getProjId());
				//竣工报告的实际竣工日期
				List<CompleteReport> completeReports = completeReportDao.findByProjId(ja.getProjId());
				if(sc!=null){
					ja.setScNo(sc.getScNo());//分合同编号
					ja.setCuName(sc.getCuName());//施工单位
				}
				//实际竣工日期
				if(completeReports!=null && completeReports.size()>0 && completeReports.get(0).getRealEndDate()!=null){
					ja.setCompletedDate(completeReports.get(0).getRealEndDate());//实际竣工日期
					ja.setStartDate(completeReports.get(0).getRealStartDate());  //实际开工日期
					if(StringUtils.isNotBlank(completeReports.get(0).getScPlannedEndDate())){
						ja.setScPlannedEndDate(StringUtils.substring(completeReports.get(0).getScPlannedEndDate(),0,10));  //计划竣工日期
					}
					ja.setScPlannedStartDate(completeReports.get(0).getScPlannedStartDate());  //计划开工日期
					
				}
			}
		}

		return map;
	}

	@Override
	public JointAcceptance jointDetailByType(String projId, String dataType) throws ParseException {
		JointAcceptance jointAcceptance = new JointAcceptance();
		Project project = projectDao.get(projId);
		List<JointAcceptance> jointAcceptances = jointAcceptanceDao.findByType(projId,dataType);
	    
		if(jointAcceptances!=null && jointAcceptances.size()!=0){
			jointAcceptance = jointAcceptances.get(0);
			jointAcceptance.setCompletedDate(project.getCompletedDate());   //设置实际竣工日期
			jointAcceptance.setStartDate(project.getStartDate());    //设置实际开工日期
			jointAcceptance.setProjectTypeDes(project.getProjectTypeDes());   //工程类型描述
			jointAcceptance.setContributionModeDes(contributionModeDao.get(project.getContributionMode()).getContributionDes());  //取得出资方式
			if(DataTypeEnum.CRAFT_WORK.getValue().equals(dataType)){
				List<JointAcceptance> ja = jointAcceptanceDao.findByType(projId,DataTypeEnum.ALARM_PROJ.getValue());
				if(ja!=null && ja.size()!=0){
					jointAcceptance.setAlarmProj("1");
				}else{
					jointAcceptance.setAlarmProj("0");
				}
			}
		}else{
			jointAcceptance.setProjId(projId);
			jointAcceptance.setProjNo(project.getProjNo());
			jointAcceptance.setProjName(project.getProjName());
			jointAcceptance.setCustName(project.getCustName());
			jointAcceptance.setProjAddr(project.getProjAddr());
			jointAcceptance.setProjScaleDes(project.getProjScaleDes());
			jointAcceptance.setProjectTypeDes(project.getProjectTypeDes());   //工程类型描述
			jointAcceptance.setContributionModeDes(contributionModeDao.get(project.getContributionMode()).getContributionDes());  //取得出资方式
			Contract contract = contractDao.viewContractByprojId(projId);
			if(contract!=null){
				jointAcceptance.setConNo(contract.getConNo());//合同编号
			}
		}
		List<ConstructionPlan> constructionPlans = constructionPlanDao.findByProjNo(project.getProjNo());
		if(constructionPlans!=null && constructionPlans.size()!=0){
			ConstructionPlan constructionPlan = constructionPlans.get(0);
			jointAcceptance.setSuName(constructionPlan.getSuName());// 监理单位
			jointAcceptance.setCuName(constructionPlan.getCuName());//施工单位
		}
		SubContract sc = subContractDao.findSubContractByprojId(projId);
		CompleteReport completeReport = completeReportDao.findByProjId(projId, "1");
		if(sc!=null){
			jointAcceptance.setScNo(sc.getScNo());//分合同编号
			jointAcceptance.setCuName(sc.getCuName());//施工单位
			//jointAcceptance.setCompletedDate(sc.getScPlannedEndDate());//竣工日期
		}
		if(completeReport!=null){
			jointAcceptance.setCompletedDate(completeReport.getRealEndDate());//实际竣工日期
			jointAcceptance.setStartDate(completeReport.getRealStartDate());  //实际开工日期
			if(StringUtils.isNotBlank(completeReport.getScPlannedEndDate())){
				jointAcceptance.setScPlannedEndDate(StringUtils.substring(completeReport.getScPlannedEndDate(),0,10));  //计划竣工日期
			}
			jointAcceptance.setScPlannedStartDate(completeReport.getScPlannedStartDate());  //计划开工日期
		}
		return jointAcceptance;
	}

	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void signJointAcceptancePrint(String jaId) {
		JointAcceptance jointAcceptance=jointAcceptanceDao.get(jaId);
		if(jointAcceptance!=null){
			//标记已打印
			jointAcceptance.setIsPrint(ContractIsPrintEnum.ALREADY_PRINT.getValue());
			jointAcceptanceDao.update(jointAcceptance);
		}
	}
	
	/**
	 * 推送一站式验收	
	 * @author fuliwei
	 * @createTime 2017年9月5日
	 * @param 
	 * @return
	 */
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void entOneStopAcceptance(JointAcceptance jointAcceptance) {
		//更新工程信息
		Project pro=projectDao.get(jointAcceptance.getProjId());         //通过工程id查找Project
		//生成工程状态
		String statusId=workFlowService.queryProjStatusId(pro.getCorpId(), pro.getProjectType(),pro.getContributionMode(), WorkFlowActionEnum.ONE_STOP_ACCEPTANCE.getActionCode(), true);
		pro.setProjStatusId(statusId); 								//设置工程状态
		

		//形成操作记录
		String orId = IDUtil.getUniqueId(Constants.MODULE_CODE_COMPLETE);
		String todoer=operateRecordService.createOperateRecord(orId, jointAcceptance.getProjId(), pro.getProjNo(), StepEnum.ONE_STOP_ACCEPTANCE.getValue(), StepEnum.ONE_STOP_ACCEPTANCE.getMessage(), "");
		pro.setToDoer(todoer);//待办人
		projectDao.saveOrUpdate(pro);
	}
	/**
	 * 保存一站式验收
	 * @author fuliwei
	 * @createTime 2017年9月5日
	 * @param 
	 * @return
	 */
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void saveOneStopAcceptance(JointAcceptance jointAcceptance) throws Exception {
		boolean flag = false;
		if(StringUtils.isBlank(jointAcceptance.getJaId())){
			flag = true;
			jointAcceptance.setJaId(IDUtil.getUniqueId(Constants.MODULE_CODE_COMPLETE));
		}
		jointAcceptance.setIsPrint(ContractIsPrintEnum.HAVE_NOT_PRINT.getValue());	   //未打印
		jointAcceptance.setAcceptanceType(AcceptanceTypeEnum.ONE_STOP_ACCEPTANCE.getValue());//一站式验收
		//保存
		jointAcceptanceDao.saveOrUpdate(jointAcceptance);
		//保存签字
		signatureService.saveOrUpdateSign("menuId+menuNane+5",jointAcceptance.getSign(), jointAcceptance.getProjId(), jointAcceptance.getJaId(), jointAcceptance.getClass().getName(), flag);

		//更新工程信息
		Project pro=projectDao.get(jointAcceptance.getProjId());         //通过工程id查找Project
//		//取得登录人信息
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		pro.setAcceptanceDate(jointAcceptance.getJaDate());
		pro.setAcceptanceResult(jointAcceptance.getJaClum());
		pro.setAcceptanceDirector(loginInfo.getStaffName());
		//pro.setCompletedDate(jointAcceptance.getCompletedDate());                        //竣工日期
		projectDao.saveOrUpdate(pro);
		
	}

	/**
	 * 返回一站式验收单打印报表数据
	 */
	@Override
	public String findPrintDataByProjId(String projId,String acceptType,String type) {
		String result ="";
		//根据工程ID查询一站式验收单
		List<JointAcceptance> wrList= jointAcceptanceDao.findByProjIdAndType(projId, acceptType);
		Project project = projectDao.get(projId);  //根据工程ID取得实体
		JointAcceptance jointAccept =null;
		if(wrList!=null && wrList.size()>0){
			jointAccept =  new JointAcceptance();
			jointAccept = wrList.get(0);
		}
		String arrayStr ;
		if(acceptType.equals(AcceptanceTypeEnum.ONE_STOP_ACCEPTANCE.getValue())){
			arrayStr = CompletionDataPrintEnum.ONE_STOP_ACCEPT.getCptUrl();
		}else{
			arrayStr = CompletionDataPrintEnum.JOINT_ACCEPT.getCptUrl();
		}
		//2、使用JSONArray
		net.sf.json.JSONArray jsonArray=net.sf.json.JSONArray.fromObject(arrayStr);
		if(jsonArray!=null&&jsonArray.size()>0 && jointAccept!=null && project !=null){
			net.sf.json.JSONObject jsonObject=net.sf.json.JSONObject.fromObject(jsonArray.get(0));
			CompletionDataPrintDto dto = (CompletionDataPrintDto)net.sf.json.JSONObject.toBean(jsonObject, CompletionDataPrintDto.class);
			 String[] menuIdAndMark = type.split("-"); // 分解施工任务单标识符和菜单ID,从而取得菜单ID
			 String menuId = menuIdAndMark[menuIdAndMark.length-1]; // 获取菜单id
			 String key = project.getCorpId()+"_"+project.getProjectType()+"_"+menuId;
			 Object reportVersion = Constants.getSysConfigByKey(key);
			   if(reportVersion !=null){
				   //记录特定字符索引  
				   int beginIndex = dto.getReportlet().indexOf("/"); 
				   int endIndex = dto.getReportlet().lastIndexOf("/");
			       String reportlet = dto.getReportlet().substring(beginIndex, endIndex+1);  //截取字符'/'之间的字符串
				   dto.setReportlet(reportlet+reportVersion.toString());   //若reportVersion不为空则重新设置报表路径
			   }
			result = "{reportlet:'"+dto.getReportlet()+"',projName:'"+jointAccept.getProjName()+"',projId:'"+jointAccept.getProjId()+"',jaId:'"+jointAccept.getJaId()
				   +"',imgUrl:'" + Constants.DISK_PATH+Constants.SIGN_DISK_PATH + "'}";
			return result;
		}
		return null;
	}

	@Override
	public JointAcceptance jointView(String jaId) {
		JointAcceptance jointAcceptance = jointAcceptanceDao.get(jaId);
		SubContract subContract = subContractDao.findByProjId(jointAcceptance.getProjId());
		ConstructionPlan constructionPlan = constructionPlanDao.findByProjId(jointAcceptance.getProjId());
		Project project = projectDao.get(jointAcceptance.getProjId());
		return jointAcceptance;
	}
	
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void saveSignNotice(String cwId) {
		String signState = SignStateEnum.ALREADY_SIGN.getValue();
		if(StringUtils.isNotBlank(cwId)){
			JointAcceptance da=jointAcceptanceDao.get(cwId);
			if(da!=null){
				if(StringUtils.isNotBlank(da.getMarketDevSign())){
						//组织人通知置为无效
						signNoticeService.saveThisSignNotice(SignPostEnum.TECHNICIAN.getValue(), SignDataTypeEnum.JOINT_ACCEPTANCE.getValue(),
								da.getJaId(), da.getProjId(),signState);
				}
			}
			
		}
		
	}

	/**
     * 根据jaId查标记记录为已打印
     * @author wanghuijun
     * @createTime 2018年9月25日
     * @param jaId
     * @return
     */
	@Override
	@Transactional(readOnly = false,propagation = Propagation.REQUIRED)
	public void signDivisionalAcceptancePrint(String jaId) throws Exception {
		JointAcceptance jointAcceptance = jointAcceptanceDao.get(jaId);  //根据jaId取得实体
		if(jointAcceptance != null ){
			jointAcceptance.setIsPrint(ContractIsPrintEnum.ALREADY_PRINT.getValue());
			jointAcceptanceDao.update(jointAcceptance);  //更新实体
		}
	}

	
	/**20190807
	 * 王会军
	 * 签字完成时，将工作通知置为无效，另生成
	 * 通知，通知相关人员签字已完成
	 * @param businId
	 * @param projId
	 * @param dataType
	 * @param menuId
	 * @param corpId
	 * @param projectType
	 * @param contributionMode
	 * @throws Exception
	 */
	@Override
	@Transactional (readOnly = false, propagation = Propagation.REQUIRED)
	public void finshSignCreateNotice(String projId, String businId, String menuId, String stepId, String dataType) throws Exception {
		// TODO Auto-generated method stub
		Project project = projectDao.get(projId); // 取得记录
		// 查询签字通知标准
		List<SignNoticeStandard> listSignNoticeStandard = signNoticeStandardService.querySignNoticeStandard(null, dataType, project.getCorpId(), project.getProjectType(), project.getContributionMode());
		SignNotice notice = new SignNotice();
		boolean flag = false; // 标识符，判断是否执行程序
		if (listSignNoticeStandard.size() > 0 && listSignNoticeStandard != null) {
			flag = true;
			for (SignNoticeStandard signNoticeStandard : listSignNoticeStandard) {
				notice = signNoticeDao.queryByBusiIdAndPost(businId, signNoticeStandard.getPost(), signNoticeStandard.getDataType());
				if (notice != null && "1".equals(notice.getStatus())) { // 若是待签状态
					flag = false;
					break;
				}
			}
		}
		
		//生成新的通知
		if (flag) {
			OperateRecord operateRecord = operateRecordDao.findByGread(project.getProjId(), project.getCorpId(), project.getProjectType(),
					project.getContributionMode(), stepId, null, OperateWorkFlowEnum.WAIT_DONE.getValue());
			if (operateRecord != null) {
				if (operateRecord.getStepName().contains("(")) {
					operateRecord.setStepName(StringUtils.substringBefore(operateRecord.getStepName(), "(") +"(签字已完成，请及时推送。)");
				} else {
					operateRecord.setStepName(operateRecord.getStepName()+"(签字已完成，请及时推送。)");
				}
				operateRecordDao.saveOrUpdate(operateRecord);
			}
		}
		
	}

	@Override
	public boolean rollBackContainsJointAcceptance(String projId, String fallbackReason) {
		List<JointAcceptance> jsList = jointAcceptanceDao.findById(projId);
		if (jsList==null || jsList.size()<1 ) return true;

		Map<String,Object> criteriaMap=new HashMap<>();
		String t_projId = Annotations.getFieldGetMethodColumnAnNameVal(JointAcceptance.class, "projId");
		criteriaMap.put(t_projId,projId);
		String stepId=StepEnum.UNION_PRE_INSPECTION.getValue();
		String tableName = Annotations.getClassTableAnNameVal(JointAcceptance.class);
		for (JointAcceptance js:jsList) {
			abandonedRecordService.delBackupsThisTableRecordAndSignature(tableName,criteriaMap,js.getJaId(),fallbackReason,stepId);
		}

		return false;
	}

	@Override
	public JointAcceptance findByProjId(String projId) {
		List<JointAcceptance> jointAcceptanceList = jointAcceptanceDao.findById(projId);
		if (jointAcceptanceList!=null && jointAcceptanceList.size()>0){
			return jointAcceptanceList.get(0);
		}
		return null;
	}

}
