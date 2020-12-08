package cc.dfsoft.project.biz.base.complete.service;

import java.util.Map;

public interface PreInspectionRecordService {
	
	/**
	 * 查询质量预验收记录详述
	 * @param projId
	 * @return
	 */
	Map<String, String> viewPreInspectionRecordQuqlity(String projId);
	
	/**
	 * 查询资料预验收记录详述
	 * @param projId
	 * @return
	 */
	Map<String, String> viewSelfInspectionRecordMaterial(String projId);
}
