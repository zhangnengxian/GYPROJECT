package cc.dfsoft.project.biz.base.project.dao;

import java.util.List;
import java.util.Map;

import cc.dfsoft.project.biz.base.project.dto.ProjectScaleReq;
import cc.dfsoft.project.biz.base.project.entity.ContributionMode;
import cc.dfsoft.uexpress.common.dao.CommonDao;

public interface ContributionModeDao extends CommonDao<ContributionMode, String>{
	
	/**
	 * 查询出资方式
	 * @author fuliwei
	 * @createTime 2017年7月19日
	 * @param 
	 * @return
	 */
	public List<ContributionMode> findById(String typeId);
	
	/**
	 * 查询出资方式列表
	 * @author fuliwei
	 * @createTime 2017年7月28日
	 * @param 
	 * @return
	 */
	public Map<String,Object> queryContributionMode(ProjectScaleReq req);
	/**
	 * 查询所有的出资方式
	 * @return
	 */
	public List<ContributionMode> queryAllList();
}
