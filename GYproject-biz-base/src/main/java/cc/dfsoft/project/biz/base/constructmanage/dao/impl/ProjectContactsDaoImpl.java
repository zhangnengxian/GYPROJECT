package cc.dfsoft.project.biz.base.constructmanage.dao.impl;

import cc.dfsoft.project.biz.base.constructmanage.dao.ProjectContactsDao;
import cc.dfsoft.project.biz.base.constructmanage.dto.ProjectContactsReq;
import cc.dfsoft.project.biz.base.constructmanage.entity.ProjectContacts;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;
import cc.dfsoft.uexpress.common.util.ResultUtil;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class ProjectContactsDaoImpl extends NewBaseDAO<ProjectContacts, String> implements ProjectContactsDao {

	/**
	 * 工程联系单列表查询
	 * @param projectContactsReq
	 * @return
	 */
	@Override
	public Map<String, Object> queryProjectContacts(ProjectContactsReq projectContactsReq) {
		 Criteria c = super.getCriteria();
		 c.add(Restrictions.eq("projId", projectContactsReq.getProjId()));
		 // 数据库根据条件过滤记录数
		 int filterCount = this.countByCriteria(c);
		// 根据条件获取查询信息
		List<ProjectContacts> projectContactsList = this.findBySortCriteria(c, projectContactsReq);
		// 返回结果
		return ResultUtil.pageResult( filterCount, projectContactsReq.getDraw(),projectContactsList);
	}

}
