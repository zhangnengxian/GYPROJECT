package cc.dfsoft.project.biz.base.baseinfo.dao;

import java.util.List;

import cc.dfsoft.project.biz.base.common.entity.SystemSet;
import cc.dfsoft.uexpress.common.dao.CommonDao;
/**
 * 
 * @author zhangjj
 * @createTime 2016-11-25
 *
 */
public interface SystemSetDao extends CommonDao<SystemSet,String>{

	/**
	 * 查询某些步骤的时限配置
	 * @author liaoyq
	 * @createTime 2018年5月25日
	 * @param stepList
	 * @return
	 */
	List<SystemSet> queryByStepIds(List<String> stepList);
	
	/**
	 * 按菜单id和分公司id查询
	 * @author fuliwei  
	 * @date 2018年8月22日  
	 * @version 1.0
	 */
	public SystemSet querySystemSet(String menuId,String corpId);
	
	/**
	 * 按步骤id和分公司id查询
	 * @author fuliwei  
	 * @date 2018年8月22日  
	 * @version 1.0
	 */
	public SystemSet querySystemSetByStepId(String stepId,String corpId);
}
