package cc.dfsoft.project.biz.base.inspection.dao.impl;


import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cc.dfsoft.project.biz.base.inspection.dao.CheckListDao;
import cc.dfsoft.project.biz.base.inspection.dao.CheckListTypeDao;
import cc.dfsoft.project.biz.base.inspection.entity.BasicProjectContent;
import cc.dfsoft.project.biz.base.inspection.entity.CheckList;
import cc.dfsoft.project.biz.base.inspection.entity.CheckListType;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;
import cc.dfsoft.uexpress.common.util.ResultUtil;
@Repository
public class CheckListTypeDaoImpl extends NewBaseDAO<CheckListType, String> implements CheckListTypeDao{

	/*@Override
	public List<CheckListType> queryAll() {
		Criteria c=super.getCriteria();
		List<CheckListType> list=c.list();
		return list;
	}
*/
	


}
