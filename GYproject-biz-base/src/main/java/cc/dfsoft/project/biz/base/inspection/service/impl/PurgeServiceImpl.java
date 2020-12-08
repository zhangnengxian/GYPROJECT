package cc.dfsoft.project.biz.base.inspection.service.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cc.dfsoft.project.biz.base.inspection.dao.ProjectChecklistDao;
import cc.dfsoft.project.biz.base.inspection.dao.PurgeDao;
import cc.dfsoft.project.biz.base.inspection.dto.ProjectCheckListPReq;
import cc.dfsoft.project.biz.base.inspection.dto.ProjectCheckListPurgeReq;
import cc.dfsoft.project.biz.base.inspection.dto.PurgeQueryReq;
import cc.dfsoft.project.biz.base.inspection.dto.PurgeReq;
import cc.dfsoft.project.biz.base.inspection.entity.ProjectChecklist;
import cc.dfsoft.project.biz.base.inspection.entity.Purge;
import cc.dfsoft.project.biz.base.inspection.service.PurgeService;
import cc.dfsoft.project.biz.base.project.dao.AccessoryDao;
import cc.dfsoft.project.biz.base.project.enums.AccessorySourceEnum;
import cc.dfsoft.project.biz.base.project.enums.SignDataTypeEnum;
import cc.dfsoft.project.biz.base.project.enums.SignPostEnum;
import cc.dfsoft.project.biz.base.project.enums.SignStateEnum;
import cc.dfsoft.project.biz.base.project.service.SignNoticeService;
import cc.dfsoft.project.biz.base.project.service.SignatureService;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.util.IDUtil;

/**
 * 吹扫记录接口实现
 * @author Administrator
 *
 */
@Service
@Transactional(readOnly = true,propagation=Propagation.REQUIRED)
public class PurgeServiceImpl implements PurgeService{
	
	/**吹扫记录dao*/
	@Resource
	PurgeDao purgeDao;
	
	@Resource
	AccessoryDao accessoryDao;
	
	@Resource
	SignatureService signatureService;
	
	/**报验单*/
	@Resource
	ProjectChecklistDao projectCheckListDao;

	/**签字通知服务*/
	@Resource
	SignNoticeService signNoticeService;
	/**
	 * 吹扫记录列表查询
	 * @author fuliwei
	 * @createTime 2016-7-20
	 * @param purgeQueryReq 查询条件dto
	 * @return Map<String, Object>
	 */
	@Override
	public Map<String, Object> queryPurge(PurgeQueryReq purgeQueryReq) throws ParseException {
		return purgeDao.queryPurge(purgeQueryReq);
	}
	
	/**
	 * 保存吹扫记录(批量增加)
	 * @author fuliwei
	 * @createTime 2016-7-21
	 * @param list
	 * @return String
	 */
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void savePurgeRecord(ProjectCheckListPurgeReq req) {
		if(req.getList().size()>0){
			//删除所有的吹扫记录
			purgeDao.deleteByPcId(req.getPcId());
			//批量保存列表中的值
			List<Purge> list=req.getList();
			List<Purge> listNew=new ArrayList<Purge>();
			for(Purge as:list){
				as.setPurgeId(IDUtil.getUniqueId(Constants.MODULE_CODE_PURGE));
				listNew.add(as);
			}
			purgeDao.batchInsertObjects(listNew);
		}else{
			//删除所有的吹扫记录
			purgeDao.deleteByPcId(req.getPcId());
		}
		
	}

	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void updatePcIdByRecordsId(String recordsId, String pcId,String projId,String projNo) {
		if(StringUtils.isNotBlank(recordsId)){
			
			String [] ids = recordsId.split(",");
			//根据记录ID回写pcId
			for(String id : ids){
				Purge purge = purgeDao.get(id);
				if(purge!=null){
					purgeDao.updatePcIdByRecordId(id,pcId);
					//回写拍照pcId
					accessoryDao.updateBId(pcId,purge.getProjId(),id,AccessorySourceEnum.PHOTO_FILE.getValue());
				}
			}
		}
	}

	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void saveRecords(ProjectCheckListPReq checkListPReq) {
		//有记录
		if(checkListPReq.getList().size()>0){
			//先删除记录
			deleteRecord(checkListPReq);
			//批量保存记录
			List<Purge> listNew = new ArrayList<Purge>();
			for(Purge purge : checkListPReq.getList()){
				purge.setPurgeId(IDUtil.getUniqueId(Constants.MODULE_CODE_PURGE));
				listNew.add(purge);
			}
			purgeDao.batchInsertObjects(listNew);
			
		}else {
			deleteRecord(checkListPReq);
		}
	}

	private void deleteRecord(ProjectCheckListPReq checkListPReq){
		//已报验过
		if(StringUtils.isNotBlank(checkListPReq.getPcId())){
			//有pcId,则根据pcId删除记录
			purgeDao.deletePcIdIsNull(checkListPReq.getPcId());
		}else{
			purgeDao.deletePcIdIsNull();
		}
	}
	@Override
	public Purge viewRecordById(String id) {
		return purgeDao.get(id);
	}

	@Override
	public Map<String, Object> queryRecords(PurgeReq purgeReq) {
		return purgeDao.queryRecords(purgeReq);
	}

	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public String saveRecord(Purge purge) throws Exception {
		boolean flag = false;
		if(StringUtils.isBlank(purge.getPurgeId())){
			purge.setPurgeId(IDUtil.getUniqueId(Constants.MODULE_CODE_PURGE));
			flag = true;
		}
		purgeDao.saveOrUpdate(purge);
		if(purge.getSign()!=null){
			signatureService.saveOrUpdateSign(purge.getMenuId(),purge.getSign(), purge.getProjId(), purge.getPurgeId(), purge.getClass().getName(), flag);
		}
		return purge.getPurgeId();
	}

	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void deleteRecordById(String purgeId) {
		purgeDao.delete(purgeId);
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
		//现场代表fieldsRepresent
		//suJgj现场监理
		//builder施工员
		//operator班组长
		
		if(StringUtils.isNotBlank(cwId)){
			ProjectChecklist list=projectCheckListDao.get(cwId);
			if(list!=null){
				if(StringUtils.isNotBlank(list.getOperator())){
					//班组长通知置为无效
					signNoticeService.saveThisSignNotice(SignPostEnum.TEST_LEADER.getValue(), SignDataTypeEnum.PURGE.getValue(),
							list.getPcId(), list.getProjId(),signState);
				}
				
				if(StringUtils.isNotBlank(list.getBuilder())){
					//施工员通知置为无效
					signNoticeService.saveThisSignNotice(SignPostEnum.CONSTRUCTION.getValue(), SignDataTypeEnum.PURGE.getValue(),
							list.getPcId(), list.getProjId(),signState);
				}
				if(StringUtils.isNotBlank(list.getSuJgj())){
					//现场监理通知置为无效
					signNoticeService.saveThisSignNotice(SignPostEnum.SUJGJ.getValue(), SignDataTypeEnum.PURGE.getValue(),
							list.getPcId(), list.getProjId(),signState);
				}
				if(StringUtils.isNotBlank(list.getFieldsRepresent())){
					//现场代表通知置为无效
					signNoticeService.saveThisSignNotice(SignPostEnum.BUILDER.getValue(), SignDataTypeEnum.PURGE.getValue(),
							list.getPcId(), list.getProjId(),signState);
				}
			}
		}
		
		
	}

}
