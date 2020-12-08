package cc.dfsoft.project.biz.base.messagesync.controller;

import cc.dfsoft.project.biz.base.maintain.dto.WebServiceSetReq;
import cc.dfsoft.project.biz.base.messagesync.pojo.ResultMsg;
import cc.dfsoft.project.biz.base.messagesync.pojo.ResultMsg;
import cc.dfsoft.project.biz.base.plan.entity.ConstructionPlan;
import cc.dfsoft.project.biz.base.plan.service.ConstructionPlanService;
import cc.dfsoft.project.biz.base.messagesync.service.SynchronizedService;
import cc.dfsoft.project.biz.ifs.WebServiceTypeEnum;
import cc.dfsoft.project.biz.ifs.log.service.WebserviceSetService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import javax.annotation.Resource;
import java.util.Map;


/**
 *@Description: 接口管理
 *@author: zhangnx
 *@Date: 2019/11/27 19:45
 *@Version:1.0
 * */
@Controller
@RequestMapping(value = "/synchronized")
public class SynchronizedController {


	@Resource
	SynchronizedService synchronizedService;
	@Resource
	WebserviceSetService webserviceSetService;



	/**
	 * @MethodDesc: 主页
	 * @Author zhangnx
	 * @Date 2019/4/15 17:23
	 */
	@RequestMapping(value = "/main")
	public ModelAndView main(ModelAndView modelView) {
		modelView.setViewName("maintain/synchronizedMain");
		return modelView;
	}



	/**
	* @Description: 接口列表查询
	* @author zhangnx
	* @param  webServiceSetReq 条件参数
	* @date 2019/11/27 21:25
	**/
	@RequestMapping(value = "/querywebserviceSetMap")
	@ResponseBody
	public Map<String, Object> querywebserviceSetMap(WebServiceSetReq webServiceSetReq) {
		return webserviceSetService.querywebserviceSetMap(webServiceSetReq);
	}




	/**
	* @Description:  条件查询弹出框
	* @author zhangnx
	* @param
	* @date 2019/11/27 21:25
	**/
	@RequestMapping(value = "/webPopPage")
	public ModelAndView webPopPage(ModelAndView model) {
		model.setViewName("maintain/webPopPage");
		return model;
	}


	/**
	* @Description: 接口状态切换
	* @author zhangnx
	* @param
	* @date 2019/11/28 11:12
	**/
	@RequestMapping(value = "/switchStatus")
	@ResponseBody
	public String switchStatus(String wsId) {
		return webserviceSetService.switchStatus(wsId);
	}




	/**
	* @Description: 施工任务单信息同步
	* @author zhangnx
	* @param projId 工程ID
	* @date 2019/11/28 11:13
	**/
	@RequestMapping(value = "/callConstructionTask")
	@ResponseBody
	public String callConstructionTask(String projId,String interfaceNo) {
		try {
			//施工任务单新增/修改
			ResultMsg resultMsg = synchronizedService.callSynchronizedConstructionTask(projId, interfaceNo);
			if (resultMsg!=null) {
				return resultMsg.getMsg();
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		return "同步失败.....!";
	}

	/**
	* @Description: 同步交底信息（鸿巨）
	* @author zhangnx
	* @param projId 工程ID
	* @date 2019/11/28 15:08
	**/
	@RequestMapping(value = "/callConstructionWork")
	@ResponseBody
	public String callConstructionWork(String projId,String interfaceNo) {
		try {
			ResultMsg resultMsg = synchronizedService.callSynchronizedConstructionWork(projId, null, interfaceNo);
			if (resultMsg!=null){
				return resultMsg.getMsg();
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		return "同步失败.....!";
	}




	/**
	* @Description: 同步开工报告信息（鸿巨）
	* @author zhangnx
	* @param projId 工程ID
	* @date 2019/11/28 15:11
	**/
	@RequestMapping(value = "/callWorkReport")
	@ResponseBody
	public String callWorkReport(String projId,String interfaceNo) {
		try {
			ResultMsg resultMsg = synchronizedService.callSynchronizedWorkReport(projId,interfaceNo);
			if (resultMsg!=null){
				return resultMsg.getMsg();
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		return "同步失败.....!";
	}



	/**
	 * @Description: 同步预结算信息（鸿巨）
	 * @author zhangnx
	 * @param projId 工程ID
	 * @date 2019/11/28 15:11
	 **/
	@RequestMapping(value = "/callPreSettlement")
	@ResponseBody
	public String callPreSettlement(String projId,String interfaceNo) {
		try {
			ResultMsg resultMsg = synchronizedService.callSynchronizedPreSettlement(projId,interfaceNo);
			if (resultMsg!=null){
				return resultMsg.getMsg();
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		return "同步失败.....!";
	}


	/**
	* @Description: 同步施工合同信息（鸿巨）
	* @author zhangnx
	* @param projId 工程ID
	* @date 2019/11/28 15:13
	**/
	@RequestMapping(value = "/callSubContract")
	@ResponseBody
	public String callSubContract(String projId,String interfaceNo) {
		try {
			ResultMsg resultMsg = synchronizedService.callSynchronizedSubContract(projId,interfaceNo);
			if (resultMsg!=null){
				return resultMsg.getMsg();
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		return "同步失败.....!";
	}


	/**
	* @Description: 同步自检信息（鸿巨）
	* @author zhangnx
	* @param projId 工程ID
	* @date 2019/11/28 15:14
	**/
	@RequestMapping(value = "/callSelfCheck")
	@ResponseBody
	public String callSelfCheck(String projId,String interfaceNo) {
		try {
			ResultMsg resultMsg = synchronizedService.callSynchronizedSelfCheck(projId,interfaceNo);
			if (resultMsg!=null){
				return resultMsg.getMsg();
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		return "同步失败.....!";
	}


	/**
	* @Description: 同步预验收信息（鸿巨）
	* @author zhangnx
	* @param  projId 工程ID
	* @date 2019/11/28 15:15
	**/
	@RequestMapping(value = "/callPreinspection")
	@ResponseBody
	public String callPreinspection(String projId,String interfaceNo) {
		try {
			ResultMsg resultMsg = synchronizedService.callSynchronizedPreinspection(projId,interfaceNo);
			if (resultMsg!=null){
				return resultMsg.getMsg();
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		return "同步失败.....!";
	}


	/**
	* @Description: 同步联合验收信息（鸿巨）
	* @author zhangnx
	* @param projId 工程ID
	* @date 2019/11/28 15:16
	**/
	@RequestMapping(value = "/callJointAcceptance")
	@ResponseBody
	public String callJointAcceptance(String projId,String interfaceNo) {
		try {
			ResultMsg resultMsg = synchronizedService.callSynchronizedJointAcceptance(projId,interfaceNo);
			if (resultMsg!=null){
				return resultMsg.getMsg();
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		return "同步失败.....!";
	}


}
