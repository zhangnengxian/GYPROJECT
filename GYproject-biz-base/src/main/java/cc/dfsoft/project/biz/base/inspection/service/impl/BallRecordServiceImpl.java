package cc.dfsoft.project.biz.base.inspection.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cc.dfsoft.project.biz.base.inspection.dao.BallRecordDao;
import cc.dfsoft.project.biz.base.inspection.dao.ProjectChecklistDao;
import cc.dfsoft.project.biz.base.inspection.dto.BallRecordReq;
import cc.dfsoft.project.biz.base.inspection.dto.ProjectCheckListBRReq;
import cc.dfsoft.project.biz.base.inspection.entity.BallRecord;
import cc.dfsoft.project.biz.base.inspection.entity.ProjectChecklist;
import cc.dfsoft.project.biz.base.inspection.service.BallRecordService;
import cc.dfsoft.project.biz.base.project.dao.AccessoryDao;
import cc.dfsoft.project.biz.base.project.enums.AccessorySourceEnum;
import cc.dfsoft.project.biz.base.project.enums.SignDataTypeEnum;
import cc.dfsoft.project.biz.base.project.enums.SignPostEnum;
import cc.dfsoft.project.biz.base.project.enums.SignStateEnum;
import cc.dfsoft.project.biz.base.project.service.SignNoticeService;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.util.IDUtil;
/**
 * 通球记录业务实现层
 * @author liaoyq
 *
 */
@Service
@Transactional(readOnly = true,propagation = Propagation.REQUIRED)
public class BallRecordServiceImpl implements BallRecordService {

	@Resource
	private BallRecordDao ballRecordDao;
	
	@Resource
	AccessoryDao accessoryDao;
	
	/**报验单*/
	@Resource
	ProjectChecklistDao projectCheckListDao;

	/**签字通知服务*/
	@Resource
	SignNoticeService signNoticeService;
	

	@Override
	public Map<String, Object> queryBallRecord(BallRecordReq ballRecordReq) {
		return ballRecordDao.queryBallRecord(ballRecordReq);
	}

	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void saveBallRecord(ProjectCheckListBRReq checkListBRReq) {
		if(checkListBRReq.getList().size()>0){
			//删除所有的记录
			ballRecordDao.deleteByPcId(checkListBRReq.getPcId());
			//批量插入记录数据
			List<BallRecord> list = checkListBRReq.getList();
			List<BallRecord> listNew = new ArrayList<BallRecord>();
			for(BallRecord br : list){
				//生成brId;
				br.setBrId(IDUtil.getUniqueId(Constants.MODULE_CODE_BALL_RECORD));
				listNew.add(br);
			}
			ballRecordDao.batchInsertObjects(listNew);
		}else{
			ballRecordDao.deleteByPcId(checkListBRReq.getPcId());
		}
	}

	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void updatePcIdByRecordsId(String recordsId, String pcId) {
		//先置空之前报验的pcId
		ballRecordDao.updateByPcId(pcId);
		String [] ids = recordsId.split(",");
		//
		for(String id : ids){
			BallRecord record = ballRecordDao.get(id);
			if(record!=null){
				ballRecordDao.updatePcIdByRecordId(id,pcId);
				//回写拍照pcId
				accessoryDao.updateBId(pcId,record.getProjId(),id,AccessorySourceEnum.PHOTO_FILE.getValue());
			}
		}
	}

	@Override
	public Map<String, Object> queryRecords(BallRecordReq req) {
		//先置空之前报验的pcId
		return ballRecordDao.queryRecords(req);
	}

	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void saveRecords(ProjectCheckListBRReq checkListReq) {
		//有记录
		if(checkListReq.getList().size()>0){
			//先删除记录
			deleteRecord(checkListReq);
			//批量保存记录
			List<BallRecord> listNew = new ArrayList<BallRecord>();
			for(BallRecord br : checkListReq.getList()){
				br.setBrId(IDUtil.getUniqueId(Constants.MODULE_CODE_BALL_RECORD));
				listNew.add(br);
			}
			ballRecordDao.batchInsertObjects(listNew);
			
		}else {
			deleteRecord(checkListReq);
		}
	}

	private void deleteRecord(ProjectCheckListBRReq checkListReq){
		//已报验过
		if(StringUtils.isNotBlank(checkListReq.getPcId())){
			//有pcId,则根据pcId删除记录
			ballRecordDao.deletePcIdIsNull(checkListReq.getPcId());
		}else{
			ballRecordDao.deletePcIdIsNull();
		}
	}

	@Override
	public BallRecord viewRecordById(String id) {
		return ballRecordDao.get(id);
	}

	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public String saveRecord(BallRecord br) {
		if(StringUtils.isBlank(br.getBrId())){
			br.setBrId(IDUtil.getUniqueId(Constants.MODULE_CODE_BALL_RECORD));
		}
		ballRecordDao.saveOrUpdate(br);
		return br.getBrId();
	}

	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void deleteRecordById(String tbId) {
		ballRecordDao.delete(tbId);
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
		//participant班组长
		//builder施工员
		//custRepresent 甲方代表
		//suJgj监理
		if(StringUtils.isNotBlank(cwId)){
			ProjectChecklist list=projectCheckListDao.get(cwId);
			if(list!=null){
				if(StringUtils.isNotBlank(list.getParticipant())){
					//班组长通知置为无效
					signNoticeService.saveThisSignNotice(SignPostEnum.TEST_LEADER.getValue(), SignDataTypeEnum.BALL_RECORD.getValue(),
							list.getPcId(), list.getProjId(),signState);
				}
				if(StringUtils.isNotBlank(list.getBuilder())){
					//施工员通知置为无效
					signNoticeService.saveThisSignNotice(SignPostEnum.CONSTRUCTION.getValue(), SignDataTypeEnum.BALL_RECORD.getValue(),
							list.getPcId(), list.getProjId(),signState);
				}
				
				if(StringUtils.isNotBlank(list.getSuJgj())){
					//监理通知置为无效
					signNoticeService.saveThisSignNotice(SignPostEnum.CU_PM.getValue(), SignDataTypeEnum.BALL_RECORD.getValue(),
							list.getPcId(), list.getProjId(),signState);
				}
				
				if(StringUtils.isNotBlank(list.getCustRepresent())){
					//甲方代表通知置为无效
					signNoticeService.saveThisSignNotice(SignPostEnum.BUILDER.getValue(), SignDataTypeEnum.BALL_RECORD.getValue(),
							list.getPcId(), list.getProjId(),signState);
				}
				
			}
		}	
	}
}
