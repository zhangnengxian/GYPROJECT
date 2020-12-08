package cc.dfsoft.project.biz.base.inspection.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cc.dfsoft.project.biz.base.inspection.dao.AcceptanceRecordDao;
import cc.dfsoft.project.biz.base.inspection.dto.ElectrodeRecordReq;
import cc.dfsoft.project.biz.base.inspection.entity.AcceptanceRecord;
import cc.dfsoft.project.biz.base.inspection.service.AcceptanceRecordService;
import cc.dfsoft.project.biz.base.project.dao.AccessoryDao;
import cc.dfsoft.project.biz.base.project.enums.AccessorySourceEnum;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.util.IDUtil;

@Service
@Transactional(readOnly = true,propagation=Propagation.REQUIRED)
public class AcceptanceRecordServiceImpl implements AcceptanceRecordService{
	@Resource
	AcceptanceRecordDao acceptanceRecordDao;
	
	@Resource
	AccessoryDao accessoryDao;
	@Override
	public Map<String, Object> queryRecords(ElectrodeRecordReq electrodeRecordReq) {
		return acceptanceRecordDao.queryRecords(electrodeRecordReq);
	}
	
	/**
	 * 保存验收登记记录
	 * @author fuliwei  
	 * @date 2018年12月4日  
	 * @version 1.0
	 */
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public String saveRecord(AcceptanceRecord record) {
		boolean flag = false;
		if(StringUtils.isBlank(record.getArId())){
			record.setArId(IDUtil.getUniqueId(Constants.THREADING_PIPE));//验收登记
			flag = true;
		}
		acceptanceRecordDao.saveOrUpdate(record);
		return record.getArId();
	}
	/**
	 * 根据记录ID删验收登记记录
	 * @author fuliwei  
	 * @date 2018年12月4日  
	 * @version 1.0
	 */
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void deleteRecordById(String arId) {
		acceptanceRecordDao.delete(arId);
	}
	
	
	/**
	 * 将报验单id回写到记录表中
	 * @author fuliwei  
	 * @date 2018年12月4日  
	 * @version 1.0
	 */
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void updatePcIdByRecordsId(String recordsId, String pcId, String projId, String projNo) {
		//先置空之前报验的pcId
			acceptanceRecordDao.updateByPcId(pcId);
			//质空附件表中的报验单ID
			String [] ids = recordsId.split(",");
			//
			for(String id : ids){
				AcceptanceRecord record = acceptanceRecordDao.get(id);
				if(record!=null){
					acceptanceRecordDao.updatePcIdByRecordId(id,pcId);
					//回写拍照pcId
					accessoryDao.updateBId(pcId,projId,id,AccessorySourceEnum.PHOTO_FILE.getValue());
				}
			}
		
	}

	@Override
	public AcceptanceRecord viewRecordById(String id) {
		return acceptanceRecordDao.get(id);
	}

}
