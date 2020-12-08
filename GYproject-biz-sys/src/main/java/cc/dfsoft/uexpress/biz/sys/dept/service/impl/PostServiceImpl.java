package cc.dfsoft.uexpress.biz.sys.dept.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cc.dfsoft.uexpress.biz.sys.dept.dao.PostDao;
import cc.dfsoft.uexpress.biz.sys.dept.dto.DepartmentReq;
import cc.dfsoft.uexpress.biz.sys.dept.service.PostService;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class PostServiceImpl implements PostService {
	
	/**职务Dao*/
	@Resource
	PostDao postDao;
	

	@Override
	public Map<String, Object> getPost(DepartmentReq departmentReq) {
		return postDao.getPost(departmentReq);
	}

}
