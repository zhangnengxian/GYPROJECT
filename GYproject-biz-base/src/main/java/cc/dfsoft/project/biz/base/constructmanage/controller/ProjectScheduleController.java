package cc.dfsoft.project.biz.base.constructmanage.controller;

import cc.dfsoft.project.biz.base.constructmanage.dto.ProgressQueryReq;
import cc.dfsoft.project.biz.base.constructmanage.entity.Progress;
import cc.dfsoft.project.biz.base.constructmanage.service.GraphicProgressService;
import cc.dfsoft.project.biz.base.constructmanage.service.ProgressService;
import cc.dfsoft.project.biz.base.plan.entity.ConstructionPlan;
import cc.dfsoft.project.biz.base.plan.service.ConstructionPlanService;
import cc.dfsoft.project.biz.base.subpackage.entity.SubBudget;
import cc.dfsoft.project.biz.base.subpackage.service.SubBudgetService;
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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 工程进度
 * @author pengtt
 * @modify 2016-07-27
 */
@Controller
@RequestMapping(value="/projectSchedule")
public class ProjectScheduleController {
	
	/** 日志实例 */
	private static Logger logger = LoggerFactory.getLogger(ProjectScheduleController.class);
  
	/**施工计划服务接口*/
	@Resource
	ConstructionPlanService constructionPlanService;
	
	/**工程进度服务接口*/
	@Resource
	ProgressService progressService;

	/**形象进度*/
	@Resource
	GraphicProgressService graphicProgressService;

	@Resource
	SubBudgetService subBudgetService;
	/**
	 * 打开主页面
	 * @return
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelView=new ModelAndView();
		modelView.addObject("progressType", graphicProgressService.queryGraphicProgress());
		modelView.setViewName("constructmanage/projectSchedule");
		return modelView;
	}
	
	/**
	 * 列表条件查询
	 * @param projectQueryReq
	 * @author pengtt
	 * @createTime 2016-07-27
	 * @return
	 */
	@RequestMapping(value = "/queryProgress")
	@ResponseBody
	public Object queryProgress(HttpServletRequest request,ProgressQueryReq progressQueryReq){
		try {
			progressQueryReq.setSortInfo(request);
			progressService.queryTotalProgress(progressQueryReq);
			Map<String,Object> map=progressService.queryProgress(progressQueryReq);
			return map;
		} catch (Exception e) {
			logger.error("工程信息查询失败！", e);
			return null;
		}
	}
	
	
	/**
	 * 获取开工日期
	 * @author pengtt
	 * @createTime 2016-07-28
	 * @param projId
	 * @return
	 *  -------根据工程id查施工进度和开工日期一起返回 by cui-2017-9-20
	 */
	@RequestMapping(value="/getProjStartDate")
	@ResponseBody
	public Map getProjStartDate(String projId){
		try {
			Map m = new HashMap();
			//工程进度
			Progress progress = progressService.queryGraphicProgress(projId);
			SubBudget sb = subBudgetService.viewSubBudget(projId);
			ConstructionPlan cp = constructionPlanService.viewPlanById(projId);
			if(progress!=null){
				m.put("progress",progress);
			}
			if(cp!=null && cp.getPlannedStartDate()!=null){
				Date startDate = cp.getPlannedStartDate();
				m.put("startDate",startDate);
			}
			if(sb!=null && sb.getCostType()!=null){
				String costType = sb.getCostType();
				m.put("costType",costType);
			}
				return m;
		} catch (Exception e) {
			logger.error("查询工程的开工日期失败！", e);
		}
		return null;
	}
	
	/**
	 * 获取某个工程的总进度
	 * @author pengtt
	 * @createTime 2016-07-28
	 * @param progressQueryReq
	 * @return
	 */
	@RequestMapping(value="/getTotalProgress")
	@ResponseBody
	public String getTotalProgress(@RequestBody ProgressQueryReq progressQueryReq){
		return progressService.queryTotalProgress(progressQueryReq);
	}

	/**
	 * 形象进度保存
	 * @author cui
	 * @createTime 2017-09-20
	 * @return
	 */
	@RequestMapping(value="/saveGraphicProgress")
	@ResponseBody
	public String saveGraphicProgress(@RequestBody Progress progress){
		try {
			return progressService.saveGraphicProgress(progress);
		} catch (Exception e) {
			logger.error("工程进度保存失败！", e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
	
	/**
	 * 进度记录批量保存
	 * @author pengtt
	 * @createTime 2016-07-27
	 * @return
	 */
	@RequestMapping(value="/saveProgress")
	@ResponseBody
	public String save(@RequestBody List<Progress> progressList){
		try {
			progressService.saveProgress(progressList);
			return Constants.OPERATE_RESULT_SUCCESS;
		} catch (Exception e) {
			logger.error("工程进度记录批量保存失败！", e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
	
	
	/**
	 * 工程表总进度保存
	 * @author fulwei
	 * @createTime 2017-01-30
	 * @return
	 */
	@RequestMapping(value="/savePrjectTotalProgress")
	@ResponseBody
	public String savePrjectTotalProgress(@RequestBody(required = true) String projId){
		try {
			progressService.savePrjectTotalProgress(projId);
			return Constants.OPERATE_RESULT_SUCCESS;
		} catch (Exception e) {
			logger.error("工程表工程总进度保存失败！", e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
	
}
