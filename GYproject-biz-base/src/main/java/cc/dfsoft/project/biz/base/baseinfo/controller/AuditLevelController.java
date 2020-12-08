package cc.dfsoft.project.biz.base.baseinfo.controller;

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

import cc.dfsoft.project.biz.base.baseinfo.dto.DocTypeQueryReq;
import cc.dfsoft.project.biz.base.baseinfo.entity.DocType;
import cc.dfsoft.project.biz.base.baseinfo.service.DocTypeService;
import cc.dfsoft.project.biz.base.baseinfo.service.ProjectAuditStepService;
import cc.dfsoft.project.biz.base.project.service.ContributionModeService;
import cc.dfsoft.project.biz.base.project.service.ProjectTypeService;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.biz.sys.dept.enums.DeptTypeEnum;
import cc.dfsoft.uexpress.biz.sys.dept.service.DepartmentService;
import cc.dfsoft.uexpress.common.constant.Constants;

/**
 * 审核级别设置
 * @author pengtt
 * @createTime 2016-08-22
 *
 */
@Controller
@RequestMapping(value="/auditLevel")
public class AuditLevelController {
	
	/** 日志实例 */
	private static Logger logger=LoggerFactory.getLogger(AuditLevelController.class);
	
	/**单据类型服务接口*/
	@Resource
	DocTypeService docTypeService;

	/**部门服务接口*/
	@Resource
	DepartmentService departmentService;
	
	/**审核设置*/
	@Resource
	ProjectAuditStepService projectAuditStepService;
	
	/**工程类型*/
	@Resource
	ProjectTypeService projectTypeService;
	
	@Resource
	ContributionModeService contributionModeService;
	
	/**
	 * 打开主页面
	 * @author pengtt
	 *  
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelView=new ModelAndView();
		modelView.setViewName("baseinfo/auditLevel/auditLevel");
		return modelView;
	}
	
	/**
	 * 查询审核列表
	 * @param request
	 * @param docTypeQueryReq
	 * @return
	 */
	@RequestMapping(value = "/queryDocType")
	@ResponseBody
	public Map<String,Object> queryDocType(HttpServletRequest request, DocTypeQueryReq docTypeQueryReq){
		try {
			docTypeQueryReq.setSortInfo(request);
			LoginInfo loginInfo=SessionUtil.getLoginInfo();
			docTypeQueryReq.setCorpId(loginInfo.getCorpId());
			Map<String,Object> map = docTypeService.queryDocType(docTypeQueryReq);
			return map;
		} catch (Exception e) {
			logger.error("单据信息查询失败！", e);
			return null;
		}
	}
	
	/**
	 * @author pengtt
	 * 打开右侧页面
	 * @return
	 */
	@RequestMapping(value = "/viewAuditLevelPage")
	public ModelAndView viewDocTypePage() {
		ModelAndView modelView = new ModelAndView();
		modelView.addObject("corp", departmentService.findByType(DeptTypeEnum.SUBCOMPANY.getValue()));//分公司
		modelView.addObject("stepId",projectAuditStepService.queryList());//审核枚举
		modelView.addObject("projType",projectTypeService.queryAllList());//工程类型
		modelView.addObject("contributionMode",contributionModeService.queryAllList());//出资方式
		modelView.setViewName("baseinfo/auditLevel/auditLevelRight");
		return modelView;		
	}
	
	
	/**
	 * 弹出搜索屏
	 * @author zhangmeng
	 * @createTime 2016-7-15
	 * @param  
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/auditLevelSearchPopPage")
	public ModelAndView projectSearchPopPage() {
		ModelAndView modelview = new ModelAndView();
		modelview.addObject("corp",departmentService.findByType(DeptTypeEnum.SUBCOMPANY.getValue()));//分公司
		modelview.addObject("stepId",projectAuditStepService.queryList());							//审核枚举
		modelview.addObject("projType",projectTypeService.queryAllList());							//工程类型
		modelview.addObject("contributionMode",contributionModeService.queryAllList());				//出资方式
		modelview.setViewName("baseinfo/auditLevel/auditLevelSearchPopPage");
		return modelview;
	}
	
	
	/**
	 * 保存
	 * @param docType
	 * @return
	 */
	@RequestMapping(value = "/saveDocType")
	@ResponseBody
	public String saveDocType(@RequestBody DocType docType){
		try {
			docTypeService.saveDocType(docType);
			return Constants.OPERATE_RESULT_SUCCESS;
		} catch (Exception e) {
			logger.error("审核级别设置保存失败！",e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
	
	
	/**
	 * 审核级别删除
	 * @author fuliwei
	 * @createTime 2018年7月16日
	 * @param 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/deleteAuditLevel")
	public String deleteAuditLevel(String id){
		try{
			docTypeService.deleteById(id);
			return Constants.OPERATE_RESULT_SUCCESS;
		}catch(Exception e){
			logger.error("审核级别删除失败！",e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
}
	
	
	
	
	
	