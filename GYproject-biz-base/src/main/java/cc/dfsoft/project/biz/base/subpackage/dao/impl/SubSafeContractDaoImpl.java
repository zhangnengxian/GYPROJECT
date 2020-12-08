package cc.dfsoft.project.biz.base.subpackage.dao.impl;

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
import cc.dfsoft.project.biz.base.design.enums.UnitTypeEnum;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.subpackage.dao.SubSafeContractDao;
import cc.dfsoft.project.biz.base.subpackage.dto.SubSafeContractQueryReq;
import cc.dfsoft.project.biz.base.subpackage.entity.SubSafeContract;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;
import cc.dfsoft.uexpress.common.util.ResultUtil;
@Repository
public class SubSafeContractDaoImpl extends NewBaseDAO<SubSafeContract,String> implements SubSafeContractDao{

	/**外部合作伙伴Dao*/
	@Resource
	BusinessPartnersDao businessPartnersDao;
	
	@Override
	public SubSafeContract findSubSafeContractByprojId(String projId)throws ParseException {
		Criteria c = super.getCriteria();
		if(StringUtils.isNotBlank(projId)){
			c.add(Restrictions.eq("projId", projId));
			List<SubSafeContract> list = this.findByCriteria(c);
			if(list != null && list.size()>0){
				return list.get(0);
			}
		}
		return null;
	}

	@Override
	public Map<String, Object> querySubSafeContract(SubSafeContractQueryReq subSafeContractQueryReq)
			throws ParseException {
		Criteria c = super.getCriteria();
		 //工程编号 
		 if(StringUtils.isNotBlank(subSafeContractQueryReq.getProjNo())){
			 c.add(Restrictions.eq("projNo",subSafeContractQueryReq.getProjNo()));
		 }
		 
		 //工程状态
		 if((subSafeContractQueryReq.getProjStuList()!=null && subSafeContractQueryReq.getProjStuList().size()>0)){
			 List<String> projStus=subSafeContractQueryReq.getProjStuList();
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
				 return ResultUtil.pageResult(0, subSafeContractQueryReq.getDraw(),new ArrayList());
			 }
		 }
		 
		 //工程名称
		 if(StringUtils.isNotBlank(subSafeContractQueryReq.getProjName())){
			 c.add(Restrictions.like("projName","%"+subSafeContractQueryReq.getProjName()+"%"));
		 }
		 //工程地点
		 if(StringUtils.isNotBlank(subSafeContractQueryReq.getProjAddr())){
			 c.add(Restrictions.like("projAddr","%"+subSafeContractQueryReq.getProjAddr()+"%"));
		 }
		 //工程id
		 if(StringUtils.isNotBlank(subSafeContractQueryReq.getProjId())){
			 c.add(Restrictions.eq("projId",subSafeContractQueryReq.getProjId()));
		 }
		 //乙方名称
		 if(StringUtils.isNotBlank(subSafeContractQueryReq.getCuName())){
			 c.add(Restrictions.eq("cuName",subSafeContractQueryReq.getCuName()));
		 }
		 //用于字符串日期格式转换
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		 //签定日期开始
		 if(StringUtils.isNotBlank(subSafeContractQueryReq.getSignDateStart())){
			 c.add(Restrictions.ge("ssSignDate", sdf.parse(subSafeContractQueryReq.getSignDateStart())));
		 }
		 //签定日期结束
		 if(StringUtils.isNotBlank(subSafeContractQueryReq.getSignDateEnd())){
			 c.add(Restrictions.le("ssSignDate", sdf.parse(subSafeContractQueryReq.getSignDateEnd())));
		 }
		 
		 LoginInfo loginInfo=SessionUtil.getLoginInfo();
		 
		 //分包方单位人员登录时，只可看自己相关项目
		 BusinessPartners bp =  businessPartnersDao.get(loginInfo.getDeptId());
		 if(bp!=null){
			 //若登录人是分包方单位人员
			 if(UnitTypeEnum.CONSTRUCTION_UNIT.getValue().equals(bp.getUnitType())){
				 //通过分包单位人员id查询
				 StringBuffer sql = new StringBuffer("proj_id in(select cp.proj_id from construction_plan cp where cp.cu_id='").append(loginInfo.getDeptId()).append("')");
				 c.add(Restrictions.sqlRestriction(sql.toString()));
			 }
		 }
		 
		 //施工管理处找自己的合同
		 String deptId = loginInfo.getDeptId();
		 if ("110201".equals(deptId) || "110202".equals(deptId) ||"110203".equals(deptId) 
				 ||"110204".equals(deptId) ||"110205".equals(deptId) ) {
			 //综合统计员找自己的分包合同
			 StringBuffer sql = new StringBuffer("proj_id in(select cp.proj_id from construction_plan cp where cp.MANAGEMENT_ID='").append(loginInfo.getDeptId()).append("')");
			 c.add(Restrictions.sqlRestriction(sql.toString()));
			 
		 }
		 // 数据库根据条件过滤记录数
		 int filterCount = this.countByCriteria(c);

		 // 根据条件获取查询信息
		 List<SubSafeContract> subContracts = this.findBySortCriteria(c, subSafeContractQueryReq);
		
		 // 返回结果
		 return ResultUtil.pageResult( filterCount, subSafeContractQueryReq.getDraw(),subContracts);
	}

}
