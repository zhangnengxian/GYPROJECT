package cc.dfsoft.project.biz.base.project.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import cc.dfsoft.project.biz.base.project.dao.OperateRecordLogDao;
import cc.dfsoft.project.biz.base.project.entity.OperateRecordLog;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;
@Repository
public class OperateRecordLogDaoImpl extends NewBaseDAO<OperateRecordLog, String> implements OperateRecordLogDao{

	@SuppressWarnings("unchecked")
	@Override
	public OperateRecordLog findLatelyLog(String operateType, String businessId) {
		StringBuffer hql = new StringBuffer();
		hql.append("from OperateRecordLog where operateType ='").append(operateType).append("' and businessId = '")
		.append(businessId).append("'");
		hql.append(" order by operateTime desc");
		List<OperateRecordLog> result = super.findByHql(hql.toString());
		if(result.size()>0){
			return result.get(0);
		}
		return null;
	}
}
