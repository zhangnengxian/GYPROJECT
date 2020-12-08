package cc.dfsoft.project.biz.base.constructmanage.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import cc.dfsoft.project.biz.base.constructmanage.dto.ProgressQueryReq;
import cc.dfsoft.project.biz.base.constructmanage.entity.Progress;

/**
 * 工程进步service
 * @author pengtt
 * @createTime 2016-07-27
 */
public interface ProgressService {
	
	/**
	 * 工程进度
	 * @author pengtt
	 * @param reviewRecordQueryReq
	 * @return
	 */
	public Map<String, Object> queryProgress(ProgressQueryReq progressQueryReq);
	
	
	/**
	 * 工程进度保存
	 * @param progressList
	 */
	public void saveProgress(List<Progress> progressList);
	
	/**
	 * 查询某工程的总进度
	 * @author pengtt
	 * @createTime 2016-07-28
	 * @param progressQueryReq
	 * @return
	 */
	public String queryTotalProgress(ProgressQueryReq progressQueryReq);
	
	/**
	 * 获取数据库当前时间
	 * @return
	 */
	public Date getDatabaseDate();
	
	/**
	 * 工程表总进度保存
	 * @author fulwei
	 * @createTime 2017-01-30
	 * @return
	 */
	public void savePrjectTotalProgress(String projId);
	
	/**
	 * 工程表查询工程总进度 
	 * @author fuliwei
	 * @createTime 2017年2月11日
	 * @param progressQueryReq
	 * @return String
	 */
	public String queryProjectTotalProgress(String projId);

    Progress queryGraphicProgress(String projId);

    String saveGraphicProgress(Progress progress);
}
