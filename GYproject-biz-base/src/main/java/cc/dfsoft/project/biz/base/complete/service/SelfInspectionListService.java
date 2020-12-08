package cc.dfsoft.project.biz.base.complete.service;

import cc.dfsoft.project.biz.base.complete.dto.PreinspectionReq;
import cc.dfsoft.project.biz.base.complete.entity.SelfInspectionList;

import java.text.ParseException;
import java.util.Map;

/**
 * 自检单Service接口
 * @author Yuanyx
 *
 */
public interface SelfInspectionListService {
	/**
	 * 根据工程ID查自检单
	 * @param projId
	 * @return
	 * @throws ParseException 
	 */
	SelfInspectionList viewSelfInspectionList(String projId) throws ParseException;
	
	/**
	 * 保存自检单
	 * @author fuliwei
	 * @createTime 2017年7月31日
	 * @param 
	 * @return
	 */
	void saveSelfInspectionList(SelfInspectionList selfInspectionList) throws Exception;
	
	/**
	 * 自检单列表查询
	 * @author fuliwei
	 * @createTime 2017年8月4日
	 * @param 
	 * @return
	 */
	public Map<String,Object> querySelInspection(PreinspectionReq req) throws ParseException ;
	

	/**
	 * 自检单打印标记
	 * @author fuliwei
	 * @createTime 2017年8月4日
	 * @param 
	 * @return
	 */
	public void signSelInspectionPrint(String projId);

	/**
	 * 根据工程ID查询自检信息，返回报表路径
	 * @param projId
	 * @return
	 */
	public String findPrintDataByProjId(String projId,String type);

    boolean modifySelfCheck(SelfInspectionList selfCheck);

    boolean rollBackContainsSelfInspectionList(String projId, String fallbackReason);
}

