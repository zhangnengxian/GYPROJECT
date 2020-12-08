package cc.dfsoft.project.biz.base.design.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cc.dfsoft.project.biz.base.budget.dto.ChangeManagementQueryReq;
import cc.dfsoft.project.biz.base.budget.service.ChangeManagementService;
import cc.dfsoft.project.biz.base.constructmanage.entity.ChangeManagement;
import cc.dfsoft.project.biz.base.design.enums.DesignChangeStateEnum;
import cc.dfsoft.project.biz.base.project.dto.ProjectQueryReq;
import cc.dfsoft.project.biz.base.project.enums.ProjStatusEnum;
import cc.dfsoft.project.biz.base.project.service.ManageRecordService;
import cc.dfsoft.project.biz.base.project.service.ProjectService;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.common.constant.Constants;

/**
 * 设计调整
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value="/designChange")
public class DesignChangeController {
	
	/** 日志实例 */
	private static Logger logger=LoggerFactory.getLogger(SurveyResultRegisterController.class);
	
	/**工程服务接口*/
	@Resource
	ProjectService projectService;
	
	/**变更记录*/
	@Resource
	ChangeManagementService changeManagementService;
	
	/**审核记录*/
	@Resource
	ManageRecordService manageRecordService;
	
	/**
	 * 打开主页面
	 * @return
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelView=new ModelAndView();
		modelView.addObject("loginInfo",SessionUtil.getLoginInfo());			//放入登录人信息
		modelView.addObject("sysDate",manageRecordService.getDatabaseDate());   //系统时间
		modelView.setViewName("design/designChange");
		return modelView;
	}
	
	/**
	 * 弹出搜索屏
	 * @author zhangmeng
	 * @createTime 2016-7-04
	 * @param  
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/projectSearchChangePage")
	public ModelAndView projectSearchPopPage() {
		ModelAndView modelview = new ModelAndView();
		modelview.setViewName("design/projectSearchChangePage");
		return modelview;
	}
	
	/**
	 * 工程列表条件查询--已作废
	 * @author zhangmeng
	 * @createTime
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/queryProject")
	@ResponseBody
	public Map<String,Object> queryProject(HttpServletRequest request,ProjectQueryReq projectQueryReq){
		try {
			projectQueryReq.setSortInfo(request);
			List<String> projStuList = new ArrayList();
			List<ProjStatusEnum> enums = ProjStatusEnum.getThanValue(ProjStatusEnum.TO_DRAWING.getValue(),ProjStatusEnum.TO_CONSTRUCTION.getValue());
			for(ProjStatusEnum projStatusEnum :enums){
				projStuList.add(projStatusEnum.getValue());
			}
			Map<String, Object> map;
			map = projectService.queryProjectByParam(projectQueryReq, projStuList);
			return map;
		} catch (Exception e) {
			logger.error("工程信息查询失败！", e);
			return null;
		}
	}
	
	/**
	 * 变更列表查询-默认只查确认中的 其他状态的可在查询条件中查出
	 * @author fuliwei
	 * @createTime 2017年11月11日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/queryCheckList")
	@ResponseBody
	public Map<String,Object> queryDesignAlteration(HttpServletRequest request,ChangeManagementQueryReq changeManagementQueryReq){
		try {
			changeManagementQueryReq.setSortInfo(request);
			if(StringUtils.isBlank(changeManagementQueryReq.getDesignChangeType())){
				changeManagementQueryReq.setDesignChangeType(DesignChangeStateEnum.WAIT_CONFIRM.getValue());  //默认查询确认中的设计变更
			}
			Map<String, Object> map;
			map = changeManagementService.queryChangeManagement(changeManagementQueryReq);
			if(map==null){
				return new HashMap<String, Object>();
			}
			return map;
		} catch (Exception e) {
			logger.error("变更信息查询失败！", e);
			return null;
		}
	}
	
	/**
	 * 查询详述
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/viewChangeManagement")
	@ResponseBody
	public ChangeManagement viewInspectionRecordResult(HttpServletRequest request,@RequestParam(required=true) String id){
		ChangeManagement changeManagement=changeManagementService.viewChangeManagement(id);
		return changeManagement;
	}
	
	/**
	 * 变更记录保存
	 * @author fuliwei
	 * @createTime 2017年11月11日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/saveChangeAlteration")
	@ResponseBody
	public String saveDesignAlteration(@RequestBody ChangeManagement changeManagement){
		try {
			changeManagementService.saveChangeManagement(changeManagement,DesignChangeStateEnum.WAIT_CONFIRM.getValue());
			return Constants.OPERATE_RESULT_SUCCESS;
		} catch (Exception e) {
			logger.error("变更信息保存失败！", e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
	
	/**
	 * 推送到预算组-变更签证调整
	 * @author fuliwei
	 * @createTime 2017年11月11日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/updateChangeState")
	@ResponseBody
	public String updateChangeState(@RequestBody(required = true) String cmId) throws ParseException {
		try {
			changeManagementService.updateChangeState(cmId,DesignChangeStateEnum.WAIT_BUDGET_CHANGE.getValue());
			return Constants.OPERATE_RESULT_SUCCESS;
		} catch (Exception e) {
			logger.error("推送设计变更失败！", e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
}


