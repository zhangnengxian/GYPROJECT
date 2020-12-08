package cc.dfsoft.project.biz.base.budget.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cc.dfsoft.project.biz.base.budget.dao.MaterialListDao;
import cc.dfsoft.project.biz.base.budget.dto.MaterialListQueryReq;
import cc.dfsoft.project.biz.base.budget.dto.MaterialListReq;
import cc.dfsoft.project.biz.base.budget.entity.MaterialList;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;
import cc.dfsoft.uexpress.common.util.ResultUtil;

@Repository
public class MaterialListDaoImpl extends NewBaseDAO<MaterialList, String> implements MaterialListDao {

	@Override
	public Map<String, Object> queryMaterialList(MaterialListQueryReq materialListQueryReq) {
		
		 Criteria c = super.getCriteria();
		 
		 //工程id
		 if(StringUtils.isNotBlank(materialListQueryReq.getProjId())){
			 c.add(Restrictions.eq("projId",materialListQueryReq.getProjId()));
		 }
		 //材料名称
		 if(StringUtils.isNotBlank(materialListQueryReq.getMaterialName())){
			 c.add(Restrictions.like("materialName", "%"+materialListQueryReq.getMaterialName()+"%"));
		 }
		// 数据库根据条件过滤记录数
		int filterCount = this.countByCriteria(c);

		// 根据条件获取查询信息
		List<MaterialList> MaterialLists = this.findBySortCriteria(c, materialListQueryReq);
		
		// 返回结果
		return ResultUtil.pageResult(filterCount, materialListQueryReq.getDraw(),MaterialLists);
	}

	@Override
	public List<MaterialList> getExportDataList(String projId) {
		Criteria c = super.getCriteria();
		if(StringUtils.isNotBlank(projId)){
			c.add(Restrictions.eq("projId",projId));
		}
		List<MaterialList> MaterialLists = this.findByCriteria(c);
		return MaterialLists;
	}

	@Override
	public List<MaterialList> queryMaterialListList(String projId) {
		Criteria c = super.getCriteria();
		 if(StringUtils.isNotBlank(projId)){
			 c.add(Restrictions.eq("projId",projId));
		 }
		 List<MaterialList> list = this.findByCriteria(c);
		 return list;
	}
	
	/**
	 * 根据材料名称查找材料
	 * @author fuliwei
	 * @createTime 2017年2月18日
	 * @param materialName
	 * @return
	 */
	@Override
	public MaterialList queryMaterialList(String materialName,String projId) {
		Criteria c=super.getCriteria();
		if(StringUtils.isNotBlank(materialName)){
			c.add(Restrictions.eq("materialName", materialName));
		}
		if(StringUtils.isNotBlank(projId)){
			c.add(Restrictions.eq("projId", projId));
		}
		List<MaterialList> list=this.findByCriteria(c);
		
		if(list!=null && list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}
	
	/**
	 * 列表查询
	 * @author fuliwei
	 * @createTime 2017-02-19
	 * @param projId
	 * @return
	 */
	@Override
	public List<MaterialList> queryMaterialListById(String projId) {
		Criteria c=super.getCriteria();
		if(StringUtils.isNotBlank(projId)){
			c.add(Restrictions.eq("projId", projId));
			c.addOrder(Order.desc("materialSpec"));
			List<MaterialList> list=this.findByCriteria(c);
			return list;
		}
		return null;
		
	}
	

	/**
	 * 通过工程id删除所有的材料清单
	 * @author fuliwei
	 * @createTime 2017年11月14日
	 * @param 
	 * @return
	 */
	@Override
	public void deleteByProjId(String projId) {
		StringBuffer hql = new StringBuffer("delete from MaterialList where projId='").append(projId).append("'");
		super.executeHql(hql.toString());
	}

	@Override
	public MaterialList queryMaterial(MaterialListQueryReq req) {
		Criteria c = super.getCriteria();
		 
		 //工程id
		 if(StringUtils.isNotBlank(req.getProjId())){
			 c.add(Restrictions.eq("projId",req.getProjId()));
		 }
		 //材料名称
		 if(StringUtils.isNotBlank(req.getMaterialName())){
			 c.add(Restrictions.like("materialName", req.getMaterialName()));
		 }
		 //材料规格
		 if(StringUtils.isNotBlank(req.getMaterialSpec())){
			 c.add(Restrictions.like("materialSpec", req.getMaterialSpec()));
		 }
		//变更ID
		 if(StringUtils.isNotBlank(req.getCmId())){
			 c.add(Restrictions.like("cmId", req.getCmId()));
		 }else{
			 StringBuffer sql = new StringBuffer();
			 sql.append(" (CM_ID is null or CM_ID='')");
			 c.add(Restrictions.sqlRestriction(sql.toString())); 
		 }
		//材料标记
		 if(StringUtils.isNotBlank(req.getFlag())){
			 c.add(Restrictions.eq("flag", req.getFlag()));
		 }
		 List<MaterialList> list=this.findByCriteria(c);
		 if(list!=null && list.size()>0){
			 return list.get(0);
		 }
		return null;
	}

	/**
	 * 根据工程id、材料名称、材料规格删除材料信息
	 */
	@Override
	public void deleteByProjId(MaterialListQueryReq req) {
		String hql ="delete from MaterialList where 1=1";
		 //工程id
		 if(StringUtils.isNotBlank(req.getProjId())){
			hql += " and projId='"+req.getProjId()+"'";
		 }
		 //材料名称
		 if(StringUtils.isNotBlank(req.getMaterialName())){
			 hql += " and materialName ='"+req.getMaterialName()+"'";
		 }
		 //材料规格
		 if(StringUtils.isNotBlank(req.getMaterialSpec())){
			 hql += " and materialSpec ='"+req.getMaterialSpec()+"'";
		 }
		 super.executeHql(hql);
	}

	/**
	 * @author liaoyq
	 * @createTime 2018-2-7
	 *  
	 **/
	@Override
	public MaterialList queryByNameAndSpec(String projId, String materialName,
			String materialSpec, String flag) {
		Criteria c = super.getCriteria();
		 
		 //工程id
		 if(StringUtils.isNotBlank(projId)){
			 c.add(Restrictions.eq("projId",projId));
		 }
		 //材料名称
		 if(StringUtils.isNotBlank(materialName)){
			 c.add(Restrictions.like("materialName", materialName));
		 }
		 //材料规格
		 if(StringUtils.isNotBlank(materialSpec)){
			 c.add(Restrictions.like("materialSpec", materialSpec));
		 }
		 //1是否由物资公司购买
		 if(StringUtils.isNotBlank(flag)){
			 c.add(Restrictions.like("flag", flag));
		 }
		 List<MaterialList> list=this.findByCriteria(c);
		 if(list!=null && list.size()>0){
			 return list.get(0);
		 }
		return null;
	}

	/**
	 * 查询材料清单-可带变更材料id查询
	 * @author liaoyq
	 */
	@Override
	public List<MaterialList> queryMaterials(MaterialListReq req) {
		Criteria c = super.getCriteria();
		 
		 //工程id
		 if(StringUtils.isNotBlank(req.getProjId())){
			 c.add(Restrictions.eq("projId",req.getProjId()));
		 }
		 //材料名称
		 if(StringUtils.isNotBlank(req.getMaterialName())){
			 c.add(Restrictions.like("materialName", req.getMaterialName()));
		 }
		 //材料规格
		 if(StringUtils.isNotBlank(req.getMaterialSpec())){
			 c.add(Restrictions.like("materialSpec", req.getMaterialSpec()));
		 }
		 //变更ID
		 if(StringUtils.isNotBlank(req.getCmId())){
			 c.add(Restrictions.like("cmId", req.getCmId()));
		 }else{
			 StringBuffer sql = new StringBuffer();
			 sql.append(" (CM_ID is null or CM_ID='')");
			 c.add(Restrictions.sqlRestriction(sql.toString())); 
		 }
		 //1是否由物资公司购买
		 if(StringUtils.isNotBlank(req.getFlag())){
			 c.add(Restrictions.like("flag", req.getFlag()));
		 }
		 return this.findByCriteria(c);
	}

	
	
}
