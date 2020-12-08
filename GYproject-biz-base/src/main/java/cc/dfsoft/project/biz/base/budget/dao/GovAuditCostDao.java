package cc.dfsoft.project.biz.base.budget.dao;

import cc.dfsoft.project.biz.base.budget.entity.GovAuditCost;
import cc.dfsoft.uexpress.common.dao.CommonDao;
/**
 * 
 * 描述:政府审定价dao
 * @author liaoyq
 * @createTime 2017年9月8日
 */
public interface GovAuditCostDao extends CommonDao<GovAuditCost, String> {

	/**
	 * 根据工程ID和审定价类型查询审定价详述
	 * @param projId
	 * @param gACType
	 * @return
	 */
	GovAuditCost queryByProjIdAndType(String projId, String gACType);

}
