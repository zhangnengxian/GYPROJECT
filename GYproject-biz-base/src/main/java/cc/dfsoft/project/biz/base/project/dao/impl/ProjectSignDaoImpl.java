package cc.dfsoft.project.biz.base.project.dao.impl;

import cc.dfsoft.project.biz.base.project.dao.ProjectSignDao;
import cc.dfsoft.project.biz.base.project.entity.ProjectSign;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 工程标记信息impl
 * @author cui
 *
 */
@Repository
public class ProjectSignDaoImpl extends NewBaseDAO<ProjectSign, String> implements ProjectSignDao {

    @Override
    public ProjectSign findOnly(String projId, String signType) {
        Criteria c = super.getCriteria();
        //工程id
        if(StringUtils.isNotBlank(projId)){
            c.add(Restrictions.eq("projId",projId));
        }

        //类型
        if(StringUtils.isNotBlank(signType)){
            c.add(Restrictions.eq("signType",signType));
        }
        // 根据条件获取查询信息
        List<ProjectSign> projectSigns = this.findByCriteria(c);
        if(projectSigns!=null&&projectSigns.size()>0){
            return projectSigns.get(0);
        }
        return null;
    }
    
    /**
     *查询是否存在标记
     * @author fuliwei
     * @createTime 2017年12月28日
     * @param 
     * @return
     */
	@Override
	public List<ProjectSign> findByProjIdAndStatus(String projId, String status) {
		 Criteria c = super.getCriteria();
        //工程id
        if(StringUtils.isNotBlank(projId)){
            c.add(Restrictions.eq("projId",projId));
        }

        //类型
        if(StringUtils.isNotBlank(status)){
            c.add(Restrictions.eq("status",status));
        }
        // 根据条件获取查询信息
        List<ProjectSign> projectSigns = this.findByCriteria(c);
        
        return projectSigns;
	}
	
	@Override
	public List<ProjectSign> findByProjIdAndStatus(String projId, String status,List<String> signTypes) {
		 Criteria c = super.getCriteria();
        //工程id
        if(StringUtils.isNotBlank(projId)){
            c.add(Restrictions.eq("projId",projId));
        }

        //类型
        if(StringUtils.isNotBlank(status)){
            c.add(Restrictions.eq("status",status));
        }
        //状态
        if(signTypes!=null&&signTypes.size()>0){
        	StringBuffer sql = new StringBuffer(" SIGN_TYPE in ('");
        	 for(int i=1;i<signTypes.size();i++){
        		 sql.append(signTypes.get(i)).append("',").append("'");
			 }
        	 sql.append(signTypes.get(0)).append("')");
        	c.add(Restrictions.sqlRestriction(sql.toString()));
        }
        // 根据条件获取查询信息
        List<ProjectSign> projectSigns = this.findByCriteria(c);
        
        return projectSigns;
	}

    @Override
    public List<ProjectSign> findListByStatus(String status) {
        Criteria c = super.getCriteria();
        if (StringUtils.isNotBlank(status)) {
            c.add(Restrictions.eq("status", status));
        }
        return c.list();
    }
}
