package cc.dfsoft.project.biz.base.baseinfo.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cc.dfsoft.project.biz.base.baseinfo.dao.SystemSetDao;
import cc.dfsoft.project.biz.base.baseinfo.service.SystemSetService;
import cc.dfsoft.project.biz.base.common.entity.SystemSet;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.util.IDUtil;
import cc.dfsoft.uexpress.common.util.StringUtil;

@Service
@Transactional(readOnly = true,propagation=Propagation.REQUIRED)
public class SystemSetServiceImpl implements SystemSetService{

    @Resource
    SystemSetDao systemSetDao;
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void saveSystemSet(SystemSet systemSet) {	
		if(!StringUtil.isNotBlank(systemSet.getSysId())){
			systemSet.setSysId(IDUtil.getUniqueId(Constants.MODULE_CODE_AUTH));
		}		
		systemSetDao.saveOrUpdate(systemSet);
	}
	@Override
	public SystemSet querySystemSet(String menuId,String corpId) {
		return systemSetDao.querySystemSet(menuId, corpId);
	}
	@Override
	public SystemSet querySystemSetByStepId(String stepId) {
		
		return systemSetDao.get("stepId", stepId);
	}
 
	
}
