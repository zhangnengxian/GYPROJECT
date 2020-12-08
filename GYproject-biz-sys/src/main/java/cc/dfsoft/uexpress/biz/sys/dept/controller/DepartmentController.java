package cc.dfsoft.uexpress.biz.sys.dept.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import cc.dfsoft.uexpress.biz.sys.dept.entity.Department;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.DepartmentDto;
import cc.dfsoft.uexpress.biz.sys.dept.dto.DepartmentReq;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.biz.sys.dept.enums.BusinessTypeEnum;
import cc.dfsoft.uexpress.biz.sys.dept.enums.DeptDivideEnum;
import cc.dfsoft.uexpress.biz.sys.dept.service.DepartmentService;

/**
 * 部门管理控制器
 * @author 1919
 *
 */
@Controller
@RequestMapping("/dept")
public class DepartmentController {

	/** 日志实例 */
	private static Logger logger = LoggerFactory.getLogger(DepartmentController.class);
	
	/** 部门服务接口 */
	@Resource
	private DepartmentService departmentService;
	/**
	 * 初始化部门管理页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/main")
	public ModelAndView main() {
		ModelAndView modelview = new ModelAndView();
		modelview.setViewName("dept/deptInfo");
		return modelview;
	}
	
	/**
	 * 业务操作页面
	 * @param model
	 * @return
	 */
	@RequestMapping("/viewPage")
	public ModelAndView viewPage(Model model) {
		
		ModelAndView modelview = new ModelAndView();
		modelview.addObject("businessType", BusinessTypeEnum.values()); 			//业务类型
		//modelview.addObject("projContructType", ProjContructTypeEnum.values()); 	//工程建设类型
		modelview.addObject("deptDivide", DeptDivideEnum.values());				//部门划分 
		modelview.setViewName("/dept/deptRight");
		return modelview;
	}
	
	/**
	 * 查询部门列表信息
	 * @return
	 */
	@RequestMapping(value = "/queryDepartmentList")
	@ResponseBody
	public Map<String, Object> queryDepartmentList(HttpServletRequest request, DepartmentReq departmentReq){
		try{
			//设置排序信息
			departmentReq.setSortInfo(request);
			Map<String, Object> resultMap = departmentService.queryDepartmentList(departmentReq);
			return resultMap;
		}catch(Exception e){
			logger.error("查询部门信息发生异常。", e);
			return null;
		}
	}


	/**
	 * 查询部门列表信息
	 * @return
	 */
	@RequestMapping(value = "/geDepartmentList")
	@ResponseBody
	public List<Department> geDepartmentList(@RequestBody DepartmentReq departmentReq){
		try{
			return departmentService.geDepartmentList(departmentReq);
		}catch(Exception e){
			logger.error("查询部门信息发生异常。", e);
			return null;
		}
	}


	
	/**
	 * 查询部门信息
	 * @return
	 */
	@RequestMapping(value = "/queryDepartment")
	@ResponseBody
	public DepartmentDto queryDepartment(String deptId){
		try{
			DepartmentDto departmentDto = departmentService.queryDepartment(SessionUtil.getTenantId(), deptId);
			return departmentDto;
		}catch(Exception e){
			logger.error("查询部门信息发生异常。", e);
			return null;
		}
	}
	
	/**
	 * 获取部门树结构信息
	 * @return
	 */
	@RequestMapping(value = "/getDeptTreeData")
	@ResponseBody
	public List<Map<String, String>> getDeptTreeData(){
		try{
			LoginInfo loginInfo = SessionUtil.getLoginInfo();
			List<Map<String, String>> maplist = departmentService.getDeptTreeData(loginInfo.getCorpId());
			return maplist;
		}catch(Exception e){
			logger.error("获取部门树结构信息发生异常。", e);
			return null;
		}
	}
	
	/**
	 * 新增或修改部门信息
	 * @param departmentDto
	 * @return
	 */
	@RequestMapping(value = "/addOrUpdateDepartment")
	@ResponseBody
	public String addOrUpdateDepartment(@RequestBody(required = true) DepartmentDto departmentDto){
		try{
			boolean flag = departmentService.addOrUpdateDepartment(departmentDto);
			return String.valueOf(flag);
		}catch(Exception e){
			logger.error("新增或修改部门信息发生异常。departmentDto="+departmentDto.toString(), e);
			return "false";
		}
	}
	
	/**
	 * 删除部门信息
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/deleteDepartment")
	@ResponseBody
	public String deleteDepartment(String deptId){
		try{
			boolean flag = departmentService.deleteDepartment(SessionUtil.getTenantId(), deptId);
			return String.valueOf(flag);
		}catch(Exception e){
			logger.error("删除部门信息发生异常，deptId="+deptId, e);
			return "false";
		}
	}
	/**
	 * 获取部门树结构信息(业务沟通页面专用)
	 * @return
	 */
	@RequestMapping(value = "/getDeptTreeDataByCorpId")
	@ResponseBody
	public List<Map<String, String>> getDeptTreeDataByCorpId(String deptId){
		try{
			List<Map<String, String>> maplist = departmentService.getDeptTreeData(deptId);
			return maplist;
		}catch(Exception e){
			logger.error("获取部门树结构信息发生异常。", e);
			return null;
		}
	}
}
