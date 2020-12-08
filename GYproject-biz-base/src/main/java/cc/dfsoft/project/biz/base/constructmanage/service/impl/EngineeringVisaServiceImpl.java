package cc.dfsoft.project.biz.base.constructmanage.service.impl;

import cc.dfsoft.project.biz.base.baseinfo.dao.DocTypeDao;
import cc.dfsoft.project.biz.base.baseinfo.entity.DocType;
import cc.dfsoft.project.biz.base.baseinfo.enums.AssistProgressTypeEnum;
import cc.dfsoft.project.biz.base.baseinfo.enums.WorkFlowTypeEnum;
import cc.dfsoft.project.biz.base.baseinfo.service.DocTypeService;
import cc.dfsoft.project.biz.base.baseinfo.service.FestivalService;
import cc.dfsoft.project.biz.base.baseinfo.service.WorkFlowService;
import cc.dfsoft.project.biz.base.budget.dao.BudgetDao;
import cc.dfsoft.project.biz.base.budget.dao.MaterialListDao;
import cc.dfsoft.project.biz.base.budget.dto.MaterialListQueryReq;
import cc.dfsoft.project.biz.base.budget.entity.Budget;
import cc.dfsoft.project.biz.base.budget.entity.MaterialList;
import cc.dfsoft.project.biz.base.budget.service.BudgetService;
import cc.dfsoft.project.biz.base.change.dao.MaterialChangeDao;
import cc.dfsoft.project.biz.base.change.dto.MaterialChangeReq;
import cc.dfsoft.project.biz.base.change.entity.MaterialChange;
import cc.dfsoft.project.biz.base.change.enums.ChangeStateEnum;
import cc.dfsoft.project.biz.base.change.enums.MCTypeEnum;
import cc.dfsoft.project.biz.base.charge.enums.ARFlagEnum;
import cc.dfsoft.project.biz.base.charge.enums.ArContractTypeEnum;
import cc.dfsoft.project.biz.base.charge.service.AccrualsRecordService;
import cc.dfsoft.project.biz.base.common.dao.SysConfigDao;
import cc.dfsoft.project.biz.base.common.entity.SysConfigBean;
import cc.dfsoft.project.biz.base.complete.enums.CompletionDataPrintEnum;
import cc.dfsoft.project.biz.base.constructmanage.dao.EngineeringVisaDao;
import cc.dfsoft.project.biz.base.constructmanage.dao.VisaQuantitiesRecordDao;
import cc.dfsoft.project.biz.base.constructmanage.dto.CompletionDataPrintDto;
import cc.dfsoft.project.biz.base.constructmanage.dto.EngineeringVisaQueryReq;
import cc.dfsoft.project.biz.base.constructmanage.dto.UploadResult;
import cc.dfsoft.project.biz.base.constructmanage.entity.EngineeringVisa;
import cc.dfsoft.project.biz.base.constructmanage.entity.VisaQuantitiesRecord;
import cc.dfsoft.project.biz.base.constructmanage.enums.*;
import cc.dfsoft.project.biz.base.constructmanage.service.EngineeringVisaService;
import cc.dfsoft.project.biz.base.contract.dao.SupplementalContractDao;
import cc.dfsoft.project.biz.base.contract.entity.SupplementalContract;
import cc.dfsoft.project.biz.base.plan.service.ConstructionPlanService;
import cc.dfsoft.project.biz.base.project.dao.*;
import cc.dfsoft.project.biz.base.project.dto.AccessoryQueryReq;
import cc.dfsoft.project.biz.base.project.dto.ManageRecordQueryReq;
import cc.dfsoft.project.biz.base.project.dto.OperateRecordQueryReq;
import cc.dfsoft.project.biz.base.project.entity.*;
import cc.dfsoft.project.biz.base.project.enums.*;
import cc.dfsoft.project.biz.base.project.service.*;
import cc.dfsoft.project.biz.base.subpackage.dao.SubBudgetDao;
import cc.dfsoft.project.biz.base.subpackage.entity.SubBudget;
import cc.dfsoft.project.biz.ifs.ResultMessage;
import cc.dfsoft.project.biz.ifs.WebServiceTypeEnum;
import cc.dfsoft.project.biz.ifs.finance.enums.ResultTypeEnum;
import cc.dfsoft.project.biz.ifs.material.enums.MaterialOperateTypeEnum;
import cc.dfsoft.project.biz.ifs.material.enums.MaterialTableTypeEnum;
import cc.dfsoft.project.biz.ifs.material.service.MaterialNcService;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dao.StaffDao;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.biz.sys.dept.entity.Staff;
import cc.dfsoft.uexpress.biz.sys.dept.enums.PostTypeEnum;
import cc.dfsoft.uexpress.biz.sys.dept.enums.UnitTypeEnum;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.enums.WorkFlowActionEnum;
import cc.dfsoft.uexpress.common.exception.ExpressException;
import cc.dfsoft.uexpress.common.util.FestivalUtil;
import cc.dfsoft.uexpress.common.util.FileUtil;
import cc.dfsoft.uexpress.common.util.IDUtil;
import cc.dfsoft.uexpress.common.util.StringUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fr.stable.StringUtils;
import net.sf.json.JSONSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * 签证服务接口实现
 *
 * @author cui
 *
 */
@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class EngineeringVisaServiceImpl implements EngineeringVisaService {

	/** 签证记录Dao */
	@Resource
	EngineeringVisaDao engineeringVisaDao;

	@Resource
	SignatureService signatureService;

	@Resource
	SupplementalContractDao supplementalContractDao;

	@Resource
	ProjectDao projectDao;

	@Resource
	AccrualsRecordService accrualsRecordService;

	/** 签证工程量记录 */
	@Resource
	VisaQuantitiesRecordDao visaQuantitiesRecordDao;

	/** 通知服务 */
	@Resource
	NoticeService noticeService;

	/** 通知服务Dao */
	@Resource
	NoticeDao noticeDao;

	/** 审核级别 */
	@Resource
	DocTypeDao docTypeDao;

	@Resource
	BudgetDao budgetDao;
	@Resource
	BudgetService budgetService;

	/** 审核记录 */
	@Resource
	ManageRecordDao manageRecordDao;

	@Resource
	AccessoryDao accessoryDao;
	@Resource
	AccessoryService accessoryService;

	@Autowired
	OperateRecordDao operateRecordDao;

	/**签字通知服务*/
	@Resource
	SignNoticeService signNoticeService;
	@Resource
	MaterialChangeDao materialChangeDao;
	@Resource
	MaterialListDao materialListDao;
	@Resource
	ProjectService projService;
	@Resource
	MaterialNcService materialNcService;

	@Resource
	DocTypeService docTypeService;
	@Resource
	SysConfigDao sysConfigDao;
	@Resource
	FestivalService festivalService;


	@Resource
	SignNoticeDao signNoticeDao;
	@Resource
	OperateRecordService operateRecordService;
	@Resource
	ConstructionPlanService constructionPlanService;

	@Autowired
	SubBudgetDao subBudgetDao;

	@Autowired
	StaffDao staffDao;
	@Resource
	WorkFlowService workFlowService;
	
	@Override
	public Map<String, Object> queryEngineeringVisa(EngineeringVisaQueryReq engineeringVisaQueryReq) throws ParseException {
		//return engineeringVisaDao.queryEngineeringVisa(engineeringVisaQueryReq);
		Map<String, Object> map=engineeringVisaDao.queryEngineeringVisa(engineeringVisaQueryReq);
		List<EngineeringVisa> list=(List<EngineeringVisa>) map.get("data");
		AccessoryQueryReq accessoryQueryReq = new AccessoryQueryReq();
		if (list!=null && list.size()>0) {
			for (EngineeringVisa engineeringVisa:list) {
				Project project=projectDao.get(engineeringVisa.getProjId());
				accessoryQueryReq.setProjId(project.getProjId()); // 工程ID
				accessoryQueryReq.setBusRecordId(engineeringVisa.getEvId()); // 业务Id
				accessoryQueryReq.setSourceType(AccessorySourceEnum.COLLECT_FILE.getValue()); // 附件来源，0表示是通过页面右侧关联操作采集的文件
				accessoryQueryReq.setStep(StepOutWorkflowEnum.ENGINEERING_RECORD.getValue()); // 步骤id,当前签证记录的步骤Id为
				Map<String, Object> MapAccessoryLists = accessoryService.queryAccessoryList(accessoryQueryReq);
				List<AccessoryList> listAccessoryLists = (List<AccessoryList>)MapAccessoryLists.get("data");
				if (listAccessoryLists !=null && listAccessoryLists.size() > 0) {
					engineeringVisa.setIsBudgetBook(Integer.toString(listAccessoryLists.size())); // 已上传附件
				} else {
					engineeringVisa.setIsBudgetBook("0"); // 未上传附件
				}
				if (project!=null) {
					engineeringVisa.setProjectType(project.getProjectType());
					engineeringVisa.setBudgeterAudit(project.getBudgeterAudit());
				}
				//签证待办人
				engineeringVisa.setTodoer(operateRecordService.queryTodoer(engineeringVisa.getEvId()));
			}
		}

		return map;
	}

	@Override
	public EngineeringVisa viewEngineeringVisa(String id) {
		EngineeringVisa engineeringVisa = engineeringVisaDao.get(id);
		return engineeringVisa;
	}

	@Override
	public EngineeringVisa viewEngineeringVisa(String id, String menuDes) {
		EngineeringVisa engineeringVisa = engineeringVisaDao.get(id);         //根据主键ID得到签证所有信息
		if(engineeringVisa == null){return engineeringVisa;}

		List<Signature> signatureList=signatureService.findListByBusId(id);

		if (signatureList!=null && signatureList.size()>0){
			for (Signature sig:signatureList) {
				if (org.apache.commons.lang3.StringUtils.isNotBlank(sig.getFieldName())){
					if ("cmoPrincipal".equals(sig.getFieldName())){//现场代表
						engineeringVisa.setBuilderAuditDate(sig.getSignTime());
					}else if ("suJgj".equals(sig.getFieldName())){//监理
						engineeringVisa.setSuAuditDate(sig.getSignTime());
					}else if ("suPrincipal".equals(sig.getFieldName())){//项目经理
						engineeringVisa.setCustAuditDate(sig.getSignTime());
					}else if ("builder".equals(sig.getFieldName())){//施工员

					}
				}
			}
		}

		Budget budget = budgetService.queryByType(engineeringVisa.getProjId(),id, MCTypeEnum.MATERIAL_VISA.getValue());
		Signature signature = signatureService.selectImg(id, menuDes);
		if (signature != null) {
			engineeringVisa.setDrawUrl(Constants.DIAGRAM_DISK_PATH
					+ signature.getImgUrl());
		}
		if (budget != null && StringUtil.isNotBlank(budget.getBudgetId())) {// 已有预算调整
			engineeringVisa.setQuantitiesTotal(budget.getBudgetTotalCost());// 预算金额
			engineeringVisa.setRemark(budget.getRemark());   //备注
			engineeringVisa.setBudgeter(budget.getBudgeter()); // 预算人
			engineeringVisa.setBudgetAdjustDate(budget.getBudgetAdjustDate()
					.toString());// 预算时间
		}
		engineeringVisa.setMenuDes(menuDes);

		List<AccessoryList> list = accessoryService.queryAccessoryByBus(engineeringVisa.getEvId(),AccessorySourceEnum.CHANGE_FILE.getValue());
		if(list!=null && list.size()>0){
			engineeringVisa.setFileUrl(list.get(0).getAlId());
		}
		return engineeringVisa;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public String saveEngineeringVisa(EngineeringVisa engineeringVisa)
			throws Exception {
		boolean flag = false;
		if (StringUtils.isBlank(engineeringVisa.getEvId())) {// 新增
			flag = true;
			engineeringVisa.setEvId(IDUtil
					.getUniqueId(Constants.MODULE_CODE_PROCESS));// 设置唯一id 施工过程
			engineeringVisa.setEvState(ChangeStateEnum.NO_HANDLE.getValue()); // 设置变更状态--未处理
			engineeringVisa.setFlag("0");//签证审核有无不通过标记
			//设置自增签证编号
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			String date = sdf.format(engineeringVisaDao.getDatabaseDate());
			String evNo = engineeringVisaDao.getMaxEvNo(date,engineeringVisa.getProjId());
			if(null == evNo || "".equals(evNo)){
				evNo = date+"01";
			}
			engineeringVisa.setEvNo(evNo);
		}
		engineeringVisa.setBackReason("");
		engineeringVisa.setAuditState(StageProjectApplicationEnum.TO_PUSH
				.getValue());
		;
		if(engineeringVisa.getSuPrincipal()==null||engineeringVisa.getBuilder()==null||engineeringVisa.getCmoPrincipal()==null||engineeringVisa.getSuJgj()==null){
			engineeringVisa.setIsSignComplete("0");
		}else{
			engineeringVisa.setIsSignComplete("1");
		}
		this.insertEnVisaOperateRecord(engineeringVisa);  //调用方法生成预算员工作通知
		engineeringVisaDao.saveOrUpdate(engineeringVisa);

		List<Signature> signs=engineeringVisa.getSign();
		if(signs!=null && signs.size()>0){
			for(Signature sign:signs){
				sign.setDataType(SignDataTypeEnum.ENGINEERING.getValue());
			}
			engineeringVisa.setSignature(signs);
		}

		signatureService.saveOrUpdateSign(engineeringVisa.getMenuDes(),engineeringVisa.getSign(),
				engineeringVisa.getProjId(), engineeringVisa.getEvId(),
				engineeringVisa.getClass().getName(), flag);
		//新增签证：生成待办事项:todo
		if(StageProjectApplicationEnum.TO_PUSH
				.getValue().equals(engineeringVisa.getAuditState()) && flag){
			Staff staff = new Staff();
			staff.setStaffId(SessionUtil.getLoginInfo().getStaffId());
			staff.setStaffName(SessionUtil.getLoginInfo().getStaffName());
			operateRecordService.cerateCurOperateRecord(projectDao.get(engineeringVisa.getProjId()), engineeringVisa.getAuditState(), WorkFlowTypeEnum.ASSIST_PROGRESS.getValue(), AssistProgressTypeEnum.ENGINEER_PROGRESS.getValue(),engineeringVisa.getEvId(),staff,null,true);
		}
		return engineeringVisa.getEvId();
	}

	/**
	 * 签证预算调整确认 更新签证状态
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void updateVisaState(String id) {
		engineeringVisaDao.updateVisaState(id);
		SupplementalContract supplementalContract = supplementalContractDao
				.findByCmId(id, MCTypeEnum.MATERIAL_VISA.getValue());
		if (supplementalContract != null) {
			Project pro = projectDao.get(supplementalContract.getProjId());
			BigDecimal payment = supplementalContract.getScAmount();
			accrualsRecordService.insertAccrualsRecord(pro,
					IDUtil.getUniqueId(Constants.MODULE_CODE_COST),
					CollectionTypeEnum.SUPPLEMENTAL_CONTRACT.getValue(),
					Integer.valueOf(ARFlagEnum.RECEIVE_ACCOUNT.getValue()),
					payment, ArContractTypeEnum.SUP_CONTRACT.getValue(),supplementalContract.getConNo());
		}
	}

	/**
	 * 签证预算调整确认 更新签证状态
	 *
	 * @author liaoyq
	 * @throws Exception
	 * @createTime 2017-9-7
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED,rollbackFor = {Exception.class})
	public String visaConfirm(Budget budget) throws Exception {
		// 修改须算
		Budget budget2 = budgetDao.get(budget.getBudgetId());
		// 已预算
		if (budget2 != null) {
			budget2.setBudgetTotalCost(budget.getBudgetTotalCost());
			budget2.setRemark(budget.getRemark());
			budgetDao.update(budget2);
		} else {// 未预算
			budgetService.updateBudgeAdjust(budget);
		}
		// 预算确认
		if("2".equals(budget.getAuditResult())){//审核未通过
			EngineeringVisa engineeringVisa = engineeringVisaDao.get(budget.getCmId());
			engineeringVisa.setAuditState(StageProjectApplicationEnum.RE_PUSH.getValue());//待调整
			engineeringVisa.setQuantitiesTotal(budget.getBudgetTotalCost());//签证
			if(engineeringVisa.getCrontabDate()!=null){
				engineeringVisa.setCrontabDate(null);//置空
			}
			engineeringVisaDao.update(engineeringVisa);
			ManageRecord manageRecord=new ManageRecord();
			manageRecord.setMrId(IDUtil.getUniqueId(Constants.MODULE_CODE_PROCESS));		//审核记录主键id
			manageRecord.setStepId(StepOutWorkflowEnum.ENGINEERING_AUDIT.getValue());
			manageRecord.setMrResult(MrResultEnum.NOT_PASSED.getValue());
			LoginInfo loginInfo = SessionUtil.getLoginInfo();
			manageRecord.setMrCsr(loginInfo.getStaffId()); 			//审核人
			manageRecord.setMrTime(engineeringVisaDao.getDatabaseDate());
			manageRecord.setMrAopinion(budget.getAuditOpinion());//审核意见
			manageRecord.setBusinessOrderId(budget.getCmId());
			manageRecord.setProjNo(engineeringVisa.getProjNo());
			manageRecord.setFlag("0");//预算审核不通过
			manageRecord.setProjId(engineeringVisa.getProjId());
			manageRecordDao.saveOrUpdate(manageRecord);
			//重新调整推送代办人：施工员
			Staff staff = new Staff();
			Project pro = projectDao.get(engineeringVisa.getEvId());
			staff.setStaffId(StringUtil.isNotBlank(pro.getManagementQaeId())?pro.getManagementQaeId():"");
			staff.setStaffName(StringUtil.isNotBlank(pro.getManagementQae())?pro.getManagementQae():"");
			operateRecordService.cerateCurOperateRecord(pro,engineeringVisa.getAuditState(), WorkFlowTypeEnum.ASSIST_PROGRESS.getValue(), AssistProgressTypeEnum.ENGINEER_PROGRESS.getValue(),manageRecord.getBusinessOrderId(),staff,null,true);

			return Constants.OPERATE_RESULT_SUCCESS;
		}else{//通过

			String res=this.pushEv(budget.getCmId(),budget.getBudgetTotalCost()!=null?budget.getBudgetTotalCost():new BigDecimal(0));

			ManageRecord manageRecord=new ManageRecord();
			manageRecord.setMrId(IDUtil.getUniqueId(Constants.MODULE_CODE_PROCESS));		//审核记录主键id
			manageRecord.setStepId(StepOutWorkflowEnum.ENGINEERING_AUDIT.getValue());
			manageRecord.setMrResult(MrResultEnum.PASSED.getValue()); 	//审核通过
			LoginInfo loginInfo = SessionUtil.getLoginInfo();
			manageRecord.setMrCsr(loginInfo.getStaffId()); 			//审核人
			manageRecord.setMrTime(engineeringVisaDao.getDatabaseDate());
			manageRecord.setMrAopinion(budget.getAuditOpinion());//审核意见
			manageRecord.setBusinessOrderId(budget.getCmId());
			manageRecord.setProjNo(budget.getProjNo());
			manageRecord.setFlag("1");//预算审核通过
			manageRecord.setProjId(budget.getProjId());
			manageRecordDao.saveOrUpdate(manageRecord);
			return res;
		}
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void updateEngineeringVisaState() throws Exception {
		System.out
				.println("**************************updateEnginneringVisaState**********************************");
		engineeringVisaDao.updateEngineeringVisaState();
	}

	/**
	 * 签证记录保存
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public String saveEngineeringVisaFile(HttpServletRequest request, UploadResult enginneringVisa, MultipartFile[] files)
			throws Exception {
		String result = enginneringVisa.getResult();
		JSONObject jo = new JSONObject();
		EngineeringVisa cmt = JSON.parseObject(result, EngineeringVisa.class);
		if ("0".equals(cmt.getCmoPrincipalResult()) || "0".equals(cmt.getSuResult())) {
			cmt.setIsPass("0");
			signNoticeDao.deleteByBsId(cmt.getEvId(),SignDataTypeEnum.ENGINEERING.getValue()); //审核不通过时签字通知置为无效
			this.deleteOperateRecord(cmt.getProjId(),cmt); // 删除预算员工作通知
		} else {
			cmt.setIsPass("1");
		}
		List<VisaQuantitiesRecord> list = cmt.getList();

		JSONArray sign = (JSONArray) jo.parseObject(result).get("sign");
		if (sign != null) {
			List<Signature> signs = JSONObject.parseArray(sign.toJSONString(),
					Signature.class);
			cmt.setSignature(signs);
		}

		/*
		 * if(StringUtil.isBlank(cmt.getDrawName())){
		 * cmt.setDrawName(files[0].getOriginalFilename());
		 * signatureService.saveImage(request, files, cmt.getProjId(),
		 * cmt.getProjNo(), cmt.getTpId(), cmt.getMenuDes()); }
		 */
		String name = cmt.getDrawName();// 这次有没有存图片
		if (files != null) {
			cmt.setDrawName(files[0].getOriginalFilename());
		}

		String evId = this.saveEngineeringVisa(cmt); // 保存签证信息
		// 施工单位有异议重审签证预算
		if (CuReStateEnum.TO_RE_AUDIT.getValue().equals(cmt.getCuReState())) { // 重新审核执行推送签证的功能
			this.pushEvToBudget(cmt.getEvId());
			//是否增加重审的的不通过审核历史
			ManageRecord manageRecord=new ManageRecord();
			manageRecord.setMrId(IDUtil.getUniqueId(Constants.MODULE_CODE_PROCESS)); // 审核记录主键id
			manageRecord.setStepId(StepOutWorkflowEnum.ENGINEERING_AUDIT.getValue());
			manageRecord.setMrResult(MrResultEnum.NOT_PASSED.getValue());
			LoginInfo loginInfo = SessionUtil.getLoginInfo();
			manageRecord.setMrCsr(loginInfo.getStaffId()); // 审核人
			manageRecord.setMrTime(engineeringVisaDao.getDatabaseDate());
			manageRecord.setMrAopinion("请求重审："+cmt.getCuReReason()); // 重审原因
			manageRecord.setBusinessOrderId(cmt.getEvId());
			manageRecord.setProjNo(cmt.getProjNo());
			manageRecord.setFlag("0"); // 预算审核不通过-作废
			manageRecord.setProjId(cmt.getProjId());
			manageRecordDao.saveOrUpdate(manageRecord);
		}
		if (StringUtil.isBlank(name)) {
			List<AccessoryList> accs =accessoryDao.queryAccessoryByBus(cmt.getEvId(),AccessorySourceEnum.CHANGE_FILE.getValue());
			if (accs!=null&&accs.size()>0) {
				if (files==null) {
					FileUtil.deleteFile(Constants.DISK_PATH+accs.get(0).getAlPath());
				}
				accessoryDao.delete(accs.get(0));
			}
			if (files!=null) {
				AccessoryList acc = new AccessoryList();
				String path=FileUtil.uploadFile(request, enginneringVisa.getAlPath(), files);
				String fileName = files[0].getOriginalFilename();
				//String filePath= Constants.FIRST_DISK_PATH + path + fileName;
				String filePath= Constants.FIRST_DISK_PATH + path ;
				String name1 = fileName.substring(0,fileName.lastIndexOf("."));
				String fileType=fileName.substring(fileName.lastIndexOf(".")+1,fileName.length());
				LoginInfo loginInfo = SessionUtil.getLoginInfo();
				acc.setAlId(IDUtil.getUniqueId(Constants.FILE_CODE_ACCESS));
				acc.setProjId(cmt.getProjId());
				acc.setProjNo(cmt.getProjNo());
				acc.setAlTypeId(fileType);
				acc.setStepId(enginneringVisa.getStepId());
				acc.setStep(StringUtil.isNotBlank(enginneringVisa.getStep())?enginneringVisa.getStep():"");
				acc.setAlName(name1);
				acc.setAlPath(filePath);
				acc.setAlOperateCsrId(loginInfo.getStaffId());
				acc.setAlOperateCsr(loginInfo.getStaffName());
				acc.setAlOperateTime(accessoryDao.getDatabaseDate());
				acc.setSourceType(AccessorySourceEnum.CHANGE_FILE.getValue());
				acc.setBusRecordId(cmt.getEvId());
				request.setAttribute("accListId", acc.getAlId());
				accessoryDao.save(acc);
			}
		}

		// 通过签证id删除原来的签证工程量
		Boolean flag = false;
		visaQuantitiesRecordDao.deleteById(evId);
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				list.get(i).setEvId(evId);
				list.get(i).setProjId(cmt.getProjId());
				list.get(i).setVqeId(
						IDUtil.getUniqueId(Constants.MODULE_CODE_PROCESS));
				if (flag == false) {
					BigDecimal a = list.get(i).getActualNum();
					BigDecimal b = list.get(i).getQuantitiesNum();
					if (a != null) {
						int val = a.compareTo(b);
						if (val == 1 || val == 0) {
							// 大于
							flag = true;
						}
					}

				}

			}
		}
		// 批量保存签证工程量
		//visaQuantitiesRecordDao.batchInsertObjects(list);//这注释原因：1.业务要求不需要保存工程量，2.签证由版本控制，该批量保存方法会导致出现异常；若需要保存工程量时不要用此方法
		Date databaseDate = visaQuantitiesRecordDao.getDatabaseDate();
		Date evDate = cmt.getVisaDate();
		// 大于标准

		// true 发送通知到造价合同处和成本控制部 false //不发送到成本控制部

		if (databaseDate.after(evDate)) {
			// 将通知置为无效
			noticeService.updateEngineeringNotice(evId,
					ActionEnum.ENGINEERING.getValue()); // 设置通知失效
		} else {
			// 删除原来的通知
			noticeDao.deleteByEvId(evId);
			// 下发通知
			noticeService.saveNotice(evId, cmt.getProjId(), cmt.getProjName(),
					evDate, ActionEnum.ENGINEERING.getMessage(),
					ActionEnum.ENGINEERING.getValue(), flag);
		}

		enginneringVisa.setOperateId(cmt.getEvId());
//		if (StringUtil.isBlank(name)) {
//			signatureService.saveImage(request, files, cmt.getProjId(),
//					cmt.getProjNo(), cmt.getEvId(), cmt.getMenuDes());
//		}
		// 返回记录ID
		return evId;
	}

	/**
	 * 签证记录保存时生成通知，通知预算员上传上预算书
	 * @param
	 * @param
	 * @throws Exception
	 */
    @Transactional (readOnly = false, propagation = Propagation.REQUIRED)
	public  void insertEnVisaOperateRecord (EngineeringVisa engineeringVisa) throws Exception {
		SubBudget subBudget = new SubBudget();
		Project project = new Project();
		Staff staff = new Staff();
		OperateRecordQueryReq operateRecordQueryReq = new OperateRecordQueryReq();
		List<OperateRecord>  listSubBudgetOperateRecord = new ArrayList<OperateRecord>(16); // 初始化集合时必须默认大小，未知大小时默认为16，不初始化大小，会影响性能
		if (StringUtils.isNotBlank(engineeringVisa.getProjId())) {
			subBudget = subBudgetDao.get("projId",engineeringVisa.getProjId()); // 得到分包预算表操作人
			project = projectDao.get(engineeringVisa.getProjId()); // 得到工程信息
		}
		if (subBudget != null && StringUtils.isNotBlank(subBudget.getOperaterId())) {
			staff = staffDao.get("staffId",subBudget.getOperaterId()); // 根据人员Id取得人员信息
			if (staff == null) { //若为空则抛出异常，提示无该员工，或者该员工已离职，需要更改分包预算员
				throw new Exception("无该员工,或者该员工已经离职，请检查分包预算员是否为在职!");
			}
		} else {
			operateRecordQueryReq.setProjId(project.getProjId());
			operateRecordQueryReq.setProjNo(project.getProjNo());
			operateRecordQueryReq.setStepId(StepEnum.QUALITIES_DECLARATION.getValue()); // 施工预算stepID
			operateRecordQueryReq.setProjectType(project.getProjectType());
			operateRecordQueryReq.setContributionMode(project.getContributionMode());
			operateRecordQueryReq.setIsValid("1");
			operateRecordQueryReq.setCorpId(project.getCorpId());
		    Map<String, Object> mapSubBudgetOperateRecord = operateRecordService.queryOperateRecordHistory(operateRecordQueryReq); // 查询施工预算有效通知
		    listSubBudgetOperateRecord = (List<OperateRecord>)mapSubBudgetOperateRecord.get("data");
		   
		}
		AccessoryQueryReq  accessoryQueryReq = new AccessoryQueryReq();
		accessoryQueryReq.setProjId(project.getProjId()); // 工程ID
		accessoryQueryReq.setBusRecordId(engineeringVisa.getEvId()); // 签证记录Id
		accessoryQueryReq.setSourceType("0"); // 附件来源，0表示是通过页面右侧关联操作采集的文件
		accessoryQueryReq.setStep(StepOutWorkflowEnum.ENGINEERING_RECORD.getValue()); // 步骤id,当前签证记录的步骤Id为
		Map<String, Object> MapAccessoryLists = accessoryService.queryAccessoryList(accessoryQueryReq);
		List<AccessoryList> listAccessoryLists = (List<AccessoryList>)MapAccessoryLists.get("data");

		operateRecordQueryReq.setProjId(project.getProjId());
		operateRecordQueryReq.setProjNo(project.getProjNo());
		operateRecordQueryReq.setStepId(StepOutWorkflowEnum.ENGINEERING_RECORD.getValue());
		operateRecordQueryReq.setProjectType(project.getProjectType());
		operateRecordQueryReq.setContributionMode(project.getContributionMode());
		operateRecordQueryReq.setIsValid("1");
		operateRecordQueryReq.setBusinessOrderId(engineeringVisa.getEvId());
		operateRecordQueryReq.setCorpId(project.getCorpId());
	    Map<String, Object> MapOldOperateRecord = operateRecordService.queryOperateRecordHistory(operateRecordQueryReq); // 查询签证记录上传预算书有效通知
	    List<OperateRecord>  listOldOperateRecord = (List<OperateRecord>)MapOldOperateRecord.get("data");
		 // 生成签证记录操作记录
		if (listOldOperateRecord.size() < 1 && ((subBudget != null && StringUtils.isNotBlank(subBudget.getOperaterId())) || (listSubBudgetOperateRecord.size() > 0 && StringUtils.isNotBlank(listSubBudgetOperateRecord.get(0).getOperateCsr1()))) && listAccessoryLists.size() < 1 && (!"0".equals(engineeringVisa.getCmoPrincipalResult())) && (!"0".equals(engineeringVisa.getSuResult()))) {
			//生成业务操作记录的方法
			OperateRecord operateRecord = new OperateRecord();
			operateRecord.setOrId(IDUtil.getUniqueId(Constants.MODULE_CODE_PROCESS));
			operateRecord.setProjId(project.getProjId());
			operateRecord.setProjNo(project.getProjNo());
			operateRecord.setStepId(StepOutWorkflowEnum.ENGINEERING_RECORD.getValue());
			operateRecord.setStepName("签证记录(请上传预算书,编号:"+engineeringVisa.getEvNo()+")");
			operateRecord.setRemark("施工单位预算员上传预算书");
			operateRecord.setOperateCsr1(StringUtils.isNotBlank(staff.getStaffId())? (","+staff.getStaffId()+","): listSubBudgetOperateRecord.size() > 0? listSubBudgetOperateRecord.get(0).getOperateCsr1() : "");
			operateRecord.setOperater(StringUtils.isNotBlank(staff.getStaffName())? staff.getStaffName() : listSubBudgetOperateRecord.size() > 0? listSubBudgetOperateRecord.get(0).getOperater() : "施工单位预算员");
			operateRecord.setIsValid("1"); // 有效记录
			operateRecord.setModifyStatus(OperateWorkFlowEnum.WAIT_DONE.getValue());
			operateRecord.setCorpId(project.getCorpId());
			operateRecord.setProjectType(project.getProjectType());
			operateRecord.setContributionMode(project.getContributionMode());
			operateRecord.setOpType(WorkFlowTypeEnum.ASSIST_PROGRESS.getValue());
			operateRecord.setBusinessOrderId(engineeringVisa.getEvId());
			operateRecordDao.save(operateRecord);
		}

		if (project !=null) {
			projectDao.evict(project); // 托管对象
		}

		if (subBudget !=null) {
			subBudgetDao.evict(subBudget); // 托管对象
		}

		if (staff !=null) {
			staffDao.evict(staff); // 托管对象
		}
	}

	/**
	 * 删除操作记录
	 * @param projId
	 * @throws Exception
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void deleteOperateRecord (String projId,EngineeringVisa engineeringVisa) throws Exception {
		Project project = projectDao.get(projId); //取得记录
		OperateRecordQueryReq operateRecordQueryReq = new OperateRecordQueryReq();
		operateRecordQueryReq.setProjId(project.getProjId());
		operateRecordQueryReq.setProjNo(project.getProjNo());
		operateRecordQueryReq.setStepId(StepOutWorkflowEnum.ENGINEERING_RECORD.getValue());
		operateRecordQueryReq.setProjectType(project.getProjectType());
		operateRecordQueryReq.setContributionMode(project.getContributionMode());
		operateRecordQueryReq.setIsValid("1");
		operateRecordQueryReq.setModifyStatus("2");
		operateRecordQueryReq.setBusinessOrderId(engineeringVisa.getEvId());
		operateRecordQueryReq.setCorpId(project.getCorpId());
	    Map<String, Object> MapOldOperateRecord = operateRecordService.queryOperateRecordHistory(operateRecordQueryReq);
	    List<OperateRecord>  listOldOperateRecord = (List<OperateRecord>)MapOldOperateRecord.get("data");
		if (listOldOperateRecord.size() > 0 && listOldOperateRecord !=null) {
			operateRecordDao.batUpdateHistoryRecordByBoId(project.getProjId(), engineeringVisa.getEvId(), StepOutWorkflowEnum.ENGINEERING_RECORD.getValue()); // 置为无效
		}
		if (project !=null) {
			projectDao.evict(project); // 托管对象
		}
	}
	@Override
	public Map<String, Object> queryVisaAudit(EngineeringVisaQueryReq engineeringVisaQueryReq)
			throws ParseException {
		List<String> auditState = new ArrayList<String>();
		auditState.add(StageProjectApplicationEnum.TO_AUDIT.getValue());
		engineeringVisaQueryReq.setAuditState(auditState);
		Map<String, Object> result = engineeringVisaDao.queryEngineeringVisa(engineeringVisaQueryReq);

		//DocType docType = docTypeDao
		//.findByStepId(StepOutWorkflowEnum.ENGINEERING_AUDIT.getValue());
		DocType docType = new DocType();

		List<EngineeringVisa> data = (List<EngineeringVisa>) result.get("data");
		if (data != null && data.size() > 0) {
			String grade ="0";
			/**
			 * -1 未审核 0 审核未通过 1 审核通过 2待审核 若该
			 * 审核为三级审核，且一级审核通过、二级审核未通过，三级审核待审核，则键值对
			 * ：{"level1":"1","level2":"0","level3":"2"};
			 * */
			// 遍历循环 设置审核级别
			for (int i = 0; i < data.size(); i++) {
				Project pro=projectDao.get(data.get(i).getProjId());
				//获取审核级别
				String grade1 = grade;
				if(pro!=null){
					data.get(i).setProjectType(pro.getProjectType());
				}
				BigDecimal totalCost = engineeringVisaDao.getTotalCostByType(data.get(i).getProjId(), data.get(i).getEvType());
				if (totalCost == null) {
					totalCost = new BigDecimal(0);
				}
				BigDecimal cost = totalCost.add(data.get(i).getQuantitiesTotal()==null?new BigDecimal(0):data.get(i).getQuantitiesTotal());
				//判断审核级别
				String reserve = Constants.ENGINEERING_VISA+"_"+pro.getCorpId()+"_"+pro.getProjectType()+"_"+pro.getContributionMode();
				List<Map<String,Object>> consList = Constants.getConstantsMapByKey(reserve);
				if(consList == null || consList.size()<1){
					//分公司默认
					reserve =  Constants.ENGINEERING_VISA+"_"+pro.getCorpId();
					consList = Constants.getConstantsMapByKey(reserve);
				}
				//系统默认
				if(consList ==null || consList.size()<1){
					//默认级别
					consList = Constants.getConstantsMapByKey(Constants.ENGINEERING_VISA);
				}
				//获取审核级别
				for(Map<String,Object> map :consList){
					String [] str = String.valueOf(map.get("CNNAME")).split(",");
					BigDecimal big1 = new BigDecimal(str[0]);
					BigDecimal big2 = new BigDecimal(str[1]);
					if(cost.compareTo(big1) > 0&&cost.compareTo(big2) <= 0){
						grade1 = String.valueOf(map.get("CNVALUE"));
						break;
					}
				}
//				if (cost.compareTo(standard2) < 0) {
//					grade1 = String.valueOf(Integer.parseInt(grade) - 2);
//				} else if (cost.compareTo(standard3) < 0) {
//					grade1 = String.valueOf(Integer.parseInt(grade) - 1);
//				}
				data.get(i).setLevel(grade1); // 设置审核总级数（分期申请审核3级审核）
				
				//签证预算调整日期
				ManageRecord manageRecord = manageRecordDao.findEvBudgetDate(data.get(i).getEvId(),StepOutWorkflowEnum.ENGINEERING_AUDIT.getValue());
				data.get(i).setEvBudgetDate( manageRecord!=null && manageRecord.getMrTime()!=null ? manageRecord.getMrTime() : null);
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				String evOverDes = data.get(i).getEvBudgetDate()!=null?dateFormat.format(data.get(i).getEvBudgetDate()):"";
				
				Map<String, String> levelBtn = new HashMap<String, String>();
				// 以下循环，遍历出默认应该几级审核，审核状态分别为什么，例如三级审核，则一级待审、二级未审、三级未审{"level1":"2","level2":"-1","level3":"-1"}；
				for (int n = 1; StringUtil.isNotBlank(grade1) && n < Integer.parseInt(grade1) + 1; n++) {
					if (n == 1) {
						levelBtn.put("level" + n, "2");// 待审
					} else {
						levelBtn.put("level" + n, "-1");// 未审
					}
					//设定的超期可审核级别
					if(Integer.toString(n).equals(budgetReplaceAuditLevel())){
						this.handlAuditLevelBtn(levelBtn,data.get(i).getEvId());
						//组装剩余时限
						if(data.get(i).getCrontabDate()!=null){
							long overDay = isOverdue(data.get(i).getEvBudgetDate(), data.get(i).getCorpId(), budgetReplaceAuditLevel());
							if(overDay!=999){
								evOverDes = evOverDes + ",剩余时限：" + overDay+"天";
							}
							data.get(i).setOverDay(overDay);
						}
						data.get(i).setEvOverDes(evOverDes);
					}
				}
				
				ManageRecordQueryReq mrq = new ManageRecordQueryReq();
				mrq.setBusinessOrderId(data.get(i).getEvId());
				mrq.setStepId(StepOutWorkflowEnum.ENGINEERING_AUDIT.getValue());
				mrq.setProjId(data.get(i).getProjId());
				// List<ManageRecord> mrls = (List<ManageRecord>)
				// managerecorddao.queryManageRecord(mrq).get("data");
				List<ManageRecord> mrls = manageRecordDao
						.findByStepIdBusinessOrderId(data.get(i).getEvId(),
								StepOutWorkflowEnum.ENGINEERING_AUDIT
										.getValue(), MrResultEnum.PASSED
										.getValue());
				if (mrls != null && mrls.size() > 0) {
					// 遍历循环
					for (ManageRecord mr : mrls) {
						levelBtn.put("level" + mr.getMrAuditLevel(),
								mr.getMrResult());
					}
					if (mrls.size() < Integer.parseInt(grade1)) {
						levelBtn.put("level" + (mrls.size() + 1), "2");
					}
				}
				data.get(i).setMrAuditLevel(
						JSONSerializer.toJSON(levelBtn).toString());
				data.get(i).setEvTypeDesc(EngineeringTypeEnum.valueof(data.get(i).getEvType()).getMessage());
			}
			result.put("data", data);
		}
		return result;
	}

	@Override
	public Date getDatabaseDate() {
		return manageRecordDao.getDatabaseDate();
	}

	/**
	 * 推送签证到签证审核
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
	public String pushEv(String evId, BigDecimal quantitiesTotal) throws Exception{
		EngineeringVisa engineeringVisa = engineeringVisaDao.get(evId);
		engineeringVisa.setQuantitiesTotal(quantitiesTotal);
		if (engineeringVisa != null) {
			Project project = projectDao.get(engineeringVisa.getProjId());
			//判断审核级别：先根据最细的规则获取，取不到，则获取全局
			String reserve = Constants.ENGINEERING_VISA+"_"+project.getCorpId()+"_"+project.getProjectType()+"_"+project.getContributionMode();
			List<Map<String,Object>> consList = Constants.getConstantsMapByKey(reserve);
			if(consList ==null || consList.size()<1){
				consList = Constants.getConstantsMapByKey(Constants.ENGINEERING_VISA+"_"+project.getCorpId()+"_"+project.getProjectType());
			}
			if(consList ==null || consList.size()<1){
				consList = Constants.getConstantsMapByKey(Constants.ENGINEERING_VISA+"_"+project.getCorpId());
			}
			if(consList ==null || consList.size()<1){
				//默认级别
				consList = Constants.getConstantsMapByKey(Constants.ENGINEERING_VISA);
			}
			BigDecimal standard1 = new BigDecimal(-1);
			//获取需要审核的金额值
			for(Map<String,Object> map :consList){
				//有审核级别，取第一审核级别的签证金额
				if(map.get("CNVALUE")!=null && Integer.valueOf(map.get("CNVALUE").toString()).compareTo(1)==0){
					String [] str = String.valueOf(map.get("CNNAME")).split(",");
					standard1 = new BigDecimal(str[0]);
					break;
				}
			}
			BigDecimal totalCost = engineeringVisaDao.getTotalCostByType(engineeringVisa.getProjId(), engineeringVisa.getEvType());
			if (totalCost == null) {
				totalCost = new BigDecimal(0);
			}
			BigDecimal cost = totalCost.add(engineeringVisa.getQuantitiesTotal());
			engineeringVisa.setEvState(ChangeStateEnum.HANDLED.getValue()); // 签证状态为已处理

			//只要有签证金额，都需要施工单位预算员确认审核金额
			if (cost.compareTo(standard1) <= 0) {
				// 申请单状态更新为审核通过
				engineeringVisa.setAuditState(StageProjectApplicationEnum.PASSED.getValue());
				engineeringVisa.setCrontabDate(null);
				engineeringVisa.setBackReason("");
				//将施工单位确认状态重置为未确定
				engineeringVisa.setCuReState(CuReStateEnum.NOT_RE.getValue());
				engineeringVisaDao.update(engineeringVisa);
				boolean flags = this.updateMaterialList(engineeringVisa);
				//签证审核通过，需要调用接口，将签证材料传递到物资
				if(flags && projService.isToCall(engineeringVisa.getProjId(), WebServiceTypeEnum.MATERIAL_UPDATE.getValue())){
					String msg = materialNcService.synProjectInfoClient(engineeringVisa.getProjId(), engineeringVisa.getEvId(), MaterialTableTypeEnum.CHANGE_MATERIAL.getValue(), MaterialChangeTypeEnum.DESIGN_CHANGE.getValue(), MaterialOperateTypeEnum.CHANGE.getValue(),WebServiceTypeEnum.MATERIAL_UPDATE.getValue()) ;
					ResultMessage resultMessage = new ResultMessage();
					net.sf.json.JSONObject jsonbean = net.sf.json.JSONObject.fromObject(msg);
					//返回信息-当接口返回失败时，抛出异常
					resultMessage = (ResultMessage) net.sf.json.JSONObject.toBean(jsonbean,ResultMessage.class);
					if(resultMessage!=null && ResultTypeEnum.FAIL.getValue().equals(resultMessage.getRet_type()) && projService.isSynchronization(engineeringVisa.getProjId(), WebServiceTypeEnum.MATERIAL_UPDATE.getValue())){
						//回滚事物
						throw new ExpressException(resultMessage.getRet_type(),resultMessage.getRet_message());
					}
				}
				//待办todo:
				Staff staff = new Staff();
				operateRecordService.cerateCurOperateRecord(project,engineeringVisa.getAuditState(), WorkFlowTypeEnum.ASSIST_PROGRESS.getValue(), AssistProgressTypeEnum.ENGINEER_PROGRESS.getValue(),engineeringVisa.getEvId(),staff,null,false);
				return "pass";
			} else {
				// 申请单状态更新为待审核
				engineeringVisa.setAuditState(StageProjectApplicationEnum.TO_AUDIT.getValue());
				engineeringVisa.setBackReason("");
				//CrontabDate：签证预算调整通过后，判断是否需要增加定时任务时效
				if(this.engineeringVisaCrontabDate(engineeringVisa,project,budgetReplaceAuditLevel())){
					engineeringVisa.setCrontabDate(engineeringVisaDao.getDatabaseDate());
				}else{
					engineeringVisa.setCrontabDate(null);
				}
				engineeringVisaDao.update(engineeringVisa);
				//待办签证一级审核：todo
				//待办人施工单位预算员
				Staff staff = constructionPlanService.findCuBudgeter(project.getCuId(),project.getCorpId());
				operateRecordService.cerateCurOperateRecord(project,engineeringVisa.getAuditState(), WorkFlowTypeEnum.ASSIST_PROGRESS.getValue(), AssistProgressTypeEnum.ENGINEER_PROGRESS.getValue(),engineeringVisa.getEvId(),staff,"1",true);
			}
		}
		return Constants.OPERATE_RESULT_SUCCESS;
	}

	/**
	 * 注释：增加 配置，根据公司ID配置 超时超期天数,增加字段，签证预算调整第二次推送时，（存在有超期时效配置时）存储第二次推送日期
	 * @author liaoyq
	 * @createTime 2019年7月30日
	 * @param engineeringVisa
	 *
	 */
	private boolean engineeringVisaCrontabDate(EngineeringVisa engineeringVisa, Project project,String auditLevel) {
		//1.查看配置是否配置了时效，配置才会设置crontabDate
		//分公司配置
		String key =Constants.ENGINEERING_VISA_CRONTAB+"_"+auditLevel +"_"+ project.getCorpId();
		Object obj = Constants.getSysConfigByKey(key);
		if(obj == null){
			//全局默认
			key =  Constants.ENGINEERING_VISA_CRONTAB+"_"+auditLevel +"_"+ Constants.CORP_ID;
			obj = Constants.getConstantsMapByKey(key);
		}
		if(obj == null){
			return false;
		}
		//2.判断施工单位是否已有一次签证审核有异议回退历史,有则将crontabDate设置为当前日期，否则置空crontabDate
		ManageRecordQueryReq queryReq = new ManageRecordQueryReq();
		queryReq.setBusinessOrderId(engineeringVisa.getEvId());
		queryReq.setLevel(auditLevel);
		queryReq.setMrResult("0");//审核不通过的
		List<ManageRecord> manageRecords = manageRecordDao.queryManageRecords(queryReq);
		//存在审核不过的历史记录，说明之前存在过异议回退，则存储当前时间
		if(manageRecords!=null && manageRecords.size()>0){
			return true;
		}
		return false;
	}

	/**
	 * 签证推送-至预算调整
	 *
	 * @author liaoyq
	 * @createTime 2017-9-6
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public String pushEvToBudget(String evId) {
		EngineeringVisa engineeringVisa = engineeringVisaDao.get(evId);
		if (engineeringVisa != null) {
			engineeringVisa.setPushDate(engineeringVisaDao.getDatabaseDate());//签证推送日期
			if("1".equals(engineeringVisa.getIsSignComplete()) && "1".equals(engineeringVisa.getIsPass())){//完成签字并且审核通过
				Project project = projectDao.get(engineeringVisa.getProjId());
				Staff staff = new Staff();
				staff.setStaffId(project.getBudgeterAuditId());
				staff.setStaffName(project.getBudgeterAudit());
				//获取下一步骤
				String status = this.getEngineeringNextAuditState(engineeringVisa,project);
				String grade=null;
				if(WorkFlowActionEnum.CONTRACT_END.getActionCode().equals(status)){
					//未配置流 按原来的走
					// 修改记录状态为待预算调整
					engineeringVisa.setAuditState(StageProjectApplicationEnum.TO_BUDGET
							.getValue());
				}else{
					//签证量审核
					if(StageProjectApplicationEnum.QUANTY_AUDIT.getValue().equals(status)){
						grade = "1";//默认触发1级审核待办
					}
					engineeringVisa.setAuditState(status);
				}
				engineeringVisa.setEvState(ChangeStateEnum.NO_HANDLE.getValue());//未处理
				engineeringVisaDao.update(engineeringVisa);
				
				operateRecordService.cerateCurOperateRecord(project, engineeringVisa.getAuditState(), WorkFlowTypeEnum.ASSIST_PROGRESS.getValue(), AssistProgressTypeEnum.ENGINEER_PROGRESS.getValue(),engineeringVisa.getEvId(),staff,grade,true);
				return Constants.OPERATE_RESULT_SUCCESS;
			}else{
				return "IsNotSignComplete";
			}

		}
		return Constants.OPERATE_RESULT_FAILURE;
	}
	/**
	 * 
	 * 注释：获取签证的下一状态
	 * @author liaoyq
	 * @createTime 2019年8月28日
	 * @param engineeringVisa
	 * @param project
	 * @return
	 *
	 */
	public String getEngineeringNextAuditState(
			EngineeringVisa engineeringVisa, Project project) {
		//待签证调整，和待推送一致
		if(StageProjectApplicationEnum.RE_PUSH.getValue().equals(engineeringVisa.getAuditState())){
			engineeringVisa.setAuditState(StageProjectApplicationEnum.TO_PUSH.getValue());
		}
		String status=workFlowService.queryAssistProgressStatusId(engineeringVisa.getCorpId(), project.getProjectType(), project.getContributionMode(), engineeringVisa.getAuditState(),
				true, WorkFlowTypeEnum.ASSIST_PROGRESS.getValue(), AssistProgressTypeEnum.ENGINEER_PROGRESS.getValue());
		if(WorkFlowActionEnum.CONTRACT_END.getActionCode().equals(status)){
			status=workFlowService.queryAssistProgressStatusId(engineeringVisa.getCorpId(), project.getProjectType(), null, engineeringVisa.getAuditState(),
					true, WorkFlowTypeEnum.ASSIST_PROGRESS.getValue(), AssistProgressTypeEnum.ENGINEER_PROGRESS.getValue());
		}
		if(WorkFlowActionEnum.CONTRACT_END.getActionCode().equals(status)){
			status=workFlowService.queryAssistProgressStatusId(engineeringVisa.getCorpId(),null, null, engineeringVisa.getAuditState(),
					true, WorkFlowTypeEnum.ASSIST_PROGRESS.getValue(), AssistProgressTypeEnum.ENGINEER_PROGRESS.getValue());
		}
		return status;
	}

	@Override
	public List<Object> findPrintDataByProjId(String projId,String type) {
		String result ="";
		List<Object> list = new ArrayList<Object>();
		//根据工程ID查询签证记录信息
		//多条签证
		List<EngineeringVisa> evs = engineeringVisaDao.findByProjId(projId,StageProjectApplicationEnum.PASSED.getValue());
		Project project = projectDao.get(projId);  //根据工程ID取得实体
		//签证报表
		String arrayStr = CompletionDataPrintEnum.VSIA_RECORD.getCptUrl();
		//2、使用JSONArray
		net.sf.json.JSONArray jsonArray=net.sf.json.JSONArray.fromObject(arrayStr);
		if(jsonArray!=null&&jsonArray.size()>0 && evs!=null && evs.size()>0 && project !=null){
			net.sf.json.JSONObject jsonObject=net.sf.json.JSONObject.fromObject(jsonArray.get(0));
			String[] menuIdAndMark = type.split("-"); // 分解施工任务单标识符和菜单ID,从而取得菜单ID
			String menuId = menuIdAndMark[menuIdAndMark.length-1]; // 获取菜单id
			String key = project.getProjectType()+"_"+project.getCorpId()+"_"+menuId;
			Object reportVersion = Constants.getSysConfigByKey(key);
			//遍历签证记录
			for(EngineeringVisa visa : evs){
				CompletionDataPrintDto dto = (CompletionDataPrintDto)net.sf.json.JSONObject.toBean(jsonObject, CompletionDataPrintDto.class);
				if(reportVersion !=null){
					//记录特定字符索引
					int beginIndex = dto.getReportlet().indexOf("/");
					int endIndex = dto.getReportlet().lastIndexOf("/");
					String reportlet = dto.getReportlet().substring(beginIndex, endIndex+1);  //截取字符'/'之间的字符串
					dto.setReportlet(reportlet+reportVersion.toString());   //若reportVersion不为空则重新设置报表路径
				}
				result = "{reportlet:'"+dto.getReportlet()+"',projName:'"+visa.getProjName()+"',projNo:'"+visa.getProjNo()+"',projId:'"+visa.getProjId()
						+"',evId:'"+visa.getEvId()+"',custName:'"+visa.getCustName()+"',suName:'"+visa.getSuName()+"',constructionUnit:'"+visa.getConstructionUnit()
						+"',drawUrl1:'" + Constants.DISK_PATH
						+"',imgUrl:'" + Constants.DISK_PATH+Constants.SIGN_DISK_PATH + "'}";
				list.add(result);
			}
		}
		return list;
	}


	/**
	 * 保存回调
	 * @author fuliwei
	 * @createTime 2018年1月21日
	 * @param
	 * @return
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void saveSignNotice(String cwId) {
		String signState = SignStateEnum.ALREADY_SIGN.getValue();
		if(StringUtils.isNotBlank(cwId)){
			EngineeringVisa engineeringVisa=engineeringVisaDao.get(cwId);

			if(StringUtils.isNotBlank(engineeringVisa.getSuPrincipal())
					&& StringUtils.isNotBlank(engineeringVisa.getBuilder())
					&& StringUtils.isNotBlank(engineeringVisa.getCmoPrincipal())
					&& StringUtils.isNotBlank(engineeringVisa.getSuJgj())){
				//所有通知置为已签字
				signNoticeService.updateAllSignState(SignDataTypeEnum.ENGINEERING.getValue(),engineeringVisa.getEvId());
			}

			//suPrincipal 项目经理
			if(StringUtils.isNotBlank(engineeringVisa.getSuPrincipal())){
				//通知置为无效
				signNoticeService.saveThisSignNotice(SignPostEnum.CU_PM.getValue(), SignDataTypeEnum.ENGINEERING.getValue(),
						engineeringVisa.getEvId(), engineeringVisa.getProjId(),signState);
			}
			//builder 施工员
			if(StringUtils.isNotBlank(engineeringVisa.getBuilder())){
				//通知置为无效
				signNoticeService.saveThisSignNotice(SignPostEnum.CONSTRUCTION.getValue(), SignDataTypeEnum.ENGINEERING.getValue(),
						engineeringVisa.getEvId(), engineeringVisa.getProjId(),signState);
			}
			//cmoPrincipal 甲代
			if(StringUtils.isNotBlank(engineeringVisa.getCmoPrincipal())){
				//通知置为无效
				signNoticeService.saveThisSignNotice(SignPostEnum.BUILDER.getValue(), SignDataTypeEnum.ENGINEERING.getValue(),
						engineeringVisa.getEvId(), engineeringVisa.getProjId(),signState);
			}
			//suJgj 现场监理
			if(StringUtils.isNotBlank(engineeringVisa.getSuJgj())){
				//通知置为无效
				signNoticeService.saveThisSignNotice(SignPostEnum.SUJGJ.getValue(), SignDataTypeEnum.ENGINEERING.getValue(),
						engineeringVisa.getEvId(), engineeringVisa.getProjId(),signState);
			}

		}

	}

	/**
	 * 签证审核时，将签证变更材料整合到材料清单表
	 * 1.查询该工程的此签证是否存在变更材料
	 * 2.存在变更材料，遍历变更材料，如果该材料已存在材料清单表中，则更新设计量，否则，增加材料到材料清单表
	 * @author liaoyq
	 * @createTime 2018-2-7
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public boolean updateMaterialList(EngineeringVisa env) {
		if(env!=null){
			//查询材料变更
			MaterialChangeReq req = new MaterialChangeReq();
			req.setCmId(env.getEvId());
			req.setProjId(env.getProjId());
			List<MaterialChange> materialChanges = materialChangeDao.queryMaterialChangeList(req);
			List<MaterialChange> mgLists = materialChangeDao.queryMaterialChangeList(req);
			List<MaterialList> newMLists = new ArrayList<MaterialList>();//新增材料
			List<MaterialList> oldMLists = new ArrayList<MaterialList>();//变更材料
			if(materialChanges!=null){
				for(MaterialChange mg : materialChanges){
					MaterialList materialList = new MaterialList();
					//根据材料名称和规格查询材料清单
					//去掉合同主材和变更材料
					MaterialListQueryReq materialListQueryReq = new MaterialListQueryReq();
					/*MaterialList materialList = materialListDao.queryByNameAndSpec(mg.getProjId(),mg.getMaterialName(),mg.getMaterialSpec(),mg.getFlag());
					if(materialList!=null){//已存在该材料
						if(materialList.getMaterialNum()!=null && mg.getAdjustQuantity()!=null){
							materialList.setMaterialNum(materialList.getMaterialNum().add(mg.getAdjustQuantity()));
							oldMLists.add(materialList);
						}
					}else{*/
					materialList = new MaterialList();
					materialList.setMaterialId(IDUtil.getUniqueId(Constants.MODULE_CODE_BUDGET));
					materialList.setProjId(env.getProjId());
					materialList.setProjName(env.getProjName());
					materialList.setProjNo(env.getProjNo());
					materialList.setMaterialName(mg.getMaterialName());
					materialList.setMaterialSpec(mg.getMaterialSpec());
					materialList.setMaterialUnit(mg.getMaterialUnit());
					materialList.setMaterialNum(mg.getAdjustQuantity());
					materialList.setRemark("");
					materialList.setCmId(mg.getCmId());
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
		}
		return false;
	}

	/**
	 * 签证作废
	 * @author fuliwei
	 * @createTime 2018年3月8日
	 * @param
	 * @return
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void updateEngineeringVisaState(Budget budget) throws Exception {
		if(budget!=null){
			EngineeringVisa ev=engineeringVisaDao.get(budget.getCmId());
			if(ev!=null){
				ev.setEvState(ChangeStateEnum.INVALID.getValue());//已作废
				ev.setAuditState(StageProjectApplicationEnum.TO_CANCEL.getValue());//已作废
				//将施工单位确认状态重置为未确定
				ev.setCuReState(CuReStateEnum.NOT_RE.getValue());
				engineeringVisaDao.saveOrUpdate(ev);

				ManageRecord manageRecord=new ManageRecord();
				manageRecord.setMrId(IDUtil.getUniqueId(Constants.MODULE_CODE_PROCESS));		//审核记录主键id
				manageRecord.setStepId(StepOutWorkflowEnum.ENGINEERING_AUDIT.getValue());
				manageRecord.setMrResult(MrResultEnum.NOT_PASSED.getValue());
				LoginInfo loginInfo = SessionUtil.getLoginInfo();
				manageRecord.setMrCsr(loginInfo.getStaffId()); 			//审核人
				manageRecord.setMrTime(engineeringVisaDao.getDatabaseDate());
				manageRecord.setMrAopinion(budget.getAuditOpinion());//审核意见
				manageRecord.setBusinessOrderId(ev.getEvId());
				manageRecord.setProjNo(ev.getProjNo());
				manageRecord.setFlag("0");//预算审核不通过-作废
				manageRecord.setProjId(ev.getProjId());
				manageRecordDao.saveOrUpdate(manageRecord);
				signNoticeService.deleteByBsId(budget.getCmId(),null);   //删除签字通知
				//将签证操作记录置为无效todo
				Staff staff = new Staff();
				Project project = projectDao.get(ev.getProjId());
				this.deleteOperateRecord(project.getProjId(),ev); // 签证作废时删除施工单位预算员上传预算书的工作通知
				operateRecordService.cerateCurOperateRecord(project,ev.getAuditState(), WorkFlowTypeEnum.ASSIST_PROGRESS.getValue(), AssistProgressTypeEnum.ENGINEER_PROGRESS.getValue(),ev.getEvId(),staff,null,false);

			}
		}
	}

	/**
	 * 未作废的未审核完成的签证
	 */
	@Override
	public List<EngineeringVisa> noCancelEngineeringVisa(String id) {
		return engineeringVisaDao.noCancelEngineeringVisa(id);
	}

	/**
	 * 已作废
	 * 施工单位签证审核一级时
	 * 根据签证crontabDate 与当前日期比较，如果大于等于配置的时效性，则自动生成签证一级审核通过的日志，并将crontabDate置空
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void engineeringVisaCrontab() {
		//1.获取配置的签证审核时效天数
		String auditLevel = budgetReplaceAuditLevel();//一级审核
		String key = Constants.ENGINEERING_VISA_CRONTAB+"_"+auditLevel;
		List<SysConfigBean> sysConfigs = sysConfigDao.findByKey(key);
		if(sysConfigs==null || sysConfigs.size()<1){
			return;
		}
		//得到配置表中包含了key的数据，根据数据得到相应公司下配置的时效，然后遍历相应的签证
		for(SysConfigBean sysConfigBean : sysConfigs ){
			if(StringUtil.isNotBlank(sysConfigBean.getCorpId()) && StringUtil.isNotBlank(sysConfigBean.getCnvalue())){
				this.autoEngineeringVisaAudit(sysConfigBean.getCorpId(),sysConfigBean.getCnvalue(),auditLevel);
			}
		}

	}
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void autoEngineeringVisaAudit(String corpId, String limitTime,String auditLevel) {
		//获取超期的签证
		List<Object[]> list = engineeringVisaDao.queryOverdueEngineeringVisa(corpId,limitTime);
		if(list!=null && list.size()>0){
			for (Object [] obj : list) {
				String evId = obj[0]!=null?obj[0].toString():"";
				String projId = obj[1]!=null?obj[1].toString():"";
				String projNo = obj[2]!=null?obj[2].toString():"";
				//1.得到超期的签证，系统自动生成一级审核的历史
				this.saveAutoManageRecord(evId,projId,projNo,auditLevel,StepOutWorkflowEnum.ENGINEERING_AUDIT.getValue());
				//2.超期的日志置空
				engineeringVisaDao.clearCrontabDateById(evId);
			}
		}
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void saveAutoManageRecord(String evId, String projId, String projNo,String auditLevel,String stepId) {
		ManageRecord manageRecord = new ManageRecord();
		manageRecord.setMrId(IDUtil.getUniqueId(Constants.MODULE_CODE_PROCESS));
		manageRecord.setMenuId(stepId);
		manageRecord.setBusinessOrderId(evId);
		manageRecord.setProjId(projId);
		manageRecord.setProjNo(projNo);
		manageRecord.setMrAuditLevel(StringUtil.isNotBlank(auditLevel)?auditLevel:"");//审核级别
		manageRecord.setMrResult(MrResultEnum.PASSED.getValue());//审核通过
		manageRecord.setMrAopinion("系统自动通过"+auditLevel+"级审核!");
		manageRecordDao.save(manageRecord);
	}





	/**
	 * @MethodDes: 超期审核按钮控制
	 * @author zhangnx
	 * @date 2019/8/8 13:37
	 */
	public void handlAuditLevelBtn(Map<String, String> levelBtn,String evId){
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		String auditLevel = budgetReplaceAuditLevel();

		OperateRecord records = getOperateRecord(evId, "1",OperateWorkFlowEnum.WAIT_DONE.getValue(), auditLevel);//待签证审核待办记录

		if (records!=null){
			if (!isAdmin(loginInfo)){//非管理员有待办才能进行审核
				if (StringUtil.isNotBlank(records.getOperateCsr1()) && records.getOperateCsr1().contains(loginInfo.getStaffId())){
					levelBtn.put("level" + auditLevel, "2");// 待审
				}else {
					levelBtn.put("level" + auditLevel, "-1");// 未审
				}
			}
		}else{//没有待办，则只有施工单位预算员可审核
			if (UnitTypeEnum.SUBCONTRACTING_UNIT.getValue().equals(loginInfo.getUnitType())){
				levelBtn.put("level" + auditLevel, "2");// 待审
			}else {
				levelBtn.put("level" + auditLevel, "-1");// 未审
			}
			
		}
	}





	/**
	 * @MethodDes: 签证审核超期处理
	 * @author zhangnx
	 * @date 2019/8/8 13:36
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public boolean engineeringVisaOverdueTreatment() {
		String auditLevel = budgetReplaceAuditLevel();
		List<EngineeringVisa> list=engineeringVisaDao.queryCrontabDateNotnull();
		if (list==null || list.size()<1) return true;

		for (EngineeringVisa ev:list) {

			long overDay = isOverdue(ev.getCrontabDate(), ev.getCorpId(), auditLevel);

			if (overDay<=0){//超期的记录
				OperateRecord or = getOperateRecord(ev.getEvId(), "5",OperateWorkFlowEnum.HAVE_DONE.getValue(), null);//签证待预算调整已办记录（得到预算员）
				OperateRecord records = getOperateRecord(ev.getEvId(), "1",OperateWorkFlowEnum.WAIT_DONE.getValue(), auditLevel);//待签证审核待办记录

				if (records!=null && or!=null){//将预算人追加到审核人中（通知两人）

					String auditOperateId = records.getOperateCsr1();

					if (StringUtil.isNotBlank(auditOperateId) && !auditOperateId.contains(or.getOperateCsr1())){
						String operateCsr1 = auditOperateId + or.getOperateCsr1();
						records.setOperateCsr1(operateCsr1.replace(",,",","));
						records.setOperater(records.getOperater()+","+or.getOperater());
						records.setStepName(records.getStepName()+"(<span style='color:red'>已超期</span>)");
					}
					operateRecordDao.saveOrUpdate(records);
					//不清空，审核了会自动清空的
					//engineeringVisaDao.clearCrontabDateById(ev.getEvId());//将2次确认时记录的日期置为null（此日期计算是否超期使用）
				}
			}
		}
		return false;
	}







	public OperateRecord getOperateRecord(String evId,String stepId,String modifyStatus,String auditLevel){
		List<OperateRecord> recordList = operateRecordDao.queryOperateRecordList(evId, stepId, modifyStatus, auditLevel);
		if (recordList!=null && recordList.size()>0){
			return recordList.get(0);
		}

		return null;
	}







	/**
	 * @MethodDes: 是否超期判断
	 * 剩余时限大于0，则未超期，否则超期
	 * @author zhangnx
	 * @date 2019/8/8 13:39
	 */
	public long isOverdue(Date oldTime,String corpId,String auditLevel){
		SysConfigBean sys = sysConfigDao.get(Constants.ENGINEERING_VISA_CRONTAB+"_"+auditLevel +"_"+ corpId);
		if (sys==null){
			sys = sysConfigDao.get(Constants.ENGINEERING_VISA_CRONTAB+"_"+auditLevel +"_"+ Constants.CORP_ID);
		}
		if(sys==null){
			return 999;
		}
		long limitDays=(sys!=null && StringUtil.isNotBlank(sys.getCnvalue()))?Integer.parseInt(sys.getCnvalue()):3;
		Date nowDate = engineeringVisaDao.getDatabaseDate();

		//获取两个日期之间的工作日天数
		long  workDays = FestivalUtil.calLeaveDays(FestivalUtil.getChangeDate(oldTime), nowDate, festivalService.getCacheMap(Constants.WORKDAYS), festivalService.getCacheMap(Constants.HOLIDAYS));
		
		return limitDays - workDays;
	}



	public boolean isAdmin(LoginInfo loginInfo){
		if (StringUtil.isNotBlank(loginInfo.getPost()) && loginInfo.getPost().contains(PostTypeEnum.ADMIN.getValue())){ //管理员
			return true;
		}
		return false;
	}


	/**
	 * @MethodDes: 需要代替审核的级别
	 * @author zhangnx
	 * @date 2019/8/8 13:40
	 */
	public String budgetReplaceAuditLevel(){
		return "1";
		
	}
	
	@Override
	public Map<String, Object> queryVisaQuantyAudit(
			EngineeringVisaQueryReq engineeringVisaQueryReq) throws ParseException {
		List<String> auditState = new ArrayList<String>();
		auditState.add(StageProjectApplicationEnum.QUANTY_AUDIT.getValue());
		engineeringVisaQueryReq.setAuditState(auditState);
		Map<String, Object> result = engineeringVisaDao.queryEngineeringVisa(engineeringVisaQueryReq);

		List<EngineeringVisa> data = (List<EngineeringVisa>) result.get("data");
		if (data != null && data.size() > 0) {
			String grade ="0";
			/**
			 * -1 未审核 0 审核未通过 1 审核通过 2待审核 若该
			 * 审核为三级审核，且一级审核通过、二级审核未通过，三级审核待审核，则键值对
			 * ：{"level1":"1","level2":"0","level3":"2"};
			 * */
			// 遍历循环 设置审核级别
			for (int i = 0; i < data.size(); i++) {
				Project pro=projectDao.get(data.get(i).getProjId());
				//获取审核级别
				String grade1 = grade;
				if(pro!=null){
					data.get(i).setProjectType(pro.getProjectType());
				}
				DocType docType = docTypeDao.findByStepId(StepOutWorkflowEnum.ENGINEERING_QUANTY_AUDIT.getValue(), pro.getCorpId(), pro.getProjectType(), pro.getContributionMode());
				if(docType == null){
					docType = docTypeDao.findByStepId(StepOutWorkflowEnum.ENGINEERING_QUANTY_AUDIT.getValue(), pro.getCorpId(), pro.getProjectType(), null);
				}
				if(docType == null){
					docType = docTypeDao.findByStepId(StepOutWorkflowEnum.ENGINEERING_QUANTY_AUDIT.getValue(), pro.getCorpId(), null, null);
				}
				if(docType != null && StringUtil.isNotBlank(docType.getGrade())){
					grade1 = docType.getGrade();
				}
				data.get(i).setLevel(grade1); // 设置审核总级数
				
				Map<String, String> levelBtn = new HashMap<String, String>();
				// 以下循环，遍历出默认应该几级审核，审核状态分别为什么，例如三级审核，则一级待审、二级未审、三级未审{"level1":"2","level2":"-1","level3":"-1"}；
				for (int n = 1; StringUtil.isNotBlank(grade1) && n < Integer.parseInt(grade1) + 1; n++) {
					if (n == 1) {
						levelBtn.put("level" + n, "2");// 待审
					} else {
						levelBtn.put("level" + n, "-1");// 未审
					}
				}
				ManageRecordQueryReq mrq = new ManageRecordQueryReq();
				mrq.setBusinessOrderId(data.get(i).getEvId());
				mrq.setStepId(StepOutWorkflowEnum.ENGINEERING_QUANTY_AUDIT.getValue());
				mrq.setProjId(data.get(i).getProjId());
				List<ManageRecord> mrls = manageRecordDao
						.findByStepIdBusinessOrderId(data.get(i).getEvId(),
								StepOutWorkflowEnum.ENGINEERING_QUANTY_AUDIT
										.getValue(), MrResultEnum.PASSED
										.getValue());
				if (mrls != null && mrls.size() > 0) {
					// 遍历循环
					for (ManageRecord mr : mrls) {
						levelBtn.put("level" + mr.getMrAuditLevel(),
								mr.getMrResult());
					}
					if (mrls.size() < Integer.parseInt(grade1)) {
						levelBtn.put("level" + (mrls.size() + 1), "2");
					}
				}
				data.get(i).setMrAuditLevel(
						JSONSerializer.toJSON(levelBtn).toString());
				data.get(i).setEvTypeDesc(EngineeringTypeEnum.valueof(data.get(i).getEvType()).getMessage());
			}
			result.put("data", data);
		}
		return result;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public boolean updateEngineeringVisa(String evId,String auditState) {
		EngineeringVisa engineeringVisa = engineeringVisaDao.get(evId);
		if (engineeringVisa==null) return false;

		try {
			engineeringVisa.setAuditState(auditState);
			List<String> auditStepIds=new ArrayList<>();
			List<String> operateStepIds=new ArrayList<>();
			engineeringVisaDao.saveOrUpdate(engineeringVisa);

			if (StageProjectApplicationEnum.RE_PUSH.getValue().equals(auditState)){//待调整签证
				auditStepIds.add("1701-2");//待签证量审核
				auditStepIds.add("1701-1");//待签证审核
				operateStepIds.add(StageProjectApplicationEnum.TO_AUDIT.getValue());
				operateStepIds.add(StageProjectApplicationEnum.TO_BUDGET.getValue());
				operateStepIds.add(StageProjectApplicationEnum.QUANTY_AUDIT.getValue());
			}else if (StageProjectApplicationEnum.TO_BUDGET.getValue().equals(auditState)) {//待预算调整
				auditStepIds.add("1701-1");//待签证审核
				operateStepIds.add(StageProjectApplicationEnum.TO_AUDIT.getValue());
			}
			manageRecordDao.auditRecordsetValid(evId,null,auditStepIds);
			operateRecordDao.backSetToDoInValid(evId,null,operateStepIds);
			operateRecordDao.backStepSetNeedDealtWith(evId,null,auditState );
			return true;
		}catch (ExpressException e){
			e.printStackTrace();
		}
		return false;
	}


}
