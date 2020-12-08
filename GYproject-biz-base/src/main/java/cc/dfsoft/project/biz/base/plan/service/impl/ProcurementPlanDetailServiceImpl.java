package cc.dfsoft.project.biz.base.plan.service.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cc.dfsoft.project.biz.base.budget.dao.MaterialListDao;
import cc.dfsoft.project.biz.base.budget.entity.MaterialList;
import cc.dfsoft.project.biz.base.change.dao.MaterialChangeDao;
import cc.dfsoft.project.biz.base.change.entity.MaterialChange;
import cc.dfsoft.project.biz.base.plan.dao.ProcurementPlanDetailDao;
import cc.dfsoft.project.biz.base.plan.dto.ProcurementPlanDetailReq;
import cc.dfsoft.project.biz.base.plan.entity.ProcurementPlan;
import cc.dfsoft.project.biz.base.plan.entity.ProcurementPlanDetail;
import cc.dfsoft.project.biz.base.plan.service.ProcurementPlanDetailService;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.util.IDUtil;
import sun.tools.jar.resources.jar;

@Service
@Transactional(readOnly = true,propagation=Propagation.REQUIRED)
public class ProcurementPlanDetailServiceImpl implements ProcurementPlanDetailService {

	@Resource
	ProcurementPlanDetailDao procurementPlanDetailDao;
	@Resource
	MaterialListDao materialListDao;
	@Resource
	MaterialChangeDao materialChangeDao;
	/**
	 * 采购计划明细条件查询
	 */
	@Override
	public Map<String, Object> queryProcurementPlanDetail(ProcurementPlanDetailReq procurementPlanDetailReq)
			throws ParseException {
		return procurementPlanDetailDao.queryProcurementPlan(procurementPlanDetailReq);
	}

	/**
	 * 根据采购计划Id查采购计划明细
	 * @param procurPlanId
	 * @return
	 * @throws ParseException 
	 */
	@Override
	public List<ProcurementPlanDetail> findByProcurPlanId(String procurPlanId) throws ParseException {
		
		return procurementPlanDetailDao.findByProcurPlanId(procurPlanId);
	}
	
	@Override
	public List<ProcurementPlanDetail> findByPrrojId(String projId){
		
		return procurementPlanDetailDao.findByProjId(projId);
	}
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void saveDetail(ProcurementPlan procurementPlan){
		//保存采购计划明细
		List<MaterialList> materialLists = new ArrayList<MaterialList>();
		List<ProcurementPlanDetail> procurementPlanDetails = new ArrayList<ProcurementPlanDetail>();
		materialLists = materialListDao.queryMaterialListList(procurementPlan.getProjId());
		for(MaterialList materialList:materialLists){
			ProcurementPlanDetail procurementPlanDetail = new ProcurementPlanDetail();
			procurementPlanDetail.setProcurPlanId(procurementPlan.getProcurPlanId());
			procurementPlanDetail.setProcurPlanDetailId(IDUtil.getUniqueId(Constants.MODULE_CODE_CONSTRUCTIONPLAN));
			procurementPlanDetail.setProjId(procurementPlan.getProjId());
			procurementPlanDetail.setMaterialId(materialList.getMaterialId());
			procurementPlanDetail.setMaterialName(materialList.getMaterialName());
			procurementPlanDetail.setMaterialNum(materialList.getMaterialNum());
			procurementPlanDetail.setMaterialPrice(materialList.getMaterialPrice());
			procurementPlanDetail.setMaterialUnit(materialList.getMaterialUnit());
			procurementPlanDetail.setMaterialSpec(materialList.getMaterialSpec());
			procurementPlanDetails.add(procurementPlanDetail);
		}
		procurementPlanDetailDao.batchInsertObjects(procurementPlanDetails);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void savePlanDetail(ProcurementPlan procurementPlan,List<MaterialChange> materialChanges) {
		List<ProcurementPlanDetail> procurementPlanDetails = new ArrayList<ProcurementPlanDetail>();
		for(MaterialChange materialChange:materialChanges){
			ProcurementPlanDetail procurementPlanDetail = new ProcurementPlanDetail();
			procurementPlanDetail.setProcurPlanId(procurementPlan.getProcurPlanId());
			procurementPlanDetail.setProcurPlanDetailId(IDUtil.getUniqueId(Constants.MODULE_CODE_CHANGE));
			procurementPlanDetail.setProjId(procurementPlan.getProjId());
			procurementPlanDetail.setMaterialId(materialChange.getMaterialId());
			procurementPlanDetail.setMaterialName(materialChange.getMaterialName());
			procurementPlanDetail.setMaterialNum(materialChange.getAdjustQuantity());
			procurementPlanDetail.setMaterialUnit(materialChange.getMaterialUnit());
			procurementPlanDetail.setMaterialSpec(materialChange.getMaterialSpec());
			procurementPlanDetails.add(procurementPlanDetail);
		}
		procurementPlanDetailDao.batchInsertObjects(procurementPlanDetails);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void saveProcurementPlanDetail(ProcurementPlan procurementPlan){
		List<ProcurementPlanDetail> procurementPlanDetails = new ArrayList<ProcurementPlanDetail>();
		List<ProcurementPlanDetail> procurements = procurementPlanDetailDao.findByProjId(procurementPlan.getProjId());
		if(procurements!=null&&procurements.size()>0){
			for(int i = 0;i<procurements.size();i++){
				ProcurementPlanDetail procurementPlanDetail = new ProcurementPlanDetail();
				BigDecimal number = procurements.get(i).getMaterialNum();
				for(int j = 0;j<procurements.size();j++){
					if(procurements.get(i).getMaterialId().equals(procurements.get(j).getMaterialId())){
						if(i!=j){
							if(procurements.get(j).getMaterialNum()!=null){
								number = number.add(procurements.get(j).getMaterialNum());
							}
							procurements.remove(j);
						}
					}
				}
				procurementPlanDetail.setProcurPlanId(procurementPlan.getProcurPlanId());
				procurementPlanDetail.setProcurPlanDetailId(IDUtil.getUniqueId(Constants.MODULE_CODE_PROJECTSTART));
				procurementPlanDetail.setProjId(procurementPlan.getProjId());
				procurementPlanDetail.setMaterialId(procurements.get(i).getMaterialId());
				procurementPlanDetail.setMaterialName(procurements.get(i).getMaterialName());
				procurementPlanDetail.setMaterialPrice(procurements.get(i).getMaterialPrice());
				procurementPlanDetail.setMaterialUnit(procurements.get(i).getMaterialUnit());
				procurementPlanDetail.setMaterialNum(number);
				procurementPlanDetail.setMaterialSpec(procurements.get(i).getMaterialSpec());
				procurementPlanDetails.add(procurementPlanDetail);
			}
			procurementPlanDetailDao.batchInsertObjects(procurementPlanDetails);
		}
//		List<MaterialList> materialLists = new ArrayList<MaterialList>();
//		List<ProcurementPlanDetail> procurementPlanDetails = new ArrayList<ProcurementPlanDetail>();
//		List<MaterialChange> materialChanges =  new ArrayList<MaterialChange>();
//		materialLists = materialListDao.queryMaterialListList(procurementPlan.getProjId());
//		materialChanges = materialChangeDao.findByProjId(procurementPlan.getProjId());
//		if(materialLists!=null && materialLists.size()>0){
//			if(materialChanges==null){
//				this.saveDetail(procurementPlan);
//				return;
//			}else if(materialChanges.size()>0){
//				for(int i = 0;i<materialLists.size();i++){
//					ProcurementPlanDetail procurementPlanDetail = new ProcurementPlanDetail();
//					BigDecimal number = materialLists.get(i).getMaterialNum();
//						for(int j = 0;j<materialChanges.size();j++){
//							if(materialLists.get(i).getMaterialId().equals(materialChanges.get(j).getMaterialId())){
//								number.add(materialChanges.get(j).getAdjustQuantity());
//							}
//							
//						}
//						procurementPlanDetail.setProcurPlanId(procurementPlan.getProcurPlanId());
//						procurementPlanDetail.setProcurPlanDetailId(IDUtil.getUniqueId(Constants.MODULE_CODE_PROJECTSTART));
//						procurementPlanDetail.setProjId(procurementPlan.getProjId());
//						procurementPlanDetail.setMaterialName(materialLists.get(i).getMaterialName());
//						procurementPlanDetail.setMaterialPrice(materialLists.get(i).getMaterialPrice());
//						procurementPlanDetail.setMaterialUnit(materialLists.get(i).getMaterialUnit());
//						procurementPlanDetail.setMaterialNum(number);
//						procurementPlanDetail.setMaterialSpec(materialLists.get(i).getMaterialSpec());
//						procurementPlanDetails.add(procurementPlanDetail);
//				}
//				for(int i = 0;i<materialChanges.size();i++){
//					boolean flag = true;
//					ProcurementPlanDetail procurementPlanDetail = new ProcurementPlanDetail();
//						for(int j = 0;j<materialLists.size();j++){
//							if(materialLists.get(j).getMaterialId().equals(materialChanges.get(i).getMaterialId())){
//								flag =false;
//							}
//						}
//						if(flag){
//							procurementPlanDetail.setProcurPlanId(procurementPlan.getProcurPlanId());
//							procurementPlanDetail.setProcurPlanDetailId(IDUtil.getUniqueId(Constants.MODULE_CODE_PROJECTSTART));
//							procurementPlanDetail.setProjId(procurementPlan.getProjId());
//							procurementPlanDetail.setMaterialName(materialChanges.get(i).getMaterialName());
//							procurementPlanDetail.setMaterialUnit(materialChanges.get(i).getMaterialUnit());
//							procurementPlanDetail.setMaterialNum(materialChanges.get(i).getAdjustQuantity());
//							procurementPlanDetail.setMaterialSpec(materialChanges.get(i).getMaterialSpec());
//							procurementPlanDetails.add(procurementPlanDetail);
//						}
//						
//				}
//				
//				
//				
//				procurementPlanDetailDao.batchInsertObjects(procurementPlanDetails);
//			}
//
//	
//		}
	}
  }

