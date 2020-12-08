package cc.dfsoft.uexpress.biz.sys.dept.dao;

import java.util.List;
import java.util.Map;

import cc.dfsoft.uexpress.biz.sys.dept.dto.DepartmentQueryDto;
import cc.dfsoft.uexpress.biz.sys.dept.dto.DepartmentReq;
import cc.dfsoft.uexpress.biz.sys.dept.entity.Department;
import cc.dfsoft.uexpress.common.dao.CommonDao;

/**
 * 部门处理DAO
 * 
 * @author 1919
 *
 */
public interface DepartmentDao extends CommonDao<Department, String> {
	
	/**
	 * 根据条件查询部门信息
	 * @param map
	 * @return
	 */
	public List<Department> getList(Map<String, String> map);
	
	/**
	 * 模糊查询设计院LIST
	 * @return
	 */
	public List<Department> getListMh();

	/**
	 * 查询部门列表信息
	 * 
	 * @param departmentReq
	 * @return
	 */
	public Map<String, Object> queryDepartmentList(DepartmentReq departmentReq);
	
	/**
	 * 查询设计所信息
	 * @param departmentReq
	 * @return
	 */
	public Map<String, Object> queryDepartmentListByDesign(DepartmentReq departmentReq);
	
	/**
	 * 查询部门信息
	 * @param tenantId
	 * @param deptId
	 * @return
	 */
	public Department queryDepartment(String tenantId, String deptId);
	
	/**
	 * 新增部门
	 * @param department
	 */
	public void insertDepartment(Department department);
	
	/**
	 * 更新部门信息
	 * @param department
	 */
	public void updateDepartment(Department department);
	
	/**
	 * 删除部门
	 * @param tenantId
	 * @param deptId
	 */
	public boolean deleteDepartment(String tenantId, String deptId);
	
	/**
	 * 得到该部门所属ID的最大值
	 * @param tenantId
	 * @param parentDeptId
	 * @param deptType
	 * @return
	 */
	public String getMaxDeptId(String tenantId, String parentDeptId, String deptType);

	/**
	 * 部门列表
	 * @return
	 */
	public List<Department> findDepartment();
	
	/**
	 * 查询施工单位
	 * @author flw
	 * @createTime 2017-1-16
	 * @param  staffId
	 * @return  Map<String,Object>
	 */
	public Map<String,Object> queryManagementOffice(String staffId);
	
	/**
	 * 查询项目经理id
	 * @author flw
	 * @createTime 2017-1-16
	 * @param  staffId
	 * @return  String
	 */
	public String queryCuLegalRepresentId(String staffName,String deptId);
	
	/**
	 * 查询五个施工处
	 * @author fuliwei
	 * @createTime 2017-1-21
	 * @param 
	 * @return
	 */
	public List queryManagementOffice();
	
	/**
	 * 根据业务类型查找部门
	 * @author fuliwei
	 * @createTime 2017年7月20日
	 * @param 
	 * @return
	 */
	public List<Department> findByBusinessType(String type);

	/**
	 * 根据部门id查部门
	 * @param corpId
	 * @return
	 */
	public Department queryDepartment(String corpId);
	
	
	/**
	 * 查询部门
	 * @author fuliwei
	 * @createTime 2017年8月2日
	 * @param 
	 * @return
	 */
	public List<Department> findByType(DepartmentQueryDto req);
	
	/**
	 * 通过业务划分查询
	 * @author fuliwei
	 * @param string 
	 * @createTime 2017年9月8日
	 * @param 
	 * @return
	 */
	public Department queryDepartmentByDivide(String deptDivide, String deptId);

	/**
	 * @methodDesc: 查询分公司Map
	 * @author: zhangnx
	 * @date: 18:35 2018/10/4
	 */
    List<Department> findDepartmentList(DepartmentReq departmentReq);

    List<Department> findDepartmentByType();

    List<Department> queryDeptList(String[] deptIds);

    List<Department> geDepartmentList(DepartmentReq departmentReq);

    /**
     * 注释：根据部门编码查询部门信息
     * @author liaoyq
     * @createTime 2019年11月20日
     * @param deptOutCode
     * @return
     *
     */
	public Department findByDeptCode(String deptOutCode);

	/**
	 * 获取默认受理部门信息
	 * 注释：
	 * @author liaoyq
	 * @createTime 2019年11月20日
	 * @param corpId
	 * @return
	 *
	 */
	public Department findAcceptDept(String corpId);
}
