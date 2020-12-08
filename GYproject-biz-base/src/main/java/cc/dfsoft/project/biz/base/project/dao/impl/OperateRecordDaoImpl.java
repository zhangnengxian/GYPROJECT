package cc.dfsoft.project.biz.base.project.dao.impl;
import cc.dfsoft.project.biz.base.project.dao.OperateRecordDao;
import cc.dfsoft.project.biz.base.project.dto.OperateRecordQueryReq;
import cc.dfsoft.project.biz.base.project.entity.OperateRecord;
import cc.dfsoft.project.biz.base.project.entity.OperateWorkFlow;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.project.enums.OperateWorkFlowEnum;
import cc.dfsoft.project.biz.base.project.enums.ProjStatusEnum;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;
import cc.dfsoft.uexpress.common.util.MapUtils;
import cc.dfsoft.uexpress.common.util.ResultUtil;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
@Repository
public class OperateRecordDaoImpl extends NewBaseDAO<OperateRecord, String> implements OperateRecordDao{


	@Override
	public Map<String, Object> queryOperateRecord(OperateRecordQueryReq operateRecordQueryReq) {

		Criteria c = super.getCriteria();

		//工程编号
		if(StringUtils.isNotBlank(operateRecordQueryReq.getProjNo())){
			c.add(Restrictions.eq("projNo",operateRecordQueryReq.getProjNo()));
		}
		//工程id
		if(StringUtils.isNotBlank(operateRecordQueryReq.getProjId())){
			c.add(Restrictions.eq("projId",operateRecordQueryReq.getProjId()));
		}
		//步骤id
		if(StringUtils.isNotBlank(operateRecordQueryReq.getStepId())){
			c.add(Restrictions.eq("stepId", operateRecordQueryReq.getStepId()));
		}
		//是否有效
		if(StringUtils.isNotBlank(operateRecordQueryReq.getIsValid())){
			c.add(Restrictions.eq("isValid", operateRecordQueryReq.getIsValid()));
		}
		//审核级别
		if(StringUtils.isNotBlank(operateRecordQueryReq.getGrade())){
			c.add(Restrictions.eq("grade", operateRecordQueryReq.getGrade()));
		}
		//已办标记
		if(StringUtils.isNotBlank(operateRecordQueryReq.getModifyStatus())){
			c.add(Restrictions.eq("modifyStatus", operateRecordQueryReq.getModifyStatus()));
		}
		// 数据库根据条件过滤记录数
		int filterCount = this.countByCriteria(c);

		// 根据条件获取查询信息
		List<OperateRecord> operateRecordList = this.findBySortCriteria(c, operateRecordQueryReq);

		// 返回结果
		return ResultUtil.pageResult(filterCount, operateRecordQueryReq.getDraw(),operateRecordList);
	}

	@Override
	public List<Map<String, Object>> getOptRecordByTime(String projStatus){
		//oracle
		/*String sql="select * from (select t.proj_id,t.operate_time,row_number() over(partition by proj_id order by operate_time desc) row_number "
				   + "from OPERATE_RECORD t where t.proj_id in("
				   + "select b.proj_id from project b where b.proj_status_id='"+projStatus+"'"
				   + ")) a "
				   + "where a.row_number=1";*/
		//mysql
		String sql="select * from ( "
				+ "select heyf_tmp.operate_time as OPERATE_TIME, "
				+ "if(@pdept=heyf_tmp.proj_id,@rank\\:=@rank+1,@rank\\:=1) as ROW_NUMBER, "
				+ " @pdept\\:=heyf_tmp.proj_id  AS PROJ_ID from ( select t.proj_id,t.operate_time "
				+ "from operate_record t where  t.proj_id in("
				+ " select b.proj_id  from  project b  where  b.proj_status_id='"+projStatus+"'"
				+ ") order by proj_id asc ,operate_time desc ) heyf_tmp , (select @rownum\\:=0 , @pdept\\:= null ,@rank\\:=0) rn  "
				+ ") a where a.ROW_NUMBER=1";
		List<Map<String, Object>> list=this.findListBySql(sql);
		return list;

	}

	@Override
	public Map<String, Object> queryOperateRecordBySql(OperateRecordQueryReq operateRecordQueryReq) throws ParseException {
		StringBuffer sql = new StringBuffer();
		List<Object> dataParamList = new ArrayList<Object>();
		//oracle
		//sql.append("select * from (select rownum as rn,tt.proj_id projId,tt.proj_no as projNo,tt.step_id as stepId,tt.operate_time as operateTime,tt.step_name as stepName,tt.dept_name as deptName,tt.staff_name as staffName from (").
		//mysql
		sql.append("select * from (select ").append(mysqlSqlConveter.rownumberConveter(" rn,tt.proj_id projId,tt.proj_no as projNo,tt.step_id as stepId,tt.operate_time as operateTime,tt.step_name as stepName,tt.dept_name as deptName,tt.staff_name as staffName from ")).append("(").
				append(" select o.*,d.dept_name,s.staff_name from operate_record o ").
				append("  left join department d on o.operate_dept1= d.dept_id").
				append("  left join staff s on o.operate_csr1 = s.staff_id )tt where 1=1");

		//工程编号
		if(StringUtils.isNotBlank(operateRecordQueryReq.getProjNo())){
			sql.append(" and tt.proj_no = ?");
			dataParamList.add(operateRecordQueryReq.getProjNo());
		}
		//工程id
		if(StringUtils.isNotBlank(operateRecordQueryReq.getProjId())){
			sql.append(" and tt.proj_id = ?");
			dataParamList.add(operateRecordQueryReq.getProjId());
		}
		//步骤id
		if(StringUtils.isNotBlank(operateRecordQueryReq.getStepId())){
			sql.append(" and tt.step_id = ?");
			dataParamList.add(operateRecordQueryReq.getStepId());
		}
		sql.append(") total where 1=1");
		List<Map<String,Object>> countList = super.findListBySql(sql.toString(), dataParamList.toArray());
		int filterCount =  countList==null?0:countList.size();
		sql.append(" and total.rn>").append(operateRecordQueryReq.getStart());
		sql.append(" and total.rn<=").append(operateRecordQueryReq.getLength()+operateRecordQueryReq.getStart());
		sql.append(" order by ").append(operateRecordQueryReq.getSortName()).append(" ").append(operateRecordQueryReq.getSort());
		List<Map<String, Object>> list = this.findListBySql(sql.toString(), dataParamList.toArray());
		for(int i=0;i<list.size();i++){
			if((Timestamp)list.get(i).get("OPERATETIME")!=null){
				list.get(i).put("OPERATETIME", ((Timestamp)list.get(i).get("OPERATETIME")).getTime());
			}
		}
		// 返回结果
		return ResultUtil.pageResult(filterCount, operateRecordQueryReq.getDraw(),list);
	}

	@Override
	public List<Map<String, Object>> querySchedule(String projId) {
		StringBuffer sql = new StringBuffer();
		//updatesubstr
		//sql.append("select ").append(mysqlSqlConveter.funcSubstringConvert()).append("(o.step_id,0,2) as stepId, Max(o.operate_time) maxTime,min(o.operate_time) minTime,").append(mysqlSqlConveter.funcSubstringConvert()).append("(o.step_id,0,2) as type,sum(a.delay_period) as delayDay,sum(s.time_span) as TIMESPAN from operate_record o ");
		sql.append("select ").append(mysqlSqlConveter.funcSubstringConvert()).append("(o.step_id,1,2) as stepId, Max(o.operate_time) maxTime,min(o.operate_time) minTime,").append(mysqlSqlConveter.funcSubstringConvert()).append("(o.step_id,1,2) as type,sum(a.delay_period) as delayDay,sum(s.time_span) as TIMESPAN from operate_record o ");
		sql.append("left join apply_delay a on a.proj_id=o.proj_id and a.step_id=o.step_id left join system_set s on o.step_id=s.step_id where 1=1 ");
		if(StringUtils.isNotBlank(projId)){
			sql.append(" and o.proj_id='").append(projId).append("'");
		}
		sql.append(" and o.is_valid='1' ");
		//updatesubstr
		//sql.append(" group by  ").append(mysqlSqlConveter.funcSubstringConvert()).append("(o.step_id,0,2)  order by type ");
		sql.append(" group by  ").append(mysqlSqlConveter.funcSubstringConvert()).append("(o.step_id,1,2)  order by type ");
		List<Map<String, Object>> list = this.findListBySql(sql.toString());
		return list;
	}
	@Override
	public Map<String, Object> findByProjIdType(String projId, String type) {
		StringBuffer sql = new StringBuffer();
		sql.append("select a.step_id stepId,a.step_name stepName,a.operate_time operateTime from (select o.*  from operate_record o where 1=1 ");
		sql.append(" and o.step_id like '").append(type).append("%'");
		sql.append(" and o.proj_id='").append(projId).append("'");
		sql.append(" order by o.operate_time,o.step_id) a ");
		List<Map<String, Object>> list = this.findListBySql(sql.toString());
		if(list!=null && list.size()>0){
			return list.get(0);
		}
		return null;
	}
	//先按时间排序，再分组排序
	public List<Map<String, Object>> queryScheduleFlow(String projId) {
		StringBuffer sql = new StringBuffer();
		sql.append("select max(re.operate_time) operate_time,re.step_id,re.staff_name,re.photo_url from ");
		sql.append("(select o.operate_time,o.step_id,s.staff_name,s.photo_url,m.MEUN_ID from operate_record o ");
		sql.append(" LEFT JOIN menu_step m on  m.STEP_ID = o.STEP_ID left join staff s on replace(o.operate_csr1,',','')=s.staff_id where 1=1 ");
		if(StringUtils.isNotBlank(projId)){
			sql.append(" and o.proj_id='").append(projId).append("'");
		}
		sql.append(" and o.is_valid='1' and o.modify_status='1' and o.op_type = '1'");
		sql.append("ORDER BY o.OPERATE_TIME desc) re where IFNULL(re.MEUN_ID,'')!='' group by re.step_id order by step_id asc,max(re.operate_time) asc");
		List<Map<String, Object>> list = this.findListBySql(sql.toString());
		return list;
	}



	/**
	 * 将历史记录置为无效
	 * @author fuliwei
	 * @createTime 2017年12月15日
	 * @param
	 * @return
	 */
	@Override
	public void updateOrValid(List<String> stepIds,String projId) {
		Criteria c=super.getCriteria();
		if(stepIds!=null && stepIds.size()>0){
			StringBuffer sql=new StringBuffer(" update OperateRecord set isValid='0' where projId='").append(projId).append("' and stepId in('").append(stepIds.get(0));
			if(stepIds.size()>1){
				for(int i=1;i<stepIds.size();i++){
					sql.append("' ,'").append(stepIds.get(i)).append("");
				}
			}
			sql.append("')");
			super.executeHql(sql.toString());
		}
	}
	/**
	 * 关联操作查询业务操作记录
	 * @author fuliwei
	 * @date 2018年10月6日
	 * @version 1.0
	 */
	@Override
	public Map<String, Object> queryOperateRecordList(OperateRecordQueryReq req) {
		Criteria c = super.getCriteria();

		//分公司id
		if(StringUtils.isNotBlank(req.getCorpId())){
			c.add(Restrictions.like("corpId","%"+req.getCorpId()+"%"));
		}

		//步骤id
		if(StringUtils.isNotBlank(req.getStepId())){
			c.add(Restrictions.eq("stepId",req.getStepId()));
		}

		//工程类型id
		if(StringUtils.isNotBlank(req.getProjectType())){
			c.add(Restrictions.eq("projectType",req.getProjectType()));
		}

		//出资方式id
		if(StringUtils.isNotBlank(req.getContributionMode())){
			c.add(Restrictions.eq("contributionMode",req.getContributionMode()));
		}

		//工程id
		if(StringUtils.isNotBlank(req.getProjId())){
			c.add(Restrictions.eq("projId",req.getProjId()));
		}

		//工程编号
		if(StringUtils.isNotBlank(req.getProjNo())){
			c.add(Restrictions.like("projNo",req.getProjNo()+"%"));
		}

		//状态
		if(StringUtils.isNotBlank(req.getModifyStatus())){
			c.add(Restrictions.eq("modifyStatus",req.getModifyStatus()));
		}

		//操作人
		if(StringUtils.isNotBlank(req.getOperaterId())){
			c.add(Restrictions.like("operateCsr1","%,"+req.getOperaterId()+",%"));
		}

		//流程类型
		if(StringUtils.isNotBlank(req.getOpType())){
			c.add(Restrictions.eq("opType",req.getOpType()));
		}

		// 业务单ID
		if (StringUtils.isNotBlank(req.getBusinessOrderId())) {
			c.add(Restrictions.eq("businessOrderId",req.getBusinessOrderId()));
		}

		//默认为有效的操作记录
		if(StringUtils.isNotBlank(req.getIsValid())){
			c.add(Restrictions.eq("isValid",req.getIsValid()));
		}

		c.addOrder(Order.asc("stepId"));
		// 数据库根据条件过滤记录数
		int filterCount = this.countByCriteria(c);

		// 根据条件获取查询信息
		List<OperateRecord> projectTypeList = this.findBySortCriteria(c, req);

		// 返回结果
		return ResultUtil.pageResult( filterCount, req.getDraw(),projectTypeList);
	}

	/**
	 * 关联操作查询业务操作记录
	 * @author fuliwei
	 * @date 2018年10月6日
	 * @version 1.0
	 */
	@Override
	public Map<String, Object> queryOperateRecordLists(OperateRecordQueryReq req) throws Exception {
		Criteria c = super.getCriteria();

		//分公司id
		if(StringUtils.isNotBlank(req.getCorpId())){
			c.add(Restrictions.like("corpId","%"+req.getCorpId()+"%"));
		}

		//步骤id
		if(StringUtils.isNotBlank(req.getStepId())){
			c.add(Restrictions.eq("stepId",req.getStepId()));
		}

		//审核级别
		if(StringUtils.isNotBlank(req.getGrade())){
			c.add(Restrictions.eq("grade", req.getGrade()));
		}
		//工程类型id
		if(StringUtils.isNotBlank(req.getProjectType())){
			c.add(Restrictions.eq("projectType",req.getProjectType()));
		}
		//出资方式id
		if(StringUtils.isNotBlank(req.getContributionMode())){
			c.add(Restrictions.eq("contributionMode",req.getContributionMode()));
		}
		//默认为有效的操作记录
		if(StringUtils.isNotBlank(req.getIsValid())){
			c.add(Restrictions.eq("isValid",req.getIsValid()));
		}

		//修改状态
		if(StringUtils.isNotBlank(req.getModifyStatus())){
			c.add(Restrictions.ne("modifyStatus",req.getModifyStatus()));  //是未办或者代办状态
		}

		c.addOrder(Order.asc("stepId"));
		// 数据库根据条件过滤记录数
		int filterCount = this.countByCriteria(c);

		// 根据条件获取查询信息
		List<OperateRecord> projectTypeList = this.findBySortCriteria(c, req);

		// 返回结果
		return ResultUtil.pageResult( filterCount, req.getDraw(),projectTypeList);
	}


	/**
	 * 查询下一审核级别的操作记录
	 * @author fuliwei
	 * @date 2018年10月7日
	 * @version 1.0
	 */
	@Override
	public OperateRecord findByGread(String projId,String corpId, String projectType, String conMode, String stepId, String grade,String modifyStatus) {
		Criteria c = super.getCriteria();

		//工程id
		if(StringUtils.isNotBlank(projId)){
			c.add(Restrictions.eq("projId",projId));
		}

		//分公司id
		if(StringUtils.isNotBlank(corpId)){
			c.add(Restrictions.like("corpId","%"+corpId+"%"));
		}

		//步骤id
		if(StringUtils.isNotBlank(stepId)){
			c.add(Restrictions.eq("stepId",stepId));
		}

		//工程类型id
		if(StringUtils.isNotBlank(projectType)){
			c.add(Restrictions.eq("projectType",projectType));
		}
		//出资方式id
		if(StringUtils.isNotBlank(conMode)){
			c.add(Restrictions.eq("contributionMode",conMode));
		}

		//审核级别
		if(StringUtils.isNotBlank(grade)){
			c.add(Restrictions.eq("grade",grade));
		}

		//修改状态
		if(StringUtils.isNotBlank(modifyStatus)){
			c.add(Restrictions.eq("modifyStatus",modifyStatus));
		}


		//默认为有效的操作记录
		c.add(Restrictions.eq("isValid","1"));

		// 根据条件获取查询信息
		List<OperateRecord> projectTypeList = this.findByCriteria(c);

		// 返回结果
		if(projectTypeList!=null && projectTypeList.size()>0){
			return projectTypeList.get(0);
		}
		return null;
	}

	/**
	 * 将历史记录置为无效 且未办
	 * @author fuliwei
	 * @date 2018年10月7日
	 * @version 1.0
	 */
	@Override
	public void updateOrValidAndNotDone(List<String> stepIds, String projId) {

	}

	/**
	 * 将历史记录置为无效
	 * @author fuliwei
	 * @date 2018年10月17日
	 * @version 1.0
	 */
	@Override
	public void batUpdateHistoryRecordByBoId(String projId, String businessorderId, String stepId) {
		StringBuffer hql = new StringBuffer();
		hql.append("from OperateRecord where 1=1");
		if(StringUtils.isNotBlank(projId)){
			hql.append(" and projId = '").append(projId).append("'");
		}

		if(StringUtils.isNotBlank(businessorderId)){
			hql.append(" and businessOrderId = '").append(businessorderId).append("'");
		}

		if(StringUtils.isNotBlank(stepId)){
			hql.append(" and stepId = '").append(stepId).append("'");
		}
		List<OperateRecord> result = super.findByHql(hql.toString());
		for(int i=0;i<result.size();i++){
			OperateRecord mr = result.get(i);
			mr.setIsValid("0"); // 0 为作废
			mr.setModifyStatus(OperateWorkFlowEnum.NOT_DONE.getValue());//未办
			super.update(mr);
		}
	}
	/**
	 * 查询之前最大时间的操作记录 如审核回退时需重新生成操作记录
	 * @author fuliwei
	 * @date 2018年10月17日
	 * @version 1.0
	 */
	@Override
	public OperateRecord findByMaxTime(String projId, String corpId, String projectType, String conMode, String stepId,
									   String grade, String modifyStatus) {
		Criteria c = super.getCriteria();

		//工程id
		if(StringUtils.isNotBlank(projId)){
			c.add(Restrictions.eq("projId",projId));
		}

		//分公司id
		if(StringUtils.isNotBlank(corpId)){
			c.add(Restrictions.like("corpId","%"+corpId+"%"));
		}

		//步骤id
		if(StringUtils.isNotBlank(stepId)){
			c.add(Restrictions.eq("stepId",stepId));
		}

		//工程类型id
		if(StringUtils.isNotBlank(projectType)){
			c.add(Restrictions.eq("projectType",projectType));
		}
		//出资方式id
		if(StringUtils.isNotBlank(conMode)){
			c.add(Restrictions.eq("contributionMode",conMode));
		}

		//审核级别
		if(StringUtils.isNotBlank(grade)){
			c.add(Restrictions.eq("grade",grade));
		}

		//修改状态
		if(StringUtils.isNotBlank(modifyStatus)){
			c.add(Restrictions.eq("modifyStatus",modifyStatus));
		}

		//施工预算审核和终审派遣 回退到一级审核
		 /*if(StepEnum.QUALITIES_JUDGEMENT.getValue().equals(stepId)||StepEnum.AUDIT_DONE_DISPATCH.getValue().equals(stepId)){
			 c.add(Restrictions.or(Restrictions.eq("grade", "1"),Restrictions.isNull("grade")));
			 //默认为有效的操作记录
			 c.addOrder(Order.desc("grade")).addOrder(Order.desc("operateTime"));  // 操作日期
		 }else{

		 }*/
		c.addOrder(Order.desc("operateTime"));


		// 根据条件获取查询信息
		List<OperateRecord> projectTypeList = this.findByCriteria(c);

		// 返回结果
		if(projectTypeList!=null && projectTypeList.size()>0){
			return projectTypeList.get(0);
		}
		return null;
	}

	@Override
	public OperateRecord queryEndOperateRecord(OperateRecordQueryReq operateRecordQueryReq) {

		Criteria c = super.getCriteria();

		//工程编号
		if(StringUtils.isNotBlank(operateRecordQueryReq.getProjNo())){
			c.add(Restrictions.eq("projNo",operateRecordQueryReq.getProjNo()));
		}
		//公司ID
		if(StringUtils.isNotBlank(operateRecordQueryReq.getCorpId())){
			c.add(Restrictions.eq("corpId",operateRecordQueryReq.getCorpId()));
		}
		//工程id
		if(StringUtils.isNotBlank(operateRecordQueryReq.getProjId())){
			c.add(Restrictions.eq("projId",operateRecordQueryReq.getProjId()));
		}
		//步骤id
		if(StringUtils.isNotBlank(operateRecordQueryReq.getStepId())){
			c.add(Restrictions.eq("stepId", operateRecordQueryReq.getStepId()));
		}
		//审核级别
		if(StringUtils.isNotBlank(operateRecordQueryReq.getGrade())){
			c.add(Restrictions.eq("grade", operateRecordQueryReq.getGrade()));
		}
		//修改状态
		if(StringUtils.isNotBlank(operateRecordQueryReq.getModifyStatus())){
			c.add(Restrictions.ne("modifyStatus", "1"));
		}
		//是否有效
		if(StringUtils.isNotBlank(operateRecordQueryReq.getIsValid())){
			c.add(Restrictions.eq("isValid", operateRecordQueryReq.getIsValid()));
		}
		//工程类型
		if(StringUtils.isNotBlank(operateRecordQueryReq.getProjectType())){
			c.add(Restrictions.eq("projectType", operateRecordQueryReq.getProjectType()));
		}

		//出资方式
		if(StringUtils.isNotBlank(operateRecordQueryReq.getContributionMode())){
			c.add(Restrictions.eq("contributionMode",operateRecordQueryReq.getContributionMode()));
		}
		c.addOrder(Order.desc("operateTime")).addOrder(Order.asc("grade"));  //先按时间降序，在按审核级别升序

		// 根据条件获取查询信息
		List<OperateRecord> operateRecordList = this.findBySortCriteria(c, operateRecordQueryReq);
		if(operateRecordList!=null && operateRecordList.size()>0){
			return operateRecordList.get(0);
		}
		// 返回结果
		return null;
	}

	/**
	 * 查询工作通知
	 * @param projNo
	 * @param Step
	 * @param projectType
	 * @throws Exception
	 */
	@Override
	public OperateRecord queryWorkNotice(String projNo, String stepId, String projectType) throws Exception {
		// TODO Auto-generated method stub
		Criteria criteria  = super.getCriteria();
		//根据工程编号查询
		if(StringUtils.isNotBlank(projNo)){
			criteria.add(Restrictions.eq("projNo", projNo));
		}

		//根据步骤ID查询
		if(StringUtils.isNotBlank(stepId)){
			criteria.add(Restrictions.eq("stepId", stepId));
		}


		//根据工程类型查询
		if(StringUtils.isNotBlank(projectType)){
			criteria.add(Restrictions.eq("projectType", projectType));
		}

		criteria.add(Restrictions.eq("isValid", "1"));
		List<OperateRecord> list = this.findByCriteria(criteria);
		if(list !=null && list.size()>0){
			return list.get(0);
		}
		return null;
	}

	/**
	 * 终止工程：取消待办置为待办
	 */
	@Override
	public void cancelTodo(String projId) {
		StringBuffer hql = new StringBuffer("update OperateRecord set modifyStatus='").append(OperateWorkFlowEnum.NOT_DONE.getValue()).append("' where modifyStatus='2' and projId='").append(projId).append("'");
		this.executeHql(hql.toString());
	}
	/**
	 *回退审核时查找已生成的操作记录
	 * @param projId
	 * @param corpId
	 * @return
	 */
	@Override
	public List<OperateRecord> findByMaxTimeList(String projId, String corpId, String projectType, String conMode, String stepId, String grade, String modifyStatus) {
		Criteria c = super.getCriteria();

		//工程id
		if(StringUtils.isNotBlank(projId)){
			c.add(Restrictions.eq("projId",projId));
		}

		//分公司id
		if(StringUtils.isNotBlank(corpId)){
			c.add(Restrictions.like("corpId","%"+corpId+"%"));
		}

		//步骤id
		if(StringUtils.isNotBlank(stepId)){
			c.add(Restrictions.eq("stepId",stepId));
		}

		//工程类型id
		if(StringUtils.isNotBlank(projectType)){
			c.add(Restrictions.eq("projectType",projectType));
		}
		//出资方式id
		if(StringUtils.isNotBlank(conMode)){
			c.add(Restrictions.eq("contributionMode",conMode));
		}

		//审核级别
		if(StringUtils.isNotBlank(grade)){
			c.add(Restrictions.eq("grade",grade));
		}

		//修改状态
		//查询已办和代办的
		if(StringUtils.isNotBlank(modifyStatus)){
			c.add(Restrictions.or(Restrictions.eq("modifyStatus",modifyStatus),Restrictions.eq("modifyStatus",OperateWorkFlowEnum.WAIT_DONE.getValue())));
		}


		//默认为有效的操作记录
		c.add(Restrictions.eq("isValid","1"));
		c.addOrder(Order.desc("operateTime"));  // 操作日期

		// 根据条件获取查询信息
		return this.findByCriteria(c);
	}


	@Override
	public List<OperateRecord> getOptRecordListByProjIdOrOperator(String projId, String operator) {
		Criteria c = super.getCriteria();

		if(StringUtils.isNotBlank(projId) && StringUtils.isNotBlank(operator)){
			c.add(Restrictions.eq("projId",projId));//工程id
			c.add(Restrictions.like("operateCsr1","%"+operator+"%"));//代待办人
			return this.findByCriteria(c);
		}else {
			return null;
		}


	}



	/**
	 * 根据操作流更新操作记录
	 * @param operateWorkFlow
	 * @throws Exception
	 */
	@Override
	public void updateOperateRecord(OperateWorkFlow operateWorkFlow) throws Exception {
		// TODO Auto-generated method stub
		List<Object> params = new ArrayList<Object>();
		StringBuffer sql = new StringBuffer("update Operate_Record set operate_Csr1 = '").append(operateWorkFlow.getOpereaterId()).append("', operater = '").append(operateWorkFlow.getOpereater())
				.append("' where ");

		sql.append(" corp_Id = ?");
		params.add(operateWorkFlow.getCorpId());

		sql.append(" and project_Type = ?");
		params.add(operateWorkFlow.getProjectType());

		sql.append(" and contribution_Mode = ?");
		params.add(operateWorkFlow.getContributionMode());

		sql.append(" and step_Id = ?");
		params.add(operateWorkFlow.getStepId());

		if(StringUtils.isNotBlank(operateWorkFlow.getGrade())){  //审核级别非空
			sql.append(" and grade = ?");
			params.add(operateWorkFlow.getGrade());
		}
		sql.append(" and is_Valid = ?");
		params.add("1");

		sql.append(" and modify_Status <> ?");
		params.add("1");

		this.executeSqlList(sql.toString(), params);   //执行sql


	}

	@Override
	public List<OperateRecord> queryListByProjIdAndStatus(String projId, String s) {
		Criteria c = super.getCriteria();

		if(StringUtils.isNotBlank(projId)){//工程id
			c.add(Restrictions.eq("projId",projId));
		}
		if(StringUtils.isNotBlank(s)){//操作状态
			c.add(Restrictions.eq("modifyStatus",s));
		}

		return this.findByCriteria(c);
	}

	/**
	 * 注释：将待办置为已办
	 * @author liaoyq
	 * @createTime 2019年5月21日
	 * @param project
	 * @param wfAssistTypeId
	 * @param busOrderId
	 * @return
	 *
	 */
	@Override
	public void updateIsDone(Project project,String stepId, String opType,
							 String busOrderId,String grade) {
		// TODO Auto-generated method stub
		List<Object> params = new ArrayList<Object>();
		StringBuffer sql = new StringBuffer("update Operate_Record set operate_Csr1 = ?, operater = ?,modify_Status=1,OPERATE_TIME=? where ");
		params.add(","+SessionUtil.getLoginInfo().getStaffId()+",");
		params.add(SessionUtil.getLoginInfo().getStaffName());
		params.add(this.getDatabaseDate());
		sql.append(" corp_Id = ?");
		params.add(project.getCorpId());

		sql.append(" and project_Type = ?");
		params.add(project.getProjectType());

		sql.append(" and contribution_Mode = ?");
		params.add(project.getContributionMode());
		if(StringUtils.isNotBlank(stepId)){
			sql.append(" and step_Id = ?");
			params.add(stepId);
		}
		sql.append(" and op_type = ?");
		params.add(opType);
		sql.append(" and BUSINESS_ORDER_ID = ?");
		params.add(busOrderId);

		if(StringUtils.isNotBlank(grade)){  //审核级别非空
			sql.append(" and grade = ?");
			params.add(grade);
		}
		sql.append(" and is_Valid = ?");
		params.add("1");

		sql.append(" and modify_Status = ?");
		params.add("2");

		this.executeSqlList(sql.toString(), params);   //执行sql
	}

	@Override
	public List<OperateRecord> findListByProjStepStatusId(String projId, String stepId,String status) {
		Criteria c = super.getCriteria();

		if(StringUtils.isNotBlank(projId)){//工程id
			c.add(Restrictions.eq("projId",projId));
		}
		if(StringUtils.isNotBlank(stepId)){//步骤
			c.add(Restrictions.eq("stepId",stepId));
		}
		if(StringUtils.isNotBlank(status)){//操作状态
			c.add(Restrictions.eq("modifyStatus",status));
		}
		c.add(Restrictions.eq("isValid","1"));//有效


		return this.findByCriteria(c);
	}


	@Override
	public List<OperateRecord> findListByStepId(String projId, String stepId) {
		Criteria c = super.getCriteria();

		if(StringUtils.isNotBlank(projId)){//工程id
			c.add(Restrictions.eq("projId",projId));
		}
		if(StringUtils.isNotBlank(stepId)){//步骤
			c.add(Restrictions.eq("stepId",stepId));
		}
		return this.findByCriteria(c);
	}

	@Override
	public void updateIsValid(String busOrderId, String opType) {
		List<Object> params = new ArrayList<Object>();
		StringBuffer sql = new StringBuffer("update Operate_Record set is_Valid=1 where ");
		sql.append(" BUSINESS_ORDER_ID = ?");
		params.add(busOrderId);
		sql.append(" OP_TYPE = ?");
		params.add(opType);
		this.executeSqlList(sql.toString(), params);   //执行sql
	}

	@Override
	public List<OperateRecord> queryOperateRecordList(String businessOrderId,String stepId, String modifyStatus, String grade) {
		Criteria c = super.getCriteria();

		if(StringUtils.isNotBlank(businessOrderId)){
			c.add(Restrictions.eq("businessOrderId",businessOrderId));
		}
		if(StringUtils.isNotBlank(stepId)){//操作步骤
			c.add(Restrictions.eq("stepId",stepId));
		}
		if(StringUtils.isNotBlank(modifyStatus)){//操作状态
			c.add(Restrictions.eq("modifyStatus",modifyStatus));
		}
		if(StringUtils.isNotBlank(grade)){//审核级别
			c.add(Restrictions.eq("grade",grade));
		}
		c.add(Restrictions.eq("isValid","1"));//默认查询有效

		return this.findByCriteria(c);
	}

	@Override
	public List<OperateRecord> queryListByStepIds(String projId,String status, List<String> stepIds) {
		Criteria c = super.getCriteria();

		if(StringUtils.isNotBlank(projId)){//工程id
			c.add(Restrictions.eq("projId",projId));
		}

		if(stepIds!=null && stepIds.size()>0){//操作步骤
			c.add(Restrictions.in("stepId",stepIds));
		}

		if(StringUtils.isNotBlank(status)){//查询已办和待办的（不等余零的）
			c.add(Restrictions.ne("modifyStatus", status));
		}

		c.add(Restrictions.eq("isValid","1"));//默认查询有效的记录


		return this.findByCriteria(c);
	}



	/**
	 * @Description: 设置无效
	 * @author zhangnx
	 * @date 2019/8/23 11:50
	 */
	@Override
	public boolean backSetToDoInValid(String projId,List<String> stepIds) {

		if (StringUtils.isBlank(projId)) return false;
		if (stepIds==null || stepIds.size()<1) return false;

		List<Object> params = new ArrayList<Object>();
		StringBuffer sql = new StringBuffer("UPDATE OPERATE_RECORD SET IS_VALID=0, MODIFY_STATUS=1  WHERE ");

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


	/**
	 * @ Description: 恢复有效
	 * @ author zhangnx
	 * @ date 2019/11/20 17:19
	 **/

	@Override
	public void recoveryIsValid(String projId, String[] stepIds) {
		if (StringUtils.isBlank(projId)){ return;}

		List<Object> params = new ArrayList<Object>();
		StringBuffer sql = new StringBuffer("UPDATE OPERATE_RECORD SET IS_VALID=1, MODIFY_STATUS=0  WHERE ");

		sql.append("PROJ_ID = ?");
		params.add(projId);

		String unknown ="";
		for (int i = 0; i <stepIds.length; i++) {
			unknown+=(i!=stepIds.length-1)? "?,":"?";
			params.add(stepIds[i]);
		}
		sql.append("AND STEP_ID IN ("+unknown+")");

		this.executeSqlList(sql.toString(), params);   //执行sql

	}




	/**
	* @ Description: 更新为待办
	* @ author zhangnx
	* @ date 2019/11/20 17:45
	**/

	@Override
	public void recoverySetTodoer(String projId, String stepId, String grade) {
		if (StringUtils.isBlank(projId)){ return;}

		List<Object> params = new ArrayList<Object>();
		StringBuffer sql = new StringBuffer("UPDATE OPERATE_RECORD SET MODIFY_STATUS=2  WHERE ");

		sql.append("PROJ_ID = ?");
		params.add(projId);

		sql.append("AND STEP_ID = ?");
		params.add(stepId);

		if (StringUtils.isNotBlank(grade)){
			sql.append("AND GRADE = ?");
			params.add(grade);
		}

		this.executeSqlList(sql.toString(), params);   //执行sql
	}



	@Override
	public void backSetToDoInValid(String businessId,String projId,List<String> stepIds) {
		StringBuilder sql=new StringBuilder();
		sql.append("UPDATE operate_record SET MODIFY_STATUS='0' WHERE 1=1 ");

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
	public OperateRecord queryOperater(String projId, String stepId, String grade) {

		List<Object> paramList=new ArrayList<>();
		StringBuilder findSql=new StringBuilder();

		findSql.append("SELECT OPERATE_CSR1 'operateCsr1',OPERATER 'operater' FROM OPERATE_RECORD  WHERE 1=1 ");

		if (StringUtils.isNotBlank(projId)){
			findSql.append("AND PROJ_ID = ?");
			paramList.add(projId);
		}

		if (StringUtils.isNotBlank(stepId)){
			findSql.append("AND STEP_ID = ?");
			paramList.add(stepId);
		}

		if (StringUtils.isNotBlank(grade)){
			findSql.append("AND GRADE = ?");
			paramList.add(grade);
		}

		findSql.append("ORDER BY OR_ID DESC");

		List<Map<String, Object>> mapList = this.findListBySql(findSql.toString(), paramList.toArray());

		if (mapList!=null && mapList.size()>0){
			return MapUtils.mapTransBean(mapList.get(0), OperateRecord.class);
		}

		return null;
	}

	/**
	 * 查询操作记录列表
	 */
	@Override
	public List<OperateRecord> queryOperateRecords(OperateRecordQueryReq req) {
		Criteria c = super.getCriteria();

		//分公司id
		if(StringUtils.isNotBlank(req.getCorpId())){
			c.add(Restrictions.like("corpId","%"+req.getCorpId()+"%"));
		}

		//步骤id
		if(StringUtils.isNotBlank(req.getStepId())){
			c.add(Restrictions.eq("stepId",req.getStepId()));
		}

		//工程类型id
		if(StringUtils.isNotBlank(req.getProjectType())){
			c.add(Restrictions.eq("projectType",req.getProjectType()));
		}
		//出资方式id
		if(StringUtils.isNotBlank(req.getContributionMode())){
			c.add(Restrictions.eq("contributionMode",req.getContributionMode()));
		}

		//工程id
		if(StringUtils.isNotBlank(req.getProjId())){
			c.add(Restrictions.eq("projId",req.getProjId()));
		}
		//状态
		if(StringUtils.isNotBlank(req.getModifyStatus())){
			c.add(Restrictions.eq("modifyStatus",req.getModifyStatus()));
		}

		//操作人
		if(StringUtils.isNotBlank(req.getOperaterId())){
			c.add(Restrictions.like("operateCsr1","%,"+req.getOperaterId()+",%"));
		}

		//流程类型
		if(StringUtils.isNotBlank(req.getOpType())){
			c.add(Restrictions.eq("opType",req.getOpType()));
		}
		
        // 业务单ID
		if (StringUtils.isNotBlank(req.getBusinessOrderId())) {
			c.add(Restrictions.eq("businessOrderId",req.getBusinessOrderId()));
		}
		
		//默认为有效的操作记录
		c.add(Restrictions.eq("isValid","1"));

		// 根据条件获取查询信息
		return this.findByCriteria(c);
	}

	@Override
	public List<Map<String, Object>> queryOperateRecordTodo() {
		String sql ="select orr.OR_ID OR_ID,orr.proj_no PROJ_NO,project.proj_Name PROJ_NAME,orr.OPERATE_CSR1,orr.STEP_NAME ,project.PROJ_STATUS_ID PROJ_STATUS_ID,";
			   sql +="staff.staff_id STAFF_ID,staff.REGISTRATIONID REGISTRATIONID,staff.staff_name STAFF_NAME from operate_record orr";
		       sql +=" LEFT JOIN staff staff on FIND_IN_SET(staff.STAFF_ID,orr.OPERATE_CSR1)>0";
		       sql +=" LEFT JOIN project project on orr.proj_id = project.proj_id";
		       sql +=" where orr.MODIFY_STATUS='"+OperateWorkFlowEnum.WAIT_DONE.getValue()+"' and orr.IS_VALID='1' and orr.OPERATE_CSR1!=',,' and orr.OPERATE_CSR1!=',null,'";//待办
		       sql +=" and project.PROJ_STATUS_ID!='"+ ProjStatusEnum.TERMINATION.getValue()+"'";//已终止
		       sql +=" and (orr.SEND_JPUSH_STATUS is null or orr.SEND_JPUSH_STATUS !='1')";		//未推送
		       sql +=" and IFNULL(staff.REGISTRATIONID,'')!='' ";	//设备id不为空
		       sql +=" AND IFNULL(orr.OPERATE_CSR1,'') != ''";			//待办人不为空

		List<Map<String, Object>> listBySql = this.findListBySql(sql, new Object[]{});
		return  listBySql;
	}

	@Override
	public String queryTodoer(String businessId) {
		Criteria c = super.getCriteria();
		c.add(Restrictions.like("businessOrderId",businessId));
		c.add(Restrictions.like("modifyStatus","2"));//待办
		c.add(Restrictions.like("isValid","1"));//有效
		List<OperateRecord> orList = this.findByCriteria(c);
		if (orList!=null && orList.size()>0){
			return orList.get(0).getOperater();
		}
		return "无";

	}

	@Override
	public void noticeSetInvalid(List<String> projIds, String stepId,String opType) {
		StringBuilder updateSql=new StringBuilder();
		updateSql.append("UPDATE OPERATE_RECORD SET MODIFY_STATUS='1' WHERE STEP_ID='"+stepId+"' AND OP_TYPE= '"+opType+"' AND MODIFY_STATUS='2' ");

		if (projIds!=null && projIds.size()>0){
			updateSql.append("AND PROJ_ID IN(");
			for (int i = 0; i <projIds.size() ; i++) {
				if (i!=projIds.size()-1){
					updateSql.append("'"+projIds.get(i)+"',");
				}else {
					updateSql.append("'"+projIds.get(i)+"'");
				}
			}
			updateSql.append(")");
			this.executeSql(updateSql.toString());
		}
	}

	@Override
	public void backStepSetNeedDealtWith(String busiessId, String projId, String stepId) {
		StringBuilder updateSql=new StringBuilder();
		updateSql.append("UPDATE OPERATE_RECORD SET MODIFY_STATUS='2' WHERE STEP_ID='"+stepId+"'");

		if (StringUtils.isNotBlank(projId)){
			updateSql.append(" AND PROJ_ID='"+projId+"'");
		}
		if (StringUtils.isNotBlank(busiessId)){
			updateSql.append(" AND BUSINESS_ORDER_ID='"+busiessId+"'");
		}
		if (StringUtils.isNotBlank(projId) || StringUtils.isNotBlank(busiessId)){
			this.executeSql(updateSql.toString());
		}

	}

	/**
	 * 受理申请时删除除市场派工外的所有操作记录
	 * @author fuliwei
	 * @date 2019/12/10
	 */
	@Override
	public void deleteByProjId(String projId) {
		if(StringUtils.isNotBlank(projId)){
			StringBuffer sql = new StringBuffer();
			//受理申请时删除除市场派工外的所有操作记录
			sql.append("delete from OperateRecord where projId='").append(projId).append("' and stepId !='11000'");
			super.executeHql(sql.toString());
		}
	}


	@Override
	public void changeBudgeterTodo(String projId, String orgBudgeterAuditId, String staffId, String staffName) {
		StringBuilder sql=new StringBuilder();
		List<String> paramList=new ArrayList<>();
		sql.append("UPDATE OPERATE_RECORD a SET a.OPERATE_CSR1=? ,a.OPERATER=? WHERE a.PROJ_ID=? AND a.OPERATE_CSR1=? AND a.MODIFY_STATUS!=1");
		paramList.add(","+staffId+",");
		paramList.add(staffName);
		paramList.add(projId);
		paramList.add(","+orgBudgeterAuditId+",");

		this.executeSql(sql.toString(),paramList.toArray());
	}
}
