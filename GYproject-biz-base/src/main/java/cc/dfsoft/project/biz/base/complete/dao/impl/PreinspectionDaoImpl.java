package cc.dfsoft.project.biz.base.complete.dao.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cc.dfsoft.project.biz.base.baseinfo.dao.BusinessPartnersDao;
import cc.dfsoft.project.biz.base.baseinfo.entity.BusinessPartners;
import cc.dfsoft.project.biz.base.complete.dao.PreinspectionDao;
import cc.dfsoft.project.biz.base.complete.dto.PreinspectionReq;
import cc.dfsoft.project.biz.base.complete.entity.Preinspection;
import cc.dfsoft.project.biz.base.design.enums.UnitTypeEnum;
import cc.dfsoft.project.biz.base.project.dao.ProjectDao;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;
import cc.dfsoft.uexpress.common.util.ResultUtil;

@Repository
public class PreinspectionDaoImpl extends NewBaseDAO<Preinspection, String> implements PreinspectionDao {

	@Resource
	BusinessPartnersDao businessPartnersDao;

	@Resource
	ProjectDao projectDao;
	@SuppressWarnings("unchecked")
	@Override
	public Preinspection findByProjId(String projId) {
		Criteria c = super.getCriteria();
		if(StringUtils.isNotBlank(projId)){
			 c.add(Restrictions.eq("projId",projId));
		 }
		c.add(Restrictions.eq("isDel","1"));
		List<Preinspection> preinspection = c.list();
		if(preinspection!=null && preinspection.size()>0){
			return preinspection.get(0);
		}
		return null;
	}

	@Override
	public List<Preinspection> findById(String projId) {
		StringBuffer hql = new StringBuffer("from Preinspection where projId='").append(projId).append("' and isDel='1'");
		List<Preinspection> result = super.findByHql(hql.toString());
		return result;
	}
	
	/**
	 * 自检单列表查询
	 * @author fuliwei
	 * @createTime 2017年8月4日
	 * @param 
	 * @return
	 * @throws ParseException 
	 */
	@Override
	public Map<String, Object> querySelInspection(PreinspectionReq req) throws ParseException {
		
		Criteria c=super.getCriteria();
		if(StringUtils.isNotBlank(req.getProjId())){
			c.add(Restrictions.eq("projId", req.getProjId()));
		}
		
		//工程编号
		if(StringUtils.isNotBlank(req.getProjNo())){
			c.add(Restrictions.eq("projNo",req.getProjNo()));
		}
		
		//施工合同
		if(StringUtils.isNotBlank(req.getConNo())){
			c.add(Restrictions.like("scNo","%"+req.getConNo()+"%"));
		}
		//合同编号
		if(StringUtils.isNotBlank(req.getConNo())){
			c.add(Restrictions.like("conNo","%"+req.getConNo()+"%"));
		}
		if(StringUtils.isNotBlank(req.getProjName())){
			c.add(Restrictions.like("projName","%"+req.getProjName()+"%"));
		}
		
		if(StringUtils.isNotBlank(req.getProjAddr())){
			c.add(Restrictions.like("projAddr","%"+req.getProjAddr()+"%"));
		}
		
		
		//是否打印
		 if(StringUtils.isNotBlank(req.getIsPrint())){
			 c.add(Restrictions.eq("isPrint", req.getIsPrint()));
		 }
		 
		//用于字符串日期格式转换
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		 //验收日期开始
		 if(StringUtils.isNotBlank(req.getPiDateStart())){
			 c.add(Restrictions.ge("piDate", sdf.parse(req.getPiDateStart())));
		 }
		 //验收日期结束
		 if(StringUtils.isNotBlank(req.getPiDateEnd())){
			 c.add(Restrictions.le("piDate", sdf.parse(req.getPiDateEnd())));
		 }

			//业务合作伙伴看自己的
			LoginInfo loginInfo = SessionUtil.getLoginInfo();
			if(StringUtils.isNotBlank(loginInfo.getDeptId())){
				BusinessPartners bp = businessPartnersDao.get(loginInfo.getDeptId());
				 if(bp!=null){
					//若登录人是分包方单位人员
					 if(UnitTypeEnum.CONSTRUCTION_UNIT.getValue().equals(bp.getUnitType())&& loginInfo.getPost().length()>0){
						 StringBuffer sql = projectDao.cuUnitFilter(loginInfo,loginInfo.getPost(), req.getMenuId());
						 c.add(Restrictions.sqlRestriction(sql.toString()));
					 }else if(UnitTypeEnum.SUPERVISION_UNIT.getValue().equals(bp.getUnitType())){
						//若登录人是监理单位
						 StringBuffer sql = projectDao.suUnitFilter(loginInfo, loginInfo.getPost(), req.getMenuId());
						 c.add(Restrictions.sqlRestriction(sql.toString()));
					 }else{
						 //其他业务合作伙伴-如检测单位
					 }
				 }else{
					//分公司ID
					 StringBuffer sql = new StringBuffer("proj_id in(select cp.proj_id from project cp where cp.corp_id like '").append(loginInfo.getCorpId()).append("%')");
					 c.add(Restrictions.sqlRestriction(sql.toString()));
				 }
			}
			c.add(Restrictions.eq("isDel","1"));
		// 数据库根据条件过滤记录数
		int filterCount = this.countByCriteria(c);

		// 根据条件获取查询信息 
		List<Preinspection> projectList = this.findBySortCriteria(c, req);
		
		// 返回结果
		return ResultUtil.pageResult( filterCount, req.getDraw(),projectList);
	}

	@Override
	public void deleteByprojId(String projId, String isDel) {
		StringBuffer hql = new StringBuffer("update Preinspection set isDel='").append(isDel).append("' where projId='").append(projId).append("'");
		this.executeHql(hql.toString());
	}

	@Override
	public List<Preinspection> findIsDelByProjId(String projId) {
		Criteria c=super.getCriteria();
		if(StringUtils.isNotBlank(projId)){
			c.add(Restrictions.eq("projId", projId));
		}
		c.add(Restrictions.eq("isDel","0"));
		c.addOrder(Order.desc("piDate"));
		return this.findByCriteria(c);
	}

}
