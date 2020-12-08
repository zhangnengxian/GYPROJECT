package cc.dfsoft.project.biz.base.design.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cc.dfsoft.project.biz.base.budget.entity.ProjectCostSummary;
import cc.dfsoft.project.biz.base.design.dao.DrawingMaterialDao;
import cc.dfsoft.project.biz.base.design.dto.DrawingsDirectoryReq;
import cc.dfsoft.project.biz.base.design.entity.DrawingMaterial;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;
import cc.dfsoft.uexpress.common.util.ResultUtil;

@Repository
public class DrawingMaterialDaoImpl extends NewBaseDAO<DrawingMaterial, String> implements DrawingMaterialDao {
	@Override
	public Map<String, Object> queryMaterialDirectory(DrawingsDirectoryReq pageSortReq) {
		Criteria c = super.getCriteria();
		//String id=pageSortReq.getProjId();
		//根据工程Id进行查询
		if(StringUtils.isNotBlank(pageSortReq.getProjId())){
			 c.add(Restrictions.eq("projId",pageSortReq.getProjId()));
		}
		c.addOrder(Order.asc("orderNum"));
		// 数据库根据条件过滤记录数
		int filterCount = this.countByCriteria(c);
		// 根据条件获取查询信息
		List<DrawingMaterial> list = this.findBySortCriteria(c, pageSortReq);				
		// 返回结果
		return ResultUtil.pageResult(filterCount, pageSortReq.getDraw(),list);
	}

	@Override
	public List<DrawingMaterial> queryDrawingMaterialList(String projId) {
		 Criteria c = super.getCriteria();
		 if(StringUtils.isNotBlank(projId)){
			 c.add(Restrictions.eq("projId",projId));
		 }
		 List<DrawingMaterial> list = this.findByCriteria(c);
		 return list;
	}
}
