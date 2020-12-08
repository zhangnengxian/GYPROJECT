package cc.dfsoft.project.biz.base.subpackage.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cc.dfsoft.project.biz.base.subpackage.dao.SubQuantitiesDao;
import cc.dfsoft.project.biz.base.subpackage.dto.SubQuantitiesReq;
import cc.dfsoft.project.biz.base.subpackage.entity.SubQuantities;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;
import cc.dfsoft.uexpress.common.util.ResultUtil;

@Repository
public class SubQuantitiesDaoImpl extends NewBaseDAO<SubQuantities, String> implements SubQuantitiesDao{
	
	/**
	 * 根据工程ID查询分包工程量
	 * @author
	 * @createTime	2016-7-8
	 * @param	projId
	 * @return	SubQuantities
	 */
	@Override
	public SubQuantities queryInfoByProjId(String projId) {
		Criteria c = super.getCriteria();
		if(StringUtils.isNotBlank(projId)){
			c.add(Restrictions.eq("projId",projId));
		}
		List<SubQuantities> list=this.findByCriteria(c);
		if(list!=null && list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
	/**
	 * 工程量清单列表查询
	 * @author
	 * @createTime 
	 * @param
	 * @return
	 */
	@Override
	public Map<String, Object> queryPricedBoq(SubQuantitiesReq subQuantitiesReq) {
		Criteria c = super.getCriteria();
		
		if(StringUtils.isNotBlank(subQuantitiesReq.getSqStandardId())){
			c.add(Restrictions.eq("sqStandardId", subQuantitiesReq.getSqStandardId()));
		}
		if(StringUtils.isNotBlank(subQuantitiesReq.getSqBranchProjName())){
			c.add(Restrictions.like("sqBranchProjName","%"+subQuantitiesReq.getSqBranchProjName()+"%"));
		}
		// 数据库根据条件过滤记录数
		int filterCount = this.countByCriteria(c);
		// 根据条件获取查询信息
		List<SubQuantities> list = this.findBySortCriteria(c, subQuantitiesReq);
		// 返回结果
		return ResultUtil.pageResult(filterCount, subQuantitiesReq.getDraw(),list);
	}

	@Override
	public void deleteByProjId(String projId) {
		
		StringBuffer sql = new StringBuffer("delete from SUB_QUANTITIES where PROJ_ID='").append(projId).append("'");
		super.executeSql(sql.toString());
	}
	@Override
	public void deleteByProjIdSettlement(String projId,String status) {
		
		StringBuffer sql = new StringBuffer("delete from SUB_QUANTITIES where PROJ_ID='").append(projId).append("'").append("and SQ_STATUS='").append(status).append("'");
		super.executeSql(sql.toString());
	}
	
	@Override
	public Map<String, Object> queryQuantityStandardNoPage(SubQuantitiesReq subQuantitiesReq) {
		
		Criteria c = super.getCriteria();
		if(StringUtils.isNotBlank(subQuantitiesReq.getSbId())){
			c.add(Restrictions.eq("sbId", subQuantitiesReq.getSbId()));
		}
		if(StringUtils.isNotBlank(subQuantitiesReq.getSqStandardId())){
			c.add(Restrictions.eq("sqStandardId", subQuantitiesReq.getSqStandardId()));
		}
		if(StringUtils.isNotBlank(subQuantitiesReq.getSqBranchProjName())){
			c.add(Restrictions.like("sqBranchProjName","%"+subQuantitiesReq.getSqBranchProjName()+"%"));
		}
		/*if(StringUtils.isNotBlank(subQuantitiesReq.getProjId())){
			c.add(Restrictions.eq("projId", subQuantitiesReq.getProjId()));
		}*/
		if(subQuantitiesReq.getSqStatus()!=null){
			c.add(Restrictions.eq("sqStatus", subQuantitiesReq.getSqStatus()));
		}
		// 数据库根据条件过滤记录数
		int filterCount = this.countByCriteria(c);
		// 根据条件获取查询信息
		List<SubQuantities> list = super.findByCriteria(c);
		return ResultUtil.pageResult(filterCount, subQuantitiesReq.getDraw(),list);
	}
	/**
	 * 结算工程量查询
	 */
	@Override
	public Map<String, Object> querySubQuantityStandar(SubQuantitiesReq subQuantitiesReq) {
		Criteria c = super.getCriteria();
		if(StringUtils.isNotBlank(subQuantitiesReq.getSqStandardId())){
			c.add(Restrictions.eq("sqStandardId", subQuantitiesReq.getSqStandardId()));
		}
		if(StringUtils.isNotBlank(subQuantitiesReq.getSqBranchProjName())){
			c.add(Restrictions.like("sqBranchProjName","%"+subQuantitiesReq.getSqBranchProjName()+"%"));
		}
		if(StringUtils.isNotBlank(subQuantitiesReq.getSbId())){
			c.add(Restrictions.eq("sbId", subQuantitiesReq.getSbId()));
		}
		if(subQuantitiesReq.getSqStatus()!=null){
			c.add(Restrictions.eq("sqStatus", subQuantitiesReq.getSqStatus()));
		}
		// 数据库根据条件过滤记录数
		int filterCount = this.countByCriteria(c);
		// 根据条件获取查询信息
		List<SubQuantities> list = super.findByCriteria(c);
		return ResultUtil.pageResult(filterCount, subQuantitiesReq.getDraw(),list);
	}
	
	/**
	 * 分包工程量导出Excel文件
	 * @author fuliwei
	 * @createTime 2016-12-31
	 * @param String  projId
	 * @return
	 */
	@Override
	public List<SubQuantities> exprotExcel(String sbId) {
		//查找分包工程量（工程量申报下的）
		StringBuffer hql=new StringBuffer();
		if(StringUtils.isNotBlank(sbId)){
			hql.append("from SubQuantities sq where sq.sbId='").append(sbId).append("'");
		}
		List<SubQuantities> list=super.findByHql(hql.toString());
		return list;
	}

	@Override
	public void deleteBySbId(String sbId) {
		StringBuffer sql = new StringBuffer("delete from SUB_QUANTITIES where SB_ID='").append(sbId).append("'");
		super.executeSql(sql.toString());
	}

	@Override
	public void deleteBySbIdAndStatus(String sdId, Integer sqStatus) {
		StringBuffer hql = new StringBuffer("delete from SubQuantities where sbId='").append(sdId).append("'").append(" and sqStatus=").append(sqStatus);
		super.executeHql(hql.toString());
	}
}
