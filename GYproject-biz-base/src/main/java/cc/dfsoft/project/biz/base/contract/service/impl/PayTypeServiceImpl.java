package cc.dfsoft.project.biz.base.contract.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cc.dfsoft.project.biz.base.contract.dao.PayTypeDao;
import cc.dfsoft.project.biz.base.contract.entity.PayType;
import cc.dfsoft.project.biz.base.contract.service.PayTypeService;
import cc.dfsoft.project.biz.base.project.dao.ContributionModeDao;
import cc.dfsoft.project.biz.base.project.entity.ContributionMode;
import cc.dfsoft.project.biz.base.project.service.ContributionModeService;

@Service
@Transactional(readOnly = true,propagation=Propagation.REQUIRED)
public class PayTypeServiceImpl implements PayTypeService{
	
	/**付款方式dao*/
	@Resource
	PayTypeDao payTypeDao;
	/**
	 * 根据合同类型查付款类型
	 * @param contractType
	 * @return
	 */
	@Override
	public List<PayType> findByContractType(String contractType,String corpId) {
		return payTypeDao.findByContractType(contractType,corpId);
	}
	/**
	 * 查询所有合同类型
	 */
	@Override
	public List<PayType> findAllByContractType() {
		return payTypeDao.findAllByContractType();
	}

}
