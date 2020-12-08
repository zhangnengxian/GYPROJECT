package cc.dfsoft.project.biz.base.contract.dao;

import cc.dfsoft.project.biz.base.contract.entity.ProjectCost;
import cc.dfsoft.project.biz.base.design.dto.DesignerQueryReq;
import cc.dfsoft.uexpress.common.dao.CommonDao;

import java.util.Map;

/**
 * 
 * 描述:工程总造价dao接口
 * @author liaoyq
 * @createTime 2017年8月17日
 */
public interface ProjectCostDao extends CommonDao<ProjectCost, String> {

	/**
	 * 根据工程ID查询工程工程总造价信息
	 * @param projId 工程ID
	 * @return 工程总造价对象
	 */
	ProjectCost queryByProjId(String projId);

    Map<String,Object> queryCoster(DesignerQueryReq designerQueryReq);
}
