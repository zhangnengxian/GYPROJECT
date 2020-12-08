package cc.dfsoft.project.biz.base.constructmanage.dao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cc.dfsoft.project.biz.base.constructmanage.dao.WorkReportDao;
import cc.dfsoft.project.biz.base.constructmanage.entity.WorkReport;
import cc.dfsoft.project.biz.base.project.entity.OperateRecord;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;

@Repository
public class WorkReportDaoImpl extends NewBaseDAO<WorkReport, String> implements WorkReportDao {

	//@SuppressWarnings("unchecked")
	@Override
	public List<WorkReport> findByProjId(String projId,String dataType) {
		Criteria c=this.getCriteria();
		
		if(StringUtils.isNotBlank(projId)){
			c.add(Restrictions.eq("projId", projId));
		}
		if(StringUtils.isNotBlank(dataType)){
			c.add(Restrictions.eq("dataType",dataType));
		}
		List<WorkReport> result=this.findByCriteria(c);
		return result;
		
		}

	@Override
	public List<OperateRecord> getOr(String projId, String StepId,String modifyStatus) {
		StringBuffer sql=new StringBuffer();
		if(StringUtils.isNotBlank(projId)){
			sql.append("from OperateRecord where projId='").append(projId).append("'");
		}
		if(StringUtils.isNotBlank(StepId)){
			sql.append(" and stepId='").append(StepId).append("'");
		}
		if(StringUtils.isNotBlank(modifyStatus)){
			sql.append(" and modifyStatus='").append(modifyStatus).append("'");
		}
		List<OperateRecord> result = super.findByHql(sql.toString());
		return result;
	}
}
