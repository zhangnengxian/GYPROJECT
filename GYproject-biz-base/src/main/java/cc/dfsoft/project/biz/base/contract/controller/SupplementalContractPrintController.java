package cc.dfsoft.project.biz.base.contract.controller;

import cc.dfsoft.project.biz.base.contract.dto.SupplementalContractQueryReq;
import cc.dfsoft.project.biz.base.contract.enums.ContractIsPrintEnum;
import cc.dfsoft.project.biz.base.contract.enums.supplementalContractIsAuditEnum;
import cc.dfsoft.project.biz.base.contract.service.SupplementalContractService;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.project.enums.ProjLtypeEnum;
import cc.dfsoft.project.biz.base.project.service.ProjectService;
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
 * 补充合同打印
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value="/supplementalContractPrint")
public class SupplementalContractPrintController {
	
	/** 日志实例 */
	private static Logger logger=LoggerFactory.getLogger(ContractAuditController.class);
	
	/**工程服务接口*/
	@Resource
	ProjectService projectService;
	/**补充协议服务接口*/
	@Resource
	SupplementalContractService supplementalContractService;
	
	/**
	 * 打开主页面
	 * @return
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelView=new ModelAndView();
		modelView.addObject("alreadyPrint", ContractIsPrintEnum.ALREADY_PRINT.getValue());//已打印
		modelView.addObject("haveNotPrint", ContractIsPrintEnum.HAVE_NOT_PRINT.getValue());//未打印
		modelView.setViewName("contract/supplementalContractPrint");
		return modelView;
	}
	/**
	 * 补充协议列表查询
	 * @param request
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/querySupplementalContract")
	@ResponseBody
	public Map<String,Object> queryContract(HttpServletRequest request,SupplementalContractQueryReq supplementalContractQueryReq){
		try {
			supplementalContractQueryReq.setSortInfo(request);
			supplementalContractQueryReq.setIsAudit( supplementalContractIsAuditEnum.HAVE_PASS.getValue());//审核通过的
			Map<String,Object> map=supplementalContractService.querySupplementalContract(supplementalContractQueryReq);
			return map;
		} catch (Exception e) {
			logger.error("补充协议信息查询失败！", e);
			return null;
		}
	}
	/**
	 * 弹屏查询
	 * @return
	 */
	@RequestMapping(value = "/scSearchPopPage")
	public ModelAndView subContractSearchPopPage() {
		ModelAndView modelview = new ModelAndView();
		modelview.addObject("isPrint", ContractIsPrintEnum.values());//是否打印
		modelview.setViewName("contract/scSearchPopPage");
		return modelview;
	}
	/**
	 * 补充协议标记
	 * @param scId
	 * @return
	 */
	@RequestMapping(value = "/signSupplementalContract")
	@ResponseBody
	public String signSupplementalContract(@RequestBody(required = true) String scId){
		try {
			return supplementalContractService.signSupplementalContract(scId);
		} catch (Exception e) {
			logger.error("打印补充协议标记失败！", e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}

	/**
	 * @methodDesc: 查询t_sysCofig表中配置的CPT
	 * @author: zhangnx
	 * @date: 15:17 2018/11/7
	 */
	@RequestMapping(value = "/queryCptUrl")
	@ResponseBody
	public String queryCptUrl(String projId,String menuId){
		LoginInfo loginInfo= SessionUtil.getLoginInfo();
		Project project=projectService.queryProjectById(projId);

		String corpId=project!=null?project.getCorpId():loginInfo.getCorpId();
		String projType=project!=null?project.getProjectType():ProjLtypeEnum.CIVILIAN.getValue();
		String conb=project!=null?project.getContributionMode():"1";

		String ctcmKey=corpId+"_"+projType+"_"+conb+"_"+menuId;
		String ctmKey=corpId+"_"+projType+"_"+menuId;
		String cmKey=corpId+"_"+menuId;

		String cptURL1="supplementalContract.cpt";//民用
		String cptURL2="supplementalContract2.cpt";//其他
		String defaultCpt=(project!=null&&project.getProjectType().equals(11))?cptURL1:cptURL2;


		Object resultCpt1=sysConfig(ctcmKey)!=null?sysConfig(ctcmKey):sysConfig(ctmKey);
		Object resultCpt2=resultCpt1!=null?resultCpt1:sysConfig(cmKey);

		return resultCpt2!=null?resultCpt2.toString():defaultCpt;
	}

	public Object sysConfig(String key) {
		return Constants.getSysConfigByKey(key);
	}


}
