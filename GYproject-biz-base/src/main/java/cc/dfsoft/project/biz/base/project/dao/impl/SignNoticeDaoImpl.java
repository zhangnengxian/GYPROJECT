package cc.dfsoft.project.biz.base.project.dao.impl;

import cc.dfsoft.project.biz.base.baseinfo.service.BusinessPartnersService;
import cc.dfsoft.project.biz.base.design.enums.UnitTypeEnum;
import cc.dfsoft.project.biz.base.inspection.entity.DerustingPreservative;
import cc.dfsoft.project.biz.base.inspection.entity.GrooveRecord;
import cc.dfsoft.project.biz.base.inspection.entity.PepipeWelding;
import cc.dfsoft.project.biz.base.project.dao.SignNoticeDao;
import cc.dfsoft.project.biz.base.project.entity.SignNotice;
import cc.dfsoft.project.biz.base.project.enums.SignPostEnum;
import cc.dfsoft.project.biz.base.project.enums.SignStateEnum;
import cc.dfsoft.project.biz.base.project.service.NoticeService;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dao.DepartmentDao;
import cc.dfsoft.uexpress.biz.sys.dept.dao.PostDao;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.biz.sys.dept.entity.Post;
import cc.dfsoft.uexpress.biz.sys.dept.enums.PostTypeEnum;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.constant.DataFilerSetUpDto;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;
import cc.dfsoft.uexpress.common.util.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class SignNoticeDaoImpl extends NewBaseDAO<SignNotice, String> implements SignNoticeDao {

	/**部门Dao*/
	@Resource
	DepartmentDao departmentDao;
	@Resource
	NoticeService noticeService;
	@Resource
	BusinessPartnersService businessPartnersService;
	@Resource
	PostDao postDao;

	/**
	 * 查询是否已生成
	 * @author fuliwei
	 * @createTime 2018年1月18日
	 * @param
	 * @return
	 */
	@Override
	public SignNotice queryByProjIdAndPost(String projId, String post, String dataType) {
		Criteria c = super.getCriteria();

		// 工程id
		if (StringUtils.isNotBlank(projId)) {
			c.add(Restrictions.eq("projId", projId));
			// 根据条件获取查询信息
		}

		//职务
		if (StringUtils.isNotBlank(post)) {
			c.add(Restrictions.eq("post", post));
			// 根据条件获取查询信息
		}
		//单据类型
		if (StringUtils.isNotBlank(dataType)) {
			c.add(Restrictions.eq("dataType", dataType));
			// 根据条件获取查询信息
		}

		List<SignNotice> signlList = this.findByCriteria(c);
		if(signlList!=null && signlList.size()>0){
			return signlList.get(0);
		}
		return null;
	}

	/**
	 * 查询是否已生成-业务单id
	 * @author fuliwei
	 * @createTime 2018年1月20日
	 * @param
	 * @return
	 */
	@Override
	public SignNotice queryByBusiIdAndPost(String busiId, String post, String dataType) {
		Criteria c = super.getCriteria();

		// 业务单id
		if (StringUtils.isNotBlank(busiId)) {
			c.add(Restrictions.eq("businessOrderId", busiId));
			// 根据条件获取查询信息
		}

		//职务
		if (StringUtils.isNotBlank(post)) {
			c.add(Restrictions.eq("post", post));
			// 根据条件获取查询信息
		}
		//单据类型
		if (StringUtils.isNotBlank(dataType)) {
			c.add(Restrictions.eq("dataType", dataType));
			// 根据条件获取查询信息
		}

		List<SignNotice> signlList = this.findByCriteria(c);
		if(signlList!=null && signlList.size()>0){
			return signlList.get(0);
		}

		return null;
	}
	/**
	 * 查询该业务单的签字
	 * @author fuliwei
	 * @createTime 2018年1月21日
	 * @param
	 * @return
	 */
	@Override
	public List<SignNotice> queryByBusiIdAndDataType(String busiId, String dataType) {
		Criteria c = super.getCriteria();

		// 业务单id
		if (StringUtils.isNotBlank(busiId)) {
			c.add(Restrictions.eq("businessOrderId", busiId));
			// 根据条件获取查询信息
		}

		//单据类型
		if (StringUtils.isNotBlank(dataType)) {
			c.add(Restrictions.eq("dataType", dataType));
			// 根据条件获取查询信息
		}

		List<SignNotice> signlList = this.findByCriteria(c);
		return signlList;
	}



	/**
	 * 查询签字通知
	 * @author fuliwei
	 * @createTime 2018年1月21日
	 * @param
	 * @return
	 */
	@Override
	public List<SignNotice> querySignNotice() {
		Criteria criteria = super.getCriteria();
		criteria.add(Restrictions.eq("status", SignStateEnum.READY_SIGN.getValue()));  // 未签字的通知

		LoginInfo loginInfo=SessionUtil.getLoginInfo();
		String postStr=loginInfo.getPost();
		//获取用户角色菜单的签字标准配置
		List<String> dataTypes = noticeService.getNoticeMenuDateType();
		if(dataTypes!=null && dataTypes.size()>0){
			criteria.add(Restrictions.in("dataType",dataTypes));
		}else{
			//该用户没有签字通知的单据
			return new ArrayList<SignNotice>();
		}

		//施工单位人员签字通知查询
		if(UnitTypeEnum.CONSTRUCTION_UNIT.getValue().equals(loginInfo.getUnitType())&& loginInfo.getPost().length()>0){
			criteria = bpFilterSignNotice(criteria,dataTypes);
			List<SignNotice> signlList = this.findByCriteria(criteria);
			return signlList;
		}else if(UnitTypeEnum.SUPERVISION_UNIT.getValue().equals(loginInfo.getUnitType())&& loginInfo.getPost().length()>0){
			//监理单位人员签字通知查询
			criteria = bpFilterSignNotice(criteria,dataTypes);
			List<SignNotice> signlList = this.findByCriteria(criteria);
			return signlList;
		}
		//非施工、监理单位
		//有配置，则走配置的相应sql分公司工程部长
		 boolean flagFilter = true;
		//特殊情况下配置的签字通知根据配置sql查询
		 List<DataFilerSetUpDto> corpSign = Constants.getDataFilterMapByKey(SignPostEnum.PROJECT_LEADER.getValue()+"_"+loginInfo.getStaffId()+"_0");
		 if(corpSign!=null && corpSign.size()>0){
			 if(StringUtil.isNotBlank(corpSign.get(0).getSupSql())){
				 StringBuffer hql = new StringBuffer();
				 hql.append(corpSign.get(0).getSupSql());
				 criteria.add(Restrictions.sqlRestriction(hql.toString()));
			 }

			 flagFilter=false;
		 }
		//指定的派工职务
		if(postStr!=null && flagFilter){
			String[] posts =postStr.split(",");
			boolean flag = false;
			List<String> otherPost = new ArrayList<String>();
			StringBuffer filterSql = new StringBuffer();
			for(String postId : posts){
				Post post = postDao.get("id", postId);
				if(post!=null){
					//工程表中指定的字段
					if(StringUtil.isNotBlank(post.getTableColumName())){
						if(flag){
							filterSql.append(" or ");
						}
						//字段名称
						//安发检测人员
						if(PostTypeEnum.CHECKER.getValue().equals(","+post.getId()+",")){
							//查询的是业务沟通表中的检测人
							filterSql.append("(proj_id in(select cp.proj_id from business_communication cp where cp.").append(post.getTableColumName());
						}else{
							//其他工程表中指定人员
							filterSql.append("(proj_id in(select p.proj_id from project p where 1=1 and p.").append(post.getTableColumName());
						}
						filterSql.append(" ='").append(loginInfo.getStaffId()).append("'");
						filterSql.append(") and post =',"+post.getId()+",')");
						flag = true;
					}else{
						otherPost.add(postId);
					}
				}else{
					otherPost.add(postId);
				}
			}
			//非指定人员的职务，根据所属分公司ID和多职务去查询
			if(otherPost!=null && otherPost.size()>0){
				if(flag){
					filterSql.append(" or ");
				}
				StringBuffer otherNotice = new StringBuffer("(proj_id in(select p.proj_id from project p where 1=1 ");
				
				//包含下面职务的，则查询相应受理部门下的
				 if((postStr.contains(PostTypeEnum.MARKETING_CENTER_LEADER_DEPUTY.getValue())||
						 postStr.contains(PostTypeEnum.MODIFICATION_GROUP_LEADER.getValue())||
						 postStr.contains(PostTypeEnum.TRUNK_GROUP_LEADER.getValue())||
						 postStr.contains(PostTypeEnum.DEPUTY_LEADER.getValue())||
						 postStr.contains(PostTypeEnum.RECORDER.getValue()))){
					        //副主任
					 otherNotice.append(" and p.dept_id like '").append(loginInfo.getDeptId()).append("%'");
				 }
				//所属分公司
				if(StringUtil.isNotBlank(loginInfo.getBelongCorpId())){
					otherNotice.append(" and  p.corp_id in('");
					String [] belongCorpIds = loginInfo.getBelongCorpId().split(",");
					for(int i=1;i<belongCorpIds.length;i++){
						otherNotice.append(belongCorpIds[i]).append("','");
					}
					otherNotice.append(belongCorpIds[0]).append("') ");
				}else{//人员所在公司
					otherNotice.append(" and p.corp_id='").append(loginInfo.getCorpId()).append("'");
				}

				DataFilerSetUpDto config = Constants.isConfig(loginInfo.getStaffId() + "_0");
				if (config!=null && StringUtils.isNotBlank(config.getSupSql())){
					otherNotice.append(config.getSupSql());
				}

				//非派工职务
				otherNotice.append(") and post in('");
				for(int i=1;i<otherPost.size();i++){
					otherNotice.append(","+otherPost.get(i)+",").append("','");
				}
				otherNotice.append(otherPost.get(0)).append("') ");
				otherNotice.append(")");
				filterSql.append(otherNotice);
			}
			criteria.add(Restrictions.sqlRestriction("("+filterSql.toString()+")"));
		}
		
		List<SignNotice> signlList = this.findByCriteria(criteria);
		return signlList;

	}
    
	/**
	 * 注释：施工单位签字通知查询，此方法可进行优化，bpFilterSignNotice这个可查询监理、施工单位的签字通知
	 * @author liaoyq
	 * @param criteria 
	 * @param dataTypes 
	 * @createTime 2019年6月21日
	 * @return
	 *
	 */
	public Criteria cuUnitFilterSignNotice(Criteria criteria, List<String> dataTypes) {
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		String post = loginInfo.getPost();
		if(StringUtil.isNotBlank(post)){
			//项目经理
			StringBuffer cuPmSignNotice = new StringBuffer("(proj_id in(select cp.proj_id from construction_plan cp where 1=1 and cp.CU_PM_ID='").append(loginInfo.getStaffId()).append("'");
			cuPmSignNotice.append(") and post ='").append(PostTypeEnum.CU_PM.getValue()).append("')");
			//安全员
			StringBuffer saftyofficerNotice = new StringBuffer("(proj_id in(select cp.proj_id from construction_plan cp where 1=1 and cp.SAFTY_OFFICER_ID='").append(loginInfo.getStaffId()).append("'");
			saftyofficerNotice.append(") and post ='").append(PostTypeEnum.SAFTYOFFICER.getValue()).append("')");
			//质检员
			StringBuffer quaCheckerNotice = new StringBuffer("(proj_id in(select cp.proj_id from construction_plan cp where 1=1 and cp.technician_id='").append(loginInfo.getStaffId()).append("'");
			quaCheckerNotice.append(") and post ='").append(PostTypeEnum.QUALITATIVE_CHECK_MEMBER.getValue()).append("')");
			//施工员
			StringBuffer construcitonNotice = new StringBuffer("(proj_id in(select cp.proj_id from construction_plan cp where 1=1 and cp.MANAGEMENT_QAE_ID='").append(loginInfo.getStaffId()).append("'");
			construcitonNotice.append(") and post ='").append(PostTypeEnum.CONSTRUCTION.getValue()).append("')");
			//资料员
			StringBuffer documentNotice = new StringBuffer("(proj_id in(select cp.proj_id from construction_plan cp where 1=1 and cp.DOCUMENTER_ID='").append(loginInfo.getStaffId()).append("'");
			documentNotice.append(") and post ='").append(PostTypeEnum.BUSINESSASSISTANT.getValue()).append("')");
			
			//班组
			StringBuffer testLeaderNotice = new StringBuffer("(proj_id in(select cp.proj_id from construction_plan cp where 1=1 and cp.TEST_LEADER_ID like '%,").append(loginInfo.getStaffId()).append(",%'");
			testLeaderNotice.append(") and post ='").append(PostTypeEnum.TEST_LEADER.getValue()).append("')");
			//焊工
			StringBuffer welderNotice = new StringBuffer("(proj_id in(select cp.proj_id from construction_plan cp where 1=1 and cp.WELDER_ID like '%,").append(loginInfo.getStaffId()).append(",%'");
			welderNotice.append(") and post ='").append(PostTypeEnum.WELDER.getValue()).append("')");
			
			//判断职务连接不同的sql
			StringBuffer sql= new StringBuffer();
			sql.append("(");
			boolean flag = false;
			//项目经理
			if(post.contains(PostTypeEnum.CU_PM.getValue())){
				flag = true;
				sql.append(cuPmSignNotice);
			}
			//安全员
			if(post.contains(PostTypeEnum.SAFTYOFFICER.getValue())){
				if(flag){
					sql.append("or");
				}
				sql.append(saftyofficerNotice);
				flag = true;
			}
			//质检员
			if(post.contains(PostTypeEnum.QUALITATIVE_CHECK_MEMBER.getValue())){
				if(flag){
					sql.append("or");
				}
				sql.append(quaCheckerNotice);
				flag = true;
			}
			//施工员
			if(post.contains(PostTypeEnum.CONSTRUCTION.getValue())){
				if(flag){
					sql.append("or");
				}
				sql.append(construcitonNotice);
				flag = true;
			}
			//资料员
			if(post.contains(PostTypeEnum.BUSINESSASSISTANT.getValue())){
				if(flag){
					sql.append("or");
				}
				sql.append(documentNotice);
				flag = true;
			}
			//班组长
			if(post.contains(PostTypeEnum.TEST_LEADER.getValue())){
				if(flag){
					sql.append("or");
				}
				sql.append(documentNotice);
				flag = true;
			}
			//焊工
			if(post.contains(PostTypeEnum.WELDER.getValue())){
				if(flag){
					sql.append("or");
				}
				sql.append(welderNotice);
				flag = true;
			}
			
			//非派工指定人员-根据所属分公司过滤
			if(post.contains(PostTypeEnum.CENERAL_ENGINEER.getValue())){
				if(flag){
					sql.append("or");
				}
				StringBuffer otherNotice = new StringBuffer("(proj_id in(select cp.proj_id from construction_plan cp where cp.cu_id ='").append(loginInfo.getDeptId()).append("'");
				if(StringUtil.isNotBlank(loginInfo.getBelongCorpId())){
					otherNotice.append(" and cp.corp_id in('");
					String [] belongCorpIds = loginInfo.getBelongCorpId().split(",");
					for(int i=1;i<belongCorpIds.length;i++){
						otherNotice.append(belongCorpIds[i]).append("','");
					}
					otherNotice.append(belongCorpIds[0]).append("') ");
				}
				otherNotice.append(") and post ='").append(PostTypeEnum.CENERAL_ENGINEER.getValue()).append("')");
				sql.append(otherNotice);
			}
			sql.append(")");
			criteria.add(Restrictions.sqlRestriction(sql.toString()));
		}
		return criteria;
	}
	/**
	 * 注释：优化施工单位、监理单位 人员签字通知查询
	 * @author liaoyq
	 * @param criteria 
	 * @param dataTypes 
	 * @createTime 2019年6月21日
	 * @return
	 *
	 */
	public Criteria bpFilterSignNotice(Criteria criteria, List<String> dataTypes) {
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		String postStr = loginInfo.getPost();
		if(StringUtil.isNotBlank(postStr)){
			
			String[] posts =postStr.split(",");
			boolean flag = false;
			List<String> otherPost = new ArrayList<String>();
			StringBuffer filterSql = new StringBuffer();
			for(String postId : posts){
				Post post = postDao.get("id", postId);
				if(post!=null){
					//计划表中指定的字段
					if(StringUtil.isNotBlank(post.getTableColumName())){
						if(flag){
							filterSql.append(" or ");
						}
						//字段名称
						//施工单位预算员
						if(PostTypeEnum.BUDGET_MEMBER.getValue().equals(","+post.getId()+",")){
							filterSql.append("(proj_id in(select cp.proj_id from SUB_BUDGET cp where 1=1 and cp.OPERATER_ID");
						}else{//其他指定职务
							filterSql.append("(proj_id in(select cp.proj_id from construction_plan cp where 1=1 and cp.").append(post.getTableColumName());
						}
						//班组、焊工可多选
						if(PostTypeEnum.TEST_LEADER.getValue().equals(","+post.getId()+",")
								||PostTypeEnum.TEST_LEADER.getValue().equals(","+post.getId()+",")){
							filterSql.append(" like '%,").append(loginInfo.getStaffId()).append(",%'");
						}else{
							filterSql.append(" ='").append(loginInfo.getStaffId()).append("'");
						}
						filterSql.append(") and post =',"+post.getId()+",')");
						flag = true;
					}else{
						otherPost.add(postId);
					}
				}else{
					otherPost.add(postId);
				}
			}
			//非指定人员的职务，根据所属分公司ID和多职务去查询
			if(otherPost!=null && otherPost.size()>0){
				if(flag){
					filterSql.append(" or ");
				}
				StringBuffer otherNotice = new StringBuffer("(proj_id in(select cp.proj_id from construction_plan cp where ");
				//施工单位或者监理单位
				if(UnitTypeEnum.CONSTRUCTION_UNIT.getValue().equals(loginInfo.getUnitType())){
					otherNotice.append("cp.cu_id ='").append(loginInfo.getDeptId()).append("'");
				}else if(UnitTypeEnum.SUPERVISION_UNIT.getValue().equals(loginInfo.getUnitType())){
					otherNotice.append("cp.su_id ='").append(loginInfo.getDeptId()).append("'");
				}
				//所属分公司
				if(StringUtil.isNotBlank(loginInfo.getBelongCorpId())){
					otherNotice.append(" and cp.corp_id in('");
					String [] belongCorpIds = loginInfo.getBelongCorpId().split(",");
					for(int i=1;i<belongCorpIds.length;i++){
						otherNotice.append(belongCorpIds[i]).append("','");
					}
					otherNotice.append(belongCorpIds[0]).append("') ");
				}
				//非派工职务
				otherNotice.append(") and post in('");
				for(int i=1;i<otherPost.size();i++){
					otherNotice.append(","+otherPost.get(i)+",").append("','");
				}
				otherNotice.append(otherPost.get(0)).append("') ");
				otherNotice.append(")");
				filterSql.append(otherNotice);
			}
			criteria.add(Restrictions.sqlRestriction("("+filterSql.toString()+")"));
		}
		return criteria;
	}
	
	public void corpQueryConditionFilter(LoginInfo loginInfo,Criteria criteria){
		StringBuffer hql = new StringBuffer();
		hql.append("proj_id in(select cp.proj_id from project cp where cp.corp_id='" ).append(loginInfo.getCorpId()).append("')");
		criteria.add(Restrictions.sqlRestriction(hql.toString()));
	}

	/**
	 * 按签字顺序查-用与保存的回调函数
	 * @author fuliwei
	 * @createTime 2018年1月21日
	 * @param
	 * @return
	 */
	@Override
	public List<SignNotice> queryByBusiIdAndSort(String busiId, String sortNo) {
		Criteria c = super.getCriteria();
		c.add(Restrictions.eq("status", SignStateEnum.ALREADY_SIGN.getValue()));
		// 业务单id
		if (StringUtils.isNotBlank(busiId)) {
			c.add(Restrictions.eq("businessOrderId", busiId));
			// 根据条件获取查询信息
		}

		//单据类型
		if (StringUtils.isNotBlank(sortNo)) {
			c.add(Restrictions.eq("sortNo", sortNo));
			// 根据条件获取查询信息
		}

		List<SignNotice> signlList = this.findByCriteria(c);
		return signlList;
	}

	@Override
	public void deleteByProjIdAndType(String projId, String dataType) {
		StringBuffer hql = new StringBuffer("delete from SignNotice where projId='")
			.append(projId).append("' and dataType='").append(dataType).append("'");
		this.executeHql(hql.toString());
	}

	@Override
	public void deleteByBsId (String BsId,String pcDesId){
		//如果是管沟开挖
	    StringBuffer businessOrderId = new StringBuffer();
		if("3".equals(pcDesId)){
		String hql="from GrooveRecord g where g.pcId='"+BsId+"'";
		List<GrooveRecord> list=this.findByHql(hql);
		if(list!=null&&list.size()>0){
			for (int i = 0; i < list.size(); i++) {
				businessOrderId.append(list.get(i).getGrId()).append(",");
			}
		}

		}

		//如果是pe管焊接
		if("6".equals(pcDesId)){
			String hql="from PepipeWelding p where p.pcId='"+BsId+"'";
			List<PepipeWelding> list=this.findByHql(hql);
			if(list!=null&&list.size()>0){
				for (int i = 0; i < list.size(); i++) {
					businessOrderId.append(list.get(i).getPeId()).append(",");
				}
			}
			}
		//如果是防腐记录
		if("7".equals(pcDesId)){
			String hql="from DerustingPreservative d where d.pcId='"+BsId+"'";
			List<DerustingPreservative> list=this.findByHql(hql);
			if(list!=null&&list.size()>0){
				for (int i = 0; i < list.size(); i++) {
					businessOrderId.append(list.get(i).getDpId()).append(",");
				}
			}
			}
		businessOrderId.append("'"+BsId+"'");
		String sql = "update  sign_notice set status =2  where business_order_id in "+"("+businessOrderId.toString()+")";
		super.executeSql(sql);
	}

	/**
	 * 通过业务单id将所有的报验审核通知置为无效
	 * @param businessOrderId
	 */
	@Override
	public void updateInspectionAudit(String businessOrderId) {
		String sql=" update sign_notice set status=2 where  business_order_id = '"+businessOrderId+"' and grade is not null";
		super.executeSql(sql);
	}

	/**
	 * 通过业务单id将当前的报验审核通知置为无效
	 * @param businessOrderId
	 * @param grade
	 */
	@Override
	public void updateInspectionThisAudit(String businessOrderId, String grade) {
		String sql=" update sign_notice set status='2' where  business_order_id = '"+businessOrderId+"' and grade ='"+grade+"'";
		super.executeSql(sql);
	}



	/**
	 * 按审核等级查询
	 * @param busiId
	 * @param grade
	 * @return
	 */
	@Override
	public List<SignNotice> queryByBusiIdAndGrade(String busiId, String grade) {
		Criteria c = super.getCriteria();
		c.add(Restrictions.eq("status", SignStateEnum.ALREADY_SIGN.getValue()));
		// 业务单id
		if (StringUtils.isNotBlank(busiId)) {
			c.add(Restrictions.eq("businessOrderId", busiId));
			// 根据条件获取查询信息
		}

		//单据类型
		if (StringUtils.isNotBlank(grade)) {
			c.add(Restrictions.eq("grade", grade));
			// 根据条件获取查询信息
		}

		List<SignNotice> signlList = this.findByCriteria(c);
		return signlList;
	}


	@Override
	public List<SignNotice> queryListByMultiplePost(String projId, String post, String dataType) {
		Criteria c = super.getCriteria();

		if (StringUtils.isNotBlank(projId))
			c.add(Restrictions.eq("projId", projId));

		if (StringUtils.isNotBlank(dataType))
			c.add(Restrictions.eq("dataType", dataType));


		if (StringUtils.isNotBlank(post)) {
			StringBuilder sql=new StringBuilder("(");
			String[] postArr=post.split(",");
			sql.append(" post ='").append(","+postArr[0]+",").append("'");
			for (int i=1;i<postArr.length;i++){
				sql.append("or post ='").append(","+postArr[i]+",").append("'");
			}
			sql.append(")");
			c.add(Restrictions.sqlRestriction(sql.toString()));
		}

		return this.findByCriteria(c);
	}



	@Override
	public List<SignNotice> querySignNoticeListBybusinessId(String busId) {
		Criteria c = super.getCriteria();
		if (StringUtils.isNotBlank(busId)) {
			c.add(Restrictions.eq("businessOrderId", busId));
			return this.findByCriteria(c);
		}else {
			return null;
		}


	}
	@Override
	public List<SignNotice> queryFirstByBusId(String busOrderId, String dataType) {
		String hql = " from SignNotice where sortNo =1 and businessOrderId=? and dataType=?";
		List<Object> params = new ArrayList<>();
		params.add(busOrderId);
		params.add(dataType);
		return this.findByHql(hql, params.toArray());
	}



	@Override
	public List<SignNotice> querysignNoticeList(String businessId, String projId, String dataType, String post, String status) {
		Criteria c = super.getCriteria();

		if (StringUtils.isNotBlank(businessId)) {
			c.add(Restrictions.eq("businessOrderId", businessId));
		}
		if (StringUtils.isNotBlank(projId)) {
			c.add(Restrictions.eq("projId", projId));
		}
		if (StringUtils.isNotBlank(dataType)){
			c.add(Restrictions.eq("dataType", dataType));
		}
		if (StringUtils.isNotBlank(post)){
			c.add(Restrictions.eq("post", post));
		}
		if (StringUtils.isNotBlank(status)){
			c.add(Restrictions.eq("status", status));
		}

		return this.findByCriteria(c);
	}


	@Override
	public void updateSignNotice(String sjsId, String projId, String dataType) {
		List<String> paramList=new ArrayList<>();
		StringBuilder sql=new StringBuilder();
		sql.append("UPDATE SIGN_NOTICE SET BUSINESS_ORDER_ID = ? WHERE  PROJ_ID = ? AND DATA_TYPE = ? ");
		paramList.add(sjsId);
		paramList.add(projId);
		paramList.add(dataType);
		this.executeSql(sql.toString(),paramList.toArray());
	}

	@Override
	public void updateFinishSignaturNotice(String businessOrderId, String dataType, List<String> finishPost) {
		List<String> paramList=new ArrayList<>();
		StringBuilder sql=new StringBuilder();
		sql.append("UPDATE SIGN_NOTICE SET STATUS = 2 WHERE  BUSINESS_ORDER_ID = ? AND DATA_TYPE = ? ");
		paramList.add(businessOrderId);
		paramList.add(dataType);

		if (finishPost!=null && finishPost.size()>0){
			sql.append(" AND POST IN(");
			for (int i = 0; i <finishPost.size() ; i++) {
				if (i!=finishPost.size()-1){
					sql.append(" ?, ");
				}else {
					sql.append(" ? ");
				}
				paramList.add(finishPost.get(i));
			}
			sql.append(")");
			this.executeSql(sql.toString(),paramList.toArray());
		}
	}

	/**
	 * 通过业务单ID将签字通知置为已办
	 * @param busId
	 * @throws Exception
	 */
	@Override
	public void deleteSignNoticeByBsId(String busId) throws Exception {
		// TODO Auto-generated method stub
		StringBuffer sql = new StringBuffer();
		sql.append("update sign_notice set status='2' where  business_order_id = '").append(busId).append("'");
		super.executeSql(sql.toString());
		
	}


	/**
	 * 查询计划相关人员消息
	 * @author fuliwei
	 * @date 2019/8/28
	 * @param
	 * @return
	 */
	@Override
	public List<Map<String, Object>> queryPlanNotice() {
		String sql ="select staff.staff_id STAFF_ID,staff.staff_name STAFF_NAME,staff.REGISTRATIONID REGISTRATIONID,aa.proj_no PROJ_NO,aa.sign_notice_id SIGN_NOTICE_ID,aa.proj_name PROJ_NAME from staff staff,(";
		sql+="select (";
		sql+=" CASE when notice.post =',13,' THEN cp.su_Cse_id";
		sql+=" when notice.post =',55,' THEN cp.su_Jgj_id";
		sql+=" when notice.post =',54,' THEN cp.BUILDER_ID";
		sql+=" when notice.post =',53,' THEN cp.cu_pm_id";
		sql+=" when notice.post =',61,' THEN cp.MANAGEMENT_QAE_ID";
		sql+=" when notice.post =',58,' THEN cp.SAFTY_OFFICER_ID";
		sql+=" when notice.post =',59,' THEN cp.TECHNICIAN_ID end";
		/*sql+=" when notice.post =',64,' THEN cp.TEST_LEADER_ID";
		sql+=" when notice.post =',63,' THEN cp.WELDER_ID end";*/
		sql+=" ) ";
		sql+=" as staff_id,notice.sign_notice_id sign_notice_id,project.PROJ_NO PROJ_NO,project.proj_name proj_name";
		sql+=" from sign_notice notice";
		sql+=" LEFT JOIN  project project on project.proj_id = notice.proj_id";
		sql+=" LEFT JOIN  construction_plan cp on cp.proj_id = notice.proj_id";
		sql+=" where notice.`STATUS`='1'";
		sql+=" and notice.post in(',13,',',55,',',54,',',53,',',61,',',58,',',59,',',64,',',63,') ";//职务是计划中存的职务id
		sql+=" and (notice.SEND_JPUSH_STATUS is null or notice.SEND_JPUSH_STATUS!='1') ) aa ";
		sql+="where staff.staff_id = aa.staff_id ";
		sql+="and (staff.REGISTRATIONID is not null and staff.REGISTRATIONID!='')";//设备id不为空

		List<Map<String, Object>> listBySql = this.findListBySql(sql, new Object[]{});
		return  listBySql;
	}
	/**
	 * 查询班组通知
	 * @author fuliwei
	 * @date 2019/8/28
	 */
	@Override
	public List<Map<String, Object>> queryWelderNotice() {
		String sql="select aa.SIGN_NOTICE_ID SIGN_NOTICE_ID,staff.staff_id STAFF_ID,staff.staff_name STAFF_NAME,aa.proj_no PROJ_NO,aa.SEND_JPUSH_STATUS SEND_JPUSH_STATUS,staff.REGISTRATIONID REGISTRATIONID";
		       sql +=" from (";
		       sql +=" select notice.post POST,cp.WELDER_ID WELDER_ID,notice.SIGN_NOTICE_ID SIGN_NOTICE_ID,notice.proj_id PROJ_ID,cp.proj_no PROJ_NO,notice.SEND_JPUSH_STATUS SEND_JPUSH_STATUS";
		       sql +=" from sign_notice notice";
		       sql +=" LEFT JOIN construction_plan cp on notice.proj_id = cp.proj_id ";
		       sql +=" where notice.post='"+PostTypeEnum.TEST_LEADER.getValue()+"' and notice.status='1' and cp.WELDER_ID is not null";
		       sql +=" AND (notice.SEND_JPUSH_STATUS is null or notice.SEND_JPUSH_STATUS != '1')";
		       sql +="  ) aa ";
		       sql +=" LEFT JOIN  staff staff on FIND_IN_SET(staff.STAFF_ID,aa.WELDER_ID)>0";//班组长用逗号隔开 发送多条
		       sql +=" where  (staff.REGISTRATIONID is not NULL and staff.REGISTRATIONID !='')";//设备id不为空

		List<Map<String, Object>> listBySql = this.findListBySql(sql, new Object[]{});
		return  listBySql;
	}



	/**
	 * 查询设计员签字通知
	 * @author fuliwei
	 * @date 2019/8/29
	 * @param
	 * @return
	 */
	@Override
	public List<Map<String, Object>> queryDesignerNotice() {

		String  sql="select staff.STAFF_ID,staff.STAFF_NAME,aa.SIGN_NOTICE_ID SIGN_NOTICE_ID, aa.proj_no PROJ_NO,aa.proj_name PROJ_NAME,staff.REGISTRATIONID REGISTRATIONID,aa.SEND_JPUSH_STATUS  SEND_JPUSH_STATUS";
				sql+=" from (";
				sql+=" select notice.SIGN_NOTICE_ID SIGN_NOTICE_ID,project.proj_No proj_No,project.designer_id designer_id,";
				sql+=" notice.SEND_JPUSH_STATUS SEND_JPUSH_STATUS,project.PROJ_NAME PROJ_NAME";
				sql+=" from sign_notice  notice";
				sql+=" LEFT JOIN project project on project.proj_id = notice.proj_id  ";
				sql+=" where notice.post='"+PostTypeEnum.DESIGNER.getValue()+"' and notice.status='1'";
				sql+=" and (notice.SEND_JPUSH_STATUS is null or notice.SEND_JPUSH_STATUS!='1')";
				sql+=" )aa ";
				sql+=" LEFT JOIN staff on staff.staff_id = aa.designer_id";
				sql+=" where aa.proj_no is not null";
		        sql+=" and (staff.REGISTRATIONID is not null and staff.REGISTRATIONID!='')";

		List<Map<String, Object>> listBySql = this.findListBySql(sql, new Object[]{});
		return  listBySql;
	}


	/**
	 * 查询联合验收相关人员通知
	 * @author fuliwei
	 * @date 2019/8/29
	 */
	@Override
	public List<Map<String, Object>> queryJointNotice(String post) {

		String sql="select aa.SIGN_NOTICE_ID SIGN_NOTICE_ID,aa.proj_no PROJ_NO,aa.proj_name PROJ_NAME,staff.staff_id STAFF_ID,";
		sql+=" staff.staff_name STAFF_NAME,staff.REGISTRATIONID REGISTRATIONID,aa.SEND_JPUSH_STATUS  SEND_JPUSH_STATUS,aa.menu_id MENU_ID from (";
		sql+=" select notice.SIGN_NOTICE_ID,project.PROJ_NO,project.corp_id,";
		sql+=" notice.SEND_JPUSH_STATUS SEND_JPUSH_STATUS,project.PROJ_NAME PROJ_NAME,smr.menu_id";
		sql+=" from sign_notice  notice";
		sql+=" LEFT JOIN Project project on notice.proj_id = project.proj_id";
		sql+=" LEFT JOIN sign_menu_relation smr ON smr.sign_type_id = notice.data_type";
		sql+=" where notice.POST='"+post+"' and notice.STATUS='1' and (notice.SEND_JPUSH_STATUS is null or notice.SEND_JPUSH_STATUS!='1') )aa ";
		sql+=" LEFT JOIN staff on staff.corp_id = aa.corp_id";
		sql+=" where staff.POST like '%"+post+"%'";
		sql+=" and (staff.REGISTRATIONID is not null and staff.REGISTRATIONID!='')";
		List<Map<String, Object>> listBySql = this.findListBySql(sql, new Object[]{});
		return  listBySql;
	}


	/**
	 * 查询总工程师签字-燃气公司总工
	 * @author fuliwei
	 * @date 2019/9/1
	 */
	@Override
	public List<Map<String, Object>> queryCeneralEngineerNotice() {

		String 	sql =" select result.SIGN_NOTICE_ID SIGN_NOTICE_ID,result.send_jpush_status SEND_JPUSH_STATUS,result.proj_no PROJ_NO";
		       	sql += ",result.data_type DATA_TYPE,smr.menu_id MENU_ID,result.proj_name PROJ_NAME, ";
		       	sql += " staff.staff_id STAFF_ID,staff.staff_name STAFF_NAME,staff.REGISTRATIONID REGISTRATIONID from";
		       	sql += "  (";
				sql += " select ";
				sql += " notice.SIGN_NOTICE_ID,notice.send_jpush_status,project.proj_no,project.PROJ_NAME,notice.post,project.corp_id,notice.data_type";
				sql += "  from sign_notice notice";
				sql += " LEFT JOIN project project on project.proj_id = notice.PROJ_ID";
				sql += " where notice.post='"+PostTypeEnum.CENERAL_ENGINEER.getValue()+"' and notice.status='1' AND ( notice.SEND_JPUSH_STATUS IS NULL OR notice.SEND_JPUSH_STATUS != '1') and project.proj_no is not null ";
				sql += " ) result";
				sql += " LEFT JOIN  staff staff on staff.corp_id=result.corp_id";
				sql += " LEFT JOIN  sign_menu_relation smr on smr.sign_type_id= result.data_type";
				sql += " where staff.post like '%"+PostTypeEnum.CENERAL_ENGINEER.getValue()+"%'";
				sql += " and staff.UNIT_TYPE='1' and staff.DEPT_ID!='1112'";//非设计院总工 暂时写死
				sql += "and (staff.REGISTRATIONID is not null and staff.REGISTRATIONID!='')";
		List<Map<String, Object>> listBySql = this.findListBySql(sql, new Object[]{});
		return  listBySql;
	}
	/**
	 * 施工单位总工
	 * @author fuliwei
	 * @date 2019/9/2
	 */
	@Override
	public List<Map<String, Object>> queryCuCeneralEngineerNotice() {

		String  sql = " select result.SIGN_NOTICE_ID SIGN_NOTICE_ID,result.send_jpush_status SEND_JPUSH_STATUS,staff.staff_id STAFF_ID,staff.staff_name STAFF_NAME,staff.REGISTRATIONID REGISTRATIONID,";
				sql += "result.proj_no PROJ_NO,staff.UNIT_TYPE unit_type,result.data_type DATA_TYPE,smr.menu_id MENU_ID,result.cu_id CU_ID from";
				sql += " (";
				sql += " select ";
				sql += " notice.SIGN_NOTICE_ID SIGN_NOTICE_ID,notice.send_jpush_status send_jpush_status,cp.proj_no proj_no,cp.PROJ_NAME PROJ_NAME,notice.post post,notice.data_type,cp.cu_id,project.corp_id";
				sql += "  from sign_notice notice ";
				sql += " LEFT JOIN project project on project.proj_id = notice.proj_id ";
				sql += " LEFT JOIN construction_plan cp on cp.proj_id = notice.PROJ_ID";
				sql += " where notice.post='"+PostTypeEnum.CENERAL_ENGINEER.getValue()+"' and notice.status='1' AND ( notice.SEND_JPUSH_STATUS IS NULL OR notice.SEND_JPUSH_STATUS != '1')";
				sql +="  and cp.proj_no is not null ";
				sql +=" )";
				sql +=" result";
				sql +=" LEFT JOIN  staff staff on staff.dept_id=result.cu_id";
				sql +=" LEFT JOIN  sign_menu_relation smr on smr.sign_type_id= result.data_type";
				sql +=" where FIND_IN_SET(result.corp_Id,staff.BELONG_CORP_ID)>0";
				sql +=" and staff.post like '%"+PostTypeEnum.CENERAL_ENGINEER.getValue()+"%'";
				sql +=" and staff.UNIT_TYPE='5' and staff.DEPT_ID!='1112'";
				sql +=" and (staff.REGISTRATIONID is not null and staff.REGISTRATIONID!='')";

		List<Map<String, Object>> listBySql = this.findListBySql(sql, new Object[]{});
		return  listBySql;
	}
	/**
	 * 查询集团组长签字
	 * @author fuliwei
	 * @date 2019/9/1
	 */
	@Override
	public List<Map<String, Object>> queryGroupLeaderNotice() {

		String  sql =" select result.sign_notice_id SIGN_NOTICE_ID,result.post POST,result.dept_id DEPT_ID,result.proj_no PROJ_NO,";
		        sql+=" staff.staff_id STAFF_ID ,staff.staff_name STAFF_NAME,staff.REGISTRATIONID REGISTRATIONID,result.send_jpush_status SEND_JPUSH_STATUS from  ";
				sql+=" (";
				sql+=" select notice.sign_notice_id,notice.post,";
				sql+=" project.DEPT_ID,project.project_type,project.CONTRIBUTION_MODE CONTRIBUTION_MODE,project.proj_no,notice.send_jpush_status ";
				sql+=" from sign_notice notice";
				sql+=" LEFT JOIN project project on project.proj_id = notice.proj_id";
				sql+=" where notice.post=',116,' and notice.status='1' and (notice.SEND_JPUSH_STATUS is null or notice.SEND_JPUSH_STATUS!='1')";
				sql+=" and project.proj_no is not null and project.corp_id='1101'";
				sql+=" ) result ";
				sql+=" LEFT JOIN staff staff on staff.DEPT_ID = result.dept_id";
				sql+=" where staff.post like '%,116,%'";
				sql+=" and (staff.REGISTRATIONID is not null and staff.REGISTRATIONID!='')";

		List<Map<String, Object>> listBySql = this.findListBySql(sql, new Object[]{});
		return  listBySql;
	}


	/**
	 * 查询分公司部长
	 * @author fuliwei
	 * @date 2019/9/1
	 */
	@Override
	public List<Map<String, Object>> queryMinisterNotice() {
		String  sql =" select result.SIGN_NOTICE_ID SIGN_NOTICE_ID,result.post POST,result.corp_id CORP_ID,result.proj_no PROJ_NO,";
		        sql+=" staff.staff_id STAFF_ID ,staff.STAFF_NAME STAFF_NAME,staff.REGISTRATIONID REGISTRATIONID,";
		       	sql +="result.send_jpush_status SEND_JPUSH_STATUS,result.data_type DATA_TYPE,result.MENU_ID MENU_ID  from  ";
				sql +="(";
				sql +="select notice.sign_notice_id,notice.post,project.proj_no,notice.send_jpush_status,project.corp_id,notice.data_type,smr.menu_id ";
				sql +=" from sign_notice notice";
				sql +=" LEFT JOIN project project on project.proj_id = notice.proj_id";
				sql +="  LEFT JOIN  sign_menu_relation smr on smr.sign_type_id= notice.data_type";
				sql +=" where notice.post=',116,' and notice.status='1' and (notice.SEND_JPUSH_STATUS is null or notice.SEND_JPUSH_STATUS!='1')";
				sql +=" and project.proj_no is not null and project.corp_id!='1101'";
				sql +=" ) result";
				sql +=" LEFT JOIN staff staff on staff.corp_id = result.corp_id";
				sql +=" where  staff.post like '%,14,%'";
				sql +=" and (staff.REGISTRATIONID is not null and staff.REGISTRATIONID!='')";


		List<Map<String, Object>> listBySql = this.findListBySql(sql, new Object[]{});
		return  listBySql;
	}

	@Override
	public void updateStatusByBusId(String status,
			String businessOrderId, String post, String dataType, String projId) {
		StringBuffer hql =new StringBuffer(" update SignNotice set status=? where businessOrderId=?");
		List<String> params = new ArrayList<String>();
		params.add(status);
		params.add(businessOrderId);
		if(StringUtil.isNotBlank(projId)){
			hql.append(" and projId =?");
			params.add(projId);
		}
		if(StringUtil.isNotBlank(post)){
			hql.append(" and post =?");
			params.add(post);
		}
		if(StringUtil.isNotBlank(dataType)){
			hql.append(" and dataType =?");
			params.add(dataType);
		}
		this.executeHql(hql.toString(), params.toArray());
	}

	@Override
	public void updateAlreadySignByprojId(String businessId, String projId,
			String dataType, String post, String status) {
		StringBuffer hql =new StringBuffer(" update SignNotice set status=? where projId=?");
		List<String> params = new ArrayList<String>();
		params.add(SignStateEnum.ALREADY_SIGN.getValue());
		params.add(projId);
		if(StringUtil.isNotBlank(businessId)){
			hql.append(" and businessOrderId =?");
			params.add(post);
		}
		if(StringUtil.isNotBlank(post)){
			hql.append(" and post =?");
			params.add(post);
		}
		if(StringUtil.isNotBlank(dataType)){
			hql.append(" and dataType =?");
			params.add(dataType);
		}
		if(StringUtil.isNotBlank(status)){
			hql.append(" and status =?");
			params.add(status);
		}
		this.executeHql(hql.toString(), params.toArray());
	}
}






