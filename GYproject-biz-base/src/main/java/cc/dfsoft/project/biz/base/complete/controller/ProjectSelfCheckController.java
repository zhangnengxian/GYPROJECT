package cc.dfsoft.project.biz.base.complete.controller;

import cc.dfsoft.project.biz.base.accept.controller.ProjectAcceptController;
import cc.dfsoft.project.biz.base.complete.entity.CheckItem;
import cc.dfsoft.project.biz.base.complete.entity.SelfInspectionList;
import cc.dfsoft.project.biz.base.complete.entity.SelfInspectionRecord;
import cc.dfsoft.project.biz.base.complete.enums.CheckResultEnum;
import cc.dfsoft.project.biz.base.complete.enums.CheckTypeEnum;
import cc.dfsoft.project.biz.base.complete.service.CheckItemService;
import cc.dfsoft.project.biz.base.complete.service.JointAcceptanceService;
import cc.dfsoft.project.biz.base.complete.service.SelfInspectionListService;
import cc.dfsoft.project.biz.base.complete.service.SelfInspectionRecordService;
import cc.dfsoft.project.biz.base.project.dao.ProjectDao;
import cc.dfsoft.project.biz.base.project.dto.ProjectQueryReq;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.project.enums.ProjStatusEnum;
import cc.dfsoft.project.biz.base.project.enums.QualifiedEnum;
import cc.dfsoft.project.biz.base.project.enums.SignDataTypeEnum;
import cc.dfsoft.project.biz.base.project.enums.StepEnum;
import cc.dfsoft.project.biz.base.project.service.OperateRecordService;
import cc.dfsoft.project.biz.base.project.service.ProjectService;
import cc.dfsoft.uexpress.common.constant.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
/**
 * 工程自检
 * @author fuliwei
 *
 */
@Controller
@RequestMapping(value="/projectSelfCheck")
public class ProjectSelfCheckController {
	
	/** 日志实例 */
	private static Logger logger = LoggerFactory.getLogger(ProjectAcceptController.class);
	
	/** 工程服务接口 */
	@Resource
	ProjectService projectService;
	
	@Autowired
	JointAcceptanceService jointAcceptanceService;
	
	/** 自检单服务接口*/
	@Resource
	SelfInspectionListService selfInspectionListService;
	
	/** 自检记录服务接口*/
	@Resource
	SelfInspectionRecordService selfInspectionRecordService;
	
	/** 自检项服务接口*/
	@Resource
	CheckItemService checkItemService;

	@Resource
	ProjectDao projectDao;

	@Resource
	OperateRecordService operateRecordService;

	/**
	 * 打开主页面
	 * @author fuliwei
	 * @createTime 2017年7月31日
	 * @param 
	 * @return
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelView;
		modelView = new ModelAndView();
		modelView.addObject("sysDate",projectDao.getDatabaseDate());
		modelView.setViewName("complete/projectSelfCheck");
		return modelView;
	}
	
	/**
	 * 打开质量自检
	 * @author fuliwei
	 * @createTime 2017年7月31日
	 * @param 
	 * @return
	 */
	@RequestMapping(value="/qualityCheck")
	public ModelAndView qualityCheck(String corpId){
		ModelAndView modelView;
		modelView = new ModelAndView();
		try {
			List<CheckItem> checkItems1 = checkItemService.findByType("1","1",corpId);
			List<CheckItem> checkItems2 = checkItemService.findByType("1","2",corpId);
			modelView.addObject("checkItems1", checkItems1);
			modelView.addObject("checkItems2", checkItems2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		modelView.addObject("cRQuality",CheckResultEnum.values());
		modelView.addObject("checkType",CheckTypeEnum.values());
		modelView.setViewName("complete/qualityCheck");
		return modelView;
	}
	/**
	 * 打开资料自检--没用
	 * @return
	 */
	@RequestMapping(value="/materialCheck")
	public ModelAndView materialCheck(){
		ModelAndView modelView;
		modelView = new ModelAndView();
		try {
			List<CheckItem> checkItems3 = checkItemService.findByType("2");
			modelView.addObject("checkItems3", checkItems3);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		modelView.addObject("cRMaterial",QualifiedEnum.values());
		modelView.addObject("checkType",CheckTypeEnum.values());
		modelView.setViewName("complete/materialCheck");
		return modelView;
	}
	
	/**
	 * 工程列表条件查询
	 * @author fuliwei
	 * @createTime 2017年7月31日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/queryProject")
	@ResponseBody
	public Map<String,Object> queryProject(HttpServletRequest request,ProjectQueryReq projectQueryReq){
		try {
			//projectQueryReq.setProjStatusId(ProjStatusEnum.TO_SELF_CHECK.getValue());//待自检
			projectQueryReq.setSortInfo(request);
			//String[] projStatus = {ProjStatusEnum.DURING_CONSTRUCTION.getValue(),ProjStatusEnum.TO_SELF_CHECK.getValue()};
			String[] projStatus ={};
			projectQueryReq.setProjStatusId(ProjStatusEnum.TO_SELF_CHECK.getValue());
			return projectService.queryProjectByTime(projectQueryReq);
			//return projectService.queryProjectAcceptance(projectQueryReq, projStatus);
		} catch (Exception e) {
			logger.error("工程列表查询失败！", e);
			return null;
		}
	}
	
	/**
	 * 弹出搜索屏
	 * @author fuliwei
	 * @createTime 2017年7月31日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/projectSearchPopPage")
	public ModelAndView projectSearchPopPage() {
		ModelAndView modelview = new ModelAndView();
		//modelview.addObject("projLtype", ProjLtypeEnum.values());
		modelview.setViewName("complete/searchPopPage");
		return modelview;
	}
	
	/**
	 * 查自检单详述
	 * @author fuliwei
	 * @createTime 2017年7月31日
	 * @param 
	 * @return
	 */
	@RequestMapping(value="/viewSelfInspectionList")
	@ResponseBody
	public SelfInspectionList viewSurveyResult(HttpServletRequest request,@RequestParam(required=true) String id){
		try {
			return selfInspectionListService.viewSelfInspectionList(id);
		} catch (Exception e) {
			logger.error("自检单详述查询失败！", e);
			return null;
		}
	}
	
	/**
	 * 查质量自检记录详述
	 * @author fuliwei
	 * @createTime 2017年7月31日
	 * @param 
	 * @return
	 */
	@RequestMapping(value="/selfInspectionRecordQuqlity")
	@ResponseBody
	public Map<String, String> viewSelfInspectionRecordQuqlity(HttpServletRequest request,@RequestParam(required=true) String id){
		return selfInspectionRecordService.viewSelfInspectionRecordQuqlity(id);
	}
	
	
	
	/**
	 * 查资料自检记录详述--没用
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/selfInspectionRecordMaterial")
	@ResponseBody
	public Map<String, String> viewSelfInspectionRecordMaterial(HttpServletRequest request,@RequestParam(required=true) String id){
		return selfInspectionRecordService.viewSelfInspectionRecordMaterial(id);
	}
	
	/**
	 * 保存自检单
	 * @author fuliwei
	 * @createTime 2017年7月31日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/saveSelfInspectionRecord")
	@ResponseBody
	public String saveSelfInspectionRecord(HttpServletRequest request,@RequestBody(required = true) SelfInspectionList selfInspectionList){
		try {
			//推送的时候判断质量自检是否为空
			if(selfInspectionList.getFlag().equals("1")){
				List<SelfInspectionRecord> selfInspectionRecords = selfInspectionList.getSelfInspectionRecords();
				if(selfInspectionRecords==null||selfInspectionRecords.size()==0){
					return Constants.OPERATE_RESULT_DATA_INCOMPLETE;
				}

			}
			selfInspectionListService.saveSelfInspectionList(selfInspectionList);
			handleNotice(selfInspectionList);//待办
			return Constants.OPERATE_RESULT_SUCCESS;
		} catch (Exception e) {
			request.getSession().setAttribute("singtureType","error");
			logger.error("自检记录保存失败！", e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}

	public  void handleNotice( SelfInspectionList selfInspectionList){
		if(selfInspectionList.getFlag().equals("1")){//生成竣工报告待办通知；
			Project project = projectService.findById(selfInspectionList.getProjId());
			if (project!=null) {
				String operaterId=","+project.getManagementQaeId()+",";
				String operater=project.getManagementQae();
				operateRecordService.nextDealtNotice(project, StepEnum.COMPLETED_REPORT.getValue(), StepEnum.COMPLETED_REPORT.getMessage(), operaterId, operater);
			}
		}
	}

}


