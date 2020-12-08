package cc.dfsoft.uexpress.biz.sys.dept.dao.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cc.dfsoft.uexpress.biz.sys.dept.dao.PostDao;
import cc.dfsoft.uexpress.biz.sys.dept.dto.DepartmentReq;
import cc.dfsoft.uexpress.biz.sys.dept.entity.Department;
import cc.dfsoft.uexpress.biz.sys.dept.entity.Post;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;
import cc.dfsoft.uexpress.common.util.ResultUtil;
import cc.dfsoft.uexpress.common.util.StringUtil;

@Repository
public class PostDaoImpl extends NewBaseDAO<Post, String> implements PostDao{
	
	/**
	 * 查找职务
	 * @author fuliwei
	 * @createTime 2017年5月19日
	 * @param 
	 * @return
	 */
	@Override
	public Map<String, Object> getPost(DepartmentReq departmentReq) {
		Criteria criteria = super.getCriteria();

		// 过滤条件
		if (StringUtil.isNotBlank(departmentReq.getDeptType())) {
			criteria.add(Restrictions.eq("id", departmentReq.getDeptType()));
		}
		//职务名称
		if (StringUtil.isNotBlank(departmentReq.getDeptName())) {
			criteria.add(Restrictions.like("postName", "%"+departmentReq.getDeptName()+"%"));
		}
		
		// 数据库根据条件过滤记录数
	     int filterCount = this.countByCriteria(criteria);

		// 根据条件获取查询信息
		List<Post> postList = this.findBySortCriteria(criteria, departmentReq);
		// 返回结果
		return ResultUtil.pageResult( filterCount, departmentReq.getDraw(),
				postList);
	}

}
