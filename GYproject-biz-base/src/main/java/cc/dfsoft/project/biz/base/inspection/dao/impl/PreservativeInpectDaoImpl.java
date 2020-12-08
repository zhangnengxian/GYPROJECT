package cc.dfsoft.project.biz.base.inspection.dao.impl;


import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cc.dfsoft.project.biz.base.inspection.dao.PreservativeInpectDao;
import cc.dfsoft.project.biz.base.inspection.dto.PreservativeInpectReq;
import cc.dfsoft.project.biz.base.inspection.entity.PreservativeInpect;
import cc.dfsoft.project.biz.base.inspection.entity.ProjectChecklist;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;
/**
 * 防腐检查dao实现
 * @author liaoyq
 *
 */
@Repository
public class PreservativeInpectDaoImpl extends NewBaseDAO<PreservativeInpect, String> implements
		PreservativeInpectDao {

	@Override
	public void deleteByPcId(String pcId) {
		StringBuffer hql = new StringBuffer("delete from PreservativeInpect where pcId='").append(pcId).append("'");
		super.executeHql(hql.toString());
	}

	@Override
	public Map<String, Object> queryPreservativeInpect(
			PreservativeInpectReq preservativeInpect) {
		
		return null;
	}

	@Override
	public PreservativeInpect queryByPcId(String pcId) {
		Criteria criteria = super.getCriteria();
		if(StringUtils.isNotBlank(pcId)){
			criteria.add(Restrictions.eq("pcId",pcId));
		}
		List<PreservativeInpect> list = this.findByCriteria(criteria);
		if (list !=null && list.size()>0) {
			return list.get(0);
		}
		return null;
	}


}
