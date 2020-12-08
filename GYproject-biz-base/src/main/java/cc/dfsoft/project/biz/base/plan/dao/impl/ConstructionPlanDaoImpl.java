package cc.dfsoft.project.biz.base.plan.dao.impl;

import cc.dfsoft.project.biz.base.baseinfo.dao.BusinessPartnersDao;
import cc.dfsoft.project.biz.base.baseinfo.entity.BusinessPartners;
import cc.dfsoft.project.biz.base.design.enums.UnitTypeEnum;
import cc.dfsoft.project.biz.base.plan.dao.ConstructionPlanDao;
import cc.dfsoft.project.biz.base.plan.entity.ConstructionPlan;
import cc.dfsoft.project.biz.base.plan.enums.ConstructionPlanQueryReq;
import cc.dfsoft.project.biz.base.project.dao.ProjectDao;
import cc.dfsoft.project.biz.base.project.dao.ProjectSignDao;
import cc.dfsoft.project.biz.base.project.dto.ProjectQueryReq;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.project.entity.ProjectSign;
import cc.dfsoft.project.biz.base.project.enums.IsSignEnum;
import cc.dfsoft.project.biz.base.project.enums.ProjStatusEnum;
import cc.dfsoft.project.biz.base.project.enums.ProjectSignTypeEnum;
import cc.dfsoft.project.biz.base.subpackage.dto.PaymentApplyReq;
import cc.dfsoft.project.biz.base.subpackage.enums.FeeTypeEnum;
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
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Repository
public class ConstructionPlanDaoImpl extends NewBaseDAO<ConstructionPlan, String> implements ConstructionPlanDao{
	
	@Resource
	ProjectDao projectDao;
	
	/**部门Dao*/
	@Resource
	DepartmentDao departmentDao;
	
	@Resource
    BusinessPartnersDao businessPartnersDao;
	@Resource
	ProjectSignDao projectSignDao;
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ConstructionPlan> findByProjNo(String projNo) {
		StringBuffer hql = new StringBuffer();
		hql.append("from ConstructionPlan where projNo = '").append(projNo).append("'");
		return super.findByHql(hql.toString());
	}

	
	/**
	 * 根据工程id查询工程计划
	 * @author fuliwei
	 * @createTime 2016-7-5
	 * @param	id
	 * @return	ConstructionPlan
	 */
	@Override
	public ConstructionPlan viewPlanById(String id) {
		Criteria c = super.getCriteria();
		if(StringUtils.isNotBlank(id)){
			c.add(Restrictions.eq("projId", id));
			List<ConstructionPlan> list = this.findByCriteria(c);
			if(list != null && list.size()>0){
				return list.get(0);
			}
		}
		return null;
	}


	@Override
	public Map<String, Object> queryConstructionPlan(ConstructionPlanQueryReq constructionPlanQueryReq) throws ParseException {
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		Criteria c = super.getCriteria();
		 //工程编号wo b ting
		 if(StringUtils.isNotBlank(constructionPlanQueryReq.getProjNo())){
			 c.add(Restrictions.like("projNo",constructionPlanQueryReq.getProjNo()+"%"));
		 }
		 //工程id
		 if(StringUtils.isNotBlank(constructionPlanQueryReq.getProjId())){
			 c.add(Restrictions.eq("projId",constructionPlanQueryReq.getProjId()));
		 }
		 //根据监理去查询工程
		 if(StringUtils.isNotBlank(constructionPlanQueryReq.getSuJgjId())){
			 c.add(Restrictions.eq("suJgjId", constructionPlanQueryReq.getSuJgjId()));
		 }
		//根据项目经理去查询工程
		 if(StringUtils.isNotBlank(constructionPlanQueryReq.getCuPm())){
			 c.add(Restrictions.like("cuPm", "%"+constructionPlanQueryReq.getCuPm()+"%"));
		 }
		 //工程类型
		 if(StringUtils.isNotBlank(constructionPlanQueryReq.getProjLType())){
			 StringBuffer sql = new StringBuffer();
			 sql.append("proj_id in (select proj_id from project where 1=1 and PROJ_LTYPE_ID='").append(constructionPlanQueryReq.getProjLType()).append("')");
			 c.add(Restrictions.sqlRestriction(sql.toString()));
		 }
		 //工程状态
		 if(StringUtils.isNotBlank(constructionPlanQueryReq.getProjStatus())){
			 StringBuffer hql = new StringBuffer("from Project where projStatusId = '").append(constructionPlanQueryReq.getProjStatus()).append("'");
			 List<Project> projects = super.findByHql(hql.toString());
			 List<String> projIds = new ArrayList();
			 if(projects.size()>0){
				 for(Project project:projects){
					 projIds.add(project.getProjId());
				 }
				 c.add(Restrictions.in("projId",projIds));
			 }else{
				// 返回结果
				 return ResultUtil.pageResult( 0, constructionPlanQueryReq.getDraw(),new ArrayList());
			 }
		 }
		 //工程状态集合
		 if(constructionPlanQueryReq.getProjStuList()!=null&&constructionPlanQueryReq.getProjStuList().size()>0){
			//工程状态
			List<String> projStus=constructionPlanQueryReq.getProjStuList();
			 StringBuffer hql = new StringBuffer("from Project where projStatusId = '");
			 for(int i=0;i<projStus.size();i++){
				 if(i==0){
					 hql.append(projStus.get(i)).append("'");
				 }else{
					 hql.append(" or projStatusId ='").append(projStus.get(i)).append("'");
				 }
			 }
			 List<Project> projects = super.findByHql(hql.toString());
			 List<String> projIds = new ArrayList();
			 if(projects.size()>0){
				 for(Project project:projects){
					 projIds.add(project.getProjId());
				 }
				 c.add(Restrictions.in("projId",projIds));
			 }else{
				// 返回结果
				 return ResultUtil.pageResult( 0, constructionPlanQueryReq.getDraw(),new ArrayList());
			 }
		 }
		//是否打印
		if(StringUtils.isNotBlank(constructionPlanQueryReq.getIsPrint())){
			c.add(Restrictions.eq("isPrint", constructionPlanQueryReq.getIsPrint()));
		}
		//监理任务单打印
		if(StringUtils.isNotBlank(constructionPlanQueryReq.getSuIsPrint())){
			c.add(Restrictions.eq("suIsPrint",constructionPlanQueryReq.getSuIsPrint()));
		}
		//施工单位是否派遣
		if(StringUtils.isNotBlank(constructionPlanQueryReq.getCuIsDispatch())){
			c.add(Restrictions.eq("cuIsDispatch", constructionPlanQueryReq.getCuIsDispatch()));
		}
		//监理是否派遣
		if(StringUtils.isNotBlank(constructionPlanQueryReq.getSuIsDispatch())){
			c.add(Restrictions.eq("suIsDispatch",constructionPlanQueryReq.getSuIsDispatch()));
		}
		 //工程名称
		 if(StringUtils.isNotBlank(constructionPlanQueryReq.getProjName())){
			 c.add(Restrictions.like("projName", "%"+constructionPlanQueryReq.getProjName()+"%"));
		 }
		 //工程地点
		 if(StringUtils.isNotBlank(constructionPlanQueryReq.getProjAddr())){
			 c.add(Restrictions.like("projAddr", "%"+constructionPlanQueryReq.getProjAddr()+"%"));
		 }
		 //编制人
		 if(StringUtils.isNotBlank(constructionPlanQueryReq.getCpDocumenter())){
			 c.add(Restrictions.like("cpDocumenter", "%"+constructionPlanQueryReq.getCpDocumenter()+"%"));
		 }
		//用于字符串日期格式转换
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		 
		 //编制日期开始日期
		 if(StringUtils.isNotBlank(constructionPlanQueryReq.getCpArriveDateStart())){
			 c.add(Restrictions.ge("cpArriveDate", sdf.parse(constructionPlanQueryReq.getCpArriveDateStart())));
		 }
		 //编制日期结束日期
		 if(StringUtils.isNotBlank(constructionPlanQueryReq.getCpArriveDateEnd())){
			 c.add(Restrictions.le("cpArriveDate", sdf.parse(constructionPlanQueryReq.getCpArriveDateEnd())));
		 }
		 
		 //开工日期开始开始日期
		 if(StringUtils.isNotBlank(constructionPlanQueryReq.getPlannedStartDateStart())){
			 c.add(Restrictions.ge("plannedStartDate", sdf.parse(constructionPlanQueryReq.getPlannedStartDateStart())));
		 }
		 //开工日期开始结束日期
		 if(StringUtils.isNotBlank(constructionPlanQueryReq.getPlannedStartDateEnd())){
			 c.add(Restrictions.le("plannedStartDate", sdf.parse(constructionPlanQueryReq.getPlannedStartDateEnd())));
		 }
		 
		 String post=loginInfo.getPost();   //取得登录人员职务、职务可能是一个或者多个
		 String [] postArray=post.split(",");   //将多职务按照逗号分割开，并存在数组postArray中
		 
		 //分包方单位人员登录时，只可看自己相关项目
		 BusinessPartners bp =  businessPartnersDao.get(loginInfo.getDeptId());
		//分公司数据过滤 根据人员部门+菜单ID得到sql配置
		 //否则按照之前的执行
		 List<DataFilerSetUpDto> list = Constants.getDataFilterMapByKey(loginInfo.getDeptId()+"_"+constructionPlanQueryReq.getMenuId());
		 boolean flag = true;
		 if(list!=null && list.size()>0){
			 StringBuffer hql = new StringBuffer();
			 hql.append("proj_id in (select proj_id from project where 1=1 ");
			 
			 if(StringUtils.isNotBlank(list.get(0).getSupSql())){
				 hql.append(list.get(0).getSupSql());
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



			 boolean isProjId=true;
			 DataFilerSetUpDto staffIdAndDeptIdAndMenuId = Constants.isConfig(loginInfo.getStaffId() +"_"+loginInfo.getDeptId()+ "_" + constructionPlanQueryReq.getMenuId());
			 if (staffIdAndDeptIdAndMenuId!=null){
				 isProjId=false;
				 if (StringUtils.isNotBlank(staffIdAndDeptIdAndMenuId.getSupSql())) {
					 c.add(Restrictions.sqlRestriction(staffIdAndDeptIdAndMenuId.getSupSql()));
				 }
			 }


			 if(deptIdArray!=null && deptIdArray.size()>0 && isProjId){
				 StringBuffer hql = new StringBuffer("from Project where deptId in ('");
				 for(int i=1;i<deptIdArray.size();i++){
					 hql.append(deptIdArray.get(i)).append("',").append("'");
				 }
				 hql.append(deptIdArray.get(0)).append("')");
				 hql.append(" and projStatusId='").append(constructionPlanQueryReq.getProjStatus()).append("'");
				 List<Project> projects = super.findByHql(hql.toString());
				 List<String> projIds = new ArrayList();
				 if(projects!=null&&projects.size()>0){
					 for(Project project:projects){
						 projIds.add(project.getProjId());
					 }
					 c.add(Restrictions.in("projId",projIds));
				 }else{
					 return ResultUtil.pageResult( 0, constructionPlanQueryReq.getDraw(),new ArrayList()); 
				 }
			 }
		 }

		 //分包方单位人员登录时，只可看自己相关项目
		/* if(bp!=null){
			 //若登录人是分包方单位人员
			 if(UnitTypeEnum.CONSTRUCTION_UNIT.getValue().equals(bp.getUnitType())){
				 //通过分包单位人员id查询
				 StringBuffer sql = new StringBuffer("proj_id in(select cp.proj_id from construction_plan cp where cp.cu_id='").append(loginInfo.getDeptId()).append("')");
				 c.add(Restrictions.sqlRestriction(sql.toString()));
			 }else if(UnitTypeEnum.SUPERVISION_UNIT.getValue().equals(bp.getUnitType())){
				//若登录人是监理单位
				 StringBuffer sql = new StringBuffer("proj_id in(select cp.proj_id from construction_plan cp where cp.su_id='").append(loginInfo.getDeptId()).append("')");
				 c.add(Restrictions.sqlRestriction(sql.toString()));
			 }
		 }*/
		 if(bp!=null){
			//若登录人是分包方单位人员
			 if(UnitTypeEnum.CONSTRUCTION_UNIT.getValue().equals(bp.getUnitType())&& loginInfo.getPost().length()>0){
				 StringBuffer sqlFilter = projectDao.cuUnitFilter(loginInfo,loginInfo.getPost(), constructionPlanQueryReq.getMenuId());
				 c.add(Restrictions.sqlRestriction(sqlFilter.toString()));
			 }else if(UnitTypeEnum.SUPERVISION_UNIT.getValue().equals(bp.getUnitType())){
				 //若登录人是监理单位
				 StringBuffer sqlFilter = projectDao.suUnitFilter(loginInfo, loginInfo.getPost(), constructionPlanQueryReq.getMenuId());
				 c.add(Restrictions.sqlRestriction(sqlFilter.toString()));
			 }else{
				 //其他业务合作伙伴-如检测单位
			 }
		 }
		 if(null!=post && postArray.length>0 && !DeptDivideEnum.CUSTOMER_SERVICE_CENTER.getValue().equals(loginInfo.getDeptDivide())){
			 if(post.contains(PostTypeEnum.BUILDER.getValue())){
				 //贵安现场代表兼资料员的，可以看到所有工程
				 boolean flag1 = true;
				 if(post.contains(PostTypeEnum.BUSINESSASSISTANT.getValue())){
					 //是项目经理  所属一个公司，如果所属多个公司，需要配置sql
					 List<DataFilerSetUpDto> corpList = Constants.getDataFilterMapByKey(loginInfo.getCorpId()+"_"+PostTypeEnum.BUSINESSASSISTANT.getValue()+"_"+constructionPlanQueryReq.getMenuId());
					 if(corpList!=null && corpList.size()>0 && StringUtil.isNotBlank(corpList.get(0).getSupSql())){
						 c.add(Restrictions.sqlRestriction(corpList.get(0).getSupSql()));
						 flag1=false;
					 }
				 }
				 if(!post.contains(PostTypeEnum.MARKETING_CENTER_LEADER_DEPUTY.getValue())
						 && !post.contains(PostTypeEnum.MODIFICATION_GROUP_LEADER.getValue())
						 && !post.contains(PostTypeEnum.TRUNK_GROUP_LEADER.getValue())
						 && !post.contains(PostTypeEnum.VICE_MINISTER.getValue())
						 && !post.contains(PostTypeEnum.MINISTER.getValue())
						 && flag1){
					 c.add(Restrictions.eq("builderId",loginInfo.getStaffId())); 
				 }
			 }
		 }

		 c.addOrder(Order.desc("cpArriveDate"));
		 // 数据库根据条件过滤记录数
		 int filterCount = this.countByCriteria(c);

		 // 根据条件获取查询信息
		 List<ConstructionPlan> constructionPlanList = this.findBySortCriteria(c, constructionPlanQueryReq);
		
		 // 返回结果
		 return ResultUtil.pageResult( filterCount, constructionPlanQueryReq.getDraw(),constructionPlanList);
	}


	@Override
	public List<Map<String, Object>> countProjectNum(String name) {
		//查询参数集合
		List<Object> params = new ArrayList<Object>();
		String sql =" select name, "
				+ "max(decode(state,'1',contNum,0)) as toBeBuild,"
				+ "max(decode(state,'2',contNum,0)) as beBuild,"
				+ "max(decode(state,'3',contNum,0)) as builded,"
				+ "SUM(contNum) as total "
				+ "from (select count(m.proj_Id) as contNum,"+name+" as name,m.state as state "
				+ "from CONSTRUCTION_PLAN c left join "
				+ "(select proj_Id,"
				+ "CASE WHEN t.proj_status_id <= 1018 THEN '1' "
				+ "WHEN t.proj_status_id > 1018 AND t.proj_status_id < 2001  THEN '2' "
				+ "WHEN t.proj_status_id = 2001 AND t.BACK_REASON is null THEN '3' "
				+ "ELSE NULL END state "
				+ "from project t ) m on m.proj_id=c.proj_id where "+name+" is not null "
				+ "group by "+name+",m.state order by "+name
				+ ") p group by name";
		List<Map<String,Object>> list=this.findListBySql(sql, params.toArray());
	
		return list;
      
	}


	/**
	 * 根据工程id查询工程计划
	 */
	@Override
	public ConstructionPlan findByProjId(String proID) {
		 Criteria c = super.getCriteria();
		 
		 //工程id
		 if(StringUtils.isNotBlank(proID)){
			 c.add(Restrictions.eq("projId",proID));
		 }
		 List<ConstructionPlan> list = this.findByCriteria(c);
		 if(list!=null && list.size()>0){
			 return list.get(0);
		 }
		return null;
	}

	/**
	 * 查询未派工的任务
	 * @author fuliwei
	 * @createTime 2017年11月21日
	 * @param 
	 * @return
	 */
	@Override
	public List<ConstructionPlan> findNotDispacthPlan(String type) {
		Criteria c = super.getCriteria();
		
		StringBuffer hql=new StringBuffer();
		LoginInfo loginInfo=SessionUtil.getLoginInfo();
		if(StringUtils.isNotBlank(type)){
			if(UnitTypeEnum.CONSTRUCTION_UNIT.getValue().equals(type)){
				//施工单位
				hql.append("from ConstructionPlan where  (cu_Pm is null or cu_Pm='') and (management_Qae is null or management_Qae ='' ) and (management_Wacf is null or management_Wacf ='' ) and (safty_Officer is NULL or safty_Officer ='' )and (technician is null or technician='') and cu_id='").append(loginInfo.getDeptId()).append("'");
				hql.append(" and projId not in (select projId from Project where PROJ_STATUS_ID='").append(ProjStatusEnum.TO_PLAN_AUDIT.getValue()).append("' or PROJ_STATUS_ID='").append(ProjStatusEnum.TO_MAKE_PLAN.getValue()).append("')");
				
				//判断是否查看分公司数据
				if(StringUtil.isNotBlank(loginInfo.getBelongCorpId())){
					hql.append(" and corp_id in ('");
					 String [] ids=loginInfo.getBelongCorpId().split(",");
					 if(ids.length>0){
						 for(int i=1;i<ids.length;i++){
							 hql.append(ids[i]).append("',").append("'");
						 }
					 }
					 hql.append(ids[0]).append("')");
				 }
			}else{
				//监理单位
				hql.append("from ConstructionPlan where (SU_JGJ_ID is null or SU_JGJ_ID='') and (SU_CSE_ID is null or SU_CSE_ID ='' ) and (SU_PROFESSIONAL_ID is null or SU_PROFESSIONAL_ID ='' ) and (SU_REPRESENTATIVE_ID is NULL or SU_REPRESENTATIVE_ID ='') and su_id='").append(loginInfo.getDeptId()).append("'");
				hql.append(" and projId not in (select projId from Project where PROJ_STATUS_ID='").append(ProjStatusEnum.TO_PLAN_AUDIT.getValue()).append("' or PROJ_STATUS_ID='").append(ProjStatusEnum.TO_MAKE_PLAN.getValue()).append("')");
			}
			
			List<ConstructionPlan>  list=this.findByHql(hql.toString());
			return list;
		}
		
		
		return null;
	}

	
	/**
	 * 查询任务单通知
	 * @author fuliwei
	 * @createTime 2017年11月22日
	 * @param 
	 * @return
	 */
	@Override
	public List<ConstructionPlan> queryPlanNotice(ProjectQueryReq req, List<String> projStatus) {
		Criteria criteria = super.getCriteria();
		
		LoginInfo loginInfo=SessionUtil.getLoginInfo();
		
		//限制工程状态是待施工预算 到施工中的
		if(projStatus.size()>0){
			 StringBuffer hql = new StringBuffer("from Project where projStatusId in ('");
			 for(int i=1;i<projStatus.size();i++){
				 hql.append(projStatus.get(i)).append("',").append("'");
			 }
			 hql.append(projStatus.get(0)).append("')");
			 /*hql.append("and projId not in(select projId from SubSupplyContract)");*/
			 List<Project> projects = super.findByHql(hql.toString());
			 List<String> projIds = new ArrayList();
			 if(projects!=null&&projects.size()>0){
				 for(Project project : projects){
					 projIds.add(project.getProjId());
				 }
				 criteria.add(Restrictions.in("projId", projIds));
			 }else{
				 //返回结果
				 return null;
			 }
		 }
		String post=loginInfo.getPost();
		String [] postArray=post.split(",");
		 
		BusinessPartners bp =  businessPartnersDao.get(loginInfo.getDeptId());
		if(bp!=null){
			 //若登录人是分包方单位人员
			 if(UnitTypeEnum.CONSTRUCTION_UNIT.getValue().equals(bp.getUnitType())){
				 
				 if(post.contains(PostTypeEnum.CU_PM.getValue())||
							post.contains(PostTypeEnum.SAFTYOFFICER.getValue())||
							post.contains(PostTypeEnum.CONSTRUCTION.getValue())||
							post.contains(PostTypeEnum.MANAGEMENTWACF.getValue())||
							post.contains(PostTypeEnum.QUALITATIVE_CHECK_MEMBER.getValue())||
							post.contains(PostTypeEnum.TEST_LEADER.getValue())||
							post.contains(PostTypeEnum.WELDER.getValue())){
							 
					StringBuffer sql= new StringBuffer("proj_id in(select cp.proj_id from construction_plan cp where 1=1 and cp.CU_PM_ID='").append(loginInfo.getStaffId()).append("'");
					sql.append(" or cp.SAFTY_OFFICER_ID='").append(loginInfo.getStaffId()).append("'");
					sql.append(" or cp.MANAGEMENT_QAE_ID='").append(loginInfo.getStaffId()).append("'");
					sql.append(" or cp.management_wacf_id='").append(loginInfo.getStaffId()).append("'");
					sql.append(" or cp.technician_id='").append(loginInfo.getStaffId()).append("'");
					sql.append(" or cp.test_leader_id like'%,").append(loginInfo.getStaffId()).append(",%'");
					sql.append(" or cp.welder_id like'%,").append(loginInfo.getStaffId()).append(",%'");
					sql.append(")");
					criteria.add(Restrictions.sqlRestriction(sql.toString()));
				 }
				 
				
			 }else if(UnitTypeEnum.SUPERVISION_UNIT.getValue().equals(bp.getUnitType())){
				//若登录人是监理单位
				 if(post.contains(PostTypeEnum.SUCSE.getValue())){//总监可以看到所有
					 StringBuffer sql = new StringBuffer("proj_id in(select cp.proj_id from construction_plan cp where cp.su_id='").append(loginInfo.getDeptId()).append("')");
					 criteria.add(Restrictions.sqlRestriction(sql.toString()));
				 }else if(post.contains(PostTypeEnum.SUJGJ.getValue())||
				    post.contains(PostTypeEnum.PROFESSIONAL_SUPERVISION.getValue())||
				    post.contains(PostTypeEnum.SUCSE_REPRESENTATIVE.getValue())){//这四个职务只能看分配到相应角色的工程
					 StringBuffer sql = new StringBuffer("proj_id in(select cp.proj_id from construction_plan cp where cp.SU_JGJ_ID='").append(loginInfo.getStaffId()).append("'");
					 sql.append(" or cp.SU_PROFESSIONAL_ID='").append(loginInfo.getStaffId()).append("'");
						sql.append(" or cp.SU_REPRESENTATIVE_ID='").append(loginInfo.getStaffId()).append("'");
						sql.append(")");
						criteria.add(Restrictions.sqlRestriction(sql.toString()));
				 }else{//其他职务可以看到所有
					 StringBuffer sql = new StringBuffer("proj_id in(select cp.proj_id from construction_plan cp where cp.su_id='").append(loginInfo.getDeptId()).append("')");
					 criteria.add(Restrictions.sqlRestriction(sql.toString()));
				 }
			
			 }
			 
		}
		
		//现场代表
		 if(post.contains(PostTypeEnum.BUILDER.getValue())){
			 StringBuffer sql= new StringBuffer("proj_id in(select cp.proj_id from Construction_Plan cp where cp.BUILDER_ID = '").append(loginInfo.getStaffId()).append("')");
			 criteria.add(Restrictions.sqlRestriction(sql.toString()));
		 }
		
		List<ConstructionPlan> list = this.findByCriteria(criteria);
		return list;
	}

	
	/**
	 * 查询可付款申请工程
	 * @author fuliwei
	 * @createTime 2018年6月11日
	 * @param 
	 * @return
	 */
	@Override
	public Map<String, Object> queryPayProject(PaymentApplyReq paymentApplyReq) {
		Criteria c = super.getCriteria();
		
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		StringBuffer sql = new StringBuffer("select * from (select cp.*,p.proj_status_id,fal.FEE_TYPE,sd.END_DECLARATION_COST from construction_plan cp LEFT JOIN settlement_declaration sd on (sd.PROJ_ID = cp.PROJ_ID) LEFT JOIN project p on (p.PROJ_ID = cp.PROJ_ID");
		if(StringUtil.isNotBlank(paymentApplyReq.getProjLtypeId())){
			sql.append(" and p.PROJ_LTYPE_ID='").append(paymentApplyReq.getProjLtypeId()).append("'");
		}
		sql.append(" ) left JOIN fee_apply_list fal on (cp.proj_id = fal.PROJ_ID and fal.FEE_TYPE='");
		
		if(StringUtils.isNotBlank(paymentApplyReq.getFeeType())&& FeeTypeEnum.DESIGN_FEE.getValue().equals(paymentApplyReq.getFeeType())){
			//设计费
			sql.append(paymentApplyReq.getFeeType()).append("')) res WHERE IFNULL(res.proj_status_id,'')!='2001' and  IFNULL(res.FEE_TYPE,'')!='").append(paymentApplyReq.getFeeType()).append("'");
		}
		
		if(StringUtils.isNotBlank(paymentApplyReq.getFeeType())&& FeeTypeEnum.SU_FEE.getValue().equals(paymentApplyReq.getFeeType())){
			//监理费
			sql.append(paymentApplyReq.getFeeType()).append("')) res WHERE IFNULL(res.proj_status_id,'')!='2001' and  IFNULL(res.FEE_TYPE,'')!='").append(paymentApplyReq.getFeeType()).append("'");
			sql.append(" and res.proj_status_id in('1032','1035','1036')");
		}
		//检测费
		if(StringUtils.isNotBlank(paymentApplyReq.getFeeType())&& FeeTypeEnum.CHECK_FEE.getValue().equals(paymentApplyReq.getFeeType())){
			//检测费
			sql.append(paymentApplyReq.getFeeType()).append("')) res WHERE IFNULL(res.proj_status_id,'')!='2001' and  IFNULL(res.FEE_TYPE,'')!='").append(paymentApplyReq.getFeeType()).append("'");
			List<ProjStatusEnum> list=ProjStatusEnum.getThanValueNew(ProjStatusEnum.DURING_CONSTRUCTION.getValue(),ProjStatusEnum.ALREADY_COMPLETED.getValue());
			String filter = "";
			for(int i=0;i<list.size();i++){
				 if(list.size()==1 || i==(list.size()-1)){
					 filter += "'"+list.get(i)+"'";
				 }else{
					 filter += "'"+list.get(i)+"',";
				 }
			 }
			sql.append(" and res.proj_status_id in("+filter+")");
		}
		//分包方单位人员登录时，只可看自己相关项目
		 BusinessPartners bp =  businessPartnersDao.get(loginInfo.getDeptId());
		 if(bp!=null){
			 if(UnitTypeEnum.SUPERVISION_UNIT.getValue().equals(bp.getUnitType())){
				//通过分包单位人员id查询
				 sql.append(" and res.su_id='").append(loginInfo.getDeptId()).append("'");
			 }
		 }
		
		 if(StringUtils.isNotBlank(paymentApplyReq.getProjNo())){
			 sql.append(" and res.proj_no like '%").append(paymentApplyReq.getProjNo()).append("%'");
		 }
		 if(StringUtils.isNotBlank(paymentApplyReq.getProjName())){
			 sql.append(" and res.proj_name like '%").append(paymentApplyReq.getProjName()).append("%'");
		 }
		  //分公司过滤工程--开始
		 String corpId = "";
			if(loginInfo.getBelongCorpId() != null){
				corpId = loginInfo.getBelongCorpId();
			}else{  
				corpId = loginInfo.getDeptId();
			}
			 List<DataFilerSetUpDto> list2 = Constants.getDataFilterMapByKey(corpId+"_"+paymentApplyReq.getMenuId());  //按照条件过滤工程
			 if(list2!=null && list2.size()>0){
				 if(StringUtils.isNotBlank(list2.get(0).getSupSql())){
					String[] sup_sql = list2.get(0).getSupSql().split("and");
					 sql.append(" and res.").append(sup_sql[1]);
				 }
			 }
			  //分公司过滤工程--结束
		 //Map<String, Object> map= super.findObjectBySql(sql.toString());
		 List<Map<String, Object>> mapList = this.findListBySql(sql.toString());
		 List<Object> paramList = new ArrayList<Object>();
		 //int filterCount = this.getCountBySql(sql.toString(), paramList.toArray());
		 
		 List<ConstructionPlan> planList = new ArrayList<ConstructionPlan>();
		// 组装对象，将获得的map值set到对象中
		if (mapList != null && mapList.size() > 0) {
			for(Map<String, Object> map : mapList){
				ConstructionPlan plan = new ConstructionPlan();
				plan.setProjId((String)map.get("PROJ_ID"));
				plan.setProjNo((String)map.get("PROJ_NO"));
				plan.setProjName((String)map.get("PROJ_NAME"));
				plan.setProjAddr((String)map.get("PROJ_ADDR"));
				plan.setProjScaleDes((String)map.get("PROJ_SCALE_DES"));
				plan.setEndSettlementCost(map.get("END_DECLARATION_COST")!=null?map.get("END_DECLARATION_COST").toString():"");
				plan.setEndAmount("0");
				//用户款项未清增加标记
				List<String> signTypes = new ArrayList<String>();
				signTypes.add(ProjectSignTypeEnum.SPECIAL.getValue());
				signTypes.add(ProjectSignTypeEnum.UNPAID.getValue());
				signTypes.add(ProjectSignTypeEnum.INCOMPLETE_COST.getValue());
				signTypes.add(ProjectSignTypeEnum.NO_SUP_MONEY.getValue());
				List<ProjectSign> projectSignList=projectSignDao.findByProjIdAndStatus(plan.getProjId(),IsSignEnum.IS_SIGN.getValue(),signTypes);
				if(projectSignList!=null && projectSignList.size()>0){
					plan.setIsSpecialSign(IsSignEnum.IS_SIGN.getValue());//是特殊工程
				}
				planList.add(plan);
			}
		}
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("data", planList);
		resultMap.put("recordsFiltered", planList.size());
		resultMap.put("recordsTotal",planList.size());
		// 返回结果
		return resultMap;
		 
	}

	@Override
	public boolean isExist(String projId) {
		int countBySql = this.getCountBySql("SELECT COUNT(CP_ID) FROM construction_plan  WHERE PROJ_ID=? ", new String[]{projId});
		if (countBySql>0){
			return true;
		}
		return false;
	}
}
