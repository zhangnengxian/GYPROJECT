package cc.dfsoft.project.biz.base.project.dao;

import java.util.List;
import java.util.Map;

import cc.dfsoft.project.biz.base.project.dto.ProjectQueryReq;
import cc.dfsoft.project.biz.base.project.entity.Notice;
import cc.dfsoft.uexpress.common.dao.CommonDao;
import cc.dfsoft.uexpress.common.dto.PageSortReq;

public interface NoticeDao extends CommonDao<Notice, String> {
	
	/**
	 * 通知查看
	 * @param notice
	 */
	public Map<String, Object> queryNotice(PageSortReq pageSortReq);
	
	/**
	 * 判断该工程此项活动是否已经下达通知
	 * @param projId
	 * @param actionType
	 * @return
	 */
	public List getNotice(String projId, String actionType);
	
	
	/**
	 * 判断签证是否已经下达通知
	 * @param projId
	 * @param actionType
	 * @return
	 */
	public List getEngineeringNotice(String evId, String actionType);
	
	/**
	 * 根据签证id删除原来的通知
	 * @author fuliwei
	 * @createTime 2017年2月9日
	 * @param evId
	 * @return
	 */
	public void deleteByEvId(String evId);
	
	 /** 删除原来的通知	
	 * @author fuliwei
	 * @createTime 2017年2月14日
	 * @param  projId actionType
	 * @return
	 */
	public void deleteByIdAndActionType(String projId,String actionType);
	
	/**
	 * 审核通知查看
	 * @param notice
	 */
	public int queryAuditNotice(String grade,String type);
	
	/**
	 * 通过工程id和审核类型查询通知
	 * @author fuliwei
	 * @createTime 2017年7月30日
	 * @param 
	 * @return
	 */
	public Notice findByProjIdAndType(String projId, String auditType);
	
	
	/**
	 * 通过工程id]业务单id和审核类型查询通知
	 * @author fuliwei
	 * @createTime 2017年10月23日
	 * @param 
	 * @return
	 */
	public Notice findByProjIdAndTypeAndBusId(String projId,String busId,String auditType);
	
	/**
	 * 查询任务单通知
	 * @author fuliwei
	 * @createTime 2017年11月1日
	 * @param 
	 * @return
	 */
	//public List<Notice> queryPlanNotice(ProjectQueryReq req,List<String> projStatus) ;
	
	
	/**
	 * 查询签字通知是否下达
	 * @param projId
	 * @param actionType
	 * @return
	 */
	public List<Notice> getSignNotice(String businessOrderId, String signType);
	
	
	
	/**
	 * 查询签字通知
	 * @author fuliwei
	 * @createTime 2017年11月28日
	 * @param 
	 * @return
	 */
	public List<Notice> querySignNotice();
}
