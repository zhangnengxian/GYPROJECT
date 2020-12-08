package cc.dfsoft.project.biz.base.contract.service;

import java.text.ParseException;
import java.util.Map;

import cc.dfsoft.project.biz.base.contract.dto.ProjectCostReq;
import cc.dfsoft.project.biz.base.contract.entity.ProjectCost;
import cc.dfsoft.project.biz.base.design.dto.DesignDispatchDto;
import cc.dfsoft.project.biz.base.design.dto.DesignerQueryReq;
import cc.dfsoft.project.biz.base.project.dto.ProjectQueryReq;

/**
 * 
 * 描述:工程总造价service接口
 * @author liaoyq
 * @createTime 2017年8月17日
 */
public interface ProjectCostService {

	/**
	 * 根据工程ID查询工程造价，并进行保存或修改
	 * @author liaoyq
	 * @createTime 2017年8月17日
	 * @param auditLevel审核级别
	 * @param projectCostReq 工程造价dto
	 */
	void findAndSaveOrUpdate(String auditLevel, ProjectCostReq projectCostReq);

	/**
	 * 分页获取待工程造价审核的工程列表
	 * @author liaoyq
	 * @createTime 2017年8月17日
	 * @param projectQueryReq 
	 * @param stepId
	 * @return Map<String, Object>
	 * @throws ParseException 
	 */
	Map<String, Object> queryAuditProject(ProjectQueryReq projectQueryReq,
			String stepId) throws ParseException;

	/**
	 * 根据工程ID查询工程造价信息
	 * @author liaoyq
	 * @createTime 2017年8月17日
	 * @param projId 工程ID
	 * @return 
	 */
	ProjectCost queryByProjId(String projId);

	Map<String, Object> queryCoster(DesignerQueryReq designerQueryReq) throws ParseException;

	 void updateProject(DesignDispatchDto designDispatchReq);
}
