package cc.dfsoft.project.biz.base.inspection.service;

import java.util.Map;

import cc.dfsoft.project.biz.base.inspection.dto.PreservativeInpectReq;
import cc.dfsoft.project.biz.base.inspection.dto.ProjectCheckListPIReq;
import cc.dfsoft.project.biz.base.inspection.entity.PreservativeInpect;

/**
 * 防腐检查服务接口层
 * @author liaoyq
 *
 */
public interface PreservativeInpectService {

	/**
	 * 保存防腐检查信息
	 * @param preservativeInpect
	 */
	void savePreservativeInpect(PreservativeInpect preservativeInpect);

	/**
	 * 查询防腐检查信息
	 * @param preservativeInpect
	 * @return
	 */
	Map<String, Object> queryPreservativeInpect(
			PreservativeInpectReq preservativeInpect);

	/**
	 * 
	 * @param pcId
	 * @return
	 */
	PreservativeInpect queryByPcId(String pcId);

	/**
	 * 保存回调
	 * @author fuliwei
	 * @createTime 2018年1月21日
	 * @param 
	 * @return
	 */
	public void saveSignNotice(String cwId);
}
