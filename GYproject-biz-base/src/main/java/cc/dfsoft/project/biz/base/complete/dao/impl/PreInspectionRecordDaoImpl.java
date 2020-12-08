package cc.dfsoft.project.biz.base.complete.dao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cc.dfsoft.project.biz.base.complete.dao.PreInspectionRecordDao;
import cc.dfsoft.project.biz.base.complete.entity.PreInspectionRecord;
import cc.dfsoft.project.biz.base.complete.entity.SelfInspectionRecord;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;
@Repository
public class PreInspectionRecordDaoImpl extends NewBaseDAO<PreInspectionRecord, String> implements PreInspectionRecordDao {
	
	/**
	 * 批量删除预验收记录
	 */
	@Override
	public void deleteObjects(String piId) {
		Criteria c = super.getCriteria();
		if(StringUtils.isNotBlank(piId)){
			 c.add(Restrictions.eq("piId",piId));
		 }
		 List<PreInspectionRecord> selfInspectionRecords = this.findByCriteria(c);
		 
		 super.batchDeleteObjects(selfInspectionRecords);
		
	}
	
	/**
	 * 根据proj_id,sir_type查询验收单
	 * @author fuliwei
	 * @createTime 2017年8月2日
	 * @param 
	 * @return
	 */
	@Override
	public List<PreInspectionRecord> findQuqlityByProjIdType(String projId, String sirType) {
		Criteria c = super.getCriteria();
		if(StringUtils.isNotBlank(projId)){
			 c.add(Restrictions.eq("projId",projId));
		}
		if(StringUtils.isNotBlank(sirType)){
			 c.add(Restrictions.eq("sirType",sirType));
		}
		List<PreInspectionRecord> preInspectionRecords = this.findByCriteria(c);
		return preInspectionRecords;
	}
}
