package cc.dfsoft.project.biz.base.constructmanage.service;

import java.util.List;

import cc.dfsoft.project.biz.base.constructmanage.entity.MaterialCollar;

/**
 * 材料领用记录
 * @author pengtt
 * @createTime 2016-07-19
 *
 */
public interface MaterialCollarService {
	
	/**
	 * 材料批量领用，批量保存
	 * @author pengtt
	 * @param materialLists
	 */
	public void saveMaterialCollars(List<MaterialCollar> materialCollars);
}
