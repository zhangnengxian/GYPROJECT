package cc.dfsoft.project.biz.base.accept.controller;

import cc.dfsoft.project.biz.base.accept.service.ScaleDetailCommissionService;
import cc.dfsoft.project.biz.base.baseinfo.service.SystemSetService;
import cc.dfsoft.project.biz.base.design.entity.DesignInfo;
import cc.dfsoft.project.biz.base.design.service.DesignInfoService;
import cc.dfsoft.project.biz.base.project.dto.ProjectQueryReq;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.project.enums.ProjLtypeEnum;
import cc.dfsoft.project.biz.base.project.enums.ProjStatusEnum;
import cc.dfsoft.project.biz.base.project.enums.StepEnum;
import cc.dfsoft.project.biz.base.project.service.ProjectService;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.util.SessionUtils;
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
import java.util.Map;

/**
 * 资料采集
 * todo:后台逻辑
 * @author liaoyq
 *
 */
@Controller
@RequestMapping(value="/dataAcquisition")
public class DataAcquisitionController {
	
	/** 日志实例 */
	private static Logger logger = LoggerFactory.getLogger(DataAcquisitionController.class);
	
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
		modelView.addObject("projNo", SessionUtils.getProjNo());
		modelView.addObject("notThrough",Constants.MODULE_CODE_PROJECTAPPROVAL);
		modelView.addObject("stepId",StepEnum.DESIGN_DRAWING.getValue());
		modelView.addObject("curStepId",StepEnum.DATA_ACQUISITION.getValue());
		modelView.setViewName("accept/dataAcquisition");
		return modelView;
	}
	
	/**
	 * 打开右侧页面
	 * @return
	 */
	@RequestMapping(value = "/viewPage")
	public ModelAndView viewPage(String projId,String corpId,String menuId) {
		String key = corpId+"_"+menuId;
		Object obj = Constants.getSysConfigByKey(key);
		String viewUrl = "";
		if(obj !=null){
			viewUrl = obj.toString();
		}else{
			viewUrl = "dataAcquisitionRight";
		}
		ModelAndView modelview = new ModelAndView();
		modelview.addObject("budgetType",Constants.getConstantsMapByKey(Constants.MODULE_CODE_BUDGET_TYPE));
//		modelview.setViewName("accept/dataAcquisitionRight");
		dataAcquisition(modelview,key);//安顺资料收集
		modelview.setViewName("accept/"+viewUrl);
		return modelview;
	}

	public void dataAcquisition(ModelAndView model,String key){ //安顺资料收集
		Object dataAcquisition = Constants.getSysConfigByKey(key+"_dataAcquisition");
		if(dataAcquisition !=null){
			model.addObject("viewUrl", dataAcquisition.toString());
		}
	}

	/**
	 * 工程列表查询
	 * @author fuliwei
	 * @createTime 2017年7月26日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/queryProject")
	@ResponseBody
	public Map<String,Object> queryProject(HttpServletRequest request,ProjectQueryReq projectQueryReq){
		try {
			projectQueryReq.setSortInfo(request);//用于排序查询
			projectQueryReq.setProjStatusId(ProjStatusEnum.TO_DATA_ACQUISITION.getValue());//待资料收集
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
	@RequestMapping(value="/viewProject")
	@ResponseBody
	public Project viewProject(HttpServletRequest request,@RequestParam(required=true) String id){
		Project pro=projectService.viewProject(id);
		return pro;
	}
	
	/**
	 * 弹出搜索屏
	 * @return
	 */
	@RequestMapping(value = "/searchPopPage")
	public ModelAndView projectSearchPopPage() {
		ModelAndView modelview = new ModelAndView();
		modelview.addObject("projLtype", ProjLtypeEnum.values());
		modelview.setViewName("accept/searchPopPage");
		return modelview;
	}
	
	/**
	 * 保存数据
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/saveData")
	@ResponseBody
	public String saveDesignInfo(@RequestBody(required = true) DesignInfo designInfo){
		try {
			//todo:具体修改
			return designInfoService.saveDesignInfo(designInfo);
		} catch (Exception e) {
			logger.error("委托区信息保存失败！", e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
	
}
