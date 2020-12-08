package cc.dfsoft.project.biz.base.complete.service;

import cc.dfsoft.project.biz.base.complete.entity.DataAcceptance;
import cc.dfsoft.project.biz.base.project.entity.Project;

/**
 * 资料验收申请
 * @author fuliwei
 *
 */
public interface DataAcceptanceService {
	

	/**
	 * 根据工程id查询详述
	 * @author fuliwei
	 * @createTime 2017年8月3日
	 * @param 
	 * @return
	 */
	public Project findProjectById(String projId);
	
	/**
	 * 根据工程id查询资料验收申请表
	 * @author fuliwei
	 * @createTime 2017年8月4日
	 * @param 
	 * @return
	 */
	public DataAcceptance findByProjId(String projId);
	
	/**
	 * 保存资料申请
	 * @author fuliwei
	 * @createTime 2017年8月8日
	 * @param 
	 * @return
	 */
	public String saveDataAcceptance(DataAcceptance da);

    boolean rollBackContainsDataAcceptance(String projId, String fallbackReason);
}
