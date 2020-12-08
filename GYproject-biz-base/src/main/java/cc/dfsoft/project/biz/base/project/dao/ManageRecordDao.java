package cc.dfsoft.project.biz.base.project.dao;

import cc.dfsoft.project.biz.base.project.dto.ManageRecordQueryReq;
import cc.dfsoft.project.biz.base.project.entity.ManageRecord;
import cc.dfsoft.uexpress.common.dao.CommonDao;

import java.util.List;
import java.util.Map;

/**
 * 管理记录dao
 * @author pengtt
 * @createTime 2016-06-27
 *
 */
public interface ManageRecordDao  extends CommonDao<ManageRecord, String>{
	
	/**
	 * 查询管理记录
	 * @author pengtt
	 * @createTime 2016-06-27
	 * @param manageRecordQueryReq 查询对象
	 * @return
	 */
	public Map<String,Object> queryManageRecord(ManageRecordQueryReq manageRecordQueryReq);
	
	/**
	 * 通过工程id查询管理记录
	 * 以 审核时间降序
	 * @author pengtt
	 * @createTime 2016-06-27
	 * @param projId 工程id
	 * @return
	 */
	public List<ManageRecord> findByProjId(String projId);
	
	/**
	 * 通过工程Id查询管理记录 以时间降序
	 * @author
	 * @createTime 2016-7-5
	 * @param
	 * @return
	 */
	public List<ManageRecord> findByStepIdProjId(String projId,String stepId);
	
	
	/**
	 * 查询管理记录(按工程id分组，时间降序排列，取最后一条)
	 * @author zhangjj
	 * @createTime 2016-7-14
	 * @param projStatus 工程状态
	 * @return
	 */
	public List<Map<String, Object>> getManageRecordByTime(String projStatus);
	
	/**
	 * 通过工程Id、步骤id、审核结果查询管理记录 以时间降序
	 * @author pengtt
	 * @createTime 2016-08-13
	 * @param
	 * @return
	 */
	public List<ManageRecord> findByStepIdProjId(String projId,String stepId,String auditResult);

	public List<ManageRecord> findByStepIdBusId(String busId,String stepId,String auditResult);

	
	/**
	 * 更新作废标记
	 * 注：此方法用于若此次审核不通过，则更新此工程的步骤的审核记录为作废
	 * @param projId
	 * @param stepId
	 */
	public void batUpdateHistoryRecord(String projId,String stepId);
	
	/**
	 * 查询审核结果(根据工程ID 和 步骤ID)
	 * @author ht.hu
	 * @param projId
	 * @param StepId
	 * @return
	 */
	public ManageRecord queryAuditInformation(String projId, String StepId);


	public ManageRecord queryAuditInsInformation(String businessOrderId);

	/**
	 * 通过业务单Id查询管理记录 以时间降序
	 * @param businessOrderId
	 * @param stepId
	 * @param auditResult
	 * @return
	 */
	public List<ManageRecord> findByStepIdBusinessOrderId(String businessOrderId, String stepId, String auditResult);
	
	/**
	 * 更新作废标记
	 * 注：此方法用于若此次审核不通过，则更新此步骤的审核记录为作废
	 * @param businessorderId
	 * @param stepId
	 */
	public void batUpdateHistoryRecordByBoId(String businessorderId,String stepId);

	/**
	 * 根据工程id、步骤id、审核人id查询审核通过的信息
	 * @author liaoyq
	 * @createTime 2017年10月25日
	 * @param projId
	 * @param stepId
	 * @param mrCsr
	 */
	public ManageRecord findByMrCsrId(String projId, String stepId, String mrCsr);

	/**
	 * 根据工程id、步骤id、审核级别查询审核通过的信息
	 * @author liaoyq
	 * @createTime 2017年10月25日
	 * @param projId
	 * @param stepId
	 * @param level
	 */
	public ManageRecord findFirstMrCsr(String projId, String stepId,
			String level);
	
	/**
	 * 查询业务单审核信息结果
	 * @author fuliwei
	 * @createTime 2017年12月1日
	 * @param 
	 * @return
	 */
	public ManageRecord queryBusAuditInformation(String businessOrderId, String StepId);

	/**
	 * 由于多人同时操作同一审核级别，导致审核历史有问题，如果两人人都操作为通过，则第一级审核，没有显示为绿色，一直未审核，下一级审核已发送通知，但是审核不了
	 * @author liaoyq
	 * @createTime 2018年7月3日
	 * @param manageRecord
	 */
	public void saveManageRecord(ManageRecord manageRecord);

	/**
	 * 获取最后一次审批的信息
	 * @author liaoyq
	 * @createTime 2018-10-11
	 * @param queryReq
	 * @return
	 */
	public ManageRecord findEndMrCsr(ManageRecordQueryReq queryReq);
	
	/**
	 * 处理审核记录使用
	 * @author fuliwei  
	 * @date 2018年11月6日  
	 * @version 1.0
	 */
	public List<ManageRecord> findByStepId(String projId, String stepId);
	/**
	 * 根据业务ID更新审核历时，签证审核历时使用
	 * 王会军 2019-2-21 
	 * @param businessOrderId
	 * @param stepId
	 */
	public void updateAuditRecord (String businessOrderId,String stepId);

	/**
	 * 审核查询审核历史
	 * @param projId
	 * @param stepId
	 * @param auditResult
	 * @return
	 */
	List<ManageRecord> findByStepIdProjIdIsPass(String projId, String stepId,
			String auditResult);
	/**
	 * 查询当前审核级别是否已审核
	 * @param manageRecord
	 * @return
	 */
	public ManageRecord queryManRdHistory(String projId,String businssId,String stepId,String MrAuditLevel,String flag);

	/**
	 * 
	 * 注释：查询审核通过的数据，有重复审核数据，导致审核按钮不能点击，过滤时进行分组
	 * @author liaoyq
	 * @createTime 2019年5月22日
	 * @param busId
	 * @param stepId
	 * @param auditResult
	 * @return
	 *
	 */
	List<ManageRecord> findByStepIdBusIdIsPass(String busId, String stepId,
			String auditResult);

	/**
	 * 
	 * 注释：保存审核信息时先判断该工程该业务单据当前级别是否已有审核有效历史
	 * @author liaoyq
	 * @createTime 2019年5月22日
	 * @param manageRecord
	 * @return
	 *
	 */
	String saveManageRecordNew(ManageRecord manageRecord);

	/**
	 * 注释：根据条件查询审核历史列表
	 * @author liaoyq
	 * @createTime 2019年7月30日
	 * @param queryReq
	 * @return
	 *
	 */
	public List<ManageRecord> queryManageRecords(ManageRecordQueryReq queryReq);

	/**
	 * 
	 * 注释：查询签证预算调整的审核历史记录
	 * @author liaoyq
	 * @createTime 2019年8月13日
	 * @param evId
	 * @param stepId
	 * @return
	 *
	 */
	public ManageRecord findEvBudgetDate(String evId, String stepId);

    boolean backSetAuditRecordInValid(String projId, List<String> stepIds);

    void auditRecordsetValid(String businessId, String projId, List<String> stepIds);

    boolean isExist(String businessOrderId, String mrAuditLevel, String stepId);

	List<Map<String, Object>> queryStepIdByProjId(String projId);
}
