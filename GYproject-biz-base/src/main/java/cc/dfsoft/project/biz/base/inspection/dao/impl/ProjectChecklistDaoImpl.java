package cc.dfsoft.project.biz.base.inspection.dao.impl;

import cc.dfsoft.project.biz.base.auditina.dto.AuditInsReq;
import cc.dfsoft.project.biz.base.inspection.dao.ProjectChecklistDao;
import cc.dfsoft.project.biz.base.inspection.dto.ProjectChecklistQueryReq;
import cc.dfsoft.project.biz.base.inspection.entity.ProjectChecklist;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;
import cc.dfsoft.uexpress.common.util.ResultUtil;
import cc.dfsoft.uexpress.common.util.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
/**
 * 工程报验单Dao实现
 * @author Administrator
 *
 */
@Repository
public class ProjectChecklistDaoImpl extends NewBaseDAO<ProjectChecklist, String> implements ProjectChecklistDao{

	@Override
	public Map<String, Object> queryProjectChecklist(ProjectChecklistQueryReq listQueryReq) throws ParseException {
		Criteria c = super.getCriteria();
		//删除标记，1表示未删除，0表示已删除，只查找未删除的，已做删除标记的不查找
		c.add(Restrictions.eq("pcFlag","1"));
		//工程Id
		c.add(Restrictions.eq("projId",listQueryReq.getProjId()));
		//c.add(Restrictions.or(Restrictions.eq("flag",0),Restrictions.eq("flag",1),Restrictions.eq("flag",3)));
		//报验单id
		if(StringUtils.isNotBlank(listQueryReq.getPcDesId())){
			c.add(Restrictions.eq("pcDesId",listQueryReq.getPcDesId()));
		}
		//工程编号
		if(StringUtils.isNotBlank(listQueryReq.getProjNo())){
			c.add(Restrictions.eq("projNo",listQueryReq.getProjNo()));
		}
		//测试放线部位
		if(StringUtils.isNotBlank(listQueryReq.getSlPart())){
			c.add(Restrictions.like("slPart","%"+listQueryReq.getSlPart()+"%"));
		}
		//依据材料页数
		if(StringUtils.isNotBlank(listQueryReq.getSlBasePage())){
			c.add(Restrictions.eq("slBasePage",listQueryReq.getSlBasePage()));
		}
		//施工工序
		if(StringUtils.isNotBlank(listQueryReq.getProcess())){
			c.add(Restrictions.like("process","%"+listQueryReq.getProcess()+"%"));
		}
		// 数据库根据条件过滤记录数
		int filterCount = this.countByCriteria(c);

		// 根据条件获取查询信息
		List<ProjectChecklist> list = this.findBySortCriteria(c, listQueryReq);

		// 返回结果
		return ResultUtil.pageResult(filterCount, listQueryReq.getDraw(),list);
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<ProjectChecklist> getByPcId(String pcId){
		// TODO Auto-generated method stub
		StringBuffer hql = new StringBuffer();
		hql.append("from ProjectChecklist p where p.pcId = '").append(pcId).append("'");

		return super.findByHql(hql.toString());
	}

	/**
	 * 按工程id和报验单ID查询
	 * @param
	 * @return
	 */
	@Override
	public ProjectChecklist viewProjectCheckList(String projId, String pcId) {
		Criteria criteria = super.getCriteria();
		//工程id
		if(StringUtils.isNotBlank(projId)){
			criteria.add(Restrictions.eq("projId",projId));
		}
		if(StringUtils.isNotBlank(pcId)){
			criteria.add(Restrictions.eq("pcId",pcId));
		}

		List<ProjectChecklist> list = this.findByCriteria(criteria);
		if (list !=null && list.size()>0) {
			return list.get(0);
		}
		return null;
	}

/**
 * @MethodDesc: 作废使用updateFlagByPcId1方法
 * @Author zhangnx
 * @Date 2019/1/10 14:20
 * @Param
 * @Return
 */
	@Override
	public void updateFlagByPcId(String pcId, Integer flag) {
		//
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		String sql = "update PROJECT_CHECKLIST set flag='" + flag + "'";
		//点完成时候保存
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if(flag == 1){
			sql += " ,FINISHED_DATE = concat(IFNULL(FINISHED_DATE,''),'"+format.format(this.getDatabaseDate())+",')";
			sql += " ,FINISHED_OPR = concat(IFNULL(FINISHED_OPR,''),'"+loginInfo.getStaffId()+",')";
		}
		//重置
		if(flag == 0){
			sql += " ,RESET_DATE = concat(IFNULL(RESET_DATE,''),'"+format.format(this.getDatabaseDate())+",')";
		}
		sql += " where PC_ID='" + pcId + "'";
		boolean res = this.executeSql(sql);
		System.err.println(res);
	}



	/**
	 * @MethodDesc: 报验推送或审核
	 * @Author zhangnx
	 * @Date 2019/1/10 13:48
	 */
	@Override
	public void updateFlagByPcId1(ProjectChecklist proChe) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		if(proChe.getFlag().equals(1))propertySet(proChe,format);//不需要审核或最后一级审核
		if(proChe.getFlag().equals(2))propertySet(proChe,format);//需要审核

		if(proChe.getFlag().equals(0)){//重置
			String resetDate=StringUtil.isNotBlank(proChe.getResetDate())?proChe.getResetDate():"";
			proChe.setResetDate(resetDate+format.format(this.getDatabaseDate())+",");
		}

		this.update(proChe);
	}


	public void propertySet(ProjectChecklist proChe,SimpleDateFormat format){
		LoginInfo loginInfo = SessionUtil.getLoginInfo();

		String finishedDate=StringUtil.isNotBlank(proChe.getFinishedDate())?proChe.getFinishedDate():"";
		String finishedOpr=StringUtil.isNotBlank(proChe.getFinishedOpr())?proChe.getFinishedOpr():"";

		proChe.setFinishedDate(finishedDate+format.format(this.getDatabaseDate())+",");
		proChe.setFinishedOpr(finishedOpr+loginInfo.getStaffId()+",");
	}




	@Override
	public List<ProjectChecklist> findByProjIdAndDesId(String projId,
													   String pcDesId,Integer flag) {
		Criteria criteria = super.getCriteria();
		//过滤掉已经标记为删除的报验记录,1表示未删除，0表示已经删除
		criteria.add(Restrictions.eq("pcFlag","1"));
		//工程id
		if(StringUtils.isNotBlank(projId)){
			criteria.add(Restrictions.eq("projId",projId));
		}
		if(StringUtils.isNotBlank(pcDesId)){
			criteria.add(Restrictions.eq("pcDesId",pcDesId));
		}
		if(flag!=null){
			criteria.add(Restrictions.eq("flag", flag));
		}
		List<ProjectChecklist> list = this.findByCriteria(criteria);
		return list;
	}


	@Override
	/**删除报验列表记录
	 *
	 * @author 王会军
	 * @createTime 2018-01-24
	 */
	public void deleteListById(String pcId) {
		// TODO Auto-generated method stub
		String sql = "update  project_checklist set pc_flag = 0 where pc_id = "+pcId;
		super.executeSql(sql);

	}

	@Override
	public void sendListById(String pcId) {
		String sql = "update  project_checklist set flag = 1 where pc_id = "+pcId;
		super.executeSql(sql);
	}

	@Override
	public Map<String, Object> getDataList(AuditInsReq listQueryReq) throws ParseException {
		Criteria c = super.getCriteria();
		//删除标记，1表示未删除，0表示已删除，只查找未删除的，已做删除标记的不查找
		c.add(Restrictions.eq("pcFlag","1"));
		//工程Id
		if(StringUtils.isNotBlank(listQueryReq.getProjId())){
			c.add(Restrictions.eq("projId",listQueryReq.getProjId()));
		}
		//状态默认2
		String flag = "2";
		if(Constants.getConsByKeyAndId(Constants.CHECK_STATUS,"100303")!=null){
			flag = String.valueOf(Constants.getConsByKeyAndId(Constants.CHECK_STATUS,"100303").get("CNVALUE"));
		}
		c.add(Restrictions.eq("flag",Integer.valueOf(flag)));
		//工程编号
		if(StringUtils.isNotBlank(listQueryReq.getProjNo())){
			c.add(Restrictions.eq("projNo",listQueryReq.getProjNo()));
		}

		//施工工序
		if(StringUtils.isNotBlank(listQueryReq.getProcess())){
			c.add(Restrictions.like("process","%"+listQueryReq.getProcess()+"%"));
		}
		// 数据库根据条件过滤记录数
		int filterCount = this.countByCriteria(c);

		// 根据条件获取查询信息
		List<ProjectChecklist> list = this.findBySortCriteria(c, listQueryReq);

		// 返回结果
		return ResultUtil.pageResult(filterCount, listQueryReq.getDraw(),list);
	}

	@Override
	public void updateData(String pcId) {
		String sql = "update PROJECT_CHECKLIST set FLAG = 3 where PC_ID = ?";
		this.executeSql(sql,new Object[]{pcId});
	}


	/**
	 * 查询是否完成签字
	 * @author fuliwei
	 * @date 2019/3/15
	 * @param
	 * @return
	 */
	@Override
	public String checkIsFinishSign(String pcId, String tableName) {
		StringBuffer hql=new StringBuffer();
		hql.append("select count(*) from ").append(tableName).append(" where pc_id='").
				append(pcId).append("' and ( is_finish_sign!='").append(Constants.YES).append("' or is_finish_sign is null)");
		hql.append(" union ");
	    hql.append("select count(*) from project_checklist where pc_id='").
				append(pcId).append("' and ( is_finish_sign!='").append(Constants.YES).append("' or is_finish_sign is null)");
		List hqlCurrentList = this.findBySql(hql.toString());
		if(hqlCurrentList!=null && hqlCurrentList.size()>0){
			for(int j = 0;j < hqlCurrentList.size();j ++){
				Object obj=(Object) hqlCurrentList.get(j);
				int a=Integer.parseInt(obj.toString());
				if(a>0){
					return Constants.OPERATE_RESULT_FAILURE;
				}
			}
		}
		return Constants.OPERATE_RESULT_SUCCESS;
	}
	/**
	 * 检查报验单是否完成签字
	 * @author fuliwei
	 * @date 2019/3/15
	 * @param
	 * @return
	 */
	@Override
	public String checkListIsFinishSign(String pcId) {
		StringBuffer hql=new StringBuffer();
		hql.append("select count(*) from project_checklist where pc_id='").
				append(pcId).append("' and ( is_finish_sign!='").append(Constants.YES).append("' or is_finish_sign is null)");
		List hqlCurrentList = this.findBySql(hql.toString());
		if(hqlCurrentList!=null && hqlCurrentList.size()>0){
			for(int j = 0;j < hqlCurrentList.size();j ++){
				Object obj=(Object) hqlCurrentList.get(j);
				int a=Integer.parseInt(obj.toString());
				if(a>0){
					return Constants.OPERATE_RESULT_FAILURE;
				}
			}
		}
		return Constants.OPERATE_RESULT_SUCCESS;
	}
}
