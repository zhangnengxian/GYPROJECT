package cc.dfsoft.project.biz.base.inspection.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cc.dfsoft.project.biz.base.inspection.dao.ClearRecordDao;
import cc.dfsoft.project.biz.base.inspection.dao.ProjectChecklistDao;
import cc.dfsoft.project.biz.base.inspection.dto.ClearRecordReq;
import cc.dfsoft.project.biz.base.inspection.dto.ProjectCheckListCRReq;
import cc.dfsoft.project.biz.base.inspection.entity.ClearRecord;
import cc.dfsoft.project.biz.base.inspection.entity.ProjectChecklist;
import cc.dfsoft.project.biz.base.inspection.service.ClearRecordService;
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
 * 清扫记录业务逻辑实现层
 * @author liaoyq
 * @createTime 2017年7月25日
 */
@Service
@Transactional(readOnly = true,propagation=Propagation.REQUIRED)
public class ClearRecordServiceImpl implements ClearRecordService {

	@Resource
	ClearRecordDao clearRecordDao;
	
	@Resource
	AccessoryService accessoryService;
	
	@Resource
	AccessoryDao accessoryDao;
	
	@Resource
	SignatureService signatureService;
	
	/**报验单*/
	@Resource
	ProjectChecklistDao projectCheckListDao;

	/**签字通知服务*/
	@Resource
	SignNoticeService signNoticeService;

	
	/**
	 * 根据清扫记录ID回写报验单ID
	 * @author liaoyq
	 * @createTime 2017年7月25日
	 * @param recordsId 清扫记录ID组合
	 * @param pcId 报验单ID
	 * @param projId 工程ID
	 * @param projNo 工程编号
	 */
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void updatePcIdByRecordsId(String recordsId, String pcId,String projId,String projNo) {
		if(StringUtils.isNotBlank(recordsId)){
			//先置空之前报验的pcId
			clearRecordDao.updateByPcId(pcId);
			//质空附件表中的报验单ID
			accessoryService.clearBRId(projId,projNo,pcId);
			String [] ids = recordsId.split(",");
			//根据记录ID回写pcId
			for(String id : ids){
				ClearRecord record = clearRecordDao.get(id);
				if(record!=null){
					clearRecordDao.updatePcIdByRecordId(id,pcId);
					//回写拍照pcId
					accessoryDao.updateBId(pcId,record.getProjId(),id,AccessorySourceEnum.PHOTO_FILE.getValue());
				}
			}
		}
	}

	/**
	 * 分页查询清扫记录
	 * @author liaoyq
	 * @createTime 2017年7月25日
	 * @param clearRecordReq
	 * @return Map<String, Object>
	 */
	@Override
	public Map<String, Object> queryRecords(ClearRecordReq clearRecordReq) {
		return clearRecordDao.queryRecords(clearRecordReq);
	}

	/**
	 * 保存清扫记录
	 * @author liaoyq
	 * @createTime 2017年7月25日
	 * @param checkListCRReq
	 */
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void saveRecords(ProjectCheckListCRReq checkListCRReq) {
		//已有记录进行删除
		if(StringUtils.isNotBlank(checkListCRReq.getRecordsId())){
			String [] recordId = checkListCRReq.getRecordsId().split(",");
			for(String id : recordId){
				
				clearRecordDao.delete(id);
				//todo:删除附件表中的数据及磁盘上的附件路径
				String path = id;
				accessoryService.delAccessoryListByProjIdAndNo(checkListCRReq.getProjId(),id,path);
			}
		}
		//有记录
		if(checkListCRReq.getList().size()>0){
			//先删除记录
			//deleteRecord(checkListCRReq);
			//批量保存记录
			List<ClearRecord> listNew = new ArrayList<ClearRecord>();
			for(ClearRecord cr : checkListCRReq.getList()){
				//新增加的记录
				if(StringUtils.isBlank(cr.getCrId())){
					cr.setCrId(IDUtil.getUniqueId(Constants.MODULE_CODE_CLEAR_RECORD));
					listNew.add(cr);
				}
			}
			clearRecordDao.batchInsertObjects(listNew);
			
		}
		/*else {
			deleteRecord(checkListCRReq);
		}*/
	}

	private void deleteRecord(ProjectCheckListCRReq checkListCRReq) {
		//已报验过
		if(StringUtils.isNotBlank(checkListCRReq.getPcId())){
			//有pcId,则根据pcId删除记录
			clearRecordDao.deleteByPcId(checkListCRReq.getPcId());
		}else{
			clearRecordDao.deletePcIdIsNullByProjId(checkListCRReq.getProjId());
		}
	}

	/**
	 * 根据记录ID查询记录详述
	 * @author liaoyq
	 * @createTime 2017年7月25日
	 * @param id 记录ID
	 * @return ClearRecord
	 */
	@Override
	public ClearRecord viewRecordById(String id) {
		return clearRecordDao.get(id);
	}

	/**
	 * 保存记录（一条）
	 * @author liaoyq
	 * @createTime 2017年9月12日
	 */
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public String saveRecord(ClearRecord cr) throws Exception {
		boolean flag = false;
		if(StringUtils.isBlank(cr.getCrId())){
			cr.setCrId(IDUtil.getUniqueId(Constants.MODULE_CODE_CLEAR_RECORD));
			flag = true;
		}
		clearRecordDao.saveOrUpdate(cr);
		
		if(cr.getSign()!=null){
			signatureService.saveOrUpdateSign(cr.getMenuId(),cr.getSign(), cr.getProjId(), cr.getCrId(), cr.getClass().getName(), flag);
		}
		return cr.getCrId();
	}

	/**
	 * 根据记录ID删除记录
	 * @author liaoyq
	 * @createTime 2017年9月12日
	 */
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void deleteRecordById(String crId) {
		clearRecordDao.delete(crId);
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
		//fieldsRepresent现场代表
		//suJgj
		//builder
		//operator班组长
		
		if(StringUtils.isNotBlank(cwId)){
			ProjectChecklist list=projectCheckListDao.get(cwId);
			if(list!=null){
				if(StringUtils.isNotBlank(list.getOperator())){
					//班组长通知置为无效
					signNoticeService.saveThisSignNotice(SignPostEnum.TEST_LEADER.getValue(), SignDataTypeEnum.CLEAR_RECORD.getValue(),
							list.getPcId(), list.getProjId(),signState);
				}
				if(StringUtils.isNotBlank(list.getBuilder())){
					//施工员通知置为无效
					signNoticeService.saveThisSignNotice(SignPostEnum.CONSTRUCTION.getValue(), SignDataTypeEnum.CLEAR_RECORD.getValue(),
							list.getPcId(), list.getProjId(),signState);
				}
				if(StringUtils.isNotBlank(list.getSuJgj())){
					//监理通知置为无效
					signNoticeService.saveThisSignNotice(SignPostEnum.SUJGJ.getValue(), SignDataTypeEnum.CLEAR_RECORD.getValue(),
							list.getPcId(), list.getProjId(),signState);
				}
				if(StringUtils.isNotBlank(list.getFieldsRepresent())){
					//甲方代表通知置为无效
					signNoticeService.saveThisSignNotice(SignPostEnum.BUILDER.getValue(), SignDataTypeEnum.CLEAR_RECORD.getValue(),
							list.getPcId(), list.getProjId(),signState);
				}
			}
		}
	}
	
}
