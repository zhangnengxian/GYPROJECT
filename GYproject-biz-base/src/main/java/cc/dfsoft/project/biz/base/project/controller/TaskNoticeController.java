package cc.dfsoft.project.biz.base.project.controller;

import cc.dfsoft.project.biz.base.project.service.SignNoticeService;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.common.dto.PageSortReq;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@RequestMapping(value="/taskNotice")
public class TaskNoticeController {
	
	/** 日志实例 */
	private static Logger logger = LoggerFactory.getLogger(TaskNoticeController.class);
	
	/**签字通知*/
	@Resource
	SignNoticeService signNoticeService;
	
	
	/**
	 * 打开页面
	 * @author fuliwei
	 * @createTime 2018年1月10日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/main")
	public ModelAndView main() {
		ModelAndView modelview = new ModelAndView();
		modelview.addObject("login", SessionUtil.getLoginInfo());
		modelview.setViewName("project/task/taskNotice");
		return modelview;
	}
	
	
	/**
	 * 任务门户-查询签字通知
	 * @author fuliwei
	 * @createTime 2018年1月21日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/querySignNotice")
	@ResponseBody
	public Map<String,Object> queryAuditNotice(HttpServletRequest request,PageSortReq pageSortReq){
		try {
			pageSortReq.setSortInfo(request);
			Map<String,Object> map = signNoticeService.querySignNotice(pageSortReq);
			return map;
		} catch (Exception e) {
			logger.error("签字通知查询失败！", e);
			return null;
		}
	}



	/**
	 * @MethodDesc: projNo 存入session中
	 * @Author zhangnx
	 * @Date 2019/3/21 15:15
	 */
	@RequestMapping(value = "/saveProjNoSession")
	@ResponseBody
	public String saveSessionProjId(HttpServletRequest request){
		HttpSession session = request.getSession();
		session.setAttribute("projNo", request.getParameter("projNo"));
		session.setAttribute("businessOrderId", request.getParameter("businessOrderId"));
		return "success";
	}
}
