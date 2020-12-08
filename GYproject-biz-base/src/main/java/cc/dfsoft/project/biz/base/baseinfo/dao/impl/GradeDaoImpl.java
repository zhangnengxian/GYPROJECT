package cc.dfsoft.project.biz.base.baseinfo.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cc.dfsoft.project.biz.base.baseinfo.dao.GradeDao;
import cc.dfsoft.project.biz.base.baseinfo.entity.Grade;
import cc.dfsoft.project.biz.base.baseinfo.entity.ScoreStandard;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;
import cc.dfsoft.uexpress.common.util.ResultUtil;

@Repository
public class GradeDaoImpl extends NewBaseDAO<Grade,String> implements GradeDao{
	
	/**
	 * 根据评分表id查询评分
	 * @author
	 * @createTime 
	 * @param
	 * @return
	 */
	@Override
	public List queryBySsid(String projId,String staffId) {
		StringBuffer sql=new StringBuffer();
		sql.append("from Grade g where  g.projId='").append(projId).append("' and graderId='").append(staffId).append("'");
		List<Grade> list=this.findByHql(sql.toString());
		if(list!=null && list.size()>0){
			return list;
		}
		return null;
	}
	
	/**
	 * 根据unitid和工程id删除grade表相应部门的内容
	 * @author
	 * @createTime 
	 * @param
	 * @return
	 */
	@Override
	public void deleteByProjIdAndUnitId(String projId, String deptId) {
		StringBuffer hql=new StringBuffer("delete from Grade where projId='").append(projId).append("'").append(" and deptId='").append(deptId).append("'");
		super.executeHql(hql.toString());
	}

	@Override
	public Map<String, Object> queryBySsidAndProjId(String ssId, String projId) {
		 return null;
	}

}
