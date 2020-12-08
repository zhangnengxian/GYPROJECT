package cc.dfsoft.project.biz.base.inspection.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cc.dfsoft.project.biz.base.inspection.dao.AnodeInstallDao;
import cc.dfsoft.project.biz.base.inspection.dao.ProjectChecklistDao;
import cc.dfsoft.project.biz.base.inspection.dto.AnodeInstallReq;
import cc.dfsoft.project.biz.base.inspection.dto.ProjectCheckListAIReq;
import cc.dfsoft.project.biz.base.inspection.entity.AnodeInstall;
import cc.dfsoft.project.biz.base.inspection.entity.ProjectChecklist;
import cc.dfsoft.project.biz.base.inspection.service.AnodeInstallService;
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
 * 阳极安装业务实现层
 * @author liaoyq
 *
 */
@Service
@Transactional(readOnly=true,propagation = Propagation.REQUIRED)
public class AnodeInstallServiceImpl implements AnodeInstallService {

	@Resource
	private AnodeInstallDao anodeInstallDao;

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
	public Map<String, Object> queryAnodeInstall(AnodeInstallReq anodeInstallReq) {
		return anodeInstallDao.queryAnodeInstall(anodeInstallReq);
	}

	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void saveAnodeInstall(ProjectCheckListAIReq checkListAIReq) {
		if(checkListAIReq.getList().size()>0){
			//删除所有的记录
			anodeInstallDao.deleteByPcId(checkListAIReq.getPcId());
			//批量插入记录数据
			List<AnodeInstall> list = checkListAIReq.getList();
			List<AnodeInstall> listNew = new ArrayList<AnodeInstall>();
			for(AnodeInstall ai : list){
				//生成aiId;
				ai.setAiId(IDUtil.getUniqueId(Constants.MODULE_CODE_ANODE_INSTALL));
				listNew.add(ai);
			}
			anodeInstallDao.batchInsertObjects(listNew);
		}else{
			anodeInstallDao.deleteByPcId(checkListAIReq.getPcId());
		}
	}

	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void updatePcIdByRecordsId(String recordsId, String pcId,String projId,String projNo) {
		//先置空之前报验的pcId
		anodeInstallDao.updateByPcId(pcId);
		//质空附件表中的报验单ID
		accessoryService.clearBRId(projId,projNo,pcId);
		String [] ids = recordsId.split(",");
		//
		for(String id : ids){
			AnodeInstall record = anodeInstallDao.get(id);
			if(record!=null){
				anodeInstallDao.updatePcIdByRecordId(id,pcId);
				//回写拍照pcId
				accessoryDao.updateBId(pcId,record.getProjId(),id,AccessorySourceEnum.PHOTO_FILE.getValue());
			}
		}
	}

	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void saveRecords(ProjectCheckListAIReq checkListReq) {
		//有记录
		if(checkListReq.getList().size()>0){
			//先删除记录
			deleteRecord(checkListReq);
			//批量保存记录
			List<AnodeInstall> listNew = new ArrayList<AnodeInstall>();
			for(AnodeInstall ai : checkListReq.getList()){
				ai.setAiId(IDUtil.getUniqueId(Constants.MODULE_CODE_ANODE_INSTALL));
				listNew.add(ai);
			}
			anodeInstallDao.batchInsertObjects(listNew);
			
		}else {
			deleteRecord(checkListReq);
		}
	}

	private void deleteRecord(ProjectCheckListAIReq checkListReq){
		//已报验过
		if(StringUtils.isNotBlank(checkListReq.getPcId())){
			//有pcId,则根据pcId删除记录
			anodeInstallDao.deletePcIdIsNull(checkListReq.getPcId());
		}else{
			anodeInstallDao.deletePcIdIsNull();
		}
	}
	@Override
	public Map<String, Object> queryRecords(AnodeInstallReq req) {
		return anodeInstallDao.queryRecords(req);
	}

	@Override
	public AnodeInstall viewRecordById(String id) {
		return anodeInstallDao.get(id);
	}

	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public String saveRecord(AnodeInstall ai) {
		if(StringUtils.isBlank(ai.getAiId())){
			ai.setAiId(IDUtil.getUniqueId(Constants.MODULE_CODE_ANODE_INSTALL));
		}
		anodeInstallDao.saveOrUpdate(ai);
		return ai.getAiId();
	}

	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void deleteRecordById(String aiId) {
		anodeInstallDao.delete(aiId);
		
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
		//projectLeader项目经理
		//constructionQc质检员
		//builder施工员
		
		if(StringUtils.isNotBlank(cwId)){
			ProjectChecklist list=projectCheckListDao.get(cwId);
			if(list!=null){
				if(StringUtils.isNotBlank(list.getBuilder())){
					//施工员 通知置为无效
					signNoticeService.saveThisSignNotice(SignPostEnum.CONSTRUCTION.getValue(), SignDataTypeEnum.ANODE_INSTALL.getValue(),
							list.getPcId(), list.getProjId(),signState);
				}
				if(StringUtils.isNotBlank(list.getProjectLeader())){
					//项目经理 通知置为无效
					signNoticeService.saveThisSignNotice(SignPostEnum.CU_PM.getValue(), SignDataTypeEnum.ANODE_INSTALL.getValue(),
							list.getPcId(), list.getProjId(),signState);
				}
				if(StringUtils.isNotBlank(list.getConstructionQc())){
					//质检员 通知置为无效
					signNoticeService.saveThisSignNotice(SignPostEnum.QUALITATIVE_CHECK_MEMBER.getValue(), SignDataTypeEnum.ANODE_INSTALL.getValue(),
							list.getPcId(), list.getProjId(),signState);
				}
				if(StringUtils.isNotBlank(list.getSuJgj())){
					//监理 通知置为无效
					signNoticeService.saveThisSignNotice(SignPostEnum.SUJGJ.getValue(), SignDataTypeEnum.ANODE_INSTALL.getValue(),
							list.getPcId(), list.getProjId(),signState);
				}
				if(StringUtils.isNotBlank(list.getFieldsRepresent())){
					//甲方代表 通知置为无效
					signNoticeService.saveThisSignNotice(SignPostEnum.BUILDER.getValue(), SignDataTypeEnum.ANODE_INSTALL.getValue(),
							list.getPcId(), list.getProjId(),signState);
				}
			}	
		}
	}
	
}
