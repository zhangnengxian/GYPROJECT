package cc.dfsoft.project.biz.base.project.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cc.dfsoft.project.biz.base.project.dao.OperateWorkFlowRecordDao;
import cc.dfsoft.project.biz.base.project.dto.OperateWorkFlowReq;
import cc.dfsoft.project.biz.base.project.entity.OperateWorkFlow;
import cc.dfsoft.project.biz.base.project.entity.OperateWorkFlowRecord;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;
import cc.dfsoft.uexpress.common.util.ResultUtil;
@Repository
public class OperateWorkFlowRecordDaoImpl extends NewBaseDAO<OperateWorkFlowRecord, String> implements OperateWorkFlowRecordDao{

	@Override
	public Map<String, Object> queryList(OperateWorkFlowReq req) {
		 Criteria c = super.getCriteria();
			
		 //分公司id
		 if(StringUtils.isNotBlank(req.getCorpId())){
			 c.add(Restrictions.like("corpId","%"+req.getCorpId()+"%"));
		 }
		 
		 //步骤id
		 if(StringUtils.isNotBlank(req.getStepId())){
			 c.add(Restrictions.eq("stepId",req.getStepId()));
		 }
		 
		 //工程类型id
		 if(StringUtils.isNotBlank(req.getProjectType())){
			 c.add(Restrictions.eq("projectType",req.getProjectType()));
		 }
		 //出资方式id
		 if(StringUtils.isNotBlank(req.getContributionMode())){
			 c.add(Restrictions.eq("contributionMode",req.getContributionMode()));
		 }
		 
		//工程id
		 if(StringUtils.isNotBlank(req.getProjId())){
			 c.add(Restrictions.eq("projId",req.getProjId()));
		 }
		 //状态
		 if(StringUtils.isNotBlank(req.getModifyStatus())){
			 c.add(Restrictions.eq("modifyStatus",req.getModifyStatus()));
		 }
		 
		
		 if(StringUtils.isNotBlank(req.getOperaterId())){
			 c.add(Restrictions.eq("opereaterId",req.getOperaterId()));
		 }
		 c.addOrder(Order.asc("stepId"));
		 // 数据库根据条件过滤记录数
		 int filterCount = this.countByCriteria(c);
	
		 // 根据条件获取查询信息
		 List<OperateWorkFlowRecord> projectTypeList = this.findBySortCriteria(c, req);
			
		 // 返回结果
		 return ResultUtil.pageResult( filterCount, req.getDraw(),projectTypeList); 
	}

	@Override
	public List<OperateWorkFlowRecord> queryListByReq(OperateWorkFlowReq req) {
		 Criteria c = super.getCriteria();
			
		 //分公司id
		 if(StringUtils.isNotBlank(req.getCorpId())){
			 c.add(Restrictions.like("corpId","%"+req.getCorpId()+"%"));
		 }
		 
		 //步骤id
		 if(StringUtils.isNotBlank(req.getStepId())){
			 c.add(Restrictions.eq("stepId",req.getStepId()));
		 }
		 
		 //工程类型id
		 if(StringUtils.isNotBlank(req.getProjectType())){
			 c.add(Restrictions.eq("projectType",req.getProjectType()));
		 }
		 //出资方式id
		 if(StringUtils.isNotBlank(req.getContributionMode())){
			 c.add(Restrictions.eq("contributionMode",req.getContributionMode()));
		 }
		 //工程id
		 if(StringUtils.isNotBlank(req.getProjId())){
			 c.add(Restrictions.eq("projId",req.getProjId()));
		 }
		 
		 //状态
		 if(StringUtils.isNotBlank(req.getModifyStatus())){
			 c.add(Restrictions.eq("modifyStatus",req.getModifyStatus()));
		 }
		 
		 //操作人
		 if(StringUtils.isNotBlank(req.getOperaterId())){
			 c.add(Restrictions.eq("opereaterId",req.getOperaterId()));
		 }
		 
		 
		 List<OperateWorkFlowRecord> operateWorkFlowList = c.list();
		 return operateWorkFlowList;
	}

}
