package cc.dfsoft.project.biz.base.inspection.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cc.dfsoft.project.biz.base.inspection.dao.IndoorPocketWatchDao;
import cc.dfsoft.project.biz.base.inspection.dao.ProjectChecklistDao;
import cc.dfsoft.project.biz.base.inspection.dto.IndoorPocketWatchReq;
import cc.dfsoft.project.biz.base.inspection.dto.ProjectCheckListIPWReq;
import cc.dfsoft.project.biz.base.inspection.entity.IndoorPocketWatch;
import cc.dfsoft.project.biz.base.inspection.entity.ProjectChecklist;
import cc.dfsoft.project.biz.base.inspection.service.IndoorPocketWatchService;
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
 * 户内挂表业务实现层
 * @author liaoyq
 * @createTime 2017年7月24日
 */
@Service
@Transactional(readOnly = true,propagation = Propagation.REQUIRED)
public class IndoorPocketWatchServiceImpl implements IndoorPocketWatchService {
	
	@Resource
	IndoorPocketWatchDao indoorPocketWatchDao;

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
	 * 分页查询户内挂表记录
	 * @author liaoyq
	 * @createTime 2017-7-24
	 * @param indoorPocketWatchReq 户内挂表查询辅助类
	 * @return 户内挂表记录及分页信息
	 */
	@Override
	public Map<String, Object> queryRecords(
			IndoorPocketWatchReq indoorPocketWatchReq) {
		return indoorPocketWatchDao.queryRecords(indoorPocketWatchReq);
	}

	/**
	 * 保存户内挂表记录
	 * @author liaoyq
	 * @createTime 2017-7-24
	 * @param checkListIPWReq 户内挂表保存辅助类
	 */
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void saveIndoorPocketWatchServiceRecord(
			ProjectCheckListIPWReq checkListIPWReq) {
		//有记录
				if(checkListIPWReq.getList().size()>0){
					//先删除记录
					deleteRecord(checkListIPWReq);
					//批量保存记录
					List<IndoorPocketWatch> listNew = new ArrayList<IndoorPocketWatch>();
					for(IndoorPocketWatch ipw : checkListIPWReq.getList()){
						ipw.setIpwId(IDUtil.getUniqueId(Constants.MODULE_CODE_INDOOR_POCKET_WATCH));
						listNew.add(ipw);
						//showTableColumns(ipw);
					}
					
					indoorPocketWatchDao.batchInsertObjects(listNew);
					
				}else {
					deleteRecord(checkListIPWReq);
				}
	}
	/**
	 * 根据条件判断删除记录
	 * @author liaoyq
	 * @createTime 2017-7-24
	 * @param checkListIPWReq
	 */
	private void deleteRecord(ProjectCheckListIPWReq checkListIPWReq){
		//已报验过
		if(StringUtils.isNotBlank(checkListIPWReq.getPcId())){
			//有pcId,则根据pcId删除记录
			indoorPocketWatchDao.deletePcIdIsNull(checkListIPWReq.getPcId());
		}else{
			indoorPocketWatchDao.deletePcIdIsNull();
		}
	}
	
	/**
	 * 判断字段
	 * @author liaoyq
	 * @createTime 2017-7-27
	 * @param ipw
	 * @return
	 */
	private boolean showTableColumns(IndoorPocketWatch ipw){
		
		if(StringUtils.isNotBlank(ipw.getFloorStart())&&StringUtils.isNotBlank(ipw.getFloorEnd())){
			List<Map<String, Object>> list = indoorPocketWatchDao.showTableColumns();
			if(list!=null && list.size()>0){
				//楼层数大于已有楼层数
				if(list.size()<Integer.parseInt(ipw.getFloorEnd())){
					//增加表字段
					String floorName =IndoorPocketWatch.floorName;
					String str = "";
					 for(int start = list.size();start<=Integer.parseInt(ipw.getFloorEnd());start++){
						 str +=  " ADD "+floorName+(start+1)+" VARCHAR(4),";
					 }
					 return indoorPocketWatchDao.addTableColumns(str);
				}
			}
		}
		return false;
		
	}

	/**
	 * 根据户内挂表记录ID 查询户内挂表记录详述
	 * @author liaoyq
	 * @createTime 2017年7月24日
	 * @param id 户内挂表记录ID
	 * @return indoorPocketWatch
	 */
	@Override
	public IndoorPocketWatch viewRecordById(String id) {
		return indoorPocketWatchDao.get(id);
	}

	/**
	 * 根据报验记录ID回写报验单ID
	 * @author liaoyq
	 * @createTime 2017年7月24日
	 * @param recordsId 报验记录ID组合
	 * @param pcId 报验单ID
	 */
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void updatePcIdByRecordsId(String recordsId, String pcId,String projId,String projNo) {
		//先置空之前报验的pcId
		indoorPocketWatchDao.updateByPcId(pcId);
		//质空附件表中的报验单ID
		accessoryService.clearBRId(projId,projNo,pcId);
		String [] ids = recordsId.split(",");
		//
		for(String id : ids){
			IndoorPocketWatch record = indoorPocketWatchDao.get(id);
			if(record!=null){
				indoorPocketWatchDao.updatePcIdByRecordId(id,pcId);
				//回写拍照pcId
				accessoryDao.updateBId(pcId,record.getProjId(),id,AccessorySourceEnum.PHOTO_FILE.getValue());
			}
		}
	}

	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void deleteRecordById(String ipwId) {
		indoorPocketWatchDao.delete(ipwId);
	}

	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public String saveRecord(IndoorPocketWatch record) {
		if(StringUtils.isBlank(record.getIpwId())){
			record.setIpwId(IDUtil.getUniqueId(Constants.MODULE_CODE_INDOOR_POCKET_WATCH));
		}
		indoorPocketWatchDao.saveOrUpdate(record);
		return record.getIpwId();
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
				if(StringUtils.isNotBlank(list.getConstructionPrincipal())){
					//项目经理通知置为无效
					signNoticeService.saveThisSignNotice(SignPostEnum.CU_PM.getValue(), SignDataTypeEnum.INDOOR_POCKET_WATCH.getValue(),
							list.getPcId(), list.getProjId(),signState);
				}
				if(StringUtils.isNotBlank(list.getFieldsRepresent())){
					//现场代表通知置为无效
					signNoticeService.saveThisSignNotice(SignPostEnum.BUILDER.getValue(), SignDataTypeEnum.INDOOR_POCKET_WATCH.getValue(),
							list.getPcId(), list.getProjId(),signState);
				}
			}	
		}
	}

}
