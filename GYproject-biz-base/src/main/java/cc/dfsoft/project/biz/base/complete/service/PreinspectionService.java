package cc.dfsoft.project.biz.base.complete.service;


import cc.dfsoft.project.biz.base.complete.dto.PreinspectionReq;
import cc.dfsoft.project.biz.base.complete.entity.Preinspection;

import java.text.ParseException;
import java.util.Map;

public interface PreinspectionService {
	/**
	 * 保存预验收记录
	 * @param preinspection
	 * @return
	 * @throws ParseException 
	 */
	public String savePreinspection( Preinspection preinspection,String stepID) throws Exception;
	/**
	 * 根据工程Id查预验收信息
	 * @param projId
	 * @return
	 */
	public Preinspection findByProjId(String projId)throws ParseException ;
	
	/**
	 * 自检单列表查询
	 * @author fuliwei
	 * @createTime 2017年8月4日
	 * @param 
	 * @return
	 */
	public Map<String,Object> querySelInspection(PreinspectionReq req)throws ParseException ;
	
	/**
	 * 自检单打印标记
	 * @author fuliwei
	 * @createTime 2017年8月4日
	 * @param 
	 * @return
	 */
	public void signSelInspectionPrint(String projId);
	
	
	/**
	 * 查询质量预验收记录详述
	 * @author fuliwei
	 * @createTime 2017年8月15日
	 * @param 
	 * @return
	 */
	public Map<String, String> viewPreInspectionRecordQuqlity(String projId);

	/**
	 * 查询资料预验收记录详述
	 * @author fuliwei
	 * @createTime 2017年8月15日
	 * @param 
	 * @return
	 */
	public Map<String, String> viewPreInspectionRecordMaterial(String projId);
	
	/**
	 * 查询材料预验收记录详述
	 * @author fuliwei
	 * @createTime 2017年8月15日
	 * @param 
	 * @return
	 */
	public Map<String, String> viewPreInspectionRecordData(String projId);
	/**
	 * 根据工程ID查询预验收信息并组装预验收单打印报表
	 * @author liaoyq
	 * @createTime 2017-11-27
	 * @param projId
	 * @return
	 */
	public String findPrintDataByProjId(String projId,String type);
	
	/**
	 * 保存回调
	 * @author fuliwei
	 * @createTime 2018年1月21日
	 * @param 
	 * @return
	 */
	public void saveSignNotice(String cwId);

	boolean modifyPreinspection(Preinspection preinspection) throws Exception;

    boolean rollBackContainsPreinspection(String projId, String fallbackReason);
}
