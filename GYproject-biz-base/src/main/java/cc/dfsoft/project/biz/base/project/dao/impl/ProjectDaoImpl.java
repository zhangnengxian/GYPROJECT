package cc.dfsoft.project.biz.base.project.dao.impl;

import cc.dfsoft.project.biz.base.baseinfo.dao.BusinessPartnersDao;
import cc.dfsoft.project.biz.base.baseinfo.entity.BusinessPartners;
import cc.dfsoft.project.biz.base.charge.enums.ARStatusEnum;
import cc.dfsoft.project.biz.base.charge.enums.ArContractTypeEnum;
import cc.dfsoft.project.biz.base.design.enums.UnitTypeEnum;
import cc.dfsoft.project.biz.base.project.dao.ProjectDao;
import cc.dfsoft.project.biz.base.project.dto.ProjectQueryReq;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.project.enums.ProjLtypeEnum;
import cc.dfsoft.project.biz.base.project.enums.ProjStatusEnum;
import cc.dfsoft.project.biz.base.project.enums.StepEnum;
import cc.dfsoft.project.biz.base.settlement.enums.AuditResultEnum;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dao.DepartmentDao;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.biz.sys.dept.entity.Department;
import cc.dfsoft.uexpress.biz.sys.dept.enums.BusinessTypeEnum;
import cc.dfsoft.uexpress.biz.sys.dept.enums.DeptDivideEnum;
import cc.dfsoft.uexpress.biz.sys.dept.enums.DeptTypeEnum;
import cc.dfsoft.uexpress.biz.sys.dept.enums.PostTypeEnum;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.constant.DataFilerSetUpDto;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;
import cc.dfsoft.uexpress.common.enums.WorkFlowActionEnum;
import cc.dfsoft.uexpress.common.util.ResultUtil;
import cc.dfsoft.uexpress.common.util.StringUtil;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


@Repository
public class ProjectDaoImpl extends NewBaseDAO<Project, String> implements ProjectDao {

	/**
	 * 注入mysqlSqlConveter类
	 */
	@Resource
	BusinessPartnersDao businessPartnersDao;

	/**部门Dao*/
	@Resource
	DepartmentDao departmentDao;

	@Override
	public Map<String, Object> queryProjectByStatus(ProjectQueryReq projectQueryReq,List<String> projStuList) throws ParseException {

		Criteria c = super.getCriteria();
		//工程编号


		if(StringUtils.isNotBlank(projectQueryReq.getProjNo())){
			c.add(Restrictions.eq("projNo",projectQueryReq.getProjNo()));
		}
		//工程状态
		if(StringUtils.isNotBlank(projectQueryReq.getProjStatusId())){
			c.add(Restrictions.eq("projStatusId",projectQueryReq.getProjStatusId()));
		}

		//出资方式
		if(StringUtils.isNotBlank(projectQueryReq.getContributionMode())){
			c.add(Restrictions.eq("contributionMode", projectQueryReq.getContributionMode())) ;
		}
		if(projStuList.size()>0){
			StringBuffer hql = new StringBuffer("from Project where projStatusId in ('");
			for(int i=1;i<projStuList.size();i++){
				hql.append(projStuList.get(i)).append("',").append("'");
			}
			hql.append(projStuList.get(0)).append("')");
			/*hql.append("and projId not in(select projId from SubSupplyContract)");*/
			List<Project> projects = super.findByHql(hql.toString());
			List<String> projIds = new ArrayList();
			if(projects!=null&&projects.size()>0){
				for(Project project : projects){
					projIds.add(project.getProjId());
				}
				c.add(Restrictions.in("projId", projIds));
			}else{
				//返回结果
				return ResultUtil.pageResult(0, projectQueryReq.getDraw(), new ArrayList());
			}
		}
		//工程大类
		if(StringUtils.isNotBlank(projectQueryReq.getProjLtypeId())){
			c.add(Restrictions.eq("projLtypeId",projectQueryReq.getProjLtypeId()));
		}
		//工程名称
		if(StringUtils.isNotBlank(projectQueryReq.getProjName())){
			c.add(Restrictions.like("projName", "%"+projectQueryReq.getProjName()+"%"));
			//instr写法用于代替like写法，提高查询效率
			//c.add(Restrictions.sqlRestriction("instr('projName','"+projectQueryReq.getProjName()+"',1,1)!=0"));
		}

		//客户名称
		if(StringUtils.isNotBlank(projectQueryReq.getCustName())){
			c.add(Restrictions.like("custName", "%"+projectQueryReq.getCustName()+"%"));
			//instr写法用于代替like写法，提高查询效率
			//c.add(Restrictions.sqlRestriction("instr('projName','"+projectQueryReq.getProjName()+"',1,1)!=0"));
		}

		//工程地点
		if(StringUtils.isNotBlank(projectQueryReq.getProjAddr())){
			c.add(Restrictions.like("projAddr", "%"+projectQueryReq.getProjAddr()+"%"));
		}
		//工程规模
		if(StringUtils.isNotBlank(projectQueryReq.getProjScaleDes())){
			c.add(Restrictions.like("projScaleDes", "%"+projectQueryReq.getProjScaleDes()+"%"));
		}
		//区域
		if(StringUtils.isNotBlank(projectQueryReq.getArea())){
			c.add(Restrictions.eq("area",projectQueryReq.getArea()));
		}
		//勘察人
		if(StringUtils.isNotBlank(projectQueryReq.getSurveyer())){
			c.add(Restrictions.like("surveyer", "%"+projectQueryReq.getSurveyer()+"%"));
		}
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		//选择接气方案时 ，只能显示当前当前操作者的工程
		 /*if (StringUtils.isNotBlank(projectQueryReq.getSideBarID()) && "110202".equals(projectQueryReq.getSideBarID())) {
			 c.add(Restrictions.eq("surveyerId", loginInfo.getStaffId()));
		 }
		 */
		// 当前登录操作者 管网工艺员
		if (StringUtil.isNotBlank(loginInfo.getPost()) && loginInfo.getPost().contains(PostTypeEnum.WEBSITE_ENGINEER.getValue())) {
			c.add(Restrictions.eq("area", loginInfo.getDeptId()));
		}

		//预算员
		if(StringUtils.isNotBlank(projectQueryReq.getBudgeter())){
			c.add(Restrictions.like("budgeter", "%"+projectQueryReq.getBudgeter()+"%"));
		}

		//造价员
		if(StringUtils.isNotBlank(projectQueryReq.getCostMember())){
			c.add(Restrictions.like("costMember", "%"+projectQueryReq.getCostMember()+"%"));
		}
		//结算员
		if(StringUtils.isNotBlank(projectQueryReq.getSettlementer())){
			c.add(Restrictions.like("settlementer", "%"+projectQueryReq.getSettlementer()+"%"));
		}
		//用于字符串日期格式转换
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		//受理日期开始日期
		if(StringUtils.isNotBlank(projectQueryReq.getAcceptDateStart())){
			c.add(Restrictions.ge("acceptDate", sdf.parse(projectQueryReq.getAcceptDateStart())));
		}
		//受理日期结束日期
		if(StringUtils.isNotBlank(projectQueryReq.getAcceptDateEnd())){
			c.add(Restrictions.le("acceptDate", sdf.parse(projectQueryReq.getAcceptDateEnd())));
		}
		//勘察完成开始日期
		if(StringUtils.isNotBlank(projectQueryReq.getSurveyDateStart())){
			c.add(Restrictions.ge("surveyDate", sdf.parse(projectQueryReq.getSurveyDateStart())));
		}
		//勘察完成结束日期
		if(StringUtils.isNotBlank(projectQueryReq.getSurveyDateEnd())){
			c.add(Restrictions.le("surveyDate", sdf.parse(projectQueryReq.getSurveyDateEnd())));
		}
		//设计完成开始日期
		if(StringUtils.isNotBlank(projectQueryReq.getDuCompleteDateStart())){
			c.add(Restrictions.ge("duCompleteDate", sdf.parse(projectQueryReq.getDuCompleteDateStart())));
		}
		//设计完成结束日期
		if(StringUtils.isNotBlank(projectQueryReq.getDuCompleteDateEnd())){
			c.add(Restrictions.le("duCompleteDate", sdf.parse(projectQueryReq.getDuCompleteDateEnd())));
		}

		//预算日期开始日期
		if(StringUtils.isNotBlank(projectQueryReq.getBudgetDateStart())){
			c.add(Restrictions.ge("budgetDate", sdf.parse(projectQueryReq.getBudgetDateStart())));
		}
		//预算日期结束日期
		if(StringUtils.isNotBlank(projectQueryReq.getBudgetDateEnd())){
			c.add(Restrictions.le("budgetDate", sdf.parse(projectQueryReq.getBudgetDateEnd())));
		}

		//合同签订开始日期
		if(StringUtils.isNotBlank(projectQueryReq.getContractSignDateStart())){
			c.add(Restrictions.ge("signDate", sdf.parse(projectQueryReq.getContractSignDateStart())));
		}
		//合同签订结束日期
		if(StringUtils.isNotBlank(projectQueryReq.getContractSignDateEnd())){
			c.add(Restrictions.le("signDate", sdf.parse(projectQueryReq.getContractSignDateEnd())));
		}
		//验收日期开始日期
		if(StringUtils.isNotBlank(projectQueryReq.getAcceptanceDateStart())){
			c.add(Restrictions.ge("acceptanceDate", sdf.parse(projectQueryReq.getAcceptanceDateStart())));
		}
		//验收日期结束日期
		if(StringUtils.isNotBlank(projectQueryReq.getAcceptanceDateEnd())){
			c.add(Restrictions.ge("acceptanceDate", sdf.parse(projectQueryReq.getAcceptanceDateEnd())));
		}
		//结算日期开始日期
		if(StringUtils.isNotBlank(projectQueryReq.getSettlementDateStart())){
			c.add(Restrictions.ge("settlementDate", sdf.parse(projectQueryReq.getSettlementDateStart())));
		}
		//结算日期结束日期
		if(StringUtils.isNotBlank(projectQueryReq.getSettlementDateEnd())){
			c.add(Restrictions.ge("settlementDate", sdf.parse(projectQueryReq.getSettlementDateEnd())));
		}
		//分包合同签定日期
		if(StringUtils.isNotBlank(projectQueryReq.getSubContractDateStart()) || StringUtils.isNotBlank(projectQueryReq.getSubContractDateEnd())){
			StringBuffer sql = new StringBuffer("select sc.proj_id from sub_contract sc where 1=1");
			//分包合同签定开始日期
			if(StringUtils.isNotBlank(projectQueryReq.getSubContractDateStart())){
				sql.append(" and sc.sc_sign_date>=").append(mysqlSqlConveter.dataOperate(projectQueryReq.getSubContractDateStart()));
			}
			//分包合同签定结束日期
			if(StringUtils.isNotBlank(projectQueryReq.getSubContractDateEnd())){
				sql.append(" and sc.sc_sign_date<=").append(mysqlSqlConveter.dataOperate(projectQueryReq.getSubContractDateEnd()));
			}
			StringBuffer resultSql = new StringBuffer("proj_id in(").append(sql.toString()).append(")");
			c.add(Restrictions.sqlRestriction(resultSql.toString()));
		}

		//分包方单位人员登录时，只可看自己相关项目
		BusinessPartners bp =  businessPartnersDao.get(loginInfo.getDeptId());
		if(bp!=null){
			//若登录人是施工方单位人员
			if(UnitTypeEnum.CONSTRUCTION_UNIT.getValue().equals(bp.getUnitType())){
				//通过施工单位id查询
				StringBuffer sql = new StringBuffer("proj_id in(select cp.proj_id from construction_plan cp where cp.cu_id='").append(loginInfo.getDeptId()).append("')");
				c.add(Restrictions.sqlRestriction(sql.toString()));
			}
		}
		//开工日期开始日期
		if(StringUtils.isNotBlank(projectQueryReq.getStartDateStart())){
			c.add(Restrictions.ge("startDate", sdf.parse(projectQueryReq.getStartDateStart())));
		}
		//开工日期结束日期
		if(StringUtils.isNotBlank(projectQueryReq.getStartDateEnd())){
			c.add(Restrictions.ge("startDate", sdf.parse(projectQueryReq.getStartDateEnd())));
		}
		//竣工日期开始日期
		if(StringUtils.isNotBlank(projectQueryReq.getCompleteDateStart())){
			c.add(Restrictions.ge("completedDate", sdf.parse(projectQueryReq.getCompleteDateStart())));
		}
		//竣工日期结束日期
		if(StringUtils.isNotBlank(projectQueryReq.getCompleteDateEnd())){
			c.add(Restrictions.ge("completedDate", sdf.parse(projectQueryReq.getCompleteDateEnd())));
		}
		//结束开始日期
		if(StringUtils.isNotBlank(projectQueryReq.getFinishedDateStart()) && StringUtils.isBlank(projectQueryReq.getFinishedDateEnd())){
			StringBuffer sql = new StringBuffer();
			sql.append("(finished_Date is null or finished_date>=").append(mysqlSqlConveter.dataOperate(projectQueryReq.getFinishedDateStart())).append(")");
			c.add(Restrictions.sqlRestriction(sql.toString()));
		}
		if(StringUtils.isNotBlank(projectQueryReq.getFinishedDateStart()) && StringUtils.isNotBlank(projectQueryReq.getFinishedDateEnd())){
			c.add(Restrictions.ge("finishedDate", sdf.parse(projectQueryReq.getFinishedDateStart())));
			c.add(Restrictions.ge("finishedDate", sdf.parse(projectQueryReq.getFinishedDateEnd())));
		}
		//结束截止日期
		if(StringUtils.isNotBlank(projectQueryReq.getFinishedDateEnd())){
			c.add(Restrictions.ge("finishedDate", sdf.parse(projectQueryReq.getFinishedDateEnd())));
		}
		//造价确认开始日期
		if(StringUtils.isNotBlank(projectQueryReq.getAffirmCostDateStart())){
			c.add(Restrictions.ge("affirmCostDate", sdf.parse(projectQueryReq.getAffirmCostDateStart())));
		}
		//造价确认结束日期
		if(StringUtils.isNotBlank(projectQueryReq.getAffirmCostDateEnd())){
			c.add(Restrictions.ge("affirmCostDate", sdf.parse(projectQueryReq.getAffirmCostDateEnd())));
		}
		//分包方名称
		if(StringUtils.isNotBlank(projectQueryReq.getCuName())){
			StringBuffer sql = new StringBuffer();
			sql.append("proj_id in(select cp.proj_id from CONSTRUCTION_PLAN cp where cp.CU_NAME like '%").append(projectQueryReq.getCuName()).append("%')");
			c.add(Restrictions.sqlRestriction(sql.toString()));
		}
		//设计员找自己的变更
		if(StringUtil.isNotBlank(loginInfo.getPost()) && ( loginInfo.getPost().contains(PostTypeEnum.DESIGNER.getValue())|| loginInfo.getPost().contains(PostTypeEnum.DESIGN_DIRECTOR.getValue()))){
			StringBuffer sql=new StringBuffer("proj_id in(select p.proj_id from project p where p.designer_id='").append(loginInfo.getStaffId()).append("')");
			c.add(Restrictions.sqlRestriction(sql.toString()));
		}

		c.addOrder(Order.desc("acceptDate"));  // 受理日期

		// 数据库根据条件过滤记录数
		int filterCount = this.countByCriteria(c);

		// 根据条件获取查询信息
		List<Project> projectList = this.findBySortCriteria(c, projectQueryReq);
		// 返回结果
		return ResultUtil.pageResult( filterCount, projectQueryReq.getDraw(),projectList);
	}

	/**
	 * 工程项目-工程列表查询
	 * @author
	 * @createTime
	 * @param
	 * @return
	 */
	@Override
	public Map<String, Object> queryProject(ProjectQueryReq projectQueryReq) throws ParseException {

		Criteria c = super.getCriteria();

		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		boolean flagProj = true;

		//分公司数据过滤 如
		List<DataFilerSetUpDto> list = Constants.getDataFilterMapByKey(loginInfo.getDeptId()+"_"+projectQueryReq.getMenuId());
		if(list!=null && list.size()>0){
			StringBuffer hql = new StringBuffer();
			hql.append("proj_id in (select proj_id from project where 1=1 ");

			if(StringUtils.isNotBlank(list.get(0).getSupSql())){
				hql.append(list.get(0).getSupSql());
			}
			hql.append(")");
			c.add(Restrictions.sqlRestriction(hql.toString()));
		}

		//根据工程编号名称查询时，可默认加载已竣工和终止的工程
		//工程编号
		if(StringUtils.isNotBlank(projectQueryReq.getProjNo())){
			c.add(Restrictions.eq("projNo",projectQueryReq.getProjNo()));
			flagProj = false;
		}
		//工程名称
		if(StringUtils.isNotBlank(projectQueryReq.getProjName())){
			c.add(Restrictions.like("projName", "%"+projectQueryReq.getProjName()+"%"));
			//instr写法用于代替like写法，提高查询效率
			//c.add(Restrictions.sqlRestriction("instr('projName','"+projectQueryReq.getProjName()+"',1,1)!=0"));
			flagProj = false;
		}
		//工程状态
		if(StringUtils.isNotBlank(projectQueryReq.getProjStatusId())){
			c.add(Restrictions.eq("projStatusId",projectQueryReq.getProjStatusId()));
		}else if(flagProj){
			//默认过滤掉终止的工程
			StringBuffer sql = new StringBuffer();
			sql.append("proj_status_id !='2001' and proj_status_id !='1036'");
			c.add(Restrictions.sqlRestriction(sql.toString()));
		}

		//业务部门
		if(StringUtils.isNotBlank(projectQueryReq.getDeptId())){
			c.add(Restrictions.eq("deptId",projectQueryReq.getDeptId()));
		}
		//工程类型
		if(StringUtils.isNotBlank(projectQueryReq.getProjectType())){
			c.add(Restrictions.like("projectType",projectQueryReq.getProjectType()));
		}
		//出资方式
		if(StringUtils.isNotBlank(projectQueryReq.getContributionMode())){
			c.add(Restrictions.like("contributionMode",projectQueryReq.getContributionMode()));
		}

		if(projectQueryReq.getProjStuList()!=null && projectQueryReq.getProjStuList().size()>0){
			List<String> projStus=projectQueryReq.getProjStuList();
			c.add(Restrictions.in("projStatusId",projStus));
		}


		//工程大类
		if(StringUtils.isNotBlank(projectQueryReq.getProjLtypeId())){
			c.add(Restrictions.like("projLtypeId","%"+projectQueryReq.getProjLtypeId()+"%"));
		}
		
		//工程地点
		if(StringUtils.isNotBlank(projectQueryReq.getProjAddr())){
			c.add(Restrictions.like("projAddr", "%"+projectQueryReq.getProjAddr()+"%"));
		}
		//工程规模
		if(StringUtils.isNotBlank(projectQueryReq.getProjScaleDes())){
			c.add(Restrictions.like("projScaleDes", "%"+projectQueryReq.getProjScaleDes()+"%"));
		}
		//区域
		if(StringUtils.isNotBlank(projectQueryReq.getArea())){
			c.add(Restrictions.eq("area",projectQueryReq.getArea()));
		}
		//勘察人
		if(StringUtils.isNotBlank(projectQueryReq.getSurveyer())){
			c.add(Restrictions.like("surveyer", "%"+projectQueryReq.getSurveyer()+"%"));
		}

		//设计人
		if(StringUtils.isNotBlank(projectQueryReq.getDesigner())){
			c.add(Restrictions.like("designer", "%"+projectQueryReq.getDesigner()+"%"));
		}


		//客户名称
		if(StringUtils.isNotBlank(projectQueryReq.getCustName())){
			c.add(Restrictions.like("custName", "%"+projectQueryReq.getCustName()+"%"));
			//instr写法用于代替like写法，提高查询效率
			//c.add(Restrictions.sqlRestriction("instr('projName','"+projectQueryReq.getProjName()+"',1,1)!=0"));
		}


		//预算员
		if(StringUtils.isNotBlank(projectQueryReq.getBudgeter())){
			c.add(Restrictions.like("budgeter", "%"+projectQueryReq.getBudgeter()+"%"));
		}
		if(StringUtils.isNotBlank(projectQueryReq.getBudgeterId())){
			c.add(Restrictions.eq("budgeterId", projectQueryReq.getBudgeterId()));
		}
		//造价员
		if(StringUtils.isNotBlank(projectQueryReq.getCostMember())){
			c.add(Restrictions.like("costMember", "%"+projectQueryReq.getCostMember()+"%"));
		}
		//结算员
		if(StringUtils.isNotBlank(projectQueryReq.getSettlementer())){
			c.add(Restrictions.like("settlementer", "%"+projectQueryReq.getSettlementer()+"%"));
		}
		//合同员
		if(StringUtils.isNotBlank(projectQueryReq.getOperator())){
			c.add(Restrictions.like("operator", "%"+projectQueryReq.getOperator()+"%"));
		}

		//合同编号
		if(StringUtils.isNotBlank(projectQueryReq.getConNo())){
			StringBuffer sql=new StringBuffer();
			sql.append("proj_id in(select c.proj_id from contract c where c.con_no like '%").append(projectQueryReq.getConNo()).append("%')");
			c.add(Restrictions.sqlRestriction(sql.toString()));
		}

		//用于字符串日期格式转换
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		//受理日期开始日期
		if(StringUtils.isNotBlank(projectQueryReq.getAcceptDateStart())){
			c.add(Restrictions.ge("acceptDate", sdf.parse(projectQueryReq.getAcceptDateStart())));
		}
		//受理日期结束日期
		if(StringUtils.isNotBlank(projectQueryReq.getAcceptDateEnd())){
			c.add(Restrictions.le("acceptDate", sdf.parse(projectQueryReq.getAcceptDateEnd())));
		}
		//勘察完成开始日期
		if(StringUtils.isNotBlank(projectQueryReq.getSurveyDateStart())){
			c.add(Restrictions.ge("surveyDate", sdf.parse(projectQueryReq.getSurveyDateStart())));
		}
		//勘察完成结束日期
		if(StringUtils.isNotBlank(projectQueryReq.getSurveyDateEnd())){
			c.add(Restrictions.le("surveyDate", sdf.parse(projectQueryReq.getSurveyDateEnd())));
		}
		//设计完成开始日期
		if(StringUtils.isNotBlank(projectQueryReq.getDuCompleteDateStart())){
			c.add(Restrictions.ge("duCompleteDate", sdf.parse(projectQueryReq.getDuCompleteDateStart())));
		}
		//设计完成结束日期
		if(StringUtils.isNotBlank(projectQueryReq.getDuCompleteDateEnd())){
			c.add(Restrictions.le("duCompleteDate", sdf.parse(projectQueryReq.getDuCompleteDateEnd())));
		}

		//预算日期开始日期
		if(StringUtils.isNotBlank(projectQueryReq.getBudgetDateStart())){
			c.add(Restrictions.ge("budgetDate", sdf.parse(projectQueryReq.getBudgetDateStart())));
		}
		//预算日期结束日期
		if(StringUtils.isNotBlank(projectQueryReq.getBudgetDateEnd())){
			c.add(Restrictions.le("budgetDate", sdf.parse(projectQueryReq.getBudgetDateEnd())));
		}

		/* //合同签订开始日期
		 if(StringUtils.isNotBlank(projectQueryReq.getContractSignDateStart())){
			 c.add(Restrictions.ge("signDate", sdf.parse(projectQueryReq.getContractSignDateStart())));
		 }
		 //合同签订结束日期
		 if(StringUtils.isNotBlank(projectQueryReq.getContractSignDateEnd())){
			 c.add(Restrictions.le("signDate", sdf.parse(projectQueryReq.getContractSignDateEnd())));
		 }*/
		//验收日期开始日期
		if(StringUtils.isNotBlank(projectQueryReq.getAcceptanceDateStart())){
			c.add(Restrictions.ge("acceptanceDate", sdf.parse(projectQueryReq.getAcceptanceDateStart())));
		}
		//验收日期结束日期
		if(StringUtils.isNotBlank(projectQueryReq.getAcceptanceDateEnd())){
			c.add(Restrictions.le("acceptanceDate", sdf.parse(projectQueryReq.getAcceptanceDateEnd())));
		}
		//结算日期开始日期
		if(StringUtils.isNotBlank(projectQueryReq.getSettlementDateStart())){
			c.add(Restrictions.ge("settlementDate", sdf.parse(projectQueryReq.getSettlementDateStart())));
		}
		//结算日期结束日期
		if(StringUtils.isNotBlank(projectQueryReq.getSettlementDateEnd())){
			c.add(Restrictions.le("settlementDate", sdf.parse(projectQueryReq.getSettlementDateEnd())));
		}
		//分包合同签定日期
		if(StringUtils.isNotBlank(projectQueryReq.getSubContractDateStart()) || StringUtils.isNotBlank(projectQueryReq.getSubContractDateEnd())){
			StringBuffer sql = new StringBuffer("select sc.proj_id from sub_contract sc where 1=1");
			//分包合同签定开始日期
			if(StringUtils.isNotBlank(projectQueryReq.getSubContractDateStart())){
				sql.append(" and sc.sc_sign_date>=").append(mysqlSqlConveter.dataOperate(projectQueryReq.getSubContractDateStart()));
			}
			//分包合同签定结束日期
			if(StringUtils.isNotBlank(projectQueryReq.getSubContractDateEnd())){
				sql.append(" and sc.sc_sign_date<=").append(mysqlSqlConveter.dataOperate(projectQueryReq.getSubContractDateEnd()));
			}
			StringBuffer resultSql = new StringBuffer("proj_id in(").append(sql.toString()).append(")");
			c.add(Restrictions.sqlRestriction(resultSql.toString()));
		}

		//施工合同签订日期
		if(StringUtils.isNotBlank(projectQueryReq.getContractSignDateStart())|| StringUtils.isNotBlank(projectQueryReq.getContractSignDateEnd())){
			StringBuffer sql = new StringBuffer("select c.proj_id from contract c where 1=1");
			//合同签定开始日期
			if(StringUtils.isNotBlank(projectQueryReq.getContractSignDateStart())){
				sql.append(" and c.sign_date>=").append(mysqlSqlConveter.dataOperate(projectQueryReq.getContractSignDateStart()));
			}
			//合同签定结束日期
			if(StringUtils.isNotBlank(projectQueryReq.getContractSignDateEnd())){
				sql.append(" and c.sign_date<=").append(mysqlSqlConveter.dataOperate(projectQueryReq.getContractSignDateEnd()));
			}
			StringBuffer resultSql = new StringBuffer("proj_id in(").append(sql.toString()).append(")");
			c.add(Restrictions.sqlRestriction(resultSql.toString()));
		}
		//计划查 开工日期-查合同表
		if(StringUtils.isNotBlank(projectQueryReq.getPlannedStartDateStart())|| StringUtils.isNotBlank(projectQueryReq.getPlannedStartDateEnd())){
			StringBuffer sql = new StringBuffer("select c.proj_id from contract c where 1=1");
			//开工日期开始日期
			if(StringUtils.isNotBlank(projectQueryReq.getPlannedStartDateStart())){
				sql.append(" and c.planned_start_date>=").append(mysqlSqlConveter.dataOperate(projectQueryReq.getPlannedStartDateStart()));
			}
			//开工日期结束日期
			if(StringUtils.isNotBlank(projectQueryReq.getPlannedStartDateEnd())){
				sql.append(" and c.planned_start_date<=").append(mysqlSqlConveter.dataOperate(projectQueryReq.getPlannedStartDateEnd()));
			}
			StringBuffer resultSql = new StringBuffer("proj_id in(").append(sql.toString()).append(")");
			c.add(Restrictions.sqlRestriction(resultSql.toString()));
		}

		//计划查 竣工日期-查合同表
		if(StringUtils.isNotBlank(projectQueryReq.getPlannedEndDateStart())|| StringUtils.isNotBlank(projectQueryReq.getPlannedEndDateEnd())){
			StringBuffer sql = new StringBuffer("select c.proj_id from contract c where 1=1");
			//合同签定开始日期
			if(StringUtils.isNotBlank(projectQueryReq.getPlannedEndDateStart())){
				sql.append(" and c.planned_end_date>=").append(mysqlSqlConveter.dataOperate(projectQueryReq.getPlannedEndDateStart()));
			}
			//合同签定结束日期
			if(StringUtils.isNotBlank(projectQueryReq.getPlannedEndDateEnd())){
				sql.append(" and c.planned_end_date<=").append(mysqlSqlConveter.dataOperate(projectQueryReq.getPlannedEndDateEnd()));
			}
			StringBuffer resultSql = new StringBuffer("proj_id in(").append(sql.toString()).append(")");
			c.add(Restrictions.sqlRestriction(resultSql.toString()));
		}


		//开工日期开始日期
		if(StringUtils.isNotBlank(projectQueryReq.getStartDateStart())){
			c.add(Restrictions.ge("startDate", sdf.parse(projectQueryReq.getStartDateStart())));
		}
		//开工日期结束日期
		if(StringUtils.isNotBlank(projectQueryReq.getStartDateEnd())){
			c.add(Restrictions.le("startDate", sdf.parse(projectQueryReq.getStartDateEnd())));
		}
		//竣工日期开始日期
		if(StringUtils.isNotBlank(projectQueryReq.getCompleteDateStart())){
			c.add(Restrictions.ge("completedDate", sdf.parse(projectQueryReq.getCompleteDateStart())));
		}
		//竣工日期结束日期
		if(StringUtils.isNotBlank(projectQueryReq.getCompleteDateEnd())){
			c.add(Restrictions.le("completedDate", sdf.parse(projectQueryReq.getCompleteDateEnd())));
		}
		//结束开始日期
		if(StringUtils.isNotBlank(projectQueryReq.getFinishedDateStart()) && StringUtils.isBlank(projectQueryReq.getFinishedDateEnd())){
			StringBuffer sql = new StringBuffer();
			sql.append("(finished_Date is null or finished_date>=").append(mysqlSqlConveter.dataOperate(projectQueryReq.getFinishedDateStart())).append(")");
			c.add(Restrictions.sqlRestriction(sql.toString()));
		}
		if(StringUtils.isNotBlank(projectQueryReq.getFinishedDateStart()) && StringUtils.isNotBlank(projectQueryReq.getFinishedDateEnd())){
			c.add(Restrictions.ge("finishedDate", sdf.parse(projectQueryReq.getFinishedDateStart())));
			c.add(Restrictions.le("finishedDate", sdf.parse(projectQueryReq.getFinishedDateEnd())));
		}
		//结束截止日期
		if(StringUtils.isNotBlank(projectQueryReq.getFinishedDateEnd())){
			c.add(Restrictions.ge("finishedDate", sdf.parse(projectQueryReq.getFinishedDateEnd())));
		}
		//造价确认开始日期
		if(StringUtils.isNotBlank(projectQueryReq.getAffirmCostDateStart())){
			c.add(Restrictions.ge("affirmCostDate", sdf.parse(projectQueryReq.getAffirmCostDateStart())));
		}
		//造价确认结束日期
		if(StringUtils.isNotBlank(projectQueryReq.getAffirmCostDateEnd())){
			c.add(Restrictions.le("affirmCostDate", sdf.parse(projectQueryReq.getAffirmCostDateEnd())));
		}
		//分包方名称
		if(StringUtils.isNotBlank(projectQueryReq.getCuName())){
			StringBuffer sql = new StringBuffer();
			sql.append("proj_id in(select cp.proj_id from CONSTRUCTION_PLAN cp where cp.CU_NAME like '%").append(projectQueryReq.getCuName()).append("%')");
			c.add(Restrictions.sqlRestriction(sql.toString()));
		}
		//分包方id
		if(StringUtils.isNotBlank(projectQueryReq.getCuId())){
			StringBuffer sql = new StringBuffer();
			sql.append("proj_id in(select cp.proj_id from CONSTRUCTION_PLAN cp where cp.CU_ID = '").append(projectQueryReq.getCuId()).append("')");
			c.add(Restrictions.sqlRestriction(sql.toString()));
		}

		//分包方单位人员登录时，只可看自己相关项目
		BusinessPartners bp =  businessPartnersDao.get(loginInfo.getDeptId());
		String post = loginInfo.getPost();
		boolean isConfigQuery=true;
		if(bp!=null){
			//若登录人是分包方单位人员
			if(UnitTypeEnum.CONSTRUCTION_UNIT.getValue().equals(bp.getUnitType())&& post.length()>0){
				StringBuffer sql = this.cuUnitFilter(loginInfo,post, projectQueryReq.getMenuId());
				c.add(Restrictions.sqlRestriction(sql.toString()));
			}else if(UnitTypeEnum.SUPERVISION_UNIT.getValue().equals(bp.getUnitType())){
				//若登录人是监理单位
				StringBuffer sql = this.suUnitFilter(loginInfo, post, projectQueryReq.getMenuId());
				c.add(Restrictions.sqlRestriction(sql.toString()));
			}else{
				//其他业务合作伙伴-如检测单位
			}
		}else{
			//根据人员配置来查询
			DataFilerSetUpDto config = Constants.isConfig(loginInfo.getStaffId() + "_" + projectQueryReq.getMenuId());
			if (config!=null){
				isConfigQuery= false;
				StringBuffer sql = new StringBuffer("proj_id in (select proj_id from project where 1=1 ");
				sql.append(config.getSupSql()).append(")");
				c.add(Restrictions.sqlRestriction(sql.toString()));
			}
		}



		boolean flag = true;

		boolean customerServiceCenterFlag = true;
		//非设计院且非业务合作伙伴
		if(!DeptDivideEnum.DESIGN_INSTITUTE.getValue().equals(loginInfo.getDeptDivide()) && bp==null && isConfigQuery){
			//所属公司id
			 /*if(StringUtils.isNotBlank(loginInfo.getCorpId())){
				 c.add(Restrictions.like("corpId",loginInfo.getCorpId()+"%"));
			 }*/

			//查询是否看全公司的数据 -施工预算等环节
			//工程预算派工-根据集团的配置查看相应的工程
			List<DataFilerSetUpDto> corpBudget = Constants.getDataFilterMapByKey(loginInfo.getCorpId()+"_"+loginInfo.getDeptId()+"_"+projectQueryReq.getMenuId());
			if(corpBudget!=null && corpBudget.size()>0){
				StringBuffer hql = new StringBuffer();
				hql.append("proj_id in (select proj_id from project where 1=1 ");
				if(StringUtils.isNotBlank(corpBudget.get(0).getSupSql())){
					hql.append(corpBudget.get(0).getSupSql());
				}
				hql.append(")");
				c.add(Restrictions.sqlRestriction(hql.toString()));
				flag = false;
			}else{
				c.add(Restrictions.like("corpId",loginInfo.getCorpId()+"%"));
			}
			//造价员，查看派工给自己的 造价确认和合同签订走这个方法
			if(post!=null && post.length()>0 && post.contains(PostTypeEnum.SALESMA.getValue())){
				List<DataFilerSetUpDto> costerList = Constants.getDataFilterMapByKey(loginInfo.getCorpId()+PostTypeEnum.SALESMA.getValue()+"_"+projectQueryReq.getMenuId());
				if(costerList!=null && costerList.size()>0){
					if(StringUtils.isNotBlank(costerList.get(0).getSupSql())){
						c.add(Restrictions.eq(costerList.get(0).getSupSql(), loginInfo.getStaffId()));
					}
				}
				//现场踏勘环节市场营销员可看自己的 或者自己部门立项的
				List<DataFilerSetUpDto> sureyList = Constants.getDataFilterMapByKey(loginInfo.getDeptId()+PostTypeEnum.SALESMA.getValue()+"_"+projectQueryReq.getMenuId());
				if(sureyList!=null && sureyList.size()>0){
					StringBuffer hql = new StringBuffer();
					hql.append("proj_id in (select proj_id from project p where 1=1 and (p.ACCEPTER_ID = '").append(loginInfo.getStaffId()).append("'");
					if(StringUtils.isNotBlank(sureyList.get(0).getSupSql())){
						hql.append(sureyList.get(0).getSupSql());
					}
					hql.append("))");
					c.add(Restrictions.sqlRestriction(hql.toString()));
				}
				flag = false;
			}
			if(BusinessTypeEnum.BUSINESS_GROUP.getValue().equals(loginInfo.getBusinessType())){	 //业务部门
				//智能表组过滤
				if(DeptDivideEnum.INTELLIGENT_METER.getValue().equals(loginInfo.getDeptDivide())){
					c.add(Restrictions.eq("isIntelligentMeter","1"));
				}else{
					//施工预算审核是否有数据，没有则按原来的走
					if(flag){
						String deptId = loginInfo.getDeptId().substring(0, loginInfo.getDeptId().length() - DeptTypeEnum.SUBCOMPANY.getInitVal().length());
						c.add(Restrictions.like("deptId", deptId+"%"));
					}
				}
			}else if(DeptDivideEnum.CUSTOMER_SERVICE_CENTER.getValue().equals(loginInfo.getDeptDivide())){//客服中心
				//安装合同时 客服中心 踏勘员  过滤看自己的安装合同；
				if(StringUtils.isNotBlank(projectQueryReq.getProjStatusId()) && projectQueryReq.getProjStatusId().equals(ProjStatusEnum.TO_SIGN_CONTRACT.getValue())){
					if(post.contains(PostTypeEnum.SURVEYER.getValue())){
						c.add(Restrictions.eq("surveyerId", loginInfo.getStaffId()));
					}
				}
				if(StringUtil.isNotBlank(loginInfo.getPost()) && (loginInfo.getPost().contains(PostTypeEnum.RECORDER.getValue())|| loginInfo.getPost().contains(PostTypeEnum.CHIEF_ENGINEER.getValue()))){

					String deptId =loginInfo.getDeptId();
					c.add(Restrictions.like("deptId", "%"+deptId+"%"));
				}else{
					String deptId = loginInfo.getDeptId().substring(0, loginInfo.getDeptId().length() - DeptTypeEnum.SUBCOMPANY.getInitVal().length());
					c.add(Restrictions.like("deptId", "%"+deptId+"%"));
				}
				//客服中心组长，某些菜单下配置可见工程
				if(post.contains(PostTypeEnum.GROUP_LEADER.getValue())){
					List<DataFilerSetUpDto> filterList = Constants.getDataFilterMapByKey(loginInfo.getDeptId()+PostTypeEnum.GROUP_LEADER.getValue()+"_"+projectQueryReq.getMenuId());
					if(filterList!=null && filterList.size()>0){
						customerServiceCenterFlag = false;
					}
				}
			}else if(DeptDivideEnum.ENGINEERING_CONSTRUCTION_CENTER.getValue().equals(loginInfo.getDeptDivide())){
				//工程建设中心
				c.add(Restrictions.like("deptId", loginInfo.getDeptId()+"%"));
			}else{
				//施工预算审核派工是否有数据，没有则按原来的走,预算派工

				if(DeptDivideEnum.CIVIL_GROUP.getValue().equals(loginInfo.getDeptDivide())||DeptDivideEnum.MARKETING_CENTER.getValue().equals(loginInfo.getDeptDivide())||
						DeptDivideEnum.TRUNK_GROUP .getValue().equals(loginInfo.getDeptDivide())||DeptDivideEnum.MODIFICATION_GROUP.getValue().equals(loginInfo.getDeptDivide())){
					//工程部门 民用 公建 改管 干线

					c.add(Restrictions.sqlRestriction(addDeptIdLikeQuery(loginInfo.getDeptId(),loginInfo.getDeptTransfer())));//部门条件查询

				}else if(DeptDivideEnum.MARKET_DEVELOPMENT_DEPARTMENT.getValue().equals(loginInfo.getDeptDivide()) && flag){	//如果是集团市场发展部的受理部门

					c.add(Restrictions.sqlRestriction(addDeptIdLikeQuery(loginInfo.getDeptId(),loginInfo.getDeptTransfer())));//部门条件查询

				}else if(flag){
					String deptId = loginInfo.getDeptId().substring(0, loginInfo.getDeptId().length() - DeptTypeEnum.SUBCOMPANY.getInitVal().length());
					c.add(Restrictions.like("deptId", "%"+deptId+"%"));
				}
			}
		}else if(DeptDivideEnum.DESIGN_INSTITUTE.getValue().equals(loginInfo.getDeptDivide()) && isConfigQuery){
			//设计单位设计员踏勘审核，图纸审核时，可根据配置sql查看工程
			List<DataFilerSetUpDto> designerUnit = Constants.getDataFilterMapByKey(loginInfo.getCorpId()+"_"+PostTypeEnum.DESIGNER.getValue()+"_"+projectQueryReq.getMenuId());
			boolean flagDesigner = true;
			if(designerUnit!=null && designerUnit.size()>0){
				StringBuffer hql = new StringBuffer();
				hql.append("proj_id in (select proj_id from project p where 1=1 ");
				if(StringUtils.isNotBlank(designerUnit.get(0).getSupSql())){
					hql.append(designerUnit.get(0).getSupSql());
				}
				hql.append(")");
				c.add(Restrictions.sqlRestriction(hql.toString()));
				flagDesigner = false;
			}
			if(flagDesigner){
				//市场发展部工程
				//c.add(Restrictions.like("deptId", "11__01%"));
				//查看所属分公司工程
				//设计院查看数据根据配置sql过滤
				List<DataFilerSetUpDto> designUnit = Constants.getDataFilterMapByKey(loginInfo.getCorpId()+"_0");
				if(designUnit!=null && designUnit.size()>0){
					StringBuffer hql = new StringBuffer();
					hql.append("proj_id in (select proj_id from project p where 1=1 ");
					if(StringUtils.isNotBlank(designUnit.get(0).getSupSql())){
						hql.append(designUnit.get(0).getSupSql());
					}
					hql.append(")");
					c.add(Restrictions.sqlRestriction(hql.toString()));
				}
			}
			//根据配置的所属分公司ID过滤
			if(StringUtil.isNotBlank(loginInfo.getBelongCorpId())){
				String[] belongCorpIds = loginInfo.getBelongCorpId().split(",");
				if(belongCorpIds!=null && belongCorpIds.length>0){
					c.add(Restrictions.in("corpId",belongCorpIds));
				}
			}
		}

		Department dept=new Department();
		List<Object> deptIdArray=new ArrayList<>();




		DataFilerSetUpDto dfsud = Constants.isConfig(loginInfo.getStaffId()+"_posts_"+projectQueryReq.getMenuId());
		boolean isPostsFlag=dfsud!=null?false:true;
		if (dfsud!=null && StringUtils.isNotBlank(dfsud.getSupSql())){//配置格式为:surveyer_Id =${XXX} or designer_Id =${XXX}
			String sql=dfsud.getSupSql().replaceAll("\\$\\{[^}]+\\}","'"+loginInfo.getStaffId()+"'");
			c.add(Restrictions.sqlRestriction(sql));
		}

		if(null!=post && isPostsFlag){
			if(post.length()>0){

				//现场代表兼踏勘员查看踏勘员自己的工程和现场代表的工程
				if(post.contains(PostTypeEnum.SURVEYER.getValue()) /*&& !(post.contains(PostTypeEnum.MINISTER.getValue()) || post.contains(PostTypeEnum.VICE_MINISTER.getValue()))*/){
					//现场踏勘  资料收集 图纸签收 图纸审核 踏勘员只看自己的
					List<DataFilerSetUpDto> surveyerList = Constants.getDataFilterMapByKey(PostTypeEnum.SURVEYER.getValue()+"_"+projectQueryReq.getMenuId());
					if(surveyerList!=null && surveyerList.size()>0){
						if(StringUtils.isNotBlank(projectQueryReq.getMenuId())&& projectQueryReq.getMenuId().equals(surveyerList.get(0).getMenuId())){
							  if ((post.contains(PostTypeEnum.BUILDER.getValue()) && ProjStatusEnum.TO_RESULT_REGISTRY.getValue().equals(projectQueryReq.getProjStatusId()))){//现场代表，现场踏勘
								c.add(Restrictions.or(Restrictions.eq("surveyerId", loginInfo.getStaffId()),Restrictions.eq("surveyBuilderId", loginInfo.getStaffId())));
							}else if((post.contains(PostTypeEnum.BUILDER.getValue()) && ProjStatusEnum.TO_DRAWING.getValue().equals(projectQueryReq.getProjStatusId()))){ //现场代表、踏勘员图纸审核看自己的工程
								List<DataFilerSetUpDto> listByStaffQueryRecord = Constants.getDataFilterMapByKey(SessionUtil.getLoginInfo().getStaffId()+"_"+projectQueryReq.getMenuId()); // 根据staffId_menuID查询记录
									if (listByStaffQueryRecord!=null && listByStaffQueryRecord.size() > 0) {
										if(StringUtil.isNotBlank(listByStaffQueryRecord.get(0).getSupSql())){
											c.add(Restrictions.sqlRestriction(listByStaffQueryRecord.get(0).getSupSql().toString()));
										}
									} else {
										c.add(Restrictions.or(Restrictions.eq("surveyerId", loginInfo.getStaffId()),Restrictions.eq("surveyBuilderId", loginInfo.getStaffId())));
									}
							}else{
								//配置踏勘员进行资料收集时可以看什么工程
								List<DataFilerSetUpDto> surveyerFindDocoment = Constants.getDataFilterMapByKey(loginInfo.getDeptId()+PostTypeEnum.BUSINESSASSISTANT.getValue()+"_"+projectQueryReq.getMenuId());
								if(surveyerFindDocoment !=null && surveyerFindDocoment.size() > 0 ){
									if (StringUtils.isNotBlank(surveyerFindDocoment.get(0).getSupSql())) {
										c.add(Restrictions.sqlRestriction(surveyerFindDocoment.get(0).getSupSql()));
									}
								}else{
									c.add(Restrictions.eq("surveyerId", loginInfo.getStaffId()));
								}

							}
						}
					}
					//贵安踏勘员看自己的项目
					List<DataFilerSetUpDto> corpSurveyerList = Constants.getDataFilterMapByKey(loginInfo.getCorpId()+PostTypeEnum.SURVEYER.getValue()+"_"+projectQueryReq.getMenuId());
					if(corpSurveyerList!=null && corpSurveyerList.size()>0){
						c.add(Restrictions.eq(corpSurveyerList.get(0).getSupSql(), loginInfo.getStaffId()));
					}

					if(ProjStatusEnum.TO_DETERMINE_COST.getValue().equals(projectQueryReq.getProjStatusId())){
						//工程造价
						if(!post.contains(PostTypeEnum.MODIFICATION_GROUP_LEADER.getValue())){
							c.add(Restrictions.eq("surveyerId", loginInfo.getStaffId()));
						}
					}
				}

				//市场营销员
				if(post.contains(PostTypeEnum.SALESMA.getValue())){

				}



				//现场代表
				if((post.contains(PostTypeEnum.BUILDER.getValue())
						&&! ProjStatusEnum.TO_RESULT_REGISTRY.getValue().equals(projectQueryReq.getProjStatusId())
						&&! ProjStatusEnum.TO_SURVEY.getValue().equals(projectQueryReq.getProjStatusId())
						&& !ProjStatusEnum.TO_DATA_ACQUISITION.getValue().equals(projectQueryReq.getProjStatusId())
						&& !ProjStatusEnum.TO_DETERMINE_COST.getValue().equals(projectQueryReq.getProjStatusId())
						&& !ProjStatusEnum.TO_DETERMINE_COST.getValue().equals(projectQueryReq.getHomePageRole())
						&& !ProjStatusEnum.TO_FORMAL_DRAW.getValue().equals(projectQueryReq.getProjStatusId())
						&& !ProjStatusEnum.TO_BUDGET_RESULT_REGISTRATION.getValue().equals(projectQueryReq.getProjStatusId())
						&& !ProjStatusEnum.TO_PROJECT_COST_AUDIT.getValue().equals(projectQueryReq.getProjStatusId())
						&& !ProjStatusEnum.TO_SIGN_CONTRACT.getValue().equals(projectQueryReq.getProjStatusId())
						&& !ProjStatusEnum.TO_AUDIT_CONTRACT.getValue().equals(projectQueryReq.getProjStatusId())
						&& !ProjStatusEnum.REPORT_DONE_CONFIRM.getValue().equals(projectQueryReq.getProjStatusId())
						&& !ProjStatusEnum.TO_BUDGET_DISPATCH.getValue().equals(projectQueryReq.getProjStatusId())
						&& !ProjStatusEnum.TO_AUDIT_AMOUNT.getValue().equals(projectQueryReq.getProjStatusId())
						&& !ProjStatusEnum.TO_DRAWING.getValue().equals(projectQueryReq.getProjStatusId())
						&& !ProjStatusEnum.TO_BUDGET_AUDIT.getValue().equals(projectQueryReq.getProjStatusId())
						&& !ProjStatusEnum.TO_MAKE_PLAN.getValue().equals(projectQueryReq.getProjStatusId())
						&& !ProjStatusEnum.TO_PLAN_AUDIT.getValue().equals(projectQueryReq.getProjStatusId())
						&& !ProjStatusEnum.TO_DETERMINE_DISPATCH.getValue().equals(projectQueryReq.getProjStatusId())
						&& !ProjStatusEnum.AUDIT_DONE_DISPATCH.getValue().equals(projectQueryReq.getProjStatusId())
						&& !ProjStatusEnum.REPORT_DONE_CONFIRM.getValue().equals(projectQueryReq.getProjStatusId())
						//&& !ProjStatusEnum.TO_COMPLETION_DATA_AUDIT.getValue().equals(projectQueryReq.getProjStatusId())
						&& !ProjStatusEnum.TO_BUDGET_GOV_AUDIT_COST.getValue().equals(projectQueryReq.getProjStatusId())) && flag && customerServiceCenterFlag){

					if(!(post.contains(PostTypeEnum.BUSINESSASSISTANT.getValue()))&&!(post.contains(PostTypeEnum.PROJECT_LEADER.getValue()))){
						StringBuffer sql = new StringBuffer("proj_id in(select cp.proj_id from construction_plan cp where cp.BUILDER_ID='").append(loginInfo.getStaffId()).append("')");
						c.add(Restrictions.sqlRestriction(sql.toString()));
					}
				}


				if(post.contains(PostTypeEnum.BUDGET_MEMBER.getValue())){
					//预算员
					if(StepEnum.BUDGET_RESULT_REGISTER.getValue().equals(projectQueryReq.getStepId())){
						//预算结果登记
						List<DataFilerSetUpDto>  budgetFindProject= Constants.getDataFilterMapByKey(loginInfo.getDeptId()+PostTypeEnum.BUDGET_MEMBER.getValue()+"_"+projectQueryReq.getMenuId());
						if(budgetFindProject != null && budgetFindProject.size() > 0){
							if(StringUtil.isNotBlank(budgetFindProject.get(0).getSupSql())){
								c.add(Restrictions.sqlRestriction(budgetFindProject.get(0).getSupSql()));
							}
						}else{
							c.add(Restrictions.eq("budgeterId", loginInfo.getStaffId()));
						}
					}
					if(StepEnum.QUALITIES_JUDGEMENT.getValue().equals(projectQueryReq.getStepId()) && !DeptDivideEnum.CUSTOMER_SERVICE_CENTER.getValue().equals(loginInfo.getDeptDivide())){
						//施工预算审核
						if(!post.contains(PostTypeEnum.BUDGET_GROUP_LEADER.getValue())){
							//预算组长
							c.add(Restrictions.eq("budgeterAuditId", loginInfo.getStaffId()));
						}
					}
					if(StepEnum.REPORT_DONE_CONFIRM.getValue().equals(projectQueryReq.getStepId()) && !DeptDivideEnum.CUSTOMER_SERVICE_CENTER.getValue().equals(loginInfo.getDeptDivide())){
						//结算审核
						if(!post.contains(PostTypeEnum.BUDGET_GROUP_LEADER.getValue())){
							//预算组长
							c.add(Restrictions.eq("settlementerId", loginInfo.getStaffId()));
						}
					}
				}

				if(post.contains(PostTypeEnum.DESIGNER.getValue())){
					//现场踏勘 设计出图按设计员过滤
					List<DataFilerSetUpDto> designerList = Constants.getDataFilterMapByKey(PostTypeEnum.DESIGNER.getValue()+"_"+projectQueryReq.getMenuId());
					if(designerList!=null && designerList.size()>0){
						if(StringUtils.isNotBlank(projectQueryReq.getMenuId())&& projectQueryReq.getMenuId().equals(designerList.get(0).getMenuId())){
							c.add(Restrictions.eq("designerId", loginInfo.getStaffId()));
						}
					}
				}


			}
		}


		List<Object> projecTypeArray=new ArrayList<>();
		if(post.length()>0 && isPostsFlag){
			if(post.contains(PostTypeEnum.CIVIL_HEAD.getValue())){
				//查库 是否存在，否则走集团按deptid查询 主要判断分管副总
				List<DataFilerSetUpDto> civilList = Constants.getDataFilterMapByKey(loginInfo.getCorpId()+PostTypeEnum.CIVIL_HEAD.getValue()+"_"+ProjLtypeEnum.CIVILIAN.getValue());
				if(civilList!=null && civilList.size()>0){
					projecTypeArray.add(ProjLtypeEnum.CIVILIAN.getValue());
				}else{
					//民用负责人
					dept=departmentDao.queryDepartmentByDivide(DeptDivideEnum.CIVIL_GROUP.getValue(),loginInfo.getDeptId());
					if(dept!=null && StringUtils.isNotBlank(dept.getDeptId())){
						deptIdArray.add(dept.getDeptId());
					}
				}
			}
			if(post.contains(PostTypeEnum.MARKETING_CENTER_HEAD.getValue())){
				//查库 是否存在，否则走集团按deptid查询
				List<DataFilerSetUpDto> publiclList = Constants.getDataFilterMapByKey(loginInfo.getCorpId()+PostTypeEnum.MARKETING_CENTER_HEAD.getValue()+"_"+ProjLtypeEnum.PUBLIC.getValue());
				if(publiclList!=null && publiclList.size()>0){
					projecTypeArray.add(ProjLtypeEnum.PUBLIC.getValue());
				}else{
					//公建负责人
					dept=departmentDao.queryDepartmentByDivide(DeptDivideEnum.MARKETING_CENTER.getValue(),loginInfo.getDeptId());
					if(dept!=null && StringUtils.isNotBlank(dept.getDeptId())){
						deptIdArray.add(dept.getDeptId());
					}
				}
			}
			if(post.contains(PostTypeEnum.MODIFICATION_HEAD.getValue())){
				//查库 是否存在，否则走集团按deptid查询
				List<DataFilerSetUpDto> modifyList = Constants.getDataFilterMapByKey(loginInfo.getCorpId()+PostTypeEnum.MODIFICATION_HEAD.getValue()+"_"+ProjLtypeEnum.PIPE.getValue());
				if(modifyList!=null && modifyList.size()>0){
					projecTypeArray.add(ProjLtypeEnum.PIPE.getValue());
				}else{
					//改管负责人
					dept=departmentDao.queryDepartmentByDivide(DeptDivideEnum.MODIFICATION_GROUP.getValue(),loginInfo.getDeptId());
					if(dept!=null && StringUtils.isNotBlank(dept.getDeptId())){
						deptIdArray.add(dept.getDeptId());
					}
				}

			}
			if(post.contains(PostTypeEnum.TRUNK_HEAD.getValue())){
				List<DataFilerSetUpDto> trunkList = Constants.getDataFilterMapByKey(loginInfo.getCorpId()+PostTypeEnum.TRUNK_HEAD.getValue()+"_"+ProjLtypeEnum.TRUNK.getValue());
				if(trunkList!=null && trunkList.size()>0){
					projecTypeArray.add(ProjLtypeEnum.TRUNK.getValue());
				}else{
					//干线负责人
					dept=departmentDao.queryDepartmentByDivide(DeptDivideEnum.TRUNK_GROUP.getValue(),loginInfo.getDeptId());
					if(dept!=null && StringUtils.isNotBlank(dept.getDeptId())){
						deptIdArray.add(dept.getDeptId());
					}
				}
			}

			boolean isProjId=true;
			DataFilerSetUpDto staffIdAndDeptIdAndMenuId = Constants.isConfig(loginInfo.getStaffId() +"_"+loginInfo.getDeptId()+ "_" + projectQueryReq.getMenuId());
			if (staffIdAndDeptIdAndMenuId!=null){
				isProjId=false;
				if (StringUtils.isNotBlank(staffIdAndDeptIdAndMenuId.getSupSql())) {
					c.add(Restrictions.sqlRestriction(staffIdAndDeptIdAndMenuId.getSupSql()));
				}
			}

			if(deptIdArray!=null && deptIdArray.size()>0 && isProjId){
				StringBuffer hql = new StringBuffer("from Project where deptId in ('");
				for(int i=1;i<deptIdArray.size();i++){
					hql.append(deptIdArray.get(i)).append("',").append("'");
				}
				hql.append(deptIdArray.get(0)).append("')");
				List<Project> projects = super.findByHql(hql.toString());

				List<String> projIds = new ArrayList();
				if(projects!=null&&projects.size()>0){
					for(Project project:projects){
						projIds.add(project.getProjId());
					}
					c.add(Restrictions.in("projId",projIds));
				}
			}

			if(projecTypeArray!=null && projecTypeArray.size()>0){
				StringBuffer hql = new StringBuffer(" proj_id in (select proj_id from Project where project_type in ('");
				for(int i=1;i<projecTypeArray.size();i++){
					hql.append(projecTypeArray.get(i)).append("',").append("'");
				}
				hql.append(projecTypeArray.get(0)).append("'))");
				c.add(Restrictions.sqlRestriction(hql.toString()));
			}

		}
		//是否需要更正
		if(StringUtils.isNotBlank(projectQueryReq.getIsModify())){
			c.add(Restrictions.eq("isModify", projectQueryReq.getIsModify()));
		}

		//反馈状态
		if(StringUtils.isNotBlank(projectQueryReq.getFeedbackState())){
			c.add(Restrictions.eq("feedbackState",projectQueryReq.getFeedbackState()));
		}

		//智能表标记
		if(StringUtils.isNotBlank(projectQueryReq.getIsIntelligentMeter())){
			c.add(Restrictions.eq("isIntelligentMeter",projectQueryReq.getIsIntelligentMeter()));
		}

		//现场代表
		if(StringUtils.isNotBlank(projectQueryReq.getBuilder())){
			StringBuffer sql = new StringBuffer("proj_id in(select cp.proj_id from construction_plan cp where cp.BUILDER like '%").append(projectQueryReq.getBuilder()).append("%')");
			c.add(Restrictions.sqlRestriction(sql.toString()));
		}
		//现场监理
		if(StringUtils.isNotBlank(projectQueryReq.getSuJgj())){
			StringBuffer sql = new StringBuffer("proj_id in(select cp.proj_id from construction_plan cp where cp.SU_JGJ like '%").append(projectQueryReq.getSuJgj()).append("%')");
			c.add(Restrictions.sqlRestriction(sql.toString()));
		}
		//分包预算审核员
		if(StringUtils.isNotBlank(projectQueryReq.getBudgeterAudit())){
			c.add(Restrictions.like("budgeterAudit","%"+projectQueryReq.getBudgeterAudit()+"%"));
		}
		//电子图申请审核状态
		if(StringUtils.isNotBlank(projectQueryReq.getAuditState())){
			if(!projectQueryReq.getAuditState().equals(AuditResultEnum.NOT_APPLY.getValue())){
				StringBuffer sql = new StringBuffer("proj_id in(select cp.proj_id from DRAWING_APPLY cp where cp.AUDIT_STATE='").append(projectQueryReq.getAuditState()).append("')");
				c.add(Restrictions.sqlRestriction(sql.toString()));
			}
		}
		//主合同已有收款的工程
		if(StringUtil.isNotBlank(projectQueryReq.getArStatus()) && ARStatusEnum.ALREADY_CHARGE.getValue().equals(projectQueryReq.getArStatus())){
			String sql = "EXISTS ( SELECT cf.proj_id FROM cash_flow cf WHERE cf.proj_id = this_.proj_id AND cf.IS_VALID = '1' and cf.CONTRACT_TYPE='"+ArContractTypeEnum.CONSTRUCTION.getValue()+"')";
			c.add(Restrictions.sqlRestriction(sql));
		}
		c.addOrder(Order.desc("acceptDate"));  // 受理日期

		// 数据库根据条件过滤记录数
		int filterCount = this.countByCriteria(c);

		// 根据条件获取查询信息
		List<Project> projectList = this.findBySortCriteria(c, projectQueryReq);
		// 返回结果
		return ResultUtil.pageResult( filterCount, projectQueryReq.getDraw(),projectList);
	}


	@Override
	public String addDeptIdLikeQuery(String deptId, String deptTransfer) {
		StringBuilder sql=new StringBuilder();
		sql.append("( dept_transfer like '").append(deptId+"%'");
		if (StringUtils.isNotBlank(deptTransfer)){
			String[] deptTransfers = deptTransfer.split(",");
			for (int i = 0; i <deptTransfers.length ; i++) {
				sql.append(" or dept_transfer like '").append(deptTransfers[i]+"%'");
			}
		}
		sql.append(")");
		return sql.toString();
	}

	@Override
	public List<Project> findByProjNo(String projNo) {

		StringBuffer hql = new StringBuffer();
		hql.append("from Project where projNo = '").append(projNo).append("'");
		return super.findByHql(hql.toString());
	}

	@Override
	public String getProjNo() {
		StringBuffer sql = new StringBuffer();
		String projNo = "01";
		//sql.append("select p.proj_no from project p where trunc(p.ACCEPT_DATE) = trunc(sysdate) order by p.proj_no desc");
		sql.append("select p.proj_no from project p where DATE_FORMAT(p.ACCEPT_DATE,'%Y-%m-%d') = DATE_FORMAT(sysdate(),'%Y-%m-%d') order by p.proj_no desc");
		List list = super.findBySql(sql.toString());
		if(list!=null && list.size()!=0){
			String currentNo = (String) list.get(0);
			if(StringUtils.isNotBlank(currentNo) && currentNo.length()>2){
				currentNo = currentNo.substring(currentNo.length()-2);
				int no = Integer.parseInt(currentNo);
				no = no+1;
				if(no<10){
					projNo = "0"+no;
				}else{
					projNo = ""+no;
				}
			}
		}
		return projNo;
	}

	/**
	 * 工程施工-查询工程列表
	 * @author
	 * @createTime
	 * @param
	 * @return
	 */
	@Override
	public Map<String, Object> queryProject(ProjectQueryReq projectQueryReq, String[] projStatusId)
			throws ParseException {

		Criteria c = super.getCriteria();
		//工程编号
		if(StringUtils.isNotBlank(projectQueryReq.getProjNo())){
			c.add(Restrictions.like("projNo","%"+projectQueryReq.getProjNo()+"%"));
		}
		//出资方式
		if(StringUtils.isNotBlank(projectQueryReq.getContributionMode())){
			c.add(Restrictions.eq("contributionMode",projectQueryReq.getContributionMode()));
		}
		//工程状态条件
		if(StringUtils.isNotBlank(projectQueryReq.getProjStatusId())){
			c.add(Restrictions.eq("projStatusId",projectQueryReq.getProjStatusId()));
		}else{

			//按工程状态过滤
			if(projectQueryReq.getProjStuList()!=null && projectQueryReq.getProjStuList().size()>0){
				List<String> projStus=projectQueryReq.getProjStuList();
				StringBuffer hql = new StringBuffer("from Project where projStatusId = '");
				for(int i=0;i<projStus.size();i++){
					if(i==0){
						hql.append(projStus.get(i)).append("'");
					}else{
						hql.append(" or projStatusId ='").append(projStus.get(i)).append("'");
					}
				}
				List<Project> projects = super.findByHql(hql.toString());
				List<String> projIds = new ArrayList();
				if(projects.size()>0){
					for(Project project:projects){
						projIds.add(project.getProjId());
					}
					c.add(Restrictions.in("projId",projIds));
				}else{
					// 返回结果
					return ResultUtil.pageResult( 0, projectQueryReq.getDraw(),new ArrayList());
				}
			}
		}

		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		//分公司数据过滤 如
		boolean flag = true;
		List<DataFilerSetUpDto> filterlist = Constants.getDataFilterMapByKey(loginInfo.getDeptId()+"_"+projectQueryReq.getMenuId());
		if(filterlist!=null && filterlist.size()>0){
			StringBuffer hql = new StringBuffer();
			hql.append("proj_id in (select proj_id from project where 1=1 ");

			if(StringUtils.isNotBlank(filterlist.get(0).getSupSql())){
				hql.append(filterlist.get(0).getSupSql());
			}
			hql.append(")");
			c.add(Restrictions.sqlRestriction(hql.toString()));
			flag= false;
		}

		//分包合同签定日期
		if(StringUtils.isNotBlank(projectQueryReq.getSubContractDateStart()) || StringUtils.isNotBlank(projectQueryReq.getSubContractDateEnd())){
			StringBuffer sql = new StringBuffer("select sc.proj_id from sub_contract sc where 1=1");
			//分包合同签定开始日期
			if(StringUtils.isNotBlank(projectQueryReq.getSubContractDateStart())){
				sql.append(" and sc.sc_sign_date>=").append(mysqlSqlConveter.dataOperate(projectQueryReq.getSubContractDateStart()));
			}
			//分包合同签定结束日期
			if(StringUtils.isNotBlank(projectQueryReq.getSubContractDateEnd())){
				sql.append(" and sc.sc_sign_date<=").append(mysqlSqlConveter.dataOperate(projectQueryReq.getSubContractDateEnd()));
			}
			StringBuffer resultSql = new StringBuffer("proj_id in(").append(sql.toString()).append(")");
			c.add(Restrictions.sqlRestriction(resultSql.toString()));
		}
		//工程大类
		if(StringUtils.isNotBlank(projectQueryReq.getProjLtypeId())){
			c.add(Restrictions.eq("projLtypeId",projectQueryReq.getProjLtypeId()));
		}
		//工程名称
		if(StringUtils.isNotBlank(projectQueryReq.getProjName())){
			c.add(Restrictions.like("projName", "%"+projectQueryReq.getProjName()+"%"));
			//instr写法用于代替like写法，提高查询效率
			//c.add(Restrictions.sqlRestriction("instr('projName','"+projectQueryReq.getProjName()+"',1,1)!=0"));
		}
		//工程地点
		if(StringUtils.isNotBlank(projectQueryReq.getProjAddr())){
			c.add(Restrictions.like("projAddr", "%"+projectQueryReq.getProjAddr()+"%"));
		}
		//用于字符串日期格式转换
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		//开工日期开始日期
		if(StringUtils.isNotBlank(projectQueryReq.getStartDateStart())){
			c.add(Restrictions.ge("startDate", sdf.parse(projectQueryReq.getStartDateStart())));
		}
		//开工日期结束日期
		if(StringUtils.isNotBlank(projectQueryReq.getStartDateEnd())){
			c.add(Restrictions.ge("startDate", sdf.parse(projectQueryReq.getStartDateEnd())));
		}
		//竣工日期开始日期
		if(StringUtils.isNotBlank(projectQueryReq.getCompleteDateStart())){
			c.add(Restrictions.ge("completedDate", sdf.parse(projectQueryReq.getCompleteDateStart())));
		}
		//竣工日期结束日期
		if(StringUtils.isNotBlank(projectQueryReq.getCompleteDateEnd())){
			c.add(Restrictions.ge("completedDate", sdf.parse(projectQueryReq.getCompleteDateEnd())));
		}

		// 当前登录操作者
		 /*String deptId = loginInfo.getDeptId();
		 if ("110201".equals(deptId) || "110202".equals(deptId) ||"110203".equals(deptId)
				 ||"110204".equals(deptId) ||"110205".equals(deptId) ) {
		    c.add(Restrictions.eq("area", loginInfo.getDeptId()));
		 }*/

		//财务查询
		if(StringUtils.isNotBlank(projectQueryReq.getArFlag()) || StringUtils.isNotBlank(projectQueryReq.getArStatus())|| StringUtils.isNotBlank(projectQueryReq.getArPayStatus())|| StringUtils.isNotBlank(projectQueryReq.getCollectionType())){
			StringBuffer sql=new StringBuffer("select ar.proj_id from accruals_record ar where ar.proj_id = this_.proj_id ");
			//财务收付过滤
			if(StringUtils.isNotBlank(projectQueryReq.getArFlag())){
				sql.append("and ar.ar_flag='").append(projectQueryReq.getArFlag()).append("'");
			}
			//待收费、已收费
			if(StringUtils.isNotBlank(projectQueryReq.getArStatus())){
				sql.append("and ar.ar_status='").append(projectQueryReq.getArStatus()).append("'");
			}

			//是否智能表
			if(StringUtils.isNotBlank(projectQueryReq.getContractType())){
				sql.append("and ar.contract_type='").append(projectQueryReq.getContractType()).append("'");
			}
			//收付类型
			if(StringUtils.isNotBlank(projectQueryReq.getCollectionType())){
				sql.append("and ar.ar_type='").append(projectQueryReq.getCollectionType()).append("'");
			}
			StringBuffer resultSql = new StringBuffer("EXISTS (").append(sql.toString()).append(")");
			c.add(Restrictions.sqlRestriction(resultSql.toString()));
		}

		//工程施工报表屏  选择加载施工处
		if(StringUtils.isNotBlank(projectQueryReq.getManagementOfficeId())){
			StringBuffer sql=new StringBuffer();
			sql.append("proj_id in(select cp.proj_id from construction_plan cp where cp.management_id = '").append(projectQueryReq.getManagementOfficeId()).append("')");
			c.add(Restrictions.sqlRestriction(sql.toString()));
		}

		//工程施工报表屏  选择加载分包单位
		if(StringUtils.isNotBlank(projectQueryReq.getCuId())){
			StringBuffer sql=new StringBuffer();
			sql.append("proj_id in(select cp.proj_id from construction_plan cp where cp.cu_id = '").append(projectQueryReq.getCuId()).append("')");
			c.add(Restrictions.sqlRestriction(sql.toString()));
		}
		//分包方名称
		if(StringUtils.isNotBlank(projectQueryReq.getCuName())){
			StringBuffer sql = new StringBuffer();
			sql.append("proj_id in(select cp.proj_id from CONSTRUCTION_PLAN cp where cp.CU_NAME like '%").append(projectQueryReq.getCuName()).append("%')");
			c.add(Restrictions.sqlRestriction(sql.toString()));
		}

		//施工名称处查询
		if(StringUtils.isNotBlank(projectQueryReq.getManagementOffice())){
			StringBuffer sql=new StringBuffer();
			sql.append("proj_id in(select cp.proj_id from construction_plan cp where cp.management_office like '%").append(projectQueryReq.getManagementOffice()).append("%')");
			c.add(Restrictions.sqlRestriction(sql.toString()));
		}
		//发货状态
		if(StringUtils.isNotBlank(projectQueryReq.getGoodsCompleteStatus())){
			c.add(Restrictions.eq("goodsCompleteStatus",projectQueryReq.getGoodsCompleteStatus()));
		}

		//分包方单位人员登录时，只可看自己相关项目
		BusinessPartners bp =  businessPartnersDao.get(loginInfo.getDeptId());


		Department dept=new Department();
		List<Object> deptIdArray=new ArrayList<>();

		String post=loginInfo.getPost();

		if(bp!=null){
			//若登录人是分包方单位人员
			if(UnitTypeEnum.CONSTRUCTION_UNIT.getValue().equals(bp.getUnitType())&& post.length()>0){
				StringBuffer sql = this.cuUnitFilter(loginInfo,post, projectQueryReq.getMenuId());
				c.add(Restrictions.sqlRestriction(sql.toString()));
			}else if(UnitTypeEnum.SUPERVISION_UNIT.getValue().equals(bp.getUnitType())){
				//若登录人是监理单位
				StringBuffer sql = this.suUnitFilter(loginInfo, post, projectQueryReq.getMenuId());
				c.add(Restrictions.sqlRestriction(sql.toString()));
			}else{
				//其他业务合作伙伴-如检测单位
			}
		}


		//非设计院且非业务合作伙伴
		if(!DeptDivideEnum.DESIGN_INSTITUTE.getValue().equals(loginInfo.getDeptDivide()) && bp==null){

			DataFilerSetUpDto config = Constants.isConfig(loginInfo.getStaffId() + "_" +projectQueryReq.getMenuId());
			if (config!=null && StringUtils.isNotBlank(config.getSupSql())){
				StringBuffer hql = new StringBuffer();
				hql.append("proj_id in (select proj_id from project where 1=1 ").append(config.getSupSql()).append(")");
				c.add(Restrictions.sqlRestriction(hql.toString()));
				flag=false;
			}

			if (flag) {
				//所属公司id
				if (StringUtils.isNotBlank(loginInfo.getCorpId())) {
					c.add(Restrictions.like("corpId", loginInfo.getCorpId() + "%"));
				}
				//综合组或技术组
				if (DeptDivideEnum.COMPREHENSIVE_GROUP.getValue().equals(loginInfo.getDeptDivide()) || DeptDivideEnum.TECHNOLOGY_GROUP.getValue().equals(loginInfo.getDeptDivide())
						|| DeptDivideEnum.PRE_SETTLEMENT_GROUP.getValue().equals(loginInfo.getDeptDivide()) || DeptDivideEnum.QUALITY_SAFETY_GROUP.getValue().equals(loginInfo.getDeptDivide())) {
					String deptId = loginInfo.getDeptId().substring(0, loginInfo.getDeptId().length() - DeptTypeEnum.SUBCOMPANY.getInitVal().length());
					c.add(Restrictions.like("deptId", "%" + deptId + "%"));
				} else if (DeptDivideEnum.CUSTOMER_SERVICE_CENTER.getValue().equals(loginInfo.getDeptDivide())) {

					if (StringUtil.isNotBlank(loginInfo.getPost()) && (loginInfo.getPost().contains(PostTypeEnum.RECORDER.getValue()) || loginInfo.getPost().contains(PostTypeEnum.CHIEF_ENGINEER.getValue()))) {
						//副主任或主任
						String deptId = loginInfo.getDeptId();
						c.add(Restrictions.like("deptId", "%" + deptId + "%"));
					} else {
						//客服中心
						String deptId = loginInfo.getDeptId().substring(0, loginInfo.getDeptId().length() - DeptTypeEnum.SUBCOMPANY.getInitVal().length());
						c.add(Restrictions.like("deptId", "%" + deptId + "%"));
					}
				} else if (DeptDivideEnum.INTELLIGENT_METER.getValue().equals(loginInfo.getDeptDivide())) {
					//智能表组过滤
					c.add(Restrictions.eq("isIntelligentMeter", "1"));
				} else {
					if (DeptDivideEnum.CIVIL_GROUP.getValue().equals(loginInfo.getDeptDivide()) || DeptDivideEnum.MARKETING_CENTER.getValue().equals(loginInfo.getDeptDivide()) ||
							DeptDivideEnum.TRUNK_GROUP.getValue().equals(loginInfo.getDeptDivide()) || DeptDivideEnum.MODIFICATION_GROUP.getValue().equals(loginInfo.getDeptDivide()) || DeptDivideEnum.MARKET_DEVELOPMENT_DEPARTMENT.getValue().equals(loginInfo.getDeptDivide())) {
						//营销中心、改管、干线、民用
						//String deptId = loginInfo.getDeptId();
						c.add(Restrictions.sqlRestriction(addDeptIdLikeQuery(loginInfo.getDeptId(),loginInfo.getDeptTransfer())));//部门条件查询
						//c.add(Restrictions.like("deptId", deptId + "%"));
					} else if (DeptDivideEnum.MARKET_DEVELOPMENT_DEPARTMENT.getValue().equals(loginInfo.getDeptDivide()) && flag) {
						//如果是集团市场发展部的受理部门
						//c.add(Restrictions.like("deptId", loginInfo.getDeptId() + "%"));
						c.add(Restrictions.sqlRestriction(addDeptIdLikeQuery(loginInfo.getDeptId(),loginInfo.getDeptTransfer())));//部门条件查询
					} else if (flag) {
						String deptId = loginInfo.getDeptId().substring(0, loginInfo.getDeptId().length() - DeptTypeEnum.SUBCOMPANY.getInitVal().length());
						c.add(Restrictions.like("deptId", "%" + deptId + "%"));
					}
				}
			}
		}


		if(null!=post && post.length()>0){
			if(post.contains(PostTypeEnum.BUILDER.getValue())){
				if(!post.contains(PostTypeEnum.MARKETING_CENTER_LEADER_DEPUTY.getValue())
						&& !post.contains(PostTypeEnum.MODIFICATION_GROUP_LEADER.getValue())
						&& !post.contains(PostTypeEnum.TRUNK_GROUP_LEADER.getValue())
						&& !post.contains(PostTypeEnum.DEPUTY_LEADER.getValue())
						&& !post.contains(PostTypeEnum.MINISTER.getValue())){
					StringBuffer sql = new StringBuffer("proj_id in(select cp.proj_id from construction_plan cp where cp.BUILDER_ID='").append(loginInfo.getStaffId()).append("')");
					c.add(Restrictions.sqlRestriction(sql.toString()));
				}
			}
		}

		if(post.length()>0){
			if(post.contains(PostTypeEnum.CIVIL_HEAD.getValue())){
				//民用负责人
				dept=departmentDao.queryDepartmentByDivide(DeptDivideEnum.CIVIL_GROUP.getValue(),loginInfo.getDeptId());
				if(dept!=null && StringUtils.isNotBlank(dept.getDeptId())){
					deptIdArray.add(dept.getDeptId());
				}
			}
			if(post.contains(PostTypeEnum.MARKETING_CENTER_HEAD.getValue())){
				//公建负责人
				dept=departmentDao.queryDepartmentByDivide(DeptDivideEnum.MARKETING_CENTER.getValue(),loginInfo.getDeptId());
				if(dept!=null && StringUtils.isNotBlank(dept.getDeptId())){
					deptIdArray.add(dept.getDeptId());
				}
			}
			if(post.contains(PostTypeEnum.MODIFICATION_HEAD.getValue())){
				//改管负责人
				dept=departmentDao.queryDepartmentByDivide(DeptDivideEnum.MODIFICATION_GROUP.getValue(),loginInfo.getDeptId());
				if(dept!=null && StringUtils.isNotBlank(dept.getDeptId())){
					deptIdArray.add(dept.getDeptId());
				}
			}
			if(post.contains(PostTypeEnum.TRUNK_HEAD.getValue())){
				//干线负责人
				dept=departmentDao.queryDepartmentByDivide(DeptDivideEnum.TRUNK_GROUP.getValue(),loginInfo.getDeptId());
				if(dept!=null && StringUtils.isNotBlank(dept.getDeptId())){
					deptIdArray.add(dept.getDeptId());
				}
			}


			if(deptIdArray!=null && deptIdArray.size()>0){
				StringBuffer hql = new StringBuffer("from Project where deptId in ('");
				for(int i=1;i<deptIdArray.size();i++){
					hql.append(deptIdArray.get(i)).append("',").append("'");
				}
				hql.append(deptIdArray.get(0)).append("')");
				List<Project> projects = super.findByHql(hql.toString());
				List<String> projIds = new ArrayList();
				if(projects!=null&&projects.size()>0){
					for(Project project:projects){
						projIds.add(project.getProjId());
					}
					c.add(Restrictions.in("projId",projIds));
				}
			}
		}
		//设计员
		if(StringUtil.isNotBlank(loginInfo.getPost()) && loginInfo.getPost().contains(PostTypeEnum.DESIGNER.getValue())){
			c.add(Restrictions.eq("designerId", loginInfo.getStaffId()));
		}
		if(StringUtil.isBlank(projectQueryReq.getSortName())){
			c.addOrder(Order.desc("acceptDate"));
		}


		Map<String, Object> resultMap=new HashMap<>();
		if (projectQueryReq.getRedFlag()!=null&&projectQueryReq.getRedFlag()){
			resultMap.put("data",c.list());
			return resultMap;
		}

		// 数据库根据条件过滤记录数
		int filterCount = this.countByCriteria(c);

		// 根据条件获取查询信息
		List<Project> projectList = this.findBySortCriteria(c, projectQueryReq);
		// 返回结果
		return ResultUtil.pageResult( filterCount, projectQueryReq.getDraw(),projectList);
	}

	/**
	 * 工程分布
	 */
	@Override
	public Map<String, Object> queryDistributionProject(ProjectQueryReq projectQueryReq) throws ParseException {
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		Criteria c = super.getCriteria();
		boolean projStatusflag = false;
		//分公司数据过滤 如
		List<DataFilerSetUpDto> list = Constants.getDataFilterMapByKey(loginInfo.getDeptId()+"_"+projectQueryReq.getMenuId());
		if(list!=null && list.size()>0){
			StringBuffer hql = new StringBuffer();
			hql.append("proj_id in (select proj_id from project where 1=1 ");

			if(StringUtils.isNotBlank(list.get(0).getSupSql())){
				hql.append(list.get(0).getSupSql());
			}
			hql.append(")");
			c.add(Restrictions.sqlRestriction(hql.toString()));
		}
		//工程编号
		if(StringUtils.isNotBlank(projectQueryReq.getProjNo())){
			c.add(Restrictions.eq("projNo",projectQueryReq.getProjNo()));
			projStatusflag = true;
		}
		//工程状态条件
		if(StringUtils.isNotBlank(projectQueryReq.getProjStatusId())){
			c.add(Restrictions.eq("projStatusId",projectQueryReq.getProjStatusId()));
			projStatusflag = true;
		}

		//工程大类
		if(StringUtils.isNotBlank(projectQueryReq.getProjLtypeId())){
			c.add(Restrictions.eq("projLtypeId",projectQueryReq.getProjLtypeId()));
			projStatusflag = true;
		}
		//工程名称
		if(StringUtils.isNotBlank(projectQueryReq.getProjName())){
			c.add(Restrictions.like("projName", "%"+projectQueryReq.getProjName()+"%"));
			projStatusflag = true;
		}
		//工程地点
		if(StringUtils.isNotBlank(projectQueryReq.getProjAddr())){
			c.add(Restrictions.like("projAddr", "%"+projectQueryReq.getProjAddr()+"%"));
			projStatusflag = true;
		}
		//工程规模
		if(StringUtils.isNotBlank(projectQueryReq.getProjScaleDes())){
			c.add(Restrictions.like("projScaleDes", "%"+projectQueryReq.getProjScaleDes()+"%"));
			projStatusflag = true;
		}
		//区域
		if(StringUtils.isNotBlank(projectQueryReq.getArea())){
			c.add(Restrictions.eq("area",projectQueryReq.getArea()));
			projStatusflag = true;
		}
		//勘察人
		if(StringUtils.isNotBlank(projectQueryReq.getSurveyer())){
			c.add(Restrictions.like("surveyer", "%"+projectQueryReq.getSurveyer()+"%"));
		}
		//选择接气方案时 ，只能显示当前当前操作者的工程
		if (StringUtils.isNotBlank(projectQueryReq.getSideBarID()) && "110202".equals(projectQueryReq.getSideBarID())) {
			c.add(Restrictions.eq("surveyerId", loginInfo.getStaffId()));
		}


		//预算员
		if(StringUtils.isNotBlank(projectQueryReq.getBudgeter())){
			c.add(Restrictions.like("budgeter", "%"+projectQueryReq.getBudgeter()+"%"));
		}

		//造价员
		if(StringUtils.isNotBlank(projectQueryReq.getCostMember())){
			c.add(Restrictions.like("costMember", "%"+projectQueryReq.getCostMember()+"%"));
		}
		//结算员
		if(StringUtils.isNotBlank(projectQueryReq.getSettlementer())){
			c.add(Restrictions.like("settlementer", "%"+projectQueryReq.getSettlementer()+"%"));
		}
		//用于字符串日期格式转换
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		//受理日期开始日期
		if(StringUtils.isNotBlank(projectQueryReq.getAcceptDateStart())){
			c.add(Restrictions.ge("acceptDate", sdf.parse(projectQueryReq.getAcceptDateStart())));
		}
		//受理日期结束日期
		if(StringUtils.isNotBlank(projectQueryReq.getAcceptDateEnd())){
			c.add(Restrictions.le("acceptDate", sdf.parse(projectQueryReq.getAcceptDateEnd())));
		}
		//勘察完成开始日期
		if(StringUtils.isNotBlank(projectQueryReq.getSurveyDateStart())){
			c.add(Restrictions.ge("surveyDate", sdf.parse(projectQueryReq.getSurveyDateStart())));
		}
		//勘察完成结束日期
		if(StringUtils.isNotBlank(projectQueryReq.getSurveyDateEnd())){
			c.add(Restrictions.le("surveyDate", sdf.parse(projectQueryReq.getSurveyDateEnd())));
		}
		//设计完成开始日期
		if(StringUtils.isNotBlank(projectQueryReq.getDuCompleteDateStart())){
			c.add(Restrictions.ge("duCompleteDate", sdf.parse(projectQueryReq.getDuCompleteDateStart())));
		}
		//设计完成结束日期
		if(StringUtils.isNotBlank(projectQueryReq.getDuCompleteDateEnd())){
			c.add(Restrictions.le("duCompleteDate", sdf.parse(projectQueryReq.getDuCompleteDateEnd())));
		}

		//预算日期开始日期
		if(StringUtils.isNotBlank(projectQueryReq.getBudgetDateStart())){
			c.add(Restrictions.ge("budgetDate", sdf.parse(projectQueryReq.getBudgetDateStart())));
		}
		//预算日期结束日期
		if(StringUtils.isNotBlank(projectQueryReq.getBudgetDateEnd())){
			c.add(Restrictions.le("budgetDate", sdf.parse(projectQueryReq.getBudgetDateEnd())));
		}

		//合同签订开始日期
		if(StringUtils.isNotBlank(projectQueryReq.getContractSignDateStart())){
			c.add(Restrictions.ge("signDate", sdf.parse(projectQueryReq.getContractSignDateStart())));
		}
		//合同签订结束日期
		if(StringUtils.isNotBlank(projectQueryReq.getContractSignDateEnd())){
			c.add(Restrictions.le("signDate", sdf.parse(projectQueryReq.getContractSignDateEnd())));
		}
		//验收日期开始日期
		if(StringUtils.isNotBlank(projectQueryReq.getAcceptanceDateStart())){
			c.add(Restrictions.ge("acceptanceDate", sdf.parse(projectQueryReq.getAcceptanceDateStart())));
		}
		//验收日期结束日期
		if(StringUtils.isNotBlank(projectQueryReq.getAcceptanceDateEnd())){
			c.add(Restrictions.ge("acceptanceDate", sdf.parse(projectQueryReq.getAcceptanceDateEnd())));
		}
		//结算日期开始日期
		if(StringUtils.isNotBlank(projectQueryReq.getSettlementDateStart())){
			c.add(Restrictions.ge("settlementDate", sdf.parse(projectQueryReq.getSettlementDateStart())));
		}
		//结算日期结束日期
		if(StringUtils.isNotBlank(projectQueryReq.getSettlementDateEnd())){
			c.add(Restrictions.ge("settlementDate", sdf.parse(projectQueryReq.getSettlementDateEnd())));
		}
		//分包合同签定日期
		if(StringUtils.isNotBlank(projectQueryReq.getSubContractDateStart()) || StringUtils.isNotBlank(projectQueryReq.getSubContractDateEnd())){
			StringBuffer sql = new StringBuffer("select sc.proj_id from sub_contract sc where 1=1");
			//分包合同签定开始日期
			if(StringUtils.isNotBlank(projectQueryReq.getSubContractDateStart())){
				sql.append(" and sc.sc_sign_date>=").append(mysqlSqlConveter.dataOperate(projectQueryReq.getSubContractDateStart()));
			}
			//分包合同签定结束日期
			if(StringUtils.isNotBlank(projectQueryReq.getSubContractDateEnd())){
				sql.append(" and sc.sc_sign_date<=").append(mysqlSqlConveter.dataOperate(projectQueryReq.getSubContractDateEnd()));
			}
			StringBuffer resultSql = new StringBuffer("proj_id in(").append(sql.toString()).append(")");
			c.add(Restrictions.sqlRestriction(resultSql.toString()));
		}

		//分包方单位人员登录时，只可看自己相关项目
		BusinessPartners bp =  businessPartnersDao.get(loginInfo.getDeptId());
		String post=loginInfo.getPost();
		if(bp!=null){
			//若登录人是分包方单位人员
			if(UnitTypeEnum.CONSTRUCTION_UNIT.getValue().equals(bp.getUnitType())&& post.length()>0){
				StringBuffer sql = this.cuUnitFilter(loginInfo,post, projectQueryReq.getMenuId());
				c.add(Restrictions.sqlRestriction(sql.toString()));
			}else if(UnitTypeEnum.SUPERVISION_UNIT.getValue().equals(bp.getUnitType())){
				//若登录人是监理单位
				StringBuffer sql = this.suUnitFilter(loginInfo, post, projectQueryReq.getMenuId());
				c.add(Restrictions.sqlRestriction(sql.toString()));
			}else{
				//其他业务合作伙伴-如检测单位
			}
		}

		//开工日期开始日期
		if(StringUtils.isNotBlank(projectQueryReq.getStartDateStart())){
			c.add(Restrictions.ge("startDate", sdf.parse(projectQueryReq.getStartDateStart())));
		}
		//开工日期结束日期
		if(StringUtils.isNotBlank(projectQueryReq.getStartDateEnd())){
			c.add(Restrictions.ge("startDate", sdf.parse(projectQueryReq.getStartDateEnd())));
		}
		//竣工日期开始日期
		if(StringUtils.isNotBlank(projectQueryReq.getCompleteDateStart())){
			c.add(Restrictions.ge("completedDate", sdf.parse(projectQueryReq.getCompleteDateStart())));
		}
		//竣工日期结束日期
		if(StringUtils.isNotBlank(projectQueryReq.getCompleteDateEnd())){
			c.add(Restrictions.ge("completedDate", sdf.parse(projectQueryReq.getCompleteDateEnd())));
		}
		//结束开始日期
		if(StringUtils.isNotBlank(projectQueryReq.getFinishedDateStart()) && StringUtils.isBlank(projectQueryReq.getFinishedDateEnd())){
			StringBuffer sql = new StringBuffer();
			sql.append("(finished_Date is null or finished_date>=").append(mysqlSqlConveter.dataOperate(projectQueryReq.getFinishedDateStart())).append(")");
			c.add(Restrictions.sqlRestriction(sql.toString()));
		}
		if(StringUtils.isNotBlank(projectQueryReq.getFinishedDateStart()) && StringUtils.isNotBlank(projectQueryReq.getFinishedDateEnd())){
			c.add(Restrictions.ge("finishedDate", sdf.parse(projectQueryReq.getFinishedDateStart())));
			c.add(Restrictions.ge("finishedDate", sdf.parse(projectQueryReq.getFinishedDateEnd())));
		}
		//结束截止日期
		if(StringUtils.isNotBlank(projectQueryReq.getFinishedDateEnd())){
			c.add(Restrictions.ge("finishedDate", sdf.parse(projectQueryReq.getFinishedDateEnd())));
		}
		//造价确认开始日期
		if(StringUtils.isNotBlank(projectQueryReq.getAffirmCostDateStart())){
			c.add(Restrictions.ge("affirmCostDate", sdf.parse(projectQueryReq.getAffirmCostDateStart())));
		}
		//造价确认结束日期
		if(StringUtils.isNotBlank(projectQueryReq.getAffirmCostDateEnd())){
			c.add(Restrictions.ge("affirmCostDate", sdf.parse(projectQueryReq.getAffirmCostDateEnd())));
		}
		//分包方名称
		if(StringUtils.isNotBlank(projectQueryReq.getCuName())){
			StringBuffer sql = new StringBuffer();
			sql.append("proj_id in(select cp.proj_id from CONSTRUCTION_PLAN cp where cp.CU_NAME like '%").append(projectQueryReq.getCuName()).append("%')");
			c.add(Restrictions.sqlRestriction(sql.toString()));
		}
		c.addOrder(Order.desc("acceptDate"));  // 受理日期
		c.add(Restrictions.sqlRestriction("proj_latitude is not null and proj_longitude is not null"));


		//非设计院且非业务合作伙伴
		if(!DeptDivideEnum.DESIGN_INSTITUTE.getValue().equals(loginInfo.getDeptDivide()) && bp==null){
			//所属公司id
			if(StringUtils.isNotBlank(loginInfo.getCorpId())){
				c.add(Restrictions.like("corpId",loginInfo.getCorpId()+"%"));
			}
			//综合组或技术组
			if(DeptDivideEnum.COMPREHENSIVE_GROUP.getValue().equals(loginInfo.getDeptDivide())|| DeptDivideEnum.TECHNOLOGY_GROUP.getValue().equals(loginInfo.getDeptDivide())
					|| DeptDivideEnum.PRE_SETTLEMENT_GROUP.getValue().equals(loginInfo.getDeptDivide())|| DeptDivideEnum.QUALITY_SAFETY_GROUP.getValue().equals(loginInfo.getDeptDivide())){
				String deptId = loginInfo.getDeptId().substring(0, loginInfo.getDeptId().length() - DeptTypeEnum.SUBCOMPANY.getInitVal().length());
				c.add(Restrictions.like("deptId", "%"+deptId+"%"));
			}else if(DeptDivideEnum.CUSTOMER_SERVICE_CENTER.getValue().equals(loginInfo.getDeptDivide())){
				//客服中心
				String deptId = loginInfo.getDeptId().substring(0, loginInfo.getDeptId().length() - DeptTypeEnum.SUBCOMPANY.getInitVal().length());
				c.add(Restrictions.like("deptId", "%"+deptId+"%"));
			}else{
				//客服中心、营销中心、改管、干线、民用
				String deptId = loginInfo.getDeptId();
				c.add(Restrictions.like("deptId", deptId+"%"));
			}
		}

		//没有查询条件，按照默认工程状态过滤
		if(!projStatusflag){
			//按工程状态过滤
			if(projectQueryReq.getProjStuList()!=null && projectQueryReq.getProjStuList().size()>0){
				List<String> projStus=projectQueryReq.getProjStuList();
				StringBuffer hql = new StringBuffer("from Project where projStatusId = '");
				for(int i=0;i<projStus.size();i++){
					if(i==0){
						hql.append(projStus.get(i)).append("'");
					}else{
						hql.append(" or projStatusId ='").append(projStus.get(i)).append("'");
					}
				}
				List<Project> projects = super.findByHql(hql.toString());
				List<String> projIds = new ArrayList();
				if(projects.size()>0){
					for(Project project:projects){
						projIds.add(project.getProjId());
					}
					c.add(Restrictions.in("projId",projIds));
				}else{
					// 返回结果
					return ResultUtil.pageResult( 0, projectQueryReq.getDraw(),new ArrayList());
				}
			}
		}

		// 根据条件获取查询信息
		List<Project> projectList = this.findByCriteria(c);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", projectList);
		// 返回结果
		return map;
	}

	@Override
	public List<Map<String,Object>> everyAreaProjectNum() {

		/**一年前日期*/
		Format f = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		c.setTime(super.getDatabaseDate());
		c.add(Calendar.YEAR, -1);
		String date = f.format(c.getTime());
		StringBuffer sql = new StringBuffer();
		sql.append("select p.area,b.stepId,count(*) totalNum from project p ,");
		sql.append(" (select ord.proj_id,").append(mysqlSqlConveter.funcSubstringConvert()).append("(ord.step_id,1,2)stepId from operate_record ord, ");
		sql.append(" (select Max(o.operate_time) maxTime,min(o.operate_time) minTime,o.proj_id from operate_record o group by o.proj_id) a ");
		sql.append(" where a.minTime >=").append(mysqlSqlConveter.dataOperate(date)).append(" and ord.proj_id=a.proj_id and ord.operate_time=a.maxTime) b where p.proj_id=b.proj_id group by p.area,b.stepId");
		sql.append(" order by b.stepId");
		List<Map<String,Object>> result = super.findListBySql(sql.toString());
		return result;
	}

	@Override
	public Map<String, Object> queryProjectcostSummary(ProjectQueryReq projectQueryReq) throws ParseException {
		Criteria c = super.getCriteria();
		//工程编号
		if(StringUtils.isNotBlank(projectQueryReq.getProjNo())){
			c.add(Restrictions.eq("projNo",projectQueryReq.getProjNo()));
		}
		//工程名称
		if(StringUtils.isNotBlank(projectQueryReq.getProjName())){
			c.add(Restrictions.like("projName", "%"+projectQueryReq.getProjName()+"%"));
		}
		//工程地点
		if(StringUtils.isNotBlank(projectQueryReq.getProjAddr())){
			c.add(Restrictions.like("projAddr", "%"+projectQueryReq.getProjAddr()+"%"));
		}
		//用于字符串日期格式转换
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		//受理日期开始日期
		if(StringUtils.isNotBlank(projectQueryReq.getAcceptDateStart())){
			c.add(Restrictions.ge("acceptDate", sdf.parse(projectQueryReq.getAcceptDateStart())));
		}
		//受理日期结束日期
		if(StringUtils.isNotBlank(projectQueryReq.getAcceptDateEnd())){
			c.add(Restrictions.le("acceptDate", sdf.parse(projectQueryReq.getAcceptDateEnd())));
		}
		//设计完成开始日期
		if(StringUtils.isNotBlank(projectQueryReq.getDuCompleteDateStart())){
			c.add(Restrictions.ge("duCompleteDate", sdf.parse(projectQueryReq.getDuCompleteDateStart())));
		}
		//设计完成结束日期
		if(StringUtils.isNotBlank(projectQueryReq.getDuCompleteDateEnd())){
			c.add(Restrictions.le("duCompleteDate", sdf.parse(projectQueryReq.getDuCompleteDateEnd())));
		}

		//预算日期开始日期
		if(StringUtils.isNotBlank(projectQueryReq.getBudgetDateStart())){
			c.add(Restrictions.ge("budgetDate", sdf.parse(projectQueryReq.getBudgetDateStart())));
		}
		//预算日期结束日期
		if(StringUtils.isNotBlank(projectQueryReq.getBudgetDateEnd())){
			c.add(Restrictions.le("budgetDate", sdf.parse(projectQueryReq.getBudgetDateEnd())));
		}
		//合同签订开始日期
		if(StringUtils.isNotBlank(projectQueryReq.getContractSignDateStart())){
			c.add(Restrictions.ge("signDate", sdf.parse(projectQueryReq.getContractSignDateStart())));
		}
		//合同签订结束日期
		if(StringUtils.isNotBlank(projectQueryReq.getContractSignDateEnd())){
			c.add(Restrictions.le("signDate", sdf.parse(projectQueryReq.getContractSignDateEnd())));
		}
		//造价确认开始日期
		if(StringUtils.isNotBlank(projectQueryReq.getAffirmCostDateStart())){
			c.add(Restrictions.ge("affirmCostDate", sdf.parse(projectQueryReq.getAffirmCostDateStart())));
		}
		//造价确认结束日期
		if(StringUtils.isNotBlank(projectQueryReq.getAffirmCostDateEnd())){
			c.add(Restrictions.ge("affirmCostDate", sdf.parse(projectQueryReq.getAffirmCostDateEnd())));
		}
		StringBuffer sql = new StringBuffer();
		sql.append("exists(select 1 from supplemental_contract s where s.proj_id=this_.proj_id)");
		c.add(Restrictions.sqlRestriction(sql.toString()));

		c.addOrder(Order.desc("acceptDate"));  // 受理日期

		// 数据库根据条件过滤记录数
		int filterCount = this.countByCriteria(c);

		// 根据条件获取查询信息
		List<Project> projectList = this.findBySortCriteria(c, projectQueryReq);
		// 返回结果
		return ResultUtil.pageResult( filterCount, projectQueryReq.getDraw(),projectList);
	}

	@Override
	public List<Map<String, Object>> backReasonProjectNum() {
		/**一年前日期*/
		Format f = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		c.setTime(super.getDatabaseDate());
		c.add(Calendar.YEAR, -1);
		String date = f.format(c.getTime());
		StringBuffer sql = new StringBuffer();
		sql.append("select count(*) value,p.back_reason nameId from project p where p.back_reason is not null and p.back_date>=");
		sql.append(mysqlSqlConveter.dataOperate(date)).append(" group by p.back_reason");
		sql.append(" order by p.back_reason");
		List<Map<String,Object>> result = super.findListBySql(sql.toString());
		return result;
	}

	@Override
	public List<Map<String, Object>> paymentNum() {
		/**一年前日期*/
		Format f = new SimpleDateFormat("yyyy-MM");
		Calendar c = Calendar.getInstance();
		c.setTime(super.getDatabaseDate());
		c.add(Calendar.YEAR, -1);
		c.add(Calendar.MONTH, 1);
		String date = f.format(c.getTime());
		StringBuffer sql = new StringBuffer();
		sql.append("select ").append(mysqlSqlConveter.dataOperateYearAndMonth("ar.ar_date")).append(" arDate，ar.ar_flag arFlag,sum(ar.ar_cost) cost from accruals_record ar where ar.ar_date>=");
		sql.append(mysqlSqlConveter.dataOperate(date)).append(" group by ").append(mysqlSqlConveter.dataOperateYearAndMonth("ar.ar_date")).append(",ar.ar_flag order by arFlag,arDate");
		List<Map<String,Object>> result = super.findListBySql(sql.toString());
		return result;
	}

	@Override
	public List<Map<String, Object>> projectScheduleStatistics() {
		StringBuffer sql = new StringBuffer();
		sql.append("select c.management_office manageOffice,pt.progress,round(sum(pt.tprogress)/count(*),2) progressValue from ");
		sql.append("(select case when p.total_progress<=30 then 'A'");
		sql.append(" when p.total_progress<=50 and p.total_progress>30 then 'B'");
		sql.append(" when p.total_progress<=60 and p.total_progress>50 then 'C'");
		sql.append(" when p.total_progress<=80 and p.total_progress>60 then 'D'");
		sql.append(" when p.total_progress<=90 and p.total_progress>80 then 'E'");
		sql.append(" when p.total_progress<=100 and p.total_progress>90 then 'F' else null End progress,p.proj_id projId,p.total_progress tprogress from project p where p.total_progress is not null) pt,");
		sql.append(" construction_plan c where c.proj_id = pt.projId and pt.progress is not null group by c.management_office,pt.progress");
		List<Map<String,Object>> result = super.findListBySql(sql.toString());
		return result;
	}
	/**
	 * 自检
	 * @author
	 * @createTime
	 * @param
	 * @return
	 */
	@Override
	public Map<String, Object> queryProjectAcceptance(ProjectQueryReq projectQueryReq, String[] projStatusId)
			throws ParseException {

		Criteria c = super.getCriteria();
		//工程编号
		if(StringUtils.isNotBlank(projectQueryReq.getProjNo())){
			c.add(Restrictions.eq("projNo",projectQueryReq.getProjNo()));
		}
		//工程状态条件
		if(StringUtils.isNotBlank(projectQueryReq.getProjStatusId())){
			c.add(Restrictions.eq("projStatusId",projectQueryReq.getProjStatusId()));
		}
		//工程状态数组
		if(projStatusId!=null && projStatusId.length>0){
			StringBuffer sql = new StringBuffer();
			sql.append("( ");
			for(int i=0;i<projStatusId.length;i++){
				if(i==0){
					sql.append(" proj_Status_Id ='").append(projStatusId[i]).append("'");
				}else{
					sql.append(" or proj_Status_Id ='").append(projStatusId[i]).append("'");
				}
			}
			sql.append(")");
			c.add(Restrictions.sqlRestriction(sql.toString()));
		}
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		String post=loginInfo.getPost();
		//String [] postArray=post.split(",");

		//分包方单位人员登录时，只可看自己相关项目
		BusinessPartners bp =  businessPartnersDao.get(loginInfo.getDeptId());
		if(bp!=null){
			//若登录人是分包方单位人员
			if(UnitTypeEnum.CONSTRUCTION_UNIT.getValue().equals(bp.getUnitType())&& post.length()>0){
				StringBuffer sql = this.cuUnitFilter(loginInfo,post, projectQueryReq.getMenuId());
				c.add(Restrictions.sqlRestriction(sql.toString()));
			}else if(UnitTypeEnum.SUPERVISION_UNIT.getValue().equals(bp.getUnitType())){
				//若登录人是监理单位
				StringBuffer sql = this.suUnitFilter(loginInfo, post, projectQueryReq.getMenuId());
				c.add(Restrictions.sqlRestriction(sql.toString()));
			}else{
				//其他业务合作伙伴-如检测单位
			}
		}

		// 施工管理处的人员只能看到参与的工程
		String deptId = loginInfo.getDeptId();

		//分包合同签定日期
		if(StringUtils.isNotBlank(projectQueryReq.getSubContractDateStart()) || StringUtils.isNotBlank(projectQueryReq.getSubContractDateEnd())){
			StringBuffer sql = new StringBuffer("select sc.proj_id from sub_contract sc where 1=1");
			//分包合同签定开始日期
			if(StringUtils.isNotBlank(projectQueryReq.getSubContractDateStart())){
				sql.append(" and sc.sc_sign_date>=").append(mysqlSqlConveter.dataOperate(projectQueryReq.getSubContractDateStart()));
			}
			//分包合同签定结束日期
			if(StringUtils.isNotBlank(projectQueryReq.getSubContractDateEnd())){
				sql.append(" and sc.sc_sign_date<=").append(mysqlSqlConveter.dataOperate(projectQueryReq.getSubContractDateEnd()));
			}
			StringBuffer resultSql = new StringBuffer("proj_id in(").append(sql.toString()).append(")");
			c.add(Restrictions.sqlRestriction(resultSql.toString()));
		}
		//工程大类
		if(StringUtils.isNotBlank(projectQueryReq.getProjLtypeId())){
			c.add(Restrictions.eq("projLtypeId",projectQueryReq.getProjLtypeId()));
		}
		//工程名称
		if(StringUtils.isNotBlank(projectQueryReq.getProjName())){
			c.add(Restrictions.like("projName", "%"+projectQueryReq.getProjName()+"%"));
			//instr写法用于代替like写法，提高查询效率
			//c.add(Restrictions.sqlRestriction("instr('projName','"+projectQueryReq.getProjName()+"',1,1)!=0"));
		}
		//工程地点
		if(StringUtils.isNotBlank(projectQueryReq.getProjAddr())){
			c.add(Restrictions.like("projAddr", "%"+projectQueryReq.getProjAddr()+"%"));
		}
		//用于字符串日期格式转换
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		//开工日期开始日期
		if(StringUtils.isNotBlank(projectQueryReq.getStartDateStart())){
			c.add(Restrictions.ge("startDate", sdf.parse(projectQueryReq.getStartDateStart())));
		}
		//开工日期结束日期
		if(StringUtils.isNotBlank(projectQueryReq.getStartDateEnd())){
			c.add(Restrictions.le("startDate", sdf.parse(projectQueryReq.getStartDateEnd())));
		}
		//竣工日期开始日期
		if(StringUtils.isNotBlank(projectQueryReq.getCompleteDateStart())){
			c.add(Restrictions.ge("completedDate", sdf.parse(projectQueryReq.getCompleteDateStart())));
		}
		//竣工日期结束日期
		if(StringUtils.isNotBlank(projectQueryReq.getCompleteDateEnd())){
			c.add(Restrictions.le("completedDate", sdf.parse(projectQueryReq.getCompleteDateEnd())));
		}


		// 数据库根据条件过滤记录数
		int filterCount = this.countByCriteria(c);

		// 根据条件获取查询信息
		List<Project> projectList = this.findBySortCriteria(c, projectQueryReq);
		// 返回结果
		return ResultUtil.pageResult( filterCount, projectQueryReq.getDraw(),projectList);

	}

	@Override
	public Map<String, Object> queryAcceptProject(ProjectQueryReq projectQueryReq) throws ParseException {

		Criteria c = super.getCriteria();

		LoginInfo loginInfo = SessionUtil.getLoginInfo();

		Boolean flag=true;
		//受理打印查询 按状态查询 如安顺在立项审核后可查看
		List<DataFilerSetUpDto> list = Constants.getDataFilterMapByKey(loginInfo.getCorpId()+"_"+projectQueryReq.getMenuId());
		if(list!=null && list.size()>0){
			StringBuffer hql = new StringBuffer();
			hql.append("proj_id in (select proj_id from project where 1=1 and corp_id in('").append(loginInfo.getCorpId()).append("')");

			if(StringUtils.isNotBlank(list.get(0).getSupSql())){
				hql.append(list.get(0).getSupSql());
			}
			hql.append(")");
			c.add(Restrictions.sqlRestriction(hql.toString()));
			flag=false;
		}

		String post = loginInfo.getPost();
		//受理申请时查看自己的工程
		if(post!=null && post.length()>0 && post.contains(PostTypeEnum.SALESMA.getValue())){
			List<DataFilerSetUpDto> costerList = Constants.getDataFilterMapByKey(loginInfo.getCorpId()+PostTypeEnum.SALESMA.getValue()+"_"+projectQueryReq.getMenuId());
			if(costerList!=null && costerList.size()>0){
				if(StringUtils.isNotBlank(costerList.get(0).getSupSql())){
					c.add(Restrictions.eq(costerList.get(0).getSupSql(), loginInfo.getStaffId()));
				}
			}
		}


		//所属公司id
		if(StringUtils.isNotBlank(loginInfo.getCorpId())){
			c.add(Restrictions.like("corpId",loginInfo.getCorpId()+"%"));
		}

		//计划Id
		if(StringUtils.isNotBlank(projectQueryReq.getPlanId())){
			c.add(Restrictions.eq("planId",projectQueryReq.getPlanId()));
		}

		//工程编号
		if(StringUtils.isNotBlank(projectQueryReq.getProjNo())){
			c.add(Restrictions.eq("projNo",projectQueryReq.getProjNo()));
		}
		//工程状态
		if((projectQueryReq.getProjStuList()).size()>0){
			List<String> projStus=projectQueryReq.getProjStuList();
			c.add(Restrictions.in("projStatusId",projStus));
		}

		//工程状态
		if(StringUtils.isNotBlank(projectQueryReq.getProjStatusId())){
			c.add(Restrictions.eq("projStatusId",projectQueryReq.getProjStatusId()));
		}else{
			//默认去掉终止的
			c.add(Restrictions.ne("projStatusId",ProjStatusEnum.TERMINATION.getValue()));
		}
		//工程类型
		if(StringUtils.isNotBlank(projectQueryReq.getProjectType())){
			c.add(Restrictions.eq("projectType",projectQueryReq.getProjectType()));
		}

		//退单通知
		if(projectQueryReq.getBackInform()!=null){
			c.add(Restrictions.eq("backInform",projectQueryReq.getBackInform()));
		}
		//工程名称
		if(StringUtils.isNotBlank(projectQueryReq.getProjName())){
			c.add(Restrictions.like("projName", "%"+projectQueryReq.getProjName()+"%"));
			//instr写法用于代替like写法，提高查询效率
			//c.add(Restrictions.sqlRestriction("instr('projName','"+projectQueryReq.getProjName()+"',1,1)!=0"));
		}
		//工程地点
		if(StringUtils.isNotBlank(projectQueryReq.getProjAddr())){
			c.add(Restrictions.like("projAddr", "%"+projectQueryReq.getProjAddr()+"%"));
		}

		//用于字符串日期格式转换
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		//受理日期开始日期
		if(StringUtils.isNotBlank(projectQueryReq.getAcceptDateStart())){
			c.add(Restrictions.ge("acceptDate", sdf.parse(projectQueryReq.getAcceptDateStart())));
		}
		//受理日期结束日期
		if(StringUtils.isNotBlank(projectQueryReq.getAcceptDateEnd())){
			c.add(Restrictions.le("acceptDate", sdf.parse(projectQueryReq.getAcceptDateEnd())));
		}

		//立项方式
		if(StringUtils.isNotBlank(projectQueryReq.getAcceptType())){
			c.add(Restrictions.eq("acceptType", projectQueryReq.getAcceptType()));
		}

		c.addOrder(Order.desc("acceptDate"));  // 受理日期



		//综合组
		if(DeptDivideEnum.COMPREHENSIVE_GROUP.getValue().equals(loginInfo.getDeptDivide())){
			String deptId = loginInfo.getDeptId().substring(0, loginInfo.getDeptId().length() - DeptTypeEnum.SUBCOMPANY.getInitVal().length());
			c.add(Restrictions.sqlRestriction(addDeptIdLikeQuery(deptId,loginInfo.getDeptTransfer())));//部门条件查询
		}else if(DeptDivideEnum.CUSTOMER_SERVICE_CENTER.getValue().equals(loginInfo.getDeptDivide())){
			//客服中心
			String deptId = loginInfo.getDeptId().substring(0, loginInfo.getDeptId().length() - DeptTypeEnum.SUBCOMPANY.getInitVal().length());
			c.add(Restrictions.sqlRestriction(addDeptIdLikeQuery(deptId,loginInfo.getDeptTransfer())));//部门条件查询
		}else{
			if(flag){
				//客服中心、营销中心、改管
				c.add(Restrictions.sqlRestriction(addDeptIdLikeQuery(loginInfo.getDeptId(),loginInfo.getDeptTransfer())));//部门条件查询
			}
		}


		// 数据库根据条件过滤记录数
		int filterCount = this.countByCriteria(c);

		// 根据条件获取查询信息
		List<Project> projectList = this.findBySortCriteria(c, projectQueryReq);
		// 返回结果
		return ResultUtil.pageResult( filterCount, projectQueryReq.getDraw(),projectList);
	}

	/**
	 * 工程列表条件查询-报表系统
	 * @param
	 * @author
	 * @createTime 2017-01-4
	 * @return Map<String,Object>
	 */
	@Override
	public Map<String, Object> queryProjStatis(ProjectQueryReq projectQueryReq, String[] projStatusId)
			throws ParseException {
		Criteria c = super.getCriteria();
		//工程编号
		if(StringUtils.isNotBlank(projectQueryReq.getProjNo())){
			c.add(Restrictions.eq("projNo",projectQueryReq.getProjNo()));
		}
		//工程状态条件
		if(StringUtils.isNotBlank(projectQueryReq.getProjStatusId())){
			c.add(Restrictions.eq("projStatusId",projectQueryReq.getProjStatusId()));
		}
		//工程状态数组
		if(projStatusId!=null && projStatusId.length>0){
			//已竣工且至今的时间小于等于3个月
			StringBuffer idSql = new StringBuffer(" (proj_id in (select distinct(pj.proj_id) from");
			//updateaddmonths
			//idSql.append(" (select operateRecord.proj_id from (select o.proj_id,add_months(o.operate_time,3) ot from operate_record o ) operateRecord where operateRecord.OT>=");
			idSql.append(" (select operateRecord.proj_id from (select o.proj_id,").append(mysqlSqlConveter.funcAddMonths("o.operate_time",3)).append(" ot from operate_record o ) operateRecord where operateRecord.OT>=");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			idSql.append(mysqlSqlConveter.dataOperate(sdf.format(super.getDatabaseDate()))).append(") ord,project pj");
			idSql.append(" where ord.proj_id = pj.proj_id and pj.back_reason is null and  pj.proj_status_id='").append(ProjStatusEnum.ALREADY_COMPLETED.getValue()).append("')");
			StringBuffer sql = new StringBuffer();
			sql.append(idSql.toString());
			for(int i=0;i<projStatusId.length;i++){
				sql.append(" or proj_Status_Id ='").append(projStatusId[i]).append("'");
			}
			sql.append(")");
			c.add(Restrictions.sqlRestriction(sql.toString()));
		}

		//分包合同签定日期
		if(StringUtils.isNotBlank(projectQueryReq.getSubContractDateStart()) || StringUtils.isNotBlank(projectQueryReq.getSubContractDateEnd())){
			StringBuffer sql = new StringBuffer("select sc.proj_id from sub_contract sc where 1=1");
			//分包合同签定开始日期
			if(StringUtils.isNotBlank(projectQueryReq.getSubContractDateStart())){
				sql.append(" and sc.sc_sign_date>=").append(mysqlSqlConveter.dataOperate(projectQueryReq.getSubContractDateStart()));
			}
			//分包合同签定结束日期
			if(StringUtils.isNotBlank(projectQueryReq.getSubContractDateEnd())){
				sql.append(" and sc.sc_sign_date<=").append(mysqlSqlConveter.dataOperate(projectQueryReq.getSubContractDateEnd()));
			}
			StringBuffer resultSql = new StringBuffer("proj_id in(").append(sql.toString()).append(")");
			c.add(Restrictions.sqlRestriction(resultSql.toString()));
		}
		//工程大类
		if(StringUtils.isNotBlank(projectQueryReq.getProjLtypeId())){
			c.add(Restrictions.eq("projLtypeId",projectQueryReq.getProjLtypeId()));
		}
		//工程名称
		if(StringUtils.isNotBlank(projectQueryReq.getProjName())){
			c.add(Restrictions.like("projName", "%"+projectQueryReq.getProjName()+"%"));
			//instr写法用于代替like写法，提高查询效率
			//c.add(Restrictions.sqlRestriction("instr('projName','"+projectQueryReq.getProjName()+"',1,1)!=0"));
		}
		//工程地点
		if(StringUtils.isNotBlank(projectQueryReq.getProjAddr())){
			c.add(Restrictions.like("projAddr", "%"+projectQueryReq.getProjAddr()+"%"));
		}
		//用于字符串日期格式转换
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		//开工日期开始日期
		if(StringUtils.isNotBlank(projectQueryReq.getStartDateStart())){
			c.add(Restrictions.ge("startDate", sdf.parse(projectQueryReq.getStartDateStart())));
		}
		//开工日期结束日期
		if(StringUtils.isNotBlank(projectQueryReq.getStartDateEnd())){
			c.add(Restrictions.ge("startDate", sdf.parse(projectQueryReq.getStartDateEnd())));
		}
		//竣工日期开始日期
		if(StringUtils.isNotBlank(projectQueryReq.getCompleteDateStart())){
			c.add(Restrictions.ge("completedDate", sdf.parse(projectQueryReq.getCompleteDateStart())));
		}
		//竣工日期结束日期
		if(StringUtils.isNotBlank(projectQueryReq.getCompleteDateEnd())){
			c.add(Restrictions.ge("completedDate", sdf.parse(projectQueryReq.getCompleteDateEnd())));
		}

		// 当前登录操作者
		 /*String deptId = loginInfo.getDeptId();
		 if ("110201".equals(deptId) || "110202".equals(deptId) ||"110203".equals(deptId)
				 ||"110204".equals(deptId) ||"110205".equals(deptId) ) {
		    c.add(Restrictions.eq("area", loginInfo.getDeptId()));
		 }*/



		//工程施工报表屏  选择加载施工处
		if(StringUtils.isNotBlank(projectQueryReq.getManagementOfficeId())){
			StringBuffer sql=new StringBuffer();
			sql.append("proj_id in(select cp.proj_id from construction_plan cp where cp.management_id = '").append(projectQueryReq.getManagementOfficeId()).append("')");
			c.add(Restrictions.sqlRestriction(sql.toString()));
		}

		//工程施工报表屏  选择加载分包单位
		if(StringUtils.isNotBlank(projectQueryReq.getCuId())){
			StringBuffer sql=new StringBuffer();
			sql.append("proj_id in(select cp.proj_id from construction_plan cp where cp.cu_id = '").append(projectQueryReq.getCuId()).append("')");
			c.add(Restrictions.sqlRestriction(sql.toString()));
		}

		// 数据库根据条件过滤记录数
		int filterCount = this.countByCriteria(c);

		// 根据条件获取查询信息
		List<Project> projectList = this.findBySortCriteria(c, projectQueryReq);
		// 返回结果
		return ResultUtil.pageResult( filterCount, projectQueryReq.getDraw(),projectList);
	}

	/**
	 * 根据状态返回工程数量
	 * @author fuliwei
	 * @createTime 2017年7月23日
	 * @param
	 * @return
	 */
	@Override
	public int queryWorkNotice(String statusId) {
		Criteria c = super.getCriteria();
		LoginInfo loginInfo = SessionUtil.getLoginInfo();

		//工程状态
		if(StringUtils.isNotBlank(statusId)){
			c.add(Restrictions.eq("projStatusId", statusId));
		}

		//分包方单位人员登录时，只可看自己相关项目
		BusinessPartners bp =  businessPartnersDao.get(loginInfo.getDeptId());
		//非设计院且非业务合作伙伴

		if(!DeptDivideEnum.DESIGN_INSTITUTE.getValue().equals(loginInfo.getDeptDivide()) && bp==null){
			//所属公司id
			if(StringUtils.isNotBlank(loginInfo.getCorpId())){
				c.add(Restrictions.like("corpId",loginInfo.getCorpId()+"%"));
			}
			//综合组或技术组
			if(DeptDivideEnum.COMPREHENSIVE_GROUP.getValue().equals(loginInfo.getDeptDivide())|| DeptDivideEnum.TECHNOLOGY_GROUP.getValue().equals(loginInfo.getDeptDivide())
					|| DeptDivideEnum.PRE_SETTLEMENT_GROUP.getValue().equals(loginInfo.getDeptDivide())|| DeptDivideEnum.QUALITY_SAFETY_GROUP.getValue().equals(loginInfo.getDeptDivide())){
				String deptId = loginInfo.getDeptId().substring(0, loginInfo.getDeptId().length() - DeptTypeEnum.SUBCOMPANY.getInitVal().length());
				c.add(Restrictions.like("deptId", "%"+deptId+"%"));
			}else if(DeptDivideEnum.CUSTOMER_SERVICE_CENTER.getValue().equals(loginInfo.getDeptDivide())){

				if(StringUtil.isNotBlank(loginInfo.getPost()) && (loginInfo.getPost().contains(PostTypeEnum.RECORDER.getValue())|| loginInfo.getPost().contains(PostTypeEnum.CHIEF_ENGINEER.getValue()))){
					//副主任或主任
					String deptId =loginInfo.getDeptId();
					c.add(Restrictions.like("deptId", "%"+deptId+"%"));
				}else{
					//客服中心
					String deptId = loginInfo.getDeptId().substring(0, loginInfo.getDeptId().length() - DeptTypeEnum.SUBCOMPANY.getInitVal().length());
					c.add(Restrictions.like("deptId", "%"+deptId+"%"));
				}
			}else{
				if(DeptDivideEnum.CIVIL_GROUP.getValue().equals(loginInfo.getDeptDivide())||DeptDivideEnum.MARKETING_CENTER.getValue().equals(loginInfo.getDeptDivide())||
						DeptDivideEnum.TRUNK_GROUP .getValue().equals(loginInfo.getDeptDivide())||DeptDivideEnum.MODIFICATION_GROUP.getValue().equals(loginInfo.getDeptDivide())||DeptDivideEnum.MARKET_DEVELOPMENT_DEPARTMENT.getValue().equals(loginInfo.getDeptDivide())){
					//营销中心、改管、干线、民用
					String deptId = loginInfo.getDeptId();
					c.add(Restrictions.like("deptId", deptId+"%"));
				}else{
					String deptId = loginInfo.getDeptId().substring(0, loginInfo.getDeptId().length() - DeptTypeEnum.SUBCOMPANY.getInitVal().length());
					c.add(Restrictions.like("deptId", "%"+deptId+"%"));
				}
			}
		}

		String post=loginInfo.getPost();
		//String [] postArray=post.split(",");
		if(post.length()>0){
			//踏勘员
			if(post.contains(PostTypeEnum.SURVEYER.getValue())){

				WorkFlowActionEnum wfae=WorkFlowActionEnum.byStatusCode(statusId);
				if(wfae!=null){
					//现场踏勘 资料收集 造价确认
					if(StepEnum.SURVEY_RESULT_REGISTER.getValue().equals(wfae.getActionCode())
							|| StepEnum.DATA_ACQUISITION.getValue().equals(wfae.getActionCode())){
						c.add(Restrictions.eq("surveyerId", loginInfo.getStaffId()));
					}
					if(StepEnum.PROJECT_COST.getValue().equals(wfae.getActionCode())){
						if(!post.contains(PostTypeEnum.MODIFICATION_GROUP_LEADER.getValue())){
							c.add(Restrictions.eq("surveyerId", loginInfo.getStaffId()));
						}
					}
				}
			}


			//现场代表   2018 1-20
			if(post.contains(PostTypeEnum.BUILDER.getValue())){
				WorkFlowActionEnum wfae=WorkFlowActionEnum.byStatusCode(statusId);
				if(wfae!=null){
					//结算初审 //安装合同 //施工合同 //图纸签收
					if(StepEnum.SETTLEMENT_REPORT_START.getValue().equals(wfae.getActionCode())
							||StepEnum.OFFICIAL_DRAWING.getValue().equals(wfae.getActionCode())
							||StepEnum.CONSTRUCT_CONTRACT.getValue().equals(wfae.getActionCode())
							||StepEnum.SUB_CONTRACT.getValue().equals(wfae.getActionCode())){
						StringBuffer sql = new StringBuffer("proj_id in(select cp.proj_id from construction_plan cp where cp.BUILDER_ID='").append(loginInfo.getStaffId()).append("')");
						c.add(Restrictions.sqlRestriction(sql.toString()));
					}
				}
			}


			if(post.contains(PostTypeEnum.BUDGET_MEMBER.getValue())){


				WorkFlowActionEnum wfae=WorkFlowActionEnum.byStatusCode(statusId);
				if(wfae!=null){
					//预算员
					if(StepEnum.BUDGET_RESULT_REGISTER.getValue().equals(wfae.getActionCode())){
						//预算结果登记
						c.add(Restrictions.eq("budgeterId", loginInfo.getStaffId()));
					}

				}


			}

			//设计员
			if(post.contains(PostTypeEnum.DESIGNER.getValue())) {
				c.add(Restrictions.eq("designerId", loginInfo.getStaffId()));
			}

		}

		int filterCount = this.countByCriteria(c);
		return filterCount;
	}



	/**
	 *联合验收、结算汇签调用
	 */
	@Override
	public Map<String, Object> queryProjectForJoint(ProjectQueryReq projectQueryReq) throws ParseException {
		Criteria c = super.getCriteria();
		LoginInfo loginInfo = SessionUtil.getLoginInfo();

		String belongCorpId = loginInfo.getBelongCorpId();

		if (StringUtils.isNotBlank(belongCorpId)) {
			c.add(Restrictions.in("corpId",belongCorpId.split(",")));
		}


		//分公司数据过滤 如
		List<DataFilerSetUpDto> list = Constants.getDataFilterMapByKey(loginInfo.getDeptId()+"_"+projectQueryReq.getMenuId());
		boolean flag = true;
		if(list!=null && list.size()>0){
			StringBuffer hql = new StringBuffer();
			hql.append("proj_id in (select proj_id from project where 1=1 ");

			if(StringUtils.isNotBlank(list.get(0).getSupSql())){
				hql.append(list.get(0).getSupSql());
			}
			hql.append(")");
			c.add(Restrictions.sqlRestriction(hql.toString()));
			flag = false;
		}
		//工程编号
		if(StringUtils.isNotBlank(projectQueryReq.getProjNo())){
			c.add(Restrictions.like("projNo","%"+projectQueryReq.getProjNo()+"%"));
		}
		//工程状态
		if(StringUtils.isNotBlank(projectQueryReq.getProjStatusId())){
			c.add(Restrictions.eq("projStatusId",projectQueryReq.getProjStatusId()));
		}


		if(projectQueryReq.getProjStuList()!=null && projectQueryReq.getProjStuList().size()>0){
			List<String> projStus=projectQueryReq.getProjStuList();
			c.add(Restrictions.in("projStatusId",projStus));
		}

		//工程名称
		if(StringUtils.isNotBlank(projectQueryReq.getProjName())){
			c.add(Restrictions.like("projName", "%"+projectQueryReq.getProjName()+"%"));
		}
		//工程地点
		if(StringUtils.isNotBlank(projectQueryReq.getProjAddr())){
			c.add(Restrictions.like("projAddr", "%"+projectQueryReq.getProjAddr()+"%"));
		}
		//用于字符串日期格式转换
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		//受理日期开始日期
		if(StringUtils.isNotBlank(projectQueryReq.getAcceptDateStart())){
			c.add(Restrictions.ge("acceptDate", sdf.parse(projectQueryReq.getAcceptDateStart())));
		}
		//受理日期结束日期
		if(StringUtils.isNotBlank(projectQueryReq.getAcceptDateEnd())){
			c.add(Restrictions.le("acceptDate", sdf.parse(projectQueryReq.getAcceptDateEnd())));
		}
		//验收日期开始日期
		if(StringUtils.isNotBlank(projectQueryReq.getAcceptanceDateStart())){
			c.add(Restrictions.ge("acceptanceDate", sdf.parse(projectQueryReq.getAcceptanceDateStart())));
		}
		//验收日期结束日期
		if(StringUtils.isNotBlank(projectQueryReq.getAcceptanceDateEnd())){
			c.add(Restrictions.le("acceptanceDate", sdf.parse(projectQueryReq.getAcceptanceDateEnd())));
		}
		//结算日期开始日期
		if(StringUtils.isNotBlank(projectQueryReq.getSettlementDateStart())){
			c.add(Restrictions.ge("settlementDate", sdf.parse(projectQueryReq.getSettlementDateStart())));
		}
		//结算日期结束日期
		if(StringUtils.isNotBlank(projectQueryReq.getSettlementDateEnd())){
			c.add(Restrictions.le("settlementDate", sdf.parse(projectQueryReq.getSettlementDateEnd())));
		}
		//分包合同签定日期
		if(StringUtils.isNotBlank(projectQueryReq.getSubContractDateStart()) || StringUtils.isNotBlank(projectQueryReq.getSubContractDateEnd())){
			StringBuffer sql = new StringBuffer("select sc.proj_id from sub_contract sc where 1=1");
			//分包合同签定开始日期
			if(StringUtils.isNotBlank(projectQueryReq.getSubContractDateStart())){
				sql.append(" and sc.sc_sign_date>=").append(mysqlSqlConveter.dataOperate(projectQueryReq.getSubContractDateStart()));
			}
			//分包合同签定结束日期
			if(StringUtils.isNotBlank(projectQueryReq.getSubContractDateEnd())){
				sql.append(" and sc.sc_sign_date<=").append(mysqlSqlConveter.dataOperate(projectQueryReq.getSubContractDateEnd()));
			}
			StringBuffer resultSql = new StringBuffer("proj_id in(").append(sql.toString()).append(")");
			c.add(Restrictions.sqlRestriction(resultSql.toString()));
		}

		//开工日期开始日期
		if(StringUtils.isNotBlank(projectQueryReq.getStartDateStart())){
			c.add(Restrictions.ge("startDate", sdf.parse(projectQueryReq.getStartDateStart())));
		}
		//开工日期结束日期
		if(StringUtils.isNotBlank(projectQueryReq.getStartDateEnd())){
			c.add(Restrictions.le("startDate", sdf.parse(projectQueryReq.getStartDateEnd())));
		}
		//竣工日期开始日期
		if(StringUtils.isNotBlank(projectQueryReq.getCompleteDateStart())){
			c.add(Restrictions.ge("completedDate", sdf.parse(projectQueryReq.getCompleteDateStart())));
		}
		//竣工日期结束日期
		if(StringUtils.isNotBlank(projectQueryReq.getCompleteDateEnd())){
			c.add(Restrictions.le("completedDate", sdf.parse(projectQueryReq.getCompleteDateEnd())));
		}

		//完成汇签状态
		if(StringUtils.isNotBlank(projectQueryReq.getSignStatus())){
			StringBuffer sql = new StringBuffer("proj_id in(select cp.proj_id from settlement_jonintly_sign cp where cp.sign_status='").append(projectQueryReq.getSignStatus()).append("')");
			c.add(Restrictions.sqlRestriction(sql.toString()));
		}

		//汇签日期开始日期
		if(StringUtils.isNotBlank(projectQueryReq.getFinishDateStart())){
			StringBuffer sql = new StringBuffer("select sc.proj_id from settlement_jonintly_sign sc where 1=1");
			//汇签开始日期
			if(StringUtils.isNotBlank(projectQueryReq.getFinishDateStart())){
				sql.append(" and sc.finish_date>=").append(mysqlSqlConveter.dataOperate(projectQueryReq.getFinishDateStart()));
			}
			//汇签结束日期
			if(StringUtils.isNotBlank(projectQueryReq.getFinishDateEnd())){
				sql.append(" and sc.finish_date<=").append(mysqlSqlConveter.dataOperate(projectQueryReq.getFinishDateEnd()));
			}
			StringBuffer resultSql = new StringBuffer("proj_id in(").append(sql.toString()).append(")");
			c.add(Restrictions.sqlRestriction(resultSql.toString()));
		}

		String post=loginInfo.getPost();
		// String [] postArray=post.split(",");
		//分包方单位人员登录时，只可看自己相关项目
		BusinessPartners bp =  businessPartnersDao.get(loginInfo.getDeptId());
		if(bp!=null){
			//若登录人是分包方单位人员
			if(UnitTypeEnum.CONSTRUCTION_UNIT.getValue().equals(bp.getUnitType())&& post.length()>0){
				StringBuffer sql = this.cuUnitFilter(loginInfo,post, projectQueryReq.getMenuId());
				c.add(Restrictions.sqlRestriction(sql.toString()));
			}else if(UnitTypeEnum.SUPERVISION_UNIT.getValue().equals(bp.getUnitType())){
				//若登录人是监理单位
				StringBuffer sql = this.suUnitFilter(loginInfo, post, projectQueryReq.getMenuId());
				c.add(Restrictions.sqlRestriction(sql.toString()));
			}
		}

		Department dept=new Department();
		List<Object> deptIdArray=new ArrayList<>();


		//设计员
		if(post.contains(PostTypeEnum.DESIGNER.getValue())){
			StringBuffer sql = new StringBuffer("proj_id in (select cp.proj_id from project cp where cp.designer_id='").append(loginInfo.getStaffId()).append("')");
			c.add(Restrictions.sqlRestriction(sql.toString()));
		}

		//现场代表
		if(null!=post && post.length()>0){
			if(post.contains(PostTypeEnum.BUILDER.getValue())){
				if(!post.contains(PostTypeEnum.MARKETING_CENTER_LEADER_DEPUTY.getValue())
						&& !post.contains(PostTypeEnum.MODIFICATION_GROUP_LEADER.getValue())
						&& !post.contains(PostTypeEnum.TRUNK_GROUP_LEADER.getValue())
						&& !post.contains(PostTypeEnum.DEPUTY_LEADER.getValue()) && flag){
					StringBuffer sql = new StringBuffer("proj_id in(select cp.proj_id from construction_plan cp where cp.BUILDER_ID='").append(loginInfo.getStaffId()).append("')");
					c.add(Restrictions.sqlRestriction(sql.toString()));
				}
			}
		}



		DataFilerSetUpDto config = Constants.isConfig(loginInfo.getStaffId()+"_"+projectQueryReq.getMenuId());
		boolean isConfig=config!=null?false:true;
		if (config!=null){
			StringBuffer hql = new StringBuffer("proj_id in (select proj_id from project where 1=1 ").append(config.getSupSql()).append(" )");
			c.add(Restrictions.sqlRestriction(hql.toString()));
		}


		if(bp==null && !DeptDivideEnum.DESIGN_INSTITUTE.getValue().equals(loginInfo.getDeptDivide()) && isConfig){  //非分包单位且非设计院
			//分公司ID
			StringBuffer sql = new StringBuffer("proj_id in(select cp.proj_id from project cp where cp.corp_Id like '").append(loginInfo.getCorpId()).append("%'");
			//非分包的预算员


			//工程技术部可以查看联合验收的所有工程
			StringBuilder key=new StringBuilder(loginInfo.getDeptId()).append("_").append("110703");  //写死--专注联合验收-目前贵安公司用
			List<DataFilerSetUpDto> listCon = Constants.getDataFilterMapByKey(key.toString());
			Boolean isflag=(listCon!=null && listCon.size()>0)?false:true;

			if(post.contains(PostTypeEnum.BUDGET_MEMBER.getValue()) && isflag){
				//结算派遣的人 且不是组长
				//组长，民用的均可见，非民用的和结算员一样
				sql.append(" and cp.settlementer_Id='").append(loginInfo.getStaffId()).append("'");
				if(post.contains(PostTypeEnum.BUDGET_GROUP_LEADER.getValue())){
					sql.append(" or cp.PROJ_LTYPE_ID='").append(ProjLtypeEnum.CIVILIAN.getValue()).append("'");
				}
			}
			// 数据过滤,相关部门营销员看自己的工程，根据部门id+营销员职务+菜单ID
			List<DataFilerSetUpDto> marketDepartMentFilter = Constants.getDataFilterMapByKey(loginInfo.getDeptId()+PostTypeEnum.SALESMA.getValue()+"_"+projectQueryReq.getMenuId());
			if( marketDepartMentFilter !=null && marketDepartMentFilter.size() > 0){
				sql.append(marketDepartMentFilter.get(0).getSupSql()).append(" ='").append(loginInfo.getStaffId()).append("'");
			}
			sql.append(")");
			c.add(Restrictions.sqlRestriction(sql.toString()));
		}



		c.addOrder(Order.desc("acceptDate"));  // 受理日期
		// 数据库根据条件过滤记录数
		int filterCount = this.countByCriteria(c);
		// 根据条件获取查询信息
		List<Project> projectList = this.findBySortCriteria(c, projectQueryReq);
		// 返回结果
		return ResultUtil.pageResult( filterCount, projectQueryReq.getDraw(),projectList);
	}

	/**
	 * 年度计划id
	 * @author fuliwei
	 * @createTime 2017年10月9日
	 * @param
	 * @return
	 */
	@Override
	public List<Project> findByPlanId(String planId) {
		StringBuffer hql = new StringBuffer();
		hql.append("from Project where planId = '").append(planId).append("'");
		return super.findByHql(hql.toString());
	}

	@Override
	public List<Project> findByGas(String isGas) {
		Criteria criteria = super.getCriteria();
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		String post=loginInfo.getPost();
		// String [] postArray=post.split(",");
		Department dept=new Department();
		List<Object> deptIdArray=new ArrayList<>();
		if(post.length()>0){

			if(post.contains(PostTypeEnum.CIVIL_GROUP_LEADER.getValue())||post.contains(PostTypeEnum.MARKETING_CENTER_LEADER.getValue())||
					post.contains(PostTypeEnum.MARKETING_CENTER_LEADER_DEPUTY.getValue())||post.contains(PostTypeEnum.DEPUTY_LEADER.getValue())||
					post.contains(PostTypeEnum.MODIFICATION_GROUP_LEADER.getValue())||post.contains(PostTypeEnum.TRUNK_GROUP_LEADER.getValue())){
				StringBuffer sql = new StringBuffer("proj_id in(select cp.proj_id from project cp where cp.dept_id like'%").append(loginInfo.getDeptId()).append("%')");
				criteria.add(Restrictions.sqlRestriction(sql.toString()));
			}

			if(post.contains(PostTypeEnum.CIVIL_HEAD.getValue())){
				//民用负责人
				dept=departmentDao.queryDepartmentByDivide(DeptDivideEnum.CIVIL_GROUP.getValue(),loginInfo.getDeptId());
				if(dept!=null && StringUtils.isNotBlank(dept.getDeptId())){
					deptIdArray.add(dept.getDeptId());
				}
			}
			if(post.contains(PostTypeEnum.MARKETING_CENTER_HEAD.getValue())){
				//公建负责人
				dept=departmentDao.queryDepartmentByDivide(DeptDivideEnum.MARKETING_CENTER.getValue(),loginInfo.getDeptId());
				if(dept!=null && StringUtils.isNotBlank(dept.getDeptId())){
					deptIdArray.add(dept.getDeptId());
				}
			}
			if(post.contains(PostTypeEnum.MODIFICATION_HEAD.getValue())){
				//改管负责人
				dept=departmentDao.queryDepartmentByDivide(DeptDivideEnum.MODIFICATION_GROUP.getValue(),loginInfo.getDeptId());
				if(dept!=null && StringUtils.isNotBlank(dept.getDeptId())){
					deptIdArray.add(dept.getDeptId());
				}
			}
			if(post.contains(PostTypeEnum.TRUNK_HEAD.getValue())){
				//干线负责人
				dept=departmentDao.queryDepartmentByDivide(DeptDivideEnum.TRUNK_GROUP.getValue(),loginInfo.getDeptId());
				if(dept!=null && StringUtils.isNotBlank(dept.getDeptId())){
					deptIdArray.add(dept.getDeptId());
				}
			}

		}
		if(deptIdArray!=null && deptIdArray.size()>0){
			StringBuffer hql = new StringBuffer("from Project where deptId in ('");
			for(int i=1;i<deptIdArray.size();i++){
				hql.append(deptIdArray.get(i)).append("',").append("'");
			}
			hql.append(deptIdArray.get(0)).append("')");
			List<Project> projects = super.findByHql(hql.toString());
			List<String> projIds = new ArrayList();
			if(projects!=null&&projects.size()>0){
				for(Project project:projects){
					projIds.add(project.getProjId());
				}
				criteria.add(Restrictions.in("projId",projIds));
			}else{
				return null;
			}
		}


		if(BusinessTypeEnum.BUSINESS_GROUP.getValue().equals(loginInfo.getBusinessType())){
			//业务部门
			String deptId = loginInfo.getDeptId().substring(0, loginInfo.getDeptId().length() - DeptTypeEnum.SUBCOMPANY.getInitVal().length());
			StringBuffer sql = new StringBuffer("proj_id in(select cp.proj_id from project cp where cp.dept_id like'%").append(deptId).append("%')");
			criteria.add(Restrictions.sqlRestriction(sql.toString()));

		}else if(DeptDivideEnum.CUSTOMER_SERVICE_CENTER.getValue().equals(loginInfo.getDeptDivide())){
			if(StringUtil.isNotBlank(loginInfo.getPost()) && (loginInfo.getPost().contains(PostTypeEnum.RECORDER.getValue())|| loginInfo.getPost().contains(PostTypeEnum.CHIEF_ENGINEER.getValue()))){
				String deptId =loginInfo.getDeptId();
				StringBuffer sql = new StringBuffer("proj_id in(select cp.proj_id from project cp where cp.dept_id like'%").append(deptId).append("%')");
				criteria.add(Restrictions.sqlRestriction(sql.toString()));
			}else{
				//客服中心
				String deptId = loginInfo.getDeptId().substring(0, loginInfo.getDeptId().length() - DeptTypeEnum.SUBCOMPANY.getInitVal().length());
				StringBuffer sql = new StringBuffer("proj_id in(select cp.proj_id from project cp where cp.dept_id like'%").append(deptId).append("%')");
				criteria.add(Restrictions.sqlRestriction(sql.toString()));
			}
		}else{
			//工程部门
			StringBuffer sql = new StringBuffer("proj_id in(select cp.proj_id from project cp where cp.corp_id like '").append(loginInfo.getCorpId()).append("%')");
			criteria.add(Restrictions.sqlRestriction(sql.toString()));
		}

		if(post.contains(PostTypeEnum.BUILDER.getValue())){
			if(!post.contains(PostTypeEnum.MARKETING_CENTER_LEADER_DEPUTY.getValue())
					&& !post.contains(PostTypeEnum.MODIFICATION_GROUP_LEADER.getValue())
					&& !post.contains(PostTypeEnum.TRUNK_GROUP_LEADER.getValue())
					&& !post.contains(PostTypeEnum.DEPUTY_LEADER.getValue())){
				//现场代表查自己工程
				StringBuffer sql = new StringBuffer("proj_id in(select cp.proj_id from construction_plan cp where cp.BUILDER_ID='").append(loginInfo.getStaffId()).append("')");
				criteria.add(Restrictions.sqlRestriction(sql.toString()));
			}
		}
		//工程编号
		if(StringUtils.isNotBlank(isGas)){
			criteria.add(Restrictions.eq("isGas",isGas));
		}
		return this.findByCriteria(criteria);
	}

	@Override
	public Map<String, Object> getAccepAuditList(ProjectQueryReq req) {
		Criteria c = super.getCriteria();
		LoginInfo loginInfo = SessionUtil.getLoginInfo();

		//分公司数据过滤 如
		List<DataFilerSetUpDto> list = Constants.getDataFilterMapByKey(loginInfo.getDeptId()+"_"+req.getMenuId());
		if(list!=null && list.size()>0){
			StringBuffer hql = new StringBuffer();
			hql.append("proj_id in (select proj_id from project where 1=1 ");

			if(StringUtils.isNotBlank(list.get(0).getSupSql())){
				hql.append(list.get(0).getSupSql());
			}
			hql.append(")");
			c.add(Restrictions.sqlRestriction(hql.toString()));
		}

		//工程状态
		if(StringUtils.isNotBlank(req.getProjStatusId())){
			c.add(Restrictions.eq("projStatusId",req.getProjStatusId()));
		}else{
			return ResultUtil.pageResult(0, req.getDraw(),new ArrayList());
		}
		//工程编号
		if(StringUtils.isNotBlank(req.getProjNo())){
			c.add(Restrictions.like("projNo","%"+req.getProjNo()+"%"));
		}
		//分公司
		if(StringUtils.isNotBlank(loginInfo.getCorpId())){
			c.add(Restrictions.like("corpId",loginInfo.getCorpId()+"%"));
		}
		//工程名称
		if(StringUtils.isNotBlank(req.getProjName())){
			c.add(Restrictions.like("projName","%"+req.getProjName()+"%"));
		}
		//工程地点
		if(StringUtils.isNotBlank(req.getProjAddr())){
			c.add(Restrictions.like("projAddr","%"+req.getProjAddr()+"%"));
		}
		//工程规模
		if(StringUtils.isNotBlank(req.getProjScaleDes())){
			c.add(Restrictions.like("projScaleDes","%"+req.getProjScaleDes()+"%"));
		}
		c.addOrder(Order.desc("projNo"));
		// 数据库根据条件过滤记录数
		int filterCount = this.countByCriteria(c);
		// 根据条件获取查询信息
		List<Project> surveyInfoList = this.findBySortCriteria(c, req);

		return ResultUtil.pageResult(filterCount, req.getDraw(),surveyInfoList);
	}



	public Map<String, Object> queryHistoryProject(ProjectQueryReq projectQueryReq) throws ParseException {
		Criteria c = super.getCriteria();
		if(StringUtils.isNotBlank(projectQueryReq.getCorpId())){
			c.add(Restrictions.like("corpId",projectQueryReq.getCorpId()+"%"));
		}

		if(StringUtils.isNotBlank(projectQueryReq.getProjectType())){
			c.add(Restrictions.eq("projectType",projectQueryReq.getProjectType()));
		}
		StringBuffer hql = new StringBuffer();
		hql.append("proj_id in (select proj_id from project where 1=1  and proj_status_id!='2001' and proj_status_id!='1036' and proj_status_id!='4001'");
		hql.append(")");
		c.add(Restrictions.sqlRestriction(hql.toString()));

		// 数据库根据条件过滤记录数
		int filterCount = this.countByCriteria(c);
		// 根据条件获取查询信息
		List<Project> surveyInfoList = this.findBySortCriteria(c, projectQueryReq);

		return ResultUtil.pageResult(filterCount, projectQueryReq.getDraw(),surveyInfoList);
	}

	@Override
	public Map<String, Object> toDesignDispatchProjectList(ProjectQueryReq projectQueryReq) {
		Criteria c = super.getCriteria();
		LoginInfo loginInfo = SessionUtil.getLoginInfo();

		/*if(StringUtils.isNotBlank(loginInfo.getCorpId())){
			c.add(Restrictions.like("corpId",loginInfo.getCorpId()+"%"));
		}*/
		//根据配置的所属分公司ID过滤
		if(StringUtil.isNotBlank(loginInfo.getBelongCorpId())){
			String[] belongCorpIds = loginInfo.getBelongCorpId().split(",");
			if(belongCorpIds!=null && belongCorpIds.length>0){
				c.add(Restrictions.in("corpId",belongCorpIds));
			}
		}

		if (projectQueryReq.getProjStuList()!=null&&projectQueryReq.getProjStuList().size()>0){
			String[] strArr=projectQueryReq.getProjStuList().toArray(new String[projectQueryReq.getProjStuList().size()]);
			c.add(Restrictions.in("projStatusId",strArr));
		}

		// 数据库根据条件过滤记录数
		int filterCount = this.countByCriteria(c);
		// 根据条件获取查询信息
		List<Project> toDesignDispatchProjectList = this.findBySortCriteria(c, projectQueryReq);

		return ResultUtil.pageResult(filterCount, projectQueryReq.getDraw(),toDesignDispatchProjectList);
	}



	public Map<String, Object> getData(String projId){
		String sql = "SELECT P.PROJ_ID,P.PROJ_NO,P.PROJ_NAME,P.PROJ_ADDR,"
				+ "P.CUST_NAME,P.CUST_CONTACT,P.PROJECT_TYPE_DES,"
				+ "P.CONTRIBUTION_MODE_DES,P.CONTRACT_AMOUNT,P.INST_TASK_NOTE,R.AR_COST,R.RECEIVE_AMOUNT "
				+ "FROM PROJECT P JOIN ACCRUALS_RECORD R ON P.PROJ_ID = R.PROJ_ID WHERE AR_TYPE='13' AND P.PROJ_ID = ?";
		return this.findObjectBySql(sql, new Object[]{projId});
	}

	public List<Project> getInsttasks(String projId){
		String hql = "select p.projId,p.projNo,p.projName,p.projAddr,p.custName,p.custContact,p.projectTypeDes,"
				+ "p.contributionModeDes,p.contractAmount,p.instTaskNote "
				+ "from Project p, AccrualsRecord r  where p.projId=r.projId and r.arType=13 and p.projId = ?";
		return this.findByHql(hql,projId);
	}




	@Override
	public void updateToDoerById(String doer, String projId) {
		String sql = "UPDATE PROJECT SET TO_DOER = ? WHERE PROJ_ID = ?";
		this.executeSql(sql,new Object[]{doer,projId});
	}



	@Override
	public Map<String, Object> queryProjectMap(ProjectQueryReq req) {
		Criteria c = super.getCriteria();
		LoginInfo loginInfo = SessionUtil.getLoginInfo();

		if(StringUtils.isNotBlank(req.getProjNo())) {//工程编号
			c.add(Restrictions.eq("projNo", req.getProjNo()));
		}else{
			if(StringUtils.isNotBlank(req.getProjStatusId())){	//工程状态
				c.add(Restrictions.eq("projStatusId",req.getProjStatusId()));
			}else{
				StringBuffer hql = new StringBuffer();
				hql.append("proj_status_id not in ('1035','1036','2001')");
				c.add(Restrictions.sqlRestriction(hql.toString()));
			}
		}



		if (StringUtils.isNotBlank(req.getProjName())) {	//工程名称
			c.add(Restrictions.like("projName", "%" + req.getProjName() + "%"));
		}


		if (StringUtils.isNotBlank(req.getProjAddr())) {//工程地点
			c.add(Restrictions.like("projAddr", "%" + req.getProjAddr() + "%"));
		}


		//分公司过滤
		c.add(Restrictions.like("corpId",loginInfo.getCorpId()+"%"));


		/*if (!loginInfo.getPost().contains(PostTypeEnum.ADMIN.getValue())) {//非管理员根据部门过滤
			c.add(Restrictions.eq("deptId", loginInfo.getDeptId()));
		}*/
		String post = loginInfo.getPost();
		if(post!=null && post.length()>0){
			//市场营销员
			if(post.contains(PostTypeEnum.SALESMA.getValue())){
				List<DataFilerSetUpDto> costerList = Constants.getDataFilterMapByKey(PostTypeEnum.SALESMA.getValue()+"_"+req.getMenuId());
				if(costerList!=null && costerList.size()>0){
					if(StringUtils.isNotBlank(costerList.get(0).getSupSql())){
						c.add(Restrictions.eq(costerList.get(0).getSupSql(), loginInfo.getStaffId()));
					}
				}
			}

			//踏勘员
			if(post.contains(PostTypeEnum.SURVEYER.getValue())){
				List<DataFilerSetUpDto> costerList = Constants.getDataFilterMapByKey(PostTypeEnum.SURVEYER.getValue()+"_"+req.getMenuId());
				if(costerList!=null && costerList.size()>0){
					if(StringUtils.isNotBlank(costerList.get(0).getSupSql())){
						c.add(Restrictions.eq(costerList.get(0).getSupSql(), loginInfo.getStaffId()));
					}
				}
			}
		}


		// 数据库根据条件过滤记录数
		int filterCount = this.countByCriteria(c);
		// 根据条件获取查询信息
		List<Project> projectList = this.findBySortCriteria(c, req);

		return ResultUtil.pageResult(filterCount, req.getDraw(),projectList);

	}
	/**
	 * 施工单位过滤sql组装
	 * @author liaoyq
	 * @createTime 2019-4-17
	 */
	@Override
	public StringBuffer cuUnitFilter(LoginInfo loginInfo,String post,String menuId){
		StringBuffer sql= new StringBuffer();
		//首先根据公司IDs
		sql= new StringBuffer("proj_id in(select cp.proj_id from construction_plan cp where cp.CU_ID='").append(loginInfo.getDeptId()).append("' ");

		boolean flag = true;
		//过滤个人特殊配置
		List<DataFilerSetUpDto> listCon = Constants.getDataFilterMapByKey(loginInfo.getStaffId()+"_"+menuId);
		String filtersql = "";
		if(listCon!=null && listCon.size()>0){
			if(StringUtil.isNotBlank(listCon.get(0).getSupSql())){
				filtersql = listCon.get(0).getSupSql();
				flag = false;
			}
		}
		//项目经理等相关派工的职务并且没有特殊配置的时候
		if((post.contains(PostTypeEnum.CU_PM.getValue())||
				post.contains(PostTypeEnum.SAFTYOFFICER.getValue())||
				post.contains(PostTypeEnum.CONSTRUCTION.getValue())||
				post.contains(PostTypeEnum.MANAGEMENTWACF.getValue())||
				post.contains(PostTypeEnum.BUSINESSASSISTANT.getValue())||
				post.contains(PostTypeEnum.QUALITATIVE_CHECK_MEMBER.getValue())||
				post.contains(PostTypeEnum.TEST_LEADER.getValue())||
				post.contains(PostTypeEnum.WELDER.getValue())) && !post.contains(PostTypeEnum.BUDGET_MEMBER.getValue()) && flag){
			sql.append(" and (");
			sql.append(" cp.CU_PM_ID='").append(loginInfo.getStaffId()).append("'");
			sql.append(" or cp.SAFTY_OFFICER_ID='").append(loginInfo.getStaffId()).append("'");
			sql.append(" or cp.MANAGEMENT_QAE_ID='").append(loginInfo.getStaffId()).append("'");
			sql.append(" or cp.management_wacf_id='").append(loginInfo.getStaffId()).append("'");
			sql.append(" or cp.technician_id='").append(loginInfo.getStaffId()).append("'");
			sql.append(" or cp.test_leader_id like'%,").append(loginInfo.getStaffId()).append(",%'");
			sql.append(" or cp.welder_id like'%,").append(loginInfo.getStaffId()).append(",%'");
			//由于资料员有空的情况
			if(post.contains(PostTypeEnum.BUSINESSASSISTANT.getValue())){
				sql.append(" or cp.documenter_id='").append(loginInfo.getStaffId()).append("'").append("or cp.documenter_id is null");  //加载具有资料员的工程和资料员为空的工程，此代码后期添加，所以前期没有资料员的工程也应该加载
			}
			sql.append(") ");
		}
		//再根据所属分公司过滤
		if(StringUtil.isNotBlank(loginInfo.getBelongCorpId())){
			sql.append(" and cp.corp_id in('");
			String [] belongCorpIds = loginInfo.getBelongCorpId().split(",");
			for(int i=1;i<belongCorpIds.length;i++){
				sql.append(belongCorpIds[i]).append("','");
			}
			sql.append(belongCorpIds[0]).append("') ");
		}
		//特殊配置
		if(StringUtil.isNotBlank(filtersql)){
			sql.append(filtersql);
		}
		sql.append(")");
		return sql;
	}
	/**
	 * 监理单位过滤sql组装
	 * @author liaoyq
	 * @createTime 2019-4-17
	 */
	@Override
	public StringBuffer suUnitFilter(LoginInfo loginInfo,String post,String menuId){
		StringBuffer sql= new StringBuffer();
		//首先根据公司ID
		sql= new StringBuffer("proj_id in(select cp.proj_id from construction_plan cp where cp.SU_ID='").append(loginInfo.getDeptId()).append("' ");

		boolean flag = true;
		//过滤个人特殊配置
		List<DataFilerSetUpDto> listCon = Constants.getDataFilterMapByKey(loginInfo.getStaffId()+"_"+menuId);
		String filtersql = "";
		if(listCon!=null && listCon.size()>0){
			if(StringUtil.isNotBlank(listCon.get(0).getSupSql())){
				filtersql = listCon.get(0).getSupSql();
				flag = false;
			}
		}
		//总监等相关派工的职务并且没有特殊配置的时候
		if((post.contains(PostTypeEnum.SUCSE.getValue())||
				post.contains(PostTypeEnum.SUJGJ.getValue())||
				post.contains(PostTypeEnum.PROFESSIONAL_SUPERVISION.getValue())||
				post.contains(PostTypeEnum.SUCSE_REPRESENTATIVE.getValue())) && flag){
			sql.append(" and (");
			sql.append(" cp.SU_CSE_ID='").append(loginInfo.getStaffId()).append("'");
			sql.append(" or cp.SU_JGJ_ID='").append(loginInfo.getStaffId()).append("'");
			sql.append(" or cp.SU_PROFESSIONAL_ID='").append(loginInfo.getStaffId()).append("'");
			sql.append(" or cp.SU_REPRESENTATIVE_ID='").append(loginInfo.getStaffId()).append("'");
			sql.append(") ");
		}

		//再根据所属分公司过滤
		if(StringUtil.isNotBlank(loginInfo.getBelongCorpId())){
			sql.append(" and cp.corp_id in('");
			String [] belongCorpIds = loginInfo.getBelongCorpId().split(",");
			for(int i=1;i<belongCorpIds.length;i++){
				sql.append(belongCorpIds[i]).append("','");
			}
			sql.append(belongCorpIds[0]).append("') ");
		}
		//特殊配置
		if(StringUtil.isNotBlank(filtersql)){
			sql.append(filtersql);
		}
		sql.append(")");
		return sql;
	}

	@Override
	public Map<String, Object> queryProjectSerivce(ProjectQueryReq projectQueryReq) throws ParseException {
		Criteria c = super.getCriteria();

		//工程编号
		if(StringUtils.isNotBlank(projectQueryReq.getProjNo())){
			c.add(Restrictions.eq("projNo",projectQueryReq.getProjNo()));
		}
		//工程名称
		if(StringUtils.isNotBlank(projectQueryReq.getProjName())){
			c.add(Restrictions.like("projName","%"+projectQueryReq.getProjName()+"%"));
		}
		//工程状态
		if(StringUtils.isNotBlank(projectQueryReq.getProjStatusId())){
			c.add(Restrictions.eq("projStatusId",projectQueryReq.getProjStatusId()));
		}
		if(projectQueryReq.getProjStuList()!=null && projectQueryReq.getProjStuList().size()>0){
			List<String> projStus=projectQueryReq.getProjStuList();
			c.add(Restrictions.in("projStatusId",projStus));
		}
		//公司ID
		if(StringUtils.isNotBlank(projectQueryReq.getCorpId())){
			c.add(Restrictions.eq("corpId",projectQueryReq.getCorpId()));
		}
		//公司名称
		if(StringUtils.isNotBlank(projectQueryReq.getCorpName())){
			c.add(Restrictions.like("corpName","%"+projectQueryReq.getCorpName()+"%"));
		}
		//业务部门
		if(StringUtils.isNotBlank(projectQueryReq.getDeptId())){
			c.add(Restrictions.eq("deptId",projectQueryReq.getDeptId()));
		}
		//工程类型
		if(StringUtils.isNotBlank(projectQueryReq.getProjectType())){
			c.add(Restrictions.like("projectType",projectQueryReq.getProjectType()));
		}
		//出资方式
		if(StringUtils.isNotBlank(projectQueryReq.getContributionMode())){
			c.add(Restrictions.like("contributionMode",projectQueryReq.getContributionMode()));
		}
		
		//工程大类
		if(StringUtils.isNotBlank(projectQueryReq.getProjectType())){
			c.add(Restrictions.like("projLtypeId","%"+projectQueryReq.getProjectType()+"%"));
		}
		
		//工程地点
		if(StringUtils.isNotBlank(projectQueryReq.getProjAddr())){
			c.add(Restrictions.like("projAddr", "%"+projectQueryReq.getProjAddr()+"%"));
		}
		//工程规模
		if(StringUtils.isNotBlank(projectQueryReq.getProjScaleDes())){
			c.add(Restrictions.like("projScaleDes", "%"+projectQueryReq.getProjScaleDes()+"%"));
		}
		//区域
		if(StringUtils.isNotBlank(projectQueryReq.getArea())){
			c.add(Restrictions.eq("area",projectQueryReq.getArea()));
		}
		//勘察人
		if(StringUtils.isNotBlank(projectQueryReq.getSurveyer())){
			c.add(Restrictions.like("surveyer", "%"+projectQueryReq.getSurveyer()+"%"));
		}

		//设计人
		if(StringUtils.isNotBlank(projectQueryReq.getDesigner())){
			c.add(Restrictions.like("designer", "%"+projectQueryReq.getDesigner()+"%"));
		}


		//客户名称
		if(StringUtils.isNotBlank(projectQueryReq.getCustName())){
			c.add(Restrictions.like("custName", "%"+projectQueryReq.getCustName()+"%"));
		}
		//客户联系人
		if(StringUtils.isNotBlank(projectQueryReq.getCustContact())){
			c.add(Restrictions.like("custContact", "%"+projectQueryReq.getCustContact()+"%"));
		}
		//客户联系人电话
		if(StringUtils.isNotBlank(projectQueryReq.getCustPhone())){
			c.add(Restrictions.like("custPhone", "%"+projectQueryReq.getCustPhone()+"%"));
		}

		//预算员
		if(StringUtils.isNotBlank(projectQueryReq.getBudgeter())){
			c.add(Restrictions.like("budgeter", "%"+projectQueryReq.getBudgeter()+"%"));
		}
		if(StringUtils.isNotBlank(projectQueryReq.getBudgeterId())){
			c.add(Restrictions.eq("budgeterId", projectQueryReq.getBudgeterId()));
		}
		//造价员
		if(StringUtils.isNotBlank(projectQueryReq.getCostMember())){
			c.add(Restrictions.like("costMember", "%"+projectQueryReq.getCostMember()+"%"));
		}
		//结算员
		if(StringUtils.isNotBlank(projectQueryReq.getSettlementer())){
			c.add(Restrictions.like("settlementer", "%"+projectQueryReq.getSettlementer()+"%"));
		}
		//合同员
		if(StringUtils.isNotBlank(projectQueryReq.getOperator())){
			c.add(Restrictions.like("operator", "%"+projectQueryReq.getOperator()+"%"));
		}

		//合同编号
		if(StringUtils.isNotBlank(projectQueryReq.getConNo())){
			StringBuffer sql=new StringBuffer();
			sql.append("proj_id in(select c.proj_id from contract c where c.con_no like '%").append(projectQueryReq.getConNo()).append("%')");
			c.add(Restrictions.sqlRestriction(sql.toString()));
		}

		//用于字符串日期格式转换
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		//受理日期开始日期
		if(StringUtils.isNotBlank(projectQueryReq.getAcceptDateStart())){
			c.add(Restrictions.ge("acceptDate", sdf.parse(projectQueryReq.getAcceptDateStart())));
		}
		//受理日期结束日期
		if(StringUtils.isNotBlank(projectQueryReq.getAcceptDateEnd())){
			c.add(Restrictions.le("acceptDate", sdf.parse(projectQueryReq.getAcceptDateEnd())));
		}
		//勘察完成开始日期
		if(StringUtils.isNotBlank(projectQueryReq.getSurveyDateStart())){
			c.add(Restrictions.ge("surveyDate", sdf.parse(projectQueryReq.getSurveyDateStart())));
		}
		//勘察完成结束日期
		if(StringUtils.isNotBlank(projectQueryReq.getSurveyDateEnd())){
			c.add(Restrictions.le("surveyDate", sdf.parse(projectQueryReq.getSurveyDateEnd())));
		}
		//设计完成开始日期
		if(StringUtils.isNotBlank(projectQueryReq.getDuCompleteDateStart())){
			c.add(Restrictions.ge("duCompleteDate", sdf.parse(projectQueryReq.getDuCompleteDateStart())));
		}
		//设计完成结束日期
		if(StringUtils.isNotBlank(projectQueryReq.getDuCompleteDateEnd())){
			c.add(Restrictions.le("duCompleteDate", sdf.parse(projectQueryReq.getDuCompleteDateEnd())));
		}

		//预算日期开始日期
		if(StringUtils.isNotBlank(projectQueryReq.getBudgetDateStart())){
			c.add(Restrictions.ge("budgetDate", sdf.parse(projectQueryReq.getBudgetDateStart())));
		}
		//预算日期结束日期
		if(StringUtils.isNotBlank(projectQueryReq.getBudgetDateEnd())){
			c.add(Restrictions.le("budgetDate", sdf.parse(projectQueryReq.getBudgetDateEnd())));
		}

		 /*//合同签订开始日期
		 if(StringUtils.isNotBlank(projectQueryReq.getContractSignDateStart())){
			 c.add(Restrictions.ge("signDate", sdf.parse(projectQueryReq.getContractSignDateStart())));
		 }
		 //合同签订结束日期
		 if(StringUtils.isNotBlank(projectQueryReq.getContractSignDateEnd())){
			 c.add(Restrictions.le("signDate", sdf.parse(projectQueryReq.getContractSignDateEnd())));
		 }*/
		//验收日期开始日期
		if(StringUtils.isNotBlank(projectQueryReq.getAcceptanceDateStart())){
			c.add(Restrictions.ge("acceptanceDate", sdf.parse(projectQueryReq.getAcceptanceDateStart())));
		}
		//验收日期结束日期
		if(StringUtils.isNotBlank(projectQueryReq.getAcceptanceDateEnd())){
			c.add(Restrictions.le("acceptanceDate", sdf.parse(projectQueryReq.getAcceptanceDateEnd())));
		}
		//结算日期开始日期
		if(StringUtils.isNotBlank(projectQueryReq.getSettlementDateStart())){
			c.add(Restrictions.ge("settlementDate", sdf.parse(projectQueryReq.getSettlementDateStart())));
		}
		//结算日期结束日期
		if(StringUtils.isNotBlank(projectQueryReq.getSettlementDateEnd())){
			c.add(Restrictions.le("settlementDate", sdf.parse(projectQueryReq.getSettlementDateEnd())));
		}
		//分包合同签定日期
		if(StringUtils.isNotBlank(projectQueryReq.getSubContractDateStart()) || StringUtils.isNotBlank(projectQueryReq.getSubContractDateEnd())){
			StringBuffer sql = new StringBuffer("select sc.proj_id from sub_contract sc where 1=1");
			//分包合同签定开始日期
			if(StringUtils.isNotBlank(projectQueryReq.getSubContractDateStart())){
				sql.append(" and sc.sc_sign_date>=").append(mysqlSqlConveter.dataOperate(projectQueryReq.getSubContractDateStart()));
			}
			//分包合同签定结束日期
			if(StringUtils.isNotBlank(projectQueryReq.getSubContractDateEnd())){
				sql.append(" and sc.sc_sign_date<=").append(mysqlSqlConveter.dataOperate(projectQueryReq.getSubContractDateEnd()));
			}
			StringBuffer resultSql = new StringBuffer("proj_id in(").append(sql.toString()).append(")");
			c.add(Restrictions.sqlRestriction(resultSql.toString()));
		}

		//安装合同签订日期
		if(StringUtils.isNotBlank(projectQueryReq.getContractSignDateStart())|| StringUtils.isNotBlank(projectQueryReq.getContractSignDateEnd())){
			StringBuffer sql = new StringBuffer("select c.proj_id from contract c where 1=1");
			//合同签定开始日期
			if(StringUtils.isNotBlank(projectQueryReq.getContractSignDateStart())){
				sql.append(" and c.sign_date>=").append(mysqlSqlConveter.dataOperate(projectQueryReq.getContractSignDateStart()));
			}
			//合同签定结束日期
			if(StringUtils.isNotBlank(projectQueryReq.getContractSignDateEnd())){
				sql.append(" and c.sign_date<=").append(mysqlSqlConveter.dataOperate(projectQueryReq.getContractSignDateEnd()));
			}
			StringBuffer resultSql = new StringBuffer("proj_id in(").append(sql.toString()).append(")");
			c.add(Restrictions.sqlRestriction(resultSql.toString()));
		}
		//计划查 开工日期-查合同表
		if(StringUtils.isNotBlank(projectQueryReq.getPlannedStartDateStart())|| StringUtils.isNotBlank(projectQueryReq.getPlannedStartDateEnd())){
			StringBuffer sql = new StringBuffer("select c.proj_id from contract c where 1=1");
			//开工日期开始日期
			if(StringUtils.isNotBlank(projectQueryReq.getPlannedStartDateStart())){
				sql.append(" and c.planned_start_date>=").append(mysqlSqlConveter.dataOperate(projectQueryReq.getPlannedStartDateStart()));
			}
			//开工日期结束日期
			if(StringUtils.isNotBlank(projectQueryReq.getPlannedStartDateEnd())){
				sql.append(" and c.planned_start_date<=").append(mysqlSqlConveter.dataOperate(projectQueryReq.getPlannedStartDateEnd()));
			}
			StringBuffer resultSql = new StringBuffer("proj_id in(").append(sql.toString()).append(")");
			c.add(Restrictions.sqlRestriction(resultSql.toString()));
		}

		//计划查 竣工日期-查合同表
		if(StringUtils.isNotBlank(projectQueryReq.getPlannedEndDateStart())|| StringUtils.isNotBlank(projectQueryReq.getPlannedEndDateEnd())){
			StringBuffer sql = new StringBuffer("select c.proj_id from contract c where 1=1");
			//合同签定开始日期
			if(StringUtils.isNotBlank(projectQueryReq.getPlannedEndDateStart())){
				sql.append(" and c.planned_end_date>=").append(mysqlSqlConveter.dataOperate(projectQueryReq.getPlannedEndDateStart()));
			}
			//合同签定结束日期
			if(StringUtils.isNotBlank(projectQueryReq.getPlannedEndDateEnd())){
				sql.append(" and c.planned_end_date<=").append(mysqlSqlConveter.dataOperate(projectQueryReq.getPlannedEndDateEnd()));
			}
			StringBuffer resultSql = new StringBuffer("proj_id in(").append(sql.toString()).append(")");
			c.add(Restrictions.sqlRestriction(resultSql.toString()));
		}


		//开工日期开始日期
		if(StringUtils.isNotBlank(projectQueryReq.getStartDateStart())){
			c.add(Restrictions.ge("startDate", sdf.parse(projectQueryReq.getStartDateStart())));
		}
		//开工日期结束日期
		if(StringUtils.isNotBlank(projectQueryReq.getStartDateEnd())){
			c.add(Restrictions.le("startDate", sdf.parse(projectQueryReq.getStartDateEnd())));
		}
		//竣工日期开始日期
		if(StringUtils.isNotBlank(projectQueryReq.getCompleteDateStart())){
			c.add(Restrictions.ge("completedDate", sdf.parse(projectQueryReq.getCompleteDateStart())));
		}
		//竣工日期结束日期
		if(StringUtils.isNotBlank(projectQueryReq.getCompleteDateEnd())){
			c.add(Restrictions.le("completedDate", sdf.parse(projectQueryReq.getCompleteDateEnd())));
		}
		//结束开始日期
		if(StringUtils.isNotBlank(projectQueryReq.getFinishedDateStart()) && StringUtils.isBlank(projectQueryReq.getFinishedDateEnd())){
			StringBuffer sql = new StringBuffer();
			sql.append("(finished_Date is null or finished_date>=").append(mysqlSqlConveter.dataOperate(projectQueryReq.getFinishedDateStart())).append(")");
			c.add(Restrictions.sqlRestriction(sql.toString()));
		}
		if(StringUtils.isNotBlank(projectQueryReq.getFinishedDateStart()) && StringUtils.isNotBlank(projectQueryReq.getFinishedDateEnd())){
			c.add(Restrictions.ge("finishedDate", sdf.parse(projectQueryReq.getFinishedDateStart())));
			c.add(Restrictions.le("finishedDate", sdf.parse(projectQueryReq.getFinishedDateEnd())));
		}
		//结束截止日期
		if(StringUtils.isNotBlank(projectQueryReq.getFinishedDateEnd())){
			c.add(Restrictions.ge("finishedDate", sdf.parse(projectQueryReq.getFinishedDateEnd())));
		}
		//造价确认开始日期
		if(StringUtils.isNotBlank(projectQueryReq.getAffirmCostDateStart())){
			c.add(Restrictions.ge("affirmCostDate", sdf.parse(projectQueryReq.getAffirmCostDateStart())));
		}
		//造价确认结束日期
		if(StringUtils.isNotBlank(projectQueryReq.getAffirmCostDateEnd())){
			c.add(Restrictions.le("affirmCostDate", sdf.parse(projectQueryReq.getAffirmCostDateEnd())));
		}
		//分包方名称
		if(StringUtils.isNotBlank(projectQueryReq.getCuName())){
			StringBuffer sql = new StringBuffer();
			sql.append("proj_id in(select cp.proj_id from CONSTRUCTION_PLAN cp where cp.CU_NAME like '%").append(projectQueryReq.getCuName()).append("%')");
			c.add(Restrictions.sqlRestriction(sql.toString()));
		}
		//分包方id
		if(StringUtils.isNotBlank(projectQueryReq.getCuId())){
			StringBuffer sql = new StringBuffer();
			sql.append("proj_id in(select cp.proj_id from CONSTRUCTION_PLAN cp where cp.CU_ID = '").append(projectQueryReq.getCuId()).append("')");
			c.add(Restrictions.sqlRestriction(sql.toString()));
		}


		//是否需要更正
		if(StringUtils.isNotBlank(projectQueryReq.getIsModify())){
			c.add(Restrictions.eq("isModify", projectQueryReq.getIsModify()));
		}

		//反馈状态
		if(StringUtils.isNotBlank(projectQueryReq.getFeedbackState())){
			c.add(Restrictions.eq("feedbackState",projectQueryReq.getFeedbackState()));
		}

		//智能表标记
		if(StringUtils.isNotBlank(projectQueryReq.getIsIntelligentMeter())){
			c.add(Restrictions.eq("isIntelligentMeter",projectQueryReq.getIsIntelligentMeter()));
		}

		//现场代表
		if(StringUtils.isNotBlank(projectQueryReq.getBuilder())){
			StringBuffer sql = new StringBuffer("proj_id in(select cp.proj_id from construction_plan cp where cp.BUILDER like '%").append(projectQueryReq.getBuilder()).append("%')");
			c.add(Restrictions.sqlRestriction(sql.toString()));
		}
		//现场监理
		if(StringUtils.isNotBlank(projectQueryReq.getSuJgj())){
			StringBuffer sql = new StringBuffer("proj_id in(select cp.proj_id from construction_plan cp where cp.SU_JGJ like '%").append(projectQueryReq.getSuJgj()).append("%')");
			c.add(Restrictions.sqlRestriction(sql.toString()));
		}
		//分包预算审核员
		if(StringUtils.isNotBlank(projectQueryReq.getBudgeterAudit())){
			c.add(Restrictions.like("budgeterAudit","%"+projectQueryReq.getBudgeterAudit()+"%"));
		}
		//电子图申请审核状态
		if(StringUtils.isNotBlank(projectQueryReq.getAuditState())){
			if(!projectQueryReq.getAuditState().equals(AuditResultEnum.NOT_APPLY.getValue())){
				StringBuffer sql = new StringBuffer("proj_id in(select cp.proj_id from DRAWING_APPLY cp where cp.AUDIT_STATE='").append(projectQueryReq.getAuditState()).append("')");
				c.add(Restrictions.sqlRestriction(sql.toString()));
			}
		}
		//主合同已有收款的工程
		if(StringUtil.isNotBlank(projectQueryReq.getArStatus()) && ARStatusEnum.ALREADY_CHARGE.getValue().equals(projectQueryReq.getArStatus())){
			String sql = "EXISTS ( SELECT cf.proj_id FROM cash_flow cf WHERE cf.proj_id = this_.proj_id AND cf.IS_VALID = '1' and cf.CONTRACT_TYPE='"+ArContractTypeEnum.CONSTRUCTION.getValue()+"')";
			c.add(Restrictions.sqlRestriction(sql));
		}
		if(StringUtil.isBlank(projectQueryReq.getSortName())){
			c.addOrder(Order.desc("acceptDate"));  // 受理日期
		}

		// 数据库根据条件过滤记录数
		int filterCount = this.countByCriteria(c);
		//判断是否传递了分页信息
		if(projectQueryReq.getStart() == null ||projectQueryReq.getStart()<0){
			projectQueryReq.setStart(0);
		}
		//没有传递分页信息，则查询全部
		if(projectQueryReq.getLength() == null || projectQueryReq.getLength()<0){
			projectQueryReq.setLength(filterCount);
		}
		// 根据条件获取查询信息
		List<Project> projectList = this.findBySortCriteria(c, projectQueryReq);
		// 返回结果
		return ResultUtil.pageResult( filterCount, projectQueryReq.getDraw(),projectList);
	}
}
