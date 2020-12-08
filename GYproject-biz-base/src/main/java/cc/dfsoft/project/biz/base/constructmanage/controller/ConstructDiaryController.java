package cc.dfsoft.project.biz.base.constructmanage.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cc.dfsoft.project.biz.base.constructmanage.dto.DailyLogQueryReq;
import cc.dfsoft.project.biz.base.constructmanage.entity.DailyLog;
import cc.dfsoft.project.biz.base.constructmanage.service.DailyLogService;
import cc.dfsoft.project.biz.base.project.dto.ProjectViewDto;
import cc.dfsoft.project.biz.base.project.service.ProjectService;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.biz.sys.dept.enums.PostTypeEnum;
import cc.dfsoft.uexpress.common.constant.Constants;

/**
 * 施工日志
 * @author  pengtt
 */
@Controller
@RequestMapping(value="/constructDiary")
public class ConstructDiaryController {
	
	/**输出日志实例*/
	private static Logger logger = LoggerFactory.getLogger(ConstructDiaryController.class);
	
	/**日志服务接口*/
	@Resource
	DailyLogService dailyLogService;
	
	@Resource
	ProjectService projectService;
	
	/**
	 * 施工日志主页面
	 * @return
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelView=new ModelAndView();
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		modelView.addObject("loginPost",loginInfo.getPost());
		modelView.addObject("BUILDER",PostTypeEnum.BUILDER.getValue());
		modelView.addObject("CONSTRUCTION",PostTypeEnum.CONSTRUCTION.getValue());
		modelView.addObject("postType",PostTypeEnum.SUB_FIELDPRINCIPAL);
		modelView.addObject("imgUrl",Constants.DISK_PATH+Constants.SIGN_DISK_PATH); 	//img url
		modelView.setViewName("constructmanage/constructDiary");
		return modelView;
	}
	/**
	 * 施工日志右侧页面
	 * @return
	 */
	@RequestMapping(value = "/viewPage")
	public ModelAndView viewPage() {
		ModelAndView modelview = new ModelAndView();
		modelview.setViewName("constructmanage/constructDiaryRight");
		return modelview;
		
	}

	/***
	 * 工程日志单列表查询
	 * @param request
	 * @param materialListQueryReq
	 * @return
	 */
	@RequestMapping(value = "/queryDailyLog")
	@ResponseBody
	public Map<String,Object> queryDailyLog(HttpServletRequest request,DailyLogQueryReq dailyLogQueryReq){
		try {
			dailyLogQueryReq.setSortInfo(request);
			Map<String,Object> result = dailyLogService.queryDailyLog(dailyLogQueryReq);
			return result;
		} catch (Exception e) {
			logger.error("日志---工程日志列表查询失败！", e);
			return null;
		}
	}
	
	/**
	 * 保存对象
	 * @param dailyLog
	 * @return
	 */
	@RequestMapping(value = "/saveDailyLog")
	@ResponseBody
	public Object saveDailyLog(HttpServletRequest request,@RequestBody(required = true) DailyLog dailyLog){
		try {
			Map<String,Object> imgurl = dailyLogService.saveDailyLog(dailyLog);
			imgurl.put("dlId", dailyLog.getDlId());
			return imgurl;
			//return Constants.OPERATE_RESULT_SUCCESS;
		} catch (Exception e) {
			request.getSession().setAttribute("singtureType","error");
			logger.error("日志---工程日志保存失败！", e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
	
	/**
	 * 通过工程id查询工程对象
	 * @param projId
	 * @return
	 */
	@RequestMapping(value = "/findByProjId")
	@ResponseBody
	public ProjectViewDto findByProjId(String projId){
		try {
			return projectService.diaryViewProject(projId);
		} catch (Exception e) {
			logger.error("工程id查询工程对象失败！", e);
			return null;
		}
	}
	
	@RequestMapping(value = "/byDlIdDeletedialy") 
	@ResponseBody
	public String byDlIdDeletedialy (String dlId){
		try {
			dailyLogService.byDlIdDeletedialy(dlId);			
			return Constants.OPERATE_RESULT_SUCCESS;
		} catch (Exception e) {
			// TODO: handle exception
		    System.err.println(e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	
		
		
	}
	
	
}
