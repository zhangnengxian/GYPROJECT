package cc.dfsoft.project.biz.base.constructmanage.service;

import cc.dfsoft.project.biz.base.budget.entity.Budget;
import cc.dfsoft.project.biz.base.constructmanage.dto.EngineeringVisaQueryReq;
import cc.dfsoft.project.biz.base.constructmanage.dto.UploadResult;
import cc.dfsoft.project.biz.base.constructmanage.entity.EngineeringVisa;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.sql.rowset.serial.SerialException;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

public interface EngineeringVisaService {

	
	/**
	 * 签证列表条件查询
	 * @author cui
	 * @createTime 2016-07-25
	 * @param engineeringVisaQueryReq 查询条件dto
	 * @return Map<String,Object>
	 * @throws ParseException 
	 */
	Map<String,Object> queryEngineeringVisa(EngineeringVisaQueryReq engineeringVisaQueryReq) throws ParseException;
	
	/**
	 *  查询签证详述
	 * @author cui
	 * @createTime 2016-07-25
	 * 
	 */
	EngineeringVisa viewEngineeringVisa(String id);
	EngineeringVisa viewEngineeringVisa(String id,String menuDes);
	
	/**
	 * 签证记录保存
	 * @author cui
	 * @throws SQLException 
	 * @throws SerialException 
	 * @createTime  2016-7-21
	 * 
	 */
	public String saveEngineeringVisa(EngineeringVisa engineeringVisa) throws Exception;
	
	public String saveEngineeringVisaFile(HttpServletRequest request,UploadResult enginneringVisa,MultipartFile[] files) throws Exception;

	/**
	 *  更新签证状态为已处理
	 * @author zhangjj
	 * @createTime 2016-08-08
	 * 
	 */
	public void updateVisaState(String id);
	
	/**
	 * 签证记录时效性（定时任务）
	 * 状态为未处理的签证记录,签证日期距离当前时间超过三天且无业务代表的状态标记为作废
	 * @author pengtt
	 * @createTime 2016-08-25
	 * */
    public void updateEngineeringVisaState() throws Exception;
    
    /**
	 * 签证审核列表条件查询
	 * @param engineeringVisaQueryReq
	 * @return
	 * @throws ParseException
	 */
	Map<String,Object> queryVisaAudit(EngineeringVisaQueryReq engineeringVisaQueryReq) throws ParseException;
	
	/**
	 * 获得数据库当前时间
	 * @return
	 */
	java.util.Date getDatabaseDate();

	/**
	 * 推送签证值审核
	 * @param evId
	 * @return
	 * @throws Exception 
	 */
	String pushEv(String evId, BigDecimal quantitiesTotal) throws Exception;

	/**
	 * 推送签证-调整预算
	 * @author liaoyq
	 * @createTime 
	 * @param evId 签证ID 
	 */
	String pushEvToBudget(String evId);

	/**
	 * 预算调整确认
	 * @param budget
	 * @return
	 * @throws Exception 
	 */
	String visaConfirm(Budget budget) throws Exception;

	/**
	 * 根据工程ID组装批量打印报表路径和参数
	 * @param projId
	 * @return
	 */
	List<Object> findPrintDataByProjId(String projId,String type);
	
	
	/**
	 * 保存回调
	 * @author fuliwei
	 * @createTime 2018年1月21日
	 * @param 
	 * @return
	 */
	public void saveSignNotice(String cwId);

	/**
	 * 签证材料变更时，整合材料清单表
	 * @param env
	 * @return 
	 */
	boolean updateMaterialList(EngineeringVisa env);
	
	/**
	 * 签证作废
	 * @author fuliwei
	 * @createTime 2018年3月8日
	 * @param 
	 * @return
	 */
	public void updateEngineeringVisaState(Budget budget) throws Exception;

	/**
	 * 注释：查询未作废的未审核通过的签证
	 * @author liaoyq
	 * @createTime 2018年12月7日
	 * @param id
	 * @return
	 *
	 */
	List<EngineeringVisa> noCancelEngineeringVisa(String id);

	/**
	 * 
	 * 注释：根据签证crontabDate 与当前日期比较，如果大于等于配置的时效性，则自动生成签证一级审核通过的日志，并将crontabDate置空
	 * @author liaoyq
	 * @createTime 2019年7月30日
	 *
	 */
	void engineeringVisaCrontab();

	/**
	 * 
	 * 注释：
	 * @author liaoyq
	 * @createTime 2019年7月30日
	 * @param corpId
	 * @param cnvalue
	 *
	 */
	void autoEngineeringVisaAudit(String corpId, String cnvalue,String auditLevel);
	/**
	 * 
	 * 注释：自动保存签证某个级别审核历史
	 * @author liaoyq
	 * @createTime 2019年7月31日
	 * @param evId
	 * @param projId
	 * @param projNo
	 *
	 */
	void saveAutoManageRecord(String evId, String projId, String projNo,String auditLevel,String stepId);




	/**
	 * @MethodDes: 签证审核超期处理
	 * @author zhangnx
	 * @date 2019/8/7 10:20
	 * @param
	 * @return
	 */
    boolean engineeringVisaOverdueTreatment();

    /**
     * 注释：待签证量审核签证列表查询
     * @author liaoyq
     * @createTime 2019年8月28日
     * @param engineeringVisaQueryReq
     * @return
     * @throws ParseException 
     *
     */
	Map<String, Object> queryVisaQuantyAudit(
			EngineeringVisaQueryReq engineeringVisaQueryReq) throws ParseException;

    boolean updateEngineeringVisa(String evId,String auditState);
}


