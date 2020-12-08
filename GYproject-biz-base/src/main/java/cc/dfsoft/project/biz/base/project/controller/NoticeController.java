

package cc.dfsoft.project.biz.base.project.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cc.dfsoft.project.biz.base.project.dto.ProjectQueryReq;
import cc.dfsoft.project.biz.base.project.enums.ProjStatusEnum;
import cc.dfsoft.project.biz.base.project.service.NoticeService;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dao.StaffRoleDao;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.biz.sys.dept.entity.StaffRole;
import cc.dfsoft.uexpress.common.dto.PageSortReq;
import cc.dfsoft.uexpress.common.util.ResultUtil;

@Controller
@RequestMapping(value = "/notice")
public class NoticeController {
	
	private static Logger logger = LoggerFactory.getLogger(NoticeController.class);
	
	@Resource 
	NoticeService noticeService;
	
	@Resource
	StaffRoleDao staffRoleDao;
	
	@RequestMapping(value = "/main")
	public ModelAndView main() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("");
		return modelAndView;
	}
	
	
	/**
	 * 通知查看
	 * @param request
	 * @param pageSortReq
	 * @return
	 */
	@RequestMapping(value = "/queryNotice")
	@ResponseBody
	public Map <String, Object> queryNotice(HttpServletRequest request, PageSortReq pageSortReq ) {
		try {
			/*LoginInfo loginInfo = SessionUtil.getLoginInfo();
			StaffRole staffRole = staffRoleDao.queryStaffRoleInfo("", loginInfo.getStaffId());  // 提取员工角色
			if (staffRole!=null) {
				String roleIds = staffRole.getRoleIds();
				if (roleIds.contains("1021")) {  
					pageSortReq.setSortInfo(request);
					Map<String,Object> map = noticeService.queryNotice(pageSortReq);
					return map;
				}
			}
			return ResultUtil.pageResult(0, 0, null);*/
			pageSortReq.setSortInfo(request);
			return noticeService.queryWorkNotice(pageSortReq);
		} catch (Exception e) {
			logger.error("通知信息查询失败！", e);
			return null;
		}
	}
	
	
	/**
	 * 任务门户-查询工作通知
	 * @author fuliwei
	 * @createTime 2018年1月15日
	 * @param 
	 * @return
	 * 该方法已作废，标记人王会军。
	 */
	@RequestMapping(value = "/queryWorkNotice")
	@ResponseBody
	public Map<String,Object> queryWorkNoticeNew(HttpServletRequest request,PageSortReq pageSortReq){
		try {
			pageSortReq.setSortInfo(request);
			Map<String,Object> map = noticeService.queryWorkNoticeNew(pageSortReq);
			return map;
		} catch (Exception e) {
			logger.error("工程信息查询失败！", e);
			return null;
		}
	}
	
	/**
	 * 任务门户-查询审核通知--作废
	 * @author fuliwei
	 * @createTime 2018年1月15日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/queryAuditNotice")
	@ResponseBody
	public Map<String,Object> queryAuditNotice(HttpServletRequest request,PageSortReq pageSortReq){
		try {
			pageSortReq.setSortInfo(request);
			Map<String,Object> map = noticeService.queryAuditNotice(pageSortReq);
			return map;
		} catch (Exception e) {
			logger.error("工程信息查询失败！", e);
			return null;
		}
	}
	
	/**
	 * 任务门户-查询审核通知
	 * @author fuliwei
	 * @createTime 2018年1月15日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/queryDispatchNotice")
	@ResponseBody
	public Map<String,Object> queryDispatchNotice(HttpServletRequest request,PageSortReq pageSortReq){
		try {
			pageSortReq.setSortInfo(request);
			Map<String,Object> map = noticeService.queryDispatchNotice(pageSortReq);
			return map;
		} catch (Exception e) {
			logger.error("工程信息查询失败！", e);
			return null;
		}
	}
	
	
}
