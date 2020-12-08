package cc.dfsoft.project.biz.base.contract.service;

import java.util.List;

import cc.dfsoft.project.biz.base.contract.entity.PayType;

public interface PayTypeService {
	/**
	 * 根据合同类型查付款类型
	 * @param contractType
	 * @return
	 */
	public List<PayType> findByContractType(String contractType,String corpId);

	/**
	 * 描述:查询所有付款方式
	 * @author liaoyq
	 * @createTime 2018年10月16日
	 * @return
	 */
	public List<PayType> findAllByContractType();
}
