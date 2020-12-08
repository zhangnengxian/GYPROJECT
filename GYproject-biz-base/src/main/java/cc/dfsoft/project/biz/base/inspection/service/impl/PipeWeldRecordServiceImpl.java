package cc.dfsoft.project.biz.base.inspection.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cc.dfsoft.project.biz.base.inspection.dao.PipeWeldRecordDao;
import cc.dfsoft.project.biz.base.inspection.dao.ProjectChecklistDao;
import cc.dfsoft.project.biz.base.inspection.dto.PipeWeldRecordReq;
import cc.dfsoft.project.biz.base.inspection.dto.ProjectCheckListPWRReq;
import cc.dfsoft.project.biz.base.inspection.entity.PipeWeldRecord;
import cc.dfsoft.project.biz.base.inspection.entity.ProjectChecklist;
import cc.dfsoft.project.biz.base.inspection.service.PipeWeldRecordService;
import cc.dfsoft.project.biz.base.project.dao.AccessoryDao;
import cc.dfsoft.project.biz.base.project.enums.AccessorySourceEnum;
import cc.dfsoft.project.biz.base.project.enums.SignDataTypeEnum;
import cc.dfsoft.project.biz.base.project.enums.SignPostEnum;
import cc.dfsoft.project.biz.base.project.enums.SignStateEnum;
import cc.dfsoft.project.biz.base.project.service.AccessoryService;
import cc.dfsoft.project.biz.base.project.service.SignNoticeService;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.util.IDUtil;
/**
 * 焊口记录业务逻辑实现层
 * @author liaoyq
 * @createTime 2017年7月25日
 */
@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class PipeWeldRecordServiceImpl implements PipeWeldRecordService {

	@Resource
	PipeWeldRecordDao pipeWeldRecordDao;
	@Resource
	AccessoryService accessoryService;
	@Resource
	AccessoryDao accessoryDao;
	
	/**报验单*/
	@Resource
	ProjectChecklistDao projectCheckListDao;

	/**签字通知服务*/
	@Resource
	SignNoticeService signNoticeService;

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void updatePcIdByRecordsId(String recordsId, String pcId,String projId,String projNo) {
		if(StringUtils.isNotBlank(recordsId)){
			String [] ids = recordsId.split(",");
			//根据记录ID回写pcId
			for(String id : ids){
				PipeWeldRecord record = pipeWeldRecordDao.get(id);
				if(record!=null){
					pipeWeldRecordDao.updatePcIdByRecordId(id,pcId);
					//回写拍照pcId
					accessoryDao.updateBId(pcId,record.getProjId(),id,AccessorySourceEnum.PHOTO_FILE.getValue());
				}
			}
		}
	}

	@Override
	public Map<String, Object> queryRecords(PipeWeldRecordReq pipeWeldRecordReq) {
		return pipeWeldRecordDao.queryRecords(pipeWeldRecordReq);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void saveRecords(ProjectCheckListPWRReq checkListCRReq) {
		//已有记录进行删除
		if(StringUtils.isNotBlank(checkListCRReq.getRecordsId())){
			String [] recordId = checkListCRReq.getRecordsId().split(",");
			for(String id : recordId){
				
				pipeWeldRecordDao.delete(id);
				//todo:删除附件表中的数据及磁盘上的附件路径
				String path = id;
				accessoryService.delAccessoryListByProjIdAndNo(checkListCRReq.getProjId(),id,path);
			}
		}
		//有保存记录
		if(checkListCRReq.getList().size()>0){
			//先删除记录
			//deleteRecord(checkListCRReq);
			//批量保存x新增记录
			List<PipeWeldRecord> listNew = new ArrayList<PipeWeldRecord>();
			for(PipeWeldRecord pwr : checkListCRReq.getList()){
				//新增加的记录
				if(StringUtils.isBlank(pwr.getPwrId())){
					pwr.setPwrId(IDUtil.getUniqueId(Constants.MODULE_CODE_PIPE_WELD_RECORD));
					listNew.add(pwr);
				}
			}
			pipeWeldRecordDao.batchInsertObjects(listNew);
			
		}
		/*else {
			deleteRecord(checkListCRReq);
		}*/
	}

	private void deleteRecord(ProjectCheckListPWRReq checkListCRReq) {
		//已报验过
		if(StringUtils.isNotBlank(checkListCRReq.getPcId())){
			//有pcId,则根据pcId删除记录
			pipeWeldRecordDao.deletePcIdIsNull(checkListCRReq.getPcId());
		}else{
			//根据工程id删除未报验的记录
			pipeWeldRecordDao.deletePcIdIsNullByProjId(checkListCRReq.getProjId());
		}
	}

	@Override
	public PipeWeldRecord viewRecordById(String id) {
		return pipeWeldRecordDao.get(id);
	}

	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public String saveRecord(PipeWeldRecord pwr) {
		if(StringUtils.isBlank(pwr.getPwrId())){
			pwr.setPwrId(IDUtil.getUniqueId(Constants.MODULE_CODE_PIPE_WELD_RECORD));
		}
		pipeWeldRecordDao.saveOrUpdate(pwr);;
		return pwr.getPwrId();
	}

	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void deleteRecordById(String pwrId) {
		pipeWeldRecordDao.delete(pwrId);
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
		//fieldsRepresent
		//projectLeader
		//builder
		//welder
		
		if(StringUtils.isNotBlank(cwId)){
			ProjectChecklist list=projectCheckListDao.get(cwId);
			if(list!=null){
				if(StringUtils.isNotBlank(list.getWelder())){
					//焊工通知置为无效
					signNoticeService.saveThisSignNotice(SignPostEnum.WELDER.getValue(), SignDataTypeEnum.PIPE_WELD_RECORD.getValue(),
							list.getPcId(), list.getProjId(),signState);
				}
				if(StringUtils.isNotBlank(list.getBuilder())){
					//施工员通知置为无效
					signNoticeService.saveThisSignNotice(SignPostEnum.CONSTRUCTION.getValue(), SignDataTypeEnum.PIPE_WELD_RECORD.getValue(),
							list.getPcId(), list.getProjId(),signState);
				}
				if(StringUtils.isNotBlank(list.getProjectLeader())){
					//项目经理通知置为无效
					signNoticeService.saveThisSignNotice(SignPostEnum.CU_PM.getValue(), SignDataTypeEnum.PIPE_WELD_RECORD.getValue(),
							list.getPcId(), list.getProjId(),signState);
				}
				if(StringUtils.isNotBlank(list.getFieldsRepresent())){
					//现场管理员通知置为无效
					signNoticeService.saveThisSignNotice(SignPostEnum.BUILDER.getValue(), SignDataTypeEnum.PIPE_WELD_RECORD.getValue(),
							list.getPcId(), list.getProjId(),signState);
				}
			}
		}
	}
}
