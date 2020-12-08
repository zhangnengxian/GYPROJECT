package cc.dfsoft.project.biz.base.settlement.dao.Impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.settlement.dao.TurnFixedApplyDao;
import cc.dfsoft.project.biz.base.settlement.dto.TurnFixedApplyReq;
import cc.dfsoft.project.biz.base.settlement.entity.TurnFixedApply;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dao.DepartmentDao;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.biz.sys.dept.entity.Department;
import cc.dfsoft.uexpress.biz.sys.dept.enums.DeptDivideEnum;
import cc.dfsoft.uexpress.biz.sys.dept.enums.PostTypeEnum;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;
import cc.dfsoft.uexpress.common.util.ResultUtil;

@Repository
public class TurnFixedApplyDaoImpl extends NewBaseDAO<TurnFixedApply, String> implements TurnFixedApplyDao{
	
	/**部门*/
	@Resource
	DepartmentDao departmentDao;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<TurnFixedApply> findByProjId(String projId) {
		 Criteria c = super.getCriteria();
		 //工程编号
		 if(StringUtils.isNotBlank(projId)){
			 c.add(Restrictions.eq("projId",projId));
		 }
		// 根据条件获取查询信息
		List<TurnFixedApply> turnFixedApplyList = c.list();
		return turnFixedApplyList;
	}

	@Override
	public Map<String, Object> queryTurnFixedApply(TurnFixedApplyReq turnFixedApplyReq) throws ParseException {
		 Criteria c = super.getCriteria();
		 //工程编号
		 if(StringUtils.isNotBlank(turnFixedApplyReq.getProjNo())){
			 c.add(Restrictions.eq("projNo",turnFixedApplyReq.getProjNo()));
		 }
		//工程状态
		 if(StringUtils.isNotBlank(turnFixedApplyReq.getProjStatus())){
			 StringBuffer hql = new StringBuffer("from Project where projStatusId = '").append(turnFixedApplyReq.getProjStatus()).append("'");
			 List<Project> projects = super.findByHql(hql.toString());
			 List<String> projIds = new ArrayList();
			 if(projects.size()>0){
				 for(Project project:projects){
					 projIds.add(project.getProjId());
				 }
				 c.add(Restrictions.in("projId",projIds));
			 }else{
				// 返回结果
				 return ResultUtil.pageResult(0, turnFixedApplyReq.getDraw(),new ArrayList());
			 }
		 }
		 //工程名称
		 if(StringUtils.isNotBlank(turnFixedApplyReq.getProjName())){
			 c.add(Restrictions.like("projName","%"+turnFixedApplyReq.getProjName()+"%"));
		 }
		 //施工单位
		 if(StringUtils.isNotBlank(turnFixedApplyReq.getCuName())){
			 c.add(Restrictions.like("cuName","%"+turnFixedApplyReq.getCuName()+"%"));
		 }
		 //用于字符串日期格式转换
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		 //转固开始日期
		 if(StringUtils.isNotBlank(turnFixedApplyReq.getApplyDateStart())){
			 c.add(Restrictions.ge("tfaDate", sdf.parse(turnFixedApplyReq.getApplyDateStart())));
		 }
		 //转固结束日期
		 if(StringUtils.isNotBlank(turnFixedApplyReq.getApplyDateEnd())){
			 c.add(Restrictions.le("tfaDate", sdf.parse(turnFixedApplyReq.getApplyDateEnd())));
		 }
		 LoginInfo loginInfo = SessionUtil.getLoginInfo();
		 //分公司Id
		 if(StringUtils.isNotBlank(loginInfo.getCorpId())){
			 c.add(Restrictions.like("corpId",loginInfo.getCorpId()+"%"));
		 }
		 
		 
		 
		 Department dept=new Department();
		 
		 String post=loginInfo.getPost();
		 String [] postArray=post.split(",");
		 
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
				
			 if(deptIdArray!=null && deptIdArray.size()>0){
				 StringBuffer hql = new StringBuffer("from Project where deptId in ('");
				 for(int i=1;i<deptIdArray.size();i++){
					 hql.append(deptIdArray.get(i)).append("',").append("'");
				 }
				 hql.append(deptIdArray.get(0)).append("')");
				 hql.append(" and projStatusId='").append(turnFixedApplyReq.getProjStatus()).append("'");
				 List<Project> projects = super.findByHql(hql.toString());
				 List<String> projIds = new ArrayList();
				 if(projects!=null&&projects.size()>0){
					 for(Project project:projects){
						 projIds.add(project.getProjId());
					 }
					 c.add(Restrictions.in("projId",projIds));
				 }else{
					// 返回结果
					 return ResultUtil.pageResult( 0, turnFixedApplyReq.getDraw(),new ArrayList()); 
				 }
			 }
			 
		 }
		 
		 
		// 数据库根据条件过滤记录数
		int filterCount = this.countByCriteria(c);
		// 根据条件获取查询信息
		List<TurnFixedApply> surveyInfoList = this.findBySortCriteria(c, turnFixedApplyReq);
		// 返回结果
		return ResultUtil.pageResult(filterCount, turnFixedApplyReq.getDraw(),surveyInfoList);
	}

	@Override
	public Map<String, Object> queryTurnFixedApplyPrint(TurnFixedApplyReq turnFixedApplyReq) throws ParseException {
		Criteria c = super.getCriteria();
		 //工程编号 
		 if(StringUtils.isNotBlank(turnFixedApplyReq.getProjNo())){
			 c.add(Restrictions.eq("projNo",turnFixedApplyReq.getProjNo()));
		 }
		 //工程名称
		 if(StringUtils.isNotBlank(turnFixedApplyReq.getProjName())){
			 c.add(Restrictions.like("projName","%"+turnFixedApplyReq.getProjName()+"%"));
		 }
		 //施工单位
		 if(StringUtils.isNotBlank(turnFixedApplyReq.getCuName())){
			 c.add(Restrictions.like("cuName","%"+turnFixedApplyReq.getCuName()+"%"));
		 }
		 //工程id
		 if(StringUtils.isNotBlank(turnFixedApplyReq.getProjId())){
			 c.add(Restrictions.eq("projId",turnFixedApplyReq.getProjId()));
		 }
		 //用于字符串日期格式转换
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		 //转固日期开始
		 if(StringUtils.isNotBlank(turnFixedApplyReq.getApplyDateStart())){
			 c.add(Restrictions.ge("applyDate", sdf.parse(turnFixedApplyReq.getApplyDateStart())));
		 }
		 //转固日期结束
		 if(StringUtils.isNotBlank(turnFixedApplyReq.getApplyDateEnd())){
			 c.add(Restrictions.le("applyDate", sdf.parse(turnFixedApplyReq.getApplyDateEnd())));
		 }
		 //是否打印
		 if(StringUtils.isNotBlank(turnFixedApplyReq.getIsPrint())){
			 c.add(Restrictions.eq("isPrint", turnFixedApplyReq.getIsPrint()));
		 }
		 // 数据库根据条件过滤记录数
		 int filterCount = this.countByCriteria(c);

		 // 根据条件获取查询信息 
		 List<TurnFixedApply> SettlementDeclarationList = this.findBySortCriteria(c, turnFixedApplyReq);
		 // 返回结果
		 return ResultUtil.pageResult( filterCount, turnFixedApplyReq.getDraw(),SettlementDeclarationList);
	}

}
