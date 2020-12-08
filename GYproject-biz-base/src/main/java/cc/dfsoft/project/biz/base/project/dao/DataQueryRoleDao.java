package cc.dfsoft.project.biz.base.project.dao;

import cc.dfsoft.project.biz.base.project.entity.DataQueryRole;
import cc.dfsoft.uexpress.common.dao.CommonDao;

public interface DataQueryRoleDao extends CommonDao<DataQueryRole, String>{
	
	/**
	 * 通过部门id查询
	 * @author fuliwei
	 * @createTime 2017年10月9日
	 * @param 
	 * @return
	 */
	public DataQueryRole findByUnitId(String unitId);
}
