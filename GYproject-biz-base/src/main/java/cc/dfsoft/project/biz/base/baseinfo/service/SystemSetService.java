package cc.dfsoft.project.biz.base.baseinfo.service;

import cc.dfsoft.project.biz.base.common.entity.SystemSet;

public interface SystemSetService {
	/**
	 * 保存
	 * @param systemSet
	 */
	public void saveSystemSet(SystemSet systemSet);
	
	/**
	 * 按菜单id和分公司id查询
	 * @author fuliwei  
	 * @date 2018年8月22日  
	 * @version 1.0
	 */
	public SystemSet querySystemSet(String menuId,String corpId);
	
	public SystemSet querySystemSetByStepId(String stepId);
}
