package cc.dfsoft.project.biz.base.project.dao;

import java.util.Map;

import cc.dfsoft.project.biz.base.project.dto.ProjectScaleReq;
import cc.dfsoft.project.biz.base.project.entity.ProjectScale;
import cc.dfsoft.uexpress.common.dao.CommonDao;
/**
 * 工程类型dao
 * @author zhangjj
 *
 */
public interface ProjectScaleDao extends CommonDao<ProjectScale, String>{
	/**
	 * 查询工程类型列表
	 * @param ProjectScaleReq
	 * @return
	 */
	public Map<String,Object> queryProjectScaleList(ProjectScaleReq req);
	/**
	 * 根据id删除工程类型
	 * @param id
	 * @return
	 */
	public void delProjectScale(String id);
	
}
