package cc.dfsoft.project.biz.base.inspection.dao.impl;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import antlr.StringUtils;
import cc.dfsoft.project.biz.base.inspection.dao.CheckListDao;
import cc.dfsoft.project.biz.base.inspection.entity.CheckList;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;
@Repository
public class CheckListDaoImpl extends NewBaseDAO<CheckList, String> implements CheckListDao{

	@Override
	public List<CheckList> queryByProjId(String id) {
		Criteria c = super.getCriteria();
		if(null!=id){
			c.add(Restrictions.eq("projId", id));
		}
		List<CheckList> checkList=c.list();
		if(checkList.size()>0){
			return checkList;
		}
		return null;
	}


}
