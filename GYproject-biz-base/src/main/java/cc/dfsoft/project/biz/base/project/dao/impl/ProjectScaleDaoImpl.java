package cc.dfsoft.project.biz.base.project.dao.impl;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cc.dfsoft.project.biz.base.baseinfo.dao.BusinessPartnersDao;
import cc.dfsoft.project.biz.base.project.dao.ProjectScaleDao;
import cc.dfsoft.project.biz.base.project.dto.ProjectScaleReq;
import cc.dfsoft.project.biz.base.project.entity.ProjectScale;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;
import cc.dfsoft.uexpress.common.util.ResultUtil;
@Repository
public class ProjectScaleDaoImpl extends NewBaseDAO<ProjectScale, String> implements ProjectScaleDao {
	
	@Resource
	BusinessPartnersDao businessPartnersDao;
	/**
	 * 查询附件列表
	 * @param accessoryQueryReq
	 * @return
	 */
	@Override
	public Map<String,Object> queryProjectScaleList(ProjectScaleReq req){
		
		 Criteria c = super.getCriteria();
		
		 //工程大类
		 if(StringUtils.isNotBlank(req.getPsDes())){
			 c.add(Restrictions.like("projTypeDes","%"+req.getPsDes()+"%"));
		 }
		
		 // 数据库根据条件过滤记录数
		 int filterCount = this.countByCriteria(c);

		 // 根据条件获取查询信息
		 List<ProjectScale> ProjectScaleList = this.findBySortCriteria(c, req);
			
		 // 返回结果
		 return ResultUtil.pageResult( filterCount, req.getDraw(),ProjectScaleList);
		
	}

	
	@Override
	public void delProjectScale(String id) {
		ProjectScale pt=this.get("psId", id);
		if(pt!=null){
			this.delete(pt);
		}
	
	}

	

}
