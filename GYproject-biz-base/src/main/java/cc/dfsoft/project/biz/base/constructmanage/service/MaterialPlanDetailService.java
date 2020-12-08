package cc.dfsoft.project.biz.base.constructmanage.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import cc.dfsoft.project.biz.base.budget.dto.MaterialListQueryReq;
import cc.dfsoft.project.biz.base.constructmanage.entity.MaterialPlanDetail;

public interface MaterialPlanDetailService {
	
	/**
	 * 材料计划明细列表查询
	 * @author fuliwei
	 * @createTime 2017-1-30
	 * @param materialListQueryReq
	 * @return Map<String,Object>
	 */
	public Map<String, Object> queryMaterialPlanList(MaterialListQueryReq materialListQueryReq);
	
	/**
	 * 材料计划导出Excel文件
	 * @author fuliwei
	 * @createTime 2017-2-11
	 * @param String  mpId
	 * @return List<MaterialPlan>
	 */
	public List<MaterialPlanDetail> exprotExcel(String mpId);
	
	
	/**
	 * 材料计划明细列表查询-修改后
	 * @author fuliwei
	 * @createTime 2017-2-16
	 * @param projId  createDate
	 * @return Map<String,Object>
	 */
	public Map<String, Object> queryMaterialPlanDetailList(MaterialListQueryReq materialListQueryReq);
}
