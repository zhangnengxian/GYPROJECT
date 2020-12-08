package cc.dfsoft.project.biz.base.inspection.dao;


import java.util.List;

import cc.dfsoft.project.biz.base.inspection.entity.CheckList;
import cc.dfsoft.uexpress.common.dao.CommonDao;

public interface CheckListDao extends CommonDao<CheckList, String>{
/**
 * 根据工程id查询
 * 
 */
	public List<CheckList> queryByProjId(String id);
	
}
