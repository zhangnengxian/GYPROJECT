package cc.dfsoft.project.biz.base.inspection.service;

import java.util.Map;

import cc.dfsoft.project.biz.base.inspection.dto.AltimetricSurveyReq;
import cc.dfsoft.project.biz.base.inspection.dto.ProjectCheckListASReq;

/**
 * 工程报验服务接口
 * @author zhangjj
 *
 */
public interface AltimetricSurveyService {
	/**
	 * 保存高程测量记录(批量增加)
	 * @param list
	 */
	public void saveAltimSurvey(ProjectCheckListASReq projectCheckListASReq);
	/**
	 * 查询高程测量记录
	 * @param altimetricSurveyReq
	 * @return
	 */
	public Map<String,Object> queryAltimSurvey(AltimetricSurveyReq altimetricSurveyReq);
	
}
