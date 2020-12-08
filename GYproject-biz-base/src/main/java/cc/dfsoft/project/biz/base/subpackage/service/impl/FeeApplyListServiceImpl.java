package cc.dfsoft.project.biz.base.subpackage.service.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cc.dfsoft.project.biz.base.constructmanage.dao.NdeRecordDao;
import cc.dfsoft.project.biz.base.constructmanage.entity.NdeRecord;
import cc.dfsoft.project.biz.base.plan.dao.ConstructionPlanDao;
import cc.dfsoft.project.biz.base.plan.entity.ConstructionPlan;
import cc.dfsoft.project.biz.base.plan.enums.IsDispatchEnum;
import cc.dfsoft.project.biz.base.project.dao.ProjectDao;
import cc.dfsoft.project.biz.base.project.dao.ProjectSignDao;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.project.entity.ProjectSign;
import cc.dfsoft.project.biz.base.project.enums.CollectionTypeEnum;
import cc.dfsoft.project.biz.base.project.enums.IsSignEnum;
import cc.dfsoft.project.biz.base.project.enums.ProjStypeEnum;
import cc.dfsoft.project.biz.base.project.enums.ProjectSignTypeEnum;
import cc.dfsoft.project.biz.base.project.enums.SignDataTypeEnum;
import cc.dfsoft.project.biz.base.settlement.dao.SettlementDeclarationDao;
import cc.dfsoft.project.biz.base.settlement.entity.SettlementDeclaration;
import cc.dfsoft.project.biz.base.settlement.enums.AuditResultEnum;
import cc.dfsoft.project.biz.base.subpackage.dao.FeeApplyListDao;
import cc.dfsoft.project.biz.base.subpackage.dao.PaymentApplyDao;
import cc.dfsoft.project.biz.base.subpackage.dto.PaymentApplyReq;
import cc.dfsoft.project.biz.base.subpackage.entity.FeeApplyList;
import cc.dfsoft.project.biz.base.subpackage.entity.PaymentApply;
import cc.dfsoft.project.biz.base.subpackage.enums.ApplyFeeTypeEnum;
import cc.dfsoft.project.biz.base.subpackage.enums.AuditUnitTypeEnum;
import cc.dfsoft.project.biz.base.subpackage.enums.FeeApplyResultEnum;
import cc.dfsoft.project.biz.base.subpackage.enums.FeeTypeEnum;
import cc.dfsoft.project.biz.base.subpackage.service.FeeApplyListService;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dao.DepartmentDao;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.biz.sys.dept.entity.Department;
import cc.dfsoft.uexpress.biz.sys.dept.enums.DeptDivideEnum;
import cc.dfsoft.uexpress.biz.sys.dept.enums.DeptTypeEnum;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.util.IDUtil;
import cc.dfsoft.uexpress.common.util.JsonUtils;
import cc.dfsoft.uexpress.common.util.StringUtil;
/**
 * 费用申请工程清单
 * @author fuliwei
 *
 */
@Service
@Transactional(readOnly = true,propagation=Propagation.REQUIRED)
public class FeeApplyListServiceImpl implements  FeeApplyListService {
	
	/**费用申请工程清单Dao*/
	@Resource
	FeeApplyListDao feeApplyListDao;
	
	/**工程Dao*/
	@Resource
	ProjectDao projectDao;
	
	/**付款申请Dao*/
	@Resource
	PaymentApplyDao paymentApplyDao;
	
	/**工程计划*/
	@Resource
	ConstructionPlanDao constructionPlanDao;
	
	/**结算信息*/
	@Resource
	SettlementDeclarationDao settlementDeclarationDao;
	
	/**探伤委托*/
	@Resource
	NdeRecordDao ndeRecordDao;
	
	/**部门*/
	@Resource
	DepartmentDao departmentDao;
	
	@Resource
	ProjectSignDao projectSignDao;
	
	/**
	 * 查询费用申请工程列表
	 * @author fuliwei
	 * @createTime 2017年12月10日
	 * @param  paymentApplyReq
	 * @return Map<String, Object>
	 */
	@Override
	public Map<String, Object> queryFeeApplyList(PaymentApplyReq paymentApplyReq) throws ParseException {
		Map<String, Object> map = feeApplyListDao.queryFeeApplyList(paymentApplyReq);
		List<FeeApplyList> list = (List<FeeApplyList>) map.get("data");
		if(list!=null && list.size()>0){
			for(FeeApplyList fa : list){
				//查询结算信息
				SettlementDeclaration sd = settlementDeclarationDao.findByProjId(fa.getProjId());
				if(sd!=null){
					fa.setEndSettlementCost(sd.getEndDeclarationCost());
				}
				//用户款项未清增加标记
				List<String> signTypes = new ArrayList<String>();
				signTypes.add(ProjectSignTypeEnum.SPECIAL.getValue());
				signTypes.add(ProjectSignTypeEnum.UNPAID.getValue());
				signTypes.add(ProjectSignTypeEnum.INCOMPLETE_COST.getValue());
				signTypes.add(ProjectSignTypeEnum.NO_SUP_MONEY.getValue());
				List<ProjectSign> projectSignList=projectSignDao.findByProjIdAndStatus(fa.getProjId(),IsSignEnum.IS_SIGN.getValue(),signTypes);
				if(projectSignList!=null && projectSignList.size()>0){
					fa.setIsSpecialSign(IsSignEnum.IS_SIGN.getValue());//是特殊工程
				}
			}
		}
		
		return map;
	}
	
	
	/**
	 * 批量导入清单
	 * @author fuliwei
	 * @createTime 2017年12月10日
	 * @param jsonArr
	 * @return void
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public String batInsertFeeApplyList(JSONArray jsonArr,PaymentApplyReq req) {
		if(FeeTypeEnum.DESIGN_FEE.getValue().equals(req.getFeeType())){
			//设计费
			return this.batInsertDesignFee(jsonArr, req);
		}else if(FeeTypeEnum.SU_FEE.getValue().equals(req.getFeeType())){
			//监理费
			return this.batInsertSuFee(jsonArr, req);
		}else if(FeeTypeEnum.CHECK_FEE.getValue().equals(req.getFeeType())){
			//检测费
			return this.batInsertCheckFee(jsonArr, req);
		}
		return Constants.OPERATE_RESULT_FAILURE;
	}

	/**
	 * 导入设计费
	 * @author fuliwei
	 * @createTime 2017年12月10日
	 * @param 
	 * @return String
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public String batInsertDesignFee(JSONArray jsonArr, PaymentApplyReq req) {
		PaymentApply apply=new PaymentApply();
		if(StringUtils.isBlank(req.getPaId())){
			apply.setPaId(IDUtil.getUniqueId(Constants.MODULE_CODE_SUBCONTRACT));
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			String date = sdf.format(paymentApplyDao.getDatabaseDate());
			String applyNo = paymentApplyDao.getMaxApplyNo(date);
			if(null == applyNo || "".equals(applyNo)){
				applyNo = date+"01";
			}
			apply.setApplyNo(applyNo);
			req.setPaId(apply.getPaId());
		}else{
			apply.setPaId(req.getPaId());
		}
		BigDecimal designAmount=BigDecimal.ZERO;

		List<FeeApplyList> list=new ArrayList<FeeApplyList>();
		//导入设计费
		for (int i = 0; i < jsonArr.size(); i++) {
			JSONObject jo = jsonArr.getJSONObject(i);
			FeeApplyList fal=JsonUtils.jsonToBean(jo, FeeApplyList.class);
			if(StringUtils.isNotBlank(fal.getProjNo())){
				//用工程No去查
				List<Project> proj=projectDao.findByProjNo(fal.getProjNo());
				if(proj!=null && proj.size()>0){
					apply.setCorpId(proj.get(0).getCorpId());
					apply.setProjId(proj.get(0).getProjId());
					//用工程id去查计划 是否下达 否则不允许
					List<ConstructionPlan> planList=constructionPlanDao.findByProjNo(proj.get(0).getProjNo());
					if(planList!=null && planList.size()>0){
						fal.setProjId(proj.get(0).getProjId());
						fal.setPaId(req.getPaId());
						fal.setFalId(IDUtil.getUniqueId(Constants.MODULE_CODE_SUBCONTRACT));
						
						if(fal.getApplyAmount()!=null){
							designAmount=designAmount.add(fal.getApplyAmount());
							apply.setApplyAmount(designAmount);	
						}
						fal.setFeeType(FeeTypeEnum.DESIGN_FEE.getValue());
						
						list.add(fal);
					}else{
						return fal.getProjNo()+FeeApplyResultEnum.HAVE_NOT_PLAN.getValue();
					}
					
				}else{
					//XX该工程不存在
					return fal.getProjNo()+FeeApplyResultEnum.HAVE_NOT_PROJ.getValue();
				}
			}else{
				//工程编号为空
				return FeeApplyResultEnum.HAVE_NOT_PROJ_NO.getValue();
			}
		}
		
		
		LoginInfo login=SessionUtil.getLoginInfo();
		apply.setApplyer(login.getStaffName());						//先存审核人，推送的时候再存申请时间
		apply.setApplyDeptName(login.getDeptName());				//申请部门
		apply.setApplyDeptId(login.getDeptId());
		apply.setAuditState(AuditResultEnum.NOT_APPLY.getValue());  //未推送
		apply.setIsDispatch(IsDispatchEnum.NOT_DISPATCH.getValue());//未派工
		apply.setFeeType(req.getFeeType());
		apply.setApplyFeeType(ApplyFeeTypeEnum.THIRD_PARTY_FEE.getValue());//第三方费用
		apply.setApplyDate(paymentApplyDao.getDatabaseDate());
		apply.setApplyRemark(req.getApplyRemark());
		if(StringUtil.isNotBlank(req.getApplyNo())){
			apply.setApplyNo(req.getApplyNo());
		}
		LoginInfo loginInfo =SessionUtil.getLoginInfo();
		Department dept=departmentDao.queryDepartmentByDivide(DeptDivideEnum.PRE_SETTLEMENT_GROUP.getValue(),null);
		apply.setOrgId(dept.getDeptId());
		apply.setApplyReason(CollectionTypeEnum.DESIGN_FEE.getValue());//设计费
		apply.setApplyAmount(designAmount);
		paymentApplyDao.saveOrUpdate(apply);
		
		
		feeApplyListDao.deleteByPaId(apply.getPaId());
		feeApplyListDao.batchInsertObjects(list);
		
		return Constants.OPERATE_RESULT_SUCCESS;
	}

	/**
	 * 导入监理费
	 * @author fuliwei
	 * @createTime 2017年12月10日
	 * @param 
	 * @return String
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public String batInsertSuFee(JSONArray jsonArr, PaymentApplyReq req) {
		PaymentApply apply=new PaymentApply();
		if(StringUtils.isBlank(req.getPaId())){
			apply.setPaId(IDUtil.getUniqueId(Constants.MODULE_CODE_SUBCONTRACT));
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			String date = sdf.format(paymentApplyDao.getDatabaseDate());
			String applyNo = paymentApplyDao.getMaxApplyNo(date);
			if(null == applyNo || "".equals(applyNo)){
				applyNo = date+"01";
			}
			apply.setApplyNo(applyNo);
			req.setPaId(apply.getPaId());
		}else{
			apply.setPaId(req.getPaId());
		}
		
		BigDecimal suAmount=BigDecimal.ZERO;
		
		List<FeeApplyList> list=new ArrayList<FeeApplyList>();
		//导入监理费
		for (int i = 0; i < jsonArr.size(); i++) {
			JSONObject jo = jsonArr.getJSONObject(i);
			FeeApplyList fal=JsonUtils.jsonToBean(jo, FeeApplyList.class);
			if(StringUtils.isNotBlank(fal.getProjNo())){
				//用工程No去查
				List<Project> proj=projectDao.findByProjNo(fal.getProjNo());
				if(proj!=null && proj.size()>0){
					apply.setCorpId(proj.get(0).getCorpId());
					apply.setProjId(proj.get(0).getProjId());
					//用工程id去查结算金额 否则不允许
					SettlementDeclaration sd=settlementDeclarationDao.findByProjId(proj.get(0).getProjId());
					if(sd!=null && sd.getEndDeclarationCost()!=null){
						fal.setProjId(proj.get(0).getProjId());
						fal.setPaId(req.getPaId());
						fal.setFalId(IDUtil.getUniqueId(Constants.MODULE_CODE_SUBCONTRACT));
						
						if(fal.getApplyAmount()!=null){
							suAmount=suAmount.add(fal.getApplyAmount());
						}

						fal.setFeeType(FeeTypeEnum.SU_FEE.getValue());
						list.add(fal);
					}else{
						return fal.getProjNo()+FeeApplyResultEnum.HAVE_NOT_SETTLEMENT.getValue();
					}
					
				}else{
					//XX该工程不存在
					return fal.getProjNo()+FeeApplyResultEnum.HAVE_NOT_PROJ.getValue();
				}
			}else{
				//工程编号为空
				return FeeApplyResultEnum.HAVE_NOT_PROJ_NO.getValue();
			}
		}
		
		LoginInfo login=SessionUtil.getLoginInfo();
		apply.setApplyer(login.getStaffName());						//先存审核人，推送的时候再存申请时间
		apply.setApplyDeptName(login.getDeptName());				//申请部门
		apply.setApplyDeptId(login.getDeptId());
		apply.setAuditState(AuditResultEnum.NOT_APPLY.getValue());  //未推送
		apply.setIsDispatch(IsDispatchEnum.NOT_DISPATCH.getValue());//未派工
		apply.setFeeType(req.getFeeType());
		apply.setApplyFeeType(ApplyFeeTypeEnum.THIRD_PARTY_FEE.getValue());
		apply.setApplyDate(paymentApplyDao.getDatabaseDate());
		apply.setApplyRemark(req.getApplyRemark());
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		//如果同一个监理单位申请不同分公司下的工程进行结算，会有问题
		Department dept=departmentDao.queryDepartmentByDivide(DeptDivideEnum.PRE_SETTLEMENT_GROUP.getValue(),null);
		apply.setOrgId(dept.getDeptId());
		
		if(StringUtil.isNotBlank(req.getApplyNo())){
			apply.setApplyNo(req.getApplyNo());
		}
		apply.setApplyReason(CollectionTypeEnum.SU_FEE.getValue());//监理费
		apply.setApplyAmount(suAmount);
		paymentApplyDao.saveOrUpdate(apply);
		
		feeApplyListDao.deleteByPaId(apply.getPaId());
		feeApplyListDao.batchInsertObjects(list);
		
		return Constants.OPERATE_RESULT_SUCCESS;
	}

	/**
	 * 导入检测费
	 * @author fuliwei
	 * @createTime 2017年12月10日
	 * @param 
	 * @return String
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public String batInsertCheckFee(JSONArray jsonArr, PaymentApplyReq req) {
		
		PaymentApply apply=new PaymentApply();
		if(StringUtils.isBlank(req.getPaId())){
			apply.setPaId(IDUtil.getUniqueId(Constants.MODULE_CODE_SUBCONTRACT));
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			String date = sdf.format(paymentApplyDao.getDatabaseDate());
			String applyNo = paymentApplyDao.getMaxApplyNo(date);
			if(null == applyNo || "".equals(applyNo)){
				applyNo = date+"01";
			}
			apply.setApplyNo(applyNo);
			
			req.setPaId(apply.getPaId());
		}else{
			apply.setPaId(req.getPaId());
		}
		
		BigDecimal checkAmount=BigDecimal.ZERO;
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		List<FeeApplyList> list=new ArrayList<FeeApplyList>();
		
		Department deptCustCenter=departmentDao.queryDepartmentByDivide(DeptDivideEnum.CUSTOMER_SERVICE_CENTER.getValue(),null);
		String deptCustCenterId = deptCustCenter.getDeptId().substring(0, deptCustCenter.getDeptId().length() - DeptTypeEnum.SUBCOMPANY.getInitVal().length());
		//1101
		
		Department deptCustMarket=departmentDao.queryDepartmentByDivide(DeptDivideEnum.PRE_SETTLEMENT_GROUP.getValue(),null);
		String deptCustMarketId = deptCustMarket.getDeptId().substring(0, deptCustMarket.getDeptId().length() - DeptTypeEnum.SUBCOMPANY.getInitVal().length());
		//110101
		
		
		//导入监理费
		for (int i = 0; i < jsonArr.size(); i++) {
			JSONObject jo = jsonArr.getJSONObject(i);
			FeeApplyList fal=JsonUtils.jsonToBean(jo, FeeApplyList.class);
			if(StringUtils.isNotBlank(fal.getProjNo())){
				//用工程No去查
				List<Project> proj=projectDao.findByProjNo(fal.getProjNo());
				
				if(proj!=null && proj.size()>0){
					apply.setCorpId(proj.get(0).getCorpId());
					apply.setProjId(proj.get(0).getProjId());
					String projDeptId=proj.get(0).getDeptId().substring(0, proj.get(0).getDeptId().length()-DeptTypeEnum.SUBCOMPANY.getInitVal().length());
					//先去查审核部门和工程类型是否一致
					if(AuditUnitTypeEnum.CUSTOMER_SERVICE_CENTER.getValue().equals(req.getAuditUnit())){
						//客服中心
						if(!deptCustCenterId.equals(projDeptId)){
							return fal.getProjNo()+FeeApplyResultEnum.HAVE_NOT_AUDIT_UNIT.getValue();
						}
					}else if(AuditUnitTypeEnum.MARKET_DEVELOPMENT_DEPARTMENT.getValue().equals(req.getAuditUnit())){
						//市场部
						if(!deptCustMarketId.equals(projDeptId)){
							return fal.getProjNo()+FeeApplyResultEnum.HAVE_NOT_AUDIT_UNIT.getValue();
						}
					}
					//用工程id去查无损检测 否则不允许报金额
					NdeRecord sd=ndeRecordDao.findBypProjId(proj.get(0).getProjId());
					if(sd!=null){
						fal.setProjId(proj.get(0).getProjId());
						fal.setPaId(req.getPaId());
						fal.setFalId(IDUtil.getUniqueId(Constants.MODULE_CODE_SUBCONTRACT));
						

						if(fal.getApplyAmount()!=null){
							checkAmount=checkAmount.add(fal.getApplyAmount());
						}
						fal.setFeeType(FeeTypeEnum.CHECK_FEE.getValue());
						list.add(fal);
					}else{
						return fal.getProjNo()+FeeApplyResultEnum.HAVE_NOT_NDE.getValue();
					}
				}else{
					//XX该工程不存在
					return fal.getProjNo()+FeeApplyResultEnum.HAVE_NOT_PROJ.getValue();
				}
			}else{
				//工程编号为空
				return FeeApplyResultEnum.HAVE_NOT_PROJ_NO.getValue();
			}
		}
		
		
		
		LoginInfo login=SessionUtil.getLoginInfo();
		apply.setApplyer(login.getStaffName());						//先存审核人，推送的时候再存申请时间
		apply.setApplyDeptName(login.getDeptName());				//申请部门
		apply.setApplyDeptId(login.getDeptId());
		apply.setAuditState(AuditResultEnum.NOT_APPLY.getValue());  //未推送
		apply.setIsDispatch(IsDispatchEnum.NOT_DISPATCH.getValue());//未派工
		apply.setFeeType(req.getFeeType());
		apply.setApplyFeeType(ApplyFeeTypeEnum.THIRD_PARTY_FEE.getValue());
		apply.setApplyDate(paymentApplyDao.getDatabaseDate());
		apply.setApplyRemark(req.getApplyRemark());
		Department dept=departmentDao.queryDepartmentByDivide(DeptDivideEnum.PRE_SETTLEMENT_GROUP.getValue(),null);
		
		if(AuditUnitTypeEnum.CUSTOMER_SERVICE_CENTER.getValue().equals(req.getAuditUnit())){
			apply.setOrgId(deptCustCenter.getDeptId());
		}else{
			apply.setOrgId(dept.getDeptId());
		}
		
		if(StringUtil.isNotBlank(req.getApplyNo())){
			apply.setApplyNo(req.getApplyNo());
		}
		
		
		apply.setAuditUnit(req.getAuditUnit());
		apply.setApplyReason(CollectionTypeEnum.CHECK_FEE.getValue());//检测费
		
		
		
		apply.setApplyAmount(checkAmount);
		paymentApplyDao.saveOrUpdate(apply);
		
		feeApplyListDao.deleteByPaId(apply.getPaId());
		feeApplyListDao.batchInsertObjects(list);
		
		return Constants.OPERATE_RESULT_SUCCESS;
	}

	
	/**
	 * 删除记录
	 * @author fuliwei
	 * @createTime 2017年12月11日
	 * @param 
	 * @return
	 */
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void deleteByPaId(String paId) {
		feeApplyListDao.delete(paId);
	}

}
