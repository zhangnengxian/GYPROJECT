package cc.dfsoft.project.biz.base.subpackage.dao.impl;

import cc.dfsoft.project.biz.base.baseinfo.dao.BusinessPartnersDao;
import cc.dfsoft.project.biz.base.baseinfo.entity.BusinessPartners;
import cc.dfsoft.project.biz.base.design.dto.DesignerQueryReq;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.settlement.enums.AuditResultEnum;
import cc.dfsoft.project.biz.base.subpackage.dao.PaymentApplyDao;
import cc.dfsoft.project.biz.base.subpackage.dto.PaymentApplyReq;
import cc.dfsoft.project.biz.base.subpackage.entity.PaymentApply;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dao.DepartmentDao;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.biz.sys.dept.entity.Department;
import cc.dfsoft.uexpress.biz.sys.dept.enums.*;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.constant.DataFilerSetUpDto;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;
import cc.dfsoft.uexpress.common.util.MoneyConverter;
import cc.dfsoft.uexpress.common.util.ResultUtil;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 付款申请
 * @author fuliwei
 *
 */
@Repository
public class PaymentApplyDaoImpl extends NewBaseDAO<PaymentApply,String> implements PaymentApplyDao{
	
	/**部门审核*/
	@Resource
	DepartmentDao departmentDao;
	
	@Resource
    BusinessPartnersDao businessPartnersDao;
	
	/**
	 * 付款申请审核列表
	 * @author fuliwei
	 * @createTime 2017年8月7日
	 * @param 
	 * @return
	 */
	@Override
	public Map<String, Object> queryPaymentApplyAudit(PaymentApplyReq req) throws ParseException {
		Criteria c = super.getCriteria();
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		//分公司数据过滤 如
		String corpId = "";
		if(loginInfo.getBelongCorpId() != null){
			corpId = loginInfo.getBelongCorpId();
		}else{  
			corpId = loginInfo.getDeptId();
		}
		 List<DataFilerSetUpDto> list2 = Constants.getDataFilterMapByKey(corpId+"_"+req.getMenuId());
		/* if(list2!=null && list2.size()>0){
			 StringBuffer hql = new StringBuffer();
			 hql.append("proj_id in (select proj_id from project where 1=1 ");
			 
			 if(StringUtils.isNotBlank(list2.get(0).getSupSql())){
				 hql.append(list2.get(0).getSupSql());
			 }
			 hql.append(")");
			 c.add(Restrictions.sqlRestriction(hql.toString()));
		 }*/

		//根据Id进行查询
		if(StringUtils.isNotBlank(req.getPaId())){
			c.add(Restrictions.eq("paId",req.getPaId()));
		}

		//根据工程Id进行查询
		if(StringUtils.isNotBlank(req.getProjId())){
			 c.add(Restrictions.eq("projId",req.getProjId()));
		}

		//根据申请付款类型进行查询
		if(StringUtils.isNotBlank(req.getPayType())){
			c.add(Restrictions.eq("payType",req.getPayType()));
		}
      
		
		//根据申请人ID查询
		if(StringUtils.isNotBlank(req.getApplyerId())){
			c.add(Restrictions.eq("applyerId", req.getApplyerId()));
		}
		
		//费用类型
		if(StringUtils.isNotBlank(req.getFeeType())){
			c.add(Restrictions.eq("feeType",req.getFeeType()));
		}

		//申请费用类型
		if(StringUtils.isNotBlank(req.getApplyFeeType())){
			c.add(Restrictions.eq("applyFeeType",req.getApplyFeeType()));
		}

		/*if(DeptDivideEnum.CUSTOMER_SERVICE_CENTER.getValue().equals(loginInfo.getDeptDivide())){
			//客服中心 过滤看自己的工程；
			StringBuffer sql = new StringBuffer("proj_id in(select p.proj_id from project p where p.DEPT_ID like'").append(loginInfo.getDeptId()).append("%')");
			c.add(Restrictions.sqlRestriction(sql.toString()));
		}*/

		//审核状态
		if(req.getAuditList()!=null && req.getAuditList().size()>0){
			 List<String> auditState=req.getAuditList();
			 c.add(Restrictions.in("auditState",auditState));
		}else{
			if(StringUtils.isNotBlank(req.getAuditState())){
				c.add(Restrictions.eq("auditState",req.getAuditState()));
			}else{
				//默认不加载审核通过的
				c.add(Restrictions.ne("auditState", AuditResultEnum.PASSED.getValue()));
			}
		}

		//工程编号
		if(StringUtils.isNotBlank(req.getProjNo())){
			c.add(Restrictions.like("projNo","%"+req.getProjNo()+"%"));
		}
		//工程名称
		if(StringUtils.isNotBlank(req.getProjName())){
			c.add(Restrictions.like("projName","%"+req.getProjName()+"%"));
		}
		//工程地点
		if(StringUtils.isNotBlank(req.getProjAddr())){
			c.add(Restrictions.like("projAddr","%"+req.getProjAddr()+"%"));
		}

		//申请编号
		if(StringUtils.isNotBlank(req.getApplyNo())){
			c.add(Restrictions.like("applyNo","%"+req.getApplyNo()+"%"));
		}

		//请款单位
		if(StringUtils.isNotBlank(req.getRequestFoundsUnit())){
			c.add(Restrictions.like("requestFoundsUnit","%"+req.getRequestFoundsUnit()+"%"));
		}

		//申请单位
		if(StringUtils.isNotBlank(req.getApplyDeptName())){
			c.add(Restrictions.like("applyDeptName","%"+req.getApplyDeptName()+"%"));
		}
		//用于字符串日期格式转换
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		 //申请日期开始
		 if(StringUtils.isNotBlank(req.getApplyStartDate())){
			 c.add(Restrictions.ge("applyDate", sdf.parse(req.getApplyStartDate())));
		 }
		 //申请日期结束
		 if(StringUtils.isNotBlank(req.getApplyEndDate())){
			 c.add(Restrictions.le("applyDate", sdf.parse(req.getApplyEndDate())));
		 }
		//分公司数据过滤 如
		 boolean flag = true;
		 List<DataFilerSetUpDto> listCorp = Constants.getDataFilterMapByKey(loginInfo.getCorpId()+"_"+req.getMenuId());
		 if(listCorp!=null && listCorp.size()>0){
			 c.add(Restrictions.like("orgId", listCorp.get(0).getSupSql()+"%"));
			 flag = false;
		 }
		 String post=loginInfo.getPost();
		 String [] postArray=post.split(",");

		 //分包方单位人员登录时，只可看自己相关项目
		 BusinessPartners bp =  businessPartnersDao.get(loginInfo.getDeptId());

		 if(!DeptDivideEnum.DESIGN_INSTITUTE.getValue().equals(loginInfo.getDeptDivide())&& bp==null){
			 if(BusinessTypeEnum.BUSINESS_GROUP.getValue().equals(loginInfo.getBusinessType())){	 //业务部门
				 if(post.contains(PostTypeEnum.BUDGET_MEMBER.getValue())){
					 if(!post.contains(PostTypeEnum.BUDGET_GROUP_LEADER.getValue())){
						 //预算员审核施工预算的 或者结算员是自己的 所以改为查审核人的
						 //StringBuffer sql= new StringBuffer("proj_id in(select cp.proj_id from Project cp where cp.budgeter_audit_id = '").append(loginInfo.getStaffId()).append("')");
						 //c.add(Restrictions.sqlRestriction(sql.toString()));
						 c.add(Restrictions.eq("paAuditerId", loginInfo.getStaffId()));
					 }else{
						 String deptId = loginInfo.getDeptId().substring(0, loginInfo.getDeptId().length() - DeptTypeEnum.SUBCOMPANY.getInitVal().length());
						 c.add(Restrictions.like("orgId", deptId+"%"));
					 }
				 }else{
					 String deptId = loginInfo.getDeptId().substring(0, loginInfo.getDeptId().length() - DeptTypeEnum.SUBCOMPANY.getInitVal().length());
					 c.add(Restrictions.like("orgId", deptId+"%"));
				 }
			 }else if(DeptDivideEnum.CUSTOMER_SERVICE_CENTER.getValue().equals(loginInfo.getDeptDivide())){

				 if(!post.contains(PostTypeEnum.CHIEF_ENGINEER.getValue())
						 && !post.contains(PostTypeEnum.RECORDER.getValue())){
					 String deptId = loginInfo.getDeptId().substring(0, loginInfo.getDeptId().length() - DeptTypeEnum.SUBCOMPANY.getInitVal().length());
					 c.add(Restrictions.like("orgId", "%"+deptId+"%"));
				 }else{
					 c.add(Restrictions.like("orgId", "%"+loginInfo.getDeptId()+"%"));
				 }


			 }else if(flag){
				 //如果是市场发展部的
				 if(DeptDivideEnum.CIVIL_GROUP.getValue().equals(loginInfo.getDeptDivide())||DeptDivideEnum.MARKETING_CENTER.getValue().equals(loginInfo.getDeptDivide())||
						 DeptDivideEnum.TRUNK_GROUP .getValue().equals(loginInfo.getDeptDivide())||DeptDivideEnum.MODIFICATION_GROUP.getValue().equals(loginInfo.getDeptDivide())){
					 //工程部门 民用 公建 改管 干线
					 if(post.contains(PostTypeEnum.BUDGET_MEMBER.getValue())){
						 StringBuffer sql= new StringBuffer("proj_id in(select cp.proj_id from Project cp where cp.budgeter_audit_id = '").append(loginInfo.getStaffId()).append("')");
						 c.add(Restrictions.sqlRestriction(sql.toString()));
					 }else{
						 c.add(Restrictions.like("orgId", loginInfo.getDeptId()+"%"));
					 }
				 }else{
					 //String deptId = loginInfo.getDeptId().substring(0, loginInfo.getDeptId().length() - DeptTypeEnum.SUBCOMPANY.getInitVal().length());
					 c.add(Restrictions.like("orgId", "%"+loginInfo.getDeptId()+"%"));
				 }
			 }
		 }

		 Department dept=new Department();


		 List<Object> deptIdArray=new ArrayList<>();
		 if(postArray.length>0){
				 if(post.contains(PostTypeEnum.CIVIL_GROUP_LEADER.getValue())||post.contains(PostTypeEnum.MARKETING_CENTER_LEADER.getValue())||
						 post.contains(PostTypeEnum.MODIFICATION_GROUP_LEADER.getValue())||post.contains(PostTypeEnum.TRUNK_GROUP_LEADER.getValue())){
					 StringBuffer sql = new StringBuffer("proj_id in(select cp.proj_id from project cp where cp.dept_id like'%").append(loginInfo.getDeptId()).append("%')");
					 c.add(Restrictions.sqlRestriction(sql.toString()));
				 }

				 if(post.contains(PostTypeEnum.CIVIL_HEAD.getValue())){
					 //民用负责人
					 dept=departmentDao.queryDepartmentByDivide(DeptDivideEnum.CIVIL_GROUP.getValue(),loginInfo.getDeptId());
					 if(StringUtils.isNotBlank(dept.getDeptId())){
						 deptIdArray.add(dept.getDeptId());
					 }
				 }
				 if(post.contains(PostTypeEnum.MARKETING_CENTER_HEAD.getValue())){
					 //公建负责人
					 dept=departmentDao.queryDepartmentByDivide(DeptDivideEnum.MARKETING_CENTER.getValue(),loginInfo.getDeptId());
					 if(StringUtils.isNotBlank(dept.getDeptId())){
						 deptIdArray.add(dept.getDeptId());
					 }
				 }
				 if(post.contains(PostTypeEnum.MODIFICATION_HEAD.getValue())){
					 //改管负责人
					 dept=departmentDao.queryDepartmentByDivide(DeptDivideEnum.MODIFICATION_GROUP.getValue(),loginInfo.getDeptId());
					 if(StringUtils.isNotBlank(dept.getDeptId())){
						 deptIdArray.add(dept.getDeptId());
					 }
				 }
				 if(post.contains(PostTypeEnum.TRUNK_HEAD.getValue())){
					 //干线负责人
					 dept=departmentDao.queryDepartmentByDivide(DeptDivideEnum.TRUNK_GROUP.getValue(),loginInfo.getDeptId());
					 if(StringUtils.isNotBlank(dept.getDeptId())){
						 deptIdArray.add(dept.getDeptId());
					 }
				 }

				 boolean isProjId=true;
				 DataFilerSetUpDto config = Constants.isConfig(loginInfo.getStaffId() + "_" + loginInfo.getDeptId() + "_" + req.getMenuId());
				 if (config!=null){
					 isProjId=false;
					 if (StringUtils.isNotBlank(config.getSupSql())){
						 c.add(Restrictions.sqlRestriction(config.getSupSql()));
					 }
				 }

			 if(deptIdArray!=null && deptIdArray.size()>0 && isProjId){
				 StringBuffer hql = new StringBuffer("from Project where deptId in ('");
				 for(int i=1;i<deptIdArray.size();i++){
					 hql.append(deptIdArray.get(i)).append("',").append("'");
				 }
				 hql.append(deptIdArray.get(0)).append("')");
				 //hql.append(" and projStatusId='").append(constructionPlanQueryReq.getProjStatus()).append("'");
				 List<Project> projects = super.findByHql(hql.toString());
				 List<String> projIds = new ArrayList();
				 if(projects!=null&&projects.size()>0){
					 for(Project project:projects){
						 projIds.add(project.getProjId());
					 }
					 c.add(Restrictions.in("projId",projIds));
				 }else{
					 return ResultUtil.pageResult( 0, req.getDraw(),new ArrayList());
				 }
			 }
			 //贵安子公司，现场代表一级付款审核
			 List<DataFilerSetUpDto> listBuilder = Constants.getDataFilterMapByKey(loginInfo.getCorpId()+PostTypeEnum.BUILDER.getValue()+"_"+req.getMenuId());
			 if(listBuilder!=null && listBuilder.size()>0 && post.contains(PostTypeEnum.BUILDER.getValue()) && !post.contains(PostTypeEnum.BUSINESSASSISTANT.getValue())&& !post.contains(PostTypeEnum.MINISTER.getValue())){
				 c.add(Restrictions.eq("paAuditerId", loginInfo.getStaffId()));
			 }
		 }

		 if(bp!=null){
			 //监理单位或检测单位
			 if(UnitTypeEnum.CONSTRUCTION_CONTROL_UNIT.getValue().equals(bp.getUnitType())||UnitTypeEnum.DETECTION_UNIT.getValue().equals(bp.getUnitType())){
				 c.add(Restrictions.eq("applyDeptId",bp.getUnitId()));
			 }
		 }
		 //设计院
		 if(DeptDivideEnum.DESIGN_INSTITUTE.getValue().equals(loginInfo.getDeptDivide())){
			 c.add(Restrictions.eq("applyDeptId",loginInfo.getDeptId()));
		 }

		 //是否派工
		 if(StringUtils.isNotBlank(req.getIsDispatch())){
			 c.add(Restrictions.eq("isDispatch",req.getIsDispatch()));
		 }
		 c.addOrder(Order.desc("applyNo"));
		// 数据库根据条件过滤记录数
		int filterCount = this.countByCriteria(c);
		// 根据条件获取查询信息
		List<PaymentApply> list = this.findBySortCriteria(c, req);
		// 返回结果
		return ResultUtil.pageResult(filterCount, req.getDraw(),list);
	}

	@Override
	public String getMaxApplyNo(String date,String projId) {
		Criteria c = super.getCriteria();
		c.setProjection(Projections.max("applyNo"));
		c.add(Restrictions.like("applyNo",date+"%"));
		//工程编号
		if(StringUtils.isNotBlank(projId)){
			c.add(Restrictions.eq("projId", projId));
		}
		String applyNoMax = (String) c.uniqueResult();
		if(StringUtils.isNotBlank(applyNoMax)){
			int applyNo = Integer.parseInt(applyNoMax)+1;
			return ""+applyNo;
		}
		return "";
	}

	/**
	 * 查询预算员
	 * @author fuliwei
	 * @createTime 2017年12月21日
	 * @param
	 * @return
	 */
	@Override
	public Map<String, Object> queryAuditer(DesignerQueryReq designerQueryReq) {
		//查询参数集合
		List<Object> params = new ArrayList<Object>();

		StringBuilder sql = new StringBuilder();
		sql.append("select s.STAFF_NAME surveyer,s.STAFF_ID surveyerId");
		//待处理任务
		if(StringUtils.isNotBlank(designerQueryReq.getProjStatus())){
			sql.append(",count(case when AUDIT_STATE=? and m.MR_AUDIT_LEVEL is null then AUDIT_STATE end) surveyRegister");
			params.add(designerQueryReq.getProjStatus());
		}
		sql.append(" from payment_apply p right join staff s on (p.PA_AUDITER_ID = s.STAFF_ID) left join MANAGE_RECORD m on ( s.STAFF_ID = m.MR_CSR AND m.BUSINESS_ORDER_ID = p.PA_ID) where 1=1");
		LoginInfo loginInfo=SessionUtil.getLoginInfo();
		//todo:超级管理员查询时
		//其他人员可查询自己部门的预算员
		sql.append(" and s.CORP_ID like ?");
		params.add(loginInfo.getCorpId()+"%");
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

		sql.append(" group by s.STAFF_NAME,s.STAFF_ID");

		List<Map<String, Object>> data = this.findListBySql(sql.toString(), params.toArray());
		return ResultUtil.pageResult(data.size(), designerQueryReq.getDraw(), data);
	}



	/**
	 * 生成费用申请编号
	 * @author fuliwei
	 * @createTime 2017年12月21日
	 * @param
	 * @return
	 */
	@Override
	public String getMaxApplyNo(String date) {
		Criteria c = super.getCriteria();
		c.setProjection(Projections.max("applyNo"));
		c.add(Restrictions.like("applyNo",date+"%"));
		String applyNoMax = (String) c.uniqueResult();
		if(StringUtils.isNotBlank(applyNoMax)){
			int applyNo = Integer.parseInt(applyNoMax)+1;
			return ""+applyNo;
		}
		return "";
	}

	@Override
	public Map<String, Object> getpaymentpplyList(PaymentApplyReq paymentApplyReq) {
		Criteria c = super.getCriteria();
		LoginInfo loginInfo = SessionUtil.getLoginInfo();

		//根据Id进行查询
		if(StringUtils.isNotBlank(paymentApplyReq.getProjId())){
			c.add(Restrictions.eq("projId",paymentApplyReq.getProjId()));
		}
		//工程编号
		if(StringUtils.isNotBlank(paymentApplyReq.getProjNo())){
			c.add(Restrictions.like("projNo","%"+paymentApplyReq.getProjNo()+"%"));
		}
		//工程名称
		if(StringUtils.isNotBlank(paymentApplyReq.getProjName())){
			c.add(Restrictions.like("projName","%"+paymentApplyReq.getProjName()+"%"));
		}
		//审核状态
		if(StringUtils.isNotBlank(paymentApplyReq.getAuditState())){
			c.add(Restrictions.eq("auditState",paymentApplyReq.getAuditState() ));
		}
		//分公司ID
		if(StringUtils.isNotBlank(loginInfo.getCorpId())){
			c.add(Restrictions.eq("corpId",loginInfo.getCorpId()));
		}

		c.addOrder(Order.desc("applyDate"));  // 受理日期

		// 数据库根据条件过滤记录数
		int filterCount = this.countByCriteria(c);

		// 根据条件获取查询信息
		List<PaymentApply> paymentApplyList = this.findBySortCriteria(c, paymentApplyReq);

		for(PaymentApply p:paymentApplyList){
			if (p.getApplyAmount()!=null){//将请款金额转大写
				p.setLegalApplyAmount(MoneyConverter.Num2RMB(p.getApplyAmount().doubleValue()));
			}
		}

		// 返回结果
		return ResultUtil.pageResult( filterCount, paymentApplyReq.getDraw(),paymentApplyList);
	}
}
