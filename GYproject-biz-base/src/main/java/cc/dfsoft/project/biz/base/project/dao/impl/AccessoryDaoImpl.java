package cc.dfsoft.project.biz.base.project.dao.impl;
import cc.dfsoft.project.biz.base.baseinfo.dao.BusinessPartnersDao;
import cc.dfsoft.project.biz.base.baseinfo.entity.BusinessPartners;
import cc.dfsoft.project.biz.base.project.dao.AccessoryDao;
import cc.dfsoft.project.biz.base.project.dao.DataQueryRoleDao;
import cc.dfsoft.project.biz.base.project.dto.AccessoryQueryReq;
import cc.dfsoft.project.biz.base.project.entity.AccessoryList;
import cc.dfsoft.project.biz.base.project.entity.DataQueryRole;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dao.StaffDao;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;
import cc.dfsoft.uexpress.common.util.FileUtil;
import cc.dfsoft.uexpress.common.util.ResultUtil;
import cc.dfsoft.uexpress.common.util.StringUtil;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

import java.util.*;
@Repository
public class AccessoryDaoImpl extends NewBaseDAO<AccessoryList, String> implements AccessoryDao {
	
	@Resource
	BusinessPartnersDao businessPartnersDao;
	
	@Resource
	StaffDao staffDao;
	
	/**资料查看权限设置*/
	@Resource
	DataQueryRoleDao dataQueryRoleDao;
	
	/**
	 * 查询附件列表
	 * @param accessoryQueryReq
	 * @return
	 */
	@Override
	public Map<String,Object> queryAccessoryList(AccessoryQueryReq accessoryQueryReq){
		
		 Criteria c = super.getCriteria();
		
		 //工程大类
		 if(StringUtils.isNotBlank(accessoryQueryReq.getProjLtypeId())){
			 c.add(Restrictions.eq("projLtypeId",accessoryQueryReq.getProjLtypeId()));
		 }
		 //工程id
		 if(StringUtils.isNotBlank(accessoryQueryReq.getProjId())){
			 c.add(Restrictions.eq("projId",accessoryQueryReq.getProjId()));
		 }
		//工程id
		 if(StringUtils.isNotBlank(accessoryQueryReq.getProjNo())){
			 c.add(Restrictions.eq("projNo",accessoryQueryReq.getProjNo()));
		 }
		//业务单id
		 if(StringUtils.isNotBlank(accessoryQueryReq.getBusRecordId())){
			 c.add(Restrictions.eq("busRecordId",accessoryQueryReq.getBusRecordId()));
		 }
		 // 步骤Id
		 if (StringUtils.isNotBlank(accessoryQueryReq.getStep())) {
			c.add(Restrictions.eq("step",accessoryQueryReq.getStep())); 
		 }
		//业务单id
		 if(StringUtils.isNotBlank(accessoryQueryReq.getSourceType())){
			 c.add(Restrictions.eq("sourceType",accessoryQueryReq.getSourceType()));
		 }
		 //附件来源
		 if(StringUtils.isNotBlank(accessoryQueryReq.getSourceTypes())){
			 String[] sourceTypes = accessoryQueryReq.getSourceTypes().split(",");
			 if(sourceTypes!=null && sourceTypes.length>0){
				//String数组转List
				 List<String> strsToList1= Arrays.asList(sourceTypes);
				 c.add(Restrictions.in("sourceType",strsToList1));
			 }
		 }
		 LoginInfo loginInfo = SessionUtil.getLoginInfo();
		//查权限
		List list=staffDao.findByRoleId("1102-1303");
		
		
		boolean flag=true;
		if(list!=null && list.size()>0){
			int size=list.size();
			for(int i=0;i<list.size();i++){
				//登陆人有查看的权限
				Object[]obj=(Object[]) list.get(i);
				if(loginInfo.getStaffId().equals(obj[0])){
					flag=false;
					break;
				}
			}
		}
		AccessoryQueryReq aq;
		//登陆人没有查看的权限
		if(flag){
			//看自己的
			//c.add(Restrictions.eq("alOperateCsrId", loginInfo.getStaffId()));
			//别人传的 不等于自己的
			c.add(Restrictions.or(Restrictions.eq("encryption", "0"), Restrictions.eq("alOperateCsrId",loginInfo.getStaffId())));
		}
		 
		 
		 BusinessPartners bp =  businessPartnersDao.get(loginInfo.getDeptId());
		 if(bp!=null){
			 //若登录人是分包方单位人员
//			 if(UnitTypeEnum.CONSTRUCTION_UNIT.getValue().equals(bp.getUnitType())){
//				 
//				 StringBuffer sql = new StringBuffer("AL_OPERATE_CSR_ID in(select s.STAFF_ID from STAFF s where s.DEPT_ID like '1107%')");
//				 c.add(Restrictions.sqlRestriction(sql.toString()));
//			 }
			 //如果是业务合作伙伴
			/* StringBuffer sql = new StringBuffer("AL_OPERATE_CSR_ID in(select s.STAFF_ID from STAFF s where s.DEPT_ID ='").append(loginInfo.getDeptId()+"')");
			 c.add(Restrictions.sqlRestriction(sql.toString()));*/
			 
			 
			 //查指定环节的
			 String deptId=loginInfo.getDeptId();
			 DataQueryRole role=dataQueryRoleDao.findByUnitId(deptId);
			 
			 if(role!=null){
				 String stepId=role.getStepId();
				 String [] stepIds=stepId.split(",");
				 List stepIdList=Arrays.asList(stepIds);
				 if(stepIdList!=null && stepIdList.size()>0){
					 c.add(Restrictions.in("step",stepIdList));
				 }
			 }
		 }
		 
		 // 数据库根据条件过滤记录数
		 int filterCount = this.countByCriteria(c);

		 // 根据条件获取查询信息
		 List<AccessoryList> accessoryList = this.findBySortCriteria(c, accessoryQueryReq);
			
		 // 返回结果
		 return ResultUtil.pageResult( filterCount, accessoryQueryReq.getDraw(),accessoryList);
		
	}

	@Override
	public Map<String, Object> queryAccListPhoto(AccessoryQueryReq accessoryQueryReq) {
		 Criteria c = super.getCriteria();
		 Map<String, Object> map=new HashMap<String, Object>();	
		 //步骤
		 if(StringUtils.isNotBlank(accessoryQueryReq.getStepId())){
			 c.add(Restrictions.eq("stepId",accessoryQueryReq.getStepId()));
		 }
		//步骤id
		if(StringUtils.isNotBlank(accessoryQueryReq.getStep())){
			c.add(Restrictions.eq("step",accessoryQueryReq.getStep()));
		}
		 //工程id
		 if(StringUtils.isNotBlank(accessoryQueryReq.getProjId())){
			 c.add(Restrictions.eq("projId",accessoryQueryReq.getProjId()));
		 }
		//工程编号
		 if(StringUtils.isNotBlank(accessoryQueryReq.getProjNo())){
			 c.add(Restrictions.eq("projNo",accessoryQueryReq.getProjNo()));
		 }
		//工程id
		 if(StringUtils.isNotBlank(accessoryQueryReq.getBusRecordId())){
			 c.add(Restrictions.eq("busRecordId",accessoryQueryReq.getBusRecordId()));
		 }
		//附件来源
		 if(StringUtils.isNotBlank(accessoryQueryReq.getSourceType())){
			 c.add(Restrictions.eq("sourceType",accessoryQueryReq.getSourceType()));
		 }
		 // 根据条件获取查询信息
		 List<AccessoryList> accessoryList = this.findBySortCriteria(c, accessoryQueryReq);
		 List<String> list=new ArrayList<String>();
		 for(AccessoryList al:accessoryList){
			 list.add(al.getAlPath());
		 }
		 map.put("srcs", list);
		 return map;
		 
	}

	@Override
	public void delAccPhoto(String alPath) {
		AccessoryList al=this.get("alPath", alPath);
		if(al!=null){
			this.delete(al);
		}
		//删除文件
		if(StringUtil.isNoneBlank(alPath)){
		FileUtil.deleteFile(Constants.DISK_PATH+alPath);
	 }
	}

	@Override
	public List<AccessoryList> queryAccessoryByBus(String id, String sourceType) {
		Criteria c = super.getCriteria();
		//工程id
		 if(StringUtils.isNotBlank(id)){
			 c.add(Restrictions.eq("busRecordId",id));
		 }
		//附件来源
		 if(StringUtils.isNotBlank(sourceType)){
			 c.add(Restrictions.eq("sourceType",sourceType));
		 }
		 List<AccessoryList> accessoryList = this.findByCriteria(c);
		 
		 return accessoryList;
	}

	@Override
	public void delAccPicture(String projId,String stepId,String sourceType) {
		String hql="from AccessoryList a where a.projId='"+projId+"' and stepId='"+stepId+"' and sourceType='"+sourceType+"'";
		List<AccessoryList> list=super.findByHql(hql);
		if(list!=null&&list.size()>0){
			if(StringUtil.isNoneBlank(list.get(0).getAlPath())){
				this.delete(list.get(0));
				FileUtil.deleteFile(Constants.DISK_PATH+list.get(0).getAlPath());
			 }
		}
		    
		
	}

	@Override
	public List<AccessoryList> queryAccessory(AccessoryList req) {
		Criteria c = super.getCriteria();
		//工程id
		 if(StringUtils.isNotBlank(req.getProjId())){
			 c.add(Restrictions.eq("projId",req.getProjId()));
		 }
		//附件来源
		 if(StringUtils.isNotBlank(req.getSourceType())){
			 c.add(Restrictions.eq("sourceType",req.getSourceType()));
		 }
		 //业务ID
		 if(StringUtils.isNotBlank(req.getBusRecordId())){
			 c.add(Restrictions.eq("busRecordId", req.getBusRecordId()));
		 }
		 //步骤名称
		 if(StringUtils.isNotBlank(req.getStepId())){
			 c.add(Restrictions.eq("stepId", req.getStepId()));
		 }
		//步骤ID
		 if(StringUtils.isNotBlank(req.getStep())){
			 c.add(Restrictions.eq("step", req.getStep()));
		 }
		 //文件路径
		 if(StringUtils.isNotBlank(req.getAlPath())){
			 c.add(Restrictions.like("alPath", req.getAlPath()));
		 }
		 List<AccessoryList> accessoryList = this.findByCriteria(c);
		 return accessoryList;
	}

	@Override
	public void updateBId(String busRecordId, String projId, String projNo,
			String sourceType) {
		String hql = "update AccessoryList set busRecordId='"+busRecordId+"' where projId='"+projId+"' and projNo='"+projNo+"' and sourceType='"+sourceType+"'";
		this.executeHql(hql);
	}
	
	/**
	 * 查询资质证书
	 * @author fuliwei
	 * @createTime 2017年9月18日
	 * @param 
	 * @return
	 */
	@Override
	public List<AccessoryList> queryQualificationAccessoryList(AccessoryQueryReq accessoryQueryReq) {
		Criteria c = super.getCriteria();
		//步骤ID：资质证明可从三个菜单下上传
		StringBuffer hql = new StringBuffer("from AccessoryList where 1=1");
		if(StringUtil.isNotBlank(accessoryQueryReq.getStepIds())){
			String [] stepIds = accessoryQueryReq.getStepIds().split(",");
			if(stepIds!=null && stepIds.length>0){
				hql.append(" and step='");
				for(int i=0;i<stepIds.length;i++){
					if(i==0){
						 hql.append(stepIds[i]).append("'");
					 }else{
						 hql.append(" or step ='").append(stepIds[i]).append("'");
					 }
				}
			}
		}
		//业务单id
		if(accessoryQueryReq.getStaffIdList()!=null && accessoryQueryReq.getStaffIdList().size()>0){
			//工程状态
			hql.append("  and busRecordId = '");
			List<String> staffIds=accessoryQueryReq.getStaffIdList();
			 for(int i=0;i<staffIds.size();i++){
				 if(i==0){
					 hql.append(staffIds.get(i)).append("'");
				 }else{
					 hql.append(" or busRecordId ='").append(staffIds.get(i)).append("'");
				 }
			 }
			 List<AccessoryList> accessoryList = super.findByHql(hql.toString());
			 List<String> staffIdList = new ArrayList();
			 if(accessoryList.size()>0){
				 /*for(AccessoryList acc:accessoryList){
					 staffIdList.add(acc.getBusRecordId());
				 }
				 c.add(Restrictions.in("busRecordId",staffIdList));*/
				 return accessoryList;
			 }else{
				// 返回结果
				 return null;
			 }
		}
		return null;
	}

	@Override
	public void delAccPictureByStep(String projId, String step, String sourceType) {
		String hql="from AccessoryList a where a.projId='"+projId+"' and step='"+step+"' and sourceType='"+sourceType+"'";
		List<AccessoryList> list=super.findByHql(hql);
		if(list!=null&&list.size()>0){
			if(StringUtil.isNoneBlank(list.get(0).getAlPath())){
				this.delete(list.get(0));
				FileUtil.deleteFile(Constants.DISK_PATH+list.get(0).getAlPath());
			 }
		}
	}

	@Override
	public Map<String, Object> queryCompletionAccList(
			AccessoryQueryReq accessoryQueryReq, List<String> list) {
		Criteria c = super.getCriteria();
		
		 //工程大类
		 if(StringUtils.isNotBlank(accessoryQueryReq.getProjLtypeId())){
			 c.add(Restrictions.eq("projLtypeId",accessoryQueryReq.getProjLtypeId()));
		 }
		 //工程id
		 if(StringUtils.isNotBlank(accessoryQueryReq.getProjId())){
			 c.add(Restrictions.eq("projId",accessoryQueryReq.getProjId()));
		 }
		//工程id
		 if(StringUtils.isNotBlank(accessoryQueryReq.getProjNo())){
			 c.add(Restrictions.eq("projNo",accessoryQueryReq.getProjNo()));
		 }
		//业务单id
		 if(StringUtils.isNotBlank(accessoryQueryReq.getBusRecordId())){
			 c.add(Restrictions.eq("busRecordId",accessoryQueryReq.getBusRecordId()));
		 }
		 if(list!=null && list.size()>0){
			 c.add(Restrictions.in("step",list));
		 }
		 
		 // 数据库根据条件过滤记录数
		 int filterCount = this.countByCriteria(c);

		 // 根据条件获取查询信息
		 List<AccessoryList> accessoryList = this.findBySortCriteria(c, accessoryQueryReq);
			
		 // 返回结果
		 return ResultUtil.pageResult( filterCount, accessoryQueryReq.getDraw(),accessoryList);
		
	}

	@Override
	public List<AccessoryList> getAll(List<String> projIds, String step) {
		Criteria c = super.getCriteria();
		
		 if(projIds!=null && projIds.size()>0){
			 c.add(Restrictions.in("projId",projIds));
		 }
		 c.add(Restrictions.eq("step",step));
		return this.findByCriteria(c);
	}

	@Override
	public void updateCOId(String oldCoId, String newCoId) {
		String hql = "update AccessoryList set busRecordId=? where busRecordId=?";
		List<String> params = new ArrayList<String>();
		params.add(newCoId);
		params.add(oldCoId);
		this.executeHql(hql, params.toArray());
	}


}
