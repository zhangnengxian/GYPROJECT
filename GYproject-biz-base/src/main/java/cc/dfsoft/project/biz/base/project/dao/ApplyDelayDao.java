package cc.dfsoft.project.biz.base.project.dao;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import cc.dfsoft.project.biz.base.project.dto.ApplyDelayReq;
import cc.dfsoft.project.biz.base.project.entity.ApplyDelay;
import cc.dfsoft.uexpress.common.dao.CommonDao;
/**
 * 延期申请Dao
 * @author zhangjj
 *
 */
public interface ApplyDelayDao extends CommonDao<ApplyDelay, String>{
	public List<ApplyDelay> findADelayByStepId(String stepId,String projId);
	
	/**
	 * 延期申请列表查询
	 * @author fuliwei
	 * @createTime 2017年8月31日
	 * @param 
	 * @return
	 */
	public Map<String, Object> queryApplyDelay(ApplyDelayReq req)throws ParseException;

	/**
	 * 获取审批通过的延期申请天数
	 * @return
	 */
	public BigDecimal getDelyDays(String projId,String stepId);
}
