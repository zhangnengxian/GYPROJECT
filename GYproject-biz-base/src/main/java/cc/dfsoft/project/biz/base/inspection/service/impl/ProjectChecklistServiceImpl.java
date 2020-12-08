package cc.dfsoft.project.biz.base.inspection.service.impl;

import cc.dfsoft.uexpress.common.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate4.HibernateOptimisticLockingFailureException;

import cc.dfsoft.project.biz.base.constructmanage.dto.CompletionDataPrintDto;
import cc.dfsoft.project.biz.base.constructmanage.dto.UploadResult;
import cc.dfsoft.project.biz.base.inspection.dao.ProjectChecklistDao;
import cc.dfsoft.project.biz.base.inspection.dto.ProjectChecklistQueryReq;
import cc.dfsoft.project.biz.base.inspection.entity.ProjectChecklist;
import cc.dfsoft.project.biz.base.inspection.enums.ProjectCheckListFlagEnum;
import cc.dfsoft.project.biz.base.inspection.enums.ProjectChecklistTypeEnum;
import cc.dfsoft.project.biz.base.inspection.enums.StrTestPipeTypeEnum;
import cc.dfsoft.project.biz.base.inspection.service.ProjectChecklistService;
import cc.dfsoft.project.biz.base.project.dao.*;
import cc.dfsoft.project.biz.base.project.entity.*;
import cc.dfsoft.project.biz.base.project.enums.SignDataTypeEnum;
import cc.dfsoft.project.biz.base.project.enums.SignPostEnum;
import cc.dfsoft.project.biz.base.project.enums.SignStateEnum;
import cc.dfsoft.project.biz.base.project.service.SignNoticeService;
import cc.dfsoft.project.biz.base.project.service.SignatureService;
import cc.dfsoft.project.biz.ifs.finance.enums.ResultTypeEnum;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.exception.ExpressException;
import cc.dfsoft.uexpress.common.util.FileUtil;
import cc.dfsoft.uexpress.common.util.IDUtil;
import cc.dfsoft.uexpress.common.util.StringUtil;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.StaleObjectStateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateOptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
/**
 * 工程报验单Service实现
 * @author Administrator
 *
 */
@Service
@Transactional(readOnly = true,propagation=Propagation.REQUIRED)
public class ProjectChecklistServiceImpl implements ProjectChecklistService{
	public static Logger logger = LoggerFactory.getLogger("interfaceinfo");

	/**工程报验单Dao*/
	@Resource
	ProjectChecklistDao projectChecklistDao;

	@Resource
	SignatureService signatureService;

	@Resource
	AccessoryDao accessoryDao;

	@Resource
	SignNoticeDao signNoticeDao;

	/**签字通知服务*/
	@Resource
	SignNoticeService signNoticeService;

	@Resource
	ProjectDao projectDao;

	@Resource
	SignNoticeStandardDao signNoticeStandardDao;
	@Autowired
	ManageRecordDao manageRecordDao;

	@Override
	public Map<String, Object> queryPrProjectChecklist(ProjectChecklistQueryReq listQueryReq) throws ParseException {
		Map<String,Object> result = projectChecklistDao.queryProjectChecklist(listQueryReq);
		if(result!=null){
			List<ProjectChecklist> pcls = (List<ProjectChecklist>) result.get("data");
			if(pcls!=null && pcls.size()>0){
				for(int i=0;i<pcls.size();i++){
					ProjectChecklist pcl = pcls.get(i);
				}
				result.put("data", pcls);
			}

		}
		return result;
	}

	/**
	 * 工程报验单详述
	 * @param id 地址id
	 * @return ProjectChecklist
	 */
	@Override
	public ProjectChecklist viewProjectCheckList(String id) {
		ProjectChecklist pcList=projectChecklistDao.get(id);
		//查询报验单附件
		List<AccessoryList> accList= accessoryDao.queryAccessoryByBus(pcList.getPcId(),null);
		if(accList!=null && accList.size()>0){
			AccessoryList ac=accList.get(0);
			pcList.setAccListId(ac.getAlId());
		}
		return pcList;
	}

	/**
	 * 工程报验单详述
	 * @param id
	 * @param menuDes
	 * @return ProjectChecklist
	 */

	@Override
	public ProjectChecklist viewProjectCheckListFile(String id,String menuDes) {
		ProjectChecklist pcList=projectChecklistDao.get(id);
		Signature signature=signatureService.selectImg(id, menuDes);
		if(signature!=null){
			pcList.setDrawUrl(Constants.DIAGRAM_DISK_PATH+signature.getImgUrl());
		}
		pcList.setMenuDes(menuDes);
		return pcList;
	}
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void saveProChecklist(ProjectChecklist t){
		if(StringUtils.isNotBlank(t.getPcId())){
			projectChecklistDao.updateNotNullProperties(t, t.getPcId());
		}else{
			t.setPcId(IDUtil.getUniqueId(Constants.MODULE_CODE_PROJECTSTART));
			t.setProjId("1");
			t.setProjNo("1");
			projectChecklistDao.save(t);
		}
	}




	/**
	 * 保存报验单公共方法
	 * @author fuliwei
	 * @createTime 2016-7-25
	 * @param checkList
	 * @return String
	 */
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED,rollbackFor={Exception.class})
	public String saveProCheckList(ProjectChecklist checkList, String uniqueId) throws Exception {
		String dataType ="";
		boolean flag = false;
		try {
			checkList.setPcFlag("1");  //删除标记
			if(StringUtils.isNotBlank(checkList.getPcId())){
				projectChecklistDao.update(checkList);
			}else{
				flag = true;
				checkList.setPcId(IDUtil.getUniqueId(uniqueId));
				projectChecklistDao.save(checkList);
			}
			dataType=this.saveProjectCheckListSignNotice(checkList);   //得到签字单据类型
			//签字保存
			
			if(StringUtils.isNotBlank(dataType)){
				List<Signature> removeSignature = new ArrayList<Signature>() ;
				List<Signature> signs=checkList.getSign();
				if(signs!=null && signs.size()>0){
					for(Signature sign:signs){
						sign.setDataType(dataType);
						//判断管道类型--如果是户内管道则不需要安全员和质检员签字
						if(StrTestPipeTypeEnum.INDOOR_PIPE.getValue().equals(checkList.getStPipeType())){
							if(sign.getPostType().contains(SignPostEnum.SAFTYOFFICER.getValue()) || sign.getPostType().contains(SignPostEnum.QUALITATIVE_CHECK_MEMBER.getValue())){
								removeSignature.add(sign);  //如果是安全员和质检员则添加到removeSignature
							}
						}
					}
					if(removeSignature != null && removeSignature.size() >0){
						signs.removeAll(removeSignature);  //不生成安全员和质检员签字通知
					}
					checkList.setSign(signs);
				}
			}
		}catch(HibernateOptimisticLockingFailureException e){
			throw e;
		}
		signatureService.saveOrUpdateSign(checkList.getMenuId(),checkList.getSign(), checkList.getProjId(), checkList.getPcId(), checkList.getClass().getName(), flag);

		//判断是否是 管沟开挖、PE管焊接、防腐记录 、吹扫记录、清扫记录的一种
		//记录区第一个人签字不加postType
		//记录区保存不会生成签字通知
		//在生成报验单的时候，判断哪几个单据是否记录区有签字
		//查询其签字标准，获取记录区的第一、第二签字顺序的post，遍历报验单的RecordsId（已“，”隔开的），设置第一人的postType，将第一人的签字通知置为无效，生成第二签字顺序的签字通知
		if((ProjectChecklistTypeEnum.GROOVE_RECORD.getValue().equals(checkList.getPcDesId())||
				ProjectChecklistTypeEnum.PEPIPE_WELDING.getValue().equals(checkList.getPcDesId())||
				ProjectChecklistTypeEnum.PRESERVATIVE.getValue().equals(checkList.getPcDesId())||
				ProjectChecklistTypeEnum.PURGE.getValue().equals(checkList.getPcDesId())||
				ProjectChecklistTypeEnum.CLEAR_RECORD.getValue().equals(checkList.getPcDesId())) && flag){
			signatureService.saveCheckListSign(checkList,dataType);
		}

		return checkList.getPcId();
	}

	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public String saveProCheckListFile(HttpServletRequest request, UploadResult proCheckList, MultipartFile[] files,String pcDesId)
			throws Exception {
		String result = proCheckList.getResult();
		JSONObject jo = new JSONObject();
		ProjectChecklist pcl = JSON.parseObject(result, ProjectChecklist.class);
		JSONArray sign = (JSONArray) jo.parseObject(result).get("sign");
		if(sign!=null){
			List<Signature> signs = JSONObject.parseArray(sign.toJSONString(), Signature.class);
			pcl.setSign(signs);
		}
		String name=pcl.getLayoutDiagram();
		if(files!=null){
			pcl.setLayoutDiagram(files[0].getOriginalFilename());
		}
		String pcId = this.saveProCheckList(pcl,pcDesId);
		proCheckList.setOperateId(pcId);
		if(StringUtil.isBlank(name)){
			List<AccessoryList> accs =accessoryDao.queryAccessoryByBus(pcl.getPcId(), "");
			if(accs!=null&&accs.size()>0){
				if(files!=null){
					FileUtil.deleteFile(Constants.DISK_PATH+accs.get(0).getAlPath());//删除以前的附件
				}
				accessoryDao.delete(accs.get(0));//删除附件
			}
			if(files!=null){
				AccessoryList acc = new AccessoryList();
				String path=FileUtil.uploadFile(request, proCheckList.getAlPath(), files);//路径
				String fileName = files[0].getOriginalFilename();               //文件名全名（例：文件名.png）
				//String filePath= Constants.FIRST_DISK_PATH + path + fileName;
				String filePath= Constants.FIRST_DISK_PATH + path ;
				String name1 = fileName.substring(0,fileName.lastIndexOf("."));//文件去掉格式后的名（从第0位截取，到文件格式(例：“.png“）前的点结束）
				String fileType=fileName.substring(fileName.lastIndexOf(".")+1,fileName.length());//文件格式（例：“.png”）
				LoginInfo loginInfo = SessionUtil.getLoginInfo();
				acc.setAlId(IDUtil.getUniqueId(Constants.FILE_CODE_ACCESS));   //生成附件Id
				acc.setProjId(pcl.getProjId());                                 //项目Id
				acc.setProjNo(pcl.getProjNo());                                 //项目编号
//				acc.setProjLtypeId(co.getProjLtypeId());                       //工程大类
				acc.setAlTypeId(fileType);                                     //附件格式（例：“.png"）
				acc.setStepId(proCheckList.getStepId());                                 //步骤Id
				acc.setStep(proCheckList.getStep());
				acc.setAlName(name1);                                          //附件名称
				acc.setAlPath(filePath);                                       //附件路径
				acc.setAlOperateCsrId(loginInfo.getStaffId());                 //操作人Id
				acc.setAlOperateCsr(loginInfo.getStaffName());                 //操作人姓名
				acc.setAlOperateTime(accessoryDao.getDatabaseDate());          //生成操作时间
//				acc.setSourceType(AccessorySourceEnum.CHANGE_FILE.getValue()); //附件来源类型
				acc.setStep(proCheckList.getStep());
				acc.setBusRecordId(pcl.getPcId());                              //业务单Id
				accessoryDao.save(acc);
			}
		}
		/*
		 //上传简图
		 if(StringUtil.isBlank(name)){
			signatureService.saveImage(request, files, pcId, pcl.getProjNo(), pcl.getPcId(), pcl.getMenuDes(),pcl.getMenuId());
		}*/
		return pcId;
	}

	@Override
	public ProjectChecklist viewProjectCheckList(String projId, String pcId) {
		return projectChecklistDao.viewProjectCheckList(projId, pcId);
	}

	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public String updateFlag(String pcId, Integer flag) {

		ProjectChecklist projectChecklist = projectChecklistDao.get(pcId);
		if (projectChecklist == null) {
			projectChecklist = new ProjectChecklist();
		}else{
			//校验是否完成签字
			String siFinishSign = "";
			String tableName=ProjectChecklistTypeEnum.valueof(projectChecklist.getPcDesId()).getTableName();//查询的表结构 如Electrode_Record
			String judgeIsSign=ProjectChecklistTypeEnum.valueof(projectChecklist.getPcDesId()).getIsCheckSign();//是否检查记录表签字 1是 0 否
			if(Constants.NOT.equals(judgeIsSign)){
				//不检查记录表，只查报验单
				siFinishSign= projectChecklistDao.checkListIsFinishSign(projectChecklist.getPcId());
			}else{
				//检查记录表和报验单
				siFinishSign= projectChecklistDao.checkIsFinishSign(projectChecklist.getPcId(),tableName);
			}
			if(Constants.OPERATE_RESULT_FAILURE.equals(siFinishSign)){
				//未完成签字
				return Constants.NOT;
			}
		}
		return updateFlagByPcId(pcId, flag, projectChecklist);

	}





	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public String strengthTestUpdateFlag(String pcId, Integer flag,String resetReason) {
		ProjectChecklist projectChecklist = projectChecklistDao.get(pcId);
		projectChecklist.setResetReason(resetReason);

		if (projectChecklist==null) return Constants.OPERATE_RESULT_FAILURE;
		return updateFlagByPcId(pcId,flag,projectChecklist);
	}




	public String updateFlagByPcId(String pcId, Integer flag,ProjectChecklist projectChecklist){
		String dataType = this.saveProjectCheckListSignNotice(projectChecklist);  //得到签字单据类型
		Project proj = projectDao.get(projectChecklist.getProjId());
		LoginInfo loginInfo= SessionUtil.getLoginInfo();
		//1、将该报验单关联的签字通知都置为无效
		List<SignNotice> signList = signNoticeDao.queryByBusiIdAndDataType(pcId, dataType);
		if (signList != null && signList.size() > 0) {
			for (SignNotice sn : signList) {
				logger.info("报验推送-将该报验单关联的签字通知都置为无效:报验单id"+pcId+",操作人:"+loginInfo.getStaffId()+
						",操作时间:"+DateUtil.getLongDateSSS(new Date())+",更新之前签字通知的状态:"+sn.getStatus()+",签字通知id:"+sn.getSignNoticeId());
				sn.setStatus(SignStateEnum.ALREADY_SIGN.getValue());//当前职务已签字
				signNoticeDao.saveOrUpdate(sn);
			}
		}

		//判断报验单是否需要审核
		List<Map<String, Object>> list = Constants.getConstantsMapByKey(Constants.AYDIT_INS);
		for (Map<String, Object> map : list) {
			if (projectChecklist.getPcDesId().equals(String.valueOf(map.get("RESERVE1")))) {
				logger.info("报验推送-判断报验单是否需要审核:报验单id"+pcId+",类型:"+projectChecklist.getPcDesId()+",判断时间:"+DateUtil.getLongDateSSS(new Date())+",操作人:"+loginInfo.getStaffId());
				flag = 2;//设置为需要审核
				this.creatInspectionAuditNotice(proj, dataType, pcId);    //生成审核签字通知;配置的从报验审核从哪个顺序开始发通知 如发给监理 还是现场代表 要查t_sys_config表
				break;
			}
		}
		projectChecklist.setFlag(flag);
		projectChecklistDao.updateFlagByPcId1(projectChecklist);
		return Constants.OPERATE_RESULT_SUCCESS;
	}








	//生成报验审核的第一级签字通知
	public void creatInspectionAuditNotice(Project proj,String dataType,String pcId){
		Object obj = Constants.getSysConfigByKey(proj.getCorpId()+"_"+proj.getProjectType()+"_"+proj.getContributionMode()+"_"+dataType);
		if(obj==null){
			obj=Constants.getSysConfigByKey("1101_11_1_"+dataType);//如开始顺序为3(监理)
		}
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		//先查之前是否生成过签字通知
		List<SignNotice> snList=signNoticeDao.queryByBusiIdAndSort(pcId,obj.toString());
		SignNotice sn=new SignNotice();
		if(snList!=null && snList.size()>0){
			//已生成，将签字通知置为有效，即待签字
			sn=snList.get(0);
			logger.info("报验推送-将该报验单关联的签字通知置为有效:报验单id"+pcId+
					",操作时间:"+DateUtil.getLongDateSSS(new Date())+",之前的签字通知状态:"+sn.getStatus()+",操作人:"+loginInfo.getStaffId());
			sn.setStatus(SignStateEnum.READY_SIGN.getValue());//待签字
			sn.setGrade("1");
			signNoticeDao.saveOrUpdate(sn);

		}else{
			//查询签字标准 生成报验审核第一级通知,先按配置查询
			List<SignNoticeStandard> signNoticeStandardList=signNoticeStandardDao.queryBySortNoAndDateType(obj.toString(),dataType,proj.getCorpId(),proj.getProjectType(),proj.getContributionMode());
			if(signNoticeStandardList.size() < 1){
				//查询默认的签字顺序
				signNoticeStandardList=signNoticeStandardDao.queryBySortNoAndDateType(obj.toString(),dataType,Constants.CORP_ID,Constants.PROJECT_TYPE,Constants.CONTRIBUTION_MODE);
			}
			if(signNoticeStandardList!=null && signNoticeStandardList.size()>0){
				SignNoticeStandard stand=signNoticeStandardList.get(0);
				sn.setSignNoticeId(IDUtil.getUniqueId(Constants.MODULE_CODE_NOTICE));
				sn.setPost(stand.getPost());                          		//职务
				sn.setPostName(SignPostEnum.valueof(stand.getPost()).getMessage());
				sn.setDataType(StringUtil.isNotBlank(dataType)?dataType:"");         		//资料类型
				sn.setDataTypeName(StringUtil.isNotBlank(dataType)?SignDataTypeEnum.valueof(dataType).getMessage():"");
				sn.setSortNo(obj.toString());
				sn.setProjId(proj.getProjId());
				sn.setProjNo(proj.getProjNo());
				sn.setBusinessOrderId(pcId);
				sn.setProjName(proj.getProjName());
				sn.setStatus(SignStateEnum.READY_SIGN.getValue());//待签字
				sn.setGrade("1");
				logger.info("报验推送-新生成签字通知:报验单id"+pcId+
						",操作时间:"+DateUtil.getLongDateSSS(new Date())+",操作人:"+loginInfo.getStaffId()+"签字通知id:"+sn.getSignNoticeId());
				signNoticeDao.save(sn);
			}
		}
	}







	@Override
	public List<Object> findPrintDataByProjId(String projId, String pcDesId,String arrayStr) {
		//根据工程id和报验单类型查询报验单列表
		List<ProjectChecklist> pcList = projectChecklistDao.findByProjIdAndDesId(projId,pcDesId,ProjectCheckListFlagEnum.COMPLETED.getValue());
		String result ="";
		List<Object> list = new ArrayList<Object>();
		//使用JSONArray
		net.sf.json.JSONArray jsonArray=net.sf.json.JSONArray.fromObject(arrayStr);
		if(jsonArray!=null&&jsonArray.size()>0 && pcList!=null && pcList.size()>0){
			//遍历报验单
			for(ProjectChecklist pc : pcList){
				//遍历cpt
				for(Object obj:jsonArray){
					net.sf.json.JSONObject jsonObject=net.sf.json.JSONObject.fromObject(obj);
					CompletionDataPrintDto dto = (CompletionDataPrintDto)net.sf.json.JSONObject.toBean(jsonObject, CompletionDataPrintDto.class);
					//记录类型与cpt类型相同
					//cpt的type为空
					//cpt的type不为空，则需要记录类型与cpt类型相同
					result = "{reportlet:'"+dto.getReportlet()+"',projId:'"+pc.getProjId()+"',projName:'"+pc.getProjName()+"',pcId:'"+pc.getPcId()+"',projNo:'"+pc.getProjNo()+"',imgUrl:'" + Constants.DISK_PATH+Constants.SIGN_DISK_PATH;
					result += "',constructionUnit:'"+pc.getConstructionUnit()+"',suName:'"+pc.getSuName()+"',inspectionDate:'"+pc.getInspectionDate()+"',corpName:'"+pc.getCorpName()+"',projAddr:'"+pc.getProjAddr();
					result += "',custName:'"+pc.getCustName()+"',drawUrl:'"+Constants.DISK_PATH+Constants.DIAGRAM_DISK_PATH+"',cuDate:'"+pc.getCuDate();
					//result += "',stBuildingNo:'"+pc.getStBuildingNo()+"',stHouseHolds:'"+pc.getStHouseHolds()+"',stMedium:'"+pc.getStMedium()+"',stRange:'"+pc.getStRange();
					result += "'}";
					if(StringUtil.isNotBlank(dto.getType())){//cpt的type不为空，判断
						if(pcDesId.equals(ProjectChecklistTypeEnum.PRESERVATIVE.getValue()) && StringUtil.isNotBlank(pc.getPreservativeType()) && pc.getPreservativeType().equals(dto.getType())){//防腐记录
							list.add(result);
						}else if(pcDesId.equals(ProjectChecklistTypeEnum.PRESERVATIVE_INPECT.getValue()) && StringUtil.isNotBlank(pc.getPreservativeType()) && pc.getPreservativeType().equals(dto.getType())){//防腐检查
							list.add(result);
						}else if(pcDesId.equals(ProjectChecklistTypeEnum.PE_WELD_LINE_INPECT.getValue()) && pc.getMeltConnectType().equals(dto.getType())){//pe管焊缝检查
							list.add(result);
						}else if(pcDesId.equals(ProjectChecklistTypeEnum.STRENGTH_TEST.getValue()) && pc.getStPipeType().equals(dto.getType())){//强度试验
							list.add(result);
						}
					}else{
						list.add(result);
					}
				}
			}
		}
		return list;
	}


	/**
	 * 生成签字
	 * @author fuliwei
	 * @createTime 2018年1月22日
	 * @param
	 * @return
	 */
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public String saveProjectCheckListSignNotice(ProjectChecklist list) {

		if(ProjectChecklistTypeEnum.STRENGTH_TEST.getValue().equals(list.getPcDesId())){
			return SignDataTypeEnum.STRENGTH_TEST.getValue();

		}else if(ProjectChecklistTypeEnum.ELECTRODE_RECORD.getValue().equals(list.getPcDesId())){
			return SignDataTypeEnum.ELECTRODE_RECORD.getValue();

		}else if(ProjectChecklistTypeEnum.ELECTRODE_BAKING.getValue().equals(list.getPcDesId())){
			return SignDataTypeEnum.ELECTRODE_BAKING.getValue();

		}else if(ProjectChecklistTypeEnum.GROOVE_RECORD.getValue().equals(list.getPcDesId())){
			return SignDataTypeEnum.GROOVE_RECORD.getValue();

		}else if(ProjectChecklistTypeEnum.PIPELINE_INSTALL.getValue().equals(list.getPcDesId())){
			return SignDataTypeEnum.PIPELINEINSTALL.getValue();

		}else if(ProjectChecklistTypeEnum.PEPIPE_WELDING.getValue().equals(list.getPcDesId())){
			return SignDataTypeEnum.PE_PIPEWELDING_CHECK.getValue();

		}else if(ProjectChecklistTypeEnum.PEPIPE_WELDING.getValue().equals(list.getPcDesId())){
			return SignDataTypeEnum.PE_PIPEWELDING_CHECK.getValue();

		}else if(ProjectChecklistTypeEnum.PRESERVATIVE_INPECT.getValue().equals(list.getPcDesId())){
			return SignDataTypeEnum.PRESERVATIVE_INPECT.getValue();
		}else if(ProjectChecklistTypeEnum.HIDDEN_WORKS.getValue().equals(list.getPcDesId())){
			return SignDataTypeEnum.HIDDEN_WORKS.getValue();
		}else if(ProjectChecklistTypeEnum.PURGE.getValue().equals(list.getPcDesId())){
			return SignDataTypeEnum.PURGE.getValue();
		}else if(ProjectChecklistTypeEnum.UNDER_GROUND_INPECT.getValue().equals(list.getPcDesId())){
			return SignDataTypeEnum.UNDERGROUNDINPECT.getValue();
		}else if(ProjectChecklistTypeEnum.TRENCH_BACK_FILL.getValue().equals(list.getPcDesId())){
			return SignDataTypeEnum.TRENCH_BACKFILL.getValue();
		}else if(ProjectChecklistTypeEnum.BALL_RECORD.getValue().equals(list.getPcDesId())){
			return SignDataTypeEnum.BALL_RECORD.getValue();
		}else if(ProjectChecklistTypeEnum.EQUIPMENT_INSTALL.getValue().equals(list.getPcDesId())){
			return SignDataTypeEnum.EQUIPMENTINSTALL.getValue();
		}else if(ProjectChecklistTypeEnum.HOT_MELT_DOCKING.getValue().equals(list.getPcDesId())){
			return SignDataTypeEnum.HOT_MELT_DOCKING.getValue();
		}else if(ProjectChecklistTypeEnum.ANODE_INSTALL.getValue().equals(list.getPcDesId())){
			return SignDataTypeEnum.ANODE_INSTALL.getValue();
		}else if(ProjectChecklistTypeEnum.WELD_LINE_INPECT.getValue().equals(list.getPcDesId())){
			return SignDataTypeEnum.WELDLINE_INPECT.getValue();
		}else if(ProjectChecklistTypeEnum.PE_WELD_LINE_INPECT.getValue().equals(list.getPcDesId())){
			return SignDataTypeEnum.PE_WELDLINE_INSPECT.getValue();
		}else if(ProjectChecklistTypeEnum.CLEAR_RECORD.getValue().equals(list.getPcDesId())){
			return SignDataTypeEnum.CLEAR_RECORD.getValue();
		}else if(ProjectChecklistTypeEnum.PIPE_WELD_RECORD.getValue().equals(list.getPcDesId())){
			return SignDataTypeEnum.PIPE_WELD_RECORD.getValue();
		}else if(ProjectChecklistTypeEnum.INDOOR_POCKET_WATCH.getValue().equals(list.getPcDesId())){
			return SignDataTypeEnum.INDOOR_POCKET_WATCH.getValue();
		}else if(ProjectChecklistTypeEnum.PRESERVATIVE.getValue().equals(list.getPcDesId())) {
			return SignDataTypeEnum.PRESERVATIVE.getValue();
		}
		// TODO Auto-generated method stub
		return "";
	}

	/**
	 * 根据pcId删除报验列表记录
	 * @author 王会军
	 * @createTime 2018年1月24
	 */
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void deleteListById(String pcId) {
		// TODO Auto-generated method stub
		projectChecklistDao.deleteListById(pcId);

	}

	@Override
	public void sendListById(String pcId) {
		projectChecklistDao.sendListById(pcId);
	}

	@Override
	public Date getSysDate() {
		return projectChecklistDao.getDatabaseDate();
	}



	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public String resetCheck(ProjectChecklist checkList) {
		//删除签字
		signatureService.deleteByBId(checkList.getPcId());
		//审核记录改为无效
		manageRecordDao.batUpdateHistoryRecordByBoId(checkList.getPcId(), null);  
		//修改报验单状态为待推送
		String flag = "0";
		if(Constants.getConsByKeyAndId(Constants.CHECK_STATUS,"100301")!=null){
			flag = String.valueOf(Constants.getConsByKeyAndId(Constants.CHECK_STATUS,"100301").get("CNVALUE"));
		}

		ProjectChecklist proChe = projectChecklistDao.get(checkList.getPcId());
		proChe.setFlag(Integer.parseInt(flag));
		proChe.setResetReason(checkList.getResetReason());

		projectChecklistDao.updateFlagByPcId1(proChe);

		return null;
	}
}
