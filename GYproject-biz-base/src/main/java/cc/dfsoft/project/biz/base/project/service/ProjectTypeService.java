package cc.dfsoft.project.biz.base.project.service;

import java.util.List;
import java.util.Map;

import cc.dfsoft.project.biz.base.project.dto.ProjectTypeReq;
import cc.dfsoft.project.biz.base.project.entity.ProjectType;

/**
 * 工程类型接口
 * @author zhangjj
 *
 */
public interface ProjectTypeService {

	/**
	 * 查询工程类型列表
	 * @param accessoryQueryReq
	 * @return
	 */
	public Map<String,Object> queryProjectTypeList(ProjectTypeReq projectTypeReq);
	
	/**
	 * 工程类型删除功能
	 * @param id 工程类型id
	 */
	public String delProjectType(String id);
	
	
	/**
	 * 保存更新工程类型
	 * @param ProjectType
	 * @return
	 */
	public void saveOrUpdateProjectType(ProjectType projectType);
	/**
	 * 查询所有工程类型列表
	 * @param 
	 * @return
	 */
	public List<ProjectType> queryAllList();
	
	
	/**
	 * 查询计划内或计划外的工程类型列表
	 * @param 
	 * @return
	 */
	public List<ProjectType> queryProjectTypeList(String type);
	
	/**
	 * 部门id
	 * @author fuliwei
	 * @createTime 2017年8月2日
	 * @param 
	 * @return
	 */
	public List<ProjectType> queryProjectTypeByDeptId(String deptId);
	
	/**
	 * 通过主键id查询合同类型
	 * @author fuliwei
	 * @createTime 2017年8月30日
	 * @param 
	 * @return
	 */
	public String findByProjTypeId(String projTypeId);
	

}
