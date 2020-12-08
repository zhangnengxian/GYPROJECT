package cc.dfsoft.project.biz.base.constructmanage.dao;

import java.text.ParseException;
import java.util.Date;
import java.util.Map;

import cc.dfsoft.project.biz.base.constructmanage.dto.MaterialPlanReq;
import cc.dfsoft.project.biz.base.constructmanage.entity.MaterialPlan;
import cc.dfsoft.uexpress.common.dao.CommonDao;
/**
 * 施工-材料计划
 * @author fuliwei 
 * @createTime 2017-01-18
 *
 */
public interface MaterialPlanDao extends CommonDao<MaterialPlan,String>{
	
	/**
	 * 材料计划列表查询
	 * @author fuliwei
	 * @createTime 2017-1-18
	 * @param  
	 * @return
	 */
	public Map<String, Object> queryMaterialPlan(MaterialPlanReq MaterialPlanReq)throws ParseException;
	
	/**
	 * 根据工程Id查材料计划详述
	 * @author fuliwei
	 * @createTime 2017-1-18
	 * @param  projId
	 * @return MaterialPlan
	 */
	public MaterialPlan findByProjId(String projId);
	
	/**
	 * 通过领用时间删除材料计划
	 * @author fuliwei
	 * @createTime 2017年2月18日
	 * @param mgDate
	 * @return
	 */
	public void deleteByMgDate(Date mgDate);
	
}
