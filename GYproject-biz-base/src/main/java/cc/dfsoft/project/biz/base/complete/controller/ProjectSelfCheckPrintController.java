package cc.dfsoft.project.biz.base.complete.controller;

import cc.dfsoft.project.biz.base.complete.dto.PreinspectionReq;
import cc.dfsoft.project.biz.base.complete.entity.SelfInspectionList;
import cc.dfsoft.project.biz.base.complete.service.SelfInspectionListService;
import cc.dfsoft.project.biz.base.contract.enums.ContractIsPrintEnum;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
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
 * 自检表打印
 * @author liaoyq
 *
 */
@Controller
@RequestMapping(value="/projectSelfCheckPrint")
public class ProjectSelfCheckPrintController {
	
	/** 日志实例 */
	private static Logger logger=LoggerFactory.getLogger(ProjectSelfCheckPrintController.class);
	
	/**自检单服务接口*/
	@Resource
	SelfInspectionListService selfInspectionListService;
	
	/**
	 * 打开主页面
	 * @return
	 */
	@RequestMapping(value="main")
	public ModelAndView main(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("alreadyPrint", ContractIsPrintEnum.ALREADY_PRINT.getValue());//已打印
		modelAndView.addObject("haveNotPrint", ContractIsPrintEnum.HAVE_NOT_PRINT.getValue());//未打印
		modelAndView.addObject("imgUrl",Constants.DISK_PATH+Constants.SIGN_DISK_PATH); 	//img url
		modelAndView.addObject("loginPost", SessionUtil.getLoginInfo().getPost());
		modelAndView.addObject("dataAdmin", PostTypeEnum.DATAMANAGER.getValue());
		modelAndView.setViewName("complete/projectSelfCheckPrint");
		return modelAndView;
	}
	
	/**
	 * 自检单列表条件查询
	 * @author fuliwei
	 * @createTime 2017年8月4日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/queryProjectSelfCheckPrint")
	@ResponseBody
	public Map<String,Object> querySelInspection(HttpServletRequest request,PreinspectionReq req){
		try {
			req.setSortInfo(request);
			
			if(StringUtils.isBlank(req.getIsPrint())){
				req.setIsPrint(ContractIsPrintEnum.HAVE_NOT_PRINT.getValue());//默认设置未打印
			}
			
			Map<String,Object> map = selfInspectionListService.querySelInspection(req);
			return map;
		} catch (Exception e) {
			logger.error("自检单列表查询失败！", e);
			return null;
		}
	}
	
	@RequestMapping(value="viewSearchPopPage")
	public ModelAndView viewSearchPopPage(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("isPrint", ContractIsPrintEnum.values());//是否打印
		modelAndView.setViewName("complete/projectSelfCheckPrintSearchPop");
		return modelAndView;
	}



	/**
	 * @MethodDesc: 详细
	 * @Author zhangnx
	 * @Date 2019/6/11 12:58
	 */
	@RequestMapping(value="/viewDetail")
	@ResponseBody
	public SelfInspectionList viewDetail(@RequestParam(required=true) String id){
		try {
			SelfInspectionList selfInspectionList = selfInspectionListService.viewSelfInspectionList(id);
			return selfInspectionList;
		} catch (Exception e) {
			logger.error("自检单详述查询失败！", e);
			return null;
		}
	}

	@RequestMapping(value="/modifySelfCheck")
	@ResponseBody
	public boolean modifySelfCheck(@RequestBody(required = true) SelfInspectionList selfCheck){
		try {
			return selfInspectionListService.modifySelfCheck(selfCheck);
		}catch (Exception e){
			e.printStackTrace();
			return false;
		}
	}


	/**
	 * 自检单打印标记
	 * @author fuliwei
	 * @createTime 2017年8月4日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/signSelInspectionPrint")
	@ResponseBody
	public String signSelInspectionPrint(@RequestBody(required = true) String projId){
		try {
			selfInspectionListService.signSelInspectionPrint(projId);
			return Constants.OPERATE_RESULT_SUCCESS;
		} catch (Exception e) {
			logger.error("自检单标记打印失败！", e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
	
}
