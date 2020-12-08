package cc.dfsoft.project.biz.base.project.dao;

import cc.dfsoft.project.biz.base.project.dto.OperateRecordQueryReq;
import cc.dfsoft.project.biz.base.project.entity.OperateRecord;
import cc.dfsoft.project.biz.base.project.entity.OperateWorkFlow;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.uexpress.common.dao.CommonDao;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

public interface OperateRecordDao extends CommonDao<OperateRecord, String>{
	
	/**
	 * 操作记录列表条件查询
	 * @author pengtt
	 * @CreateTime 2016-07-13
	 * @param operateRecordQueryReq
	 * @return
	 */
	public Map<String, Object> queryOperateRecord(OperateRecordQueryReq operateRecordQueryReq);
	/**
	 * 根据工程id获取第一条业务操作记录（按时间降序排列）
     * @author zhangjj
     * @createTime 2016-07-13
	 */
	public List<Map<String, Object>> getOptRecordByTime(String projStatus);
	
	/**
	 * 用于详述列表展示
	 * @author pengtt
	 * @CreateTime 2016-07-14
	 * @param operateRecordQueryReq
	 * @return
	 * @throws ParseException 
	 */
	public Map<String,Object> queryOperateRecordBySql(OperateRecordQueryReq operateRecordQueryReq) throws ParseException;
	
	/**
	 * 用于甘特图统计查询
	 * @author pengtt
	 * @createTime 2016-08-09
	 * @param projId
	 * @return
	 */
	public List<Map<String, Object>> querySchedule(String projId);
	
	/**
	 * 注意：立项、设计、预算等称为环节；受理申请、设计派遣等称为步骤；
	 * 获取某一环节，最早形成操作记录的步骤id、步骤名称
	 * @author pengtt
	 * @createTime 2016-08-10
	 * @param projId  工程id
	 * @param type    环节类型
	 * @return
	 */
	public Map<String,Object> findByProjIdType(String projId,String type);
	/**
	 * 用于工程进度流程图查询
	 * @author zhangjj
	 * @createTime 2016-10-14
	 * @param projId
	 * @return
	 */
	public List<Map<String, Object>> queryScheduleFlow(String projId);
	
	/**
	 * 将历史记录置为无效
	 * @author fuliwei
	 * @createTime 2017年12月15日
	 * @param 
	 * @return
	 */
	public void updateOrValid(List<String> stepIds,String projId);

	
	/**
	 * 关联操作查询业务操作记录
	 * @author fuliwei  
	 * @date 2018年10月6日  
	 * @version 1.0
	 */
	public Map<String,Object> queryOperateRecordList(OperateRecordQueryReq req);
	
	/**
	 * 查询下一审核级别的操作记录
	 * @author fuliwei  
	 * @date 2018年10月7日  
	 * @version 1.0
	 */
	public OperateRecord findByGread(String projId,String corpId,String projectType,String conMode,String stepId,String grade,String modifyStatus);
	
	/**
	 * 将历史记录置为无效 且未办--待定
	 * @author fuliwei  
	 * @date 2018年10月7日  
	 * @version 1.0
	 */
	public void updateOrValidAndNotDone(List<String> stepIds,String projId);
	
	/**
	 * 将历史记录置为无效
	 * @author fuliwei  
	 * @date 2018年10月17日  
	 * @version 1.0
	 */
	public void batUpdateHistoryRecordByBoId(String projId,String businessorderId,String stepId);
	
	/**
	 * 查询之前最大时间的操作记录 如审核回退时需重新生成操作记录
	 * @author fuliwei  
	 * @date 2018年10月17日  
	 * @version 1.0
	 */
	public OperateRecord findByMaxTime(String projId,String corpId,String projectType,String conMode,String stepId,String grade,String modifyStatus);
	

	/**
	 * 
	 * 描述:查询最新的操作记录
	 * @author liaoyq
	 * @createTime 2018年11月1日
	 * @param operateRecordQueryReq
	 * @return
	 */
	OperateRecord queryEndOperateRecord(OperateRecordQueryReq operateRecordQueryReq);
	/**
	 * 查询工作通知
	 * @param projNo
	 * @param Step
	 * @param projectType
	 * @throws Exception
	 */
	public OperateRecord queryWorkNotice(String projNo,String stepId,String projectType) throws Exception ;
	/**
	 * 注释：取消待办
	 * @author liaoyq
	 * @createTime 2018年12月7日
	 * @param pro
	 *
	 */
	public void cancelTodo(String projId);

	/**
	 *回退审核时查找已生成的操作记录
	 * @param projId
	 * @param corpId
	 * @return
	 */
	public List<OperateRecord> findByMaxTimeList(String projId,String corpId,String projectType,String conMode,String stepId,String grade,String modifyStatus);
 
	/**
	 * 
	 * @param projId
	 * @param designerId
	 * @return
	 */
	List<OperateRecord> getOptRecordListByProjIdOrOperator(String projId, String operator);
	/**
	 * 删除操作流时，查询出相关操作记录进行处理
	 * @param req
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> queryOperateRecordLists(OperateRecordQueryReq req)throws Exception;
	
	/**
	 * 根据操作流更新操作记录
	 * @param operateWorkFlow
	 * @throws Exception
	 */
	public void  updateOperateRecord (OperateWorkFlow operateWorkFlow) throws Exception;

	List<OperateRecord> queryListByProjIdAndStatus(String projId, String s);

	List<OperateRecord> findListByProjStepStatusId(String projId, String stepId,String status);

	/**
	 * 注释：将待办变为已办
	 * @author liaoyq
	 * @createTime 2019年5月21日
	 * @param project
	 * @param stepId
	 * @param opType
	 * @param busOrderId
	 *
	 */
	public void updateIsDone(Project project,String stepId, String opType,
			String busOrderId ,String grade);
	/**
	 * 
	 * 注释：根绝业务单ID和流程类型作废操作记录
	 * @author liaoyq
	 * @createTime 2019年5月21日
	 * @param busOrderId
	 * @param opType
	 *
	 */
	public void updateIsValid(String busOrderId, String opType);

	List<OperateRecord>  queryOperateRecordList(String businessOrderId,String stepId, String modifyStatus, String grade);

	List<OperateRecord> queryListByStepIds(String projId, String status, List<String> stepIds);

    boolean backSetToDoInValid(String projId,List<String> stepIds);
    void backSetToDoInValid(String busiessId,String projId,List<String> stepIds);

	OperateRecord queryOperater(String projId, String stepId, String grade);

    /**
	 * 
	 * 注释：根据条件对象查询待办操作
	 * @author liaoyq
	 * @createTime 2019年8月30日
	 * @param req
	 * @return
	 *
	 */
	List<OperateRecord> queryOperateRecords(OperateRecordQueryReq req);
	List<Map<String, Object>> queryOperateRecordTodo();

    String queryTodoer(String businessId);

    void noticeSetInvalid(List<String> projIds, String stepId, String opType);

	void backStepSetNeedDealtWith(String busiessId,String projId,String stepId);

    List<OperateRecord> findListByStepId(String projId, String stepId);

	void recoveryIsValid(String projId, String[] stepIds);

    void recoverySetTodoer(String projId, String stepId, String grade);

    /**
	 * 查询所有的操作记录待办
	 * @author fuliwei
	 * @date 2019/8/26
	 */

	/**
	 * 受理申请时删除除市场派工外的所有操作记录
	 * @author fuliwei
	 * @date 2019/12/10
	 */
    void deleteByProjId(String projId);

    void changeBudgeterTodo(String projId, String orgBudgeterAuditId, String staffId, String staffName);
}
