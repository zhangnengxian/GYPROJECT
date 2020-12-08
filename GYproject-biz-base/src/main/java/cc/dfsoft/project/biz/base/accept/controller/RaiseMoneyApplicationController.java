
package cc.dfsoft.project.biz.base.accept.controller;


import cc.dfsoft.project.biz.base.accept.entity.RaiseMoney;
import cc.dfsoft.project.biz.base.accept.service.RaiseMoneyAplicationService;
import cc.dfsoft.project.biz.base.project.dto.ManageRecordQueryReq;
import cc.dfsoft.project.biz.base.project.dto.ProjectQueryReq;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.project.enums.ProjLtypeEnum;
import cc.dfsoft.project.biz.base.project.enums.ProjStatusEnum;
import cc.dfsoft.project.biz.base.project.enums.StepEnum;
import cc.dfsoft.project.biz.base.project.service.AccessoryService;
import cc.dfsoft.project.biz.base.project.service.ManageRecordService;
import cc.dfsoft.project.biz.base.project.service.ProjectService;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassDesc: 提资申请Controller
 * @author: zhangnx
 * @date: 10:22 2018/9/14
 * @version: V1.0
 */
@Controller
@RequestMapping(value="/raiseMoneyApplicationController")
public class RaiseMoneyApplicationController {

	private static Logger logger = LoggerFactory.getLogger(RaiseMoneyApplicationController.class);

	@Resource
	ProjectService projectService;
	@Resource
	RaiseMoneyAplicationService raiseMoneyAplicationService;
	@Resource
	AccessoryService accessoryService;

	@Resource
	ManageRecordService manageRecordService;

	/**
	 * @methodDesc: 主页面
	 * @author: zhangnx
	 * @date: 10:23 2018/9/14
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelView=new ModelAndView();
		modelView.addObject("notThrough",Constants.MODULE_CODE_PROJECTAPPROVAL);
		modelView.addObject("stepId",StepEnum.RAISEMONEY_AUDIT.getValue());	
		modelView.setViewName("accept/raiseMoneyApplication");
		return modelView;
	}



	/**
	 * @methodDesc: 获取工程列表
	 * @author: zhangnx
	 * @date: 10:23 2018/9/14
	 */
	@RequestMapping(value = "/queryProject")
	@ResponseBody
	public Map<String,Object> queryProject(ProjectQueryReq projectQueryReq){
		//查询条件
		projectQueryReq.setProjectType(ProjLtypeEnum.PUBLIC.getValue());//公建户
		projectQueryReq.setProjStatusId(ProjStatusEnum.TO_RAISEMONEY_APPLY.getValue());//待提资申请：10031

		try {
			 return projectService.queryProjectByTime(projectQueryReq);
		} catch (Exception e) {
			logger.error("获取工程列表失败！", e);
			return null;
		}


	}


	/**
	 * @methodDesc: 工程详细查询
	 * @author: zhangnx
	 * @date: 10:29 2018/9/14
	 */
	@RequestMapping(value="/queryProjectDetail")
	@ResponseBody
	public Project queryProjectDetail(@RequestParam(required=true) String id){
		//根据工程ID查询工程信息
		try {
			projectService.viewProject(id);
		}catch (Exception e){
			e.printStackTrace();
		}
		Project pro=projectService.viewProject(id);
		//根据工程ID查询提资信息
		RaiseMoney raiseMoney = raiseMoneyAplicationService.queryRaiseMoneyByProjId(id);
		if (raiseMoney!=null) {
			pro.setUploadFlag(raiseMoney.getUploadFlag());//是否上传提资资料
			pro.setRemark(raiseMoney.getRemark());//提资备注
		}
		return pro;
	}


	/**
	 * @methodDesc: 保存提资申请
	 * @author: zhangnx
	 * @date: 10:29 2018/9/14
	 */
	@RequestMapping(value="/saveRaiseMoney")
	@ResponseBody
	public String saveRaiseMoney(@RequestBody RaiseMoney raiseMoney){
		raiseMoney.setApplyDate(new Date());//提资申请日期:默认当前时间
		try {
			raiseMoneyAplicationService.saveRaiseMoney(raiseMoney);
			return Constants.OPERATE_RESULT_SUCCESS;
		}catch (Exception e){
			logger.error("提资信息保存失败！", e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}

	/**
	 * @methodDesc: 推送
	 * @author: zhangnx
	 * @date: 10:30 2018/9/14
	 */
	@RequestMapping(value="/raiseMoneyPush")
	@ResponseBody
	public String raiseMoneyPush(@RequestBody RaiseMoney raiseMoney){
		try {
			/*if(raiseMoney.getUploadFlag().equals("1") && !accessoryService.isUploadFile(raiseMoney.getProjId(), raiseMoney.getStepId(),null)){
				return "no";
			}*/
			raiseMoneyAplicationService.updateProject(raiseMoney);
			return Constants.OPERATE_RESULT_SUCCESS;
		} catch (Exception e) {
			logger.error("提资申请推送失败！", e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}



}
	  
	
	
