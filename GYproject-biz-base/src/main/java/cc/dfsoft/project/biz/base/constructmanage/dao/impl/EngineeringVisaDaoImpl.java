package cc.dfsoft.project.biz.base.constructmanage.dao.impl;

import cc.dfsoft.project.biz.base.baseinfo.dao.BusinessPartnersDao;
import cc.dfsoft.project.biz.base.baseinfo.entity.BusinessPartners;
import cc.dfsoft.project.biz.base.change.enums.ChangeStateEnum;
import cc.dfsoft.project.biz.base.constructmanage.dao.EngineeringVisaDao;
import cc.dfsoft.project.biz.base.constructmanage.dto.EngineeringVisaQueryReq;
import cc.dfsoft.project.biz.base.constructmanage.entity.EngineeringVisa;
import cc.dfsoft.project.biz.base.constructmanage.enums.CuReStateEnum;
import cc.dfsoft.project.biz.base.constructmanage.enums.StageProjectApplicationEnum;
import cc.dfsoft.project.biz.base.design.enums.UnitTypeEnum;
import cc.dfsoft.project.biz.base.project.dao.ProjectDao;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dao.DepartmentDao;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.biz.sys.dept.entity.Department;
import cc.dfsoft.uexpress.biz.sys.dept.enums.BusinessTypeEnum;
import cc.dfsoft.uexpress.biz.sys.dept.enums.DeptDivideEnum;
import cc.dfsoft.uexpress.biz.sys.dept.enums.DeptTypeEnum;
import cc.dfsoft.uexpress.biz.sys.dept.enums.PostTypeEnum;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.constant.DataFilerSetUpDto;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;
import cc.dfsoft.uexpress.common.util.ResultUtil;
import cc.dfsoft.uexpress.common.util.StringUtil;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class EngineeringVisaDaoImpl extends NewBaseDAO<EngineeringVisa,String> implements EngineeringVisaDao{
	
	/**部门*/
	@Resource
	DepartmentDao departmentDao;
	@Resource
	ProjectDao projectDao;
	
	/**业务合作伙伴*/
	@Resource
	BusinessPartnersDao businessPartnersDao;
	
	@Override
	public Map<String, Object> queryEngineeringVisa(EngineeringVisaQueryReq engineeringVisaQueryReq) 
		throws ParseException {
			Criteria c = super.getCriteria();
			LoginInfo loginInfo =SessionUtil.getLoginInfo();
			String post=loginInfo.getPost();
			String [] postArray=post.split(",");
			//签证id
			if(StringUtils.isNotBlank(engineeringVisaQueryReq.getEvId())){
				c.add(Restrictions.eq("evId",engineeringVisaQueryReq.getEvId()));
			}
			//工程id
			if(StringUtils.isNotBlank(engineeringVisaQueryReq.getProjId())){
				c.add(Restrictions.eq("projId",engineeringVisaQueryReq.getProjId()));
			}
			//工程编号
			if(StringUtils.isNotBlank(engineeringVisaQueryReq.getProjNo())){
				c.add(Restrictions.like("projNo","%"+engineeringVisaQueryReq.getProjNo()+"%"));
			}
			//签证编号
			if(StringUtils.isNotBlank(engineeringVisaQueryReq.getEvNo())){
				c.add(Restrictions.like("evNo","%"+engineeringVisaQueryReq.getEvNo()+"%"));
			}
			//工程名称
			if(StringUtils.isNotBlank(engineeringVisaQueryReq.getProjName())){
				c.add(Restrictions.like("projName","%"+engineeringVisaQueryReq.getProjName()+"%"));
			}
			//审核状态
			if(engineeringVisaQueryReq.getAuditState()!=null&&engineeringVisaQueryReq.getAuditState().size()>0){
				c.add(Restrictions.in("auditState", engineeringVisaQueryReq.getAuditState()));
			}
			//用于字符串日期格式转换
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			//签证日期
			if(StringUtils.isNotBlank(engineeringVisaQueryReq.getVisaDate())){
				c.add(Restrictions.like("visaDate", sdf.parse(engineeringVisaQueryReq.getVisaDate()+"%")));
			}
			
			//签证状态
			if(engineeringVisaQueryReq.getEvState()!=null&&engineeringVisaQueryReq.getEvState().size()>0){
				c.add(Restrictions.in("evState", engineeringVisaQueryReq.getEvState()));
			}
			
			//签证事由原因
			if(StringUtils.isNotBlank(engineeringVisaQueryReq.getEvContent())){
				c.add(Restrictions.like("evContent","%"+engineeringVisaQueryReq.getEvContent()+"%"));
			}
			//变更日期开始日期
			if(StringUtils.isNotBlank(engineeringVisaQueryReq.getVisaDateStart())){
				c.add(Restrictions.ge("visaDate", sdf.parse(engineeringVisaQueryReq.getVisaDateStart())));
			}
			//变更日期结束日期
			if(StringUtils.isNotBlank(engineeringVisaQueryReq.getVisaDateEnd())){
				c.add(Restrictions.le("visaDate", sdf.parse(engineeringVisaQueryReq.getVisaDateEnd())));
			}
			//分包方单位人员登录时，只可看自己相关项目
			 BusinessPartners bp =  businessPartnersDao.get(loginInfo.getDeptId());
			 if(bp != null){
				if(UnitTypeEnum.CONSTRUCTION_UNIT.getValue().equals(bp.getUnitType())){
					//根据自己所属分公司过滤数据
					StringBuffer sql = projectDao.cuUnitFilter(loginInfo,post, engineeringVisaQueryReq.getMenuId());
					c.add(Restrictions.sqlRestriction(sql.toString()));
				}
			 }
			 
			 
			//查询是否有配置
			 Boolean flag=true;
			 List<DataFilerSetUpDto> builderList = Constants.getDataFilterMapByKey(loginInfo.getDeptId()+"_"+engineeringVisaQueryReq.getMenuId());
			 if(builderList!=null && builderList.size()>0){
				 flag=false;
			 }
			 
			 
			 List<DataFilerSetUpDto> corpBudget = Constants.getDataFilterMapByKey(loginInfo.getCorpId()+"_"+engineeringVisaQueryReq.getMenuId());
			 if(corpBudget!=null && corpBudget.size()>0){
				 StringBuffer hql = new StringBuffer();
				 hql.append("proj_id in (select proj_id from project where 1=1 ");
				 if(StringUtils.isNotBlank(corpBudget.get(0).getSupSql())){
					 hql.append(corpBudget.get(0).getSupSql());
				 }
				 hql.append(")");
				 c.add(Restrictions.sqlRestriction(hql.toString()));
				 flag = false;
			 }
			 
			 
			 if(bp==null && flag){
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
			
			
			
			
			 Department dept=new Department();
			 List<Object> deptIdArray=new ArrayList<>();
			 if(postArray.length>0){
					 if(post.contains(PostTypeEnum.CIVIL_GROUP_LEADER.getValue())||post.contains(PostTypeEnum.MARKETING_CENTER_LEADER.getValue())||
							 post.contains(PostTypeEnum.MODIFICATION_GROUP_LEADER.getValue())||post.contains(PostTypeEnum.TRUNK_GROUP_LEADER.getValue())){
						 StringBuffer sql = new StringBuffer("proj_id in(select cp.proj_id from project cp where cp.dept_id like'%").append(loginInfo.getDeptId()).append("%')");
						 c.add(Restrictions.sqlRestriction(sql.toString())); 
					 }

					 if(post.contains(PostTypeEnum.CIVIL_HEAD.getValue())){
						 //民用负责人
						 dept=departmentDao.queryDepartmentByDivide(DeptDivideEnum.CIVIL_GROUP.getValue(),loginInfo.getCorpId());
						 if(StringUtils.isNotBlank(dept.getDeptId())){
							 deptIdArray.add(dept.getDeptId());
						 }
					 }
					 if(post.contains(PostTypeEnum.MARKETING_CENTER_HEAD.getValue())){
						 //公建负责人
						 dept=departmentDao.queryDepartmentByDivide(DeptDivideEnum.MARKETING_CENTER.getValue(),loginInfo.getCorpId());
						 if(StringUtils.isNotBlank(dept.getDeptId())){
							 deptIdArray.add(dept.getDeptId());
						 }
					 }
					 if(post.contains(PostTypeEnum.MODIFICATION_HEAD.getValue())){
						 //改管负责人
						 dept=departmentDao.queryDepartmentByDivide(DeptDivideEnum.MODIFICATION_GROUP.getValue(),loginInfo.getCorpId());
						 if(StringUtils.isNotBlank(dept.getDeptId())){
							 deptIdArray.add(dept.getDeptId());
						 }
					 }
					 if(post.contains(PostTypeEnum.TRUNK_HEAD.getValue())){
						 //干线负责人
						 dept=departmentDao.queryDepartmentByDivide(DeptDivideEnum.TRUNK_GROUP.getValue(),loginInfo.getCorpId());
						 if(StringUtils.isNotBlank(dept.getDeptId())){
							 deptIdArray.add(dept.getDeptId());
						 }
					 }

				boolean isProjId=true;
				 DataFilerSetUpDto staffDeptIdMenuId = Constants.isConfig(loginInfo.getStaffId() + "_" + loginInfo.getDeptId() + "_" + engineeringVisaQueryReq.getMenuId());
				 if (staffDeptIdMenuId!=null){
					 isProjId=false;
					 if (StringUtils.isNotBlank(staffDeptIdMenuId.getSupSql())){
						 c.add(Restrictions.sqlRestriction(staffDeptIdMenuId.getSupSql()));
					 }
				 }


				 if(deptIdArray!=null && deptIdArray.size()>0 && isProjId){
					 StringBuffer hql = new StringBuffer("from Project where deptId in ('");
					 for(int i=1;i<deptIdArray.size();i++){
						 hql.append(deptIdArray.get(i)).append("',").append("'");
					 }
					 hql.append(deptIdArray.get(0)).append("')");
					 List<Project> projects = super.findByHql(hql.toString());
					 List<String> projIds = new ArrayList();
					 if(projects!=null&&projects.size()>0){
						 for(Project project:projects){
							 projIds.add(project.getProjId());
						 }
						 c.add(Restrictions.in("projId",projIds));
					 }else{
						 return ResultUtil.pageResult( 0, engineeringVisaQueryReq.getDraw(),new ArrayList()); 
					 }
				 }
			 }
			
			//设计员找自己的变更
			if(post.contains(PostTypeEnum.DESIGNER.getValue())){
				StringBuffer sql=new StringBuffer("proj_id in(select p.proj_id from project p where p.designer_id='").append(loginInfo.getStaffId()).append("')");
				c.add(Restrictions.sqlRestriction(sql.toString()));
			}

			DataFilerSetUpDto dataFilter=  Constants.isConfig(loginInfo.getStaffId() + "_" + engineeringVisaQueryReq.getMenuId());
			//不是分包单位的预算员找自己的工程
			if(bp==null && post.contains(PostTypeEnum.BUDGET_MEMBER.getValue()) 
					&& !post.contains(PostTypeEnum.BUDGET_GROUP_LEADER.getValue())
					&& !DeptDivideEnum.CUSTOMER_SERVICE_CENTER.getValue().equals(loginInfo.getDeptDivide())&& dataFilter==null){

				StringBuffer sql=new StringBuffer("proj_id in(select p.proj_id from project p where p.BUDGETER_AUDIT_ID='").append(loginInfo.getStaffId()).append("')");
				c.add(Restrictions.sqlRestriction(sql.toString()));

			}
			if(post.contains(PostTypeEnum.BUILDER.getValue())){//默认现场代表
				if(dataFilter!=null && StringUtil.isNotBlank(dataFilter.getSupSql())){
					c.add(Restrictions.sqlRestriction(dataFilter.getSupSql()));
				}else{
					StringBuffer sql=new StringBuffer("proj_id in(select cp.proj_id from CONSTRUCTION_PLAN cp where cp.BUILDER_ID='").append(loginInfo.getStaffId()).append("')");
					c.add(Restrictions.sqlRestriction(sql.toString()));
				}
			}
			 c.addOrder(Order.desc("isPass"));
			// 数据库根据条件过滤记录数
			int filterCount = this.countByCriteria(c);

			// 根据条件获取查询信息
			List<EngineeringVisa> engineeringVisaList = this.findBySortCriteria(c, engineeringVisaQueryReq);
			
			// 返回结果
			return ResultUtil.pageResult( filterCount, engineeringVisaQueryReq.getDraw(),engineeringVisaList);
		}

	@Override
	public void updateVisaState(String id) {
		String sql="update engineering_visa m set m.ev_state=1 where m.ev_id="+id;
		super.executeSql(sql);
	}

	@Override
	public void updateEngineeringVisaState() {
		StringBuffer sql = new StringBuffer();
		sql.append("update engineering_visa eva set eva.ev_state='").append(ChangeStateEnum.INVALID.getValue()).append("' where eva.ev_id in (");
		sql.append("select old.ev_id from (select sysdate-ev.visa_date as days,ev.* from engineering_visa  ev where ev.CUST_PAL is null and ev.ev_state='");
		sql.append(ChangeStateEnum.NO_HANDLE.getValue()).append("') old where old.days>=3)");
		super.executeSql(sql.toString());
	}

	@Override
	public BigDecimal getTotalCostByType(String projId,String evType) {
		StringBuffer sql = new StringBuffer();
		//查询参数集合
		List<Object> params = new ArrayList<Object>();
		sql.append("select SUM(EV.QUANTITIES_TOTAL) totalCost FROM engineering_visa EV WHERE EV.PROJ_ID= ? AND EV.EV_TYPE = ? AND AUDIT_STATE = ?");
		params.add(projId);
		params.add(evType);
		params.add(StageProjectApplicationEnum.PASSED.getValue());
		Map<String, Object> result = this.findObjectBySql(sql.toString(), params.toArray());
		return (BigDecimal) result.get("totalCost");
	}

	@Override
	public String getMaxEvNo(String date,String projId) {
		Criteria c = super.getCriteria();
		c.setProjection(Projections.max("evNo"));
		c.add(Restrictions.like("evNo",date+"%"));
		//工程编号
		if(StringUtils.isNotBlank(projId)){
			c.add(Restrictions.eq("projId", projId));
		}
		String evNoMax = (String) c.uniqueResult();
		if(StringUtils.isNotBlank(evNoMax)){
			int evNo = Integer.parseInt(evNoMax)+1;
			return ""+evNo;
		}
		return "";
	}

	/**
	 * 根据工程ID查询所有签证记录
	 */
	@Override
	public List<EngineeringVisa> findByProjId(String projId,String auditState) {
		Criteria c = super.getCriteria();
		//工程编号
		if(StringUtils.isNotBlank(projId)){
			c.add(Restrictions.eq("projId", projId));
		}
		//签证审核状态
		if(StringUtils.isNotBlank(auditState)){
			c.add(Restrictions.eq("auditState", auditState));
		}
		return this.findByCriteria(c);
	}
	
	/**
	 * 查询签证通知
	 * @author fuliwei
	 * @createTime 2018年1月31日
	 * @param 
	 * @return
	 */
	@Override
	public List<EngineeringVisa> queryVisaNotice(String status) {
		Criteria c = super.getCriteria();
		if(StringUtils.isNotBlank(status)){
			c.add(Restrictions.eq("auditState", status));
		}
		LoginInfo loginInfo =SessionUtil.getLoginInfo();
		 String post=loginInfo.getPost();
		 String [] postArray=post.split(",");
		if(post.contains(PostTypeEnum.BUDGET_MEMBER.getValue()) 
				&& !post.contains(PostTypeEnum.BUDGET_GROUP_LEADER.getValue())
				&& !DeptDivideEnum.CUSTOMER_SERVICE_CENTER.getValue().equals(loginInfo.getDeptDivide())){
			StringBuffer sql=new StringBuffer("proj_id in(select p.proj_id from project p where p.BUDGETER_AUDIT_ID='").append(loginInfo.getStaffId()).append("')");
			c.add(Restrictions.sqlRestriction(sql.toString()));
		}else{
			//不让查询
			c.add(Restrictions.eq("auditState", StageProjectApplicationEnum.PASSED.getValue()));   
		}
		return this.findByCriteria(c);
	}

	/**
	 * 审核已通过，但还未进行确认的签证列表通知
	 */
	@Override
	public List<EngineeringVisa> queryEVNotice() {
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		Criteria c = super.getCriteria();
		//施工单位
		if(loginInfo!=null){
			StringBuffer sql=new StringBuffer("proj_id in(select cp.proj_id from construction_plan cp where cp.cu_id='").append(loginInfo.getDeptId()).append("')");
			c.add(Restrictions.sqlRestriction(sql.toString()));
		}
		//已审核通过或作废的签证
		StringBuffer sql=new StringBuffer("( AUDIT_STATE='").append(StageProjectApplicationEnum.PASSED.getValue()).append("' or AUDIT_STATE='").append(StageProjectApplicationEnum.TO_CANCEL.getValue()).append("')");
		c.add(Restrictions.sqlRestriction(sql.toString()));
		//未确认
		c.add(Restrictions.eq("cuReState", CuReStateEnum.NOT_RE.getValue()));
		return this.findByCriteria(c);
	}

	/**
	 * 
	 * 注释：未作废未完成的签证
	 * @author liaoyq
	 * @createTime 2018年12月7日
	 * @param id
	 * @return
	 *
	 */
	@Override
	public List<EngineeringVisa> noCancelEngineeringVisa(String id) {
		Criteria c = super.getCriteria();
		//工程id
		if(StringUtils.isNotBlank(id)){
			c.add(Restrictions.eq("projId",id));
		}
		c.add(Restrictions.ne("auditState",StageProjectApplicationEnum.TO_CANCEL.getValue()));
		c.add(Restrictions.ne("auditState",StageProjectApplicationEnum.PASSED.getValue()));
		c.add(Restrictions.ne("isPass","0"));
		return this.findByCriteria(c);
	}

	@Override
	public List<Object[]> queryOverdueEngineeringVisa(String corpId,
			String limitTime) {
		String sql = "select * from (select ev.EV_ID as evId, ev.proj_id as projId ,ev.PROJ_NO as projNo,DATEDIFF(DATE_FORMAT(now(),'%Y-%m-%d'),DATE_FORMAT(ev.CRONTAB_DATE,'%Y-%m-%d')) dayDiff from ENGINEERING_VISA ev where ev.corp_id=? and ev.CRONTAB_DATE is not null) res where res.dayDiff>?";
		List<Object> params = new ArrayList<Object>();
		params.add(corpId);
		params.add(limitTime);
		return this.findBySql(sql, params.toArray());
	}

	@Override
	public void clearCrontabDateById(String evId) {
		String sql = " update ENGINEERING_VISA set CRONTAB_DATE=NULL where EV_ID=?";
		List<Object> params = new ArrayList<Object>();
		params.add(evId);
		this.executeSql(sql, params.toArray());
	}

	@Override
	public List<EngineeringVisa> queryCrontabDateNotnull() {
		Criteria c = super.getCriteria();
		c.add(Restrictions.isNotNull("crontabDate"));//返回日期不空
		return this.findByCriteria(c);
	}
}
