package cc.dfsoft.project.biz.base.project.dao.impl;
import cc.dfsoft.project.biz.base.project.dao.ApplyDelayDao;
import cc.dfsoft.project.biz.base.project.dao.ProjectDao;
import cc.dfsoft.project.biz.base.project.dto.ApplyDelayReq;
import cc.dfsoft.project.biz.base.project.entity.ApplyDelay;
import cc.dfsoft.project.biz.base.settlement.enums.AuditResultEnum;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.biz.sys.dept.enums.DeptDivideEnum;
import cc.dfsoft.uexpress.biz.sys.dept.enums.PostTypeEnum;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.constant.DataFilerSetUpDto;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;
import cc.dfsoft.uexpress.common.util.ResultUtil;
import cc.dfsoft.uexpress.common.util.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
@Repository
public class ApplyDelayDaoImpl extends NewBaseDAO<ApplyDelay, String> implements ApplyDelayDao {

	@Resource
	ProjectDao projectDao;
	 
	@SuppressWarnings("unchecked")
	@Override
	public List<ApplyDelay> findADelayByStepId(String stepId,String projId) {
		
		Criteria c = super.getCriteria();
		 //工程编号
		 if(StringUtils.isNotBlank(stepId)){
			 c.add(Restrictions.like("stepId", stepId+"%"));
		 }
		//工程编号
		 if(StringUtils.isNotBlank(projId)){
			 c.add(Restrictions.eq("projId", projId));
		 }
		 c.setFetchMode("staff", org.hibernate.FetchMode.JOIN);  
		return c.list();
	}
	
	/**
	 * 延期申请列表查询
	 * @author fuliwei
	 * @createTime 2017年8月31日
	 * @param 
	 * @return
	 */
	@Override
	public Map<String, Object> queryApplyDelay(ApplyDelayReq req) throws ParseException {
		Criteria c = super.getCriteria();
		
		//工程编号
		if(StringUtils.isNotBlank(req.getProjNo())){
			c.add(Restrictions.eq("projNo",req.getProjNo()));
		}
		//工程id
		if(StringUtils.isNotBlank(req.getProjId())){
			c.add(Restrictions.eq("projId",req.getProjId()));
		}
		//步骤id
		if(StringUtils.isNotBlank(req.getStepId())){
			c.add(Restrictions.eq("stepId", req.getStepId()));
		}
		
		//工程名称
		if(StringUtils.isNotBlank(req.getProjName())){
			c.add(Restrictions.like("projName","%"+req.getProjName()+"%"));
		}
		
		//工程地点
		if(StringUtils.isNotBlank(req.getProjAddr())){
			c.add(Restrictions.like("projAddr","%"+req.getProjAddr()+"%"));
		}
		
		//审核状态
		if(StringUtils.isNotBlank(req.getAuditState())){
			c.add(Restrictions.eq("auditState", req.getAuditState()));
		}
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		String post=loginInfo.getPost();
		String [] postArray=post.split(",");
		
		//
		if(!DeptDivideEnum.DESIGN_INSTITUTE.getValue().equals(loginInfo.getDeptDivide())){
            Boolean flag =true;
			List<DataFilerSetUpDto> listCon = Constants.getDataFilterMapByKey(loginInfo.getDeptId()+"_"+req.getMenuId());
			StringBuffer filtersql = new StringBuffer();
			if(listCon!=null && listCon.size()>0){
				if(StringUtil.isNotBlank(listCon.get(0).getSupSql())){
					filtersql = filtersql.append(listCon.get(0).getSupSql());
					flag = false;
				}
			}

			StringBuffer sql= new StringBuffer();
			if(flag){
				//根据部门过滤 原来的代码
				sql = sql.append(" PROJ_ID in(select p.PROJ_ID from project p where 1=1 ");
				sql.append("and "+projectDao.addDeptIdLikeQuery(loginInfo.getDeptId(),loginInfo.getDeptTransfer()));
				if(post.contains(PostTypeEnum.SURVEYER.getValue())){
					sql.append(" and p.SURVEYER_ID='").append(loginInfo.getStaffId()).append("'");
				}
				sql.append(")");
			}else{
				sql = filtersql;
			}
			c.add(Restrictions.sqlRestriction(sql.toString()));
		}else{
			if(post.contains(PostTypeEnum.DESIGNER.getValue())){
				StringBuffer sql= new StringBuffer(" PROJ_ID in(select p.PROJ_ID from project p where p.designer_id ='").append(loginInfo.getStaffId()).append("')");
				c.add(Restrictions.sqlRestriction(sql.toString()));
			}
		}
		
		
		//用于字符串日期格式转换
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		 
		 //申请日期开始
		 if(StringUtils.isNotBlank(req.getApplyDateStart())){
			 c.add(Restrictions.ge("optTime", sdf.parse(req.getApplyDateStart())));
		 }
		 //申请日期结束
		 if(StringUtils.isNotBlank(req.getApplyDateEnd())){
			 c.add(Restrictions.le("optTime", sdf.parse(req.getApplyDateEnd())));
		 }
		// 数据库根据条件过滤记录数
		int filterCount = this.countByCriteria(c);
		// 根据条件获取查询信息
		List<ApplyDelay> list = this.findBySortCriteria(c, req);
		// 返回结果
		return ResultUtil.pageResult(filterCount, req.getDraw(),list);
		
	}

	@Override
	public BigDecimal getDelyDays(String projId,String stepId) {
		//查询参数集合
		List<Object> params = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder();
		sql.append("select SUM(DELAY_PERIOD) delayDays FROM apply_delay WHERE PROJ_ID = ? AND STEP_ID = ? AND AUDIT_STATE = ?");
		params.add(projId);
		params.add(stepId);
		params.add(AuditResultEnum.PASSED.getValue());
		Map<String, Object> result = this.findObjectBySql(sql.toString(), params.toArray());
		if(null==result.get("delayDays")){
			return BigDecimal.ZERO;
		}
		BigDecimal delayDays = new BigDecimal(result.get("delayDays").toString());
		return delayDays;
	}
	

}
