package cc.dfsoft.project.biz.base.constructmanage.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cc.dfsoft.project.biz.base.constructmanage.dao.DigRoadDao;
import cc.dfsoft.project.biz.base.constructmanage.dto.DigRoadQueryReq;
import cc.dfsoft.project.biz.base.constructmanage.entity.DigRoad;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;
import cc.dfsoft.uexpress.common.util.ResultUtil;

@Repository
public class DigRoadDaoImpl extends NewBaseDAO<DigRoad, String> implements DigRoadDao {

	@Override
	public List<DigRoad> findbyTpId(String tpId) {
		StringBuffer hql = new StringBuffer("from DigRoad where tpId='").append(tpId).append("'");
		List<DigRoad> result = super.findByHql(hql.toString());
		return result;
	}

	@Override
	public Map<String, Object> queryDigRoad(DigRoadQueryReq digRoadQueryReq) {
		
		Criteria c = super.getCriteria();
		if(StringUtils.isNotBlank(digRoadQueryReq.getTpId())){
		    c.add(Restrictions.eq("tpId",digRoadQueryReq.getTpId()));
		}
		// 数据库根据条件过滤记录数
		int filterCount = this.countByCriteria(c);
		 
		// 根据条件获取查询信息
		List<DigRoad> digRoadList = this.findBySortCriteria(c, digRoadQueryReq);
		// 返回结果
		return ResultUtil.pageResult(filterCount, digRoadQueryReq.getDraw(),digRoadList);
	}

	@Override
	public void deleteByTpId(String tpId) {
		StringBuffer hql = new StringBuffer("delete from DigRoad where tpId='").append(tpId).append("'");
		super.executeHql(hql.toString());
	}
	
}
