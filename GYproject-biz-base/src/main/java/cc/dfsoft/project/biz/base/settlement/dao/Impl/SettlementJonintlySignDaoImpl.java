package cc.dfsoft.project.biz.base.settlement.dao.Impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cc.dfsoft.project.biz.base.baseinfo.dao.BusinessPartnersDao;
import cc.dfsoft.project.biz.base.baseinfo.entity.BusinessPartners;
import cc.dfsoft.project.biz.base.design.enums.UnitTypeEnum;
import cc.dfsoft.project.biz.base.project.dao.ProjectDao;
import cc.dfsoft.project.biz.base.settlement.dao.SettlementJonintlySignDao;
import cc.dfsoft.project.biz.base.settlement.dto.SettlementDeclarationReq;
import cc.dfsoft.project.biz.base.settlement.entity.SettlementJonintlySign;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.constant.DataFilerSetUpDto;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;
import cc.dfsoft.uexpress.common.util.ResultUtil;
@Repository
public class SettlementJonintlySignDaoImpl extends NewBaseDAO<SettlementJonintlySign, String> implements SettlementJonintlySignDao{

	@Resource
	BusinessPartnersDao businessPartnersDao ;
	@Resource
	ProjectDao projectDao;
	@Override
	public Map<String, Object> querySettlementJonintlySign(SettlementDeclarationReq req) throws ParseException {
		Criteria c=super.getCriteria();
		if(StringUtils.isNotBlank(req.getProjId())){
			c.add(Restrictions.eq("projId",req.getProjId()));
		}
		
		//工程编号
		if(StringUtils.isNotBlank(req.getProjNo())){
			c.add(Restrictions.eq("projNo",req.getProjNo()));
		}
		
		//合同编号
		if(StringUtils.isNotBlank(req.getConNo())){
			c.add(Restrictions.like("conNo","%"+req.getConNo()+"%"));
		}
		if(StringUtils.isNotBlank(req.getProjName())){
			c.add(Restrictions.like("projName","%"+req.getProjName()+"%"));
		}
		
		if(StringUtils.isNotBlank(req.getProjAddr())){
			c.add(Restrictions.like("projAddr","%"+req.getProjAddr()+"%"));
		}
		
		 //用于字符串日期格式转换
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		 //受理日期开始日期
		 if(StringUtils.isNotBlank(req.getFinishDateStart())){
			 c.add(Restrictions.ge("finishDate", sdf.parse(req.getFinishDateStart())));
		 }
		 //受理日期结束日期
		 if(StringUtils.isNotBlank(req.getFinishDateEnd())){
			 c.add(Restrictions.le("finishDate", sdf.parse(req.getFinishDateEnd())));
		 }
		 
		 //汇签状态
		 if(StringUtils.isNotBlank(req.getSignStatus())){
			 c.add(Restrictions.eq("signStatus",req.getSignStatus()));
		 }
		 
		//打印状态
		 if(StringUtils.isNotBlank(req.getIsPrint())){
			 c.add(Restrictions.eq("isPrint",req.getIsPrint()));
		 }
		 LoginInfo loginInfo = SessionUtil.getLoginInfo();
		 //分包方单位人员登录时，只可看自己相关项目
		 BusinessPartners bp =  businessPartnersDao.get(loginInfo.getDeptId());
		 if(bp!=null){
			//若登录人是分包方单位人员
			 if(UnitTypeEnum.CONSTRUCTION_UNIT.getValue().equals(bp.getUnitType())&& loginInfo.getPost().length()>0){
				 StringBuffer sqlFilter = projectDao.cuUnitFilter(loginInfo,loginInfo.getPost(), req.getMenuId());
				 c.add(Restrictions.sqlRestriction(sqlFilter.toString()));
			 }else if(UnitTypeEnum.SUPERVISION_UNIT.getValue().equals(bp.getUnitType())){
				 //若登录人是监理单位
				 StringBuffer sqlFilter = projectDao.suUnitFilter(loginInfo, loginInfo.getPost(), req.getMenuId());
				 c.add(Restrictions.sqlRestriction(sqlFilter.toString()));
			 }else{
				 //其他业务合作伙伴-如检测单位
			 }
		 }else{ 
			 //不是分包单位按照公司ID查询
				 StringBuffer sql = new StringBuffer("proj_id in(select p.proj_id from project p where p.corp_id like '").append(loginInfo.getCorpId()).append("%'");
				 List<DataFilerSetUpDto> list = Constants.getDataFilterMapByKey(loginInfo.getDeptId()+"_"+req.getMenuId());  //查询配置条件，根据条件过滤
				 if(list != null && list.size() > 0){
					 sql.append(list.get(0).getSupSql()); 
				 }
				 sql.append(")");
				 c.add(Restrictions.sqlRestriction(sql.toString()));
		 }
	
		int filterCount = this.countByCriteria(c);
		
		// 根据条件获取查询信息
		List<SettlementJonintlySign> list = this.findBySortCriteria(c, req);
		
		return ResultUtil.pageResult(filterCount, req.getDraw(), list);
	}
	
	/**
	 * 根据工程id查询
	 */
	@Override
	public SettlementJonintlySign findById(String projId) {
		Criteria c=super.getCriteria();
		if(StringUtils.isNotBlank(projId)){
			c.add(Restrictions.eq("projId",projId));
			List<SettlementJonintlySign> list = this.findByCriteria(c);
			if(list!=null && list.size()>0){
				return list.get(0);
			}
		}
		return null;
	}
	
}
