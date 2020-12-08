package cc.dfsoft.project.biz.base.inspection.dao.impl;

import java.util.List;
import java.util.Map;













import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cc.dfsoft.project.biz.base.inspection.dao.DerustingPreservativeDao;
import cc.dfsoft.project.biz.base.inspection.dto.DerustingPreservativeQueryReq;
import cc.dfsoft.project.biz.base.inspection.dto.PreservativeReq;
import cc.dfsoft.project.biz.base.inspection.entity.DerustingPreservative;
import cc.dfsoft.project.biz.base.inspection.entity.TrenchBackfill;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;
import cc.dfsoft.uexpress.common.util.ResultUtil;
/**
 * 防腐记录dao实现层
 * @author liaoyq
 * @createTime 2017年7月24日
 */
@Repository
public class DerustingPreservativeDaoImpl extends NewBaseDAO<DerustingPreservative, String> implements DerustingPreservativeDao{

	/**
	 * 分页查询记录信息
	 */
	@Override
	public Map<String, Object> queryDerusting(DerustingPreservativeQueryReq dpQueryReq) {
		Criteria c = super.getCriteria();

		if(StringUtils.isNotBlank(dpQueryReq.getPcId())){
			c.add(Restrictions.eq("pcId", dpQueryReq.getPcId()));
		}
		// 数据库根据条件过滤记录数
		int filterCount = this.countByCriteria(c);
		// 根据条件获取查询信息
		List<DerustingPreservative> list = this.findBySortCriteria(c, dpQueryReq);
		// 返回结果
		return ResultUtil.pageResult( filterCount, dpQueryReq.getDraw(),list);
	}

	@Override
	public Map<String, Object> queryRecords(PreservativeReq preservativeReq) {
		Criteria c = super.getCriteria();	
		//工程id
		if(StringUtils.isNotBlank(preservativeReq.getProjId())){
			c.add(Restrictions.eq("projId",preservativeReq.getProjId()));
		}
		//报验id
		if(StringUtils.isNotBlank(preservativeReq.getPcId())){
			String sqlFilter ="";
			if(preservativeReq.getFlag()!=null && preservativeReq.getFlag()==1){
				sqlFilter += " PC_ID='"+preservativeReq.getPcId()+"'";//已完成的报验，只查询报验单下的记录
			}else{
				sqlFilter = "(PC_ID='' or PC_ID is null";
				if(!preservativeReq.getPcId().equals("-1")){//
					sqlFilter += " or PC_ID='"+preservativeReq.getPcId()+"'";
				}
				sqlFilter +=")";
			}
			c.add(Restrictions.sqlRestriction(sqlFilter));
		}
		//防腐记录类型
		if(StringUtils.isNotBlank(preservativeReq.getPreservativeType())){
			c.add(Restrictions.eq("preservativeType", preservativeReq.getPreservativeType()));
		}
		// 数据库根据条件过滤记录数
		int filterCount = this.countByCriteria(c);
		// 根据条件获取查询信息
		List<DerustingPreservative> list = this.findBySortCriteria(c, preservativeReq);
		// 返回结果
		return ResultUtil.pageResult(filterCount, preservativeReq.getDraw(),list);
	}

	/**
	 * 删除已报验的记录
	 * @author laioyq
	 * @createTime 2017-7-20
	 * @param pcId 报验单ＩＤ
	 */
	@Override
	public void deleteByPcId(String pcId) {
		StringBuffer hql = new StringBuffer("delete from TrenchBackfill where pcId='").append(pcId).append("'");
		this.executeHql(hql.toString());
	}

	/**
	 * 删除未报验的记录
	 * @author laioyq
	 * @createTime 2017-7-20
	 * @param pcId 报验单ＩＤ
	 */
	@Override
	public void deletePcIdIsNull() {
		StringBuffer hql = new StringBuffer("delete from TrenchBackfill where pcId='' or pcId is null");
		this.executeHql(hql.toString());
	}

	/**
	 * 置空记录表中的报验单ID
	 * @author laioyq
	 * @createTime 2017-7-20
	 * @param pcId 报验单ＩＤ
	 */
	@Override
	public void updateByPcId(String pcId) {
		StringBuffer hql  =  new StringBuffer("update  DerustingPreservative set pcId=null where pcId='").append(pcId).append("'");
		this.executeHql(hql.toString());
	}

	/**
	 *　根据记录ID回写报验单ＩＤ
	 * @author laioyq
	 * @createTime 2017-7-20
	 * @param pcId 报验单ＩＤ
	 */
	@Override
	public void updatePcIdByRecordId(String id, String pcId) {
		StringBuffer hql = new StringBuffer("update  DerustingPreservative set pcId='").append(pcId).append("' where dpId='").append(id).append("'");
		this.executeHql(hql.toString());
	}

	/**
	 * 删除已报验(pcId)和未报验的记录
	 * @author laioyq
	 * @createTime 2017-7-20
	 * @param pcId 报验单ＩＤ
	 */
	@Override
	public void deletePcIdIsNull(String pcId) {
		StringBuffer hql = new StringBuffer("delete from DerustingPreservative where pcId='").append(pcId).append("' or pcId is null");
		this.executeHql(hql.toString());
	}

	/**
	 * 根据工程ID删除记录
	 * @author liaoyq
	 * @createTime 2017-7-25
	 * @param projId 工程ID
	 */
	@Override
	public void deleteByProjId(String projId) {
		StringBuffer hql = new StringBuffer("delete from DerustingPreservative where projId='").append(projId).append("'");
		this.executeHql(hql.toString());
	}

	@Override
	public void deleteByPcIdAndType(String pcId, String preservativeType) {
		StringBuffer hql = new StringBuffer("delete from DerustingPreservative where pcId='").append(pcId).append("'").append(" and preservativeType='").append(preservativeType).append("'");
		this.executeHql(hql.toString());
	}

	@Override
	public void deleteByProjIdAndType(String projId, String preservativeType) {
		StringBuffer hql = new StringBuffer("delete from DerustingPreservative where projId='").append(projId).append("'").append(" and preservativeType='").append(preservativeType).append("'");
		this.executeHql(hql.toString());
	}

}
