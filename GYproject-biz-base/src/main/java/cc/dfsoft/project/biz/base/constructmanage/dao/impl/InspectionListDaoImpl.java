package cc.dfsoft.project.biz.base.constructmanage.dao.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cc.dfsoft.project.biz.base.constructmanage.dao.InspectionListDao;
import cc.dfsoft.project.biz.base.constructmanage.dto.InspectionListQueryReq;
import cc.dfsoft.project.biz.base.constructmanage.entity.InspectionList;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;
import cc.dfsoft.uexpress.common.util.ResultUtil;
@Repository
public class InspectionListDaoImpl extends NewBaseDAO<InspectionList, String> implements InspectionListDao {

	@Override
	public Map<String, Object> queryInspectionList(InspectionListQueryReq inspectionListQueryReq)
			throws Exception  {
		Criteria c = super.getCriteria();
		//工程id
		if(StringUtils.isNotBlank(inspectionListQueryReq.getProjId())){
			c.add(Restrictions.eq("projId", inspectionListQueryReq.getProjId()));
		}
		//检查单类型
		if(StringUtils.isNotBlank(inspectionListQueryReq.getType())){
			c.add(Restrictions.eq("type", inspectionListQueryReq.getType()));
		}
		//检查人
//		if(StringUtils.isNotBlank(inspectionListQueryReq.getInspector())){
//			c.add(Restrictions.like("inspector","%"+inspectionListQueryReq.getInspector()+"%"));
//		}
		 //用于字符串日期格式转换
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		 //检查日期开始日期
		 if(StringUtils.isNotBlank(inspectionListQueryReq.getCheckDateStart())){
			 c.add(Restrictions.ge("checkDate", sdf.parse(inspectionListQueryReq.getCheckDateStart())));
		 }
		 //检查日期结束日期
		 if(StringUtils.isNotBlank(inspectionListQueryReq.getCheckDateEnd())){
			 c.add(Restrictions.le("checkDate", sdf.parse(inspectionListQueryReq.getCheckDateEnd())));
		 }
		 //扣分下限
		 if(StringUtils.isNotBlank(inspectionListQueryReq.getTotalStart())){
			 c.add(Restrictions.ge("total", Double.parseDouble(inspectionListQueryReq.getTotalStart())));
		 }
		 //扣分上限
		 if(StringUtils.isNotBlank(inspectionListQueryReq.getTotalEnd())){
			 c.add(Restrictions.le("total", Double.parseDouble(inspectionListQueryReq.getTotalEnd())));
		 }
		 c.addOrder(Order.desc("ilId"));
		// 数据库根据条件过滤记录数
		int filterCount = this.countByCriteria(c);

		// 根据条件获取查询信息
		List<InspectionList> list = this.findBySortCriteria(c, inspectionListQueryReq);
		
		// 返回结果
		return ResultUtil.pageResult( filterCount, inspectionListQueryReq.getDraw(),list);
	}

	@Override
	public List<Map<String, Object>> queryInspectionList(String param) {
		Criteria c = super.getCriteria();
		StringBuffer sql = new StringBuffer();
		sql.append("select ").append(mysqlSqlConveter.dataOperateYearAndMonth("a.CHECK_DATE")).append(" YEAR,COUNT(*) COUNT from");
		//updateaddmonths
		//sql.append(" (select ins.*,ADD_MONTHS(sysdate, -36) dateTime3 from inspection_list ins)a");
		sql.append(" (select ins.*,").append(mysqlSqlConveter.funcAddMonths("sysdate",-36)).append(" dateTime3 from inspection_list ins)a");
		sql.append(" where a.construction_department= ?");
		sql.append(" and a.CHECK_DATE>=a.dateTime3  group by ").append(mysqlSqlConveter.dataOperateYearAndMonth("a.CHECK_DATE")).append(" order by YEAR");
		List<Map<String, Object>> list = super.findListBySql(sql.toString(), new Object[]{param});
		return list;
	}
}
