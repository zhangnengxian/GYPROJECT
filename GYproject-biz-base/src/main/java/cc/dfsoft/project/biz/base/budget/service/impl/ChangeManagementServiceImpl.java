package cc.dfsoft.project.biz.base.budget.service.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONSerializer;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import cc.dfsoft.project.biz.base.baseinfo.enums.AssistProgressTypeEnum;
import cc.dfsoft.project.biz.base.baseinfo.enums.WorkFlowTypeEnum;
import cc.dfsoft.project.biz.base.budget.dao.BudgetDao;
import cc.dfsoft.project.biz.base.budget.dao.ChangeManagementDao;
import cc.dfsoft.project.biz.base.budget.dao.MaterialListDao;
import cc.dfsoft.project.biz.base.budget.dto.ChangeManagementQueryReq;
import cc.dfsoft.project.biz.base.budget.entity.Budget;
import cc.dfsoft.project.biz.base.budget.entity.MaterialList;
import cc.dfsoft.project.biz.base.budget.enums.BudgetAdjustEnum;
import cc.dfsoft.project.biz.base.budget.enums.BudgetTypeEnum;
import cc.dfsoft.project.biz.base.budget.service.ChangeManagementService;
import cc.dfsoft.project.biz.base.change.dao.MaterialChangeDao;
import cc.dfsoft.project.biz.base.change.dto.MaterialChangeReq;
import cc.dfsoft.project.biz.base.change.entity.MaterialChange;
import cc.dfsoft.project.biz.base.change.enums.ChangeStateEnum;
import cc.dfsoft.project.biz.base.change.enums.MCTypeEnum;
import cc.dfsoft.project.biz.base.charge.enums.ARFlagEnum;
import cc.dfsoft.project.biz.base.charge.enums.ArContractTypeEnum;
import cc.dfsoft.project.biz.base.charge.service.AccrualsRecordService;
import cc.dfsoft.project.biz.base.complete.enums.CompletionDataPrintEnum;
import cc.dfsoft.project.biz.base.constructmanage.dto.CompletionDataPrintDto;
import cc.dfsoft.project.biz.base.constructmanage.dto.UploadResult;
import cc.dfsoft.project.biz.base.constructmanage.entity.ChangeManagement;
import cc.dfsoft.project.biz.base.constructmanage.entity.TouchPlan;
import cc.dfsoft.project.biz.base.constructmanage.enums.AuditStateEnum;
import cc.dfsoft.project.biz.base.constructmanage.enums.ChangeTypeEnum;
import cc.dfsoft.project.biz.base.constructmanage.enums.MaterialChangeTypeEnum;
import cc.dfsoft.project.biz.base.constructmanage.service.TouchPlanService;
import cc.dfsoft.project.biz.base.contract.dao.SupplementalContractDao;
import cc.dfsoft.project.biz.base.contract.entity.SupplementalContract;
import cc.dfsoft.project.biz.base.contract.enums.ContractTypeEnum;
import cc.dfsoft.project.biz.base.design.enums.DesignChangeStateEnum;
import cc.dfsoft.project.biz.base.plan.entity.ProcurementPlan;
import cc.dfsoft.project.biz.base.plan.enums.ProcurementPlanExport;
import cc.dfsoft.project.biz.base.plan.enums.ProjectOperateEnum;
import cc.dfsoft.project.biz.base.plan.service.ProcurementPlanDetailService;
import cc.dfsoft.project.biz.base.plan.service.ProcurementPlanService;
import cc.dfsoft.project.biz.base.project.dao.AccessoryDao;
import cc.dfsoft.project.biz.base.project.dao.NoticeDao;
import cc.dfsoft.project.biz.base.project.dao.ProjectDao;
import cc.dfsoft.project.biz.base.project.dao.ProjectTypeDao;
import cc.dfsoft.project.biz.base.project.entity.AccessoryList;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.project.entity.ProjectType;
import cc.dfsoft.project.biz.base.project.entity.Signature;
import cc.dfsoft.project.biz.base.project.enums.AccessorySourceEnum;
import cc.dfsoft.project.biz.base.project.enums.CollectionTypeEnum;
import cc.dfsoft.project.biz.base.project.enums.SignDataTypeEnum;
import cc.dfsoft.project.biz.base.project.enums.SignPostEnum;
import cc.dfsoft.project.biz.base.project.enums.SignStateEnum;
import cc.dfsoft.project.biz.base.project.service.ManageRecordService;
import cc.dfsoft.project.biz.base.project.service.OperateRecordService;
import cc.dfsoft.project.biz.base.project.service.ProjectService;
import cc.dfsoft.project.biz.base.project.service.SignNoticeService;
import cc.dfsoft.project.biz.base.project.service.SignatureService;
import cc.dfsoft.project.biz.ifs.ResultMessage;
import cc.dfsoft.project.biz.ifs.WebServiceTypeEnum;
import cc.dfsoft.project.biz.ifs.finance.enums.ResultTypeEnum;
import cc.dfsoft.project.biz.ifs.material.enums.MaterialOperateTypeEnum;
import cc.dfsoft.project.biz.ifs.material.enums.MaterialTableTypeEnum;
import cc.dfsoft.project.biz.ifs.material.service.MaterialNcService;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.biz.sys.dept.entity.Staff;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.exception.ExpressException;
import cc.dfsoft.uexpress.common.util.CoordinatesUtil;
import cc.dfsoft.uexpress.common.util.FileUtil;
import cc.dfsoft.uexpress.common.util.IDUtil;
import cc.dfsoft.uexpress.common.util.StringUtil;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
/**
 * 变更记录服务接口实现
 * @author Administrator
 *
 */
@Service
@Transactional(readOnly = true,propagation=Propagation.REQUIRED)
public class ChangeManagementServiceImpl implements ChangeManagementService{
	
	/**变更记录Dao*/
	@Resource
	ChangeManagementDao changeManagementDao;
	
	/**工程Dao*/
	@Resource
	ProjectDao projectDao;
	
	@Resource
	SignatureService signatureService;
	
	@Resource
	ProcurementPlanService procurementPlanService;
	
	@Resource
	ProcurementPlanDetailService procurementPlanDetailService;
	
	@Resource
	MaterialChangeDao materialChangeDao;
	
	@Resource
	TouchPlanService touchPlanService;
	
	@Resource
	AccessoryDao accessoryDao;
	
	@Resource
	SupplementalContractDao supplementalContractDao;
	
	@Resource
	AccrualsRecordService accrualsRecordService;
	
	/**材料清单*/
	@Resource
	MaterialNcService materialService;
	
	/**工程类型Dao*/
	@Resource
	ProjectTypeDao projectTypeDao;
	
    /**预算Dao*/
	@Resource
	BudgetDao  budgetDao;
	
	@Resource
	MaterialListDao materialListDao;
	
	/**签字通知服务*/
	@Resource
	SignNoticeService signNoticeService;
	@Resource
	MaterialNcService materialNcService;
	@Resource
	ManageRecordService manageRecordService;
	@Resource
	ProjectService projectService;
	@Resource
	NoticeDao noticeDao;
	@Resource
	OperateRecordService operateRecordService;
	@Override
	public Map<String, Object> queryChangeManagement(ChangeManagementQueryReq changeManagementQueryReq)
			throws ParseException {
		Map<String, Object> map = changeManagementDao.queryChangeManagement(changeManagementQueryReq);
		
        List<ChangeManagement> list=(List<ChangeManagement>) map.get("data");
		
		if(list!=null && list.size()>0){
			for(ChangeManagement changeManagement:list){
				//需要上传设计变更材料
				if(changeManagement!=null &&BudgetTypeEnum.ADJUSTED.getValue().equals(changeManagement.getChangeMaterialFlag())){
					//判断工程是否已上传材料
					List<MaterialChange> materialChanges = materialChangeDao.findByType(changeManagement.getCmId(),MCTypeEnum.MATERIAL_CHANGE.getValue());
					if(materialChanges==null || materialChanges.size()<1){
						//未上传材料目录，不可推送
						changeManagement.setMaterialFlag(BudgetTypeEnum.ADJUSTED.getValue());
					}
				}

					Project project=projectDao.get(changeManagement.getProjId());
					if(project!=null){
						changeManagement.setProjectType(project.getProjectType());
						changeManagement.setDesigner(StringUtils.isNotBlank(project.getDesigner())?project.getDesigner():"");
						changeManagement.setDesignerId(StringUtils.isNotBlank(project.getDesignerId())?project.getDesignerId():"");
					}
				
			}
		}
		
		/*List<ChangeManagement> list = (List<ChangeManagement>) map.get("data");
		long secondsLimit=2*24*60*60;
		for (ChangeManagement cm : list) {
			// 变更记录生成时间
			Date oldTime = cm.getCmDate();
	
			// 当前时间
			Date nowTime = changeManagementDao.getDatabaseDate();
			long seconds = (nowTime.getTime() - oldTime.getTime()) / 1000;
			// 如果当前时间-上个步骤的操作时间大于时间限制段则为超时
			if (seconds > secondsLimit) {
				cm.setOverdue(true);
				continue;
			}
		}*/
		return map;
	}

	@Override
	public ChangeManagement viewChangeManagement(String id) {
		ChangeManagement changeManagement=changeManagementDao.get(id);
		if(StringUtils.isNotBlank(changeManagement.getProjId())){
			Project pro=projectDao.get(changeManagement.getProjId());
			if(pro!=null){
				changeManagement.setCorpName(pro.getCorpName());
				changeManagement.setProjectTypeDes(pro.getProjectTypeDes());
				changeManagement.setContributionModeDes(pro.getContributionModeDes());
				changeManagement.setDeptName(pro.getDeptName());
			}
		}
		
		return changeManagement;
	}
	
	@Override
	public Map<String, Object> queryChange(ChangeManagementQueryReq changeManagementQueryReq) throws ParseException {
		Map<String,Object> result = this.queryChangeManagement(changeManagementQueryReq);
		List<ChangeManagement> data = (List<ChangeManagement>) result.get("data");
		String projId=null;
		if(data!=null && data.size()>0){
			for(int i = 0;i<data.size();i++){
				projId=data.get(i).getProjId();
				//id查询工程
				Project pro=projectDao.get(projId);
				//设置data的工程大类
				data.get(i).setProjLtypeId(pro.getProjLtypeId());
				//签证待办人
				data.get(i).setToDoer(operateRecordService.queryTodoer(data.get(i).getCmId()));
			}
			result.put("data", data);
		}
		return result;
	}

//	@Override
	public ChangeManagement viewChangeManagement(String id,String menuDes) {
		ChangeManagement changeManagement=changeManagementDao.get(id);
		Signature signature=signatureService.selectImg(id, menuDes);
		if(signature!=null){
			changeManagement.setDrawUrl(Constants.DIAGRAM_DISK_PATH+signature.getImgUrl());
		}
		changeManagement.setMenuDes(menuDes);
		
		
		//查询附件
		List<AccessoryList> accList= accessoryDao.queryAccessoryByBus(id,null);
		if(accList!=null && accList.size()>0){
			AccessoryList ac=accList.get(0);
			changeManagement.setAccListId(ac.getAlId());
		}
		return changeManagement;
	}
	
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void saveChangeManagement(ChangeManagement changeManagement) throws Exception {
		boolean flag = false;
		if(StringUtils.isBlank(changeManagement.getCmId())){//新增
			flag = true;
			changeManagement.setCmId(IDUtil.getUniqueId(Constants.MODULE_CODE_PROCESS));//设置唯一id 施工过程过程
			changeManagement.setCmState(ChangeStateEnum.NO_HANDLE.getValue()); 		//设置变更状态--未处理
            //设置自增变更编号
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            String date = sdf.format(changeManagementDao.getDatabaseDate());
            String cmNo = changeManagementDao.getMaxCmNo(date,changeManagement.getProjId());
            if(null == cmNo || "".equals(cmNo)){
                cmNo = date+"01";
            }
            changeManagement.setCmNo(cmNo);
		}
		/**
		 * 20180411
		 * 王会军
		 * 增加废弃标记并保存废弃申请信息
		 */
		if(changeManagement.getDesignChangeType().equals(DesignChangeStateEnum.TO_CANCEL.getValue())){
			changeManagement.setDesignChangeType(DesignChangeStateEnum.TO_CANCEL.getValue());//废弃
			changeManagement.setCancelStaffId(SessionUtil.getLoginInfo().getStaffId());      //保存废弃申请人ID
			changeManagement.setCancelStaffName(SessionUtil.getLoginInfo().getStaffName());  //保存废弃申请人姓名
			changeManagement.setCancelDate(manageRecordService.getDatabaseDate());    //保存当前废弃时间
			//待办置为无效
			Staff staff = new Staff();
			Project project = projectDao.get("projId", changeManagement.getProjId());
			operateRecordService.cerateCurOperateRecord(project, changeManagement.getDesignChangeType(), WorkFlowTypeEnum.ASSIST_PROGRESS.getValue(), AssistProgressTypeEnum.DESIGN_CHANGE_PROGRESS.getValue(),changeManagement.getCmId(),staff,null,false);
		}else{
			changeManagement.setDesignChangeType(DesignChangeStateEnum.WAIT_PUSH.getValue());//待推送
			if(flag){
				//设计变更待推送待办通知,现场代表有签字通知，现场代表推送
				//Staff staff = new Staff();
				//staff.setStaffId(SessionUtil.getLoginInfo().getStaffId());
				//staff.setStaffName(SessionUtil.getLoginInfo().getStaffName());
				//Project project = projectDao.get("projId", changeManagement.getProjId());
				//operateRecordService.cerateCurOperateRecord(project, changeManagement.getDesignChangeType(), WorkFlowTypeEnum.ASSIST_PROGRESS.getValue(), AssistProgressTypeEnum.DESIGN_CHANGE_PROGRESS.getValue(),changeManagement.getCmId(),staff,null,true);
			}
		}
		
		changeManagementDao.saveOrUpdate(changeManagement);
		
		
		
		/*if(StringUtils.isNotBlank(changeManagement.getCuPm())
				&& StringUtils.isNotBlank(changeManagement.getSuCes())
				&& StringUtils.isNotBlank(changeManagement.getCustLeader())){
				//所有通知置为已签字
				signNoticeService.updateAllSignState(SignDataTypeEnum.DESIGNALTERATION.getValue(),changeManagement.getCmId());
		 }else{
			 if(StringUtils.isNotBlank(changeManagement.getCuPm())
						&& StringUtils.isBlank(changeManagement.getSuCes())
						&& StringUtils.isBlank(changeManagement.getCustLeader())){
				//只有施工员签字的时候 才激活下一个签字
					//把自己的置为已签字 
					 signNoticeService.saveSignNotice(SignPostEnum.CU_PM.getValue(), SignDataTypeEnum.DESIGNALTERATION.getValue(),
							 changeManagement.getCmId(), changeManagement.getProjId());
			 }
		 }*/


		List<Signature> signs=changeManagement.getSign();
		if(signs!=null && signs.size()>0){
			for(Signature sign:signs){
				sign.setDataType(SignDataTypeEnum.DESIGNALTERATION.getValue());
			}
			changeManagement.setSignature(signs);
		}
	
		
		signatureService.saveOrUpdateSign(changeManagement.getMenuDes(),changeManagement.getSign(), changeManagement.getProjId(), changeManagement.getCmId(), changeManagement.getClass().getName(),flag);
	}

	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void updateChangeState(String id) {
		changeManagementDao.updateChangeState(id);
		List<MaterialChange> materialChanges = materialChangeDao.findByType(id,MCTypeEnum.MATERIAL_CHANGE.getValue());
		if(materialChanges!=null&&materialChanges.size()>0){
			//保存采购计划
			ChangeManagement change = changeManagementDao.get(id);
			ProcurementPlan procurementPlan = new ProcurementPlan();
			procurementPlan.setProcurPlanId(IDUtil.getUniqueId(Constants.MODULE_CODE_CHANGE));
			procurementPlan.setProjId(change.getProjId());
			procurementPlan.setProjName(change.getProjName());
			procurementPlan.setProjNo(change.getProjNo());
			procurementPlan.setStatus(ProjectOperateEnum.MATERIAL_CHANGE.getValue());
			procurementPlan.setIsExport(ProcurementPlanExport.HAVE_NOT_EXPORT.getValue());//未导出
			procurementPlan.setBusinessOrderId(id);
			procurementPlanService.saveProcurementPlan(procurementPlan);
			//保存采购明细
			procurementPlanDetailService.savePlanDetail(procurementPlan,materialChanges);
		}
		SupplementalContract supplementalContract = supplementalContractDao.findByCmId(id, MCTypeEnum.MATERIAL_CHANGE.getValue());
		if(supplementalContract!=null){
			Project pro = projectDao.get(supplementalContract.getProjId());
			BigDecimal payment = supplementalContract.getScAmount();
			accrualsRecordService.insertAccrualsRecord(pro, IDUtil.getUniqueId(Constants.MODULE_CODE_COST), CollectionTypeEnum.SUPPLEMENTAL_CONTRACT.getValue(), 
					Integer.valueOf(ARFlagEnum.RECEIVE_ACCOUNT.getValue()), payment,ArContractTypeEnum.SUP_CONTRACT.getValue(),supplementalContract.getConNo());
		}
	}
	/**
	 * 变更保存(包括图片)
	 * @throws ParseException 
	 */
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void saveChangeManagement(HttpServletRequest request, UploadResult ur, MultipartFile[] files) throws Exception {
		String result = ur.getResult();
		JSONObject jo = new JSONObject();
		ChangeManagement cmt = JSON.parseObject(result, ChangeManagement.class);
		JSONArray sign = (JSONArray) jo.parseObject(result).get("sign");
		if(sign!=null){
			List<Signature> signs = JSONObject.parseArray(sign.toJSONString(), Signature.class);
			cmt.setSignature(signs);
		}
		
		String name=cmt.getDrawName();
		if(files!=null){
			 cmt.setDrawName(files[0].getOriginalFilename());
		}
		this.saveChangeManagement(cmt);
		ur.setOperateId(cmt.getCmId());
		if(StringUtil.isBlank(name)){
			if(cmt.getChangeType().equals(ChangeTypeEnum.USER_CHANGE.getValue())){//设计变更
				signatureService.saveImage(request, files, cmt.getProjId(), cmt.getProjNo(), cmt.getCmId(), cmt.getMenuDes());
			}else{//工程变更
				List<AccessoryList> accs =accessoryDao.queryAccessoryByBus(cmt.getCmId(),AccessorySourceEnum.CHANGE_FILE.getValue());
				if(accs!=null&&accs.size()>0){
					if(files!=null){
						FileUtil.deleteFile(Constants.DISK_PATH+accs.get(0).getAlPath());
					}
					accessoryDao.delete(accs.get(0));
				}
				if(files!=null){
					AccessoryList acc = new AccessoryList();
					String path=FileUtil.uploadFile(request, ur.getAlPath(), files);		
					String fileName = files[0].getOriginalFilename();
					//String filePath= Constants.FIRST_DISK_PATH + path + fileName;
					String filePath= Constants.FIRST_DISK_PATH + path ;
					String name1 = fileName.substring(0,fileName.lastIndexOf("."));	
					String fileType=fileName.substring(fileName.lastIndexOf(".")+1,fileName.length());	
					LoginInfo loginInfo = SessionUtil.getLoginInfo();
					acc.setAlId(IDUtil.getUniqueId(Constants.FILE_CODE_ACCESS));
					acc.setProjId(cmt.getProjId());
					acc.setProjNo(cmt.getProjNo());
					acc.setProjLtypeId(cmt.getProjLtypeId());
					acc.setAlTypeId(fileType);
					acc.setStepId(ur.getStepId());
					acc.setStep(ur.getStep());
					acc.setAlName(name1);
					acc.setAlPath(filePath);
					acc.setStep(ur.getStep());
					acc.setAlOperateCsrId(loginInfo.getStaffId());
					acc.setAlOperateCsr(loginInfo.getStaffName());
					acc.setAlOperateTime(accessoryDao.getDatabaseDate());
					acc.setSourceType(AccessorySourceEnum.CHANGE_FILE.getValue());
					acc.setBusRecordId(cmt.getCmId());
					accessoryDao.save(acc);
				}
			}
		}
		if ("0".equals(cmt.getSuAuditResult())|| "0".equals(cmt.getCustLeaderAuditResult()) || DesignChangeStateEnum.TO_CANCEL.getValue().equals(cmt.getDesignChangeType()) ){//审核不通过将将签字通知置无效，作废将签字通知置为无效
			signNoticeService.updateAllSignState(SignDataTypeEnum.DESIGNALTERATION.getValue(),cmt.getCmId());
		}


		//审核通过后，调用物资接口，将变更材料传递到NC系统
		/*if(StringUtil.isNotBlank(cmt.getAuditState()) && cmt.getAuditState().equals("3")){
			materialNcService.synProjectInfoClient(cmt.getProjId(), cmt.getCmId(), MaterialTableTypeEnum.CHANGE_MATERIAL.getValue(), MaterialChangeTypeEnum.DESIGN_CHANGE.getValue(), MaterialOperateTypeEnum.CHANGE.getValue());
		}*/
	}
	/**
	 * 碰口方案保存(包括图片)
	 * @throws ParseException 
	 */
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void saveChangeConnect(HttpServletRequest request, UploadResult ur,
			MultipartFile[] files) throws Exception {
		String result = ur.getResult();
		JSONObject jo = new JSONObject();
		TouchPlan cmt = JSON.parseObject(result, TouchPlan.class);
		Map<String, Object> map = CoordinatesUtil.coordinatesConversion(cmt.getLongitude(), cmt.getLatitude());
		if(map!=null){
		cmt.setLongitude(map.get("x").toString());
		cmt.setLatitude(map.get("y").toString());
		}
		JSONArray sign = (JSONArray) jo.parseObject(result).get("sign");
		
		if(sign!=null){
			List<Signature> signs = JSONObject.parseArray(sign.toJSONString(), Signature.class);
			cmt.setSignature(signs);
		}
		
		
		/*if(StringUtil.isBlank(cmt.getDrawName())){
		    cmt.setDrawName(files[0].getOriginalFilename());
			signatureService.saveImage(request, files, cmt.getProjId(), cmt.getProjNo(), cmt.getTpId(), cmt.getMenuDes());
		}*/
		String name=cmt.getDrawName();
		if(files!=null){
			 cmt.setDrawName(files[0].getOriginalFilename());
		}
		//ur.setOperateId(cmt.getTpId());
		touchPlanService.saveTouchPlan(cmt);
		if(StringUtil.isBlank(name)){		   
			signatureService.saveImage(request, files, cmt.getProjId(), cmt.getProjNo(), cmt.getTpId(), cmt.getMenuDes());
		}
		
		
		
	}

	@Override
	public Map<String, Object> queryChangeManagementAudit(ChangeManagementQueryReq changeManagementQueryReq)
			throws ParseException {
		changeManagementQueryReq.setChangeType(ChangeTypeEnum.CONSTRUCTION_CHANGE.getValue());
		List<String> auditState = new ArrayList<String>();
		auditState.add(AuditStateEnum.TO_AUDIT.getValue());
		auditState.add(AuditStateEnum.PASSED.getValue());
		changeManagementQueryReq.setAuditState(auditState);
		Map<String,Object> result = changeManagementDao.queryChangeManagement(changeManagementQueryReq);
		List<ChangeManagement> data =  (List<ChangeManagement>) result.get("data");
		  if(data!=null && data.size()>0){

			for(int i=0 ;i<data.size();i++){
				Map<String,String> levelBtn = new HashMap();
				levelBtn.put("level1", "2"); 	// 待审
				if(data.get(i).getAuditState().equals(AuditStateEnum.PASSED.getValue())){
					levelBtn.put("level1", "1"); 	//审核通过
				}
				data.get(i).setMrAuditLevel(JSONSerializer.toJSON(levelBtn).toString());
			}
			result.put("data", data);
		}
		return result;
	}

	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public String pushChangeManagement(String cmId) {
		ChangeManagement cm = changeManagementDao.get(cmId);
		if(cm!=null){
			cm.setAuditState(AuditStateEnum.TO_AUDIT.getValue());
			cm.setDesignChangeType(DesignChangeStateEnum.CHNAGE_AUDTI.getValue());//待审核
			changeManagementDao.update(cm);
			Project project  = projectDao.get("projId",cm.getProjId());
			Staff staff = new Staff();
			if(project!=null && StringUtil.isNotBlank(project.getDesignerId())){
				staff.setStaffId(project.getDesignerId());
				staff.setStaffName(StringUtil.isNotBlank(project.getDesigner())?project.getDesigner():"");
			}
			//设计变更审核待办
			operateRecordService.cerateCurOperateRecord(project, cm.getDesignChangeType(), WorkFlowTypeEnum.ASSIST_PROGRESS.getValue(), AssistProgressTypeEnum.DESIGN_CHANGE_PROGRESS.getValue(),cm.getCmId(),staff,"1",true);
		}
		return Constants.OPERATE_RESULT_SUCCESS;
	}

	@Override
	public ChangeManagement findByProjId(String projId) {
		ChangeManagement changeManagement = changeManagementDao.queryByprojId(projId);
		if(changeManagement==null){
			changeManagement = new ChangeManagement();
			Project project = projectDao.get(projId);
			if(project!=null && StringUtils.isNotBlank(project.getCorpName())){
				changeManagement.setCorpName(project.getCorpName());
			}
		}
		LoginInfo loginInfo =SessionUtil.getLoginInfo();
		changeManagement.setCmAdvanceUnit(loginInfo.getCorpName());
		changeManagement.setCmAdvanceStaffId(loginInfo.getStaffId());
		changeManagement.setCmAdvanceStaffName(loginInfo.getStaffName());
		changeManagement.setCmDate(projectDao.getDatabaseDate());
		return changeManagement;
	}

	@Override
	public List<Object> findPrintDataByProjId(String projId,String type) {
		String result ="";
		List<Object> list = new ArrayList<Object>();
		//根据工程ID查询变更记录信息
		//多条变更
		List<ChangeManagement> cms = changeManagementDao.findByProjId(projId,DesignChangeStateEnum.ALREADY_FINISHED.getValue(),ChangeTypeEnum.CONSTRUCTION_CHANGE.getValue());
		Project project = projectDao.get(projId);  //根据工程ID取得实体
		String arrayStr = CompletionDataPrintEnum.CHANGE_MENT.getCptUrl();
		//2、使用JSONArray
		net.sf.json.JSONArray jsonArray=net.sf.json.JSONArray.fromObject(arrayStr);
		if(jsonArray!=null&&jsonArray.size()>0 && cms!=null && cms.size()>0 && project !=null){
			 String[] menuIdAndMark = type.split("-"); // 分解施工任务单标识符和菜单ID,从而取得菜单ID
			 String menuId = menuIdAndMark[menuIdAndMark.length-1]; // 获取菜单id
			//遍历变更记录
			for(ChangeManagement cm : cms){
				//遍历cpt
				for(Object obj:jsonArray){
					net.sf.json.JSONObject jsonObject=net.sf.json.JSONObject.fromObject(obj);
					CompletionDataPrintDto dto = (CompletionDataPrintDto)net.sf.json.JSONObject.toBean(jsonObject, CompletionDataPrintDto.class);
					//变更记录变更类型与cpt类型相同，
					if(cm.getChangeType()!=null && cm.getChangeType().equals(dto.getType())){
						 String key = project.getProjectType()+"_"+project.getCorpId()+"_"+menuId;
						 Object reportVersion = Constants.getSysConfigByKey(key);
						   if(reportVersion !=null){
							   //记录特定字符索引  
							   int beginIndex = dto.getReportlet().indexOf("/"); 
							   int endIndex = dto.getReportlet().lastIndexOf("/");
						       String reportlet = dto.getReportlet().substring(beginIndex, endIndex+1);  //截取字符'/'之间的字符串
							   dto.setReportlet(reportlet+reportVersion.toString());   //若reportVersion不为空则重新设置报表路径
						   }
						result = "{reportlet:'"+dto.getReportlet()+"',projName:'"+cm.getProjName()+"',projNo:'"+cm.getProjNo()
								+"',cmDate:'"+cm.getCmDate()+"',cmId:'"+cm.getCmId()
								+"',imgUrl:'" + Constants.DISK_PATH+Constants.SIGN_DISK_PATH + "'}";
						list.add(result);
					}
				}
			}
		}
		return list;
	}
	
	/**
	 * 保存变更记录新方法
	 * @author fuliwei
	 * @createTime 2017年11月6日
	 * @param 
	 * @return
	 */
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void saveChangeManagement(ChangeManagement changeManagement, String type) {
		
		LoginInfo loginInfo=SessionUtil.getLoginInfo();
		boolean flag = false;
		if(StringUtils.isBlank(changeManagement.getCmId())){//新增
			changeManagement.setCmId(IDUtil.getUniqueId(Constants.MODULE_CODE_PROCESS));	//设置唯一id 施工过程过程
			changeManagement.setCmState(ChangeStateEnum.NO_HANDLE.getValue()); 				//设置变更状态--未处理
			changeManagement.setDesignChangeType(type);                                     //设置变更状态
			//设置自增变更编号
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            String date = sdf.format(changeManagementDao.getDatabaseDate());
            String cmNo = changeManagementDao.getMaxCmNo(date,changeManagement.getProjId());
            if(null == cmNo || "".equals(cmNo)){
                cmNo = date+"01";
            }
            changeManagement.setCmAdvanceStaffName(loginInfo.getStaffName());  //保存变更人
            changeManagement.setCmAdvanceStaffId(loginInfo.getStaffId());      //保存变更人ID
            changeManagement.setCmNo(cmNo);
            flag = true;
		}
	
		if(changeManagement.getDesignChangeType()!=null && changeManagement.getDesignChangeType().equals("-1")){    //如果是废弃操作
			changeManagement.setCancelStaffName(loginInfo.getStaffName());    //保存废弃申请人
			changeManagement.setCancelStaffId(loginInfo.getStaffId());         //保存废弃申请人ID
			//待办标记为已办
			Project pro = projectDao.get(changeManagement.getProjId());
			operateRecordService.cerateCurOperateRecord(pro,changeManagement.getDesignChangeType(), WorkFlowTypeEnum.ASSIST_PROGRESS.getValue(), AssistProgressTypeEnum.CHANGE_PROGRESS.getValue(),changeManagement.getCmId(),new Staff(),null,false);
			
		}else{
			changeManagement.setDesignChangeType(type); 
		}
		changeManagement.setCmState(ChangeStateEnum.NO_HANDLE.getValue()); 					//设置变更状态--未处理
		changeManagement.setChangeType(ChangeTypeEnum.USER_CHANGE.getValue());				//用户变更
		changeManagementDao.saveOrUpdate(changeManagement);
		//待推送待办todo
		/*if(flag){
			Staff staff = new Staff();
			staff.setStaffId(SessionUtil.getLoginInfo().getStaffId());
			staff.setStaffName(SessionUtil.getLoginInfo().getStaffName());
			Project pro = projectDao.get(changeManagement.getProjId());
			operateRecordService.cerateCurOperateRecord(pro,changeManagement.getDesignChangeType(), WorkFlowTypeEnum.ASSIST_PROGRESS.getValue(), AssistProgressTypeEnum.CHANGE_PROGRESS.getValue(),changeManagement.getCmId(),staff,null,true);
		}*/
	}
	
	/**
	 * 更新变更状态
	 * @author fuliwei
	 * @createTime 2017年11月6日
	 * @param 
	 * @return
	 * @throws ParseException 
	 */
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public String updateChangeState(String cmId, String type) throws ParseException {
		ChangeManagement cm=changeManagementDao.get(cmId);
		LoginInfo loginInfo=SessionUtil.getLoginInfo();
		String result="";
		if(cm!=null){
			cm.setDesignChangeType(type);
			Project pro=projectDao.get(cm.getProjId());
			
			//推送到确认中
			if(DesignChangeStateEnum.WAIT_CONFIRM.getValue().equals(type)){
				cm.setCmAdvanceStaffName(loginInfo.getStaffName());
				cm.setCmAdvanceStaffId(loginInfo.getStaffId());
				Staff staff = new Staff();
				//设计员
				staff.setStaffId(StringUtil.isNotBlank(pro.getDesignerId())?pro.getDesignerId():"");
				staff.setStaffName(StringUtil.isNotBlank(pro.getDesigner())?pro.getDesigner():"");
				operateRecordService.cerateCurOperateRecord(pro,cm.getDesignChangeType(), WorkFlowTypeEnum.ASSIST_PROGRESS.getValue(), AssistProgressTypeEnum.CHANGE_PROGRESS.getValue(),cm.getCmId(),staff,null,true);
			}else if(DesignChangeStateEnum.WAIT_BUDGET_CHANGE.getValue().equals(type)){
				//推送到预算调整
				cm.setDesigner(loginInfo.getStaffName());
				cm.setDesignerId(loginInfo.getStaffId());
				
				//调用物资接口
				/*String msg=materialService.synProjectInfoClient(cm.getProjId(),cm.getCmId(),"1","2");
				if(msg!=null){
					result = msg;
				}*/
				
				//如果是民用-推送到签订补充协议
				Staff staff = new Staff();
				//预算员todo
				staff.setStaffId(StringUtil.isNotBlank(pro.getBudgeterId())?pro.getBudgeterId():"");
				staff.setStaffName(StringUtil.isNotBlank(pro.getBudgeter())?pro.getBudgeter():"");
				
				if(pro!=null){
					ProjectType proType=projectTypeDao.get(pro.getProjectType());
					if(ContractTypeEnum.RESIDENT.getValue().equals(proType.getContractType())){
						cm.setDesignChangeType(DesignChangeStateEnum.WAIT_SUPPLEMENT_CONTRACT.getValue());
						
						Budget budget=new Budget();
						budget.setBudgetId(IDUtil.getUniqueId(Constants.MODULE_CODE_DESIGN));
						budget.setProjId(pro.getProjId());
						budget.setProjNo(pro.getProjNo());
						budget.setCmId(cm.getCmId());
						budget.setBudgetTypeId(BudgetAdjustEnum.ADJUSTED.getValue());//调整预算
						budget.setCorpId(pro.getCorpId());
						budget.setBudgetAdjustDate(projectDao.getDatabaseDate());
						budget.setIsSignSuContrct("0");//未签订补充协议
						budgetDao.save(budget);
					}
				}
				//待办todo
				operateRecordService.cerateCurOperateRecord(pro,cm.getDesignChangeType(), WorkFlowTypeEnum.ASSIST_PROGRESS.getValue(), AssistProgressTypeEnum.CHANGE_PROGRESS.getValue(),cm.getCmId(),staff,null,true);
			
			}else if(DesignChangeStateEnum.WAIT_SUPPLEMENT_CONTRACT.getValue().equals(type)){
				//待办todo
				operateRecordService.cerateCurOperateRecord(pro,cm.getDesignChangeType(), WorkFlowTypeEnum.ASSIST_PROGRESS.getValue(), AssistProgressTypeEnum.CHANGE_PROGRESS.getValue(),cm.getCmId(),new Staff(),null,true);
			
			}
			changeManagementDao.saveOrUpdate(cm);
			
			result=Constants.OPERATE_RESULT_SUCCESS;
			return result;
		}
		return Constants.OPERATE_RESULT_FAILURE;
	}
	

	/**
	 * 根据工程id和主键id查询工程
	 * @author fuliwei
	 * @createTime 2017年11月6日
	 * @param 
	 * @return
	 */
	@Override
	public ChangeManagement viewChangeManagementById(String projId, String cmId) {
		ChangeManagement changeManagement=new ChangeManagement();
		if(StringUtils.isNotBlank(cmId)){
			changeManagement=changeManagementDao.get(cmId);
			if(StringUtils.isNotBlank(changeManagement.getProjId())){
				Project pro=projectDao.get(changeManagement.getProjId());
				if(pro!=null){
					changeManagement.setCorpName(pro.getCorpName());
					changeManagement.setProjectTypeDes(pro.getProjectTypeDes());
					changeManagement.setContributionModeDes(pro.getContributionModeDes());
					changeManagement.setDeptName(pro.getDeptName());
				}
			}
			
		}else{
			if(StringUtils.isNotBlank(projId)){
				Project pro=projectDao.get(projId);
				changeManagement.setProjName(pro.getProjName());
				changeManagement.setProjNo(pro.getProjNo());
				changeManagement.setProjId(pro.getProjId());
				changeManagement.setProjAddr(pro.getProjAddr());
				changeManagement.setProjScaleDes(pro.getProjScaleDes());
				changeManagement.setCorpName(pro.getCorpName());
				changeManagement.setProjectTypeDes(pro.getProjectTypeDes());
				changeManagement.setContributionModeDes(pro.getContributionModeDes());
				changeManagement.setDeptName(pro.getDeptName());		
			
			}
		}
		
		return changeManagement;
	}

	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED, rollbackFor = {Exception.class})
	public void saveChangeManagementAudit(HttpServletRequest request, UploadResult ur,
			MultipartFile[] files) throws Exception {
		String result = ur.getResult();
		JSONObject jo = new JSONObject();
		ChangeManagement changeManagement = JSON.parseObject(result, ChangeManagement.class);
		JSONArray sign = (JSONArray) jo.parseObject(result).get("sign");
		if(sign!=null){
			List<Signature> signs = JSONObject.parseArray(sign.toJSONString(), Signature.class);
			changeManagement.setSignature(signs);
		}
		
		boolean flag = false;
		if(StringUtils.isBlank(changeManagement.getCmId())){//新增
			flag = true;
			changeManagement.setCmId(IDUtil.getUniqueId(Constants.MODULE_CODE_PROCESS));//设置唯一id 施工过程过程
			changeManagement.setCmState(ChangeStateEnum.NO_HANDLE.getValue()); 		//设置变更状态--未处理
            //设置自增变更编号
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            String date = sdf.format(changeManagementDao.getDatabaseDate());
            String cmNo = changeManagementDao.getMaxCmNo(date,changeManagement.getProjId());
            if(null == cmNo || "".equals(cmNo)){
                cmNo = date+"01";
            }
            changeManagement.setCmNo(cmNo);
		}
		//查询变更的工程
		Project pro = projectDao.get(changeManagement.getProjId());
		
		if(AuditStateEnum.PASSED.getValue().equals(changeManagement.getAuditState())){
			//通过
			changeManagement.setDesignChangeType(DesignChangeStateEnum.ALREADY_FINISHED.getValue());//已完成
			changeManagement.setAuditState(AuditStateEnum.PASSED.getValue());
			//将变更材料整合存入到材料清单表中
			boolean flags = this.updateMaterialList(pro,changeManagement);
			//调用用友物资接口物料计划单,todo:
			if(flags && projectService.isToCall(pro.getProjId(),WebServiceTypeEnum.MATERIAL_UPDATE.getValue())){
				ResultMessage resultMessage = new ResultMessage();
				String msg= materialNcService.synProjectInfoClient(pro.getProjId(), changeManagement.getCmId(),MaterialTableTypeEnum.CHANGE_MATERIAL.getValue(), MaterialChangeTypeEnum.DESIGN_CHANGE.getValue(), MaterialOperateTypeEnum.CHANGE.getValue(),WebServiceTypeEnum.MATERIAL_UPDATE.getValue()) ;
				net.sf.json.JSONObject jsonbean = net.sf.json.JSONObject.fromObject(msg);
				//返回信息-当接口返回失败时，抛出异常
				resultMessage = (ResultMessage) net.sf.json.JSONObject.toBean(jsonbean,ResultMessage.class);
				if(resultMessage!=null && ResultTypeEnum.FAIL.getValue().equals(resultMessage.getRet_type()) && projectService.isSynchronization(pro.getProjId(),WebServiceTypeEnum.MATERIAL_UPDATE.getValue())){
					//回滚事物
					throw new ExpressException(resultMessage.getRet_type(),resultMessage.getRet_message());
				}
			}
		}else{
			changeManagement.setDesignChangeType(DesignChangeStateEnum.WAIT_PUSH.getValue());//待推送
		}
		//变更审核待办todo
		operateRecordService.cerateCurOperateRecord(pro,changeManagement.getDesignChangeType(), WorkFlowTypeEnum.ASSIST_PROGRESS.getValue(), AssistProgressTypeEnum.DESIGN_CHANGE_PROGRESS.getValue(),changeManagement.getCmId(),new Staff(),null,false);
	
		changeManagementDao.saveOrUpdate(changeManagement);


		signatureService.saveOrUpdateSign(changeManagement.getMenuDes(),changeManagement.getSign(), changeManagement.getProjId(), changeManagement.getCmId(), changeManagement.getClass().getName(),flag);

	}
	
	/**
	 * 保存回调
	 * @author fuliwei
	 * @createTime 2018年1月21日
	 * @param 
	 * @return
	 */
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void saveSignNotice(String cwId) {
		String signState = SignStateEnum.ALREADY_SIGN.getValue();
		if(StringUtils.isNotBlank(cwId)){
			ChangeManagement cm=changeManagementDao.get(cwId);
			if(cm!=null){
				//现场代表
				if(StringUtils.isNotBlank(cm.getCustLeader())){
					//通知置为无效
					signNoticeService.saveThisSignNotice(SignPostEnum.BUILDER.getValue(), SignDataTypeEnum.DESIGNALTERATION.getValue(),
							cm.getCmId(), cm.getProjId(),signState);
				}
				//项目经理
				if(StringUtils.isNotBlank(cm.getCuPm())){
					//通知置为无效
					signNoticeService.saveThisSignNotice(SignPostEnum.CU_PM.getValue(), SignDataTypeEnum.DESIGNALTERATION.getValue(),
							cm.getCmId(), cm.getProjId(),signState);
				}
				//总监
				if(StringUtils.isNotBlank(cm.getSuCes())){
					//通知置为无效
					signNoticeService.saveThisSignNotice(SignPostEnum.SUCSE.getValue(), SignDataTypeEnum.DESIGNALTERATION.getValue(),
							cm.getCmId(), cm.getProjId(),signState);
				}
			}
		}
	}

	/**
	 * 变更材料整合
	 * 1.查询变更材料表
	 * 2.遍历变更材料表中的数据，如果该材料数据已存在材料清单表，直接修改设计量（累加），否则增加材料
	 * @author liaoyq
	 * @return 
	 * @creatime 2018-2-7
	 */
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public boolean updateMaterialList(Project pro,
			ChangeManagement changeManagement) {
		//根据工程名称和变更ID查询
		MaterialChangeReq req = new MaterialChangeReq();
		req.setCmId(changeManagement.getCmId());
		req.setProjId(pro.getProjId());
		List<MaterialChange> materialChanges = materialChangeDao.queryMaterialChangeList(req);
		List<MaterialList> newMLists = new ArrayList<MaterialList>();//新增材料
		List<MaterialList> oldMLists = new ArrayList<MaterialList>();//变更材料
		if(materialChanges!=null){
			for(MaterialChange mg : materialChanges){
				MaterialList materialList = new MaterialList();
				//根据材料名称和规格查询材料清单
				//去掉合并主材和变更材料
				/*MaterialListQueryReq materialListQueryReq = new MaterialListQueryReq();
				materialList = materialListDao.queryByNameAndSpec(mg.getProjId(),mg.getMaterialName(),mg.getMaterialSpec(),mg.getFlag());
				if(materialList!=null){//已存在该材料
					if(materialList.getMaterialNum()!=null && mg.getAdjustQuantity()!=null){
						materialList.setMaterialNum(materialList.getMaterialNum().add(mg.getAdjustQuantity()));
						oldMLists.add(materialList);
					}
				}else{*/
					materialList = new MaterialList();
					materialList.setMaterialId(IDUtil.getUniqueId(Constants.MODULE_CODE_BUDGET));
					materialList.setProjId(pro.getProjId());
					materialList.setProjName(pro.getProjName());
					materialList.setProjNo(pro.getProjNo());
					materialList.setMaterialName(mg.getMaterialName());
					materialList.setMaterialSpec(mg.getMaterialSpec());
					materialList.setMaterialUnit(mg.getMaterialUnit());
					materialList.setMaterialNum(mg.getAdjustQuantity());
					materialList.setCmId(changeManagement.getCmId());
					materialList.setRemark("");
					materialList.setFlag(StringUtil.isNotBlank(mg.getFlag())?mg.getFlag():"1");
					newMLists.add(materialList);
			//	}
			}
			//修改材料清单
			materialListDao.batchInsertOrUpdateObjects(oldMLists);
			//增加新的材料
			materialListDao.batchInsertObjects(newMLists);
			return true;
		}
		return false;
	}

	/**
	 * 未终止、未完成的变更
	 */
	@Override
	public List<ChangeManagement> noCancelChangeManagement(String projId,String changeType) {
		return changeManagementDao.findNoCancelCM(projId,changeType);
	}
	
	

}
