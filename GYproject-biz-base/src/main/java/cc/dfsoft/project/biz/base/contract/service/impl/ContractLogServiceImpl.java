package cc.dfsoft.project.biz.base.contract.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cc.dfsoft.project.biz.base.contract.dao.ContractLogDao;
import cc.dfsoft.project.biz.base.contract.entity.ContractLog;
import cc.dfsoft.project.biz.base.contract.service.ContractLogService;

@Service
@Transactional(readOnly = true,propagation=Propagation.REQUIRED)
public class ContractLogServiceImpl implements ContractLogService{

	/** 合同修改日志*/
	@Resource
	ContractLogDao contractLogDao;
	@Override
	public ContractLog findByModifystate(String modifystate, String orlId) {
		return contractLogDao.findByModifystate(modifystate,orlId);
	}
	
}
