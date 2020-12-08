package cc.dfsoft.project.biz.base.design.dao.impl;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cc.dfsoft.project.biz.base.budget.entity.ProjectCostSummary;
import cc.dfsoft.project.biz.base.design.dao.DrawingsDirectoryDao;
import cc.dfsoft.project.biz.base.design.dto.DrawingsDirectoryReq;
import cc.dfsoft.project.biz.base.design.entity.DrawingsDirectory;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;
import cc.dfsoft.uexpress.common.util.ResultUtil;

@Repository
public class DrawingsDirectoryDaoImpl extends NewBaseDAO<DrawingsDirectory, String> implements DrawingsDirectoryDao {

	@Override
	public Map<String, Object> queryDrawDirectory(DrawingsDirectoryReq pageSortReq) {
		Criteria c = super.getCriteria();
		//根据工程Id进行查询
		if(StringUtils.isNoneBlank(pageSortReq.getProjId())){
			 c.add(Restrictions.eq("projId",pageSortReq.getProjId()));
		}
		//根据图纸编号进行查询
		if(StringUtils.isNoneBlank(pageSortReq.getDrawingNo())){
			 c.add(Restrictions.eq("drawingNo",pageSortReq.getDrawingNo()));
		}
		// 数据库根据条件过滤记录数
		int filterCount = this.countByCriteria(c);
		// 根据条件获取查询信息
		List<DrawingsDirectory> list = this.findBySortCriteria(c, pageSortReq);				
		// 返回结果
		return ResultUtil.pageResult(filterCount, pageSortReq.getDraw(),list);
	}

	@Override
	public List<DrawingsDirectory> queryAllDrawDirect(DrawingsDirectory drawingsDirectory) {
		Criteria c = super.getCriteria();
		//根据工程Id进行查询
		if(StringUtils.isNoneBlank(drawingsDirectory.getProjId())){
			 c.add(Restrictions.eq("projId",drawingsDirectory.getProjId()));
		}
		// 根据条件获取查询信息
		List<DrawingsDirectory> list = this.findByCriteria(c);	
		return list;
	}
	
	/**
	 * 图纸审核页面查询图纸审核等级用
	 * @author
	 * @createTime 
	 * @param
	 * @return
	 */
	@Override
	public List<DrawingsDirectory> findByProjId(String projId) {
		Criteria c=super.getCriteria();
		if(StringUtils.isNotBlank(projId)){
			c.add(Restrictions.eq("projId", projId));
		}
		List<DrawingsDirectory> list=this.findByCriteria(c);
		return list;
	}

	@Override
	public List<DrawingsDirectory> queryDrawingsDirectoryList(String projId) {
		 Criteria c = super.getCriteria();
		 
		 if(StringUtils.isNotBlank(projId)){
			 c.add(Restrictions.eq("projId",projId));
		 }
		 List<DrawingsDirectory> list = this.findByCriteria(c);
		return list;
	}


}
