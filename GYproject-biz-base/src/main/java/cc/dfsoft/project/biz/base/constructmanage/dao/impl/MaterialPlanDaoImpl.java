package cc.dfsoft.project.biz.base.constructmanage.dao.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cc.dfsoft.project.biz.base.constructmanage.dao.MaterialPlanDao;
import cc.dfsoft.project.biz.base.constructmanage.dto.MaterialPlanReq;
import cc.dfsoft.project.biz.base.constructmanage.entity.MaterialPlan;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;
import cc.dfsoft.uexpress.common.util.ResultUtil;
/**
 * 施工-材料计划
 * @author fuliwei 
 * @createTime 2017-01-18
 *
 */
@Repository
public class MaterialPlanDaoImpl extends NewBaseDAO<MaterialPlan,String> implements MaterialPlanDao{
	
	/**
	 * 材料计划列表查询
	 * @author fuliwei
	 * @createTime 2017-1-18
	 * @param  
	 * @return
	 */
	@Override
	public Map<String, Object> queryMaterialPlan(MaterialPlanReq MaterialPlanReq) throws ParseException {
		Criteria c = super.getCriteria();
		 //工程编号 
		if(StringUtils.isNotBlank(MaterialPlanReq.getProjNo())){
			c.add(Restrictions.eq("projNo",MaterialPlanReq.getProjNo()));
		}
		//工程名称
		if(StringUtils.isNotBlank(MaterialPlanReq.getProjName())){
			c.add(Restrictions.like("projName","%"+MaterialPlanReq.getProjName()+"%"));
		}
		//工程id
		if(StringUtils.isNotBlank(MaterialPlanReq.getProjId())){
			c.add(Restrictions.eq("projId",MaterialPlanReq.getProjId()));
		}
		if(StringUtils.isNotBlank(MaterialPlanReq.getIsFeedBack())){
			c.add(Restrictions.eq("isFeedBack",MaterialPlanReq.getIsFeedBack()));
		}
		//甲方代表
		if(StringUtils.isNotBlank(MaterialPlanReq.getBuilder())){
			c.add(Restrictions.like("builder","%"+MaterialPlanReq.getBuilder()+"%"));
		}
		//项目经理
		if(StringUtils.isNotBlank(MaterialPlanReq.getConstructionPm())){
			c.add(Restrictions.like("cuLegalRepresent","%"+MaterialPlanReq.getConstructionPm()+"%"));
		}
		//分包单位
		if(StringUtils.isNotBlank(MaterialPlanReq.getCuName())){
			 StringBuffer sql = new StringBuffer();
			 sql.append("proj_id in(select cp.proj_id from CONSTRUCTION_PLAN cp where cp.CU_ID = '").append(MaterialPlanReq.getCuName()).append("')");
			 c.add(Restrictions.sqlRestriction(sql.toString()));
		}
		//施工管理处
		if(StringUtils.isNotBlank(MaterialPlanReq.getConstructionUnit())){
			 StringBuffer sql = new StringBuffer();
			 sql.append("proj_id in(select cp.proj_id from CONSTRUCTION_PLAN cp where cp.MANAGEMENT_ID = '").append(MaterialPlanReq.getConstructionUnit()).append("')");
			 c.add(Restrictions.sqlRestriction(sql.toString()));
		}
		//反馈人
		if(StringUtils.isNotBlank(MaterialPlanReq.getFeedBacker())){
			c.add(Restrictions.like("feedBacker","%"+MaterialPlanReq.getFeedBacker()+"%"));
		}
		
		//是否标记导出
		if(StringUtils.isNotBlank(MaterialPlanReq.getIsExport())){
			c.add(Restrictions.eq("isExport",MaterialPlanReq.getIsExport()));
		}
				
		//用于字符串日期格式转换
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		//反馈开始日期
		if(StringUtils.isNotBlank(MaterialPlanReq.getApplicationDateStart())){
			c.add(Restrictions.ge("feedBackDate", sdf.parse(MaterialPlanReq.getApplicationDateStart())));
		}
		//反馈结束日期
		if(StringUtils.isNotBlank(MaterialPlanReq.getApplicationDateEnd())){
			c.add(Restrictions.le("feedBackDate", sdf.parse(MaterialPlanReq.getApplicationDateEnd())));
		}
		//计划领用开始日期
		if(StringUtils.isNotBlank(MaterialPlanReq.getPlanReceiveDateStart())){
			c.add(Restrictions.ge("modifyReceiveDate", sdf.parse(MaterialPlanReq.getPlanReceiveDateStart())));
		}
		//计划领用开始日期
		if(StringUtils.isNotBlank(MaterialPlanReq.getPlanReceiveDateEnd())){
			c.add(Restrictions.le("modifyReceiveDate", sdf.parse(MaterialPlanReq.getPlanReceiveDateEnd())));
		}
		
		// 数据库根据条件过滤记录数
		int filterCount = this.countByCriteria(c);

		// 根据条件获取查询信息
		List<MaterialPlan> materialPlanList = this.findBySortCriteria(c, MaterialPlanReq);
		
		// 返回结果
		return ResultUtil.pageResult( filterCount, MaterialPlanReq.getDraw(),materialPlanList);
	}
	
	/**
	 * 根据工程Id查材料计划详述
	 * @author fuliwei
	 * @createTime 2017-1-18
	 * @param  projId
	 * @return MaterialPlan
	 */
	@Override
	public MaterialPlan findByProjId(String projId) {
		Criteria c = super.getCriteria();
		if(StringUtils.isNotBlank(projId)){
			c.add(Restrictions.eq("projId", projId));
			List<MaterialPlan> list = this.findByCriteria(c);
			if(list != null && list.size()>0){
				return list.get(0);
			}
		}
		return null;
	}
	
	/**
	 * 通过领用时间删除材料计划
	 * @author fuliwei
	 * @createTime 2017年2月18日
	 * @param mgDate
	 * @return
	 */
	@Override
	public void deleteByMgDate(Date mgDate) {
		SimpleDateFormat simple=new SimpleDateFormat("yyyy-MM-dd");
		//updateto_date
		//StringBuffer sql=new StringBuffer("delete from MaterialPlan where create_date>=").append(mysqlSqlConveter.dataOperate(simple.format(mgDate)).append(" 00:00:00','yyyy-MM-dd hh24:mi:ss')");
		StringBuffer sql=new StringBuffer("delete from MaterialPlan where create_date>=").append(mysqlSqlConveter.dataOperate(simple.format(mgDate)+" 00:00:00"));
		sql.append(" and create_date<=").append(mysqlSqlConveter.dataOperate(simple.format(mgDate)+" 23:59:59"));
		super.executeHql(sql.toString());
	}
	
	
}
