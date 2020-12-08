package cc.dfsoft.project.biz.base.contract.dao.impl;

import cc.dfsoft.project.biz.base.contract.dao.PayTypeDao;
import cc.dfsoft.project.biz.base.contract.entity.PayType;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PayTypeDaoImpl extends NewBaseDAO<PayType, String> implements PayTypeDao {

	@Override
	public List<PayType> findByContractType(String contractType,String corpId) {
		Criteria c = super.getCriteria();
		if (StringUtils.isNotBlank(contractType)) {
			c.add(Restrictions.eq("contractType", contractType));
		}
		if (StringUtils.isNotBlank(corpId)) {
			c.add(Restrictions.eq("corpId", corpId));
		}else{
			//分公司ID为空的
			c.add(Restrictions.isNull("corpId"));
		}
		List<PayType> list=this.findByCriteria(c);
		return list;
	}

	@Override
	public List<PayType> findByProjType(Project project) {
		List<PayType> payTypeList = findPayTypeListByType(project.getProjectType(), project.getCorpId());

		if (payTypeList!=null&&payTypeList.size()>0){
			return payTypeList;
		}
		return findPayTypeListByType(project.getProjectType(),"0");
	}




	public List<PayType> findPayTypeListByType(String projType,String corpId){
		Criteria c = super.getCriteria();
		if (StringUtils.isNotBlank(projType)) {
			c.add(Restrictions.eq("projTypeId", projType));
		}
		if (StringUtils.isNotBlank(corpId)) {
			c.add(Restrictions.eq("corpId",corpId));
		}

		return this.findByCriteria(c);
	}




	@Override
	public List<PayType> findAllByContractType() {
		Criteria c = super.getCriteria();
		return this.findByCriteria(c);
	}

}
