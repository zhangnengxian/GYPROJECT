package cc.dfsoft.project.biz.base.inspection.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cc.dfsoft.project.biz.base.inspection.dao.GrooveRecordDao;
import cc.dfsoft.project.biz.base.inspection.dto.GrooveRecordReq;
import cc.dfsoft.project.biz.base.inspection.dto.ProjectCheckListGrReq;
import cc.dfsoft.project.biz.base.inspection.entity.GrooveRecord;
import cc.dfsoft.project.biz.base.inspection.service.GrooveRecordService;
import cc.dfsoft.project.biz.base.project.dao.AccessoryDao;
import cc.dfsoft.project.biz.base.project.entity.Signature;
import cc.dfsoft.project.biz.base.project.enums.AccessorySourceEnum;
import cc.dfsoft.project.biz.base.project.enums.SignDataTypeEnum;
import cc.dfsoft.project.biz.base.project.enums.SignPostEnum;
import cc.dfsoft.project.biz.base.project.enums.SignStateEnum;
import cc.dfsoft.project.biz.base.project.service.AccessoryService;
import cc.dfsoft.project.biz.base.project.service.SignNoticeService;
import cc.dfsoft.project.biz.base.project.service.SignatureService;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.util.IDUtil;


@Service
@Transactional(readOnly = true,propagation=Propagation.REQUIRED)
public class GrooveRecordServiceImpl implements GrooveRecordService{
	@Resource
	GrooveRecordDao grooveRecordDao;
	
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
	 * 保存沟槽记录(批量增加)
	 * @author zhangjj
	 * @param projectCheckListGrReq
	 * @return
	 */
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void saveGrooveRecord(ProjectCheckListGrReq projectCheckListGrReq) {
		//根据pcid删除所有的沟槽记录
		grooveRecordDao.deleteByPcId(projectCheckListGrReq.getPcId());
		List<GrooveRecord> list=projectCheckListGrReq.getList();
		
		List<GrooveRecord> listNew=new ArrayList<GrooveRecord>();
		for(GrooveRecord gr:list){
			gr.setGrId(IDUtil.getUniqueId(Constants.MODULE_CODE_GROOVE_RECORD));
			listNew.add(gr);
		}
		grooveRecordDao.batchInsertObjects(listNew);
	}
	
	
	/**
	 * 据录区查询沟槽记录
	 * @author zhangjj
	 * @param
	 * @return map
	 */
	@Override
	public Map<String, Object> queryGrooveRecord(GrooveRecordReq dtoReq) {
		return grooveRecordDao.queryGrooveRecord(dtoReq);
	}


	@Override
	public Map<String, Object> queryRecords(GrooveRecordReq grReq) {
		return grooveRecordDao.queryRecords(grReq);
	}


	@Override
	@Transactional(readOnly = false,propagation = Propagation.REQUIRED)
	public void saveRecords(ProjectCheckListGrReq checkListGrReq) {
		if(checkListGrReq.getList().size()>0){
			//删除所有的记录
			grooveRecordDao.deleteByPcId(checkListGrReq.getPcId());
			//批量插入记录数据
			List<GrooveRecord> list = checkListGrReq.getList();
			List<GrooveRecord> listNew = new ArrayList<GrooveRecord>();
			for(GrooveRecord gr : list){
				//生成ebId;
				gr.setGrId(IDUtil.getUniqueId(Constants.MODULE_CODE_GROOVE_RECORD));
				listNew.add(gr);
			}
			grooveRecordDao.batchInsertObjects(listNew);
		}else{
			grooveRecordDao.deleteByPcId(checkListGrReq.getPcId());
		}
	}


	@Override
	@Transactional(readOnly = false,propagation = Propagation.REQUIRED)
	public void updatePcIdByRecordsId(String recordsId, String pcId,String projId,String projNo) {
		//先置空之前报验的pcId
		grooveRecordDao.updateByPcId(pcId);
		//质空附件表中的报验单ID
		accessoryService.clearBRId(projId,projNo,pcId);
		String [] ids = recordsId.split(",");
		//
		for(String id : ids){
			GrooveRecord record = grooveRecordDao.get(id);
			if(record!=null){
				grooveRecordDao.updatePcIdByRecordId(id,pcId);
				//回写拍照pcId
				accessoryDao.updateBId(pcId,record.getProjId(),id,AccessorySourceEnum.PHOTO_FILE.getValue());
			}
		}
	}
	
	@Override
	public GrooveRecord viewRecordById(String id) {
		return grooveRecordDao.get(id);
	}


	@Override
	@Transactional(readOnly = false,propagation = Propagation.REQUIRED)
	public String saveRecord(GrooveRecord gr) throws Exception {
		boolean flag = false;
		if(StringUtils.isBlank(gr.getGrId())){
			gr.setGrId(IDUtil.getUniqueId(Constants.MODULE_CODE_GROOVE_RECORD));
			flag=true;
		}
		
		//更新记录
		grooveRecordDao.saveOrUpdate(gr);
		
		
		List<Signature> signs=gr.getSign();
		if(signs!=null && signs.size()>0){
			for(Signature sign:signs){
				sign.setDataType(SignDataTypeEnum.GROOVE_RECORD.getValue());
			}
			gr.setSign(signs);
		}
		//记录区签字保存
		if(gr.getSign()!=null){
			signatureService.saveOrUpdateSign(gr.getMenuId(),gr.getSign(), gr.getProjId(), gr.getGrId(), gr.getClass().getName(), flag);
		}
		return gr.getGrId();
	}

	@Override
	@Transactional(readOnly = false,propagation = Propagation.REQUIRED)
	public void deleteRecordById(String grId) {
		grooveRecordDao.delete(grId);
	}

	/**
	 * 保存回调
	 * @author fuliwei
	 * @createTime 2018年1月21日
	 * @param 
	 * @return
	 */
	@Override
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
	}
	
	
	

}
