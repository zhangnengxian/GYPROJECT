package cc.dfsoft.project.biz.base.baseinfo.dao.impl;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cc.dfsoft.project.biz.base.baseinfo.dao.CorrelationDao;
import cc.dfsoft.project.biz.base.baseinfo.dto.CorrelationReq;
import cc.dfsoft.project.biz.base.baseinfo.entity.Correlation;
import cc.dfsoft.project.biz.base.baseinfo.enums.ScaleTypeEnum;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;
import cc.dfsoft.uexpress.common.util.ResultUtil;
@Repository
public class CorrelationDaoImpl extends NewBaseDAO<Correlation, String> implements CorrelationDao {

	@Override
	public Map<String, Object> queryCorrelation(CorrelationReq correlationReq) {
		 Criteria c = super.getCriteria();
		 
		 if (StringUtils.isNotBlank(correlationReq.getCorpId())) {
				c.add(Restrictions.like("corpId", "%"+correlationReq.getCorpId()+"%"));
		 }
		 
		 //相关信息描述
		 if(StringUtils.isNotBlank(correlationReq.getCorrelateInfoDes())){
			 c.add(Restrictions.like("correlateInfoDes","%"+correlationReq.getCorrelateInfoDes()+"%"));
		 }
		 
		 //类型
		 if(StringUtils.isNotBlank(correlationReq.getCorType())){
			 c.add(Restrictions.eq("corType",correlationReq.getCorType()));
		 }
		 
		 //立项方式
		 if(StringUtils.isNotBlank(correlationReq.getAcceptType())){
			 c.add(Restrictions.eq("acceptType",correlationReq.getAcceptType()));
		 }
		 
		 //规模
		 if(StringUtils.isNotBlank(correlationReq.getScaleType())){
			 c.add(Restrictions.eq("scaleType",correlationReq.getScaleType()));
		 }
		 
		 // 数据库根据条件过滤记录数
		 int filterCount = this.countByCriteria(c);
		 // 根据条件获取查询信息
		 List<Correlation> correlationList = this.findBySortCriteria(c, correlationReq);
		 // 返回结果
		 return ResultUtil.pageResult( filterCount, correlationReq.getDraw(),correlationList);
	}

	@Override
	public List<Correlation> findCorType(CorrelationReq req) {
		
		Criteria criteria = super.getCriteria();
		
		LoginInfo login=SessionUtil.getLoginInfo();
		criteria.add(Restrictions.eq("corpId", login.getCorpId()));
		
		 //关联类型
		if(StringUtils.isNotBlank(req.getCorType())){
			criteria.add(Restrictions.eq("corType",req.getCorType()));
		}
		//相关信息
		if(StringUtils.isNotBlank(req.getCorrelateInfoId())){
			criteria.add(Restrictions.eq("correlateInfoId",req.getCorrelateInfoId()));
		}
		
		if(StringUtils.isNotBlank(req.getCorrelatedInfoId())){
			criteria.add(Restrictions.eq("correlatedInfoId",req.getCorrelatedInfoId()));
		}
		
		//只用于-立项时设置部门id
		if(StringUtils.isNotBlank(req.getAcceptCorrelatedInfoId())){
			criteria.add(Restrictions.like("correlatedInfoId",req.getAcceptCorrelatedInfoId()+"%"));
		}
		
		//立项方式
		if(StringUtils.isNotBlank(req.getAcceptType())){
			criteria.add(Restrictions.eq("acceptType",req.getAcceptType()));
		}
		
		if(StringUtils.isNotBlank(req.getScaleType())){
			criteria.add(Restrictions.eq("scaleType",req.getScaleType()));
		}
		
		if(ScaleTypeEnum.SMALL_SCALE.getValue().equals(req.getAcceptScaleType())){
			criteria.add(Restrictions.ne("scaleType",req.getAcceptScaleType()));
		}else if(ScaleTypeEnum.LARGE_SCALE.getValue().equals(req.getAcceptScaleType())){
			criteria.add(Restrictions.eq("scaleType",ScaleTypeEnum.SMALL_SCALE.getValue()));
		}
		
		
		List<Correlation> list = this.findByCriteria(criteria);
		return list;
	}

	@Override
	public List<Correlation> findProjType(String corType, List<String> list) {
		Criteria criteria = super.getCriteria();
		 //关联类型
		if(StringUtils.isNotBlank(corType)){
			criteria.add(Restrictions.eq("corType",corType));
		}
		//相关信息
		if(null!=list&&list.size()>0){
			criteria.add(Restrictions.in("correlateInfoId", list));
		}
		List<Correlation> projTypeList = this.findByCriteria(criteria);
		return projTypeList;
	}

	@Override
	public Correlation findCode(String corType, String projType, String contributionModelId,String corpId) {
		Criteria criteria = super.getCriteria();
		 //关联类型
		if(StringUtils.isNotBlank(corType)){
			criteria.add(Restrictions.eq("corType",corType));
		}
		//相关信息
		if(StringUtils.isNotBlank(projType)){
			criteria.add(Restrictions.eq("correlateInfoId",projType));
		}
		//关联信息
		if(StringUtils.isNotBlank(contributionModelId)){
			criteria.add(Restrictions.eq("correlatedInfoId",contributionModelId));
		}
		//公司ID
		if(StringUtils.isNotBlank(corpId)){
			criteria.add(Restrictions.eq("corpId",corpId));
		}else{
			criteria.add(Restrictions.eq("corpId",Constants.START_REPORT_CPT_CORP_MODE));
		}
		List<Correlation> projTypeList = this.findByCriteria(criteria);
		if(null!=projTypeList&&projTypeList.size()>0){
			return projTypeList.get(0);
		}
		return null;
	}
	
	/**
	 * 通过编号查询
	 * @author fuliwei
	 * @createTime 2017年9月8日
	 * @param 
	 * @return
	 */
	@Override
	public Correlation findByContriubbtionCode(String code) {
		Criteria criteria = super.getCriteria();
		 //关联类型
		if(StringUtils.isNotBlank(code)){
			criteria.add(Restrictions.eq("contributionCode",code));
		}
		List<Correlation> projTypeList = this.findByCriteria(criteria);
		
		if(projTypeList!=null && projTypeList.size()>0){
			return projTypeList.get(0);
		}
		return null;
	}
}
