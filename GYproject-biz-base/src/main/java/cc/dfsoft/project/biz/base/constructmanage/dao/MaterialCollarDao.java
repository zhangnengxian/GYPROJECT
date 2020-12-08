package cc.dfsoft.project.biz.base.constructmanage.dao;

import java.util.Date;
import java.util.List;

import cc.dfsoft.project.biz.base.constructmanage.entity.MaterialCollar;
import cc.dfsoft.uexpress.common.dao.CommonDao;

/**
 * 材料领用记录Dao
 * @author pengtt
 * @createTime 2016-07-19
 *
 */
public interface MaterialCollarDao extends CommonDao<MaterialCollar,String>{
	
	/**
	 * 材料计划明细列表查询-修改后
	 * @author fuliwei
	 * @createTime 2017-2-16
	 * @param projId  createDate
	 * @return List
	 */
	public List queryMaterialPlanDeatilList(String projId,String createDate);
}
