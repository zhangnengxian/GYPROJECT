package cc.dfsoft.uexpress.biz.sys.dept.dao.impl;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cc.dfsoft.uexpress.biz.sys.dept.dao.EnclosureDao;
import cc.dfsoft.uexpress.biz.sys.dept.entity.Enclosure;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;
@Repository
public class EnclosureDaoImpl extends NewBaseDAO<Enclosure, String> implements EnclosureDao {

	@Override
	public List<Enclosure> queryEnuclosureByBus(String id, String sourceType) {
		Criteria c = super.getCriteria();
		//业务单
		 if(StringUtils.isNotBlank(id)){
			 c.add(Restrictions.eq("busRecordId",id));
		 }
		//附件来源
		 if(StringUtils.isNotBlank(sourceType)){
			 c.add(Restrictions.eq("sourceType",sourceType));
		 }
		 List<Enclosure> enclosure = this.findByCriteria(c);
		 return enclosure;
	}
	
}
