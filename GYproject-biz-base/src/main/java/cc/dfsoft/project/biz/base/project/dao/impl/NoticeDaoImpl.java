package cc.dfsoft.project.biz.base.project.dao.impl;

import java.util.ArrayList;
import java.util.Arrays;
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
import cc.dfsoft.project.biz.base.design.enums.UnitTypeEnum;
import cc.dfsoft.project.biz.base.project.dao.NoticeDao;
import cc.dfsoft.project.biz.base.project.entity.Notice;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.project.enums.ActionEnum;
import cc.dfsoft.project.biz.base.project.enums.NoticeMenuEnum;
import cc.dfsoft.project.biz.base.project.enums.NoticeStateEnum;
import cc.dfsoft.project.biz.base.project.enums.SignStatusEnum;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dao.DepartmentDao;
import cc.dfsoft.uexpress.biz.sys.dept.dao.StaffRoleDao;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.biz.sys.dept.entity.Department;
import cc.dfsoft.uexpress.biz.sys.dept.entity.StaffRole;
import cc.dfsoft.uexpress.biz.sys.dept.enums.BusinessTypeEnum;
import cc.dfsoft.uexpress.biz.sys.dept.enums.DeptDivideEnum;
import cc.dfsoft.uexpress.biz.sys.dept.enums.DeptTypeEnum;
import cc.dfsoft.uexpress.biz.sys.dept.enums.PostTypeEnum;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;
import cc.dfsoft.uexpress.common.dto.PageSortReq;
import cc.dfsoft.uexpress.common.util.ResultUtil;
import cc.dfsoft.uexpress.common.util.StringUtil;


@Repository
public class NoticeDaoImpl extends NewBaseDAO<Notice, String> implements NoticeDao{
	
	/**业务合作伙伴*/
	@Resource
	BusinessPartnersDao businessPartnersDao;
	
	/**部门Dao*/
	@Resource
	DepartmentDao departmentDao;
	
	@Resource
	StaffRoleDao staffRoleDao;
	
	/**
	 * 通知查看
	 * @param notice
	 */
	@Override
	public Map<String, Object> queryNotice(PageSortReq pageSortReq) {
		Criteria criteria = super.getCriteria();
		criteria.add(Restrictions.eq("noticeState", NoticeStateEnum.EFFECTIVE.getValue()));  // 有效通知
		
		
		LoginInfo login=SessionUtil.getLoginInfo();
		//登录人是设计员
		if(PostTypeEnum.DESIGNER.getValue().equals(login.getPost())||PostTypeEnum.DESIGN_DIRECTOR.getValue().equals(login.getPost())){
			StringBuffer sql=new StringBuffer();
			sql.append(" proj_id in(select proj_id from project where designer_id='").append(login.getStaffId()).append("')");
			criteria.add(Restrictions.sqlRestriction(sql.toString()));
			//criteria.add(Restrictions.eq("noticeType",NoticeTypeEnum.ALL.getValue() ));
		}
		//分包方单位人员登录时，只看到自己单位的通知
		BusinessPartners bp =  businessPartnersDao.get(login.getDeptId());
		if(bp!=null){
			//若登录人是分包方单位人员
			if(UnitTypeEnum.CONSTRUCTION_UNIT.getValue().equals(bp.getUnitType())){
				 //通过分包单位人员id查询
				StringBuffer sql = new StringBuffer("proj_id in(select cp.proj_id from construction_plan cp where cp.cu_id='").append(login.getDeptId()).append("')");
				criteria.add(Restrictions.sqlRestriction(sql.toString()));
			}
			//若登录人是监理单位人员
			if(UnitTypeEnum.SUPERVISION_UNIT.getValue().equals(bp.getUnitType())){
				 //通过监理单位人员id查询
				StringBuffer sql = new StringBuffer("proj_id in(select cp.proj_id from construction_plan cp where cp.su_id='").append(login.getDeptId()).append("')");
				criteria.add(Restrictions.sqlRestriction(sql.toString()));
			}
		}else{
			//公司ID
			StringBuffer sql = new StringBuffer("proj_id in(select cp.proj_id from project cp where cp.corp_id like '").append(login.getCorpId()).append("%')");
			criteria.add(Restrictions.sqlRestriction(sql.toString()));
		}
		//施工处
		/*if(PostTypeEnum.BUILDER.getValue().equals(login.getPost())){
			StringBuffer sql=new StringBuffer();
			sql.append(" proj_id in(select cp.proj_id from construction_plan cp where cp.BUILDER_ID='").append(login.getStaffId()).append("')");
			criteria.add(Restrictions.sqlRestriction(sql.toString()));
		}*/
		String deptId = login.getDeptId();
		if ("110201".equals(deptId) || "110202".equals(deptId) ||"110203".equals(deptId) 
				 ||"110204".equals(deptId) ||"110205".equals(deptId) ) {
			//施工人员找自己的通知
			StringBuffer sql=new StringBuffer();
			sql.append(" proj_id in(select cp.proj_id from construction_plan cp where cp.BUILDER_ID='").append(login.getStaffId()).append("')");
			criteria.add(Restrictions.sqlRestriction(sql.toString()));
		 }
		//造价合同处
		if("110206".equals(deptId)){
			//预算员只看自己的
			if(PostTypeEnum.BUDGET_MEMBER.getValue().equals(login.getPost())){
				StringBuffer sql=new StringBuffer();
				sql.append(" proj_id in(select proj_id from project where budgeter_id='").append(login.getStaffId()).append("')");
				criteria.add(Restrictions.sqlRestriction(sql.toString()));
			}
			//只找签证的通知
			criteria.add(Restrictions.eq("actionType",ActionEnum.ENGINEERING.getValue() ));
		}
		//当前用户角色-回退审核通知
		 StaffRole staffRole = staffRoleDao.queryStaffRoleInfo("", login.getStaffId());  // 提取员工角色
		 if(staffRole!=null){
			 String  roleIds = staffRole.getRoleIds();
			 //有预结算回退审核权限
			 if(StringUtils.isNotBlank(roleIds) && roleIds.contains("101091")){
				 StringBuffer sql = new StringBuffer("proj_id in(select fa.PROJ_ID from fallback_apply fa LEFT JOIN menu_back_set mbs on fa.MBS_ID = mbs.MBS_ID where mbs.FLAG ='1')");
				 criteria.add(Restrictions.sqlRestriction(sql.toString())); 
			 }else if(StringUtils.isNotBlank(roleIds) && roleIds.contains("101092")){//组长 回退审核
				 StringBuffer sql = new StringBuffer("proj_id in(select fa.PROJ_ID from fallback_apply fa LEFT JOIN menu_back_set mbs on fa.MBS_ID = mbs.MBS_ID where mbs.FLAG is null OR mbs.FLAG='')");
				 criteria.add(Restrictions.sqlRestriction(sql.toString())); 
			 }
		 }
		criteria.addOrder(Order.desc("noticeTime"));  // 通知时间
		
		Integer count = this.countByCriteria(criteria);
		// 根据条件获取查询信息
		List<Notice> noticeList = this.findBySortCriteria(criteria, pageSortReq);
		return ResultUtil.pageResult(count, pageSortReq.getDraw(), noticeList);
	}
	
	/**
	 * 判断该工程此项活动是否已经下达通知
	 * @param projId
	 * @param actionType
	 * @return
	 */
	public List getNotice(String projId, String actionType){
		if (StringUtils.isNotBlank(projId)  && StringUtils.isNotBlank(actionType) ) {
			Criteria criteria = super.getCriteria();
			criteria.add(Restrictions.eq("projId", projId));
			criteria.add(Restrictions.eq("actionType", actionType));
			List<Notice> list = this.findByCriteria(criteria);
			return list;
		}
		return null;
	}
	
	/**
	 * 判断签证是否已经下达通知
	 * @param projId
	 * @param actionType
	 * @return
	 */
	@Override
	public List getEngineeringNotice(String evId, String actionType) {
		if (StringUtils.isNotBlank(evId)  && StringUtils.isNotBlank(actionType) ) {
			Criteria criteria = super.getCriteria();
			criteria.add(Restrictions.eq("evId", evId));
			criteria.add(Restrictions.eq("actionType", actionType));
			List<Notice> list = this.findByCriteria(criteria);
			return list;
		}
		return null;
	}
	
	/**
	 * 根据签证id删除原来的通知
	 * @author fuliwei
	 * @createTime 2017年2月9日
	 * @param evId
	 * @return
	 */
	@Override
	public void deleteByEvId(String evId) {
		StringBuffer sql=new StringBuffer("delete from Notice where evId='").append(evId).append("'");
		this.executeHql(sql.toString());
		
	}
	
	 /** 删除原来的通知	
	 * @author fuliwei
	 * @createTime 2017年2月14日
	 * @param  projId actionType
	 * @return
	 */
	@Override
	public void deleteByIdAndActionType(String projId, String actionType) {
		StringBuffer sql=new StringBuffer("delete from Notice where proj_id='").append(projId).append("' and action_type='").append(actionType).append("'");
		super.executeHql(sql.toString());
	}
	
	
	/**
	 * 审核通知查看
	 * @param notice
	 */
	@Override
	public int queryAuditNotice(String grade,String type) {
		Criteria criteria = super.getCriteria();
		criteria.add(Restrictions.eq("noticeState", NoticeStateEnum.EFFECTIVE.getValue()));  // 有效通知
		
		//审核级别
		if(StringUtils.isNotBlank(grade)){
			criteria.add(Restrictions.eq("grade", grade));
		}
		//审核类型
		
		if(StringUtils.isNotBlank(type)){
			criteria.add(Restrictions.eq("auditType", type));
		}
		
		
		
		LoginInfo loginInfo=SessionUtil.getLoginInfo();
		String post=loginInfo.getPost();
		String [] postArray=post.split(",");
		 //非设计院且非业务合作伙伴
		 if(!DeptDivideEnum.DESIGN_INSTITUTE.getValue().equals(loginInfo.getDeptDivide())){
			//所属公司id
			 if(StringUtils.isNotBlank(loginInfo.getCorpId())){
				 criteria.add(Restrictions.like("corpId",loginInfo.getCorpId()+"%"));
			 }
			 StringBuffer sql=new StringBuffer("");
			 if(BusinessTypeEnum.BUSINESS_GROUP.getValue().equals(loginInfo.getBusinessType())){
					//业务部门 
					 String deptId = loginInfo.getDeptId().substring(0, loginInfo.getDeptId().length() - DeptTypeEnum.SUBCOMPANY.getInitVal().length());
					 sql= new StringBuffer("(proj_id in(select cp.proj_id from project cp where cp.dept_id like '").append(deptId).append("%')");
					 //criteria.add(Restrictions.sqlRestriction(sql.toString()));
			 }else if(DeptDivideEnum.CUSTOMER_SERVICE_CENTER.getValue().equals(loginInfo.getDeptDivide())){
				 if(StringUtil.isNotBlank(loginInfo.getPost()) && (loginInfo.getPost().contains(PostTypeEnum.RECORDER.getValue())|| loginInfo.getPost().contains(PostTypeEnum.CHIEF_ENGINEER.getValue()))){
					 String deptId =loginInfo.getDeptId();
						 sql= new StringBuffer("(proj_id in(select cp.proj_id from project cp where cp.dept_Id like '%").append(deptId).append("%')");
						 //criteria.add(Restrictions.sqlRestriction(sql.toString()));
					 }else{
						//客服中心
						 String deptId = loginInfo.getDeptId().substring(0, loginInfo.getDeptId().length() - DeptTypeEnum.SUBCOMPANY.getInitVal().length());
						 sql= new StringBuffer("(proj_id in(select cp.proj_id from project cp where cp.dept_Id like '%").append(deptId).append("%')");
						 //criteria.add(Restrictions.sqlRestriction(sql.toString()));
					 }
			  }else{	 
					 //工程部门
					 sql= new StringBuffer("(proj_id in(select cp.proj_id from project cp where cp.dept_Id like '").append(loginInfo.getDeptId()).append("%')");
					 //criteria.add(Restrictions.sqlRestriction(sql.toString()));
			  }
			 
			//现场代表
			if(post.contains(PostTypeEnum.BUILDER.getValue())){
				if(!post.contains(PostTypeEnum.MARKETING_CENTER_LEADER_DEPUTY.getValue())&&
						!post.contains(PostTypeEnum.MODIFICATION_GROUP_LEADER.getValue())&&
						!post.contains(PostTypeEnum.TRUNK_GROUP_LEADER.getValue())&&
						!post.contains(PostTypeEnum.DEPUTY_LEADER.getValue())){
					sql.append("or proj_id in(select cp.proj_id from construction_plan cp where cp.builder_id='").append(loginInfo.getStaffId()).append("'))");
					criteria.add(Restrictions.sqlRestriction(sql.toString()));
				}else{
					sql.append(")");
				}
			}else{
				sql.append(")");
			}
			criteria.add(Restrictions.sqlRestriction(sql.toString()));
		 }else if(DeptDivideEnum.DESIGN_INSTITUTE.getValue().equals(loginInfo.getDeptDivide())){
			 StringBuffer sql= new StringBuffer("proj_id in(select cp.proj_id from project cp where cp.dept_Id like '110101%')");
			 criteria.add(Restrictions.sqlRestriction(sql.toString()));
		 }
		 
		 
	
		 Department dept=new Department();
		 List<Object> deptIdArray=new ArrayList<>();
		 if(postArray.length>0){
			
				 if(post.contains(PostTypeEnum.CIVIL_HEAD.getValue())){
					 //民用负责人
					 dept=departmentDao.queryDepartmentByDivide(DeptDivideEnum.CIVIL_GROUP.getValue(),loginInfo.getDeptId());
					 if(StringUtils.isNotBlank(dept.getDeptId())){
						 deptIdArray.add(dept.getDeptId());
					 }
				 }
				 if(post.contains(PostTypeEnum.MARKETING_CENTER_HEAD.getValue())){
					 //公建负责人
					 dept=departmentDao.queryDepartmentByDivide(DeptDivideEnum.MARKETING_CENTER.getValue(),loginInfo.getDeptId());
					 if(StringUtils.isNotBlank(dept.getDeptId())){
						 deptIdArray.add(dept.getDeptId());
					 }
				 }
				 if(post.contains(PostTypeEnum.MODIFICATION_HEAD.getValue())){
					 //改管负责人
					 dept=departmentDao.queryDepartmentByDivide(DeptDivideEnum.MODIFICATION_GROUP.getValue(),loginInfo.getDeptId());
					 if(StringUtils.isNotBlank(dept.getDeptId())){
						 deptIdArray.add(dept.getDeptId());
					 }
				 }
				 if(post.contains(PostTypeEnum.TRUNK_HEAD.getValue())){
					 //干线负责人
					 dept=departmentDao.queryDepartmentByDivide(DeptDivideEnum.TRUNK_GROUP.getValue(),loginInfo.getDeptId());
					 if(StringUtils.isNotBlank(dept.getDeptId())){
						 deptIdArray.add(dept.getDeptId());
					 }
				 }
				 
			
			 
			 if(deptIdArray!=null && deptIdArray.size()>0){
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
					 criteria.add(Restrictions.in("projId",projIds));
				 }
			 }
			 
		 }	  
		//施工预算
		if(NoticeMenuEnum.CON_BUDGET_AUDIT1.getType().equals(type) && NoticeMenuEnum.CON_BUDGET_AUDIT1.getGrade().equals(grade)){
			//预算员
			if(post.contains(PostTypeEnum.BUDGET_MEMBER.getValue())){
				StringBuffer sql = new StringBuffer("proj_id in(select cp.proj_id from project cp where cp.budgeter_audit_id='").append(loginInfo.getStaffId()).append("')");
				criteria.add(Restrictions.sqlRestriction(sql.toString()));
			}
		} 
		 
		//结算审核
		if(NoticeMenuEnum.SETTLEMENT_AUDIT1.getType().equals(type) && NoticeMenuEnum.SETTLEMENT_AUDIT1.getGrade().equals(grade)){
			//预算员
			if(post.contains(PostTypeEnum.BUDGET_MEMBER.getValue())){
				StringBuffer sql = new StringBuffer("proj_id in(select cp.proj_id from project cp where cp.settlementer_id='").append(loginInfo.getStaffId()).append("')");
				criteria.add(Restrictions.sqlRestriction(sql.toString()));
			}
		}
		
		//付款审核
		if(NoticeMenuEnum.PAY_AUDIT1.getType().equals(type) && NoticeMenuEnum.PAY_AUDIT1.getGrade().equals(grade)){
			if(post.contains(PostTypeEnum.BUDGET_MEMBER.getValue())){
				StringBuffer sql = new StringBuffer("business_order_id in(select cp.pa_id from payment_apply cp where cp.pa_auditer_id='").append(loginInfo.getStaffId()).append("')");
				criteria.add(Restrictions.sqlRestriction(sql.toString()));
			}
		}
		
		//踏勘审核
		if(NoticeMenuEnum.SURVEY_AUDIT1.getType().equals(type) && NoticeMenuEnum.SURVEY_AUDIT1.getGrade().equals(grade)){
			if(post.contains(PostTypeEnum.BUILDER.getValue())){
				StringBuffer sql = new StringBuffer("proj_id in(select cp.proj_id from project cp where cp.surveyer_id='").append(loginInfo.getStaffId()).append("')");
				criteria.add(Restrictions.sqlRestriction(sql.toString())); 
			}
		}
		
		//资料审核
		if(NoticeMenuEnum.DATA_AUDIT1.getType().equals(type) && NoticeMenuEnum.DATA_AUDIT1.getGrade().equals(grade)){
			if(post.contains(PostTypeEnum.BUILDER.getValue())){
				StringBuffer sql =new StringBuffer(" proj_id in(select cp.proj_id from construction_plan cp where cp.builder_id='").append(loginInfo.getStaffId()).append("')");
				criteria.add(Restrictions.sqlRestriction(sql.toString()));
			}
		}
		if(NoticeMenuEnum.BACK_AUDIT1.getType().equals(type) && NoticeMenuEnum.BACK_AUDIT1.getGrade().equals(grade)){
			//当前用户角色-回退审核通知
			 StaffRole staffRole = staffRoleDao.queryStaffRoleInfo("", loginInfo.getStaffId());  // 提取员工角色
			 if(staffRole!=null){
				 String  roleIds = staffRole.getRoleIds();
				 //有预结算回退审核权限
				 if(StringUtils.isNotBlank(roleIds) && roleIds.contains("101091")){
					 StringBuffer sql = new StringBuffer("proj_id in(select fa.PROJ_ID from fallback_apply fa LEFT JOIN menu_back_set mbs on fa.MBS_ID = mbs.MBS_ID where mbs.FLAG ='1' and fa.AUDIT_STATE='1')");
					 criteria.add(Restrictions.sqlRestriction(sql.toString())); 
				 }else if(StringUtils.isNotBlank(roleIds) && roleIds.contains("101092")){//组长 回退审核
					 StringBuffer sql = new StringBuffer("proj_id in(select fa.PROJ_ID from fallback_apply fa LEFT JOIN menu_back_set mbs on fa.MBS_ID = mbs.MBS_ID where (mbs.FLAG is null OR mbs.FLAG='') and fa.AUDIT_STATE='1')");
					 criteria.add(Restrictions.sqlRestriction(sql.toString())); 
				 }
			 }
		}		
		int filterCount = this.countByCriteria(criteria);
		return filterCount;
	}
	
	/**
	 * 通过工程id和审核类型查询通知
	 * @author fuliwei
	 * @createTime 2017年7月30日
	 * @param 
	 * @return
	 */
	@Override
	public Notice findByProjIdAndType(String projId, String auditType) {
		if (StringUtils.isNotBlank(projId)  && StringUtils.isNotBlank(auditType) ) {
			Criteria criteria = super.getCriteria();
			criteria.add(Restrictions.eq("projId", projId));
			criteria.add(Restrictions.eq("auditType", auditType));
			List<Notice> list = this.findByCriteria(criteria);
			if(list!=null && list.size()>0){
				return list.get(0);
			}
		}
		return null;
	}
	
	/**
	 * 通过工程id、业务单id和审核类型查询通知
	 * @author fuliwei
	 * @createTime 2017年10月23日
	 * @param 
	 * @return
	 */
	@Override
	public Notice findByProjIdAndTypeAndBusId(String projId, String busId, String auditType) {
		if (StringUtils.isNotBlank(projId)  && StringUtils.isNotBlank(auditType) ) {
			Criteria criteria = super.getCriteria();
			criteria.add(Restrictions.eq("projId", projId));
			criteria.add(Restrictions.eq("businessOrderId", busId));
			criteria.add(Restrictions.eq("auditType", auditType));
			List<Notice> list = this.findByCriteria(criteria);
			if(list!=null && list.size()>0){
				return list.get(0);
			}
		}
		return null;
	}
	
	/**
	 * 查询签字通知是否下达
	 * @param projId
	 * @param actionType
	 * @return
	 */
	@Override
	public List<Notice> getSignNotice(String businessOrderId, String signType) {
		if (StringUtils.isNotBlank(businessOrderId)  && StringUtils.isNotBlank(signType) ) {
			Criteria criteria = super.getCriteria();
			criteria.add(Restrictions.eq("businessOrderId", businessOrderId));
			criteria.add(Restrictions.eq("signType", signType));
			List<Notice> list = this.findByCriteria(criteria);
			return list;
		}
		return null;
	}

	@Override
	public List<Notice> querySignNotice() {
		Criteria criteria = super.getCriteria();
		criteria.add(Restrictions.eq("noticeState", NoticeStateEnum.EFFECTIVE.getValue()));  // 有效通知
		
		
		LoginInfo login=SessionUtil.getLoginInfo();
		//分包方单位人员登录时，只看到自己单位的通知
		BusinessPartners bp =  businessPartnersDao.get(login.getDeptId());
		if(bp!=null){
			//若登录人是分包方单位人员
			if(UnitTypeEnum.CONSTRUCTION_UNIT.getValue().equals(bp.getUnitType())){
				 //通过分包单位人员id查询
				StringBuffer sql = new StringBuffer("proj_id in(select cp.proj_id from construction_plan cp where cp.cu_id='").append(login.getDeptId()).append("')");
				criteria.add(Restrictions.sqlRestriction(sql.toString()));
			}
			//若登录人是监理单位人员
			if(UnitTypeEnum.SUPERVISION_UNIT.getValue().equals(bp.getUnitType())){
				 //通过监理单位人员id查询
				StringBuffer sql = new StringBuffer("proj_id in(select cp.proj_id from construction_plan cp where cp.su_id='").append(login.getDeptId()).append("')");
				criteria.add(Restrictions.sqlRestriction(sql.toString()));
			}
		}
		
		String post=login.getPost();
		String [] postArray=post.split(",");
		
		//总工程师
		if(post.contains(PostTypeEnum.CENERAL_ENGINEER.getValue())){
			criteria.add(Restrictions.eq("signType", SignStatusEnum.CENERAL_ENGINEER.getValue()));  // 总工程师通知
		}
		
		//总监
		if(post.contains(PostTypeEnum.SUCSE.getValue())){
			criteria.add(Restrictions.eq("signType", SignStatusEnum.SUCSE.getValue()));  			// 总监通知
		}
		
		//现场监理
		if(post.contains(PostTypeEnum.SUJGJ.getValue())){
			criteria.add(Restrictions.eq("signType", SignStatusEnum.SUJGJ.getValue()));  			// 现场监理通知
		}
		
		
		//工程负责人 各组组长
		if(post.contains(PostTypeEnum.MARKETING_CENTER_LEADER_DEPUTY.getValue())||
				post.contains(PostTypeEnum.MODIFICATION_GROUP_LEADER.getValue())||
				post.contains(PostTypeEnum.TRUNK_GROUP_LEADER.getValue())||
				post.contains(PostTypeEnum.DEPUTY_LEADER.getValue())){
			StringBuffer sql = new StringBuffer("proj_id in(select cp.proj_id from project cp where cp.dept_id='").append(login.getDeptId()).append("')");
			criteria.add(Restrictions.sqlRestriction(sql.toString()));
			criteria.add(Restrictions.eq("signType", SignStatusEnum.PROJECT_LEADER.getValue())); 
		}
		
		
		//现场代表
		if(post.contains(PostTypeEnum.BUILDER.getValue())){
			if(!post.contains(PostTypeEnum.MARKETING_CENTER_LEADER_DEPUTY.getValue())&&
					!post.contains(PostTypeEnum.MODIFICATION_GROUP_LEADER.getValue())&&
					!post.contains(PostTypeEnum.TRUNK_GROUP_LEADER.getValue())&&
					!post.contains(PostTypeEnum.DEPUTY_LEADER.getValue())){
				StringBuffer sql = new StringBuffer("proj_id in(select cp.proj_id from construction_plan cp where cp.builder_id='").append(login.getStaffId()).append("')");
				criteria.add(Restrictions.sqlRestriction(sql.toString()));
				criteria.add(Restrictions.eq("signType", SignStatusEnum.BUILDER.getValue())); 
			}
		}
		

		
		if(post.contains(PostTypeEnum.CU_PM.getValue())||
			post.contains(PostTypeEnum.SAFTYOFFICER.getValue())||
			post.contains(PostTypeEnum.CONSTRUCTION.getValue())||
			post.contains(PostTypeEnum.MANAGEMENTWACF.getValue())||
			post.contains(PostTypeEnum.QUALITATIVE_CHECK_MEMBER.getValue())){
			StringBuffer sql= new StringBuffer("proj_id in(select cp.proj_id from construction_plan cp where 1=1 and cp.CU_PM_ID='").append(login.getStaffId()).append("'");
			sql.append(" or cp.SAFTY_OFFICER_ID='").append(login.getStaffId()).append("'");
			sql.append(" or cp.MANAGEMENT_QAE_ID='").append(login.getStaffId()).append("'");
			sql.append(" or cp.management_wacf_id='").append(login.getStaffId()).append("'");
			sql.append(" or cp.technician_id='").append(login.getStaffId()).append("'");
			sql.append(")");
			criteria.add(Restrictions.sqlRestriction(sql.toString()));
		
		 }
		
		
		List<String> signPost=new ArrayList<String>();
		
		if(postArray.length>0){
			if(postArray.length>1){
				if(post.contains(PostTypeEnum.CU_PM.getValue())||
						post.contains(PostTypeEnum.SAFTYOFFICER.getValue())||
						post.contains(PostTypeEnum.CONSTRUCTION.getValue())||
						post.contains(PostTypeEnum.MANAGEMENTWACF.getValue())||
						post.contains(PostTypeEnum.QUALITATIVE_CHECK_MEMBER.getValue())){
					for(int i=0;i<postArray.length;i++){
						SignStatusEnum s=SignStatusEnum.byPost(","+postArray[i]+",");
						if(s!=null){
							String ss=s.getValue();
							signPost.add(ss);
						}
					}
					criteria.add(Restrictions.in("signType", signPost));
				}
			}else{
				//项目经理
				if(post.contains(PostTypeEnum.CU_PM.getValue())){
					criteria.add(Restrictions.eq("signType", SignStatusEnum.CU_PM.getValue()));  			
				}
				//施工员
				if(post.contains(PostTypeEnum.CONSTRUCTION.getValue())){
					criteria.add(Restrictions.eq("signType", SignStatusEnum.CONSTRUCTION.getValue())); 
				}
				
				//安全员
				if(post.contains(PostTypeEnum.SAFTYOFFICER.getValue())){
					criteria.add(Restrictions.eq("signType", SignStatusEnum.SAFTYOFFICER.getValue())); 
				}
				
				//质检员
				if(post.contains(PostTypeEnum.QUALITATIVE_CHECK_MEMBER.getValue())){
					criteria.add(Restrictions.eq("signType", SignStatusEnum.QUALITATIVE_CHECK_MEMBER.getValue())); 
				}
			}
		}
		
		
		List<Notice> list = this.findByCriteria(criteria);
		return list;
	}
	
}
