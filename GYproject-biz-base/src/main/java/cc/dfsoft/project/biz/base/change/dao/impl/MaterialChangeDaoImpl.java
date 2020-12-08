package cc.dfsoft.project.biz.base.change.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cc.dfsoft.project.biz.base.budget.entity.BudgetTotalTable;
import cc.dfsoft.project.biz.base.budget.entity.MaterialList;
import cc.dfsoft.project.biz.base.change.dao.MaterialChangeDao;
import cc.dfsoft.project.biz.base.change.dto.MaterialChangeReq;
import cc.dfsoft.project.biz.base.change.entity.MaterialChange;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;
import cc.dfsoft.uexpress.common.util.ResultUtil;
@Repository
public class MaterialChangeDaoImpl extends NewBaseDAO<MaterialChange, String> implements MaterialChangeDao{

	@Override
	public Map<String, Object> queryMaterialChange(MaterialChangeReq materialChangeReq) {
		 Criteria c = super.getCriteria();
		 
		 //工程ID
		 if(StringUtils.isNotBlank(materialChangeReq.getProjId())){
			 c.add(Restrictions.eq("projId",materialChangeReq.getProjId()));
		 }
	
		 //变更记录
		 if(StringUtils.isNotBlank(materialChangeReq.getCmId())){
			 c.add(Restrictions.eq("cmId",materialChangeReq.getCmId()));
		 }
		 //签证记录
		 if(StringUtils.isNotBlank(materialChangeReq.getEvId())){
			 c.add(Restrictions.eq("evId",materialChangeReq.getEvId()));
		 }
		
		 // 数据库根据条件过滤记录数
		 int filterCount = this.countByCriteria(c);

		 // 根据条件获取查询信息
		 List<MaterialChange> accessoryList = this.findBySortCriteria(c, materialChangeReq);
			
		 // 返回结果
		 return ResultUtil.pageResult( filterCount, materialChangeReq.getDraw(),accessoryList);
		
	
	}

	@Override
	public List<MaterialChange> findByType(String cmId, String mcType) {
		Criteria c = super.getCriteria();
		 if(StringUtils.isNotBlank(cmId)){
			 c.add(Restrictions.eq("cmId",cmId));
		 }
		 if(StringUtils.isNotBlank(mcType)){
			 c.add(Restrictions.eq("mcType",mcType));
		 }
		 List<MaterialChange> list = this.findByCriteria(c);
		 return list;
	}

	@Override
	public List<MaterialChange> findByProjId(String projId) {
		Criteria c = super.getCriteria();
		 if(StringUtils.isNotBlank(projId)){
			 c.add(Restrictions.eq("projId",projId));
		 }
		 List<MaterialChange> list = this.findByCriteria(c);
		 return list;
	}
	
	/**
	 * 变更记录导入材料表-查找是否已存材料变更表
	 * @param jsonArr 
	 * @param req  
	 * 
	 */
	@Override
	public List<MaterialChange> queryMaterialChangeList(MaterialChangeReq req) {
		Criteria c = super.getCriteria();
		 //费用类型
		 if(StringUtils.isNotBlank(req.getProjId())){
			 c.add(Restrictions.eq("projId",req.getProjId()));
			
		 }
		 if(StringUtils.isNotBlank(req.getCmId())){
			 c.add(Restrictions.eq("cmId",req.getCmId()));
		 }
		 if(StringUtils.isNotBlank(req.getFlag())){
			 c.add(Restrictions.eq("flag",req.getFlag()));
		 }
		 // 根据条件获取查询信息
		 List<MaterialChange> list = this.findBySortCriteria(c, req);
		 return list;
	}

}
