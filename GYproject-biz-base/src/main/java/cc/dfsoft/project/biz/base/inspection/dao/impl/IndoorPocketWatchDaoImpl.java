package cc.dfsoft.project.biz.base.inspection.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cc.dfsoft.project.biz.base.inspection.dao.IndoorPocketWatchDao;
import cc.dfsoft.project.biz.base.inspection.dto.IndoorPocketWatchReq;
import cc.dfsoft.project.biz.base.inspection.entity.IndoorPocketWatch;
import cc.dfsoft.project.biz.base.inspection.entity.TrenchBackfill;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;
import cc.dfsoft.uexpress.common.util.ResultUtil;
/**
 * 户内挂表dao实现层
 * @author liaoyq
 * @createTime 2017年7月24日
 */
@Repository
public class IndoorPocketWatchDaoImpl extends NewBaseDAO<IndoorPocketWatch, String> implements IndoorPocketWatchDao {

	/**
	 *分页查询户内挂表记录
	 * @author liaoyq
	 * @createTime 2017年7月24日
	 * @param indoorPocketWatchReq 户内挂表记录查询辅助类
	 * @return Map<String, Object>
	 */
	@Override
	public Map<String, Object> queryRecordsBySql(
			IndoorPocketWatchReq indoorPocketWatchReq) {
		StringBuffer sql = new StringBuffer("select * from indoor_pocket_watch where 1=1 ");
		StringBuffer sqlCount = new StringBuffer("select count(IPW_ID) from indoor_pocket_watch where 1=1 ");
		List<Object> params = new ArrayList<Object>();
		String sqlFilter="";
		//工程id
		if(StringUtils.isNotBlank(indoorPocketWatchReq.getProjId())){
			params.add(indoorPocketWatchReq.getProjId());
			sqlFilter += " and PROJ_ID=?";
		}
		//报验id
		if(StringUtils.isNotBlank(indoorPocketWatchReq.getPcId())){
			sqlFilter += " and (PC_ID='' or PC_ID is null";
			if(!indoorPocketWatchReq.getPcId().equals("-1")){
				sqlFilter += " or PC_ID='"+indoorPocketWatchReq.getPcId()+"'";
			}
			sqlFilter +=")";
		}
		// 数据库根据条件过滤记录数
		int filterCount = this.getCountBySql(sqlCount.toString()+sqlFilter,params.toArray());
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		if(filterCount>0){
			 // 根据条件获取查询信息
			// 根据条件获取查询信息
			int start = indoorPocketWatchReq.getStart()+1;
			int end = start+(indoorPocketWatchReq.getLength()-1);
			sql.append(sqlFilter);
			if(StringUtils.isNotBlank(indoorPocketWatchReq.getSortName()) && StringUtils.isNotBlank(indoorPocketWatchReq.getSort())){
				sql.append(" order by  "+indoorPocketWatchReq.getSortName()+" "+indoorPocketWatchReq.getSort()+"");
			}
			//sql.append(" limit "+start+" , "+ end);
			list = this.findListBySql(sql.toString(), params.toArray());
		}
		// 返回结果
		return ResultUtil.pageResult(filterCount, indoorPocketWatchReq.getDraw(),list);
	}

	/**
	 *
	 * @author liaoyq
	 * @createTime 2017年7月24日
	 * @param pcId 报验单ID
	 */
	@Override
	public void deletePcIdIsNull(String pcId) {
		StringBuffer hql = new StringBuffer("delete from IndoorPocketWatch where pcId='").append(pcId).append("' or pcId is null");
		this.executeHql(hql.toString());
	}

	@Override
	public void deletePcIdIsNull() {
		StringBuffer hql = new StringBuffer("delete from IndoorPocketWatch where pcId='' or pcId is null");
		this.executeHql(hql.toString());
	}

	@Override
	public void updateByPcId(String pcId) {
		StringBuffer hql  =  new StringBuffer("update  IndoorPocketWatch set pcId=null where pcId='").append(pcId).append("'");
		this.executeHql(hql.toString());
	}

	@Override
	public void updatePcIdByRecordId(String id, String pcId) {
		StringBuffer hql = new StringBuffer("update  IndoorPocketWatch set pcId='").append(pcId).append("' where ipwId='").append(id).append("'");
		this.executeHql(hql.toString());
	}

	@Override
	public List<Map<String, Object>> showTableColumns() {
		String sql = " show  fields from indoor_pocket_watch where FIELD LIKE 'FLOOR_%'";
		return this.findListBySql(sql);
	}

	@Override
	public boolean addTableColumns(String str) {
		String sql = "ALTER TABLE indoor_pocket_watch " + str;
		return this.executeSql(sql);
	}

	@Override
	public Map<String, Object> queryRecords(
			IndoorPocketWatchReq indoorPocketWatchReq) {
		Criteria c = super.getCriteria();	
		//工程id
		if(StringUtils.isNotBlank(indoorPocketWatchReq.getProjId())){
			c.add(Restrictions.eq("projId",indoorPocketWatchReq.getProjId()));
		}
		//报验id
		if(StringUtils.isNotBlank(indoorPocketWatchReq.getPcId())){
			String sqlFilter ="";
			if(indoorPocketWatchReq.getFlag()!=null && indoorPocketWatchReq.getFlag()==1){
				sqlFilter += " PC_ID='"+indoorPocketWatchReq.getPcId()+"'";//已完成的报验，只查询报验单下的记录
			}else{
				sqlFilter = "(PC_ID='' or PC_ID is null";
				if(!indoorPocketWatchReq.getPcId().equals("-1")){//
					sqlFilter += " or PC_ID='"+indoorPocketWatchReq.getPcId()+"'";
				}
				sqlFilter +=")";
			}
			c.add(Restrictions.sqlRestriction(sqlFilter));
		}
		// 数据库根据条件过滤记录数
		int filterCount = this.countByCriteria(c);
		// 根据条件获取查询信息
		List<IndoorPocketWatch> list = this.findBySortCriteria(c, indoorPocketWatchReq);
		// 返回结果
		return ResultUtil.pageResult(filterCount, indoorPocketWatchReq.getDraw(),list);
	}


}
