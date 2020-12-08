package cc.dfsoft.project.biz.base.project.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import cc.dfsoft.project.biz.base.baseinfo.enums.AssistProgressTypeEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cc.dfsoft.project.biz.base.baseinfo.enums.WorkFlowTypeEnum;
import cc.dfsoft.project.biz.base.project.dto.OperateWorkFlowReq;
import cc.dfsoft.project.biz.base.project.entity.OperateWorkFlow;
import cc.dfsoft.project.biz.base.project.enums.StepEnum;
import cc.dfsoft.project.biz.base.project.service.ContributionModeService;
import cc.dfsoft.project.biz.base.project.service.OperateWorkFlowService;
import cc.dfsoft.project.biz.base.project.service.ProjectTypeService;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.biz.sys.dept.enums.DeptTypeEnum;
import cc.dfsoft.uexpress.biz.sys.dept.service.DepartmentService;
import cc.dfsoft.uexpress.common.constant.Constants;
/**
 * 操作流程设置
 * @author fulw
 *
 */
@Controller
@RequestMapping(value="/operateWorkFlow")
public class OperateWorkFlowController {
	
	
	/** 日志实例 */
	private static Logger logger=LoggerFactory.getLogger(OperateWorkFlowController.class);
	
	/**部门服务接口*/
	@Resource
	DepartmentService departmentService;
	
	/**工程类型*/
	@Resource
	ProjectTypeService projectTypeService;
	
	@Resource
	ContributionModeService contributionModeService;
	
	/**操作流*/
	@Resource
	OperateWorkFlowService  operateWorkFlowService;
	
	/**
	 * 打开主页面
	 * @author fuliwei  
	 * @date 2018年9月7日  
	 * @version 1.0
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelView=new ModelAndView();
		modelView.setViewName("baseinfo/operateWorkFlow/operateWorkFlow");
		return modelView;
	}
	
	
	/**
	 * 打开右侧
	 * @author fuliwei  
	 * @date 2018年9月7日  
	 * @version 1.0
	 */
	@RequestMapping(value = "/viewPage")
	public ModelAndView viewDocTypePage() {
		ModelAndView modelView = new ModelAndView();
		modelView.addObject("corp", departmentService.findByType(DeptTypeEnum.SUBCOMPANY.getValue()));//分公司
		modelView.addObject("stepId",StepEnum.values());					//步骤枚举
		modelView.addObject("opType",WorkFlowTypeEnum.values());			//流程类型
		modelView.addObject("assistTypeId", AssistProgressTypeEnum.values());	//辅助流程类型
		modelView.addObject("projType",projectTypeService.queryAllList());	//工程类型
		modelView.addObject("contributionMode",contributionModeService.queryAllList());//出资方式
		modelView.setViewName("baseinfo/operateWorkFlow/operateWorkFlowRight");
		return modelView;		
	}
	
	/**
	 * 弹出审核页面
	 * @author fuliwei  
	 * @date 2018年9月7日  
	 * @version 1.0
	 */
	@RequestMapping(value = "/operateWorkFlowSearchPopPage")
	public ModelAndView projectSearchPopPage() {
		ModelAndView modelview = new ModelAndView();
		modelview.addObject("corp",departmentService.findByType(DeptTypeEnum.SUBCOMPANY.getValue()));//分公司
		modelview.addObject("stepId",StepEnum.values());											//步骤枚举
		modelview.addObject("projType",projectTypeService.queryAllList());							//工程类型
		modelview.addObject("contributionMode",contributionModeService.queryAllList());				//出资方式
		modelview.setViewName("baseinfo/operateWorkFlow/operateWorkFlowSearchPopPage");
		return modelview;
	}
	
	
	/**
	 * 查询列表
	 * @author fuliwei  
	 * @date 2018年9月7日  
	 * @version 1.0
	 */
	@RequestMapping(value = "/queryList")
	@ResponseBody
	public Map<String,Object> queryList(HttpServletRequest request, OperateWorkFlowReq req){
		try {
			req.setSortInfo(request);
			String a = request.getParameter("projectType");
			LoginInfo loginInfo=SessionUtil.getLoginInfo();
			req.setCorpId(loginInfo.getCorpId());
			Map<String,Object> map = operateWorkFlowService.queryList(req);
			return map;
		} catch (Exception e) {
			logger.error("列表查询失败！", e);
			return null;
		}
	}
	
	
	/**
	 * 保存操作流
	 * @author fuliwei  
	 * @date 2018年9月7日  
	 * @version 1.0
	 */
	@RequestMapping(value = "/saveOperateWorkFlow")
	@ResponseBody
	public String saveDocType(@RequestBody OperateWorkFlow owf){
		try {
			return	operateWorkFlowService.saveOperateWork(owf);
		} catch (Exception e) {
			logger.error("操作流保存失败！",e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
	
	
	/**
	 * 删除操作流
	 * @author fuliwei  
	 * @date 2018年9月7日  
	 * @version 1.0
	 */
	@ResponseBody
	@RequestMapping(value = "/deleteByOwfId")
	public String deleteAuditLevel(String id){
		try{
			operateWorkFlowService.deleteByOwfId(id);
			return Constants.OPERATE_RESULT_SUCCESS;
		}catch(Exception e){
			logger.error("操作流删除失败！",e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
	
	/**
	 * 查询
	 * @author fuliwei  
	 * @date 2018年10月26日  
	 * @version 1.0
	 */
	@ResponseBody
	@RequestMapping(value = "/queryOpStaff")
	public Map<String,Object> queryOpStaff(HttpServletRequest request,OperateWorkFlowReq req){
		try {
			req.setSortInfo(request);
			LoginInfo loginInfo=SessionUtil.getLoginInfo();
			req.setCorpId(loginInfo.getCorpId());
			Map<String,Object> map = operateWorkFlowService.queryOpStaff(req);
			return map;
		} catch (Exception e) {
			logger.error("列表查询失败！", e);
			return null;
		}
	}
	
	
}
