package cc.dfsoft.project.biz.base.inspection.service.impl;

import cc.dfsoft.project.biz.base.inspection.dao.PressureRecordsDao;
import cc.dfsoft.project.biz.base.inspection.dto.GrooveRecordReq;
import cc.dfsoft.project.biz.base.inspection.dto.ProjectCheckListGrReq;
import cc.dfsoft.project.biz.base.inspection.entity.PressureRecords;
import cc.dfsoft.project.biz.base.inspection.service.PressureRecordsService;
import cc.dfsoft.project.biz.base.project.dao.AccessoryDao;
import cc.dfsoft.project.biz.base.project.service.AccessoryService;
import cc.dfsoft.project.biz.base.project.service.SignNoticeService;
import cc.dfsoft.project.biz.base.project.service.SignatureService;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.util.IDUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service
@Transactional(readOnly = true,propagation=Propagation.REQUIRED)
public class PressureRecordsServiceImpl implements PressureRecordsService {
/*	@Resource
	GrooveRecordDao grooveRecordDao;*/

	@Resource
	PressureRecordsDao pressureRecordsDao;
	
	@Resource
	SignatureService signatureService;
	
	@Resource
	AccessoryDao accessoryDao;
	
	@Resource
	AccessoryService accessoryService;
	
	/**签字通知服务*/
	@Resource
	SignNoticeService signNoticeService;
	
	/**
	 * 保存复压记录(批量增加)
	 * @author wangang
	 * @return
	 */
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void savePressureRecords(ProjectCheckListGrReq projectCheckListGrReq) {
		//根据pcid删除所有的沟槽记录
		pressureRecordsDao.deleteByPcId(projectCheckListGrReq.getPcId());
		List<PressureRecords> list=projectCheckListGrReq.getListPressureRecords();
		
		List<PressureRecords> listNew=new ArrayList<PressureRecords>();
		for(PressureRecords gr:list){
			gr.setId(IDUtil.getUniqueId(Constants.PRESSURE_RECORDS));
			listNew.add(gr);
		}
		pressureRecordsDao.batchInsertObjects(listNew);
	}
	
	
	/**
	 * 据录区查询复压记录
	 * @author wangang
	 * @return map
	 */
	@Override
	public Map<String, Object> queryPressureRecords(GrooveRecordReq dtoReq) {
		return pressureRecordsDao.queryPressureRecords(dtoReq);
	}


	@Override
	public Map<String, Object> queryRecords(GrooveRecordReq grReq) {
		return pressureRecordsDao.queryRecords(grReq);
	}


	@Override
	@Transactional(readOnly = false,propagation = Propagation.REQUIRED)
	public void saveRecords(ProjectCheckListGrReq checkListGrReq) {
		if(checkListGrReq.getList().size()>0){
			//删除所有的记录
			pressureRecordsDao.deleteByPcId(checkListGrReq.getPcId());
			//批量插入记录数据
			List<PressureRecords> list = checkListGrReq.getListPressureRecords();
			List<PressureRecords> listNew = new ArrayList<PressureRecords>();
			for(PressureRecords gr : list){
				//生成Id;
				gr.setId(IDUtil.getUniqueId(Constants.PRESSURE_RECORDS));
				listNew.add(gr);
			}
			pressureRecordsDao.batchInsertObjects(listNew);
		}else{
			pressureRecordsDao.deleteByPcId(checkListGrReq.getPcId());
		}
	}


	@Override
	@Transactional(readOnly = false,propagation = Propagation.REQUIRED)
	public void updatePcIdByRecordsId(String recordsId, String pcId,String projId,String projNo) {
		//先置空之前报验的pcId
		pressureRecordsDao.updateByPcId(pcId);
		//质空附件表中的报验单ID
		//accessoryService.clearBRId(projId,projNo,pcId);
		String [] ids = recordsId.split(",");
		//
		for(String id : ids){
			PressureRecords pressureRecords = pressureRecordsDao.get(id);
			if(pressureRecords!=null){
				pressureRecordsDao.updatePcIdByRecordId(id,pcId);
				//回写拍照pcId
				//accessoryDao.updateBId(pcId,pressureRecords.getProjId(),id,AccessorySourceEnum.PHOTO_FILE.getValue());
			}
		}
	}
	
	@Override
	public PressureRecords viewRecordById(String id) {
		return pressureRecordsDao.get(id);
	}


	@Override
	@Transactional(readOnly = false,propagation = Propagation.REQUIRED)
	public String saveRecord(PressureRecords pr) throws Exception {
		boolean flag = false;
		if(StringUtils.isBlank(pr.getId())){
			pr.setId(IDUtil.getUniqueId(Constants.MODULE_CODE_GROOVE_RECORD));
			flag=true;
		}
		
		//更新记录
		pressureRecordsDao.saveOrUpdate(pr);
		
		
/*		List<Signature> signs=pr.getSign();
		if(signs!=null && signs.size()>0){
			for(Signature sign:signs){
				sign.setDataType(SignDataTypeEnum.GROOVE_RECORD.getValue());
			}
			pr.setSign(signs);
		}*/
		//记录区签字保存
/*		if(pr.getSign()!=null){
			signatureService.saveOrUpdateSign(pr.getSign(), pr.getProjId(), pr.getId(), pr.getClass().getName(), flag);
		}*/
		return pr.getId();
	}

	@Override
	@Transactional(readOnly = false,propagation = Propagation.REQUIRED)
	public void deleteRecordById(String id) {
		pressureRecordsDao.delete(id);
	}

/*	@Override
	@Transactional(readOnly = false,propagation = Propagation.REQUIRED)
	public void saveSignNotice(String cwId) {
		String signState = SignStateEnum.ALREADY_SIGN.getValue();
		if(StringUtils.isNotBlank(cwId)){
			//grBuilder施工员
			//firstParty甲方
		    //supervision监理
			GrooveRecord grooveRecord=grooveRecordDao.get(cwId);
			if(grooveRecord!=null){
				if(StringUtils.isNotBlank(grooveRecord.getGrBuilder())){
					//施工员为无效
					signNoticeService.saveThisSignNotice(SignPostEnum.CONSTRUCTION.getValue(), SignDataTypeEnum.GROOVE_RECORD.getValue(),
							grooveRecord.getGrId(), grooveRecord.getProjId(),signState);
				}
				if(StringUtils.isNotBlank(grooveRecord.getFirstParty())){
					//甲方为无效
					signNoticeService.saveThisSignNotice(SignPostEnum.BUILDER.getValue(), SignDataTypeEnum.GROOVE_RECORD.getValue(),
							grooveRecord.getGrId(), grooveRecord.getProjId(),signState);
				}
				if(StringUtils.isNotBlank(grooveRecord.getSupervision())){
					//监理为无效
					signNoticeService.saveThisSignNotice(SignPostEnum.SUJGJ.getValue(), SignDataTypeEnum.GROOVE_RECORD.getValue(),
							grooveRecord.getGrId(), grooveRecord.getProjId(),signState);
				}
			}
		}
	}*/
	
	
	

}
