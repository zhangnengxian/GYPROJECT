package cc.dfsoft.project.biz.base.plan.service.impl;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cc.dfsoft.project.biz.base.contract.dao.ContractDao;
import cc.dfsoft.project.biz.base.contract.entity.Contract;
import cc.dfsoft.project.biz.base.plan.dao.ProcurementPlanDao;
import cc.dfsoft.project.biz.base.plan.dto.ProcurementPlanReq;
import cc.dfsoft.project.biz.base.plan.entity.ProcurementPlan;
import cc.dfsoft.project.biz.base.plan.enums.ProcurementPlanExport;
import cc.dfsoft.project.biz.base.plan.service.ProcurementPlanDetailService;
import cc.dfsoft.project.biz.base.plan.service.ProcurementPlanService;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.common.constant.Constants;

@Service
@Transactional(readOnly = true,propagation=Propagation.REQUIRED)
public class ProcurementPlanServiceImpl implements ProcurementPlanService {
	
	@Resource
	ProcurementPlanDao procurementPlanDao;
	
	@Resource
	ProcurementPlanDetailService procurementPlanDetailService;
	@Resource
	ContractDao contractDao;
	/**
	 * 采购计划条件列表查询
	 */
	@Override
	public Map<String, Object> queryProcurementPlan(ProcurementPlanReq procurementPlanReq) throws ParseException{
		Map<String, Object> map= procurementPlanDao.queryProcurementPlan(procurementPlanReq);
		List<ProcurementPlan> list = (List<ProcurementPlan>) map.get("data");
		for(ProcurementPlan procure : list){
			Contract contract = contractDao.viewContractByprojId(procure.getProjId());
			if(null!=contract){
				//procure.setProjTypeDes(contract.getProjTypeDes());
			}else{
				procure.setProjTypeDes("未确定");
			}
			
		}
		return map;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void saveProcurementPlan(ProcurementPlan procurementPlan) {
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
										
		procurementPlan.setCreateTime(procurementPlanDao.getDatabaseDate());
		procurementPlan.setCreateStaffId(loginInfo.getStaffId());
		procurementPlan.setCreateStaffName(loginInfo.getStaffName());
		procurementPlanDao.saveOrUpdate(procurementPlan);
		
	}

	@Override
	public List<ProcurementPlan> queryProcuPlanById(String projId) {
		return procurementPlanDao.queryProcuPlanById(projId);
	}

	@Override
	public ProcurementPlan findByBusiness(String businessOrderId) {
		return procurementPlanDao.get("businessOrderId", businessOrderId);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public String signprocurementPlanExport(String procurPlanId) {
		ProcurementPlan procuer =procurementPlanDao.get(procurPlanId);
		if(procuer!=null){
			//标记已导出
			procuer.setIsExport(ProcurementPlanExport.ALREADY_EXPORT.getValue());
			procurementPlanDao.update(procuer);
		}
		return Constants.OPERATE_RESULT_SUCCESS;
	}
}
