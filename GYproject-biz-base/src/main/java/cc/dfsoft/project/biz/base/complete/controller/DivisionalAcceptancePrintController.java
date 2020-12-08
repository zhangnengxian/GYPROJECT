package cc.dfsoft.project.biz.base.complete.controller;

import cc.dfsoft.project.biz.base.complete.dto.DivisionalAcceptanceReq;
import cc.dfsoft.project.biz.base.complete.entity.DivisionalAcceptance;
import cc.dfsoft.project.biz.base.complete.service.DivisionalAcceptanceService;
import cc.dfsoft.project.biz.base.contract.enums.ContractIsPrintEnum;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.project.enums.ProjLtypeEnum;
import cc.dfsoft.project.biz.base.project.service.ProjectService;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.common.constant.Constants;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;


/**
 * 分部验收表打印
 * @author fuliwei
 *
 */
@Controller
@RequestMapping(value="/divisionalAcceptancePrint")
public class DivisionalAcceptancePrintController {
	
	/** 日志实例 */
	private static Logger logger=LoggerFactory.getLogger(DivisionalAcceptancePrintController.class);
	
	/**分部验收申请*/
	@Resource
	DivisionalAcceptanceService divisionalAcceptanceService;
	
	@Resource
	ProjectService  projectService;

	/**
	 * 打开主页面
	 * @author fuliwei
	 * @createTime 2017年6月21日
	 * @param 
	 * @return
	 */
	@RequestMapping(value="main")
	public ModelAndView main(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("imgUrl",Constants.DISK_PATH+Constants.SIGN_DISK_PATH); 	//img url
		modelAndView.setViewName("complete/divisionalAcceptancePrint");
		return modelAndView;
	}
	
	/**
	 * 弹屏查询
	 * @return
	 */
	@RequestMapping(value = "/acceptanceSearchPopPage")
	public ModelAndView projectSearchPopPage() {
		ModelAndView modelview = new ModelAndView();
		modelview.addObject("isPrint", ContractIsPrintEnum.values());//是否打印
		modelview.setViewName("complete/acceptancePrintSearchPop");
		return modelview;
	}
	
	/**
	 * 分部验收打印列表
	 * @author fuliwei
	 * @createTime 2017年8月14日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/queryDivisionalAcceptance")
	@ResponseBody
	public Map<String,Object> queryDivisionalAcceptance(HttpServletRequest request,DivisionalAcceptanceReq req){
		try {
			req.setSortInfo(request);
			if(StringUtils.isBlank(req.getIsPrint())){
				req.setIsPrint(ContractIsPrintEnum.HAVE_NOT_PRINT.getValue());
			}
			Map<String, Object> stringObjectMap = divisionalAcceptanceService.queryDivisionalAcceptance(req);
			List<DivisionalAcceptance> daList = (List<DivisionalAcceptance>) stringObjectMap.get("data");
			if (daList==null ||daList.size()<1) return stringObjectMap;

			for (int i = 0; i <daList.size() ; i++) {//移除未完成验收的记录
				if ("0".equals(daList.get(i).getAcceptanceState())){
					daList.remove(daList.get(i));
				}
			}
			return stringObjectMap;

		} catch (Exception e) {
			logger.error("分部验收打印列表查询失败！",e);
			return null;
		}
	}





	/**s
	 * 验收单打印标记
	 * @author fuliwei
	 * @createTime 2017年8月14日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/signDivisionalAcceptancePrint")
	@ResponseBody
	public String signDivisionalAcceptancePrint(@RequestBody(required = true) String daId){
		try {
			divisionalAcceptanceService.signDivisionalAcceptance(daId);
			return Constants.OPERATE_RESULT_SUCCESS;
		} catch (Exception e) {
			logger.error("打印合同标记失败！", e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
	/**
	 * 根据公司ID,工程类型，菜单ID查找cpt类型
	 * 参数不是必须的
	 * @author wanghuijun
	 * @createTime 2018年9月1日
	 * @param projId
	 * @param menuId
	 * @return
	 */
	@RequestMapping(value="/queryCptType")
	@ResponseBody
	public String queryCptType (String projId,String menuId){
		LoginInfo loginInfo = SessionUtil.getLoginInfo(); // 取得当前登录人信息
		Project project = projectService.queryProjectById(projId);

		//key规则：corpId__projType__menuId
		String ctmKey=(project!=null?project.getCorpId():loginInfo.getCorpId())+"_"+(project!=null?project.getProjectType(): ProjLtypeEnum.CIVILIAN.getValue())+"_"+menuId;
		//key规则：corpId__menuId
		String cmKey=(project!=null?project.getCorpId():loginInfo.getCorpId())+"_"+menuId;
		String defaultCpt="divisionalAcceptancePrint.cpt";
		return sysConfig(ctmKey)!=null? sysConfig(ctmKey).toString():sysConfig(cmKey)!=null?sysConfig(cmKey).toString():defaultCpt;
	}




	public Object sysConfig(String key){
		return Constants.getSysConfigByKey(key);
	}

}
