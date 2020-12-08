package cc.dfsoft.project.biz.base.constructmanage.service.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cc.dfsoft.project.biz.base.budget.dao.MaterialListDao;
import cc.dfsoft.project.biz.base.budget.entity.MaterialList;
import cc.dfsoft.project.biz.base.constructmanage.dao.MaterialPlanDao;
import cc.dfsoft.project.biz.base.constructmanage.dao.MaterialPlanDetailDao;
import cc.dfsoft.project.biz.base.constructmanage.dto.MaterialPlanReq;
import cc.dfsoft.project.biz.base.constructmanage.entity.MaterialPlan;
import cc.dfsoft.project.biz.base.constructmanage.entity.MaterialPlanDetail;
import cc.dfsoft.project.biz.base.constructmanage.enums.MaterialPlanFeedBackEnum;
import cc.dfsoft.project.biz.base.constructmanage.service.MaterialPlanService;
import cc.dfsoft.project.biz.base.plan.dao.ConstructionPlanDao;
import cc.dfsoft.project.biz.base.plan.enums.ProcurementPlanExport;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.util.IDUtil;

/**
 * 施工-材料计划
 * @author fuliwei 
 * @createTime 2017-01-18
 *
 */
@Service
@Transactional(readOnly = true,propagation=Propagation.REQUIRED)
public class MaterialPlanServiceImpl implements MaterialPlanService{
	
	/**材料计划Dao*/
	@Resource
	MaterialPlanDao  materialPlanDao;
	
	/**施工计划Dao*/
	@Resource
	ConstructionPlanDao contructionPlanDao;
	
	/**材料清单Dao*/
	@Resource
	MaterialListDao materialListDao;
	
	/**材料计划明细Dao*/
	@Resource
	MaterialPlanDetailDao materialPlanDetailDao;
	
	/**
	 * 材料计划列表查询
	 * @author fuliwei
	 * @createTime 2017-1-18
	 * @param  
	 * @return
	 */
	@Override
	public Map<String, Object> queryMaterialPlan(MaterialPlanReq MaterialPlanReq) throws ParseException {
		return materialPlanDao.queryMaterialPlan(MaterialPlanReq);
	}
	
	/**
	 * 根据Id查材料计划详述
	 * @author fuliwei
	 * @createTime 2017-1-18
	 * @param  mpId
	 * @return MaterialPlan
	 */
	@Override
	public MaterialPlan findById(String id) {
		
		LoginInfo login=SessionUtil.getLoginInfo();
		
		MaterialPlan materialPlan=materialPlanDao.get(id);
		if(materialPlan != null){
			//采购部-未反馈
			if(MaterialPlanFeedBackEnum.HAVE_NOT_FEED_BACK.getValue().equals(materialPlan.getIsFeedBack())){
				materialPlan.setFeedBackDate(materialPlanDao.getDatabaseDate());//反馈日期为当前日期
				materialPlan.setFeedBacker(login.getStaffName());				//反馈人为登录人
				materialPlan.setFeedBackerId(login.getStaffId());				//登录人Id
			}
		}
		return materialPlan;
	}
	
	/**
	 * 保存材料计划
	 * @author fuliwei
	 * @createTime 2017-1-31
	 * @param  MaterialPlan
	 * @return String
	 */
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void saveMaterialPlan(MaterialPlan mp) {
		if(StringUtils.isBlank(mp.getMpId())){
			mp.setMpId(IDUtil.getUniqueId(Constants.MODULE_CODE_MATERIAL));
		}
		//推送
		if("1".equals(mp.getFlag())){
			mp.setIsFeedBack(MaterialPlanFeedBackEnum.HAVEN_FEED_BACK.getValue());//设置状态-以反馈
		}
		mp.setIsExport(ProcurementPlanExport.HAVE_NOT_EXPORT.getValue());//设置未导出
		mp.setFeedBackDate(materialPlanDao.getDatabaseDate());//反馈时间
		materialPlanDao.saveOrUpdate(mp);//保存材料计划申请
		
		
		List<MaterialPlanDetail> list=mp.getMpList();
		
		if(list!=null && list.size()>0){
			List<MaterialPlanDetail> saveList=new ArrayList<MaterialPlanDetail>();
			for(int i=0;i<list.size();i++){
				MaterialPlanDetail mpd=list.get(i);
				mpd.setMpId(mp.getMpId());
				mpd.setMpdId(IDUtil.getUniqueId(Constants.MODULE_CODE_MATERIAL));//设置主键id
				/*//推送后设置总计划量
				if("1".equals(mp.getFlag())){
					//通过材料id查找材料 并设置领用量
					MaterialList material=materialListDao.get(mpd.getMaterialId());
					BigDecimal planTotalNum=material.getPlanTotalNum();
					if(planTotalNum==null){
						planTotalNum=BigDecimal.ZERO;
					}
					material.setPlanTotalNum(planTotalNum.add(mpd.getPlanNum()));
					materialListDao.update(material);
				}*/
				saveList.add(mpd);
			}
			materialPlanDetailDao.deleteByMpId(mp.getMpId());
			materialPlanDetailDao.batchInsertObjects(list);
		}
		
	}
	
	/**
	 * 保存材料计划
	 * @author fuliwei
	 * @createTime 2017-1-31
	 * @param  MaterialPlan
	 * @return String
	 */
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void saveMaterialPlanFeedBack(MaterialPlan mp) {
		
		mp.setIsFeedBack(MaterialPlanFeedBackEnum.HAVEN_FEED_BACK.getValue());//设置状态-已反馈
		mp.setFeedBackDate(materialPlanDao.getDatabaseDate());
		materialPlanDao.update(mp);//保存材料计划申请
		//通过mpId查找材料计划明细 然后删除
		materialPlanDetailDao.deleteByMpId(mp.getMpId());
		List<MaterialPlanDetail> list=mp.getMpList();
		List<MaterialPlanDetail> saveList=new ArrayList<MaterialPlanDetail>();
		
		for(int i=0;i<list.size();i++){
			MaterialPlanDetail mpd=list.get(i);
			mpd.setMpId(mp.getMpId());
			
			if(mpd.getCertificateComplete()==null){
				mpd.setCertificateComplete("1");//是
			}
			
			MaterialList material=materialListDao.get(mpd.getMaterialId());
			//欠量=计划领量-领用总量
			BigDecimal planTotalNum=material.getPlanTotalNum();
			if(planTotalNum==null){
				planTotalNum=BigDecimal.ZERO;
			}
			BigDecimal getAnum=material.getGetAnum();
			if(getAnum==null){
				getAnum=BigDecimal.ZERO;
			}
			mpd.setOweNum(planTotalNum.subtract(getAnum));
			mpd.setMpdId(IDUtil.getUniqueId(Constants.MODULE_CODE_MATERIAL));//设置主键id
			saveList.add(mpd);
		}
		materialPlanDetailDao.batchInsertObjects(saveList);
	}
	
	/**
	 * 材料计划导出标记
	 * @createTime 2017-2-20
	 * @param  String mpId
	 * @return String 
	 */
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void signMaterialPlan(String mpId) {
		MaterialPlan mp=materialPlanDao.get(mpId);
		if(mp!=null){
			mp.setIsExport(ProcurementPlanExport.ALREADY_EXPORT.getValue());//0
			materialPlanDao.update(mp);
		}
	}
}
