package cc.dfsoft.project.biz.base.budget.dao;

import java.util.List;
import java.util.Map;

import cc.dfsoft.project.biz.base.budget.dto.MaterialListQueryReq;
import cc.dfsoft.project.biz.base.budget.dto.MaterialListReq;
import cc.dfsoft.project.biz.base.budget.entity.MaterialList;
import cc.dfsoft.uexpress.common.dao.CommonDao;

/**
 * 材料清单dao
 * @author pengtt
 *
 */
public interface MaterialListDao  extends CommonDao<MaterialList, String>{
	
	/**
	 * 材料列表查询
	 * @author pengtt
	 * @createTime 2016-07-19
	 * @param 
	 * @return
	 */
	public Map<String,Object> queryMaterialList(MaterialListQueryReq materialListQueryReq);

	/**
	 * 查询材料记录导出数据
	 * @param projId
	 * @return
	 */
	public List<MaterialList> getExportDataList(String projId);
	/**
	 * 根据工程ID查材料列表
	 * @param projId
	 * @return
	 */
	public List<MaterialList> queryMaterialListList(String projId);
	
	
	/**
	 * 根据材料名称查找材料
	 * @author fuliwei
	 * @createTime 2017年2月18日
	 * @param materialName
	 * @return
	 */
	public MaterialList queryMaterialList(String materialName,String projId);
	
	
	/**
	 * 列表查询
	 * @author fuliwei
	 * @createTime 2017-02-19
	 * @param projId
	 * @return
	 */
	public List<MaterialList> queryMaterialListById(String projId);
	
	/**
	 * 通过工程id删除所有的材料清单
	 * @author fuliwei
	 * @createTime 2017年11月14日
	 * @param 
	 * @return
	 */
	public void deleteByProjId(String projId);
	/**
	 * 根据工程ID、材料名称和材料规格查询工程信息
	 * @param req
	 * @return
	 */
	public MaterialList queryMaterial(MaterialListQueryReq req);
	/**
	 *  根据工程ID、材料名称和材料规格删除工程信息
	 * @param req
	 */
	public void deleteByProjId(MaterialListQueryReq req);
	/**
	 * 根据工程ID、材料名称和规格获取材料清单
	 * @param materialName
	 * @param materialSpec
	 * @param projId 
	 * @param flag 
	 * @return
	 */
	public MaterialList queryByNameAndSpec(String projId,String materialName,
			String materialSpec, String flag);
	/**
	 * 查询材料清单信息
	 * @author liaoyq
	 * @createTime 2018年6月1日
	 * @param req
	 * @return
	 */
	public List<MaterialList> queryMaterials(MaterialListReq req);
}
