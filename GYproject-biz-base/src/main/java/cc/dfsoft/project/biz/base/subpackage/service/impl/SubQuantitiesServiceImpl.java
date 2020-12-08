package cc.dfsoft.project.biz.base.subpackage.service.impl;


import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cc.dfsoft.project.biz.base.constructmanage.dao.ProgressDao;
import cc.dfsoft.project.biz.base.constructmanage.entity.Progress;
import cc.dfsoft.project.biz.base.plan.dao.ConstructionPlanDao;
import cc.dfsoft.project.biz.base.plan.entity.ConstructionPlan;
import cc.dfsoft.project.biz.base.project.dao.ProjectDao;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.project.enums.StepEnum;
import cc.dfsoft.project.biz.base.project.service.OperateRecordService;
import cc.dfsoft.project.biz.base.settlement.dao.SettlementDeclarationDao;
import cc.dfsoft.project.biz.base.settlement.entity.SettlementDeclaration;
import cc.dfsoft.project.biz.base.settlement.enums.SubQuantitiesStatusEnum;
import cc.dfsoft.project.biz.base.settlement.service.SettlementAuditService;
import cc.dfsoft.project.biz.base.subpackage.dao.SubBudgetDao;
import cc.dfsoft.project.biz.base.subpackage.dao.SubContractDao;
import cc.dfsoft.project.biz.base.subpackage.dao.SubQuantitiesDao;
import cc.dfsoft.project.biz.base.subpackage.dto.SubQuantitiesDto;
import cc.dfsoft.project.biz.base.subpackage.dto.SubQuantitiesReq;
import cc.dfsoft.project.biz.base.subpackage.entity.SubBudget;
import cc.dfsoft.project.biz.base.subpackage.entity.SubQuantities;
import cc.dfsoft.project.biz.base.subpackage.service.SubQuantitiesService;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.util.IDUtil;
import cc.dfsoft.uexpress.common.util.ResultUtil;
import cc.dfsoft.uexpress.common.util.WorkFlowUtil;

@Service
@Transactional(readOnly = true,propagation=Propagation.REQUIRED)
public class SubQuantitiesServiceImpl implements SubQuantitiesService{
	
	@Resource
	SubQuantitiesDao subQuantitiesDao;
	@Resource
	ProjectDao projectDao;
	@Resource
	OperateRecordService operateRecordService;
	@Resource
	SubBudgetDao subBudgetDao;
	/**分包协议Dao*/
	@Resource
	SubContractDao subContractDao;
	
	/**工程进度dao*/
	@Resource
	ProgressDao progressDao;
	
	/**结算审核dao*/
	@Resource
	SettlementDeclarationDao settlementDeclarationDao;
	
	@Resource
	SettlementAuditService settlementAuditService;
	
	/**施工计划*/
	@Resource
	ConstructionPlanDao constructionPlanDao;
	
	/**
	 * 工程量审定页面查询分包工程量
	 * @author
	 * @createTime 2016-7-8
	 * @param	projId工程ID
	 * @return  分包工程量
	 */
	@Override
	public SubQuantities queryById(String projId) {
		SubQuantities quantities=subQuantitiesDao.queryInfoByProjId(projId);
		return quantities;
	}
	
	
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public String batInsertSubQualities(SubQuantitiesDto qdto) throws ParseException{
		List<SubQuantities> list=new ArrayList<SubQuantities>();
		
		//更新工程进度
		List<Progress> listProcess=new ArrayList<Progress>();
		
		String unitQueId;
		//添加分包工程量记录
		for(SubQuantities sq:qdto.getList()){
			//工程量标准
			unitQueId=IDUtil.getUniqueId(Constants.MODULE_CODE_SUBCONTRACT);
			sq.setSqId(unitQueId);
			list.add(sq);
			Progress pros=new Progress();
			//工程进度
			pros.setProjId(qdto.getProjId());	//工程进度projId
			pros.setProjNo(qdto.getProjNo());	//工程进度projNo
			pros.setProjName(qdto.getProjName());//工程名称
			pros.setProjScaleDes(qdto.getProjScaleDes());   //工程规模描述
			pros.setQuId(unitQueId);						//工程进度工程量id
			pros.setNuitProject(sq.getSqBranchProjName());  //分部分项名称
			pros.setSqUnit(sq.getSqUnit());				    //单位
			pros.setAllProgressNum(sq.getSqNum());		    //数量
			pros.setRegisterDate(progressDao.getDatabaseDate());//登记时间
			pros.setProgressId(IDUtil.getUniqueId(Constants.MODULE_CODE_PROCESS));
			listProcess.add(pros);
		}
		subQuantitiesDao.deleteByProjId(qdto.getProjId());
		progressDao.deleteByProjId(qdto.getProjId());
		
		subQuantitiesDao.batchInsertObjects(list);
		progressDao.batchInsertObjects(listProcess);
		
		//更新工程状态
		
		Project pro= projectDao.get(qdto.getProjId());
		
		//推送
		if("1".equals(qdto.getFlag())){
			for(SubQuantities sq:list){
				if(sq.getSqNum()==null){
					return "numberError";
				}
				
			}
			//形成操作记录
			String orId = IDUtil.getUniqueId(Constants.MODULE_CODE_SUBCONTRACT);//生成唯一ID
			operateRecordService.createOperateRecord(orId, pro.getProjId(), pro.getProjNo(),StepEnum.QUALITIES_DECLARATION.getValue(), StepEnum.QUALITIES_DECLARATION.getMessage(), "");
			pro.setProjStatusId(WorkFlowUtil.workFlowStatus(StepEnum.QUALITIES_DECLARATION.getValue()));
		}
		
		pro.setSubmitAmount(qdto.getSubmitAmount());  // 申报金额合计
	    projectDao.update(pro);
	    
		return Constants.OPERATE_RESULT_SUCCESS;
	
	}
	
	/**
	 * 工程量清单列表查询
	 * @param subQuantitiesReq
	 * @return
	 */
	@Override
	public Map<String, Object> queryQuantityStandard(SubQuantitiesReq subQuantitiesReq) throws ParseException {
		return subQuantitiesDao.queryPricedBoq(subQuantitiesReq);
	}


	@Override
	public Map<String, Object> queryQuantityStandardNoPage(SubQuantitiesReq subQuantitiesReq) throws ParseException {
		return subQuantitiesDao.queryQuantityStandardNoPage(subQuantitiesReq);
	}

	/**
	 * 预算查询工程量
	 * @author liaoyq
	 * @createTime 2017-8-10
	 */
	@Override
	public Map<String, Object> querySubQuantityStandard(SubQuantitiesReq subQuantitiesReq) throws ParseException {
		//根据工程ID查询结算信息
		SettlementDeclaration settlementDeclaration = settlementAuditService.findByProjId(subQuantitiesReq.getProjId());
		if(settlementDeclaration!=null){
			//已有结算
			subQuantitiesReq.setSbId(settlementDeclaration.getSdId());
			subQuantitiesReq.setSqStatus(SubQuantitiesStatusEnum.SETTLEMENT.getValue());
		}else{//没有结算，则根据工程ID查询预算工程量
			SubBudget budget = subBudgetDao.findByProjId(subQuantitiesReq.getProjId());
			if(budget!=null){
				subQuantitiesReq.setSbId(budget.getSbId());
				subQuantitiesReq.setSqStatus(SubQuantitiesStatusEnum.BUDGET.getValue());
			}
		}
		//没有工程量
		if(StringUtils.isBlank(subQuantitiesReq.getSbId())){
			List<SubQuantities> list = new ArrayList<SubQuantities>();
			return ResultUtil.pageResult(0, subQuantitiesReq.getDraw(),list);
		}
		Map<String, Object> result = subQuantitiesDao.querySubQuantityStandar(subQuantitiesReq);
		return result;
	}


	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void batInsertSubQualities(List<SubQuantities> list) throws ParseException {
		subQuantitiesDao.batchInsertObjects(list);
		
	}


	@Override
	public List<SubQuantities> exprotExcel(String projId,String sbId) {
		List<SubQuantities> sqlist= subQuantitiesDao.exprotExcel(sbId);
		SubBudget subBudget=subBudgetDao.get(sbId);
		if(projId!=null){
			SubQuantities sq=new SubQuantities();
			sq.setSqBranchProjName("合计");
			sq.setSqAmount(subBudget.getTotalAmount().doubleValue());//合计
			sqlist.add(sq);
		}
		return sqlist;
	}

	
	/**
	 * 查询详述
	 * @author fuliwei
	 * @createTime 2017年8月1日
	 * @param 
	 * @return
	 */
	@Override
	public Project findProjectByProjId(String projId) {
		Project proj=projectDao.get(projId);
		if(proj!=null){
			ConstructionPlan cp =constructionPlanDao.viewPlanById(projId);
			if(cp!=null){
				proj.setCuName(cp.getCuName());			 //施工单位
				proj.setCuPm(cp.getCuPm());//项目经理
			}
		}
		
		return null;
	}
}
