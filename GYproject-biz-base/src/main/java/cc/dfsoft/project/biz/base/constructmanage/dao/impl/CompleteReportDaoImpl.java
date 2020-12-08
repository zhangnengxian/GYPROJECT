package cc.dfsoft.project.biz.base.constructmanage.dao.impl;

import cc.dfsoft.project.biz.base.constructmanage.dao.CompleteReportDao;
import cc.dfsoft.project.biz.base.constructmanage.dto.CompleteReportReq;
import cc.dfsoft.project.biz.base.constructmanage.entity.CompleteReport;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;
import cc.dfsoft.uexpress.common.util.ResultUtil;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class CompleteReportDaoImpl extends NewBaseDAO<CompleteReport, String> implements CompleteReportDao {

	/**
	 * 竣工报告列表查询
	 * @param completeReportReq
	 * @return
	 */
	@Override
	public Map<String, Object> queryCompleteReport(CompleteReportReq completeReportReq) {
		 Criteria c = super.getCriteria();
		 c.add(Restrictions.eq("projId", completeReportReq.getProjId()));
		 c.add(Restrictions.eq("crFlag","1"));
//		 c.addOrder(Order.desc("cuDate"));  // 报审日期（项目经理签字日期）
		 // 数据库根据条件过滤记录数
		 int filterCount = this.countByCriteria(c);
		// 根据条件获取查询信息
		List<CompleteReport> completeReportList = this.findBySortCriteria(c, completeReportReq);
		// 返回结果
		return ResultUtil.pageResult( filterCount, completeReportReq.getDraw(),completeReportList);
	}

	/**
	 * 根据竣工报告id查询详述
	 * @param crId
	 * @return
	 */
	@Override
	public CompleteReport findByCrId(String crId) {
		StringBuffer hql = new StringBuffer();
		hql.append("from CompleteReport where crId = '").append(crId).append("'");
		List<CompleteReport> completeReportList = super.findByHql(hql.toString());
		CompleteReport completeReport = new CompleteReport();
		if(completeReportList.size()>0){
			completeReport=completeReportList.get(0);
		}
		return completeReport;
	}

	@Override
	public void deleteById(String crId) {
		String sql="update COMPLETE_REPORT m set m.CR_FLAG=0 where m.CR_ID="+crId;
		super.executeSql(sql);
	}

	@Override
	public List<CompleteReport> findByProjId(String projId) {
		 Criteria c = super.getCriteria();
		 c.add(Restrictions.eq("projId", projId));
		 c.add(Restrictions.eq("crFlag","1"));
		 c.addOrder(Order.desc("realEndDate"));//实际竣工日期降序排序
		return this.findByCriteria(c);
	}

	@Override
	public CompleteReport findByProjId(String projId, String cfFlag) {
		Criteria c = super.getCriteria();
		 c.add(Restrictions.eq("projId", projId));
		 c.add(Restrictions.eq("crFlag",cfFlag));
		List<CompleteReport> list = this.findByCriteria(c);
		if(list!=null && list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public void deleteByProjId(String projId, String isDel) {
		StringBuffer hql = new StringBuffer("update CompleteReport set crFlag='").append(isDel).append("' where projId='").append(projId).append("'");
		this.executeHql(hql.toString());
	}
}
