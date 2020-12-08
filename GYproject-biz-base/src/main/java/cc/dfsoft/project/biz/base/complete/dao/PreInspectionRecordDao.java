package cc.dfsoft.project.biz.base.complete.dao;



import java.util.List;

import cc.dfsoft.project.biz.base.complete.entity.PreInspectionRecord;
import cc.dfsoft.uexpress.common.dao.CommonDao;

public interface PreInspectionRecordDao extends CommonDao<PreInspectionRecord, String>{
	
	/**
	 * 根据预验收id批量删除
	 * @author fuliwei
	 * @createTime 2017年8月2日
	 * @param 
	 * @return
	 */
	public void deleteObjects(String piId);
	
	/**
	 * 根据proj_id,sir_type查询验收单
	 * @author fuliwei
	 * @createTime 2017年8月2日
	 * @param 
	 * @return
	 */
	public List<PreInspectionRecord> findQuqlityByProjIdType(String projId,String sirType);
}
