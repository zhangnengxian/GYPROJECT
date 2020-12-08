package cc.dfsoft.project.biz.base.design.service;

import java.util.Map;

import com.alibaba.fastjson.JSONArray;

import cc.dfsoft.project.biz.base.design.dto.DrawingsDirectoryReq;
import cc.dfsoft.project.biz.base.design.entity.DesignInfo;
import cc.dfsoft.project.biz.base.project.entity.ManageRecord;
import cc.dfsoft.project.biz.base.project.entity.Project;
/**
 * 正式出图服务接口
 * @author zhangmeng
 * @createTime 2016-07-06
 *
 */
public interface OfficialDrawingService {
	/**
	 * 材料目录列表查询
	 * @param PageSortReq
	 * @return
	 */
	public Map<String, Object> queryMaterialDirectory(DrawingsDirectoryReq pageSortReq);
	
	public void batInsertCostSum(JSONArray jsonArr,String projId);

	public void updateProjectRecord(Project pro);
	
	/**
	 * 图纸签收确认
	 * @author fuliwei
	 * @createTime 2017年8月26日
	 * @param 
	 * @return
	 */
	public void updateState(DesignInfo designInfo);
	
	/**
	 * 白图登记
	 * @author fuliwei  
	 * @date 2018年11月8日  
	 * @version 1.0
	 */
	public void saveWhiteMap(DesignInfo designInfo);
	
	/**
	 * 白图审核
	 * @author fuliwei  
	 * @date 2018年11月9日  
	 * @version 1.0
	 */
	public void saveWhiteMapAudit(ManageRecord manageRecord, String docTypeID ,String auditLevel ,String stepID,String constants);
}
