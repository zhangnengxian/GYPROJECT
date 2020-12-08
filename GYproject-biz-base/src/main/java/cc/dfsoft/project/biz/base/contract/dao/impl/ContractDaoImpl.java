package cc.dfsoft.project.biz.base.contract.dao.impl;

import cc.dfsoft.project.biz.base.contract.dao.ContractDao;
import cc.dfsoft.project.biz.base.contract.dto.ContractQueryReq;
import cc.dfsoft.project.biz.base.contract.entity.Contract;
import cc.dfsoft.project.biz.base.contract.enums.MaterialIsPassRegisterEnum;
import cc.dfsoft.project.biz.base.contract.enums.ModifyStatusEnum;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.project.enums.ProjLtypeEnum;
import cc.dfsoft.project.biz.base.project.service.ProjectService;
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
import cc.dfsoft.uexpress.common.util.CheckUtil;
import cc.dfsoft.uexpress.common.util.MoneyConverter;
import cc.dfsoft.uexpress.common.util.ResultUtil;
import cc.dfsoft.uexpress.common.util.StringUtil;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class ContractDaoImpl extends NewBaseDAO<Contract, String> implements ContractDao{
	
	/**部门Dao*/
	@Resource
	DepartmentDao departmentDao;
	@Resource
	ProjectService projectService;
	
	@Override
	public List<Contract> findByConNo(String conNo) {
		StringBuffer hql = new StringBuffer();
		if(StringUtils.isNotBlank(conNo)){
			hql.append("from Contract where conNo = '").append(conNo).append("'");
			return super.findByHql(hql.toString());
		}
		return null;
	}
	
	/**
	 * 根据工程ID查询合同
	 * zhangmeng
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Contract viewContractByprojId(String id) {
		
		Criteria c = super.getCriteria();
		if(StringUtils.isNotBlank(id)){
			c.add(Restrictions.eq("projId", id));
			List<Contract> list = this.findByCriteria(c);
			if(list != null && list.size()>0){
				return list.get(0);
			}
		}
		return null;
	}

	@Override
	public Map<String, Object> queryContract(ContractQueryReq contractQueryReq) throws ParseException {
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		 Criteria c = super.getCriteria();
		
		 //工程编号 
		 if(StringUtils.isNotBlank(contractQueryReq.getProjNo())){
			 c.add(Restrictions.eq("projNo",contractQueryReq.getProjNo()));
		 }
		 
		 //合同编号
		 if(StringUtils.isNotBlank(contractQueryReq.getConNo())){
			 c.add(Restrictions.eq("conNo",contractQueryReq.getConNo()));
		 }
		 
		 //工程状态
		 if(StringUtils.isNotBlank(contractQueryReq.getProjStatusId())){
			 StringBuffer hql = new StringBuffer("from Project where projStatusId = '").append(contractQueryReq.getProjStatusId()).append("'");
			 List<Project> projects = super.findByHql(hql.toString());
			 List<String> projIds = new ArrayList();
			 if(projects.size()>0){
				 for(Project project:projects){
					 projIds.add(project.getProjId());
				 }
				 c.add(Restrictions.in("projId",projIds));
			 }else{
				// 返回结果
				 return ResultUtil.pageResult( 0, contractQueryReq.getDraw(),new ArrayList());
			 }
		 }
		 //工程名称
		 if(StringUtils.isNotBlank(contractQueryReq.getProjName())){
			 c.add(Restrictions.like("projName","%"+contractQueryReq.getProjName()+"%"));
		 }
		 //客户名称
		 if(StringUtils.isNotBlank(contractQueryReq.getCustName())){
			 c.add(Restrictions.like("custName","%"+contractQueryReq.getCustName()+"%"));
		 }
		 //工程地点
		 if(StringUtils.isNotBlank(contractQueryReq.getProjAddr())){
			 c.add(Restrictions.like("projAddr","%"+contractQueryReq.getProjAddr()+"%"));
		 }
		 //付款方式
		 if(StringUtils.isNotBlank(contractQueryReq.getPayType())){
			 c.add(Restrictions.eq("payType",contractQueryReq.getPayType()));
		 }
		 //资金来源
		 if(StringUtils.isNotBlank(contractQueryReq.getFundSource())){
			 c.add(Restrictions.eq("fundSource",contractQueryReq.getFundSource()));
		 }
		 //工程id
		 if(StringUtils.isNotBlank(contractQueryReq.getProjId())){
			 c.add(Restrictions.eq("projId",contractQueryReq.getProjId()));
		 }
		 //修改审核状态
		 if(StringUtils.isNotBlank(contractQueryReq.getModifyStatus())){
			 c.add(Restrictions.eq("modifyStatus",contractQueryReq.getModifyStatus()));
		 }else{
			 StringBuffer sql=new StringBuffer("(MODIFY_STATUS !='0' OR MODIFY_STATUS IS NULL)");
			 c.add(Restrictions.sqlRestriction(sql.toString()));
		 }
		 //用于字符串日期格式转换
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		 //签定日期开始
		 if(StringUtils.isNotBlank(contractQueryReq.getSignDateStart())){
			 c.add(Restrictions.ge("signDate", sdf.parse(contractQueryReq.getSignDateStart())));
		 }
		 //签定日期结束
		 if(StringUtils.isNotBlank(contractQueryReq.getSignDateEnd())){
			 c.add(Restrictions.le("signDate", sdf.parse(contractQueryReq.getSignDateEnd())));
		 }
		 
		 
		 //受理单号
		 if(StringUtils.isNotBlank(contractQueryReq.getPaNo())){
			 StringBuffer sql=new StringBuffer("proj_id in(select pa.proj_id from project_application pa where pa.pa_no like '%").append(contractQueryReq.getPaNo()).append("%')");
			 c.add(Restrictions.sqlRestriction(sql.toString()));
		 }
		 //分公司ID
		 c.add(Restrictions.like("corpId", loginInfo.getCorpId(),MatchMode.START));
		//分公司数据过滤 如
		 List<DataFilerSetUpDto> list = Constants.getDataFilterMapByKey(loginInfo.getDeptId()+"_"+contractQueryReq.getMenuId());
		 boolean flag = false;	
		 if(list!=null && list.size()>0){
			 StringBuffer hql = new StringBuffer();
			 hql.append("proj_id in (select proj_id from project where 1=1 ");
			 
			 if(StringUtils.isNotBlank(list.get(0).getSupSql())){
				 hql.append(list.get(0).getSupSql());
			 }
			 hql.append(")");
			 c.add(Restrictions.sqlRestriction(hql.toString()));
			 flag = true;
		 }
		//通过公司职务判断
		 List<DataFilerSetUpDto> postList = Constants.getDataFilterMapByKey(loginInfo.getCorpId()+"-"+loginInfo.getPost()+"_"+contractQueryReq.getMenuId());
		 if(postList!=null && postList.size()>0){
			 StringBuffer hql = new StringBuffer();
			 hql.append("proj_id in (select proj_id from project where 1=1 ");
			 
			 if(StringUtils.isNotBlank(postList.get(0).getSupSql())){
				 hql.append(postList.get(0).getSupSql());
			 }
			 hql.append(")");
			 c.add(Restrictions.sqlRestriction(hql.toString()));
			 flag = true; 
		 }
		 if(!flag){
		 	 if(BusinessTypeEnum.BUSINESS_GROUP.getValue().equals(loginInfo.getBusinessType())){//业务部门
				 String deptId = loginInfo.getDeptId().substring(0, loginInfo.getDeptId().length() - DeptTypeEnum.SUBCOMPANY.getInitVal().length());
				 c.add(Restrictions.sqlRestriction(projectService.addDeptIdLikeQuery(deptId))); //部门条件查询
				 
			 }else if(DeptDivideEnum.CUSTOMER_SERVICE_CENTER.getValue().equals(loginInfo.getDeptDivide())){
				 if(StringUtil.isNotBlank(loginInfo.getPost()) && (loginInfo.getPost().contains(PostTypeEnum.RECORDER.getValue())|| loginInfo.getPost().contains(PostTypeEnum.CHIEF_ENGINEER.getValue()))){

					 c.add(Restrictions.sqlRestriction(projectService.addDeptIdLikeQuery(loginInfo.getDeptId())));//部门条件查询

				 }else{//客服中心
					 String deptId = loginInfo.getDeptId().substring(0, loginInfo.getDeptId().length() - DeptTypeEnum.SUBCOMPANY.getInitVal().length());
					 c.add(Restrictions.sqlRestriction(projectService.addDeptIdLikeQuery(deptId))); //部门条件查询
				 }
			 }else{	  //工程部门
				 c.add(Restrictions.sqlRestriction(projectService.addDeptIdLikeQuery(loginInfo.getDeptId())));//部门条件查询
			 }
		 }
		 Department dept=new Department();
		 
		 String post=loginInfo.getPost();
		 String [] postArray=post.split(",");
		 
		 List<Object> deptIdArray=new ArrayList<>();
		 List<Object> projecTypeArray=new ArrayList<>();
		 if(postArray.length>0){
			
			 if(post.contains(PostTypeEnum.CIVIL_GROUP_LEADER.getValue())||post.contains(PostTypeEnum.MARKETING_CENTER_LEADER.getValue())||
					 post.contains(PostTypeEnum.MODIFICATION_GROUP_LEADER.getValue())||post.contains(PostTypeEnum.TRUNK_GROUP_LEADER.getValue())){

				 c.add(Restrictions.sqlRestriction(projectService.addDeptIdLikeQuery(loginInfo.getDeptId()))); //部门条件查询
			 }
				 
			 if(post.contains(PostTypeEnum.CIVIL_HEAD.getValue())){
				//查库 是否存在，否则走集团按deptid查询 主要判断分管副总
				 List<DataFilerSetUpDto> civilList = Constants.getDataFilterMapByKey(loginInfo.getCorpId()+PostTypeEnum.CIVIL_HEAD.getValue()+"_"+ProjLtypeEnum.CIVILIAN.getValue());
				 if(civilList!=null && civilList.size()>0){
					 projecTypeArray.add(ProjLtypeEnum.CIVILIAN.getValue());
				 }else{
					 //民用负责人
					 dept=departmentDao.queryDepartmentByDivide(DeptDivideEnum.CIVIL_GROUP.getValue(),loginInfo.getDeptId());
					 if(StringUtils.isNotBlank(dept.getDeptId())){
						 deptIdArray.add(dept.getDeptId());
					 }
				 }
			 }
			 if(post.contains(PostTypeEnum.MARKETING_CENTER_HEAD.getValue())){
				 //公建负责人
				//查库 是否存在，否则走集团按deptid查询
				 List<DataFilerSetUpDto> publiclList = Constants.getDataFilterMapByKey(loginInfo.getCorpId()+PostTypeEnum.MARKETING_CENTER_HEAD.getValue()+"_"+ProjLtypeEnum.PUBLIC.getValue());
				 if(publiclList!=null && publiclList.size()>0){
					 projecTypeArray.add(ProjLtypeEnum.PUBLIC.getValue());
				 }else{
					 dept=departmentDao.queryDepartmentByDivide(DeptDivideEnum.MARKETING_CENTER.getValue(),loginInfo.getDeptId());
					 if(StringUtils.isNotBlank(dept.getDeptId())){
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
					 if(StringUtils.isNotBlank(dept.getDeptId())){
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
					 if(StringUtils.isNotBlank(dept.getDeptId())){
						 deptIdArray.add(dept.getDeptId());
					 }
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
					 return ResultUtil.pageResult( 0, contractQueryReq.getDraw(),new ArrayList()); 
				 }
			 }
			 
		 }
		 
		 // 数据库根据条件过滤记录数
		 int filterCount = this.countByCriteria(c);

		 // 根据条件获取查询信息
		 List<Contract> projectList = this.findBySortCriteria(c, contractQueryReq);
		
		 // 返回结果
		 return ResultUtil.pageResult(filterCount, contractQueryReq.getDraw(),projectList);
	}


	@Override
	public Map<String, Object> queryContractPrint(ContractQueryReq contractQueryReq) throws ParseException {
		 Criteria c = super.getCriteria();
		 LoginInfo loginInfo = SessionUtil.getLoginInfo(); //取得当前登录人信息
		 //工程编号 
		 if(StringUtils.isNotBlank(contractQueryReq.getProjNo())){
			 c.add(Restrictions.eq("projNo",contractQueryReq.getProjNo()));
		 }else{
			//工程状态
			 if(contractQueryReq.getProjStuList()!=null&&(contractQueryReq.getProjStuList()).size()>0){
				 List<String> projStus=contractQueryReq.getProjStuList();
				 StringBuffer hql = new StringBuffer("from Project where projStatusId in ('");
					 for(int i=1;i<projStus.size();i++){
						 hql.append(projStus.get(i)).append("',").append("'");
					 }
				 hql.append(projStus.get(0)).append("')");
				 List<Project> projects = super.findByHql(hql.toString());
				 List<String> projIds = new ArrayList();
				 if(projects!=null&&projects.size()>0){
					 for(Project project:projects){
						 projIds.add(project.getProjId());
					 }
					 c.add(Restrictions.in("projId",projIds));
				 }else{
					// 返回结果
					 return ResultUtil.pageResult(0, contractQueryReq.getDraw(),new ArrayList());
				 }
			 }
		 }
		 
		 //合同编号
		 if(StringUtils.isNotBlank(contractQueryReq.getConNo())){
			 c.add(Restrictions.eq("conNo",contractQueryReq.getConNo()));
		 }
		 
		 
		 //工程名称
		 if(StringUtils.isNotBlank(contractQueryReq.getProjName())){
			 c.add(Restrictions.like("projName","%"+contractQueryReq.getProjName()+"%"));
		 }
		 //客户名称
		 if(StringUtils.isNotBlank(contractQueryReq.getCustName())){
			 c.add(Restrictions.like("custName","%"+contractQueryReq.getCustName()+"%"));
		 }
		 //工程地点
		 if(StringUtils.isNotBlank(contractQueryReq.getProjAddr())){
			 c.add(Restrictions.like("projAddr","%"+contractQueryReq.getProjAddr()+"%"));
		 }
		 //付款方式
		 if(StringUtils.isNotBlank(contractQueryReq.getPayType())){
			 c.add(Restrictions.eq("payType",contractQueryReq.getPayType()));
		 }
		 //资金来源
		 if(StringUtils.isNotBlank(contractQueryReq.getFundSource())){
			 c.add(Restrictions.eq("fundSource",contractQueryReq.getFundSource()));
		 }
		 //工程id
		 if(StringUtils.isNotBlank(contractQueryReq.getProjId())){
			 c.add(Restrictions.eq("projId",contractQueryReq.getProjId()));
		 }
		 //用于字符串日期格式转换
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		 //签定日期开始
		 if(StringUtils.isNotBlank(contractQueryReq.getSignDateStart())){
			 c.add(Restrictions.ge("signDate", sdf.parse(contractQueryReq.getSignDateStart())));
		 }
		 //签定日期结束
		 if(StringUtils.isNotBlank(contractQueryReq.getSignDateEnd())){
			 c.add(Restrictions.le("signDate", sdf.parse(contractQueryReq.getSignDateEnd())));
		 }
		 
		//受理单号
		 if(StringUtils.isNotBlank(contractQueryReq.getPaNo())){
			 StringBuffer sql=new StringBuffer("proj_id in(select pa.proj_id from project_application pa where pa.pa_no like '%").append(contractQueryReq.getPaNo()).append("%')");
			 c.add(Restrictions.sqlRestriction(sql.toString()));
		 }
		 //配置公司部门下人员能在合同审核中的也能看到合同打印-贵安
		 boolean conFlag = true;
		 //贵安公司行政部门，只要存在合同信息都能查看合同打印
		List<DataFilerSetUpDto> filters  = Constants.getDataFilterMapByKey(loginInfo.getCorpId()+"_"+loginInfo.getDeptId()+"_"+contractQueryReq.getMenuId());
		if(filters!=null && filters.size()>0){
			conFlag = false;
		}
		 //是否打印
		 if(StringUtils.isNotBlank(contractQueryReq.getIsPrint())){
			 c.add(Restrictions.eq("isPrint", contractQueryReq.getIsPrint()));
		 }
		 //是否通过审核
		 if(StringUtils.isNotBlank(contractQueryReq.getIsPass()) && conFlag){
			 c.add(Restrictions.eq("isPass", contractQueryReq.getIsPass()));
		 }
		 //修改审核状态
		 if(conFlag){
			 if(StringUtils.isNotBlank(contractQueryReq.getModifyStatus())){
				 if(contractQueryReq.getModifyStatus().equals(ModifyStatusEnum.NO_MODIFY.getValue())){
					 StringBuffer sql=new StringBuffer("(MODIFY_STATUS ='3' OR MODIFY_STATUS IS NULL)");
					 c.add(Restrictions.sqlRestriction(sql.toString()));
				 }else{
					 c.add(Restrictions.eq("modifyStatus",contractQueryReq.getModifyStatus()));
				 }
			 }else{
				 StringBuffer sql=new StringBuffer("(MODIFY_STATUS !='0'  OR MODIFY_STATUS IS NULL)");
				 c.add(Restrictions.sqlRestriction(sql.toString()));
			 }
		 }
		 Boolean falg=true;
		 List<DataFilerSetUpDto> list = Constants.getDataFilterMapByKey(loginInfo.getDeptId()+"_"+contractQueryReq.getMenuId());
		 if(list!=null && list.size()>0){
			 StringBuffer hql = new StringBuffer();
			 hql.append("proj_id in (select proj_id from project where 1=1 ");
			 
			 if(StringUtils.isNotBlank(list.get(0).getSupSql())){
				 hql.append(list.get(0).getSupSql());
			 }
			 hql.append(")");
			 c.add(Restrictions.sqlRestriction(hql.toString()));
			 falg=false;
		 }
		 
		 
	 	 if(BusinessTypeEnum.BUSINESS_GROUP.getValue().equals(loginInfo.getBusinessType())){
			//业务部门 
			 String deptId = loginInfo.getDeptId().substring(0, loginInfo.getDeptId().length() - DeptTypeEnum.SUBCOMPANY.getInitVal().length());
			 c.add(Restrictions.sqlRestriction(projectService.addDeptIdLikeQuery(deptId))); //部门查询
			 
		 }else if(DeptDivideEnum.CUSTOMER_SERVICE_CENTER.getValue().equals(loginInfo.getDeptDivide())){
			 if(StringUtil.isNotBlank(loginInfo.getPost()) && (loginInfo.getPost().contains(PostTypeEnum.RECORDER.getValue())|| loginInfo.getPost().contains(PostTypeEnum.CHIEF_ENGINEER.getValue()))){
				 String deptId =loginInfo.getDeptId();
				 c.add(Restrictions.sqlRestriction(projectService.addDeptIdLikeQuery(deptId))); //部门查询
			 }else{
				//客服中心
				 String deptId = loginInfo.getDeptId().substring(0, loginInfo.getDeptId().length() - DeptTypeEnum.SUBCOMPANY.getInitVal().length());
				 c.add(Restrictions.sqlRestriction(projectService.addDeptIdLikeQuery(deptId))); //部门查询
			 }
		 }else{	
			 if(falg){
				 //工程部门
				 c.add(Restrictions.sqlRestriction(projectService.addDeptIdLikeQuery(loginInfo.getDeptId()))); //部门查询
			 }
		 }
	 	 
	 	 
	 	 
	 	 //根据踏勘员职务过滤
	 	 String post=loginInfo.getPost();
		 String [] postArray=post.split(",");		
			/**
			 * 如果是民用、干线、营销、改管、预结算等组长查看本组所属工程
			 * 20180413
			 * 王会军
			 */
			if(post.contains(PostTypeEnum.CIVIL_GROUP_LEADER.getValue()) || 
					post.contains(PostTypeEnum.MARKETING_CENTER_LEADER.getValue()) ||
					post.contains(PostTypeEnum.MARKETING_CENTER_LEADER_DEPUTY.getValue()) ||
					post.contains(PostTypeEnum.MODIFICATION_GROUP_LEADER.getValue())   ||
					post.contains(PostTypeEnum.TRUNK_GROUP_LEADER.getValue()) ||
					post.contains(PostTypeEnum.BUDGET_GROUP_LEADER.getValue())
					){
					c.add(Restrictions.sqlRestriction(projectService.addDeptIdLikeQuery(loginInfo.getDeptId()))); //部门查询
			   }else if(falg){

					 //如果是现场代表
					if(post.contains(PostTypeEnum.BUILDER.getValue()) && post.contains(PostTypeEnum.SURVEYER.getValue())){
						 StringBuffer sql=new StringBuffer("proj_id in(select cp.proj_id from CONSTRUCTION_PLAN cp where cp.BUILDER_ID='").append(loginInfo.getStaffId()).append("')");
						 c.add(Restrictions.sqlRestriction(sql.toString()));
						 sql=new StringBuffer("(1=1 or proj_id in (select p.proj_id from project p where p.SURVEYER_ID='").append(loginInfo.getStaffId()).append("'");
						 sql.append("))");
						c.add(Restrictions.sqlRestriction(sql.toString()));
					}else if(post.contains(PostTypeEnum.SURVEYER.getValue())){ //如果是踏勘员
						 StringBuffer sql=new StringBuffer("proj_id in(select p.proj_id from project p where p.SURVEYER_ID='").append(loginInfo.getStaffId()).append("')");
						 c.add(Restrictions.sqlRestriction(sql.toString()));
					}else if((post.contains(PostTypeEnum.BUILDER.getValue()))){//现场
						StringBuffer sql=new StringBuffer("proj_id in(select cp.proj_id from CONSTRUCTION_PLAN cp where cp.BUILDER_ID='").append(loginInfo.getStaffId()).append("')");
						 c.add(Restrictions.sqlRestriction(sql.toString()));
					}
			   }
		 // 数据库根据条件过滤记录数
		 int filterCount = this.countByCriteria(c);

		 // 根据条件获取查询信息
		 List<Contract> contractList = this.findBySortCriteria(c, contractQueryReq);
		 if(contractList!=null&&contractList.size()>0){
			 for(Contract con :contractList){
				 if(con.getContractAmount()!=null){
					 con.setLegalAmount(MoneyConverter.Num2RMB(con.getContractAmount().doubleValue()));
				 }
				 if(con.getFirstPayment()!=null){
					 con.setLegalFirstPayment(MoneyConverter.Num2RMB(con.getFirstPayment().doubleValue()));
				 }
				 if(con.getSecondPayment()!=null){
					 con.setLegalSecondPaymentAmount(MoneyConverter.Num2RMB(con.getSecondPayment().doubleValue()));
				 }
				 if(con.getThirdPayment()!=null){
					 con.setLegalThirdPaymentAmount(MoneyConverter.Num2RMB(con.getThirdPayment().doubleValue()));
				 }
				 if(con.getAgentUnitAddress()!=null && CheckUtil.checkFloat(con.getAgentUnitAddress())){
					 con.setLegalFourPaymentAmount(MoneyConverter.Num2RMB( new BigDecimal(con.getAgentUnitAddress()).doubleValue()));
				 }
				 if(con.getIncrementAmount()!=null){
					 con.setLegalIncrementAmount(MoneyConverter.Num2RMB(con.getIncrementAmount().doubleValue()));
				 }
				 if(con.getHoseAmount()!=null){
					 con.setLegalHoseAmount(MoneyConverter.Num2RMB(con.getHoseAmount().doubleValue()));
				 }
				 if(con.getBudgetCost()!=null){
					 con.setLegalBudgetCost(MoneyConverter.Num2RMB(con.getBudgetCost().doubleValue()));
				 }
				 if(con.getContractAmount()!=null){
					 con.setLegalContractAmount(MoneyConverter.Num2RMB(con.getContractAmount().doubleValue()));
				 }
				 if(con.getAgentUnitAddress()!=null){
					boolean flag = StringUtils.isNumeric(con.getAgentUnitAddress());   //判断是否是数字
					 if(flag){  //若是数字，则转变为大写金额
						 con.setAgentUnitAddress(MoneyConverter.Num2RMB(new BigDecimal(con.getAgentUnitAddress()).doubleValue()));
					 }
				 }  
			 } 
		 }
		 
		
		 // 返回结果
		 return ResultUtil.pageResult( filterCount, contractQueryReq.getDraw(),contractList);
	}

	@Override
	public Map<String, Object> queryPassContract(ContractQueryReq contractQueryReq) throws ParseException {
		Criteria c=super.getCriteria();
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		if(StringUtil.isNoneBlank(contractQueryReq.getIsPass())){
			 c.add(Restrictions.eq("isPass", contractQueryReq.getIsPass()));
		}
		//工程名称
		 if(StringUtils.isNotBlank(contractQueryReq.getProjName())){
			 c.add(Restrictions.like("projName", "%"+contractQueryReq.getProjName()+"%"));
			 //instr写法用于代替like写法，提高查询效率
			 //c.add(Restrictions.sqlRestriction("instr('projName','"+projectQueryReq.getProjName()+"',1,1)!=0"));
		 }
		//工程编号
		 if(StringUtils.isNotBlank(contractQueryReq.getProjNo())){
			 c.add(Restrictions.like("projNo", "%"+contractQueryReq.getProjNo()+"%"));
			 //instr写法用于代替like写法，提高查询效率
			 //c.add(Restrictions.sqlRestriction("instr('projName','"+projectQueryReq.getProjName()+"',1,1)!=0"));
		 }
		//合同编号
		 if(StringUtils.isNotBlank(contractQueryReq.getConNo())){
			 c.add(Restrictions.eq("conNo", contractQueryReq.getConNo()));
		 }
		 //物资是否已经登记
		 if(StringUtil.isNoneBlank(contractQueryReq.getMaterialIsRegister())){
			 c.add(Restrictions.eq("materialIsRegister", contractQueryReq.getMaterialIsRegister()));
		 }else{
			 c.add(Restrictions.eq("materialIsRegister", MaterialIsPassRegisterEnum.HAVE_NOT_REGISTER.getValue()));
		 }
		 //根据公司ID查询工程
		 if(StringUtil.isNotBlank(loginInfo.getCorpId())){
			 StringBuffer sql = new StringBuffer("proj_id in(select p.proj_id from project p where p.corp_id='").append(loginInfo.getCorpId()).append("')");
			 c.add(Restrictions.sqlRestriction(sql.toString()));
		 }
		int filterCount=this.countByCriteria(c);
		List<Contract> list=this.findBySortCriteria(c,contractQueryReq);
		for(Contract l:list){
			if(l.getMaterialIsRegister().equals(MaterialIsPassRegisterEnum.ALREADY_REGISTER.getValue())){
				l.setMaterialDes("已登记");
			}else{
				l.setMaterialDes("未登记");
			}
		}
		return ResultUtil.pageResult(filterCount,contractQueryReq.getDraw(),list);
	}

}
