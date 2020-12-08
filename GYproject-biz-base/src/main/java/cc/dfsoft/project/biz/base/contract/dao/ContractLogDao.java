package cc.dfsoft.project.biz.base.contract.dao;

import cc.dfsoft.project.biz.base.contract.entity.ContractLog;
import cc.dfsoft.uexpress.common.dao.CommonDao;
/**
 * 合同日志Dao
 * @author Yuanyx
 *
 */
public interface ContractLogDao extends CommonDao<ContractLog, String>{

	/**
	 * 根据修改类型和日志id查合同修改日志
	 * @param modifystate
	 * @param orlId
	 * @return
	 */
	ContractLog findByModifystate(String modifystate, String orlId);

}
