package cc.dfsoft.project.biz.base.inspection.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cc.dfsoft.project.biz.base.inspection.dao.ElectrodeBakingDao;
import cc.dfsoft.project.biz.base.inspection.dto.ElectrodeBakingReq;
import cc.dfsoft.project.biz.base.inspection.entity.ElectrodeBaking;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;
import cc.dfsoft.uexpress.common.util.ResultUtil;
/**
 * 焊条烘烤dao实现
 * @author liaoyq
 *
 */
@Repository
public class ElectrodeBakingDaoImpl extends NewBaseDAO<ElectrodeBaking, String> implements ElectrodeBakingDao {

	@Override
	public Map<String, Object> queryEclectrodeBakings(
			ElectrodeBakingReq electrodeBakingReq) {
		Criteria c = super.getCriteria();	
		//工程id
		if(StringUtils.isNotBlank(electrodeBakingReq.getPcId())){
			c.add(Restrictions.eq("pcId",electrodeBakingReq.getPcId()));
		}
		// 数据库根据条件过滤记录数
		int filterCount = this.countByCriteria(c);
		// 根据条件获取查询信息
		List<ElectrodeBaking> list = this.findBySortCriteria(c,electrodeBakingReq);
		// 返回结果
		return ResultUtil.pageResult(filterCount, electrodeBakingReq.getDraw(),list);
	}

	@Override
	public void deleteByPcId(String pcId) {
		StringBuffer hql = new StringBuffer("delete from ElectrodeBaking where pcId='").append(pcId).append("'");
		super.executeHql(hql.toString());
		
	}

	@Override
	public Map<String, Object> queryRecords(ElectrodeBakingReq ebReq) {
		Criteria c = super.getCriteria();	
		//工程id
		if(StringUtils.isNotBlank(ebReq.getProjId())){
			c.add(Restrictions.eq("projId",ebReq.getProjId()));
		}
		//报验id
		if(StringUtils.isNotBlank(ebReq.getPcId())){
			String sqlFilter ="";
			if(ebReq.getFlag()!=null && ebReq.getFlag()==1){
				sqlFilter += " PC_ID='"+ebReq.getPcId()+"'";//已完成的报验，只查询报验单下的记录
			}else{
				sqlFilter = "(PC_ID='' or PC_ID is null";
				if(!ebReq.getPcId().equals("-1")){//
					sqlFilter += " or PC_ID='"+ebReq.getPcId()+"'";
				}
				sqlFilter +=")";
			}
			c.add(Restrictions.sqlRestriction(sqlFilter));
		}
		// 数据库根据条件过滤记录数
		int filterCount = this.countByCriteria(c);
		// 根据条件获取查询信息
		List<ElectrodeBaking> list = this.findBySortCriteria(c, ebReq);
		// 返回结果
		return ResultUtil.pageResult(filterCount, ebReq.getDraw(),list);
	
	}

	@Override
	public void updatePcIdByRecordId(String id, String pcId) {
		StringBuffer hql = new StringBuffer("update  ElectrodeBaking set pcId='").append(pcId).append("' where ebId='").append(id).append("'");
		this.executeHql(hql.toString());
	}

	@Override
	public void updateByPcId(String pcId) {
		StringBuffer hql  =  new StringBuffer("update  ElectrodeBaking set pcId=null where pcId='").append(pcId).append("'");
		this.executeHql(hql.toString());
	}

	

}
