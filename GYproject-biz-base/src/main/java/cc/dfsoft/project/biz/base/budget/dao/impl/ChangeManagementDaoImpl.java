package cc.dfsoft.project.biz.base.budget.dao.impl;

import cc.dfsoft.project.biz.base.budget.dao.ChangeManagementDao;
import cc.dfsoft.project.biz.base.budget.dto.ChangeManagementQueryReq;
import cc.dfsoft.project.biz.base.budget.enums.BudgetTypeEnum;
import cc.dfsoft.project.biz.base.constructmanage.entity.ChangeManagement;
import cc.dfsoft.project.biz.base.design.enums.DesignChangeStateEnum;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.biz.sys.dept.enums.PostTypeEnum;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.constant.DataFilerSetUpDto;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;
import cc.dfsoft.uexpress.common.util.ResultUtil;
import cc.dfsoft.uexpress.common.util.StringUtil;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
@Repository
public class ChangeManagementDaoImpl extends NewBaseDAO<ChangeManagement, String> implements ChangeManagementDao{
	
	/**
	 * 变更记录列表查询
	 * @author fuliwei
	 * @createTime 2016-07-12
	 * @param changeManagementQueryReq
	 * @return Map<String, Object>
	 */
	@Override
	public Map<String, Object> queryChangeManagement(ChangeManagementQueryReq changeManagementQueryReq)
			throws ParseException {
		Criteria c = super.getCriteria();
		//工程id
		if(StringUtils.isNotBlank(changeManagementQueryReq.getProjId())){
			c.add(Restrictions.eq("projId",changeManagementQueryReq.getProjId()));
		}
		//工程编号
		if(StringUtils.isNotBlank(changeManagementQueryReq.getProjNo())){
			c.add(Restrictions.eq("projNo",changeManagementQueryReq.getProjNo()));
		}
		//工程名称
		if(StringUtils.isNotBlank(changeManagementQueryReq.getProjName())){
			c.add(Restrictions.eq("projName",changeManagementQueryReq.getProjName()));
		}
		//工程地点
		if(StringUtils.isNotBlank(changeManagementQueryReq.getProjAddr())){
			c.add(Restrictions.eq("projAddr",changeManagementQueryReq.getProjAddr()));
		}
		//变更状态
		if(StringUtils.isNotBlank(changeManagementQueryReq.getCmState())){
			c.add(Restrictions.eq("cmState",changeManagementQueryReq.getCmState()));
		}
		//变更类型
		if(StringUtils.isNotBlank(changeManagementQueryReq.getChangeType())){
			c.add(Restrictions.eq("changeType",changeManagementQueryReq.getChangeType()));
		}
		//审核状态
		if(changeManagementQueryReq.getAuditState()!=null&&changeManagementQueryReq.getAuditState().size()>0){
			c.add(Restrictions.in("auditState", changeManagementQueryReq.getAuditState()));
		}
		//变更原因
		if(StringUtils.isNotBlank(changeManagementQueryReq.getCuReason())){
			c.add(Restrictions.like("cuReason","%"+changeManagementQueryReq.getCuReason()+"%"));
		}
		//变更编号
		if(StringUtils.isNotBlank(changeManagementQueryReq.getCmNo())){
			c.add(Restrictions.eq("cmNo",changeManagementQueryReq.getCmNo()));
		}
		//用于字符串日期格式转换
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		//变更日期开始日期
		if(StringUtils.isNotBlank(changeManagementQueryReq.getCmDateStart())){
			c.add(Restrictions.ge("cmDate", sdf.parse(changeManagementQueryReq.getCmDateStart())));
		}
		//变更日期结束日期
		if(StringUtils.isNotBlank(changeManagementQueryReq.getCmDateEnd())){
			c.add(Restrictions.le("cmDate", sdf.parse(changeManagementQueryReq.getCmDateEnd())));
		}
		
		//申请日期开始日期
		if(StringUtils.isNotBlank(changeManagementQueryReq.getApplyDateStart())){
			c.add(Restrictions.ge("applyDate", sdf.parse(changeManagementQueryReq.getApplyDateStart())));
		}
		//申请日期结束日期
		if(StringUtils.isNotBlank(changeManagementQueryReq.getApplyDateEnd())){
			c.add(Restrictions.le("applyDate", sdf.parse(changeManagementQueryReq.getApplyDateEnd())));
		}
				
		
		
		LoginInfo loginInfo =SessionUtil.getLoginInfo();
		String post=loginInfo.getPost();
		//设计员找自己的变更
		if(post.contains(PostTypeEnum.DESIGNER.getValue())){
			StringBuffer sql=new StringBuffer("proj_id in(select p.proj_id from project p where p.designer_id='").append(loginInfo.getStaffId()).append("')");
			c.add(Restrictions.sqlRestriction(sql.toString()));
		}

		DataFilerSetUpDto isConfig = Constants.isConfig(loginInfo.getStaffId() + "_" + changeManagementQueryReq.getMenuId());
		//预算员找自己的工程
		if(post.contains(PostTypeEnum.BUDGET_MEMBER.getValue())&& isConfig==null){
			StringBuffer sql=new StringBuffer("proj_id in(select p.proj_id from project p where p.BUDGETER_ID='").append(loginInfo.getStaffId()).append("')");
			c.add(Restrictions.sqlRestriction(sql.toString()));
		}
		//本公司管理员查找本公司的工程
		if(post.contains(PostTypeEnum.ADMIN.getValue())){  
			StringBuffer sql=new StringBuffer("proj_id in(select p.proj_id from project p where p.dept_id like '").append(loginInfo.getDeptId()).append("%')");
			//System.out.println(sql);
			c.add(Restrictions.sqlRestriction(sql.toString()));
		}
		//部长查看本公司设计变更
		if(post.contains(PostTypeEnum.MINISTER.getValue())){
			StringBuffer sql=new StringBuffer("proj_id in(select p.proj_id from project p where p.dept_id like '").append(loginInfo.getDeptId()).append("%')");
			//System.out.println(sql);
			c.add(Restrictions.sqlRestriction(sql.toString()));
		}
		//现场代表找自己的工程
		if(post.contains(PostTypeEnum.BUILDER.getValue())){
			StringBuffer sql=new StringBuffer("proj_id in(select cp.proj_id from CONSTRUCTION_PLAN cp where cp.BUILDER_ID='").append(loginInfo.getStaffId()).append("')");
			c.add(Restrictions.sqlRestriction(sql.toString()));
		}
		//变更状态
		if(StringUtils.isNotBlank(changeManagementQueryReq.getDesignChangeType())){
			c.add(Restrictions.eq("designChangeType", changeManagementQueryReq.getDesignChangeType()));
		}
		
		
		c.addOrder(Order.desc("cmId"));
		
		// 数据库根据条件过滤记录数
		int filterCount = this.countByCriteria(c);

		// 根据条件获取查询信息
		List<ChangeManagement> projectList = this.findBySortCriteria(c, changeManagementQueryReq);
		
		// 返回结果
		return ResultUtil.pageResult( filterCount, changeManagementQueryReq.getDraw(),projectList);
	}
	
	/**
	 * id查询变更记录
	 * @author fuliwei
	 * @createTime 2016-7-14
	 * @param projId
	 * @return ChangeManagement
	 */
	@Override
	public ChangeManagement queryByprojId(String projId) {
		StringBuffer hql = new StringBuffer();
		hql.append("from ChangeManagement c where c.projId='").append(projId).append("'");
		List<ChangeManagement>list = super.findByHql(hql.toString());
		if(null!=list&&list.size()>0){
		return list.get(0);
		}
		return null;
	}

	@Override
	public void updateChangeState(String id) {
		String sql="update change_management m set m.cm_state=1 where m.cm_id='"+id+"'";
		super.executeSql(sql);
	}

	@Override
	public String getMaxCmNo(String date,String projId) {
		Criteria c = super.getCriteria();
		c.setProjection(Projections.max("cmNo"));
		c.add(Restrictions.like("cmNo",date+"%"));
		//工程编号
		if(StringUtils.isNotBlank(projId)){
			c.add(Restrictions.eq("projId", projId));
		}
		String cmNoMax = (String) c.uniqueResult();
		if(StringUtils.isNotBlank(cmNoMax)){
			int cmNo = Integer.parseInt(cmNoMax)+1;
			return ""+cmNo;
		}
		return "";
	}

	/**
	 * 根据工程ID查询所有变更记录
	 */
	@Override
	public List<ChangeManagement> findByProjId(String projId,String designChangeType,String changeType) {
		Criteria c = super.getCriteria();
		//工程id
		if(StringUtils.isNotBlank(projId)){
			c.add(Restrictions.eq("projId",projId));
		}
		//审核状态
		if(StringUtils.isNotBlank(designChangeType)){
			c.add(Restrictions.eq("designChangeType",designChangeType));
		}
		//变更类型
		if(StringUtils.isNotBlank(changeType)){
			c.add(Restrictions.eq("changeType",changeType));
		}
		List<ChangeManagement> cList = this.findByCriteria(c);
		return cList;
	}

	/**
	 * 未终止、未完成的变更记录
	 */
	@Override
	public List<ChangeManagement> findNoCancelCM(String projId,String changeType) {
		Criteria c = super.getCriteria();
		//工程id
		if(StringUtils.isNotBlank(projId)){
			c.add(Restrictions.eq("projId",projId));
		}
		//工程id
		if(StringUtils.isNotBlank(changeType)){
			c.add(Restrictions.eq("changeType",changeType));
		}
		//未终止、未签完补充协议，未完成
		c.add(Restrictions.ne("designChangeType",DesignChangeStateEnum.TO_CANCEL.getValue()));
		c.add(Restrictions.ne("designChangeType",DesignChangeStateEnum.ALREADY_SIGN_SUPPLEMENT.getValue()));
		c.add(Restrictions.ne("designChangeType",DesignChangeStateEnum.ALREADY_FINISHED.getValue()));
		return this.findByCriteria(c);
	}

	/**
	 * 当前设计人待推送且存在材料变更的记录
	 */
	@Override
	public List<ChangeManagement> queryChangementNotice() {
		Criteria c = super.getCriteria();
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		//当前设计员
		c.add(Restrictions.eq("designerId",loginInfo.getStaffId()));
		//待推送状态的
		c.add(Restrictions.eq("designChangeType",DesignChangeStateEnum.WAIT_PUSH.getValue()));
		//需要设计变更材料
		c.add(Restrictions.eq("changeMaterialFlag",BudgetTypeEnum.ADJUSTED.getValue()));
		return this.findByCriteria(c);
	}

	/**
	 * 待审核的变更记录
	 */
	@Override
	public List<ChangeManagement> queryChangementAuditNotice(String changeType) {
		Criteria c = super.getCriteria();
		//变更类型
		if(StringUtil.isNotBlank(changeType)){
			c.add(Restrictions.eq("changeType",changeType));
		}
		//待审核状态的
		c.add(Restrictions.eq("designChangeType",DesignChangeStateEnum.WAIT_AUDIT.getValue()));
		return this.findByCriteria(c);
	}

}
