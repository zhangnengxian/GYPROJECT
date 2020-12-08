package cc.dfsoft.uexpress.biz.sys.dept.service.impl;

import cc.dfsoft.uexpress.biz.sys.dept.common.DeptHelper;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.converter.DepartmentDtoConverter;
import cc.dfsoft.uexpress.biz.sys.dept.dao.DepartmentDao;
import cc.dfsoft.uexpress.biz.sys.dept.dto.DepartmentDto;
import cc.dfsoft.uexpress.biz.sys.dept.dto.DepartmentQueryDto;
import cc.dfsoft.uexpress.biz.sys.dept.dto.DepartmentReq;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.biz.sys.dept.entity.Department;
import cc.dfsoft.uexpress.biz.sys.dept.enums.DeptTypeEnum;
import cc.dfsoft.uexpress.biz.sys.dept.service.DepartmentService;
import cc.dfsoft.uexpress.common.util.IDUtil;
import cc.dfsoft.uexpress.common.util.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
 *
 * @author zhangjingjing
 * @createTime 2016-01-25
 *
 */
@Service
@Transactional(propagation = Propagation.REQUIRED)
public class DepartmentServiceImpl implements DepartmentService {

	/** 部门处理DAO */
	@Resource
	private DepartmentDao departmentDao;

	@Override
	public List<Department> getList(Map<String, String> map) {
		return departmentDao.getList(map);
	}

	@Override
	public List<Map<String, String>> getDeptTreeData(String deptId) {
		List<Map<String, String>> mapList = new ArrayList<Map<String, String>>();

		Map<String, String> paramMap = new HashMap<String, String>();
		//paramMap.put("tenantId", SessionUtil.getTenantId());
		paramMap.put("deptId", deptId);
		List<Department> departmentList = departmentDao.getList(paramMap);

		if (departmentList != null && departmentList.size() > 0) {
			for (Department department : departmentList) {
				Map<String, String> map = new HashMap<String, String>();
				map.put("id", department.getDeptId());
				map.put("parent", DeptHelper.getDeptParentId(deptId, department.getDeptId(), department.getDeptType()));
				map.put("text", department.getDeptName());
				mapList.add(map);
			}
		}

		return mapList;
	}

	@Override
	public Map<String, Object> queryDepartmentList(DepartmentReq departmentReq) {
		return departmentDao.queryDepartmentList(departmentReq);
	}

	@Override
	public DepartmentDto queryDepartment(String tenantId, String deptId) {
		Department department = departmentDao.queryDepartment(tenantId, deptId);
		return DepartmentDtoConverter.convert(department);
	}

	@Override
	public boolean addOrUpdateDepartment(DepartmentDto departmentDto) {
		departmentDto.setTenantId(SessionUtil.getTenantId());
		//部门ID不为空即为更新操作
		if(StringUtil.isNotBlank(departmentDto.getDeptId())){
			Department department = DepartmentDtoConverter.convert(departmentDto);
			departmentDao.update(department);
			return true;
		}

		if (StringUtil.isBlank(departmentDto.getParentDeptId())
				|| StringUtil.isBlank(departmentDto.getParentDeptType())) {
			return false;
		}

		//根据父节点部门类型，得出本次新增部门的类型
		departmentDto.setDeptType(String.valueOf(Integer.valueOf(departmentDto.getParentDeptType()) + 1));
		String deptId = departmentDao.getMaxDeptId(departmentDto.getTenantId(),
				departmentDto.getParentDeptId(), departmentDto.getDeptType());

		if(StringUtil.isNotBlank(deptId)){
			deptId = deptId.substring(departmentDto.getParentDeptId().length(), deptId.length());
			deptId = IDUtil.getDeptId(deptId);
			deptId = departmentDto.getParentDeptId() + deptId; //当deptId大于99时，会出错,
		}else{
			deptId = departmentDto.getParentDeptId() + DeptTypeEnum.valueof(departmentDto.getDeptType()).getInitVal();
		}

		departmentDto.setDeptId(deptId);
		Department department = DepartmentDtoConverter.convert(departmentDto);
		departmentDao.insertDepartment(department);
		return true;
	}

	@Override
	public boolean deleteDepartment(String tenantId, String deptId) {
		return departmentDao.deleteDepartment(tenantId, deptId);
	}

	@Override
	public List<Department> getListMh() {
		return departmentDao.getListMh();
	}

	@Override
	public Map<String, Object> queryDepartmentListByDesign(DepartmentReq departmentReq) {

		Map<String, Object> map=departmentDao.queryDepartmentListByDesign(departmentReq);
		List<Department> list= (List<Department>) map.get("data");
		List<Department> listResult=new ArrayList<Department>();
		if(list!=null && list.size()>0){
			for(int i=0;i<list.size();i++){
				if(!departmentReq.getAllDesign().equals(list.get(i).getDeptId())){
					list.get(i).setDeptTypeName(DeptTypeEnum.SUBCOMPANY.getMessage());
					listResult.add(list.get(i));
				}
			}
		}
		Map<String, Object> mapResult=new HashMap<String, Object>();
		mapResult.put("data", listResult);
		return mapResult;
	}

	@Override
	public List<Department> queryAllList() {
		// TODO Auto-generated method stub
		return departmentDao.findDepartment();
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
		return departmentDao.queryManagementOffice(staffId);
	}

	/**
	 * 查询项目经理id
	 * @author flw
	 * @createTime 2017-1-16
	 * @param
	 * @return  String
	 */
	@Override
	public String queryCuLegalRepresentId(String staffName, String deptId) {
		return departmentDao.queryCuLegalRepresentId(staffName,deptId);
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
		return departmentDao.queryManagementOffice();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Department> findByType(String value) {
		DepartmentReq departmentReq = new DepartmentReq();
		departmentReq.setDeptType(value);
		Map<String, Object>  map = this.queryDepartmentList(departmentReq);
		List<Department> list = (List<Department>) map.get("data");
		return list;
	}

	/**
	 * @methodDesc: 查询分公司List
	 * @author: zhangnx
	 * @date: 18:25 2018/10/4
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Department> queryListByDeptType(LoginInfo loginInfo) {

		DepartmentReq departmentReq=new DepartmentReq(DeptTypeEnum.SUBCOMPANY.getValue());

		boolean isBlank=StringUtil.isNotBlank(loginInfo.getCorpId());

		Department department=null;
		if (isBlank){
			department=departmentDao.get("deptId",loginInfo.getCorpId());
		}

		if (department!=null){
			departmentReq.setDeptPath(department.getDeptPath());//根据deptPath查询下属公司
		}

		List<Department> departmentList = departmentDao.findDepartmentList(departmentReq);

		return isBlank?departmentList:belongCorpList(departmentList,loginInfo.getBelongCorpId());
	}




	public List<Department> belongCorpList(List<Department> departmentList,String belongCorpId){
		List<String> strList=null;
		if (StringUtil.isNotBlank(belongCorpId)){
			strList = Arrays.asList(belongCorpId.split(","));
		}
		List<Department> resultList=new ArrayList<>();
		if (strList != null && departmentList != null) {
			for (String str : strList) {
				for (Department d : departmentList) {
					if (d.getDeptId().equals(str)) {
						resultList.add(d);
					}
				}
			}
		}
		return resultList;
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
		return departmentDao.findByBusinessType(type);
	}

	@Override
	public Department queryDepartment(String corpId) {
		return departmentDao.queryDepartment(corpId);
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
		return departmentDao.findByType(req);
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
		return departmentDao.queryDepartmentByDivide(deptDivide,deptId);
	}

	@Override
	public String getLoginDeptDivide() {
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		Department department = departmentDao.get(loginInfo.getDeptId());
		if(null!=department){
			if(department.getDeptType().equals(DeptTypeEnum.BUSINESS_GROUPS.getValue())){
				Department parDepartment = departmentDao.get(loginInfo.getDeptId().substring(0, 6));
				if(null!=parDepartment){
					return parDepartment.getDeptDivide();
				}
			}else{
				return department.getDeptDivide();
			}
		}
		return null;
	}


	@Override
	public List<Department> findDepartmentByType() {
		return departmentDao.findDepartmentByType();
	}

	@Override
	public List<Department> geDepartmentList(DepartmentReq departmentReq) {
		return departmentDao.geDepartmentList(departmentReq);
	}
}
