package cc.dfsoft.project.biz.base.inspection.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cc.dfsoft.project.biz.base.inspection.dao.PipeLineInstallDao;
import cc.dfsoft.project.biz.base.inspection.dao.ProjectChecklistDao;
import cc.dfsoft.project.biz.base.inspection.dto.PipelineInstallReq;
import cc.dfsoft.project.biz.base.inspection.dto.ProjectCheckListPLIReq;
import cc.dfsoft.project.biz.base.inspection.entity.PipeLineInstall;
import cc.dfsoft.project.biz.base.inspection.entity.ProjectChecklist;
import cc.dfsoft.project.biz.base.inspection.service.PipeLineInstallService;
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
 * 管道安装业务实现层
 * @author liaoyq
 *
 */
@Service
@Transactional(readOnly = true,propagation=Propagation.REQUIRED)
public class PipeLineInstallServiceImpl implements PipeLineInstallService {

	@Resource
	PipeLineInstallDao pipeLineInstallDao;
	
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
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void saveRecords(ProjectCheckListPLIReq checkListReq) {
		if(checkListReq.getList().size()>0){
			//删除所有的焊条领用记录
			pipeLineInstallDao.deleteByPcId(checkListReq.getPcId());
			//批量插入记录数据
			List<PipeLineInstall> list = checkListReq.getList();
			List<PipeLineInstall> listNew = new ArrayList<PipeLineInstall>();
			for(PipeLineInstall er : list){
				//生成erId;
				er.setPliId(IDUtil.getUniqueId(Constants.MODULE_CODE_PIPELINE_INSTALL));
				listNew.add(er);
			}
			pipeLineInstallDao.batchInsertObjects(listNew);
		}else{
			pipeLineInstallDao.deleteByPcId(checkListReq.getPcId());
		}
	}

	@Override
	public Map<String, Object> queryPipelineInstall(
			PipelineInstallReq pipelineInstallReq) {
		return pipeLineInstallDao.queryPipelineInstall(pipelineInstallReq);
	}

	@Override
	public PipeLineInstall viewRecordById(String id) {
		return pipeLineInstallDao.get(id);
	}

	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public String saveRecord(PipeLineInstall pli) {
		if(StringUtils.isBlank(pli.getPliId())){
			pli.setPliId(IDUtil.getUniqueId(Constants.MODULE_CODE_PIPELINE_INSTALL));
		}
		pipeLineInstallDao.saveOrUpdate(pli);
		return pli.getPliId();
	}

	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void deleteRecordById(String pliId) {
		pipeLineInstallDao.delete(pliId);
	}

	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void updatePcIdByRecordsId(String recordsId, String pcId,String projId,String projNo) {
		//先置空之前报验的pcId
		pipeLineInstallDao.updateByPcId(pcId);
		//质空附件表中的报验单ID
				accessoryService.clearBRId(projId,projNo,pcId);
				String [] ids = recordsId.split(",");
				//
				for(String id : ids){
					PipeLineInstall record = pipeLineInstallDao.get(id);
					if(record!=null){
						pipeLineInstallDao.updatePcIdByRecordId(id,pcId);
						//回写拍照pcId
						accessoryDao.updateBId(pcId,record.getProjId(),id,AccessorySourceEnum.PHOTO_FILE.getValue());
					}
				}
	}

	@Override
	public Map<String, Object> queryRecords(
			PipelineInstallReq pipelineInstallReq) {
		return pipeLineInstallDao.queryRecords(pipelineInstallReq);
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
					signNoticeService.saveThisSignNotice(SignPostEnum.WELDER.getValue(), SignDataTypeEnum.PIPELINEINSTALL.getValue(),
							list.getPcId(), list.getProjId(),signState);
				}
				if(StringUtils.isNotBlank(list.getTechnicalLeader())){
					//施工员
					signNoticeService.saveThisSignNotice(SignPostEnum.CONSTRUCTION.getValue(), SignDataTypeEnum.PIPELINEINSTALL.getValue(),
							list.getPcId(), list.getProjId(),signState);
				}
				if(StringUtils.isNotBlank(list.getReviewer())){
					//项目经理
					signNoticeService.saveThisSignNotice(SignPostEnum.CU_PM.getValue(), SignDataTypeEnum.PIPELINEINSTALL.getValue(),
							list.getPcId(), list.getProjId(),signState);
				}
			}
		}
		
	}
}
