package cc.dfsoft.project.biz.base.contract.service;

import cc.dfsoft.project.biz.base.subpackage.entity.IntelligentMeterContractLog;

/**
 * 智能表合同修改接口
 * @author cui
 *
 */
public interface ImcContractLogService {

	/**
	 * 根据修改类型和日志id查合同修改日志
	 * @param modifystate
	 * @param orlId
	 * @return
	 */
	IntelligentMeterContractLog findByModifystate(String modifystate, String orlId);
}
