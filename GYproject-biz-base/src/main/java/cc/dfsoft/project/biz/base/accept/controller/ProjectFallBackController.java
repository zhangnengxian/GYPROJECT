package cc.dfsoft.project.biz.base.accept.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cc.dfsoft.project.biz.base.baseinfo.enums.BackReasonEnum;
import cc.dfsoft.project.biz.base.project.entity.ProjectType;
import cc.dfsoft.project.biz.base.project.enums.AreaEnum;

/**
 * 工程回退
 * @author fuliwei
 *
 */
@Controller
@RequestMapping(value="/projInfoBack")
public class ProjectFallBackController {
	/** 日志实例 */
	private static Logger logger = LoggerFactory.getLogger(ProjectFallBackController.class);
	
	
	/**
	 * 打开主页面
	 * @author fuliwei
	 * @createTime 2017年11月16日
	 * @param 
	 * @return
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelView=new ModelAndView();
		modelView.setViewName("accept/projInfoBack");
		return modelView;
	}
	
	/**
	 * 打开右侧详述页面
	 * @author fuliwei
	 * @createTime 2017年11月16日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/viewPage")
	public ModelAndView viewPage() {
		ModelAndView modelview = new ModelAndView();
		modelview.setViewName("accept/projInfoBackRight");
		return modelview;
		
	}
	
	
	
	
}
