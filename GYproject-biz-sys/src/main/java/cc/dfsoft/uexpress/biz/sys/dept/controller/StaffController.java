package cc.dfsoft.uexpress.biz.sys.dept.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.ChangePasswordReq;
import cc.dfsoft.uexpress.biz.sys.dept.dto.DepartmentReq;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.biz.sys.dept.dto.StaffDto;
import cc.dfsoft.uexpress.biz.sys.dept.dto.StaffQueryReq;
import cc.dfsoft.uexpress.biz.sys.dept.entity.Post;
import cc.dfsoft.uexpress.biz.sys.dept.entity.Staff;
import cc.dfsoft.uexpress.biz.sys.dept.enums.DeptTypeEnum;
import cc.dfsoft.uexpress.biz.sys.dept.enums.PostTypeEnum;
import cc.dfsoft.uexpress.biz.sys.dept.enums.UnitTypeEnum;
import cc.dfsoft.uexpress.biz.sys.dept.service.DepartmentService;
import cc.dfsoft.uexpress.biz.sys.dept.service.PostService;
import cc.dfsoft.uexpress.biz.sys.dept.service.StaffService;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.util.Base64Util;

/**
 * 员工管理控制器
 * @author 1919
 *
 */
@Controller
@RequestMapping("/staff")
public class StaffController {

	/** 日志实例 */
	private static Logger logger = LoggerFactory.getLogger(StaffController.class);
	
	/** 员工服务接口 */
	@Resource
	private StaffService staffService;
	
	/**职务*/
	@Resource
	PostService postService;
	
	/**部门服务接口*/
	@Resource
	DepartmentService departmentService;
	
	/**
	 * 初始化员工管理页面
	 * @param model
	 * @return
	 */
	@RequestMapping("/main")
	public String meterInfo(Model model) {
		return "/dept/staffInfo";
	}
	
	/**
	 * 业务操作页面unitTypes
	 * @param model
	 * @return
	 */
	@RequestMapping("/viewPage")
	public String viewPage(Model model) {
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		model.addAttribute("staffCorpId", loginInfo!=null ? loginInfo.getCorpId() : "");
		List<PostTypeEnum> posts = PostTypeEnum.getObj();
		model.addAttribute("posts", posts);
		UnitTypeEnum[] unitTypes = UnitTypeEnum.values();
		model.addAttribute("unitTypes", unitTypes);
		model.addAttribute("deptType",DeptTypeEnum.SUBCOMPANY.getValue());
		model.addAttribute("staffId", loginInfo.getStaffId());
		return "/dept/staffRight";
	}
	
	/**
	 * 查询员工列表信息弹窗
	 * @param staffDto
	 * @return
	 */
	@RequestMapping(value = "/pop")
	public ModelAndView pop(String pageId, String post) {
		ModelAndView modelview = new ModelAndView();
		modelview.addObject("post", post);
		modelview.setViewName("staff/" + pageId);
		return modelview;
	}
	
	/**
	 * 新增或修改员工信息
	 * @param staffDto
	 * @return
	 */
	@RequestMapping(value = "/addOrUpdateStaff")
	@ResponseBody
	public String addOrUpdateStaff(@RequestBody(required = true) StaffDto staffDto){
		try{
			/*boolean flag = staffService.addOrUpdateStaff(staffDto);
			return String.valueOf(flag);*/
			return staffService.addOrUpdateStaff(staffDto);
		}catch(Exception e){
			e.printStackTrace();
			logger.error("新增或修改员工信息发生异常。staffDto="+staffDto.toString(), e);
			return "false";
		}
	}
	
	/**
	 * 初始化搜索部门员工
	 * @param model
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/staffSearchView")
	public String staffSearch(Model model) {
		model.addAttribute("unitTypes", UnitTypeEnum.values());
		//model.addAttribute("postType", PostTypeEnum.values());
		DepartmentReq departmentReq = new DepartmentReq();
		Map<String, Object> map = postService.getPost(departmentReq);
		List<Post> list = (List<Post>) map.get("data");
		model.addAttribute("postType", list);														  //职务
		model.addAttribute("corp", departmentService.findByType(DeptTypeEnum.SUBCOMPANY.getValue()));//分公司
		model.addAttribute("comp", DeptTypeEnum.GROUP_COMPANY.getValue());							 //集团
		return "/dept/staffSearch";
	}
	
	/**
	 * 查询部门员工列表信息
	 * @param staffDto
	 * @return
	 */
	@RequestMapping(value = "/queryDeptStaffList")
	@ResponseBody
	public Map<String, Object> queryDeptStaffList(HttpServletRequest request, StaffQueryReq staffQueryReq){
		try{
			//设置排序信息
			staffQueryReq.setSortInfo(request);
			Map<String, Object> resultMap = staffService.queryDeptStaffList(staffQueryReq);
			return resultMap;
		}catch(Exception e){
			logger.error("查询部门员工列表信息发生异常。", e);
			return null;
		}
	}
	
	/**
	 * 查询员工列表信息
	 * @param staffDto
	 * @return
	 */
	@RequestMapping(value = "/queryStaffList")
	@ResponseBody
	public Map<String, Object> queryStaffList(HttpServletRequest request, StaffQueryReq staffQueryReq){
		try{
			//设置排序信息
			staffQueryReq.setSortInfo(request);
			Map<String, Object> resultMap = staffService.queryStaffList(staffQueryReq);
			return resultMap;
		}catch(Exception e){
			logger.error("查询员工列表信息发生异常。", e);
			return null;
		}
	}
	
	/**
	 * 查询员工信息
	 * @return
	 */
	@RequestMapping(value = "/queryStaffRole")
	@ResponseBody
	public StaffDto queryStaffRole(String id){
		try{
			StaffDto staffDto = staffService.queryStaffRole(SessionUtil.getTenantId(), id);
			return staffDto;
		}catch(Exception e){
			e.printStackTrace();
			logger.error("查询员工信息发生异常。", e);
			return null;
		}
	}
	
	/**
	 * 初始化员工部门信息
	 * @param model
	 * @return
	 */
	@RequestMapping("/staffDeptTree")
	public String staffDeptTree(Model model) {
		return "/dept/staffDeptTree";
	}


	@RequestMapping("/deptTreePop")
	public String deptTreePop(Model model) {

		return "/dept/deptTreePop";

	}




	/**
	 * 打开人员头像弹框
	 * @param model
	 * @return
	 */
	@RequestMapping("/sideBarStaff")
	public String sideBarStaff(Model model) {
		return "/common/profile";
	}
	/**
	 * 菜单栏获取人员信息
	 * @param model
	 * @return
	 */
	@RequestMapping("/viewBarStaff")
	public String viewBarStaff(Model model) {
		return "/common/viewBarStaff";
	}
	/**
	 * 打开人员头像弹框
	 * @param model
	 * @return
	 */
	@RequestMapping("/staffPhoto")
	public String staffPhoto(Model model) {
		return "/dept/staffPhoto";
	}
	
	/**
	 * 删除员工信息--物理删除
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/deleteStaff")
	@ResponseBody
	public String deleteStaff(String id){
		try{
			boolean flag = staffService.deleteStaff(SessionUtil.getTenantId(), id);
			return String.valueOf(flag);
		}catch(Exception e){
			logger.error("删除员工信息发生异常，staffId="+id, e);
			return "false";
		}
	}
	//弹出修改密码屏
	@RequestMapping(value = "/changePasswordPop")
	public ModelAndView projectSearchPopPage() {
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		ModelAndView modelview = new ModelAndView();
		modelview.addObject("staffId", loginInfo.getStaffId());
		modelview.setViewName("common/changePasswordPop");
		return modelview;
	}
	//保存修改密码
	@RequestMapping(value = "/passwordSave")
	@ResponseBody
	public String totalSave(@RequestBody(required = true) ChangePasswordReq changePasswordReq){
		try {
			staffService.savePasswordChange(changePasswordReq);
			return Constants.OPERATE_RESULT_SUCCESS;
		} catch (Exception e) {
			logger.error("修改密码保存失败！", e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
	
	/**
	 * 查找施工、报验查看权限
	 * @author flw
	 * @createTime 2017-1-12
	 * @param staffId
	 * @return String
	 */
	@RequestMapping(value = "/queryCheckRole")
	@ResponseBody
	public String queryCheckRole(){
		return staffService.queryCheckRole();
	}

	/**
	 * 查找施工、报验查看权限
	 * @author flw
	 * @createTime 2017-1-12
	 * @param staffId
	 * @return String
	 */
	@RequestMapping(value = "/getOldPassword")
	@ResponseBody
	public String getOldPassword(){
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		Staff staff =staffService.getStaff(loginInfo.getStaffId());
		staff.setPassword(Base64Util.jdkBase64Decoder(staff.getPassword()));
		return staff.getPassword();
	};
	
	
	@RequestMapping(value = "/postPop")
	public ModelAndView deptPop(String deptType){
		ModelAndView modelview = new ModelAndView();
		modelview.setViewName("dept/postPop");
		return modelview;
	}
	
	

	/**
	 * 职务选择列表查询
	 * @param PageSortReq
	 * @return
	 */
	@RequestMapping(value = "/queryPost")
	@ResponseBody
	public Map<String, Object> queryDept(HttpServletRequest request,DepartmentReq departmentReq) {
		try {
			departmentReq.setSortInfo(request);
			Map<String, Object> map = postService.getPost(departmentReq);
		    return map;
		} catch (Exception e) {
			logger.error("职务选择列表查询失败！", e);
			return null;
		}
	}
	
	/**
	 * 资质证书
	 * @author fuliwei
	 * @createTime 2017年9月16日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/staffCertificatePop")
	public ModelAndView staffCertificatePop(){
		ModelAndView modelview = new ModelAndView();
		modelview.setViewName("dept/staffCertificatePop");
		return modelview;
	}
	
	
	/**
	 * 查询班组长或者焊工
	 * @author fuliwei
	 * @createTime 2017年11月4日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/buildPop")
	public ModelAndView buildPop(HttpServletRequest request,String post,String deptId,String corpId){
		ModelAndView modelview = new ModelAndView();
		modelview.addObject("post", post);			//职务
		modelview.addObject("deptId",deptId);		//部门ID
		modelview.addObject("corpId",corpId);		//分公司ID
		modelview.setViewName("dept/buildPop");
		return modelview;
	}
}
