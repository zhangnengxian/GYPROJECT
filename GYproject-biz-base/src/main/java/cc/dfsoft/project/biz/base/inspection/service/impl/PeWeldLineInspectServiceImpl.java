package cc.dfsoft.project.biz.base.inspection.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cc.dfsoft.project.biz.base.inspection.dao.PeWeldLineInspectDao;
import cc.dfsoft.project.biz.base.inspection.dao.ProjectChecklistDao;
import cc.dfsoft.project.biz.base.inspection.dto.PeWeldLineInpectReq;
import cc.dfsoft.project.biz.base.inspection.dto.ProjectCheckListPEWLIReq;
import cc.dfsoft.project.biz.base.inspection.entity.PeWeldLineInspect;
import cc.dfsoft.project.biz.base.inspection.entity.ProjectChecklist;
import cc.dfsoft.project.biz.base.inspection.service.PeWeldLineInspectService;
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
 * pe管焊缝检查业务实现层
 * @author liaoyq
 *
 */
@Service
@Transactional(readOnly = true,propagation=Propagation.REQUIRED)
public class PeWeldLineInspectServiceImpl implements PeWeldLineInspectService {

	@Resource
	private PeWeldLineInspectDao peWeldLineInspectDao;
	@Resource
	AccessoryDao accessoryDao; 
	@Resource 
	AccessoryService accessoryService;
	
	/**报验单*/
	@Resource
	ProjectChecklistDao projectCheckListDao;
	/**签字通知服务*/
	@Resource
	SignNoticeService signNoticeService;
	
	@Override
	public Map<String, Object> queryPeWeldLineInpect(
			PeWeldLineInpectReq peWeldLineInpectReq) {
		return peWeldLineInspectDao.queryPeWeldLineInpect(peWeldLineInpectReq);
	}

	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void savePeWeldLineInpect(ProjectCheckListPEWLIReq checkListPEWLIReq) {
		if(checkListPEWLIReq.getList().size()>0){
			//删除所有记录
			peWeldLineInspectDao.deleteByPcId(checkListPEWLIReq.getPcId());
			//批量插入记录数据
			List<PeWeldLineInspect> list = checkListPEWLIReq.getList();
			List<PeWeldLineInspect> listNew = new ArrayList<PeWeldLineInspect>();
			for(PeWeldLineInspect pewli : list){
				//生成wliId;
				pewli.setPeWliId(IDUtil.getUniqueId(Constants.MODULE_CODE_PE_WELD_LINE_INPECT));
				listNew.add(pewli);
			}
			peWeldLineInspectDao.batchInsertObjects(listNew);
		}else{
			//删除所有记录
			peWeldLineInspectDao.deleteByPcId(checkListPEWLIReq.getPcId());
		}
		
	}

	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void updatePcIdByRecordsId(String recordsId, String pcId,String projId,String projNo) {
		//先置空之前报验的pcId
		peWeldLineInspectDao.updateByPcId(pcId);
		//质空附件表中的报验单ID
		accessoryService.clearBRId(projId,projNo,pcId);
		String [] ids = recordsId.split(",");
		//
		for(String id : ids){
			PeWeldLineInspect record = peWeldLineInspectDao.get(id);
			if(record!=null){
				peWeldLineInspectDao.updatePcIdByRecordId(id,pcId);
				//回写拍照pcId
				accessoryDao.updateBId(pcId,record.getProjId(),id,AccessorySourceEnum.PHOTO_FILE.getValue());
			}
		}
	}

	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void saveRecords(ProjectCheckListPEWLIReq checkListReq) {
		//有记录
		if(checkListReq.getList().size()>0){
			//先删除记录
			deleteRecord(checkListReq);
			//批量保存记录
			List<PeWeldLineInspect> listNew = new ArrayList<PeWeldLineInspect>();
			for(PeWeldLineInspect record : checkListReq.getList()){
				record.setPeWliId(IDUtil.getUniqueId(Constants.MODULE_CODE_PE_WELD_LINE_INPECT));
				listNew.add(record);
			}
			peWeldLineInspectDao.batchInsertObjects(listNew);
			
		}else {
			deleteRecord(checkListReq);
		}
	}

	private void deleteRecord(ProjectCheckListPEWLIReq checkListReq){
		//已报验过
		if(StringUtils.isNotBlank(checkListReq.getPcId())){
			//有pcId,则根据pcId删除记录
			peWeldLineInspectDao.deletePcIdIsNull(checkListReq.getPcId());
		}else{
			peWeldLineInspectDao.deletePcIdIsNull();
		}
	}
	@Override
	public Map<String, Object> queryRecords(PeWeldLineInpectReq req) {
		return peWeldLineInspectDao.queryRecords(req);
	}

	@Override
	public PeWeldLineInspect viewRecordById(String id) {
		return peWeldLineInspectDao.get(id);
	}

	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public String saveRecord(PeWeldLineInspect record) {
		if(StringUtils.isBlank(record.getPeWliId())){
			record.setPeWliId(IDUtil.getUniqueId(Constants.MODULE_CODE_PE_WELD_LINE_INPECT));
		}
		peWeldLineInspectDao.saveOrUpdate(record);
		return record.getPeWliId();
	}

	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void deleteRecordById(String peWliId) {
		peWeldLineInspectDao.delete(peWliId);
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
		//suJgj
		//projectLeader项目经理
		//builder
		//constructionQc
		
		if(StringUtils.isNotBlank(cwId)){
			ProjectChecklist list=projectCheckListDao.get(cwId);
			if(list!=null){
				if(StringUtils.isNotBlank(list.getBuilder())){
					//施工员通知置为无效
					signNoticeService.saveThisSignNotice(SignPostEnum.CONSTRUCTION.getValue(), SignDataTypeEnum.PE_WELDLINE_INSPECT.getValue(),
							list.getPcId(), list.getProjId(),signState);
				}
				if(StringUtils.isNotBlank(list.getProjectLeader())){
					//项目经理通知置为无效
					signNoticeService.saveThisSignNotice(SignPostEnum.CU_PM.getValue(), SignDataTypeEnum.PE_WELDLINE_INSPECT.getValue(),
							list.getPcId(), list.getProjId(),signState);
				}
				if(StringUtils.isNotBlank(list.getConstructionQc())){
					//质检员通知置为无效
					signNoticeService.saveThisSignNotice(SignPostEnum.QUALITATIVE_CHECK_MEMBER.getValue(), SignDataTypeEnum.PE_WELDLINE_INSPECT.getValue(),
							list.getPcId(), list.getProjId(),signState);
				}
				if(StringUtils.isNotBlank(list.getSuJgj())){
					//现场监理通知置为无效
					signNoticeService.saveThisSignNotice(SignPostEnum.SUJGJ.getValue(), SignDataTypeEnum.PE_WELDLINE_INSPECT.getValue(),
							list.getPcId(), list.getProjId(),signState);
				}
				if(StringUtils.isNotBlank(list.getFieldsRepresent())){
					//现场代表通知置为无效
					signNoticeService.saveThisSignNotice(SignPostEnum.BUILDER.getValue(), SignDataTypeEnum.PE_WELDLINE_INSPECT.getValue(),
							list.getPcId(), list.getProjId(),signState);
				}
			}
		}
	}

}
