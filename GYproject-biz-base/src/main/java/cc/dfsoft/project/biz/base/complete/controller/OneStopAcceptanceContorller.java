package cc.dfsoft.project.biz.base.complete.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate4.HibernateOptimisticLockingFailureException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cc.dfsoft.project.biz.base.complete.dto.JointAcceptanceReq;
import cc.dfsoft.project.biz.base.complete.entity.JointAcceptance;
import cc.dfsoft.project.biz.base.complete.service.JointAcceptanceService;
import cc.dfsoft.project.biz.base.constructmanage.enums.DataTypeEnum;
import cc.dfsoft.project.biz.base.project.dto.ProjectQueryReq;
import cc.dfsoft.project.biz.base.project.enums.ProjLtypeEnum;
import cc.dfsoft.project.biz.base.project.enums.ProjStatusEnum;
import cc.dfsoft.project.biz.base.project.enums.StepEnum;
import cc.dfsoft.project.biz.base.project.service.ProjectService;
import cc.dfsoft.uexpress.common.constant.Constants;

/**
 * 一站式验收
 * @author fuliwei
 *
 */
@Controller
@RequestMapping(value="/oneStopAcceptance")
public class OneStopAcceptanceContorller {
	

	/** 日志实例 */
	private static Logger logger=LoggerFactory.getLogger(OneStopAcceptanceContorller.class);
	
	/**工程服务接口*/
	@Resource
	ProjectService projectService;
	
	/**联合验收服务接口*/
	@Resource
	JointAcceptanceService jointAcceptanceService;
	
	/**
	 * 打开主页面
	 * @author fuliwei
	 * @createTime 2017年9月5日
	 * @param 
	 * @return
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelView=new ModelAndView();
		modelView.setViewName("complete/oneStopAcceptance");
		return modelView;
	}
	
	/**
	 * 打开右侧页面
	 * @author fuliwei
	 * @createTime 2017年9月5日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/viewPage")
	public ModelAndView viewPlanningPage() {
		ModelAndView modelview = new ModelAndView();
		modelview.setViewName("complete/oneStopAcceptanceRight");
		return modelview;
	}
	
	/**
	 * 工程列表条件查询
	 * @param projectQueryReq
	 * @author cui
	 * @createTime 2016-08-16 
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/queryJointAcceptancer")
	@ResponseBody
	public Map<String,Object> queryJointAcceptancer(ProjectQueryReq projectQueryReq){
		try {
			projectQueryReq.setProjStatusId(ProjStatusEnum.ONE_STOP_ACCEPTANCE.getValue());				  //待联合验收工程列表
			projectQueryReq.setStepId(StepEnum.ONE_STOP_ACCEPTANCE.getValue());
			return projectService.queryProjectByTime(projectQueryReq);
		} catch (Exception e) {
			logger.error("工程信息查询失败！", e);
			return null;
		}
	}

	/**
	 * 联合验收列表条件查询
	 * @param jointAcceptanceReq
	 * @author cui
	 * @createTime 2017-08-11
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/queryJointList")
	@ResponseBody
	public Map<String,Object> queryJointList(JointAcceptanceReq jointAcceptanceReq){
		try {
			return jointAcceptanceService.queryJointAcceptance(jointAcceptanceReq);
		} catch (Exception e) {
			logger.error("联合验收信息查询失败！", e);
			return null;
		}
	}

	/**
	 * 弹出搜索
	 * @author cui
	 * @createTime 2016-8-16
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/projectSearchPopPage")
	public ModelAndView projectSearchPopPage() {
		ModelAndView modelview = new ModelAndView();
		modelview.addObject("projLtype", ProjLtypeEnum.values());
		modelview.setViewName("complete/jointSearchPop");
		return modelview;
	}
	
	/**
	 * 联合验收单详述
	 * @return
	 */
	@RequestMapping(value = "/jointDetail")
	@ResponseBody
	public JointAcceptance jointDetail(@RequestParam(required=true) String id){
		try {
			return jointAcceptanceService.jointDetailByType(id,DataTypeEnum.CRAFT_WORK.getValue());//默认为工艺
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 保存一站式验收
	 * @author fuliwei
	 * @createTime 2017年9月5日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/saveJoint")
	@ResponseBody
	public String saveJoint(HttpServletRequest request, @RequestBody(required = true) JointAcceptance jointAcceptance){
		try {
			jointAcceptanceService.saveOneStopAcceptance(jointAcceptance);
			return Constants.OPERATE_RESULT_SUCCESS;
		}catch(HibernateOptimisticLockingFailureException e ){
			request.getSession().setAttribute("singtureType","error");
			logger.error("版本冲突，需刷新页面！", e);
			return "already";
		}catch(Exception e){
			request.getSession().setAttribute("singtureType","error");
			logger.error("保存一站式验收失败！",e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
	
	/**
	 * 推送一站式验收
	 * @author fuliwei
	 * @createTime 2017年9月5日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/entOneStopAcceptance")
	@ResponseBody
	public String entJoint(@RequestBody(required = true) JointAcceptance jointAcceptance){
		try {
			jointAcceptanceService.entOneStopAcceptance(jointAcceptance);
			return Constants.OPERATE_RESULT_SUCCESS;
		} catch (Exception e) {
			logger.error("一站式验收保存失败！", e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
	/**
	 * 详述
	 * @param id
	 * @param dataType
	 * @return
	 */
	@RequestMapping(value = "/jointDetailByType")
	@ResponseBody
	public JointAcceptance jointDetailByType(@RequestParam(required=true) String id,String dataType){
		try {
			return jointAcceptanceService.jointDetailByType(id,dataType);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
}
