package cc.dfsoft.project.biz.base.inspection.dao.impl;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cc.dfsoft.project.biz.base.inspection.dao.PipeWeldRecordDao;
import cc.dfsoft.project.biz.base.inspection.dto.PipeWeldRecordReq;
import cc.dfsoft.project.biz.base.inspection.entity.ClearRecord;
import cc.dfsoft.project.biz.base.inspection.entity.PipeWeldRecord;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;
import cc.dfsoft.uexpress.common.util.ResultUtil;
/**
 * 焊口记录dao实现层
 * @author liaoyq
 * @createTime 2017年7月25日
 */
@Repository
public class PipeWeldRecordDaoImpl extends NewBaseDAO<PipeWeldRecord, String> implements
		PipeWeldRecordDao {

	@Override
	public void updateByPcId(String pcId) {
		StringBuffer hql  =  new StringBuffer("update  PipeWeldRecord set pcId=null where pcId='").append(pcId).append("'");
		this.executeHql(hql.toString());
	}

	@Override
	public void updatePcIdByRecordId(String id, String pcId) {
		StringBuffer hql = new StringBuffer("update  PipeWeldRecord set pcId='").append(pcId).append("' where pwrId='").append(id).append("'");
		this.executeHql(hql.toString());
	}

	@Override
	public Map<String, Object> queryRecords(PipeWeldRecordReq pipeWeldRecordReq) {
		Criteria c = super.getCriteria();	
		//工程id
		if(StringUtils.isNotBlank(pipeWeldRecordReq.getProjId())){
			c.add(Restrictions.eq("projId",pipeWeldRecordReq.getProjId()));
		}
		//报验id
		if(StringUtils.isNotBlank(pipeWeldRecordReq.getPcId())){
			String sqlFilter ="";
			if(pipeWeldRecordReq.getFlag()!=null && pipeWeldRecordReq.getFlag()==1){
				sqlFilter += " PC_ID='"+pipeWeldRecordReq.getPcId()+"'";//已完成的报验，只查询报验单下的记录
			}else{
				sqlFilter = "(PC_ID='' or PC_ID is null";
				if(!pipeWeldRecordReq.getPcId().equals("-1")){//
					sqlFilter += " or PC_ID='"+pipeWeldRecordReq.getPcId()+"'";
				}
				sqlFilter +=")";
			}
			c.add(Restrictions.sqlRestriction(sqlFilter));
		}
		// 数据库根据条件过滤记录数
		int filterCount = this.countByCriteria(c);
		// 根据条件获取查询信息
		List<PipeWeldRecord> list = this.findBySortCriteria(c, pipeWeldRecordReq);
		// 返回结果
		return ResultUtil.pageResult(filterCount, pipeWeldRecordReq.getDraw(),list);
	}

	@Override
	public void deletePcIdIsNull(String pcId) {
		StringBuffer hql = new StringBuffer("delete from PipeWeldRecord where pcId='").append(pcId).append("' or pcId is null");
		this.executeHql(hql.toString());
	}

	@Override
	public void deletePcIdIsNullByProjId(String projId) {
		StringBuffer hql = new StringBuffer("delete from PipeWeldRecord where (pcId='' or pcId is null) and projId='").append(projId).append("'");
		this.executeHql(hql.toString());
	}

	@Override
	public void deleteByPcId(String pcId) {
		StringBuffer hql = new StringBuffer("delete from PipeWeldRecord where pcId='").append(pcId).append("'");
		this.executeHql(hql.toString());
	}
}
