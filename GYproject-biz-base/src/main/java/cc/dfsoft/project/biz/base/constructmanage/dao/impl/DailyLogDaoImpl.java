package cc.dfsoft.project.biz.base.constructmanage.dao.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cc.dfsoft.project.biz.base.baseinfo.dao.BusinessPartnersDao;
import cc.dfsoft.project.biz.base.baseinfo.entity.BusinessPartners;
import cc.dfsoft.project.biz.base.constructmanage.dao.DailyLogDao;
import cc.dfsoft.project.biz.base.constructmanage.dto.DailyLogQueryReq;
import cc.dfsoft.project.biz.base.constructmanage.entity.DailyLog;
import cc.dfsoft.project.biz.base.design.enums.UnitTypeEnum;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;
import cc.dfsoft.uexpress.common.util.ResultUtil;

@Repository
public class DailyLogDaoImpl extends NewBaseDAO<DailyLog,String> implements DailyLogDao{
	
	/**业务合作伙伴Dao*/
	@Resource
	BusinessPartnersDao businessPartnersDao;
	
	@Override
	public Map<String, Object> queryDailyLog(DailyLogQueryReq dailyLogQueryReq) {
		
		 Criteria c = super.getCriteria();
		 LoginInfo loginInfo = SessionUtil.getLoginInfo();
		 
		 BusinessPartners bp =  businessPartnersDao.get(loginInfo.getDeptId());
		 if(bp!=null){
			 //是否是施工单位
			 if(UnitTypeEnum.CONSTRUCTION_UNIT.getValue().equals(bp.getUnitType())){
				 StringBuffer sql=new StringBuffer(" dl_recorder_id in (select staff_id from staff where dept_id=").append(loginInfo.getDeptId()).append(")");
				 c.add(Restrictions.sqlRestriction(sql.toString()));
			 }
		 }
		 
		 
		 //工程id
		 if(StringUtils.isNotBlank(dailyLogQueryReq.getProjId())){
			 c.add(Restrictions.eq("projId",dailyLogQueryReq.getProjId()));
		 }
		 
		 c.addOrder(Order.desc("dlDate"));
		 // 数据库根据条件过滤记录数
		 int filterCount = this.countByCriteria(c);

		 // 根据条件获取查询信息
		 List<DailyLog> dailyLogList = this.findBySortCriteria(c, dailyLogQueryReq);
		
		 // 返回结果
		 return ResultUtil.pageResult( filterCount, dailyLogQueryReq.getDraw(),dailyLogList);
	}

	/**
	 * 根据工程ID查询工程下的所有施工日志
	 */
	@Override
	public List<DailyLog> findByProjId(String projId) {
		 Criteria c = super.getCriteria();
		 //工程id
		 if(StringUtils.isNotBlank(projId)){
			 c.add(Restrictions.eq("projId",projId));
		 }
		return this.findByCriteria(c);
	}

}
