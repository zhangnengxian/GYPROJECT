package cc.dfsoft.project.biz.base.project.service;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import cc.dfsoft.project.biz.base.project.dto.ApplyDelayReq;
import cc.dfsoft.project.biz.base.project.entity.ApplyDelay;

/**
 * 延期申请接口
 * @author zhangjj
 *
 */
public interface ApplyDelayService {
	
	public void saveApplyDelay(ApplyDelay applyDelay);
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
	 * 查询详述
	 * @author fuliwei
	 * @createTime 2017年9月1日
	 * @param 
	 * @return
	 */
	public ApplyDelay findById(String adId);
	
	
	/**
	 * 延期审核列表查询
	 * @author fuliwei
	 * @createTime 2017年8月31日
	 * @param 
	 * @return
	 */
	public Map<String, Object> queryApplyDelayAudit(ApplyDelayReq req)throws ParseException;
	
	

}
