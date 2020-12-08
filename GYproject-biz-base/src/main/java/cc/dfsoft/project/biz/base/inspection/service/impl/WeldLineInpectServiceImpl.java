package cc.dfsoft.project.biz.base.inspection.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cc.dfsoft.project.biz.base.inspection.dao.ProjectChecklistDao;
import cc.dfsoft.project.biz.base.inspection.dao.WeldLineInpectDao;
import cc.dfsoft.project.biz.base.inspection.dto.ProjectCheckListWLIReq;
import cc.dfsoft.project.biz.base.inspection.dto.WeldLineInpectReq;
import cc.dfsoft.project.biz.base.inspection.entity.ProjectChecklist;
import cc.dfsoft.project.biz.base.inspection.entity.WeldLineInpect;
import cc.dfsoft.project.biz.base.inspection.service.WeldLineInpectService;
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
 * 管道焊缝检查业务实现层
 * @author liaoyq
 *
 */
@Service
@Transactional(readOnly = true,propagation = Propagation.REQUIRED)
public class WeldLineInpectServiceImpl implements WeldLineInpectService {

	@Resource
	WeldLineInpectDao weldLineInpectDao;
	
	@Resource
	AccessoryDao accessoryDao;
	@Resource 
	AccessoryService accessoryService;
	
	@Resource
	SignatureService signatureService;
	
	/**报验单*/
	@Resource
	ProjectChecklistDao projectCheckListDao;


	/**签字通知服务*/
	@Resource
	SignNoticeService signNoticeService;
	@Override
	public Map<String, Object> queryWeldLineInpect(
			WeldLineInpectReq weldLineInpectReq) {
		return weldLineInpectDao.queryWeldLineInpect(weldLineInpectReq);
	}

	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void saveWeldLineInpect(ProjectCheckListWLIReq checkListWLIReq) {
		if(checkListWLIReq.getList().size()>0){
			//删除所有的记录
			weldLineInpectDao.deleteByPcId(checkListWLIReq.getPcId());
			//批量插入记录数据
			List<WeldLineInpect> list = checkListWLIReq.getList();
			List<WeldLineInpect> listNew = new ArrayList<WeldLineInpect>();
			for(WeldLineInpect wli : list){
				//生成wliId;
				wli.setWliId(IDUtil.getUniqueId(Constants.MODULE_CODE_WELD_LINE_INPECT));
				listNew.add(wli);
			}
			weldLineInpectDao.batchInsertObjects(listNew);
		}else{
			weldLineInpectDao.deleteByPcId(checkListWLIReq.getPcId());
		}
	}

	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void updatePcIdByRecordsId(String recordsId, String pcId,String projId,String projNo) {
		//先置空之前报验的pcId
		weldLineInpectDao.updateByPcId(pcId);
		accessoryService.clearBRId(projId, projNo, pcId);
		String [] ids = recordsId.split(",");
		//
		for(String id : ids){
			WeldLineInpect record = weldLineInpectDao.get(id);
			if(record!=null){
				weldLineInpectDao.updatePcIdByRecordId(id,pcId);
				//回写拍照pcId
				accessoryDao.updateBId(pcId,record.getProjId(),id,AccessorySourceEnum.PHOTO_FILE.getValue());
			}
		}
	}

	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void saveRecords(ProjectCheckListWLIReq checkListReq) {
		//有记录
		if(checkListReq.getList().size()>0){
			//先删除记录
			deleteRecord(checkListReq);
			//批量保存记录
			List<WeldLineInpect> listNew = new ArrayList<WeldLineInpect>();
			for(WeldLineInpect wi : checkListReq.getList()){
				wi.setWliId(IDUtil.getUniqueId(Constants.MODULE_CODE_WELD_LINE_INPECT));
				listNew.add(wi);
			}
			weldLineInpectDao.batchInsertObjects(listNew);
			
		}else {
			deleteRecord(checkListReq);
		}
	}
	private void deleteRecord(ProjectCheckListWLIReq checkListReq){
		//已报验过
		if(StringUtils.isNotBlank(checkListReq.getPcId())){
			//有pcId,则根据pcId删除记录
			weldLineInpectDao.deletePcIdIsNull(checkListReq.getPcId());
		}else{
			weldLineInpectDao.deletePcIdIsNull();
		}
	}
	@Override
	public Map<String, Object> queryRecords(WeldLineInpectReq req) {
		return weldLineInpectDao.queryRecords(req);
	}

	@Override
	public WeldLineInpect viewRecordById(String id) {
		return weldLineInpectDao.get(id);
	}

	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public String saveRecord(WeldLineInpect wi) throws Exception {
		boolean flag = false;
		if(StringUtils.isBlank(wi.getWliId())){
			wi.setWliId(IDUtil.getUniqueId(Constants.MODULE_CODE_WELD_LINE_INPECT));
			flag = true;
		}
		weldLineInpectDao.saveOrUpdate(wi);
		if(wi.getSign()!=null){
			signatureService.saveOrUpdateSign("menuId+menuNane+23",wi.getSign(), wi.getProjId(), wi.getWliId(), wi.getClass().getName(), flag);
		}
		return wi.getWliId();
	}

	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void deleteRecordById(String wliId) {
		weldLineInpectDao.delete(wliId);
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
		//焊工weldLeader
		//施工员qualityLeader
		if(StringUtils.isNotBlank(cwId)){
			ProjectChecklist list=projectCheckListDao.get(cwId);
			if(list!=null){
				if(StringUtils.isNotBlank(list.getWeldLeader())){
					//焊工通知置为无效
					signNoticeService.saveThisSignNotice(SignPostEnum.WELDER.getValue(), SignDataTypeEnum.WELDLINE_INPECT.getValue(),
							list.getPcId(), list.getProjId(),signState);
				}
				if(StringUtils.isNotBlank(list.getQualityLeader())){
					//施工员通知置为无效
					signNoticeService.saveThisSignNotice(SignPostEnum.CONSTRUCTION.getValue(), SignDataTypeEnum.WELDLINE_INPECT.getValue(),
							list.getPcId(), list.getProjId(),signState);
				}
			}
		}
	}

}
