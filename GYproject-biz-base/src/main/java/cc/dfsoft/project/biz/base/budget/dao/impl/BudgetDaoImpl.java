package cc.dfsoft.project.biz.base.budget.dao.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import cc.dfsoft.uexpress.common.constant.DataFilerSetUpDto;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cc.dfsoft.project.biz.base.baseinfo.dao.BusinessPartnersDao;
import cc.dfsoft.project.biz.base.baseinfo.entity.BusinessPartners;
import cc.dfsoft.project.biz.base.budget.dao.BudgetDao;
import cc.dfsoft.project.biz.base.budget.dto.BudgetChangeReq;
import cc.dfsoft.project.biz.base.budget.dto.BudgetReq;
import cc.dfsoft.project.biz.base.budget.entity.Budget;
import cc.dfsoft.project.biz.base.design.dto.DesignerQueryReq;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.biz.sys.dept.enums.BusinessTypeEnum;
import cc.dfsoft.uexpress.biz.sys.dept.enums.DeleteOfMarkEnum;
import cc.dfsoft.uexpress.biz.sys.dept.enums.DeptDivideEnum;
import cc.dfsoft.uexpress.biz.sys.dept.enums.DeptTypeEnum;
import cc.dfsoft.uexpress.biz.sys.dept.enums.PostTypeEnum;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;
import cc.dfsoft.uexpress.common.util.ResultUtil;
import cc.dfsoft.uexpress.common.util.StringUtil;

@Repository
public class BudgetDaoImpl extends NewBaseDAO<Budget, String> implements BudgetDao {
	
	/**业务合作伙伴*/
	@Resource
	BusinessPartnersDao businessPartnersDao;
	
	/**
	 * 根据projId查预算总表
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Budget queryBudgeByprojId(String projId) {
		StringBuffer hql = new StringBuffer();
		hql.append("from Budget e where e.projId='").append(projId).append("'");
		List<Budget>list = super.findByHql(hql.toString());
		if(null!=list&&list.size()>0){
		return list.get(0);
		}
		return null;
	}
	/**
	 * 根据工程id和预算类型id查询预算总表
	 * @author fuliwei
	 * @createTime 2016-7-14
	 * @param
	 * @return Budget
	 */
	@Override
	public Budget queryById(String projId, String budgetTypeId) {
		StringBuffer hql = new StringBuffer();
		if(StringUtils.isNotBlank(projId)){
			hql.append("from Budget b where b.projId='").append(projId).append("'");
		}
		if(StringUtils.isNotBlank(budgetTypeId)){
			hql.append("and b.budgetTypeId='").append(budgetTypeId).append("'");
		}
		
		List<Budget>list = super.findByHql(hql.toString());
		if(null!=list&&list.size()>0){
		return list.get(0);
		}
		return new Budget();

	}
	@Override
	public Budget queryByType(String cmId, String mcType) {
		StringBuffer hql = new StringBuffer();
		if(StringUtils.isNotBlank(cmId)){
			hql.append("from Budget b where b.cmId='").append(cmId).append("'");
		}
		if(StringUtils.isNotBlank(mcType)){
			hql.append("and b.mcType='").append(mcType).append("'");
		}
		List<Budget>list = super.findByHql(hql.toString());
		if(null!=list && list.size()>0){
		return list.get(0);
		}
		return null;
	}

	@Override
	public Map<String, Object> queryBudgetAdjust(BudgetChangeReq budgetChangeReq) throws ParseException {
		Criteria c = super.getCriteria();
		 //工程id
		 if(StringUtils.isNotBlank(budgetChangeReq.getProjId())){
			 c.add(Restrictions.eq("projId",budgetChangeReq.getProjId()));
		 }
		 //预算类型id
		 if(StringUtils.isNotBlank(budgetChangeReq.getBudgetTypeId())){
			 c.add(Restrictions.eq("budgetTypeId",budgetChangeReq.getBudgetTypeId()));
		 }
		 // 数据库根据条件过滤记录数
		 int filterCount = this.countByCriteria(c);
		 // 根据条件获取查询信息
		 List<Budget> budgetList = this.findBySortCriteria(c, budgetChangeReq);
		
		 // 返回结果
		 return ResultUtil.pageResult( filterCount, budgetChangeReq.getDraw(),budgetList);
	}
	
	/**
	 * 预算派工查询预算员
	 * @author liaoyq
	 * @createTime 2017-8-11
	 * @param designerQueryReq
	 * @return
	 * @throws ParseException
	 */
	@Override
	public Map<String, Object> queryBudgeter(DesignerQueryReq designerQueryReq) throws ParseException {
		//查询参数集合
		List<Object> params = new ArrayList<Object>();
		
		StringBuilder sql = new StringBuilder();
		sql.append("select s.STAFF_NAME budgeter,s.STAFF_ID budgeterId");
		//待预算记录
		if(StringUtils.isNotBlank(designerQueryReq.getProjStatus())){

			//sql.append(",count(case when PROJ_STATUS_ID=? and m.BUDGETER_ID is null then PROJ_STATUS_ID end) budgeterTask");//原sql
			sql.append(",count(case when PROJ_STATUS_ID=?  then PROJ_STATUS_ID end) budgeterTask");
			params.add(designerQueryReq.getProjStatus());
		}
		sql.append(" from project p right join staff s on (p.BUDGETER_ID = s.STAFF_ID) where 1=1");
		LoginInfo loginInfo=SessionUtil.getLoginInfo();


		//查询是否配置sql 如安顺公司可派集团的人
		List<DataFilerSetUpDto> list = Constants.getDataFilterMapByKey(loginInfo.getCorpId()+"_"+Constants.DISPATCH_BUDGETER+"_"+designerQueryReq.getMenuId());
		if(list!=null && list.size()>0){
			sql.append(list.get(0).getSupSql());
		}else{
			//其他人员可查询自己部门的预算员
			sql.append(" and s.CORP_ID like ?");
			params.add(loginInfo.getCorpId()+"%");
		}

		//职位类型
		if(StringUtils.isNotBlank(designerQueryReq.getPostType())){
			sql.append(" and s.POST like ?");
			params.add("%"+designerQueryReq.getPostType()+"%");
		}
		//部门
		if(StringUtils.isNotBlank(designerQueryReq.getDeptId())){
			sql.append(" and s.DEPT_ID like ?");
			params.add(designerQueryReq.getDeptId()+"%");
		}
		sql.append(" and s.MARK_OF_DELETE = ?");  //查询在职员工
		params.add(DeleteOfMarkEnum.ON_THE_JOB.getValue());
		
		sql.append(" group by s.STAFF_NAME,s.STAFF_ID");
		
		List<Map<String, Object>> data = this.findListBySql(sql.toString(), params.toArray());
		return ResultUtil.pageResult(data.size(), designerQueryReq.getDraw(), data);
	}
	
	
	/**
	 *预算结果登记 --查询审核记录
	 * @param projId
	 * @param StepId
	 * @param mrResult
	 * @return String
	 */
	@Override
	public String queryManageRecord(String projId, String StepId, String mrResult,String mrLevel) {
		StringBuffer hql = new StringBuffer();
		LoginInfo login=SessionUtil.getLoginInfo();
		if(StringUtils.isNotBlank(projId)){
			hql.append("from ManageRecord mr where mr.projId='").append(projId).append("'");
		}
		if(StringUtils.isNotBlank(StepId)){
			hql.append("and mr.stepId='").append(StepId).append("'");
		}
		if(StringUtils.isNotBlank(mrResult)){
			hql.append("and mr.mrResult='").append(mrResult).append("'");
		}
		if(StringUtils.isNotBlank(mrLevel)){
			hql.append("and mr.mrAuditLevel='").append(mrLevel).append("'");
		}
		if(StringUtils.isNotBlank(login.getStaffId())){
			hql.append("and mr.mrCsr='").append(login.getStaffId()).append("'");
		}
		
		List<Budget>list = super.findByHql(hql.toString());
		if(null!=list&&list.size()>0){
			return Constants.OPERATE_RESULT_SUCCESS;
		}else{
			return Constants.OPERATE_RESULT_FAILURE;
		}
		
		
	}
	
	/**
	 * 根据工程id和预算类型id、变更过id查询预算总表
	 * @author fuliwei
	 * @createTime 2016-7-14
	 * @param
	 * @return Budget
	 */
	@Override
	public Budget queryById(String projId, String budgetTypeId, String cmId) {
		StringBuffer hql = new StringBuffer();
		if(StringUtils.isNotBlank(projId)){
			hql.append("from Budget b where b.projId='").append(projId).append("'");
		}
		if(StringUtils.isNotBlank(budgetTypeId)){
			hql.append("and b.budgetTypeId='").append(budgetTypeId).append("'");
		}
		if(StringUtils.isNotBlank(cmId)){
			hql.append("and b.cmId='").append(cmId).append("'");
		}
		List<Budget>list = super.findByHql(hql.toString());
		if(null!=list&&list.size()>0){
		return list.get(0);
		}
		return new Budget();
	}
	
	/**
	 * 查询变更预算列表
	 * @author fuliwei
	 * @createTime 2017-1-23
	 * @param req
	 * @return Map<String,Object>
	 */
	@Override
	public Map<String, Object> queryAdjustBudget(BudgetReq req)  throws ParseException {
		Criteria c = super.getCriteria();
		 //工程id 
		if(StringUtils.isNotBlank(req.getProjId())){
			 c.add(Restrictions.eq("projId",req.getProjId()));
		}
		 //工程id 
		if(StringUtils.isNotBlank(req.getProjNo())){
			 c.add(Restrictions.eq("projNo",req.getProjNo()));
		}
		 //工程名称 
		if(StringUtils.isNotBlank(req.getProjName())){
			StringBuffer sql = new StringBuffer();
			 sql.append("proj_id in(select cp.proj_id from PROJECT cp where cp.PROJ_NAME like '%").append(req.getProjName()).append("%')");
			 c.add(Restrictions.sqlRestriction(sql.toString()));
		}
		 //工程地点 
		if(StringUtils.isNotBlank(req.getProjAddr())){
			StringBuffer sql = new StringBuffer();
			 sql.append("proj_id in(select cp.proj_id from PROJECT cp where cp.PROJ_ADDR like '%").append(req.getProjAddr()).append("%')");
			 c.add(Restrictions.sqlRestriction(sql.toString()));
		}
		 //客户名称 
		if(StringUtils.isNotBlank(req.getCustName())){
			StringBuffer sql = new StringBuffer();
			 sql.append("proj_id in(select cp.proj_id from PROJECT cp where cp.CUST_NAME like '%").append(req.getCustName()).append("%')");
			 c.add(Restrictions.sqlRestriction(sql.toString()));
		}
		//变更类型
		if(StringUtils.isNotBlank(req.getBudgetTypeId())){
			 c.add(Restrictions.eq("budgetTypeId",req.getBudgetTypeId()));
		}
		//是否签订补充协议
		if(StringUtils.isNotBlank(req.getIsSignSuContrct())){
			 c.add(Restrictions.eq("isSignSuContrct",req.getIsSignSuContrct()));
		}
		 //查询待签补充协议的
		if(StringUtils.isNotBlank(req.getDesignChangeType())){
			 StringBuffer sql = new StringBuffer();
			 sql.append("cm_id in(select cp.cm_id from CHANGE_MANAGEMENT cp where cp.DESIGN_CHANGE_TYPE = '").append(req.getDesignChangeType()).append("')");
			 c.add(Restrictions.sqlRestriction(sql.toString()));
		}
		//用于字符串日期格式转换
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		 //受理日期开始日期
		 if(StringUtils.isNotBlank(req.getSignDateStart())){
			 StringBuffer sql = new StringBuffer();
			 //updateto_date
			 //sql.append("proj_id in(select cp.proj_id from SUPPLEMENTAL_CONTRACT cp where cp.SIGN_DATE >=  ").append(mysqlSqlConveter.dataOperate(req.getSignDateStart()).append(" 00:00:00','yyyy-MM-dd hh24:mi;ss')").append(")");
			 sql.append("proj_id in(select cp.proj_id from SUPPLEMENTAL_CONTRACT cp where cp.SIGN_DATE >= ").append(mysqlSqlConveter.dataOperate(req.getSignDateStart()+" 00:00:00")).append(")");
			 c.add(Restrictions.sqlRestriction(sql.toString()));
		 }
		 //受理日期结束日期
		 if(StringUtils.isNotBlank(req.getSignDateEnd())){
			 StringBuffer sql = new StringBuffer();
			 sql.append("proj_id in(select cp.proj_id from SUPPLEMENTAL_CONTRACT cp where cp.SIGN_DATE <=  ").append(mysqlSqlConveter.dataOperate(req.getSignDateEnd()+" 00:00:00")).append(")");
			 c.add(Restrictions.sqlRestriction(sql.toString()));
		 }
		 
		 LoginInfo loginInfo=SessionUtil.getLoginInfo();
		 
		 BusinessPartners bp =  businessPartnersDao.get(loginInfo.getDeptId());
		 if(bp==null){
			StringBuffer filter = new StringBuffer("proj_id in(select cp.proj_id from project cp where cp.corp_id like'").append(loginInfo.getCorpId()).append("%')");
			 c.add(Restrictions.sqlRestriction(filter.toString())); 
			 if(BusinessTypeEnum.BUSINESS_GROUP.getValue().equals(loginInfo.getBusinessType())){
					//业务部门 
					 String deptId = loginInfo.getDeptId().substring(0, loginInfo.getDeptId().length() - DeptTypeEnum.SUBCOMPANY.getInitVal().length());
					 StringBuffer sql = new StringBuffer("proj_id in(select cp.proj_id from project cp where cp.dept_id like'%").append(deptId).append("%')");
					 c.add(Restrictions.sqlRestriction(sql.toString())); 
					 
			 }else if(DeptDivideEnum.CUSTOMER_SERVICE_CENTER.getValue().equals(loginInfo.getDeptDivide())){
				 if(StringUtil.isNotBlank(loginInfo.getPost()) && (loginInfo.getPost().contains(PostTypeEnum.RECORDER.getValue())|| loginInfo.getPost().contains(PostTypeEnum.CHIEF_ENGINEER.getValue()))){
					 String deptId =loginInfo.getDeptId();
					 StringBuffer sql = new StringBuffer("proj_id in(select cp.proj_id from project cp where cp.dept_id like'%").append(deptId).append("%')");
					 c.add(Restrictions.sqlRestriction(sql.toString())); 
				 }else{
					//客服中心	
					 String deptId = loginInfo.getDeptId().substring(0, loginInfo.getDeptId().length() - DeptTypeEnum.SUBCOMPANY.getInitVal().length());
					 StringBuffer sql = new StringBuffer("proj_id in(select cp.proj_id from project cp where cp.dept_id like'%").append(deptId).append("%')");
					 c.add(Restrictions.sqlRestriction(sql.toString())); 
				 }
			 }else{	 
				 //工程部门
				 StringBuffer sql = new StringBuffer("proj_id in(select cp.proj_id from project cp where cp.dept_id like'%").append(loginInfo.getDeptId()).append("%')");
				 c.add(Restrictions.sqlRestriction(sql.toString()));
			 }
		 }
		 String post=loginInfo.getPost();
		 if(post.contains(PostTypeEnum.SURVEYER.getValue())){
			 StringBuffer sql = new StringBuffer("proj_id in(select cp.proj_id from project cp where cp.surveyer_id ='").append(loginInfo.getStaffId()).append("')");
			 c.add(Restrictions.sqlRestriction(sql.toString()));
		 }
		 
		 
		// 数据库根据条件过滤记录数
		int filterCount = this.countByCriteria(c);

		// 根据条件获取查询信息
		List<Budget> budgetList = this.findBySortCriteria(c, req);
	
		// 返回结果
		return ResultUtil.pageResult(filterCount, req.getDraw(),budgetList);
	}


	@Override
	public boolean getbudgetType(String projId) {
		String sql = "select BUDGET_TYPE from design_info where PROJ_ID=?";
		Map<String,Object> map = this.findObjectBySql(sql,new String[]{projId});
		if(map !=null){
			String type = String.valueOf(map.get("BUDGET_TYPE"));
			//第三方预算
			if("thirdParty".equals(type)){
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}
	}

	/**
	 * 查询预算打印列表
	 * @author wangang
	 * @createTime 2018-12-18
	 * @return Map<String,Object>
	 */
/*	@Override
	public Map<String, Object> queryBudget(BudgetReq budgetReq) throws ParseException {

		Criteria c = super.getCriteria();
		//工程id
		if(StringUtils.isNotBlank(budgetReq.getProjId())){
			c.add(Restrictions.eq("projId",budgetReq.getProjId()));
		}
		//工程编号
*//*		if(StringUtils.isNotBlank(budgetReq.getProjNo())){
			c.add(Restrictions.eq("projNo",budgetReq.getProjNo()));
		}
		//工程名称
		if(StringUtils.isNotBlank(budgetReq.getProjName())){
			c.add(Restrictions.like("projName", '%'+budgetReq.getProjName()+'%'));
		}*//*
		// 数据库根据条件过滤记录数
		int filterCount = this.countByCriteria(c);
		List<Budget> budgetList=null;
		try {
			// 根据条件获取查询信息
			budgetList = this.findBySortCriteria(c, budgetReq);
		}catch (Exception e){
			e.printStackTrace();
		}
		// 返回结果
		return ResultUtil.pageResult( filterCount, budgetReq.getDraw(),budgetList);
	}*/

	/**
	 * 查询预算
	 */
	@Override
	public Map<String, Object> queryBudget(BudgetReq budgetReq) {
		//查询参数集合
		List<Object> params = new ArrayList<Object>();

		StringBuilder sql = new StringBuilder();
		sql.append("select b.budget_id budgetId, b.proj_id projId, b.proj_no projNo, p.proj_name projName, b.budget_total_cost budgetTotalCost");
		sql.append(" from budget b left join project p on (b.proj_id = p.proj_id ) where b.is_print = 0 ");

		//工程编号
		if(StringUtils.isNotBlank(budgetReq.getProjNo())){
			sql.append(" and p.proj_no like ?");
			params.add(budgetReq.getProjNo()+"%");
		}
		//工程名称
		if(StringUtils.isNotBlank(budgetReq.getProjName())){
			sql.append(" and p.proj_name like ?");
			params.add(budgetReq.getProjName()+"%");
		}

		List<Map<String, Object>> data = this.findListBySql(sql.toString(), params.toArray());
		return ResultUtil.pageResult(data.size(), budgetReq.getDraw(), data);
	}
}
