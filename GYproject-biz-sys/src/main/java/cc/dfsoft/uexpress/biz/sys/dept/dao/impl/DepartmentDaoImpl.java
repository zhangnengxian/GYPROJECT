package cc.dfsoft.uexpress.biz.sys.dept.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.converter.DepartmentDtoConverter;
import cc.dfsoft.uexpress.biz.sys.dept.dao.DepartmentDao;
import cc.dfsoft.uexpress.biz.sys.dept.dto.DepartmentQueryDto;
import cc.dfsoft.uexpress.biz.sys.dept.dto.DepartmentReq;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.biz.sys.dept.entity.Department;
import cc.dfsoft.uexpress.biz.sys.dept.enums.DeptTypeEnum;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;
import cc.dfsoft.uexpress.common.util.ResultUtil;
import cc.dfsoft.uexpress.common.util.StringUtil;

/**
 * 部门处理DAO实现类
 *
 * @author 1919
 *
 */
@Repository
public class DepartmentDaoImpl extends NewBaseDAO<Department, String> implements DepartmentDao {
	public List<Department> getList(Map<String, String> map) {
		if (null != map) {
			Criteria criteria = super.getCriteria();
			if (StringUtils.isNotBlank(map.get("tenantId"))) {
				criteria.add(Restrictions.eq("tenantId", map.get("tenantId")));
			}
			if (StringUtils.isNotBlank(map.get("deptType"))) {
				criteria.add(Restrictions.eq("deptType", map.get("deptType")));
			}
			if (StringUtils.isNotBlank(map.get("deptId"))) {
				criteria.add(Restrictions.ilike("deptId", map.get("deptId") + "%"));
			}
			if (StringUtils.isNotBlank(map.get("deptName"))) {
				criteria.add(Restrictions.ilike("deptName", "%" + map.get("deptName") + "%"));
			}

			criteria.addOrder(Order.asc("deptId"));

			List<Department> list = this.findByCriteria(criteria);

			return list;
		}
		return null;
	}

	@Override
	public Map<String, Object> queryDepartmentList(DepartmentReq departmentReq) {
		Criteria criteria = super.getCriteria();

		LoginInfo loginInfo=SessionUtil.getLoginInfo();
		criteria.add(Restrictions.like("deptId", "%"+loginInfo.getCorpId()+"%"));

		// 过滤条件
		if (StringUtil.isNotBlank(departmentReq.getDeptType())) {
			criteria.add(Restrictions.eq("deptType", departmentReq.getDeptType()));
		}
		if (StringUtil.isNotBlank(departmentReq.getDeptName())) {
			criteria.add(Restrictions.ilike("deptName", "%" + departmentReq.getDeptName() + "%"));
		}

		// 数据库根据条件过滤记录数
		int filterCount = this.countByCriteria(criteria);

		// 根据条件获取查询信息
		List<Department> departmentList = this.findBySortCriteria(criteria, departmentReq);
		// 返回结果
		return ResultUtil.pageResult( filterCount, departmentReq.getDraw(), DepartmentDtoConverter.convert(departmentList));
	}
	/**
	 * @methodDesc: 查询分公司Map
	 * @author: zhangnx
	 * @date: 18:36 2018/10/4
	 */
	@Override
	public List<Department> findDepartmentList(DepartmentReq departmentReq) {
		Criteria criteria = super.getCriteria();
		// 过滤条件
		if (StringUtil.isNotBlank(departmentReq.getDeptType())) {
			criteria.add(Restrictions.eq("deptType", departmentReq.getDeptType()));
		}
		if (StringUtil.isNotBlank(departmentReq.getDeptPath())){
			criteria.add(Restrictions.like("deptPath",departmentReq.getDeptPath()+"%"));
		}

		// 根据条件获取查询信息
		List<Department> departmentList = this.findBySortCriteria(criteria, departmentReq);
		return departmentList;
	}




	@Override
	public Department queryDepartment(String tenantId, String deptId) {
		//String hql = "from Department where tenantId=? and deptId=?";
		String hql = "from Department where deptId=?";
		Department department = this.findClassByHql(hql, new Object[] {deptId });
		return department;
	}

	@Override
	public void insertDepartment(Department department) {
		this.save(department);
	}

	@Override
	public void updateDepartment(Department department) {
		this.update(department);
	}

	@Override
	public boolean deleteDepartment(String tenantId, String deptId) {
		//String hql = "delete Department where tenantId = ? and deptId like ?";
		String hql = "delete Department where deptId like ?";
		return this.executeHql(hql, new Object[]{deptId+"%"});
	}

	@SuppressWarnings("rawtypes")
	@Override
	public String getMaxDeptId(String tenantId, String parentDeptId, String deptType) {
		Criteria criteria = super.getCriteria();
		criteria.add(Restrictions.eq("deptType", deptType));
		criteria.add(Restrictions.ilike("deptId", parentDeptId + "%"));
		//criteria.add(Restrictions.eq("tenantId", tenantId));
		criteria.setProjection(Projections.max("deptId"));
		List list = this.findByCriteria(criteria);
		if(null == list || list.size() == 0) {
			DeptTypeEnum valueof = DeptTypeEnum.valueof(deptType);
			return "";
		}

		return (String)list.get(0);
	}

	@Override
	public List<Department> getListMh() {
		Criteria criteria = super.getCriteria();
		criteria.add(Restrictions.ilike("deptName","%设计院"));
		List<Department> list = this.findByCriteria(criteria);
		return list;
	}

	@Override
	public Map<String, Object> queryDepartmentListByDesign(DepartmentReq departmentReq) {
		Criteria criteria = super.getCriteria();

		// 过滤条件
		if (StringUtil.isNotBlank(departmentReq.getDeptType())) {
			criteria.add(Restrictions.eq("deptType", departmentReq.getDeptType()));
		}
		if (StringUtil.isNotBlank(departmentReq.getDeptName())) {
			criteria.add(Restrictions.ilike("deptName", "%" + departmentReq.getDeptName() + "%"));
		}
		if(StringUtil.isNotBlank(departmentReq.getAllDesign())){
			criteria.add(Restrictions.like("deptId", departmentReq.getAllDesign()+"%"));
		}

		// 数据库根据条件过滤记录数
		int filterCount = this.countByCriteria(criteria);

		// 根据条件获取查询信息
		List<Department> departmentList = this.findBySortCriteria(criteria, departmentReq);
		// 返回结果
		return ResultUtil.pageResult( filterCount, departmentReq.getDraw(),
				departmentList);
	}

	@Override
	public List<Department> findDepartment() {
		Criteria c = super.getCriteria();
		c.addOrder(Order.asc("deptId"));
		// 根据条件获取查询信息
		return c.list();
	}

	/**
	 * 查询施工单位
	 * @author flw
	 * @createTime 2017-1-16
	 * @param  staffId
	 * @return  Map<String,Object>
	 */
	@Override
	public Map<String, Object> queryManagementOffice(String staffId) {
		StringBuffer sql=new StringBuffer();
		sql.append(" select dept_name name,dept_id id from department where dept_id in(select dept_id from staff where staff_id='").append(staffId).append("')");
		List list=this.findBySql(sql.toString());
		Map map=new HashMap();
		for(int i=0;i<list.size();i++){
			Object [] obj=(Object[]) list.get(0);
			map.put("name", obj[0]);
			map.put("id", obj[1]);
		}
		return map;
	}

	/**
	 * 查询项目经理id
	 * @author flw
	 * @createTime 2017-1-16
	 * @param  staffId
	 * @return  String
	 */
	@Override
	public String queryCuLegalRepresentId(String staffName, String deptId) {
		StringBuffer sql=new StringBuffer();
		sql.append("select staff_id from staff where dept_id='").append(deptId).append("' and staff_name='").append(staffName).append("'");
		List list=this.findBySql(sql.toString());
		for(int i=0;i<list.size();i++){
			String staffId=(String) list.get(i);
			return staffId;
		}
		return null;
	}

	/**
	 * 查询五个施工处
	 * @author fuliwei
	 * @createTime 2017-1-21
	 * @param
	 * @return
	 */
	@Override
	public List queryManagementOffice() {
		StringBuffer sql=new StringBuffer();
		sql.append(" select dept_id deptId,dept_name deptName from department where dept_name in(");
		sql.append("select cp.management_office from construction_plan  cp group by management_office )");
		List<Object[]> list=this.findBySql(sql.toString());
		List<Object> manageOfficeList=new ArrayList<Object>();
		for(int i=0;i<list.size();i++){
			Map map=new HashMap();
			Object [] obj=(Object[]) list.get(i);
			map.put("id", obj[0]);
			map.put("name", obj[1]);
			manageOfficeList.add(map);
		}
		return manageOfficeList;
	}

	/**
	 * 根据业务类型查找部门
	 * @author fuliwei
	 * @createTime 2017年7月20日
	 * @param
	 * @return
	 */
	@Override
	public List<Department> findByBusinessType(String type) {
		Criteria criteria = super.getCriteria();

		LoginInfo login=SessionUtil.getLoginInfo();
		criteria.add(Restrictions.like("deptId", "%"+login.getCorpId()+"%"));

		// 过滤条件
		if (StringUtil.isNotBlank(type)) {
			criteria.add(Restrictions.eq("businessType", type));
		}
		List<Department> list=this.findByCriteria(criteria);
		return list;
	}

	@Override
	public Department queryDepartment(String corpId) {
		Criteria criteria = super.getCriteria();
		//工程id
		if(StringUtils.isNotBlank(corpId)){
			criteria.add(Restrictions.eq("deptId",corpId));
		}
		List<Department> list = this.findByCriteria(criteria);
		if (list !=null && list.size()>0) {
			return list.get(0);
		}
		return null;
	}

	/**
	 * 查询部门
	 * @author fuliwei
	 * @createTime 2017年8月2日
	 * @param
	 * @return
	 */
	@Override
	public List<Department> findByType(DepartmentQueryDto req) {
		Criteria criteria = super.getCriteria();
		//工程id
		if(StringUtils.isNotBlank(req.getBusinessType())){
			criteria.add(Restrictions.eq("businessType",req.getBusinessType()));
		}
		if(StringUtils.isNotBlank(req.getProContructType())){
			criteria.add(Restrictions.eq("proContructType",req.getProContructType()));
		}
		List<Department> list = this.findByCriteria(criteria);
		return list;
	}

	/**
	 * 通过业务划分查询
	 * @author fuliwei
	 * @createTime 2017年9月8日
	 * @param
	 * @return
	 */
	@Override
	public Department queryDepartmentByDivide(String deptDivide,String deptId) {
		Criteria criteria = super.getCriteria();
		//工程id
		if(StringUtils.isNotBlank(deptDivide)){
			criteria.add(Restrictions.eq("deptDivide",deptDivide));
		}
		//部门ID-一人多个部门的情况下，需要根据公司ID
		if(StringUtils.isNotBlank(deptId)){
			StringBuffer sql=new StringBuffer("ORG_ID in (select d.ORG_ID from department d where d.dept_id ='").append(deptId).append("')");
			criteria.add(Restrictions.sqlRestriction(sql.toString()));
		}
		List<Department> list = this.findByCriteria(criteria);
		if (list !=null && list.size()>0) {
			return list.get(0);
		}
		return null;
	}


	@Override
	public List<Department> findDepartmentByType() {
		Criteria criteria = super.getCriteria();
		criteria.add(Restrictions.eq("deptType","2"));
		criteria.add(Restrictions.isNotNull("deptPath"));

		return this.findByCriteria(criteria);
	}

	@Override
	public List<Department> queryDeptList(String[] deptIds) {
		Criteria criteria = super.getCriteria();
		criteria.add(Restrictions.in("deptId",deptIds));
		return this.findByCriteria(criteria);
	}

	@Override
	public List<Department> geDepartmentList(DepartmentReq departmentReq) {
		Criteria criteria = super.getCriteria();

		// 过滤条件
		if (StringUtil.isNotBlank(departmentReq.getDeptType())) {
			criteria.add(Restrictions.eq("deptType", departmentReq.getDeptType()));
		}
		if (StringUtil.isNotBlank(departmentReq.getDeptName())) {
			criteria.add(Restrictions.ilike("deptName", "%" + departmentReq.getDeptName() + "%"));
		}
		if(StringUtil.isNotBlank(departmentReq.getDeptId())){
			criteria.add(Restrictions.like("deptId", departmentReq.getDeptId()+"%"));
		}

		return this.findBySortCriteria(criteria, departmentReq);
	}

	@Override
	public Department findByDeptCode(String deptOutCode) {
		Criteria criteria = super.getCriteria();
		//公司编码
		if(StringUtils.isNotBlank(deptOutCode)){
			criteria.add(Restrictions.eq("deptOutCode",deptOutCode));
		}
		List<Department> list = this.findByCriteria(criteria);
		if(list!=null && list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public Department findAcceptDept(String corpId) {
		Criteria criteria = super.getCriteria();
		//公司ID
		criteria.add(Restrictions.eq("orgId",corpId));
		//默认受理部门
		criteria.add(Restrictions.eq("isAcceptDept","1"));
		List<Department> list = this.findByCriteria(criteria);
		if(list!=null && list.size()>0){
			return list.get(0);
		}
		return null;
	}
}
