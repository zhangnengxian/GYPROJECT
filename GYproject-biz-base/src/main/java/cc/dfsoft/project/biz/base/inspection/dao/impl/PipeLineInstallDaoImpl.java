package cc.dfsoft.project.biz.base.inspection.dao.impl;


import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cc.dfsoft.project.biz.base.inspection.dao.PipeLineInstallDao;
import cc.dfsoft.project.biz.base.inspection.dto.PipelineInstallReq;
import cc.dfsoft.project.biz.base.inspection.entity.PipeLineInstall;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;
import cc.dfsoft.uexpress.common.util.ResultUtil;
/**
 * 管道安装dao实现层
 * @author liaoyq
 *
 */
@Repository
public class PipeLineInstallDaoImpl extends NewBaseDAO<PipeLineInstall, String> implements
		PipeLineInstallDao {

	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void deleteByPcId(String pcId) {
		StringBuffer hql = new StringBuffer("delete from PipeLineInstall where pcId='").append(pcId).append("'");
		super.executeHql(hql.toString());
	}

	@Override
	public Map<String, Object> queryPipelineInstall(
			PipelineInstallReq pipelineInstallReq) {
		Criteria c = super.getCriteria();	
		//工程id
		if(StringUtils.isNotBlank(pipelineInstallReq.getPcId())){
			c.add(Restrictions.eq("pcId",pipelineInstallReq.getPcId()));
		}
		// 数据库根据条件过滤记录数
		int filterCount = this.countByCriteria(c);
		// 根据条件获取查询信息
		List<PipeLineInstall> list = this.findBySortCriteria(c, pipelineInstallReq);
		// 返回结果
		return ResultUtil.pageResult(filterCount, pipelineInstallReq.getDraw(),list);
	}

	@Override
	public void updatePcIdByRecordId(String id, String pcId) {
		StringBuffer hql = new StringBuffer("update  PipeLineInstall set pcId='").append(pcId).append("' where pliId='").append(id).append("'");
		this.executeHql(hql.toString());
	}

	@Override
	public Map<String, Object> queryRecords(
			PipelineInstallReq pipelineInstallReq) {
		Criteria c = super.getCriteria();	
		//工程id
		if(StringUtils.isNotBlank(pipelineInstallReq.getProjId())){
			c.add(Restrictions.eq("projId",pipelineInstallReq.getProjId()));
		}
		//报验id
		if(StringUtils.isNotBlank(pipelineInstallReq.getPcId())){
			String sqlFilter ="";
			if(pipelineInstallReq.getFlag()!=null && pipelineInstallReq.getFlag()==1){
				sqlFilter += " PC_ID='"+pipelineInstallReq.getPcId()+"'";//已完成的报验，只查询报验单下的记录
			}else{
				sqlFilter = "(PC_ID='' or PC_ID is null";
				if(!pipelineInstallReq.getPcId().equals("-1")){//
					sqlFilter += " or PC_ID='"+pipelineInstallReq.getPcId()+"'";
				}
				sqlFilter +=")";
			}
			c.add(Restrictions.sqlRestriction(sqlFilter));
		}
		// 数据库根据条件过滤记录数
		int filterCount = this.countByCriteria(c);
		// 根据条件获取查询信息
		List<PipeLineInstall> list = this.findBySortCriteria(c, pipelineInstallReq);
		// 返回结果
		return ResultUtil.pageResult(filterCount, pipelineInstallReq.getDraw(),list);
	
	}

	@Override
	public void updateByPcId(String pcId) {
		StringBuffer hql  =  new StringBuffer("update  PipeLineInstall set pcId=null where pcId='").append(pcId).append("'");
		this.executeHql(hql.toString());
	}

}
