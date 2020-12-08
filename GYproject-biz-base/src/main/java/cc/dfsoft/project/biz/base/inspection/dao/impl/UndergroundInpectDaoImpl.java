package cc.dfsoft.project.biz.base.inspection.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cc.dfsoft.project.biz.base.inspection.dao.UndergroundInpectDao;
import cc.dfsoft.project.biz.base.inspection.dto.UndergroundInpectReq;
import cc.dfsoft.project.biz.base.inspection.entity.Purge;
import cc.dfsoft.project.biz.base.inspection.entity.UndergroundInpect;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;
import cc.dfsoft.uexpress.common.util.ResultUtil;
/**
 * 埋地检查dao实现层
 * @author liaoyq
 *
 */
@Repository
public class UndergroundInpectDaoImpl extends
		NewBaseDAO<UndergroundInpect, String> implements UndergroundInpectDao {

	@Override
	public Map<String, Object> queryUndergroundInpect(
			UndergroundInpectReq undergroundInpectReq) {
		Criteria c = super.getCriteria();	
		//工程id
		if(StringUtils.isNotBlank(undergroundInpectReq.getPcId())){
			c.add(Restrictions.eq("pcId",undergroundInpectReq.getPcId()));
		}
		// 数据库根据条件过滤记录数
		int filterCount = this.countByCriteria(c);
		// 根据条件获取查询信息
		List<UndergroundInpect> list = this.findBySortCriteria(c, undergroundInpectReq);
		// 返回结果
		return ResultUtil.pageResult(filterCount, undergroundInpectReq.getDraw(),list);
	}

	@Override
	public void deleteByPcId(String pcId) {
		StringBuffer hql = new StringBuffer("delete from UndergroundInpect where pcId='").append(pcId).append("'");
		this.executeHql(hql.toString());
	}

	@Override
	public Map<String, Object> queryRecords(
			UndergroundInpectReq undergroundInpectReq) {
		Criteria c = super.getCriteria();	
		//工程id
		if(StringUtils.isNotBlank(undergroundInpectReq.getProjId())){
			c.add(Restrictions.eq("projId",undergroundInpectReq.getProjId()));
		}
		//报验id
		if(StringUtils.isNotBlank(undergroundInpectReq.getPcId())){
			String sqlFilter ="";
			if(undergroundInpectReq.getFlag()!=null && undergroundInpectReq.getFlag()==1){
				sqlFilter += " PC_ID='"+undergroundInpectReq.getPcId()+"'";//已完成的报验，只查询报验单下的记录
			}else{
				sqlFilter = "(PC_ID='' or PC_ID is null";
				if(!undergroundInpectReq.getPcId().equals("-1")){//
					sqlFilter += " or PC_ID='"+undergroundInpectReq.getPcId()+"'";
				}
				sqlFilter +=")";
			}
			c.add(Restrictions.sqlRestriction(sqlFilter));
		}
		// 数据库根据条件过滤记录数
		int filterCount = this.countByCriteria(c);
		// 根据条件获取查询信息
		List<UndergroundInpect> list = this.findBySortCriteria(c, undergroundInpectReq);
		// 返回结果
		return ResultUtil.pageResult(filterCount, undergroundInpectReq.getDraw(),list);
	}

	@Override
	public void deletePcIdIsNull(String pcId) {
		StringBuffer hql = new StringBuffer("delete from UndergroundInpect where pcId='").append(pcId).append("' or pcId is null");
		this.executeHql(hql.toString());
	}

	@Override
	public void deletePcIdIsNull() {
		StringBuffer hql = new StringBuffer("delete from UndergroundInpect where pcId='' or pcId is null");
		this.executeHql(hql.toString());
	}

	@Override
	public void updatePcIdByRecordId(String id, String pcId) {
		StringBuffer hql = new StringBuffer("update  UndergroundInpect set pcId='").append(pcId).append("' where ugiId='").append(id).append("'");
		this.executeHql(hql.toString());
	}

	@Override
	public void updateByPcId(String pcId) {
		StringBuffer hql  =  new StringBuffer("update  UndergroundInpect set pcId=null where pcId='").append(pcId).append("'");
		this.executeHql(hql.toString());
	}

}
