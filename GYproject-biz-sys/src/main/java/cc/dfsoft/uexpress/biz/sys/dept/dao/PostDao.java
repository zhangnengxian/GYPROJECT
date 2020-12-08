package cc.dfsoft.uexpress.biz.sys.dept.dao;

import java.util.List;
import java.util.Map;

import cc.dfsoft.uexpress.biz.sys.dept.dto.DepartmentReq;
import cc.dfsoft.uexpress.biz.sys.dept.entity.Post;
import cc.dfsoft.uexpress.common.dao.CommonDao;
/**
 * 职务
 * @author fuliwei
 *
 */
public interface PostDao extends CommonDao<Post, String> {
	/**
	 * 查找职务
	 * @author fuliwei
	 * @createTime 2017年5月19日
	 * @param 
	 * @return
	 */
	public Map<String, Object> getPost(DepartmentReq departmentReq);
}
