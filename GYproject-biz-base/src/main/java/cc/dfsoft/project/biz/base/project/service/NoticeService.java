package cc.dfsoft.project.biz.base.project.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import cc.dfsoft.project.biz.base.project.dto.ProjectQueryReq;
import cc.dfsoft.project.biz.base.project.entity.Notice;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.uexpress.biz.sys.auth.entity.Menu;
import cc.dfsoft.uexpress.common.dto.PageSortReq;

public interface NoticeService {

	/**
	 * 下达通知
	 * @param projName   工程名称
	 * @param actionTime 活动时间
	 * @param actionName 通知活动名称
	 */
	public void	 saveNotice(String projId ,String projName ,Date actionTime, String actionName , String actionType);

	/**
	 * 通知查看
	 * @param pageSortReq
	 * @return
	 */
	public Map<String, Object> queryNotice(PageSortReq pageSortReq);
	
	/**
	 * 判断该工程此项活动是否已经下达通知
	 * @param projId
	 * @param actionType
	 * @return
	 */
	public boolean checkExist(String projId, String actionType);

	/**
	 * 将通知设置为失效
	 * @param projId
	 * @param actionType
	 */
	public void updateNotice(String projId, String actionType);
	
	/**
	 * 将签证通知设置为失效
	 * @param evId
	 * @param actionType
	 */
	public void updateEngineeringNotice(String evId, String actionType);
	
	/**
	 * 判断该签证是否已经下达通知
	 * @param evId
	 * @param actionType
	 * @return
	 */
	public boolean checkEngineeringExist(String projId, String actionType);
	
	/**
	 * 下达签证通知
	 * @param projName   工程名称
	 * @param actionTime 活动时间
	 * @param actionName 通知活动名称
	 */
	public void	 saveNotice(String evId,String projId ,String projName ,Date actionTime, String actionName , String actionType,Boolean flag);

	/**
	 * 删除原来的通知	
	 * @author fuliwei
	 * @createTime 2017年2月14日
	 * @param  projId actionType
	 * @return
	 */
	public void deleteByIdAndActionType(String projId,String actionType);
	
	/**
	 * 查询工作通知
	 * @author fuliwei
	 * @createTime 2017年7月23日
	 * @param 
	 * @return
	 */
	public Map<String, Object> queryWorkNotice(PageSortReq pageSortReq);
	
	/**
	 * 查询工作通知条数
	 * @author fuliwei
	 * @createTime 2017年7月23日
	 * @param 
	 * @return
	 */
	public List<Notice> queryStausAndRole();
	
	/**
	 * 查询业务沟通的通知
	 * @author fuliwei
	 * @createTime 2017年9月22日
	 * @param 
	 * @return
	 */
	public List<Notice> queryBusinessCommunicationNotice();
	
	/**
	 * 修改密码提示
	 * @author fuliwei
	 * @createTime 2017年9月25日
	 * @param 
	 * @return
	 */
	public Notice queryModifyPassword();
	
	
	/**
	 * 查询任务单通知
	 * @author fuliwei
	 * @createTime 2017年11月1日
	 * @param 
	 * @return
	 */
	public List<Notice> queryPlanNotice(ProjectQueryReq req,List<String> projStatus) ;
	
	
	/**
	 * 查询施工派遣或监理派遣任务
	 * @author fuliwei
	 * @createTime 2017年11月21日
	 * @param 
	 * @return
	 */
	public List<Notice> queryDispatchNotice(String type);
	
	/**
	 * 查询收费通知
	 * @author fuliwei
	 * @createTime 2017年11月24日
	 * @param 
	 * @return
	 */
	public List<Notice> queryAmountNotice(String arType, String arFlag, String deptId);
	
	/**
	 * 保存签字通知
	 * @author fuliwei
	 * @createTime 2017年11月28日
	 * @param 
	 * @return
	 */
	public void saveSignNotice(Project pro, String businessOrderId , String signType,String actionName);
	
	
	/**
	 * 查询签字通知置为有效
	 * @author fuliwei
	 * @createTime 2018年1月4日
	 * @param 
	 * @return
	 */
	public void updateSignNoticeValid(String businessOrderId, String signType);
	
	
	/**
	 * 查询签字通知是否下达
	 * @author fuliwei
	 * @createTime 2017年11月28日
	 * @param 
	 * @return
	 */
	public boolean checkSignNoticeExist(String businessOrderId, String signType);
	
	/**
	 * 设置签字通知无效
	 * @author fuliwei
	 * @createTime 2017年11月28日
	 * @param 
	 * @return
	 */
	public void updateSignNotice(String businessOrderId, String signType);
	
	
	/**
	 * 查询签字通知
	 * @author fuliwei
	 * @createTime 2017年11月28日
	 * @param 
	 * @return
	 */
	public List<Notice> querySignNotice();
	
	
	//任务门户的方法 
	/**
	 * 查询工作通知
	 * @author fuliwei
	 * @createTime 2018年1月15日
	 * @param 
	 * @return
	 */
	public Map<String, Object> queryWorkNoticeNew(PageSortReq pageSortReq);
	
	/**
	 * 查询审核通知
	 * @author fuliwei
	 * @createTime 2018年1月15日
	 * @param 
	 * @return
	 */
	public Map<String, Object> queryAuditNotice(PageSortReq pageSortReq);
	
	/**
	 * 查询派遣通知
	 * @author fuliwei
	 * @createTime 2018年1月22日
	 * @param 
	 * @return
	 */
	public Map<String, Object> queryDispatchNotice(PageSortReq pageSortReq);
	
	/**
	 * 查询变更签证调整通知
	 * @author fuliwei
	 * @createTime 2018年1月31日
	 * @param 
	 * @return
	 */
	public List<Notice> queryEnginnerNotice();

	/**
	 * 获取登录用户的角色菜单
	 * @author liaoyq
	 * @createTime 2018年5月18日
	 * @return
	 */
	List<Menu> getLoginStaffNoticeMenu();

	/**
	 * 根据菜单ID查询签字标准配置的dataType
	 * @author liaoyq
	 * @createTime 2018年5月18日
	 * @return
	 */
	List<String> getNoticeMenuDateType();

	/**
	 * 查询施工组织重报的通知
	 * @author liaoyq
	 * @createTime 2018年6月8日
	 * @return
	 */
	List<Notice> queryCuOrganizationNotice();

	/**
	 * 施工单位预算员获取 签证已审核，施工单位还没有确认的签证记录
	 * @author liaoyq
	 * @createTime 2018年6月13日
	 * @return
	 */
	List<Notice> queryEVNotice();

	/**
	 * 待费用申请的工程通知查询
	 * @author liaoyq
	 * @createTime 2018年6月20日
	 * @return
	 */
	List<Notice> queryPayNotice();

	/**
	 * 设计人员获取相应变更工程需要上传材料的
	 * @author liaoyq
	 * @createTime 2018年6月28日
	 * @return
	 */
	List<Notice> queryChangementNotice();

	/**
	 * 查询待审核的变更记录
	 * @author liaoyq
	 * @createTime 2018年6月28日
	 * @return
	 */
	List<Notice> queryChangementAuditNotice();

	/**
	 * 通知
	 * @param pageSortReq
	 * @return
	 */
	public Map<String, Object> queryWorkNotices(PageSortReq pageSortReq);
	
}
