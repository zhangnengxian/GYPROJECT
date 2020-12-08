package cc.dfsoft.project.biz.base.subpackage.service.impl;

import cc.dfsoft.project.biz.base.baseinfo.dao.BusinessPartnersDao;
import cc.dfsoft.project.biz.base.baseinfo.dao.DocTypeDao;
import cc.dfsoft.project.biz.base.baseinfo.dao.SystemSetDao;
import cc.dfsoft.project.biz.base.baseinfo.entity.BusinessPartners;
import cc.dfsoft.project.biz.base.baseinfo.enums.AssistProgressTypeEnum;
import cc.dfsoft.project.biz.base.baseinfo.enums.WorkFlowTypeEnum;
import cc.dfsoft.project.biz.base.baseinfo.service.DocTypeService;
import cc.dfsoft.project.biz.base.charge.dao.CashFlowDao;
import cc.dfsoft.project.biz.base.charge.entity.CashFlow;
import cc.dfsoft.project.biz.base.charge.enums.ARFlagEnum;
import cc.dfsoft.project.biz.base.charge.service.ChargeService;
import cc.dfsoft.project.biz.base.common.entity.SystemSet;
import cc.dfsoft.project.biz.base.constructmanage.enums.StepOutWorkflowEnum;
import cc.dfsoft.project.biz.base.contract.dao.ContractDao;
import cc.dfsoft.project.biz.base.contract.dao.IntelligentSupplementDao;
import cc.dfsoft.project.biz.base.contract.dao.SupplementalContractDao;
import cc.dfsoft.project.biz.base.contract.entity.Contract;
import cc.dfsoft.project.biz.base.contract.entity.IntelligentSupplement;
import cc.dfsoft.project.biz.base.contract.entity.SupplementalContract;
import cc.dfsoft.project.biz.base.design.dto.DesignDispatchDto;
import cc.dfsoft.project.biz.base.design.dto.DesignerQueryReq;
import cc.dfsoft.project.biz.base.design.enums.UnitTypeEnum;
import cc.dfsoft.project.biz.base.plan.dao.ConstructionPlanDao;
import cc.dfsoft.project.biz.base.plan.entity.ConstructionPlan;
import cc.dfsoft.project.biz.base.plan.enums.IsDispatchEnum;
import cc.dfsoft.project.biz.base.project.dao.ManageRecordDao;
import cc.dfsoft.project.biz.base.project.dao.NoticeDao;
import cc.dfsoft.project.biz.base.project.dao.OperateRecordDao;
import cc.dfsoft.project.biz.base.project.dao.ProjectDao;
import cc.dfsoft.project.biz.base.project.entity.ManageRecord;
import cc.dfsoft.project.biz.base.project.entity.OperateRecord;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.project.enums.CollectionTypeEnum;
import cc.dfsoft.project.biz.base.project.enums.MrResultEnum;
import cc.dfsoft.project.biz.base.project.enums.OperateWorkFlowEnum;
import cc.dfsoft.project.biz.base.project.enums.ProjLtypeEnum;
import cc.dfsoft.project.biz.base.project.service.OperateRecordService;
import cc.dfsoft.project.biz.base.settlement.enums.AuditResultEnum;
import cc.dfsoft.project.biz.base.subpackage.dao.FeeApplyListDao;
import cc.dfsoft.project.biz.base.subpackage.dao.IntelligentMeterContractDao;
import cc.dfsoft.project.biz.base.subpackage.dao.PaymentApplyDao;
import cc.dfsoft.project.biz.base.subpackage.dao.SubContractDao;
import cc.dfsoft.project.biz.base.subpackage.dto.PaymentApplyReq;
import cc.dfsoft.project.biz.base.subpackage.entity.FeeApplyList;
import cc.dfsoft.project.biz.base.subpackage.entity.IntelligentMeterContract;
import cc.dfsoft.project.biz.base.subpackage.entity.PaymentApply;
import cc.dfsoft.project.biz.base.subpackage.entity.SubContract;
import cc.dfsoft.project.biz.base.subpackage.enums.ApplyFeeTypeEnum;
import cc.dfsoft.project.biz.base.subpackage.enums.AuditUnitTypeEnum;
import cc.dfsoft.project.biz.base.subpackage.enums.FeeTypeEnum;
import cc.dfsoft.project.biz.base.subpackage.enums.PayTypeEnum;
import cc.dfsoft.project.biz.base.subpackage.service.PaymentApplyService;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dao.DepartmentDao;
import cc.dfsoft.uexpress.biz.sys.dept.dao.StaffDao;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.biz.sys.dept.entity.Department;
import cc.dfsoft.uexpress.biz.sys.dept.entity.Staff;
import cc.dfsoft.uexpress.biz.sys.dept.enums.DeptDivideEnum;
import cc.dfsoft.uexpress.biz.sys.dept.enums.PostTypeEnum;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.constant.DataFilerSetUpDto;
import cc.dfsoft.uexpress.common.util.IDUtil;
import cc.dfsoft.uexpress.common.util.StringUtil;
import net.sf.json.JSONSerializer;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * 付款申请
 * @author fuliwei
 *
 */
@Service
@Transactional(readOnly = true,propagation=Propagation.REQUIRED)
public class PaymentApplyServiceImpl  implements PaymentApplyService{
	
	/**付款申请Dao*/
	@Resource
	PaymentApplyDao paymentApplyDao;
	
	/**施工计划Dao*/
	@Resource
	ConstructionPlanDao  constructionPlanDao;
	
	/**合同Dao*/
	@Resource
	ContractDao contractDao;
	
	/**工程Dao*/
	@Resource
	ProjectDao projectDao;
	
	/**分包合同Dao*/
	@Resource
	SubContractDao subContractDao;
	
	/**收费服务*/
	@Resource
	ChargeService chargeService;
	
	/**业务单类型*/
	@Resource
	DocTypeDao docTypeDao;
	
	/**系统设置*/
	@Resource
	SystemSetDao systemSetDao;
	
	/**审核记录*/
	@Resource
	ManageRecordDao manageRecordDao;
	
	@Resource
	CashFlowDao cashFlowDao;

	@Resource
	BusinessPartnersDao businessPartnersDao;
	
	/**部门Dao*/
	@Resource
	DepartmentDao departmentDao;
	
	/**员工*/
	@Resource
	StaffDao staffDao;
	
	/**通知*/
	@Resource
	NoticeDao noticeDao;
	
	/**付款申请清单*/
	@Resource
	FeeApplyListDao feeApplyListDao;
	
	@Resource
	DocTypeService docTypeService;
	@Resource
	OperateRecordService operateRecordService;

	@Resource
	OperateRecordDao operateRecordDao;
	@Resource
	SupplementalContractDao supplementalContractDao;
	@Resource
	IntelligentMeterContractDao intelligentMeterContractDao;
	@Resource
	IntelligentSupplementDao intelligentSupplementDao;
	
	/**
	 * 查询详述
	 * @author fuliwei
	 * @createTime 2017年8月16日
	 * @param 
	 * @return
	 * @throws ParseException 
	 */
	@Override
	public PaymentApply findById(String projId, String paId) throws ParseException {
		PaymentApply pa=paymentApplyDao.get(paId);						//分部验收申请
		Project pro = projectDao.get(projId);							//工程
		ConstructionPlan cp=constructionPlanDao.viewPlanById(projId);   //计划
		
		//实收实付

		LoginInfo login=SessionUtil.getLoginInfo();
		BusinessPartners businessPartners = businessPartnersDao.get(login.getDeptId());
		if(null!=pa){
			if(businessPartners!=null){
				if(UnitTypeEnum.SUPERVISION_UNIT.getValue().equals(businessPartners.getUnitType())){
					//监理单位监理费申请
					pa.setPayType(PayTypeEnum.SUPERVISOR.getValue());
				}else if(UnitTypeEnum.TEST_UNIT.getValue().equals(businessPartners.getUnitType())){
					//检测单位
					pa.setPayType(PayTypeEnum.DETECTION.getValue());
				}else if(UnitTypeEnum.CONSTRUCTION_UNIT.getValue().equals(businessPartners.getUnitType())){
					//施工单位
					pa.setPayType(PayTypeEnum.CONSTRUCTION.getValue());
				}else {
					// TODO: 2017/12/1
				}
			}

			if(pro!=null){
				pa.setDeptName(pro.getDeptName());						//业务部门
				pa.setCorpName(pro.getCorpName());						//分公司名称
			}
			
			if(StringUtils.isBlank(pa.getApplyer())){
				pa.setApplyer(login.getStaffName());
			}
			
			if(StringUtil.isNotBlank(projId)){
				pa = this.getContractAndReciveAndPayInfo(pa,projId);
			}
			
			
			return pa;
		}else{
			PaymentApply das = new PaymentApply();
			if(null!=pro){
				das.setProjId(projId);
				das.setProjName(pro.getProjName());
				das.setProjNo(pro.getProjNo());
				das.setProjAddr(pro.getProjAddr());
				das.setProjScaleDes(pro.getProjScaleDes());
				das.setDeptName(pro.getDeptName());						//业务部门
				das.setCorpName(pro.getCorpName());						//分公司名称
				das.setCorpId(pro.getCorpId());                         //分公司id
				das.setContributionModeDes(pro.getContributionModeDes());//出资方式
				das.setProjectTypeDes(pro.getProjectTypeDes());
			}

			if(businessPartners!=null){
				if(UnitTypeEnum.SUPERVISION_UNIT.getValue().equals(businessPartners.getUnitType())){
					//监理单位监理费申请
					das.setPayType(PayTypeEnum.SUPERVISOR.getValue());
				}else if(UnitTypeEnum.TEST_UNIT.getValue().equals(businessPartners.getUnitType())){
					//检测单位
					das.setPayType(PayTypeEnum.DETECTION.getValue());
				}else if(UnitTypeEnum.CONSTRUCTION_UNIT.getValue().equals(businessPartners.getUnitType())){
					//施工单位
					das.setPayType(PayTypeEnum.CONSTRUCTION.getValue());
				}
			}

			if(cp!=null){
				das.setRequestFoundsUnit(cp.getCuName());				//请款单位
			}
			if(StringUtil.isNotBlank(projId)){
				das = this.getContractAndReciveAndPayInfo(das,projId);
			}
			
			return das;
		}
	}
	
	public PaymentApply getContractAndReciveAndPayInfo(PaymentApply pa, String projId) throws ParseException {
		Contract con=contractDao.viewContractByprojId(projId);          //安装合同
		List<SupplementalContract> supCon=supplementalContractDao.findByProjId(projId);          //补充协议
		IntelligentMeterContract intelCon=intelligentMeterContractDao.findContractByprojId(projId);          //智能表合同
		List<IntelligentSupplement> intelSupCon=intelligentSupplementDao.findByProjId(projId);          //智能表补充协议
		SubContract sc=subContractDao.findSubContractByprojId(projId);	//施工合同
		String contractDes = "";
		if(con!=null && con.getContractAmount()!=null ){
			pa.setContractAmount(con.getContractAmount());			//合同金额
			contractDes += "安装合同金额："+con.getContractAmount().toString()+"元";
		}
		if(supCon!=null && supCon.size()>0){
			contractDes += ",补充协议共计金额：";
			BigDecimal supAmount = new BigDecimal(0);
			for(SupplementalContract sup : supCon){
				supAmount = supAmount.add(sup.getScAmount());
			}
			contractDes += supAmount.toString()+"元";
		}
		if(intelCon!=null && intelCon.getTotalCost()!=null){
			contractDes += "智能表合同金额："+con.getContractAmount().toString()+"元";
		}
		if(intelSupCon!=null && intelSupCon.size()>0){
			contractDes += ",智能表补充协议共计金额：";
			BigDecimal supAmount = new BigDecimal(0);
			for(IntelligentSupplement supIntel : intelSupCon){
				supAmount = supAmount.add(supIntel.getIsAmount());
			}
			contractDes += supAmount.toString()+"元";
		}
		//合同款描述
		pa.setContractDes(contractDes);
		
		//共计收款
		BigDecimal payAmount=chargeService.queryPayAmount(projId, ARFlagEnum.RECEIVE_ACCOUNT.getValue());
		pa.setPayAmount(payAmount);
		//有效实收实付
		List<CashFlow> cashFlows = chargeService.queryCashFlowByProjIdType(projId,null,null);
		if(cashFlows!=null && cashFlows.size()>0){
			String receiveMoneyDes = "";//收款描述
			String payMoneyDes = "";//付款描述
			String refundDes = "";//退款描述
			for(CashFlow cash : cashFlows){
				if(ARFlagEnum.RECEIVE_ACCOUNT.getValue().equals(cash.getCfFlag())){
					receiveMoneyDes += CollectionTypeEnum.valueof(cash.getCfType()).getMessage() + ":" + cash.getCfAmount()+";";
				}else if(ARFlagEnum.ACCOUNTS_PAY.getValue().equals(cash.getCfFlag())){
					payMoneyDes += CollectionTypeEnum.valueof(cash.getCfType()).getMessage() + ":" + cash.getCfAmount()+";";
				}else{//退款
					refundDes += CollectionTypeEnum.valueof(cash.getCfType()).getMessage() + ":" + cash.getCfAmount()+";";
				}
			}
			pa.setReceiveMoneyDes(receiveMoneyDes + refundDes);
			pa.setPayMoneyDes(payMoneyDes);
		}
		//施工合同金额
		if(sc!=null){
			pa.setScAmount(sc.getScAmount());						
		}
		//已登记付款
		BigDecimal payScAmount=chargeService.queryPayAmount(projId, ARFlagEnum.ACCOUNTS_PAY.getValue());
		pa.setPayScAmount(payScAmount);
		return pa;
	}

	/**
	 *付款申请保存
	 * @author fuliwei
	 * @createTime 2017年8月16日
	 * @param 
	 * @return
	 */
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void savePaymentApply(PaymentApply paymentApply) {
		boolean flag = false;
		if(StringUtils.isBlank(paymentApply.getPaId())){
			paymentApply.setPaId(IDUtil.getUniqueId(Constants.MODULE_CODE_SUBCONTRACT));
			//设置唯一付款申请编号
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			String date = sdf.format(paymentApplyDao.getDatabaseDate());
			String applyNo = paymentApplyDao.getMaxApplyNo(date,paymentApply.getProjId());
			if(null == applyNo || "".equals(applyNo)){
				applyNo = date+"01";
			}
			paymentApply.setApplyNo(applyNo);
			flag = true;
		}

		Project pro=projectDao.get(paymentApply.getProjId());
		if(pro!=null){
			paymentApply.setCorpId(pro.getCorpId());
			paymentApply.setTenantId(pro.getTenantId());
			paymentApply.setOrgId(pro.getDeptId());
		}


		//贵安子公司现场代表为付款审核人
		List<DataFilerSetUpDto> list = Constants.getDataFilterMapByKey(pro.getCorpId()+PostTypeEnum.BUILDER.getValue()+"_"+paymentApply.getMenuId());
		if(list!=null && list.size()>0){
			//配置某个人，就取某个人，否则取现场代表
			paymentApply.setPaAuditerId(StringUtil.isNotBlank(list.get(0).getSupSql())?list.get(0).getSupSql():pro.getBuilderId());
		}else{
			if(CollectionTypeEnum.SUBCONTRACT_PAYMENT.getValue().equals(paymentApply.getApplyReason()) || CollectionTypeEnum.STAGE_PAYMENT.getValue().equals(paymentApply.getApplyReason()) ){
				//分包首付款 审核人为待施工预算审核人
				if(pro!=null){
					paymentApply.setPaAuditerId(pro.getBudgeterAuditId());
				}
			}else if(CollectionTypeEnum.SUBCONTRACT_BALANCE_PAYMENT.getValue().equals(paymentApply.getApplyReason())){
				//结算款 审核人为终审确认审核人
				if(pro!=null){
					paymentApply.setPaAuditerId(pro.getSettlementerId());
				}
			}
		}

		Staff stf = staffDao.get(paymentApply.getPaAuditerId());
		if (!stf.getCorpId().contains(pro.getCorpId())){
			paymentApply.setPaAuditerId("");
		}

		//根据付款类型来配置一级审核人（1：施工费；3：设计费/检测费；4：监理费）
		DataFilerSetUpDto config = Constants.isConfig(pro.getCorpId()+"_"+paymentApply.getFeeType()+"_"+paymentApply.getMenuId());
		if (config!=null){
			paymentApply.setPaAuditerId(config.getSupSql());
		}


		LoginInfo login=SessionUtil.getLoginInfo();
		paymentApply.setApplyer(login.getStaffName());
		paymentApply.setApplyDeptName(login.getDeptName());					//申请部门
		
		if(StringUtil.isBlank(paymentApply.getApplyDeptName())){
			paymentApply.setApplyDeptName(paymentApply.getRequestFoundsUnit());
		}
		
		paymentApply.setApplyDeptId(login.getDeptId());						//申请部门
		paymentApply.setAuditState(AuditResultEnum.NOT_APPLY.getValue());  //未推送
		paymentApply.setFeeType(FeeTypeEnum.CONSFEE.getValue());			//工程费
		paymentApply.setApplyFeeType(ApplyFeeTypeEnum.CONSTRUCTION_SFEE.getValue());//1施工费 2第三方费用
		paymentApply.setIsDispatch(IsDispatchEnum.IS_DISPATCH.getValue());	//已派工
		paymentApplyDao.saveOrUpdate(paymentApply);
		if(flag){
			//增加待推送待办
			Staff staff = new Staff();
			staff.setStaffId(login.getStaffId());
			staff.setStaffName(login.getStaffName());
			operateRecordService.cerateCurOperateRecord(pro,paymentApply.getAuditState(), WorkFlowTypeEnum.ASSIST_PROGRESS.getValue(), AssistProgressTypeEnum.PAYMENT.getValue(),paymentApply.getPaId(),staff,null,true);
			
		}
	}
	
	/**
	 * 付款申请审核列表
	 * @author fuliwei
	 * @createTime 2017年8月7日
	 * @param 
	 * @return
	 */
	@Override
	public Map<String, Object> queryPaymentApplyAudit(PaymentApplyReq req) throws ParseException {
		String grade=null;
		
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
        SystemSet systemSet=systemSetDao.querySystemSetByStepId(req.getStepId(),loginInfo.getCorpId());
		
		if(systemSet!=null&&StringUtil.isNotBlank(systemSet.getTimeSpan())){
			req.setTimeLimit(Integer.parseInt(systemSet.getTimeSpan()));
		}
		
		Map<String, Object> result= paymentApplyDao.queryPaymentApplyAudit(req);
		List<PaymentApply> data = (List<PaymentApply>) result.get("data");
		
		if(data!=null && data.size()>0){
			for(int i = 0;i<data.size();i++){
				PaymentApply da=data.get(i);
				Project pro=new Project();

				//设计、监理、检测没有存储projId到申请表
				if(StringUtil.isNotBlank(data.get(i).getProjId())){
					pro = projectDao.get(data.get(i).getProjId());
				}else{
					//获取付款部门所在的公司ID
					Department department = departmentDao.get("deptId", StringUtil.isNotBlank(data.get(i).getOrgId())?data.get(i).getOrgId():"");
					pro.setCorpId(department.getOrgId());
					pro.setProjectType("0");//默认
				}


			//************************审核级别查询*********************************
				String stepId = StepOutWorkflowEnum.PAYMENT_AUDIT.getValue();
				String corfet = pro.getCorpId() + "_" + data.get(i).getFeeType();
				grade= docTypeService.queryAuditLevel(stepId, corfet, pro.getProjectType(), pro.getContributionMode());
				if (StringUtils.isBlank(grade)){
					grade = docTypeService.queryAuditLevel(stepId, pro.getCorpId(), pro.getProjectType(), pro.getContributionMode());
				}




				data.get(i).setLevel(grade);// 设置审核总级数（合同审核2级审核）


				if(StringUtils.isNotBlank(da.getPaAuditerId())){
					Staff staff=staffDao.get(da.getPaAuditerId());
					if(staff!=null){
						data.get(i).setPaAuditer(staff.getStaffName());
					}
				}
				
				if(StringUtils.isNotBlank(da.getProjId())){
					if(pro!=null){
						data.get(i).setProjectType(pro.getProjectType());
					}
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
				List<ManageRecord> mrls = manageRecordDao.findByStepIdBusinessOrderId(data.get(i).getPaId(),StepOutWorkflowEnum.PAYMENT_AUDIT.getValue(),MrResultEnum.PASSED.getValue());
				if(mrls!=null && mrls.size()>0){
					String size = mrls.size()+"";
					if(mrls.size()<Integer.parseInt(grade)){
						//遍历循环，获取审核是否通过
						for(ManageRecord mr:mrls){
							levelBtn.put("level"+mr.getMrAuditLevel(),mr.getMrResult());
						}
						//付款审核人配置的话，不能审核
						List<DataFilerSetUpDto> datafiltes = Constants.getDataFilterMapByKey(loginInfo.getStaffId()+"_"+(mrls.size()+1)+"_"+req.getMenuId());
						if(datafiltes!=null && datafiltes.size()>0 && loginInfo.getStaffId().equals(da.getPaAuditerId())){
							levelBtn.put("level" + (mrls.size() + 1), "-1");
						}else{
							levelBtn.put("level" + (mrls.size() + 1), "2");
						}
					}
				}else{
					//付款审核人配置的话，不能审核
					List<DataFilerSetUpDto> datafiltes = Constants.getDataFilterMapByKey(loginInfo.getStaffId()+"_"+(mrls.size() + 1)+"_"+req.getMenuId());
					if(datafiltes!=null && datafiltes.size()>0 && loginInfo.getStaffId().equals(da.getPaAuditerId())){
						levelBtn.put("level" + (mrls.size() + 1), "-1");
					}
				}
				data.get(i).setMrAuditLevel(JSONSerializer.toJSON(levelBtn).toString());
			}
			result.put("data", data);
		}
		return result;
	}
	
	/**
	 * 付款申请审核列表
	 * @author fuliwei
	 * @createTime 2017年8月7日
	 * @param 
	 * @return
	 */
	@Override
	public Map<String, Object> queryPaymentApply(PaymentApplyReq req) throws ParseException {
		return paymentApplyDao.queryPaymentApplyAudit(req);
	}
	
	/**
	 * 推送到待审核
	 * @author fuliwei
	 * @createTime 2017年8月16日
	 * @param 
	 * @return
	 */
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void pushPaymentApply(String paId) {
		PaymentApply paymentApply=paymentApplyDao.get(paId);
		Project pro = new Project();
		if(paymentApply!=null){
			paymentApply.setApplyDate(paymentApplyDao.getDatabaseDate());//申请时间
			paymentApply.setAuditState(AuditResultEnum.APPLYING.getValue());//审核中
			paymentApply.setSignBack("");
			pro = projectDao.get(paymentApply.getProjId());
			if(pro == null){
				pro = new Project();
			}
		}
		LoginInfo login=SessionUtil.getLoginInfo();
		paymentApply.setApplyer(login.getStaffName());
		paymentApply.setApplyerId(login.getStaffId());
		paymentApplyDao.saveOrUpdate(paymentApply);
		//付款审核待办todo：
		
		Staff staff = new Staff();
		staff.setStaffId(StringUtil.isNotBlank(paymentApply.getPaAuditerId())?paymentApply.getPaAuditerId():"");
		staff.setStaffName(StringUtil.isNotBlank(paymentApply.getPaAuditer())?paymentApply.getPaAuditer():"");
		operateRecordService.cerateCurOperateRecord(pro,paymentApply.getAuditState(), WorkFlowTypeEnum.ASSIST_PROGRESS.getValue(), AssistProgressTypeEnum.PAYMENT.getValue(),paymentApply.getPaId(),staff,"1",true);
		
	}

	/**
	 * 根据主键id查询详述
	 * @author fuliwei
	 * @createTime 2017年12月10日
	 * @param 
	 * @return
	 */
	@Override
	public PaymentApply findById(String paId) {
		return paymentApplyDao.get(paId);
	}
	
	/**
	 * 费用申请页面保存
	 * @author fuliwei
	 * @createTime 2017年12月11日
	 * @param 
	 * @return
	 */
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void saveCostPaymentApply(PaymentApplyReq paymentApplyReq) {

		Project pro = new Project();
		PaymentApply paymentApply=new PaymentApply();
		boolean flag= false;
		if(StringUtils.isBlank(paymentApplyReq.getPaId())){
			paymentApply.setPaId(IDUtil.getUniqueId(Constants.MODULE_CODE_SUBCONTRACT));
			//设置唯一付款申请编号
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			String date = sdf.format(paymentApplyDao.getDatabaseDate());
			String applyNo = paymentApplyDao.getMaxApplyNo(date);
			if(null == applyNo || "".equals(applyNo)){
				applyNo = date+"01";
			}
			paymentApply.setApplyNo(applyNo);
			flag = true;
		}else{
			paymentApply=paymentApplyDao.get(paymentApplyReq.getPaId());
		}
		
		paymentApply.setFeeType(paymentApplyReq.getFeeType());
		
		paymentApply.setApplyDate(paymentApplyDao.getDatabaseDate());
		LoginInfo login=SessionUtil.getLoginInfo();
		paymentApply.setApplyer(login.getStaffName());
		paymentApply.setApplyerId(login.getStaffId());
		paymentApply.setApplyDeptId(login.getDeptId());
		paymentApply.setApplyRemark(paymentApplyReq.getApplyRemark());
		paymentApply.setApplyAmount(paymentApplyReq.getApplyAmount());
		paymentApply.setApplyDeptName(paymentApplyReq.getApplyDeptName());
		paymentApply.setAuditUnit(paymentApplyReq.getAuditUnit());
		paymentApply.setAuditState(AuditResultEnum.NOT_APPLY.getValue());  //未推送
		paymentApply.setIsDispatch(IsDispatchEnum.NOT_DISPATCH.getValue());//未派工
		if(StringUtil.isNotBlank(paymentApplyReq.getFeeType()) && paymentApplyReq.getFeeType().equals(FeeTypeEnum.CHECK_FEE.getValue())){
			paymentApply.setApplyReason(CollectionTypeEnum.CHECK_FEE.getValue());
		}
		if(StringUtil.isNotBlank(paymentApplyReq.getFeeType()) && paymentApplyReq.getFeeType().equals(FeeTypeEnum.SU_FEE.getValue())){
			paymentApply.setApplyReason(CollectionTypeEnum.SU_FEE.getValue());
		}
		if(StringUtil.isNotBlank(paymentApplyReq.getFeeType()) && paymentApplyReq.getFeeType().equals(FeeTypeEnum.DESIGN_FEE.getValue())){
			paymentApply.setApplyReason(CollectionTypeEnum.DESIGN_FEE.getValue());
		}
		
		Department dept=departmentDao.queryDepartmentByDivide(DeptDivideEnum.PRE_SETTLEMENT_GROUP.getValue(),null);
		Department deptCustCenter=departmentDao.queryDepartmentByDivide(DeptDivideEnum.CUSTOMER_SERVICE_CENTER.getValue(),null);
		//todo:子公司的请款，付款单位部门不正确，默认集团了
		if(AuditUnitTypeEnum.CUSTOMER_SERVICE_CENTER.getValue().equals(paymentApply.getAuditUnit())){
			paymentApply.setOrgId(deptCustCenter.getDeptId());
			pro.setProjectType(ProjLtypeEnum.PIPE.getValue());//工程类型
			pro.setCorpId(deptCustCenter.getOrgId());//公司ID
		}else{
			paymentApply.setOrgId(dept.getDeptId());
			if(StringUtil.isNotBlank(paymentApply.getCorpId())){
				pro.setCorpId(paymentApply.getCorpId());//公司ID
			}else{
				pro.setCorpId(dept.getOrgId());//公司ID
			}
		}
		
		paymentApply.setApplyFeeType(ApplyFeeTypeEnum.THIRD_PARTY_FEE.getValue());
		paymentApplyDao.saveOrUpdate(paymentApply);
		
		
		//处理list数据
		if(paymentApplyReq.getList().size()>0){
			//先删除所有的
			feeApplyListDao.deleteByPaId(paymentApplyReq.getPaId());
			
			List<FeeApplyList> list=paymentApplyReq.getList();
			List<FeeApplyList> preList=new ArrayList<FeeApplyList>();
			for(FeeApplyList ps:list){
				ps.setPaId(paymentApply.getPaId());
				ps.setFalId(IDUtil.getUniqueId(Constants.MODULE_CODE_SUBCONTRACT));
				ps.setFeeType(paymentApplyReq.getFeeType());
				preList.add(ps);
			}
			feeApplyListDao.batchInsertObjects(preList);
		}else{
			feeApplyListDao.deleteByPaId(paymentApplyReq.getPaId());
		}
		if(flag){
			//增加待推送待办todo:
			Staff staff = new Staff();
			staff.setStaffId(login.getStaffId());
			staff.setStaffName(login.getStaffName());
			operateRecordService.cerateCurOperateRecord(pro,paymentApply.getAuditState(), WorkFlowTypeEnum.ASSIST_PROGRESS.getValue(), AssistProgressTypeEnum.PAYMENT.getValue(),paymentApply.getPaId(),staff,null,true);
		}
		
	}
	
	
	/**
	 * 保存审核派遣
	 * @author fuliwei
	 * @createTime 2017年12月21日
	 * @param 
	 * @return
	 */
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void saveCostPaymentDispatch(DesignDispatchDto designDispatchDto) {
		
		if(StringUtils.isNotBlank(designDispatchDto.getBusinessOrderId())){
			PaymentApply pa=paymentApplyDao.get(designDispatchDto.getBusinessOrderId());
			pa.setIsDispatch(IsDispatchEnum.IS_DISPATCH.getValue());	//已派工
			pa.setPaAuditerId(StringUtil.isNotBlank(designDispatchDto.getSurveyerId())?designDispatchDto.getSurveyerId():"");		//审核人
			pa.setPaAuditer(StringUtil.isNotBlank(designDispatchDto.getSurveyer())?designDispatchDto.getSurveyer():"");		//审核人
			paymentApplyDao.saveOrUpdate(pa);
			//审核待办todo:
			Staff staff = new Staff();
			staff.setStaffId(StringUtil.isNotBlank(designDispatchDto.getSurveyerId())?designDispatchDto.getSurveyerId():"");
			staff.setStaffName(StringUtil.isNotBlank(designDispatchDto.getSurveyer())?designDispatchDto.getSurveyer():"");
			Project pro = new Project();
			//传递付款单位ID
			Department dept=departmentDao.queryDepartmentByDivide(DeptDivideEnum.PRE_SETTLEMENT_GROUP.getValue(),null);
			Department deptCustCenter=departmentDao.queryDepartmentByDivide(DeptDivideEnum.CUSTOMER_SERVICE_CENTER.getValue(),null);
			//todo:子公司的请款，付款单位部门不正确，默认集团了
			if(AuditUnitTypeEnum.CUSTOMER_SERVICE_CENTER.getValue().equals(pa.getAuditUnit())){
				pro.setProjectType(ProjLtypeEnum.PIPE.getValue());//工程类型
				pro.setCorpId(deptCustCenter.getOrgId());//公司ID
			}else{
				if(StringUtil.isNotBlank(pa.getCorpId())){
					pro.setCorpId(pa.getCorpId());//公司ID
				}else{
					pro.setCorpId(dept.getOrgId());//公司ID
				}
			}
			operateRecordService.cerateCurOperateRecord(pro,AuditResultEnum.APPLYING.getValue(), WorkFlowTypeEnum.ASSIST_PROGRESS.getValue(), AssistProgressTypeEnum.PAYMENT.getValue(),pa.getPaId(),staff,"1",true);
		
		}
		
		
		
		
		
	}
	
	/**
	 * 查询预算员
	 * @author fuliwei
	 * @createTime 2017年12月21日
	 * @param 
	 * @return
	 */
	@Override
	public Map<String, Object> queryAuditer(DesignerQueryReq designerQueryReq) {
		return paymentApplyDao.queryAuditer(designerQueryReq);
	}
	
	
	/**
	 * 推送待审核派遣
	 * @author fuliwei
	 * @createTime 2017年8月16日
	 * @param 
	 * @return
	 */
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void pushCostApply(String paId) {
		PaymentApply paymentApply=paymentApplyDao.get(paId);
		if(paymentApply!=null){
			paymentApply.setApplyDate(paymentApplyDao.getDatabaseDate());//申请时间
			paymentApply.setAuditState(AuditResultEnum.APPLYING.getValue());//审核中
			paymentApply.setSignBack("");
		}
		//查询配置，如果子公司有配置，则不需要进行派遣 贵安需要派遣给现场代表一级审核
		//默认菜单ID为0
		/*List<DataFilerSetUpDto> list = Constants.getDataFilterMapByKey(paymentApply.getCorpId()+"_110614");
		if(list!=null && list.size()>0){
			paymentApply.setIsDispatch(IsDispatchEnum.IS_DISPATCH.getValue());
		}*/
		//待付款派工待办todo
		LoginInfo login=SessionUtil.getLoginInfo();
		paymentApply.setApplyer(login.getStaffName());
		paymentApply.setApplyerId(login.getStaffId());
		paymentApplyDao.saveOrUpdate(paymentApply);
		Staff staff = new Staff();
		staff.setStaffId(login.getStaffId());
		staff.setStaffName(login.getStaffName());
		Project pro = new Project();
		//传递付款单位ID
		pro.setCorpId(StringUtil.isNotBlank(paymentApply.getOrgId())?paymentApply.getOrgId():Constants.CORP_ID);
		operateRecordService.cerateCurOperateRecord(pro,AuditResultEnum.DISPATCH.getValue(), WorkFlowTypeEnum.ASSIST_PROGRESS.getValue(), AssistProgressTypeEnum.PAYMENT.getValue(),paymentApply.getPaId(),staff,null,true);
	}

	@Override
	public Map<String, Object> getpaymentpplyMap(PaymentApplyReq paymentApplyReq) {
		return paymentApplyDao.getpaymentpplyList(paymentApplyReq);
	}



	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void saveUpdate(PaymentApply pa) {
		paymentApplyDao.saveOrUpdate(pa);

		List<OperateRecord> recordList = operateRecordDao.queryOperateRecordList(pa.getPaId(), null, OperateWorkFlowEnum.WAIT_DONE.getValue(), null);
		if (recordList!=null && recordList.size()>0){
			for (OperateRecord o:recordList) {
				o.setModifyStatus(OperateWorkFlowEnum.NOT_DONE.getValue());//未办
				o.setIsValid("0");//无效
				operateRecordDao.saveOrUpdate(o);
			}
		}

	}

}
