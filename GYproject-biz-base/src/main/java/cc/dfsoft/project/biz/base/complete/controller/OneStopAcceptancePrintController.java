package cc.dfsoft.project.biz.base.complete.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cc.dfsoft.project.biz.base.complete.dto.JointAcceptanceReq;
import cc.dfsoft.project.biz.base.complete.enums.AcceptanceTypeEnum;
import cc.dfsoft.project.biz.base.complete.service.JointAcceptanceService;
import cc.dfsoft.project.biz.base.contract.enums.ContractIsPrintEnum;
import cc.dfsoft.project.biz.base.project.service.ProjectService;
import cc.dfsoft.uexpress.common.constant.Constants;

@Controller
@RequestMapping(value="/oneStopAcceptancePrint")
public class OneStopAcceptancePrintController {
	
	/** 日志实例 */
	private static Logger logger=LoggerFactory.getLogger(OneStopAcceptancePrintController.class);
	
	/**工程服务接口*/
	@Resource
	ProjectService projectService;
	
	/**联合验收服务接口*/
	@Resource
	JointAcceptanceService jointAcceptanceService;
	
	/**
	 * 打开主页面
	 * @return
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelView=new ModelAndView();
		modelView.addObject("alreadyPrint", ContractIsPrintEnum.ALREADY_PRINT.getValue());//已打印
		modelView.addObject("haveNotPrint", ContractIsPrintEnum.HAVE_NOT_PRINT.getValue());//未打印
		modelView.addObject("imgUrl",Constants.DISK_PATH+Constants.SIGN_DISK_PATH); 	//img url
		modelView.setViewName("complete/oneStopAcceptancePrint");
		return modelView;
	}
	
	/**
	 * 列表条件查询
	 * @param request
	 * @param jointAcceptanceReq
	 * @return
	 */
	@RequestMapping(value = "/queryJointAcceptance")
	@ResponseBody
	public Map<String,Object> queryJointAcceptancer(HttpServletRequest request,JointAcceptanceReq jointAcceptanceReq){
		try {
			jointAcceptanceReq.setSortInfo(request);
			jointAcceptanceReq.setAcceptanceType(AcceptanceTypeEnum.ONE_STOP_ACCEPTANCE.getValue());//默认加载一站式验收
			Map<String,Object> map = jointAcceptanceService.queryJointAcceptance(jointAcceptanceReq);
			return map;
		} catch (Exception e) {
			logger.error("工程信息查询失败！", e);
			return null;
		}
	}
	/**
	 * 弹屏查询
	 * @return
	 */
	@RequestMapping(value = "/acceptanceSearchPopPage")
	public ModelAndView projectSearchPopPage() {
		ModelAndView modelview = new ModelAndView();
		//modelview.addObject("projLtype", ProjLtypeEnum.values());
		modelview.addObject("isPrint", ContractIsPrintEnum.values());//是否打印
		modelview.setViewName("complete/acceptanceSearchPop");
		return modelview;
	}
	
	/**
	 * 验收单打印标记
	 * @author cui
	 * @createTime 2017-2-15
	 * @param  jaId
	 * @return String 
	 */
	@RequestMapping(value = "/signJointAcceptancePrint")
	@ResponseBody
	public String signJointAcceptancePrint(@RequestBody(required = true) String jaId){
		try {
			jointAcceptanceService.signJointAcceptancePrint(jaId);
			return Constants.OPERATE_RESULT_SUCCESS;
		} catch (Exception e) {
			logger.error("打印合同标记失败！", e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
}
