package cc.dfsoft.project.biz.base.constructmanage.service;

import java.util.List;

import cc.dfsoft.project.biz.base.constructmanage.entity.MaterialUse;

/**
 * 材料使用记录
 * @author pengtt
 * @createTime 2016-07-20
 *
 */
public interface MaterialUseService {
	
	/**
	 * 材料批量使用登记，批量保存
	 * @author pengtt
	 * @param materialUses
	 */
	public void saveMaterialUses(List<MaterialUse> materialUses);
}
