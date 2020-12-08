package cc.dfsoft.project.biz.base.constructmanage.dao;

import java.util.List;
import java.util.Map;

import cc.dfsoft.project.biz.base.budget.dto.MaterialListQueryReq;
import cc.dfsoft.project.biz.base.constructmanage.entity.MaterialPlan;
import cc.dfsoft.project.biz.base.constructmanage.entity.MaterialPlanDetail;
import cc.dfsoft.uexpress.common.dao.CommonDao;

public interface MaterialPlanDetailDao extends CommonDao<MaterialPlanDetail,String>{
	
	/**
	 * 材料计划明细列表查询
	 * @author fuliwei
	 * @createTime 2017-1-30
	 * @param materialListQueryReq
	 * @return Map<String,Object>
	 */
	public Map<String, Object> queryMaterialPlanList(MaterialListQueryReq materialListQueryReq);
	
	/**
	 * 通过mpId删除所有的材料计划明细
	 * @author fuliwei
	 * @createTime 2017-2-2
	 * @param mpId
	 * @return
	 */
	public void deleteByMpId(String mpId);
	
	
	/**
	 * 材料计划导出Excel文件
	 * @author fuliwei
	 * @createTime 2017-2-11
	 * @param String  mpId
	 * @return List<MaterialPlan>
	 */
	public List<MaterialPlanDetail> exprotExcel(String mpId);
}
