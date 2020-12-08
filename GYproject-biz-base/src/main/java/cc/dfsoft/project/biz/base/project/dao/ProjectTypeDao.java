package cc.dfsoft.project.biz.base.project.dao;

import java.util.List;
import java.util.Map;

import cc.dfsoft.project.biz.base.project.dto.ProjectTypeReq;
import cc.dfsoft.project.biz.base.project.entity.ProjectType;
import cc.dfsoft.uexpress.common.dao.CommonDao;
/**
 * 工程类型dao
 * @author zhangjj
 *
 */
public interface ProjectTypeDao extends CommonDao<ProjectType, String>{
	/**
	 * 查询工程类型列表
	 * @param ProjectTypeReq
	 * @return
	 */
	public Map<String,Object> queryProjectTypeList(ProjectTypeReq req);
	/**
	 * 根据id删除工程类型
	 * @param id
	 * @return
	 */
	public void delProjectType(String id);
	public List<ProjectType> findProjectType();
	
	/***
	 * 根据类型id查询工程类型
	 * @param typeId
	 * @return
	 */
	public ProjectType findByTypeId(String typeId);
	
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
}
