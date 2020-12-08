package cc.dfsoft.project.biz.base.constructmanage.controller;


import cc.dfsoft.project.biz.base.constructmanage.dto.ProjectContactsReq;
import cc.dfsoft.project.biz.base.constructmanage.entity.ProjectContacts;
import cc.dfsoft.project.biz.base.constructmanage.service.ConstructionWorkService;
import cc.dfsoft.project.biz.base.constructmanage.service.ProjectContactsService;
import cc.dfsoft.uexpress.common.constant.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate4.HibernateOptimisticLockingFailureException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 竣工报告
 * @author cui
 *
 */
@Controller
@RequestMapping(value="/projectContacts")
public class ProjectContactsController {
	
	/** 日志实例 */
	public static Logger logger = LoggerFactory.getLogger(ProjectContactsController.class);
	
   
    /**交底记录服务接口*/
    @Resource
    ConstructionWorkService constructionWorkService;
    
    /**竣工报告服务接口*/
    @Resource
	ProjectContactsService projectContactsService;
    
 
	/**
	 * 打开主页面
	 * @return
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(HttpServletRequest request){
		ModelAndView modelView=new ModelAndView();
		modelView.addObject("imgUrl", Constants.DISK_PATH+ Constants.SIGN_DISK_PATH);
//		modelView.addObject("DEPUTY_DIRECTOR", PostTypeEnum.DEPUTY_DIRECTOR.getValue()); //副处长
//		modelView.addObject("SUPERVISOR", PostTypeEnum.SUJGJ.getValue()); //监理工程师
		modelView.setViewName("constructmanage/projectContacts");
		return modelView;
	}

	/**
	 * 工程联系单列表查询
	 * @param request
	 * @param projectContactsReq
	 * @return
	 */
	@RequestMapping(value = "/queryProjectContacts")
	@ResponseBody
	public Map<String,Object> queryProjectContacts(HttpServletRequest request,ProjectContactsReq projectContactsReq){
		try {
			projectContactsReq.setSortInfo(request);
			return projectContactsService.queryProjectContacts(projectContactsReq);
		} catch (Exception e) {
			logger.error("竣工报告列表查询失败！", e);
			return null;
		}
	}
	
//	/**
//	 * 根据竣工报告id查询详述
//	 * @param id
//	 * @return
//	 */
//	@RequestMapping(value = "/detailById")
//	@ResponseBody
//	public CompleteReport detailById(HttpServletRequest request,@RequestParam(required=true) String id){
//		try {
//			CompleteReport cr = projectContactsService.findByCrId(id);
//			return cr;
//		} catch (Exception e) {
//			logger.error("根据竣工报告id查询详述失败！", e);
//			return null;
//		}
//	}

	/**
	 * 工程联系单保存
	 * @param projectContacts
	 * @return
	 */
	@RequestMapping(value = "/saveProjectContacts")
	@ResponseBody
	public String saveProjectContacts(@RequestBody(required=true) ProjectContacts projectContacts){
		try {
			return projectContactsService.projectContactsSave(projectContacts);
		}catch(HibernateOptimisticLockingFailureException e ){
			logger.error("版本冲突，需刷新页面！", e);
			return "already";
		} catch (Exception e) {
			logger.error("工程联系单保存失败!",e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
//	/**
//	 * 详述
//	 * @param id
//	 * @return
//	 */
//	@RequestMapping(value = "/constructionWorkDetail")
//	@ResponseBody
//	public CompleteReport constructionWorkDetail(@RequestParam(required=true) String id){
//		try {
//			return projectContactsService.findByProjId(id);
//		} catch (Exception e) {
//			e.printStackTrace();
//			return null;
//		}
//
//	}

//	/**
//	 * 竣工报告添加删除标记
//	 * @param
//	 * @return
//	 */
//	@RequestMapping("/deleteList")
//	@ResponseBody
//	public String deleteList(String crId){
//		try{
//			projectContactsService.deleteById(crId);
//			return Constants.OPERATE_RESULT_SUCCESS;
//		}catch(Exception e){
//			logger.error("竣工报告删除失败！",e);
//			return Constants.OPERATE_RESULT_FAILURE;
//		}
//	}
}
