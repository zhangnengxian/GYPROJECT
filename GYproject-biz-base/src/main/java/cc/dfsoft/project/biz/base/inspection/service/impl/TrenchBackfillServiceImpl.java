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
import cc.dfsoft.project.biz.base.inspection.dao.TrenchBackfillDao;
import cc.dfsoft.project.biz.base.inspection.dto.ProjectCheckListTBReq;
import cc.dfsoft.project.biz.base.inspection.dto.TrenchBackfillReq;
import cc.dfsoft.project.biz.base.inspection.entity.ProjectChecklist;
import cc.dfsoft.project.biz.base.inspection.entity.TrenchBackfill;
import cc.dfsoft.project.biz.base.inspection.service.TrenchBackfillService;
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
 * 沟槽回填业务实现层
 * @author liaoyq
 * @createTime 2017年7月24日
 */
@Service
@Transactional(readOnly = true,propagation=Propagation.REQUIRED)
public class TrenchBackfillServiceImpl implements TrenchBackfillService {
	
	@Resource
	TrenchBackfillDao trenchBackfillDao;
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
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void saveTrenchBackfill(ProjectCheckListTBReq checkListTBReq) {
		if(checkListTBReq.getList().size()>0){
			//删除所有的记录
			trenchBackfillDao.deleteByPcId(checkListTBReq.getPcId());
			//批量插入记录数据
			List<TrenchBackfill> list = checkListTBReq.getList();
			List<TrenchBackfill> listNew = new ArrayList<TrenchBackfill>();
			for(TrenchBackfill tb : list){
				//生成tbId;
				tb.setTbId(IDUtil.getUniqueId(Constants.MODULE_CODE_TRENCH_BACK_FILL));
				listNew.add(tb);
			}
			trenchBackfillDao.batchInsertObjects(listNew);
		}else{
			trenchBackfillDao.deleteByPcId(checkListTBReq.getPcId());
		}
	}

	@Override
	public Map<String, Object> queryRecords(
			TrenchBackfillReq trenchBackfillReq) {
		return trenchBackfillDao.queryRecords(trenchBackfillReq);
	}

	
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void saveRecords(ProjectCheckListTBReq checkListTBReq) {
		//有记录
		if(checkListTBReq.getList().size()>0){
			//先删除记录
			deleteRecord(checkListTBReq);
			//批量保存记录
			List<TrenchBackfill> listNew = new ArrayList<TrenchBackfill>();
			for(TrenchBackfill tb : checkListTBReq.getList()){
				tb.setTbId(IDUtil.getUniqueId(Constants.MODULE_CODE_TRENCH_BACK_FILL));
				listNew.add(tb);
			}
			trenchBackfillDao.batchInsertObjects(listNew);
			
		}else {
			deleteRecord(checkListTBReq);
		}
	}
	
	private void deleteRecord(ProjectCheckListTBReq checkListTBReq){
		//已报验过
		if(StringUtils.isNotBlank(checkListTBReq.getPcId())){
			//有pcId,则根据pcId删除记录
			trenchBackfillDao.deletePcIdIsNull(checkListTBReq.getPcId());
		}else{
			trenchBackfillDao.deletePcIdIsNull();
		}
	}

	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void updatePcIdByRecordsId(String recordsId,String pcId,String projId,String projNo) {
		//先置空之前报验的pcId
		trenchBackfillDao.updateByPcId(pcId);
		//质空附件表中的报验单ID
		accessoryService.clearBRId(projId,projNo,pcId);
		String [] ids = recordsId.split(",");
		//
		for(String id : ids){
			TrenchBackfill record = trenchBackfillDao.get(id);
			if(record!=null){
				trenchBackfillDao.updatePcIdByRecordId(id,pcId);
				//回写拍照pcId
				accessoryDao.updateBId(pcId,record.getProjId(),id,AccessorySourceEnum.PHOTO_FILE.getValue());
			}
		}
	}

	@Override
	public TrenchBackfill viewRecordById(String id) {
		return trenchBackfillDao.get(id);
	}

	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public String saveRecord(TrenchBackfill tb) {
		if(StringUtils.isBlank(tb.getTbId())){
			tb.setTbId(IDUtil.getUniqueId(Constants.MODULE_CODE_TRENCH_BACK_FILL));
		}
		trenchBackfillDao.saveOrUpdate(tb);
		return tb.getTbId();
	}

	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void deleteRecordById(String tbId) {
		trenchBackfillDao.delete(tbId);
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
					signNoticeService.saveThisSignNotice(SignPostEnum.CONSTRUCTION.getValue(), SignDataTypeEnum.TRENCH_BACKFILL.getValue(),
							list.getPcId(), list.getProjId(),signState);
				}
				if(StringUtils.isNotBlank(list.getConstructionQc())){
					//质检员通知置为无效
					signNoticeService.saveThisSignNotice(SignPostEnum.QUALITATIVE_CHECK_MEMBER.getValue(), SignDataTypeEnum.TRENCH_BACKFILL.getValue(),
							list.getPcId(), list.getProjId(),signState);
				}
				if(StringUtils.isNotBlank(list.getProjectLeader())){
					//项目经理通知置为无效
					signNoticeService.saveThisSignNotice(SignPostEnum.CU_PM.getValue(), SignDataTypeEnum.TRENCH_BACKFILL.getValue(),
							list.getPcId(), list.getProjId(),signState);
				}
				if(StringUtils.isNotBlank(list.getSuJgj())){
					//现场监理通知置为无效
					signNoticeService.saveThisSignNotice(SignPostEnum.SUJGJ.getValue(), SignDataTypeEnum.TRENCH_BACKFILL.getValue(),
							list.getPcId(), list.getProjId(),signState);
				}
				if(StringUtils.isNotBlank(list.getFieldsRepresent())){
					//现场代表通知置为无效
					signNoticeService.saveThisSignNotice(SignPostEnum.BUILDER.getValue(), SignDataTypeEnum.TRENCH_BACKFILL.getValue(),
							list.getPcId(), list.getProjId(),signState);
				}
			}
		}
	}

}
