package cc.dfsoft.project.biz.base.baseinfo.controller;

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

import cc.dfsoft.project.biz.base.baseinfo.dto.DocTypeQueryReq;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.biz.sys.dept.dto.StaffDto;
import cc.dfsoft.uexpress.biz.sys.dept.dto.StaffQueryReq;
import cc.dfsoft.uexpress.biz.sys.dept.enums.DeptTypeEnum;
import cc.dfsoft.uexpress.biz.sys.dept.enums.PostTypeEnum;
import cc.dfsoft.uexpress.biz.sys.dept.enums.UnitTypeEnum;
import cc.dfsoft.uexpress.biz.sys.dept.service.StaffService;
import cc.dfsoft.uexpress.common.util.StringUtil;

/**
 * 
 * 描述:人员信息维护
 * 主要给施工单位、监理单位、检测单位
 * @author liaoyq
 * @createTime 2018年10月11日
 */
@Controller
@RequestMapping(value="/staffInfo")
public class StaffInfoController {
	/** 日志实例 */
	private static Logger logger=LoggerFactory.getLogger(StaffInfoController.class);
	@Resource
	StaffService staffService;

	/**
	 * 打开主页面
	 * @author pengtt
	 *  
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelView=new ModelAndView();
		modelView.setViewName("staff/staffInfo");
		return modelView;
	}
	
	/**
	 * 描述:查询人员列表  默认加载当前用户的公司以及部门下的
	 * @author liaoyq
	 * @createTime 2018年10月11日
	 * @param request
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/queryStaff")
	@ResponseBody
	public Map<String,Object> queryStaff(HttpServletRequest request, StaffQueryReq req){
		try {
			req.setSortInfo(request);
			LoginInfo loginInfo=SessionUtil.getLoginInfo();
			String corpId = request.getParameter("corpId");
			if(StringUtil.isBlank(corpId)){
				corpId = loginInfo.getCorpId();
			}
			String deptId = request.getParameter("deptId");
			if(StringUtil.isBlank(deptId)){
				deptId = loginInfo.getDeptId();
			}
			req.setCorpId(corpId);
			req.setDeptId(deptId);
			//第三方公司查询相应第三方的人员
			if(!UnitTypeEnum.GAS_GROUP.getValue().equals(loginInfo.getUnitType())){
				req.setCorpType(loginInfo.getUnitType());
			}
			//查询人员列表
			Map<String,Object> map = staffService.queryDeptStaffList(req);
			return map;
		} catch (Exception e) {
			logger.error("查询人员列表失败！", e);
			return null;
		}
	}
	/**
	 * 
	 * 描述:详述屏
	 * @author liaoyq
	 * @createTime 2018年10月11日
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
		return "/staff/staffRight";
	}
	
	/**
	 * 修改员工信息
	 * @param staffDto
	 * @return
	 */
	@RequestMapping(value = "/updateStaff")
	@ResponseBody
	public String updateStaff(@RequestBody(required = true) StaffDto staffDto){
		try{
			return staffService.updateStaff(staffDto);
		}catch(Exception e){
			e.printStackTrace();
			logger.error("修改员工信息发生异常。staffDto="+staffDto.toString(), e);
			return "false";
		}
	}
}
