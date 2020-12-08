package cc.dfsoft.project.biz.base.subpackage.controller;

import cc.dfsoft.project.biz.base.contract.enums.ContractIsPrintEnum;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.project.enums.ProjLtypeEnum;
import cc.dfsoft.project.biz.base.project.service.ProjectService;
import cc.dfsoft.project.biz.base.subpackage.dto.IntelligentMeterContractReq;
import cc.dfsoft.project.biz.base.subpackage.enums.IntelligentMeterConPushEnum;
import cc.dfsoft.project.biz.base.subpackage.service.IntelligentMeterContractService;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.common.constant.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 
 * 描述:智能表合同打印控制类
 * @author liaoyq
 * @createTime 2017年9月19日
 */
@Controller
@RequestMapping(value="/intelligentMeterConPrint")
public class IntelligentMeterConPrintController {
	/** 日志实例 */
	private static Logger logger=LoggerFactory.getLogger(IntelligentMeterConPrintController.class);
	@Resource
	IntelligentMeterContractService meterContractService;
	@Resource
	ProjectService projectService;
	
	/**
	 * 打开主页面
	 * @return
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelView=new ModelAndView();
		modelView.addObject("alreadyPrint", ContractIsPrintEnum.ALREADY_PRINT.getValue());//已打印
		modelView.addObject("haveNotPrint", ContractIsPrintEnum.HAVE_NOT_PRINT.getValue());//未打印
		modelView.setViewName("subcontract/intelligentMeterConPrint");
		return modelView;
	}

	/**
	 * 智能表合同列表查询
	 * @param request
	 * @param imcReq
	 * @return
	 */
	@RequestMapping(value = "/queryImcContract")
	@ResponseBody
	public Map<String,Object> queryContract(HttpServletRequest request,IntelligentMeterContractReq imcReq){
		try {
			imcReq.setFlag(IntelligentMeterConPushEnum.PUSHED.getValue());//已推送的智能表合同
			imcReq.setSortInfo(request);
			imcReq.setModifyStatus("all");
			Map<String,Object> map=meterContractService.queryContract(imcReq);
			return map;
		} catch (Exception e) {
			logger.error("智能表合同信息查询失败！", e);
			return null;
		}
	}
	/**
	 * 弹屏查询
	 * @return
	 */
	@RequestMapping(value = "/imcSearchPopPage")
	public ModelAndView imcSearchPopPage() {
		ModelAndView modelview = new ModelAndView();
		modelview.addObject("isPrint", ContractIsPrintEnum.values());//是否打印
		modelview.setViewName("subcontract/intelligentMeterConPrintSearchPopPage");
		return modelview;
	}

	/**
	 * 打开右侧详述页面
	 * @return
	 */
	@RequestMapping(value = "/viewPage")
	public ModelAndView viewPage() {
		ModelAndView modelview = new ModelAndView();
		modelview.setViewName("subcontract/subContractPrintRight");
		return modelview;
		
	}

	
	/**
	 * 合同打印标记
	 * @author cui
	 * @createTime 2017-2-14
	 * @param  projId
	 * @return String
	 */
	@RequestMapping(value = "/signImcPrint")
	@ResponseBody
	public String signImcPrint(@RequestBody(required = true) String projId){
		try {
			meterContractService.signImcPrint(projId);
			return Constants.OPERATE_RESULT_SUCCESS;
		} catch (Exception e) {
			logger.error("打印合同标记失败！", e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}




	/**
	 * @methodDesc: 查询cpt
	 * @author: zhangnx
	 * @date: 19:45 2018/9/26
	 */
	@RequestMapping(value = "/queryCptUrl")
	@ResponseBody
	public String queryCptUrl(String projId,String menuId){
		LoginInfo loginInfo= SessionUtil.getLoginInfo();
		Project project=projectService.queryProjectById(projId);

		//分公司ID+工程类型+菜单ID
		String key=(project!=null?project.getCorpId():loginInfo.getCorpId())+"_"+(project!=null?project.getProjectType():ProjLtypeEnum.CIVILIAN.getValue())+"_"+menuId;
		Object obj = Constants.getSysConfigByKey(key);

		String cptURL="intelligentMeterConPrint.cpt";//默认加载
		return obj!=null?obj.toString():cptURL;
	}

}


