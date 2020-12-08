package cc.dfsoft.project.biz.ifs.log.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cc.dfsoft.project.biz.base.project.dto.WebserviceLogReq;
import cc.dfsoft.project.biz.ifs.log.dao.WebserviceLogDao;
import cc.dfsoft.project.biz.ifs.log.entity.WebserviceLog;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;
import cc.dfsoft.uexpress.common.util.ResultUtil;
/**
 * 接口日志dao实现层
 * @author liaoyq
 *
 */
@Repository
public class WebserviceLogDaoImpl extends NewBaseDAO<WebserviceLog, String> implements
		WebserviceLogDao {

	@Override
	public Map<String, Object> queryWebServiceLog(
			WebserviceLogReq webserviceLogReq) {
		Criteria c = super.getCriteria();
        if(StringUtils.isNotBlank(webserviceLogReq.getProjId())){
            c.add(Restrictions.eq("projId",webserviceLogReq.getProjId()));
        }
        if(StringUtils.isNotBlank(webserviceLogReq.getProjNo())){
        	c.add(Restrictions.eq("projNo",webserviceLogReq.getProjNo()));
        }
        if(StringUtils.isNotBlank(webserviceLogReq.getResultType())){
        	c.add(Restrictions.eq("resultType",webserviceLogReq.getResultType()));
        }
        if(StringUtils.isNotBlank(webserviceLogReq.getOperateType())){
        	c.add(Restrictions.eq("operateType",webserviceLogReq.getOperateType()));
        }
        if(StringUtils.isNotBlank(webserviceLogReq.getServiceType())){
        	c.add(Restrictions.eq("serviceType",webserviceLogReq.getServiceType()));
        }
        c.addOrder(Order.desc("logTime"));  // 接口调用日期
		 
		 // 数据库根据条件过滤记录数
		 int filterCount = this.countByCriteria(c);
        
		// 根据条件获取查询信息
		List<WebserviceLog> list = this.findBySortCriteria(c, webserviceLogReq);
		// 返回结果
		return ResultUtil.pageResult( filterCount, webserviceLogReq.getDraw(),list);
	}

	@Override
	public int getWebServiceLogListByProjIdResultType(String projId,String resultType) {
		Criteria c = super.getCriteria();

		if(StringUtils.isNotBlank(projId)){
			c.add(Restrictions.eq("projId",projId));
		}
		if(StringUtils.isNotBlank(resultType)){
			c.add(Restrictions.eq("resultType",resultType));
		}
		// 数据库根据条件过滤记录数
		return this.countByCriteria(c);
	}
}
