package cc.dfsoft.project.biz.base.constructmanage.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;

/**
 * 资料下载
 * @author fuliwei
 *
 */
@Controller
@RequestMapping(value="/dataDownLoad")
public class DataDownLoadController {
	/** 日志实例 */
	public static Logger logger = LoggerFactory.getLogger(DataDownLoadController.class);
	
	
	/**
	 *  打开主页面
	 * @author fuliwei
	 * @createTime 2017年8月18日
	 * @param 
	 * @return
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(HttpServletRequest request){
		ModelAndView modelView=new ModelAndView();
		LoginInfo login=SessionUtil.getLoginInfo();
		modelView.addObject("loginName",login); //登录人
		modelView.setViewName("constructmanage/data/dataDownLoad");
		return modelView;
	}
}
