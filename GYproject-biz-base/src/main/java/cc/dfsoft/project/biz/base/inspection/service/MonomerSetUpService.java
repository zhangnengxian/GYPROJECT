package cc.dfsoft.project.biz.base.inspection.service;

import java.util.Map;

import cc.dfsoft.project.biz.base.inspection.dto.AltimetricSurveyReq;
import cc.dfsoft.project.biz.base.inspection.dto.ProjectCheckListMSUReq;

/**
 * 单体调校接口
 * @author Yuanyx
 *
 */
public interface MonomerSetUpService {
	/**
	 * 保存单体调校记录(批量增加)
	 * @param projectCheckListMSUReq
	 */
	public void saveMonomerSetUps(ProjectCheckListMSUReq projectCheckListMSUReq);
	/**
	 * 查询单体调校记录
	 * @param altimetricSurveyReq
	 * @return
	 */
	public Map<String,Object> queryMonomerSetUp(AltimetricSurveyReq altimetricSurveyReq);
	
}
