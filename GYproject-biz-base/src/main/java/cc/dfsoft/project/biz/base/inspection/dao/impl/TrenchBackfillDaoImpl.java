package cc.dfsoft.project.biz.base.inspection.dao.impl;

import java.util.List;
import java.util.Map;




import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cc.dfsoft.project.biz.base.inspection.dao.TrenchBackfillDao;
import cc.dfsoft.project.biz.base.inspection.dto.TrenchBackfillReq;
import cc.dfsoft.project.biz.base.inspection.entity.TrenchBackfill;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;
import cc.dfsoft.uexpress.common.util.ResultUtil;
/**
 * 沟槽回填dao实现层
 * @author liaoyq
 * @createTime 2017年7月20日
 */
@Repository
public class TrenchBackfillDaoImpl extends NewBaseDAO<TrenchBackfill, String> implements TrenchBackfillDao {

	@Override
	public TrenchBackfill queryTrenchBackfillById(String id) {
		Criteria c = super.getCriteria();	
		//工程id
		c.add(Restrictions.eq("pcId",id));
		// 数据库根据条件过滤记录数
		int filterCount = this.countByCriteria(c);
		// 根据条件获取查询信息
		List<TrenchBackfill> list = this.findByCriteria(c);
		// 返回结果
		if(list != null && list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}

	/**
	 * 分野查询记录信息
	 * @author laioyq
	 * @createTime 2017-7-20
	 * @param trenchBackfillReq 沟槽回填记录查询辅助类
	 * @return  Map<String, Object>
	 */
	@Override
	public Map<String, Object> queryRecords(
			TrenchBackfillReq trenchBackfillReq) {
		Criteria c = super.getCriteria();	
		//工程id
		if(StringUtils.isNotBlank(trenchBackfillReq.getProjId())){
			c.add(Restrictions.eq("projId",trenchBackfillReq.getProjId()));
		}
		//报验id
		if(StringUtils.isNotBlank(trenchBackfillReq.getPcId())){
			String sqlFilter ="";
			if(trenchBackfillReq.getFlag()!=null && trenchBackfillReq.getFlag()==1){
				sqlFilter += " PC_ID='"+trenchBackfillReq.getPcId()+"'";//已完成的报验，只查询报验单下的记录
			}else{
				sqlFilter = "(PC_ID='' or PC_ID is null";
				if(!trenchBackfillReq.getPcId().equals("-1")){//
					sqlFilter += " or PC_ID='"+trenchBackfillReq.getPcId()+"'";
				}
				sqlFilter +=")";
			}
			c.add(Restrictions.sqlRestriction(sqlFilter));
		}
		// 数据库根据条件过滤记录数
		int filterCount = this.countByCriteria(c);
		// 根据条件获取查询信息
		List<TrenchBackfill> list = this.findBySortCriteria(c, trenchBackfillReq);
		// 返回结果
		return ResultUtil.pageResult(filterCount, trenchBackfillReq.getDraw(),list);
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
		StringBuffer hql  =  new StringBuffer("update  TrenchBackfill set pcId=null where pcId='").append(pcId).append("'");
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
		StringBuffer hql = new StringBuffer("update  TrenchBackfill set pcId='").append(pcId).append("' where tbId='").append(id).append("'");
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
		StringBuffer hql = new StringBuffer("delete from TrenchBackfill where pcId='").append(pcId).append("' or pcId is null");
		this.executeHql(hql.toString());
	}

}
