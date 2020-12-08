package cc.dfsoft.project.biz.base.accept.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cc.dfsoft.project.biz.base.accept.service.ScaleDetailCommissionService;
import cc.dfsoft.project.biz.base.baseinfo.service.SystemSetService;
import cc.dfsoft.project.biz.base.design.entity.DesignInfo;
import cc.dfsoft.project.biz.base.design.service.DesignInfoService;
import cc.dfsoft.project.biz.base.project.dto.ProjectQueryReq;
import cc.dfsoft.project.biz.base.project.dto.ScaleDetailQueryReq;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.project.enums.ProjLtypeEnum;
import cc.dfsoft.project.biz.base.project.enums.ProjStatusEnum;
import cc.dfsoft.project.biz.base.project.enums.StepEnum;
import cc.dfsoft.project.biz.base.project.service.ProjectService;
import cc.dfsoft.uexpress.common.constant.Constants;

/**
 * 设计委托
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value="/designCommission")
public class DesignCommissionController {
	
	/** 日志实例 */
	private static Logger logger = LoggerFactory.getLogger(DesignCommissionController.class);
	
	/** 工程服务接口 */
	@Resource
	ProjectService projectService;
	
	/** 工程规模明细服务接口 */
	@Resource
	ScaleDetailCommissionService scaleDetailCommissionService;
	
	/** 设计信息服务接口*/
	@Resource
	DesignInfoService designInfoService;
	@Resource
	SystemSetService systemSetService;
	/**
	 * 打开主页面
	 * @return
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelView=new ModelAndView();
		modelView.addObject("projStatus", ProjStatusEnum.values());
		modelView.setViewName("accept/designCommission");
		return modelView;
	}
	
	/**
	 * 打开右侧页面
	 * @return
	 */
	@RequestMapping(value = "/viewPage")
	public ModelAndView viewPage() {
		ModelAndView modelview = new ModelAndView();
		modelview.addObject("projLtype", ProjLtypeEnum.values());
		modelview.setViewName("accept/designCommissionRight");
		return modelview;
	}
	
	/**
	 * 工程列表查询
	 * @param projectQueryReq
	 * @return
	 */
	@RequestMapping(value = "/queryProject")
	@ResponseBody
	public Map<String,Object> queryProject(HttpServletRequest request,ProjectQueryReq projectQueryReq){
		try {
			projectQueryReq.setSortInfo(request);//用于排序查询
			//projectQueryReq.setStepId(StepEnum.DESINE_COMMISSION.getValue());
			return projectService.queryProjectByTime(projectQueryReq);
		} catch (Exception e) {
			logger.error("工程信息查询失败！", e);
			return null;
		}
	}
	
	/**
	 * 详述
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/viewdesignCommission")
	@ResponseBody
	public Project viewContract(HttpServletRequest request,@RequestParam(required=true) String id){
		Project pro=projectService.viewProject(id);
		return pro;
	}
	/**
	 * 工程规模明细
	 * @param scaleDetailQueryReq
	 * @return
	 */
	@RequestMapping(value = "/queryScaleDetail")
	@ResponseBody
	public Map<String,Object> queryScaleDetail(ScaleDetailQueryReq scaleDetailQueryReq){
		try {
			return scaleDetailCommissionService.queryScaleDetail(scaleDetailQueryReq);
		} catch (Exception e) {
			logger.error("工程规模明细信息查询失败！", e);
			return null;
		}
	}
	
	/**
	 * 弹出搜索屏
	 * @return
	 */
	@RequestMapping(value = "/projectSearchPopPage")
	public ModelAndView projectSearchPopPage() {
		ModelAndView modelview = new ModelAndView();
		modelview.addObject("projLtype", ProjLtypeEnum.values());
		modelview.setViewName("accept/searchPopPage");
		return modelview;
	}
	
	/**
	 * 保存
	 * @param contract
	 * @return
	 */
	@RequestMapping(value = "/saveDesignCommission")
	@ResponseBody
	public String saveDesignInfo(@RequestBody(required = true) DesignInfo designInfo){
		try {
			return designInfoService.saveDesignInfo(designInfo);
		} catch (Exception e) {
			logger.error("委托区信息保存失败！", e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
	
}
