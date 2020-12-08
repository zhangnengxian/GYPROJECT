package cc.dfsoft.project.biz.base.budget.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cc.dfsoft.project.biz.base.budget.dao.GovAuditCostDao;
import cc.dfsoft.project.biz.base.budget.entity.GovAuditCost;
import cc.dfsoft.project.biz.base.budget.entity.MaterialList;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;
/**
 * 
 * 描述:政府审定价dao实现层
 * @author liaoyq
 * @createTime 2017年9月8日
 */
@Repository
public class GovAuditCostDaoImpl extends NewBaseDAO<GovAuditCost, String> implements
		GovAuditCostDao {

	/**
	 * 根据工程ID和审定价类型查询详述
	 */
	@Override
	public GovAuditCost queryByProjIdAndType(String projId, String gacType) {
		Criteria c=super.getCriteria();
		if(StringUtils.isNotBlank(projId)){
			c.add(Restrictions.eq("projId", projId));
		}
		if(StringUtils.isNotBlank(gacType)){
			c.add(Restrictions.eq("gacType", gacType));
		}
		List<GovAuditCost> list = this.findByCriteria(c);
		if(list!=null&&list.size()>0){
			return list.get(0);
		}
		return null;
	}


}
