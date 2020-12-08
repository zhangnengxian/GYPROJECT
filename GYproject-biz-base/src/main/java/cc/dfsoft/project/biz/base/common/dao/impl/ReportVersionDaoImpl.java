package cc.dfsoft.project.biz.base.common.dao.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cc.dfsoft.project.biz.base.common.dao.ReportVersionDao;
import cc.dfsoft.project.biz.base.common.dto.ReportVersionReq;
import cc.dfsoft.project.biz.base.common.entity.ReportVersion;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;
import cc.dfsoft.uexpress.common.util.StringUtil;
/**
 * 打印报表版本控制DAO实现
 * @author liaoyq
 * @createTime 2018年8月13日
 */
@Repository
public class ReportVersionDaoImpl extends NewBaseDAO<ReportVersion, String>
		implements ReportVersionDao {

	/**
	 * 查询打印报表版本控制列表
	 * @throws ParseException 
	 */
	@Override
	public List<ReportVersion> queryReportVersions(
			ReportVersionReq reportVersionReq) throws ParseException {
		Criteria c = super.getCriteria();
		if(StringUtil.isNotBlank(reportVersionReq.getCorpId())){
			c.add(Restrictions.eq("corpId",reportVersionReq.getCorpId()));
		}
		if(StringUtil.isNotBlank(reportVersionReq.getMenuId())){
			c.add(Restrictions.eq("menuId",reportVersionReq.getMenuId()));
		}
		if(StringUtil.isNotBlank(reportVersionReq.getProjType())){
			c.add(Restrictions.eq("projType",reportVersionReq.getProjType()));
		}
		//分包合同签定日期
		//用于字符串日期格式转换
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		 //受理日期开始日期
		 if(StringUtils.isNotBlank(reportVersionReq.getSignDate())){
			 c.add(Restrictions.le("rvDate", sdf.parse(reportVersionReq.getSignDate())));
		 }/*
		 StringBuffer sql = new StringBuffer();
		 if(StringUtils.isNotBlank(reportVersionReq.getSignDate())){
			 sql.append(" and RV_DATE<=").append(mysqlSqlConveter.dataOperate(reportVersionReq.getSignDate()));
		 }
		 c.add(Restrictions.sqlRestriction(sql.toString()));
		*/
		c.addOrder(Order.desc("rvDate"));
		return this.findByCriteria(c);
	}

}
