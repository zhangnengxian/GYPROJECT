package cc.dfsoft.project.biz.base.constructmanage.service;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import cc.dfsoft.project.biz.base.constructmanage.dto.MaterialPlanReq;
import cc.dfsoft.project.biz.base.constructmanage.entity.MaterialPlan;

/**
 * 施工-材料计划
 * @author fuliwei 
 * @createTime 2017-01-18
 *
 */
public interface MaterialPlanService {
	
	/**
	 * 材料计划列表查询
	 * @author fuliwei
	 * @createTime 2017-1-18
	 * @param  MaterialPlanReq
	 * @return Map<String, Object>
	 */
	public Map<String, Object> queryMaterialPlan(MaterialPlanReq MaterialPlanReq)throws ParseException;
	
	/**
	 * 根据Id查材料计划详述
	 * @author fuliwei
	 * @createTime 2017-1-18
	 * @param  mpId
	 * @return MaterialPlan
	 */
	public MaterialPlan findById(String mpId);
	
	/**
	 * 保存材料计划
	 * @author fuliwei
	 * @createTime 2017-1-31
	 * @param  MaterialPlan
	 * @return String
	 */
	public void saveMaterialPlan(MaterialPlan mp);
	
	
	/**
	 * 保存材料计划
	 * @author fuliwei
	 * @createTime 2017-1-31
	 * @param  MaterialPlan
	 * @return String
	 */
	public void saveMaterialPlanFeedBack(MaterialPlan mp);
	
	/**
	 * 材料计划导出标记
	 * @createTime 2017-2-20
	 * @param  String mpId
	 * @return String 
	 */
	public void signMaterialPlan(String mpId);
}
