package cc.dfsoft.uexpress.biz.sys.dept.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.sql.rowset.serial.SerialException;

import cc.dfsoft.uexpress.biz.sys.auth.entity.Role;
import cc.dfsoft.uexpress.biz.sys.dept.dto.ChangePasswordReq;
import cc.dfsoft.uexpress.biz.sys.dept.dto.StaffDto;
import cc.dfsoft.uexpress.biz.sys.dept.dto.StaffQueryReq;
import cc.dfsoft.uexpress.biz.sys.dept.entity.Staff;

/**
 * 员工服务接口
 * @author 1919
 *
 */
public interface StaffService {

	/**
	 * 根据租户ID和员工ID查询员工角色信息
	 * @param tenantId
	 * @param staffId
	 * @return
	 */
	public StaffDto queryStaffRole(String tenantId, String staffId)throws Exception;


	/**
	 * 查询部门所有员工
	 * @param tenantId
	 * @param deptId
	 * @return
	 */
	public List<StaffDto> queryStaffByDeptId(String tenantId, String deptId);

	/**
	 * 查询员工列表信息
	 * @param staffQueryReq
	 * @return
	 */
	public Map<String, Object> queryStaffList(StaffQueryReq staffQueryReq);

	/**
	 * 查询部门员工信息
	 * @param staffQueryReq
	 * @return
	 */
	public Map<String, Object> queryDeptStaffList(StaffQueryReq staffQueryReq);

	/**
	 * 新增员工信息
	 * @param staffDto
	 * @throws SQLException
	 * @throws SerialException
	 */
	public String addOrUpdateStaff(StaffDto staffDto)throws Exception ;

	/**
	 * 删除员工信息
	 * @param staffId
	 */
	public boolean deleteStaff(String tenantId, String staffId);


	/**
	 * 员工登录
	 * @param tenantId
	 * @param loginAccount
	 * @param password
	 * @return
	 */
	public Map<String, Object> staffLogin(String tenantId, String loginAccount, String password,String registrationID);

	/**
	 * 查询员工菜单信息列表
	 * @param tenantId
	 * @param staffId
	 * @return
	 */
	public Map<String,Object> getStaffMenuList(String tenantId, String staffId);

	/**
	 * 按员工编号查询
	 * @author
	 * @createTime 2016-08-13
	 * @param staffNo
	 * @return
	 */
	public List<Staff> findByStaffNo(String staffNo);

	/**
	 * 按登录账号查询
	 * @param loginAccount
	 * @return
	 */
	public List<Staff> findByLoginAccount(String loginAccount);

	/**
	 * 查询员工角色信息
	 *
	 * @param tenantId
	 * @param staffId
	 * @return
	 */
	public List<Role> getRoleList(String tenantId, String staffId);
	/**
	 * 根据员工Id查员工信息
	 * @param staffId
	 * @return
	 */
	public Staff getStaff(String staffId);
	/**
	 * 保存修改后新密码
	 * @param changePasswordReq
	 */
	public void savePasswordChange(ChangePasswordReq changePasswordReq);

	/**
	 * 查找施工、报验查看权限
	 * @author flw
	 * @createTime 2017-1-12
	 * @param staffId
	 * @return String
	 */
	public String queryCheckRole();

	/**
	 * 查询职务
	 * @author fuliwei
	 * @createTime 2017-1-21
	 * @param
	 * @return List
	 */
	public List queryPost(String post);


	/**
	 * 查询员工列表信息
	 * @param staffQueryReq
	 * @return
	 */
	public Map<String, Object> queryManageStaffList(StaffQueryReq staffQueryReq);


	/**
	 * 查询员工通知菜单信息列表
	 * @param tenantId
	 * @param staffId
	 * @return
	 */
	public Map<String,Object> getStaffRoleMenuList(String tenantId, String staffId);


	/**
	 * 描述:人员维护修改人员信息
	 * @author liaoyq
	 * @createTime 2018年10月15日
	 * @param staffDto
	 * @return
	 * @throws IOException
	 * @throws SQLException
	 * @throws SerialException
	 */
	public String updateStaff(StaffDto staffDto) throws Exception;

	/**
	 * 查询账号表信息
	 * @param ids
	 * @return
	 */
	public List<Map<String,Object>> getUserByIds (String [] ids);
	
	
	/**
	 * 查询操作人--操作流程设置查询使用
	 * @param request
	 * @param staffQueryReq
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> queryOperateStaff(StaffQueryReq staffQueryReq)throws Exception;

    boolean isExistStaff(String staffId);


    /**
     * 更新设备id
     * @author fuliwei
     * @date 2019/8/27
     * @param
     * @return String
    */
    public  String updateStaffRegiId(String registrationID);


    /**
     * 
     * 注释：清空当前用户的设备ID
     * @author liaoyq
     * @createTime 2019年9月11日
     * @param staffId
     * @param registrationId
     *
     */
	public void clearCurStaffRegistrationId(String staffId,
			String registrationId);
}
