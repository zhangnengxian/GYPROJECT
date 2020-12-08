package cc.dfsoft.project.biz.base.complete.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cc.dfsoft.project.biz.base.complete.dao.PreInspectionRecordDao;
import cc.dfsoft.project.biz.base.complete.entity.PreInspectionRecord;
import cc.dfsoft.project.biz.base.complete.service.PreInspectionRecordService;

@Service
@Transactional(readOnly = true,propagation=Propagation.REQUIRED)
public class PreInspectionRecordServiceImpl implements PreInspectionRecordService {
	
	/**预验收Dao*/
	@Resource
	PreInspectionRecordDao preInspectionRecordDao;
	
	/**
	 * 查询质量预验收记录详述
	 * @param projId
	 * @return
	 */
	@Override
	public Map<String, String> viewPreInspectionRecordQuqlity(String projId) {
		
		List<PreInspectionRecord> mainPipeRecords =preInspectionRecordDao.findQuqlityByProjIdType(projId,"2");//庭院
		
		List<PreInspectionRecord> mainPipeRecords1 =preInspectionRecordDao.findQuqlityByProjIdType(projId,"2");//户内
		
		List<PreInspectionRecord> mainPipeRecords2 =preInspectionRecordDao.findQuqlityByProjIdType(projId,"2");//调压箱
		
		
		
		return null;
	}
	
	/**
	 * 查询资料预验收记录详述
	 * @param projId
	 * @return
	 */
	@Override
	public Map<String, String> viewSelfInspectionRecordMaterial(String projId) {
		// TODO Auto-generated method stub
		return null;
	}

}
