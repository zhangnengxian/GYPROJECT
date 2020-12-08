package cc.dfsoft.project.biz.base.plan.controller;

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

import cc.dfsoft.project.biz.base.plan.service.ConstructionPlanService;
import cc.dfsoft.project.biz.base.project.dto.ProjectQueryReq;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.project.enums.AccessorySourceEnum;
import cc.dfsoft.project.biz.base.project.enums.ProjLtypeEnum;
import cc.dfsoft.project.biz.base.project.enums.ProjStatusEnum;
import cc.dfsoft.project.biz.base.project.enums.StepEnum;
import cc.dfsoft.project.biz.base.project.service.AccessoryService;
import cc.dfsoft.project.biz.base.project.service.ProjectService;
import cc.dfsoft.project.biz.base.subpackage.entity.SubBudget;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.constant.DataFilerSetUpDto;
/**
 * 报审手续
 * @author fulw
 *
 */
@Controller
@RequestMapping(value="/constructionProc")
public class ConstructionProcController {
	
	/** 日志实例 */
	private static Logger logger=LoggerFactory.getLogger(ConstructionProcController.class);
	
	/**工程服务接口*/
	@Resource
	ProjectService projectService;
	
	/**计划服务接口*/
	@Resource
	ConstructionPlanService constructionPlanService ;
	
	@Resource
	AccessoryService accessoryService;
	
	/**
	 * 打开主页面
	 * @author fuliwei  
	 * @date 2018年11月14日  
	 * @version 1.0
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelView=new ModelAndView();
		modelView.addObject("stepId", StepEnum.CONSTRUCTION_PROC.getValue());
		modelView.setViewName("plan/constructionProc");
		return modelView;
	}
	
	/**
	 * 列表查询
	 * @author fuliwei  
	 * @date 2018年11月8日  
	 * @version 1.0
	 */
	@RequestMapping(value = "/queryProject")
	@ResponseBody
	public Map<String,Object> queryProject(HttpServletRequest request,ProjectQueryReq projectQueryReq){
		try {
			projectQueryReq.setSortInfo(request);
			projectQueryReq.setProjStatusId(ProjStatusEnum.TO_CONSTRUCTION_PROC.getValue());
			projectQueryReq.setStepId(StepEnum.CONSTRUCTION_PROC.getValue());
			return projectService.queryProjectByTime(projectQueryReq);
		} catch (Exception e) {
			logger.error("工程信息查询失败！", e);
			return null;
		}
	}
	
	/**
	 * 打开右侧页面
	 * @author fuliwei  
	 * @date 2018年11月14日  
	 * @version 1.0
	 */
	@RequestMapping(value = "/viewPage")
	public ModelAndView viewPage(String menuId) {
		ModelAndView modelview = new ModelAndView();
		//获取配置，桐梓公司不用验证附件一定要上传
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		String key = loginInfo.getCorpId()+"_"+menuId;
		Object obj = Constants.getSysConfigByKey(key);
		if(obj!=null){
			modelview.addObject("isUploadFile",obj);
		}
		modelview.setViewName("plan/constructionProcRight");
		return modelview;
		
	}
	
	/**
	 * 打开查询弹框
	 * @author fuliwei  
	 * @date 2018年11月8日  
	 * @version 1.0
	 */
	@RequestMapping(value = "/projectSearchPopPage")
	public ModelAndView projectSearchPopPage() {
		ModelAndView modelview = new ModelAndView();
		modelview.addObject("projLtype", ProjLtypeEnum.values());
		//提示修改
		modelview.setViewName("design/projectSearchPopPage");
		return modelview;
	}
	
	/**
	 * 报审手续登记保存
	 * @author fuliwei  
	 * @date 2018年11月14日  
	 * @version 1.0
	 */
	@RequestMapping(value = "/updateState")
	@ResponseBody
	public String updateState(@RequestBody(required = true) SubBudget subBudget){ 
		try {
			//获取配置，桐梓公司不用验证附件一定要上传
			LoginInfo loginInfo = SessionUtil.getLoginInfo();
			String key = loginInfo.getCorpId()+"_"+subBudget.getMenuId();
			Object obj = Constants.getSysConfigByKey(key);
			//obj有配置的话，可以不用验证附件必须上传
			if(obj == null && "1".equals(subBudget.getIsConstructionProc()) && !accessoryService.isUploadFile(subBudget.getProjId(), subBudget.getStepId(),null,AccessorySourceEnum.COLLECT_FILE.getValue())){
				return "no";
			}
			 constructionPlanService.saveConProc(subBudget);
			 return Constants.OPERATE_RESULT_SUCCESS;
		} catch (Exception e) {
			logger.error("报审手续保存数据失败！");
			return Constants.OPERATE_RESULT_FAILURE;
		}
		
	}
	
}
