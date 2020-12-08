package cc.dfsoft.project.biz.base.complete.controller;

import cc.dfsoft.project.biz.base.baseinfo.dao.BusinessPartnersDao;
import cc.dfsoft.project.biz.base.baseinfo.entity.BusinessPartners;
import cc.dfsoft.project.biz.base.constructmanage.enums.StepOutWorkflowEnum;
import cc.dfsoft.project.biz.base.withgas.dto.GasProjectReq;
import cc.dfsoft.project.biz.base.withgas.entity.GasProject;
import cc.dfsoft.project.biz.base.withgas.enums.GasProjectStatusEnum;
import cc.dfsoft.project.biz.base.withgas.service.GasProjectService;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.util.StringUtil;

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
 * 开通计划
 * @author cui
 *
 */
@Controller
@RequestMapping(value="/openPlan")
public class OpenPlanController {
	/** 日志实例 */
	private static Logger logger=LoggerFactory.getLogger(OpenPlanController.class);

	/**通气工程服务接口*/
	@Resource
	GasProjectService gasProjectService;

	
	@Resource 
	BusinessPartnersDao businessPartnersDao;
	/**
	 *  打开主页面
	 * @author cui
	 * @createTime 2017-10-19
	 * @param 
	 * @return
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelView=new ModelAndView();
		modelView.addObject("notThrough", GasProjectStatusEnum.GAS_AUDIT_NO_PASS.getValue());
		modelView.addObject("stepId", StepOutWorkflowEnum.GAS_PROJECT_AUDIT.getValue());
		modelView.setViewName("complete/openPlan");
		return modelView;
	}

	/**
	 * 打开右侧详述页面
	 * @author cui
	 * @createTime 2017-8-9
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/viewPage")
	public ModelAndView viewPage(String corpId,String projectType,String menuId) {
		ModelAndView modelView = new ModelAndView();
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		String viewUrl = "openProjectPlanRight";  //定义右侧页面
		String key = corpId+"_"+projectType+"_"+menuId;  //组装key值
		Object obj = Constants.getSysConfigByKey(key);  //根据key查找右侧 页面
		if(obj != null){
			viewUrl = obj.toString();  
		}
		if(obj==null){  //若为空则默认加载该公司下的第一个右侧jsp页面
			key = loginInfo.getCorpId()+"_"+11+"_"+"110715";
		    obj = Constants.getSysConfigByKey(key);
		    if(obj != null){
		    	 viewUrl = obj.toString();
		    }
		}
		if(obj == null){  //若登录人为第三方人员，默认加载所在第一个公司下的第一个右侧页面
			String detptId = loginInfo.getDeptId();  //得到当前登录人部门ID
			BusinessPartners businessPartners = businessPartnersDao.get(detptId);
			if(businessPartners!=null){
				String[] bpCorpId = businessPartners.getCorpId().split(",");
				key = bpCorpId[0]+"_"+"11"+"_"+"110715";
				  obj = Constants.getSysConfigByKey(key);
				    if(obj != null){
				    	 viewUrl = obj.toString();
				    }
			}
		}
		modelView.addObject("loginInfo",loginInfo);
		modelView.setViewName("complete/"+viewUrl);
		return modelView;
	}

	/**
	 * 通气工程查询弹窗
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/searchPopPage")
	public ModelAndView gasPlanSearchPopPage() {
		ModelAndView modelview = new ModelAndView();
		modelview.addObject("gasProjectStatusEnum",GasProjectStatusEnum.values());
		modelview.setViewName("withgas/gasProjectPopPage");
		return modelview;
	}

	/**
	 * 查询可以通气工程(已分布验收或联合验收)
	 * @author cui
	 * @createTime 2017-8-7
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/queryProject")
	@ResponseBody
	public Map<String,Object> queryProject(HttpServletRequest request, GasProjectReq gasProjectReq){
		try {
			if(StringUtil.isBlank(gasProjectReq.getGasProjStatus())){
				gasProjectReq.setGasProjStatus(GasProjectStatusEnum.OPEN_PROJECT.getValue());
			}
			gasProjectReq.setSortInfo(request);
			return gasProjectService.queryAllowGasProject(gasProjectReq);
		} catch (Exception e) {
			logger.error("待通气工程列表查询查询失败！", e);
			return null;
		}
	}

	/**
	 * 保存通气工程
	 * @author cui
	 * @createTime 2017-8-7
	 * @param  gasProject 通气工程
	 * @return String
	 */
	@RequestMapping(value = "/saveGasProject")
	@ResponseBody
	public String saveGasProject(@RequestBody(required = true) GasProject gasProject){
		try {
			return gasProjectService.saveGasProject(gasProject);
		} catch (Exception e) {
			logger.error("通气工程信息保存失败！", e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
}
