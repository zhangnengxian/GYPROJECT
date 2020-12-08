package cc.dfsoft.project.biz.base.project.controller;

import java.util.List;
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

import cc.dfsoft.project.biz.base.contract.enums.ContractTypeEnum;
import cc.dfsoft.project.biz.base.project.dto.ProjectTypeReq;
import cc.dfsoft.project.biz.base.project.entity.ProjectType;
import cc.dfsoft.project.biz.base.project.service.ProjectTypeService;
import cc.dfsoft.uexpress.biz.sys.dept.service.DepartmentService;
import cc.dfsoft.uexpress.common.constant.Constants;

@Controller
@RequestMapping(value="/projectType")
public class ProjectTypeController {
	
	/** 日志实例 */
	private static Logger logger=LoggerFactory.getLogger(ProjectTypeController.class);
	
	/**部门服务接口*/
	@Resource
	DepartmentService departmentService;
	
	/**
	 * 资料标准
	 */
	@Resource
	ProjectTypeService projectTypeService;
	
	/**
	 * 打开主页面
	 * @return
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelView=new ModelAndView();
		modelView.setViewName("projecttype/projectType");
		return modelView;
	}
	
	/**
	 * 打开右侧
	 * @return
	 */
	@RequestMapping(value="/projectTypeView")
	public ModelAndView accessoryItemView(){
		ModelAndView modelView=new ModelAndView();
		modelView.addObject("contractType",ContractTypeEnum.values());//合同类型
		modelView.setViewName("projecttype/projectTypeRight");
		return modelView;
	}
	
	/**
	 * 资料标准列表条件查询
	 * @param 
	 * @author 
	 * @createTime 2016-07-12
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/queryProjectType")
	@ResponseBody
	public Map<String,Object> queryProjectType(HttpServletRequest request,ProjectTypeReq projectTypeReq){
		try {
			projectTypeReq.setSortInfo(request);
			Map<String,Object> map = projectTypeService.queryProjectTypeList(projectTypeReq);
			return map;
		} catch (Exception e) {
			logger.error("工程信息查询失败！", e);
			return null;
		}
	}
	
	
	/**
	 * 弹出搜索屏
	 * @author zhangjj
	 * @createTime 2016-11-28
	 * @param  
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/projectTypeSearchPop")
	public ModelAndView projectTypeSearchPop() {
		ModelAndView modelview = new ModelAndView();
		modelview.setViewName("projectType/projectTypeSearchPop");
		return modelview;
	}
	
	/**
	 * 保存工程类型
	 * @author zhangjj
	 * @createTime 2016-11-28
	 * @return 
	 */
	@ResponseBody
	@RequestMapping(value = "/saveOrUpdateProjectType")
	public String saveOrUpdateProjectType(@RequestBody(required = true) ProjectType projectType){
		try{
			 projectTypeService.saveOrUpdateProjectType(projectType);
			 return Constants.OPERATE_RESULT_SUCCESS;
		}catch(Exception e){
			logger.error("工程类型保存失败！",e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}

	/**
	 * 删除工程类型
	 * @author zhangjj
	 * @createTime 2016-11-28
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/deleteProjectType")
	public String deleteProjectType(String id){
		try{
			return projectTypeService.delProjectType(id);
		}catch(Exception e){
			logger.error("工程类型删除失败！",e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
	
	/**
	 * 根据部门id查询工程类型
	 * @author fuliwei
	 * @createTime 2017年8月2日
	 * @param 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/queryPorjectTypeList")
	public List<ProjectType> queryProjectTypeByDeptId(String deptId){
		try{
			return projectTypeService.queryProjectTypeByDeptId(deptId);
		}catch(Exception e){
			logger.error("查询工程类型失败！",e);
			return null;
		}
	}
	
	/**
	 * 通过 主键id查询合同类型
	 * @author fuliwei
	 * @createTime 2017年8月30日
	 * @param 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/findByProjTypeId")
	public String findByProjTypeId(String projTypeId){
		try{
			return projectTypeService.findByProjTypeId(projTypeId);
		}catch(Exception e){
			logger.error("查询合同类型失败！",e);
			return null;
		}
	}
	
}


