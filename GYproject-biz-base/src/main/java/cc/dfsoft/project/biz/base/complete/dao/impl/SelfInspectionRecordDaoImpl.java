package cc.dfsoft.project.biz.base.complete.dao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cc.dfsoft.project.biz.base.complete.dao.SelfInspectionRecordDao;
import cc.dfsoft.project.biz.base.complete.entity.SelfInspectionRecord;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;

@Repository
public class SelfInspectionRecordDaoImpl extends NewBaseDAO<SelfInspectionRecord, String> implements SelfInspectionRecordDao{

	@Override
	public List<SelfInspectionRecord> findQuqlityByProjId(String projId) {
		Criteria c = super.getCriteria();
		if(StringUtils.isNotBlank(projId)){
			 c.add(Restrictions.eq("projId",projId));
		 }
			c.add(Restrictions.eq("sirType","1"));
		 List<SelfInspectionRecord> selfInspectionRecords = this.findByCriteria(c);
		return selfInspectionRecords;
	}

	@Override
	public List<SelfInspectionRecord> findMaterialByProjId(String projId) {
		Criteria c = super.getCriteria();
		if(StringUtils.isNotBlank(projId)){
			 c.add(Restrictions.eq("projId",projId));
		 }
			c.add(Restrictions.eq("sirType","2"));
		 List<SelfInspectionRecord> selfInspectionRecords = this.findByCriteria(c);
		return selfInspectionRecords;
	}

	@Override
	public void deleteObjects(String silId) {
		Criteria c = super.getCriteria();
		if(StringUtils.isNotBlank(silId)){
			 c.add(Restrictions.eq("silId",silId));
		 }
		 List<SelfInspectionRecord> selfInspectionRecords = this.findByCriteria(c);
		 
		 super.batchDeleteObjects(selfInspectionRecords);
		
	}
	/**
	 * 根据工程id 和sirType 查询自检
	 * @author
	 * @createTime 11-28
	 * @param
	 * @return
	 */
	@Override
	public List<SelfInspectionRecord> findQuqlityByProjIdType(String projId, String sirType) {
		Criteria c = super.getCriteria();
		if(StringUtils.isNotBlank(projId)){
			 c.add(Restrictions.eq("projId",projId));
		}
		if(StringUtils.isNotBlank(sirType)){
			 c.add(Restrictions.eq("sirType",sirType));
		}
		List<SelfInspectionRecord> selfInspectionRecords = this.findByCriteria(c);
		return selfInspectionRecords;
	}

}
