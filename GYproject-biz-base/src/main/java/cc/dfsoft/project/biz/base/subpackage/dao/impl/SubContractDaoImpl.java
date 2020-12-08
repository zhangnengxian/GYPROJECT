package cc.dfsoft.project.biz.base.subpackage.dao.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cc.dfsoft.project.biz.base.baseinfo.dao.BusinessPartnersDao;
import cc.dfsoft.project.biz.base.baseinfo.entity.BusinessPartners;
import cc.dfsoft.project.biz.base.contract.enums.ModifyStatusEnum;
import cc.dfsoft.project.biz.base.design.enums.UnitTypeEnum;
import cc.dfsoft.project.biz.base.project.dao.ProjectDao;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.subpackage.dao.SubContractDao;
import cc.dfsoft.project.biz.base.subpackage.dto.SubContractQueryReq;
import cc.dfsoft.project.biz.base.subpackage.entity.SubContract;
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
import cc.dfsoft.uexpress.common.util.MoneyConverter;
import cc.dfsoft.uexpress.common.util.ResultUtil;
import cc.dfsoft.uexpress.common.util.StringUtil;
@Repository
public class SubContractDaoImpl extends NewBaseDAO<SubContract,String> implements SubContractDao{
	
	/**外部合作伙伴Dao*/
	@Resource
	BusinessPartnersDao businessPartnersDao;
    /**部门Dao*/
	@Resource
	DepartmentDao departmentDao;
	@Resource
	ProjectDao projectDao;
	/**
	 * 按分包合同编号查询
	 * @createTime 2016-7-6
	 * @param scNo
	 * @return List<SubContract>
	 */
	@Override
	public List<SubContract> findByScNo(String scNo) {
		StringBuffer hql = new StringBuffer();
		hql.append("from SubContract where 1=1");
		if(StringUtils.isNotBlank(scNo)){
			hql.append(" and scNo = '").append(scNo).append("'");
		}
		List<SubContract> result= super.findByHql(hql.toString());
		return result;
	}
	/**
	 * 分包合同条件列表查询
	 */
	@Override
	public Map<String, Object> querySubContract(SubContractQueryReq subContractQueryReq)throws ParseException{

		 Criteria c = super.getCriteria();
		 LoginInfo loginInfo=SessionUtil.getLoginInfo();
		 
		 //工程编号 
		 if(StringUtils.isNotBlank(subContractQueryReq.getProjNo())){
			 c.add(Restrictions.eq("projNo",subContractQueryReq.getProjNo()));
		 }
		 
		 //分包合同编号
		 if(StringUtils.isNotBlank(subContractQueryReq.getScNo())){
			 c.add(Restrictions.eq("scNo",subContractQueryReq.getScNo()));
		 }

		 //合同修改状态
		 if(StringUtil.isNotBlank(subContractQueryReq.getModifyState())){//合同修改审核时传递
			 if(!subContractQueryReq.getModifyState().equals("-1"))
				 c.add(Restrictions.eq("modifyState",subContractQueryReq.getModifyState()));
		 }else{//除去待审核的
			 //c.add(Restrictions.ne("modifyState",ModifyStatusEnum.TO_AUDIT.getValue()));
			 StringBuffer sql = new StringBuffer("(MODIFY_STATE <> '").append(ModifyStatusEnum.TO_AUDIT.getValue()).append("' or MODIFY_STATE is null)");
			 c.add(Restrictions.sqlRestriction(sql.toString()));
		 }
		 
		 //工程状态
		 if((subContractQueryReq.getProjStuList()!=null && subContractQueryReq.getProjStuList().size()>0)){
			 List<String> projStus=subContractQueryReq.getProjStuList();
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
				 return ResultUtil.pageResult(0, subContractQueryReq.getDraw(),new ArrayList());
			 }
		 }
		 
		 //工程名称
		 if(StringUtils.isNotBlank(subContractQueryReq.getProjName())){
			 c.add(Restrictions.like("projName","%"+subContractQueryReq.getProjName()+"%"));
		 }
		 //工程地点
		 if(StringUtils.isNotBlank(subContractQueryReq.getProjAddr())){
			 c.add(Restrictions.like("projAddr","%"+subContractQueryReq.getProjAddr()+"%"));
		 }
		 //工程id
		 if(StringUtils.isNotBlank(subContractQueryReq.getProjId())){
			 c.add(Restrictions.eq("projId",subContractQueryReq.getProjId()));
		 }
		 //乙方名称
		 if(StringUtils.isNotBlank(subContractQueryReq.getCuName())){
			 c.add(Restrictions.eq("cuName",subContractQueryReq.getCuName()));
		 }
		 //用于字符串日期格式转换
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		 //签定日期开始
		 if(StringUtils.isNotBlank(subContractQueryReq.getScSignDateStart())){
			 c.add(Restrictions.ge("scSignDate", sdf.parse(subContractQueryReq.getScSignDateStart())));
		 }
		 //签定日期结束
		 if(StringUtils.isNotBlank(subContractQueryReq.getScSignDateEnd())){
			 c.add(Restrictions.le("scSignDate", sdf.parse(subContractQueryReq.getScSignDateEnd())));
		 }
		 //付款方式
		 if(StringUtils.isNotBlank(subContractQueryReq.getPayType())){
			 c.add(Restrictions.eq("payType",subContractQueryReq.getPayType()));
		 }
		//是否打印
		if(StringUtils.isNotBlank(subContractQueryReq.getIsPrint())){
			c.add(Restrictions.eq("isPrint", subContractQueryReq.getIsPrint()));
		}
		
		 String post = loginInfo.getPost();
		 String[] postArray=post.split(",");

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
		 
		 
	 
 		 //分包方单位人员登录时，只可看自己相关项目
		 BusinessPartners bp =  businessPartnersDao.get(loginInfo.getDeptId());
		 if(bp!=null){
			//若登录人是分包方单位人员
			 if(UnitTypeEnum.CONSTRUCTION_UNIT.getValue().equals(bp.getUnitType())&& loginInfo.getPost().length()>0){
				 StringBuffer sqlFilter = projectDao.cuUnitFilter(loginInfo,loginInfo.getPost(), subContractQueryReq.getMenuId());
				 c.add(Restrictions.sqlRestriction(sqlFilter.toString()));
			 }else if(UnitTypeEnum.SUPERVISION_UNIT.getValue().equals(bp.getUnitType())){
				 //若登录人是监理单位
				 StringBuffer sqlFilter = projectDao.suUnitFilter(loginInfo, loginInfo.getPost(), subContractQueryReq.getMenuId());
				 c.add(Restrictions.sqlRestriction(sqlFilter.toString()));
			 }else{
				 //其他业务合作伙伴-如检测单位
			 }
		 }else{
			 if(StringUtils.isNotBlank(loginInfo.getCorpId())){
				 c.add(Restrictions.like("corpId","%"+loginInfo.getCorpId()+"%"));
			 }
		 }
		 Boolean falg=true;
		 if(!DeptDivideEnum.DESIGN_INSTITUTE.getValue().equals(loginInfo.getDeptDivide()) && bp==null){
			 
			 List<DataFilerSetUpDto> list = Constants.getDataFilterMapByKey(loginInfo.getDeptId()+"_"+subContractQueryReq.getMenuId());
			 if(list!=null && list.size()>0){
				 falg=false;
			 }
			 if(BusinessTypeEnum.BUSINESS_GROUP.getValue().equals(loginInfo.getBusinessType())){
					//业务部门 
					 String deptId = loginInfo.getDeptId().substring(0, loginInfo.getDeptId().length() - DeptTypeEnum.SUBCOMPANY.getInitVal().length());
					 StringBuffer sql = new StringBuffer("proj_id in(select cp.proj_id from project cp where cp.dept_id like'%").append(deptId).append("%')");
					 c.add(Restrictions.sqlRestriction(sql.toString())); 
					 
				 }else if(DeptDivideEnum.CUSTOMER_SERVICE_CENTER.getValue().equals(loginInfo.getDeptDivide())){
					 if(StringUtil.isNotBlank(loginInfo.getPost()) && (loginInfo.getPost().contains(PostTypeEnum.RECORDER.getValue())|| loginInfo.getPost().contains(PostTypeEnum.CHIEF_ENGINEER.getValue()))){
						 String deptId =loginInfo.getDeptId();
						 StringBuffer sql = new StringBuffer("proj_id in(select cp.proj_id from project cp where cp.dept_id like'%").append(deptId).append("%')");
						 c.add(Restrictions.sqlRestriction(sql.toString())); 
					 }else{
						//客服中心
						 String deptId = loginInfo.getDeptId().substring(0, loginInfo.getDeptId().length() - DeptTypeEnum.SUBCOMPANY.getInitVal().length());
						 StringBuffer sql = new StringBuffer("proj_id in(select cp.proj_id from project cp where cp.dept_id like'%").append(deptId).append("%')");
						 c.add(Restrictions.sqlRestriction(sql.toString())); 
					 }
				 }else{	
					 if(falg){
						//工程部门
						 StringBuffer sql = new StringBuffer("proj_id in(select cp.proj_id from project cp where cp.dept_id like'%").append(loginInfo.getDeptId()).append("%')");
						 c.add(Restrictions.sqlRestriction(sql.toString()));
					 }
				 }
		 }
	 	 
		 c.addOrder(Order.desc("scSignDate"));
		 // 数据库根据条件过滤记录数
		 int filterCount = this.countByCriteria(c);

		 // 根据条件获取查询信息
		 List<SubContract> subContracts = this.findBySortCriteria(c, subContractQueryReq);
		 if(subContracts!=null&&subContracts.size()>0){
			 for(SubContract sub :subContracts){
				 if(sub.getScAmount()!=null){
					 sub.setLegalAmount(MoneyConverter.Num2RMB(sub.getScAmount().doubleValue()));
				 }
				
			 } 
		 }
		
		 // 返回结果
		 return ResultUtil.pageResult( filterCount, subContractQueryReq.getDraw(),subContracts);
	}
	/**
	 * 根据工程Id查分包合同
	 */
	@Override
	public SubContract findSubContractByprojId(String projId)throws ParseException {
		Criteria c = super.getCriteria();
		if(StringUtils.isNotBlank(projId)){
			c.add(Restrictions.eq("projId", projId));
			List<SubContract> list = this.findByCriteria(c);
			if(list != null && list.size()>0){
				return list.get(0);
			}
		}
		return null;
	}
	/**
	 * 根据工程编号查分包合同
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<SubContract> findByProjNo(String projNo) {
		StringBuffer hql = new StringBuffer();
		hql.append("from SubContract where projNo = '").append(projNo).append("'");
		return super.findByHql(hql.toString());
	}
	/**
	 * 根据工程Id查询分包合同信息
	 */
	@Override
	public SubContract findByProjId(String projId) {
		StringBuffer hql = new StringBuffer();
		hql.append("from SubContract where projId = '").append(projId).append("'");
		List<SubContract> list = super.findByHql(hql.toString());
		if(list!=null && list.size()>0){
			return list.get(0);
		}
		return null;
	}

}
