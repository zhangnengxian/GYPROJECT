package cc.dfsoft.project.biz.base.design.dao;

import java.util.List;
import java.util.Map;

import cc.dfsoft.project.biz.base.design.dto.DrawingsDirectoryReq;
import cc.dfsoft.project.biz.base.design.entity.DrawingMaterial;
import cc.dfsoft.uexpress.common.dao.CommonDao;
/**
 * 
 * @author zhangmeng
 * @CreateTime 2016-07-06
 *
 */
public interface DrawingMaterialDao extends CommonDao<DrawingMaterial, String>{
	public Map<String, Object> queryMaterialDirectory(DrawingsDirectoryReq pageSortReq);
	/**
	 * 根据工程ID查询出图材料表
	 * @param projId
	 * @return
	 */
	public List<DrawingMaterial> queryDrawingMaterialList(String projId);
}
