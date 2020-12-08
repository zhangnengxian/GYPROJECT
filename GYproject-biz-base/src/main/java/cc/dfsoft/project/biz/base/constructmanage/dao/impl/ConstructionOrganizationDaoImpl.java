package cc.dfsoft.project.biz.base.constructmanage.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cc.dfsoft.project.biz.base.constructmanage.dao.ConstructionOrganizationDao;
import cc.dfsoft.project.biz.base.constructmanage.entity.ConstructionOrganization;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;

@Repository
public class ConstructionOrganizationDaoImpl extends NewBaseDAO<ConstructionOrganization,String> implements ConstructionOrganizationDao {


    @Override
    public List<ConstructionOrganization> findbyProjId(String projId) {
        StringBuffer hql = new StringBuffer("from ConstructionOrganization where projId='").append(projId).append("'");
        List<ConstructionOrganization> result = super.findByHql(hql.toString());
        return result;
    }
    /**
	 * 查询施工组织是否已完成
	 * @author fuliwei
	 * @createTime 2017年11月4日
	 * @param 
	 * @return
	 */
	@Override
	public ConstructionOrganization findByProjIdAndState(String projId, String signState) {
		 StringBuffer hql = new StringBuffer("from ConstructionOrganization where projId='").append(projId).append("' and signState='").append(signState).append("'");
		 List<ConstructionOrganization> result = super.findByHql(hql.toString());
		 if(result!=null && result.size()>0){
			 return result.get(0);
		 }
		 return null;
	}
	/**
	 * 查询当前施工员用户的重报施工组织
	 */
	@Override
	public List<ConstructionOrganization> queryCuOrganizationNotice(String staffId) {
		Criteria c = super.getCriteria();
		//施工任务中的施工员
		 if(StringUtils.isNotBlank(staffId)){
			 StringBuffer sql = new StringBuffer();
			 sql.append("proj_id in(select cp.proj_id from CONSTRUCTION_PLAN cp where cp.MANAGEMENT_QAE_ID = '").append(staffId).append("')");
			 c.add(Restrictions.sqlRestriction(sql.toString())); 
		 }
		//重报的
		StringBuffer sql = new StringBuffer();
		sql.append(" (CHECK_RESULT ='2' or CHECK_RESULT ='3') and IFNULL(RE_STATE,'')!='1'");
		c.add(Restrictions.sqlRestriction(sql.toString())); 
		
		 List<ConstructionOrganization> list=this.findByCriteria(c);
		 return list;
	}
	@Override
	public Integer countSignedByProjId(String projId, String signStatus) {
		String sql = "select count(CO_ID) from construction_organization  where PROJ_ID=? and SIGN_STATE=?";
		List<Object> params = new ArrayList<>();
		params.add(projId);
		params.add(signStatus);
		return this.getCountBySql(sql, params.toArray());
	}
}
