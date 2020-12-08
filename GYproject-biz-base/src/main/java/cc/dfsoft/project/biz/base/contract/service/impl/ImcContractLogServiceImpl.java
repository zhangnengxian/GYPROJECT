package cc.dfsoft.project.biz.base.contract.service.impl;

import cc.dfsoft.project.biz.base.contract.dao.ImcContractLogDao;
import cc.dfsoft.project.biz.base.contract.service.ImcContractLogService;
import cc.dfsoft.project.biz.base.subpackage.entity.IntelligentMeterContractLog;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional(readOnly = true,propagation=Propagation.REQUIRED)
public class ImcContractLogServiceImpl implements ImcContractLogService {

	/** 智能表合同修改日志*/
	@Resource
	ImcContractLogDao contractLogDao;

	@Override
	public IntelligentMeterContractLog findByModifystate(String modifystate, String orlId) {
		return contractLogDao.findByModifystate(modifystate,orlId);
	}
	
}
