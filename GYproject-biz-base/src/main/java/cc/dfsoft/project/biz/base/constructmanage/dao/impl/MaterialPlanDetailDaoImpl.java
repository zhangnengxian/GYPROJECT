package cc.dfsoft.project.biz.base.constructmanage.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cc.dfsoft.project.biz.base.budget.dto.MaterialListQueryReq;
import cc.dfsoft.project.biz.base.constructmanage.dao.MaterialPlanDetailDao;
import cc.dfsoft.project.biz.base.constructmanage.entity.MaterialPlan;
import cc.dfsoft.project.biz.base.constructmanage.entity.MaterialPlanDetail;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;
import cc.dfsoft.uexpress.common.util.ResultUtil;
@Repository
public class MaterialPlanDetailDaoImpl extends NewBaseDAO<MaterialPlanDetail,String> implements MaterialPlanDetailDao{

	/**
	 * 材料计划明细列表查询
	 * @author fuliwei
	 * @createTime 2017-1-30
	 * @param materialListQueryReq
	 * @return Map<String,Object>
	 */
	@Override
	public Map<String, Object> queryMaterialPlanList(MaterialListQueryReq materialListQueryReq) {
		Criteria c = super.getCriteria();
		 //工程id
		if(StringUtils.isNotBlank(materialListQueryReq.getProjId())){
			 c.add(Restrictions.eq("projId",materialListQueryReq.getProjId()));
		}
		//材料计划id
		if(StringUtils.isNotBlank(materialListQueryReq.getMpId())){
			 c.add(Restrictions.eq("mpId",materialListQueryReq.getMpId()));
		}
		// 数据库根据条件过滤记录数
		int filterCount = this.countByCriteria(c);

		// 根据条件获取查询信息
		List<MaterialPlanDetail> MaterialLists = this.findBySortCriteria(c, materialListQueryReq);
		
		// 返回结果
		return ResultUtil.pageResult(filterCount, materialListQueryReq.getDraw(),MaterialLists);
	}
	
	/**
	 * 通过mpId删除所有的材料计划明细
	 * @author fuliwei
	 * @createTime 2017-2-2
	 * @param mpId
	 * @return
	 */
	@Override
	public void deleteByMpId(String mpId) {
		StringBuffer hql = new StringBuffer("delete from MaterialPlanDetail where mpId='").append(mpId).append("'");
		super.executeHql(hql.toString());
	}
	
	
	/**
	 * 材料计划导出Excel文件
	 * @author fuliwei
	 * @createTime 2017-2-11
	 * @param String  mpId
	 * @return List<MaterialPlan>
	 */
	@Override
	public List<MaterialPlanDetail> exprotExcel(String mpId) {
		Criteria c = super.getCriteria();
		if(StringUtils.isNotBlank(mpId)){
			c.add(Restrictions.eq("mpId", mpId));
			List<MaterialPlanDetail> list = this.findByCriteria(c);
			return list;
		}
		return null;
	}
}
