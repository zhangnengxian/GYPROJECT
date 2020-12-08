package cc.dfsoft.project.biz.base.project.dao.impl;

import cc.dfsoft.project.biz.base.baseinfo.dao.BusinessPartnersDao;
import cc.dfsoft.project.biz.base.baseinfo.entity.BusinessPartners;
import cc.dfsoft.project.biz.base.project.dao.FallbackApplyDao;
import cc.dfsoft.project.biz.base.project.dto.FallbackApplyReq;
import cc.dfsoft.project.biz.base.project.entity.FallbackApply;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.ifs.projectQuery.dto.ProjectDto;
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
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.constant.DataFilerSetUpDto;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;
import cc.dfsoft.uexpress.common.util.MapUtils;
import cc.dfsoft.uexpress.common.util.ResultUtil;
import cc.dfsoft.uexpress.common.util.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
@Repository
public class FallbackApplyDaoImpl extends NewBaseDAO<FallbackApply, String> implements FallbackApplyDao {
	
	/**部门Dao*/
	@Resource
	DepartmentDao departmentDao;
	
	@Resource
    BusinessPartnersDao businessPartnersDao;
	@Resource
	StaffRoleDao staffRoleDao;
	
	@Override
	public Map<String, Object> queryFallbackApply(FallbackApplyReq req) throws ParseException {
		Criteria c = super.getCriteria();
		//工程id
		if(StringUtils.isNotBlank(req.getProjId())){
			c.add(Restrictions.eq("projId",req.getProjId()));
		}
		//工程编号
		if(StringUtils.isNotBlank(req.getProjNo())){
			c.add(Restrictions.eq("projNo",req.getProjNo()));
		}
		//工程名称
		if(StringUtils.isNotBlank(req.getProjName())){
			c.add(Restrictions.like("projName","%"+req.getProjName()+"%"));
		}
		//初始步骤Id
		if(StringUtils.isNotBlank(req.getOriginalStepId())){
			c.add(Restrictions.eq("originalStepId", req.getOriginalStepId()));
		}
		//回退步骤Id
		if(StringUtils.isNotBlank(req.getFallbackStepId())){
			c.add(Restrictions.eq("fallbackStepId", req.getFallbackStepId()));
		}
		//审核状态
		if(StringUtils.isNotBlank(req.getAuditState())){
			c.add(Restrictions.eq("auditState", req.getAuditState()));
		}
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		String post=loginInfo.getPost();
		String [] postArray=post.split(",");
		

//		if(!DeptDivideEnum.DESIGN_INSTITUTE.getValue().equals(loginInfo.getDeptDivide())){
//			//根据部门过滤
//			StringBuffer sql= new StringBuffer(" PROJ_ID in(select p.PROJ_ID from project p where p.DEPT_ID like '").append(loginInfo.getDeptId()).append("%'");
//			if(post.contains(PostTypeEnum.SURVEYER.getValue())){
//				sql.append(" and p.SURVEYER_ID='").append(loginInfo.getStaffId()).append("'");
//			}
//			sql.append(")");
//			c.add(Restrictions.sqlRestriction(sql.toString()));
//		}else{
//			if(post.contains(PostTypeEnum.DESIGNER.getValue())){
//				StringBuffer sql= new StringBuffer(" PROJ_ID in(select p.PROJ_ID from project p where p.designer_id ='").append(loginInfo.getStaffId()).append("')");
//				c.add(Restrictions.sqlRestriction(sql.toString()));
//			}
//		}
		
		//用于字符串日期格式转换
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		 
		 //申请日期开始
		 if(StringUtils.isNotBlank(req.getApplyTimeStart())){
			 c.add(Restrictions.ge("applyTime", sdf.parse(req.getApplyTimeStart())));
		 }
		 //申请日期结束
		 if(StringUtils.isNotBlank(req.getApplyTimeEnd())){
			 c.add(Restrictions.le("applyTime", sdf.parse(req.getApplyTimeEnd())));
		 }

		 Boolean flag=true;
		 List<DataFilerSetUpDto> datalist = Constants.getDataFilterMapByKey(loginInfo.getCorpId()+"_"+Constants.FALL_BACK);
		 if(datalist!=null && datalist.size()>0){
		 	//是否配置
			 StringBuffer hql = new StringBuffer();
			if(StringUtils.isNotBlank(datalist.get(0).getSupSql())){
				c.add(Restrictions.sqlRestriction(hql.toString()));
			}
			flag = false;
		 }

		 
		 //分包方单位人员登录时，只可看自己相关项目
		 BusinessPartners bp =  businessPartnersDao.get(loginInfo.getDeptId());
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
					 dept=departmentDao.queryDepartmentByDivide(DeptDivideEnum.CIVIL_GROUP.getValue(),loginInfo.getDeptId());
					 if(dept != null && StringUtils.isNotBlank(dept.getDeptId())){
						 deptIdArray.add(dept.getDeptId());
					 }
				 }
				 if(post.contains(PostTypeEnum.MARKETING_CENTER_HEAD.getValue())){
					 //公建负责人
					 dept=departmentDao.queryDepartmentByDivide(DeptDivideEnum.MARKETING_CENTER.getValue(),loginInfo.getDeptId());
					 if(dept != null && StringUtils.isNotBlank(dept.getDeptId())){
						 deptIdArray.add(dept.getDeptId());
					 }
				 }
				 if(post.contains(PostTypeEnum.MODIFICATION_HEAD.getValue())){
					 //改管负责人
					 dept=departmentDao.queryDepartmentByDivide(DeptDivideEnum.MODIFICATION_GROUP.getValue(),loginInfo.getDeptId());
					 if(dept != null && StringUtils.isNotBlank(dept.getDeptId())){
						 deptIdArray.add(dept.getDeptId());
					 }
				 }
				 if(post.contains(PostTypeEnum.TRUNK_HEAD.getValue())){
					 //干线负责人
					 dept=departmentDao.queryDepartmentByDivide(DeptDivideEnum.TRUNK_GROUP.getValue(),loginInfo.getDeptId());
					 if(dept != null && StringUtils.isNotBlank(dept.getDeptId())){
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
					 c.add(Restrictions.in("projId",projIds));
				 }else{
					 return ResultUtil.pageResult( 0, req.getDraw(),new ArrayList()); 
				 }
			 }
		 } 
		 //当前用户角色
		 StaffRole staffRole = staffRoleDao.queryStaffRoleInfo("", loginInfo.getStaffId());  // 提取员工角色
		 if(staffRole!=null){
			 String  roleIds = staffRole.getRoleIds();
			 //有预结算回退审核权限
			 if(StringUtils.isNotBlank(roleIds) && roleIds.contains("101091")){
				 StringBuffer sql = new StringBuffer("mbs_id in(select mbs.mbs_id from menu_back_set mbs where mbs.FLAG ='1')");
				 c.add(Restrictions.sqlRestriction(sql.toString())); 
			 }else if(StringUtils.isNotBlank(roleIds) && roleIds.contains("101092")){//组长 回退审核
				 StringBuffer sql = new StringBuffer("mbs_id in(select mbs.mbs_id from menu_back_set mbs where mbs.FLAG is null OR mbs.FLAG='')");
				 c.add(Restrictions.sqlRestriction(sql.toString())); 
			 }
		 }
		// 数据库根据条件过滤记录数
		int filterCount = this.countByCriteria(c);
		// 根据条件获取查询信息
		List<FallbackApply> list = this.findBySortCriteria(c, req);
		// 返回结果
		return ResultUtil.pageResult(filterCount, req.getDraw(),list);
		
	}

	/**
	 * @MethodDesc: 查询回退审核列表
	 * @Author zhangnx
	 * @Date 2019/7/4 19:30
	 */
	@Override
	public Map<String, Object> queryRollBackAuditList(FallbackApplyReq req) {

		List<Object> paramList=new ArrayList<>();
		String conditionalSql = conditionalFilter(req);

		StringBuilder filterCountSql=new StringBuilder();
		filterCountSql.append("SELECT count(b.FA_ID)  FROM project a, fallback_apply b  WHERE a.PROJ_ID = b.PROJ_ID ");
		filterCountSql.append(conditionalSql);
		int filterCount = this.getCountBySql(filterCountSql.toString(),paramList.toArray());

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT b.FA_ID 'faId',\n" +
							"b.PROJ_ID 'projId',\n" +
							"b.PROJ_NO 'projNo',\n" +
							"b.PROJ_NAME 'projName',\n" +
							"b.ORIGINAL_STEP_ID 'originalStepId',\n" +
							"b.FALLBACK_STEP_ID 'fallbackStepId',\n" +
							"b.FALLBACK_REASON 'fallbackReason',\n" +
							"b.APPLY_TIME 'applyTime',\n" +
							"b.FALLBACK_TIME 'fallbackTime',\n" +
							"b.APPLY_OPERATOR_ID 'applyOperatorId',\n" +
							"b.APPLY_OPERATOR 'applyOperator',\n" +
							"b.AUDIT_STATE 'auditState',\n" +
							"b.AUDIT_INFO 'auditInfo',\n" +
							"b.MBS_ID 'mbsId',\n" +
							"b.CORP_ID 'corpId'\n" +
				"FROM fallback_apply b,project a \n" +
				"WHERE b.PROJ_ID = a.PROJ_ID ");

		sql.append(conditionalSql);

		if (req.getLength()!=0) {
			sql.append(" LIMIT ").append(req.getStart()).append(",").append(req.getLength());
		}

		List<Map<String, Object>> listBySql = this.findListBySql(sql.toString(),paramList.toArray());
		List<FallbackApply> fallbackApplyList = MapUtils.convertListMap2ListBean(listBySql, FallbackApply.class);

		return ResultUtil.pageResult(filterCount, req.getDraw(),fallbackApplyList);

	}

	@Override
	public FallbackApply queryFallbackApplyInfo(String projId, String currentStepId, String fallBackStepId) {
		Criteria c = super.getCriteria();

		if(StringUtils.isNotBlank(projId)){
			c.add(Restrictions.eq("projId",projId));
		}
		if (StringUtils.isNotBlank(currentStepId)){
			c.add(Restrictions.eq("originalStepId",currentStepId));
		}
		if (StringUtils.isNotBlank(fallBackStepId)){
			c.add(Restrictions.eq("fallbackStepId",fallBackStepId));
		}

		List<FallbackApply> list = this.findByCriteria(c);
		if (list!=null && list.size()>0){
			return list.get(0);
		}

		return null;
	}


	/**
	 * @MethodDesc: 查询条件
	 * @Author zhangnx
	 * @Date 2019/7/4 19:31
	 */
	public String conditionalFilter(FallbackApplyReq req){

		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		StringBuilder whereHql=new StringBuilder();

		whereHql.append("AND a.CORP_ID='"+loginInfo.getCorpId()+"'");

		DataFilerSetUpDto dfs = Constants.isConfig(loginInfo.getDeptId() + "_" + req.getMenuId());
		if (dfs!=null && StringUtil.isNotBlank(dfs.getSupSql())){
			whereHql.append(dfs.getSupSql());
		}

		if (StringUtil.isNotBlank(req.getProjNo()))whereHql.append(" AND a.PROJ_NO='"+req.getProjNo()+"'");
		if (StringUtil.isNotBlank(req.getProjName()))whereHql.append(" AND a.PROJ_NAME='"+req.getProjName()+"'");
		whereHql.append("AND b.AUDIT_STATE='1'");


		List<ProjectDto> dtoList = getQueryAuthority();
		if (dtoList!=null && dtoList.size()>0){
			whereHql.append("AND (");
			List<String> sqlList=new ArrayList<>();
			int count=1;
			for (ProjectDto p: dtoList) {
				boolean isProjType = StringUtil.isNotBlank(p.getProjType()) && !"0".equals(p.getProjType());
				boolean isConb = StringUtil.isNotBlank(p.getConb()) && !"0".equals(p.getConb());
				boolean isStepId = StringUtil.isNotBlank(p.getStepId()) && !"0".equals(p.getStepId());

				if (!isStepId) continue;
				if (count>1)whereHql.append(" OR ");
				if (isProjType)whereHql.append("(");
				whereHql.append(" b.FALLBACK_STEP_ID='"+p.getStepId()+"'");

				if (isProjType){
					whereHql.append("and a.PROJECT_TYPE='"+p.getProjType()+"'");
					if (isConb){
						whereHql.append("and a.CONTRIBUTION_MODE='"+p.getConb()+"'");
					}
					whereHql.append(")");
				}
				count++;
			}
			whereHql.append(")");
		}
		return whereHql.toString();
	}



	/**
	 * @MethodDesc: 获取需要审核的查询条件
	 * @Author zhangnx
	 * @Date 2019/7/4 19:31
	 */
	public List<ProjectDto> getQueryAuthority(){
		LoginInfo loginInfo = SessionUtil.getLoginInfo();

		List<Object> paramList=new ArrayList<>();
		StringBuilder querySql=new StringBuilder();

		querySql.append("SELECT b.proj_type 'projType', b.conb 'conb', b.step_id 'stepId',b.grade 'grade'");
		querySql.append("FROM sys_staff_role a, role b  WHERE a.role_id = b.ROLE_ID ");
		querySql.append(" AND b.grade IS NOT NULL ");
		querySql.append(" AND a.staff_id =? ");

		paramList.add(loginInfo.getStaffId());

		List<Map<String, Object>> listBySql = this.findListBySql(querySql.toString(),paramList.toArray());
		if (listBySql==null || listBySql.size()<1) return null;

		List<ProjectDto> projectDtoList = MapUtils.convertListMap2ListBean(listBySql, ProjectDto.class);

		return projectDtoList;

	}







}
