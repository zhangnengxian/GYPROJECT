package cc.dfsoft.project.biz.base.project.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 投资管理
 * @author fuliwei
 *
 */
@Controller
@RequestMapping(value="/investmentManage")
public class InvestmentManageController {
	/** 日志实例 */
	private static Logger logger=LoggerFactory.getLogger(InvestmentManageController.class);
	
	/**
	 * 打开主页面
	 * @author fuliwei
	 * @createTime 2017年3月4日
	 * @param 
	 * @return
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelView=new ModelAndView();
		modelView.setViewName("project/investmentManage/investmentManage");
		return modelView;
	}
}
