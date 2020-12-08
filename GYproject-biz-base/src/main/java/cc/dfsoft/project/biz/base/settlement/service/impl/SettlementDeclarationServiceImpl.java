package cc.dfsoft.project.biz.base.settlement.service.impl;

import cc.dfsoft.project.biz.base.baseinfo.dao.DocTypeDao;
import cc.dfsoft.project.biz.base.baseinfo.dao.SystemSetDao;
import cc.dfsoft.project.biz.base.baseinfo.dao.TimeLimitDao;
import cc.dfsoft.project.biz.base.baseinfo.dto.WorkDayDto;
import cc.dfsoft.project.biz.base.baseinfo.entity.DocType;
import cc.dfsoft.project.biz.base.baseinfo.entity.TimeLimit;
import cc.dfsoft.project.biz.base.baseinfo.enums.TimeLimitTypeEnum;
import cc.dfsoft.project.biz.base.baseinfo.service.DocTypeService;
import cc.dfsoft.project.biz.base.baseinfo.service.FestivalService;
import cc.dfsoft.project.biz.base.baseinfo.service.WorkFlowService;
import cc.dfsoft.project.biz.base.budget.dao.BudgetDao;
import cc.dfsoft.project.biz.base.budget.entity.Budget;
import cc.dfsoft.project.biz.base.common.entity.SystemSet;
import cc.dfsoft.project.biz.base.complete.enums.CompletionDataPrintEnum;
import cc.dfsoft.project.biz.base.constructmanage.dto.CompletionDataPrintDto;
import cc.dfsoft.project.biz.base.contract.enums.ContractIsPrintEnum;
import cc.dfsoft.project.biz.base.design.dao.DesignInfoDao;
import cc.dfsoft.project.biz.base.design.dto.DesignDispatchDto;
import cc.dfsoft.project.biz.base.design.entity.DesignInfo;
import cc.dfsoft.project.biz.base.maintain.service.AbandonedRecordService;
import cc.dfsoft.project.biz.base.plan.dao.ConstructionPlanDao;
import cc.dfsoft.project.biz.base.plan.entity.ConstructionPlan;
import cc.dfsoft.project.biz.base.project.dao.ApplyDelayDao;
import cc.dfsoft.project.biz.base.project.dao.ManageRecordDao;
import cc.dfsoft.project.biz.base.project.dao.OperateRecordDao;
import cc.dfsoft.project.biz.base.project.dao.ProjectDao;
import cc.dfsoft.project.biz.base.project.dto.ManageRecordQueryReq;
import cc.dfsoft.project.biz.base.project.dto.ProjectQueryReq;
import cc.dfsoft.project.biz.base.project.entity.ContributionMode;
import cc.dfsoft.project.biz.base.project.entity.ManageRecord;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.project.enums.MrResultEnum;
import cc.dfsoft.project.biz.base.project.enums.StepEnum;
import cc.dfsoft.project.biz.base.project.service.OperateRecordService;
import cc.dfsoft.project.biz.base.project.service.SignatureService;
import cc.dfsoft.project.biz.base.settlement.dao.SettlementDeclarationDao;
import cc.dfsoft.project.biz.base.settlement.dto.SettlementDeclarationReq;
import cc.dfsoft.project.biz.base.settlement.entity.SettlementDeclaration;
import cc.dfsoft.project.biz.base.settlement.entity.SettlementJonintlySign;
import cc.dfsoft.project.biz.base.settlement.enums.AuditResultEnum;
import cc.dfsoft.project.biz.base.settlement.enums.SubQuantitiesStatusEnum;
import cc.dfsoft.project.biz.base.settlement.service.SettlementDeclarationService;
import cc.dfsoft.project.biz.base.settlement.service.SettlementJonintlySignService;
import cc.dfsoft.project.biz.base.subpackage.dao.SubContractDao;
import cc.dfsoft.project.biz.base.subpackage.dao.SubQuantitiesDao;
import cc.dfsoft.project.biz.base.subpackage.dao.SubSupplyContractDao;
import cc.dfsoft.project.biz.base.subpackage.dto.SubQuantitiesDto;
import cc.dfsoft.project.biz.base.subpackage.entity.SubContract;
import cc.dfsoft.project.biz.base.subpackage.entity.SubQuantities;
import cc.dfsoft.project.biz.base.subpackage.entity.SubSupplyContract;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.biz.sys.dept.entity.Department;
import cc.dfsoft.uexpress.biz.sys.dept.entity.Staff;
import cc.dfsoft.uexpress.biz.sys.dept.enums.PostTypeEnum;
import cc.dfsoft.uexpress.biz.sys.dept.service.DepartmentService;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.constant.DataFilerSetUpDto;
import cc.dfsoft.uexpress.common.enums.WorkFlowActionEnum;
import cc.dfsoft.uexpress.common.util.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.*;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class SettlementDeclarationServiceImpl implements SettlementDeclarationService {

	/**业务操作记录服务接口*/
	@Resource
	OperateRecordService operateRecordService;
	
	/**工程dao*/
	@Resource
	ProjectDao projectDao;
	
	@Resource
	SettlementDeclarationDao settlementDeclarationDao;
	
	@Resource
	ConstructionPlanDao constructionPlanDao;
	
	@Resource
	BudgetDao budGetDao;	
	@Resource
	SubContractDao subContractDao;	
	@Resource
	SubQuantitiesDao subQuantitiesDao;
	@Resource
	DocTypeDao docTypeDao;
	@Resource
	ManageRecordDao managerecordDao;	
	@Resource
	SignatureService signatureService;
	/**业务操作记录*/
	@Resource
	OperateRecordDao operateRecordDao;
	/**员工dao*/
	@Resource
	DesignInfoDao designInfoDao;
	@Resource
	SystemSetDao systemSetDao;
	@Resource
	WorkFlowService workFlowService;
	@Resource
	SubSupplyContractDao subSupplyContractDao;

	/**部门服务*/
	@Resource
	DepartmentService departmentService;
	/**时限服务接口*/
	@Resource
	TimeLimitDao timeLimitDao;
	/**延期申请服务接口*/
	@Resource
	ApplyDelayDao applyDelayDao;
	@Resource
	FestivalService festivalService;
	
	@Resource
	DocTypeService docTypeService;
	@Resource
	AbandonedRecordService abandonedRecordService;
	@Resource
	SettlementJonintlySignService settlementJonintlySignService;



	
	@Override
	public ConstructionPlan constructionPlan(String projId) {
		if(StringUtils.isNotBlank(projId)){
			ConstructionPlan cp = constructionPlanDao.viewPlanById(projId);
			return cp;
		}
		return null;
	}

	@Override
	public Budget budGet(String projId) {
		if(StringUtils.isNotBlank(projId)){
			Budget bg = budGetDao.queryBudgeByprojId(projId);
			return bg;
		}
		return null;
	}

	@Override
	public SubContract subContract(String projId) {
		if(StringUtils.isNotBlank(projId)){
			try {
				SubContract sc = subContractDao.findSubContractByprojId(projId);
				return sc;
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		}
		return null;
	}

	@Override
	public SubSupplyContract subSupplyContract(String projId) {
		if(StringUtils.isNotBlank(projId)){
			SubSupplyContract ssc = subSupplyContractDao.findByProjId(projId);
			return ssc;
		}
		return null;
	}
	@Override
	public SettlementDeclaration getSettlementDeclaration(String projId) {
		SettlementDeclaration result = new SettlementDeclaration();
		LoginInfo loginInfo=SessionUtil.getLoginInfo();
		SettlementDeclaration sd = settlementDeclarationDao.findByProjId(projId);
		if(sd!=null){
			if(sd.getMaterialsCost()!=null&&sd.getFirstMaterialsCost()==null){
				sd.setFirstMaterialsCost(sd.getMaterialsCost());
			}
			if(sd.getFirstMaterialsCost()!=null && sd.getEndMaterialsCost()==null){
				sd.setEndMaterialsCost(sd.getFirstMaterialsCost());
			}
			result=sd;
			if(StringUtils.isNotBlank(projId)){
				ConstructionPlan cp=this.constructionPlan(projId);			
				if(null != cp){
					//result.setCmoName(cp.getManagementOffice());
					result.setSuName(cp.getSuName());
					result.setCuName(cp.getCuName());
					result.setCuPhone(cp.getCuPhone());
				}
			}
			SubContract sc=this.subContract(projId);
			if(sc!= null  ){
				result.setScAmount(sc.getScAmount());                  //分包合同金额
				result.setScNo(sc.getScNo());						   //分包合同编号				
			}
			/*SubSupplyContract ssc = this.subSupplyContract(projId);
			if(ssc != null){
				result.setSscNo(ssc.getSscNo());					//分包协议编号
			}*/
			Project pro = projectDao.get(projId);
			if(pro != null ){
				result.setProjId(projId);
				result.setProjName(pro.getProjName());
				result.setProjAddr(pro.getProjAddr());
				result.setProjNo(pro.getProjNo());
				result.setProjScaleDes(pro.getProjScaleDes());
				result.setProjTypeDesc(pro.getProjectTypeDes());
				result.setProjContributionModeDes(pro.getContributionModeDes());
				result.setCorpId(pro.getCorpId());
				result.setCorpName(pro.getCorpName());
				result.setDeptId(pro.getDeptId());
				result.setDeptName(pro.getDeptName());
				result.setBudgeterAudit(pro.getBudgeter());
				result.setSettlementerId(pro.getSettlementerId());
				result.setSettlementer(pro.getSettlementer());
				Department department = departmentService.queryDepartment(pro.getDeptId());
				if(department!=null){
					result.setDeptDivide(department.getDeptDivide());
				}
				result.setContractAmount(pro.getContractAmount());//安装合同金额
				
			}
			//合同编号
			/*Contract contract=contractDao.viewContractByprojId(projId);
			if(contract !=null ){
				result.setConNo(contract.getConNo());				   //合同编号
				result.setContractAmount(contract.getContractAmount());//合同金额
			}*/
			//图纸编号
			DesignInfo designInfo = designInfoDao.queryInfoByProjId(projId);
			if(designInfo!=null && StringUtils.isNotBlank(designInfo.getDesignDrawingNo())){
				result.setDrawingNo(designInfo.getDesignDrawingNo());
			}
			//编制人信息
			if(StringUtils.isBlank(sd.getCompilerId())){
				result.setCompilerPhone(loginInfo.getMobile());
				result.setCompiler(loginInfo.getStaffName());
				result.setCompilerId(loginInfo.getStaffId());
			}
			
		}else{
			if(StringUtils.isNotBlank(projId)){
				ConstructionPlan cp=this.constructionPlan(projId);			
				if(cp!=null){
					//result.setCmoName(cp.getManagementOffice());
					result.setSuName(cp.getSuName());
					result.setCuName(cp.getCuName());
					result.setCuPhone(cp.getCuPhone());
				}
				SubContract sc=this.subContract(projId);
				if(null != sc){
					result.setScAmount(sc.getScAmount());
					result.setScNo(sc.getScNo());
				}
				/*SubSupplyContract ssc = this.subSupplyContract(projId);
				if(ssc != null){
					result.setSscNo(ssc.getSscNo());					//分包协议编号
				}*/
				//合同编号
				/*Contract contract=contractDao.viewContractByprojId(projId);
				if(contract !=null ){
					result.setConNo(contract.getConNo());				   //合同编号
					result.setContractAmount(contract.getContractAmount());//合同金额
				}*/
				//图纸编号
				DesignInfo designInfo = designInfoDao.queryInfoByProjId(projId);
				if(designInfo!=null && StringUtils.isNotBlank(designInfo.getDesignDrawingNo())){
					result.setDrawingNo(designInfo.getDesignDrawingNo());
				}
				Project pro = projectDao.get(projId);
				if(pro!=null){
					result.setProjId(projId);
					result.setProjName(pro.getProjName());
					result.setProjAddr(pro.getProjAddr());
					result.setProjNo(pro.getProjNo());
					result.setProjScaleDes(pro.getProjScaleDes());
					result.setProjTypeDesc(pro.getProjectTypeDes());
					result.setProjContributionModeDes(pro.getContributionModeDes());
					result.setCorpId(pro.getCorpId());
					result.setCorpName(pro.getCorpName());
					result.setDeptId(pro.getDeptId());
					result.setDeptName(pro.getDeptName());
					result.setSettlementerId(pro.getSettlementerId());
					result.setSettlementer(pro.getSettlementer());
				}
				//编制人信息
				result.setCompilerPhone(loginInfo.getMobile());
				result.setCompiler(loginInfo.getStaffName());
				result.setCompilerId(loginInfo.getStaffId());
			}
			result.setOcoDate(settlementDeclarationDao.getDatabaseDate());
		}
		
		return result;
	}

	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public String saveSettlementDeclaration(SettlementDeclaration settlementDeclaration) throws Exception {
		boolean flag = false;
		if(StringUtils.isBlank(settlementDeclaration.getSdId())){
			settlementDeclaration.setSdId(IDUtil.getUniqueId(Constants.MODULE_CODE_SETTLEMENT));
			flag = true;
		}
		settlementDeclaration.setIsPrint(ContractIsPrintEnum.HAVE_NOT_PRINT.getValue());
		settlementDeclaration.setPushStatus(AuditResultEnum.NOT_PASSED.getValue());
		//当前登录人信息
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		//编制人信息
		if(StringUtils.isBlank(settlementDeclaration.getCompilerId())){
			settlementDeclaration.setTenantId(loginInfo.getTenantId());
			settlementDeclaration.setCompilerId(loginInfo.getStaffId());
		}
		//更新预算信息
		settlementDeclarationDao.saveOrUpdate(settlementDeclaration);
		//保存签字信息
		signatureService.saveOrUpdateSign("menuId+menuNane+26",settlementDeclaration.getSign(), settlementDeclaration.getProjId(), settlementDeclaration.getSdId(), settlementDeclaration.getClass().getName(),flag);
		/*//更新工程信息
		Project pro=projectDao.get(settlementDeclaration.getProjId());         //通过工程id查找Project
		//生成工程状态
		String statusId=workFlowService.queryProjStatusId(pro.getCorpId(),pro.getProjectType(),pro.getContributionMode(),WorkFlowActionEnum.SETTLEMENT_REPORT.getActionCode(), true);
		//设置工程状态
		pro.setProjStatusId(statusId); 								
		projectDao.saveOrUpdate(pro);

		//形成操作记录
		String orId = IDUtil.getUniqueId(Constants.MODULE_CODE_SETTLEMENT);
		operateRecordService.createOperateRecord(orId, settlementDeclaration.getProjId(), pro.getProjNo(), StepEnum.SETTLEMENT_REPORT.getValue(), StepEnum.SETTLEMENT_REPORT.getMessage(), "");
		*/
		return "true";
	}

	/**
	 * 保存结算工程量
	 * @author liaoyq
	 * @createTime 217-8-10
	 */
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public String batInsertSubQualities(SubQuantitiesDto qdto) {
		SettlementDeclaration sd = settlementDeclarationDao.findByProjId(qdto.getProjId());
		String sdId;
		if(sd!=null){
			sd.setQuantitiesTotal(qdto.getSubmitAmount());
			if(StringUtils.isBlank(sd.getPushStatus())){
				sd.setPushStatus(AuditResultEnum.NOT_PASSED.getValue());
			}
			settlementDeclarationDao.saveOrUpdate(sd);
			sdId = sd.getSdId();
		}else{
			SettlementDeclaration settlementDeclaration = new SettlementDeclaration();
			settlementDeclaration.setSdId(IDUtil.getUniqueId(Constants.MODULE_CODE_SETTLEMENT));
			settlementDeclaration.setProjId(qdto.getProjId());
			settlementDeclaration.setProjNo(qdto.getProjNo());
			settlementDeclaration.setProjName(qdto.getProjName());
			settlementDeclaration.setQuantitiesTotal(qdto.getSubmitAmount());
			settlementDeclaration.setPushStatus(AuditResultEnum.NOT_PASSED.getValue());
			settlementDeclarationDao.saveOrUpdate(settlementDeclaration);
			sdId = settlementDeclaration.getSdId();
		}
		//保存工程量
		if(StringUtils.isNotBlank(sdId)){
			List<SubQuantities> list=new ArrayList<SubQuantities>();
			//添加分包工程量记录
			for(SubQuantities sq:qdto.getList()){
				//工程量标准
				sq.setSqId(IDUtil.getUniqueId(Constants.MODULE_CODE_SUBCONTRACT));
				sq.setSbId(sdId); //存储结算ID
				sq.setSqStatus(SubQuantitiesStatusEnum.SETTLEMENT.getValue());
				list.add(sq);
			}
			subQuantitiesDao.deleteBySbIdAndStatus(sdId,SubQuantitiesStatusEnum.SETTLEMENT.getValue());
			subQuantitiesDao.batchInsertObjects(list);
		}
		
		return sdId;
	}

	@Override
	public Map<String,Object> querySettlement(SettlementDeclarationReq req) throws ParseException {
		Map<String,Object> map=new HashMap<String,Object>();
		if(StringUtils.isNotBlank(req.getProjStatusId())){
			ProjectQueryReq projReq=new ProjectQueryReq();
			projReq.setMenuId(req.getMenuId());
			projReq.setProjStatusId(req.getProjStatusId());
			Map<String,Object> map1=projectDao.queryProject(projReq);
			 
			if(map1 != null && map1.get("data") != null){
				
				List<Project> list=(List) map1.get("data");
				if(list.size()>0){
					List<String> listNew=new ArrayList<String>();
					for(Project proj:list){
						listNew.add(proj.getProjId());
					}
					return settlementDeclarationDao.querySettlement(req,listNew);
				}
			}
		}
		map.put("data",new ArrayList());
		map.put("recordsTotal", 0);
		map.put("recordsFiltered", 0);
		map.put("draw", req.getDraw());
		return map;
		
	}
	@Override
	public Map<String, Object> querySettlementForAudit(SettlementDeclarationReq req)
			throws ParseException {
		 SystemSet systemSet=systemSetDao.get("stepId", req.getStepId());
		  if(systemSet!=null&&StringUtil.isNotBlank(systemSet.getTimeSpan())){
			  req.setTimeLimit(Integer.parseInt(systemSet.getTimeSpan()));
		  }
		Map<String,Object> result =this.querySettlementByTime(req);
		List<SettlementDeclaration> data = (List<SettlementDeclaration>) result.get("data");
		//按步骤id进行查询 获取单据类型
		//DocType docType = docTypeDao.findByStepId(req.getStepId());
		//String grade = docType==null?"":docType.getGrade();
		DocType docType = new DocType();
		String grade="";
		if(data!=null && data.size()>0){
		   /**
			 * -1 未审核  0 审核未通过  1 审核通过  2待审核
			 * 若该 审核为三级审核，且一级审核通过、二级审核未通过，三级审核待审核，则键值对：{"level1":"1","level2":"0","level3":"2"};
			 * */
			//遍历循环  设置审核级别
			for(int i = 0;i<data.size();i++){
				Project pro=projectDao.get(data.get(i).getProjId());
				docType = docTypeService.findByStepId(StepEnum.REPORT_DONE_CONFIRM.getValue(),pro.getCorpId(),pro.getProjectType(),pro.getContributionMode());
				if(docType!=null && StringUtils.isNotBlank(docType.getGrade())){
					data.get(i).setLevel(docType.getGrade());// 设置审核总级数（结算审核3级审核）
					grade=docType.getGrade();
				}else{
					grade="0";
					data.get(i).setLevel(grade);// 设置审核总级数（结算审核3级审核）
				}
				
				Map<String,String> levelBtn = new HashMap();
				//以下循环，遍历出默认应该几级审核，审核状态分别为什么，例如三级审核，则一级待审、二级未审、三级未审{"level1":"2","level2":"-1","level3":"-1"}；
				for(int n=1;n<Integer.parseInt(grade)+1;n++){
					if(n==1){
						levelBtn.put("level"+n, "2");// 待审
					}else{
						levelBtn.put("level"+n, "-1");//未审
					}
				}
				ManageRecordQueryReq mrq = new ManageRecordQueryReq();
				mrq.setBusinessOrderId(data.get(i).getSdId());				
				mrq.setStepId(req.getProjStatusId());								
				mrq.setProjId(data.get(i).getProjId());
				//List<ManageRecord> mrls  = (List<ManageRecord>) managerecorddao.queryManageRecord(mrq).get("data");
				List<ManageRecord> mrls = managerecordDao.findByStepIdProjIdIsPass(data.get(i).getProjId(),req.getStepId(),MrResultEnum.PASSED.getValue());
				if(mrls!=null && mrls.size()>0){
					//遍历循环，获取审核是否通过
					for(ManageRecord mr:mrls){
						levelBtn.put("level"+mr.getMrAuditLevel(),mr.getMrResult());
					}
					if(mrls.size()<Integer.parseInt(grade)){
						levelBtn.put("level"+(mrls.size()+1), "2");
					}
				}


				//某个人配置的某个公司下的级别不能审核（stffId_corpId_menuId）
				LoginInfo loginInfo = SessionUtil.getLoginInfo();
				DataFilerSetUpDto dfsud = Constants.isConfig(loginInfo.getStaffId() + "_" +data.get(i).getCorpId() + "_"+req.getMenuId());
				if (dfsud!=null){
					levelBtn.put("level" +dfsud.getSupSql(), "-1");
				}



				data.get(i).setMrAuditLevel(JSONSerializer.toJSON(levelBtn).toString());
			}
			result.put("data", data);
		}
		return result;
	}
	  private Map<String, Object> querySettlementByTime(SettlementDeclarationReq req) throws ParseException {
		    Map<String,Object> map =this.querySettlement(req);
			 //最后一个操作记录的工程记录列表（符合状态的当前工程）
			List<Map<String, Object>> ors=operateRecordDao.getOptRecordByTime(req.getProjStatusId());
			
			//符合当前状态的工程列表
			List<SettlementDeclaration> list=(List<SettlementDeclaration>) map.get("data");
			List<SettlementDeclaration> listNew=new ArrayList<SettlementDeclaration>();
			//时间限制（单位天）
			long timel = req.getTimeLimit()!=null?req.getTimeLimit():0;;	
			long secondsLimit=-1l;
			if(timel!=0){
				secondsLimit=timel*24*60*60;
			}
			for(SettlementDeclaration con :list){
				Project pro=projectDao.get(con.getProjId());
				con.setSettlementer(pro.getSettlementer());
				con.setSettlementerId(pro.getSettlementerId());
				con.setContributionMode(pro.getContributionMode());//回退时使用
				for(Map<String, Object> or:ors){
					if(or.get("PROJ_ID").equals(con.getProjId())){
						//业务操作记录中时间
						Date oldTime=(Date) or.get("OPERATE_TIME");		
						//当前时间
						Date nowTime=projectDao.getDatabaseDate();
						//获取两个日期之间的工作日天数
						long workDays = 0;
						try {
							workDays = FestivalUtil.calLeaveDays(oldTime, nowTime, festivalService.getCacheMap(Constants.WORKDAYS), festivalService.getCacheMap(Constants.HOLIDAYS));
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						long seconds=workDays*24*60*60;
						//如果是终审确认，那么原定时长为
						if(StepEnum.REPORT_DONE_CONFIRM.getValue().equals(req.getStepId())){
							String timeLimitType = null;
							if(con.getSendDeclarationCost().compareTo(new BigDecimal(50000))<0){
								timeLimitType = TimeLimitTypeEnum.TIME_LIMIT13.getValue();
							}else if(con.getSendDeclarationCost().compareTo(new BigDecimal(100000))<0){
								timeLimitType = TimeLimitTypeEnum.TIME_LIMIT14.getValue();
							}else if(con.getSendDeclarationCost().compareTo(new BigDecimal(500000))<0){
								timeLimitType = TimeLimitTypeEnum.TIME_LIMIT15.getValue();
							}else if(con.getSendDeclarationCost().compareTo(new BigDecimal(1000000))<0){
								timeLimitType = TimeLimitTypeEnum.TIME_LIMIT16.getValue();
							}else{
								timeLimitType = TimeLimitTypeEnum.TIME_LIMIT17.getValue();
							}
							TimeLimit  timeLimit = timeLimitDao.findByType(timeLimitType);
							if(null!=timeLimit){
								if(timeLimit.getTlDuration().compareTo(BigDecimal.ZERO)>0){
									long days = timeLimit.getTlDuration().setScale(0, BigDecimal.ROUND_HALF_UP).longValue();
									if(days>0){
										secondsLimit=days*24*60*60;
										timel = days;
									 }
								}
							}
						}
						//该步骤延期申请已通过的时长之和
						Integer delyDays =applyDelayDao.getDelyDays(pro.getProjId(),req.getStepId()).setScale(0, BigDecimal.ROUND_HALF_UP).intValue();
						if(delyDays>0){
							secondsLimit+=(delyDays*24*60*60);
							timel += delyDays;
						}
						//用于时限进度展示
						WorkDayDto workDayDto = new WorkDayDto();
						workDayDto.setDaysLimit(String.valueOf(timel));
						workDayDto.setWorkDays(String.valueOf(workDays));
						workDayDto.setHaveDays(String.valueOf(timel-workDays));
						con.setWorkDayDto(workDayDto);
						//如果当前时间-上个步骤的操作时间大于时间限制段则为超时
						if(secondsLimit>0&&seconds>secondsLimit){
							con.setOverdue(true);
							int i = (int)Math.ceil((seconds-secondsLimit)/(24*60*60));
							con.setOverDay(i);		
							continue;
						}
					}
				}
				if(pro!=null){
					con.setProjectType(pro.getProjectType());
				}
				listNew.add(con);
			}
			map.put("data",listNew);
			return map;
		}
	@Override
	public Map<String, Object> querySettlementForEnd(SettlementDeclarationReq req,String grade) throws ParseException {
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		Map<String,Object> result =this.querySettlementByTime(req);
		List<SettlementDeclaration> data = (List<SettlementDeclaration>) result.get("data");
		/*Map<String, Object> result = this.queryProjectByTime(projectQueryReq);
		List<Project> data = (List<Project>) result.get("data");*/
		/*List<ConstructionPlan> cp = constructionPlanDao.getAll();
		for(int i=0 ; i<cp.size(); i++){
			for(int j=0; j<data.size(); j++){
				if(cp.get(i).getProjId().equals(data.get(j).getProjId())){
					//data.get(j).setManagementOffice(cp.get(i).getManagementOffice());
					data.get(j).setCuName(cp.get(i).getCuName());
				}
			}
		}*/
		if (data != null && data.size() > 0) {
			
			/**
			 * -1 未审核 0 审核未通过 1 审核通过 2待审核 若该
			 * 审核为三级审核，且一级审核通过、二级审核未通过，三级审核待审核，则键值对：{"level1":"1","level2":"0",
			 * "level3":"2"};
			 */
			for (int i = 0; i < data.size(); i++) {
				DocType docType = docTypeService.findByStepId(StepEnum.REPORT_DONE_CONFIRM.getValue(),data.get(i).getCorpId(),data.get(i).getProjectType(),data.get(i).getContributionMode());
				if(docType!=null && StringUtils.isNotBlank(docType.getGrade())){
					grade=docType.getGrade();
				}else{
					grade="0";
				}
				data.get(i).setLevel(grade);// 设置审核总级数（设计信息2级审核）
				Map<String, String> levelBtn = new HashMap<String, String>();

				for (int n = 1; n < Integer.parseInt(grade) + 1; n++) {
					if (n == 1) {
						levelBtn.put("level" + n, "2");// 待审
					} else {
						levelBtn.put("level" + n, "-1");// 未审
					}
				}
				List<ManageRecord> mrls = managerecordDao.findByStepIdProjIdIsPass(data.get(i).getProjId(), req.getStepId(),
						MrResultEnum.PASSED.getValue());
				if (mrls != null && mrls.size() > 0) {
					// 遍历循环，获取审核是否通过
					for (ManageRecord mr : mrls) {
						levelBtn.put("level" + mr.getMrAuditLevel(), mr.getMrResult());
					}
					if (mrls.size() < Integer.parseInt(grade)) {
						levelBtn.put("level" + (mrls.size() + 1), "2");
					}
					//哪个公司的工程某个人不能审核某个级别
					List<DataFilerSetUpDto> datafiltes = Constants.getDataFilterMapByKey(data.get(i).getCorpId()+"_"+loginInfo.getStaffId()+"_"+(mrls.size() + 1)+"_"+req.getMenuId());
					if(datafiltes!=null && datafiltes.size()>0){
						levelBtn.put("level" + (mrls.size() + 1), "-1");
					}else{
						levelBtn.put("level" + (mrls.size() + 1), "2");
					}
					
					// 根据工程类型出资方式配置配置审核权限
					List<DataFilerSetUpDto> reviewPermissions = Constants.getDataFilterMapByKey(loginInfo.getDeptId()+"_"+data.get(i).getProjectType()+"_"+data.get(i).getContributionMode()+"_"+(mrls.size() + 1)+"_"+req.getMenuId());
					if(reviewPermissions !=null && reviewPermissions.size()>0){
						levelBtn.put("level" + (mrls.size() + 1), reviewPermissions.get(0).getSupSql());
					}else{
						levelBtn.put("level" + (mrls.size() + 1), "2");
					}
				}else{
					//没有派工给预结算组组长不能审核一级
					Project pro = projectDao.get(data.get(i).getProjId());
					if(!loginInfo.getStaffId().equals(pro.getSettlementerId()) && loginInfo.getPost().contains(PostTypeEnum.BUDGET_GROUP_LEADER.getValue())){
						levelBtn.put("level"+(mrls.size()+1), "-1");
					}else{
						// 根据工程类型出资方式配置配置审核权限
						List<DataFilerSetUpDto> reviewPermissions = Constants.getDataFilterMapByKey(loginInfo.getDeptId()+"_"+data.get(i).getProjectType()+"_"+data.get(i).getContributionMode()+"_"+(mrls.size() + 1)+"_"+req.getMenuId());
						if(reviewPermissions !=null && reviewPermissions.size()>0){
							levelBtn.put("level" + (mrls.size() + 1), reviewPermissions.get(0).getSupSql());
						}else{
							levelBtn.put("level" + (mrls.size() + 1), "2");
						}
					}
				}
				data.get(i).setMrAuditLevel(JSONSerializer.toJSON(levelBtn).toString());
			}
			result.put("data", data);
		}
		return result;
	}
	
	/**
	 * 派遣结算员
	 * @author
	 * @createTime 
	 * @param
	 * @return
	 */
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void update(DesignDispatchDto designDispatchReq) {
		if(StringUtils.isNotBlank(designDispatchReq.getProjId())){
			
			//更新工程信息
			Project pro=projectDao.get(designDispatchReq.getProjId());//根据Id查询工程
			String staffName=designDispatchReq.getSurveyer();
			
			//结算人
			pro.setSettlementer(staffName);
			
			List<Staff> list= designInfoDao.findByStaffName(staffName);
	 		if(list.size()>0){
	 			pro.setSettlementerId(list.get(0).getStaffId());
	 		}
			
			String statusId=WorkFlowUtil.workFlowStatus(WorkFlowActionEnum.AUDIT_START_DISPATCH.getActionCode());
			pro.setProjStatusId(statusId);          				 //更新工程状态
			
			//更新工程
			projectDao.update(pro);
			//形成操作记录
			String orId = IDUtil.getUniqueId(Constants.MODULE_CODE_SETTLEMENT);
			//operateRecordService.createOperateRecord(orId, pro.getProjId(), pro.getProjNo(), StepEnum.AUDIT_START_DISPATCH.getValue(), StepEnum.AUDIT_START_DISPATCH.getMessage(), "");
		}
	}

	@Override
	@Transactional(readOnly = false, propagation =Propagation.REQUIRED)
	public String pushSettlement(SettlementDeclaration sd) throws Exception {
		sd.setPushStatus(AuditResultEnum.APPLYING.getValue());//修改推送状态为已推送：1
		settlementDeclarationDao.saveOrUpdate(sd);
		//保存签字信息
		signatureService.saveOrUpdateSign("menuId+menuNane+25",sd.getSign(), sd.getProjId(), sd.getSdId(), sd.getClass().getName(),false);
		//更新工程信息
		Project pro=projectDao.get(sd.getProjId());         //通过工程id查找Project
		//生成工程状态
		String statusId=workFlowService.queryProjStatusId(pro.getCorpId(),pro.getProjectType(),pro.getContributionMode(),WorkFlowActionEnum.SETTLEMENT_REPORT.getActionCode(), true);
		//设置工程状态
		pro.setProjStatusId(statusId); 								
		

		//形成操作记录
		String orId = IDUtil.getUniqueId(Constants.MODULE_CODE_SETTLEMENT);
		String todoer=operateRecordService.createOperateRecord(orId, sd.getProjId(), pro.getProjNo(), StepEnum.SETTLEMENT_REPORT.getValue(), StepEnum.SETTLEMENT_REPORT.getMessage(), "");
		
		pro.setToDoer(todoer);//待办人
		projectDao.saveOrUpdate(pro);
		return sd.getSdId();
	}

	@Override
	public String findPrintDataByProjId(String projId,String type) {
		String result ="";
		//根据工程ID查询合同信息
		Project project = projectDao.get(projId);  //根据工程ID取得实体
		SettlementDeclaration sd= settlementDeclarationDao.findByProjId(projId);
		//安装合同报表
		String arrayStr = CompletionDataPrintEnum.SETTLEMENT.getCptUrl();
		//2、使用JSONArray
		JSONArray jsonArray=JSONArray.fromObject(arrayStr);
		if(jsonArray!=null&&jsonArray.size()>0 && sd!=null  && project !=null){
			for(Object obj : jsonArray){
				 JSONObject jsonObject=JSONObject.fromObject(obj);
				 CompletionDataPrintDto dto = (CompletionDataPrintDto)JSONObject.toBean(jsonObject, CompletionDataPrintDto.class);
				 String[] menuIdAndMark = type.split("-"); // 分解施工任务单标识符和菜单ID,从而取得菜单ID
				 String menuId = menuIdAndMark[menuIdAndMark.length-1]; // 获取菜单id
				 String key = project.getContributionMode()+"_"+project.getCorpId()+"_"+menuId;
				 Object reportVersion = Constants.getSysConfigByKey(key);
				   if(reportVersion !=null){
					   //记录特定字符索引  
					   int beginIndex = dto.getReportlet().indexOf("/"); 
					   int endIndex = dto.getReportlet().lastIndexOf("/");
				       String reportlet = dto.getReportlet().substring(beginIndex, endIndex+1);  //截取字符'/'之间的字符串
					   dto.setReportlet(reportlet+reportVersion.toString());   //若reportVersion不为空则重新设置报表路径
				   }
				 result = "{reportlet:'"+dto.getReportlet()+"',projId:'"+sd.getProjId()+"',sdId:'"+sd.getSdId()+"',imgUrl:'"+Constants.DISK_PATH+Constants.SIGN_DISK_PATH+"'}";
				 return result;
				
			}
		}
		return null;
	}

	@Override
	public Map<String, Object> querySettlementAuditStart(
			SettlementDeclarationReq req) throws ParseException {
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		 SystemSet systemSet=systemSetDao.get("stepId", req.getStepId());
		  if(systemSet!=null&&StringUtil.isNotBlank(systemSet.getTimeSpan())){
			  req.setTimeLimit(Integer.parseInt(systemSet.getTimeSpan()));
		  }
		Map<String,Object> result =this.querySettlementByTime(req);
		List<SettlementDeclaration> data = (List<SettlementDeclaration>) result.get("data");
		//按步骤id进行查询 获取单据类型
		//DocType docType = docTypeDao.findByStepId(req.getStepId());
		//String grade = docType==null?"":docType.getGrade();
		DocType docType = new DocType();
		String grade="";
		if(data!=null && data.size()>0){
		   /**
			 * -1 未审核  0 审核未通过  1 审核通过  2待审核
			 * 若该 审核为三级审核，且一级审核通过、二级审核未通过，三级审核待审核，则键值对：{"level1":"1","level2":"0","level3":"2"};
			 * */
			//遍历循环  设置审核级别
			for(int i = 0;i<data.size();i++){
				Project pro=projectDao.get(data.get(i).getProjId());
				docType = docTypeService.findByStepId(StepEnum.SETTLEMENT_REPORT_START.getValue(),pro.getCorpId(),pro.getProjectType(),pro.getContributionMode());
				if(docType!=null && StringUtils.isNotBlank(docType.getGrade())){
					data.get(i).setLevel(docType.getGrade());// 设置审核总级数（结算审核3级审核）
					grade=docType.getGrade();
				}else{
					grade="0";
					data.get(i).setLevel(grade);// 设置审核总级数（结算审核3级审核）
				}
				
				Map<String,String> levelBtn = new HashMap();
				//以下循环，遍历出默认应该几级审核，审核状态分别为什么，例如三级审核，则一级待审、二级未审、三级未审{"level1":"2","level2":"-1","level3":"-1"}；
				for(int n=1;n<Integer.parseInt(grade)+1;n++){
					if(n==1){
						levelBtn.put("level"+n, "2");// 待审
					}else{
						levelBtn.put("level"+n, "-1");//未审
					}
				}
				ManageRecordQueryReq mrq = new ManageRecordQueryReq();
				mrq.setBusinessOrderId(data.get(i).getSdId());				
				mrq.setStepId(req.getStepId());								
				mrq.setProjId(data.get(i).getProjId());
				//List<ManageRecord> mrls  = (List<ManageRecord>) managerecorddao.queryManageRecord(mrq).get("data");
				List<ManageRecord> mrls = managerecordDao.findByStepIdProjIdIsPass(data.get(i).getProjId(),req.getStepId(),MrResultEnum.PASSED.getValue());
				if(mrls!=null && mrls.size()>0){
					//遍历循环，获取审核是否通过
					for(ManageRecord mr:mrls){
						levelBtn.put("level"+mr.getMrAuditLevel(),mr.getMrResult());
					}
					if(mrls.size()<Integer.parseInt(grade)){
						levelBtn.put("level"+(mrls.size()+1), "2");
					}
				}else{
					//结算初审，幕投类项目现场代表不能一级初审
					if(ContributionMode.INVESTMENT_CODE_TYPE.equals(data.get(i).getContributionMode()) && loginInfo.getPost().contains(PostTypeEnum.BUILDER.getValue())){
						levelBtn.put("level1", "-1");
					}
					//结算初审，非幕投类项目监理不能一级初审
					if(!ContributionMode.INVESTMENT_CODE_TYPE.equals(data.get(i).getContributionMode()) && loginInfo.getPost().contains(PostTypeEnum.SUJGJ.getValue())){
						levelBtn.put("level1", "-1");
					}
				}
				data.get(i).setMrAuditLevel(JSONSerializer.toJSON(levelBtn).toString());
			}
			result.put("data", data);
		}
		return result;
	}









	@Override
	public boolean rollBackContainsSettlementDeclaration(String projId, String rollBackReason) {
		Project project = projectDao.get(projId);
		SettlementDeclaration settlDeclar= settlementDeclarationDao.findByProjId(projId);


		//备份原信息记录
		Map<String,Object> criteriaMap=new HashMap<>();
		String t_projId = Annotations.getFieldGetMethodColumnAnNameVal(SettlementDeclaration.class, "projId");
		criteriaMap.put(t_projId,projId);
		String tableName = Annotations.getClassTableAnNameVal(SettlementDeclaration.class);
		String origData = abandonedRecordService.getThisTableOrigData(tableName,criteriaMap);

		if (settlDeclar!=null) {
			abandonedRecordService.saveAbandonedRecord(settlDeclar.getSdId(), projId, StepEnum.SETTLEMENT_REPORT.getValue(), rollBackReason, origData);

			try {//更新结算报审信息
				settlDeclar.setCompilerSign(null); //编制人签字
				settlDeclar.setCuPrincipal(null); //施工负责人签字
				settlDeclar.setCuAudit(null);//施工审核人签字
				settlDeclar.setOcoDate(null);//报审日期
				settlDeclar.setCostControlPrincipal(null);
				settlDeclar.setCostControlReAudit(null);
				settlDeclar.setCostControlAudit(null);
				settlDeclar.setEndDeclaraDate(null);
				settlDeclar.setEndDeclarationCost(null);
				settlDeclar.setSendDeclarationCost(null);
				settlDeclar.setFirstAuditDate(null);
				settlDeclar.setFirstDeclarationCost(null);
				settlementDeclarationDao.saveOrUpdate(settlDeclar);

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}


		//更新工程信息
		project.setSettlementer(null);	//结算员
		project.setSettlementerId(null);//结算员ID
		project.setSettlementDate(null);//结算时间
		projectDao.saveOrUpdate(project);




		//删除并备份结算会签信息（签字记录、磁盘图片以及签字通知）
		SettlementJonintlySign jonintlySign = settlementJonintlySignService.findById(projId);
		if (jonintlySign==null) return true;

		Map<String,Object> map=new HashMap<>();
		String t_sj_projId = Annotations.getFieldGetMethodColumnAnNameVal(SettlementJonintlySign.class, "projId");
		map.put(t_sj_projId,projId);
		String stepId=StepEnum.SETTLEMENT_JONINTLY_SIGN.getValue();
		String sj_tableName = Annotations.getClassTableAnNameVal(SettlementJonintlySign.class);
		abandonedRecordService.delBackupsThisTableRecordAndSignature(sj_tableName,map,jonintlySign.getSjsId(),rollBackReason,stepId);


		return true;
	}

}
