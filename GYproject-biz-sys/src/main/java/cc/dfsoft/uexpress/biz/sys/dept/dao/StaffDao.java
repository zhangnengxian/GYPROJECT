package cc.dfsoft.uexpress.biz.sys.dept.dao;

import cc.dfsoft.uexpress.biz.sys.dept.dto.StaffDto;
import cc.dfsoft.uexpress.biz.sys.dept.dto.StaffQueryReq;
import cc.dfsoft.uexpress.biz.sys.dept.entity.Staff;
import cc.dfsoft.uexpress.common.dao.CommonDao;

import java.util.List;
import java.util.Map;

/**
 * 员工处理DAO
 * @author 1919
 *
 */
public interface StaffDao extends CommonDao<Staff, String>{

	/**
	 * 新增员工信息
	 * @param staff
	 */
	public void insertStaff(Staff staff);

	/**
	 * 删除员工信息
	 * @param tenantId
	 * @param staffId
	 */
	public boolean deleteStaff(String tenantId, String staffId);

	/**
	 * 更新员工信息
	 * @param staff
	 */
	public void updateStaff(Staff staff);

	/**
	 * 根据员工Id查询员工信息
	 * @param tenantId
	 * @param staffId
	 * @return
	 */
	public Staff queryStaff(String tenantId, String staffId);

	/**
	 * 查询员工信息
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
	 * 根据条件查询该员工登录信息
	 * @param tenantId
	 * @param loginAccount
	 * @param password
	 * @return
	 */
	public Staff queryStaffLoginInfo(String tenantId, String loginAccount);

	/**
	 * 查询部门所有员工
	 * @param tenantId
	 * @param deptId
	 * @return
	 */
	public List<Staff> queryStaffByDeptId(String tenantId, String deptId);

	/**
	 * 更新管理员密码
	 * @param tenantId
	 * @param loginAccount
	 * @param password
	 */
	public void updatePassword(String tenantId, String loginAccount, String password);

	/**
	 * 按员工编号查询
	 * @param staffNo
	 * @return
	 */
	public List<Staff> findByStaffNo(String staffNo);

	/**
	 * 按角色查询员工
	 * @author
	 * @createTime 2016-12-18
	 * @param roleId
	 * @return Staff
	 */
	public List findByRoleId(String roleId);

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
	 * 按登录账号
	 * @param loginAccount
	 * @return
	 */
	public List<Staff> findByLoginAccount(String loginAccount);


	public List<Map<String, Object>> getUserByIds(String[] ids);

    List<Staff> findStaffListByBelongCorpIdAndPost(String corpId,String suId,String cuId);
	/**
	 * 查询操作人--操作流程设置查询使用
	 * @param request
	 * @param staffQueryReq
	 * @return
	 * @throws Exception
	 */
    public Map<String, Object> queryOperateStaff(StaffQueryReq staffQueryReq) throws Exception;

    boolean isExistStaff(String staffId);

	StaffDto obtainOperater(String staffCorpId,String roleCorpId, String projectType, String contributionMode, String backStepId, int i);

    List<Staff> queryStaffListByIds(String[] staffIds);


	/**
	 * 获得员工所有的菜单id
	 * @author fuliwei
	 * @date 2019/9/2
	 * @param
	 * @return
	 */
	public List<Map<String, Object>> getStaffMenuIdList(String staffId);

	/**
	 * 
	 * 注释：清空非当前登录人员的该设备号
	 * @author liaoyq
	 * @createTime 2019年9月11日
	 * @param staffId
	 * @param registrationID
	 *
	 */
	public void clearRegistrationID(String staffId, String registrationID);

	/**
	 * 
	 * 注释：清空当前用户的设备ID
	 * @author liaoyq
	 * @createTime 2019年9月11日
	 * @param staffId
	 * @param registrationId
	 *
	 */
	public void clearCurStaffRegistrationId(String staffId,String registrationId);
}
