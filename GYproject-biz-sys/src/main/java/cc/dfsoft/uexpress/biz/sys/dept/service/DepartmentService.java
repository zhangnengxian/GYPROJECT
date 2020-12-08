package cc.dfsoft.uexpress.biz.sys.dept.service;

import cc.dfsoft.uexpress.biz.sys.dept.dto.DepartmentDto;
import cc.dfsoft.uexpress.biz.sys.dept.dto.DepartmentQueryDto;
import cc.dfsoft.uexpress.biz.sys.dept.dto.DepartmentReq;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.biz.sys.dept.entity.Department;

import java.util.List;
import java.util.Map;

/**
 * 部门服务管理
 * 
 * @author 1919
 *
 */
public interface DepartmentService {

	/**
	 * 获取部门信息
	 * 
	 * @param map
	 * @return
	 */
	public List<Department> getList(Map<String, String> map);
	
	/**
	 * 模糊查询设计院list
	 * @param name
	 * @return
	 */
	public List<Department> getListMh();

	/**
	 * 获取部门树结构
	 * 
	 * @return
	 */
	public List<Map<String, String>> getDeptTreeData(String deptId);

	/**
	 * 查询部门列表信息
	 * 
	 * @param departmentReq
	 * @return
	 */
	public Map<String, Object> queryDepartmentList(DepartmentReq departmentReq);
	
	/**
	 * 查询设计所列表
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
	public DepartmentDto queryDepartment(String tenantId, String deptId);

	/**
	 * 新增或修改部门信息
	 * 
	 * @param departmentDto
	 */
	public boolean addOrUpdateDepartment(DepartmentDto departmentDto);

	/**
	 * 删除部门信息
	 * 
	 * @param tenantId
	 * @param deptId
	 */
	public boolean deleteDepartment(String tenantId, String deptId);

	/**
	 * 部门列表
	 * 
	 * @param deptId
	 */
	public List<Department> queryAllList();
	
	
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
	 * 根据部门类型查找部门
	 * @param value
	 * @return
	 */
	public List<Department> findByType(String value);
	
	
	
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
	 * @createTime 2017年9月8日
	 * @param 
	 * @return
	 */
	public Department queryDepartmentByDivide(String deptDivide, String deptId);
	
	/**
	 * 获取登陆者的部门划分类型
	 * @return
	 */
	public String getLoginDeptDivide();

	/**
	 * @methodDesc: 查询分公司List
	 * @author: zhangnx
	 * @date: 18:26 2018/10/4
	 */
    List<Department> queryListByDeptType(LoginInfo loginInfo);

	List<Department> findDepartmentByType();

    List<Department> geDepartmentList(DepartmentReq departmentReq);
}
