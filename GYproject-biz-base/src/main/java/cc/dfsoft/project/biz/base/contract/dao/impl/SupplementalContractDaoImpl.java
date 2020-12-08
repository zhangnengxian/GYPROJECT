package cc.dfsoft.project.biz.base.contract.dao.impl;

import cc.dfsoft.project.biz.base.contract.dao.SupplementalContractDao;
import cc.dfsoft.project.biz.base.contract.dto.SupplementalContractQueryReq;
import cc.dfsoft.project.biz.base.contract.entity.SupplementalContract;
import cc.dfsoft.project.biz.base.contract.enums.ModifyStatusEnum;
import cc.dfsoft.project.biz.base.contract.enums.supplementalContractIsAuditEnum;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.project.service.ProjectService;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dao.DepartmentDao;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.biz.sys.dept.entity.Department;
import cc.dfsoft.uexpress.biz.sys.dept.enums.BusinessTypeEnum;
import cc.dfsoft.uexpress.biz.sys.dept.enums.DeptDivideEnum;
import cc.dfsoft.uexpress.biz.sys.dept.enums.DeptTypeEnum;
import cc.dfsoft.uexpress.biz.sys.dept.enums.PostTypeEnum;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;
import cc.dfsoft.uexpress.common.util.MoneyConverter;
import cc.dfsoft.uexpress.common.util.ResultUtil;
import cc.dfsoft.uexpress.common.util.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class SupplementalContractDaoImpl extends NewBaseDAO<SupplementalContract, String> implements SupplementalContractDao{
	
	/**部门Dao*/
	@Resource
	DepartmentDao departmentDao;
	@Resource
	ProjectService projectService;
	
	@Override
	public List<SupplementalContract> findByScNo(String scNo) {
		Criteria c = super.getCriteria();
		 if(StringUtils.isNotBlank(scNo)){
			 c.add(Restrictions.eq("scNo",scNo));
			 return this.findByCriteria(c);
		 }
		return null;
	}
	
	@Override
	public SupplementalContract findByCmId(String cmId,String mcType) {
		Criteria c = super.getCriteria();
		 if(StringUtils.isNotBlank(cmId)){
			 c.add(Restrictions.eq("cmId",cmId));
		 }
		 if(StringUtils.isNotBlank(mcType)){
			 c.add(Restrictions.eq("mcType",mcType));
		 }
		 List<SupplementalContract> list= this.findByCriteria(c);
		 if(list != null  && list.size()>0){
			 return list.get(0);
		 }
		 return null;
	}
	/**
	 * 补充协议条件查询
	 */
	@Override
	public Map<String, Object> querySupplementalContract(SupplementalContractQueryReq supplementalContractQueryReq)
			throws ParseException {
		Criteria c = super.getCriteria();
		//是否推送
		if(StringUtils.isNotBlank(supplementalContractQueryReq.getIsAudit())){
			c.add(Restrictions.eq("isAudit",supplementalContractQueryReq.getIsAudit()));
		}
		 //工程编号 
		 if(StringUtils.isNotBlank(supplementalContractQueryReq.getProjNo())){
			 c.add(Restrictions.eq("projNo",supplementalContractQueryReq.getProjNo()));
		 }
		 //合同编号 
		 if(StringUtils.isNotBlank(supplementalContractQueryReq.getScNo())){
			 c.add(Restrictions.eq("scNo",supplementalContractQueryReq.getScNo()));
		 }
		 //工程名称
		 if(StringUtils.isNotBlank(supplementalContractQueryReq.getProjName())){
			 c.add(Restrictions.like("projName","%"+supplementalContractQueryReq.getProjName()+"%"));
		 }
		 
		 //工程地点
		 if(StringUtils.isNotBlank(supplementalContractQueryReq.getProjAddr())){
			 c.add(Restrictions.like("projAddr","%"+supplementalContractQueryReq.getProjAddr()+"%"));
		 }
		 
		 //工程id
		 if(StringUtils.isNotBlank(supplementalContractQueryReq.getProjId())){
			 c.add(Restrictions.eq("projId",supplementalContractQueryReq.getProjId()));
		 }
		 //甲方名称
		 if(StringUtils.isNotBlank(supplementalContractQueryReq.getCustName())){
			 c.add(Restrictions.like("custName","%"+supplementalContractQueryReq.getCustName()+"%"));
		 }
		 //是否打印
		 if(StringUtils.isNotBlank(supplementalContractQueryReq.getIsPrint())){
			 c.add(Restrictions.eq("isPrint",supplementalContractQueryReq.getIsPrint()));
		 }
		 //修改审核状态
		 if(StringUtils.isNotBlank(supplementalContractQueryReq.getModifyStatus())){
			 if(supplementalContractQueryReq.getModifyStatus().equals(ModifyStatusEnum.NO_MODIFY.getValue())){
				 StringBuffer sql=new StringBuffer("(MODIFY_STATUS ='3' OR MODIFY_STATUS IS NULL)");
				 c.add(Restrictions.sqlRestriction(sql.toString()));
			 }else{
				 c.add(Restrictions.eq("modifyStatus",supplementalContractQueryReq.getModifyStatus()));
			 }
		 }else{
			 StringBuffer sql=new StringBuffer("(MODIFY_STATUS !='0'  OR MODIFY_STATUS IS NULL)");
			 c.add(Restrictions.sqlRestriction(sql.toString()));
		 }
		 //用于字符串日期格式转换
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		 //签定日期开始
		 if(StringUtils.isNotBlank(supplementalContractQueryReq.getSignDateStart())){
			 c.add(Restrictions.ge("signDate", sdf.parse(supplementalContractQueryReq.getSignDateStart())));
		 }
		 //签定日期结束
		 if(StringUtils.isNotBlank(supplementalContractQueryReq.getSignDateEnd())){
			 c.add(Restrictions.le("scSignDate", sdf.parse(supplementalContractQueryReq.getSignDateEnd())));
		 }
		 LoginInfo loginInfo = SessionUtil.getLoginInfo();
		 String post=loginInfo.getPost();
		 String [] postArray=post.split(",");
		 
		 StringBuffer sql = new StringBuffer("proj_id in(select pro.proj_id from project pro where pro.corp_id like '").append(loginInfo.getCorpId()).append("%' ");
		 if(BusinessTypeEnum.BUSINESS_GROUP.getValue().equals(loginInfo.getBusinessType())){	 //业务部门 
			 //只能表组过滤
			 if(DeptDivideEnum.INTELLIGENT_METER.getValue().equals(loginInfo.getDeptDivide())){
				 sql.append("and pro.is_intelligent_meter = '1' ");
			 }else{
				 String deptId = loginInfo.getDeptId().substring(0, loginInfo.getDeptId().length() - DeptTypeEnum.SUBCOMPANY.getInitVal().length());
				 sql.append(" and ").append(projectService.deptIdFilterStr(deptId));
			 }
		 }else if(DeptDivideEnum.CUSTOMER_SERVICE_CENTER.getValue().equals(loginInfo.getDeptDivide())){
			 //客服中心
			 //踏勘员
			 if(post.contains(PostTypeEnum.SURVEYER.getValue())){
				 sql.append("and pro.surveyer_id = '").append(loginInfo.getStaffId()).append("' ");
			 }
			 if(post.contains(PostTypeEnum.RECORDER.getValue())||post.contains(PostTypeEnum.CHIEF_ENGINEER.getValue())){
				 String deptId =loginInfo.getDeptId();
				 sql.append(" and ").append(projectService.deptIdFilterStr(deptId));
			 }else{
				 String deptId = loginInfo.getDeptId().substring(0, loginInfo.getDeptId().length() - DeptTypeEnum.SUBCOMPANY.getInitVal().length());
				 sql.append(" and ").append(projectService.deptIdFilterStr(deptId));
			 }
		 }else{	
			 //如果是市场发展部的
			 if(DeptDivideEnum.CIVIL_GROUP.getValue().equals(loginInfo.getDeptDivide())||DeptDivideEnum.MARKETING_CENTER.getValue().equals(loginInfo.getDeptDivide())||
					 DeptDivideEnum.TRUNK_GROUP .getValue().equals(loginInfo.getDeptDivide())||DeptDivideEnum.MODIFICATION_GROUP.getValue().equals(loginInfo.getDeptDivide())){
				 //工程部门 民用 公建 改管 干线 
				 sql.append(" and ").append(projectService.deptIdFilterStr(loginInfo.getDeptId()));
			 }else{
				 String deptId = loginInfo.getDeptId().substring(0, loginInfo.getDeptId().length() - DeptTypeEnum.SUBCOMPANY.getInitVal().length());

				 sql.append("and ").append(projectService.deptIdFilterStr(deptId));
			 }
		 }
		 sql.append(")");
		 c.add(Restrictions.sqlRestriction(sql.toString()));
		 
		 
		 Department dept=new Department();
		 List<Object> deptIdArray=new ArrayList<>();
		 
		 if(postArray.length>0){	 
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
				
			 if(deptIdArray!=null && deptIdArray.size()>0){
				 StringBuffer hql = new StringBuffer("from Project p where p.deptId in ('");
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
				 }else{
					// 返回结果
					 return ResultUtil.pageResult( 0, supplementalContractQueryReq.getDraw(),new ArrayList()); 
				 }
			 }
			 
		 }
		 
		 // 数据库根据条件过滤记录数
		 int filterCount = this.countByCriteria(c);

		 // 根据条件获取查询信息
		 List<SupplementalContract> supplementalContracts = this.findBySortCriteria(c, supplementalContractQueryReq);
		 
		 if(supplementalContracts!=null&&supplementalContracts.size()>0){
			 for(SupplementalContract sup :supplementalContracts){
				 if(sup.getScAmount()!=null){
					 sup.setLegalScAmount(MoneyConverter.Num2RMB(sup.getScAmount().doubleValue()));
				 }
				
			 } 
		 }
		
		 // 返回结果
		 return ResultUtil.pageResult(filterCount, supplementalContractQueryReq.getDraw(),supplementalContracts);
	}
	
	/**
	 * 通过预算id查询补充协议
	 * @author fuliwei
	 * @createTime 2017-1-25
	 * @param budgetId
	 * @return SupplementalContract
	 */
	@Override
	public SupplementalContract viewSupplementalContract(String budgetId) {
		Criteria c = super.getCriteria();
		 if(StringUtils.isNotBlank(budgetId)){
			 c.add(Restrictions.eq("budgetId",budgetId));
		 }
		 List<SupplementalContract> list= this.findByCriteria(c);
		 if(list != null  && list.size()>0){
			 return list.get(0);
		 }
		 return null;
	}

	@Override
	public List<SupplementalContract> findByConID(String conId) {
		Criteria c = super.getCriteria();
		 if(StringUtils.isNotBlank(conId)){
			 c.add(Restrictions.eq("conId",conId));
			 return this.findByCriteria(c);
		 }
		return null;
	}

	/**
	 *已审核的补充协议
	 */
	@Override
	public List<SupplementalContract> findByProjId(String projId) {
		 Criteria c = super.getCriteria();
		 if(StringUtil.isNotBlank(projId)){
			 c.add(Restrictions.eq("projId", projId));
		 }
		 c.add(Restrictions.eq("isAudit", supplementalContractIsAuditEnum.HAVE_PASS.getValue()));
		 
		 return this.findByCriteria(c);
	}
    /**
     * 查询补充协议列表
     * 已完成补充协议并且在除去修改审核状态
     */
	@Override
	public Map<String, Object> queryAgreementList(SupplementalContractQueryReq supplementalContract)
			throws ParseException {
		// TODO Auto-generated method stub
		Criteria c = super.getCriteria();
		//已审核通过
	    c.add(Restrictions.eq("isAudit", supplementalContractIsAuditEnum.HAVE_PASS.getValue()));
		
		 //根据工程Id查询工程
		if(StringUtils.isNotBlank(supplementalContract.getProjId())){
			 c.add(Restrictions.eq("projId",supplementalContract.getProjId()));
		}
		//根据工程编号查询工程
		if(StringUtils.isNotBlank(supplementalContract.getProjNo())){
			c.add(Restrictions.eq("projNo",supplementalContract.getProjNo()));
		}
		//根据工程名称查询工程
		if(StringUtils.isNotBlank(supplementalContract.getProjName())){
			c.add(Restrictions.like("projName","%"+supplementalContract.getProjName()+"%"));
		}
		//根据工程地点查找相关工程
		if(StringUtils.isNotBlank(supplementalContract.getProjAddr())){
			c.add(Restrictions.like("projAddr", "%"+supplementalContract.getProjAddr()+"%"));
		}
		//根据客户名称查找工程
		if(StringUtils.isNoneBlank(supplementalContract.getCustName())){
			c.add(Restrictions.like("custName", "%"+supplementalContract.getCustName()+"%"));
		}
		//按照修改标记查找工程
		if(StringUtils.isNotBlank(supplementalContract.getModifyStatus())){
			if(supplementalContract.getModifyStatus().equals(ModifyStatusEnum.NO_MODIFY.getValue())){
				 StringBuffer sql=new StringBuffer("(MODIFY_STATUS ='3' OR MODIFY_STATUS IS NULL)");
				 c.add(Restrictions.sqlRestriction(sql.toString()));
			}else{
				c.add(Restrictions.eq("modifyStatus", supplementalContract.getModifyStatus()));
			}
		}/*else{
			 StringBuffer sql=new StringBuffer("IFNULL(MODIFY_STATUS,'')!='"+ModifyStatusEnum.TO_AUDIT.getValue()+"'");
			 c.add(Restrictions.sqlRestriction(sql.toString()));
		}*/
		
		 //签订日期开始
		 if(StringUtils.isNotBlank(supplementalContract.getSignDateStart())){
			 StringBuffer sql = new StringBuffer();
			 sql.append("proj_id in(select cp.proj_id from SUPPLEMENTAL_CONTRACT cp where cp.SIGN_DATE >= ").append(mysqlSqlConveter.dataOperate(supplementalContract.getSignDateStart()+" 00:00:00")).append(")");
			 c.add(Restrictions.sqlRestriction(sql.toString()));
		 }

		 //签订日期结束
		 if(StringUtils.isNotBlank(supplementalContract.getSignDateEnd())){
			 StringBuffer sql = new StringBuffer();
			 sql.append("proj_id in (select cp.proj_id from SUPPLEMENTAL_CONTRACT cp where cp.SIGN_DATE <=  ").append(mysqlSqlConveter.dataOperate(supplementalContract.getSignDateEnd()+" 00:00:00")).append(")");
			 c.add(Restrictions.sqlRestriction(sql.toString()));
		 }

		//根据分公司查询
		if(StringUtils.isNotBlank(supplementalContract.getCorpId())){
			StringBuffer sql = new StringBuffer();
			sql.append("proj_id in (select a.proj_id from project a where a.CORP_ID like '"+supplementalContract.getCorpId()+"%')");
			c.add(Restrictions.sqlRestriction(sql.toString()));
		}
		 c.addOrder(Order.desc("signDate"));  // 按照签订日期排序
		// 数据库根据条件过滤记录数

		int filterCount = this.countByCriteria(c);
		// 根据条件获取查询信息
		List<SupplementalContract> supplementalContractList = this.findBySortCriteria(c,supplementalContract);
		// 返回结果
	    return ResultUtil.pageResult(filterCount, supplementalContract.getDraw(),supplementalContractList);
	}
    /**
     * 查询修改过的补充协议进行审核
     */
	@Override
	public Map<String, Object> querySupplementalModify(SupplementalContractQueryReq supplementalContractQueryReq) throws Exception {
		Criteria c = super.getCriteria();
		   //是否推送
		if(StringUtils.isNotBlank(supplementalContractQueryReq.getIsAudit())){
			c.add(Restrictions.eq("isAudit",supplementalContractQueryReq.getIsAudit())); 
		}
		 //工程编号 
		 if(StringUtils.isNotBlank(supplementalContractQueryReq.getProjNo())){
			 c.add(Restrictions.eq("projNo",supplementalContractQueryReq.getProjNo()));
		 }
		 //合同编号 
		 if(StringUtils.isNotBlank(supplementalContractQueryReq.getScNo())){
			 c.add(Restrictions.eq("scNo",supplementalContractQueryReq.getScNo()));
		 }
		 //工程名称
		 if(StringUtils.isNotBlank(supplementalContractQueryReq.getProjName())){
			 c.add(Restrictions.like("projName","%"+supplementalContractQueryReq.getProjName()+"%"));
		 }
		 
		 //工程地点
		 if(StringUtils.isNotBlank(supplementalContractQueryReq.getProjAddr())){
			 c.add(Restrictions.like("projAddr","%"+supplementalContractQueryReq.getProjAddr()+"%"));
		 }
		 
		 //工程id
		 if(StringUtils.isNotBlank(supplementalContractQueryReq.getProjId())){
			 c.add(Restrictions.eq("projId",supplementalContractQueryReq.getProjId()));
		 }
		 //甲方名称
		 if(StringUtils.isNotBlank(supplementalContractQueryReq.getCustName())){
			 c.add(Restrictions.like("custName","%"+supplementalContractQueryReq.getCustName()+"%"));
		 }
		 //是否打印
		 if(StringUtils.isNotBlank(supplementalContractQueryReq.getIsPrint())){
			 c.add(Restrictions.eq("isPrint",supplementalContractQueryReq.getIsPrint()));
		 }
		 //修改审核状态
		 if(StringUtils.isNotBlank(supplementalContractQueryReq.getModifyStatus())){
			 c.add(Restrictions.eq("modifyStatus",supplementalContractQueryReq.getModifyStatus()));
		 }
		 //用于字符串日期格式转换-+ 
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		 //签定日期开始
		 if(StringUtils.isNotBlank(supplementalContractQueryReq.getSignDateStart())){
			 c.add(Restrictions.ge("signDate", sdf.parse(supplementalContractQueryReq.getSignDateStart())));
		 }
		 //签定日期结束
		 if(StringUtils.isNotBlank(supplementalContractQueryReq.getSignDateEnd())){
			 c.add(Restrictions.le("scSignDate", sdf.parse(supplementalContractQueryReq.getSignDateEnd())));
		 }
		 LoginInfo loginInfo = SessionUtil.getLoginInfo();
		 String post=loginInfo.getPost();
		 String [] postArray=post.split(",");
		 
		 StringBuffer sql = new StringBuffer("proj_id in(select pro.proj_id from project pro where pro.corp_id like '").append(loginInfo.getCorpId()).append("%' ");
		 if(BusinessTypeEnum.BUSINESS_GROUP.getValue().equals(loginInfo.getBusinessType())){	 //业务部门 
			 //只能表组过滤
			 if(DeptDivideEnum.INTELLIGENT_METER.getValue().equals(loginInfo.getDeptDivide())){
				 sql.append("and pro.is_intelligent_meter = '1' ");
			 }else{
				 String deptId = loginInfo.getDeptId().substring(0, loginInfo.getDeptId().length() - DeptTypeEnum.SUBCOMPANY.getInitVal().length());
				 sql.append(" and ").append(projectService.deptIdFilterStr(deptId));
			 }
		 }else if(DeptDivideEnum.CUSTOMER_SERVICE_CENTER.getValue().equals(loginInfo.getDeptDivide())){
			 //客服中心
			 //踏勘员
			 if(post.contains(PostTypeEnum.SURVEYER.getValue())){
				 sql.append("and pro.surveyer_id = '").append(loginInfo.getStaffId()).append("' ");
			 }
			 if(post.contains(PostTypeEnum.RECORDER.getValue())||post.contains(PostTypeEnum.CHIEF_ENGINEER.getValue())){
				 String deptId =loginInfo.getDeptId();
				 sql.append(" and ").append(projectService.deptIdFilterStr(deptId));
			 }else{
				 String deptId = loginInfo.getDeptId().substring(0, loginInfo.getDeptId().length() - DeptTypeEnum.SUBCOMPANY.getInitVal().length());
				 sql.append(" and ").append(projectService.deptIdFilterStr(deptId));
			 }
		 }else{	
			 //如果是市场发展部的
			 if(DeptDivideEnum.CIVIL_GROUP.getValue().equals(loginInfo.getDeptDivide())||DeptDivideEnum.MARKETING_CENTER.getValue().equals(loginInfo.getDeptDivide())||
					 DeptDivideEnum.TRUNK_GROUP .getValue().equals(loginInfo.getDeptDivide())||DeptDivideEnum.MODIFICATION_GROUP.getValue().equals(loginInfo.getDeptDivide())){
				 //工程部门 民用 公建 改管 干线 
				 sql.append(" and ").append(projectService.deptIdFilterStr(loginInfo.getDeptId()));
			 }else{
				 String deptId = loginInfo.getDeptId().substring(0, loginInfo.getDeptId().length() - DeptTypeEnum.SUBCOMPANY.getInitVal().length());
				 sql.append(" and ").append(projectService.deptIdFilterStr(deptId));
			 }
		 }
		 sql.append(")");
		 c.add(Restrictions.sqlRestriction(sql.toString()));
		 
		 
		 Department dept=new Department();
		 List<Object> deptIdArray=new ArrayList<>();
		 
		 if(postArray.length>0){
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
				
			 if(deptIdArray!=null && deptIdArray.size()>0){
				 StringBuffer hql = new StringBuffer("from Project p where p.deptId in ('");
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
				 }else{
					// 返回结果
					 return ResultUtil.pageResult( 0, supplementalContractQueryReq.getDraw(),new ArrayList()); 
				 }
			 }
			 
		 }
		 
		 // 数据库根据条件过滤记录数
		 int filterCount = this.countByCriteria(c);

		 // 根据条件获取查询信息
		 List<SupplementalContract> supplementalContracts = this.findBySortCriteria(c, supplementalContractQueryReq);
		 
		 if(supplementalContracts!=null&&supplementalContracts.size()>0){
			 for(SupplementalContract sup :supplementalContracts){
				 if(sup.getScAmount()!=null){
					 sup.setLegalScAmount(MoneyConverter.Num2RMB(sup.getScAmount().doubleValue()));
				 }
			 } 
		 }
		
		 // 返回结果
		 return ResultUtil.pageResult(filterCount, supplementalContractQueryReq.getDraw(),supplementalContracts);
		 }

}
