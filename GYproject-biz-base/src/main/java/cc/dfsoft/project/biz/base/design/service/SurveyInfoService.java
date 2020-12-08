package cc.dfsoft.project.biz.base.design.service;

import cc.dfsoft.project.biz.base.design.dto.SurveyInfoQueryReq;
import cc.dfsoft.project.biz.base.design.entity.SurveyInfo;
import cc.dfsoft.project.biz.base.project.entity.ManageRecord;
import org.springframework.orm.hibernate4.HibernateOptimisticLockingFailureException;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;


/**
 * 勘察信息服务接口
 * @author pengtt
 * @createTime 2016-06-27
 *
 */
public interface SurveyInfoService {
	
	/**
	 * 勘察信息列表条件查询
	 * @author pengtt
	 * @createTime 2016-06-27
	 * @param surveyInfoQueryReq 查询条件dto
	 * @return Map<String,Object>
	 * @throws ParseException 
	 */
	Map<String,Object> querySurveyInfo(SurveyInfoQueryReq surveyInfoQueryReq) throws ParseException;
	
	/**
	 * 推送现场踏勘
	 * @author fuliwei
	 * @createTime 2017-7-25
	 * @param  surveyInfo 勘察信息
	 * @return String 
	 * @throws Exception 
	 */
	public String saveSurveyInfo(SurveyInfo surveyInfo)throws HibernateOptimisticLockingFailureException, IOException, Exception;
	
	/**
	 * 该勘察信息列表查询专用于接气方案审核页面
	 * @author pengtt
	 * @createTime 2016-06-27
	 * @param surveyInfoQueryReq 查询条件dto
	 * @return Map<String,Object>
	 * @throws ParseException 
	 */
	Map<String,Object> querySurveyInfoForAudit(SurveyInfoQueryReq surveyInfoQueryReq) throws ParseException;
	
	/**
	 * 接气方案审核页面详述
	 * @author pengtt
	 * @createTime 2016-06-27
	 * @param surveyId 勘察信息id
	 * @return
	 */
	public SurveyInfo detail(String surveyId);
	
	/**
	 * 通过工程id查询勘察信息
	 * @author pengtt
	 * @createTime 2016-07-12
	 * @param projId
	 * @return
	 */
	public List<SurveyInfo> findByProjId(String projId);
	
	/**
	 * 查询详述
	 * @author fuliwei
	 * @createTime 2017年7月22日
	 * @param 
	 * @return
	 */
	public SurveyInfo viewSurveyInfo(String projId);
	
	/**
	 * 保存现场踏勘
	 * @author fuliwei
	 * @createTime 2017-7-25
	 * @param  surveyInfo 勘察信息
	 * @return String 
	 * @throws Exception 
	 */
	public String saveSurvey(SurveyInfo surveyInfo)throws HibernateOptimisticLockingFailureException, IOException, Exception;
	
	/**
	 * 方案确认通过
	 * @createTime 
	 * @param manageRecord
	 */
	public void auditPass(ManageRecord manageRecord);

    boolean rollBackContainsSurvey(String projId,String rollBackReason);
}
