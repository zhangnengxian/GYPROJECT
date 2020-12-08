package cc.dfsoft.project.biz.base.constructmanage.service;

import java.util.List;
import java.util.Map;

import cc.dfsoft.project.biz.base.constructmanage.dto.CompletionDataPrintDto;
import cc.dfsoft.project.biz.base.constructmanage.dto.DailyLogQueryReq;
import cc.dfsoft.project.biz.base.constructmanage.entity.DailyLog;

/**
 * 工程日志service
 * @author pengtt
 * @createTime 2016-07-21
 *
 */
public interface DailyLogService {
	
	/**
	 * 工程日志
	 * @param reviewRecordQueryReq
	 * @return
	 */
	public Map<String, Object> queryDailyLog(DailyLogQueryReq dailyLogQueryReq);
	
	/**
	 * 新增或修改对象
	 * @param dailyLog
	 */
	public Map<String, Object> saveDailyLog(DailyLog dailyLog) throws Exception;

   /**
    * 根据公司ID、菜单ID查找报表
    * @author wanghuijun
    * @createTime 2018年10月8日
    * @param projId
    * @param type
    * @return
    */
	public List<Object> findPrintDataByProjId(String projId,String type);
	/**
	 * 删除工程日志，根据dlId
	 * @param dlId
	 * @return
	 * @throws Exception
	 */
	public void byDlIdDeletedialy (String dlId) throws Exception;
}
