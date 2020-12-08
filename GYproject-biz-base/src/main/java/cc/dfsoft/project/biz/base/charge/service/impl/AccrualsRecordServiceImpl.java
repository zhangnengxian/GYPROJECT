package cc.dfsoft.project.biz.base.charge.service.impl;


import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cc.dfsoft.project.biz.base.budget.entity.GovAuditCost;
import cc.dfsoft.project.biz.base.charge.dao.AccrualsRecordDao;
import cc.dfsoft.project.biz.base.charge.entity.AccrualsRecord;
import cc.dfsoft.project.biz.base.charge.enums.ARFlagEnum;
import cc.dfsoft.project.biz.base.charge.enums.ARStatusEnum;
import cc.dfsoft.project.biz.base.charge.enums.ArContractTypeEnum;
import cc.dfsoft.project.biz.base.charge.service.AccrualsRecordService;
import cc.dfsoft.project.biz.base.contract.enums.PayTypeSCEnum;
import cc.dfsoft.project.biz.base.project.dao.ProjectDao;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.project.enums.CollectionTypeEnum;
import cc.dfsoft.project.biz.base.settlement.dao.SettlementDeclarationDao;
import cc.dfsoft.project.biz.base.settlement.entity.SettlementDeclaration;
import cc.dfsoft.project.biz.base.subpackage.dao.FeeApplyListDao;
import cc.dfsoft.project.biz.base.subpackage.dao.IntelligentMeterContractDao;
import cc.dfsoft.project.biz.base.subpackage.dao.PaymentApplyDao;
import cc.dfsoft.project.biz.base.subpackage.dao.SubContractDao;
import cc.dfsoft.project.biz.base.subpackage.dao.SubSupplyContractDao;
import cc.dfsoft.project.biz.base.subpackage.entity.FeeApplyList;
import cc.dfsoft.project.biz.base.subpackage.entity.PaymentApply;
import cc.dfsoft.project.biz.base.subpackage.entity.SubContract;
import cc.dfsoft.project.biz.base.subpackage.service.PaymentApplyService;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.entity.Staff;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.util.IDUtil;
import cc.dfsoft.uexpress.common.util.StringUtil;

/**
 * 应收应付服务接口实现类
 * @author songqn
 *
 */
@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class AccrualsRecordServiceImpl implements AccrualsRecordService{
	
	@Resource
	AccrualsRecordDao accrualsRecordDao;
	
	@Resource
	SubContractDao subContractDao;
	
	@Resource
	SubSupplyContractDao subSupplyContractDao;
	
	/**费用申请工程清单*/
	@Resource
	FeeApplyListDao feeApplyListDao;
	
	/**工程Dao*/
	@Resource
	ProjectDao projectDao;
	
	/**智能表合同DAO*/
	@Resource
	IntelligentMeterContractDao imcContractDao;
	
	/**
	 * 结算报审接口
	 */
	@Resource
	SettlementDeclarationDao settlementDeclarationDao;
	@Resource
	PaymentApplyDao paymentApplyDao;
	@Resource
	PaymentApplyService paymentApplyService;
	/**
	 * 保存应收应付记录
	 * @param project    工程
	 * @param arTypeID   收付款类型
	 * @param arFlag     收付标志
	 * @param amount     金额
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void insertAccrualsRecord(Project project, String arType ,int arFlag , BigDecimal amount){
		if (project!=null ) {
			AccrualsRecord accrualsRecord = new AccrualsRecord();
			accrualsRecord.setArId(IDUtil.getUniqueId(Constants.MODULE_CODE_COST));  // 主键ID
			
			if (StringUtils.isNotBlank(project.getProjId())) {    // 工程ID
				accrualsRecord.setProjId(project.getProjId());    
			}
			if (StringUtils.isNotBlank(project.getProjNo())) {    // 工程编号
				accrualsRecord.setProjNo(project.getProjNo());
			}
			if (StringUtils.isNotBlank(project.getCustNo())) {    // 客户ID
				accrualsRecord.setCustId(project.getCustNo());
			}
			if (StringUtils.isNotBlank(project.getCustName())) {  // 客户名称
				accrualsRecord.setProjCustName(project.getCustName());
			}
			
			accrualsRecord.setArType(arType);   //收付款类型
			accrualsRecord.setArFlag(arFlag);   //收付标志
			accrualsRecord.setArCost(amount);   //金额
			accrualsRecord.setArStatus(ARStatusEnum.TO_BE_CHARGE.getValue());  //待收费
			accrualsRecord.setArDate(accrualsRecordDao.getDatabaseDate());     //产生时间
			if (SessionUtil.getLoginInfo()!=null) {
				String staffId = SessionUtil.getLoginInfo().getStaffId();
				Staff staff =  new Staff();
				staff.setStaffId(staffId);
				accrualsRecord.setStaff(staff);
			}
			//付款是查询是否有付款申请，将申请单ID插入到应付流水
			/*if(accrualsRecord.getArFlag().equals(ARFlagEnum.ACCOUNTS_PAY.getValue())){
				PaymentApply paymentApply = paymentApplyService.findById(projId, paId);
			}*/
			accrualsRecordDao.save(accrualsRecord);
		}
	}

	/**
	 * 生成应收应付流水
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void insertAccrualsRecord(Project project, String arId, String arType, int arFlag, BigDecimal amount,String contractType,String conNo) {

		if (project!=null ) {
			AccrualsRecord accrualsRecord = new AccrualsRecord();
			accrualsRecord.setArId(arId);  // 主键ID
			
			if (StringUtils.isNotBlank(project.getProjId())) {    // 工程ID
				accrualsRecord.setProjId(project.getProjId());    
			}
			if (StringUtils.isNotBlank(project.getProjNo())) {    // 工程编号
				accrualsRecord.setProjNo(project.getProjNo());
			}
			if (StringUtils.isNotBlank(project.getCustId())) {    // 客户ID
				accrualsRecord.setCustId(project.getCustId());
			}
			if (StringUtils.isNotBlank(project.getCustName())) {  // 客户名称
				accrualsRecord.setProjCustName(project.getCustName());
			}
			
			accrualsRecord.setArType(arType);   //收付款类型
			accrualsRecord.setArFlag(arFlag);   //收付标志
			accrualsRecord.setArCost(amount);   //金额
			//合同类型：智能表合同、补充协议、主合同
			if(StringUtils.isNotBlank(contractType)){
				accrualsRecord.setContractType(contractType);//合同类型
			}else{
				accrualsRecord.setContractType(ArContractTypeEnum.CONSTRUCTION.getValue());//其他合同
			}
			//合同编号
			if(StringUtils.isNotBlank(conNo)){
				accrualsRecord.setConNo(conNo);
			}else{
				accrualsRecord.setConNo(project.getProjNo());
			}
		
				accrualsRecord.setArStatus(ARStatusEnum.TO_BE_CHARGE.getValue());  //待收款
			
			accrualsRecord.setArDate(accrualsRecordDao.getDatabaseDate());     //产生时间
			if (SessionUtil.getLoginInfo()!=null) {
				String staffId = SessionUtil.getLoginInfo().getStaffId();
				Staff staff =  new Staff();
				staff.setStaffId(staffId);
				accrualsRecord.setStaff(staff);
			}
			//将请款ID存入应付流水
			if(StringUtil.isNotBlank(project.getPaId())){
				accrualsRecord.setPaId(project.getPaId());
			}
			accrualsRecordDao.save(accrualsRecord);
		}
	
		
	}

	@Override
	public AccrualsRecord get(String arId) {
		return accrualsRecordDao.get(arId);
	}

	@Override
	public List<AccrualsRecord> findbyProjIdType(String projId, String projNo, String arFlag,String conNo) {
		
		return accrualsRecordDao.findbyProjIdType(projId, projNo, arFlag,conNo);
	}
	
	@Override
	public List<AccrualsRecord> findbyProjIdType(String projNo) {
		
		return accrualsRecordDao.findbyProjIdType(projNo);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void insertAccrualsRecord(Project project) throws Exception {
		if(project!=null){
			//查询分包合同记录
			SubContract subContract = subContractDao.findSubContractByprojId(project.getProjId());
			String ptDes = PayTypeSCEnum.valueof(subContract.getPayType()).getMessage();
			
			BigDecimal amount = subContract.getScAmount().multiply(new BigDecimal(ptDes.substring(0, ptDes.length()-1))).divide(new BigDecimal("100"));
			//查询分包协议记录 不影响流程2016-11-16 flw注 
			//SubSupplyContract ssc = subSupplyContractDao.findByProjId(project.getProjId());
			
			
			//结算定案表的材料扣款
			SettlementDeclaration sDeclaration = settlementDeclarationDao.findSByprojId(project.getProjId());
			BigDecimal meterAmount = sDeclaration.getMaterialCutPayment();
			if(meterAmount==null){
				meterAmount = new BigDecimal("0");
			}
			/*尾款 = 分包协议款-分包首款-材料扣款
			BigDecimal lastAmount = ssc.getSscAmount().subtract(amount).subtract(meterAmount);*/
			
			//尾款 = 结算定案终审价值-分包首款-通气尾款-材料扣款
			BigDecimal lastAmount = sDeclaration.getEndDeclarationCost().subtract(amount).subtract(meterAmount);
			//查询通气确认款
			AccrualsRecord ard = accrualsRecordDao.findByType(project.getProjId(), CollectionTypeEnum.GAS_CONFIRM.getValue());
			//存在通气确认款记录
			if(ard!=null){
				if(ard.getArCost()!=null){
					//再减去通气确认款
					lastAmount = lastAmount.subtract(ard.getArCost());
				}
			}
	        //形成协议尾款流水
	        this.insertAccrualsRecord(project, CollectionTypeEnum.SUBCONTRACT_BALANCE_PAYMENT.getValue(), Integer.parseInt(ARFlagEnum.ACCOUNTS_PAY.getValue()), lastAmount);
		}else{
			throw new Exception("project对象为空");
		} 
		
	}

	@Override
	public List<AccrualsRecord> findForUpdate(String projId, String arFlag, String contractType) {
		return accrualsRecordDao.findForUpdate(projId, arFlag,contractType);
	}
	
	
	/**
	 * 批量生成付款金额
	 * @author fuliwei
	 * @createTime 2017年12月25日
	 * @param paId
	 * @return  void
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void insertPaAccrualsRecord(String paId) {
		
		if(StringUtils.isNotBlank(paId)){
			List<FeeApplyList> list=feeApplyListDao.findByPaId(paId);
			PaymentApply pa=paymentApplyDao.get(paId);
			//查工程清单
			if(list!=null && list.size()>0){
				for(FeeApplyList fal:list){
					//查询工程 批次生成付款流水
					List<Project> proList=projectDao.findByProjNo(fal.getProjNo());
					if(proList!=null && proList.size()>0){
						
						AccrualsRecord accrualsRecord = new AccrualsRecord();
						accrualsRecord.setArId(IDUtil.getUniqueId(Constants.MODULE_CODE_COST));  // 主键ID
						
						if (StringUtils.isNotBlank(proList.get(0).getProjId())) {    // 工程ID
							accrualsRecord.setProjId(proList.get(0).getProjId());    
						}
						if (StringUtils.isNotBlank(proList.get(0).getProjNo())) {    // 工程编号
							accrualsRecord.setProjNo(proList.get(0).getProjNo());
						}
						
						accrualsRecord.setArType(pa.getApplyReason());   								  //收付款类型
						accrualsRecord.setArFlag(Integer.valueOf(ARFlagEnum.ACCOUNTS_PAY.getValue()));   //收付标志
						accrualsRecord.setArCost(fal.getApplyAmount());   								  //金额
						accrualsRecord.setArStatus(ARStatusEnum.TO_BE_CHARGE.getValue());  			//待收费
						accrualsRecord.setArDate(accrualsRecordDao.getDatabaseDate());     				//产生时间
						accrualsRecord.setPaId(paId);//请款单ID
						
						if (SessionUtil.getLoginInfo()!=null) {
							String staffId = SessionUtil.getLoginInfo().getStaffId();
							Staff staff =  new Staff();
							staff.setStaffId(staffId);
							accrualsRecord.setStaff(staff);
						}
						accrualsRecordDao.save(accrualsRecord);
					}
				}
			}
			
		}
	}

	/**
	 * 1.查询工程应收流水信息
	 * 2.修改尾款的应收金额为审定价减去收付款应收金额
	 */
	@Override
	public void updateByGovAuditCost(GovAuditCost govAuditCost) {
		//主合同的流水
		List<AccrualsRecord> list = accrualsRecordDao.findbyProjIdType(govAuditCost.getProjId(), govAuditCost.getProjNo(), null, govAuditCost.getProjNo());
		//存储阶段款
		AccrualsRecord accrualsRecord  = new AccrualsRecord();
		BigDecimal firstAmount = new BigDecimal(0);
		if(list!=null && list.size()>0){
			for(AccrualsRecord ar : list){
				if(CollectionTypeEnum.STAGE_PAYMENT.getValue().equals(ar.getArType())){
					accrualsRecord = ar;
				}else if(CollectionTypeEnum.INITIAL_PAYMENT.getValue().equals(ar.getArType())){
					firstAmount = firstAmount.add(ar.getArCost());
				}
			}
		}
		if(accrualsRecord!=null && StringUtil.isNotBlank(accrualsRecord.getArId())){
			accrualsRecord.setArCost((govAuditCost.getAuthorizedCost().subtract(firstAmount)));
			accrualsRecordDao.update(accrualsRecord);
		}
	}

	@Override
	public List<AccrualsRecord> getDataByProj(String projId, String arType, String arFlag, String arStatus) {
		
		
		return accrualsRecordDao.getDataByProj(projId,arType,arFlag,arStatus);
	}
}
