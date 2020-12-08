package cc.dfsoft.project.biz.base.settlement.dao.Impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cc.dfsoft.project.biz.base.baseinfo.dao.BusinessPartnersDao;
import cc.dfsoft.project.biz.base.baseinfo.entity.BusinessPartners;
import cc.dfsoft.project.biz.base.design.dto.DesignerQueryReq;
import cc.dfsoft.project.biz.base.design.enums.UnitTypeEnum;
import cc.dfsoft.project.biz.base.project.dao.ProjectDao;
import cc.dfsoft.project.biz.base.settlement.dao.SettlementDeclarationDao;
import cc.dfsoft.project.biz.base.settlement.dto.SettlementDeclarationReq;
import cc.dfsoft.project.biz.base.settlement.entity.SettlementDeclaration;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.biz.sys.dept.enums.DeleteOfMarkEnum;
import cc.dfsoft.uexpress.biz.sys.dept.enums.DeptDivideEnum;
import cc.dfsoft.uexpress.biz.sys.dept.enums.PostTypeEnum;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.constant.DataFilerSetUpDto;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;
import cc.dfsoft.uexpress.common.util.MoneyConverter;
import cc.dfsoft.uexpress.common.util.ResultUtil;

@Repository
public class SettlementDeclarationDaoImpl extends NewBaseDAO<SettlementDeclaration, String> implements SettlementDeclarationDao{
	
	@Resource
	BusinessPartnersDao businessPartnersDao;
	@Resource
	ProjectDao projectDao;
	
	@Override
	public SettlementDeclaration findByProjId(String projId) {
		SettlementDeclaration result = super.get("projId", projId);
		return result;
	}
	
	/**
	 * 根据工程Id查材料扣款
	 */
	@Override
	public SettlementDeclaration findSByprojId(String projId)throws ParseException {
		Criteria c = super.getCriteria();
		if(StringUtils.isNotBlank(projId)){
			c.add(Restrictions.eq("projId", projId));
			List<SettlementDeclaration> list = this.findByCriteria(c);
			if(list != null && list.size()>0){
				return list.get(0);
			}
		}
		return null;
	}

	@Override
	public Map<String, Object> querySettlementDeclaration(SettlementDeclarationReq settlementDeclarationReq)
			throws ParseException {
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		Criteria c = super.getCriteria();
		 //工程编号 
		 if(StringUtils.isNotBlank(settlementDeclarationReq.getProjNo())){
			 c.add(Restrictions.eq("projNo",settlementDeclarationReq.getProjNo()));
		 }
		 
		 //工程名称
		 if(StringUtils.isNotBlank(settlementDeclarationReq.getProjName())){
			 c.add(Restrictions.like("projName","%"+settlementDeclarationReq.getProjName()+"%"));
		 }
		 //施工地点
		 if(StringUtils.isNotBlank(settlementDeclarationReq.getProjAddr())){
			 c.add(Restrictions.like("projAddr","%"+settlementDeclarationReq.getProjAddr()+"%"));
		 }
		 
		 //工程id
		 if(StringUtils.isNotBlank(settlementDeclarationReq.getProjId())){
			 c.add(Restrictions.eq("projId",settlementDeclarationReq.getProjId()));
		 }
		 if(StringUtils.isNotBlank(settlementDeclarationReq.getAuditStatus())){
			 c.add(Restrictions.eq("auditStatus",settlementDeclarationReq.getAuditStatus()));
		 }
		 //客户名称
		 if(StringUtils.isNotBlank(settlementDeclarationReq.getCustName())){
			 c.add(Restrictions.eq("custName",settlementDeclarationReq.getCustName()));
		 }
		 //用于字符串日期格式转换
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		 //送审日期开始
		 if(StringUtils.isNotBlank(settlementDeclarationReq.getOcoDateStart())){
			 c.add(Restrictions.ge("ocoDate", sdf.parse(settlementDeclarationReq.getOcoDateStart())));
		 }
		 //送审日期结束
		 if(StringUtils.isNotBlank(settlementDeclarationReq.getOcoDateEnd())){
			 c.add(Restrictions.le("ocoDate", sdf.parse(settlementDeclarationReq.getOcoDateEnd())));
		 }
		 //初审日期开始
		 if(StringUtils.isNotBlank(settlementDeclarationReq.getFirstAuditDateStart())){
			 c.add(Restrictions.ge("firstAuditDate", sdf.parse(settlementDeclarationReq.getFirstAuditDateStart())));
		 }
		 //初审日期结束
		 if(StringUtils.isNotBlank(settlementDeclarationReq.getFirstAuditDateEnd())){
			 c.add(Restrictions.le("firstAuditDate", sdf.parse(settlementDeclarationReq.getFirstAuditDateEnd())));
		 }
		 //终审日期开始
		 if(StringUtils.isNotBlank(settlementDeclarationReq.getEndDeclaraDateStart())){
			 c.add(Restrictions.ge("endDeclaraDate", sdf.parse(settlementDeclarationReq.getEndDeclaraDateStart())));
		 }
		 //终审日期结束
		 if(StringUtils.isNotBlank(settlementDeclarationReq.getEndDeclaraDateEnd())){
			 c.add(Restrictions.le("endDeclaraDate", sdf.parse(settlementDeclarationReq.getEndDeclaraDateEnd())));
		 }
		 //工程状态
		 if(StringUtils.isNotBlank(settlementDeclarationReq.getProjStatusId())){
			 StringBuffer sql = new StringBuffer();
			 sql.append("proj_id in (select proj_id from project p where p.proj_Status_Id >'").append(settlementDeclarationReq.getProjStatusId()).append("')") ;
			 c.add(Restrictions.sqlRestriction(sql.toString()));
		 }
		 //是否打印
		 if(StringUtils.isNotBlank(settlementDeclarationReq.getIsPrint())){
			 c.add(Restrictions.eq("isPrint", settlementDeclarationReq.getIsPrint()));
		 }
		//分包方单位人员登录时，只可看自己相关项目
		 BusinessPartners bp =  businessPartnersDao.get(loginInfo.getDeptId());
		 if(bp!=null){
			//若登录人是分包方单位人员
			 if(UnitTypeEnum.CONSTRUCTION_UNIT.getValue().equals(bp.getUnitType())&& loginInfo.getPost().length()>0){
				 StringBuffer sqlFilter = projectDao.cuUnitFilter(loginInfo,loginInfo.getPost(), settlementDeclarationReq.getMenuId());
				 c.add(Restrictions.sqlRestriction(sqlFilter.toString()));
			 }else if(UnitTypeEnum.SUPERVISION_UNIT.getValue().equals(bp.getUnitType())){
				 //若登录人是监理单位
				 StringBuffer sqlFilter = projectDao.suUnitFilter(loginInfo, loginInfo.getPost(), settlementDeclarationReq.getMenuId());
				 c.add(Restrictions.sqlRestriction(sqlFilter.toString()));
			 }else{
				 //其他业务合作伙伴-如检测单位
			 }
		 }else{
			 //分公司ID
			 StringBuffer sql = new StringBuffer("proj_id in(select cp.proj_id from project cp where cp.corp_id like '").append(loginInfo.getCorpId()).append("%')");
			 c.add(Restrictions.sqlRestriction(sql.toString())); 
		 }
		 // 数据库根据条件过滤记录数
		 int filterCount = this.countByCriteria(c);

		 // 根据条件获取查询信息 
		 List<SettlementDeclaration> SettlementDeclarationList = this.findBySortCriteria(c, settlementDeclarationReq);
		 if(SettlementDeclarationList != null && SettlementDeclarationList.size()>0){
			 for(SettlementDeclaration settle :SettlementDeclarationList){
				 if(settle.getEndDeclarationCost()!=null){
					 settle.setLegalAmount(MoneyConverter.Num2RMB(settle.getEndDeclarationCost().doubleValue())); 
				 }
				 
			 }
		 }
		
		 // 返回结果
		 return ResultUtil.pageResult( filterCount, settlementDeclarationReq.getDraw(),SettlementDeclarationList);
	}

	@Override
	public Map<String, Object> querySettlement(SettlementDeclarationReq req, List list) throws ParseException {
		 //用于字符串日期格式转换
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		 Criteria c = super.getCriteria();		
		 c.add(Restrictions.in("projId", list));
		 //工程编号 
		 if(StringUtils.isNotBlank(req.getProjNo())){
			 c.add(Restrictions.eq("projNo",req.getProjNo()));
		 }
		//工程名称
		 if(StringUtils.isNotBlank(req.getProjName())){
			 c.add(Restrictions.like("projName","%"+req.getProjName()+"%"));
		 }
		 //施工地点
		 if(StringUtils.isNotBlank(req.getProjAddr())){
			 c.add(Restrictions.like("projAddr","%"+req.getProjAddr()+"%"));
		 }
		 //送审日期开始
		 if(StringUtils.isNotBlank(req.getOcoDateStart())){
			 c.add(Restrictions.ge("ocoDate", sdf.parse(req.getOcoDateStart())));
		 }
		 //送审日期结束
		 if(StringUtils.isNotBlank(req.getOcoDateEnd())){
			 c.add(Restrictions.le("ocoDate", sdf.parse(req.getOcoDateEnd())));
		 }
		 //初审日期开始
		 if(StringUtils.isNotBlank(req.getFirstAuditDateStart())){
			 c.add(Restrictions.ge("firstAuditDate", sdf.parse(req.getFirstAuditDateStart())));
		 }
		 //初审日期结束
		 if(StringUtils.isNotBlank(req.getFirstAuditDateEnd())){
			 c.add(Restrictions.le("firstAuditDate", sdf.parse(req.getFirstAuditDateEnd())));
		 }
		 //终审日期开始
		 if(StringUtils.isNotBlank(req.getEndDeclaraDateStart())){
			 c.add(Restrictions.ge("endDeclaraDate", sdf.parse(req.getEndDeclaraDateStart())));
		 }
		 //终审日期结束
		 if(StringUtils.isNotBlank(req.getEndDeclaraDateEnd())){
			 c.add(Restrictions.le("endDeclaraDate", sdf.parse(req.getEndDeclaraDateEnd())));
		 }
		 
		 
		 LoginInfo loginInfo=SessionUtil.getLoginInfo();
		 

		 
		 String post=loginInfo.getPost();
		 String [] postArray=post.split(",");
		
		 if(null!=post){
			 if(postArray.length>0){
				 if(post.contains(PostTypeEnum.BUDGET_MEMBER.getValue())){
					//结算初审:配置子公司查看的sql
					 List<DataFilerSetUpDto> corpBudget = Constants.getDataFilterMapByKey(loginInfo.getCorpId()+PostTypeEnum.BUDGET_MEMBER.getValue()+"_"+req.getMenuId());
					 if(corpBudget==null || corpBudget.size()==0){
						 corpBudget = Constants.getDataFilterMapByKey(loginInfo.getStaffId()+PostTypeEnum.BUDGET_MEMBER.getValue()+"_"+req.getMenuId());
					 }
					 if(corpBudget!=null && corpBudget.size()>0){
						 StringBuffer hql = new StringBuffer();
						 hql.append("proj_id in (select proj_id from project where 1=1 ");
						 if(StringUtils.isNotBlank(corpBudget.get(0).getSupSql())){
							 hql.append(corpBudget.get(0).getSupSql());
						 }
						 hql.append(")");
						 c.add(Restrictions.sqlRestriction(hql.toString()));
					 }else{
						//结算审核
						if(!post.contains(PostTypeEnum.BUDGET_GROUP_LEADER.getValue()) && !DeptDivideEnum.CUSTOMER_SERVICE_CENTER.getValue().equals(loginInfo.getDeptDivide())){
								//预算组长
							StringBuffer sql = new StringBuffer("proj_id in(select cp.proj_id from project cp where cp.settlementer_id='").append(loginInfo.getStaffId()).append("')");
							c.add(Restrictions.sqlRestriction(sql.toString()));
							
							//	c.add(Restrictions.eq("settlementerId", loginInfo.getStaffId()));
						}
				 	}
				 }
			 }
		 } 	 
		 
		// 数据库根据条件过滤记录数
		int filterCount = this.countByCriteria(c);
		// 根据条件获取查询信息
		List<SettlementDeclaration> settlementList = this.findBySortCriteria(c,req);
		// 返回结果
		return ResultUtil.pageResult( filterCount, req.getDraw(),settlementList);
		 
	}

	/**
	 * 查结算员
	 */
	@Override
	public Map<String, Object> queryBudgetMember(DesignerQueryReq designerQueryReq) {

		//查询参数集合
		List<Object> params = new ArrayList<Object>();
		
		StringBuilder sql = new StringBuilder();
		sql.append("select s.staff_name surveyer, sum(p.CONTRACT_AMOUNT) contractAmount");
		//待处理任务
		if(StringUtils.isNotBlank(designerQueryReq.getProjStatus())){
			sql.append(",count(case when proj_status_id=? then proj_status_id end) surveyRegister");
			params.add(designerQueryReq.getProjStatus());
		}


		sql.append(" from project p right join staff s on (p.SETTLEMENTER = s.STAFF_NAME) where 1=1");

		//职位类型
		if(StringUtils.isNotBlank(designerQueryReq.getPostType())){
			sql.append(" and s.post=?");
			params.add(designerQueryReq.getPostType());
		}

		
		sql.append(" group by s.staff_name");
		
		List<Map<String, Object>> data = this.findListBySql(sql.toString(), params.toArray());
		return ResultUtil.pageResult(data.size(), designerQueryReq.getDraw(), data);
	}

	@Override
	public Map<String, Object> queryCostManager(DesignerQueryReq designerQueryReq) {
		//查询参数集合
				List<Object> params = new ArrayList<Object>();
				
				StringBuilder sql = new StringBuilder();
				sql.append("select s.staff_name surveyer,s.staff_id surveyerId");
				//待处理任务
				if(StringUtils.isNotBlank(designerQueryReq.getProjStatus())){
					sql.append(",count(case when proj_status_id=? and m.MR_AUDIT_LEVEL is null then proj_status_id end) surveyRegister");
					params.add(designerQueryReq.getProjStatus());
				}

				sql.append(" from project p right join staff s on (p.COST_MANAGER = s.STAFF_NAME) left join MANAGE_RECORD m on ( s.STAFF_id = m.MR_CSR ) where 1=1");

				//所属单位
					sql.append(" and s.dept_id = '1105'");
				//职位类型
				if(StringUtils.isNotBlank(designerQueryReq.getPostType())){
					sql.append(" and s.post=?");
					params.add(designerQueryReq.getPostType());
				}
				if(StringUtils.isNotBlank(designerQueryReq.getPostType2())){
					sql.append(" or s.post=?");
					params.add(designerQueryReq.getPostType2());
				}
				
				sql.append(" group by s.staff_name");
				
				List<Map<String, Object>> data = this.findListBySql(sql.toString(), params.toArray());
				return ResultUtil.pageResult(data.size(), designerQueryReq.getDraw(), data);
			}

	@Override
	public Map<String, Object> querySettleMember(
			DesignerQueryReq designerQueryReq) {
		//查询参数集合
		List<Object> params = new ArrayList<Object>();
		
		StringBuilder sql = new StringBuilder();
		sql.append("select s.STAFF_NAME surveyer,s.STAFF_ID surveyerId");
		//待处理任务
		if(StringUtils.isNotBlank(designerQueryReq.getProjStatus())){
			sql.append(",count(DISTINCT case when PROJ_STATUS_ID=? and m.MR_AUDIT_LEVEL is null then PROJ_STATUS_ID end) surveyRegister");
			params.add(designerQueryReq.getProjStatus());
		}
		sql.append(" from project p right join staff s on (p.SETTLEMENTER_ID = s.STAFF_ID) left join MANAGE_RECORD m on ( s.STAFF_ID = m.MR_CSR AND m.PROJ_ID = p.PROJ_ID) where 1=1");
		LoginInfo loginInfo=SessionUtil.getLoginInfo();
		//todo:超级管理员查询时
		//其他人员可查询自己部门的预算员
	/*	sql.append(" and s.CORP_ID like ?");
		params.add(loginInfo.getCorpId()+"%");去掉条件能查询出预算员当前拥有多少条工程，不去掉则只能查询集团的工程*/
		//职位类型
		if(StringUtils.isNotBlank(designerQueryReq.getPostType())){
			sql.append(" and s.POST like ?");
			params.add("%"+designerQueryReq.getPostType()+"%");
		}
		//部门
		if(StringUtils.isNotBlank(designerQueryReq.getDeptId())){
			sql.append(" and s.DEPT_ID like ?");
			params.add(designerQueryReq.getDeptId()+"%");
		}
		sql.append(" and s.MARK_OF_DELETE = ?");
		params.add(DeleteOfMarkEnum.ON_THE_JOB.getValue());  //查询在职人员
		sql.append(" group by s.STAFF_NAME,s.STAFF_ID");
		
		List<Map<String, Object>> data = this.findListBySql(sql.toString(), params.toArray());
		return ResultUtil.pageResult(data.size(), designerQueryReq.getDraw(), data);
	}

}
