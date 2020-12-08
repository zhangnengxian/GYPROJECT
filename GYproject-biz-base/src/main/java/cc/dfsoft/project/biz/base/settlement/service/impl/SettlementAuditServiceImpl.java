package cc.dfsoft.project.biz.base.settlement.service.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cc.dfsoft.project.biz.base.baseinfo.entity.DocType;
import cc.dfsoft.project.biz.base.baseinfo.service.DocTypeService;
import cc.dfsoft.project.biz.base.baseinfo.service.WorkFlowService;
import cc.dfsoft.project.biz.base.complete.dao.CompletionTransferDao;
import cc.dfsoft.project.biz.base.contract.dao.ContractDao;
import cc.dfsoft.project.biz.base.contract.entity.Contract;
import cc.dfsoft.project.biz.base.contract.enums.ContractIsPrintEnum;
import cc.dfsoft.project.biz.base.contract.service.ContractService;
import cc.dfsoft.project.biz.base.design.dao.DesignInfoDao;
import cc.dfsoft.project.biz.base.design.dto.DesignerQueryReq;
import cc.dfsoft.project.biz.base.design.entity.DesignInfo;
import cc.dfsoft.project.biz.base.plan.dao.ConstructionPlanDao;
import cc.dfsoft.project.biz.base.plan.entity.ConstructionPlan;
import cc.dfsoft.project.biz.base.project.dao.ProjectDao;
import cc.dfsoft.project.biz.base.project.entity.ManageRecord;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.project.enums.StepEnum;
import cc.dfsoft.project.biz.base.project.service.ManageRecordService;
import cc.dfsoft.project.biz.base.project.service.OperateRecordService;
import cc.dfsoft.project.biz.base.project.service.SignatureService;
import cc.dfsoft.project.biz.base.settlement.dao.SettlementDeclarationDao;
import cc.dfsoft.project.biz.base.settlement.dto.SettlementDeclarationReq;
import cc.dfsoft.project.biz.base.settlement.entity.SettlementDeclaration;
import cc.dfsoft.project.biz.base.settlement.enums.SettlementAuditResultEnum;
import cc.dfsoft.project.biz.base.settlement.service.SettlementAuditService;
import cc.dfsoft.project.biz.base.subpackage.dao.SubContractDao;
import cc.dfsoft.project.biz.base.subpackage.dao.SubQuantitiesDao;
import cc.dfsoft.project.biz.base.subpackage.dao.SubSupplyContractDao;
import cc.dfsoft.project.biz.base.subpackage.dto.SubQuantitiesDto;
import cc.dfsoft.project.biz.base.subpackage.entity.SubBudget;
import cc.dfsoft.project.biz.base.subpackage.entity.SubContract;
import cc.dfsoft.project.biz.base.subpackage.entity.SubQuantities;
import cc.dfsoft.project.biz.base.subpackage.entity.SubSupplyContract;
import cc.dfsoft.project.biz.base.subpackage.enums.AuditStatusEnum;
import cc.dfsoft.project.biz.ifs.finance.service.IFinanceInfoService;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.enums.WorkFlowActionEnum;
import cc.dfsoft.uexpress.common.util.IDUtil;
import cc.dfsoft.uexpress.common.util.StringUtil;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class SettlementAuditServiceImpl implements SettlementAuditService {

	@Resource
	SettlementDeclarationDao settlementDeclarationDao;
	
	/**业务操作记录服务接口*/
	@Resource
	OperateRecordService operateRecordService;

	/**工程dao*/
	@Resource
	ProjectDao projectDao;
	
	@Resource
	ContractService contractService;
	
	@Resource
	SubQuantitiesDao subQuantitiesDao;
	
	@Resource
	CompletionTransferDao completionTransferDao;
	
	/**施工计划Dao*/
	@Resource
	ConstructionPlanDao constructionPlanDao;
	
	/**分包合同Dao*/
	@Resource
	SubContractDao  subContractDao;
	
	@Resource
	WorkFlowService workFlowService;
	
	@Resource
	SignatureService signatureService;
	/**审核记录service*/
	@Resource
	ManageRecordService manageRecordService;
	@Resource
	DocTypeService docTypeService;
	@Resource
	SubSupplyContractDao subSupplyContractDao;
	/**合同Dao*/
	@Resource
	ContractDao contractDao;
	@Resource
	DesignInfoDao designInfoDao;
	@Resource
	IFinanceInfoService financeInfoService;
	
	@Override
	public SettlementDeclaration auditStartDetail(String projId) throws ParseException {
		SettlementDeclaration settlementDeclaration = settlementDeclarationDao.findByProjId(projId);
		//没有初审主材费，则默认取报审主材费
		if(settlementDeclaration!=null && settlementDeclaration.getMaterialsCost()!=null && settlementDeclaration.getFirstMaterialsCost()==null){
			settlementDeclaration.setFirstMaterialsCost(settlementDeclaration.getMaterialsCost());
		}
		ConstructionPlan cp=constructionPlanDao.viewPlanById(projId);			
		if(cp!=null){
			//result.setCmoName(cp.getManagementOffice());
			settlementDeclaration.setSuName(cp.getSuName());
			settlementDeclaration.setCuName(cp.getCuName());
			settlementDeclaration.setCuPhone(cp.getCuPhone());
		}
		SubContract sc=subContractDao.findSubContractByprojId(projId);
		if(null != sc){
			settlementDeclaration.setScAmount(sc.getScAmount());
			settlementDeclaration.setScNo(sc.getScNo());
		}
		SubSupplyContract ssc = subSupplyContractDao.findByProjId(projId);
		if(ssc != null){
			settlementDeclaration.setSscNo(ssc.getSscNo());					//分包协议编号
		}
		//图纸编号
		DesignInfo designInfo = designInfoDao.queryInfoByProjId(projId);
		if(designInfo!=null && StringUtils.isNotBlank(designInfo.getDesignDrawingNo())){
			settlementDeclaration.setDrawingNo(designInfo.getDesignDrawingNo());
		}
		//合同编号
		Contract contract=contractDao.viewContractByprojId(projId);
		if(contract !=null ){
			settlementDeclaration.setConNo(contract.getConNo());				   //合同编号
			settlementDeclaration.setContractAmount(contract.getContractAmount());//合同金额
		}
		Project pro = projectDao.get(projId);
		if(pro!=null){
			settlementDeclaration.setProjId(projId);
			settlementDeclaration.setProjName(pro.getProjName());
			settlementDeclaration.setProjAddr(pro.getProjAddr());
			settlementDeclaration.setProjNo(pro.getProjNo());
			settlementDeclaration.setProjScaleDes(pro.getProjScaleDes());
			settlementDeclaration.setProjTypeDesc(pro.getProjectTypeDes());
			settlementDeclaration.setProjContributionModeDes(pro.getContributionModeDes());
			settlementDeclaration.setCorpId(pro.getCorpId());
			settlementDeclaration.setCorpName(pro.getCorpName());
			settlementDeclaration.setDeptId(pro.getDeptId());
			settlementDeclaration.setDeptName(pro.getDeptName());
		}
		
		LoginInfo login=SessionUtil.getLoginInfo();
		settlementDeclaration.setFirstAuditer(login.getStaffName());
		settlementDeclaration.setFirstAuditerPhone(login.getMobile());
		settlementDeclaration.setFirstAuditDate(settlementDeclarationDao.getDatabaseDate());
		return settlementDeclaration;
	}

	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void saveAuditStart(SettlementDeclaration settlementDeclaration) throws Exception {
		
		if(StringUtils.isBlank(settlementDeclaration.getSdId())){
			settlementDeclaration.setSdId(IDUtil.getUniqueId(Constants.MODULE_CODE_SETTLEMENT));
		}
		//获取初审人信息
		LoginInfo loginInfo=SessionUtil.getLoginInfo();
		settlementDeclaration.setFirstAuditerId(loginInfo.getStaffId());
		
		settlementDeclaration.setIsPrint(ContractIsPrintEnum.HAVE_NOT_PRINT.getValue());	   //未打印
		settlementDeclarationDao.saveOrUpdate(settlementDeclaration);
		signatureService.saveOrUpdateSign("menuId+menuNane+24",settlementDeclaration.getSign(), settlementDeclaration.getProjId(), settlementDeclaration.getSdId(), settlementDeclaration.getClass().getName(),false);
		//更新工程信息
		Project pro=projectDao.get(settlementDeclaration.getProjId());         //通过工程id查找Project
		//生成工程状态
		String statusId=workFlowService.queryProjStatusId(pro.getCorpId(),pro.getProjectType(),pro.getContributionMode(),WorkFlowActionEnum.SETTLEMENT_REPORT_START.getActionCode(), true);
		
		pro.setProjStatusId(statusId); 								//设置工程状态
		projectDao.saveOrUpdate(pro);
		
		//形成操作记录
		String orId = IDUtil.getUniqueId(Constants.MODULE_CODE_SETTLEMENT);
		operateRecordService.createOperateRecord(orId, settlementDeclaration.getProjId(), pro.getProjNo(), StepEnum.SETTLEMENT_REPORT_START.getValue(), StepEnum.SETTLEMENT_REPORT_START.getMessage(), "");
	}

	@Override
	public Map<String, Object> querySettlementDeclaration(SettlementDeclarationReq settlementDeclarationReq)throws ParseException {
		//return settlementDeclarationDao.querySettlementDeclaration(settlementDeclarationReq);
		Map<String, Object> map=settlementDeclarationDao.querySettlementDeclaration(settlementDeclarationReq);
		
		List<SettlementDeclaration> list=(List<SettlementDeclaration>) map.get("data");
		
		if(list!=null && list.size()>0){
			for(SettlementDeclaration settlementDeclaration:list){
				
				
					Project project=projectDao.get(settlementDeclaration.getProjId());
					if(project!=null){
						settlementDeclaration.setProjectType(project.getProjectType());
					}
				
			}
		}
		
		return map;
	}

	/**
	 * 结算终审
	 * @author liaoyq
	 * @throws ParseException 
	 * @createTime 2017-8-9
	 */
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public String saveAuditDone(SettlementDeclaration settlementDeclaration) throws Exception {
		
		LoginInfo loginInfo=SessionUtil.getLoginInfo();
		//预算员审核 -签字即为审核通过
		if(settlementDeclaration.getCostControlAudit()!=null&&settlementDeclaration.getCostControlAuditStatus()<SettlementAuditResultEnum.PASSED.getValue()){
			settlementDeclaration.setCostControlAuditStatus(SettlementAuditResultEnum.PASSED.getValue());
		}else if(settlementDeclaration.getCostControlPrincipal()!=null&&settlementDeclaration.getCostControlPrincipalStatus()!=null){
			if(settlementDeclaration.getCostControlPrincipalStatus()<SettlementAuditResultEnum.PASSED.getValue()){
				//预算组长审批不通过，返回到预算员修改预算
				settlementDeclaration.setCostControlAuditStatus(SettlementAuditResultEnum.RE_AUDIT.getValue());
			}else{
				if(settlementDeclaration.getCostControlReAudit()!=null&&settlementDeclaration.getCostControlReAuditStatus()!=null){
					if(settlementDeclaration.getCostControlReAuditStatus()<SettlementAuditResultEnum.PASSED.getValue()){
						//市场发展部审批不通过，返回到预算员修改预算
						settlementDeclaration.setCostControlPrincipalStatus(SettlementAuditResultEnum.RE_AUDIT.getValue());
						settlementDeclaration.setCostControlAuditStatus(SettlementAuditResultEnum.RE_AUDIT.getValue());
					}else{
						//审核通过，保存终审人
						settlementDeclaration.setFinalAuditer(loginInfo.getStaffName());
						settlementDeclaration.setFinalAuditerId(loginInfo.getStaffId());
					}
				}
				
			}
		}
		
		settlementDeclaration.setFinalAuditerId(loginInfo.getStaffId());
		settlementDeclaration.setFinalAuditer(loginInfo.getStaffName());
		settlementDeclarationDao.saveOrUpdate(settlementDeclaration);
		signatureService.saveOrUpdateSign("menuId+menuNane+24",settlementDeclaration.getSign(), settlementDeclaration.getProjId(), settlementDeclaration.getSdId(), settlementDeclaration.getClass().getName(),false);
		
		//生成审核记录
		if(StringUtils.isNotBlank(settlementDeclaration.getMrAuditLevel())){
			DocType docType = new DocType();
			docType = docTypeService.findByStepId(StepEnum.REPORT_DONE_CONFIRM.getValue());
			ManageRecord manageRecord = new ManageRecord();
			manageRecord.setProjId(settlementDeclaration.getProjId());
			manageRecord.setProjNo(settlementDeclaration.getProjNo());
			String auditLevel = settlementDeclaration.getMrAuditLevel();
			manageRecord.setMrAuditLevel(auditLevel);
			if(auditLevel.equals("1")){
				manageRecord.setMrResult(settlementDeclaration.getCostControlAuditStatus().toString());
			}else if(auditLevel.equals("2")){
				manageRecord.setMrResult(settlementDeclaration.getCostControlPrincipalStatus().toString());
				if(StringUtil.isNotBlank(settlementDeclaration.getCostControlPrincipalAdvice())){
					manageRecord.setMrAopinion(settlementDeclaration.getCostControlPrincipalAdvice());
				}
				
			}else {
				manageRecord.setMrResult(settlementDeclaration.getCostControlReAuditStatus().toString());
				if(StringUtil.isNotBlank(settlementDeclaration.getCostControlReAuditAdvice())){
					manageRecord.setMrAopinion(settlementDeclaration.getCostControlReAuditAdvice());
				}
			}
			manageRecord.setMrId(IDUtil.getUniqueId(Constants.MODULE_CODE_SETTLEMENT));
			if(StringUtil.isBlank(manageRecord.getBusinessOrderId())){
				manageRecord.setBusinessOrderId(IDUtil.getUniqueId(Constants.MODULE_CODE_SETTLEMENT));
			}
			manageRecord.setDocTypeId(docType==null?"":docType.getId());
			manageRecord.setStepId(WorkFlowActionEnum.REPORT_DONE_CONFIRM.getActionCode());
			manageRecord.setMrCsr(loginInfo.getStaffId());
			manageRecord.setMrDeptId(loginInfo.getDeptId());
			manageRecord.setMrTime(settlementDeclarationDao.getDatabaseDate());
			manageRecordService.save(manageRecord);
		}
		
		
		//更新工程信息
		Project pro=projectDao.get(settlementDeclaration.getProjId());	//通过工程id查找Project
		if(settlementDeclaration.getCostControlReAudit()!=null&&settlementDeclaration.getCostControlReAuditStatus()==SettlementAuditResultEnum.PASSED.getValue()){
			//生成工程状态
			String statusId=workFlowService.queryProjStatusId(pro.getCorpId(),pro.getProjectType(),pro.getContributionMode(),WorkFlowActionEnum.REPORT_DONE_CONFIRM.getActionCode(), true);
			pro.setProjStatusId(statusId);								//设置工程状态
			
			//形成操作记录
			String orId = IDUtil.getUniqueId(Constants.MODULE_CODE_SETTLEMENT);
			operateRecordService.createOperateRecord(orId, settlementDeclaration.getProjId(), pro.getProjNo(), StepEnum.REPORT_DONE_CONFIRM.getValue(), StepEnum.REPORT_DONE_CONFIRM.getMessage(), "");
			pro.setSettlementDate(settlementDeclaration.getEndDeclaraDate());
			projectDao.saveOrUpdate(pro);
		}
		return Constants.OPERATE_RESULT_SUCCESS;
		//取得登录人信息
		//LoginInfo loginInfo = SessionUtil.getLoginInfo();
		//pro.setSettlementDate(settlementDeclaration.getEndDeclaraDate());
		//pro.setSettlementer(loginInfo.getStaffName());
		//projectDao.saveOrUpdate(pro);
		//contractService.createRetainage(pro);    					// 创建合同尾款
		
	   //生成资料流转单	
//		CompletionTransfer completionTransfer = new CompletionTransfer();
//		completionTransfer.setCtId(IDUtil.getUniqueId(Constants.MODULE_CODE_COMPLETE));
//		completionTransfer.setProjId(settlementDeclaration.getProjId());
//		completionTransfer.setProjNo(settlementDeclaration.getProjNo());
//		completionTransfer.setProjName(settlementDeclaration.getProjName());
//		completionTransfer.setCmoName(settlementDeclaration.getCmoName());
//		completionTransfer.setSuName(settlementDeclaration.getSuName());
//		completionTransfer.setProjAddr(settlementDeclaration.getProjAddr());
//		completionTransfer.setProjScaleDes(settlementDeclaration.getProjScaleDes());
//		completionTransferDao.saveOrUpdate(completionTransfer);
		
	}

	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void batInsertSubQualities(SubQuantitiesDto qdto) {
		
		List<SubQuantities> list=new ArrayList<SubQuantities>();
		
		SettlementDeclaration setdec = settlementDeclarationDao.findByProjId(qdto.getProjId());
		if(setdec!=null){
			setdec.setQuantitiesTotal(qdto.getSubmitAmount());
			setdec.setFirstDeclarationCost(qdto.getSubmitAmount());
		}
		settlementDeclarationDao.saveOrUpdate(setdec);
			
		//添加分包工程量记录
		for(SubQuantities sq:qdto.getList()){
			//工程量标准
//			sq.setProjId(qdto.getProjId());
//			sq.setProjNo(qdto.getProjNo());
			sq.setSqId(IDUtil.getUniqueId(Constants.MODULE_CODE_SUBCONTRACT));
//			sq.setAuditStatus(AuditStatusEnum.START.getValue());
			list.add(sq);
		}
		subQuantitiesDao.deleteByProjIdSettlement(qdto.getProjId(),AuditStatusEnum.START.getValue());
		subQuantitiesDao.batchInsertObjects(list);
				
	}

	@Override
	public Map<String, Object> queryBudgetMember(DesignerQueryReq designerQueryReq) throws ParseException {
		return settlementDeclarationDao.queryBudgetMember(designerQueryReq);
	}

	@Override
	public Map<String, Object> queryMember(DesignerQueryReq designerQueryReq) {
		return settlementDeclarationDao.queryCostManager(designerQueryReq);
	}

	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void signSettlementAuditPrint(String projId) {
		SettlementDeclaration settlementDeclaration=settlementDeclarationDao.findByProjId(projId);
		if(settlementDeclaration!=null){
			//标记已打印
			settlementDeclaration.setIsPrint(ContractIsPrintEnum.ALREADY_PRINT.getValue());
			settlementDeclarationDao.update(settlementDeclaration);
		}
	}

	/**
	 * 
	 */
	@Override
	public Map<String, Object> querySettleMember(
			DesignerQueryReq designerQueryReq) {
		return settlementDeclarationDao.querySettleMember(designerQueryReq);
	}

	/**
	 * 根据工程ID获取结算详述
	 * @author liaoyq
	 * @createTime 2017-8-10
	 */
	@Override
	public SettlementDeclaration findByProjId(String projId) {
		return settlementDeclarationDao.findByProjId(projId);
	}
	
	/**
	 * 监检报告登记保存
	 * @author fuliwei  
	 * @date 2018年11月14日  
	 * @version 1.0
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void saveMonitoringReport(SubBudget subBudget) {
		Project pro= projectDao.get(subBudget.getProjId());
		String statusId=workFlowService.queryProjStatusId(pro.getCorpId(),pro.getProjectType(),pro.getContributionMode(),WorkFlowActionEnum.MONITORING_REPORT.getActionCode(), true);
		pro.setProjStatusId(statusId);								   //监检报告登记
	
		pro.setConstructionProcRemark(subBudget.getRemark());		  //监检报告登记
		
		String orId = IDUtil.getUniqueId(Constants.MODULE_CODE_DESIGN);//生成唯一ID
	    String toder=operateRecordService.createOperateRecord(orId, pro.getProjId(), pro.getProjNo(),StepEnum.MONITORING_REPORT.getValue(), StepEnum.MONITORING_REPORT.getMessage(), "");
	    pro.setToDoer(toder);//待办人
	    projectDao.update(pro);
	}
}
