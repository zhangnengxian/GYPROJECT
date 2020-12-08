package cc.dfsoft.project.biz.base.withgas.controller;

import cc.dfsoft.project.biz.base.withgas.dto.GasPlanReq;
import cc.dfsoft.project.biz.base.withgas.dto.GasProjectReq;
import cc.dfsoft.project.biz.base.withgas.enums.GasProjectStatusEnum;
import cc.dfsoft.project.biz.base.withgas.service.GasPlanService;
import cc.dfsoft.project.biz.base.withgas.service.GasProjectService;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.common.constant.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping(value="/gasPlan")
public class GasPlanController {

	/**
	 * 带气计划服务类
	 */
	@Resource
	GasPlanService gasPlanService;

	@Resource
	GasProjectService gasProjectService;
	
	/**日志实例*/
	private static Logger logger = LoggerFactory.getLogger(GasPlanController.class);
	
	/**
	 * 打开主页面
	 * @author fuliwei
	 * @createTime 2017-1-18
	 * @param 
	 * @return ModelAndView
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelView=new ModelAndView();
		modelView.setViewName("withgas/gasPlan");
		return modelView;
	}
	
	/**
	 * 打开右侧页面
	 * @author cui
	 * @createTime 2017-8-11
	 * @param 
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/viewPage")
	public ModelAndView viewPage() {
		ModelAndView modelView = new ModelAndView();
//		modelView.addObject("createTime",gasPlanService.getDatabaseDate().getTime());	//当前数据库时间
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		modelView.addObject("loginInfo",loginInfo);
		modelView.setViewName("withgas/gasPlanRight");
		return modelView;
	}

	/**
	 * 通气计划页面弹出搜索
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/gasPlanSearchPopPage")
	public ModelAndView gasPlanSearchPopPage() {
		ModelAndView modelview = new ModelAndView();
		modelview.setViewName("withgas/gasPlanPopPage");
		return modelview;
	}

	/**
	 * 查询带气计划
	 * @author cui
	 * @createTime 2017-8-7
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/queryGasPlan")
	@ResponseBody
	public Map<String,Object> queryGasPlan(HttpServletRequest request, GasPlanReq gasPlanReq){
		try {
			gasPlanReq.setSortInfo(request);
			return gasPlanService.queryGasPlan(gasPlanReq);
		} catch (Exception e) {
			logger.error("带气计划列表查询查询失败！", e);
			return null;
		}
	}

	/**
	 * 查询待编通气计划工程
	 * @author cui
	 * @createTime 2017-8-7
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/queryGasProject")
	@ResponseBody
	public Map<String,Object> queryGasProject(HttpServletRequest request, GasProjectReq gasProjectReq){
		try {
			gasProjectReq.setSortInfo(request);
			gasProjectReq.setGasProjStatus(GasProjectStatusEnum.GAS_PLAN.getValue());
			return gasProjectService.queryGasProject(gasProjectReq);
		} catch (Exception e) {
			logger.error("待通气工程列表查询查询失败！", e);
			return null;
		}
	}
	
	/**
	 * 弹出工程列表
	 * @author cui
	 * @createTime 2017-8-8
	 * @param 
	 * @return ModelAndView
	 */
	@RequestMapping(value="/gasProjectPop")
	public ModelAndView materialList(){
		ModelAndView modelView=new ModelAndView();
		modelView.setViewName("withgas/gasProjectPop");
		return modelView;
	}

	/**
	 * 保存带气计划
	 * @author cui
	 * @createTime 2017-8-8
	 * @param  gasPlanReq
	 * @return String
	 */
	@RequestMapping(value = "/saveGasPlan")
	@ResponseBody
	public String saveGasPlan(@RequestBody(required = true) GasPlanReq gasPlanReq){
		try {
			return gasPlanService.saveGasPlan(gasPlanReq);
		} catch (Exception e) {
			logger.error("带气计划信息保存失败！", e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
}
