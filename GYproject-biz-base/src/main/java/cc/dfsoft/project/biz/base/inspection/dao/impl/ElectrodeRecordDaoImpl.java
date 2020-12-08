package cc.dfsoft.project.biz.base.inspection.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cc.dfsoft.project.biz.base.inspection.dao.ElectrodeRecordDao;
import cc.dfsoft.project.biz.base.inspection.dto.ElectrodeRecordReq;
import cc.dfsoft.project.biz.base.inspection.entity.ElectrodeRecord;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;
import cc.dfsoft.uexpress.common.util.ResultUtil;
/**
 * 焊条领用Dao实现层
 * 
 * @author liaoyq
 *
 */
@Repository
public class ElectrodeRecordDaoImpl extends NewBaseDAO<ElectrodeRecord, String> implements ElectrodeRecordDao {

	@Override
	public void deleteByPcId(String pcId) {
		StringBuffer hql = new StringBuffer("delete from ElectrodeRecord where pcId='").append(pcId).append("'");
		super.executeHql(hql.toString());
	}

	@Override
	public Map<String, Object> queryEclectrodeRecords(
			ElectrodeRecordReq electrodeRecordReq) {
		Criteria c = super.getCriteria();	
		//工程id
		if(StringUtils.isNotBlank(electrodeRecordReq.getPcId())){
			c.add(Restrictions.eq("pcId",electrodeRecordReq.getPcId()));
		}
		// 数据库根据条件过滤记录数
		int filterCount = this.countByCriteria(c);
		// 根据条件获取查询信息
		List<ElectrodeRecord> list = this.findBySortCriteria(c, electrodeRecordReq);
		// 返回结果
		return ResultUtil.pageResult(filterCount, electrodeRecordReq.getDraw(),list);
	}

	/**
	 *　根据记录ID回写报验单ＩＤ
	 * @author laioyq
	 * @createTime 2017-7-20
	 * @param pcId 报验单ＩＤ
	 */
	@Override
	public void updatePcIdByRecordId(String id, String pcId) {
		StringBuffer hql = new StringBuffer("update  ElectrodeRecord set pcId='").append(pcId).append("' where erId='").append(id).append("'");
		this.executeHql(hql.toString());
	}

	@Override
	public Map<String, Object> queryRecords(
			ElectrodeRecordReq electrodeRecordReq) {
		Criteria c = super.getCriteria();	
		//工程id
		if(StringUtils.isNotBlank(electrodeRecordReq.getProjId())){
			c.add(Restrictions.eq("projId",electrodeRecordReq.getProjId()));
		}
		//报验id
		if(StringUtils.isNotBlank(electrodeRecordReq.getPcId())){
			String sqlFilter ="";
			if(electrodeRecordReq.getFlag()!=null && electrodeRecordReq.getFlag()==1){
				sqlFilter += " PC_ID='"+electrodeRecordReq.getPcId()+"'";//已完成的报验，只查询报验单下的记录
			}else{
				sqlFilter = "(PC_ID='' or PC_ID is null";
				if(!electrodeRecordReq.getPcId().equals("-1")){//
					sqlFilter += " or PC_ID='"+electrodeRecordReq.getPcId()+"'";
				}
				sqlFilter +=")";
			}
			c.add(Restrictions.sqlRestriction(sqlFilter));
		}
		// 数据库根据条件过滤记录数
		int filterCount = this.countByCriteria(c);
		// 根据条件获取查询信息
		List<ElectrodeRecord> list = this.findBySortCriteria(c, electrodeRecordReq);
		// 返回结果
		return ResultUtil.pageResult(filterCount, electrodeRecordReq.getDraw(),list);
	}

	@Override
	public void updateByPcId(String pcId) {
		StringBuffer hql  =  new StringBuffer("update  ElectrodeRecord set pcId=null where pcId='").append(pcId).append("'");
		this.executeHql(hql.toString());
	}

	
}
