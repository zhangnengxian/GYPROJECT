package cc.dfsoft.project.biz.base.common.dao;

import cc.dfsoft.project.biz.base.common.entity.MenuStep;
/**
 * 查询步骤
 * @author fulw
 *
 */
public interface MenuStepDao {
	/**
	 * 通过步骤id查询menuId
	 * @author fuliwei  
	 * @date 2018年10月17日  
	 * @version 1.0
	 */
	public  MenuStep findByStepId(String stepId);


	public MenuStep findByMenuId(String menuId);
}
