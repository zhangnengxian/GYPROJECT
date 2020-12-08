package cc.dfsoft.project.biz.base.contract.controller;

import cc.dfsoft.project.biz.base.contract.dto.IntelligentSupplementReq;
import cc.dfsoft.project.biz.base.contract.service.IntelligentSupplementService;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.project.enums.AuditStatusEnum;
import cc.dfsoft.project.biz.base.project.enums.ProjLtypeEnum;
import cc.dfsoft.project.biz.base.project.service.ProjectService;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.common.constant.Constants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @ClassDesc: 智能表合同补充协议打印
 * @Author zhangnx
 * @Date 2019/7/26 14:36
 * Version:1.0
 */
@Controller
@RequestMapping(value="/intelligentSupplementPrint")
public class IntelligentSupplementPrintController {

	@Resource
	IntelligentSupplementService intelligentSupplementService;
	@Resource
	ProjectService projectService;


	/**
	 * 打开主页面
	 * @return
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(ModelAndView modelView){
		modelView.addObject("imgUrl", Constants.DISK_PATH+Constants.SIGN_DISK_PATH);//签字图片地址
		modelView.setViewName("contract/intelligentSupplementPrintMain");
		return modelView;
	}


	@RequestMapping(value = "/queryAlreadyAuditSupplement")
	@ResponseBody
	public Map<String, Object> queryAlreadyAuditSupplement(IntelligentSupplementReq req) {
		req.setIsStatus(AuditStatusEnum.PASS.getCode());//审核通过
		req.setIsValid("1");//有效的
		try {
			Map<String, Object> map = intelligentSupplementService.queryToAuditSupplement(req);
			return map;
		}catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}


	/**
	 * @MethodDesc: CPT报表查询
	 * @Author zhangnx
	 * @Date 2019/7/29 18:04
	 */
	@RequestMapping(value = "/queryCptUrl")
	@ResponseBody
	public String queryCptUrl(String projId,String menuId){
		LoginInfo loginInfo= SessionUtil.getLoginInfo();
		Project pro=projectService.queryProjectById(projId);

		String ctmKey=(pro!=null?pro.getCorpId():loginInfo.getCorpId())+"_"+(pro!=null?pro.getProjectType(): ProjLtypeEnum.PUBLIC.getValue())+"_"+menuId;//分公司+类型配置
		String cmKey=(pro!=null?pro.getCorpId():loginInfo.getCorpId())+"_"+menuId;//分公司默认配置
		String defaultCPT="intelligentSupplement.cpt";//全局默认

		return getCpt(ctmKey)!=null?getCpt(ctmKey).toString():getCpt(cmKey)!=null?getCpt(cmKey).toString():defaultCPT;
	}

	public Object getCpt(String key){
		return Constants.getSysConfigByKey(key);
	}



}
