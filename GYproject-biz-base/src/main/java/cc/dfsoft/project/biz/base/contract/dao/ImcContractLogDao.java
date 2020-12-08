package cc.dfsoft.project.biz.base.contract.dao;

import cc.dfsoft.project.biz.base.subpackage.entity.IntelligentMeterContractLog;
import cc.dfsoft.uexpress.common.dao.CommonDao;

/**
 * 智能表合同日志Dao
 * @author Cui
 *
 */
public interface ImcContractLogDao extends CommonDao<IntelligentMeterContractLog, String>{

	/**
	 * 根据修改类型和日志id查合同修改日志
	 * @param modifystate
	 * @param orlId
	 * @return
	 */
	IntelligentMeterContractLog findByModifystate(String modifystate, String orlId);

}
