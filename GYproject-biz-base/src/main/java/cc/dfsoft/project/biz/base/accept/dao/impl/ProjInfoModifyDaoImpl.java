package cc.dfsoft.project.biz.base.accept.dao.impl;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cc.dfsoft.project.biz.base.accept.dao.ProjInfoModifyDao;
import cc.dfsoft.project.biz.base.accept.dto.ProjInfoModifyReq;
import cc.dfsoft.project.biz.base.accept.entity.ProjInfoModify;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;
import cc.dfsoft.uexpress.common.util.ResultUtil;

@Repository
public class ProjInfoModifyDaoImpl extends NewBaseDAO<ProjInfoModify,String> implements ProjInfoModifyDao{
	

	/**
	 * 查询详述
	 * @author fuliwei
	 * @createTime 2017-01-3
	 * @param id 工程id
	 * @return ProjInfoModify
	 */
	@Override
	public ProjInfoModify queryById(String projId) {
		Criteria c = super.getCriteria();
		
		if(StringUtils.isNotBlank(projId)){
			c.add(Restrictions.eq("projId",projId));
		}
		
		List<ProjInfoModify> list= this.findByCriteria(c);
		
		if(list != null  && list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
	/**
	 * 列表查询
	 * @author fuliwei
	 * @createTime 2017-01-3
	 * @param ProjInfoModifyReq
	 * @return
	 */
	@Override
	public Map<String, Object> queryProModify(ProjInfoModifyReq req) throws ParseException {
		Criteria c = super.getCriteria();
		
		 //工程大类
		 if(StringUtils.isNotBlank(req.getProjId())){
			 c.add(Restrictions.eq("projId",req.getProjId()));
		 }
		
		 // 数据库根据条件过滤记录数
		 int filterCount = this.countByCriteria(c);

		 // 根据条件获取查询信息
		 List<ProjInfoModify> list = this.findBySortCriteria(c, req);
			
		 // 返回结果
		 return ResultUtil.pageResult( filterCount, req.getDraw(),list);
	}

}
