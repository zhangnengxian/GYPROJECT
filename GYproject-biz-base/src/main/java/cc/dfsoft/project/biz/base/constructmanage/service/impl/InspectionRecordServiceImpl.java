package cc.dfsoft.project.biz.base.constructmanage.service.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cc.dfsoft.project.biz.base.constructmanage.dao.InspectionRecordDao;
import cc.dfsoft.project.biz.base.constructmanage.dto.InspectionRecordQueryReq;
import cc.dfsoft.project.biz.base.constructmanage.entity.InspectionRecord;
import cc.dfsoft.project.biz.base.constructmanage.service.InspectionRecordService;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;
import cc.dfsoft.uexpress.common.util.IDUtil;
@Service
@Transactional(readOnly = true,propagation=Propagation.REQUIRED)
public class InspectionRecordServiceImpl extends NewBaseDAO<InspectionRecord, String> implements InspectionRecordService {
	
	/** 安全质量检查Dao*/
	@Resource
	InspectionRecordDao inspectionRecordDao;
	@Override
	public Map<String, Object> queryInspectionRecord(InspectionRecordQueryReq inspectionRecordQueryReq) throws Exception {
		/**
		 * 安全质量检查记录条件查询
		 */
		return inspectionRecordDao.queryInspectionRecord(inspectionRecordQueryReq);
	}
	@Override
	public InspectionRecord viewInspectionRecordResult(String id) {
		InspectionRecord inspectionRecord = inspectionRecordDao.get(id);
		return inspectionRecord;
	}
	
	@Override
	@Transactional(readOnly = true,propagation=Propagation.REQUIRED)
	public void saveInfringeRecord(List<InspectionRecord> list) {
		List<InspectionRecord> listNew=new ArrayList<InspectionRecord>();
		for(InspectionRecord ir:list){
			ir.setIrId(IDUtil.getUniqueId(Constants.MODULE_CODE_PROJECTSTART));
			listNew.add(ir);
		}
		inspectionRecordDao.batchInsertObjects(listNew);
	}
	@Override
	public void reSaveInspctionRecords(Map<String, Object> mapIns) {
		inspectionRecordDao.deleByIlId((String)mapIns.get("ilId"));
		List<InspectionRecord> inspectionRecords = (List<InspectionRecord>) mapIns.get("insList");
		
		for(InspectionRecord ins : inspectionRecords){
			ins.setIlId((String)mapIns.get("ilId"));
			ins.setIrId(IDUtil.getUniqueId(Constants.MODULE_CODE_PURGE));
		}
		inspectionRecordDao.batchInsertObjects(inspectionRecords);
	}
	@Override
	public List<Map<String, Object>> inspectionTop() {
		List<Map<String, Object>> inspectionTop = inspectionRecordDao.queryInspectionRecordTop();
		return inspectionTop;
	}

}

