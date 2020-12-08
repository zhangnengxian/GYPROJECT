package cc.dfsoft.project.biz.base.baseinfo.dao;

import java.util.List;
import java.util.Map;

import cc.dfsoft.project.biz.base.baseinfo.dto.DealyReasonQueryReq;
import cc.dfsoft.project.biz.base.baseinfo.entity.DelayReason;
import cc.dfsoft.uexpress.biz.sys.dept.entity.Department;
import cc.dfsoft.uexpress.common.dao.CommonDao;

public interface DelayReasonDao extends CommonDao<DelayReason, String>{
	

	/**
	 * 查询延期原因列表
	 * @author
	 * @createTime  2016-12-8
	 * @param
	 * @return
	 */
	public Map<String,Object> queryDealyReasonList(DealyReasonQueryReq dealyReasonQueryReq);
	
	/**
	 * 删除延期原因
	 * @author
	 * @createTime 2016-12-8
	 * @param
	 * @return
	 */
	public void delDealyReason(String id);
	
	/**
	 * 延期原因列表
	 * @return
	 */
	public List<DelayReason> findDelayReason();
}
