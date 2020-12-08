package cc.dfsoft.project.biz.base.project.dao.impl;

import cc.dfsoft.project.biz.base.project.dao.OperateWorkFlowDao;
import cc.dfsoft.project.biz.base.project.dto.OperateWorkFlowReq;
import cc.dfsoft.project.biz.base.project.entity.OperateWorkFlow;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;
import cc.dfsoft.uexpress.common.util.ResultUtil;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
@Repository
public class OperateWorkFlowDaoImpl extends NewBaseDAO<OperateWorkFlow, String> implements OperateWorkFlowDao{
	
	/**
	 * 查询流程配置标准
	 * @author fuliwei  
	 * @date 2018年9月7日  
	 * @version 1.0
	 */
	@Override
	public Map<String,Object> queryList(OperateWorkFlowReq req) {
		 Criteria c = super.getCriteria();
		
		 //分公司id
		 if(StringUtils.isNotBlank(req.getCorpId())){
			 c.add(Restrictions.like("corpId","%"+req.getCorpId()+"%"));
		 }
		 
		 //步骤id
		 if(StringUtils.isNotBlank(req.getStepId())){
			 c.add(Restrictions.like("stepId",req.getStepId()));
		 }
		 
		 //工程类型id
		 if(StringUtils.isNotBlank(req.getProjectType())){
			 c.add(Restrictions.like("projectType",req.getProjectType()));
		 }
		 //出资方式id
		 if(StringUtils.isNotBlank(req.getContributionMode())){
			 c.add(Restrictions.like("contributionMode",req.getContributionMode()));
		 }
		 
		 //操作人
		 if(StringUtils.isNotBlank(req.getOperater())){
			 c.add(Restrictions.like("opereater","%"+req.getOperater()+"%"));
		 }
		 c.addOrder(Order.asc("stepId"));  //按照stepId排序
		 // 数据库根据条件过滤记录数
		 int filterCount = this.countByCriteria(c);
	
		 // 根据条件获取查询信息
		 List<OperateWorkFlow> projectTypeList = this.findBySortCriteria(c, req);
			
		 // 返回结果
		 return ResultUtil.pageResult( filterCount, req.getDraw(),projectTypeList); 
	}
	
	/**
	 * 通过条件查询查询流程配置标准
	 * @author fuliwei  
	 * @date 2018年9月7日  
	 * @version 1.0
	 */
	@Override
	public List<OperateWorkFlow> queryListByReq(OperateWorkFlowReq req) {
		 Criteria c = super.getCriteria();
			
		 //分公司id
		if(StringUtils.isNotBlank(req.getCorpId())){
			c.add(Restrictions.like("corpId",req.getCorpId()+"%"));
		 }
		 
		 //步骤id
		 if(StringUtils.isNotBlank(req.getStepId())){
			 c.add(Restrictions.like("stepId",req.getStepId()));
		 }

		//步骤ids
		if(req.getStepIds()!=null && req.getStepIds().size()>0){
			c.add(Restrictions.in("stepId",req.getStepIds()));
		}

		 //工程类型id
		 if(StringUtils.isNotBlank(req.getProjectType())){
			 c.add(Restrictions.like("projectType",req.getProjectType()));
		 }
		 //出资方式id
		 if(StringUtils.isNotBlank(req.getContributionMode())){
			 c.add(Restrictions.like("contributionMode",req.getContributionMode()));
		 }
		/* //流程类型
		 if(StringUtils.isNotBlank(req.getOpType())){
			 c.add(Restrictions.like("opType",req.getOpType()));
		 }*/
		 
		 //操作流程类型 1 代表主流程，2代表辅助流程
		 if(StringUtils.isNotBlank(req.getOpType())){
			 c.add(Restrictions.like("opType",req.getOpType()));
		 }
		 if(StringUtils.isNotBlank(req.getGrade())){
			 c.add(Restrictions.like("grade",req.getGrade()));
		 }
		  c.addOrder(Order.desc("grade"));  //倒序排列
		 List<OperateWorkFlow> operateWorkFlowList = c.list();
		 return operateWorkFlowList;
	}
	
	/**
	 * 查询标准
	 * @author fuliwei  
	 * @date 2018年10月17日  
	 * @version 1.0
	 */
	@Override
	public OperateWorkFlow findByGrade(String corpId, String projectType, String conMode, String stepId, String grade,
			String opType) {
		
		Criteria c = super.getCriteria();
		 //分公司id
		 if(StringUtils.isNotBlank(corpId)){
			 c.add(Restrictions.like("corpId","%"+corpId+"%"));
		 }
		 
		 //工程类型id
		 if(StringUtils.isNotBlank(projectType)){
			 c.add(Restrictions.like("projectType",projectType));
		 }
		 //出资方式id
		 if(StringUtils.isNotBlank(conMode)){
			 c.add(Restrictions.like("contributionMode",conMode));
		 }
		 
		 //步骤id
		 if(StringUtils.isNotBlank(stepId)){
			 c.add(Restrictions.like("stepId",stepId));
		 }
		 
		 //审核级别
		 if(StringUtils.isNotBlank(grade)){
			 c.add(Restrictions.like("grade",grade));
		 }
		 
		 //类型
		 if(StringUtils.isNotBlank(opType)){
			 c.add(Restrictions.like("opType",opType));
		 }
		 List<OperateWorkFlow> operateWorkFlowList = c.list();
		 
		 if(operateWorkFlowList!=null &&operateWorkFlowList.size()>0){
			 return  operateWorkFlowList.get(0);
		 }
		 
		return null;
	}

	@Override
	public List<OperateWorkFlow> queryOperateWorkFlowList(String corpId, String projectType, String contributionMode, List<String> stepIds) {
		Criteria c = super.getCriteria();

		if(StringUtils.isNotBlank(corpId)){//分公司id
			c.add(Restrictions.eq("corpId",corpId));
		}

		if(StringUtils.isNotBlank(projectType)){//工程类型
			c.add(Restrictions.eq("projectType",projectType));
		}

		if(StringUtils.isNotBlank(contributionMode)){//出资方式
			c.add(Restrictions.eq("contributionMode",contributionMode));
		}

		if (stepIds!=null && stepIds.size()>0){
			c.add(Restrictions.in("stepId",stepIds));
		}

		List<OperateWorkFlow> owfList = this.findByCriteria(c);

		return owfList;
	}

}
