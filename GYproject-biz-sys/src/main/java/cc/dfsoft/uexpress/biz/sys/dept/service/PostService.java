package cc.dfsoft.uexpress.biz.sys.dept.service;

import java.util.Map;

import cc.dfsoft.uexpress.biz.sys.dept.dto.DepartmentReq;

public interface PostService {
	
	/**
	 * 查找职务
	 * @author fuliwei
	 * @createTime 2017年5月19日
	 * @param 
	 * @return
	 */
	public Map<String, Object> getPost(DepartmentReq departmentReq);
}
