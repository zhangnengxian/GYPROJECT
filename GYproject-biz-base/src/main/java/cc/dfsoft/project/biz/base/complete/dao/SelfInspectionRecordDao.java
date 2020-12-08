package cc.dfsoft.project.biz.base.complete.dao;

import java.util.List;

import cc.dfsoft.project.biz.base.complete.entity.SelfInspectionRecord;
import cc.dfsoft.uexpress.common.dao.CommonDao;
/**
 * 
 * @author Yuanyx
 *
 */
public interface SelfInspectionRecordDao extends CommonDao<SelfInspectionRecord, String>{

	/**
	 * 根据工程Id查质量自检记录
	 * @param projId
	 * @return
	 */
	List<SelfInspectionRecord> findQuqlityByProjId(String projId);

	/**
	 * 根据工程Id查资料自检记录
	 * @param projId
	 * @return
	 */
	List<SelfInspectionRecord> findMaterialByProjId(String projId);

	/**
	 * 
	 * @param silId
	 */
	void deleteObjects(String silId);
	
	
	/**
	 * 根据proj_id,sir_type查询自检单
	 * 
	 */
	List<SelfInspectionRecord> findQuqlityByProjIdType(String projId,String sirType);
	
}
