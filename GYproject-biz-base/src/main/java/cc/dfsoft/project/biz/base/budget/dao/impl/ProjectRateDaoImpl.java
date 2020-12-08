package cc.dfsoft.project.biz.base.budget.dao.impl;


import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cc.dfsoft.project.biz.base.budget.dao.ProjectRateDao;
import cc.dfsoft.project.biz.base.budget.dto.ProjectRateReq;
import cc.dfsoft.project.biz.base.budget.entity.ProjectRate;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;
import cc.dfsoft.uexpress.common.util.ResultUtil;

@Repository
public class ProjectRateDaoImpl extends NewBaseDAO<ProjectRate, String> implements ProjectRateDao{

	@Override
	public List<ProjectRate> queryProjectRate(ProjectRateReq projectRateReq) {
		Criteria c = super.getCriteria();
		//根据rateId进行查询
		if(StringUtils.isNotBlank(projectRateReq.getRateId())){
			 c.add(Restrictions.eq("rateId",projectRateReq.getRateId()));
		}
		
		// 数据库根据条件过滤记录数
		int filterCount = this.countByCriteria(c);
		// 根据条件获取查询信息
		List<ProjectRate> list = this.findByCriteria(c);
		// 返回结果
		return list;
	}

}