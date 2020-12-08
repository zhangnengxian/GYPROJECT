package cc.dfsoft.project.biz.base.contract.dao;

import cc.dfsoft.project.biz.base.contract.entity.PayType;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.uexpress.common.dao.CommonDao;

import java.util.List;

public interface PayTypeDao extends CommonDao<PayType, String>{

	/**
	 * 根据合同类型查付款类型
	 * @param contractType
	 * @return
	 */
	List<PayType> findByContractType(String contractType,String corpId);

	/**
	 * 根据工程类型查支付方式
	 * @param projLtypeId
	 * @return
	 */
    List<PayType> findByProjType(Project project);

    /**
     * 
     * 描述:查询所有付款方式
     * @author liaoyq
     * @createTime 2018年10月16日
     * @return
     */
	List<PayType> findAllByContractType();
}
