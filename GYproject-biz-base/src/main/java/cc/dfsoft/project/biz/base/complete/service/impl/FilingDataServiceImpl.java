package cc.dfsoft.project.biz.base.complete.service.impl;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cc.dfsoft.project.biz.base.complete.dao.FilingDataDao;
import cc.dfsoft.project.biz.base.complete.entity.FilingData;
import cc.dfsoft.project.biz.base.complete.service.FilingDataService;
import cc.dfsoft.project.biz.base.project.dao.ProjectDao;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.project.enums.StepEnum;
import cc.dfsoft.project.biz.base.project.service.OperateRecordService;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.enums.WorkFlowActionEnum;
import cc.dfsoft.uexpress.common.util.IDUtil;
import cc.dfsoft.uexpress.common.util.WorkFlowUtil;
@Service
@Transactional(readOnly = true,propagation=Propagation.REQUIRED)
public class FilingDataServiceImpl implements FilingDataService {

	@Resource
	FilingDataDao filingDataDao;
	
	@Resource
	ProjectDao projectDao;
	
	/**业务操作记录服务接口*/
	@Resource
	OperateRecordService operateRecordService;
	
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void saveFilingData(FilingData filingData) {
		if(StringUtils.isBlank(filingData.getFdId())){
			filingData.setFdId(IDUtil.getUniqueId(Constants.MODULE_CODE_COMPLETE));
		}
		filingDataDao.saveOrUpdate(filingData);
		//更新工程信息
		Project pro=projectDao.get(filingData.getProjId());         //通过工程id查找Project
		//生成工程状态
		String statusId=WorkFlowUtil.workFlowStatus(WorkFlowActionEnum.COMPLETION_TRANSFER.getActionCode());
		pro.setProjStatusId(statusId); 								//设置工程状态
		projectDao.saveOrUpdate(pro);
		
		//形成操作记录
		String orId = IDUtil.getUniqueId(Constants.MODULE_CODE_COMPLETE);
		//operateRecordService.createOperateRecord(orId, filingData.getProjId(), pro.getProjNo(), StepEnum.COMPLETION_TRANSFER.getValue(), StepEnum.COMPLETION_TRANSFER.getMessage(), "");
	}
}