package cc.dfsoft.project.biz.base.subpackage.dao.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.constant.DataFilerSetUpDto;
import cc.dfsoft.uexpress.common.util.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cc.dfsoft.project.biz.base.baseinfo.dao.BusinessPartnersDao;
import cc.dfsoft.project.biz.base.baseinfo.entity.BusinessPartners;
import cc.dfsoft.project.biz.base.budget.dto.SubBudgetReq;
import cc.dfsoft.project.biz.base.design.dto.DesignerQueryReq;
import cc.dfsoft.project.biz.base.design.enums.UnitTypeEnum;
import cc.dfsoft.project.biz.base.project.dao.ProjectDao;
import cc.dfsoft.project.biz.base.subpackage.dao.SubBudgetDao;
import cc.dfsoft.project.biz.base.subpackage.entity.SubBudget;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.biz.sys.dept.enums.DeleteOfMarkEnum;
import cc.dfsoft.uexpress.biz.sys.dept.enums.PostTypeEnum;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;
import cc.dfsoft.uexpress.common.util.ResultUtil;

@Repository
public class SubBudgetDaoImpl extends NewBaseDAO<SubBudget, String> implements SubBudgetDao{

    @Resource
    BusinessPartnersDao businessPartnersDao;
    @Resource
    ProjectDao projectDao;
	@Override
	public SubBudget findByProjId(String projId) {
		Criteria c = super.getCriteria();
		if(StringUtils.isNotBlank(projId)){
			c.add(Restrictions.eq("projId", projId));
			List<SubBudget> list = this.findByCriteria(c);
			if(list != null && list.size()>0){
				return list.get(0);
			}
		}
		return null;
	}

	@Override
	public Map<String, Object> querySubBudgetPrint(SubBudgetReq subBudgetReq) throws ParseException {
		 Criteria c = super.getCriteria();
		 //工程编号 
		 if(StringUtils.isNotBlank(subBudgetReq.getProjNo())){
			 c.add(Restrictions.eq("projNo",subBudgetReq.getProjNo()));
		 }
		 //工程名称
		 if(StringUtils.isNotBlank(subBudgetReq.getProjName())){
			 c.add(Restrictions.like("projName","%"+subBudgetReq.getProjName()+"%"));
		 }
		 //工程地点
		 if(StringUtils.isNotBlank(subBudgetReq.getProjAddr())){
			 c.add(Restrictions.like("projAddr","%"+subBudgetReq.getProjAddr()+"%"));
		 }
		 //工程id
		 if(StringUtils.isNotBlank(subBudgetReq.getProjId())){
			 c.add(Restrictions.eq("projId",subBudgetReq.getProjId()));
		 }
		 //用于字符串日期格式转换
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		 //预算日期开始
		 if(StringUtils.isNotBlank(subBudgetReq.getSbDateStart())){
			 c.add(Restrictions.ge("sbDate", sdf.parse(subBudgetReq.getSbDateStart())));
		 }
		 //预算日期结束
		 if(StringUtils.isNotBlank(subBudgetReq.getSbDateEnd())){
			 c.add(Restrictions.le("sbDate", sdf.parse(subBudgetReq.getSbDateEnd())));
		 }
		 //是否打印
		 if(StringUtils.isNotBlank(subBudgetReq.getIsprint())){
			 c.add(Restrictions.eq("isprint", subBudgetReq.getIsprint()));
		 }
		 //是否通过审核
		 if(StringUtils.isNotBlank(subBudgetReq.getAuditState())){
			 c.add(Restrictions.eq("auditState", subBudgetReq.getAuditState()));
		 }
		 
		 LoginInfo loginInfo = SessionUtil.getLoginInfo();
		 //分包方单位人员登录时，只可看自己相关项目
		 BusinessPartners bp =  businessPartnersDao.get(loginInfo.getDeptId());
		 if(bp!=null){
			//若登录人是分包方单位人员
			 if(UnitTypeEnum.CONSTRUCTION_UNIT.getValue().equals(bp.getUnitType())&& loginInfo.getPost().length()>0){
				 StringBuffer sqlFilter = projectDao.cuUnitFilter(loginInfo,loginInfo.getPost(), subBudgetReq.getMenuId());
				 c.add(Restrictions.sqlRestriction(sqlFilter.toString()));
			 }else if(UnitTypeEnum.SUPERVISION_UNIT.getValue().equals(bp.getUnitType())){
				 //若登录人是监理单位
				 StringBuffer sqlFilter = projectDao.suUnitFilter(loginInfo, loginInfo.getPost(), subBudgetReq.getMenuId());
				 c.add(Restrictions.sqlRestriction(sqlFilter.toString()));
			 }else{
				 //其他业务合作伙伴-如检测单位
			 }
		 }else{
			 boolean flag = true;

			 //过滤个人特殊配置
			 List<DataFilerSetUpDto> listCon = Constants.getDataFilterMapByKey(loginInfo.getStaffId()+"_"+subBudgetReq.getMenuId());
			 String filtersql = "";
			 if(listCon!=null && listCon.size()>0){
				 if(StringUtil.isNotBlank(listCon.get(0).getSupSql())){
					 filtersql = listCon.get(0).getSupSql();
					 flag = false;
				 }
			 }

			 String post=loginInfo.getPost();
			 String [] postArray=post.split(",");
			 StringBuffer sql=new StringBuffer();
			 if(post.contains(PostTypeEnum.BUDGET_MEMBER.getValue()) && flag){
				 //查看自己的预算记录
				 sql = sql.append("proj_id in(select p.proj_id from project p where p.budgeter_audit_id='").append(loginInfo.getStaffId()).append("'");
			 }else{
				 //如果不是预算员则按照公司ID查询
				 sql = sql.append("proj_id in(select p.proj_id from project p where p.corp_id='").append(loginInfo.getCorpId()).append("'");
			 }

			 if(StringUtil.isNotBlank(filtersql)){
				 sql.append(filtersql);
			 }
			 sql.append(")");
			 c.add(Restrictions.sqlRestriction(sql.toString()));
		 }
		 // 数据库根据条件过滤记录数
		 int filterCount = this.countByCriteria(c);

		 // 根据条件获取查询信息
		 List<SubBudget> subBudgetList = this.findBySortCriteria(c, subBudgetReq);
		 // 返回结果
		 return ResultUtil.pageResult( filterCount, subBudgetReq.getDraw(),subBudgetList);
	}

	@Override
	public Map<String, Object> queryBudgeter(DesignerQueryReq designerQueryReq) throws ParseException {
		//查询参数集合
				List<Object> params = new ArrayList<Object>();
				
				StringBuilder sql = new StringBuilder();
				sql.append("select s.STAFF_NAME budgeter,s.STAFF_ID budgeterId");
				//待预算记录
				if(StringUtils.isNotBlank(designerQueryReq.getProjStatus())){
					sql.append(",count(case when PROJ_STATUS_ID=? then PROJ_STATUS_ID end) budgeterTask");
					params.add(designerQueryReq.getProjStatus());
				}
				sql.append(" from project p right join staff s on (p.BUDGETER_AUDIT_ID = s.STAFF_ID) where 1=1");
				LoginInfo loginInfo=SessionUtil.getLoginInfo();
				//其他人员可查询自己部门的预算员
				sql.append(" and s.CORP_ID like ?");
				params.add(loginInfo.getCorpId()+"%");
				//职位类型
				if(StringUtils.isNotBlank(designerQueryReq.getPostType())){
					sql.append(" and s.POST like ?");
					params.add("%"+designerQueryReq.getPostType()+"%");
				}
				if(StringUtils.isNotBlank(designerQueryReq.getDeptId())){
					sql.append("and s.DEPT_ID like ?");
					params.add(designerQueryReq.getDeptId()+"%");
				}
				
				sql.append("and s.MARK_OF_DELETE = ?");
				params.add(DeleteOfMarkEnum.ON_THE_JOB.getValue());
				sql.append(" group by s.STAFF_NAME,s.STAFF_ID");
				
				List<Map<String, Object>> data = this.findListBySql(sql.toString(), params.toArray());
				return ResultUtil.pageResult(data.size(), designerQueryReq.getDraw(), data);
	}
	
}
