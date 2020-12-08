package cc.dfsoft.project.biz.base.complete.dao.impl;

import cc.dfsoft.project.biz.base.baseinfo.dao.BusinessPartnersDao;
import cc.dfsoft.project.biz.base.baseinfo.entity.BusinessPartners;
import cc.dfsoft.project.biz.base.complete.dao.JointAcceptanceDao;
import cc.dfsoft.project.biz.base.complete.dto.JointAcceptanceReq;
import cc.dfsoft.project.biz.base.complete.entity.JointAcceptance;
import cc.dfsoft.project.biz.base.design.enums.UnitTypeEnum;
import cc.dfsoft.project.biz.base.project.dao.ProjectDao;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.biz.sys.dept.enums.DeptDivideEnum;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;
import cc.dfsoft.uexpress.common.util.ResultUtil;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

@Repository
public class JointAcceptanceDaoImpl extends NewBaseDAO<JointAcceptance, String> implements JointAcceptanceDao {
	
	/**业务合作伙伴*/
	@Resource
    BusinessPartnersDao businessPartnersDao;
	@Resource
	ProjectDao projectDao;
	
	/**
	 * 联合验收条件查询
	 */
	@Override
	public Map<String, Object> queryJointAcceptance(JointAcceptanceReq jointAcceptanceReq) throws ParseException {
		 
		Criteria c = super.getCriteria();
		 //工程编号 
		 if(StringUtils.isNotBlank(jointAcceptanceReq.getProjNo())){
			 c.add(Restrictions.eq("projNo",jointAcceptanceReq.getProjNo()));
		 }
		 //工程名称
		 if(StringUtils.isNotBlank(jointAcceptanceReq.getProjName())){
			 c.add(Restrictions.like("projName","%"+jointAcceptanceReq.getProjName()+"%"));
		 }
		 //施工地点
		 if(StringUtils.isNotBlank(jointAcceptanceReq.getProjAddr())){
			 c.add(Restrictions.like("projAddr","%"+jointAcceptanceReq.getProjAddr()+"%"));
		 }
		 //工程id
		 if(StringUtils.isNotBlank(jointAcceptanceReq.getProjId())){
			 c.add(Restrictions.eq("projId",jointAcceptanceReq.getProjId()));
		 }
		 //客户名称
		 if(StringUtils.isNotBlank(jointAcceptanceReq.getCustName())){
			 c.add(Restrictions.eq("custName",jointAcceptanceReq.getCustName()));
		 }
		 
		 //验收类型
		 if(StringUtils.isNotBlank(jointAcceptanceReq.getAcceptanceType())){
			 c.add(Restrictions.eq("acceptanceType",jointAcceptanceReq.getAcceptanceType()));
			 StringBuffer sql = new StringBuffer("proj_id in (select p.proj_id from project p where p.proj_status_id >='").append("1028").append("')"); //加载结算报审之后的工程
			 c.add(Restrictions.sqlRestriction(sql.toString()));
		 }
		 //加载报审之后的工程
		 
	   
		 
		 //用于字符串日期格式转换
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		 //验收日期开始
		 if(StringUtils.isNotBlank(jointAcceptanceReq.getJaDateStart())){
			 c.add(Restrictions.ge("jaDate", sdf.parse(jointAcceptanceReq.getJaDateStart())));
		 }
		 //验收日期结束
		 if(StringUtils.isNotBlank(jointAcceptanceReq.getJaDateEnd())){
			 c.add(Restrictions.le("jaDate", sdf.parse(jointAcceptanceReq.getJaDateEnd())));
		 }
		 
		 
		 //申请日期开始
		 if(StringUtils.isNotBlank(jointAcceptanceReq.getApplyDateStart())){
			 c.add(Restrictions.ge("applyDate", sdf.parse(jointAcceptanceReq.getApplyDateStart())));
		 }
		 //申请日期结束
		 if(StringUtils.isNotBlank(jointAcceptanceReq.getApplyDateEnd())){
			 c.add(Restrictions.le("applyDate", sdf.parse(jointAcceptanceReq.getApplyDateEnd())));
		 }
		 
		 
		//是否打印
		 if(StringUtils.isNotBlank(jointAcceptanceReq.getIsPrint())){
			 c.add(Restrictions.eq("isPrint", jointAcceptanceReq.getIsPrint()));
		 }
		 
		 LoginInfo loginInfo=SessionUtil.getLoginInfo();
		 // 施工管理处的人员只能看到参与的工程
		 String deptId = loginInfo.getDeptId();
//		 if("110201".equals(deptId)||"110202".equals(deptId)||"110203".equals(deptId)||
//				 "110204".equals(deptId)||"110205".equals(deptId)){
//			 //找施工员自己负责的联合验收单
//			 if(PostTypeEnum.BUILDER.getValue().equals(loginInfo.getPost())){
//				 StringBuffer sql=new StringBuffer("proj_id in (select cp.proj_id from construction_Plan cp where cp.BUILDER='").append(loginInfo.getStaffName()).append("')");
//				 c.add(Restrictions.sqlRestriction(sql.toString()));
//			 }else{
//				 //施工管理处副处、处长找自己工程处的工程
//				 StringBuffer sql = new StringBuffer("proj_id in(select cp.proj_id from construction_plan cp where cp.MANAGEMENT_ID='").append(loginInfo.getDeptId()).append("')");
//				 c.add(Restrictions.sqlRestriction(sql.toString()));
//			 }
//		 }
		 
		//分包方单位人员登录时，只可看自己相关项目
		 BusinessPartners bp =  businessPartnersDao.get(loginInfo.getDeptId()); 
		 if(bp!=null){
			//若登录人是分包方单位人员
			 if(UnitTypeEnum.CONSTRUCTION_UNIT.getValue().equals(bp.getUnitType())&& loginInfo.getPost().length()>0){
				 StringBuffer sql = projectDao.cuUnitFilter(loginInfo,loginInfo.getPost(), jointAcceptanceReq.getMenuId());
				 c.add(Restrictions.sqlRestriction(sql.toString()));
			 }else if(UnitTypeEnum.SUPERVISION_UNIT.getValue().equals(bp.getUnitType())){
				//若登录人是监理单位
				 StringBuffer sql = projectDao.suUnitFilter(loginInfo, loginInfo.getPost(), jointAcceptanceReq.getMenuId());
				 c.add(Restrictions.sqlRestriction(sql.toString()));
			 }else{
				 //其他业务合作伙伴-如检测单位
			 }
		 }else if(bp==null && !DeptDivideEnum.DESIGN_INSTITUTE.getValue().equals(loginInfo.getDeptDivide())){ //非分包单位且非设计院
			//分公司id查询
			 StringBuffer sql = new StringBuffer("proj_id in(select cp.proj_id from project cp where cp.corp_id like '").append(loginInfo.getCorpId()).append("%')");
			 c.add(Restrictions.sqlRestriction(sql.toString())); 
		 }
		 
		 //验收处片区管理员只能看到参与的工程
//		 if(PostTypeEnum.AREAMANAGER.getValue().equals(loginInfo.getPost())){
//			 StringBuffer sql=new StringBuffer("proj_id in(select cp.proj_id from construction_plan cp where cp.AREA_MANAGER='").append(loginInfo.getStaffName()).append("')");
//			 c.add(Restrictions.sqlRestriction(sql.toString()));
//		 }
		 c.addOrder(Order.desc("jaDate"));  // 验收日期
		 // 数据库根据条件过滤记录数
		 int filterCount = this.countByCriteria(c);

		 // 根据条件获取查询信息 
		 List<JointAcceptance> projectList = this.findBySortCriteria(c, jointAcceptanceReq);
		
		 // 返回结果
		 return ResultUtil.pageResult( filterCount, jointAcceptanceReq.getDraw(),projectList);
	}

	@Override
	public List<JointAcceptance> findById(String projId) {
		StringBuffer hql = new StringBuffer("from JointAcceptance where projId='").append(projId).append("'");
		List<JointAcceptance> result = super.findByHql(hql.toString());
		return result;
	}

	@Override
	public List<JointAcceptance> findByType(String projId, String dataType) {
		Criteria c = super.getCriteria();
		 //工程id
		 if(StringUtils.isNotBlank(projId)){
			 c.add(Restrictions.eq("projId",projId));
		 }
		 //资料类型
		 if(StringUtils.isNotBlank(dataType)){
			 c.add(Restrictions.eq("dataType",dataType));
		 }
		 List<JointAcceptance> result = this.findByCriteria(c);
		return result;
	}

	@Override
	public List<JointAcceptance> findByProjIdAndType(String projId, String value) {
		Criteria c = super.getCriteria();
		
		 //工程id
		 if(StringUtils.isNotBlank(projId)){
			 c.add(Restrictions.eq("projId",projId));
		 }
		 //验收类型
		 if(StringUtils.isNotBlank(value)){
			 c.add(Restrictions.eq("acceptanceType",value));
		 }
		 List<JointAcceptance> result = this.findByCriteria(c);
		return result;
	}



	@Override
	public int totalByProjId(String projId) {
		Criteria c = super.getCriteria();
		if(StringUtils.isNotBlank(projId))
			c.add(Restrictions.eq("projId",projId));
		return this.countByCriteria(c);
	}

}
