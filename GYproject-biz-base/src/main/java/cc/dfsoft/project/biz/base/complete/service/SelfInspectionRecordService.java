package cc.dfsoft.project.biz.base.complete.service;

import java.util.List;
import java.util.Map;

import cc.dfsoft.project.biz.base.complete.entity.SelfInspectionList;
import cc.dfsoft.project.biz.base.complete.entity.SelfInspectionRecord;

/**
 * 自检记录Service
 * @author Yuanyx
 *
 */
public interface SelfInspectionRecordService {
	
	/**
	 * 批量保存自检记录
	 * @param mapSirs
	 */
	void saveSelfInspectionRecords(List<SelfInspectionRecord> selfInspectionRecords);

	/**
	 * 批量更新自检记录
	 * @param selfInspectionRecords
	 */
	void updateSelfInspectionRecords(List<SelfInspectionRecord> selfInspectionRecords);

	/**
	 * 查询质量自检记录详述
	 * @param projId
	 * @return
	 */
	Map<String, String> viewSelfInspectionRecordQuqlity(String projId);

	/**
	 * 查询资料自检记录详述
	 * @param projId
	 * @return
	 */
	Map<String, String> viewSelfInspectionRecordMaterial(String projId);

	
	}

