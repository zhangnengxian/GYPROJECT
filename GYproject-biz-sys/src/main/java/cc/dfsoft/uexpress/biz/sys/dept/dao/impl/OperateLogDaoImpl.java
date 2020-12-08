package cc.dfsoft.uexpress.biz.sys.dept.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cc.dfsoft.uexpress.biz.sys.dept.dao.OperateLogDao;
import cc.dfsoft.uexpress.biz.sys.dept.dto.ProjectTotalStatictisReq;
import cc.dfsoft.uexpress.biz.sys.dept.entity.OperateLog;
import cc.dfsoft.uexpress.biz.sys.dept.enums.PostTypeEnum;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;

/**
 * 操作日志DAO实现
 * @author cui
 *
 */
@Repository
public class OperateLogDaoImpl extends NewBaseDAO<OperateLog, String> implements OperateLogDao{
	
	/**
	 * 统计当前登陆人信息
	 * @author fuliwei
	 * @createTime 2016-12-12
	 * @return
	 */
	@Override
	public List<Map<String,Object>> queryMapStatistics(ProjectTotalStatictisReq projectTotalStatictisReq) {
		
		StringBuffer sql=new StringBuffer();
		List<Map<String, Object>> list=new ArrayList<>();
		Criteria c = super.getCriteria();
		sql.append("select o.staff_id staffId,o.operate_log_id operateLogId,o.xaxis xaxis,o.yaxis yaxis,o.operate_time operateTime ,s.staff_name staffName,s.photo_url photoUrl,s.post post,o.dept_id deptId,d.dept_name deptName from operate_log o left join staff s on o.staff_id=s.staff_id  left join department d on o.dept_id=d.dept_id where o.operate_log_id in");
		sql.append("(select t.operate_log_id from");
		sql.append(" (select max(o.operate_time), max(o.operate_log_id) operate_log_id,o.staff_id from operate_log o group by o.staff_id) t");
		sql.append(" )");
		sql.append(" and o.operate_type <> '2' and o.xaxis is not null");
		c.add(Restrictions.sqlRestriction(sql.toString()));
		/*
		// 数据库根据条件过滤记录数
		int filterCount = this.countByCriteria(c);
        
		// 根据条件获取查询信息
		List<OperateLog> list = this.findBySortCriteria(c, projectTotalStatictisReq);
		// 返回结果
		return ResultUtil.pageResult( filterCount, projectTotalStatictisReq.getDraw(),list);
		*/
		List<Map<String,Object>> result = super.findListBySql(sql.toString());
		
		if(result != null && result.size()>0){
			for(int i=0;i<result.size();i++){
				String post=(String) result.get(i).get("POST");
				result.get(i).remove("POST");
				result.get(i).put("POST", PostTypeEnum.valueof(post).getMessage());
			}
		}
		
		
		
		if(result!=null && result.size()>0){
			return result;
		}else{
			return list;
		}
		
		/*List sqlList = this.findBySql(sql.toString());
		
		for(int i = 0;i <sqlList.size();i++){
			map.pu
		}
		*/
	}
	

}
