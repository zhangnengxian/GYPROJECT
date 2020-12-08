package cc.dfsoft.project.biz.base.inspection.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
/**
 * 立管验收
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value="/standPipe")
public class StandPipeController {
	
	/** 日志实例 */
	private static Logger logger = LoggerFactory.getLogger(StandPipeController.class);
	
	/**
	 * 户内燃气表安装
	 * @author
	 * @createTime 2016-12-15
	 * @param
	 * @return
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelView = new ModelAndView();
		modelView.setViewName("inspection/standPipe");
		return modelView;
	}
}
