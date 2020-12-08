package cc.dfsoft.project.biz.base.withgas.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.project.service.ProjectService;
import cc.dfsoft.project.biz.base.subpackage.enums.SubBudgetIsPrintEnum;
import cc.dfsoft.project.biz.base.withgas.dto.GasProjectReq;
import cc.dfsoft.project.biz.base.withgas.service.GasProjectService;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.util.StringUtil;


/**
 * 通气单打印
 * @author fulw
 *
 */
@Controller
@RequestMapping(value="/gasPrint")
public class GasPrintController {
	
	/** 日志实例 */
	private static Logger logger=LoggerFactory.getLogger(GasPrintController.class);
	

	/**通气工程表*/
	@Resource
	GasProjectService gasProjectService;
	
	/**工程信息*/
	@Resource
	ProjectService projService;
	
	/**
	 * 打开主页面
	 * @author fuliwei  
	 * @date 2018年9月27日  
	 * @version 1.0
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("alreadyPrint", SubBudgetIsPrintEnum.ALREADY_PRINT.getValue());//已打印
		modelAndView.addObject("haveNotPrint", SubBudgetIsPrintEnum.HAVE_NOT_PRINT.getValue());//未打印
		modelAndView.addObject("imgUrl",Constants.DISK_PATH+Constants.SIGN_DISK_PATH);
		modelAndView.setViewName("withgas/gasPrint");
		return modelAndView;
	}
	
	
	/**
	 * 弹出打印页面
	 * @author fuliwei  
	 * @date 2018年9月27日  
	 * @version 1.0
	 */
	@RequestMapping(value="gasPrintSearchPopPage")
	public ModelAndView viewBudgetPrintRightPage(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("isPrint", SubBudgetIsPrintEnum.values());//是否打印
		modelAndView.setViewName("withgas/gasDateRegisterPopPage");
		return modelAndView;
	}
	
	/**
	 * 查询通气单
	 * @author fuliwei  
	 * @date 2018年9月27日  
	 * @version 1.0
	 */
	@RequestMapping(value="queryGasProject")
	@ResponseBody
	public Map<String,Object> queryGasProject(HttpServletRequest request,GasProjectReq req){
		try {
			req.setSortInfo(request);
			if(StringUtil.isBlank(req.getIsprint())){
				req.setIsprint(SubBudgetIsPrintEnum.HAVE_NOT_PRINT.getValue());//默认未打印
			}
			Map<String,Object> map=gasProjectService.queryGasProject(req);
			return map;
		} catch (Exception e) {
			logger.error("通气单打印查询失败！", e);
			return null;
		}
	}
	
	/**
	 * 打印标记
	 * @author fuliwei  
	 * @date 2018年9月27日  
	 * @version 1.0
	 */
	@RequestMapping(value="signGasPrint")
	@ResponseBody
	public String signGasPrint(@RequestBody(required = true) String gprojId){
		try {
			return gasProjectService.signGasPrint(gprojId);
		} catch (Exception e) {
			logger.error("通气单标记失败！", e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
	
	/**
	 * 查询配置的通气表
	 * key值规则：出资方式_分公司ID_菜单ID_出资方式
	 * @author fuliwei  
	 * @date 2018年9月27日  
	 * @version 1.0
	 */
	@RequestMapping(value="viewGasPrintReport",method = RequestMethod.POST)
	@ResponseBody
	public String viewSubBudgetReport(String projId, String menuId){
		String cptUrl = "";
		try {
			//获取工程信息
			Project project = projService.queryProjectById(projId);
			if(project==null){
				return cptUrl;
			}
			//组装key值得到信息
			String key = project.getContributionMode() + "_" + project.getCorpId() + "_" + menuId;
			Object obj = Constants.getSysConfigByKey(key);
			if(obj == null){
				return cptUrl;
			}
			return obj.toString();
		} catch (Exception e) {
			logger.error("通气表打印版本查询失败！", e);
			e.printStackTrace();
			return cptUrl;
		}
		
	}
	
	
	
}
