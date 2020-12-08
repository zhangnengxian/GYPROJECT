package cc.dfsoft.project.biz.base.inspection.service.impl;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cc.dfsoft.project.biz.base.inspection.dao.PePipeWeldingDao;
import cc.dfsoft.project.biz.base.inspection.dao.ProjectChecklistDao;
import cc.dfsoft.project.biz.base.inspection.dto.PePipeWeldingReq;
import cc.dfsoft.project.biz.base.inspection.dto.ProjectCheckListPePipeReq;
import cc.dfsoft.project.biz.base.inspection.entity.PepipeWelding;
import cc.dfsoft.project.biz.base.inspection.entity.ProjectChecklist;
import cc.dfsoft.project.biz.base.inspection.service.PePipeWeldingService;
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
public class PePipeWeldingServiceImpl implements PePipeWeldingService {

	@Resource
	PePipeWeldingDao pePipeWeldingDao;
	
	@Resource
	SignatureService signatureService;
	
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
	
	
	/**
	 * PE管焊接记录列表查询
	 */
	@Override
	public Map<String, Object> queryPePipeWelding(PePipeWeldingReq pePipeWeldingReq) {
		return pePipeWeldingDao.queryPePipeWelding(pePipeWeldingReq);
	}

	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void savePepipewelding(ProjectCheckListPePipeReq req) {
		if(req.getList().size()>0){
			//删除所有的PE焊接记录
			pePipeWeldingDao.deleteByPcId(req.getPcId());
			//批量插入
			List<PepipeWelding> list=req.getList();
			List<PepipeWelding> listNew=new ArrayList<PepipeWelding>();
			for(PepipeWelding pw:list){
				pw.setPeId(IDUtil.getUniqueId(Constants.MODULE_CODE_PEPIPEWELDING));
				listNew.add(pw);
			}
			pePipeWeldingDao.batchInsertObjects(listNew);
		}else{
			//删除所有的PE焊接记录
			pePipeWeldingDao.deleteByPcId(req.getPcId());
		}
	}

	@Override
	public Map<String, Object> queryRecords(PePipeWeldingReq pipeWeldingReq) {
		return pePipeWeldingDao.queryRecords(pipeWeldingReq);
	}

	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void saveRecords(ProjectCheckListPePipeReq checkListReq) {
		if(checkListReq.getList().size()>0){
			//删除所有的焊条领用记录
			pePipeWeldingDao.deleteByPcId(checkListReq.getPcId());
			//批量插入记录数据
			List<PepipeWelding> list = checkListReq.getList();
			List<PepipeWelding> listNew = new ArrayList<PepipeWelding>();
			for(PepipeWelding pepw : list){
				//生成peId;
				pepw.setPeId(IDUtil.getUniqueId(Constants.MODULE_CODE_PEPIPEWELDING));
				listNew.add(pepw);
			}
			pePipeWeldingDao.batchInsertObjects(listNew);
		}else{
			pePipeWeldingDao.deleteByPcId(checkListReq.getPcId());
		}
	}

	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void updatePcIdByRecordsId(String recordsId, String pcId,String projId,String projNo) {
		//先置空之前报验的pcId
		pePipeWeldingDao.updateByPcId(pcId);
		//质空附件表中的报验单ID
		accessoryService.clearBRId(projId,projNo,pcId);
		String [] ids = recordsId.split(",");
		//
		for(String id : ids){
			PepipeWelding record = pePipeWeldingDao.get(id);
			if(record!=null){
				pePipeWeldingDao.updatePcIdByRecordId(id,pcId);
				//回写拍照pcId
				accessoryDao.updateBId(pcId,record.getProjId(),id,AccessorySourceEnum.PHOTO_FILE.getValue());
			}
		}
	}

	@Override
	public PepipeWelding viewRecordById(String id) {
		return pePipeWeldingDao.get(id);
	}

	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public String saveRecord(PepipeWelding pePw) throws Exception {
		boolean flag = false;
		if(StringUtils.isBlank(pePw.getPeId())){
			pePw.setPeId(IDUtil.getUniqueId(Constants.MODULE_CODE_PEPIPEWELDING));
			flag = true;
		}
		pePipeWeldingDao.saveOrUpdate(pePw);
		
		
		List<Signature> signs=pePw.getSign();
		if(signs!=null && signs.size()>0){
			for(Signature sign:signs){
				sign.setDataType(SignDataTypeEnum.PE_PIPEWELDING.getValue());
			}
			pePw.setSign(signs);
		}
		if(pePw.getSign()!=null){
			signatureService.saveOrUpdateSign(pePw.getMenuId(),pePw.getSign(), pePw.getProjId(), pePw.getPeId(), pePw.getClass().getName(), flag);
		}
		return pePw.getPeId();
	}

	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void deleteRecordById(String peId) {
		pePipeWeldingDao.delete(peId);
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
				if(StringUtils.isNotBlank(list.getWelder())){
					//焊工通知置为无效
					signNoticeService.saveThisSignNotice(SignPostEnum.WELDER.getValue(), SignDataTypeEnum.PE_PIPEWELDING_CHECK.getValue(),
							list.getPcId(), list.getProjId(),signState);
				}
				if(StringUtils.isNotBlank(list.getChecker())){
					//施工员
					signNoticeService.saveThisSignNotice(SignPostEnum.CONSTRUCTION.getValue(), SignDataTypeEnum.PE_PIPEWELDING_CHECK.getValue(),
							list.getPcId(), list.getProjId(),signState);
				}
				if(StringUtils.isNotBlank(list.getProjectLeader())){
					//项目经理
					signNoticeService.saveThisSignNotice(SignPostEnum.CU_PM.getValue(), SignDataTypeEnum.PE_PIPEWELDING_CHECK.getValue(),
							list.getPcId(), list.getProjId(),signState);
				}
			}
		}
		
	}
	
	/**
	 * 保存回调-记录区
	 * @author fuliwei
	 * @createTime 2018年1月21日
	 * @param 
	 * @return
	 */
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void saveSignNoticeReocrd(String cwId) {
		String signState = SignStateEnum.ALREADY_SIGN.getValue();
		if(StringUtils.isNotBlank(cwId)){
			PepipeWelding pePipeWelding=pePipeWeldingDao.get(cwId);
			if(pePipeWelding!=null){
				//builder 施工员
				//firstParty甲方
				//supervision 监理
				
				if(StringUtils.isNotBlank(pePipeWelding.getBuilder())){
					//施工员通知置为无效
					signNoticeService.saveThisSignNotice(SignPostEnum.CONSTRUCTION.getValue(), SignDataTypeEnum.PE_PIPEWELDING.getValue(),
							pePipeWelding.getPeId(), pePipeWelding.getProjId(),signState);
				}
				if(StringUtils.isNotBlank(pePipeWelding.getFirstParty())){
					//甲方通知置为无效
					signNoticeService.saveThisSignNotice(SignPostEnum.BUILDER.getValue(), SignDataTypeEnum.PE_PIPEWELDING.getValue(),
							pePipeWelding.getPeId(), pePipeWelding.getProjId(),signState);
				}
				if(StringUtils.isNotBlank(pePipeWelding.getSupervision())){
					//监理通知置为无效
					signNoticeService.saveThisSignNotice(SignPostEnum.BUILDER.getValue(), SignDataTypeEnum.PE_PIPEWELDING.getValue(),
							pePipeWelding.getPeId(), pePipeWelding.getProjId(),signState);
				}
			}
			
			
		}
		
	}
}
