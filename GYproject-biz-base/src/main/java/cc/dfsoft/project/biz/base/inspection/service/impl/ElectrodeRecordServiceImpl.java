package cc.dfsoft.project.biz.base.inspection.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cc.dfsoft.project.biz.base.inspection.dao.ElectrodeRecordDao;
import cc.dfsoft.project.biz.base.inspection.dao.ProjectChecklistDao;
import cc.dfsoft.project.biz.base.inspection.dto.ElectrodeRecordReq;
import cc.dfsoft.project.biz.base.inspection.dto.ProjectCheckListERReq;
import cc.dfsoft.project.biz.base.inspection.entity.ElectrodeRecord;
import cc.dfsoft.project.biz.base.inspection.entity.ProjectChecklist;
import cc.dfsoft.project.biz.base.inspection.service.ElectrodeRecordService;
import cc.dfsoft.project.biz.base.project.dao.AccessoryDao;
import cc.dfsoft.project.biz.base.project.enums.AccessorySourceEnum;
import cc.dfsoft.project.biz.base.project.enums.SignDataTypeEnum;
import cc.dfsoft.project.biz.base.project.enums.SignPostEnum;
import cc.dfsoft.project.biz.base.project.enums.SignStateEnum;
import cc.dfsoft.project.biz.base.project.service.AccessoryService;
import cc.dfsoft.project.biz.base.project.service.SignNoticeService;
import cc.dfsoft.project.biz.base.project.service.SignatureService;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.util.IDUtil;
/**
 * 焊条领用服务实现层
 * @author liaoyq
 *
 */
@Service
@Transactional(readOnly = true,propagation=Propagation.REQUIRED)
public class ElectrodeRecordServiceImpl implements ElectrodeRecordService {

	@Resource
	private ElectrodeRecordDao electrodeRecordDao;
	
	@Resource
	SignatureService signatureService;
	
	@Resource
	AccessoryDao accessoryDao;
	@Resource
	AccessoryService accessoryService;
	
	/**签字通知服务*/
	@Resource
	SignNoticeService signNoticeService;
	/**报验单*/
	@Resource
	ProjectChecklistDao projectCheckListDao;
	
	/**
	 * 保存焊条领用记录(批量增加)
	 * 先删除已有的记录，再插入记录数据
	 * @param request
	 * @param checkListERReq
	 */
	@Override
	@Transactional(readOnly = false,propagation= Propagation.REQUIRED)
	public void saveElectrodeRecords(ProjectCheckListERReq checkListERReq) {
		if(checkListERReq.getList().size()>0){
			//删除所有的焊条领用记录
			electrodeRecordDao.deleteByPcId(checkListERReq.getPcId());
			//批量插入记录数据
			List<ElectrodeRecord> list = checkListERReq.getList();
			List<ElectrodeRecord> listNew = new ArrayList<ElectrodeRecord>();
			for(ElectrodeRecord er : list){
				//生成erId;
				er.setErId(IDUtil.getUniqueId(Constants.MODULE_CODE_ELECTRODE_RECORD));
				listNew.add(er);
			}
			electrodeRecordDao.batchInsertObjects(listNew);
		}else{
			electrodeRecordDao.deleteByPcId(checkListERReq.getPcId());
		}
		
	}

	@Override
	public Map<String, Object> queryEclectrodeRecords(
			ElectrodeRecordReq electrodeRecordReq) {
		return electrodeRecordDao.queryEclectrodeRecords(electrodeRecordReq);
	}

	/**
	 * 根据记录ID回写报验单ID
	 * @author liaoyq
	 * @createTime 2017-7-24
	 * @param recordsId 记录ID组合
	 * @param pcId 报验单ID
	 */
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void updatePcIdByRecordsId(String recordsId, String pcId,String projId,String projNo) {
		//先置空之前报验的pcId
		electrodeRecordDao.updateByPcId(pcId);
		//质空附件表中的报验单ID
		accessoryService.clearBRId(projId,projNo,pcId);
		String [] ids = recordsId.split(",");
		//
		for(String id : ids){
			ElectrodeRecord record = electrodeRecordDao.get(id);
			if(record!=null){
				electrodeRecordDao.updatePcIdByRecordId(id,pcId);
				//回写拍照pcId
				accessoryDao.updateBId(pcId,record.getProjId(),id,AccessorySourceEnum.PHOTO_FILE.getValue());
			}
		}
	}

	/**
	 * 根据记录ID回写报验单ID
	 * @author liaoyq
	 * @createTime 2017-7-24
	 * @param id 记录ID
	 */
	@Override
	public ElectrodeRecord viewRecordById(String id) {
		return electrodeRecordDao.get(id);
	}

	/**
	 * 保存焊条领用记录(一条)
	 * @author liaoyq
	 * @createTime 2017-8-29
	 * @param record
	 * @return 焊条领用记录ID
	 */
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public String saveRecord(ElectrodeRecord record) throws Exception {
		boolean flag = false;
		if(StringUtils.isBlank(record.getErId())){
			record.setErId(IDUtil.getUniqueId(Constants.MODULE_CODE_ELECTRODE_RECORD));//焊条领用记录
			flag = true;
		}
		electrodeRecordDao.saveOrUpdate(record);
		if(record.getSign()!=null){
			signatureService.saveOrUpdateSign(record.getMenuId(),record.getSign(), record.getProjId(), record.getErId(), record.getClass().getName(), flag);
		}
		return record.getErId();
	}

	/**
	 *根据记录ID删除记录
	 * @author liaoyq
	 * @createTime 2017-8-29
	 * @param erId 记录ID
	 */
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void deleteRecordById(String erId) {
		electrodeRecordDao.delete(erId);
	}

	/**
	 * 分页查询焊条领用记录
	 * @author liaoyq
	 * @createTime 2017-7-24
	 * @param preservativeReq 焊条领用记录查询辅助类
	 * @return Map<String, Object> 焊条领用记录以及分页信息
	 */
	@Override
	public Map<String, Object> queryRecords(
			ElectrodeRecordReq electrodeRecordReq) {
		return electrodeRecordDao.queryRecords(electrodeRecordReq);
	}
	
	/**
	 * 保存回调
	 * @author fuliwei
	 * @createTime 2018年1月21日
	 * @param 
	 * @return
	 */
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void saveSignNotice(String cwId) {
		String signState = SignStateEnum.ALREADY_SIGN.getValue();
		if(StringUtils.isNotBlank(cwId)){
			ProjectChecklist list=projectCheckListDao.get(cwId);
			if(list!=null){
				if(StringUtils.isNotBlank(list.getRecorder())){
					//焊工通知置为无效
					signNoticeService.saveThisSignNotice(SignPostEnum.WELDER.getValue(), SignDataTypeEnum.ELECTRODE_RECORD.getValue(),
							list.getPcId(), list.getProjId(),signState);
				}
				if(StringUtils.isNotBlank(list.getReviewer())){
					//施工员通知置为无效
					signNoticeService.saveThisSignNotice(SignPostEnum.CONSTRUCTION.getValue(), SignDataTypeEnum.ELECTRODE_RECORD.getValue(),
							list.getPcId(), list.getProjId(),signState);
				}
			}
		}
		
	}
}
