package cc.dfsoft.project.biz.base.plan.dao.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cc.dfsoft.project.biz.base.plan.dao.ProcurementPlanDao;
import cc.dfsoft.project.biz.base.plan.dto.ProcurementPlanReq;
import cc.dfsoft.project.biz.base.plan.entity.ProcurementPlan;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;
import cc.dfsoft.uexpress.common.util.ResultUtil;
@Repository
public class ProcurementPlanDaoImpl extends NewBaseDAO<ProcurementPlan, String> implements ProcurementPlanDao{

	@Override
	public Map<String, Object> queryProcurementPlan(ProcurementPlanReq procurementPlanReq) throws ParseException {
		Criteria c = super.getCriteria();
		 //工程编号 
		 if(StringUtils.isNotBlank(procurementPlanReq.getProjNo())){
			 c.add(Restrictions.eq("projNo",procurementPlanReq.getProjNo()));
		 }
		 //工程名称
		 if(StringUtils.isNotBlank(procurementPlanReq.getProjName())){
			 c.add(Restrictions.like("projName","%"+procurementPlanReq.getProjName()+"%"));
		 }
		 //工程id
		 if(StringUtils.isNotBlank(procurementPlanReq.getProjId())){
			 c.add(Restrictions.eq("projId",procurementPlanReq.getProjId()));
		 }
		 //创建时工程操作
		 if(StringUtils.isNotBlank(procurementPlanReq.getStatus())){
			 c.add(Restrictions.eq("status",procurementPlanReq.getStatus()));
		 }
		 //采购计划是否被导出
		 if(StringUtils.isNotBlank(procurementPlanReq.getIsExport())){
			 c.add(Restrictions.eq("isExport",procurementPlanReq.getIsExport()));
		 }
		 //采购计划是否反馈
		 if(StringUtils.isNotBlank(procurementPlanReq.getIsFeedBack())){
			 StringBuffer sql=new StringBuffer("proj_id in(select proj_id from project where FEEDBACK_STATE='").append(procurementPlanReq.getIsFeedBack()).append("')");
			 c.add(Restrictions.sqlRestriction(sql.toString()));
		 }
		 //施工管理处
		 if(StringUtils.isNotBlank(procurementPlanReq.getManagementOffice())){
			 StringBuffer sql=new StringBuffer("proj_id in(select proj_id from construction_plan where management_id='").append(procurementPlanReq.getManagementOffice()).append("')");
			 c.add(Restrictions.sqlRestriction(sql.toString()));
		 }
		 
		 //创建人名称
		 if(StringUtils.isNotBlank(procurementPlanReq.getCreateStaffName())){
			 c.add(Restrictions.like("projName","%"+procurementPlanReq.getCreateStaffName()+"%"));
		 }
		 //用于字符串日期格式转换
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		 //开工日期开始
		 if(StringUtils.isNotBlank(procurementPlanReq.getProjStartDateStar())){
			 c.add(Restrictions.ge("projStartDate", sdf.parse(procurementPlanReq.getProjStartDateStar())));
		 }
		 //开工日期结束
		 if(StringUtils.isNotBlank(procurementPlanReq.getProjStartDateEnd())){
			 c.add(Restrictions.le("projStartDate", sdf.parse(procurementPlanReq.getProjStartDateEnd())));
		 }
		 //创建日期开始
		 if(StringUtils.isNotBlank(procurementPlanReq.getCreateTimeStar())){
			 c.add(Restrictions.ge("createTime", sdf.parse(procurementPlanReq.getCreateTimeStar())));
		 }
		 //创建日期结束
		 if(StringUtils.isNotBlank(procurementPlanReq.getCreateTimeEnd())){
			 c.add(Restrictions.le("createTime", sdf.parse(procurementPlanReq.getCreateTimeEnd())));
		 }
		 
		 //工程类型
		 if(StringUtils.isNotBlank(procurementPlanReq.getProjType())){
			 StringBuffer sql=new StringBuffer("proj_id in(select proj_id from contract where proj_type='").append(procurementPlanReq.getProjType()).append("')");
			 c.add(Restrictions.sqlRestriction(sql.toString()));
		 }
		 
		 
		 // 数据库根据条件过滤记录数
		 int filterCount = this.countByCriteria(c);

		 // 根据条件获取查询信息
		 List<ProcurementPlan> procurementPlanList = this.findBySortCriteria(c, procurementPlanReq);
		
		 // 返回结果
		 return ResultUtil.pageResult( filterCount, procurementPlanReq.getDraw(),procurementPlanList);
	}

	@Override
	public ProcurementPlan findByBusinessOrder(String businessOrderId, String status) {
		Criteria c = super.getCriteria();
		 //业务单Id 
		 if(StringUtils.isNotBlank(businessOrderId)){
			 c.add(Restrictions.eq("businessOrderId",businessOrderId));
		 }
		 //工程操作
		 if(StringUtils.isNotBlank(status)){
			 c.add(Restrictions.eq("status",status));
		 }
		 List<ProcurementPlan> procurementPlans =this.findByCriteria(c);
		 if(procurementPlans!=null&&procurementPlans.size()>0){
			 return procurementPlans.get(0);
		 }else{
			 return null;
		 }
	}

	@Override
	public List<ProcurementPlan> queryProcuPlanById(String projId) {
		Criteria c = super.getCriteria();
		
		 if(StringUtils.isNotBlank(projId)){
			 c.add(Restrictions.eq("projId",projId));
		 }
		 List<ProcurementPlan> procurementPlans =this.findByCriteria(c);
		 
		 return procurementPlans;
	}
	
	/**
	 * 工程交底 根据工程id和状态
	 * @author
	 * @createTime 2017-1-28
	 * @param 
	 * @return
	 */
	@Override
	public ProcurementPlan findBypProjIdAndStatus(String projId, String status) {
		Criteria c = super.getCriteria();
		 //业务单Id 
		 if(StringUtils.isNotBlank(projId)){
			 c.add(Restrictions.eq("projId",projId));
		 }
		 //工程操作
		 if(StringUtils.isNotBlank(status)){
			 c.add(Restrictions.eq("status",status));
		 }
		 List<ProcurementPlan> procurementPlans =this.findByCriteria(c);
		 if(procurementPlans!=null&&procurementPlans.size()>0){
			 return procurementPlans.get(0);
		 }else{
			 return null;
		 }
	}
	
	

}
