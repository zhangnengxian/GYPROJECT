package cc.dfsoft.uexpress.biz.sys.dept.common;

import cc.dfsoft.uexpress.biz.sys.dept.enums.DeptTypeEnum;
import cc.dfsoft.uexpress.common.util.StringUtil;

/**
 * 部门处理公用类
 * @author 1919
 *
 */
public class DeptHelper {
	
	/**
	 * 得到部门父节点
	 * @param deptId
	 * @param deptType
	 * @return
	 */
	public static String getDeptParentId(String deptId, String deptType){
		return getDeptParentId(null, deptId, deptType);
	}

	/**
	 * 得到部门父节点
	 * @param parentDeptId
	 * @param deptId
	 * @param deptType
	 * @return
	 */
	public static String getDeptParentId(String parentDeptId, String deptId, String deptType){
		String parentId = "";
		//TODO
		//集团父节点
		if (deptType.equals(DeptTypeEnum.GROUP_COMPANY.getValue())
				|| (StringUtil.isNotEmpty(parentDeptId) && deptId.equals(parentDeptId))) {
			parentId = "#";
			return parentId;
		}
		/*//成员公司
		if (deptType.equals(DeptTypeEnum.COMPANY.getValue())) {
			parentId = deptId.substring(0, deptId.length() - DeptTypeEnum.COMPANY.getInitVal().length());
		}*/
		//分公司
		if (deptType.equals(DeptTypeEnum.SUBCOMPANY.getValue())) {
			parentId = deptId.substring(0, deptId.length() - DeptTypeEnum.SUBCOMPANY.getInitVal().length());
		}
		//管理处
		if (deptType.equals(DeptTypeEnum.MANAGE_DEPT.getValue())) {
			parentId = deptId.substring(0, deptId.length() - DeptTypeEnum.MANAGE_DEPT.getInitVal().length());
		}
		
		if (deptType.equals(DeptTypeEnum.BUSINESS_GROUPS.getValue())) {
			parentId = deptId.substring(0, deptId.length() - DeptTypeEnum.BUSINESS_GROUPS.getInitVal().length());
		}
		
		return parentId;
	}
}
