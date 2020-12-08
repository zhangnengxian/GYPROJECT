package cc.dfsoft.project.biz.base.constructmanage.dao.impl;

import java.util.ArrayList;
import java.util.Arrays;
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
import cc.dfsoft.project.biz.base.constructmanage.dao.BusinessCommunicationDao;
import cc.dfsoft.project.biz.base.constructmanage.dto.BusinessCommunicationReq;
import cc.dfsoft.project.biz.base.constructmanage.entity.BusinessCommunication;
import cc.dfsoft.project.biz.base.constructmanage.enums.BcStatusEnum;
import cc.dfsoft.project.biz.base.design.enums.UnitTypeEnum;
import cc.dfsoft.project.biz.base.project.dao.ProjectDao;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.biz.sys.dept.enums.PostTypeEnum;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.constant.DataFilerSetUpDto;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;
import cc.dfsoft.uexpress.common.util.ResultUtil;
import org.springframework.test.context.jdbc.Sql;

@Repository
public class BusinessCommunicationDaoImpl extends NewBaseDAO<BusinessCommunication, String> implements BusinessCommunicationDao {
	
	@Resource
	BusinessPartnersDao businessPartnersDao;
	@Resource
	ProjectDao projectDao;
	
	@Override
	public Map<String, Object> queryBusinessCommunication(BusinessCommunicationReq businessCommunicationReq) {
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		String staffId = loginInfo.getStaffId();
		Criteria c = super.getCriteria();
		 //工程id
		 if(StringUtils.isNotBlank(businessCommunicationReq.getProjId())){
			 c.add(Restrictions.eq("projId",businessCommunicationReq.getProjId()));
		 }
		 //通知细类
		 if(StringUtils.isNotBlank(businessCommunicationReq.getBcTypeDetail())){
			 c.add(Restrictions.eq("bcTypeDetail",businessCommunicationReq.getBcTypeDetail()));
		 }
		 //通知状态
		 if(StringUtils.isNotBlank(businessCommunicationReq.getBcStatus())){
			 c.add(Restrictions.eq("bcStatus",businessCommunicationReq.getBcStatus()));
		 }
		 StringBuffer sql = new StringBuffer("");
		 //sql = sql.append(staffId).append("' or BC_RECIPIENT_ID='").append(staffId).append("')");
		//如果是无损探伤需要场代表和施工员看到
		 String post=loginInfo.getPost();
		 String [] postArray=post.split(",");
		BusinessPartners bp =  businessPartnersDao.get(loginInfo.getDeptId());
		if(StringUtils.isNotBlank(businessCommunicationReq.getBcTypeDetail()) && "2011".equals(businessCommunicationReq.getBcTypeDetail())){
			if(bp!=null && UnitTypeEnum.CONSTRUCTION_UNIT.getValue().equals(bp.getUnitType()) ){
				//施工单位
				if(post.contains(PostTypeEnum.CU_PM.getValue())||
					post.contains(PostTypeEnum.SAFTYOFFICER.getValue())||
					post.contains(PostTypeEnum.CONSTRUCTION.getValue())||
					post.contains(PostTypeEnum.MANAGEMENTWACF.getValue())||
					post.contains(PostTypeEnum.QUALITATIVE_CHECK_MEMBER.getValue())||
					post.contains(PostTypeEnum.TEST_LEADER.getValue())||
					post.contains(PostTypeEnum.WELDER.getValue())){
					 
					sql.append("proj_id in(select cp.proj_id from construction_plan cp where 1=1 and cp.CU_PM_ID='").append(loginInfo.getStaffId()).append("'");
					sql.append(" or cp.SAFTY_OFFICER_ID='").append(loginInfo.getStaffId()).append("'");
					sql.append(" or cp.MANAGEMENT_QAE_ID='").append(loginInfo.getStaffId()).append("'");
					sql.append(" or cp.management_wacf_id='").append(loginInfo.getStaffId()).append("'");
					sql.append(" or cp.technician_id='").append(loginInfo.getStaffId()).append("'");
					sql.append(" or cp.test_leader_id like'%,").append(loginInfo.getStaffId()).append(",%'");
					sql.append(" or cp.welder_id like'%,").append(loginInfo.getStaffId()).append(",%'");
					sql.append(")");
					c.add(Restrictions.sqlRestriction(sql.toString()));
				 }else{
					sql.append("proj_id in(select cp.proj_id from construction_plan cp where cp.cu_id='").append(loginInfo.getDeptId()).append("')");
					c.add(Restrictions.sqlRestriction(sql.toString()));
				 }
			}else if(bp!=null &&  UnitTypeEnum.SUPERVISION_UNIT.getValue().equals(bp.getUnitType())){
				sql.append("proj_id in(select cp.proj_id from construction_plan cp where cp.su_id='").append(loginInfo.getDeptId()).append("'");
				 if(postArray.length>0){
						 if(post.contains(PostTypeEnum.SUJGJ.getValue())){
							 //现场监理
							// sql = new StringBuffer("proj_id in(select cp.proj_id from construction_plan cp where cp.SU_JGJ_ID='").append(loginInfo.getStaffId()).append("')");
							 sql.append("or cp.SU_JGJ_ID = '").append(loginInfo.getStaffId()).append("'");
						 } 
						 if(post.contains(PostTypeEnum.PROFESSIONAL_SUPERVISION.getValue())){
							 //专业监理师
							 //sql = new StringBuffer("proj_id in(select cp.proj_id from construction_plan cp where cp.SU_PROFESSIONAL_ID='").append(loginInfo.getStaffId()).append("')");
							  sql.append("or cp.SU_PROFESSIONAL_ID = '").append(loginInfo.getStaffId()).append("'");
						 } 
						 if(post.contains(PostTypeEnum.SUCSE_REPRESENTATIVE.getValue())){
							 //总监代表
							// sql = new StringBuffer("proj_id in(select cp.proj_id from construction_plan cp where cp.SU_PROFESSIONAL_ID='").append(loginInfo.getStaffId()).append("')");
							 sql.append("or cp.SU_PROFESSIONAL_ID = '").append(loginInfo.getStaffId()).append("'");
						 }
						 if(post.contains(PostTypeEnum.SUCSE.getValue())){
							// sql = new StringBuffer("proj_id in(select cp.proj_id from construction_plan cp where cp.SU_CSE_ID='").append(loginInfo.getStaffId()).append("')");
							 sql.append("or cp.SU_CSE_ID = '").append(loginInfo.getStaffId()).append("'");
							
						 }
					 }
				 sql.append(")");
				 c.add(Restrictions.sqlRestriction(sql.toString()));
			}else if(post.contains(PostTypeEnum.BUILDER.getValue())){
				if(!post.contains(PostTypeEnum.MARKETING_CENTER_LEADER_DEPUTY.getValue())
						 && !post.contains(PostTypeEnum.MODIFICATION_GROUP_LEADER.getValue())
						 && !post.contains(PostTypeEnum.TRUNK_GROUP_LEADER.getValue())
						 && !post.contains(PostTypeEnum.DEPUTY_LEADER.getValue())){
					 sql.append(" proj_id in(select cp.proj_id from CONSTRUCTION_PLAN cp where cp.BUILDER_ID ='").append(staffId).append("')");
				 }
				c.add(Restrictions.sqlRestriction(sql.toString()));
			}
			c.add(Restrictions.ne("bcStatus", BcStatusEnum.NO_PUSHED.getValue()));
		}else{
			//总监需要看到无损检测委托单
			//预结算组预结算员可看到无损检测委托单
			String sqlFilter="";
			if(bp!=null && postArray.length>0 &&  (UnitTypeEnum.SUPERVISION_UNIT.getValue().equals(bp.getUnitType()) || post.contains(PostTypeEnum.BUDGET_MEMBER.getValue()))){
				for(int i=0;i<postArray.length;i++){
					if(post.contains(PostTypeEnum.SUCSE.getValue())){
						//无损检测单
						sqlFilter += " or BC_TYPE_DETAIL='2011'";
						break;
					} 
				}
			}
			// 六盘水加部长，委托负责人需部长签字
			if(bp ==null && post.contains(PostTypeEnum.MINISTER.getValue())){
				sqlFilter += " or BC_TYPE_DETAIL='2011'";
			}
			sql.append("(BC_INITIATOR_ID='");
			//增加推送状态-接收人不可查看未推送的
			sql = sql.append(staffId).append("' or (BC_RECIPIENT_ID='").append(staffId).append("' and BC_STATUS!='0')"+sqlFilter+")");
			c.add(Restrictions.sqlRestriction(sql.toString()));
			
		}
		
		List<DataFilerSetUpDto> list = Constants.getDataFilterMapByKey(loginInfo.getCorpId()+"_"+"120213");  //根据ID查找
		//根据发起人单位类型查找业务沟通记录----判断职务是否是部长
		 if(loginInfo.getPost().contains(PostTypeEnum.VICE_MINISTER.getValue()) && list !=null && list.size() > 0){
			 StringBuffer sqll = new StringBuffer( "1=1 or (UNITTYPE_OF_INITIATOR = '");
			 sqll.append(loginInfo.getUnitType()).append("'").append(list.get(0).getSupSql()).append(")");
			 c.add(Restrictions.sqlRestriction(sqll.toString()));
		 }
		 
		 //通知日期倒序
		 c.addOrder(Order.desc("noticeDate"));
		 // 数据库根据条件过滤记录数
		 int filterCount = this.countByCriteria(c);
		 // 根据条件获取查询信息
		 List<BusinessCommunication> businessCommunicationReqList = this.findBySortCriteria(c, businessCommunicationReq);
		 // 返回结果
		 return ResultUtil.pageResult( filterCount, businessCommunicationReq.getDraw(),businessCommunicationReqList);
	}
	
	/**
	 * 查询业务通知单列表
	 * @author fuliwei
	 * @createTime 2017年9月21日
	 * @param 
	 * @return
	 */
	@Override
	public List<BusinessCommunication> queryBusinessCommunicationList(
			BusinessCommunicationReq businessCommunicationReq) {
		Criteria c = super.getCriteria();
		
		//接收人id
		if(StringUtils.isNotBlank(businessCommunicationReq.getBcRecipientId())){
			c.add(Restrictions.eq("bcRecipientId", businessCommunicationReq.getBcRecipientId()));
		}
		
		
		//业务通知状态列表显示1：待通知，2：待回复，3：已回复
		if(StringUtils.isNotBlank(businessCommunicationReq.getBcStatus())){
			c.add(Restrictions.eq("bcStatus", businessCommunicationReq.getBcStatus()));
		}
		
		List<BusinessCommunication> list =this.findByCriteria(c);
		return list;
	}

	@Override
	public void updateVersionByBcId(BusinessCommunication bc) {
		String hql = "update BusinessCommunication set version="+bc.getVersion()+" where bcId='"+bc.getBcId()+"'";
		super.executeHql(hql);
		
	}

	@Override
	public List<BusinessCommunication> queryByProjIdAndTypeDetail(
			String projId, String bcTypeDetail) {
		Criteria c = super.getCriteria();
		//projId
		if(StringUtils.isNotBlank(projId)){
			c.add(Restrictions.eq("projId", projId));
		}
		//业务沟通细类
		if(StringUtils.isNotBlank(bcTypeDetail)){
			c.add(Restrictions.eq("bcTypeDetail", bcTypeDetail));
		}
		
		return this.findByCriteria(c);
	}



	@Override
	public void updateBuscomNotice(String projId, String origsuJgjId, String currSuJgjId,String currSuJgj) {


		StringBuilder updateSql=new StringBuilder().append("UPDATE BUSINESS_COMMUNICATION SET BC_INITIATOR_ID= ? ,BC_INITIATOR_NAME= ? WHERE PROJ_ID= ? AND BC_INITIATOR_ID= ?  AND BC_STATUS IN(0,1)");

		StringBuilder upSql=new StringBuilder().append("UPDATE BUSINESS_COMMUNICATION SET BC_RECIPIENT_ID= ? ,BC_RECIPIENT_NAME = ? WHERE PROJ_ID= ? AND BC_RECIPIENT_ID= ?  AND BC_STATUS IN(0,1) ");

		List<Object> paramList=new ArrayList<>();
		paramList.add(currSuJgjId);
		paramList.add(currSuJgj);
		paramList.add(projId);
		paramList.add(origsuJgjId);

		this.executeSql(updateSql.toString(),paramList.toArray());

		this.executeSql(upSql.toString(),paramList.toArray());

	}

	/**
	 * 查询业务沟通通知
	 * @author fuliwei
	 * @date 2019/8/28
	 * @param
	 * @return
	 */
	@Override
	public List<Map<String, Object>> queryBcNotice() {
		String sql ="select bcc.BC_ID BC_ID,bcc.proj_no PROJ_NO,bcc.proj_name PROJ_NAME,bcc.bc_recipient_id BC_RECIPIENT_ID,staff.REGISTRATIONID REGISTRATIONID,staff.staff_name STAFF_NAME,staff.STAFF_ID STAFF_ID";
		       sql +=" from business_communication bcc,staff staff";
		       sql +=" where bcc.bc_recipient_id =  staff.STAFF_ID";
		       sql +=" and bcc.bc_status='"+BcStatusEnum.TO_REPLY.getValue()+"'";			//待回复
		       sql +=" AND (bcc.SEND_JPUSH_STATUS is null or bcc.SEND_JPUSH_STATUS != '1')";//未推送
		       sql +=" and (staff.REGISTRATIONID is not NULL and staff.REGISTRATIONID !='')";//设备不为空
		       sql +=" and (bcc.bc_recipient_id is not null and bcc.bc_recipient_id !='')";//接收人不为空
		List<Map<String, Object>> listBySql = this.findListBySql(sql, new Object[]{});
		return  listBySql;
	}

}
