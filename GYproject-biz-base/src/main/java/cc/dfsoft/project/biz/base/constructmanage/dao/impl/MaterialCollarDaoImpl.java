package cc.dfsoft.project.biz.base.constructmanage.dao.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import cc.dfsoft.project.biz.base.constructmanage.dao.MaterialCollarDao;
import cc.dfsoft.project.biz.base.constructmanage.entity.MaterialCollar;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;

@Repository
public class MaterialCollarDaoImpl extends NewBaseDAO<MaterialCollar,String> implements MaterialCollarDao{
	
	/**
	 * 材料计划明细列表查询
	 * @author fuliwei
	 * @createTime 2017-2-17
	 * @param String projId,Date createDate
	 * @return Map<String,Object>
	 */
	@Override
	public List queryMaterialPlanDeatilList(String projId,String createDate) {
		//SimpleDateFormat simple=new SimpleDateFormat("yyyy-MM-dd");
		StringBuffer sql=new StringBuffer();
		//updateto_date
		//sql.append("select sum(MC_NUM) sum,BM_NAME name from MATERIAL_COLLAR  where MG_DATE>=to_date('").append(createDate).append(" 00:00:00','yyyy-MM-dd hh24:mi:ss')");
		sql.append("select sum(MC_NUM) sum,BM_NAME name from MATERIAL_COLLAR  where MG_DATE>=").append(mysqlSqlConveter.dataOperate(createDate+" 00:00:00"));
		sql.append(" and MG_DATE<=").append(mysqlSqlConveter.dataOperate(createDate+" 23:59:59"));
		sql.append(" and proj_id='").append(projId).append("' group by BM_NAME");
		List sqlList = this.findBySql(sql.toString());
		return sqlList;
	}
	
}
