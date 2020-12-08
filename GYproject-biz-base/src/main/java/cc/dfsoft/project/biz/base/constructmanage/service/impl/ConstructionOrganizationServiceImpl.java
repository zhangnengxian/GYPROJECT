package cc.dfsoft.project.biz.base.constructmanage.service.impl;

import cc.dfsoft.project.biz.base.complete.enums.CompletionDataPrintEnum;
import cc.dfsoft.project.biz.base.constructmanage.dao.ConstructionOrganizationDao;
import cc.dfsoft.project.biz.base.constructmanage.dao.ConstructionWorkDao;
import cc.dfsoft.project.biz.base.constructmanage.dao.ShutdownRecordDao;
import cc.dfsoft.project.biz.base.constructmanage.dto.CompletionDataPrintDto;
import cc.dfsoft.project.biz.base.constructmanage.dto.ConstructionWorkReq;
import cc.dfsoft.project.biz.base.constructmanage.dto.UploadResult;
import cc.dfsoft.project.biz.base.constructmanage.entity.ConstructionOrganization;
import cc.dfsoft.project.biz.base.constructmanage.entity.ConstructionWork;
import cc.dfsoft.project.biz.base.constructmanage.entity.WorkReport;
import cc.dfsoft.project.biz.base.constructmanage.enums.FinishStateEnum;
import cc.dfsoft.project.biz.base.constructmanage.service.ConstructionOrganizationService;
import cc.dfsoft.project.biz.base.constructmanage.service.ConstructionWorkService;
import cc.dfsoft.project.biz.base.constructmanage.service.WorkReportService;
import cc.dfsoft.project.biz.base.maintain.service.AbandonedRecordService;
import cc.dfsoft.project.biz.base.plan.dao.ConstructionPlanDao;
import cc.dfsoft.project.biz.base.plan.entity.ConstructionPlan;
import cc.dfsoft.project.biz.base.project.dao.AccessoryDao;
import cc.dfsoft.project.biz.base.project.dao.NoticeDao;
import cc.dfsoft.project.biz.base.project.dao.ProjectDao;
import cc.dfsoft.project.biz.base.project.dao.SignNoticeDao;
import cc.dfsoft.project.biz.base.project.entity.AccessoryList;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.project.entity.Signature;
import cc.dfsoft.project.biz.base.project.enums.*;
import cc.dfsoft.project.biz.base.project.service.SignNoticeService;
import cc.dfsoft.project.biz.base.project.service.SignatureService;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.util.Annotations;
import cc.dfsoft.uexpress.common.util.FileUtil;
import cc.dfsoft.uexpress.common.util.IDUtil;
import cc.dfsoft.uexpress.common.util.StringUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
@Transactional(readOnly = true,propagation=Propagation.REQUIRED)
public class ConstructionOrganizationServiceImpl implements ConstructionOrganizationService {

	@Resource
	ConstructionOrganizationDao constructionOrganizationDao;
	
	@Resource
	ProjectDao projectDao;

	@Resource
	AccessoryDao accessoryDao; //附件表
	
	@Resource
	ConstructionPlanDao constructionPlanDao;
	
	@Resource
	SignNoticeDao signNoticeDao;
	
	@Resource
	SignatureService signatureService;
	
	@Resource
	WorkReportService workReportService;
	
	@Resource
	ConstructionWorkService constructionWorkService;
	
	/**会审交底*/
	@Resource
	ConstructionWorkDao constructionWorkDao;
	
	/**签字通知服务*/
	@Resource
	SignNoticeService signNoticeService;
	@Resource
	NoticeDao noticeDao;
	@Resource
	ShutdownRecordDao shutdownRecordDao;

	@Resource
	AbandonedRecordService abandonedRecordService;


	@Override
	public Project isAllowWorkStart(Project project) {
		if(project !=null && StringUtil.isNotBlank(project.getProjId())){
			//工程进场辅助状态
			if(project.getProjStatusId().equals(ProjStatusEnum.TO_DETERMINE_AMOUNT.getValue())
					||project.getProjStatusId().equals(ProjStatusEnum.TO_AUDIT_AMOUNT_FIRST.getValue())
					||project.getProjStatusId().equals(ProjStatusEnum.TO_DETERMINE_DISPATCH.getValue())
					||project.getProjStatusId().equals(ProjStatusEnum.TO_SIGNED_SUBCONTRACT.getValue())
					||project.getProjStatusId().equals(ProjStatusEnum.TO_AUDIT_SUBCONTRACT.getValue())//待审施工合同
					||project.getProjStatusId().equals(ProjStatusEnum.TO_CONSTRUCTION.getValue())
					||project.getProjStatusId().equals(ProjStatusEnum.TO_AUDIT_AMOUNT.getValue())){
				Integer cwCount = constructionWorkService.countSignedByProjId(project.getProjId(),FinishStateEnum.ALREADY_FINISHED.getValue());
				//还没有已完成签字的会审交底
				if((cwCount==null || cwCount<=0)){
					project.setProjConStatus(Constants.CONSTRUCTION_WORK);
					project.setProjConStatusDes("待会审交底");
					return project;
				}
				Integer coCount = constructionOrganizationDao.countSignedByProjId(project.getProjId(),FinishStateEnum.ALREADY_FINISHED.getValue());
				//工程未完成施工组织
				if(null ==coCount || coCount<=0){
					project.setProjConStatus(Constants.CONSTRUCTION_ORGANIZATION);
					project.setProjConStatusDes("待编制施工组织");
					return project;
				}
				WorkReport workReport = workReportService.findByProjId(project.getProjId());
				//开工报告未完成
				if(workReport==null || !FinishStateEnum.ALREADY_FINISHED.getValue().equals(workReport.getSignState())){
					project.setProjConStatus(Constants.WORK_REPORT);
					project.setProjConStatusDes("待编制开工报告");
					return project;
				}else {
					//并行流程到施工状态
					//存在未复工的停工令
					Integer srCount = shutdownRecordDao.findSRByProjdId(project.getProjId());
					if(srCount!=null && srCount>0){
						project.setProjConStatus(Constants.SHUTDONN_RECORD);
						project.setProjConStatusDes("停工中");
						return project;
					}
					project.setProjConStatus(Constants.COMPLETE_WORK_REPORT);
					project.setProjConStatusDes("施工中");
					return project;
				}
			}else if(project.getProjStatusId().equals(ProjStatusEnum.DURING_CONSTRUCTION.getValue())){
				//主流程到施工中，是否存在停工令
				//存在未复工的停工令
				Integer srCount = shutdownRecordDao.findSRByProjdId(project.getProjId());
				if(srCount!=null && srCount>0){
					project.setProjConStatus(Constants.SHUTDONN_RECORD);
					project.setProjConStatusDes("停工中");
					return project;
				}
			}
		}
		return project;
	}

	@Override
	public ConstructionOrganization constructionOrganizationDetail(String projId) throws Exception {
		Project project = projectDao.get(projId);
		ConstructionOrganization constructionOrganization1 = constructionOrganizationDao.get("projId" , projId);
		ConstructionWorkReq req=new ConstructionWorkReq();
		req.setProjId(projId);
		req.setSignState(FinishStateEnum.ALREADY_FINISHED.getValue());
		Map<String,Object> map=constructionWorkDao.queryList(req);
		List<ConstructionWork> list=(List<ConstructionWork>) map.get("data");
		
		if(null != constructionOrganization1){
			if(list!=null && list.size()>0){
				//已完成交底 可进行施工组织
				constructionOrganization1.setCwSignState(FinishStateEnum.ALREADY_FINISHED.getValue());
			}else{
				constructionOrganization1.setCwSignState(FinishStateEnum.NOT_FINISH.getValue());
			}
			
			//查询附件
			List<AccessoryList> accList= accessoryDao.queryAccessoryByBus(constructionOrganization1.getCoId(),null);
			if(accList!=null && accList.size()>0){
				AccessoryList ac=accList.get(0);
				constructionOrganization1.setAccListId(ac.getAlId());
			}
			
			if(constructionOrganization1.getEditDate()==null){
				constructionOrganization1.setEditDate(constructionOrganizationDao.getDatabaseDate());
			}
			accList = null;
			return constructionOrganization1;
		}else{
			List<ConstructionPlan> constructionPlans = constructionPlanDao.findByProjNo(project.getProjNo());
			ConstructionOrganization constructionOrganization = new ConstructionOrganization();
			if(constructionPlans!=null && constructionPlans.size()!=0){
				ConstructionPlan constructionPlan = constructionPlans.get(0);
				constructionOrganization.setSuName(constructionPlan.getSuName());//监理单位
				constructionOrganization.setCuName(constructionPlan.getCuName());//施工单位
			}
			if(list!=null && list.size()>0){
				//已完成交底 可进行施工组织
				constructionOrganization.setCwSignState(FinishStateEnum.ALREADY_FINISHED.getValue());
			}else{
				constructionOrganization.setCwSignState(FinishStateEnum.NOT_FINISH.getValue());
			}
			constructionOrganization.setProjId(project.getProjId());
			constructionOrganization.setProjNo(project.getProjNo());
			constructionOrganization.setProjAddr(project.getProjAddr());
			constructionOrganization.setProjName(project.getProjName());
			constructionOrganization.setCustName(project.getCorpName());//建设单位-燃气集团
			LoginInfo loginInfo = SessionUtil.getLoginInfo();
			constructionOrganization.setApplicateDept(loginInfo!=null?loginInfo.getDeptName():"");
			if(constructionOrganization.getEditDate()==null){
				constructionOrganization.setEditDate(constructionOrganizationDao.getDatabaseDate());
			}
			project = null;
			constructionOrganization1 = null;
			req = null;
			map = null;
			list = null;
			constructionPlans = null; // 手动置为空，加快gc回收时间，减少占用内存
			return constructionOrganization;
		}
	}

	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public String constructionOrganizationSave(ConstructionOrganization constructionOrganization) throws Exception {

		boolean flag = false;
		if(StringUtils.isBlank(constructionOrganization.getCoId())){
			flag = true;
			constructionOrganization.setCoId(IDUtil.getUniqueId(Constants.MODULE_CODE_PROJECTSTART));//生产施工组织Id
			List<ConstructionOrganization> isNotNullCsOr = constructionOrganizationDao.findbyProjId(constructionOrganization.getProjId());
			if(isNotNullCsOr !=null && isNotNullCsOr.size() > 0){
				return "already";   //施工组织只能有一条
			}
		}
		constructionOrganizationDao.saveOrUpdate(constructionOrganization);//保存施工组织
	
		List<Signature> signs=constructionOrganization.getSign();
		if(signs!=null && signs.size()>0){
			for(Signature sign:signs){
				sign.setDataType(SignDataTypeEnum.CONSTRUCTION_ORGANIZATION.getValue());
			}
			constructionOrganization.setSign(signs);
		}
		
		signatureService.saveOrUpdateSign("menuId+menuNane+10",constructionOrganization.getSign(), constructionOrganization.getProjId(), constructionOrganization.getCoId(), constructionOrganization.getClass().getName(), flag);
		return constructionOrganization.getCoId();
	}

	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public String saveConstructionOrganization(HttpServletRequest request, UploadResult ur, MultipartFile[] files) throws Exception {
		JSONObject jo = new JSONObject();
		ConstructionOrganization co = JSON.parseObject(ur.getResult(), ConstructionOrganization.class);
		JSONArray sign = (JSONArray) jo.parseObject(ur.getResult()).get("sign");
		//判断签字信息，保存签字
		if(sign!=null){
			List<Signature> signs = JSONObject.parseArray(sign.toJSONString(), Signature.class);
			co.setSignature(signs);
		}
		String name=co.getDrawName();//施工组织附件名称
		if(files!=null){
			co.setDrawName(files[0].getOriginalFilename());//附件原始名称
		}
		//保存到施工组织表
		String coId=this.constructionOrganizationSave(co);
        if(StringUtils.isNotBlank(coId) && "already".equals(coId)){
        	return "already";  //施工记录只能有一条记录
        }

		if(StringUtil.isBlank(name)){
			List<AccessoryList> accs =accessoryDao.queryAccessoryByBus(co.getCoId(), "");
			if(accs!=null&&accs.size()>0){
				if(files!=null){
					FileUtil.deleteFile(Constants.DISK_PATH+accs.get(0).getAlPath());//删除以前的附件
				}
				accessoryDao.delete(accs.get(0));//删除附件
			}
			if(files!=null){
				AccessoryList acc = new AccessoryList();
				String path=FileUtil.uploadFile(request, ur.getAlPath(), files);//路径
				String fileName = files[0].getOriginalFilename();               //文件名全名（例：文件名.png）
				//String filePath= Constants.FIRST_DISK_PATH + path + fileName;
				String filePath= Constants.FIRST_DISK_PATH + path ;
				String name1 = fileName.substring(0,fileName.lastIndexOf("."));//文件去掉格式后的名（从第0位截取，到文件格式(例：“.png“）前的点结束）
				String fileType=fileName.substring(fileName.lastIndexOf(".")+1,fileName.length());//文件格式（例：“.png”）
				LoginInfo loginInfo = SessionUtil.getLoginInfo();
				acc.setAlId(IDUtil.getUniqueId(Constants.FILE_CODE_ACCESS));   //生成附件Id
				acc.setProjId(co.getProjId());                                 //项目Id
				acc.setProjNo(co.getProjNo());                                 //项目编号
//				acc.setProjLtypeId(co.getProjLtypeId());                       //工程大类
				acc.setAlTypeId(fileType);                                     //附件格式（例：“.png"）
				acc.setStepId(ur.getStepId());                                 //步骤Id
				acc.setStep(ur.getStep());
				acc.setAlName(name1);                                          //附件名称
				acc.setAlPath(filePath);                                       //附件路径
				acc.setAlOperateCsrId(loginInfo.getStaffId());                 //操作人Id
				acc.setAlOperateCsr(loginInfo.getStaffName());                 //操作人姓名
				acc.setAlOperateTime(accessoryDao.getDatabaseDate());          //生成操作时间
//				acc.setSourceType(AccessorySourceEnum.CHANGE_FILE.getValue()); //附件来源类型
				acc.setStep(ur.getStep());
				acc.setBusRecordId(co.getCoId());                              //业务单Id
				accessoryDao.save(acc);
			}
		}
		return coId;
	}

	@Override
	public String findPrintDataByProjId(String projId,String type) {
		String result ="";
		//根据工程ID查询施工组织信息
		List<ConstructionOrganization> coList= constructionOrganizationDao.findbyProjId(projId);
		Project project = projectDao.get(projId);  //根据工程ID取得实体
		ConstructionOrganization co =null;
		if(coList!=null && coList.size()>0){
			co = new ConstructionOrganization();
			co = coList.get(0);
		}
		//安装合同报表
		String arrayStr = CompletionDataPrintEnum.CU_ORGANIZATION.getCptUrl();
		//2、使用JSONArray
		net.sf.json.JSONArray jsonArray=net.sf.json.JSONArray.fromObject(arrayStr);
		if(jsonArray!=null&&jsonArray.size()>0 && co!=null && project !=null){
			net.sf.json.JSONObject jsonObject=net.sf.json.JSONObject.fromObject(jsonArray.get(0));
			CompletionDataPrintDto dto = (CompletionDataPrintDto)net.sf.json.JSONObject.toBean(jsonObject, CompletionDataPrintDto.class);
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
			result += "{reportlet:'"+dto.getReportlet()+"',coId:'" +co.getCoId()+ "',projName:'"+co.getProjName()+"',suName:'"+co.getSuName()+"',imgUrl:'" + Constants.DISK_PATH+Constants.SIGN_DISK_PATH + "'}";
			return result;
		}
		return null;
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
			ConstructionOrganization constructionOrganization=constructionOrganizationDao.get(cwId);
			if(constructionOrganization!=null){
				
				//项目经理
				if(StringUtils.isNotBlank(constructionOrganization.getChecker())){
					//通知置为无效
					signNoticeService.saveThisSignNotice(SignPostEnum.CU_PM.getValue(), SignDataTypeEnum.CONSTRUCTION_ORGANIZATION.getValue(),
							constructionOrganization.getCoId(), constructionOrganization.getProjId(),signState);
				}
				//总工程师
				if(StringUtils.isNotBlank(constructionOrganization.getTechDeptChecker())){
					//通知置为无效
					signNoticeService.saveThisSignNotice(SignPostEnum.CENERAL_ENGINEER.getValue(), SignDataTypeEnum.CONSTRUCTION_ORGANIZATION.getValue(),
							constructionOrganization.getCoId(), constructionOrganization.getProjId(),signState);
				}
				if(StringUtils.isNotBlank(constructionOrganization.getSuChecker())){
					//通知置为无效
					signNoticeService.saveThisSignNotice(SignPostEnum.SUCSE.getValue(), SignDataTypeEnum.CONSTRUCTION_ORGANIZATION.getValue(),
							constructionOrganization.getCoId(), constructionOrganization.getProjId(),signState);
				}else{
					//如果施工组织监理审核不通过，则将签字通知置为待通知
					if(StringUtil.isNotBlank(constructionOrganization.getCheckResult()) && !constructionOrganization.getCheckResult().equals("1")){
						signNoticeService.saveThisSignNotice(SignPostEnum.SUCSE.getValue(), SignDataTypeEnum.CONSTRUCTION_ORGANIZATION.getValue(),
								constructionOrganization.getCoId(), constructionOrganization.getProjId(),SignStateEnum.NO_SIGN.getValue());
					}
					//如果施工组织已重传，则将签字通知置为待签字
					if(StringUtil.isNotBlank(constructionOrganization.getReState()) && constructionOrganization.getReState().equals("1")){
						signNoticeService.saveThisSignNotice(SignPostEnum.SUCSE.getValue(), SignDataTypeEnum.CONSTRUCTION_ORGANIZATION.getValue(),
								constructionOrganization.getCoId(), constructionOrganization.getProjId(),SignStateEnum.READY_SIGN.getValue());
					
					}
				}
			}
		}
	}

	@Override
	public ConstructionOrganization findById(String s) {
		return constructionOrganizationDao.get(s);
	}




	/**
	* @Description: 施工组织
	* @author zhangnx
	* @date 2019/8/23 23:41
	*/
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public boolean delBackupsConstructionOrganization(String projId,String rollBackReason) {
		List<ConstructionOrganization> organizations = constructionOrganizationDao.findbyProjId(projId);
		if (organizations==null || organizations.size()<1) return true;

		Map<String,Object> criteriaMap=new HashMap<>();
		String t_projId = Annotations.getFieldGetMethodColumnAnNameVal(ConstructionOrganization.class, "projId");
		criteriaMap.put(t_projId,projId);
		String stepId=StepEnum.CONSTRUCTIONORGANIZATION.getValue();

		String tableName = Annotations.getClassTableAnNameVal(ConstructionOrganization.class);
		for (ConstructionOrganization or:organizations) {

			abandonedRecordService.delBackupsThisTableRecordAndSignature(tableName,criteriaMap,or.getCoId(),rollBackReason,stepId);

			ConstructionOrganization constOrgan=new ConstructionOrganization();
			constOrgan.setCoId(IDUtil.getUniqueId(Constants.MODULE_CODE_PROJECTSTART));
			constOrgan.setProjId(or.getProjId());
			constOrgan.setProjNo(or.getProjNo());
			constOrgan.setProjName(or.getProjName());
			constOrgan.setProjAddr(or.getProjAddr());
			constOrgan.setCustName(or.getCustName());
			constOrgan.setDrawName(or.getDrawName());
			
			constructionOrganizationDao.save(constOrgan);

			//将原附件的 业务单ID置为新的业务单ID
			accessoryDao.updateCOId(or.getCoId(),constOrgan.getCoId());
		}

		return true;
	}



}
