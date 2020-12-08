package cc.dfsoft.project.biz.base.complete.controller;

import cc.dfsoft.project.biz.base.baseinfo.service.DocTypeService;
import cc.dfsoft.project.biz.base.complete.entity.FilingData;
import cc.dfsoft.project.biz.base.complete.service.ConnectConfirmService;
import cc.dfsoft.project.biz.base.project.dto.ManageRecordQueryReq;
import cc.dfsoft.project.biz.base.project.dto.ProjectQueryReq;
import cc.dfsoft.project.biz.base.project.enums.MrResultEnum;
import cc.dfsoft.project.biz.base.project.enums.ProjStatusEnum;
import cc.dfsoft.project.biz.base.project.service.ManageRecordService;
import cc.dfsoft.project.biz.base.project.service.ProjectService;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
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
import java.util.Map;

/**
 * 资料归档
 * @author fuliwei
 *
 */

@Controller
@RequestMapping(value="/connectConfirm")
public class ConnectConfirmController {

	/** 日志实例 */
	private static Logger logger=LoggerFactory.getLogger(JointAcceptanceController.class);
	
	/**工程服务接口*/
	@Resource
	ProjectService projectService;
	
	/**管理记录服务接口*/
	@Resource
	ManageRecordService manageRecordService;
	
	/**资料交接服务接口*/
	@Resource
	ConnectConfirmService connectConfirmService;
	
	/**单据类型服务接口*/
	@Resource
	DocTypeService docTypeService;
	
	/**
	 * 打开主页面
	 * @return
	 */
	@RequestMapping(value="/mian")
	public ModelAndView main(){
		ModelAndView modelView=new ModelAndView();
		modelView.addObject("projNo", SessionUtils.getProjNo());
		modelView.setViewName("complete/connectConfirm");
		return modelView;
	}
	
	/**
	 * 弹出搜索屏
	 * @return
	 */
	@RequestMapping(value = "/projectSearchPopPage")
	public ModelAndView projectSearchPopPage() {
		ModelAndView modelview = new ModelAndView();
		modelview.setViewName("complete/searchDataPopPage");
		return modelview;
	}
	
	
	/**
	 * 打开右侧页面
	 * @return
	 */
	@RequestMapping(value = "/viewPage")
	public ModelAndView viewPlanningPage(String projId,String corpId,String menuId) {
//		Project proj = projectService.viewProject(projId);
//		String key = proj.getCorpId()+"_"+menuId;
//		String key = corpId+"_"+menuId;
//		Object obj = Constants.getSysConfigByKey(key);
//		String viewUrl = "";
//		if(obj !=null){
//			viewUrl = obj.toString();
//		}else{
//			viewUrl = "connectConfirmRight";
//		}
		ModelAndView modelview = new ModelAndView();
		modelview.addObject("auditTime",manageRecordService.getDatabaseDate().getTime());	//当前数据库时间
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		modelview.addObject("loginInfo",loginInfo);											//登录人信息
		modelview.addObject("mrResult",MrResultEnum.values());
//		modelview.setViewName("complete/"+viewUrl);
		modelview.setViewName("complete/connectConfirmRight");
		return modelview;
	}
	
	/**
	 * 工程列表条件查询
	 * @param projectQueryReq
	 * @author zhangmeng
	 * @createTime 2016-09-19
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/queryConnectConfirm")
	@ResponseBody
	public Map<String,Object> queryJointAcceptancer(ProjectQueryReq projectQueryReq){
		try {
			projectQueryReq.setProjStatusId(ProjStatusEnum.CONNECT_CONFIRM.getValue());		  //待资料归档
			//projectQueryReq.setStepId(StepEnum.CONNECT_CONFIRM.getValue());
			return projectService.queryProjectByTimeForJoint(projectQueryReq);
		} catch (Exception e) {
			logger.error("工程信息查询失败！", e);
			return null;
		}
	}
	
	/**
	 * 资料交接详述
	 * @return
	 */
	@RequestMapping(value = "/jointDetail")
	@ResponseBody
	public FilingData jointDetail(@RequestParam(required=true) String id){
		try {
			return connectConfirmService.jointDetail(id);
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
			//manageRecordQueryReq.setStepId(StepEnum.CONNECT_CONFIRM.getValue());	//资料交接确认
			return manageRecordService.queryManageRecord(manageRecordQueryReq);
		} catch (Exception e) {
			logger.error("管理记录列表查询失败！",e);
			return null;
		}
	}
	
	/**
	 * 保存归档资料
	 * @author fuliwei
	 * @createTime 2017年9月1日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/auditSave")
	@ResponseBody
	public String auditSave(@RequestBody(required = true) FilingData dataRrcord){
		
		try {
			return connectConfirmService.saveConnect(dataRrcord);
		} catch (Exception e) {
			logger.error("资料归档保存失败！",e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
		
	}
}
