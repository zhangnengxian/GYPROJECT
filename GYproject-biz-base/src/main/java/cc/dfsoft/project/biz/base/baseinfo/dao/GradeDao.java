package cc.dfsoft.project.biz.base.baseinfo.dao;

import java.util.List;
import java.util.Map;

import cc.dfsoft.project.biz.base.baseinfo.entity.Grade;
import cc.dfsoft.uexpress.common.dao.CommonDao;

public interface GradeDao extends CommonDao<Grade,String>{
	
	/**
	 * 根据评分表id查询评分
	 * @author
	 * @createTime 
	 * @param
	 * @return
	 */
	public List queryBySsid(String projId,String staffId);
	
	/**
	 * 根据unitid和工程id删除grade表相应部门的内容
	 * @author
	 * @createTime 
	 * @param
	 * @return
	 */
	public void deleteByProjIdAndUnitId(String projId,String UnitID);
	
	
	/**
	 * 根据评分表id查询评分
	 * @author
	 * @createTime 
	 * @param
	 * @return
	 */
	public Map<String, Object> queryBySsidAndProjId(String ssId,String projId);
	
	
}
