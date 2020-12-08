package cc.dfsoft.project.biz.base.complete.dao.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cc.dfsoft.project.biz.base.baseinfo.dao.BusinessPartnersDao;
import cc.dfsoft.project.biz.base.baseinfo.entity.BusinessPartners;
import cc.dfsoft.project.biz.base.complete.dao.SelfInspectionListDao;
import cc.dfsoft.project.biz.base.complete.dto.PreinspectionReq;
import cc.dfsoft.project.biz.base.complete.entity.SelfInspectionList;
import cc.dfsoft.project.biz.base.design.enums.UnitTypeEnum;
import cc.dfsoft.project.biz.base.project.dao.ProjectDao;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;
import cc.dfsoft.uexpress.common.util.ResultUtil;

@Repository
public class SelfInspectionListDaoImpl extends NewBaseDAO<SelfInspectionList, String> implements SelfInspectionListDao{

	@Resource
	BusinessPartnersDao businessPartnersDao;
	@Resource
	ProjectDao projectDao;
	@SuppressWarnings("unchecked")
	@Override
	public List<SelfInspectionList> findByprojectId(String projId) {
		 Criteria c = super.getCriteria();
		 if(StringUtils.isNotBlank(projId)){
			 c.add(Restrictions.eq("projId",projId));
		 }
		 //未删除的
		 c.add(Restrictions.eq("isDel","1"));
		 List<SelfInspectionList> selfInspectionList = c.list();
		return selfInspectionList;
	}
	
	/**
	 * 自检单列表查询-未删除的
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
		 //自检日期开始
		 if(StringUtils.isNotBlank(req.getSilDateStart())){
			 c.add(Restrictions.ge("silDate", sdf.parse(req.getSilDateStart())));
		 }
		 //自检日期结束
		 if(StringUtils.isNotBlank(req.getSilDateEnd())){
			 c.add(Restrictions.le("silDate", sdf.parse(req.getSilDateEnd())));
		 }

		//业务合作伙伴看自己的
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		String post = loginInfo.getPost();
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
				//公司ID
				 StringBuffer sql = new StringBuffer("proj_id in(select cp.proj_id from project cp where cp.corp_id like '").append(loginInfo.getCorpId()).append("%')");
				 c.add(Restrictions.sqlRestriction(sql.toString())); 
			 }
		}
		//未删除的
		 c.add(Restrictions.eq("isDel","1"));
		// 数据库根据条件过滤记录数
		int filterCount = this.countByCriteria(c);

		// 根据条件获取查询信息 
		List<SelfInspectionList> projectList = this.findBySortCriteria(c, req);
		
		// 返回结果
		return ResultUtil.pageResult( filterCount, req.getDraw(),projectList);
	}

	@Override
	public void deleteByProjID(String projId, String isDel) {
		StringBuffer hql = new StringBuffer("update SelfInspectionList set isDel='").append(isDel).append("' where projId='").append(projId).append("'");
		this.executeHql(hql.toString());
	}

	@Override
	public boolean isExistValid(String projId) {
		List<SelfInspectionList> list = this.findByprojectId(projId);
		if (list!=null && list.size()>0){
			return true;
		}
		return false;
	}

}
