package cc.dfsoft.project.biz.base.complete.dao.impl;

import cc.dfsoft.project.biz.base.baseinfo.dao.BusinessPartnersDao;
import cc.dfsoft.project.biz.base.baseinfo.entity.BusinessPartners;
import cc.dfsoft.project.biz.base.complete.dao.DivisionalAcceptanceDao;
import cc.dfsoft.project.biz.base.complete.dto.DivisionalAcceptanceReq;
import cc.dfsoft.project.biz.base.complete.entity.DivisionalAcceptance;
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
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;
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
import java.util.Arrays;
import java.util.List;
import java.util.Map;
@Repository
public class DivisionalAcceptanceDaoImpl extends NewBaseDAO<DivisionalAcceptance, String> implements DivisionalAcceptanceDao{
	
	@Resource
	BusinessPartnersDao businessPartnersDao;
	@Resource
	DepartmentDao departmentDao;
	@Resource
	ProjectDao projectDao;
	/**
	 * 查询分部验收申请列表
	 * @author fuliwei
	 * @createTime 2017年8月9日
	 * @param 
	 * @return
	 */
	@Override
	public Map<String, Object> queryDivisionalAcceptance(DivisionalAcceptanceReq req) throws ParseException {
		Criteria c=super.getCriteria();
		
		if(StringUtils.isNotBlank(req.getProjId())){
			c.add(Restrictions.eq("projId",req.getProjId()));
		}
		//工程编号
		if(StringUtils.isNotBlank(req.getProjNo())){
			c.add(Restrictions.eq("projNo",req.getProjNo()));
		}
		
		//合同编号
		if(StringUtils.isNotBlank(req.getConNo())){
			c.add(Restrictions.like("conNo","%"+req.getConNo()+"%"));
		}
		
		//协议编号
		if(StringUtils.isNotBlank(req.getScNo())){
			c.add(Restrictions.like("scNo","%"+req.getScNo()+"%"));
		}
		
		if(StringUtils.isNotBlank(req.getProjName())){
			c.add(Restrictions.like("projName","%"+req.getProjName()+"%"));
		}
		
		if(StringUtils.isNotBlank(req.getProjAddr())){
			c.add(Restrictions.like("projAddr","%"+req.getProjAddr()+"%"));
		}
		
		//是否打印
		if(StringUtils.isNotBlank(req.getIsPrint())){
			c.add(Restrictions.eq("isPrint",req.getIsPrint()));
		}
		
		//审核状态
		if(StringUtils.isNotBlank(req.getAuditState())){
			c.add(Restrictions.eq("auditState", req.getAuditState()));
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		 //验收日期开始日期
		if(StringUtils.isNotBlank(req.getAcceptanceDateStart())){
			 c.add(Restrictions.ge("acceptanceDate", sdf.parse(req.getAcceptanceDateStart())));
		}
		 //验收日期结束日期
		if(StringUtils.isNotBlank(req.getAcceptanceDateEnd())){
			 c.add(Restrictions.le("acceptanceDate", sdf.parse(req.getAcceptanceDateEnd())));
		}
		
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		String post=loginInfo.getPost();
		String [] postArray=post.split(",");
		Department dept=new Department();
		 List<Object> deptIdArray=new ArrayList<>();
       if(postArray.length>0){
				 if(post.contains(PostTypeEnum.CIVIL_GROUP_LEADER.getValue())||post.contains(PostTypeEnum.MARKETING_CENTER_LEADER.getValue())||
						 post.contains(PostTypeEnum.MARKETING_CENTER_LEADER_DEPUTY.getValue())||post.contains(PostTypeEnum.DEPUTY_LEADER.getValue())||
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
				 List<Project> projects = super.findByHql(hql.toString());
				 List<String> projIds = new ArrayList();
				 if(projects!=null&&projects.size()>0){
					 for(Project project:projects){
						 projIds.add(project.getProjId());
					 }
					 c.add(Restrictions.in("projId",projIds));
				 }else{
					 return null; 
				 }
			 }
		 }
		 
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
			 StringBuffer sql = new StringBuffer("proj_id in(select cp.proj_id from project cp where cp.corp_id like '").append(loginInfo.getCorpId()).append("%')");
			 c.add(Restrictions.sqlRestriction(sql.toString()));
		 }
      
		 if(post.contains(PostTypeEnum.BUILDER.getValue())){
	       	if(!post.contains(PostTypeEnum.MARKETING_CENTER_LEADER_DEPUTY.getValue())
						 && !post.contains(PostTypeEnum.MODIFICATION_GROUP_LEADER.getValue())
						 && !post.contains(PostTypeEnum.TRUNK_GROUP_LEADER.getValue())
						 && !post.contains(PostTypeEnum.DEPUTY_LEADER.getValue())){
	       		//现场代表查自己工程
	               StringBuffer sql = new StringBuffer("proj_id in(select cp.proj_id from construction_plan cp where cp.BUILDER_ID='").append(loginInfo.getStaffId()).append("')");
	               c.add(Restrictions.sqlRestriction(sql.toString()));
	       	}
      }

		//业务合作伙伴看自己的
		if(StringUtils.isNotBlank(loginInfo.getDeptId())){
			BusinessPartners bp = businessPartnersDao.get(loginInfo.getDeptId());
			 if(bp!=null){
				//若登录人是分包方单位人员
				 if(UnitTypeEnum.CONSTRUCTION_UNIT.getValue().equals(bp.getUnitType())&& loginInfo.getPost().length()>0){
					 StringBuffer sql = projectDao.cuUnitFilter(loginInfo,loginInfo.getPost(), req.getMenuId());
					 c.add(Restrictions.sqlRestriction(sql.toString()));
				 }else if(UnitTypeEnum.SUPERVISION_UNIT.getValue().equals(bp.getUnitType())){
					//若登录人是监理单位
					 StringBuffer sql = projectDao.suUnitFilter(loginInfo, loginInfo.getPost(), req.getMenuId());
					 c.add(Restrictions.sqlRestriction(sql.toString()));
				 }else{
					 //其他业务合作伙伴-如检测单位
				 }
			 }else{
				//分公司ID
				 StringBuffer sql = new StringBuffer("proj_id in(select cp.proj_id from project cp where cp.corp_id like '").append(loginInfo.getCorpId()).append("%')");
				 c.add(Restrictions.sqlRestriction(sql.toString())); 
			 }
		}
		
		//是否验收
		if(StringUtils.isNotBlank(req.getAcceptanceState())){
			c.add(Restrictions.eq("acceptanceState",req.getAcceptanceState()));
		}
				
		
		int filterCount = this.countByCriteria(c);
		
		// 根据条件获取查询信息
		List<DivisionalAcceptance> list = this.findBySortCriteria(c, req);
		
		return ResultUtil.pageResult(filterCount, req.getDraw(), list);
		
	}
	
	/**
	 * 查询详述
	 * @author fuliwei
	 * @createTime 2017年8月10日
	 * @param 
	 * @return
	 */
	@Override
	public DivisionalAcceptance viewByDaaId(String daaId) {
		Criteria c=super.getCriteria();
		if(StringUtils.isNotBlank(daaId)){
			c.add(Restrictions.eq("daaId",daaId));
			List<DivisionalAcceptance>  list=this.findByCriteria(c);
			if(list!=null && list.size()>0){
				return list.get(0);
			}
		}
		return null;
	}
	
	@Override
	public List<DivisionalAcceptance> findByprojectId(String projId) {
		Criteria c=super.getCriteria();
		
		if(StringUtils.isNotBlank(projId)){
			c.add(Restrictions.eq("projId",projId));
		}
		return this.findByCriteria(c);
	}

}
