package cc.dfsoft.project.biz.base.settlement.service;

import cc.dfsoft.project.biz.base.design.dto.DesignDispatchDto;
import cc.dfsoft.project.biz.base.settlement.entity.SettlementDeclaration;
import cc.dfsoft.project.biz.base.subpackage.dto.SubQuantitiesDto;

public interface SettlementAuditDoneService {

	/**
	 * 查询结算报审
	 * @param id
	 * @return
	 */
	SettlementDeclaration auditStartDetail(String projId);

	/**
	 * 保存终审工程量
	 * @param 
	 * @return
	 */
	void batInsertSubQualities(SubQuantitiesDto qdto);

	/**
	 * 保存终审派遣
	 * @param 
	 * @return
	 */
	void update(DesignDispatchDto designDispatchReq);

}
