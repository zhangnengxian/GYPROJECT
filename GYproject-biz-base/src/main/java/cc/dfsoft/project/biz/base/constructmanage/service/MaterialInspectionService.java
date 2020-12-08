package cc.dfsoft.project.biz.base.constructmanage.service;

import java.util.List;

import cc.dfsoft.project.biz.base.constructmanage.entity.MaterialInspection;

/**
 * 材料报验记录
 * @author pengtt
 * @createTime 2016-07-20
 *
 */
public interface MaterialInspectionService {
	
	/**
	 * 材料批量报验，批量保存
	 * @author pengtt
	 * @param materialLists
	 */
	public void saveMaterialInspections(List<MaterialInspection> materialInspections);
}
