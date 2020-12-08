package cc.dfsoft.project.biz.base.inspection.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cc.dfsoft.project.biz.base.inspection.dao.HotMeltDockingDao;
import cc.dfsoft.project.biz.base.inspection.dao.ProjectChecklistDao;
import cc.dfsoft.project.biz.base.inspection.dto.HotMeltDockingReq;
import cc.dfsoft.project.biz.base.inspection.dto.ProjectCheckListHMDReq;
import cc.dfsoft.project.biz.base.inspection.entity.HotMeltDocking;
import cc.dfsoft.project.biz.base.inspection.entity.ProjectChecklist;
import cc.dfsoft.project.biz.base.inspection.service.HotMeltDockingService;
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
 * 热熔对接业务实现层
 * @author liaoyq
 *
 */
@Service
@Transactional(readOnly=true,propagation = Propagation.REQUIRED)
public class HotMeltDockingServiceImpl implements HotMeltDockingService {

	@Resource
	private HotMeltDockingDao hotMeltDockingDao;
	
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
	public Map<String, Object> queryHotMeltDocking(
			HotMeltDockingReq hotMeltDockingReq) {
		return hotMeltDockingDao.queryHotMeltDocking(hotMeltDockingReq);
	}

	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void saveHotMeltDocking(ProjectCheckListHMDReq checkListHMDReq) {
		if(checkListHMDReq.getList().size()>0){
			//删除所有的记录
			hotMeltDockingDao.deleteByPcId(checkListHMDReq.getPcId());
			//批量插入记录数据
			List<HotMeltDocking> list = checkListHMDReq.getList();
			List<HotMeltDocking> listNew = new ArrayList<HotMeltDocking>();
			for(HotMeltDocking hd : list){
				//生成hdId;
				hd.setHdId(IDUtil.getUniqueId(Constants.MODULE_CODE_HOT_MELT_DOCKING));
				listNew.add(hd);
			}
			hotMeltDockingDao.batchInsertObjects(listNew);
		}else{
			hotMeltDockingDao.deleteByPcId(checkListHMDReq.getPcId());
		}
	}

	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void updatePcIdByRecordsId(String recordsId, String pcId,String projId,String projNo) {
		//先置空之前报验的pcId
		hotMeltDockingDao.updateByPcId(pcId);
		//质空附件表中的报验单ID
		accessoryService.clearBRId(projId,projNo,pcId);
		String [] ids = recordsId.split(",");
		for(String id : ids){
			HotMeltDocking record = hotMeltDockingDao.get(id);
			if(record!=null){
				hotMeltDockingDao.updatePcIdByRecordId(id,pcId);
				//回写拍照pcId
				accessoryDao.updateBId(pcId,record.getProjId(),id,AccessorySourceEnum.PHOTO_FILE.getValue());
			}
		}
	}

	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void saveRecords(ProjectCheckListHMDReq checkListReq) {
		//有记录
		if(checkListReq.getList().size()>0){
			//先删除记录
			deleteRecord(checkListReq);
			//批量保存记录
			List<HotMeltDocking> listNew = new ArrayList<HotMeltDocking>();
			for(HotMeltDocking hd : checkListReq.getList()){
				hd.setHdId(IDUtil.getUniqueId(Constants.MODULE_CODE_HOT_MELT_DOCKING));
				listNew.add(hd);
			}
			hotMeltDockingDao.batchInsertObjects(listNew);
			
		}else {
			deleteRecord(checkListReq);
		}
	}

	private void deleteRecord(ProjectCheckListHMDReq checkListReq){
		//已报验过
		if(StringUtils.isNotBlank(checkListReq.getPcId())){
			//有pcId,则根据pcId删除记录
			hotMeltDockingDao.deletePcIdIsNull(checkListReq.getPcId());
		}else{
			hotMeltDockingDao.deletePcIdIsNull();
		}
	}
	
	@Override
	public Map<String, Object> queryRecords(HotMeltDockingReq req) {
		return hotMeltDockingDao.queryRecords(req);
	}

	@Override
	public HotMeltDocking viewRecordById(String id) {
		return hotMeltDockingDao.get(id);
	}

	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public String saveRecord(HotMeltDocking hd) {
		if(StringUtils.isBlank(hd.getHdId())){
			hd.setHdId(IDUtil.getUniqueId(Constants.MODULE_CODE_HOT_MELT_DOCKING));
		}
		hotMeltDockingDao.saveOrUpdate(hd);
		return hd.getHdId();
	}

	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void deleteRecordById(String hdId) {
		hotMeltDockingDao.delete(hdId);
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
		//tester 检测人
		//suJgj 监理
		
		if(StringUtils.isNotBlank(cwId)){
			ProjectChecklist list=projectCheckListDao.get(cwId);
			if(list!=null){
				if(StringUtils.isNotBlank(list.getTester())){
					//检测人-施工员通知置为无效
					signNoticeService.saveThisSignNotice(SignPostEnum.CONSTRUCTION.getValue(), SignDataTypeEnum.HOT_MELT_DOCKING.getValue(),
							list.getPcId(), list.getProjId(),signState);
				}
				if(StringUtils.isNotBlank(list.getSuJgj())){
					//监理通知置为无效
					signNoticeService.saveThisSignNotice(SignPostEnum.SUJGJ.getValue(), SignDataTypeEnum.HOT_MELT_DOCKING.getValue(),
							list.getPcId(), list.getProjId(),signState);
				}
			}	
		}
	}

}
