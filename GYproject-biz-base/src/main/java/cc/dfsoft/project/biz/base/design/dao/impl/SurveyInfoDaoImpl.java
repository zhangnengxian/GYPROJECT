package cc.dfsoft.project.biz.base.design.dao.impl;

import cc.dfsoft.project.biz.base.baseinfo.dao.BusinessPartnersDao;
import cc.dfsoft.project.biz.base.baseinfo.entity.BusinessPartners;
import cc.dfsoft.project.biz.base.contract.enums.ContractIsPrintEnum;
import cc.dfsoft.project.biz.base.design.dao.SurveyInfoDao;
import cc.dfsoft.project.biz.base.design.dto.SurveyInfoQueryReq;
import cc.dfsoft.project.biz.base.design.entity.SurveyInfo;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.project.enums.ProjStatusEnum;
import cc.dfsoft.project.biz.base.project.service.ProjectService;
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
import java.util.List;
import java.util.Map;

@Repository
public class SurveyInfoDaoImpl extends NewBaseDAO<SurveyInfo, String> implements SurveyInfoDao {
	
	/**部门Dao*/
	@Resource
	DepartmentDao departmentDao;
	
	@Resource
	BusinessPartnersDao businessPartnersDao;
	@Resource
	ProjectService projectService;
	
	@Override
	public Map<String, Object> querySurveyInfo(SurveyInfoQueryReq surveyInfoQueryReq) throws ParseException {
		
		 Criteria c = super.getCriteria();
		 
		 LoginInfo loginInfo = SessionUtil.getLoginInfo();
		 //分公司数据过滤 如安顺只看到
		 List<DataFilerSetUpDto> list = Constants.getDataFilterMapByKey(loginInfo.getDeptId()+"_"+surveyInfoQueryReq.getMenuId());
		 if(list!=null && list.size()>0){
			 StringBuffer hql = new StringBuffer();
			 hql.append("proj_id in (select proj_id from project where 1=1 ");
			 
			 if(StringUtils.isNotBlank(list.get(0).getSupSql())){
				 hql.append(list.get(0).getSupSql());
			 }
			 hql.append(")");
			 c.add(Restrictions.sqlRestriction(hql.toString()));
		 }
		 
		 
		 //工程编号
		 if(StringUtils.isNotBlank(surveyInfoQueryReq.getProjNo())){
			 c.add(Restrictions.eq("projNo",surveyInfoQueryReq.getProjNo()));
		 }
		//工程状态
		 if(StringUtils.isNotBlank(surveyInfoQueryReq.getProjStatus())){
			 StringBuffer hql = new StringBuffer("from Project where projStatusId = '").append(surveyInfoQueryReq.getProjStatus()).append("'");
			 List<Project> projects = super.findByHql(hql.toString());
			 List<String> projIds = new ArrayList();
			 if(projects.size()>0){
				 for(Project project:projects){
					 projIds.add(project.getProjId());
				 }
				 c.add(Restrictions.in("projId",projIds));
			 }else{
				// 返回结果
				 return ResultUtil.pageResult(0, surveyInfoQueryReq.getDraw(),new ArrayList());
			 }
		 }
		 
		
		 
		 Department dept=new Department();
		 List<Object> deptIdArray=new ArrayList<>();
		 
		 
		 BusinessPartners bp =  businessPartnersDao.get(loginInfo.getDeptId());
		
		 String post=loginInfo.getPost();
		 String [] postArray=post.split(",");
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
			
				 //设计员-踏勘审核可以见全部
				 if(post.contains(PostTypeEnum.DESIGNER.getValue()) && !ProjStatusEnum.TO_APPROVAL.getValue().equals(surveyInfoQueryReq.getProjStatus())){
					 c.add(Restrictions.eq("designerId", loginInfo.getStaffId()));
				 }
		
			 if(deptIdArray!=null && deptIdArray.size()>0){
				 StringBuffer hql = new StringBuffer("from Project where deptId in ('");
				 for(int i=1;i<deptIdArray.size();i++){
					 hql.append(deptIdArray.get(i)).append("',").append("'");
				 }
				 hql.append(deptIdArray.get(0)).append("')");
				 hql.append(" and projStatusId='").append(surveyInfoQueryReq.getProjStatus()).append("'");
				 List<Project> projects = super.findByHql(hql.toString());
				 List<String> projIds = new ArrayList();
				 if(projects!=null&&projects.size()>0){
					 for(Project project:projects){
						 projIds.add(project.getProjId());
					 }
					 c.add(Restrictions.in("projId",projIds));
				 }
			 }
		 }
		 
		 //非设计院
		 if(!DeptDivideEnum.DESIGN_INSTITUTE.getValue().equals(loginInfo.getDeptDivide())){
			//所属公司id
			 if(StringUtils.isNotBlank(loginInfo.getCorpId())){
				 StringBuffer sql = new StringBuffer();
				 sql.append("proj_id in(select p.proj_id from project p where p.corp_id  like '").append(loginInfo.getCorpId()).append("%')");
				 c.add(Restrictions.sqlRestriction(sql.toString()));
			 }
			
			 if(BusinessTypeEnum.BUSINESS_GROUP.getValue().equals(loginInfo.getBusinessType())){//业务部门
				 String deptId = loginInfo.getDeptId().substring(0, loginInfo.getDeptId().length() - DeptTypeEnum.SUBCOMPANY.getInitVal().length());
				 c.add(Restrictions.sqlRestriction(projectService.addDeptIdLikeQuery(deptId)));//部门条件查询

			 }else if(DeptDivideEnum.CUSTOMER_SERVICE_CENTER.getValue().equals(loginInfo.getDeptDivide())){
				 if(StringUtil.isNotBlank(loginInfo.getPost()) && (loginInfo.getPost().contains(PostTypeEnum.RECORDER.getValue())|| loginInfo.getPost().contains(PostTypeEnum.CHIEF_ENGINEER.getValue()))){
					 c.add(Restrictions.sqlRestriction(projectService.addDeptIdLikeQuery(loginInfo.getDeptId())));//部门条件查询

				 }else{//客服中心
					 String deptId = loginInfo.getDeptId().substring(0, loginInfo.getDeptId().length() - DeptTypeEnum.SUBCOMPANY.getInitVal().length());
					 c.add(Restrictions.sqlRestriction(projectService.addDeptIdLikeQuery(deptId)));//部门条件查询
				 }
				
			 }else{	 
				 //工程部门
				 if(list!=null && list.size()>0){
					 
				 }else{
					 StringBuffer sql= new StringBuffer(projectService.addDeptIdLikeQuery(loginInfo.getDeptId()));
					 c.add(Restrictions.sqlRestriction(sql.toString()));
				 }
			 }
			 
		 }else{
			 if(list!=null && list.size()>0){
				 
			 }else{
				 StringBuffer sql = new StringBuffer();
				 sql.append("proj_id in(select p.proj_id from project p where p.dept_id not like'").append(surveyInfoQueryReq.getDeptId()).append("%')");
				 c.add(Restrictions.sqlRestriction(sql.toString()));
			 }
		 }
		 
		 
		 //工程名称
		 if(StringUtils.isNotBlank(surveyInfoQueryReq.getProjName())){
			 c.add(Restrictions.like("projName","%"+surveyInfoQueryReq.getProjName()+"%"));
		 }
		 //用于字符串日期格式转换
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		 //勘察开始日期
		 if(StringUtils.isNotBlank(surveyInfoQueryReq.getSurveyDateStart())){
			 c.add(Restrictions.ge("surveyDate", sdf.parse(surveyInfoQueryReq.getSurveyDateStart())));
		 }
		 //勘察结束日期
		 if(StringUtils.isNotBlank(surveyInfoQueryReq.getSurveyDateEnd())){
			 c.add(Restrictions.le("surveyDate", sdf.parse(surveyInfoQueryReq.getSurveyDateEnd())));
		 }
		 //勘察人
		 if(StringUtils.isNotBlank(surveyInfoQueryReq.getSurveyer())){
			 c.add(Restrictions.like("surveyer", "%"+surveyInfoQueryReq.getSurveyer()+"%"));
		 }

		 c.addOrder(Order.desc("surveyDate"));
		// 数据库根据条件过滤记录数
		int filterCount = this.countByCriteria(c);

		// 根据条件获取查询信息
		List<SurveyInfo> surveyInfoList = this.findBySortCriteria(c, surveyInfoQueryReq);
		
		// 返回结果
		return ResultUtil.pageResult(filterCount, surveyInfoQueryReq.getDraw(),surveyInfoList);
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<SurveyInfo> findByConnectGasNo(String connectGasNo) {
		StringBuffer hql = new StringBuffer();
		hql.append("from SurveyInfo where connectGasNo = '").append(connectGasNo).append("'");
		return super.findByHql(hql.toString());
	}

	@Override
	public List<SurveyInfo> findByProjId(String projId) {
		 Criteria c = super.getCriteria();
		 //工程编号
		 if(StringUtils.isNotBlank(projId)){
			 c.add(Restrictions.eq("projId",projId));
		 }
		// 根据条件获取查询信息
		List<SurveyInfo> surveyInfoList = this.findByCriteria(c);
		return surveyInfoList;
	}

	@Override
	public Map<String, Object> getSurveyInfoList(SurveyInfoQueryReq surveyInfoQueryReq) throws ParseException {
		//登录信息
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		Criteria c = super.getCriteria();
		
		//踏勘打印查询 按状态查询
		List<DataFilerSetUpDto> list = Constants.getDataFilterMapByKey(loginInfo.getCorpId()+"_"+surveyInfoQueryReq.getMenuId());
		 if(list!=null && list.size()>0){
			 StringBuffer hql = new StringBuffer();
			 hql.append("proj_id in (select proj_id from project where 1=1 and corp_id in('").append(loginInfo.getCorpId()).append("')");
			 
			 if(StringUtils.isNotBlank(list.get(0).getSupSql())){
				 hql.append(list.get(0).getSupSql());
			 }
			 hql.append(")");
			 c.add(Restrictions.sqlRestriction(hql.toString()));
		 }
		 
		 
		 //查看居民公建、改管
		 List<DataFilerSetUpDto> typeList = Constants.getDataFilterMapByKey(loginInfo.getDeptId()+"_"+surveyInfoQueryReq.getMenuId());
		 if(typeList!=null && typeList.size()>0){
			 StringBuffer hql = new StringBuffer();
			 hql.append("proj_id in (select proj_id from project where 1=1 ");
			 
			 if(StringUtils.isNotBlank(typeList.get(0).getSupSql())){
				 hql.append(typeList.get(0).getSupSql());
			 }
			 hql.append(")");
			 c.add(Restrictions.sqlRestriction(hql.toString()));
		 }
		 
		 
		 
		 
		c.add(Restrictions.eq("corpId",loginInfo.getCorpId()));
		//是否打印
		if(StringUtils.isNotBlank(surveyInfoQueryReq.getSigns())){
			c.add(Restrictions.eq("signs",surveyInfoQueryReq.getSigns()));
		}else{
			//为选择时，默认查询未打印项
			c.add(Restrictions.eq("signs",ContractIsPrintEnum.HAVE_NOT_PRINT.getValue()));
		}
		//工程编号
		if(StringUtils.isNotBlank(surveyInfoQueryReq.getProjNo())){
			c.add(Restrictions.eq("projNo",surveyInfoQueryReq.getProjNo()));
		}
		//工程名称
		if(StringUtils.isNotBlank(surveyInfoQueryReq.getProjName())){
			c.add(Restrictions.like("projName","%"+surveyInfoQueryReq.getProjName()+"%"));
		}
		//用于字符串日期格式转换
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		//勘察开始日期
		if(StringUtils.isNotBlank(surveyInfoQueryReq.getSurveyDateStart())){
			c.add(Restrictions.ge("surveyDate", sdf.parse(surveyInfoQueryReq.getSurveyDateStart())));
		}
		//勘察结束日期
		if(StringUtils.isNotBlank(surveyInfoQueryReq.getSurveyDateEnd())){
			c.add(Restrictions.le("surveyDate", sdf.parse(surveyInfoQueryReq.getSurveyDateEnd())));
		}
		//勘察人
		if(StringUtils.isNotBlank(surveyInfoQueryReq.getSurveyer())){
			c.add(Restrictions.like("surveyer", "%"+surveyInfoQueryReq.getSurveyer()+"%"));
		}
		
		String post = loginInfo.getPost();
		
		if(null!=post){
			 if(post.length()>0){
				 //踏勘员看自己的
				 if(post.contains(PostTypeEnum.SURVEYER.getValue())){
					 c.add(Restrictions.eq("surveyerId", loginInfo.getStaffId()));
				 }
				 
				 //设计员看自己的
				 if(post.contains(PostTypeEnum.DESIGNER.getValue())){
					 c.add(Restrictions.eq("designerId", loginInfo.getStaffId()));
				 }
			 }
		}
		
		
		c.addOrder(Order.desc("surveyDate"));
		// 数据库根据条件过滤记录数
		int filterCount = this.countByCriteria(c);

		// 根据条件获取查询信息
		List<SurveyInfo> surveyInfoList = this.findBySortCriteria(c, surveyInfoQueryReq);

		// 返回结果
		return ResultUtil.pageResult(filterCount, surveyInfoQueryReq.getDraw(),surveyInfoList);
	}

}
