package cc.dfsoft.project.biz.base.constructmanage.service.impl;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import cc.dfsoft.project.biz.base.constructmanage.dao.DigRoadDao;
import cc.dfsoft.project.biz.base.constructmanage.dao.TouchPlanDao;
import cc.dfsoft.project.biz.base.constructmanage.dto.TouchPlanQueryReq;
import cc.dfsoft.project.biz.base.constructmanage.entity.DigRoad;
import cc.dfsoft.project.biz.base.constructmanage.entity.TouchPlan;
import cc.dfsoft.project.biz.base.constructmanage.service.TouchPlanService;
import cc.dfsoft.project.biz.base.contract.dao.ContractDao;
import cc.dfsoft.project.biz.base.contract.entity.Contract;
import cc.dfsoft.project.biz.base.plan.dao.ConstructionPlanDao;
import cc.dfsoft.project.biz.base.plan.entity.ConstructionPlan;
import cc.dfsoft.project.biz.base.project.dao.ProjectDao;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.project.entity.Signature;
import cc.dfsoft.project.biz.base.project.enums.ActionEnum;
import cc.dfsoft.project.biz.base.project.service.NoticeService;
import cc.dfsoft.project.biz.base.project.service.SignatureService;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.util.IDUtil;
import net.sf.json.JSONSerializer;

@Service
@Transactional(readOnly = true,propagation=Propagation.REQUIRED)
public class TouchPlanServiceImpl implements TouchPlanService{

	@Resource
	TouchPlanDao touchPlanDao;
	
	@Resource
	ProjectDao projectDao;
	
	@Resource
	ConstructionPlanDao constructionPlanDao;
	
	@Resource
	ContractDao contractDao;
	
	@Resource
	DigRoadDao digRoadDao;
	
	@Resource
	NoticeService noticeServie;
	
	@Resource
	SignatureService signatureService;
	
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void saveTouchPlan(TouchPlan touchPlan) throws Exception {
		boolean flag = false;
		if(StringUtils.isBlank(touchPlan.getTpId())){
			flag = true;
			touchPlan.setTpId(IDUtil.getUniqueId(Constants.MODULE_CODE_PROJECTTOUCH));
		}
		//删除原实际开挖道路及面积列表
		digRoadDao.deleteByTpId(touchPlan.getTpId());
		//增加新的开挖路况记录
		List<DigRoad> digRoads = touchPlan.getDigRoads();
		if(digRoads!=null && digRoads.size()>0){
			for(int i=0;i<digRoads.size();i++){
				DigRoad digRoad = digRoads.get(i);
				digRoad.setTpId(touchPlan.getTpId());
				digRoad.setDrId(IDUtil.getUniqueId(Constants.MODULE_CODE_PROJECTTOUCH));
				digRoadDao.saveOrUpdate(digRoad);
			}
		}
		SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
		String chageTpDate=touchPlan.getChangeTpDate();
		String chagedigDate=touchPlan.getChangeDigDate();
		
		if(StringUtils.isNotBlank(chagedigDate)){
			touchPlan.setDigDate(sdf.parse(chagedigDate));
		}
		Date databaseDate = touchPlanDao.getDatabaseDate(); 
		
		/// 发出通知
		
		String projId = touchPlan.getProjId();
		String projName = touchPlan.getProjName();
		if(StringUtils.isNotBlank(chagedigDate)){
			touchPlan.setTpDate(sdf.parse(chageTpDate));
			Date tpDate = sdf.parse(chageTpDate);  //碰口时间
			if (databaseDate.after(tpDate)) { 
				noticeServie.updateNotice(projId, ActionEnum.TOUCH.getValue());  //设置通知失效
			}else{
				//删除原来的通知
				noticeServie.deleteByIdAndActionType(projId, ActionEnum.TOUCH.getValue());
				// 下达碰口通知
				noticeServie.saveNotice(projId ,projName,tpDate
						,ActionEnum.TOUCH.getMessage(),ActionEnum.TOUCH.getValue());
			}
		}
		
		touchPlanDao.saveOrUpdate(touchPlan);
		
		signatureService.saveOrUpdateSign(touchPlan.getMenuDes(),touchPlan.getSign(), touchPlan.getProjId(), touchPlan.getTpId(), touchPlan.getClass().getName(),flag);
	}

	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void saveDigRoad(List<DigRoad> digRoadList) {
		List<DigRoad> listNew=new ArrayList<DigRoad>();
		for(DigRoad digRoad:digRoadList){
			digRoad.setDrId(IDUtil.getUniqueId(Constants.MODULE_CODE_PROJECTSTART));
			listNew.add(digRoad);
		}
		touchPlanDao.batchInsertObjects(listNew);
	}

	@Override
	public TouchPlan touchPlanDetail(String projId) {
		Project project = projectDao.get(projId);
		TouchPlan touchPlan = new TouchPlan();
		List<TouchPlan> touchPlans = touchPlanDao.findbyProjId(projId);
		
		if(touchPlans!=null && touchPlans.size()!=0){
			touchPlan = touchPlans.get(0);
			return touchPlan;
		}
		List<ConstructionPlan> constructionPlans = constructionPlanDao.findByProjNo(project.getProjNo());
		if(constructionPlans!=null && constructionPlans.size()!=0){
			ConstructionPlan constructionPlan = constructionPlans.get(0);
			//touchPlan.setConstructionUnit(constructionPlan.getManagementOffice());//施工单位
		}
		
		Contract contract = contractDao.viewContractByprojId(project.getProjId());
		if(null != contract){
			touchPlan.setConNo(contract.getConNo());//合同编号
		}
		touchPlan.setProjId(project.getProjId());
		touchPlan.setProjScaleDes(project.getProjScaleDes());
		touchPlan.setProjName(project.getProjName());
		touchPlan.setProjNo(project.getProjNo());
		return touchPlan;
	}
	
	@Override
	public TouchPlan touchPlanDetail(String projId,String menuDes) {
		Project project = projectDao.get(projId);
		TouchPlan touchPlan = new TouchPlan();
		List<TouchPlan> touchPlans = touchPlanDao.findbyProjId(projId);
		if(touchPlans!=null && touchPlans.size()!=0){
			touchPlan = touchPlans.get(0);
			Signature signature=signatureService.selectImg(touchPlan.getTpId(), menuDes);
			if(signature!=null){
				touchPlan.setDrawUrl(Constants.DIAGRAM_DISK_PATH+signature.getImgUrl());
			}
			touchPlan.setMenuDes(menuDes);
			//日期转字符串
			Date tpDate=touchPlan.getTpDate();
			Date digDate=touchPlan.getDigDate();
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(tpDate != null){
				touchPlan.setChangeTpDate(sdf.format(tpDate));
			}
			if(digDate != null){
				touchPlan.setChangeDigDate(sdf.format(digDate));
			}
			return touchPlan;
		}
		List<ConstructionPlan> constructionPlans = constructionPlanDao.findByProjNo(project.getProjNo());
		if(constructionPlans!=null && constructionPlans.size()!=0){
			ConstructionPlan constructionPlan = constructionPlans.get(0);
			touchPlan.setConstructionUnit(constructionPlan.getCuName());//由施工单位改为分包
		}
		
		Contract contract = contractDao.viewContractByprojId(project.getProjId());
		if(null != contract){
			touchPlan.setConNo(contract.getConNo());//合同编号
		}
		
		
		
		touchPlan.setProjId(project.getProjId());
		touchPlan.setProjScaleDes(project.getProjScaleDes());
		touchPlan.setProjName(project.getProjName());
		touchPlan.setProjNo(project.getProjNo());
		touchPlan.setTouchAddr(project.getProjAddr());
		
		return touchPlan;
	}

	@Override
	public List<DigRoad> digRoadDetail(String tpId) {
		List<DigRoad> digRoads = touchPlanDao.findbyTpId(tpId);
		if(digRoads!=null && digRoads.size()!=0){
			return digRoads;
		}else{
			return null;
		}
	}

	@Override
	public Map<String, Object> queryTouchPlanAudit(TouchPlanQueryReq touchPlanQueryReq) throws ParseException {
		Map<String,Object> result = touchPlanDao.queryTouchPlanAudit(touchPlanQueryReq);
		List<TouchPlan> data =  (List<TouchPlan>) result.get("data");
		  if(data!=null && data.size()>0){

			for(int i=0 ;i<data.size();i++){
				Map<String,String> levelBtn = new HashMap();
				levelBtn.put("level1", "2"); 	// 待审
				levelBtn.put("level2", "-1"); 	// 未审
				levelBtn.put("level3", "-1"); 	// 未审
				levelBtn.put("level4", "-1"); 	// 未审
				
				if(data.get(i).getOngcDate() != null){
					levelBtn.put("level1", "1"); 	//审核通过
					levelBtn.put("level2", "1"); 	//审核通过
					levelBtn.put("level3", "1");    //审核通过
					levelBtn.put("level4", "1");    //审核通过
				}else if(data.get(i).getCuSpdView() != null){
					levelBtn.put("level1", "1");    // 审核通过
					levelBtn.put("level2", "1");    // 审核通过
					levelBtn.put("level3", "1");    // 审核通过
					levelBtn.put("level4", "2");    // 待审
				}else if(data.get(i).getAcceptanceDate() != null){
					levelBtn.put("level1", "1"); 	// 审核通过
					levelBtn.put("level2", "1"); 	// 审核通过
					levelBtn.put("level3", "2"); 	// 待审
					levelBtn.put("level4", "-1"); 	// 未审
				}else if(data.get(i).getSuJgjDate() != null){
					levelBtn.put("level1", "1");  	// 审核通过
					levelBtn.put("level2", "2");  	// 待审
					levelBtn.put("level3", "-1"); 	// 未审
					levelBtn.put("level4", "-1"); 	// 未审
				}
				data.get(i).setMrAuditLevel(JSONSerializer.toJSON(levelBtn).toString());
			}
			result.put("data", data);
		}
		
		return result;
	}

	@Override
	public boolean isAllowRecord(String projId) {
		if(StringUtils.isNotBlank(projId)){
			TouchPlan touchPlan = this.touchPlanDetail(projId);
			//天然气公司已经签字
			if(null !=touchPlan && null != touchPlan.getOngcPrincipal()){
				return true;
			}
		}
		return false;
	}

	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void saveTouchPlan(HttpServletRequest request, TouchPlan touchPlan, MultipartFile[] files) throws Exception {
		this.saveTouchPlan(touchPlan);
		signatureService.saveImage(request, files, touchPlan.getProjId(), touchPlan.getProjNo(), touchPlan.getTpId(), touchPlan.getMenuDes());
	}
	
	
}
