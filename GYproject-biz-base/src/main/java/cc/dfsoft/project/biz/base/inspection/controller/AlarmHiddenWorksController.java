package cc.dfsoft.project.biz.base.inspection.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cc.dfsoft.project.biz.base.inspection.dto.ProjectChecklistQueryReq;
import cc.dfsoft.project.biz.base.inspection.enums.ProjectChecklistTypeEnum;
import cc.dfsoft.project.biz.base.inspection.service.ProjectChecklistService;
import cc.dfsoft.uexpress.biz.sys.dept.enums.PostTypeEnum;
import cc.dfsoft.uexpress.common.constant.Constants;


/**
 * 警报隐藏工程报验
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value="/alarmHiddenWorks")
public class AlarmHiddenWorksController {
	
	/** 日志实例 */
	private static Logger logger = LoggerFactory.getLogger(AlarmHiddenWorksController.class);
	
	/**工程报验单服务接口*/
	@Resource
	ProjectChecklistService projectChecklistService;
	
	/**
	 * 隐蔽工程主页面
	 * @return
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelView = new ModelAndView();
		modelView.addObject("pcDesId",ProjectChecklistTypeEnum.HIDDEN_WORKS_ALARM.getValue());//隐蔽工程
		modelView.addObject("qualitativeCheckMember",PostTypeEnum.QUALITATIVE_CHECK_MEMBER.getValue());//质检员
		modelView.addObject("deputyDirector",PostTypeEnum.DEPUTY_DIRECTOR.getValue());//副处长
		modelView.addObject("sujgj",PostTypeEnum.SUJGJ.getValue());//监理
		modelView.addObject("path",Constants.DISK_PATH+"sign/");
		modelView.addObject("drawUrl1",Constants.DISK_PATH+Constants.DIAGRAM_DISK_PATH);
		modelView.setViewName("inspection/alarmHiddenWorks");
		return modelView;
	}
	
	/**
	 * 工程报验单条件查询
	 * @author fuliwei
	 * @createTime 2016-07-22
	 * @param listQueryReq 查询条件dto
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/queryProjectList")
	@ResponseBody
	public Map<String,Object> queryProjectList(HttpServletRequest request,ProjectChecklistQueryReq listQueryReq){
		try {
			listQueryReq.setSortInfo(request);
			listQueryReq.setPcDesId(ProjectChecklistTypeEnum.HIDDEN_WORKS_ALARM.getValue());
			return projectChecklistService.queryPrProjectChecklist(listQueryReq);
		} catch (Exception e) {
			logger.error("报验单信息查询失败！", e);
			return null;
		}
	}
	
}
