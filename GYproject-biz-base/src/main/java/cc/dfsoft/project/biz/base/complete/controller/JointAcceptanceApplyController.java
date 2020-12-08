package cc.dfsoft.project.biz.base.complete.controller;

import java.text.ParseException;
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

import cc.dfsoft.project.biz.base.complete.entity.JointAcceptance;
import cc.dfsoft.project.biz.base.complete.service.JointAcceptanceApplyService;
import cc.dfsoft.project.biz.base.project.dto.ProjectQueryReq;
import cc.dfsoft.project.biz.base.project.enums.ProjStatusEnum;
import cc.dfsoft.project.biz.base.project.enums.StepEnum;
import cc.dfsoft.project.biz.base.project.service.ProjectService;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.util.StringUtil;

/**
 * 
 * 描述:联合验收申请控制层
 * 
 * @author liaoyq
 * @createTime 2018年9月9日
 */
@Controller
@RequestMapping(value = "/jointAcceptanceApply")
public class JointAcceptanceApplyController {

	/** 日志实例 */
	private static Logger logger = LoggerFactory
			.getLogger(JointAcceptanceApplyController.class);

	/** 分期申请服务接口 */
	@Resource
	JointAcceptanceApplyService jointAcceptanceApplyService;

	@Resource
	ProjectService projService;

	/**
	 * 打开主页面
	 * 
	 * @author fuliwei
	 * @createTime 2017年6月27日
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/main")
	public ModelAndView main() {
		ModelAndView modelView = new ModelAndView();
		modelView.addObject("notThrough", Constants.MODULE_CODE_COMPLETE);
		modelView.addObject("stepId",StepEnum.UNION_PRE_INSPECTION_AUDIT.getValue());
		modelView.setViewName("complete/jointAcceptanceApply");
		return modelView;
	}

	/**
	 * 打开右侧详述页面
	 * 
	 * @author fuliwei
	 * @createTime 2017年6月27日
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/viewPage")
	public ModelAndView viewPage(String projId,String projectType,String corpId,String menuId) {
		ModelAndView modelview = new ModelAndView();
		LoginInfo info = SessionUtil.getLoginInfo();
		//规则corpId+menuId
		if(StringUtil.isBlank(corpId)){
			corpId = info.getCorpId();
		}
		String key = corpId+"_"+menuId;
		Object obj = Constants.getSysConfigByKey(key);
		String viewUrl = "";
		if(obj !=null){
			viewUrl = obj.toString();
		}else{
			viewUrl = "jointAcceptanceApplyRight";
		}
		modelview.setViewName("complete/"+viewUrl);
		return modelview;
	}

	/**
	 * 工程列表
	 * 
	 * @author fuliwei
	 * @createTime 2017年8月7日
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/queryProject")
	@ResponseBody
	public Map<String, Object> queryProject(HttpServletRequest request,
			ProjectQueryReq req) {
		try {
			req.setSortInfo(request);
			req.setProjStatusId(ProjStatusEnum.TO_UNION_PRE_INSPECTION_APPLY
					.getValue());// 待联合验收申请
			Map<String, Object> map = projService.queryProject(req);
			return map;
		} catch (Exception e) {
			logger.error("工程列表查询失败！", e);
			return null;
		}
	}

	/**
	 * 查询验收申请详述
	 * @param projId
	 * @param jaaId
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(value = "/viewJointAcceptanceApply")
	@ResponseBody
	public JointAcceptance viewJointAcceptanceApply(
			@RequestParam(required = true) String id)
			throws ParseException {
		return jointAcceptanceApplyService.findById(id);
	}

	/**
	 * 保存联合验收申请
	 * 
	 * @author fuliwei
	 * @createTime 2017年8月9日
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/saveJointAcceptanceApply")
	@ResponseBody
	public String saveJointAcceptanceApply(HttpServletRequest request,
			@RequestBody(required = true) JointAcceptance jointAcceptance) {
		try {
			jointAcceptanceApplyService
					.saveJointAcceptanceApply(jointAcceptance);
			return Constants.OPERATE_RESULT_SUCCESS;
		} catch (Exception e) {
			request.getSession().setAttribute("singtureType","error");
			logger.error("保存联合验收申请失败！", e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
}
