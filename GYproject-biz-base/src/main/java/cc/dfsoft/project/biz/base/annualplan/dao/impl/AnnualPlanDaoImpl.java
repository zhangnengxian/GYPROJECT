package cc.dfsoft.project.biz.base.annualplan.dao.impl;

import cc.dfsoft.project.biz.base.annualplan.dao.AnnualPlanDao;
import cc.dfsoft.project.biz.base.annualplan.dto.AnnualPlanReq;
import cc.dfsoft.project.biz.base.annualplan.entity.AnnualPlan;
import cc.dfsoft.project.biz.base.project.dao.ProjectTypeDao;
import cc.dfsoft.project.biz.base.project.entity.ProjectType;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dao.DepartmentDao;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.biz.sys.dept.entity.Department;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;
import cc.dfsoft.uexpress.common.util.ResultUtil;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author cui
 * @createTime 2017-8-8
 *
 */
@Repository
public class AnnualPlanDaoImpl extends NewBaseDAO<AnnualPlan, String> implements AnnualPlanDao {

    @Resource
    ProjectTypeDao projectTypeDao;

    @Resource
    DepartmentDao departmentDao;

    @Override
    public Map<String, Object> queryAnnualPlan(AnnualPlanReq annualPlanReq) {
        Criteria c = super.getCriteria();
        //计划id
        if(StringUtils.isNotBlank(annualPlanReq.getPlanId())){
            c.add(Restrictions.eq("planId",annualPlanReq.getPlanId()));
        }
        //计划编号
        if(StringUtils.isNotBlank(annualPlanReq.getPlanNo())){
            c.add(Restrictions.like("planNo",annualPlanReq.getPlanNo()+"%"));
        }
        //计划名称
        if(StringUtils.isNotBlank(annualPlanReq.getPlanName())){
            c.add(Restrictions.like("planName","%"+annualPlanReq.getPlanName()+"%"));
        }
        //计划地址
        if(StringUtils.isNotBlank(annualPlanReq.getPlanAddr())){
            c.add(Restrictions.like("planAddr","%"+annualPlanReq.getPlanAddr()+"%"));
        }
        //项目类型
        if(StringUtils.isNotBlank(annualPlanReq.getProjectType())){
            c.add(Restrictions.like("projectType",annualPlanReq.getProjectType()));
        }
        
        LoginInfo loginInfo = SessionUtil.getLoginInfo();
        String deptId = loginInfo.getDeptId();
		c.add(Restrictions.like("deptId", "%"+deptId+"%"));
        
        // 数据库根据条件过滤记录数
        int filterCount = this.countByCriteria(c);
        // 根据条件获取查询信息
        List<AnnualPlan> annualPalns = this.findBySortCriteria(c, annualPlanReq);

        for(AnnualPlan ap:annualPalns){
        	if(StringUtils.isNotBlank(ap.getProjectType())){
        		 ProjectType pt = projectTypeDao.get(ap.getProjectType());
        		 if(null!=pt){
                     ap.setProjectTypeDes(pt.getProjTypeDes());
                 }
        	}
        	/*if(StringUtils.isNotBlank(ap.getAffiliatedCompany())){
        		 Department department = departmentDao.get(ap.getAffiliatedCompany());
                 if(department!=null){
                     ap.setCorpName(department.getDeptName());
                 }
        	}*/
        }
        
        // 返回结果
        return ResultUtil.pageResult( filterCount, annualPlanReq.getDraw(),annualPalns);
    }
    
    /**
     * 根据计划编号删除年度计划
     * @author fuliwei
     * @createTime 2017年8月31日
     * @param 
     * @return
     */
	@Override
	public void deleteByPlanNo(String planNo,String deptId) {
		StringBuffer hql = new StringBuffer("delete from AnnualPlan where planNo='").append(planNo).append("' and deptId='").append(deptId).append("'");
		super.executeHql(hql.toString());
	}
}