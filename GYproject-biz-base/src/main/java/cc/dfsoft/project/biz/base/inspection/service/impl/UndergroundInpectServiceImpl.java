package cc.dfsoft.project.biz.base.inspection.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cc.dfsoft.project.biz.base.inspection.dao.ProjectChecklistDao;
import cc.dfsoft.project.biz.base.inspection.dao.UndergroundInpectDao;
import cc.dfsoft.project.biz.base.inspection.dto.ProjectCheckListUIReq;
import cc.dfsoft.project.biz.base.inspection.dto.UndergroundInpectReq;
import cc.dfsoft.project.biz.base.inspection.entity.ProjectChecklist;
import cc.dfsoft.project.biz.base.inspection.entity.UndergroundInpect;
import cc.dfsoft.project.biz.base.inspection.service.UndergroundInpectService;
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
 * 埋地检查业务实现层
 * @author liaoyq
 *
 */
@Service
@Transactional(readOnly=true,propagation=Propagation.REQUIRED)
public class UndergroundInpectServiceImpl implements UndergroundInpectService {

	@Resource
	private UndergroundInpectDao  undergroundInpectDao;
	
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
	public Map<String, Object> queryUndergroundInpect(
			UndergroundInpectReq undergroundInpectReq) {
		return undergroundInpectDao.queryUndergroundInpect(undergroundInpectReq);
	}

	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void saveUndergroundInpect(ProjectCheckListUIReq checkListUIReq) {
		if(checkListUIReq.getList().size()>0){
			//删除已有记录
			undergroundInpectDao.deleteByPcId(checkListUIReq.getPcId());
			//批量插入记录数据
			List<UndergroundInpect> list = checkListUIReq.getList();
			List<UndergroundInpect> listNew = new ArrayList<UndergroundInpect>();
			for(UndergroundInpect ui : list){
				//生成ugiId;
				ui.setUgiId(IDUtil.getUniqueId(Constants.MODULE_CODE_UNDER_GROUND_INPECT));
				listNew.add(ui);
			}
			undergroundInpectDao.batchInsertObjects(listNew);
			
		}else{
			undergroundInpectDao.deleteByPcId(checkListUIReq.getPcId());
		}
	}

	@Override
	public Map<String, Object> queryRecords(
			UndergroundInpectReq undergroundInpectReq) {
		return undergroundInpectDao.queryRecords(undergroundInpectReq);
	}

	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void saveRecords(ProjectCheckListUIReq checkListReq) {
		//有记录
		if(checkListReq.getList().size()>0){
			//先删除记录
			deleteRecord(checkListReq);
			//批量保存记录
			List<UndergroundInpect> listNew = new ArrayList<UndergroundInpect>();
			for(UndergroundInpect ug : checkListReq.getList()){
				ug.setUgiId(IDUtil.getUniqueId(Constants.MODULE_CODE_UNDER_GROUND_INPECT));
				listNew.add(ug);
			}
			undergroundInpectDao.batchInsertObjects(listNew);
			
		}else {
			deleteRecord(checkListReq);
		}
	}
	private void deleteRecord(ProjectCheckListUIReq checkListReq){
		//已报验过
		if(StringUtils.isNotBlank(checkListReq.getPcId())){
			//有pcId,则根据pcId删除记录
			undergroundInpectDao.deletePcIdIsNull(checkListReq.getPcId());
		}else{
			undergroundInpectDao.deletePcIdIsNull();
		}
	}
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public UndergroundInpect viewRecordById(String id) {
		return undergroundInpectDao.get(id);
	}

	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public String saveRecord(UndergroundInpect ug) {
		if(StringUtils.isBlank(ug.getUgiId())){
			ug.setUgiId(IDUtil.getUniqueId(Constants.MODULE_CODE_UNDER_GROUND_INPECT));
		}
		undergroundInpectDao.saveOrUpdate(ug);
		return ug.getUgiId();
	}

	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void deleteRecordById(String ugiId) {
		undergroundInpectDao.delete(ugiId);
	}

	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void updatePcIdByRecordsId(String recordsId, String pcId,String projId,String projNo) {
		//先置空之前报验的pcId
		undergroundInpectDao.updateByPcId(pcId);
		//质空附件表中的报验单ID
		accessoryService.clearBRId(projId,projNo,pcId);
		String [] ids = recordsId.split(",");
		for(String id : ids){
			UndergroundInpect record = undergroundInpectDao.get(id);
			if(record!=null){
				undergroundInpectDao.updatePcIdByRecordId(id,pcId);
				//回写拍照pcId
				accessoryDao.updateBId(pcId,record.getProjId(),id,AccessorySourceEnum.PHOTO_FILE.getValue());
			}
		}
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
		//埋地检查
		//fieldsRepresent 现场代表
		//suJgj 现场监理
		//projectLeader 项目经理
		//builder 施工员
		//constructionQc 质检员
		
		if(StringUtils.isNotBlank(cwId)){
			ProjectChecklist list=projectCheckListDao.get(cwId);
			if(list!=null){
				if(StringUtils.isNotBlank(list.getBuilder())){
					//施工员通知置为无效
					signNoticeService.saveThisSignNotice(SignPostEnum.CONSTRUCTION.getValue(), SignDataTypeEnum.UNDERGROUNDINPECT.getValue(),
							list.getPcId(), list.getProjId(),signState);
				}
				
				if(StringUtils.isNotBlank(list.getConstructionQc())){
					//质检员通知置为无效
					signNoticeService.saveThisSignNotice(SignPostEnum.QUALITATIVE_CHECK_MEMBER.getValue(), SignDataTypeEnum.UNDERGROUNDINPECT.getValue(),
							list.getPcId(), list.getProjId(),signState);
				}
				
				if(StringUtils.isNotBlank(list.getProjectLeader())){
					//项目经理通知置为无效
					signNoticeService.saveThisSignNotice(SignPostEnum.CU_PM.getValue(), SignDataTypeEnum.UNDERGROUNDINPECT.getValue(),
							list.getPcId(), list.getProjId(),signState);
				}
				
				if(StringUtils.isNotBlank(list.getSuJgj())){
					//现场监理通知置为无效
					signNoticeService.saveThisSignNotice(SignPostEnum.SUJGJ.getValue(), SignDataTypeEnum.UNDERGROUNDINPECT.getValue(),
							list.getPcId(), list.getProjId(),signState);
				}
				
				if(StringUtils.isNotBlank(list.getFieldsRepresent())){
					//现场代表通知置为无效
					signNoticeService.saveThisSignNotice(SignPostEnum.BUILDER.getValue(), SignDataTypeEnum.UNDERGROUNDINPECT.getValue(),
							list.getPcId(), list.getProjId(),signState);
				}
			}
		}
	}

}
