package cc.dfsoft.project.biz.base.constructmanage.dao.impl;

import cc.dfsoft.project.biz.base.constructmanage.dao.ProgressDao;
import cc.dfsoft.project.biz.base.constructmanage.dto.ProgressQueryReq;
import cc.dfsoft.project.biz.base.constructmanage.entity.Progress;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;
import cc.dfsoft.uexpress.common.util.ResultUtil;
import cc.dfsoft.uexpress.common.util.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.List;
import java.util.Map;

@Repository
public class ProgressDaoImpl extends NewBaseDAO<Progress,String> implements ProgressDao{

	@Override
	public Map<String, Object> queryProgress(ProgressQueryReq progressQueryReq) {
		
		StringBuffer sql = new StringBuffer();
		//sql.append("select * from (");
		//updatenvl
		//sql.append("select rownum rn,p.proj_id projId,p.proj_name projName,p.proj_scale_des projScaleDes,p.proj_no projNo,p.qu_id quId,p.progress_id progressId,p.nuit_project nuitProject,p.sq_unit sqUnit,p.all_progress_num allProgressNum,p.heap_progress_num heapProgressNum,p.this_progress_num thisProgressNum,").append(mysqlSqlConveter.funcNvl2()).append("(p.this_progress_num,'','') thisProgressNumNvl, p.finish_progress finishProgress from progress p,");
		//
		//sql.append("select  p.proj_id projId,p.proj_name projName,p.proj_scale_des projScaleDes,p.proj_no projNo,p.qu_id quId,p.progress_id progressId,p.nuit_project nuitProject,p.sq_unit sqUnit,p.all_progress_num allProgressNum,p.heap_progress_num heapProgressNum,p.this_progress_num thisProgressNum,").append(mysqlSqlConveter.funcNvl2()).append("(p.this_progress_num,'','') thisProgressNumNvl, p.finish_progress finishProgress from progress p,");
		//修改方式2
		sql.append("select ").append(mysqlSqlConveter.rownumberConveter(" rn,  p.proj_id projId,p.proj_name projName,p.proj_scale_des projScaleDes,p.proj_no projNo,p.register_Date registerDate,p.qu_id quId,p.progress_id progressId,p.nuit_project nuitProject,p.sq_unit sqUnit,p.all_progress_num allProgressNum,p.heap_progress_num heapProgressNum,p.this_progress_num thisProgressNum,"+mysqlSqlConveter.funcNvl2()+"(p.this_progress_num,'','') thisProgressNumNvl, p.finish_progress finishProgress from ")).append(" progress p,");
		sql.append("(select  b.nuit_project nuitProject,max(b.register_date) registerDate from PROGRESS b  where 1=1 ");
		//工程id
		if(StringUtils.isNotBlank(progressQueryReq.getProjId())){
			sql.append(" and  b.proj_id='").append(progressQueryReq.getProjId()).append("'");
		}
		//截止日期
		if(StringUtils.isNotBlank(progressQueryReq.getRegisterDate())){
			//updateto_date
			//sql.append(" and b.register_date<= to_date('").append(progressQueryReq.getRegisterDate()+" 23:59:59").append("','YYYY-MM-DD hh24:mi:ss')");
			sql.append(" and b.register_date<=").append(mysqlSqlConveter.dataOperate(progressQueryReq.getRegisterDate()+" 23:59:59"));
		}
		sql.append(" group by b.nuit_project) t");
		sql.append(" where p.nuit_project = t.nuitProject and p.register_date=t.registerDate ");
		
		//分步分项名称
		if(StringUtils.isNotBlank(progressQueryReq.getNuitProject())){
			sql.append(" and p.nuit_project like '%").append(progressQueryReq.getNuitProject()).append("%'");
		}
		//sql.append(") total where 1=1");
		List<Map<String,Object>> countList = super.findListBySql(sql.toString());
		int filterCount =  countList==null?0:countList.size();
		/*sql.append(" and total.rn>").append(progressQueryReq.getStart());
		sql.append(" and total.rn<=").append(progressQueryReq.getLength()+progressQueryReq.getStart());*/
		/*sql.append(" order by ").append(progressQueryReq.getSortName()).append(" ").append(progressQueryReq.getSort());*/
		List<Map<String,Object>> list = super.findListBySql(sql.toString());
		// 返回结果
		return ResultUtil.pageResult( filterCount, progressQueryReq.getDraw(),list);
	}

	@Override
	public String queryTotalProgress(ProgressQueryReq progressQueryReq) {
		StringBuffer sql = new StringBuffer();
		sql.append("select sum(p.all_progress_num) allProgressNum,sum(p.heap_progress_num) heapProgressNum").
		append(" from  progress p,(select  b.nuit_project nuitProject, max(b.register_date) registerDate  from PROGRESS b  where proj_id='").
		append(progressQueryReq.getProjId()).append("' and b.register_date<= ").append(mysqlSqlConveter.dataOperate(progressQueryReq.getRegisterDate()+" 23:59:59")).append("group by b.nuit_project) t ").
		append(" where p.nuit_project = t.nuitProject and p.register_date=t.registerDate ");
		
		/*sql.append("select f.allProgressNum-f.heapprogressnum  allProgressNum,f.heapProgressNum heapProgressNum from(").
		append("select sum(p.all_progress_num) allProgressNum,sum(p.heap_progress_num) heapProgressNum").
		append(" from  progress p,(select  b.nuit_project nuitProject, max(b.register_date) registerDate  from PROGRESS b  group by b.nuit_project) t ").*/
		//append(" where p.nuit_project = t.nuitProject ");
		//工程id
		/*if(StringUtils.isNotBlank(progressQueryReq.getProjId())){
			sql.append(" and  p.proj_id='").append(progressQueryReq.getProjId()).append("'");
		}*/
		//截止日期
		/*if(StringUtils.isNotBlank(progressQueryReq.getRegisterDate())){
			sql.append(" and p.register_date<= to_date('").append(progressQueryReq.getRegisterDate()+" 23:59:59").append("','YYYY-MM-DD hh24:mi:ss')");
		}*/
		//sql.append(") f");
		List<Map<String,Object>> list = super.findListBySql(sql.toString());
		
		//查询工程总进度
		StringBuffer hql=new StringBuffer();
		//hql.append(" select sum(sq_num) allProgressNum,sum(progress_anum) heapProgressNum from PROGRESS where proj_id='").append(projId).append("' and audit_status is null");
		hql.append(" SELECT sum(c.ALL_PROGRESS_NUM) allProgressNum,sum(c.HEAP_PROGRESS_NUM) heapProgressNum FROM (SELECT * from (SELECT p.ALL_PROGRESS_NUM,p.HEAP_PROGRESS_NUM,p.QU_ID FROM	progress p where proj_id='").append(progressQueryReq.getProjId()).append("'").append(" ORDER BY p.REGISTER_DATE DESC ) t GROUP BY t.QU_ID) as c");
		List<Map<String,Object>> sumList = super.findListBySql(hql.toString());
		
		
		if(list!=null && list.size()>0 && sumList!=null && sumList.size()>0){
			Map<String,Object> m = list.get(0);
			Map<String,Object> sumMap = sumList.get(0);
			if(m.get("allProgressNum") == null || m.get("heapProgressNum") == null){
				return "0%";
			}
			if("0".equals(((BigDecimal)m.get("allProgressNum")).toString()) || "0".equals(((BigDecimal)m.get("heapProgressNum")).toString())){
				return "0%";
			}else{
				BigDecimal heapProgress = (BigDecimal)m.get("heapProgressNum");
				BigDecimal allProgress = (BigDecimal)sumMap.get("allProgressNum");
				NumberFormat nf = NumberFormat.getInstance();
				nf.setMaximumFractionDigits(2);
				nf.setMinimumFractionDigits(2);
				return nf.format((heapProgress.doubleValue()/allProgress.doubleValue())*100)+"%";
			}
		}else{
			return "0%";
		}
	}

	@Override
	public void deleteByProjId(String projId) {
		StringBuffer sql = new StringBuffer("delete from PROGRESS  WHERE PROJ_ID='").append(projId).append("'");
		super.executeSql(sql.toString());
	}
	
	/**
	 * 工程表查询工程总进度 
	 * @author fuliwei
	 * @createTime 2017年2月11日
	 * @param progressQueryReq
	 * @return String
	 */
	@Override
	public String queryProjectTotalProgress(String projId) {
		StringBuffer sql=new StringBuffer();
		//sql.append(" select sum(sq_num) allProgressNum,sum(progress_anum) heapProgressNum from PROGRESS where proj_id='").append(projId).append("' and audit_status is null");
		sql.append(" SELECT sum(c.ALL_PROGRESS_NUM) allProgressNum,sum(c.HEAP_PROGRESS_NUM) heapProgressNum FROM (SELECT * from (SELECT p.ALL_PROGRESS_NUM,p.HEAP_PROGRESS_NUM,p.QU_ID FROM	progress p where proj_id='").append(projId).append("'").append(" ORDER BY p.REGISTER_DATE DESC ) t GROUP BY t.QU_ID) as c");
		List<Map<String,Object>> list = super.findListBySql(sql.toString());
		if(list!=null && list.size()>0){
			Map<String,Object> m = list.get(0);
			if(m.get("allProgressNum") == null || m.get("heapProgressNum") == null){
				return "0%";
			}
			if("0".equals(((BigDecimal)m.get("allProgressNum")).toString()) || "0".equals(((BigDecimal)m.get("heapProgressNum")).toString())){
				return "0%";
			}else{
				BigDecimal heapProgress = (BigDecimal)m.get("heapProgressNum");
				BigDecimal allProgress = (BigDecimal)m.get("allProgressNum");
				NumberFormat nf = NumberFormat.getInstance();
				nf.setMaximumFractionDigits(2);
				nf.setMinimumFractionDigits(2);
				return nf.format((heapProgress.doubleValue()/allProgress.doubleValue())*100)+"%";
			}
		}else{
			return "0%";
		}
	}

	@Override
	public Progress queryGraphicProgress(String projId) {
		Criteria c=this.getCriteria();
		if(StringUtil.isNotBlank(projId)){
			c.add(Restrictions.eq("projId", projId));
		}
		c.addOrder(Order.desc("registerDate"));
		List<Progress> list=this.findByCriteria(c);
		if(list!=null && list.size()>0){
			return list.get(0);
		}
		return new Progress();
	}

}
