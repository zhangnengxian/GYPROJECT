package cc.dfsoft.project.biz.base.budget.service;

import java.util.List;
import java.util.Map;

import cc.dfsoft.project.biz.base.budget.dto.MaterialListQueryReq;
import cc.dfsoft.project.biz.base.budget.entity.MaterialList;
import cc.dfsoft.project.biz.base.change.entity.MaterialChange;
import cc.dfsoft.project.biz.ifs.ResultMessage;
import cc.dfsoft.project.biz.ifs.material.dto.ProjectInfoDTO;

import com.alibaba.fastjson.JSONArray;

/**
 * 材料清单服务接口
 * @author pengtt
 * @createTime 2016-07-19
 *
 */
public interface MaterialListService {
	
	/**
	 * 列表查询
	 * @author pengtt
	 * @createTime 2016-07-19
	 * @param materialListQueryReq
	 * @return
	 */
	public Map<String, Object> queryMaterialList(MaterialListQueryReq materialListQueryReq);

	/**
	 * 查询工程材料记录导出数据
	 * @param projId
	 * @return
	 */
	public List<MaterialList> getExportDataList(String projId);
	/**
	 * 更新材料清单
	 * @param list
	 * @return
	 */
	public void updateMaterialList(List<MaterialList> list);
	
	/**
	 * 获取材料清单集合
	 * @author ht.hu
	 * @param jsonArr
	 * @param projId
	 * @return
	 */
	public List<MaterialChange> getMaterialList(JSONArray jsonArr, String projId);
	
	
	/**
	 * 列表查询
	 * @author fuliwei
	 * @createTime 2017-02-19
	 * @param projId
	 * @return
	 */
	public List<MaterialList> queryMaterialListById(String projId);
	
	/**
	 * 调用接口处理
	 * @author fuliwei
	 * @createTime 2017年11月13日
	 * @param 
	 * @return
	 */
	public ResultMessage updateMaterialReceive(ProjectInfoDTO projInfo,
			Map<String, Object> materialInfoList, String operate_type);
	/**
	 * 
	 * @param projInfoDto
	 * @param materialInfoList
	 * @param operateType
	 * @return
	 */
	ResultMessage updateMaterialsReceive(ProjectInfoDTO projInfoDto,
			Map<String, Object> materialInfoList, String operateType);
}
