package cc.dfsoft.project.biz.base.inspection.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cc.dfsoft.project.biz.base.inspection.dao.ElectrodeBakingDao;
import cc.dfsoft.project.biz.base.inspection.dao.ProjectChecklistDao;
import cc.dfsoft.project.biz.base.inspection.dto.ElectrodeBakingReq;
import cc.dfsoft.project.biz.base.inspection.dto.ProjectCheckListEBReq;
import cc.dfsoft.project.biz.base.inspection.entity.ElectrodeBaking;
import cc.dfsoft.project.biz.base.inspection.entity.ProjectChecklist;
import cc.dfsoft.project.biz.base.inspection.service.ElectrodeBakingService;
import cc.dfsoft.project.biz.base.project.dao.AccessoryDao;
import cc.dfsoft.project.biz.base.project.enums.AccessorySourceEnum;
import cc.dfsoft.project.biz.base.project.enums.SignDataTypeEnum;
import cc.dfsoft.project.biz.base.project.enums.SignPostEnum;
import cc.dfsoft.project.biz.base.project.enums.SignStateEnum;
import cc.dfsoft.project.biz.base.project.service.AccessoryService;
import cc.dfsoft.project.biz.base.project.service.SignNoticeService;
import cc.dfsoft.project.biz.base.project.service.SignatureService;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.util.IDUtil;
/**
 * 焊条烘烤业务实现
 * @author liaoyq
 *
 */
@Service
@Transactional(readOnly = true,propagation=Propagation.REQUIRED)
public class ElectrodeBakingServiceImpl implements ElectrodeBakingService {

	@Resource
	ElectrodeBakingDao electrodeBakingDao;

	@Resource
	AccessoryDao accessoryDao;
	
	@Resource
	SignatureService signatureService;
	@Resource
	AccessoryService accessoryService;
	
	/**报验单*/
	@Resource
	ProjectChecklistDao projectCheckListDao;


	/**签字通知服务*/
	@Resource
	SignNoticeService signNoticeService;

	@Override
	@Transactional(readOnly = false,propagation = Propagation.REQUIRED)
	public void saveElectrodeBaking(ProjectCheckListEBReq checkListEBReq) {
		if(checkListEBReq.getList().size()>0){
			//根据报验单ID 删除所有焊条烘烤记录
			electrodeBakingDao.deleteByPcId(checkListEBReq.getPcId());
			//批量插入焊条烘烤记录
			List<ElectrodeBaking> list = checkListEBReq.getList();
			List<ElectrodeBaking> listNew = new ArrayList<ElectrodeBaking>();
			for(ElectrodeBaking eb : list){
				//生成ebId
				eb.setEbId(IDUtil.getUniqueId(Constants.MODULE_CODE_ELECTRODE_BAKING));
				listNew.add(eb);
			}
			electrodeBakingDao.batchInsertObjects(listNew);
			
		}else{
			electrodeBakingDao.deleteByPcId(checkListEBReq.getPcId());
		}
		
	}

	@Override
	public Map<String, Object> queryEclectrodeBakings(
			ElectrodeBakingReq electrodeBakingReq) {
		return electrodeBakingDao.queryEclectrodeBakings(electrodeBakingReq);
	}

	@Override
	public Map<String, Object> queryRecords(ElectrodeBakingReq ebReq) {
		return electrodeBakingDao.queryRecords(ebReq);
	}

	@Override
	@Transactional(readOnly = false,propagation = Propagation.REQUIRED)
	public void saveRecords(ProjectCheckListEBReq checkListEBReq) {
		if(checkListEBReq.getList().size()>0){
			//删除所有的记录
			electrodeBakingDao.deleteByPcId(checkListEBReq.getPcId());
			//批量插入记录数据
			List<ElectrodeBaking> list = checkListEBReq.getList();
			List<ElectrodeBaking> listNew = new ArrayList<ElectrodeBaking>();
			for(ElectrodeBaking eb : list){
				//生成ebId;
				eb.setEbId(IDUtil.getUniqueId(Constants.MODULE_CODE_ELECTRODE_BAKING));
				listNew.add(eb);
			}
			electrodeBakingDao.batchInsertObjects(listNew);
		}else{
			electrodeBakingDao.deleteByPcId(checkListEBReq.getPcId());
		}
	}

	@Override
	@Transactional(readOnly = false,propagation = Propagation.REQUIRED)
	public void updatePcIdByRecordsId(String recordsId, String pcId,String projId,String projNo) {
		//先置空之前报验的pcId
		electrodeBakingDao.updateByPcId(pcId);
		//质空附件表中的报验单ID
		accessoryService.clearBRId(projId,projNo,pcId);
		String [] ids = recordsId.split(",");
		//
		for(String id : ids){
			ElectrodeBaking record = electrodeBakingDao.get(id);
			if(record!=null){
				electrodeBakingDao.updatePcIdByRecordId(id,pcId);
				//回写拍照pcId
				accessoryDao.updateBId(pcId,record.getProjId(),id,AccessorySourceEnum.PHOTO_FILE.getValue());
			}
		}
	}

	@Override
	public ElectrodeBaking viewRecordById(String id) {
		return electrodeBakingDao.get(id);
	}

	@Override
	@Transactional(readOnly = false,propagation = Propagation.REQUIRED)
	public String saveRecord(ElectrodeBaking eb) throws Exception {
		boolean flag = false;
		if(StringUtils.isBlank(eb.getEbId())){
			eb.setEbId(IDUtil.getUniqueId(Constants.MODULE_CODE_ELECTRODE_BAKING));
			flag = true;
		}
		electrodeBakingDao.saveOrUpdate(eb);
		if(eb.getSign()!=null){
			signatureService.saveOrUpdateSign(eb.getMenuId(),eb.getSign(), eb.getProjId(), eb.getEbId(), eb.getClass().getName(), flag);
		}
		return eb.getEbId();
	}

	@Override
	@Transactional(readOnly = false,propagation = Propagation.REQUIRED)
	public void deleteRecordById(String ebId) {
		electrodeBakingDao.delete(ebId);
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
		//recorder 记录人 -焊工
		//reviewer 复核人-施工员
		if(StringUtils.isNotBlank(cwId)){
			ProjectChecklist list=projectCheckListDao.get(cwId);
			if(list!=null){
				if(StringUtils.isNotBlank(list.getRecorder())){
					//焊工通知置为无效
					signNoticeService.saveThisSignNotice(SignPostEnum.WELDER.getValue(), SignDataTypeEnum.ELECTRODE_BAKING.getValue(),
							list.getPcId(), list.getProjId(),signState);
				}
				if(StringUtils.isNotBlank(list.getReviewer())){
					signNoticeService.saveThisSignNotice(SignPostEnum.CONSTRUCTION.getValue(), SignDataTypeEnum.ELECTRODE_BAKING.getValue(),
							list.getPcId(), list.getProjId(),signState);
				}
			}
		}
	}
}
