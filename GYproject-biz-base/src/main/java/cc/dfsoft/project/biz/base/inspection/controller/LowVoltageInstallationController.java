package cc.dfsoft.project.biz.base.inspection.controller;

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

import cc.dfsoft.project.biz.base.inspection.dto.ProjectChecklistQueryReq;
import cc.dfsoft.project.biz.base.inspection.dto.ThreadingPipeReq;
import cc.dfsoft.project.biz.base.inspection.entity.ProjectChecklist;
import cc.dfsoft.project.biz.base.inspection.enums.BasicCheckItemTypeEnum;
import cc.dfsoft.project.biz.base.inspection.enums.GuaranteeCheckItemTypeEnum;
import cc.dfsoft.project.biz.base.inspection.enums.ProjectChecklistTypeEnum;
import cc.dfsoft.project.biz.base.inspection.service.ProjectChecklistService;
import cc.dfsoft.project.biz.base.inspection.service.ThreadingPipeService;
import cc.dfsoft.uexpress.biz.sys.dept.enums.PostTypeEnum;
import cc.dfsoft.uexpress.common.constant.Constants;

/**
 * 低压电器安装
 * @author fuliwei
 *
 */
@Controller
@RequestMapping(value="/lowVoltageInstallation")
public class LowVoltageInstallationController {
	
	/** 日志实例 */
	private static Logger logger = LoggerFactory.getLogger(LowVoltageInstallationController.class);
	
	/**工程报验单服务接口*/
	@Resource
	ProjectChecklistService projectChecklistService;
	
	/**低压电器安装服务接口*/
	@Resource
	ThreadingPipeService threadingPipeService;
	
	/**
	 * 低压电器安装主页面
	 * @author fuliwei
	 * @createTime 2017年2月7日
	 * @param 
	 * @return ModelAndView
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelView = new ModelAndView();
		modelView.addObject("pcDesId",ProjectChecklistTypeEnum.LOW_VOLTAGE_INSTALLATION.getValue());
		modelView.addObject("qualitativeCheckMember",PostTypeEnum.QUALITATIVE_CHECK_MEMBER.getValue());//质检员
		modelView.addObject("sujgj",PostTypeEnum.SUJGJ.getValue());//监理
		modelView.addObject("path",Constants.DISK_PATH+"sign/");
		modelView.setViewName("inspection/lowVoltageInstallation");
		return modelView;
	}
	
	/**
	 * 工程报验单条件查询
	 * @author fuliwei
	 * @createTime 2017-02-6
	 * @param listQueryReq 查询条件dto
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/queryProjectList")
	@ResponseBody
	public Map<String,Object> queryProjectList(HttpServletRequest request,ProjectChecklistQueryReq listQueryReq){
		try {
			listQueryReq.setSortInfo(request);
			listQueryReq.setPcDesId(ProjectChecklistTypeEnum.LOW_VOLTAGE_INSTALLATION.getValue());
			return projectChecklistService.queryPrProjectChecklist(listQueryReq);
		} catch (Exception e) {
			logger.error("报验单信息查询失败！", e);
			return null;
		}
	}
	
	/**
	 * 查询详述
	 * @author fuliwei
	 * @createTime 2017-02-07
	 * @param id 工程id
	 * @return ProjectCheckList
	 */
	@RequestMapping(value="/viewLowVoltageInstallation")
	@ResponseBody
	public ProjectChecklist viewLowVoltageInstallation(HttpServletRequest request,@RequestParam(required=true) String id){
		ProjectChecklist list=projectChecklistService.viewProjectCheckList(id);
		return list;
	}
	
	/**
	 * 低压电器安装保存
	 * @author fuliwei
	 * @createTime  2016-7-20
	 * @param checkList
	 * @return String
	 */
	@RequestMapping(value = "/saveLowVoltageInstallation")
	@ResponseBody
	public String saveLowVoltageInstallation(@RequestBody(required = true) ProjectChecklist checkList){
		try {
			return projectChecklistService.saveProCheckList(checkList,Constants.LOW_VOLTAGE_INSTALLATION);
		} catch (Exception e) {
			logger.error("低压电器安装报验区保存失败！", e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
	
	/**
	 * 低压电器安装-保证项目列表查询
	 * @author fuliwei
	 * @createTime 2017-2-7
	 * @param purgeQueryReq 查询条件dto
	 * @return Map<String, Object>
	 */
	@RequestMapping(value = "/queryGuaranteeItemsList")
	@ResponseBody
	public Map<String, Object> queryGuaranteeItemsList(HttpServletRequest request,ThreadingPipeReq req) {
		try {
			req.setSortInfo(request);
			req.setItemType(GuaranteeCheckItemTypeEnum.LOW_VOLTAGE_INSTALLATION_GUARANTEE.getValue());//低压电器安装保证项目
		    return threadingPipeService.queryGuaranteeItemsList(req);
		} catch (Exception e) {
			logger.error("低压电器安装-保证项目列表查询失败！", e);
			return null;
		}
	}
	
	/**
	 * 低压电器安装-基本项目列表查询
	 * @author fuliwei
	 * @createTime 2017-2-7
	 * @param purgeQueryReq 查询条件dto
	 * @return Map<String, Object>
	 */
	@RequestMapping(value = "/queryLowVoltageInstallationBasic")
	@ResponseBody
	public Map<String, Object> queryLowVoltageInstallationBasic(HttpServletRequest request,ThreadingPipeReq req) {
		try {
			req.setSortInfo(request);
			req.setItemType(BasicCheckItemTypeEnum.LOW_VOLTAGE_INSTALLATION_BASIC.getValue());//低压电器安装基本项目
		    return threadingPipeService.queryThreadingPipeBasic(req);
		} catch (Exception e) {
			logger.error("低压电器安装-基本项目列表查询失败！", e);
			return null;
		}
	}
	
	/**
	 * 保存记录页面数据
	 * @author fuliwei
	 * @createTime 2017年2月8日
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/saveLowVoltageInstallationRecord")
	@ResponseBody
	public String saveThreadingPipeRecord(@RequestBody ThreadingPipeReq req){
		try {
			threadingPipeService.saveThreadingPipeRecord(req);
			return Constants.OPERATE_RESULT_SUCCESS;
		} catch (Exception e) {
			logger.error("保存记录失败！", e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	};
}
