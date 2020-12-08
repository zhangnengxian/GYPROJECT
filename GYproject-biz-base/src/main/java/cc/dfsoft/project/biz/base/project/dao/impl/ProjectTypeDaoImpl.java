package cc.dfsoft.project.biz.base.project.dao.impl;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import cc.dfsoft.project.biz.base.baseinfo.dao.BusinessPartnersDao;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cc.dfsoft.project.biz.base.project.dao.ProjectTypeDao;
import cc.dfsoft.project.biz.base.project.dto.ProjectTypeReq;
import cc.dfsoft.project.biz.base.project.entity.ProjectType;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;
import cc.dfsoft.uexpress.common.util.ResultUtil;
@Repository
public class ProjectTypeDaoImpl extends NewBaseDAO<ProjectType, String> implements ProjectTypeDao {
	
	@Resource
	BusinessPartnersDao businessPartnersDao;
	/**
	 * 查询附件列表
	 * @param accessoryQueryReq
	 * @return
	 */
	@Override
	public Map<String,Object> queryProjectTypeList(ProjectTypeReq req){
		
		 Criteria c = super.getCriteria();
		
		 //工程大类
		 if(StringUtils.isNotBlank(req.getProjectTypeDes())){
			 c.add(Restrictions.like("projTypeDes","%"+req.getProjectTypeDes()+"%"));
		 }
		
		 // 数据库根据条件过滤记录数
		 int filterCount = this.countByCriteria(c);

		 // 根据条件获取查询信息
		 List<ProjectType> projectTypeList = this.findBySortCriteria(c, req);
			
		 // 返回结果
		 return ResultUtil.pageResult( filterCount, req.getDraw(),projectTypeList);
		
	}

	
	@Override
	public void delProjectType(String id) {
		ProjectType pt=this.get("projTypeId", id);
		if(pt!=null){
			this.delete(pt);
		}
	
	}
	@Override
	public List<ProjectType> findProjectType() {
    	Criteria c = super.getCriteria();
	    //c.addOrder(Order.asc("projTypeId"));
	    // 根据条件获取查询信息
	    List<ProjectType> projectTypeList = c.list();
		return projectTypeList;
	}


	/***
	 * 根据类型id查询工程类型
	 * @param typeId
	 * @return
	 */
	@Override
	public ProjectType findByTypeId(String typeId) {
		Criteria c = super.getCriteria();
		//工程类型
		 if(StringUtils.isNotBlank(typeId)){
			 c.add(Restrictions.like("projTypeId",typeId));
		 }
	    // 根据条件获取查询信息
	    List<ProjectType> projectTypeList = c.list();
	    if(projectTypeList!=null && projectTypeList.size()>0){
	    	return projectTypeList.get(0);
	    }
		return null;
	}

	
	/**
	 * 查询计划内或计划外的工程类型列表
	 * @param 
	 * @return
	 */
	@Override
	public List<ProjectType> queryProjectTypeList(String type) {
		 Criteria c = super.getCriteria();
		//工程类型
		 if(StringUtils.isNotBlank(type)){
			 c.add(Restrictions.eq("scaleType",type));
		 }
		 List<ProjectType> projectTypeList = c.list();
		 return projectTypeList;
	}

	/**
	 * 部门id
	 * @author fuliwei
	 * @createTime 2017年8月2日
	 * @param 
	 * @return
	 */
	@Override
	public List<ProjectType> queryProjectTypeByDeptId(String deptId) {
		Criteria c=super.getCriteria();
		if(StringUtils.isNotBlank(deptId)){
			c.add(Restrictions.eq("deptId", deptId));
		}
		List<ProjectType> list=c.list();
		return list;
	}
	

}
