package cc.dfsoft.project.biz.base.contract.service;

import cc.dfsoft.project.biz.base.contract.entity.ContractLog;

/**
 * 合同修改接口
 * @author Yuanyx
 *
 */
public interface ContractLogService {

	/**
	 * 根据修改类型和日志id查合同修改日志
	 * @param modifystate
	 * @param orlId
	 * @return
	 */
	ContractLog findByModifystate(String modifystate, String orlId);
}
