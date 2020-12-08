package cc.dfsoft.project.biz.base.complete.controller;

import cc.dfsoft.project.biz.base.complete.entity.Ignite;
import cc.dfsoft.project.biz.base.complete.service.IgniteService;
import cc.dfsoft.project.biz.base.contract.enums.ContractSuIsPrintEnum;
import cc.dfsoft.project.biz.base.project.enums.AccessorySourceEnum;
import cc.dfsoft.project.biz.base.project.service.AccessoryService;
import cc.dfsoft.project.biz.base.withgas.dto.GasProjectReq;
import cc.dfsoft.project.biz.base.withgas.service.GasProjectService;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.biz.sys.dept.enums.PostTypeEnum;
import cc.dfsoft.uexpress.common.constant.Constants;

import org.apache.commons.lang3.StringUtils;
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

import java.util.Map;

/**
 * 点火单签订
 * @author cui
 * @createTime 2017-11-16
 *
 */

@Controller
@RequestMapping(value="/ignite")
public class IgniteController {
	/** 日志实例 */
	private static Logger logger=LoggerFactory.getLogger(IgniteController.class);

	/**
	 * 通气工程接口服务
	 */
	@Resource
	GasProjectService gasProjectService;
	
	@Resource
	AccessoryService accessoryService;

	/**
	 * 点火单接口服务
	 */
	@Resource
	IgniteService igniteService;
	/**
	 * 打开主页面
	 * @author cui
	 * @createTime 2017-11-10
	 * @param 
	 * @return
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelView=new ModelAndView();
		modelView.setViewName("complete/ignite");
		return modelView;
	}
	/**
	 * 打开右屏页面
	 * @author cui
	 * @createTime 2017-11-16
	 * @param
	 * @return
	 */
	@RequestMapping(value="/viewPage")
	public ModelAndView viewPage(){
		ModelAndView modelView=new ModelAndView();
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		modelView.addObject("post", loginInfo.getPost());
		modelView.addObject("builderPost",PostTypeEnum.BUILDER.getValue());  	//现场代表
		modelView.addObject("groupLeacerPost",PostTypeEnum.GROUP_LEADER.getValue());  	//技术组组长
		modelView.setViewName("complete/igniteRight");
		return modelView;
	}
	
	/**
	 * 弹出查询页面
	 * @author fuliwei
	 * @createTime 2017年12月14日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/ignitePopSerach")
	public ModelAndView gasApplySearchPopPage() {
		ModelAndView modelview = new ModelAndView();
		modelview.setViewName("complete/ignitePopSerach");
		return modelview;
	}

	/**
	 * 工程列表条件查询
	 * @author cui
	 * @createTime 2017-8-10
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/queryGasProject")
	@ResponseBody
	public Map<String,Object> queryGasProject(HttpServletRequest request,GasProjectReq gasProjectReq){
		try {
			//gasProjectReq.setGasProjStatus(GasProjectStatusEnum.GAS_AUDIT_DONE.getValue());
			if(StringUtils.isBlank(gasProjectReq.getIsSignIgnite())){
				gasProjectReq.setIsSignIgnite(ContractSuIsPrintEnum.HAVE_NOT_PRINT.getValue());//未签订
			}
			return gasProjectService.queryGasProject(gasProjectReq);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("工程信息查询失败！", e);
			return null;
		}
	}

	/**
	 * 详述
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/viewIgnite")
	@ResponseBody
	public Ignite viewIgnite(HttpServletRequest request, @RequestParam(required=true) String id,String stepId){
		Ignite ig=igniteService.viewByGprojId(id);
		ig.setUploadFlag(accessoryService.isUploadFile(ig.getProjId(),stepId,id,AccessorySourceEnum.COLLECT_FILE.getValue())?"1":"0");
		return ig;
	}

	/**
	 * 点火单保存
	 * @author cui
	 * @createTime 2017-11-10
	 * @param ignite
	 * @return
	 */
	@RequestMapping(value = "/igniteSave")
	@ResponseBody
	public String igniteSave(HttpServletRequest request,@RequestBody(required = true) Ignite ignite){
		try {
			if(ignite.isPushFlag() && !accessoryService.isUploadFile(ignite.getProjId(), ignite.getStepId(),ignite.getGprojId(),AccessorySourceEnum.COLLECT_FILE.getValue())){
				return "no";
			}
			igniteService.igniteSave(ignite);
			return Constants.OPERATE_RESULT_SUCCESS;
		} catch (Exception e) {
			request.getSession().setAttribute("singtureType","error");
			logger.error("点火通知单保存失败！",e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
}
