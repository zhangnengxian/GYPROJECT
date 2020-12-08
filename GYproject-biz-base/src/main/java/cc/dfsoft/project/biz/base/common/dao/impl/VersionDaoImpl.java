package cc.dfsoft.project.biz.base.common.dao.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cc.dfsoft.project.biz.base.common.dao.VersionDao;
import cc.dfsoft.project.biz.base.common.entity.Version;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;

@Repository
public class VersionDaoImpl extends NewBaseDAO<Version, String> implements VersionDao{

	@Override
	public List<Version> findByType(String veType) {
		Criteria c = super.getCriteria();
		if(StringUtils.isNotBlank(veType)){
			 c.add(Restrictions.eq("veType",veType));
		 }
		c.addOrder(Order.desc("greatTime"));
		 List<Version> versions = this.findByCriteria(c);
		return versions;
	}

	@Override
	public Version findVer(Date signDate) {
		// TODO Auto-generated method stub
		Criteria c = super.getCriteria();
//		ProjectionList plist = Projections.projectionList();
//		plist.add(Projections.max("veStartTime"));
		if(signDate!=null){
			c.setProjection(Projections.max("veStartTime")).add(Restrictions.le("veStartTime",signDate));
		 }
//		Criteria c1 = super.getCriteria();
//		if(c.uniqueResult()!=null){
//			c1.add(Restrictions.eq("veStartTime",c.uniqueResult()));
//		}
		
		return this.get("veStartTime", c.uniqueResult());
		
//		StringBuffer hql = new StringBuffer("from Version where Version = max(veStartTime) ");
//		if(signDate!=null){
//			hql.append(" and veStartTime<'").append(signDate).append("'");
//		}
//		Version version = super.findClassByHql(hql.toString());
//		return version;
	}

}
