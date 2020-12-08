package cc.dfsoft.project.biz.base.project.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cc.dfsoft.project.biz.base.project.dao.ContributionModeDao;
import cc.dfsoft.project.biz.base.project.dto.ProjectScaleReq;
import cc.dfsoft.project.biz.base.project.entity.ContributionMode;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;
import cc.dfsoft.uexpress.common.util.ResultUtil;

@Repository
public class ContributionModeDaoImpl extends NewBaseDAO<ContributionMode, String> implements ContributionModeDao {

	@Override
	public List<ContributionMode> findById(String typeId) {
		Criteria c = super.getCriteria();
		
		if (StringUtils.isNotBlank(typeId)) {
			c.add(Restrictions.eq("projTypeId", typeId));
		}
		List<ContributionMode> list=this.findByCriteria(c);
		return list;
	}
	
	/**
	 * 查询出资方式列表
	 * @author fuliwei
	 * @createTime 2017年7月28日
	 * @param 
	 * @return
	 */
	@Override
	public Map<String, Object> queryContributionMode(ProjectScaleReq req) {
		Criteria c=this.getCriteria();
		
		if(StringUtils.isNotBlank(req.getContributionModeDes())){
			c.add(Restrictions.like("contributionDes", "%"+req.getContributionModeDes()+"%"));
		}
		//c.addOrder(Order.asc("code"));
		int filterCount=this.countByCriteria(c);
		List<ContributionMode> list=this.findBySortCriteria(c, req);
		
		return ResultUtil.pageResult(filterCount, req.getDraw(), list);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ContributionMode> queryAllList() {
		Criteria c = super.getCriteria();
	    c.addOrder(Order.asc("id"));
	    // 根据条件获取查询信息
	    List<ContributionMode> contributionModeList = c.list();
		return contributionModeList;
	}

}
