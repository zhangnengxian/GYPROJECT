package cc.dfsoft.project.biz.base.subpackage.dao;


import java.util.List;
import java.util.Map;

import cc.dfsoft.project.biz.base.subpackage.dto.SubQuantitiesReq;
import cc.dfsoft.project.biz.base.subpackage.entity.SubQuantities;
import cc.dfsoft.uexpress.common.dao.CommonDao;

public interface SubQuantitiesDao extends CommonDao<SubQuantities,String>{
	
	/**
     * 根据工程id获取计划
     * @param projId
     * @return
     */
	public SubQuantities queryInfoByProjId(String projId);
	
	/**
	 * 根据工程id删除分包分项工程名称
	 * @author pengtt
	 * @createTime 2016-08-30
	 * @param projId
	 */
	public void deleteByProjId(String projId);

	/**
	 * 工程量清单查询
	 * @param constructionUnitReq
	 * @return
	 */
	public Map<String, Object> queryPricedBoq(SubQuantitiesReq subQuantitiesReq);
	
	/**
	 * 此方法用于工程量申报屏右侧无分页查询
	 * @author pengtt
	 * @createTime 2016-08-30
	 * @param subQuantitiesReq
	 * @return
	 */
	public Map<String, Object> queryQuantityStandardNoPage(SubQuantitiesReq subQuantitiesReq);
	
	/**
	 * 根据工程id和审核状态删除分包工程量
	 * @param projId
	 * @param status
	 */
	public void deleteByProjIdSettlement(String projId, String status);

	public Map<String, Object> querySubQuantityStandar(SubQuantitiesReq subQuantitiesReq);
	
	
	/**
	 * 分包工程量导出Excel文件
	 * @author fuliwei
	 * @createTime 2016-12-31
	 * @param String  projId
	 * @return
	 */
	public List<SubQuantities> exprotExcel(String sbId);

	/**
	 * 根据施工预算Id删除分包工程量
	 * @param sbId
	 */
	public void deleteBySbId(String sbId);

	/**
	 * @author liaoyq
	 * @createtime 2017-8-10
	 * 根据预结算ID和审核状态删除分包工程量
	 * @param sdId 预结算ID
	 * @param sqStatus 预结算状态
	 */
	public void deleteBySbIdAndStatus(String sbId, Integer sqStatus);

}
