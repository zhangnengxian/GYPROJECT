package cc.dfsoft.project.biz.base.design.dao;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import cc.dfsoft.project.biz.base.design.dto.SurveyInfoQueryReq;
import cc.dfsoft.project.biz.base.design.entity.SurveyInfo;
import cc.dfsoft.uexpress.common.dao.CommonDao;
/**
 * 
 * @author pengtt
 * @CreateTime 2016-06-27
 *
 */
public interface SurveyInfoDao extends CommonDao<SurveyInfo, String>{
	
	/**
	 * 
	 * @param projectQueryReq
	 * @return
	 * @throws ParseException 
	 */
	public Map<String,Object> querySurveyInfo(SurveyInfoQueryReq surveyInfoQueryReq) throws ParseException;
	
	/**
	 * 接气单编号查询
	 * @param connectGasNo
	 * @return
	 */
	public List<SurveyInfo> findByConnectGasNo(String connectGasNo);
	
	/**
	 * 根据工程id查询勘察信息
	 * @author pengtt
	 * @CreateTime 2016-07-12
	 * @param projId
	 * @return
	 */
	public List<SurveyInfo> findByProjId(String projId);

	/**
	 * 勘查打印列表查询
	 * @param surveyInfoQueryReq
	 * @return
	 * @throws ParseException
	 */
	public Map<String,Object> getSurveyInfoList(SurveyInfoQueryReq surveyInfoQueryReq) throws ParseException;

}
