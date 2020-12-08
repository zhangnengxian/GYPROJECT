package cc.dfsoft.project.biz.base.withgas.dao.impl;

import cc.dfsoft.project.biz.base.contract.enums.ContractSuIsPrintEnum;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.withgas.dao.GasProjectDao;
import cc.dfsoft.project.biz.base.withgas.dto.GasProjectReq;
import cc.dfsoft.project.biz.base.withgas.entity.GasProject;
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
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

/**
 * @author cui
 * @createTime 2017-8-8
 *
 */
@Repository
public class GasProjectDaoImpl extends NewBaseDAO<GasProject, String> implements GasProjectDao {
	
	/**部门Dao*/
	@Resource
	DepartmentDao departmentDao;
	
    @Override
	public Map<String, Object> queryGasProject(GasProjectReq gasProjectReq) throws ParseException {
        Criteria c = super.getCriteria();
        LoginInfo loginInfo = SessionUtil.getLoginInfo();
        String post=loginInfo.getPost();
        String [] postArray=post.split(",");
        /*if(DeptDivideEnum.CUSTOMER_SERVICE_CENTER.getValue().equals(loginInfo.getDeptDivide())) {
            //客服中心的查自己
            StringBuffer sql = new StringBuffer("proj_id in(select p.proj_id from project p where p.DEPT_ID like'").append(loginInfo.getDeptId()).append("%')");
            c.add(Restrictions.sqlRestriction(sql.toString()));
        }*/
        
        //计划id
        if(StringUtils.isNotBlank(gasProjectReq.getGpId())){
            c.add(Restrictions.eq("gpId",gasProjectReq.getGpId()));
        }
        //状态
        if(StringUtils.isNotBlank(gasProjectReq.getGasProjStatus())){
            c.add(Restrictions.eq("gasProjStatus",gasProjectReq.getGasProjStatus()));
        }
        
        //状态集合
        if(gasProjectReq.getGasStatusList()!=null && gasProjectReq.getGasStatusList().size()>0){
            c.add(Restrictions.in("gasProjStatus",gasProjectReq.getGasStatusList()));
        }
        
        //计划编号
        if(StringUtils.isNotBlank(gasProjectReq.getScNo())){
            c.add(Restrictions.like("scNo",gasProjectReq.getScNo()+"%"));
        }
        //工程名称
        if(StringUtils.isNotBlank(gasProjectReq.getProjName())){
            c.add(Restrictions.like("projName","%"+gasProjectReq.getProjName()+"%"));
        }
        //地点
        if(StringUtils.isNotBlank(gasProjectReq.getProjAddr())){
            c.add(Restrictions.like("projAddr","%"+gasProjectReq.getProjAddr()+"%"));
        }
        //
        if(StringUtils.isNotBlank(gasProjectReq.getProjNo())){
            c.add(Restrictions.like("projNo",gasProjectReq.getProjNo()+"%"));
        }

        //用于字符串日期格式转换
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //填报日期开始日期
        if(StringUtils.isNotBlank(gasProjectReq.getPreparDateStart())){
            c.add(Restrictions.ge("preparDate", sdf.parse(gasProjectReq.getPreparDateStart())));
        }
        //填报日期结束日期
        if(StringUtils.isNotBlank(gasProjectReq.getPreparDateEnd())){
            c.add(Restrictions.le("preparDate", sdf.parse(gasProjectReq.getPreparDateEnd())));
        }
        
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
					 return ResultUtil.pageResult( 0, gasProjectReq.getDraw(),new ArrayList()); 
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
		
		//是否完成签订
		if(StringUtils.isNotBlank(gasProjectReq.getIsSignIgnite())){
			c.add(Restrictions.eq("isSignIgnite",gasProjectReq.getIsSignIgnite()));
		}
		
		//是否打印
		if(StringUtils.isNotBlank(gasProjectReq.getIsprint())){
			c.add(Restrictions.eq("isPrint",gasProjectReq.getIsprint()));
		}

		//是否上传竣工确认单(1已上传  2未上传)
		if(StringUtils.isNotBlank(gasProjectReq.getIsUploadAccessory())){
		 	if(gasProjectReq.getIsUploadAccessory().equals("1")){
				StringBuffer sql = new StringBuffer("proj_id in(select al.proj_id from accessory_list al where al.STEP='").append(gasProjectReq.getMenuId()).append("')");
				c.add(Restrictions.sqlRestriction(sql.toString()));
			}
			if(gasProjectReq.getIsUploadAccessory().equals("0")){
				StringBuffer sql = new StringBuffer("proj_id not in(select al.proj_id from accessory_list al where al.STEP='").append(gasProjectReq.getMenuId()).append("')");
				c.add(Restrictions.sqlRestriction(sql.toString()));
			}

		}
		c.addOrder(Order.desc("acceptDate"));
        // 数据库根据条件过滤记录数
        int filterCount = this.countByCriteria(c);
        // 根据条件获取查询信息
        List<GasProject> gasProjects = this.findBySortCriteria(c, gasProjectReq);

        // 返回结果
        return ResultUtil.pageResult( filterCount, gasProjectReq.getDraw(),gasProjects);
    }

    @Override
    public void deleteByGpId(String gpId) {
        StringBuffer hql = new StringBuffer("delete from GasProject where gpId='").append(gpId).append("'");
        super.executeHql(hql.toString());
    }

    @Override
    public GasProject findByProjId(String projId,Date acceptDate) {
        Criteria c = super.getCriteria();
        if(StringUtils.isNotBlank(projId)){
            c.add(Restrictions.eq("projId",projId));
        }
            c.add(Restrictions.eq("acceptDate",acceptDate));
//            c.add(Restrictions.le("gasProjStatus", GasProjectStatusEnum.GAS_OPEN.getValue()));
        List<GasProject> gasProjects = c.list();
        if(gasProjects!=null && gasProjects.size()>0){
            return gasProjects.get(0);
        }
        return null;
    }
}