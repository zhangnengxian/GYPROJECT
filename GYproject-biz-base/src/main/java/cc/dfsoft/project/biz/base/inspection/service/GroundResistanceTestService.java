package cc.dfsoft.project.biz.base.inspection.service;

import java.util.Map;

import cc.dfsoft.project.biz.base.inspection.dto.GroundResistanceTestReq;
import cc.dfsoft.project.biz.base.inspection.dto.ProjectCheckListGRTReq;

/**
 * 接地测试服务接口
 * @author Yuanyx
 *
 */
public interface GroundResistanceTestService {
	/**
	 * 保存接地测试记录(批量增加)
	 * @param projectCheckListGRTReq
	 */
	public void saveGroundResistanceTestSurvey(ProjectCheckListGRTReq projectCheckListGRTReq);
	/**
	 * 查询接地测试记录
	 * @param groundResistanceTestReq
	 * @return
	 */
	public Map<String,Object> queryGroundResistanceTestSurvey(GroundResistanceTestReq groundResistanceTestReq);
	
}
