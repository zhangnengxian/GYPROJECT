package cc.dfsoft.project.biz.base.inspection.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cc.dfsoft.project.biz.base.inspection.dao.EquipmentInstallDao;
import cc.dfsoft.project.biz.base.inspection.dao.ProjectChecklistDao;
import cc.dfsoft.project.biz.base.inspection.dto.EquipmentInstallReq;
import cc.dfsoft.project.biz.base.inspection.dto.ProjectCheckListEIReq;
import cc.dfsoft.project.biz.base.inspection.entity.EquipmentInstall;
import cc.dfsoft.project.biz.base.inspection.entity.ProjectChecklist;
import cc.dfsoft.project.biz.base.inspection.service.EquipmentInstallService;
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
 * 设备安装记录业务实现层
 * @author liaoyq
 *
 */
@Service
@Transactional(readOnly=true,propagation = Propagation.REQUIRED)
public class EquipmentInstallServiceImpl implements EquipmentInstallService {

	@Resource
	private EquipmentInstallDao equipmentInstallDao;
	
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
	public Map<String, Object> queryEquipmentInstall(
			EquipmentInstallReq equipmentInstallReq) {
		return equipmentInstallDao.queryEquipmentInstall(equipmentInstallReq);
	}

	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void saveEquipmentInstall(ProjectCheckListEIReq checkListEIReq) {
		if(checkListEIReq.getList().size()>0){
			//删除所有的记录
			equipmentInstallDao.deleteByPcId(checkListEIReq.getPcId());
			//批量插入记录数据
			List<EquipmentInstall> list = checkListEIReq.getList();
			List<EquipmentInstall> listNew = new ArrayList<EquipmentInstall>();
			for(EquipmentInstall ei : list){
				//生成brId;
				ei.setEiId(IDUtil.getUniqueId(Constants.MODULE_CODE_EQUIPMENT_INSTALL));
				listNew.add(ei);
			}
			equipmentInstallDao.batchInsertObjects(listNew);
			
		}else{
			equipmentInstallDao.deleteByPcId(checkListEIReq.getPcId());
		}
	}

	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void updatePcIdByRecordsId(String recordsId, String pcId,String projId,String projNo) {
		//先置空之前报验的pcId
		equipmentInstallDao.updateByPcId(pcId);
		//质空附件表中的报验单ID
		accessoryService.clearBRId(projId,projNo,pcId);
		String [] ids = recordsId.split(",");
		//
		for(String id : ids){
			EquipmentInstall record = equipmentInstallDao.get(id);
			if(record!=null){
				equipmentInstallDao.updatePcIdByRecordId(id,pcId);
				//回写拍照pcId
				accessoryDao.updateBId(pcId,record.getProjId(),id,AccessorySourceEnum.PHOTO_FILE.getValue());
			}
		}
	}

	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void saveRecords(ProjectCheckListEIReq checkListReq) {
		//有记录
		if(checkListReq.getList().size()>0){
			//先删除记录
			deleteRecord(checkListReq);
			//批量保存记录
			List<EquipmentInstall> listNew = new ArrayList<EquipmentInstall>();
			for(EquipmentInstall ei : checkListReq.getList()){
				ei.setEiId(IDUtil.getUniqueId(Constants.MODULE_CODE_EQUIPMENT_INSTALL));
				listNew.add(ei);
			}
			equipmentInstallDao.batchInsertObjects(listNew);
			
		}else {
			deleteRecord(checkListReq);
		}
	}
	private void deleteRecord(ProjectCheckListEIReq checkListReq){
		//已报验过
		if(StringUtils.isNotBlank(checkListReq.getPcId())){
			//有pcId,则根据pcId删除记录
			equipmentInstallDao.deletePcIdIsNull(checkListReq.getPcId());
		}else{
			equipmentInstallDao.deletePcIdIsNull();
		}
	}
	@Override
	public Map<String, Object> queryRecords(EquipmentInstallReq req) {
		return equipmentInstallDao.queryRecords(req);
	}

	@Override
	public EquipmentInstall viewRecordById(String id) {
		return equipmentInstallDao.get(id);
	}

	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public String saveRecord(EquipmentInstall ei) {
		if(StringUtils.isBlank(ei.getEiId())){
			ei.setEiId(IDUtil.getUniqueId(Constants.MODULE_CODE_EQUIPMENT_INSTALL));
		}
		equipmentInstallDao.saveOrUpdate(ei);
		return ei.getEiId();
	}

	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void deleteRecordById(String eiId) {
		equipmentInstallDao.delete(eiId);
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
		//fieldsRepresent甲方代表
		//suJgj
		//constructionPrincipal项目经理
		//constructionQc质检员
		//builder施工员
		//safetyOfficer安全员
		
		if(StringUtils.isNotBlank(cwId)){
			ProjectChecklist list=projectCheckListDao.get(cwId);
			if(list!=null){
				if(StringUtils.isNotBlank(list.getBuilder())){
					//施工员通知置为无效
					signNoticeService.saveThisSignNotice(SignPostEnum.CONSTRUCTION.getValue(), SignDataTypeEnum.EQUIPMENTINSTALL.getValue(),
							list.getPcId(), list.getProjId(),signState);
				}
				if(StringUtils.isNotBlank(list.getConstructionPrincipal())){
					//项目经理通知置为无效
					signNoticeService.saveThisSignNotice(SignPostEnum.CU_PM.getValue(), SignDataTypeEnum.EQUIPMENTINSTALL.getValue(),
							list.getPcId(), list.getProjId(),signState);
				}
				if(StringUtils.isNotBlank(list.getConstructionQc())){
					//质检员通知置为无效
					signNoticeService.saveThisSignNotice(SignPostEnum.QUALITATIVE_CHECK_MEMBER.getValue(), SignDataTypeEnum.EQUIPMENTINSTALL.getValue(),
							list.getPcId(), list.getProjId(),signState);
				}
				if(StringUtils.isNotBlank(list.getSafetyOfficer())){
					//安全员通知置为无效
					signNoticeService.saveThisSignNotice(SignPostEnum.SAFTYOFFICER.getValue(), SignDataTypeEnum.EQUIPMENTINSTALL.getValue(),
							list.getPcId(), list.getProjId(),signState);
				}
				if(StringUtils.isNotBlank(list.getSuJgj())){
					//监理通知置为无效
					signNoticeService.saveThisSignNotice(SignPostEnum.SUJGJ.getValue(), SignDataTypeEnum.EQUIPMENTINSTALL.getValue(),
							list.getPcId(), list.getProjId(),signState);
				}
				if(StringUtils.isNotBlank(list.getFieldsRepresent())){
					//现场代表通知置为无效
					signNoticeService.saveThisSignNotice(SignPostEnum.BUILDER.getValue(), SignDataTypeEnum.EQUIPMENTINSTALL.getValue(),
							list.getPcId(), list.getProjId(),signState);
				}
			}	
		}
		
	}

}
