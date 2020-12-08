package cc.dfsoft.project.biz.base.baseinfo.dao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cc.dfsoft.project.biz.base.baseinfo.dao.SystemSetDao;
import cc.dfsoft.project.biz.base.common.entity.SystemSet;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;
@Repository
public class SystemSetDaoImpl extends NewBaseDAO<SystemSet,String> implements SystemSetDao{

	@Override
	public List<SystemSet> queryByStepIds(List<String> stepList) {
		 Criteria c = super.getCriteria();
		if(stepList!=null && stepList.size()>0){
			String [] steps = new String[stepList.size()];
			for(int i=0 ;i<stepList.size();i++){
				steps[i] = stepList.get(i);
			}
			c.add(Restrictions.in("stepId", steps));
		}
		return this.findByCriteria(c);
	}

	@Override
	public SystemSet querySystemSet(String menuId, String corpId) {
		 Criteria c = super.getCriteria();
		 
		 if(StringUtils.isNotBlank(menuId)){
			 c.add(Restrictions.eq("menuId",menuId));
		 }
		 if(StringUtils.isNotBlank(corpId)){
			 c.add(Restrictions.eq("corpId",corpId));
		 }
		 List<SystemSet> list = this.findByCriteria(c);
		 if(list!=null && list.size()>0){
			 return list.get(0);
		 }
		return null;
	}

	@Override
	public SystemSet querySystemSetByStepId(String stepId, String corpId) {
 Criteria c = super.getCriteria();
		 
		 if(StringUtils.isNotBlank(stepId)){
			 c.add(Restrictions.eq("stepId",stepId));
		 }
		 if(StringUtils.isNotBlank(corpId)){
			 c.add(Restrictions.eq("corpId",corpId));
		 }
		 List<SystemSet> list = this.findByCriteria(c);
		 if(list!=null && list.size()>0){
			 return list.get(0);
		 }
		return null;
	}





}
