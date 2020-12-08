package cc.dfsoft.project.biz.base.budget.service;

import java.text.ParseException;
import java.util.Map;

import cc.dfsoft.project.biz.base.project.dto.ProjectQueryReq;

/**
 * 图纸目录服务接口
 * @author 
 * @createTime 2016-07-05
 *
 */
public interface DrawingDirectoryService {
	
	/**
	 * 该工程列表查询专用于图纸审核页面
	 * @author 
	 * @createTime 2016-07-05
	 * @param projectQueryReq 查询条件dto
	 * @return Map<String,Object>
	 * @throws ParseException 
	 */
	//Map<String,Object> queryProject(ProjectQueryReq projectQueryReq) throws ParseException;
	
	/**
	 * 图纸审核页面查询工程列表
	 * @author
	 * @createTime 
	 * @param
	 * @return
	 */
	//Map<String, Object> queryAuditProject(ProjectQueryReq projectQueryReq) throws ParseException;
}
