package cc.dfsoft.project.biz.base.accept.controller;

import cc.dfsoft.project.biz.base.accept.service.MarketInfoService;
import cc.dfsoft.project.biz.base.design.dto.DesignDispatchDto;
import cc.dfsoft.project.biz.base.design.dto.DesignerQueryReq;
import cc.dfsoft.project.biz.base.project.dto.ProjectQueryReq;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.project.enums.ProjStatusEnum;
import cc.dfsoft.project.biz.base.project.enums.StepEnum;
import cc.dfsoft.project.biz.base.project.service.ProjectService;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.biz.sys.dept.enums.DeptTypeEnum;
import cc.dfsoft.uexpress.biz.sys.dept.enums.PostTypeEnum;
import cc.dfsoft.uexpress.common.constant.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 市场派工
 * @author wangang
 *
 */
@Controller
@RequestMapping(value="/marketDispatch")
public class MarketDispatchController {
	
	/** 日志实例 */
	private static Logger logger = LoggerFactory.getLogger(MarketDispatchController.class);
	
	/** 工程服务接口 */
	@Resource
	ProjectService projectService;

	/**市场营销员服务*/
	@Resource
	MarketInfoService marketInfoService;
	
	/**
	 * 打开主页面
	 * @author wangang
	 * @createTime 2019年11月27日
	 * @param 
	 * @return
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelView=new ModelAndView();
		modelView.setViewName("accept/marketDispatch");
		return modelView;
	}
	
	/**
	 * 打开右侧页面
	 * @author wangang
	 * @createTime 2019年11月27日
	 * @param 
	 * @return
	 */
	@RequestMapping(value="/viewPage")
	public ModelAndView viewPage(){
		ModelAndView modelView=new ModelAndView();
		modelView.addObject("deptType",DeptTypeEnum.SUBCOMPANY.getValue());
		modelView.setViewName("accept/marketDispatchRight");
		return modelView;
	}
	
	/**
	 * 弹出搜索
	 * @author wangang
	 * @createTime 2019年11月27日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/projectSearchPopPage")
	public ModelAndView projectSearchPopPage() {
		ModelAndView modelview = new ModelAndView();
		modelview.addObject("post", PostTypeEnum.SALESMA.getValue());
		modelview.setViewName("accept/marketDispatchSearchPopPage");
		return modelview;
	}
	
	/**
	 * 工程列表查询
	 * @author wangang
	 * @createTime 2019年11月27日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/queryProject")
	@ResponseBody
	public Map<String,Object> queryProject(HttpServletRequest request,ProjectQueryReq projectQueryReq){
		try {
			projectQueryReq.setSortInfo(request);
			projectQueryReq.setStepId(StepEnum.DESIGN_DISPATCH.getValue());//用步骤id去查
			projectQueryReq.setProjStatusId(ProjStatusEnum.TO_MARKET_DISPATCH.getValue());//待市场派工状态查询
			Map<String,Object> map= projectService.queryProjectByTime(projectQueryReq);
			return map;
		} catch (Exception e) {
			logger.error("工程信息查询失败！", e);
			return null;
		}
	}
	
	/**
	 * 查询详述
	 * @author wangang
	 * @createTime 2019年11月27日
	 * @param 
	 * @return
	 */
	@RequestMapping(value="/viewDispatchResult")
	@ResponseBody
	public Project viewDispatchResult(@RequestParam(required=true) String id){
		Project pro=projectService.viewProject(id);
		return pro;
	}
	
	/**
	 * 派工保存
	 * @author wangang
	 * @createTime 2019年11月27日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/updateProject")
	@ResponseBody
	public String updateProject(@RequestBody(required = true) DesignDispatchDto designDispatchReq){
		try {
			marketInfoService.update(designDispatchReq);
			return Constants.OPERATE_RESULT_SUCCESS;
		} catch (Exception e) {
			logger.error("派工区信息保存失败！", e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
	
	/**
	 * 市场营销员列表查询
	 * @author wangang
	 * @createTime 2019年11月27日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/queryMarket")
	@ResponseBody
	public Map<String,Object> queryMarket(HttpServletRequest request, DesignerQueryReq designerQueryReq){
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			designerQueryReq.setSortInfo(request);
			designerQueryReq.setPostType(PostTypeEnum.SALESMA.getValue());  			//营销员
			designerQueryReq.setProjStatus(ProjStatusEnum.PROJECT_ACCEPT.getValue());	//受理申请
			LoginInfo login= SessionUtil.getLoginInfo();
			//加配置
			String key = login.getDeptId()+"_"+designerQueryReq.getMenuId();
			Object obj = Constants.getSysConfigByKey(key);
			if(obj !=null){
				//有配置 取工程的deptId
				designerQueryReq.setDeptId(obj.toString());
			}else{
				//取当前登录人deptId
				designerQueryReq.setDeptId(login.getDeptId());
			}

			result= marketInfoService.queryMarket(designerQueryReq);
			return result;
		} catch (Exception e) {
			logger.error("工程信息查询失败！", e);
			return null;
		}
	}
}
