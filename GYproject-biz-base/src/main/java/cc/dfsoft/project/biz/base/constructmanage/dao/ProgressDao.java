package cc.dfsoft.project.biz.base.constructmanage.dao;

import java.util.Map;

import cc.dfsoft.project.biz.base.constructmanage.dto.ProgressQueryReq;
import cc.dfsoft.project.biz.base.constructmanage.entity.Progress;
import cc.dfsoft.uexpress.common.dao.CommonDao;

/**
 * 工程进度Dao
 * @author pengtt
 * @createTime 2016-07-27
 *
 */
public interface ProgressDao extends CommonDao<Progress,String>{
	
	/**
	 * 工程进度记录列表查询
	 * @param progressQueryReq
	 * @return
	 */
	public Map<String, Object> queryProgress(ProgressQueryReq progressQueryReq);
	
	/**
	 * 查询某工程的总进度
	 * @author pengtt
	 * @createTime 2016-07-28
	 * @param progressQueryReq
	 * @return
	 */
	public String queryTotalProgress(ProgressQueryReq progressQueryReq);
	
	/**
	 * 根据工程id删除
	 * @author pengtt
	 * @createTime 2016-08-30
	 * @param projId
	 */
	public void deleteByProjId(String projId);
	
	/**
	 * 工程表查询工程总进度 
	 * @author fuliwei
	 * @createTime 2017年2月11日
	 * @param progressQueryReq
	 * @return String
	 */
	public String queryProjectTotalProgress(String projId);

	/**
	 * 查询工程最后形象进度
	 * @author cui
	 * @createTime 2017-9-1
	 * @param projId
	 * @return String
	 */
    Progress queryGraphicProgress(String projId);
}
