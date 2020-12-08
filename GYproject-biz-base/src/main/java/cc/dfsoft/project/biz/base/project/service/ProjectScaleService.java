package cc.dfsoft.project.biz.base.project.service;

import java.util.Map;

import cc.dfsoft.project.biz.base.project.dto.ProjectScaleReq;
import cc.dfsoft.project.biz.base.project.entity.ProjectScale;

/**
 * 工程类型接口
 * @author zhangjj
 *
 */
public interface ProjectScaleService {

	/**
	 * 查询工程类型列表
	 * @param accessoryQueryReq
	 * @return
	 */
	public Map<String,Object> queryProjectScaleList(ProjectScaleReq ProjectScaleReq);
	
	/**
	 * 工程类型删除功能
	 * @param id 工程类型id
	 */
	public void delProjectScale(String id);
	
	
	/**
	 * 保存更新工程类型
	 * @param ProjectScale
	 * @return
	 */
	public void saveOrUpdateProjectScale(ProjectScale ProjectScale);
	

}
