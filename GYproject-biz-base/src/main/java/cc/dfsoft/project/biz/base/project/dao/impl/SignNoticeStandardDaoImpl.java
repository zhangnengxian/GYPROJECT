package cc.dfsoft.project.biz.base.project.dao.impl;

import cc.dfsoft.project.biz.base.project.dao.SignNoticeStandardDao;
import cc.dfsoft.project.biz.base.project.dto.SignNoticeReq;
import cc.dfsoft.project.biz.base.project.entity.SignNoticeStandard;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;
import cc.dfsoft.uexpress.common.util.ResultUtil;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
/**
 * 签字通知标准
 * @author fuliwei
 *
 */
@Repository
public class SignNoticeStandardDaoImpl extends NewBaseDAO<SignNoticeStandard, String> implements SignNoticeStandardDao {
	
	/**
	 * 签字标准列表查询
	 * @author fuliwei
	 * @createTime 2018年1月18日
	 * @param 
	 * @return
	 */
	@Override
	public Map<String, Object> querySignNoticeStandard(SignNoticeReq req) {
		Criteria c = super.getCriteria();
		//分公司
		if(StringUtils.isNotBlank(req.getCorpId())){
			c.add(Restrictions.eq("corpId",req.getCorpId()));
		}
		//出资方式
		if(StringUtils.isNotBlank(req.getContributionMode())){
			c.add(Restrictions.eq("contributionMode",req.getContributionMode()));
		}
		//工程类型
		if(StringUtils.isNotBlank(req.getProjType())){
			c.add(Restrictions.eq("projType",req.getProjType()));
		}
		//职务
		if(StringUtils.isNotBlank(req.getPost())){
			c.add(Restrictions.eq("post",req.getPost()));
		}
		//职务名称
		if(StringUtils.isNotBlank(req.getPostName())){
			c.add(Restrictions.like("postName","%"+req.getPostName()+"%"));
		}
		
		//资料类型
		if(StringUtils.isNotBlank(req.getDataType())){
			c.add(Restrictions.eq("dataType",req.getDataType()));
		}
		
		//资料类型名称
		if(StringUtils.isNotBlank(req.getDataTypeName())){
			c.add(Restrictions.like("dataTypeName","%"+req.getDataTypeName()+"%"));
		}
		
		//签字资料类型 1单个单据 2多个单据
		if(StringUtils.isNotBlank(req.getGenerateType())){
			c.add(Restrictions.eq("generateType",req.getGenerateType()));
		}
		
		//签字顺序
		if(StringUtils.isNotBlank(req.getSortNo())){
			c.add(Restrictions.eq("sortNo",req.getSortNo()));
		}
		//签字职务
		if(req.getSignPost()!=null && req.getSignPost().size()>0){
			StringBuffer sql = new StringBuffer(" post in('");
			for(int i=1;i<req.getSignPost().size();i++){
				 sql.append(req.getSignPost().get(i)).append("',").append("'");
			 }
			sql.append(req.getSignPost().get(0)).append("')");
			c.add(Restrictions.sqlRestriction(sql.toString()));
		}
		// 数据库根据条件过滤记录数
		int filterCount = this.countByCriteria(c);
		
		// 根据条件获取查询信息
		List<SignNoticeStandard> operateRecordList = this.findBySortCriteria(c, req);
		
		// 返回结果
		return ResultUtil.pageResult(filterCount, req.getDraw(),operateRecordList);
	}
	
	/**
	 * 按职务查询
	 * @author fuliwei
	 * @createTime 2018年1月18日
	 * @param 
	 * @return
	 */
	@Override
	public List<SignNoticeStandard> queryByPost(String post) {
		
		Criteria c = super.getCriteria();
		
		// 职务
		if (StringUtils.isNotBlank(post)) {
			c.add(Restrictions.eq("post", post));
			// 根据条件获取查询信息
			List<SignNoticeStandard> signlList = this.findByCriteria(c);
			return signlList;
		}
		return null;
	}
	
	/**
	 * 按职务和单据类型查询
	 * @author fuliwei
	 * @createTime 2018年1月20日
	 * @param 
	 * @return
	 */
	@Override
	public List<SignNoticeStandard> queryByPostAndDateType(String post, String dateType,String corpId,String projType,String contributionMode) {
		Criteria c = super.getCriteria();
		
		// 职务
		if (StringUtils.isNotBlank(post) && post.contains(",")) {
			c.add(Restrictions.eq("post", post));
			// 根据条件获取查询信息
		}
		if(StringUtils.isNotBlank(post) && !post.contains(",")){
			c.add(Restrictions.eq("sortNo", post));
		}
		if (StringUtils.isNotBlank(dateType)) {
			c.add(Restrictions.eq("dataType", dateType));
			// 根据条件获取查询信息
		}
		if (StringUtils.isNotBlank(corpId)) {
			c.add(Restrictions.eq("corpId", corpId));
			// 根据条件获取查询信息
		}
		if (StringUtils.isNotBlank(projType)) {
			c.add(Restrictions.eq("projType", projType));
			// 根据条件获取查询信息
		}
		if (StringUtils.isNotBlank(contributionMode)) {
			c.add(Restrictions.eq("contributionMode", contributionMode));
			// 根据条件获取查询信息
		}
		
		List<SignNoticeStandard> signlList = this.findByCriteria(c);
		return signlList;
	}
	
	/**
	 * 按签字顺序和单据类型查询
	 * @author fuliwei
	 * @createTime 2018年1月20日
	 * @param 
	 * @return
	 */
	@Override
	public List<SignNoticeStandard> queryBySortNoAndDateType(String sortNo,String dateType,String corpId,String projectType,String contributionMode) {
		Criteria c = super.getCriteria();
		
		// 签字
		if (StringUtils.isNotBlank(sortNo)) {
			c.add(Restrictions.eq("sortNo", sortNo));
			// 根据条件获取查询信息
		}
		
		if (StringUtils.isNotBlank(dateType)) {
			c.add(Restrictions.eq("dataType", dateType));
			// 根据条件获取查询信息
		}
		if (StringUtils.isNotBlank(corpId)) {
			c.add(Restrictions.eq("corpId", corpId));
			// 根据条件获取查询信息
		}
		if (StringUtils.isNotBlank(projectType)) {
			c.add(Restrictions.eq("projType", projectType));
			// 根据条件获取查询信息
		}
		if (StringUtils.isNotBlank(contributionMode)) {
			c.add(Restrictions.eq("contributionMode", contributionMode));
			// 根据条件获取查询信息
		}
		List<SignNoticeStandard> signlList = this.findByCriteria(c);
		return signlList;
	}

	@Override
	public List<SignNoticeStandard> findSignNoticeStandardList(String menuId,String corpId,String projType,String contributionMode) {
		Criteria c = super.getCriteria();

		boolean menuIdNotBlank = StringUtils.isNotBlank(menuId);
		boolean corpIdNotBlank = StringUtils.isNotBlank(corpId);
		boolean projTypeNotBlank = StringUtils.isNotBlank(projType);
		boolean ctrbnModeNotBlank = StringUtils.isNotBlank(contributionMode);

		if (menuIdNotBlank && projTypeNotBlank && corpIdNotBlank && ctrbnModeNotBlank) {
			c.add(Restrictions.eq("menuId", menuId));
			c.add(Restrictions.eq("corpId", corpId));
			c.add(Restrictions.eq("projType", projType));
			c.add(Restrictions.eq("contributionMode", contributionMode));
			return this.findByCriteria(c);
		}
		return null;
	}

}
