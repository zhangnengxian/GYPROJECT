package cc.dfsoft.project.biz.base.inspection.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cc.dfsoft.project.biz.base.inspection.dao.ClearRecordDao;
import cc.dfsoft.project.biz.base.inspection.dto.ClearRecordReq;
import cc.dfsoft.project.biz.base.inspection.entity.ClearRecord;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;
import cc.dfsoft.uexpress.common.util.ResultUtil;
/**
 * 清扫记录dao实现层
 * @author lliaoyq
 * @createTime 2017年7月25日
 */
@Repository
public class ClearRecordDaoImpl extends NewBaseDAO<ClearRecord, String>
		implements ClearRecordDao {

	/**
	 * 根据pcId将记录的报验单ID置空
	 * @author liaoyq
	 * @createTime 2017年7月25日
	 * @param pcId 报验单ID
	 */
	@Override
	public void updateByPcId(String pcId) {
		StringBuffer hql  =  new StringBuffer("update  ClearRecord set pcId=null where pcId='").append(pcId).append("'");
		this.executeHql(hql.toString());
	}

	@Override
	public void updatePcIdByRecordId(String id, String pcId) {
		StringBuffer hql = new StringBuffer("update  ClearRecord set pcId='").append(pcId).append("' where crId='").append(id).append("'");
		this.executeHql(hql.toString());
	}

	@Override
	public Map<String, Object> queryRecords(ClearRecordReq clearRecordReq) {
		Criteria c = super.getCriteria();	
		//工程id
		if(StringUtils.isNotBlank(clearRecordReq.getProjId())){
			c.add(Restrictions.eq("projId",clearRecordReq.getProjId()));
		}
		//报验id
		if(StringUtils.isNotBlank(clearRecordReq.getPcId())){
			String sqlFilter ="";
			if(clearRecordReq.getFlag()!=null && clearRecordReq.getFlag()==1){
				sqlFilter += " PC_ID='"+clearRecordReq.getPcId()+"'";//已完成的报验，只查询报验单下的记录
			}else{
				sqlFilter = "(PC_ID='' or PC_ID is null";
				if(!clearRecordReq.getPcId().equals("-1")){//
					sqlFilter += " or PC_ID='"+clearRecordReq.getPcId()+"'";
				}
				sqlFilter +=")";
			}
			c.add(Restrictions.sqlRestriction(sqlFilter));
		}
		// 数据库根据条件过滤记录数
		int filterCount = this.countByCriteria(c);
		// 根据条件获取查询信息
		List<ClearRecord> list = this.findBySortCriteria(c, clearRecordReq);
		// 返回结果
		return ResultUtil.pageResult(filterCount, clearRecordReq.getDraw(),list);
	}

	@Override
	public void deletePcIdIsNull(String pcId) {
		StringBuffer hql = new StringBuffer("delete from ClearRecord where pcId='").append(pcId).append("' or pcId is null");
		this.executeHql(hql.toString());
	}

	@Override
	public void deletePcIdIsNullByProjId(String projId) {
		StringBuffer hql = new StringBuffer("delete from ClearRecord where (pcId='' or pcId is null) and projId='").append(projId).append("'");
		this.executeHql(hql.toString());
	}

	@Override
	public void deleteByPcId(String pcId) {
		StringBuffer hql = new StringBuffer("delete from ClearRecord where pcId='").append(pcId).append("'");
		this.executeHql(hql.toString());
	}

}
