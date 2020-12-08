package cc.dfsoft.project.biz.base.constructmanage.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import cc.dfsoft.project.biz.base.constructmanage.dao.ShutdownApprovalDao;
import cc.dfsoft.project.biz.base.constructmanage.dto.ShutdownApprovalReq;
import cc.dfsoft.project.biz.base.constructmanage.entity.ShutdownApproval;
import cc.dfsoft.project.biz.base.constructmanage.enums.ShutdownPushStatusEnum;
import cc.dfsoft.project.biz.base.constructmanage.enums.ShutdownTypeEnum;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;
import cc.dfsoft.uexpress.common.util.ResultUtil;
/**
 * 复工申报dao实现层
 * @author liaoyq
 *
 */
@Repository
public class ShutdownApprovalDaoImpl extends
		NewBaseDAO<ShutdownApproval, String> implements ShutdownApprovalDao {

	@Override
	public Map<String, Object> queryShutdownRecord(
			ShutdownApprovalReq shutdownApprovalReq) {
		StringBuffer sql = new StringBuffer();
		StringBuffer sqlCount = new StringBuffer();
		String filter="";
		sql.append("select *  from (select sr.SDR_ID sdrId, sr.SDR_NO sdrNo,sr.PROJ_NO projNo,sr.PROJ_ID projId ,sr.PROJ_NAME projName,sr.CU_NAME cuName,sr.CU_MANAGER_DEPT cuManagerDept,sr.SDR_REASON sdrReason,sr.SDR_PROCESS sdrProcess,").append(mysqlSqlConveter.funcConvertISO("mysql","sr.SDR_DATE",0)+" sdrDate,sr.CORP_NAME corpName,sr.SU_NAME suName ,sr.SDR_STATUS sdrStatus,"
				+ "sa.SDA_ID sdaId,"+mysqlSqlConveter.funcConvertISO("mysql","sa.REWORK_DATE",0)+" reworkDate,"+mysqlSqlConveter.funcConvertISO("mysql","sa.CU_APPROVAL_DATE",0)+" cuApprovalDate,sa.SU_ADVICE suAdvice,sa.SUCES suCes,"+mysqlSqlConveter.funcConvertISO("mysql","sa.SUCES_REVIEW_DATE",0)+" suCesReviewDate,sa.CUST_ADVICE custAdvice,sa.CUST_REPRESENT custRepresent,"+mysqlSqlConveter.funcConvertISO("mysql","sa.CUST_AUDIT_DATE",0)+" custAuditDate,").append(mysqlSqlConveter.rownumberConveter(" rn from "));
		
		filter= " SHUTDOWN_RECORD as sr left join  SHUTDOWN_APPROVAL as sa on sr.SDR_ID = sa.SDR_ID where sr.SDR_TYPE=?";
		sqlCount.append("select count(sr.SDR_ID) from ");
		
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(ShutdownTypeEnum.SHUTDOWN.getValue());//停工令
		//推送状态
		if(shutdownApprovalReq.getPushStatus()!=null){
			filter += " and sa.PUSH_STATUS=?";
			paramList.add(shutdownApprovalReq.getPushStatus());
		}
		//工程ID
		if(StringUtils.isNotBlank(shutdownApprovalReq.getProjId())){
			filter += " and sr.PROJ_ID=?";
			paramList.add(shutdownApprovalReq.getProjId());
		}
		//停复工记录Id
		if(StringUtils.isNotBlank(shutdownApprovalReq.getSdrId())){
			filter += " and sr.SDR_ID=?";
			paramList.add(shutdownApprovalReq.getSdrId());
		} 
		//停工编号
		if(StringUtils.isNotBlank(shutdownApprovalReq.getSdrNo())){
			filter += " and sr.SDR_NO=?";
			paramList.add(shutdownApprovalReq.getSdrNo());
		}
		//停工工序
		if(StringUtils.isNotBlank(shutdownApprovalReq.getSdrProcess())){
			filter += " and sr.SDR_PROCESS=?";
			paramList.add(shutdownApprovalReq.getSdrProcess());
		}
		
		//开始日期
		if(StringUtils.isNoneBlank(shutdownApprovalReq.getReworkDateStart())){
			filter += " and sa.REWORK_DATE>="+mysqlSqlConveter.dataOperate(shutdownApprovalReq.getReworkDateStart());
		}
		//结束日期
		if(StringUtils.isNoneBlank(shutdownApprovalReq.getReworkDateEnd())){
			filter += " and sa.REWORK_DATE<="+mysqlSqlConveter.dataOperate(shutdownApprovalReq.getReworkDateEnd());
		}
		// 数据库根据条件过滤记录数
		int filterCount = this.getCountBySql(sqlCount.toString()+filter,paramList.toArray());
		// 根据条件获取查询信息
		int start = shutdownApprovalReq.getStart()+1;
		int end = start+(shutdownApprovalReq.getLength()-1);
		sql.append(filter) ;
		sql.append(" order by sr.CREATE_DATE desc, "+shutdownApprovalReq.getSortName()+" "+shutdownApprovalReq.getSort()+") result");
		sql.append(" where result.rn between "+start+" and "+ end);
		
		List<Map<String, Object>> mapList = this.findListBySql(sql.toString(), paramList.toArray());
		
		// 返回结果
		return ResultUtil.pageResult(filterCount, shutdownApprovalReq.getDraw(),mapList);
	}

	/**
	 * 根据停工记录id查询复工报审信息
	 */
	@Override
	public ShutdownApproval queryBySdrId(String sdrId) {
		StringBuffer hql = new StringBuffer("from ShutdownApproval where sdrId='").append(sdrId).append("'");
		List<ShutdownApproval> list = super.findByHql(hql.toString());
		if(list!=null && list.size()>0){
			return list.get(0);
		}
		return null;
	}

	/**
	 * 查询复工报审是否已签完字
	 * 返回：true 已签完字，false 未签完字
	 * 
	 */
	@Override
	public boolean querySignComplete(String sdaId) {
		StringBuffer hql = new StringBuffer("from ShutdownApproval where sdaId='" +sdaId+"' and cuManager is not null and suCes is not null and custRepresent is not null ");
		List<ShutdownApproval> list = super.findByHql(hql.toString());
		if(list!=null&&list.size()>0){
			return true;//签完字
		}
		return false;//未签完字
	}

}
