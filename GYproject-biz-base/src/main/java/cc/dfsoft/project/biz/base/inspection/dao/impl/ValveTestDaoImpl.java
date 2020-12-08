package cc.dfsoft.project.biz.base.inspection.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cc.dfsoft.project.biz.base.inspection.dao.ValveTestDao;
import cc.dfsoft.project.biz.base.inspection.dto.ValveTestReq;
import cc.dfsoft.project.biz.base.inspection.entity.ValveTest;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;
import cc.dfsoft.uexpress.common.util.ResultUtil;
/**
 * 阀门试验dao实现层
 * @author liaoyq
 *
 */
@Repository
public class ValveTestDaoImpl extends NewBaseDAO<ValveTest, String>
		implements ValveTestDao {

	@Override
	public Map<String, Object> queryValveTest(ValveTestReq valveTestReq) {
		Criteria c = super.getCriteria();	
		//工程id
		if(StringUtils.isNotBlank(valveTestReq.getPcId())){
			c.add(Restrictions.eq("pcId",valveTestReq.getPcId()));
		}
		// 数据库根据条件过滤记录数
		int filterCount = this.countByCriteria(c);
		// 根据条件获取查询信息
		List<ValveTest> list = this.findBySortCriteria(c, valveTestReq);
		// 返回结果
		return ResultUtil.pageResult(filterCount, valveTestReq.getDraw(),list);
	}

	@Override
	public void deleteByPcId(String pcId) {
		StringBuffer hql = new StringBuffer("delete from ValveTest where pcId='").append(pcId).append("'");
		this.executeHql(hql.toString());
	}

	@Override
	public void updateByPcId(String pcId) {
		StringBuffer hql  =  new StringBuffer("update  ValveTest set pcId=null where pcId='").append(pcId).append("'");
		this.executeHql(hql.toString());
	}

	@Override
	public void updatePcIdByRecordId(String id, String pcId) {
		StringBuffer hql = new StringBuffer("update  ValveTest set pcId='").append(pcId).append("' where vtId='").append(id).append("'");
		this.executeHql(hql.toString());
	}

	@Override
	public Map<String, Object> queryRecords(ValveTestReq valveTestReq) {
		Criteria c = super.getCriteria();	
		//工程id
		if(StringUtils.isNotBlank(valveTestReq.getProjId())){
			c.add(Restrictions.eq("projId",valveTestReq.getProjId()));
		}
		//报验id
		if(StringUtils.isNotBlank(valveTestReq.getPcId())){
			String sqlFilter ="";
			if(valveTestReq.getFlag()!=null && valveTestReq.getFlag()==1){
				sqlFilter += " PC_ID='"+valveTestReq.getPcId()+"'";//已完成的报验，只查询报验单下的记录
			}else{
				sqlFilter = "(PC_ID='' or PC_ID is null";
				if(!valveTestReq.getPcId().equals("-1")){//
					sqlFilter += " or PC_ID='"+valveTestReq.getPcId()+"'";
				}
				sqlFilter +=")";
			}
			c.add(Restrictions.sqlRestriction(sqlFilter));
		}
		// 数据库根据条件过滤记录数
		int filterCount = this.countByCriteria(c);
		// 根据条件获取查询信息
		List<ValveTest> list = this.findBySortCriteria(c, valveTestReq);
		// 返回结果
		return ResultUtil.pageResult(filterCount, valveTestReq.getDraw(),list);
	}

}
