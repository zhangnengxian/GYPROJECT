package cc.dfsoft.project.biz.base.constructmanage.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cc.dfsoft.project.biz.base.budget.dto.ChangeManagementQueryReq;
import cc.dfsoft.project.biz.base.budget.service.ChangeManagementService;
import cc.dfsoft.project.biz.base.change.enums.ChangeStateEnum;
import cc.dfsoft.project.biz.base.constructmanage.enums.ChangeTypeEnum;
import cc.dfsoft.project.biz.base.contract.controller.ContractAuditController;
import cc.dfsoft.project.biz.base.project.enums.MrResultEnum;
import cc.dfsoft.uexpress.biz.sys.dept.enums.PostTypeEnum;
import cc.dfsoft.uexpress.common.constant.Constants;

/**
 * 变更审核
 * @author Yuanyx
 *
 */
@Controller
@RequestMapping(value="/designAlterationAudit")
public class DesignAlteraionAuditController {
	
	
	/** 日志实例 */
	private static Logger logger=LoggerFactory.getLogger(ContractAuditController.class);
	
	/**变更记录服务接口*/
	@Resource
	ChangeManagementService changeManagementService;
	
	/**
	 * 打开主页面
	 * @return
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelView=new ModelAndView();
		modelView.setViewName("constructmanage/designAlterationAudit");
		return modelView;
	}
	
	
	/**
	 * 变更审核列表条件查询
	 * @param request
	 * @param changeManagementQueryReq
	 * @return
	 */
	@RequestMapping(value = "/queryDesignAlteration")
	@ResponseBody
	public Map<String,Object> queryDesignAlteration(HttpServletRequest request,ChangeManagementQueryReq changeManagementQueryReq){
		try {
			changeManagementQueryReq.setSortInfo(request);
			Map<String, Object> map = changeManagementService.queryChangeManagementAudit(changeManagementQueryReq);
			return map;
		} catch (Exception e) {
			logger.error("变更信息查询失败！", e);
			return null;
		}
	}
	
	
	/**
	* 打开审核屏
	* @return ModelAndView
	*/
	@RequestMapping(value="/auditPage")
	public ModelAndView audit(HttpServletRequest request){
		ModelAndView modelView=new ModelAndView();
		String isAudit = request.getParameter("isAudit");
		String auditLevel = request.getParameter("auditLevel");
		String cmId = request.getParameter("cmId");
		modelView.addObject("isAudit",isAudit);				//当前审核是否通过
		modelView.addObject("auditLevel",auditLevel);       //当前点击的按钮的级别
		modelView.addObject("cmId",cmId);       			//变更记录Id
		modelView.addObject("cmState", ChangeStateEnum.NO_HANDLE.getValue());
		modelView.addObject("changeType", ChangeTypeEnum.values());//变更类型
		modelView.addObject("cuPm", PostTypeEnum.DEPUTY_DIRECTOR.getValue());//副处长
		modelView.addObject("suPrincipal", PostTypeEnum.SUJGJ.getValue());//现场监理师
		modelView.addObject("designer", PostTypeEnum.DESIGNER.getValue());//设计员
		modelView.addObject("auditer", PostTypeEnum.DIRECTOR.getValue());//处长
		modelView.addObject("approvaler", PostTypeEnum.CHIEF_ENGINEER.getValue());//总工
		modelView.addObject("imgUrl",Constants.DISK_PATH+Constants.SIGN_DISK_PATH); 	//img url
		modelView.addObject("drawUrl1",Constants.DISK_PATH+Constants.DIAGRAM_DISK_PATH); 	//img url
		modelView.setViewName("constructmanage/designAlterationAuditPage");
		return modelView;
	}
	
	/**
	 * 查询弹屏
	 * @param projId
	 * @return
	 */
	@RequestMapping(value="/searchPopPage")
	public ModelAndView touchPlanAuditSearchPage(String projId){
		ModelAndView modelView=new ModelAndView();
		modelView.addObject("projId", projId);
		modelView.setViewName("constructmanage/designAlterationAuditSearchPage");
		return modelView;
	}
	
}
