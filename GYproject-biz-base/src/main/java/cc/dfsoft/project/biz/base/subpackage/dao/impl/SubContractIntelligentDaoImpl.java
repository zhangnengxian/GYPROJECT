package cc.dfsoft.project.biz.base.subpackage.dao.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cc.dfsoft.project.biz.base.subpackage.dao.SubContractIntelligentDao;
import cc.dfsoft.project.biz.base.subpackage.dto.SubConIntelligentReq;
import cc.dfsoft.project.biz.base.subpackage.entity.SubContractIntelligent;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dao.DepartmentDao;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.biz.sys.dept.entity.Department;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;
import cc.dfsoft.uexpress.common.util.ResultUtil;
import cc.dfsoft.uexpress.common.util.StringUtil;
/**
 * 
 * 描述:智能表分合同dao实现层
 * @author liaoyq
 * @createTime 2017年10月11日
 */
@Repository
public  class SubContractIntelligentDaoImpl extends NewBaseDAO<SubContractIntelligent,String> implements SubContractIntelligentDao {

	@Override
	public SubContractIntelligent findContractByprojId(String id) {
		Criteria c = super.getCriteria();
		if(StringUtils.isNotBlank(id)){
			c.add(Restrictions.eq("projId", id));
			List<SubContractIntelligent> list = this.findByCriteria(c);
			if(list != null && list.size()>0){
				return list.get(0);
			}
		}
		return null;
	}

	@Override
	public List<SubContractIntelligent> findByScNo(String itScNo) {
		Criteria c = super.getCriteria();
		if(StringUtils.isNotBlank(itScNo)){
			c.add(Restrictions.eq("itScNo", itScNo));
			List<SubContractIntelligent> list = this.findByCriteria(c);
			return list;
		}
		return null;
	}

	@Override
	public Map<String, Object> queryContractBySql(
			SubConIntelligentReq req) {
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		StringBuffer sql = new StringBuffer("select *  from (select p.PROJ_ID as projId,p.PROJ_NO as projNo,p.PROJ_NAME as projName,p.PROJECT_TYPE AS projectType,p.PROJ_STATUS_ID as projStatusId,sci.flag as subFlag,").append(mysqlSqlConveter.rownumberConveter(" rn from")).append(" project as p left join sub_contract_intelligent sci on sci.PROJ_ID = p.PROJ_ID  where 1=1");
		StringBuffer sqlCount = new StringBuffer("select count(p.PROJ_ID) from project as p left join sub_contract_intelligent sci on sci.PROJ_ID = p.PROJ_ID  where 1=1");
		StringBuffer filter = new StringBuffer();
		
		//乙方名称
		if(StringUtil.isNotBlank(req.getsPartyName())){
			filter.append(" and sci.SPARTY_NAME like '%").append(req.getsPartyName()).append("%'");
		}
		//智能表合同编号
		if(StringUtil.isNotBlank(req.getItScNo())){
			filter.append(" and sci.IT_SC_NO='").append(req.getItScNo()).append("'");
		}
		//签定日期开始
		 if(StringUtils.isNotBlank(req.getSignDateStart())){
			 filter.append(" and sci.SIGN_DATE>='").append(req.getSignDateStart()).append("'");
		 }
		 //签定日期结束
		 if(StringUtils.isNotBlank(req.getSignDateEnd())){
			 filter.append(" and sci.SIGN_DATE<='").append(req.getSignDateEnd()).append("'");
		 }
		//工程地点
		if(StringUtil.isNotBlank(req.getProjAddr())){
			filter.append(" and p.PROJ_ADDR like '%").append(req.getProjAddr()).append("%'");
		}
		//工程编号
		if(StringUtil.isNotBlank(req.getProjNo())){
			filter.append(" and p.PROJ_NO='").append(req.getProjNo()).append("'");
		}
		//工程名称
		if(StringUtil.isNotBlank(req.getProjName())){
			filter.append(" and p.PROJ_NAME like '%").append(req.getProjName()).append("%'");
		}
		if(StringUtil.isNotBlank(req.getProjStatusId())){
			filter.append(" and p.PROJ_STATUS_ID='").append(req.getProjStatusId()).append("'");
		}
		if(StringUtil.isNotBlank(req.getProjStatusIdStart())){
			filter.append(" and p.PROJ_STATUS_ID>'").append(req.getProjStatusIdStart()).append("'");
		}
		if(StringUtil.isNotBlank(req.getProjStatusIdEnd())){
			filter.append(" and p.PROJ_STATUS_ID<='").append(req.getProjStatusIdEnd()).append("'");
		}
		//过滤掉终止的和按照公司过滤
		filter.append(" and p.PROJ_STATUS_ID !='2001'");
		filter.append(" and p.corp_id like '").append(loginInfo.getCorpId()).append("%'");
		//工程类型
		if(StringUtil.isNotBlank(req.getProjLtypeId())){
			filter.append(" and p.PROJ_LTYPE_ID='").append(req.getProjLtypeId()).append("'");
		}
		//是否是智能表
		if(req.getIsIntelligentMeter()!=null){
			filter.append(" and p.IS_INTELLIGENT_METER='").append(req.getIsIntelligentMeter()).append("'");
		}
		//推送状态
		if(StringUtil.isNotBlank(req.getFlag()) && "1".equals(req.getFlag())){//已推送
			filter.append(" and sci.flag='").append(req.getFlag()).append("'");
		}else{//默认未推送
			filter.append(" and ( sci.FLAG='0' or sci.FLAG is null)");
		}
		 // 数据库根据条件过滤记录数
		int filterCount = this.getCountBySql(sqlCount.append(filter).toString());
		// 根据条件获取查询信息
		int start = req.getStart()+1;
		int end = start+(req.getLength()-1);
		sql.append(filter);
		sql.append(" order by sci.SIGN_DATE desc ) result");
		
		sql.append(" where result.rn between "+start+" and "+ end);
		
		List<Map<String, Object>> mapList = this.findListBySql(sql.toString());
		
		 // 返回结果
		 return ResultUtil.pageResult( filterCount, req.getDraw(),mapList);
	}

}
