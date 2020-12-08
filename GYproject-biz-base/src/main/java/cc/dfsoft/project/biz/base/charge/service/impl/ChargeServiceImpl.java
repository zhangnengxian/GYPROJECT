package cc.dfsoft.project.biz.base.charge.service.impl;

import cc.dfsoft.project.biz.base.accept.dao.ProjectApplicationDao;
import cc.dfsoft.project.biz.base.accept.entity.ProjectApplication;
import cc.dfsoft.project.biz.base.baseinfo.dao.CustomerDao;
import cc.dfsoft.project.biz.base.baseinfo.entity.Customer;
import cc.dfsoft.project.biz.base.budget.dao.GovAuditCostDao;
import cc.dfsoft.project.biz.base.budget.entity.GovAuditCost;
import cc.dfsoft.project.biz.base.budget.enums.GovAuditCostTypeEnum;
import cc.dfsoft.project.biz.base.charge.dao.AccrualsRecordDao;
import cc.dfsoft.project.biz.base.charge.dao.CashFlowDao;
import cc.dfsoft.project.biz.base.charge.dto.ChargeReq;
import cc.dfsoft.project.biz.base.charge.entity.AccrualsRecord;
import cc.dfsoft.project.biz.base.charge.entity.CashFlow;
import cc.dfsoft.project.biz.base.charge.enums.ARFlagEnum;
import cc.dfsoft.project.biz.base.charge.enums.ARStatusEnum;
import cc.dfsoft.project.biz.base.charge.enums.ArContractTypeEnum;
import cc.dfsoft.project.biz.base.charge.service.ChargeService;
import cc.dfsoft.project.biz.base.complete.dao.GasApplyDao;
import cc.dfsoft.project.biz.base.complete.entity.GasApply;
import cc.dfsoft.project.biz.base.contract.dao.ContractDao;
import cc.dfsoft.project.biz.base.contract.entity.Contract;
import cc.dfsoft.project.biz.base.plan.dao.ConstructionPlanDao;
import cc.dfsoft.project.biz.base.plan.entity.ConstructionPlan;
import cc.dfsoft.project.biz.base.plan.service.ProcurementPlanDetailService;
import cc.dfsoft.project.biz.base.plan.service.ProcurementPlanService;
import cc.dfsoft.project.biz.base.project.dao.ProjectDao;
import cc.dfsoft.project.biz.base.project.dao.ProjectSignDao;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.project.entity.ProjectSign;
import cc.dfsoft.project.biz.base.project.enums.CollectionTypeEnum;
import cc.dfsoft.project.biz.base.project.enums.IsSignEnum;
import cc.dfsoft.project.biz.base.project.enums.ProjectSignTypeEnum;
import cc.dfsoft.project.biz.base.project.service.ProjectService;
import cc.dfsoft.project.biz.base.subpackage.dao.PaymentApplyDao;
import cc.dfsoft.project.biz.base.subpackage.entity.PaymentApply;
import cc.dfsoft.project.biz.ifs.ResultMessage;
import cc.dfsoft.project.biz.ifs.WebServiceTypeEnum;
import cc.dfsoft.project.biz.ifs.finance.enums.ResultTypeEnum;
import cc.dfsoft.project.biz.ifs.finance.enums.UpdateTypeEnum;
import cc.dfsoft.project.biz.ifs.finance.service.IFinanceInfoService;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.biz.sys.dept.entity.Department;
import cc.dfsoft.uexpress.biz.sys.dept.entity.Staff;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.exception.ExpressException;
import cc.dfsoft.uexpress.common.util.IDUtil;
import cc.dfsoft.uexpress.common.util.StringUtil;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 收费服务接口实现类
 * @author zhangjj
 *
 */
@Service
@Transactional(readOnly = true,propagation=Propagation.REQUIRED)
public class ChargeServiceImpl implements ChargeService{
	
	@Resource
	CashFlowDao cashFlowDao;
	
	@Resource
	AccrualsRecordDao accrualsRecordDao;
	
	@Resource
	CustomerDao customerDao;
	
	@Resource
	ProjectDao projectDao;
	@Resource
	ProcurementPlanService procurementPlanService;
	@Resource
	ProcurementPlanDetailService procurementPlanDetailService;
	
	/**通气申请Dao*/
	@Resource
	GasApplyDao gasApplyDao;
	
	/**立项申请单dao*/
	@Resource
	ProjectApplicationDao projectApplicationDao;
	
	/**施工合同*/
	@Resource
	ContractDao contractDao;
	
	@Resource
	ConstructionPlanDao constructionPlanDao;
	
	@Resource
	IFinanceInfoService financeInfoService;
	
	@Resource
	PaymentApplyDao paymentApplyDao;

	@Resource
	ProjectSignDao projectSignDao;
	@Resource
	ProjectService projService;
	@Resource
	GovAuditCostDao govAuditCostDao;
	/**
	 * 查询应收应付记录
	 * @param chargeReq
	 * @return
	 */
	@Override
	public Map<String, Object> queryAccrualsRecord(ChargeReq chargeReq) {
		Map<String, Object> map = accrualsRecordDao.queryAccrualsRecord(chargeReq);
		// 应付记录查询申请人及时间
		List<AccrualsRecord> list = (List<AccrualsRecord>) map.get("data");
		if(list!=null && list.size()>0){
			for(AccrualsRecord acr: list){
				//应付款
					if(ARFlagEnum.ACCOUNTS_PAY.getValue().equals(acr.getArFlag().toString()) && StringUtil.isNotBlank(acr.getPaId())){
						//请款信息
						PaymentApply paymentApply = paymentApplyDao.get(acr.getPaId());
						if(paymentApply!=null){
							acr.setPaymentApply(paymentApply);
						}
					}
				//没有发票号的，先虚拟一个发票号
					if(StringUtil.isBlank(acr.getInvoiceNo())){
						acr.setInvoiceNo(Constants.FAPIAO_CODE+accrualsRecordDao.getDatabaseDate().getTime());
						acr.setBillStatus("0");//虚拟发票
					}
			}
		}
		return map;
	}
	@Override
	public Map<String, Object> queryAccrualsRecordNew(ChargeReq chargeReq) {
		
		Map<String, Object> map=accrualsRecordDao.queryAccrualsRecordNew(chargeReq);
		return map;
	}
	
	/**
	 * 查询应付流水
	 * @param chargeReq
	 * @return
	 */
	@Override
	public Map<String, Object> queryCashFlow(ChargeReq chargeReq) {
		return cashFlowDao.queryCashFlow(chargeReq);
	}
	//recordsFiltered=401 draw=1  recordsTotal=1
	/**
	 *新增应付流水
	 * @param accrualsId
	 * @param cashFlow
	 * @return
	 * @throws Exception 
	 */
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED, rollbackFor = {Exception.class})
	public String insertCashFlow(String accrualsId,CashFlow cashFlow,String overChange) throws Exception {
		//取得登录人信息
		 LoginInfo loginInfo = SessionUtil.getLoginInfo();
		 Project project=projectDao.get(cashFlow.getProjId());
		 cashFlow.setCfDate(cashFlowDao.getDatabaseDate());                   // 收费时间
		
		 //财务修改通气类型
		 if(cashFlow.getGasType()!=null){
			 GasApply gas=gasApplyDao.viewGasApplyById(cashFlow.getProjId());
			 if(gas!=null){
				 gas.setGasType(cashFlow.getGasType());
				 gasApplyDao.save(gas);
			 }
		 }

		 //返回信息
		 String result = Constants.OPERATE_RESULT_SUCCESS;
		 AccrualsRecord acc= accrualsRecordDao.get(accrualsId);
		 //不同的合同首付款、阶段款的类型值不同
		 String firstMoneyType = CollectionTypeEnum.INITIAL_PAYMENT.getValue();//收付款
		 String secondMoneyType = CollectionTypeEnum.STAGE_PAYMENT.getValue();//阶段款
		 if(StringUtil.isNotBlank(acc.getContractType())){
			 if(acc.getContractType().equals(ArContractTypeEnum.INTELLIGENCE.getValue())){
				 firstMoneyType = CollectionTypeEnum.INTELLIGENT_CONTRACT.getValue();
				 secondMoneyType = CollectionTypeEnum.STAGE_INTELLIGENT_CONTRACT.getValue();
			 }else if(acc.getContractType().equals(ArContractTypeEnum.SUP_CONTRACT.getValue())){
				 firstMoneyType = CollectionTypeEnum.SUPPLEMENTAL_CONTRACT.getValue();
			 }
		 }
		 if(StringUtil.isNotBlank(cashFlow.getCfId())){
			 //修改流水
			 cashFlowDao.updateNotNullProperties(cashFlow, cashFlow.getCfId());
		 }else{
			 //保存收付流水-插入流水
			 cashFlow.setCfId(IDUtil.getUniqueId(Constants.MODULE_CODE_COST));    // ID
			 cashFlow.setProjCustId(acc.getCustId());               // 客户ID
			 cashFlow.setProjCustName(acc.getProjCustName());       // 客户名称
			 cashFlow.setCfFlag(acc.getArFlag());                   // 收费标志
			 cashFlow.setArId(acc.getArId());						//应收记录id
			 cashFlow.setContractType(acc.getContractType());       //合同类型
			 cashFlow.setConNo(StringUtils.isNotBlank(acc.getConNo())?acc.getConNo():acc.getProjNo());//合同编号
			 Staff staff=new Staff();
			 staff.setStaffId(loginInfo!=null?loginInfo.getStaffId():"101"); 
			 cashFlow.setStaff(staff);                               // 收费员
			 Department dept=new Department();
			 dept.setDeptId(loginInfo!=null?loginInfo.getDeptId():"1101");
			 cashFlow.setDepartment(dept); 							 // 收费部门
			 cashFlowDao.save(cashFlow);
			
			 //选择全额收款
			 if(("1").equals(cashFlow.getFullAmount())){
				 //找到应收流水 将应收流水全部置为已收款
				 
				 //找到首付款
				 AccrualsRecord firstPayMent=accrualsRecordDao.findByType(acc.getProjId(), firstMoneyType);
				 firstPayMent.setReceiveAmount(firstPayMent.getArCost());
				 firstPayMent.setArStatus(ARStatusEnum.ALREADY_CHARGE.getValue());		  //状态为已收费
				 firstPayMent.setInvoice("0");//收费后将发票状态改为未打
				 firstPayMent.setInvoiceAmount(null);
				 firstPayMent.setInvoiceDate(null);
				 accrualsRecordDao.update(firstPayMent);                  //更新应收流水对象
				 
				 //找到尾款
				 AccrualsRecord finalPayMent=accrualsRecordDao.findByType(acc.getProjId(), CollectionTypeEnum.BALANCE_PAYMENT.getValue());
				 if(finalPayMent!=null){//判断是否存在尾款
					 finalPayMent.setReceiveAmount(finalPayMent.getArCost());
					 finalPayMent.setArStatus(ARStatusEnum.ALREADY_CHARGE.getValue());		  //状态为已收费
					 finalPayMent.setInvoice("0");//收费后将发票状态改为未打
					 finalPayMent.setInvoiceAmount(null);
					 finalPayMent.setInvoiceDate(null);
					 accrualsRecordDao.update(finalPayMent);                  //更新应收流水对象
				 }
			 }else{
			 	 //合同首付款超收
			 	 if("1".equals(overChange)){
			 		//应收金额
			 		BigDecimal arCost=acc.getArCost();
			 		//计算超收金额
			 		BigDecimal overChangeCost=cashFlow.getReceiveAmount().subtract(arCost);
			 		//如果是虚拟合同收付款超收，将所收金额放在首付款上
			 		if(acc.getArType().equals(firstMoneyType) && arCost.compareTo(new BigDecimal(0))<1){
			 			secondMoneyType = firstMoneyType;//将收款放到首付款上
			 			arCost = overChangeCost;
			 		}
			 		//找到下一条未收完费的应收流水
			 		AccrualsRecord finalPayMent=accrualsRecordDao.findByType(acc.getProjId(), secondMoneyType);
			 		if (finalPayMent==null) {
			 			//未收费尾款
			 			finalPayMent = accrualsRecordDao.findByType(acc.getProjId(), secondMoneyType);
					}
			 		if(finalPayMent!=null){
			 			finalPayMent.setReceiveAmount(overChangeCost);
			 			finalPayMent.setArStatus(ARStatusEnum.ALREADY_CHARGE.getValue());	//状态为已收费
			 			accrualsRecordDao.update(finalPayMent);
			 		}
			 		acc.setReceiveAmount(arCost);						//收款金额
			 		acc.setArStatus(ARStatusEnum.ALREADY_CHARGE.getValue());		  //状态为已收费
			 		acc.setInvoice("0");//收费后将发票状态改为未打
			 		acc.setInvoiceAmount(null);
					acc.setInvoiceDate(null);
					// acc.setCfId(cashFlow.getCfId());
					accrualsRecordDao.update(acc);                  //更新应收应付流水对象
			 	 }else{
			 		 acc.setReceiveAmount(cashFlow.getReceiveAmount());
					 acc.setArStatus(ARStatusEnum.ALREADY_CHARGE.getValue());		  //状态为已收费
					 acc.setInvoice("0");//收费后将发票状态改为未打
					 acc.setInvoiceAmount(null);
					 acc.setInvoiceDate(null);
					// acc.setCfId(cashFlow.getCfId());
					 accrualsRecordDao.update(acc);                  //更新应收应付流水对象
			 	 }
			 }
			 
			 String msg ="";
			 ResultMessage resultMessage = new ResultMessage();
			 //收款登记todo:
			 if(ARFlagEnum.RECEIVE_ACCOUNT.getValue().equals(cashFlow.getCfFlag().toString()) &&projService.isToCall(project.getProjId(), WebServiceTypeEnum.RECIVE_MONEY.getValue())){
				//调用收款单接口，将组装数据传递到财务接口，并返回信息
				 msg = financeInfoService.gatherClient(project, cashFlow,UpdateTypeEnum.INSERT.getValue(),WebServiceTypeEnum.RECIVE_MONEY.getValue());
				 JSONObject jsonbean = JSONObject.fromObject(msg);
				//返回信息-当接口返回失败时，抛出异常
				resultMessage = (ResultMessage) JSONObject.toBean(jsonbean,ResultMessage.class);
				if(resultMessage!=null && ResultTypeEnum.FAIL.getValue().equals(resultMessage.getRet_type()) && projService.isSynchronization(project.getProjId(), WebServiceTypeEnum.RECIVE_MONEY.getValue())){
					//回滚事物
					throw new ExpressException(resultMessage.getRet_type(),resultMessage.getRet_message());
				}
			 }else if(ARFlagEnum.ACCOUNTS_PAY.getValue().equals(cashFlow.getCfFlag().toString())&&projService.isToCall(project.getProjId(), WebServiceTypeEnum.PAY_MONEY.getValue())){
				 //付款登记
				 msg = financeInfoService.paymentClient(project, cashFlow,UpdateTypeEnum.INSERT.getValue(),WebServiceTypeEnum.PAY_MONEY.getValue());
				 JSONObject jsonbean = JSONObject.fromObject(msg);
				//返回信息-当接口返回失败时，抛出异常
				resultMessage = (ResultMessage) JSONObject.toBean(jsonbean,ResultMessage.class);
				if(resultMessage!=null && ResultTypeEnum.FAIL.getValue().equals(resultMessage.getRet_type()) && projService.isSynchronization(project.getProjId(), WebServiceTypeEnum.PAY_MONEY.getValue())){
					//回滚事物
					throw new ExpressException(resultMessage.getRet_type(),resultMessage.getRet_message());
				}
			 }
		 }

		//标记已收款工程
		ProjectSign projectSign1 = projectSignDao.findOnly(cashFlow.getProjId(),ProjectSignTypeEnum.UNPAID.getValue());
		if(projectSign1!=null&&projectSign1.getStatus().equals("1")){
			projectSign1.setStatus(IsSignEnum.NOT_SIGN.getValue());
			projectSignDao.update(projectSign1);
		}
		//标记已结清工程
		ChargeReq chargeReq = new ChargeReq();
		chargeReq.setProjId(cashFlow.getProjId());
		chargeReq.setArFlag(ARFlagEnum.RECEIVE_ACCOUNT.getValue());
		chargeReq.setArOver("1");
		Map<String,Object> map =accrualsRecordDao.queryAccrualsRecord(chargeReq);
		List<AccrualsRecord> accrualsRecords = (List<AccrualsRecord>) map.get("data");
		if(accrualsRecords==null||accrualsRecords.size()==0){
			ProjectSign projectSign = projectSignDao.findOnly(cashFlow.getProjId(), ProjectSignTypeEnum.INCOMPLETE_COST.getValue());
			if(projectSign!=null){
				projectSign.setSignType(ProjectSignTypeEnum.INCOMPLETE_COST.getValue());//款项未结清工程
				projectSign.setStatus(IsSignEnum.NOT_SIGN.getValue());
				projectSignDao.update(projectSign);
			}
		}
		//标记生成虚拟发票工程
		if(StringUtils.isNotBlank(cashFlow.getBillStatus())){
			if(cashFlow.getBillStatus().equals("1")){
				ProjectSign projectSign = projectSignDao.findOnly(cashFlow.getProjId(), ProjectSignTypeEnum.INVOICE.getValue());
				if(projectSign==null){
					ProjectSign projectSign2 = new ProjectSign();
					projectSign2.setProjId(cashFlow.getProjId());
					projectSign2.setSignType(ProjectSignTypeEnum.INVOICE.getValue());//虚拟发票工程
					projectSign2.setStatus(IsSignEnum.IS_SIGN.getValue());
					projectSignDao.save(projectSign2);
				}
			}else{
				List<CashFlow> cashFlows = cashFlowDao.queryCashFlawOnly(cashFlow.getProjId(),"1");
				if(cashFlows==null || cashFlows.size()==0){
					ProjectSign projectSign = projectSignDao.findOnly(cashFlow.getProjId(), ProjectSignTypeEnum.INVOICE.getValue());
					if(projectSign!=null){
						projectSign.setSignType(ProjectSignTypeEnum.INVOICE.getValue());//虚拟发票工程
						projectSign.setStatus(IsSignEnum.NOT_SIGN.getValue());
						projectSignDao.update(projectSign);
					}
				}
			}
		}
		//补充协议款未交
	   if(CollectionTypeEnum.SUPPLEMENTAL_CONTRACT.getValue().equals(cashFlow.getCfType())){
		   this.projSign(cashFlow.getProjId());
	   }
		return result;
	}
	
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void updateAccrualsRecord(AccrualsRecord accrualsRecord) {		
		accrualsRecordDao.updateNotNullProperties(accrualsRecord, accrualsRecord.getArId());
	}

	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED,rollbackFor={Exception.class})
	public void cancleCharge(String arId, String cfId) throws ParseException {
		CashFlow cf=cashFlowDao.get(cfId);
		if(cf!=null){
		   AccrualsRecord ar=accrualsRecordDao.get(arId);
		   if(ar!=null){
			   ar.setReceiveAmount(ar.getReceiveAmount().subtract(cf.getCfAmount()));
			   if(ar.getReceiveAmount().equals(0.0)){
				  ar.setArStatus(ARStatusEnum.TO_BE_CHARGE.getValue());
			   }
			   ar.setInvoice(cf.getInvoice());
			   cf.setIsValid("0");
			   cashFlowDao.update(cf);
			   accrualsRecordDao.update(ar);
			   
			   	 String msg ="";
				 ResultMessage resultMessage = new ResultMessage();
				 //收款登记todo:
				 Project project = projectDao.get(cf.getProjId());
				 if(projService.isToCall(project.getProjId(), WebServiceTypeEnum.RECIVE_MONEY_DELETE.getValue())){
					//调用删除收款信息接口todo:
					 
					 msg = financeInfoService.gatherClient(project, cf, UpdateTypeEnum.RE_MONEY_DEL.getValue(),WebServiceTypeEnum.RECIVE_MONEY_DELETE.getValue());
					 JSONObject jsonbean = JSONObject.fromObject(msg);
					//返回信息-当接口返回失败时，抛出异常
					resultMessage = (ResultMessage) JSONObject.toBean(jsonbean,ResultMessage.class);
					if(resultMessage!=null && ResultTypeEnum.FAIL.getValue().equals(resultMessage.getRet_type()) && projService.isSynchronization(project.getProjId(), WebServiceTypeEnum.RECIVE_MONEY_DELETE.getValue())){
						//回滚事物
						throw new ExpressException(resultMessage.getRet_type(),resultMessage.getRet_message());
					}
				 }
		   }
		   //首付款未交清
		   if(CollectionTypeEnum.INITIAL_PAYMENT.getValue().equals(cf.getCfType())){
			   this.projSign(cf.getProjId());
		   }
		   //补充协议款未交
		   if(CollectionTypeEnum.SUPPLEMENTAL_CONTRACT.getValue().equals(cf.getCfType())){
			   this.projSign(cf.getProjId());
		   }





			boolean b=cashFlowDao.isHaveVirtualInvoice(cf.getProjId());
		   if (!b){//实收中没有虚拟发票标记将projectSign中的虚拟发票置为不标记
		   	List<String> list=new ArrayList<>();
		   	list.add(ProjectSignTypeEnum.INVOICE.getValue());
			   List<ProjectSign> psList = projectSignDao.findByProjIdAndStatus(cf.getProjId(), IsSignEnum.IS_SIGN.getValue(), list);
			   if (psList!=null && psList.size()>0){
				   for (ProjectSign ps:psList) {
				   	ps.setStatus(IsSignEnum.NOT_SIGN.getValue());
				   	projectSignDao.saveOrUpdate(ps);
				   }
			   }
		   }


		}
	}
	@Override
	public Map<String, Object> queryCashFlowNew(ChargeReq chargeReq) {
		// TODO Auto-generated method stub
		return cashFlowDao.queryCashFlowNew(chargeReq);
	}
	
	
	
	/**
	 * 查询右侧详述
	 * @param id
	 * @return
	 */
	@Override
	public Map<String, Object> queryProject(String id) {
		Map<String,Object> map = new HashMap<String,Object>();
		//取得登录人信息
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		String account="";
		String openBank="";
		String custId="";
		String dutyParagraph="";
		String unitAddress="";
		String custName="";
		String conNo="";//合同编号
		String increment="";//税率
		String paNo="";//受理单号
		String cuName="";//分包单位
		String cuCsr="";//分包负责人
		String cuPhone="";//联系电话
		String gasType="";//通气类型
		BigDecimal govAuditCost = new BigDecimal(0);//政府审定金额
		
		if (loginInfo!=null) {
			map.put("cfOperator",loginInfo.getStaffId());
			map.put("staffName",loginInfo.getStaffName());
		}
		Project project=projectDao.get(id);
		if(null!=project&&null!=project.getCustId()){
			Customer customer=customerDao.get(project.getCustId());
			if(null!=customer){
				account=customer.getAccount();
				openBank=customer.getOpenBank();
				custId=customer.getCustId();
				dutyParagraph=customer.getDutyParagraph();
				unitAddress=customer.getUnitAddress();
				custName=project.getCustName();
			}
		}
		ProjectApplication pa=projectApplicationDao.queryById(id);
		if(pa!=null){
			paNo=pa.getPaNo();
		}
		Contract contract=contractDao.viewContractByprojId(id);
		if(contract!=null){
			conNo=contract.getConNo();
			increment=contract.getIncrement();
			custName = contract.getCustName();
		}
		ConstructionPlan cp=constructionPlanDao.viewPlanById(id);
		if(cp!=null){
			cuName=cp.getCuName();
			cuCsr=cp.getCuLegalRepresent();
			cuPhone=cp.getCuPhone();
		}
		GasApply gasApply=gasApplyDao.viewGasApplyById(id);
		if(gasApply != null){
			gasType=gasApply.getGasType();
		}
		GovAuditCost auditCost = govAuditCostDao.queryByProjIdAndType(id, GovAuditCostTypeEnum.SETTLEMENT.getValue());
		if(auditCost!=null){
			govAuditCost = auditCost.getAuthorizedCost();
		}
		map.put("gasType",gasType);
		map.put("paNo",paNo);
		map.put("conNo",conNo);
		map.put("increment",increment);
		map.put("account",account);
		map.put("openBank",openBank);
		map.put("custId",custId);
		map.put("dutyParagraph",dutyParagraph);
		map.put("unitAddress",unitAddress);
		map.put("custName",custName);
		map.put("cuName",cuName);
		map.put("cuCsr",cuCsr);
		map.put("cuPhone",cuPhone);
		map.put("govAuditCost",govAuditCost);
		return map;
	}
	
	
	/**
	 * 查询已付合同款或工程款
	 * @author fuliwei
	 * @createTime 2017年8月16日
	 * @param 
	 * @return
	 */
	@Override
	public BigDecimal queryPayAmount(String projId, String type) {
		return cashFlowDao.queryPayAmount(projId, type);
	}
	
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public boolean modifyCashFlowById(CashFlow cashFlow) {
		if(cashFlow!=null){
			return cashFlowDao.modifyCashFlowById(cashFlow.getCfId(),cashFlow.getResultFlag());
		}
		return false;
	}
	@Override
	public CashFlow queryById(String cfId, String projNo) {
		return cashFlowDao.queryById(cfId,projNo);
	}
	/**
	 * 根据工程ID查询实收有效流水
	 */
	@Override
	public List<CashFlow> queryCashFlowByProjIdType(String projId, String cfFlag,String conNo) {
		return cashFlowDao.queryCashFlowByProjIdType(projId,cfFlag,conNo);
	}

	/**
	 * 标记工程首付款未交清
	 */
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void projSign(String projId){
		if(StringUtil.isNotBlank(projId)){
			//工程已收首付款未交清的标记
			ChargeReq chargeReq1 = new ChargeReq();
			chargeReq1.setProjId(projId);
			chargeReq1.setArFlag(ARFlagEnum.RECEIVE_ACCOUNT.getValue());
			chargeReq1.setArType(CollectionTypeEnum.INITIAL_PAYMENT.getValue());
			chargeReq1.setArAtatus(ARStatusEnum.ALREADY_CHARGE.getValue());

			Map<String,Object> map1 =accrualsRecordDao.queryAccrualsRecord(chargeReq1);

			List<AccrualsRecord> accrualsRecords1 = (List<AccrualsRecord>) map1.get("data");

			ProjectSign projectSign = projectSignDao.findOnly(projId, ProjectSignTypeEnum.UNPAID.getValue());

			//存在已收首付款
			if(accrualsRecords1!=null&&accrualsRecords1.size()>0){
				//实收小于应收
				if(accrualsRecords1.get(0).getArCost()!=null &&(accrualsRecords1.get(0).getArCost().compareTo(accrualsRecords1.get(0).getReceiveAmount()!=null?accrualsRecords1.get(0).getReceiveAmount():new BigDecimal(0))>0)){
					if(projectSign==null){
						projectSign = new ProjectSign();
					}
					projectSign.setProjId(projId);
					projectSign.setSignType(ProjectSignTypeEnum.UNPAID.getValue());//首付款未交清
					projectSign.setStatus(IsSignEnum.IS_SIGN.getValue());
					projectSignDao.saveOrUpdate(projectSign);
				}else{
					if(projectSign!=null){
						projectSign.setSignType(ProjectSignTypeEnum.UNPAID.getValue());//首付款已交清
						projectSign.setStatus(IsSignEnum.NOT_SIGN.getValue());
						projectSignDao.update(projectSign);
					}
				}
			}
		}
	}
	/**
	 * 标记工程补充协议未收款
	 */
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void projSignSup(String projId) {
		if(StringUtil.isNotBlank(projId)){
			//工程已收首付款未交清的标记
			ChargeReq chargeReq1 = new ChargeReq();
			chargeReq1.setProjId(projId);
			chargeReq1.setArFlag(ARFlagEnum.RECEIVE_ACCOUNT.getValue());
			chargeReq1.setArType(CollectionTypeEnum.SUPPLEMENTAL_CONTRACT.getValue());
			chargeReq1.setArAtatus(ARStatusEnum.TO_BE_CHARGE.getValue());
			Map<String,Object> map1 =accrualsRecordDao.queryAccrualsRecord(chargeReq1);
			List<AccrualsRecord> accrualsRecords1 = (List<AccrualsRecord>) map1.get("data");
			ProjectSign projectSign = projectSignDao.findOnly(projId, ProjectSignTypeEnum.NO_SUP_MONEY.getValue());
			
			//存在未交费补充协议款
			if(accrualsRecords1!=null&&accrualsRecords1.size()>0){
				if(projectSign==null){
					projectSign = new ProjectSign();
				}
				projectSign.setProjId(projId);
				projectSign.setSignType(ProjectSignTypeEnum.NO_SUP_MONEY.getValue());//补充协议未缴款
				projectSign.setStatus(IsSignEnum.IS_SIGN.getValue());
				projectSignDao.saveOrUpdate(projectSign);
			}else{
				if(projectSign!=null){
					projectSign.setSignType(ProjectSignTypeEnum.NO_SUP_MONEY.getValue());//补充协议已缴款
					projectSign.setStatus(IsSignEnum.NOT_SIGN.getValue());
					projectSignDao.update(projectSign);
				}
			}
		}
	}
	
	
	/**
	 * 查询是否满足全额收款的条件
	 * @author fulw
	 * @createTime 2017-1-11
	 * @param projId
	 * @return String
	 */
	/*@Override
	public String queryIsFullAmount(String id,String cfFlag) {
		return cashFlowDao.queryIsFullAmount(id,cfFlag);
	}*/

}
