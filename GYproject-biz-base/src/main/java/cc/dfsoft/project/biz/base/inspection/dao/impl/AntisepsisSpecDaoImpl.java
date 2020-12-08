package cc.dfsoft.project.biz.base.inspection.dao.impl;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import cc.dfsoft.project.biz.base.inspection.dao.AntisepsisSpecDao;
import cc.dfsoft.project.biz.base.inspection.entity.AntisepsisSpec;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;
@Repository
public class AntisepsisSpecDaoImpl extends NewBaseDAO<AntisepsisSpec, String> implements  AntisepsisSpecDao {

	@Override
	public List<AntisepsisSpec> queryAntSpecByPcId(String pcId,String asType) {
		Criteria c = super.getCriteria();			
		 //报验单
		 if(StringUtils.isNotBlank(pcId)){
			 c.add(Restrictions.eq("pcId",pcId));
		 }
		 //规格类型
		 if(StringUtils.isNotBlank(asType)){
			 c.add(Restrictions.eq("asType",asType));
		 }
		 // 根据条件获取查询信息
		return this.findByCriteria(c);
	}

	@Override
	public void saveAntSpecList(List<AntisepsisSpec> list) {				
	}

	@Override
	public void deleteByPcId(String pcId) {
		StringBuffer hql = new StringBuffer("delete from AntisepsisSpec where pcId='").append(pcId).append("'");
		super.executeHql(hql.toString());
	}
	
}
