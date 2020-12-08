package cc.dfsoft.uexpress.biz.sys.dept.service.impl;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.sql.rowset.serial.SerialException;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cc.dfsoft.uexpress.biz.sys.auth.dao.MenuDao;
import cc.dfsoft.uexpress.biz.sys.auth.dao.NoticeRoleMenuDao;
import cc.dfsoft.uexpress.biz.sys.auth.dao.RoleDao;
import cc.dfsoft.uexpress.biz.sys.auth.dao.RoleMenuDao;
import cc.dfsoft.uexpress.biz.sys.auth.entity.Menu;
import cc.dfsoft.uexpress.biz.sys.auth.entity.NoticeRoleMenu;
import cc.dfsoft.uexpress.biz.sys.auth.entity.Role;
import cc.dfsoft.uexpress.biz.sys.auth.entity.RoleMenu;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.converter.StaffDtoConverter;
import cc.dfsoft.uexpress.biz.sys.dept.dao.DepartmentDao;
import cc.dfsoft.uexpress.biz.sys.dept.dao.LocationSetUpDao;
import cc.dfsoft.uexpress.biz.sys.dept.dao.StaffDao;
import cc.dfsoft.uexpress.biz.sys.dept.dao.StaffNoticeRoleDao;
import cc.dfsoft.uexpress.biz.sys.dept.dao.StaffRoleDao;
import cc.dfsoft.uexpress.biz.sys.dept.dto.ChangePasswordReq;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.biz.sys.dept.dto.StaffDto;
import cc.dfsoft.uexpress.biz.sys.dept.dto.StaffQueryReq;
import cc.dfsoft.uexpress.biz.sys.dept.entity.Department;
import cc.dfsoft.uexpress.biz.sys.dept.entity.LocationSetUp;
import cc.dfsoft.uexpress.biz.sys.dept.entity.RoleDept;
import cc.dfsoft.uexpress.biz.sys.dept.entity.Staff;
import cc.dfsoft.uexpress.biz.sys.dept.entity.StaffNoticeRole;
import cc.dfsoft.uexpress.biz.sys.dept.entity.StaffRole;
import cc.dfsoft.uexpress.biz.sys.dept.enums.DeptTypeEnum;
import cc.dfsoft.uexpress.biz.sys.dept.service.DepartmentService;
import cc.dfsoft.uexpress.biz.sys.dept.service.RoleDeptService;
import cc.dfsoft.uexpress.biz.sys.dept.service.StaffService;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.enums.ResultCodeEnum;
import cc.dfsoft.uexpress.common.util.Base64Util;
import cc.dfsoft.uexpress.common.util.FileUtil;
import cc.dfsoft.uexpress.common.util.IDUtil;
import cc.dfsoft.uexpress.common.util.ImageToBase64Str;
import cc.dfsoft.uexpress.common.util.ResultUtil;
import cc.dfsoft.uexpress.common.util.StringUtil;

/**
 * 员工服务接口实现
 *
 * @author 1919
 *
 */
@Service
@Transactional(readOnly = true,propagation=Propagation.REQUIRED)
public class StaffServiceImpl implements StaffService {
	public static Logger logger = LoggerFactory.getLogger("interfaceinfo");

	/** 员工处理DAO */
	@Resource
	private StaffDao staffDao;
	/** 角色处理DAO */
	@Resource
	private RoleDao roleDao;
	/** 员工角色处理DAO */
	@Resource
	private StaffRoleDao staffRoleDao;
	/** 菜单处理DAO */
	@Resource
	private MenuDao menuDao;
	/** 角色菜单处理DAO */
	@Resource
	private RoleMenuDao roleMenuDao;
	/** 部门处理DAO */
	@Resource
	private DepartmentDao departmentDao;

	/**定位设置Dao*/
	@Resource
	private LocationSetUpDao locationSetUpDao;

	@Resource
	DepartmentService departmentService;

	/**通知角色*/
	@Resource
	private StaffNoticeRoleDao staffNoticeRoleDao;

	/**通知权限菜单*/
	@Resource
	private NoticeRoleMenuDao noticeRoleMenuDao;

	@Resource
	RoleDeptService roleDeptService;

	@Override
	public StaffDto queryStaffRole(String tenantId, String staffId) throws Exception {
		Staff staff = staffDao.queryStaff(tenantId, staffId);
		//staff.setPassword(Base64Util.jdkBase64Decoder(staff.getPassword()));
		StaffRole staffRole = staffRoleDao.queryStaffRoleInfo(tenantId, staffId);
		StaffDto staffDto = StaffDtoConverter.convert(staff);
		staffDto.setPassword(StringUtil.isNotBlank(staffDto.getPassword())?Base64Util.jdkBase64Decoder(staffDto.getPassword()):"nji");
		if(StringUtil.isNoneBlank(staff.getPhotoUrl())){
			//String photoUrl=Constants.PHOTO_PATH;
			staffDto.setPhotoUrl(staff.getPhotoUrl());
		}
		staffDto.setRoleIds(staffRole!=null ? staffRole.getRoleIds() : "");

		//通知角色
		StaffNoticeRole noticeRole=staffNoticeRoleDao.queryStaffRoleInfo(tenantId, staffId);
		staffDto.setNoticeRoleIds(noticeRole!=null ? noticeRole.getRoleIds():"");


		if (staffDto!=null){//将根据deptTransfer拼接eptTransferNamed页面显示
			String deptTransfer = staffDto.getDeptTransfer();
			if (StringUtils.isNotBlank(deptTransfer)){
				String[] deptIds = deptTransfer.split(",");
				List<Department> deptList=departmentDao.queryDeptList(deptIds);
				if (deptList!=null && deptList.size()>0){
					for (Department d:deptList) {
						if (StringUtils.isNotBlank(staffDto.getDeptTransferName())) {
							staffDto.setDeptTransferName(staffDto.getDeptTransferName()+"，" + d.getDeptName());
						}else {
							staffDto.setDeptTransferName(d.getDeptName());
						}
					}
				}
			}
		}

		return staffDto;
	}







	@Override
	@Transactional(readOnly = true,propagation = Propagation.REQUIRED)
	public Map<String, Object> queryStaffList(StaffQueryReq staffQueryReq) {
		Map<String, Object> map = staffDao.queryStaffList(staffQueryReq);
		return map;
	}

	@Override
	public Map<String, Object> queryDeptStaffList(StaffQueryReq staffQueryReq) {
		Map<String, Object> map = staffDao.queryDeptStaffList(staffQueryReq);
		return map;

	}


	@Override
	public List<Staff> findByStaffNo(String staffNo) {
		return staffDao.findByStaffNo(staffNo);
	}

	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public String addOrUpdateStaff(StaffDto staffDto) throws Exception {
		String prefixPath=Constants.DISK_PATH+Constants.PHOTO_PATH;   			//存入图片的路径
		String signPicturePath=Constants.DISK_PATH+Constants.SIGN_DISK_PATH;   //存入电子签名的路径
		LoginInfo loginInfo = SessionUtil.getLoginInfo();             //得到当前登录人信息
		if(loginInfo == null){       //判断登录人信息是否存在
			return "false";
		}
		SimpleDateFormat sdf=new   SimpleDateFormat( " yyyy-MM-dd HH:mm:ss" );
		String date = sdf.format(new Date());
		/**
		 * wang.hui.jun
		 * 20180705
		 * 判断字符串第一位和最后一位是否是逗号 ,
		 * 如果第一位和最后一位不是逗号，则在字符串前后加上逗号
		 * 主要目的是为了提高模糊查询职务编号时的准确性
		 */
		if(StringUtil.isNotBlank(staffDto.getPost())){
			if(!staffDto.getPost().startsWith(",") && !staffDto.getPost().endsWith(",")){
				StringBuffer StringPost = new StringBuffer();
				StringPost.append(",").append(staffDto.getPost()).append(",");
				staffDto.setPost(StringPost.toString());
			}
		}
		staffDto.setTenantId(loginInfo.getTenantId());
		Staff staff = StaffDtoConverter.convert(staffDto);

		if (StringUtils.isBlank(staffDto.getDeptTransfer())){
			staff.setDeptTransfer(staff.getDeptId());
		}

		if(StringUtil.isNoneBlank(staff.getPhotoStr())){
			String imgStr="";
			String fileType="";

			String str[]=staff.getPhotoStr().split(",");
			if(str.length>0){
				imgStr=str[1];
				String temp[]=str[0].split(";");
				String temp1[]=temp[0].split("/");
				fileType=temp1[1];
			}
			String tempName=IDUtil.getUniqueId("1");
			String partPath=tempName+"."+fileType;
			String imgFilePath=prefixPath+partPath;
			FileUtil.GenerateImage(imgStr, imgFilePath);
			if(fileType.equals("png")){
				String tempPath=tempName+".jpg";
				String newPath=prefixPath+tempPath;
				partPath=tempPath;
				FileUtil.pngToJpg(imgFilePath, newPath);
			}
			if(StringUtil.isNoneBlank(staffDto.getPhotoUrl())){
				boolean b = FileUtil.deleteFile(prefixPath+staffDto.getPhotoUrl());
				if(b){
					logger.info("删除staff头像文件成功,图片路径："+prefixPath+staffDto.getPhotoUrl()+"删除时间:"+date+"删除人:"+loginInfo.getStaffId());
				}else {
					logger.info("删除staff头像文件失败,图片路径："+prefixPath+staffDto.getPhotoUrl()+"不存在!!!"+" 操作时间："+date+"删除人:"+loginInfo.getStaffId());
				}
			}
			staff.setPhotoUrl(partPath);

		}


		if(StringUtil.isNoneBlank(staff.getSignPictureStr())){
			staff.setSignPictureUrl(staff.getSignPictureStr());
		}

		if(StringUtil.isNoneBlank(staff.getSignPictureStr())){
			String imgStr="";
			String fileType="";

			String prefixSignPath=Constants.DISK_PATH + Constants.SIGN_DISK_PATH;

			String str[]=staff.getSignPictureStr().split(",");
			if(str.length>0){
				imgStr=str[1];
				String temp[]=str[0].split(";");
				String temp1[]=temp[0].split("/");
				fileType=temp1[1];
			}
			String tempName=IDUtil.getUniqueId(Constants.MODULE_CODE_NOTICE);
			String partPath="";
			//String imgFilePath=prefixSignPath+partPath;
			//FileUtil.GenerateImage(imgStr, imgFilePath);
			if(fileType.startsWith("svg")){
				//String tempPath=tempName+".png";
				//String newPath=prefixSignPath+tempPath;
				//partPath=tempPath;
				partPath = FileUtil.svgToPngSign(imgStr, tempName);
			}
			if(StringUtil.isNoneBlank(staffDto.getSignPicturePath())){
				boolean b = FileUtil.deleteFile(prefixSignPath+staffDto.getSignPicturePath());
				if(b){
					logger.info("删除staff签字文件成功,图片路径："+prefixSignPath+staffDto.getSignPicturePath()+"删除时间:"+date+"删除人:"+loginInfo.getStaffId());
				}else {
					logger.info("删除staff签字文件失败,图片路径："+prefixSignPath+staffDto.getSignPicturePath()+"不存在!!!"+" 操作时间："+date+"删除人:"+loginInfo.getStaffId());
				}
			}
			staff.setSignPicturePath(partPath);

		}


		if(StringUtil.isNotBlank(staffDto.getStaffId())){
			//新增时，需要根据部门ID得到公司ID
			Department department = departmentDao.queryDepartment(loginInfo.getTenantId(), staffDto.getDeptId());
			if(department!=null){
				staff.setOrgId(department.getOrgId());
				staff.setTenantId(department.getTenantId());
			}
			//密码通过base64编译保存
			if(StringUtil.isNotBlank(staff.getPassword())){
				staff.setPassword(Base64Util.jdkBase64Encoder(staff.getPassword()));
			}
			//修改员工信息
			staff.setModifyTime(new Date());
			staff.setModifyStaffId(loginInfo.getStaffId());
			staffDao.update(staff);

			//修改员工角色
			StaffRole staffRole = new StaffRole();
			staffRole.setStaffId(staffDto.getStaffId());
			staffRole.setRoleIds(staffDto.getRoleIds());
			//staffRole.setTenantId(staffDto.getTenantId());

			staffRoleDao.updateStaffRole(staffRole);


			//修改员工通知角色

			StaffNoticeRole noticeRole=new StaffNoticeRole();
			noticeRole.setStaffId(staffDto.getStaffId());
			noticeRole.setRoleIds(staffDto.getNoticeRoleIds());

			staffNoticeRoleDao.saveOrUpdate(noticeRole);

		}else{
			//员工号已经存在
			if(this.findByStaffNo(staff.getStaffNo()).size()>0){
				return "exist";
			}
			if(this.findByLoginAccount(staff.getLoginAccount()).size()>0){
				return "existAccount";
			}
			//新增时判断是集团公司还是业务合作伙伴
			if(staffDto.getCorpType().equals("0")){
				//新增时，需要根据部门ID得到公司ID
				Department department = departmentDao.queryDepartment(loginInfo.getTenantId(), staffDto.getDeptId());
				String deptType = department.getDeptType();
				String deptId = department.getDeptId();
				String corpId="";
				if (deptType.equals(DeptTypeEnum.GROUP_COMPANY.getValue())) {
					corpId = deptId;
				}else if(deptType.equals(DeptTypeEnum.SUBCOMPANY.getValue())){
					corpId= deptId;
				}else if(deptType.equals(DeptTypeEnum.MANAGE_DEPT.getValue())){
					corpId = deptId.substring(0, deptId.length() - DeptTypeEnum.MANAGE_DEPT.getInitVal().length());
				}else if(deptType.equals(DeptTypeEnum.BUSINESS_GROUPS.getValue())){
					corpId =  deptId.substring(0, deptId.length() - DeptTypeEnum.BUSINESS_GROUPS.getInitVal().length()-DeptTypeEnum.MANAGE_DEPT.getInitVal().length());
				}
				staff.setCorpId(corpId);
				staff.setOrgId(department.getOrgId());
				staff.setTenantId(department.getTenantId());
			}
			//密码通过base64编译保存
			if(StringUtil.isNotBlank(staff.getPassword())){
				staff.setPassword(Base64Util.jdkBase64Encoder(staff.getPassword()));
			}
			staff.setStaffId(staff.getStaffNo());
			staff.setCreateTime(new Date());
			staff.setCreateStaffId(loginInfo.getStaffId());
			staffDao.insertStaff(staff);

			//新增员工角色
			StaffRole staffRole = new StaffRole();
			staffRole.setStaffId(staff.getStaffId());
			staffRole.setRoleIds(staffDto.getRoleIds());
			//staffRole.setTenantId(staffDto.getTenantId());
			staffRoleDao.saveOrUpdate(staffRole);


			//新增通知角色
			StaffNoticeRole noticeRole=new StaffNoticeRole();
			noticeRole.setStaffId(staff.getStaffId());
			noticeRole.setRoleIds(staffDto.getNoticeRoleIds());

			staffNoticeRoleDao.saveOrUpdate(noticeRole);

		}
		return Constants.OPERATE_RESULT_SUCCESS;
	}

	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public boolean deleteStaff(String tenantId, String staffId) {
		staffDao.deleteStaff(tenantId, staffId);
		//staffRoleDao.deleteStaffRole(staffId);  注释掉---删除员工只做删除标记处理
		return true;
	}

	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public Map<String, Object> staffLogin(String sysUrl, String loginAccount, String password,String registrationID) {
		String tenantId = SessionUtil.getTenantId();
		// 先查询员工信息
		Staff staff = staffDao.queryStaffLoginInfo(tenantId, loginAccount);
		//密码base64编译
		password = Base64Util.jdkBase64Encoder(password);
		if (staff == null) {
			return ResultUtil.resultMap(ResultCodeEnum.FAIL.getValue(), "账号不存在！");
		}else if(!password.equals(staff.getPassword())){
			return ResultUtil.resultMap(ResultCodeEnum.FAIL.getValue(), "密码不正确！");
		}

		//清空该设备号下的人员，将该设备号给当前登录人
		if(StringUtil.isNotBlank(registrationID)){
			staffDao.clearRegistrationID(staff.getStaffId(),registrationID);
		}
		StringBuffer roleNames = new StringBuffer();
		//受理部门ID数组
		List<String> roleDepts = new ArrayList<String>();
		// 获取员工角色信息
		List<Role> roleList = getRoleList(tenantId, staff.getStaffId());
		for (Role role : roleList) {
			roleNames.append(roleNames.length() > 0 ? "," : "");
			roleNames.append(role.getRoleName());
			//获取角色-受理部门配置-todo
			List<RoleDept> roleDeptList = roleDeptService.findDepts(role.getRoleId());
			if(roleDeptList!=null && roleDeptList.size()>0){
				for(RoleDept roleDept :roleDeptList){
					if(StringUtil.isNotBlank(roleDept.getDeptIds())){
						String[] depts =  roleDept.getDeptIds().split(",");
						//该部门已存在
						for(String deptId:depts){
							roleDepts.add(deptId);
						}
					}
				}
			}
		}
		//查询所有菜单信息
		LoginInfo loginInfo = new LoginInfo();
		if(roleDepts!=null &&roleDepts.size()>0){
			loginInfo.setDeptIds(roleDepts);
		}
		loginInfo.setLoginAccount(staff.getLoginAccount());
		loginInfo.setCorpId(staff.getCorpId());
		loginInfo.setPhotoUrl(Constants.PHOTO_PATH+staff.getPhotoUrl());
		loginInfo.setDeptId(staff.getDeptId());
		loginInfo.setDeptTransfer(staff.getDeptTransfer());
		loginInfo.setStaffId(staff.getStaffId());
		loginInfo.setStaffName(staff.getStaffName());
		loginInfo.setMobile(staff.getMobile());
		loginInfo.setOrgId(staff.getOrgId());
		loginInfo.setUnitType(staff.getUnitType());
		loginInfo.setRegistrationId(registrationID);
		Department department = departmentDao.get(staff.getDeptId());
		if (department!=null && StringUtils.isNotBlank(department.getDeptName())) {
			loginInfo.setDeptName(department.getDeptName());
			loginInfo.setDeptDivide(department.getDeptDivide());
			loginInfo.setBusinessType(department.getBusinessType());
		}
		loginInfo.setPost(staff.getPost());

		//分公司
		Department corpDepartment=departmentService.queryDepartment(staff.getCorpId());
		if(department!=null && StringUtils.isNotBlank(corpDepartment.getDeptName())){
			loginInfo.setCorpName(corpDepartment.getDeptName());
		}
		LocationSetUp loc=locationSetUpDao.queryByDeptType(staff.getUnitType());
		if(loc!=null){
			loginInfo.setLocationDuration(loc.getLocationDuration());
		}

		loginInfo.setSignPicture(staff.getSignPicturePath());
		loginInfo.setBelongCorpId(staff.getBelongCorpId());
		//修改设备ID
		//staff.setRegistrationId(registrationID);
		staffDao.update(staff);
		return ResultUtil.resultMap(ResultCodeEnum.SUCCESS.getValue(), loginInfo);
	}


	@Override
	public List<StaffDto> queryStaffByDeptId(String tenantId, String deptId) {
		List<Staff> staffList = staffDao.queryStaffByDeptId(tenantId, deptId);
		return StaffDtoConverter.convert(staffList);
	}

	@Override
	public Map<String,Object> getStaffMenuList(String tenantId, String staffId) {
		List<Menu> menus = new ArrayList<Menu>();
		Map<String, Object> returnMap=new HashMap<String,Object>();
		// 查询员工角色信息
		StaffRole staffRole = staffRoleDao.queryStaffRoleInfo(tenantId, staffId);
		// 为空直接返回
		if(staffRole == null){
			return returnMap;
		}

		String[] roleIds = StringUtil.split(staffRole.getRoleIds(), ",");
		// 多角色合并菜单
		StringBuffer roleMenus = new StringBuffer();
		for (String roleId : roleIds) {
			RoleMenu roleMenu = roleMenuDao.queryRoleMenu(tenantId, roleId);
			if (roleMenu != null && StringUtil.isNotBlank(roleMenu.getMenuIds())) {
				if (roleMenus.length() > 0) {
					roleMenus.append(",");
				}
				roleMenus.append(roleMenu.getMenuIds());
			}
		}

		// 去除重复菜单
		List<String> roleMenuList = StringUtil.RemoveRepeatItem(roleMenus.toString());
		// 得到员工所属菜单
		List<String> parentIDList = new ArrayList<String>();
		Map<String, String> btnMap=new HashMap<String,String>();
		//
		List<String> rootMenuIDList = new ArrayList<String>();

		// 获得所有菜单
		LinkedHashMap<String, Menu> allMenuMap = this.getAllMenuInfo();
		for (String menuId : roleMenuList) {
			if(menuId.contains("btn")){
				String id=menuId.substring(4, menuId.length());
				String newId=id.substring(0, id.length()-2);
				String btnLevel=id.substring(id.length()-2,id.length());
				if(btnMap.containsKey(newId)){
					btnMap.put(newId, btnLevel+","+btnMap.get(newId));
				}else{
					btnMap.put(newId, btnLevel);
					menuId=	newId;
				}
			}else if(menuId.contains("check")){
				String newId=menuId.substring(6, menuId.length());
				if(!btnMap.containsKey(newId)){
					menuId=	newId;
					btnMap.put(newId, "-2");
				}
			}else{
				if(btnMap.containsKey(menuId)){
					continue;
				}else{
					Menu menu = allMenuMap.get(menuId);
					if(menu!=null){
						if(menu.getMenuLevel().equals("-1")){
							btnMap.put(menuId, "-1"); //按钮级别
						}else{
							btnMap.put(menuId, "-2");//菜单级别
						}
					}

				}

			}
			Menu menu = allMenuMap.get(menuId);
			if (menu == null) {
				continue;
			}else if(menu.getMenuLevel().equals("-1")){
				menu = allMenuMap.get(menu.getParentId());
			}


			if (StringUtils.isNotBlank(menu.getParentId())) {
				if (!roleMenuList.contains(menu.getParentId())  && !parentIDList.contains(menu.getParentId())) {
					parentIDList.add(menu.getParentId());
				}
			}
			if(!menus.contains(menu)){
				menus.add(menu);
			}

		}


		for (String parentMenuId : parentIDList) {
			Menu menu = allMenuMap.get(parentMenuId);
			if (menu == null) {
				continue;
			}
			if (StringUtils.isNotBlank(menu.getParentId())) {
				if (!roleMenuList.contains(menu.getParentId()) &&!parentIDList.contains(menu.getParentId()) && !rootMenuIDList.contains(menu.getParentId())) {
					rootMenuIDList.add(menu.getParentId());
				}
			}
		}

		// 如果缺少父节点菜单，则补充父菜单
		if (parentIDList!=null && parentIDList.size()>0) {
			for (String pmenuId : parentIDList) {
				Menu menu = this.getAllMenuInfo().get(pmenuId);
				menus.add(menu);
			}
		}
		// 顶级菜单
		if (rootMenuIDList!=null && rootMenuIDList.size()>0) {
			for (String rootMenuId : rootMenuIDList) {
				Menu menu = this.getAllMenuInfo().get(rootMenuId);
				menus.add(menu);
			}
		}
		returnMap.put("menus", menus);
		returnMap.put("btns", btnMap);
		return returnMap;
	}

	/**
	 * 查询所有菜单信息
	 * @return
	 */
	private LinkedHashMap<String, Menu> getAllMenuInfo() {
		LinkedHashMap<String, Menu> menuMap = null;
		List<Menu> menuList = menuDao.queryAll();
		if (menuList != null && menuList.size() > 0) {
			menuMap = new LinkedHashMap<String, Menu>();
			for (Menu menu : menuList) {
				menuMap.put(menu.getMenuId(), menu);
			}
		}
		return menuMap;
	}

	/**
	 * 查询员工角色信息
	 *
	 * @param tenantId
	 * @param staffId
	 * @return
	 */
	public List<Role> getRoleList(String tenantId, String staffId) {
		List<Role> roleList = new ArrayList<Role>();
		StaffRole staffRole = staffRoleDao.queryStaffRoleInfo(tenantId, staffId);
		if(staffRole != null){
			String[] roleIds = StringUtil.split(staffRole.getRoleIds(), ",");
			if (roleIds!=null && roleIds.length>0) {
				for (String roleId : roleIds) {
					Role role = roleDao.queryRole(tenantId, roleId);
					if (role != null) {
						roleList.add(role);
					}
				}
			}
		}
		return roleList;
	}

	@Override
	public Staff getStaff(String staffId) {

		return staffDao.get(staffId);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void savePasswordChange(ChangePasswordReq changePasswordReq) {
		Staff staff = this.getStaff(changePasswordReq.getStaffId());
		//密码通过base64编译保存
		if(StringUtil.isNotBlank(changePasswordReq.getConfirmPassword())){
			String encode = Base64Util.jdkBase64Encoder(changePasswordReq.getConfirmPassword());
			staff.setPassword(encode);
		}
		staffDao.updateStaff(staff);
	}

	/**
	 * 查找施工、报验查看权限
	 * @author flw
	 * @createTime 2017-1-12
	 * @param
	 * @return String
	 */
	@Override
	public String queryCheckRole() {
		LoginInfo loginInfo=SessionUtil.getLoginInfo();
		return staffRoleDao.queryCheckRole(loginInfo.getStaffId());
	}

	/**
	 * 查询职务
	 * @author fuliwei
	 * @createTime 2017-1-21
	 * @param
	 * @return List
	 */
	@Override
	public List queryPost(String post) {
		return staffDao.queryPost(post);
	}

	/**
	 * 查询员工列表信息
	 * @param staffQueryReq
	 * @return
	 */
	@Override
	public Map<String, Object> queryManageStaffList(StaffQueryReq staffQueryReq) {
		return staffDao.queryManageStaffList(staffQueryReq);
	}

	/**
	 * 查询员工通知菜单信息列表
	 * @param tenantId
	 * @param staffId
	 * @return
	 */
	@Override
	public Map<String, Object> getStaffRoleMenuList(String tenantId, String staffId) {
		List<Menu> menus = new ArrayList<Menu>();
		Map<String, Object> returnMap=new HashMap<String,Object>();

		// 查询员工角色信息
		//StaffRole staffRole = staffRoleDao.queryStaffRoleInfo(tenantId, staffId);
		StaffNoticeRole snr=staffNoticeRoleDao.queryStaffRoleInfo(tenantId, staffId);

		// 为空直接返回
		if(snr==null){
			return returnMap;
		}

		/*
		if(staffRole == null){
			return returnMap;
		}*/

		String[] roleIds = StringUtil.split(snr.getRoleIds(), ",");
		// 多角色合并菜单
		StringBuffer roleMenus = new StringBuffer();
		for (String roleId : roleIds) {
			//RoleMenu roleMenu = roleMenuDao.queryRoleMenu(tenantId, roleId);

			NoticeRoleMenu roleMenu=noticeRoleMenuDao.queryRoleMenu(tenantId, roleId);

			if (roleMenu != null && StringUtil.isNotBlank(roleMenu.getNrMenuIds())) {
				if (roleMenus.length() > 0) {
					roleMenus.append(",");
				}
				roleMenus.append(roleMenu.getNrMenuIds());
			}
		}

		// 去除重复菜单
		List<String> roleMenuList = StringUtil.RemoveRepeatItem(roleMenus.toString());
		// 得到员工所属菜单
		List<String> parentIDList = new ArrayList<String>();
		Map<String, String> btnMap=new HashMap<String,String>();
		//
		List<String> rootMenuIDList = new ArrayList<String>();

		// 获得所有菜单
		LinkedHashMap<String, Menu> allMenuMap = this.getAllMenuInfo();

		if(roleMenuList!=null && roleMenuList.size()>0){
			for (String menuId : roleMenuList) {
				/*if(menuId.contains("btn")){
					String id=menuId.substring(4, menuId.length());
					String newId=id.substring(0, id.length()-2);
					String btnLevel=id.substring(id.length()-2,id.length());
					if(btnMap.containsKey(newId)){
						btnMap.put(newId, btnLevel+","+btnMap.get(newId));
					}else{
						btnMap.put(newId, btnLevel);
						menuId=	newId;
					}
				}else if(menuId.contains("check")){
					String newId=menuId.substring(6, menuId.length());
					if(!btnMap.containsKey(newId)){
						menuId=	newId;
						btnMap.put(newId, "-2");
					}
				}else{
					if(btnMap.containsKey(menuId)){
						continue;
					}else{
						Menu menu = allMenuMap.get(menuId);
					    if(menu!=null){
						if(menu.getMenuLevel().equals("-1")){
							btnMap.put(menuId, "-1"); //按钮级别
						}else{
							btnMap.put(menuId, "-2");//菜单级别
						}
					    }

					}

				}*/
				Menu menu = new Menu();
				menu.setMenuId(menuId);
					/*if (menu == null) {
						continue;
					}else if(menu.getMenuLevel().equals("-1")){
						 menu = allMenuMap.get(menu.getParentId());
					}*/


				/*if (StringUtils.isNotBlank(menu.getParentId())) {
					if (!roleMenuList.contains(menu.getParentId())  && !parentIDList.contains(menu.getParentId())) {
						parentIDList.add(menu.getParentId());
					}
				}*/
				if(!menus.contains(menu)){
					menus.add(menu);
				}

			}
		}



/*		for (String parentMenuId : parentIDList) {
			Menu menu = allMenuMap.get(parentMenuId);
			if (menu == null) {
				continue;
			}
			if (StringUtils.isNotBlank(menu.getParentId())) {
				if (!roleMenuList.contains(menu.getParentId()) &&!parentIDList.contains(menu.getParentId()) && !rootMenuIDList.contains(menu.getParentId())) {
					rootMenuIDList.add(menu.getParentId());
				}
			}
		}

		// 如果缺少父节点菜单，则补充父菜单
		if (parentIDList!=null && parentIDList.size()>0) {
			for (String pmenuId : parentIDList) {
				Menu menu = this.getAllMenuInfo().get(pmenuId);
				menus.add(menu);
			}
		}
		// 顶级菜单
		if (rootMenuIDList!=null && rootMenuIDList.size()>0) {
			for (String rootMenuId : rootMenuIDList) {
				Menu menu = this.getAllMenuInfo().get(rootMenuId);
				menus.add(menu);
			}
		}*/
		returnMap.put("menus", menus);
		returnMap.put("btns", btnMap);
		return returnMap;
	}
	@Override
	public List<Staff> findByLoginAccount(String loginAccount) {
		return staffDao.findByLoginAccount(loginAccount);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public String updateStaff(StaffDto staffDto) throws Exception {
		String prefixPath=Constants.DISK_PATH+Constants.PHOTO_PATH;   			//存入图片的路径
		LoginInfo loginInfo = SessionUtil.getLoginInfo();             //得到当前登录人信息
		SimpleDateFormat sdf=new   SimpleDateFormat( " yyyy-MM-dd HH:mm:ss" );
		String date = sdf.format(new Date());
		if(loginInfo == null){       //判断登录人信息是否存在
			return Constants.OPERATE_RESULT_FAILURE;
		}
		if(StringUtil.isBlank(staffDto.getStaffId())){
			return Constants.OPERATE_RESULT_FAILURE;
		}
		Staff staff = staffDao.get(staffDto.getStaffId());
		if(StringUtil.isNoneBlank(staffDto.getStaffName())){
			staff.setStaffName(staffDto.getStaffName());
		}
		if(StringUtil.isNoneBlank(staffDto.getAddr())){
			staff.setAddr(staffDto.getAddr());
		}
		if(StringUtil.isNoneBlank(staffDto.getPhone())){
			staff.setPhone(staffDto.getPhone());
		}
		if(StringUtil.isNoneBlank(staffDto.getMobile())){
			staff.setMobile(staffDto.getMobile());
		}
		if(StringUtil.isNoneBlank(staffDto.getHomePhone())){
			staff.setHomePhone(staffDto.getHomePhone());
		}
		if(StringUtil.isNoneBlank(staffDto.getQq())){
			staff.setQq(staffDto.getQq());
		}
		if(StringUtil.isNoneBlank(staffDto.getPhotoStr())){
			staff.setPhotoStr(staffDto.getPhotoStr());
			String imgStr="";
			String fileType="";

			String str[]=staff.getPhotoStr().split(",");
			if(str.length>0){
				imgStr=str[1];
				String temp[]=str[0].split(";");
				String temp1[]=temp[0].split("/");
				fileType=temp1[1];
			}
			String tempName=IDUtil.getUniqueId("1");
			String partPath=tempName+"."+fileType;
			String imgFilePath=prefixPath+partPath;
			FileUtil.GenerateImage(imgStr, imgFilePath);
			if(fileType.equals("png")){
				String tempPath=tempName+".jpg";
				String newPath=prefixPath+tempPath;
				partPath=tempPath;
				FileUtil.pngToJpg(imgFilePath, newPath);
			}
			if(StringUtil.isNoneBlank(staffDto.getPhotoUrl())){
				Boolean b=FileUtil.deleteFile(prefixPath+staffDto.getPhotoUrl());
				if(b){
					logger.info("信息维护删除staff头像文件成功,图片路径："+prefixPath+staffDto.getPhotoUrl()+"删除时间:"+date+"删除人:"+loginInfo.getStaffId());
				}else {
					logger.info("信息维护删除staff头像文件失败,图片路径："+prefixPath+staffDto.getPhotoUrl()+"不存在!!!"+" 操作时间："+date+"删除人:"+loginInfo.getStaffId());
				}
			}
			staff.setPhotoUrl(partPath);

		}

		if(StringUtil.isNoneBlank(staff.getSignPictureStr())){
			staff.setSignPictureUrl(staff.getSignPictureStr());
		}
		if(StringUtil.isNoneBlank(staffDto.getSignPictureStr())){
			staff.setSignPictureStr(staffDto.getSignPictureStr());
			String imgStr="";
			String fileType="";

			String prefixSignPath=Constants.DISK_PATH + Constants.SIGN_DISK_PATH;

			String str[]=staff.getSignPictureStr().split(",");
			if(str.length>0){
				imgStr=str[1];
				String temp[]=str[0].split(";");
				String temp1[]=temp[0].split("/");
				fileType=temp1[1];
			}
			String tempName=IDUtil.getUniqueId(Constants.MODULE_CODE_NOTICE);
			String partPath="";
			//String imgFilePath=prefixSignPath+partPath;
			//FileUtil.GenerateImage(imgStr, imgFilePath);
			if(fileType.startsWith("svg")){
				//String tempPath=tempName+".png";
				//String newPath=prefixSignPath+tempPath;
				//partPath=tempPath;
				partPath = FileUtil.svgToPngSign(imgStr, tempName);
			}
			if(StringUtil.isNoneBlank(staffDto.getSignPicturePath())){
				Boolean b=FileUtil.deleteFile(prefixSignPath+staffDto.getSignPicturePath());
				if(b){
					logger.info("信息维护删除staff签字文件成功,图片路径："+prefixSignPath+staffDto.getSignPicturePath()+"删除时间:"+date+"删除人:"+loginInfo.getStaffId());
				}else {
					logger.info("信息维护删除staff签字文件失败,图片路径："+prefixSignPath+staffDto.getSignPicturePath()+"不存在!!!"+" 操作时间："+date+"删除人:"+loginInfo.getStaffId());
				}
			}
			staff.setSignPicturePath(partPath);

		}
		staffDao.update(staff);
		return Constants.OPERATE_RESULT_SUCCESS;
	}

	@Override
	public List<Map<String, Object>> getUserByIds(String[] ids) {
		return staffDao.getUserByIds(ids);
	}

	
	/**
	 * 查询操作人--操作流程设置查询使用
	 * @param request
	 * @param staffQueryReq
	 * @return
	 * @throws Exception
	 */
	@Override
	public Map<String, Object> queryOperateStaff(StaffQueryReq staffQueryReq)
			throws Exception {
		// TODO Auto-generated method stub
		return staffDao.queryOperateStaff(staffQueryReq);
	}

	@Override
	public boolean isExistStaff(String staffId) {
		return staffDao.isExistStaff(staffId);
	}

	/**
	 * 更新设备id
	 * @author fuliwei
	 * @date 2019/8/27
	 * @param
	 * @return String
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public String updateStaffRegiId(String registrationID) {

		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		Staff staff = staffDao.get(loginInfo.getStaffId());
		if(staff != null){
			staff.setRegistrationId(registrationID);
			staffDao.saveOrUpdate(staff);
			return Constants.OPERATE_RESULT_SUCCESS;
		}

		return Constants.OPERATE_RESULT_FAILURE;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void clearCurStaffRegistrationId(String staffId,
			String registrationId) {
		staffDao.clearCurStaffRegistrationId(staffId,registrationId);
		
	}

}
