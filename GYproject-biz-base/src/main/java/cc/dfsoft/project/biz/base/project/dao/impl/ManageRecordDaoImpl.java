package cc.dfsoft.project.biz.base.project.dao.impl;

import cc.dfsoft.project.biz.base.project.dao.ManageRecordDao;
import cc.dfsoft.project.biz.base.project.dto.ManageRecordQueryReq;
import cc.dfsoft.project.biz.base.project.entity.ManageRecord;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;
import cc.dfsoft.uexpress.common.util.MapUtils;
import cc.dfsoft.uexpress.common.util.ResultUtil;
import cc.dfsoft.uexpress.common.util.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 管理记录daoImpl
 * @author pengtt
 * @createTime 2016-06-27
 *
 */
@Repository
public class ManageRecordDaoImpl extends NewBaseDAO<ManageRecord, String> implements ManageRecordDao {

	@Override
	public Map<String, Object> queryManageRecord(ManageRecordQueryReq manageRecordQueryReq) {
		
		 Criteria c = super.getCriteria();
		
		 //工程编号
		 if(StringUtils.isNotBlank(manageRecordQueryReq.getProjNo())){
			 c.add(Restrictions.eq("projNo",manageRecordQueryReq.getProjNo()));
		 }
		 //工程id
		 if(StringUtils.isNotBlank(manageRecordQueryReq.getProjId())){
			 c.add(Restrictions.eq("projId",manageRecordQueryReq.getProjId()));
		 }
		 //步骤id
		 if(StringUtils.isNotBlank(manageRecordQueryReq.getStepId()) && !"undefined".equals(manageRecordQueryReq.getStepId())){
			 c.add(Restrictions.eq("stepId", manageRecordQueryReq.getStepId()));
		 }
		 //业务id
		 if(StringUtils.isNotBlank(manageRecordQueryReq.getBusinessOrderId())){
			 c.add(Restrictions.eq("businessOrderId", manageRecordQueryReq.getBusinessOrderId()));
		 }/*else{
			 c.add(Restrictions.eq("businessOrderId","-1"));//如果businessOrderId为空，令其等于-1，不进行查询
		 }*/
		 if(StringUtil.isNotBlank(manageRecordQueryReq.getLevel()) && Constants.FAIL_CODE.contains(manageRecordQueryReq.getLevel())){
			 //审核级别“-1”则表示查询审核级别不能为空的
			 c.add(Restrictions.isNotNull ("mrAuditLevel"));
		 }
		 //若无排序属性，则按审核时间降序
		 if(StringUtils.isBlank(manageRecordQueryReq.getSortName())){
			 manageRecordQueryReq.setSortName("mrTime");
			 manageRecordQueryReq.setSort("desc");
		 }
		 // 数据库根据条件过滤记录数
		 int filterCount = this.countByCriteria(c);

		 // 根据条件获取查询信息
		 List<ManageRecord> manageRecordList = this.findBySortCriteria(c, manageRecordQueryReq);
		
		 // 返回结果
		 return ResultUtil.pageResult(filterCount, manageRecordQueryReq.getDraw(),manageRecordList);
	}

	@Override
	public List<ManageRecord> findByProjId(String projId) {
		StringBuffer hql = new StringBuffer();
		hql.append("from ManageRecord where 1=1");
		if(StringUtils.isNotBlank(projId)){
			hql.append(" and projId = '").append(projId).append("'");
		}
		//审核时间 降序查询
		hql.append(" order by mrTime asc");
		List<ManageRecord> result = super.findByHql(hql.toString());
		return result;
	}
	
	/**
	 * 通过StepId和projId查询 
	 * @author
	 * @createTime 2016-7-5
	 * @param
	 * @return
	 */
	@Override
	public List<ManageRecord> findByStepIdProjId(String projId,String stepId) {
		StringBuffer hql = new StringBuffer();
		hql.append("from ManageRecord where 1=1");
		if(StringUtils.isNotBlank(projId)){
			hql.append(" and projId = '").append(projId).append("'");
		}
		if(StringUtils.isNotBlank(stepId)){
			hql.append(" and stepId = '").append(stepId).append("'");
		}
		hql.append(" order by mrTime asc");
		List<ManageRecord> result = super.findByHql(hql.toString());
		return result;
	}
	
	
	
	
	@Override
	public List<Map<String, Object>> getManageRecordByTime(String projStatus){
		//oracle
		/*String sql="select proj_id,mr_time as operate_time from ("
				   + "select t.*,row_number() over(partition by proj_id order by mr_time desc) row_number "
				   + "from manage_record t where t.proj_id in("
				   + "select b.proj_id from project b where b.proj_status_id='"+projStatus+"'"
				   + ")) a "
				   + "where a.row_number=1";*/
		//mysql
		String sql="select proj_id as PROJ_ID,mr_time as OPERATE_TIME from ("
				+ "select heyf_tmp.*,if(@pdept=heyf_tmp.proj_id,@rank\\:=@rank+1,@rank\\:=1) as ROW_NUMBER,"
				+ " @pdept\\:=heyf_tmp.proj_id  from ( select proj_id,mr_time "
				+ "from manage_record t where t.proj_id in("
				+ "select b.proj_id from project b where  b.proj_status_id='"+projStatus+"'"
				+ ") order by proj_id asc ,mr_time desc ) heyf_tmp , (select @rownum\\:=0 , @pdept\\:= null ,@rank\\:=0) rn "
				+ ") a where a.ROW_NUMBER=1";
		List<Map<String, Object>> list=this.findListBySql(sql);
		return list;
		
	}

	@Override
	public List<ManageRecord> findByStepIdBusId(String busId, String stepId, String auditResult) {
		StringBuffer hql = new StringBuffer();
		hql.append("from ManageRecord where 1=1");
		if(StringUtils.isNotBlank(busId)){
			hql.append(" and businessOrderId = '").append(busId).append("'");
		}
		if(StringUtils.isNotBlank(stepId)){
			hql.append(" and stepId = '").append(stepId).append("'");
		}
		if(StringUtils.isNotBlank(auditResult)){
			hql.append(" and mrResult = '").append(auditResult).append("'");
		}
		
		hql.append("and flag is null order by mrTime asc");
		List<ManageRecord> result = super.findByHql(hql.toString());
		return result;
		
	}
	/**
	 * 
	 * 注释：查询审核通过的数据，有重复审核数据，导致审核按钮不能点击，过滤时进行分组
	 * @author liaoyq
	 * @createTime 2019年5月22日
	 * @param busId
	 * @param stepId
	 * @param auditResult
	 * @return
	 *
	 */
	@Override
	public List<ManageRecord> findByStepIdBusIdIsPass(String busId, String stepId, String auditResult) {
		StringBuffer hql = new StringBuffer();
		hql.append("from ManageRecord where 1=1");
		if(StringUtils.isNotBlank(busId)){
			hql.append(" and businessOrderId = '").append(busId).append("'");
		}
		if(StringUtils.isNotBlank(stepId)){
			hql.append(" and stepId = '").append(stepId).append("'");
		}
		if(StringUtils.isNotBlank(auditResult)){
			hql.append(" and mrResult = '").append(auditResult).append("'");
		}
		
		hql.append("and flag is null  GROUP BY mrAuditLevel,flag order by mrTime asc");
		List<ManageRecord> result = super.findByHql(hql.toString());
		return result;
		
	}

	@Override
	public List<ManageRecord> findByStepIdProjId(String projId, String stepId, String auditResult) {

		StringBuffer hql = new StringBuffer();
		hql.append("from ManageRecord where 1=1");
		if(StringUtils.isNotBlank(projId)){
			hql.append(" and projId = '").append(projId).append("'");
		}
		if(StringUtils.isNotBlank(stepId)){
			hql.append(" and stepId = '").append(stepId).append("'");
		}
		if(StringUtils.isNotBlank(auditResult)){
			hql.append(" and mrResult = '").append(auditResult).append("'");
		}

		hql.append("and flag is null  order by mrTime asc");
		List<ManageRecord> result = super.findByHql(hql.toString());
		return result;

	}
	/**
	 * 查询审核通过的数据，有重复审核数据，导致审核按钮不能点击，过滤时进行分组
	 * @author liaoyq
	 * @createTime 2019-4-2
	 * @param projId
	 * @param stepId
	 * @param auditResult
	 * @return
	 */
	@Override
	public List<ManageRecord> findByStepIdProjIdIsPass(String projId, String stepId, String auditResult) {

		StringBuffer hql = new StringBuffer();
		hql.append("from ManageRecord where 1=1");
		if(StringUtils.isNotBlank(projId)){
			hql.append(" and projId = '").append(projId).append("'");
		}
		if(StringUtils.isNotBlank(stepId)){
			hql.append(" and stepId = '").append(stepId).append("'");
		}
		if(StringUtils.isNotBlank(auditResult)){
			hql.append(" and mrResult = '").append(auditResult).append("'");
		}

		hql.append("and flag is null  GROUP BY mrAuditLevel,flag order by mrTime asc");
		List<ManageRecord> result = super.findByHql(hql.toString());
		return result;

	}
	@Override
	public void batUpdateHistoryRecord(String projId, String stepId) {
		StringBuffer hql = new StringBuffer();
		hql.append("from ManageRecord where 1=1");
		if(StringUtils.isNotBlank(projId)){
			hql.append(" and projId = '").append(projId).append("'");
		}
		if(StringUtils.isNotBlank(stepId)){
			hql.append(" and stepId = '").append(stepId).append("'");
		}
		List<ManageRecord> result = super.findByHql(hql.toString());
		if(result!=null && result.size()>0){
			for(int i=0;i<result.size();i++){
				ManageRecord mr = result.get(i);
				mr.setFlag("0"); // 0 为作废
				super.update(mr);
			}
		}
	}

	@Override
	public ManageRecord queryAuditInformation(String projId, String StepId) {
		StringBuffer hql = new StringBuffer();
		hql.append("from ManageRecord where 1=1");
		if(StringUtils.isNotBlank(projId)){
			hql.append(" and projId = '").append(projId).append("'");
		}
		if(StringUtils.isNotBlank(StepId)){
			//组合stepIds
			if(StepId.contains(Constants.SPLIT_CODE)){
				String[] stepIds = StepId.split(Constants.SPLIT_CODE);
				hql.append(" and stepId in ('");
				for(int i=1;i<stepIds.length;i++){
					 hql.append(stepIds[i]).append("',").append("'");
				 }
				 hql.append(stepIds[0]).append("')");
			}else{
				hql.append(" and stepId = '").append(StepId).append("'");
			}
		}
		hql.append("and flag = '0' and mrResult = '0' order by mrTime desc");
		List<ManageRecord> result = super.findByHql(hql.toString());
		if(result.size()>0){
			return result.get(0);
		}
		return null;
	}

	@Override
	public ManageRecord queryAuditInsInformation(String businessOrderId) {
		StringBuffer hql = new StringBuffer();
		hql.append("from ManageRecord where 1=1");
		if(StringUtils.isNotBlank(businessOrderId)){
			hql.append(" and businessOrderId = '").append(businessOrderId).append("'");
		}
		hql.append("and flag = '0' and mrResult = '0' order by mrTime desc");
		List<ManageRecord> result = super.findByHql(hql.toString());
		if(result.size()>0){
			return result.get(0);
		}
		return null;
	}


	/**
	 * 通过业务单Id查询管理记录 以时间降序
	 * @param businessOrderId
	 * @param stepId
	 * @param auditResult
	 * @return
	 */
	@Override
	public List<ManageRecord> findByStepIdBusinessOrderId(String businessOrderId, String stepId, String auditResult) {
		StringBuffer hql = new StringBuffer();
		hql.append("from ManageRecord where 1=1");
		if(StringUtils.isNotBlank(businessOrderId)){
			hql.append(" and businessOrderId = '").append(businessOrderId).append("'");
		}
		if(StringUtils.isNotBlank(stepId)){
			hql.append(" and stepId = '").append(stepId).append("'");
		}
		if(StringUtils.isNotBlank(auditResult)){
			hql.append(" and mrResult = '").append(auditResult).append("'");
		}
		
		hql.append("and flag is null order by mrTime asc");
		List<ManageRecord> result = super.findByHql(hql.toString());
		return result;
	}

	@Override
	public void batUpdateHistoryRecordByBoId(String businessorderId, String stepId) {
		String sql = "update MANAGE_RECORD set flag = '0' where 1=1 ";
			sql += "and BUSINESS_ORDER_ID = '"+businessorderId+"'";
			if(StringUtils.isNotBlank(stepId)){
				sql += "and STEP_ID = '"+stepId+"'";
			}
			this.executeSql(sql);
	
	}

	/**
	 * 根据工程id、步骤id、审核人id查询审核通过的信息
	 * @author liaoyq
	 * @createTime 2017年10月25日
	 * @param projId
	 * @param stepId
	 * @param mrCsr
	 */
	@Override
	public ManageRecord findByMrCsrId(String projId, String stepId, String mrCsr) {
		StringBuffer hql = new StringBuffer();
		hql.append("from ManageRecord where 1=1");
		if(StringUtils.isNotBlank(projId)){
			hql.append(" and projId = '").append(projId).append("'");
		}
		if(StringUtils.isNotBlank(stepId)){
			hql.append(" and stepId = '").append(stepId).append("'");
		}
		if(StringUtils.isNotBlank(mrCsr)){
			hql.append(" and mrCsr = '").append(mrCsr).append("'");
		}
		hql.append(" and mrResult='1' and flag is null");
		List<ManageRecord> result = super.findByHql(hql.toString());
		if(result!=null && result.size()>0){
			return result.get(0);
		}
		return null;
	}

	/**
	 * 根据工程id、步骤id、审核人级别查询审核通过的信息
	 * @author liaoyq
	 * @createTime 2017年10月25日
	 * @param projId
	 * @param stepId
	 * @param mrCsr
	 */
	@Override
	public ManageRecord findFirstMrCsr(String projId, String stepId,
			String level) {
		StringBuffer hql = new StringBuffer();
		hql.append("from ManageRecord where 1=1");
		if(StringUtils.isNotBlank(projId)){
			hql.append(" and projId = '").append(projId).append("'");
		}
		if(StringUtils.isNotBlank(stepId)){
			hql.append(" and stepId = '").append(stepId).append("'");
		}
		if(StringUtils.isNotBlank(level)){
			hql.append(" and mrAuditLevel = '").append(level).append("'");
		}
		hql.append(" and mrResult='1' and flag is null");
		List<ManageRecord> result = super.findByHql(hql.toString());
		if(result!=null && result.size()>0){
			return result.get(0);
		}
		return null;
	}
	
	/**
	 * 查询业务单审核信息结果
	 * @author fuliwei
	 * @createTime 2017年12月1日
	 * @param 
	 * @return
	 */
	@Override
	public ManageRecord queryBusAuditInformation(String businessOrderId, String StepId) {
		StringBuffer hql = new StringBuffer();
		hql.append("from ManageRecord where 1=1");
		if(StringUtils.isNotBlank(businessOrderId)){
			hql.append(" and businessOrderId = '").append(businessOrderId).append("'");
		}
		if(StringUtils.isNotBlank(StepId)){
			//组合stepIds
			if(StepId.contains(Constants.SPLIT_CODE)){
				String[] stepIds = StepId.split(Constants.SPLIT_CODE);
				hql.append(" and stepId in ('");
				for(int i=1;i<stepIds.length;i++){
					 hql.append(stepIds[i]).append("',").append("'");
				 }
				 hql.append(stepIds[0]).append("')");
			}else{
				hql.append(" and stepId = '").append(StepId).append("'");
			}
		}
		hql.append("and flag = '0' and mrResult = '0' order by mrTime desc");
		List<ManageRecord> result = super.findByHql(hql.toString());
		if(result.size()>0){
			return result.get(0);
		}
		return null;
	}

	/**
	 * 保存审核时判断当前是否已审核，已审核则终止，未审核则保存
	 */
	@Override
	public String saveManageRecordNew(ManageRecord manageRecord) {
		if(manageRecord!=null){
			StringBuffer sql = new StringBuffer("select count(MR_ID) from manage_record where PROJ_ID=? and BUSINESS_ORDER_ID=? and MR_AUDIT_LEVEL=? ");
			List<Object> params = new ArrayList<>();
			params.add(manageRecord.getProjId());
			params.add(manageRecord.getBusinessOrderId());
			params.add(manageRecord.getMrAuditLevel());
			if(StringUtil.isNotBlank(manageRecord.getStepId())){
				sql.append(" and STEP_ID=? ");
				params.add(manageRecord.getStepId());
				
			}
			if(StringUtil.isNotBlank(manageRecord.getDocTypeId())){
				sql.append(" and DOC_TYPE_ID=? ");
				params.add(manageRecord.getDocTypeId());
				
			}
			sql.append("and MR_RESULT=1 and flag is null");
			Integer count = this.getCountBySql(sql.toString(), params.toArray());
			//已有审核
			if(count>0){
				return Constants.FAIL_CODE;
			}
			this.save(manageRecord);
			return Constants.OPERATE_RESULT_SUCCESS;
		}
		return Constants.OPERATE_RESULT_FAILURE;
	}
	@Override
	public void saveManageRecord(ManageRecord manageRecord) {
		//先修改当前审核下的历史数据，再保存审核历史
		if(manageRecord!=null 
				&& StringUtil.isNotBlank(manageRecord.getMrAuditLevel()) 
				&& StringUtil.isNotBlank(manageRecord.getStepId()) 
				&& StringUtil.isNotBlank(manageRecord.getDocTypeId())){
			StringBuffer sql = new StringBuffer("update MANAGE_RECORD set flag = 0 where PROJ_ID='"+manageRecord.getProjId()+"'");
			sql.append(" and MR_AUDIT_LEVEL='"+manageRecord.getMrAuditLevel()+"' and DOC_TYPE_ID='"+manageRecord.getDocTypeId()+"' and STEP_ID='"+manageRecord.getStepId()+"'");
		    this.executeSql(sql.toString());
			this.save(manageRecord);
		}else {
			this.save(manageRecord);
		}
	}

	@Override
	public ManageRecord findEndMrCsr(ManageRecordQueryReq queryReq) {
		Criteria c = super.getCriteria();
		
		 //工程编号
		 if(StringUtils.isNotBlank(queryReq.getProjNo())){
			 c.add(Restrictions.eq("projNo",queryReq.getProjNo()));
		 }
		 //工程id
		 if(StringUtils.isNotBlank(queryReq.getProjId())){
			 c.add(Restrictions.eq("projId",queryReq.getProjId()));
		 }
		 //步骤id
		 if(StringUtils.isNotBlank(queryReq.getLevel())){
			 c.add(Restrictions.eq("mrAuditLevel", queryReq.getLevel()));
		 }
		 //步骤id
		 if(StringUtils.isNotBlank(queryReq.getStepId())){
			 c.add(Restrictions.eq("stepId", queryReq.getStepId()));
		 }
		 //业务id
		 if(StringUtils.isNotBlank(queryReq.getBusinessOrderId())){
			 c.add(Restrictions.eq("businessOrderId", queryReq.getBusinessOrderId()));
		 }
		 //审核通过
		 c.add(Restrictions.eq("mrResult","1"));
		 //没有作废的
		 c.add(Restrictions.isNull("flag"));
		 //时间降序
		 c.addOrder(Order.desc("mrTime"));
		List<ManageRecord> result = this.findByCriteria(c);
		if(result!=null && result.size()>0){
			return result.get(0);
		}
		return null;
	}
	
	/**
	 * 处理审核记录使用
	 * @author fuliwei  
	 * @date 2018年11月6日  
	 * @version 1.0
	 */
	@Override
	public List<ManageRecord> findByStepId(String projId, String stepId) {
		StringBuffer hql = new StringBuffer();
		hql.append("from ManageRecord where 1=1");
		if(StringUtils.isNotBlank(projId)){
			hql.append(" and projId = '").append(projId).append("'");
		}
		if(StringUtils.isNotBlank(stepId)){
			hql.append(" and stepId = '").append(stepId).append("'");
		}
		hql.append(" and mrResult='1' and flag is null");
		List<ManageRecord> result = super.findByHql(hql.toString());
		return result;
	}

	/**
	 * 根据业务ID更新审核历时，签证审核历时使用
	 * 王会军 2019-2-21 
	 * @param businessOrderId
	 * @param stepId
	 */
	@Override
	public void updateAuditRecord(String businessOrderId, String stepId) {
		// TODO Auto-generated method stub
		StringBuffer hql = new StringBuffer();
		hql.append("from ManageRecord where 1=1");
		hql.append(" and businessOrderId = '").append(businessOrderId).append("'");
		hql.append(" and stepId = '").append(stepId).append("'");
		List<ManageRecord> result = super.findByHql(hql.toString());
		if(result!=null && result.size()>0){
			for(int i=0;i<result.size();i++){
				ManageRecord mr = result.get(i);
				mr.setFlag("0"); // 0 为作废
				super.update(mr);
			}
		}
		
	}
	/**
	 * 查询当前审核级别是否已审核
	 * @param manageRecord
	 * @return
	 */
	@Override
	public ManageRecord queryManRdHistory(String projId, String businssId, String stepId, String MrAuditLevel,
			String flag) {
		// TODO Auto-generated method stub
		Criteria criteria = super.getCriteria();
		//工程ID
		if(StringUtil.isNotBlank(projId)){
			criteria.add(Restrictions.eq("projId", projId));
		}
		//业务ID
		if(StringUtil.isNotBlank(businssId)){
			criteria.add(Restrictions.eq("businessOrderId", businssId));
		}
		
		//步骤ID
		if(StringUtil.isNotBlank(stepId)){
			criteria.add(Restrictions.eq("stepId", stepId));
		}
		
		//审核级别
		if(StringUtil.isNotBlank(MrAuditLevel)){
			criteria.add(Restrictions.eq("mrAuditLevel", MrAuditLevel));
		}
		
		//是否有效
		
		criteria.add(Restrictions.isNull("flag"));
	
		
		List<ManageRecord> manageRecords = this.findByCriteria(criteria);
		if(manageRecords != null && manageRecords.size() > 0){
			return manageRecords.get(0);
		}
		return null;
	}

	/**
	 * 根据条件查询返回审核信息列表
	 */
	@Override
	public List<ManageRecord> queryManageRecords(ManageRecordQueryReq queryReq) {
		 Criteria c = super.getCriteria();
		 //工程编号
		 if(StringUtils.isNotBlank(queryReq.getProjNo())){
			 c.add(Restrictions.eq("projNo",queryReq.getProjNo()));
		 }
		 //工程id
		 if(StringUtils.isNotBlank(queryReq.getProjId())){
			 c.add(Restrictions.eq("projId",queryReq.getProjId()));
		 }
		 //审核级别
		 if(StringUtils.isNotBlank(queryReq.getLevel())){
			 c.add(Restrictions.eq("mrAuditLevel", queryReq.getLevel()));
		 }
		 //步骤id
		 if(StringUtils.isNotBlank(queryReq.getStepId())){
			 c.add(Restrictions.eq("stepId", queryReq.getStepId()));
		 }
		 //业务id
		 if(StringUtils.isNotBlank(queryReq.getBusinessOrderId())){
			 c.add(Restrictions.eq("businessOrderId", queryReq.getBusinessOrderId()));
		 }
		 //审核结果
		 if(StringUtils.isNotBlank(queryReq.getMrResult())){
			 c.add(Restrictions.eq("mrResult", queryReq.getMrResult()));
		 }
		//有效标记
		 if(StringUtils.isNotBlank(queryReq.getFlag())){
			 c.add(Restrictions.eq("flag", queryReq.getFlag()));
		 }
		 return this.findByCriteria(c);
	}

	@Override
	public ManageRecord findEvBudgetDate(String evId, String stepId) {
		StringBuffer hql = new StringBuffer();
		hql.append("from ManageRecord where 1=1");
		hql.append(" and businessOrderId =? and stepId=? and flag = 1 order by mrTime desc");
		List<String> params = new ArrayList<String>();
		params.add(evId);
		params.add(stepId);
		List<ManageRecord> result = super.findByHql(hql.toString(),params.toArray());
		if(result!=null && result.size()>0){
			return result.get(0);
		}
		return null;
	}


	/**
	* @Description: 设置无效
	* @author zhangnx
	* @date 2019/8/23 11:50
	*/
	@Override
	public boolean backSetAuditRecordInValid(String projId, List<String> stepIds) {
		if (StringUtils.isBlank(projId)) return false;
		if (stepIds==null || stepIds.size()<1) return false;

		List<Object> params = new ArrayList<Object>();
		StringBuffer sql = new StringBuffer("UPDATE MANAGE_RECORD SET FLAG=0  WHERE ");

		sql.append("PROJ_ID = ?");
		params.add(projId);

		String unknown ="";
		for (int i = 0; i <stepIds.size() ; i++) {
			unknown+=(i!=stepIds.size()-1)? "?,":"?";
		}
		sql.append("AND STEP_ID IN ("+unknown+")");

		for (String stepId:stepIds){//根据参数列表的大小生成in串
			params.add(stepId);
		}

		this.executeSqlList(sql.toString(), params);   //执行sql
		return true;
	}

	@Override
	public void auditRecordsetValid(String businessId, String projId, List<String> stepIds) {
		StringBuilder sql=new StringBuilder();
		sql.append("UPDATE MANAGE_RECORD SET FLAG='0' WHERE 1=1 ");

		if (StringUtils.isNotBlank(businessId)){
			sql.append(" AND BUSINESS_ORDER_ID='"+businessId+"' ");
		}
		if (StringUtils.isNotBlank(projId)){
			sql.append(" AND PROJ_ID='"+projId+"' ");
		}

		if (stepIds!=null && stepIds.size()>0){
			sql.append(" AND STEP_ID in( ");
			for (int i = 0; i <stepIds.size() ; i++) {
				if (i!=stepIds.size()-1){
					sql.append("'"+stepIds.get(i)+"',");
				}else {
					sql.append("'"+stepIds.get(i)+"'");
				}
			}
			sql.append(")");
		}
		if (StringUtils.isNotBlank(businessId)|| StringUtils.isNotBlank(projId)){
			this.executeSql(sql.toString());
		}
	}


	@Override
	public boolean isExist(String businessOrderId, String mrAuditLevel, String stepId) {
		StringBuilder sql=new StringBuilder();
		sql.append("SELECT COUNT(A.MR_ID) FROM MANAGE_RECORD A WHERE 1=1 ");
		List<String> paramList=new ArrayList<>();
		if (StringUtils.isNotBlank(businessOrderId)){
			sql.append(" AND A.BUSINESS_ORDER_ID=?");
			paramList.add(businessOrderId);
		}
		if (StringUtils.isNotBlank(mrAuditLevel)){
			sql.append(" AND A.MR_AUDIT_LEVEL=? ");
			paramList.add(mrAuditLevel);
		}
		if (StringUtils.isNotBlank(stepId)){
			sql.append(" AND A.STEP_ID= ? ");
			paramList.add(stepId);
		}
		sql.append(" AND (A.FLAG ='1' OR A.FLAG IS NULL)");


		int countBySql = this.getCountBySql(sql.toString(), paramList.toArray());
		if (countBySql>0){
			return true;
		}

		return false;
	}


	@Override
	public List<Map<String, Object>> queryStepIdByProjId(String projId) {
		StringBuilder sql=new StringBuilder();
		List<String> paramList=new ArrayList<>();
		sql.append("SELECT a.STEP_ID 'stepId' FROM MANAGE_RECORD a WHERE a.PROJ_ID=? GROUP BY a.STEP_ID");
		paramList.add(projId);
		return this.findListBySql(sql.toString(), paramList.toArray());
	}
}
