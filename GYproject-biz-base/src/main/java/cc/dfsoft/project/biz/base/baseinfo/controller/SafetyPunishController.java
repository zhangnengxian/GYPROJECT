package cc.dfsoft.project.biz.base.baseinfo.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cc.dfsoft.project.biz.base.baseinfo.dto.SafetyPunishReq;
import cc.dfsoft.project.biz.base.baseinfo.entity.SafetyPunish;
import cc.dfsoft.project.biz.base.baseinfo.service.SafetyPunishService;
import cc.dfsoft.project.biz.base.project.entity.ProjectType;
import cc.dfsoft.project.biz.base.project.service.ProjectTypeService;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.biz.sys.dept.enums.DeptTypeEnum;
import cc.dfsoft.uexpress.biz.sys.dept.service.DepartmentService;
import cc.dfsoft.uexpress.common.constant.Constants;
/**
 * 安全质量细则
 * @author cui
 *
 */
@Controller
@RequestMapping(value="/safetyPunish")
public class SafetyPunishController {
	
	/** 日志实例 */
	private static Logger logger=LoggerFactory.getLogger(SafetyPunishController.class);
	
	/**企业合作伙伴服务接口*/
	@Resource
	SafetyPunishService safetyPunishService;
	

	/**部门服务接口*/
	@Resource
	DepartmentService departmentService;
	
	/**
	 * 工程类型
	 */
	@Resource
	ProjectTypeService projectTypeService;
	
	/**
	 * 打开主页面
	 * @author cui
	 *  
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelView=new ModelAndView();
		modelView.setViewName("baseinfo/safetyPunish/safetyPunish");
		return modelView;
	}
	
	
	/**
	 * @author cui
	 * 打开右侧页面
	 * @return
	 */
	@RequestMapping(value = "/viewSafetyPunishPage")
	public ModelAndView viewSafetyPunishPage() {
		ModelAndView modelview = new ModelAndView();
		modelview.addObject("corp", departmentService.findByType(DeptTypeEnum.SUBCOMPANY.getValue()));//分公司
		modelview.setViewName("baseinfo/safetyPunish/safetyPunishRight");
		return modelview;		
	}
	
	/**
	 * 弹出查询屏
	 * @author fuliwei  
	 * @date 2018年8月8日  
	 * @version 1.0
	 */
	@RequestMapping(value = "/safetyPunishSearchPopPage")
	public ModelAndView projectSearchPopPage() {
		ModelAndView modelview = new ModelAndView();
		List<ProjectType> list=projectTypeService.queryAllList();
		modelview.addObject("projTypeList", list);
		modelview.addObject("corp", departmentService.findByType(DeptTypeEnum.SUBCOMPANY.getValue()));//分公司
		modelview.setViewName("baseinfo/safetyPunish/safetyPunishSearchPopPage");
		return modelview;
	}
	
	/**
	 * 左屏条件查询
	 * @author cui
	 * @createTime
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/querySafetyPunish")
	@ResponseBody
	public Map<String,Object> querySafetyPunish(HttpServletRequest request,SafetyPunishReq safetyPunishReq){
		try {
			safetyPunishReq.setSortInfo(request);
			
			if(StringUtils.isBlank(safetyPunishReq.getCorpId())){
				LoginInfo loginInfo=SessionUtil.getLoginInfo();
				safetyPunishReq.setCorpId(loginInfo.getCorpId());
			}
			
			
			Map<String,Object> map=safetyPunishService.querySafetyPunish(safetyPunishReq);
			return map;
		} catch (Exception e) {
			logger.error("信息查询失败！", e);
			return null;
		}
	}
	/**
	 * 详述
	 * @author cui
	 * @createTime 2016-7-18
	 * @param id 
	 * @return Contract
	 * 
	 */
	@RequestMapping(value="/viewSafetyPunish")
	@ResponseBody
	public SafetyPunish viewSafetyPunish(@RequestParam(required=true) String id) {
		SafetyPunish safetyPunish=safetyPunishService.viewSafetyPunishById(id);
		return safetyPunish;
	}
	
	/**
	 *  查大类下属小类
	 *  
	 */
	
	@RequestMapping(value = "/querySafetyPunishMin")
	@ResponseBody
	public Map<String,Object> querySafetyPunishMin(SafetyPunishReq safetyPunishReq){
		try {
			if(StringUtils.isBlank(safetyPunishReq.getCorpId())){
				LoginInfo loginInfo=SessionUtil.getLoginInfo();
				safetyPunishReq.setCorpId(loginInfo.getCorpId());
			}
			Map<String,Object> safetyPunishM = safetyPunishService.querySafetyPunishMin(safetyPunishReq);
			return safetyPunishM;
		} catch (Exception e) {
			logger.error("信息查询失败！", e);
			return null;
		}
	}
	
	
	
	
	
	/**
	 *  增加时查询最大id
	 *  @return id
	 */
	
	@RequestMapping(value = "/querySafetyPunishAdd")
	@ResponseBody
	public String querySafetyPunishAdd(String id){
		return safetyPunishService.findId(id);
	}
	
	/**
	 * 小项增加时查询最大ID
	 * @return id
	 */
	@RequestMapping(value = "/querySmId")
	@ResponseBody
	public String querySmId(@RequestBody(required = true) String id){
		String nId  = safetyPunishService.findSmId(id);
		if(null == nId || "".equals(nId)){
			return id+"01";
		}else{
			return nId;
		}
		
	}
	
	
	/**
	 * 保存
	 * @author cui
	 * @createTime 2016-7-11
	 * @param  
	 */
	@RequestMapping(value = "/saveSafetyPunish")
	@ResponseBody
	public void saveSafetyPunish(@RequestBody(required = true) SafetyPunish safetyPunish){
		try {
			safetyPunishService.deleteSafetyPunish(safetyPunish.getId());
			safetyPunishService.saveSafetyPunish(safetyPunish);
		} catch (Exception e) {
			logger.error("单位信息保存失败！", e);
			
		}
	}
	
	/**
	 * 保存细则小类
	 * @param safetyPunish
	 * @author ht.hu
	 */
	@RequestMapping(value = "/saveAllSafetyPunish")
	@ResponseBody
	public void saveAllSafetyPunish(@RequestBody(required = true) List<SafetyPunish> safetyPunish){
		try {
			for(SafetyPunish s : safetyPunish ){
				safetyPunishService.saveSafetyPunish(s);
			}
			//safetyPunishService.saveALLSmSafetyPunish(safetyPunish) ;
		} catch (Exception e) {
			logger.error("细则小类保存失败！", e);
		}
	}
	
	
	/**
	 * 删除
	 * @author cui
	 * @createTime 2016-7-11
	 * 
	 */
	@RequestMapping("/delSafetyPunish")
	@ResponseBody
	public String delSafetyPunish(@RequestParam String id) {
		try{
			safetyPunishService.deleteSafetyPunish(id);
			return Constants.OPERATE_RESULT_SUCCESS;
		}catch(Exception e){
			logger.error("单位信息删除失败！", e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
}
	
	
	
	
	
	