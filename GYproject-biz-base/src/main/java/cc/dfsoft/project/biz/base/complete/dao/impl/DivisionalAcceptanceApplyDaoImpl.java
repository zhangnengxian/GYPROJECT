package cc.dfsoft.project.biz.base.complete.dao.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.fr.report.core.A.c;

import cc.dfsoft.project.biz.base.baseinfo.dao.BusinessPartnersDao;
import cc.dfsoft.project.biz.base.baseinfo.entity.BusinessPartners;
import cc.dfsoft.project.biz.base.complete.dao.DivisionalAcceptanceApplyDao;
import cc.dfsoft.project.biz.base.complete.dto.DivisionalAcceptanceReq;
import cc.dfsoft.project.biz.base.complete.entity.DivisionalAcceptanceApply;
import cc.dfsoft.project.biz.base.design.enums.UnitTypeEnum;
import cc.dfsoft.project.biz.base.project.dao.ProjectDao;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dao.DepartmentDao;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.biz.sys.dept.entity.Department;
import cc.dfsoft.uexpress.biz.sys.dept.enums.DeptDivideEnum;
import cc.dfsoft.uexpress.biz.sys.dept.enums.DeptTypeEnum;
import cc.dfsoft.uexpress.biz.sys.dept.enums.PostTypeEnum;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;
import cc.dfsoft.uexpress.common.util.ResultUtil;
import cc.dfsoft.uexpress.common.util.StringUtil;
@Repository
public class DivisionalAcceptanceApplyDaoImpl extends NewBaseDAO<DivisionalAcceptanceApply, String> implements DivisionalAcceptanceApplyDao{
	
	/**分部验收*/
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
	public Map<String, Object> queryDivisionalAcceptanceApply(DivisionalAcceptanceReq req) throws ParseException {
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
		if(StringUtils.isNotBlank(req.getProjName())){
			c.add(Restrictions.like("projName","%"+req.getProjName()+"%"));
		}
		
		if(StringUtils.isNotBlank(req.getProjAddr())){
			c.add(Restrictions.like("projAddr","%"+req.getProjAddr()+"%"));
		} 
		
		//是否打印
		if(StringUtils.isNotBlank(req.getIsPrint())){
			c.add(Restrictions.eq("isPrint", req.getIsPrint()));
		}
		//根据审核状态查询工程
		if(StringUtils.isNotBlank(req.getAuditState())){
			StringBuffer sql = new StringBuffer();
			sql.append("daa_id in ( select daa_id from divisional_acceptance_apply where AUDIT_STATE = ").append(req.getAuditState()).append(")");
			c.add(Restrictions.sqlRestriction(sql.toString()));
		}
		
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		 //申请日期开始日期
		if(StringUtils.isNotBlank(req.getApplyDateStart())){
			 c.add(Restrictions.ge("applyDate", sdf.parse(req.getApplyDateStart())));
		}
		 //申请日期结束日期
		if(StringUtils.isNotBlank(req.getApplyDateEnd())){
			 c.add(Restrictions.le("applyDate", sdf.parse(req.getApplyDateEnd())));
		}
		
		//是否完成分部验收
		if(StringUtils.isNotBlank(req.getAcceptanceState())){
			 c.add(Restrictions.eq("acceptanceState",req.getAcceptanceState()));
		}
		 LoginInfo loginInfo=SessionUtil.getLoginInfo();
		 String post=loginInfo.getPost();
		 String [] postArray=post.split(",");
		//分包方单位人员登录时，只可看自己相关项目
		 BusinessPartners bp =  businessPartnersDao.get(loginInfo.getDeptId());
		 Department dept=new Department();
		 List<Object> deptIdArray=new ArrayList<>();
		 
		 if(bp!=null){
			//若登录人是分包方单位人员
			 if(UnitTypeEnum.CONSTRUCTION_UNIT.getValue().equals(bp.getUnitType())&& post.length()>0){
				 StringBuffer sql = projectDao.cuUnitFilter(loginInfo,post, req.getMenuId());
				 c.add(Restrictions.sqlRestriction(sql.toString()));
			 }else if(UnitTypeEnum.SUPERVISION_UNIT.getValue().equals(bp.getUnitType())){
				//若登录人是监理单位
				 StringBuffer sql = projectDao.suUnitFilter(loginInfo, post, req.getMenuId());
				 c.add(Restrictions.sqlRestriction(sql.toString()));
			 }else{
				 //其他业务合作伙伴-如检测单位
			 }
		  }
		 
		 if(!DeptDivideEnum.DESIGN_INSTITUTE.getValue().equals(loginInfo.getDeptDivide()) && bp==null){
			 //所属公司id
			 if(StringUtils.isNotBlank(loginInfo.getCorpId())){
				 c.add(Restrictions.like("corpId",loginInfo.getCorpId()+"%"));
			 }
			 
			//综合组或技术组
			 if(DeptDivideEnum.COMPREHENSIVE_GROUP.getValue().equals(loginInfo.getDeptDivide())|| DeptDivideEnum.TECHNOLOGY_GROUP.getValue().equals(loginInfo.getDeptDivide())
					 || DeptDivideEnum.PRE_SETTLEMENT_GROUP.getValue().equals(loginInfo.getDeptDivide())|| DeptDivideEnum.QUALITY_SAFETY_GROUP.getValue().equals(loginInfo.getDeptDivide())){
				 String deptId = loginInfo.getDeptId().substring(0, loginInfo.getDeptId().length() - DeptTypeEnum.SUBCOMPANY.getInitVal().length());
				 StringBuffer sql = new StringBuffer("proj_id in(select cp.proj_id from project cp where cp.dept_id  like'%").append(deptId).append("%')");
				 c.add(Restrictions.sqlRestriction(sql.toString())); 
			 }else if(DeptDivideEnum.CUSTOMER_SERVICE_CENTER.getValue().equals(loginInfo.getDeptDivide())){
				
				 if(StringUtil.isNotBlank(loginInfo.getPost()) && (loginInfo.getPost().contains(PostTypeEnum.RECORDER.getValue())|| loginInfo.getPost().contains(PostTypeEnum.CHIEF_ENGINEER.getValue()))){
					//副主任或主任
					 String deptId =loginInfo.getDeptId();
					 StringBuffer sql = new StringBuffer("proj_id in(select cp.proj_id from project cp where cp.dept_id  like'%").append(deptId).append("%')");
					 c.add(Restrictions.sqlRestriction(sql.toString())); 
				 }else{
					//客服中心
					 String deptId = loginInfo.getDeptId().substring(0, loginInfo.getDeptId().length() - DeptTypeEnum.SUBCOMPANY.getInitVal().length());
					 StringBuffer sql = new StringBuffer("proj_id in(select cp.proj_id from project cp where cp.dept_id  like'%").append(deptId).append("%')");
					 c.add(Restrictions.sqlRestriction(sql.toString())); 
				 }
				 
			 }else{
				 if(DeptDivideEnum.CIVIL_GROUP.getValue().equals(loginInfo.getDeptDivide())||DeptDivideEnum.MARKETING_CENTER.getValue().equals(loginInfo.getDeptDivide())||
						 DeptDivideEnum.TRUNK_GROUP .getValue().equals(loginInfo.getDeptDivide())||DeptDivideEnum.MODIFICATION_GROUP.getValue().equals(loginInfo.getDeptDivide())){
					 //工程部门 民用 公建 改管 干线 
					 StringBuffer sql = new StringBuffer("proj_id in(select cp.proj_id from project cp where cp.dept_id  like'").append(loginInfo.getDeptId()).append("%')");
					 c.add(Restrictions.sqlRestriction(sql.toString())); 
				 }else{
					 String deptId = loginInfo.getDeptId().substring(0, loginInfo.getDeptId().length() - DeptTypeEnum.SUBCOMPANY.getInitVal().length());
					 StringBuffer sql = new StringBuffer("proj_id in(select cp.proj_id from project cp where cp.dept_id  like'%").append(deptId).append("%')");
					 c.add(Restrictions.sqlRestriction(sql.toString())); 
				 }	 
			 }
			 
			 //现场代表
			 if(post.contains(PostTypeEnum.BUILDER.getValue())){
				 if(!post.contains(PostTypeEnum.MARKETING_CENTER_LEADER_DEPUTY.getValue())
						 && !post.contains(PostTypeEnum.MODIFICATION_GROUP_LEADER.getValue())
						 && !post.contains(PostTypeEnum.TRUNK_GROUP_LEADER.getValue())
						 && !post.contains(PostTypeEnum.DEPUTY_LEADER.getValue())){
					 StringBuffer sql = new StringBuffer("proj_id in(select cp.proj_id from construction_plan cp where cp.BUILDER_ID='").append(loginInfo.getStaffId()).append("')");
					 c.add(Restrictions.sqlRestriction(sql.toString())); 
				 }
			 }
			 
		 }
		 
		 //设计员
		 if(post.contains(PostTypeEnum.DESIGNER.getValue())){
			 StringBuffer sql = new StringBuffer("proj_id in(select cp.proj_id from project cp where cp.designer_id='").append(loginInfo.getStaffId()).append("')");
			 c.add(Restrictions.sqlRestriction(sql.toString())); 
		 }
		 
		 
		 
		 if(postArray.length>0){
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
				 }
			 }
		 }
		 
		 
		 
		 
		
		int filterCount = this.countByCriteria(c);
		
		// 根据条件获取查询信息
		List<DivisionalAcceptanceApply> list = this.findBySortCriteria(c, req);
		
		return ResultUtil.pageResult(filterCount, req.getDraw(), list);
		
	}

	@Override
	public Integer countDivisonalAcceptanceRecord(String projId) throws Exception {
		// TODO Auto-generated method stub
		Criteria criteria = super.getCriteria();
		
		// 工程ID是否为空
		if (StringUtils.isNotBlank(projId)) {
			criteria.add(Restrictions.eq("projId", projId));
		}
		// 统计记录数
		int count = this.countByCriteria(criteria);
		return count;
	}
}
