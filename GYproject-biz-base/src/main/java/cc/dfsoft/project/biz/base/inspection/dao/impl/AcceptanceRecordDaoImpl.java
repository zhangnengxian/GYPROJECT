package cc.dfsoft.project.biz.base.inspection.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cc.dfsoft.project.biz.base.inspection.dao.AcceptanceRecordDao;
import cc.dfsoft.project.biz.base.inspection.dto.ElectrodeRecordReq;
import cc.dfsoft.project.biz.base.inspection.entity.AcceptanceRecord;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;
import cc.dfsoft.uexpress.common.util.ResultUtil;
@Repository
public class AcceptanceRecordDaoImpl extends NewBaseDAO<AcceptanceRecord, String> implements  AcceptanceRecordDao {

	@Override
	public Map<String, Object> queryRecords(ElectrodeRecordReq electrodeRecordReq) {
		Criteria c = super.getCriteria();	
		//工程id
		/*if(StringUtils.isNotBlank(electrodeRecordReq.getProjId())){
			c.add(Restrictions.eq("projId",electrodeRecordReq.getProjId()));
		}*/
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
		List<AcceptanceRecord> list = this.findBySortCriteria(c, electrodeRecordReq);
		// 返回结果
		return ResultUtil.pageResult(filterCount, electrodeRecordReq.getDraw(),list);
	}
	/**
	 * 根据报验单ID删验收登记记录
	 * @author fuliwei  
	 * @date 2018年12月4日  
	 * @version 1.0
	 */
	@Override
	public void deleteByPcId(String pcId) {
		StringBuffer hql = new StringBuffer("delete from AcceptanceRecord where pcId='").append(pcId).append("'");
		super.executeHql(hql.toString());
	}
	/**
	 * 根据记录ID回写报验单
	 * @author fuliwei  
	 * @date 2018年12月4日  
	 * @version 1.0
	 */
	@Override
	public void updatePcIdByRecordId(String id, String pcId) {
		StringBuffer hql = new StringBuffer("update  AcceptanceRecord set pcId='").append(pcId).append("' where arId='").append(id).append("'");
		this.executeHql(hql.toString());
	}
	/**
	 * 清空pcId
	 */
	@Override
	public void updateByPcId(String pcId) {
		StringBuffer hql  =  new StringBuffer("update  AcceptanceRecord set pcId=null where pcId='").append(pcId).append("'");
		this.executeHql(hql.toString());
	}

}
