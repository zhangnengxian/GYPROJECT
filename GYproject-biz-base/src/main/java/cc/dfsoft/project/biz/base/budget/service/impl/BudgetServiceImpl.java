package cc.dfsoft.project.biz.base.budget.service.impl;

import cc.dfsoft.project.biz.base.baseinfo.entity.DocType;
import cc.dfsoft.project.biz.base.baseinfo.enums.AssistProgressTypeEnum;
import cc.dfsoft.project.biz.base.baseinfo.enums.WorkFlowTypeEnum;
import cc.dfsoft.project.biz.base.baseinfo.service.DocTypeService;
import cc.dfsoft.project.biz.base.baseinfo.service.WorkFlowService;
import cc.dfsoft.project.biz.base.budget.dao.BudgetDao;
import cc.dfsoft.project.biz.base.budget.dao.BudgetTotalTableDao;
import cc.dfsoft.project.biz.base.budget.dao.ProjectCostSummaryDao;
import cc.dfsoft.project.biz.base.budget.dao.ProjectRateDao;
import cc.dfsoft.project.biz.base.budget.dto.BudgetReq;
import cc.dfsoft.project.biz.base.budget.dto.BudgetTotalQueryReq;
import cc.dfsoft.project.biz.base.budget.dto.ProjCostSummaryReq;
import cc.dfsoft.project.biz.base.budget.entity.Budget;
import cc.dfsoft.project.biz.base.budget.entity.BudgetTotalTable;
import cc.dfsoft.project.biz.base.budget.entity.ProjectCostSummary;
import cc.dfsoft.project.biz.base.budget.entity.ProjectRate;
import cc.dfsoft.project.biz.base.budget.enums.BudgetCostTypeEnum;
import cc.dfsoft.project.biz.base.budget.enums.BudgetMethodEnum;
import cc.dfsoft.project.biz.base.budget.enums.BudgetTypeEnum;
import cc.dfsoft.project.biz.base.budget.service.BudgetService;
import cc.dfsoft.project.biz.base.change.enums.ChangeStateEnum;
import cc.dfsoft.project.biz.base.constructmanage.dao.EngineeringVisaDao;
import cc.dfsoft.project.biz.base.constructmanage.dto.UploadResult;
import cc.dfsoft.project.biz.base.constructmanage.entity.EngineeringVisa;
import cc.dfsoft.project.biz.base.constructmanage.enums.StageProjectApplicationEnum;
import cc.dfsoft.project.biz.base.constructmanage.enums.StepOutWorkflowEnum;
import cc.dfsoft.project.biz.base.contract.dao.SupplementalContractDao;
import cc.dfsoft.project.biz.base.contract.entity.SupplementalContract;
import cc.dfsoft.project.biz.base.contract.enums.IsSignEnum;
import cc.dfsoft.project.biz.base.design.dao.DesignInfoDao;
import cc.dfsoft.project.biz.base.design.dto.DesignDispatchDto;
import cc.dfsoft.project.biz.base.design.dto.DesignerQueryReq;
import cc.dfsoft.project.biz.base.maintain.service.AbandonedRecordService;
import cc.dfsoft.project.biz.base.project.dao.AccessoryDao;
import cc.dfsoft.project.biz.base.project.dao.ManageRecordDao;
import cc.dfsoft.project.biz.base.project.dao.ProjectDao;
import cc.dfsoft.project.biz.base.project.dto.ProjectQueryReq;
import cc.dfsoft.project.biz.base.project.entity.AccessoryList;
import cc.dfsoft.project.biz.base.project.entity.ManageRecord;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.project.entity.Signature;
import cc.dfsoft.project.biz.base.project.enums.MrResultEnum;
import cc.dfsoft.project.biz.base.project.enums.StepEnum;
import cc.dfsoft.project.biz.base.project.service.OperateRecordService;
import cc.dfsoft.project.biz.base.project.service.ProjectService;
import cc.dfsoft.project.biz.base.project.service.SignatureService;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.biz.sys.dept.entity.Staff;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.constant.DataFilerSetUpDto;
import cc.dfsoft.uexpress.common.enums.WorkFlowActionEnum;
import cc.dfsoft.uexpress.common.util.*;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import net.sf.json.JSONSerializer;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional(readOnly = true,propagation=Propagation.REQUIRED)
public class BudgetServiceImpl implements BudgetService{
	
	@Resource
	BudgetDao budgetDao;
	@Resource
	ProjectCostSummaryDao projectCostSummaryDao;	
	@Resource
	ProjectDao projectDao;
	@Resource
	ProjectRateDao projectRateDao;
	/**业务操作记录服务接口*/
	@Resource
	OperateRecordService operateRecordService;
	/**预算总表*/
	@Resource
	BudgetTotalTableDao budgetTotalTableDao;
	
	/**设计信息*/
	@Resource
	DesignInfoDao designInfoDao;
	/**工作流接口*/
	@Resource
	WorkFlowService workFlowService;
	
	@Resource
	AccessoryDao accessoryDao;
	
	@Resource
	SignatureService signatureService;
	
	/** 管理记录服务接口 */
	@Resource
	ManageRecordDao manageRecordDao;
	
	@Resource
	ProjectService projService;
	
	@Resource
	 DocTypeService  docTypeService;
	@Resource
	EngineeringVisaDao engineeringVisaDao;

	@Resource
	SupplementalContractDao supplementalContractDao;
	@Resource
	AbandonedRecordService abandonedRecordService;
	
	
	/**
	 * 查预算总表
	 */
	@Override
	public Budget queryBudgeByprojId(String projId) {
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		
		Budget budget=budgetDao.queryBudgeByprojId(projId);
		Project pro=projectDao.get(projId);
		
		if(pro!=null){
			if(budget==null){//工程还未预算
				budget = new Budget();
				budget.setProjId(pro.getProjId());
				budget.setProjNo(pro.getProjNo());
				budget.setTenantId(loginInfo.getTenantId());
			}
			budget.setProjName(pro.getProjName());
			budget.setProjScaleDes(pro.getProjScaleDes());
			budget.setProjAddr(pro.getProjAddr());
			budget.setProjLtypeId(pro.getProjLtypeId());
			budget.setSurveyer(pro.getSurveyer());
			budget.setDesigner(pro.getDesigner());
			budget.setBudgeter(pro.getBudgeter() == null? loginInfo.getStaffName():pro.getBudgeter());  //判断预算员是否为空，为空则取当前登录人
			budget.setBudgeterId(pro.getBudgeterId()==null? loginInfo.getStaffId():pro.getBudgeterId());  //判断预算员ID是否为空，为空则取当前登录人
			budget.setCustContact(pro.getCustContact());
			budget.setCustName(pro.getCustName());
			budget.setCustPhone(pro.getCustPhone());
			budget.setCorpName(pro.getCorpName());
			budget.setCorpId(pro.getCorpId());
			budget.setDeptId(pro.getDeptId());
			budget.setDeptName(pro.getDeptName());
			budget.setProjectTypeDes(pro.getProjectTypeDes());
			budget.setContributionModeDes(pro.getContributionModeDes());
		}
		/*
		ProjectApplication pa=projectApplicationDao.queryById(projId);
		if(pa!=null && budget!=null){
			budget.setPaNo(pa.getPaNo());	//受理单号
		}*/
		return budget;
		
	}
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void batInsertCostSum(JSONArray jsonArr,BudgetReq req){
		List<ProjectCostSummary> list=new ArrayList<ProjectCostSummary>();
		BigDecimal amount=new BigDecimal(0.00);		
		ProjCostSummaryReq pros=new ProjCostSummaryReq();
		Budget budget=budgetDao.queryById(req.getProjId(),BudgetTypeEnum.NOT_ADJUST.getValue());		
		if(null==budget||null==budget.getBudgetId()){
			Project proj=projectDao.get(req.getProjId());
			budget=new Budget();
			budget.setBudgetId(IDUtil.getUniqueId(Constants.MODULE_CODE_PROJECTSTART));
			budget.setProjId(req.getProjId());
			budget.setProjNo(proj.getProjNo());
			budget.setBudgetTypeId(BudgetTypeEnum.NOT_ADJUST.getValue());//更新预算总表预算类型-正常预算
			budgetDao.save(budget);
		}
		
		for (int i = 0; i < jsonArr.size(); i++) {
			JSONObject jo = jsonArr.getJSONObject(i);
			if(StringUtils.isBlank(jo.getString("costName"))){
				continue;
			}
			ProjectCostSummary pro=JsonUtils.jsonToBean(jo, ProjectCostSummary.class);
			pro.setPcsId(IDUtil.getUniqueId(Constants.MODULE_CODE_COST));
			pro.setCostType(req.getCostType());     //费用类型
			pro.setProjId(req.getProjId());
			pro.setBudgetId(budget.getBudgetId());
			pro.setIsAdjust(req.getIsAdjust());	  //是否调整
			if(pro.getCostName().equals("建筑工程造价")){
				amount=pro.getAmount();
			}
			list.add(pro);
			
		}
		pros.setCostType(req.getCostType());
		pros.setBudgetId(budget.getBudgetId());
		pros.setIsAdjust("0");
		List<ProjectCostSummary> li =projectCostSummaryDao.queryCostSummaryList(pros);
		if(null!=li&&li.size()>0){
			projectCostSummaryDao.batchDeleteObjects(li);
		}
		projectCostSummaryDao.batchInsertObjects(list);
		BigDecimal total=null==budget.getBudgetTotalCost()?new BigDecimal(0):budget.getBudgetTotalCost();
		//民用户内
		if(req.getCostType().equals(BudgetCostTypeEnum.CIVIL_INDOOR.getValue())){
			BigDecimal val=null==budget.getHomeInstallCost()?new BigDecimal(0):budget.getHomeInstallCost();
			budget.setBudgetTotalCost(total.subtract(val).add(amount));
			budget.setHomeInstallCost(amount);
			budgetDao.update(budget);
			return;
	
		}
		//民用庭院
		if(req.getCostType().equals(BudgetCostTypeEnum.CIVIL_COURTYARD.getValue())){
			BigDecimal val=null==budget.getYardInstallCost()?new BigDecimal(0):budget.getYardInstallCost();
			budget.setBudgetTotalCost(total.subtract(val).add(amount));
			budget.setYardInstallCost(amount);
			budgetDao.update(budget);
			return;
			
		}
		//民用土建
		if(req.getCostType().equals(BudgetCostTypeEnum.CIVIL_ENGINEERING.getValue())){
			BigDecimal val=null==budget.getCivilCost()?new BigDecimal(0):budget.getCivilCost();
			budget.setBudgetTotalCost(total.subtract(val).add(amount));
			budget.setCivilCost(amount);
			budgetDao.update(budget);
			return;
	
		}
		//锅炉仪表
		if(req.getCostType().equals(BudgetCostTypeEnum.BOILER_INSTRUMENT.getValue())){
			BigDecimal val=null==budget.getBoilerMeter()?new BigDecimal(0):budget.getBoilerMeter();
			budget.setBudgetTotalCost(total.subtract(val).add(amount));
			budget.setBoilerMeter(amount);
			budgetDao.update(budget);
			return;
	
		}
		//工艺
		if(req.getCostType().equals(BudgetCostTypeEnum.TECHNOLOGY.getValue())){
			BigDecimal val=null==budget.getTechnology()?new BigDecimal(0):budget.getTechnology();
			budget.setBudgetTotalCost(total.subtract(val).add(amount));
			budget.setTechnology(amount);
			budgetDao.update(budget);
			return;
	
		}
		//庭院土方
		if(req.getCostType().equals(BudgetCostTypeEnum.COURTYARD_EARTHWORK.getValue())){
			BigDecimal val=null==budget.getYardEarthwork()?new BigDecimal(0):budget.getYardEarthwork();
			budget.setBudgetTotalCost(total.subtract(val).add(amount));
			budget.setYardEarthwork(amount);
			budgetDao.update(budget);
			return;
	
		}
		//其他
		/*if(req.getCostType().equals(BudgetCostTypeEnum.OTHER.getValue())){
			BigDecimal val=null==budget.getOtherCost1()?new BigDecimal(0):budget.getOtherCost1();
			budget.setBudgetTotalCost(total.subtract(val).add(amount));
			budget.setOtherCost1(amount);
			budgetDao.update(budget);
			return;
		}*/
	}
	@Override
	public Map<String,Object> queryCostSummary(ProjCostSummaryReq projCostSummaryReq) {
		return projectCostSummaryDao.queryCostSummary(projCostSummaryReq);
		
	}
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void updateBudge(Budget budget) {
		 budgetDao.updateNotNullProperties(budget, budget.getBudgetId());
		
		 if(budget.getIsUpdatePro()!=null&&budget.getIsUpdatePro().equals("false")){
			 
			 }else{
				 
			 Project pro= projectDao.get(budget.getProjId());
			 pro.setProjStatusId(WorkFlowUtil.workFlowStatus(StepEnum.BUDGET_RESULT_REGISTER.getValue()));
			 
			 /*//预算员为选择 不是取登录人
			 LoginInfo loginInfo = SessionUtil.getLoginInfo();			//获得登录信息
			 pro.setBudgeterId(loginInfo.getStaffId());                 //更新预算员id
			 pro.setBudgeter(loginInfo.getStaffName());					//更新预算员
	*/		 
			 pro.setBudgeter(budget.getBudgeter());
			 pro.setBudgeterId(budget.getBudgeterId());
			 
			 
			 pro.setBudgetTotalCost(budget.getBudgetTotalCost());       //更新总造价
			 pro.setBudgetDate(budgetDao.getDatabaseDate());            //更新预算时间
			 
			 projectDao.update(pro);
			 
			 //添加应收应付流水
			/* BigDecimal bd= budget.getBudgetTotalCost().multiply(new BigDecimal(0.045));
			 DecimalFormat df = new DecimalFormat("0.00");
			 String val=df.format(bd);
			 accrualsRecordService.insertAccrualsRecord(pro, CollectionTypeEnum.DESIGN_FEE.getValue(), Integer.parseInt(ARFlagEnum.RECEIVE_ACCOUNT.getValue()),new BigDecimal(val));*/
			//形成操作记录
			 String orId = IDUtil.getUniqueId(Constants.MODULE_CODE_COST);//生成唯一ID
			
			 operateRecordService.createOperateRecord(orId, pro.getProjId(), pro.getProjNo(),StepEnum.BUDGET_RESULT_REGISTER.getValue(), StepEnum.BUDGET_RESULT_REGISTER.getMessage(), "");
			 }
	}
	
	/**
	 * 根据工程id和预算类型id查询预算总表
	 * @author fuliwei
	 * @createTime 2016-7-14
	 * @param
	 * @return Budget
	 */
	@Override
	public Budget queryById(String projId, String budgetTypeId) {
		return budgetDao.queryById(projId, budgetTypeId);
	}
	/**
	 * 更新预算表(预算调整)
	 * @author fuliwei
	 * @createTime 2016-7-13
	 * @param budget
	 * @return String
	 */
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void updateBudgeAdjust(Budget budget) {
		
		budget.setBudgetTypeId(BudgetTypeEnum.ADJUSTED.getValue());//预算类型为调整
		budget.setBudgetAdjustDate(budgetDao.getDatabaseDate());//预算时间
		budget.setIsSignSuContrct(IsSignEnum.HAVE_NOT_SIGN.getValue());
		if(StringUtil.isBlank(budget.getBudgetId())){
			budget.setBudgetId(IDUtil.getUniqueId(Constants.MODULE_CODE_BUDGET));//生成预算ID
			budgetDao.save(budget);
		}else{
			budgetDao.updateNotNullProperties(budget, budget.getBudgetId());
		}
		//
		if(StringUtil.isNotBlank(budget.getMcType())){
			if(budget.getMcType().equals("0")){//变更
				
			}else{//签证
			    //修改签证的金额
				EngineeringVisa engineeringVisa = engineeringVisaDao.get(budget.getCmId());
				if(engineeringVisa!=null){
					//如果审核不通过
					if("2".equals(budget.getAuditResult())){
						ManageRecord manageRecord=new ManageRecord();
						manageRecord.setMrId(IDUtil.getUniqueId(Constants.MODULE_CODE_PROCESS));		//审核记录主键id
						manageRecord.setStepId(StepOutWorkflowEnum.ENGINEERING_AUDIT.getValue());	
						manageRecord.setMrResult(MrResultEnum.NOT_PASSED.getValue());
						LoginInfo loginInfo = SessionUtil.getLoginInfo();
						manageRecord.setMrCsr(loginInfo.getStaffId()); 			//审核人
						manageRecord.setMrTime(engineeringVisaDao.getDatabaseDate());
						manageRecord.setMrAopinion(budget.getAuditOpinion());//审核意见
						manageRecord.setBusinessOrderId(budget.getCmId());
						manageRecord.setProjNo(engineeringVisa.getProjNo());
						manageRecord.setFlag("0");//预算审核不通过
						manageRecord.setProjId(engineeringVisa.getProjId());
						manageRecordDao.saveOrUpdate(manageRecord);

						//将签证量审核记录置为无效
						List<String> stepIds=new ArrayList<>();
						stepIds.add(StepOutWorkflowEnum.ENGINEERING_QUANTY_AUDIT.getValue());
						manageRecordDao.backSetAuditRecordInValid(budget.getProjId(),stepIds);

						engineeringVisa.setEvState(ChangeStateEnum.HANDLED.getValue());//已处理过
						engineeringVisa.setAuditState(StageProjectApplicationEnum.RE_PUSH.getValue());//不通过
						//todo:签证预算调整不通过，重新调整推送
						Project pro= projectDao.get(budget.getProjId());
						//重新调整推送代办人：施工员
						Staff staff = new Staff();
						staff.setStaffId(StringUtil.isNotBlank(pro.getManagementQaeId())?pro.getManagementQaeId():"");
						staff.setStaffName(StringUtil.isNotBlank(pro.getManagementQae())?pro.getManagementQae():"");
						operateRecordService.cerateCurOperateRecord(pro,engineeringVisa.getAuditState(), WorkFlowTypeEnum.ASSIST_PROGRESS.getValue(), AssistProgressTypeEnum.ENGINEER_PROGRESS.getValue(),manageRecord.getBusinessOrderId(),staff,null,true);
						
					}
					
					engineeringVisa.setQuantitiesTotal(budget.getBudgetTotalCost());//签证
					engineeringVisaDao.update(engineeringVisa);
				}
			}
		}
		//判断是否更新工程状态	
		 if(budget.getIsUpdatePro()!=null&&budget.getIsUpdatePro().equals("false")){
			 
			 }else{//更新工程状态
				 
			 Project pro= projectDao.get(budget.getProjId());
			 pro.setProjStatusId(WorkFlowUtil.workFlowStatus(StepEnum.BUDGET_RESULT_REGISTER.getValue()));
			 
			 pro.setBudgeter(budget.getBudgeter());//预算员信息
			 pro.setBudgeterId(budget.getBudgeterId());
			 
			 pro.setBudgetTotalCost(budget.getBudgetTotalCost());       //更新总造价
			 pro.setBudgetDate(budgetDao.getDatabaseDate());            //更新预算时间
			 
			 projectDao.update(pro);
			 
			 //添加应收应付流水
			/* BigDecimal bd= budget.getBudgetTotalCost().multiply(new BigDecimal(0.045));
			 DecimalFormat df = new DecimalFormat("0.00");
			 String val=df.format(bd);
			 accrualsRecordService.insertAccrualsRecord(pro, CollectionTypeEnum.DESIGN_FEE.getValue(), Integer.parseInt(ARFlagEnum.RECEIVE_ACCOUNT.getValue()),new BigDecimal(val));*/
			//形成操作记录
			 String orId = IDUtil.getUniqueId(Constants.MODULE_CODE_COST);//生成唯一ID
			 operateRecordService.createOperateRecord(orId, pro.getProjId(), pro.getProjNo(),StepEnum.BUDGET_RESULT_REGISTER.getValue(), StepEnum.BUDGET_RESULT_REGISTER.getMessage(), "");
			 }
	}
	@Override
	public ProjectRate queryRateById(String id) {
		return projectRateDao.get(id);
	}
	
	/**
	 * 预算调整页面查询预算
	 * @author
	 * @createTime 
	 * @param
	 * @return
	 */
	@Override
	public Budget queryByType(String projId, String cmId, String mcType) {
		
		//查询调整预算
		Budget budget=budgetDao.queryByType(cmId, mcType);
		Project pro=projectDao.get(projId);
		if(budget==null){
			budget=new Budget();
		}
		if(pro !=null){
			budget.setProjId(pro.getProjId());
			budget.setProjName(pro.getProjName());//工程名称
			budget.setProjAddr(pro.getProjAddr());//工程地点
			budget.setProjScaleDes(pro.getProjScaleDes());//工程规模
			budget.setSurveyer(pro.getSurveyer());//勘察人
			budget.setDesigner(pro.getDesigner());//设计人
			budget.setProjNo(pro.getProjNo());
			LoginInfo loginInfo=SessionUtil.getLoginInfo();
		    //预算人信息,取工程表预算人
			if(mcType.equals("0")){
				budget.setBudgeter(pro.getBudgeter());//预算人
				budget.setBudgeterId(pro.getBudgeterId());//预算人ID		
			}else{
				budget.setBudgeter(pro.getBudgeterAudit());//分包预算人
				budget.setBudgeterId(pro.getBudgeterAuditId());//分包预算人ID	
			}
		}
		 return budget;
	}
	
	/**
	 * 总表导入
	 * @author
	 * @createTime 2016-11-18
	 * @param
	 * @return
	 */
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void batInsertTotalCostSum(JSONArray jsonArr, BudgetReq req) {
		List<BudgetTotalTable> list=new ArrayList<BudgetTotalTable>();
		BigDecimal amount=new BigDecimal(0.00);//合计
		BigDecimal inspectionCost=new BigDecimal(0.00);//监检费
		BigDecimal suCost=new BigDecimal(0.00);//监检费
		BigDecimal storeCost=new BigDecimal(0.00);//监检费
		String note="";
		BudgetTotalQueryReq btReq=new BudgetTotalQueryReq();
		//查找预算
		Budget budget=budgetDao.queryById(req.getProjId(), BudgetTypeEnum.NOT_ADJUST.getValue());
		
		
		if(budget==null||budget.getBudgetId()==null){
			budget=new Budget();
			Project proj=projectDao.get(req.getProjId());
			if(proj!=null){
				budget.setBudgetId(IDUtil.getUniqueId(Constants.MODULE_CODE_PROJECTSTART));//主键id
				budget.setProjId(req.getProjId());
				budget.setProjNo(proj.getProjNo());
				budget.setBudgetTypeId(BudgetTypeEnum.NOT_ADJUST.getValue());//更新预算总表预算类型-正常预算
				budgetDao.save(budget);
			}
		}
		for (int i = 0; i < jsonArr.size(); i++) {
			JSONObject jo = jsonArr.getJSONObject(i);
			if(StringUtils.isBlank(jo.getString("subitemName"))){
				BudgetTotalTable bt=JsonUtils.jsonToBean(jo, BudgetTotalTable.class);
				bt.setBtId(IDUtil.getUniqueId(Constants.MODULE_CODE_COST));
				bt.setProjId(req.getProjId());
				bt.setProjNo(budget.getProjNo());
				bt.setBudgetId(budget.getBudgetId());
				bt.setSubitemName("分项合计");
				bt.setBudgetType(BudgetTypeEnum.NOT_ADJUST.getValue());
				BigDecimal bd=new BigDecimal(jo.getString("amount")); 
				bt.setAmount(bd);
				list.add(bt);
				continue;
			}
			if(StringUtils.isNotBlank(jo.getString("amount"))){
				BudgetTotalTable bt=JsonUtils.jsonToBean(jo, BudgetTotalTable.class);
				bt.setBtId(IDUtil.getUniqueId(Constants.MODULE_CODE_COST));
				bt.setProjId(req.getProjId());
				bt.setProjNo(budget.getProjNo());
				bt.setBudgetId(budget.getBudgetId());
				bt.setBudgetType(BudgetTypeEnum.NOT_ADJUST.getValue());
				
				if(bt.getSubitemName().equals("监检费")){
					inspectionCost=bt.getAmount();
				}
				if(bt.getSubitemName().equals("监理费")){
					suCost=bt.getAmount();
				}
				if(bt.getSubitemName().equals("农民工工资储备金")){
					storeCost=bt.getAmount();
				}
				if(bt.getSubitemName().equals("合计")){
					amount=bt.getAmount();
				}
				
				
				list.add(bt);
			}
			//备注拼写
			if(StringUtils.isBlank(jo.getString("amount"))){
				BudgetTotalTable bt=JsonUtils.jsonToBean(jo, BudgetTotalTable.class);
				bt.setBtId(IDUtil.getUniqueId(Constants.MODULE_CODE_COST));
				bt.setProjId(req.getProjId());
				bt.setProjNo(budget.getProjNo());
				bt.setBudgetId(budget.getBudgetId());
				note=note+bt.getSubitemName();
			}
			
		}
		
		
		btReq.setProjId(req.getProjId());
		btReq.setBudgetId(budget.getBudgetId());
		btReq.setBudgetType(BudgetTypeEnum.NOT_ADJUST.getValue());
		
		List<BudgetTotalTable> li =budgetTotalTableDao.queryBudgetTotalTableList(btReq);
		//删除原来的总造价表
		if(null!=li&&li.size()>0){
			budgetTotalTableDao.batchDeleteObjects(li);
		}
		budgetTotalTableDao.batchInsertObjects(list);
		//更新预算表
		budget.setBudgetTotalCost(amount);
		budget.setInspectionCost(inspectionCost);
		budget.setSuCost(suCost);
		budget.setStoreCost(storeCost);
		budget.setRemark(note);
		budgetDao.update(budget);
	}
	@Override
	public Map<String, Object> queryBudgetTotal(BudgetTotalQueryReq budgetTotalQueryReq) {
		return budgetTotalTableDao.queryBudgetTotal(budgetTotalQueryReq);
	}
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void updateBudget(Budget budget) {
		 Project pro= projectDao.get(budget.getProjId());
		 pro.setProjStatusId(WorkFlowUtil.workFlowStatus(StepEnum.BUDGET_RESULT_REGISTER.getValue()));
	 
		 pro.setBudgeter(budget.getBudgeter());//预算员
		 pro.setBudgeterId(budget.getBudgeterId());//预算员ID
		 
		 pro.setBudgetTotalCost(budget.getBudgetTotalCost());       //更新总造价
		 pro.setBudgetDate(budgetDao.getDatabaseDate());            //更新预算时间
		
		 
		//形成操作记录
		 String orId = IDUtil.getUniqueId(Constants.MODULE_CODE_COST);//生成唯一ID
		 String todoer=operateRecordService.createOperateRecord(orId, pro.getProjId(), pro.getProjNo(),StepEnum.BUDGET_RESULT_REGISTER.getValue(), StepEnum.BUDGET_RESULT_REGISTER.getMessage(), "");
		 pro.setToDoer(todoer);//待办人
		 projectDao.update(pro);
	}
	
	/**
	 * 结算员列表查询
	 * @param designerQueryReq
	 * @createTime 2016-07-1
	 * @return
	 */
	@Override
	public Map<String, Object> queryBudgeter(DesignerQueryReq designerQueryReq) throws ParseException {
		return budgetDao.queryBudgeter(designerQueryReq);
	}
	
	
	/**
	 * 更新工程信息（弃用）
	 * @author fuliwei
	 * @createTime 2017-1-4
	 * @return String 
	 */
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public String updateProject(BudgetReq req) {
		
		Project pro=projectDao.get(req.getProjId());
		String staffName=req.getBudgeter();
		pro.setBudgeter(staffName);
		List<Staff> list= designInfoDao.findByStaffName(staffName);
 		if(list.size()>0){
 			Staff staff=list.get(0);
 			pro.setBudgeterId(staff.getStaffId());  			 //更新预算员ID
 		}
		projectDao.update(pro);
		
		return Constants.OPERATE_RESULT_SUCCESS;
	}
	
	
	/**
	 *预算结果登记查询审核记录
	 * @param projId
	 * @param StepId
	 * @param mrResult
	 * @return String
	 */
	@Override
	public String queryManageRecord(String projId, String StepId, String mrResult,String mrLevel) {
		return budgetDao.queryManageRecord(projId, StepId, mrResult,mrLevel);
	}
	
	/**
	 * 查询变更预算列表
	 * @author fuliwei
	 * @createTime 2017-1-23
	 * @param req
	 * @return Map<String,Object>
	 */
	@Override
	public Map<String, Object> queryAdjustBudget(BudgetReq req) throws ParseException {
		
		Map<String, Object> map=budgetDao.queryAdjustBudget(req);
		List<Budget> list=(List) map.get("data");
		
		if(list!=null  && list.size()>0){
			for(int i=0;i<list.size();i++){
				
				Project project=projectDao.get(list.get(i).getProjId());
				if(project!=null){
					list.get(i).setProjectType(project.getProjectType());
				}
				
				Budget bt=list.get(i);
				Project pro=projectDao.get(bt.getProjId());
				if(null!=pro){
					bt.setProjName(pro.getProjName());
				}
				SupplementalContract sc=supplementalContractDao.viewSupplementalContract(bt.getBudgetId());
				if(sc!=null){
					bt.setIsAudit(sc.getIsAudit());
				}
			}
		}
		
		return map;
	}
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void update(DesignDispatchDto designDispatchReq) {
		//更新工程信息
		Project pro=projectDao.get(designDispatchReq.getProjId());//根据Id查询工程
		pro.setBudgeterAudit(designDispatchReq.getSurveyer());		 //施工预算审核员
		pro.setBudgeterAuditId(designDispatchReq.getSurveyerId());	 //施工预算审核员Id
		
		//String statusId=workFlowService.queryProjStatusId(pro.getCorpId(),pro.getContributionMode(),WorkFlowActionEnum.QUALITIES_DISPATCH.getActionCode(), true);
		String statusId=workFlowService.queryProjStatusId(pro.getCorpId(), pro.getProjectType(),pro.getContributionMode(), WorkFlowActionEnum.QUALITIES_DISPATCH.getActionCode(), true);
		pro.setProjStatusId(statusId);          	   //更新工程状态
		pro.setToDoer(designDispatchReq.getSurveyer());//待办人
		//更新工程
		projectDao.update(pro);
		
		//形成操作记录
		String orId = IDUtil.getUniqueId(Constants.MODULE_CODE_SUBCONTRACT);
		//String toDoer=operateRecordService.createOperateRecord(orId, pro.getProjId(), pro.getProjNo(), StepEnum.QUALITIES_DISPATCH.getValue(), StepEnum.QUALITIES_DISPATCH.getMessage(), "");
		operateRecordService.createNextOperateRecord(orId, pro.getProjId(), pro.getProjNo(), StepEnum.QUALITIES_DISPATCH.getValue(), StepEnum.QUALITIES_DISPATCH.getMessage(), "", designDispatchReq.getSurveyerId(), designDispatchReq.getSurveyer());
		
		
		
		 /*//生成审核通知 作废
		 Notice notice=noticeDao.findByProjIdAndType(pro.getProjId(), NoticeAuditTypeEnum.CON_BUDGET_AUDIT.getValue());//施工预算审核
		 if(notice!=null){
			 notice.setNoticeState(NoticeStateEnum.EFFECTIVE.getValue());//将通知置为有效;
			 notice.setGrade(NoticeMenuEnum.PLAN_AUDIT1.getGrade());	 //一级审核
			 notice.setNoticeContent(NoticeMenuEnum.PLAN_AUDIT1.getMessage());//待施工预算审核一级
			 notice.setNoticeTime(noticeDao.getDatabaseDate());
		 }else{
			 notice=new Notice();
			 notice.setNoticeId(IDUtil.getUniqueId(Constants.MODULE_CODE_DESIGN));
			 notice.setNoticeState(NoticeStateEnum.EFFECTIVE.getValue());		//将通知置为有效;
			 notice.setGrade(NoticeMenuEnum.CON_BUDGET_AUDIT1.getGrade());	 		//一级审核
			 notice.setAuditType(NoticeMenuEnum.CON_BUDGET_AUDIT1.getType());
			 notice.setNoticeTime(noticeDao.getDatabaseDate());
			 notice.setNoticeTitle("审核通知");
			 notice.setNoticeContent(NoticeMenuEnum.CON_BUDGET_AUDIT1.getMessage());//施工预算审核审核一级
			 notice.setNoticeType("2");
			 notice.setProjId(pro.getProjId());
			 notice.setCorpId(pro.getCorpId());
		 }
		 
		 noticeDao.saveOrUpdate(notice);*/
	}
	/**
	 * 预算派工
	 * @author liaoyq
	 * @createTime 2017-8-11
	 */
	@Override
	@Transactional(readOnly = false,propagation = Propagation.REQUIRED)
	public void updateProject(DesignDispatchDto designDispatchReq) {
		//更新工程信息
		Project pro=projectDao.get(designDispatchReq.getProjId());//根据Id查询工程
		pro.setBudgeter(designDispatchReq.getSurveyer());		 // 预算审核员
		pro.setBudgeterId(designDispatchReq.getSurveyerId());	 //预算审核员Id
		pro.setToDoer(designDispatchReq.getSurveyer());
		//String statusId = workFlowService.queryProjStatusId(pro.getCorpId(),pro.getContributionMode(),WorkFlowActionEnum.BUDGET_DISPATCH.getActionCode(), true);
		String statusId=workFlowService.queryProjStatusId(pro.getCorpId(), pro.getProjectType(),pro.getContributionMode(), WorkFlowActionEnum.BUDGET_DISPATCH.getActionCode(), true);
		pro.setProjStatusId(statusId);          				 //更新工程状态
		
		//更新工程
		projectDao.update(pro);
		//形成操作记录
		String orId = IDUtil.getUniqueId(Constants.MODULE_CODE_BUDGET);
		//operateRecordService.createOperateRecord(orId, pro.getProjId(), pro.getProjNo(), StepEnum.BUDGET_DISPATCH.getValue(), StepEnum.BUDGET_DISPATCH.getMessage(), "");
		operateRecordService.createNextOperateRecord(orId, pro.getProjId(), pro.getProjNo(), StepEnum.BUDGET_DISPATCH.getValue(), StepEnum.BUDGET_DISPATCH.getMessage(), "", designDispatchReq.getSurveyerId(), designDispatchReq.getSurveyer());
	}
	/**
	 * 保存或更新预算记录
	 * @author liaoyq
	 * @throws IOException 
	 * @createTime 2017-8-14
	 */
	@Override
	@Transactional(readOnly = false,propagation = Propagation.REQUIRED)
	public String updateBudgetFile(HttpServletRequest request,
			UploadResult uploadResult, MultipartFile[] files) throws Exception {
		JSONObject jo = new JSONObject();
		Budget budget = JSON.parseObject(uploadResult.getResult(), Budget.class);
		JSONArray sign = (JSONArray) jo.parseObject(uploadResult.getResult()).get("sign");
		//判断签字信息，保存签字
		if(sign!=null){
			List<Signature> signs = JSONObject.parseArray(sign.toJSONString(), Signature.class);
			budget.setSign(signs);
		}
		String name=budget.getDrawName();//附件名称
		if(files!=null){
			budget.setDrawName(files[0].getOriginalFilename());//附件原始名称
		}
		//保存到预算表
		String budgetId = this.budgetSave(budget);
		if(StringUtil.isBlank(name)){
			List<AccessoryList> accs =accessoryDao.queryAccessoryByBus(budget.getBudgetId(), "");
			if(accs!=null&&accs.size()>0){
				if(files!=null){
					FileUtil.deleteFile(Constants.DISK_PATH+accs.get(0).getAlPath());//删除以前的附件
				}
				accessoryDao.delete(accs.get(0));//删除附件
			}
			if(files!=null){
				AccessoryList acc = new AccessoryList();
				String path=FileUtil.uploadFile(request, uploadResult.getAlPath(), files);//路径
				String fileName = files[0].getOriginalFilename();               //文件名全名（例：文件名.png）
				//String filePath= Constants.FIRST_DISK_PATH + path + fileName;
				String filePath= Constants.FIRST_DISK_PATH + path ;
				String name1 = fileName.substring(0,fileName.lastIndexOf("."));//文件去掉格式后的名（从第0位截取，到文件格式(例：“.png“）前的点结束）
				String fileType=fileName.substring(fileName.lastIndexOf(".")+1,fileName.length());//文件格式（例：“.png”）
				LoginInfo loginInfo = SessionUtil.getLoginInfo();
				acc.setAlId(IDUtil.getUniqueId(Constants.FILE_CODE_ACCESS));   //生成附件Id
				acc.setProjId(budget.getProjId());                                 //项目Id
				acc.setProjNo(budget.getProjNo());                                 //项目编号
//				acc.setProjLtypeId(co.getProjLtypeId());                       //工程大类
				acc.setAlTypeId(fileType);                                     //附件格式（例：“.png"）
				acc.setStepId(budget.getStepId());                                 //步骤Id
				acc.setAlName(name1);                                          //附件名称
				acc.setAlPath(filePath);                                       //附件路径
				acc.setAlOperateCsrId(loginInfo.getStaffId());                 //操作人Id
				acc.setAlOperateCsr(loginInfo.getStaffName());                 //操作人姓名
				acc.setAlOperateTime(accessoryDao.getDatabaseDate());          //生成操作时间
//				acc.setSourceType(AccessorySourceEnum.CHANGE_FILE.getValue()); //附件来源类型
				acc.setBusRecordId(budget.getBudgetId());                              //业务单Id
				accessoryDao.save(acc);
			}
		}
		return budgetId;
	}
	/**
	 * 保存预算记录信息
	 * @author liaoyq
	 * @createTime 2017-8-14
	 * @param budget 预算信息
	 * @return 预算ID
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public String budgetSave(Budget budget) throws Exception {
		boolean flag = false;
		if(StringUtils.isBlank(budget.getBudgetId())){
			flag = true;
			budget.setBudgetId(IDUtil.getUniqueId(Constants.MODULE_CODE_BUDGET));
		}
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		//预算记录人员
		budget.setBudgeter(loginInfo.getStaffName());
		budget.setBudgeterId(loginInfo.getStaffId());
		budget.setBudgetDate(budgetDao.getDatabaseDate());
		budget.setBudgetTypeId(BudgetTypeEnum.NOT_ADJUST.getValue());//预算类型
		budgetDao.saveOrUpdate(budget);
		
		//保存签字
		signatureService.saveOrUpdateSign(budget.getMenuId(),budget.getSign(), budget.getProjId(), budget.getBudgetId(), budget.getClass().getName(), flag);
		//更新工程信息
		Project pro = projectDao.get(budget.getProjId());
		if(pro!=null){
			 pro.setBudgetTotalCost(budget.getBudgetTotalCost());       //更新总造价
			 pro.setBudgeter(budget.getBudgeter());  //设定预算员
			 pro.setBudgeterId(budget.getBudgeterId());  //设定预算员ID
			 pro.setBudgetDate(budgetDao.getDatabaseDate());            //更新预算时间
			String statusId = "";
			if(budgetDao.getbudgetType(pro.getProjId())){
				statusId =workFlowService.queryProjStatusId(pro.getCorpId(), pro.getProjectType(),pro.getContributionMode(), WorkFlowActionEnum.BUDGET_AUDIT.getActionCode(), true);
			}else{
				statusId=workFlowService.queryProjStatusId(pro.getCorpId(), pro.getProjectType(),pro.getContributionMode(), WorkFlowActionEnum.BUDGET_RESULT_REGISTER.getActionCode(), true);

			}
			//第三方的
			if(BudgetMethodEnum.THREE_BUDGET.getValue().equals(pro.getBudgetMethod())){
				//不需预算审核
				statusId =workFlowService.queryProjStatusId(pro.getCorpId(), pro.getProjectType(),pro.getContributionMode(), WorkFlowActionEnum.BUDGET_AUDIT.getActionCode(), true);
				
				String orId = IDUtil.getUniqueId(Constants.MODULE_CODE_BUDGET);//生成唯一ID
				String toder=operateRecordService.createOperateRecordNext(orId, pro.getProjId(), pro.getProjNo(),StepEnum.BUDGET_RESULT_REGISTER.getValue(), StepEnum.BUDGET_RESULT_REGISTER.getMessage(), "",WorkFlowActionEnum.BUDGET_AUDIT.getActionCode());
				pro.setProjStatusId(statusId);
				pro.setToDoer(toder);
				 projectDao.update(pro);
				return budget.getBudgetId();
			}
			 //工程状态
			 pro.setProjStatusId(statusId);
		}
		//形成操作记录
		 String orId = IDUtil.getUniqueId(Constants.MODULE_CODE_BUDGET);//生成唯一ID
		 String toder=operateRecordService.createOperateRecord(orId, pro.getProjId(), pro.getProjNo(),StepEnum.BUDGET_RESULT_REGISTER.getValue(), StepEnum.BUDGET_RESULT_REGISTER.getMessage(), "");
		 pro.setToDoer(toder);
		 projectDao.update(pro);
		return budget.getBudgetId();
	}
	/**
	 * @throws ParseException 
	 * 
	 */
	@Override
	public Map<String, Object> queryAuditProject(
			ProjectQueryReq projectQueryReq, String auditLevel, String stepId) throws ParseException {
		projectQueryReq.setStepId(stepId);
		Map<String, Object> result = projService.queryProjectByTime(projectQueryReq);
		List<Project> data = (List<Project>) result.get("data");
		LoginInfo  loginInfo = SessionUtil.getLoginInfo();
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
				
				DocType docType = docTypeService.findByStepId(stepId,data.get(i).getCorpId(),data.get(i).getProjectType(),data.get(i).getContributionMode());
				if(docType!=null && StringUtils.isNotBlank(docType.getGrade())){
					auditLevel=docType.getGrade();
				}else{
					auditLevel="0";
				}
				data.get(i).setLevel(auditLevel);// 设置审核总级数（预算信息2级审核）
				
				Map<String, String> levelBtn = new HashMap<String, String>();

				for (int n = 1; n < Integer.parseInt(auditLevel) + 1; n++) {
					if (n == 1) {
						levelBtn.put("level" + n, "2");// 待审
					} else {
						levelBtn.put("level" + n, "-1");// 未审
					}
					//根据员工ID查找不能审核那一级别
					List<DataFilerSetUpDto> FilterData = Constants.getDataFilterMapByKey(data.get(i).getProjectType()+"_"+loginInfo.getStaffId()+"_"+n+"_"+projectQueryReq.getMenuId());
					if(FilterData!=null && FilterData.size() > 0){
						levelBtn.put("level"+n, "-1");
					}
				}
				List<ManageRecord> mrls = manageRecordDao.findByStepIdProjIdIsPass(data.get(i).getProjId(), stepId,
						MrResultEnum.PASSED.getValue());
				if (mrls != null && mrls.size() > 0) {
					// 遍历循环，获取审核是否通过
					for (ManageRecord mr : mrls) {
						levelBtn.put("level" + mr.getMrAuditLevel(), mr.getMrResult());
					}
					if (mrls.size() < Integer.parseInt(auditLevel)) {
						levelBtn.put("level" + (mrls.size() + 1), "2");
					}
				}
				data.get(i).setMrAuditLevel(JSONSerializer.toJSON(levelBtn).toString());
			}
			result.put("data", data);
		}
		return result;
	}
	/**
	 * 预算方式确认
	 */
	@Override
	public void saveProBudgetMethod(Budget budget) {
		
	}

	/**
	 * 预算列表查询
	 * @createTime 2018-12-18
	 * @author wangang
	 */
	@Override
	public Map<String, Object> queryBudget(BudgetReq budgetReq) throws ParseException {
		return budgetDao.queryBudget(budgetReq);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public String signProjectBudgetPrint(String budgetId) {
		Budget budget=budgetDao.get(budgetId);
		if(budget!=null){
			//标记已打印  状态1代表已打印
			budget.setIsPrint("1");
			budgetDao.update(budget);
		}
		return Constants.OPERATE_RESULT_SUCCESS;
	}




	@Override
	public boolean rollBackContainsBudget(String projId, String rollBackReason) {

		Budget budget=budgetDao.queryBudgeByprojId(projId);
		if (budget==null){ return false;}

		//备份原信息记录
		Map<String,Object> criteriaMap=new HashMap<>();
		String t_projId = Annotations.getFieldGetMethodColumnAnNameVal(Budget.class, "projId");
		criteriaMap.put(t_projId,projId);
		String tableName = Annotations.getClassTableAnNameVal(Budget.class);
		String origData = abandonedRecordService.getThisTableOrigData(tableName, criteriaMap);
		abandonedRecordService.saveAbandonedRecord(budget.getBudgetId(),projId,StepEnum.BUDGET_RESULT_REGISTER.getValue(),rollBackReason,origData);


		//更新预算记录
		budget.setBudgetDate(null);
		budget.setRemark(budget.getRemark()+" 备注：原工程已在 "+budget.getBudgetDate()+" 完成预算。");
		budgetDao.saveOrUpdate(budget);



		return true;
	}

}
