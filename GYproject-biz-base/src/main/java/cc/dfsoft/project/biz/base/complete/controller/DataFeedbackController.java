package cc.dfsoft.project.biz.base.complete.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cc.dfsoft.project.biz.base.baseinfo.service.DocTypeService;
import cc.dfsoft.project.biz.base.complete.entity.FilingData;
import cc.dfsoft.project.biz.base.complete.service.DataFeedbackService;
import cc.dfsoft.project.biz.base.project.dto.ManageRecordQueryReq;
import cc.dfsoft.project.biz.base.project.dto.ProjectQueryReq;
import cc.dfsoft.project.biz.base.project.enums.ProjLtypeEnum;
import cc.dfsoft.project.biz.base.project.enums.ProjStatusEnum;
import cc.dfsoft.project.biz.base.project.enums.StepEnum;
import cc.dfsoft.project.biz.base.project.service.ManageRecordService;
import cc.dfsoft.project.biz.base.project.service.ProjectService;
import cc.dfsoft.uexpress.common.constant.Constants;

/**
 * 资料反馈
 * @createTime 2016-09-18
 * @author zhangmeng
 *
 */
@Controller
@RequestMapping(value="/dataFeedback")
public class DataFeedbackController {

	/** 日志实例 */
	private static Logger logger=LoggerFactory.getLogger(JointAcceptanceController.class);
	
	/**工程服务接口*/
	@Resource
	ProjectService projectService;
	
	/**资料反馈服务接口*/
	@Resource
	DataFeedbackService dataFeedbackService;
	
	/**管理记录服务接口*/
	@Resource
	ManageRecordService manageRecordService;
	
	/**单据类型服务接口*/
	@Resource
	DocTypeService docTypeService;
	
	/**
	 * 打开主页面
	 * @return
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelView=new ModelAndView();
		modelView.setViewName("complete/dataFeedback");
		return modelView;
	}
	/**
	 * 打开右侧页面
	 * @return
	 */
	@RequestMapping(value = "/viewPage")
	public ModelAndView viewPlanningPage() {
		ModelAndView modelview = new ModelAndView();
		modelview.setViewName("complete/dataFeedbackRight");
		return modelview;
	}
	/**
	 * 工程列表条件查询
	 * @param projectQueryReq
	 * @author 
	 * @createTime 2016-09-18 
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/queryDataFeedback")
	@ResponseBody
	public Map<String,Object> queryJointAcceptancer(ProjectQueryReq projectQueryReq){
		try {
			//projectQueryReq.setProjStatusId(ProjStatusEnum.DATA_FEEDBACK.getValue());  //待资料反馈
			//projectQueryReq.setStepId(StepEnum.DATA_FEEDBACK.getValue());
			return projectService.queryProjectByTime(projectQueryReq);
		} catch (Exception e) {
			logger.error("工程信息查询失败！", e);
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
		modelview.setViewName("complete/searchDataPopPage");
		return modelview;
	}
	
	/**
	 * 归档资料详述
	 * @return
	 */
	@RequestMapping(value = "/jointDetail")
	@ResponseBody
	public FilingData jointDetail(@RequestParam(required=true) String id){
		try {
			return dataFeedbackService.jointDetail(id);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	
	/**
	 * 管理记录列表查询
	 * @author zhangmeng
	 * @createTime 2016-09-19
	 * @param manageRecordQueryReq
	 * @return
	 */
	@RequestMapping(value = "/queryManageRecord")
	@ResponseBody
	public Map<String,Object> queryManageRecord(ManageRecordQueryReq manageRecordQueryReq){
		try {
			//manageRecordQueryReq.setStepId(StepEnum.DATA_FEEDBACK.getValue());	//资料反馈
			return manageRecordService.queryManageRecord(manageRecordQueryReq);
		} catch (Exception e) {
			logger.error("管理记录列表查询失败！",e);
			return null;
		}
	}
	
	/**
	 * 资料反馈确认保存
	 * @author 张萌
	 * @createTime 2016-09-19
	 * @param manageRecord
	 * @return
	 */
	@RequestMapping(value = "/dataFeedbackSave")
	@ResponseBody
	public String auditSave(@RequestBody(required = true) FilingData dataRrcord){
		
		try {
			dataFeedbackService.saveData(dataRrcord);
			return Constants.OPERATE_RESULT_SUCCESS;
		} catch (Exception e) {
			logger.error("资料反馈确认失败！",e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
		
	}
}
