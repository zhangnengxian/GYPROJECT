package cc.dfsoft.project.biz.base.accept.dao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cc.dfsoft.project.biz.base.accept.dao.ProjectApplicationDao;
import cc.dfsoft.project.biz.base.accept.entity.ProjectApplication;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;
@Repository
public class ProjectApplicationDaoImpl extends NewBaseDAO<ProjectApplication, String> implements ProjectApplicationDao{
	
	/**
     * 根据工程id获取立项申请单信息
     * @param projId
     * @return
     */
	@Override
	public ProjectApplication queryById(String projId) {
		Criteria c = super.getCriteria();
		 //工程ID
		 if(StringUtils.isNotBlank(projId)){
			 c.add(Restrictions.eq("projId",projId));
			List<ProjectApplication> list=this.findByCriteria(c);
			if(list!=null&&list.size()>0){
				return list.get(0);
			}
		 }
		return null;
	}

}
