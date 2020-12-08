package cc.dfsoft.project.biz.base.complete.controller;

import cc.dfsoft.project.biz.base.complete.entity.DataAcceptance;
import cc.dfsoft.project.biz.base.complete.service.DataAcceptanceRecordService;
import cc.dfsoft.project.biz.base.complete.service.DataAcceptanceService;
import cc.dfsoft.project.biz.base.project.dto.AccessoryQueryReq;
import cc.dfsoft.project.biz.base.project.dto.ProjectQueryReq;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.project.enums.DataCollectionTypeEnum;
import cc.dfsoft.project.biz.base.project.enums.ProjStatusEnum;
import cc.dfsoft.project.biz.base.project.enums.StepEnum;
import cc.dfsoft.project.biz.base.project.service.AccessoryService;
import cc.dfsoft.project.biz.base.project.service.ManageRecordService;
import cc.dfsoft.project.biz.base.project.service.ProjectService;
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
import java.util.Map;
/**
 * 资料验收申请
 * @author fuliwei
 *
 */
@Controller
@RequestMapping(value="/completionDataApply")
public class CompletionDataApplyController {
	/** 日志实例 */
	private static Logger logger=LoggerFactory.getLogger(CompletionDataApplyController.class);
	
	/** 工程服务接口 */
	@Resource
	ProjectService projectService;
	
	/**附件服务接口*/
	@Resource
	AccessoryService accessoryService;
	
	/**资料验收申请*/
	@Resource
	DataAcceptanceService dataAcceptanceService;
	
	/**资料验收记录*/
	@Resource
	DataAcceptanceRecordService dataAcceptanceRecordService;

	@Resource
	ManageRecordService manageRecordService;
	/**
	 *  打开主页面
	 * @author fuliwei
	 * @createTime 2017年6月27日
	 * @param 
	 * @return
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelView=new ModelAndView();
		modelView.addObject("notThrough",Constants.MODULE_CODE_COMPLETE);
		modelView.addObject("stepId",StepEnum.COMPLETION_DATA_AUDIT.getValue());	
		modelView.setViewName("complete/completionDataApply");
		return modelView;
	}
	
	/**
	 * 打开右侧详述页面
	 * @author fuliwei
	 * @createTime 2017年6月27日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/viewPage")
	public ModelAndView viewPage() {
		ModelAndView modelview = new ModelAndView();
		modelview.addObject("sysDate",manageRecordService.getDatabaseDate());//系统时间
		modelview.setViewName("complete/completionDataApplyRight");
		return modelview;
	}
	
	
	/**
	 * 工程列表查询
	 * @author fuliwei
	 * @createTime 2017年7月20日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/queryProject")
	@ResponseBody
	public Map<String,Object> queryProject(HttpServletRequest request,ProjectQueryReq projectQueryReq){
		try {
			projectQueryReq.setSortInfo(request);
			projectQueryReq.setStepId(StepEnum.COMPLETION_DATA_APPLY.getValue());		//用步骤id去查
			projectQueryReq.setProjStatusId(ProjStatusEnum.TO_COMPLETION_DATA_APPLY.getValue());//待资料申请
			Map<String,Object> map= projectService.queryProjectByTime(projectQueryReq);
			return map;
		} catch (Exception e) {
			logger.error("工程信息查询失败！", e);
			return null;
		}
	}
	
	/**
	 * 施工验收资料记录查询
	 * @author fuliwei
	 * @createTime 2017年8月3日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/queryDataAcceptanceRecord")
	@ResponseBody
	public Map<String, Object> queryDataAcceptanceRecord(HttpServletRequest request,AccessoryQueryReq req){
		try {
			req.setSortInfo(request);
			req.setDataType(DataCollectionTypeEnum.ACCEPTANCE_DATA.getValue());//施工验收资料
			Map<String, Object> map= dataAcceptanceRecordService.queryDataAcceptanceRecord(request,req);
			return map;
		} catch (Exception e) {
			logger.error("施工验收资料查询失败！", e);
			return null;
		}
		
	}
	
	/**
	 * 详述
	 * @author fuliwei
	 * @createTime 2017年7月31日
	 * @param 
	 * @return
	 */
	@RequestMapping(value="/viewProject")
	@ResponseBody
	public Project viewProject(HttpServletRequest request,@RequestParam(required=true) String id){
		return dataAcceptanceService.findProjectById(id);
	}
	
	
	/**
	 * 保存资料申请
	 * @author fuliwei
	 * @createTime 2017年8月8日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/saveDataAcceptance")
	@ResponseBody
	public String saveDataAcceptance(@RequestBody(required = true) DataAcceptance dataAcceptance){
		try {
			return dataAcceptanceService.saveDataAcceptance(dataAcceptance);
		} catch (Exception e) {
			logger.error("保存资料申请失败！",e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
	
}
