package cc.dfsoft.project.biz.base.settlement.dao.Impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cc.dfsoft.project.biz.base.baseinfo.dao.BusinessPartnersDao;
import cc.dfsoft.project.biz.base.baseinfo.entity.BusinessPartners;
import cc.dfsoft.project.biz.base.settlement.dao.DrawingApplyDao;
import cc.dfsoft.project.biz.base.settlement.dto.DrawingApplyReq;
import cc.dfsoft.project.biz.base.settlement.entity.DrawingApply;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;
import cc.dfsoft.uexpress.common.util.ResultUtil;

/**
 * 电子图审核
 * @author fuliwei
 *
 */

@Repository
public class DrawingApplyDaoImpl extends NewBaseDAO<DrawingApply, String> implements DrawingApplyDao{
	@Resource
	BusinessPartnersDao businessPartnersDao;
	/**
	 * 通过工程id查询详述
	 * @author fuliwei
	 * @createTime 2017年8月7日
	 * @param 
	 * @return
	 */
	@Override
	public List<DrawingApply> findByProjId(String projId) {
		Criteria c=super.getCriteria();
		if(StringUtils.isNotBlank(projId)){
			c.add(Restrictions.eq("projId", projId));
			List<DrawingApply> list=this.findByCriteria(c);
			return list;		
		}
		return null;
	}
	
	/**
	 * 查询竣工图申请单列表
	 * @author fuliwei
	 * @createTime 2017年8月7日
	 * @param 
	 * @return
	 * @throws ParseException 
	 */
	@Override
	public Map<String, Object> queryDrawingApply(DrawingApplyReq req) throws ParseException {
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		Criteria c=super.getCriteria();
		if(StringUtils.isNotBlank(req.getProjId())){
			c.add(Restrictions.eq("projId", req.getProjId()));
		}
		
		if(StringUtils.isNotBlank(req.getProjNo())){
			c.add(Restrictions.eq("projNo", req.getProjNo()));
		}
		
		//工程名称
		if(StringUtils.isNotBlank(req.getProjName())){
			c.add(Restrictions.like("projName","%"+req.getProjName()+"%"));
		}
		
		//审核状态
		if(StringUtils.isNotBlank(req.getAuditState())){
			c.add(Restrictions.eq("auditState", req.getAuditState()));
		}
		
		//申请单编号
		if(StringUtils.isNotBlank(req.getApplyNo())){
			c.add(Restrictions.like("applyNo","%"+ req.getApplyNo()+"%"));
		}
		
		//用于字符串日期格式转换
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		//申请开始日期
		if(StringUtils.isNotBlank(req.getApplyDateStart())){
			c.add(Restrictions.ge("applyDate", sdf.parse(req.getApplyDateStart())));
		}
		//申请结束日期
		if(StringUtils.isNotBlank(req.getApplyDateEnd())){
			c.add(Restrictions.le("applyDate", sdf.parse(req.getApplyDateEnd())));
		}
		//分公司ID
		StringBuffer sql = new StringBuffer("proj_id in(select cp.proj_id from project cp where cp.CORP_ID like '").append(loginInfo.getCorpId()).append("%')");
		c.add(Restrictions.sqlRestriction(sql.toString())); 
		int filterCount = this.countByCriteria(c);

		
		// 根据条件获取查询信息
		List<DrawingApply> drawingApplyList = this.findBySortCriteria(c, req);	
		// 返回结果
		return ResultUtil.pageResult(filterCount, req.getDraw(),drawingApplyList);
	
	}

	@Override
	public List<DrawingApply> queryDrawingApplyNotice(DrawingApplyReq req) throws ParseException {
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		Criteria c=super.getCriteria(); 
		if(StringUtils.isNotBlank(req.getProjId())){
			c.add(Restrictions.eq("projId", req.getProjId()));
		}
		
		if(StringUtils.isNotBlank(req.getProjNo())){
			c.add(Restrictions.eq("projNo", req.getProjNo()));
		}
		
		//工程名称
		if(StringUtils.isNotBlank(req.getProjName())){
			c.add(Restrictions.like("projName","%"+req.getProjName()+"%"));
		}
		
		//审核状态
		if(StringUtils.isNotBlank(req.getAuditState())){
			c.add(Restrictions.eq("auditState", req.getAuditState()));
		}
		
		//申请单编号
		if(StringUtils.isNotBlank(req.getApplyNo())){
			c.add(Restrictions.like("applyNo","%"+ req.getApplyNo()+"%"));
		}
		
		//用于字符串日期格式转换
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		//申请开始日期
		if(StringUtils.isNotBlank(req.getApplyDateStart())){
			c.add(Restrictions.ge("applyDate", sdf.parse(req.getApplyDateStart())));
		}
		//申请结束日期
		if(StringUtils.isNotBlank(req.getApplyDateEnd())){
			c.add(Restrictions.le("applyDate", sdf.parse(req.getApplyDateEnd())));
		}
		//分包方单位人员登录时，只可看自己相关项目
		 BusinessPartners bp =  businessPartnersDao.get(loginInfo.getDeptId());
		 if(bp==null){
			 //分公司ID
			 StringBuffer sql = new StringBuffer("proj_id in(select cp.proj_id from project cp where cp.CORP_ID like '").append(loginInfo.getCorpId()).append("%')");
			 c.add(Restrictions.sqlRestriction(sql.toString())); 
		 }else{
			 //分包单位
			 StringBuffer sql = new StringBuffer("proj_id in(select cp.proj_id from CONSTRUCTION_PLAN cp where cp.cu_id = '").append(loginInfo.getDeptId()).append("')");
			 c.add(Restrictions.sqlRestriction(sql.toString())); 
		 }
		// 根据条件获取查询信息
		return this.findBySortCriteria(c, req);	
	}

}
