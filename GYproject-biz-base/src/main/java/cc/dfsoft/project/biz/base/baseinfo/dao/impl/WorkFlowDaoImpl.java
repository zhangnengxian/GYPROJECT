package cc.dfsoft.project.biz.base.baseinfo.dao.impl;


import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import java.util.Map;



import org.springframework.stereotype.Repository;

import cc.dfsoft.project.biz.base.baseinfo.dao.WorkFlowDao;
import cc.dfsoft.project.biz.base.baseinfo.dto.WorkFlowReq;
import cc.dfsoft.project.biz.base.baseinfo.entity.WorkFlow;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;
import cc.dfsoft.uexpress.common.util.ResultUtil;

/**
 * 工程流程DAO实现
 * @author Yuanyx
 *
 */
@Repository
public class WorkFlowDaoImpl extends NewBaseDAO<WorkFlow,String> implements WorkFlowDao{

	@Override
	public Map<String, Object> queryWorkFlowList(WorkFlowReq workFlowReq) {
		Criteria c = super.getCriteria();	
		//流程主键id
		if(StringUtils.isNotBlank(workFlowReq.getWfId())){
			c.add(Restrictions.eq("wfId",workFlowReq.getWfId()));
		}
		//分公司id
		if(StringUtils.isNotBlank(workFlowReq.getCorpId())){
			c.add(Restrictions.eq("corpId",workFlowReq.getCorpId()));
		}
		
		// 数据库根据条件过滤记录数
		int filterCount = this.countByCriteria(c);

		// 根据条件获取查询信息
		List<WorkFlow> list = this.findBySortCriteria(c, workFlowReq);
		 
		// 返回结果
		return ResultUtil.pageResult(filterCount, workFlowReq.getDraw(),list);
	}
	
	/**
	 * 查询工作流，返回下一状态id
	 * @author fuliwei
	 * @createTime 2017年3月10日
	 * @param corpId所属公司id typeID类型id
	 * @return
	 */
	@Override
	public WorkFlow queryProjStatusId(String corpId, String projType,String contributionCode,String typeId,String assistTypeId) {
		Criteria criteria=super.getCriteria();
		//所属公司id
		if(StringUtils.isNotBlank(corpId)){
			criteria.add(Restrictions.eq("corpId", corpId));
		}
		//工程类型id
		if(StringUtils.isNotBlank(projType)){
			criteria.add(Restrictions.eq("projType", projType));
		}
		//出资方式id
		if(StringUtils.isNotBlank(contributionCode)){
			criteria.add(Restrictions.eq("contributionCode", contributionCode));
		}
		
		//工作流类型1 主流程2 辅助流程
		if(StringUtils.isNotBlank(typeId)){
			criteria.add(Restrictions.eq("typeId", typeId));
		}
		
		//辅助流程类型 如1通气 2签证 3变更 
		if(StringUtils.isNotBlank(assistTypeId)){
			criteria.add(Restrictions.eq("assistTypeId", assistTypeId));
		}
		
		
		List<WorkFlow> list=this.findByCriteria(criteria);
		if (list !=null && list.size()>0) {
			return list.get(0);
		}
		return null;
	}
	
}
