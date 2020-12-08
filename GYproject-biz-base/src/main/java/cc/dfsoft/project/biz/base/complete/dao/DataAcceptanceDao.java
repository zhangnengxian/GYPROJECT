package cc.dfsoft.project.biz.base.complete.dao;

import cc.dfsoft.project.biz.base.complete.entity.DataAcceptance;
import cc.dfsoft.uexpress.common.dao.CommonDao;

public interface DataAcceptanceDao extends CommonDao<DataAcceptance, String>{
	
	/**
	 * 根据工程id查询资料验收申请表
	 * @author fuliwei
	 * @createTime 2017年8月4日
	 * @param 
	 * @return
	 */
	public DataAcceptance findByProjId(String projId);
	
}
