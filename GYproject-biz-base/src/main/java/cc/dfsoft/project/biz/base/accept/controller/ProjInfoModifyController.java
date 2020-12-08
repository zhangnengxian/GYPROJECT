package cc.dfsoft.project.biz.base.accept.controller;

import java.util.List;
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

import cc.dfsoft.project.biz.base.accept.dto.ProjInfoModifyReq;
import cc.dfsoft.project.biz.base.accept.entity.ProjInfoModify;
import cc.dfsoft.project.biz.base.accept.entity.ProjectApplication;
import cc.dfsoft.project.biz.base.accept.enums.ProjInfoModifyEnum;
import cc.dfsoft.project.biz.base.accept.service.ProjInfoModifyService;
import cc.dfsoft.project.biz.base.accept.service.ProjectApplicationService;
import cc.dfsoft.project.biz.base.baseinfo.enums.BackReasonEnum;
import cc.dfsoft.project.biz.base.project.dto.ProjectQueryReq;
import cc.dfsoft.project.biz.base.project.dto.ScaleDetailQueryReq;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.project.entity.ProjectType;
import cc.dfsoft.project.biz.base.project.enums.AreaEnum;
import cc.dfsoft.project.biz.base.project.service.ManageRecordService;
import cc.dfsoft.project.biz.base.project.service.ProjectService;
import cc.dfsoft.project.biz.base.project.service.ProjectTypeService;
import cc.dfsoft.project.biz.base.project.service.ScaleDetailService;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.common.constant.Constants;

/**
 * 信息更正
 * @author fulw
 * @createTime 2017-01-03
 *
 */
@Controller
@RequestMapping(value="/projInfoModify")
public class ProjInfoModifyController {
	
	/** 日志实例 */
	private static Logger logger = LoggerFactory.getLogger(ProjInfoModifyController.class);
	
	/**工程类型服务接口*/
	@Resource
	ProjectTypeService  projectTypeService;
	
	/** 工程规模明细服务接口 */
	@Resource
	ScaleDetailService scaleDetailService;
	
	/** 工程服务接口 */
	@Resource
	ProjectService projectService;
	
	/** 立项申请单信息服务接口*/
	@Resource
	ProjectApplicationService projectApplicationService;
	
	@Resource
	 ProjInfoModifyService  projInfoModifyService;
	
	@Resource
	ManageRecordService manageRecordService;
	
	/**
	 * 打开主页面
	 * @return
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelView=new ModelAndView();
		modelView.setViewName("accept/projInfoModify");
		return modelView;
	}
	
	/**
	 * 打开右侧详述页面
	 * @return
	 */
	@RequestMapping(value = "/viewPage")
	public ModelAndView viewPage() {
		ModelAndView modelview = new ModelAndView();
		List<ProjectType> list=projectTypeService.queryAllList();
		modelview.addObject("projLtype", list);
		modelview.addObject("area", AreaEnum.values());
		modelview.addObject("backReason", BackReasonEnum.values());//退单原因
		modelview.setViewName("accept/projInfoModifyRight");
		return modelview;
		
	}
	
	/**
	 * 要更正的工程列表
	 * @param projectQueryReq
	 * @return
	 */
	@RequestMapping(value = "/queryProject")
	@ResponseBody
	public Map<String,Object> queryProject(HttpServletRequest request,ProjectQueryReq projectQueryReq){
		try {
			projectQueryReq.setSortInfo(request);
			/*projectQueryReq.setStepId(StepEnum.PROJECT_CONFIRM.getValue());*/
			projectQueryReq.setIsModify(ProjInfoModifyEnum.NEED_MODIFY.getValue());
			return projectService.queryModifyProject(projectQueryReq);
		} catch (Exception e) {
			logger.error("工程信息查询失败！", e);
			return null;
		}
	}
	
	/**
	 * 工程规模明细
	 * @return
	 */
	@RequestMapping(value = "/queryScaleDetail")
	@ResponseBody
	public Map<String,Object> queryScaleDetail(HttpServletRequest request,ScaleDetailQueryReq scaleDetailQueryReq){
		try {
			scaleDetailQueryReq.setSortInfo(request);
			return scaleDetailService.queryScaleDetail(scaleDetailQueryReq);
		} catch (Exception e) {
			logger.error("工程规模明细信息查询失败！", e);
			return null;
		}
	}
	
	/**
	 * 查询详述
	 * @author fuliwei
	 * @createTime 2017-1-3
	 * @param id 工程id
	 * @return Project
	 */
	@RequestMapping(value="/viewProjectInfoModify")
	@ResponseBody
	public Project viewProjectConfirm(HttpServletRequest request,@RequestParam(required=true) String id){
		try {
			Project pro=projectService.viewProject(id);
			//通过id查立项申请信息
			ProjectApplication application=projectApplicationService.queryById(id);
			if(application!=null){
				pro.setPaNo(application.getPaNo());
			}
			return pro;
		} catch (Exception e) {
			logger.error("工程详述信息查询失败！", e);
		}
		return null;
	}
	
	
	/**
	 * 更正信息保存
	 * @param project
	 * @return
	 */
	@RequestMapping(value = "/totalSave")
	@ResponseBody
	public String totalSave(@RequestBody(required = true) Project project){
		try {
			//修改状态
			project.setIsModify(ProjInfoModifyEnum.HAVE_NOT_MODIFY.getValue());
			return projectService.acceptTotalSave(project, true);
		} catch (Exception e) {
			logger.error("信息更正保存失败！", e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
	
	/**
	 * 弹出更正屏
	 * @author
	 * @createTime 
	 * @param
	 * @return
	 */
	@RequestMapping(value="/popModifyPage")
	public ModelAndView popScore(){
		ModelAndView modelView=new ModelAndView();
		modelView.addObject("proposeDate",manageRecordService.getDatabaseDate().getTime());	//当前数据库时间
		modelView.addObject("loginInfo", SessionUtil.getLoginInfo());
		modelView.setViewName("accept/attachPopModify");
		return modelView;
	}
	
	/**
	 * 更正保存
	 * @author fulw
	 * @createTime 2017-01-3
	 * @param request
	 * @param cust
	 * @return
	 */
	@RequestMapping(value = "/saveModify")
	@ResponseBody
	public String saveCustomer(@RequestBody ProjInfoModify info) {
		try {
			projInfoModifyService.saveModify(info);
			return Constants.OPERATE_RESULT_SUCCESS;
		} catch (Exception e) {
			logger.error("更新保存失败！", e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
	
	/**
	 * 查询详述
	 * @author fuliwei
	 * @createTime 2017-01-3
	 * @param id 工程id
	 * @return ProjInfoModify
	 */
	@RequestMapping(value="/viewProjModify")
	@ResponseBody
	public ProjInfoModify viewProjModify(@RequestBody(required = true) String projId){
		try {
			ProjInfoModify pro=projInfoModifyService.queryById(projId);
			return pro;
		} catch (Exception e) {
			logger.error("更正信息查询失败！", e);
		}
		return null;
	}
	

	/**
	 * 列表查询
	 * @author fuliwei
	 * @createTime 2017-01-3
	 * @param ProjInfoModifyReq
	 * @return
	 */
	@RequestMapping(value = "/queryProModify")
	@ResponseBody
	public Map<String,Object> queryProModify(HttpServletRequest request,ProjInfoModifyReq req){
		try {
			req.setSortInfo(request);
			return projInfoModifyService.queryProModify(req);
		} catch (Exception e) {
			logger.error("修改信息查询失败！", e);
			return null;
		}
	}
	
	
}
