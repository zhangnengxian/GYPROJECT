package cc.dfsoft.project.biz.base.complete.service.impl;

import cc.dfsoft.project.biz.base.baseinfo.service.WorkFlowService;
import cc.dfsoft.project.biz.base.complete.dao.PreinspectionDao;
import cc.dfsoft.project.biz.base.complete.dao.SelfInspectionListDao;
import cc.dfsoft.project.biz.base.complete.dao.SelfInspectionRecordDao;
import cc.dfsoft.project.biz.base.complete.dto.PreinspectionReq;
import cc.dfsoft.project.biz.base.complete.entity.Preinspection;
import cc.dfsoft.project.biz.base.complete.entity.SelfInspectionList;
import cc.dfsoft.project.biz.base.complete.entity.SelfInspectionRecord;
import cc.dfsoft.project.biz.base.complete.enums.CompletionDataPrintEnum;
import cc.dfsoft.project.biz.base.complete.service.SelfInspectionListService;
import cc.dfsoft.project.biz.base.complete.service.SelfInspectionRecordService;
import cc.dfsoft.project.biz.base.constructmanage.dto.CompletionDataPrintDto;
import cc.dfsoft.project.biz.base.contract.enums.ContractIsPrintEnum;
import cc.dfsoft.project.biz.base.design.service.impl.SurveyInfoServiceImpl;
import cc.dfsoft.project.biz.base.maintain.service.AbandonedRecordService;
import cc.dfsoft.project.biz.base.messagesync.pojo.ResultMsg;
import cc.dfsoft.project.biz.base.messagesync.service.SynchronizedService;
import cc.dfsoft.project.biz.base.plan.dao.ConstructionPlanDao;
import cc.dfsoft.project.biz.base.plan.entity.ConstructionPlan;
import cc.dfsoft.project.biz.base.project.dao.ProjectDao;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.project.enums.StepEnum;
import cc.dfsoft.project.biz.base.project.service.OperateRecordService;
import cc.dfsoft.project.biz.base.project.service.SignatureService;
import cc.dfsoft.project.biz.ifs.WebServiceTypeEnum;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.enums.WorkFlowActionEnum;
import cc.dfsoft.uexpress.common.exception.ExpressException;
import cc.dfsoft.uexpress.common.util.Annotations;
import cc.dfsoft.uexpress.common.util.IDUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class SelfInspectionListServiceImpl implements SelfInspectionListService {

	/** 日志实例 */
	private static Logger logger = LoggerFactory.getLogger(SurveyInfoServiceImpl.class);

	/** 自检单Dao */
	@Resource
	SelfInspectionListDao selfInspectionListDao;

	/** 自检记录Service */
	@Resource
	SelfInspectionRecordService selfInspectionRecordService;

	@Resource
	SelfInspectionRecordDao selfInspectionRecordDao;

	/** 工程Dao */
	@Resource
	ProjectDao projectDao;

	/** 施工计划Dao */
	@Resource
	ConstructionPlanDao constructionPlanDao;

	/** 业务操作记录服务接口 */
	@Resource
	OperateRecordService operateRecordService;

	/** 签字 */
	@Resource
	SignatureService signatureService;

	/** 工作流服务接口 */
	@Resource
	WorkFlowService workFlowService;

	@Resource
	PreinspectionDao preinspectionDao;
	@Resource
	AbandonedRecordService abandonedRecordService;
	@Resource
	SynchronizedService synchronizedService;

	/**
	 * 根据工程ID查自检单
	 * 
	 * @param projId
	 * @return
	 * @throws ParseException
	 */
	@Override
	public SelfInspectionList viewSelfInspectionList(String projId) throws ParseException {
		SelfInspectionList selfInspectionList = new SelfInspectionList();
		List<SelfInspectionList> sils = selfInspectionListDao.findByprojectId(projId);
		Project proj = projectDao.get(projId);

		if (sils != null && sils.size() > 0) {
			selfInspectionList = sils.get(0);
			selfInspectionList.setProjAddr(proj.getProjAddr()); // 工程地点
			selfInspectionList.setProjScaleDes(proj.getProjScaleDes()); // 工程规模
			selfInspectionList.setProjectTypeDes(proj.getProjectTypeDes());
			selfInspectionList.setContributionModeDes(proj.getContributionModeDes());
			selfInspectionList.setCorpName(proj.getCorpName());
			selfInspectionList.setDeptName(proj.getDeptName());
		} else {

			List<ConstructionPlan> constructionPlans = constructionPlanDao.findByProjNo(proj.getProjNo());
			if (constructionPlans != null && constructionPlans.size() > 0) {
				ConstructionPlan constructionPlan = constructionPlans.get(0);
				selfInspectionList.setCmoName(constructionPlan.getCuName());// 分包单位即页面的施工单位
				selfInspectionList.setSuName(constructionPlan.getSuName()); // 监理单位
			}
			selfInspectionList.setProjId(projId);
			selfInspectionList.setProjName(proj.getProjName());
			selfInspectionList.setProjNo(proj.getProjNo());
			selfInspectionList.setProjAddr(proj.getProjAddr()); // 工程地点
			selfInspectionList.setProjScaleDes(proj.getProjScaleDes()); // 工程规模
			selfInspectionList.setProjectTypeDes(proj.getProjectTypeDes());
			selfInspectionList.setContributionModeDes(proj.getContributionModeDes());
			selfInspectionList.setCorpName(proj.getCorpName());
			selfInspectionList.setDeptName(proj.getDeptName());
		}
		// 查询最后删除标记的预验收记录，是否存在回退
		List<Preinspection> preinspection = preinspectionDao.findIsDelByProjId(proj.getProjId());
		if (preinspection != null && preinspection.size() > 0) {
			if (StringUtils.isNotBlank(preinspection.get(0).getIsBack())) {
				selfInspectionList.setIsBack(preinspection.get(0).getIsBack());
			}
			if (StringUtils.isNotBlank(preinspection.get(0).getBackRemark())) {
				selfInspectionList.setBackRemark(preinspection.get(0).getBackRemark());
			}
		}
		return selfInspectionList;
	}

	/**
	 * 保存自检单
	 * 
	 * @author fuliwei
	 * @createTime 2017年7月31日
	 * @param
	 * @return
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void saveSelfInspectionList(SelfInspectionList selfInspectionList) throws Exception {
		boolean flag = false;

		// 新建自检资料
		if (StringUtils.isBlank(selfInspectionList.getSilId())) {
			flag = true;
			selfInspectionList.setSilId(IDUtil.getUniqueId(Constants.MODULE_CODE_COMPLETE));
			selfInspectionList.setIsDel("1");
		}
		// 添加推送时间
		if (selfInspectionList.getFlag().equals("1")) {
			selfInspectionList.setPushDate(selfInspectionListDao.getDatabaseDate());

		}
		selfInspectionList.setIsPrint(ContractIsPrintEnum.HAVE_NOT_PRINT.getValue());// 未打印
		selfInspectionList.setSilDate(selfInspectionListDao.getDatabaseDate());
		try {
			selfInspectionListDao.saveOrUpdate(selfInspectionList);
		}catch (Exception e){
			e.printStackTrace();
		}

		// 保存签字
		signatureService.saveOrUpdateSign("menuId+menuNane+8",selfInspectionList.getSign(), selfInspectionList.getProjId(),
				selfInspectionList.getSilId(), selfInspectionList.getClass().getName(), flag);

		List<SelfInspectionRecord> selfInspectionRecords = selfInspectionList.getSelfInspectionRecords();

		if (selfInspectionRecords != null && selfInspectionRecords.size() > 0) {
			for (SelfInspectionRecord sir : selfInspectionRecords) {
				sir.setSilId(selfInspectionList.getSilId());
				// if(StringUtils.isBlank(sir.getSirId())){
				sir.setSirId(IDUtil.getUniqueId(Constants.MODULE_CODE_COMPLETE));
				// }
				sir.setProjId(selfInspectionList.getProjId());
			}
		}
		if (flag) {
			// 新建
			selfInspectionRecordDao.batchInsertObjects(selfInspectionRecords);
		} else {
			// 删除原来的
			selfInspectionRecordDao.deleteObjects(selfInspectionList.getSilId());
			selfInspectionRecordDao.batchInsertObjects(selfInspectionRecords);
		}

		/**
		 * 更新工程信息
		 */
		Project pro = projectDao.get(selfInspectionList.getProjId());
		if (selfInspectionList.getFlag().equals("1")) {
			// 推送
			// String
			// statusId=workFlowService.queryProjStatusId(pro.getCorpId(),pro.getContributionMode(),WorkFlowActionEnum.SELF_CHECK.getActionCode(),
			// true);
			String statusId = workFlowService.queryProjStatusId(pro.getCorpId(), pro.getProjectType(),
					pro.getContributionMode(), WorkFlowActionEnum.SELF_CHECK.getActionCode(), true);
			pro.setCompletedDate(selfInspectionListDao.getDatabaseDate());//工程竣工日期
			pro.setProjStatusId(statusId);
		}
		String todoer="";

		/**
		 * 保存业务操作记录
		 */
		if (selfInspectionList.getFlag().equals("1")) {
			String orId = IDUtil.getUniqueId(Constants.MODULE_CODE_COMPLETE);
			todoer=operateRecordService.createOperateRecord(orId, pro.getProjId(), pro.getProjNo(),
					StepEnum.SELF_CHECK.getValue(), StepEnum.SELF_CHECK.getMessage(), "");
		}
		pro.setToDoer(todoer);	//待办人
		projectDao.update(pro);// 更新工程

		//推送时调用调用鸿巨接口（自检信息新增/修改）返回信息
		if (selfInspectionList.getFlag().equals("1")) {
			ResultMsg resultMsg = synchronizedService.callSynchronizedSelfCheck(selfInspectionList.getProjId(), WebServiceTypeEnum.SELF_CHECK.getValue());
			if (resultMsg!=null && resultMsg.getCode()!=0){
				throw new ExpressException(resultMsg.getCode()+"",resultMsg.getMsg());
			}
		}
	}

	/**
	 * 自检单列表查询
	 * 
	 * @author fuliwei
	 * @createTime 2017年8月4日
	 * @param
	 * @return
	 */
	@Override
	public Map<String, Object> querySelInspection(PreinspectionReq req) throws ParseException {

		Map<String, Object> map = selfInspectionListDao.querySelInspection(req);

		List<SelfInspectionList> list = (List<SelfInspectionList>) map.get("data");

		if (list != null && list.size() > 0) {
			for (SelfInspectionList selfInspectionList : list) {

				Project project = projectDao.get(selfInspectionList.getProjId());
				if (project != null) {
					selfInspectionList.setProjectType(project.getProjectType());
				}

			}
		}

		return map;
	}

	/**
	 * 自检单打印标记
	 * 
	 * @author fuliwei
	 * @createTime 2017年8月4日
	 * @param
	 * @return
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void signSelInspectionPrint(String projId) {
		List<SelfInspectionList> selfList = selfInspectionListDao.findByprojectId(projId);
		if (selfList != null && selfList.size() > 0) {
			SelfInspectionList self = selfList.get(0);
			self.setIsPrint(ContractIsPrintEnum.ALREADY_PRINT.getValue());
			selfInspectionListDao.update(self);
		}

	}

	@Override
	public String findPrintDataByProjId(String projId,String type) {
		String result = "";
		// 根据工程ID查询一站式验收单
		List<SelfInspectionList> selfList = selfInspectionListDao.findByprojectId(projId);
		Project project = projectDao.get(projId);  //根据工程ID取得实体
		SelfInspectionList selfIn = null;
		if (selfList != null && selfList.size() > 0) {
			selfIn = new SelfInspectionList();
			selfIn = selfList.get(0);
		}
		String arrayStr = CompletionDataPrintEnum.SELF_CHECK.getCptUrl();
		// 2、使用JSONArray
		net.sf.json.JSONArray jsonArray = net.sf.json.JSONArray.fromObject(arrayStr);
		if (jsonArray != null && jsonArray.size() > 0 && selfIn != null && project !=null) {
			net.sf.json.JSONObject jsonObject = net.sf.json.JSONObject.fromObject(jsonArray.get(0));
			CompletionDataPrintDto dto = (CompletionDataPrintDto) net.sf.json.JSONObject.toBean(jsonObject,
					CompletionDataPrintDto.class);
			 String[] menuIdAndMark = type.split("-"); // 分解施工任务单标识符和菜单ID,从而取得菜单ID
			 String menuId = menuIdAndMark[menuIdAndMark.length-1]; // 获取菜单id
			 String key = project.getProjectType()+"_"+project.getCorpId()+"_"+menuId;
			 Object reportVersion = Constants.getSysConfigByKey(key);
			   if(reportVersion !=null){
				   //记录特定字符索引  
				   int beginIndex = dto.getReportlet().indexOf("/"); 
				   int endIndex = dto.getReportlet().lastIndexOf("/");
			       String reportlet = dto.getReportlet().substring(beginIndex, endIndex+1);  //截取字符'/'之间的字符串
				   dto.setReportlet(reportlet+reportVersion.toString());   //若reportVersion不为空则重新设置报表路径
			   }
			result = "{reportlet:'" + dto.getReportlet() + "',silId:'" + selfIn.getSilId() + "',projId:'"
					+ selfIn.getProjId() + "',path:'" + Constants.DISK_PATH + Constants.SIGN_DISK_PATH + "'}";
			return result;
		}
		return null;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public boolean modifySelfCheck(SelfInspectionList selfCheck) {
		List<SelfInspectionList> selfList = selfInspectionListDao.findByprojectId(selfCheck.getProjId());
		if (selfList!=null && selfList.size()>0){
			if (selfList.get(0)!=null){
				selfList.get(0).setProjManagerOpinion(selfCheck.getProjManagerOpinion());
				selfList.get(0).setSilDate(selfCheck.getSilDate());
				selfList.get(0).setPushDate(selfCheck.getSilDate());
				selfInspectionListDao.saveOrUpdate(selfList.get(0));
			}
		}
		return true;
	}

	@Override
	public boolean rollBackContainsSelfInspectionList(String projId, String fallbackReason) {
		List<SelfInspectionList> siList = selfInspectionListDao.findByprojectId(projId);
		if (siList==null || siList.size()<1) return true;

		String stepId=StepEnum.SELF_CHECK.getValue();

		for (SelfInspectionList self:siList) {
			if ("1".equals(self.getIsDel())){
				//更新自检信息
				self.setIsDel("0");//作废
				selfInspectionListDao.saveOrUpdate(self);

				abandonedRecordService.delBackupsThisTableSignatureAndNotice(self.getSilId(),projId,stepId, fallbackReason);//删除签字和签字通知
			}
		}


		return false;
	}

}
