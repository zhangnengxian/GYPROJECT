package cc.dfsoft.project.biz.base.update.dao.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cc.dfsoft.project.biz.base.baseinfo.entity.BusinessPartners;
import cc.dfsoft.project.biz.base.design.enums.UnitTypeEnum;
import cc.dfsoft.project.biz.base.project.dto.ProjectQueryReq;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.update.dao.UpdateInfoDao;
import cc.dfsoft.project.biz.base.update.dto.UpdateInfoReq;
import cc.dfsoft.project.biz.base.update.entity.UpdateInfo;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.biz.sys.dept.entity.Department;
import cc.dfsoft.uexpress.biz.sys.dept.enums.PostTypeEnum;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;
import cc.dfsoft.uexpress.common.util.ResultUtil;

@Repository
public class UpdateInfoDaoImpl extends NewBaseDAO<UpdateInfo, String> implements UpdateInfoDao{

	@Override
	public UpdateInfo findById(String id){
		Criteria c = super.getCriteria();
		if(StringUtils.isNotBlank(id)){
			 c.add(Restrictions.eq("updateId",id));
		 }
		List<UpdateInfo> updateInfos = this.findByCriteria(c);
		UpdateInfo updateInfo=new UpdateInfo();
		if(updateInfos.size()>0){
			updateInfo=updateInfos.get(0);
		}
		return updateInfo;
	}
	
	@Override
	public List<UpdateInfo> findInfo(String updateNumber,Date updateTime) {
		Criteria c = super.getCriteria();
		if(StringUtils.isNotBlank(updateNumber)){
			 c.add(Restrictions.eq("updateNumber",updateNumber));
		 }
		if(updateTime!=null){
			c.add(Restrictions.le("updateTime",updateTime));
		 }
		c.addOrder(Order.desc("updateTime"));
		
		List<UpdateInfo> updateTimes = this.findByCriteria(c);
		
		return updateTimes;
	}

	@Override
	public List<UpdateInfo> findAllInfo() {
		Criteria c = super.getCriteria();
		c.addOrder(Order.desc("updateTime"));
		
		List<UpdateInfo> updateTimes = this.findByCriteria(c);
		
		return updateTimes;
	}
	


	@Override
	public void insertUpdateInfo(UpdateInfo updateInfo) {
		this.save(updateInfo);
	}
	
	@Override
	public void updateUpdateInfo(UpdateInfo updateInfo) {
		this.update(updateInfo);
	}

	@Override
	public Map<String, Object> queryUpdateInfo(UpdateInfoReq updateInfo) throws ParseException {

		 Criteria c = super.getCriteria();
		 
		 //分公司id
		 if(StringUtils.isNotBlank(updateInfo.getCorpId())){
			 c.add(Restrictions.eq("corpId",updateInfo.getCorpId()));
		 }
		 
		 //更新编号
		 if(StringUtils.isNotBlank(updateInfo.getUpdateNo())){
			 c.add(Restrictions.eq("updateNo",updateInfo.getUpdateNo()));
		 }
		//更新次数
		 if(StringUtils.isNotBlank(updateInfo.getUpdateNumber())){
			 c.add(Restrictions.eq("updateNumber",updateInfo.getUpdateNumber()));
		 }
		 //用于字符串日期格式转换
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		 
		//更新时间
		 if(updateInfo.getUpdateTime()!=null){
			 c.add(Restrictions.le("updateTime",updateInfo.getUpdateTime()));
		 }
		 //更新开始时间
		 if(StringUtils.isNotBlank(updateInfo.getUpdateTimeStart())){
			 c.add(Restrictions.ge("updateTime",sdf.parse(updateInfo.getUpdateTimeStart())));
		 }
		 //更新结束时间
		 if(StringUtils.isNotBlank(updateInfo.getUpdateTimeEnd())){
			 c.add(Restrictions.le("updateTime",sdf.parse(updateInfo.getUpdateTimeEnd())));
		 }
		 
		 c.addOrder(Order.desc("updateTime"));  // 更新时间
		 
		 // 数据库根据条件过滤记录数
		 int filterCount = this.countByCriteria(c);
         
		// 根据条件获取查询信息
		 
		List<UpdateInfo> updateInfoList = this.findBySortCriteria(c, updateInfo);
		// 返回结果
		return ResultUtil.pageResult( filterCount, updateInfo.getDraw(),updateInfoList);
	}
}
