package cc.dfsoft.project.biz.base.project.dao.impl;

import cc.dfsoft.project.biz.base.project.dao.MenuBackSetDao;
import cc.dfsoft.project.biz.base.project.dto.FallbackApplyReq;
import cc.dfsoft.project.biz.base.project.dto.MenuBackSetReq;
import cc.dfsoft.project.biz.base.project.entity.MenuBackSet;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;
import cc.dfsoft.uexpress.common.util.ResultUtil;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
/**
 * 退回菜单配置
 * @author fuliwei
 *
 */
@Repository
public class MenuBackSetDaoImpl extends NewBaseDAO<MenuBackSet, String> implements MenuBackSetDao {
	
	/**
	 * 根据工程类型和当前的id去查返回的list
	 * @author fuliwei
	 * @createTime 2017年11月30日
	 * @param 
	 * @return
	 */
	@Override
	public List<MenuBackSet> queryMenuBackSetByProjType(FallbackApplyReq req) {
		Criteria c = super.getCriteria();
		//工程类型
		if(StringUtils.isNotBlank(req.getProjectType())){
			c.add(Restrictions.eq("projectType",req.getProjectType()));
		}
		
		if(StringUtils.isNotBlank(req.getContributionCode())){
			c.add(Restrictions.eq("contributionCode",req.getContributionCode()));
		}
		
		if(StringUtils.isNotBlank(req.getMenuId())){
			c.add(Restrictions.eq("menuId",req.getMenuId()));
		}
		if(StringUtils.isNotBlank(req.getCorpId())){
			c.add(Restrictions.eq("corpId",req.getCorpId()));
		}
		
		List<MenuBackSet> list = this.findBySortCriteria(c, req);
		return list;
	}






	@Override
	public MenuBackSet quertMenuBackSet(FallbackApplyReq req) {
		List<MenuBackSet> mbsList = this.queryMenuBackSetByProjType(req);

		if (mbsList!=null && mbsList.size()>0) {
			return mbsList.get(0);
		}

		req.setContributionCode(null);
		mbsList = this.queryMenuBackSetByProjType(req);
		if (mbsList!=null && mbsList.size()>0) {
			return mbsList.get(0);
		}

		req.setProjectType(null);
		mbsList = this.queryMenuBackSetByProjType(req);
		if (mbsList!=null && mbsList.size()>0) {
			return mbsList.get(0);
		}

		req.setCorpId("0");
		mbsList = this.queryMenuBackSetByProjType(req);
		if (mbsList!=null && mbsList.size()>0) {
			return mbsList.get(0);
		}


		return null;
	}













	@Override
	public Map<String, Object> getDataList(MenuBackSetReq req) {
		Criteria c = super.getCriteria();
		if(StringUtils.isNotBlank(req.getCorpId())){
			c.add(Restrictions.eq("corpId",req.getCorpId()));
		}
		/*String sql = "select s.MBS_ID mbsId,t.DEPT_NAME corpName," +
				"m.contribution_des contributionDes,ty.PROJ_CONSTRUCT_DES projConstructDes,n.MENU_NAME menuName " +
				"from menu_back_set s left join " +
				"department t on s.CORP_ID = t.DEPT_ID left join " +
				"contribution_mode m on s.CONTRIBUTION_CODE = m.id left join project_type ty " +
				"on s.PROJECT_TYPE= ty.PROJ_TYPE_ID left join menu n on s.MENU_ID = n.MENU_ID " +
				"where s.CORP_ID = ? " ;*/
		if(StringUtils.isNotBlank(req.getProjectType())){
			c.add(Restrictions.eq("projectType",req.getProjectType()));
		}
		if(StringUtils.isNotBlank(req.getContributionMode())){
			c.add(Restrictions.eq("contributionCode",req.getContributionMode()));
		}
		if(StringUtils.isNotBlank(req.getMenuName())){
			c.add(Restrictions.like("menuDes","%"+req.getMenuName()+"%"));
		}
		//sql=sql+"order by s.UPDATE_TIME DESC limit ?,?";

		// 数据库根据条件过滤记录数
		int filterCount = this.countByCriteria(c);

		// 根据条件获取查询信息
		//List<Map<String,Object>> list = this.findListBySql(sql,new Object[]{req.getCorpId(),req.getStart(),req.getLength()});
		List<MenuBackSet> list = this.findBySortCriteria(c, req);
		return ResultUtil.pageResult(filterCount, req.getDraw(),list);
	}

	@Override
	public MenuBackSet isExisting(MenuBackSet menuBackSet) {
		Criteria c = super.getCriteria();
		c.add(Restrictions.eq("menuId",menuBackSet.getMenuId()));
		c.add(Restrictions.eq("corpId",menuBackSet.getCorpId()));
		c.add(Restrictions.eq("projectType",menuBackSet.getProjectType()));
		c.add(Restrictions.eq("contributionCode",menuBackSet.getContributionCode()));
		List<MenuBackSet> list = this.findByCriteria(c);
		if(list != null&&list.size()>0){
			return list.get(0);
		}
		return null;
	}


}
