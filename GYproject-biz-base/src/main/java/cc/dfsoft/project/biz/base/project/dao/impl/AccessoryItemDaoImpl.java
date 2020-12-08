package cc.dfsoft.project.biz.base.project.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;



import cc.dfsoft.project.biz.base.project.dao.AccessoryItemDao;
import cc.dfsoft.project.biz.base.project.dto.AccessoryQueryReq;
import cc.dfsoft.project.biz.base.project.entity.CollectAccessoryItem;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;
import cc.dfsoft.uexpress.common.util.ResultUtil;


/**
 * 资料标准DAO实现类
 * @author songqn
 *
 */
@Repository
public class AccessoryItemDaoImpl extends NewBaseDAO<CollectAccessoryItem, String> implements AccessoryItemDao {

	/**
	 * 依据工程类型查询资料标准
	 * @param accessoryQueryReq
	 * @return
	 */
	@Override
	public List<CollectAccessoryItem> queryAll(AccessoryQueryReq accessoryQueryReq) {
		Criteria criteria = super.getCriteria();
		if (StringUtils.isNotBlank(accessoryQueryReq.getProjLtypeId())) {
			String[] str=accessoryQueryReq.getProjLtypeId().split(",");
			criteria.add(Restrictions.in("projType.projTypeId", str));
		}
		//资料类型
		if(StringUtils.isNotBlank(accessoryQueryReq.getDataType())){
			criteria.add(Restrictions.eq("dataType", accessoryQueryReq.getDataType()));
		}
		
		//分公司id
		if(StringUtils.isNotBlank(accessoryQueryReq.getCorpId())){
			criteria.add(Restrictions.eq("corpId", accessoryQueryReq.getCorpId()));
		}
		
		List<CollectAccessoryItem> collectAccessoryItemList = this.findByCriteria(criteria);
		return collectAccessoryItemList;
	}

	/**
	 * 列表查询资料标准
	 */
	@Override
	public Map<String, Object> queryAccessoryItemList(AccessoryQueryReq accessoryQueryReq) {
		Criteria c = super.getCriteria();
		
		if (StringUtils.isNotBlank(accessoryQueryReq.getCorpId())) {
			c.add(Restrictions.like("corpId", "%"+accessoryQueryReq.getCorpId()+"%"));
		}
		
		if (StringUtils.isNotBlank(accessoryQueryReq.getCaiId())) {
			c.add(Restrictions.eq("caiId", accessoryQueryReq.getCaiId()));
		}
		//资料类型
		if (StringUtils.isNotBlank(accessoryQueryReq.getProjLtypeId())) {
			c.add(Restrictions.eq("projType.projTypeId", accessoryQueryReq.getProjLtypeId()));
		}
		//资料名称
		if(StringUtils.isNotBlank(accessoryQueryReq.getAccessoryName())){
			c.add(Restrictions.like("accessoryName","%"+accessoryQueryReq.getAccessoryName()+"%"));
		}
		
		//查询
		if(accessoryQueryReq.getCaiIdList()!=null && accessoryQueryReq.getCaiIdList().size()>0){
			List<String> caiIdList=accessoryQueryReq.getCaiIdList();
			//c.add(Restrictions.in("caiId",caiIdList));
			c.add(Restrictions.not(Restrictions.in("caiId", caiIdList)));
		}
		
		//资料类型
		if(StringUtils.isNotBlank(accessoryQueryReq.getDataType())){
			c.add(Restrictions.eq("dataType", accessoryQueryReq.getDataType()));
		}
		
		// 数据库根据条件过滤记录数
		int filterCount = this.countByCriteria(c);

		// 根据条件获取查询信息
		List<CollectAccessoryItem> projectList = this.findBySortCriteria(c, accessoryQueryReq);
		
		// 返回结果
		return ResultUtil.pageResult( filterCount, accessoryQueryReq.getDraw(),projectList);
	}
	
	/**
	 * 根据资料名称查询资料标准
	 */
	@Override
	public CollectAccessoryItem queryByAccessoryName(String accessoryName) {
		Criteria c = super.getCriteria();
		c.add(Restrictions.eq("accessoryName", accessoryName));
		// 根据条件获取查询信息
		List<CollectAccessoryItem> projectList = this.findByCriteria(c);
		if(projectList.size() > 0){
			return projectList.get(0);
		}else{
			return null;
		}
	}
	
}
