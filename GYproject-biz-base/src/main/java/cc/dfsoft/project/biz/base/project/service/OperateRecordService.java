
package cc.dfsoft.project.biz.base.project.service;

import cc.dfsoft.project.biz.base.maintain.entity.AbandonedRecord;
import cc.dfsoft.project.biz.base.maintain.entity.ResultInfo;
import cc.dfsoft.project.biz.base.project.dto.OperateRecordQueryReq;
import cc.dfsoft.project.biz.base.project.entity.FallbackApply;
import cc.dfsoft.project.biz.base.project.entity.OperateRecord;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.biz.sys.dept.dto.StaffDto;
import cc.dfsoft.uexpress.biz.sys.dept.entity.Staff;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author pengtt
 * @createTime 2016-06-28
 *
 */
public interface OperateRecordService{

	/**
	 * 创建业务操作记录
	 * @author pengtt
	 * @createTime 2016-06-28
	 *
	 * @param orId   主键id
	 * @param projId 工程id
	 * @param projNo 工程编号
	 * @param stepId 步骤id
	 * @param stepName 步骤名称
	 * @param remark  备注
	 */
	public String createOperateRecord(String orId,String projId,String projNo,String stepId,String stepName,String remark);

	/**
	 * 生成操作记录，如第三方预算生成造价确认的代办人
	 * @author fuliwei
	 * @date 2018年11月18日
	 * @version 1.0
	 */
	public String createOperateRecordNext(String orId,String projId,String projNo,String stepId,String stepName,String remark,String nextStepId);


	/**
	 * 业务操作记录列表查询
	 * @author pengtt
	 * @createTime 2016-07-13
	 * @param request
	 * @param operateRecordQueryReq
	 * @return
	 * @throws ParseException
	 */
	public Map<String,Object> queryOperateRecord(OperateRecordQueryReq operateRecordQueryReq) throws ParseException;

	/**
	 *
	 */
	public List<Map<String, Object>> querySchedule(String projId);

	/**
	 * 用于工程进度流程图查询
	 * @author zhangjj
	 * @createTime 2016-10-14
	 * @param projId
	 * @return
	 */
	public List<Map<String, Object>> queryScheduleFlow(String projId);

	public String createOperateRecordByOther(String orId, String projId,
											 String projNo, String stepId, String stepName, String remark,Project pro);

	/**
	 * 关联操作查询业务操作记录
	 * @author fuliwei
	 * @date 2018年10月6日
	 * @version 1.0
	 */
	public Map<String,Object> queryOperateRecordList(OperateRecordQueryReq req);

	/**
	 * 立项时创建操作记录表
	 * @author fuliwei
	 * @date 2018年9月7日
	 * @version 1.0
	 */
	public HashMap<String,Object> cerateOperateWorkFlowRecord(Project pro, String stepId, LoginInfo login, String wfTypeId,
			String wfAssistTypeId);

	/**
	 * 生成下一个操作通知(派工时调用)
	 * @author fuliwei
	 * @date 2018年9月9日
	 * @version 1.0
	 */
	public void createNextOperateRecord(String orId,String projId,String projNo,String stepId,String stepName,String remark,String operateId, String operater );


	/**
	 * 处理操作记录，如选择设计员后处理设计员相关的记录
	 * @author fuliwei
	 * @date 2018年10月7日
	 * @version 1.0
	 */
	public void updateAboutOperateRecord(Project pro,String stepId,String OperateCsr1,String operater);


	/**
	 * 处理施工操作记录，如选择项目经理后处理相关的记录，只按工程类型，不算出资方式
	 * @author fuliwei
	 * @date 2018年10月7日
	 * @version 1.0
	 */
	public void updateConAboutOperateRecord(Project pro,String stepId,String OperateCsr1,String operater,String postId);


	/**
	 * 将历史记录置为无效  主流程审核时businessorderId传空 
	 * @author fuliwei
	 * @date 2018年10月17日
	 * @version 1.0
	 */
	public String batUpdateHistoryRecordByBoId(String projId,String businessorderId,String stepId);


	/**
	 * 查下一个审核操作记录
	 * @author fuliwei
	 * @date 2018年10月17日
	 * @version 1.0
	 */
	public OperateRecord findNextGrade(String projId,String corpId,String projectType,String conMode,String stepId,String grade,String modifyStatus,String busId);

	/**
	 * 将历史记录置为无效  退回到第一级审核
	 * @author fuliwei
	 * @date 2018年10月17日
	 * @version 1.0
	 */
	public String batUpdateHistoryRecordByBoIdToFirst(String projId,String businessorderId,String stepId);

	/**
	 * 处理历史数据-点击按钮后作废
	 * @author fuliwei
	 * @date 2018年11月5日
	 * @version 1.0
	 */
	public void updateHistoryOperateRecord(String projId,String corpId);


	/**
	 * 处理历史数据(批量处理)-点击按钮后作废
	 * @author fuliwei
	 * @date 2018年11月18日
	 * @version 1.0
	 */
	public void updateHistoryOperateRecordList(String projectType,String corpId)throws ParseException ;

	/**
	 * 处理历史审核记录
	 * @author fuliwei
	 * @date 2018年11月6日
	 * @version 1.0
	 */
	public void updateHistoryManagERecord(String projectType,String corpId);

	/**
	 * 批量处理审核记录
	 * @author fuliwei
	 * @date 2018年11月18日
	 * @version 1.0
	 */
	public void updateHistoryManagERecordList(String projectType,String corpId)throws ParseException ;

	/**
	 * 处理施工计划
	 * @author fuliwei
	 * @date 2018年11月18日
	 * @version 1.0
	 */
	public void updateHistoryConsPlan(String projId,String corpId);

	/**
	 * 批量处理
	 * @author fuliwei
	 * @date 2018年11月18日
	 * @version 1.0
	 */
	public void updateHistoryConsPlanList(String projectType,String corpId)throws ParseException ;

	/**
	 * 处理每个工程的待办人
	 * @author fuliwei
	 * @date 2018年11月18日
	 * @version 1.0
	 */
	public void  updateToder(String projId,String corpId);
	/**
	 * 查询工作通知
	 * @param projNo
	 * @param Step
	 * @param projectType
	 * @param designerId
	 * @param designer
	 * @throws Exception
	 */
	public OperateRecord queryWorkNotice(String projNo,String stepId,String projectType) throws Exception ;

    void saveOperateRecord(String projId);
    
	/**
	 * 工程一栏处查看操作历史
	 * @param req
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> queryOperateRecordHistory(OperateRecordQueryReq req) throws Exception;

	/**
	 * 注释：将当前状态置为已办，指定下一状态置为待办
	 * @param orId
	 * @param projId
	 * @param projNo
	 * @param curStep
	 * @param curStepMessage
	 * @param remark
	 * @param nextStepId
	 * @return
	 *
	 */
	public String updateCurStepActivNextStep(String orId, String projId,
			String projNo, String curStep, String curStepMessage, String remark,
			String nextStepId);


	List<OperateRecord> findListByProjStepStatusId(String projId, String stepId,String status);

	void nextDealtNotice(Project project,String stepId,String stepName,String operaterId,String operater );

	void noticeSetInvalid(String projId,String stepId,String status);
	void noticeSetInvalid(List<String> projIds,String stepId,String opType);

	/**
	 * 注释：生成当前步骤的待办通知
	 * @author liaoyq
	 * @createTime 2019年5月21日
	 * @param project
	 * @param stepId
	 * @param wfTypeId
	 * @param wfAssistTypeId
	 * @param busOrderId
	 *
	 */
	public void cerateCurOperateRecord(Project project, String stepId, String wfTypeId,String wfAssistTypeId,String busOrderId,Staff staff,String grade, boolean flag);

	/**
	 * 注释：将该业务单的操作记录置为无效
	 * @author liaoyq
	 * @createTime 2019年5月21日
	 * @param busOrderId
	 *
	 */
	public void updateIsValid(String busOrderId,String opType);

    void saveUpdateOperateRecord(Project pro, StaffDto staffDto, OperateRecord o);

    boolean updateNextNotice(String businessOrderId, String auditLevel ,boolean isUpdateNextNotice);

	String rollBackHandle(String projId, FallbackApply fallbackApply);

	 /**
     * 注释：生成上传监理评估报告的待办通知
     * @author liaoyq
     * @createTime 2019年9月2日
	 * @param businessOrderId
	 * @param pro
	 * @param noticeFlag
	 * @param stepId
	 *
	 */
	public void noticeSuReport(String businessOrderId, Project pro, boolean noticeFlag,
			String stepId);

    /**
     * 查询所有的待办
     * @author fuliwei
     * @date 2019/8/26
    */
    public List<Map<String, Object>> queryOperateRecordTodo();

    String queryTodoer(String businessId);

    ResultInfo recoveryProject(AbandonedRecord abandonedRecord);


    /**
     * 生成微信操作记录
     * @author fuliwei
     * @date 2019/12/6
    */
	public void createWxOperateRecord(String projId,String projNo,String stepId,String stepName,String remark,
									  String todoer,String todoerId,String modifyStatus,String corpId,String projectType,String contributionMode);

    void changeBudgeterTodo(String projId, String orgBudgeterAuditId, String staffId, String staffName);
}
