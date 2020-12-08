package cc.dfsoft.project.biz.base.project.service;

import java.util.List;
import java.util.Map;

import cc.dfsoft.project.biz.base.project.dto.ProjectScaleReq;
import cc.dfsoft.project.biz.base.project.entity.ContributionMode;

public interface ContributionModeService {
	
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
	 * 保存出资方式
	 * @author fuliwei
	 * @createTime 2017年7月28日
	 * @param 
	 * @return
	 */
	public String saveContributionMode(ContributionMode contributionMode);
	
	/**
	 * 根据id删除
	 * @author fuliwei
	 * @createTime 2017年7月28日
	 * @param 
	 * @return
	 */
	public void deleteById(String id);

	/**
	 * 查询所有的出资方式
	 * @return
	 */
	public List<ContributionMode> queryAllList();
	
	
}
