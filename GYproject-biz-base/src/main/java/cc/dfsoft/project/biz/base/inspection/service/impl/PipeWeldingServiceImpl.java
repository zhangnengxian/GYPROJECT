package cc.dfsoft.project.biz.base.inspection.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cc.dfsoft.project.biz.base.inspection.dao.PipeWeldingDao;
import cc.dfsoft.project.biz.base.inspection.dto.PipeWeldingReq;
import cc.dfsoft.project.biz.base.inspection.dto.ProjectCheckListPipeReq;
import cc.dfsoft.project.biz.base.inspection.entity.PipeWelding;
import cc.dfsoft.project.biz.base.inspection.service.PipeWeldingService;
import cc.dfsoft.project.biz.base.project.dao.AccessoryDao;
import cc.dfsoft.project.biz.base.project.enums.AccessorySourceEnum;
import cc.dfsoft.project.biz.base.project.service.AccessoryService;
import cc.dfsoft.project.biz.base.project.service.SignatureService;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.util.IDUtil;

@Service
@Transactional(readOnly = true,propagation=Propagation.REQUIRED)
public class PipeWeldingServiceImpl implements PipeWeldingService{
	
	@Resource
	PipeWeldingDao pipeWeldingDao;
	@Resource
	SignatureService signatureService;
	
	@Resource
	AccessoryDao accessoryDao;
	@Resource
	AccessoryService accessoryService;
	
	@Override
	public Map<String, Object> queryPipeWelding(PipeWeldingReq pipeWeldingReq) {
		return pipeWeldingDao.queryPipeWelding(pipeWeldingReq);
	}


	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void savePipewelding(ProjectCheckListPipeReq req) {
		if(req.getList().size()>0){
			//删除所有的钢管焊接记录
			pipeWeldingDao.deleteByPcId(req.getPcId());
			//批量保存
			List<PipeWelding> list=req.getList();
			List<PipeWelding> listNew=new ArrayList<PipeWelding>();
			for(PipeWelding pw:list){
				pw.setPipeId(IDUtil.getUniqueId(Constants.MODULE_CODE_PIPEWELDING));
				listNew.add(pw);
			}
			pipeWeldingDao.batchInsertObjects(listNew);
		}else{
			//删除所有的钢管焊接记录
			pipeWeldingDao.deleteByPcId(req.getPcId());
		}
	}


	@Override
	public Map<String, Object> queryRecords(PipeWeldingReq pipeWeldingReq) {
		return pipeWeldingDao.queryRecords(pipeWeldingReq);
	}


	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void saveRecords(ProjectCheckListPipeReq checkListReq) {
		if(checkListReq.getList().size()>0){
			//删除所有的焊条领用记录
			pipeWeldingDao.deleteByPcId(checkListReq.getPcId());
			//批量插入记录数据
			List<PipeWelding> list = checkListReq.getList();
			List<PipeWelding> listNew = new ArrayList<PipeWelding>();
			for(PipeWelding pw : list){
				//生成pipeId;
				pw.setPipeId(IDUtil.getUniqueId(Constants.MODULE_CODE_PIPEWELDING));
				listNew.add(pw);
			}
			pipeWeldingDao.batchInsertObjects(listNew);
		}else{
			pipeWeldingDao.deleteByPcId(checkListReq.getPcId());
		}
	}


	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void updatePcIdByRecordsId(String recordsId, String pcId,String projId,String projNo) {
		//先置空之前报验的pcId
		pipeWeldingDao.updateByPcId(pcId);
		//质空附件表中的报验单ID
		accessoryService.clearBRId(projId,projNo,pcId);
		String [] ids = recordsId.split(",");
		//
		for(String id : ids){
			PipeWelding record = pipeWeldingDao.get(id);
			if(record!=null){
				pipeWeldingDao.updatePcIdByRecordId(id,pcId);
				//回写拍照pcId
				accessoryDao.updateBId(pcId,record.getProjId(),id,AccessorySourceEnum.PHOTO_FILE.getValue());
			}
		}
	}


	@Override
	public PipeWelding viewRecordById(String id) {
		return pipeWeldingDao.get(id);
	}


	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public String saveRecord(PipeWelding pw) throws Exception {
		boolean flag = false;
		if(StringUtils.isBlank(pw.getPipeId())){
			pw.setPipeId(IDUtil.getUniqueId(Constants.MODULE_CODE_PIPEWELDING));
			flag = true;
		}
		pipeWeldingDao.saveOrUpdate(pw);
		if(pw.getSign()!=null){
			signatureService.saveOrUpdateSign(pw.getMenuId(),pw.getSign(), pw.getProjId(), pw.getPipeId(), pw.getClass().getName(), flag);
		}
		return pw.getPipeId();
	}


	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void deleteRecordById(String pipeId) {
		pipeWeldingDao.delete(pipeId);
	}
}
