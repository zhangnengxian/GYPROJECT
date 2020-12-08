package cc.dfsoft.uexpress.biz.sys.dept.converter;

import cc.dfsoft.uexpress.biz.sys.dept.dto.DepartmentDto;
import cc.dfsoft.uexpress.biz.sys.dept.entity.Department;
import cc.dfsoft.uexpress.biz.sys.dept.enums.DeptTypeEnum;
import cc.dfsoft.uexpress.common.util.StringUtil;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;


/**
 * 数据模型转换器
 * @author 1919
 *
 */
public final class DepartmentDtoConverter {

	/**
     * 禁用构造函数
     */
    private DepartmentDtoConverter() {
        //禁用构造函数
    }
    
    /**
     * 数据模型列表转换为页面模型列表
     * @param departmentList
     * @return
     */
    public static List<DepartmentDto> convert(List<Department> departmentList) {
        if (departmentList == null) {
            return null;
        }

        List<DepartmentDto> departmentDtoList = new ArrayList<DepartmentDto>();
        for (Department department : departmentList) {
        	departmentDtoList.add(convert(department));

        }
        return departmentDtoList;
    }
    
    /**
     * 数据模型转换为页面模型
     * @param department
     * @return
     */
    public static DepartmentDto convert(Department department) {
        if (department == null) {
            return null;
        }
        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setDeptId(department.getDeptId());
        departmentDto.setDeptInnerCode(department.getDeptInnerCode());
        departmentDto.setDeptOutCode(department.getDeptOutCode());
        departmentDto.setDeptName(department.getDeptName());
        departmentDto.setDeptType(department.getDeptType());
		departmentDto.setDeptTypeName(StringUtil.isNotEmpty(department.getDeptType()) ? DeptTypeEnum
						.valueof(department.getDeptType()).getMessage() : "");
        departmentDto.setLocation(department.getLocation());
        departmentDto.setFax(department.getFax());
        departmentDto.setPhone(department.getPhone());
        departmentDto.setPrincipal(department.getPrincipal());
        departmentDto.setBusinessType(department.getBusinessType());
        //departmentDto.setProjContructType(department.getProContructType());
        departmentDto.setTenantId(department.getTenantId());
        departmentDto.setOrgId(department.getOrgId());
        departmentDto.setDeptDivide(department.getDeptDivide());

        departmentDto.setIsAcceptDept(department.getIsAcceptDept());
        return departmentDto;
    }
    
    /**
     * 页面模型转换为数据模型
     * @param departmentDto
     * @return
     */
    public static Department convert(DepartmentDto departmentDto) {
        if (departmentDto == null) {
            return null;
        }
        Department department = new Department();
        department.setDeptId(departmentDto.getDeptId());
        department.setDeptInnerCode(departmentDto.getDeptInnerCode());//部门编码
        department.setDeptOutCode(departmentDto.getDeptId());
        department.setDeptName(departmentDto.getDeptName());
        department.setDeptType(departmentDto.getDeptType());
        department.setLocation(departmentDto.getLocation());
        department.setFax(departmentDto.getFax());
        department.setPhone(departmentDto.getPhone());
        department.setPrincipal(departmentDto.getPrincipal());
        department.setBusinessType(departmentDto.getBusinessType());
        //department.setProContructType(departmentDto.getProjContructType());
        
        if(StringUtils.isBlank(departmentDto.getOrgId())){
        	   if(department.getDeptId().length()>=4){ 
       	    	department.setOrgId(department.getDeptId().substring(0, 4)); //orgId取公司Id
       	    }
//        	department.setOrgId(department.getDeptOutCode());//如果没有设置组织id，默认将OutCode存到部门表
        }
        department.setTenantId(departmentDto.getDeptId());//暂时存为部门id
        department.setDeptDivide(departmentDto.getDeptDivide());

        department.setIsAcceptDept(departmentDto.getIsAcceptDept());
        return department;
    } 
    
}
