package cc.dfsoft.project.biz.base.design.service.impl;


import cc.dfsoft.project.biz.base.accept.enums.MaterialFlagEnum;
import cc.dfsoft.project.biz.base.baseinfo.dao.DocTypeDao;
import cc.dfsoft.project.biz.base.baseinfo.dao.SystemSetDao;
import cc.dfsoft.project.biz.base.baseinfo.dto.WorkDayDto;
import cc.dfsoft.project.biz.base.baseinfo.entity.Correlation;
import cc.dfsoft.project.biz.base.baseinfo.entity.DocType;
import cc.dfsoft.project.biz.base.baseinfo.service.CorrelationService;
import cc.dfsoft.project.biz.base.baseinfo.service.DocTypeService;
import cc.dfsoft.project.biz.base.baseinfo.service.FestivalService;
import cc.dfsoft.project.biz.base.baseinfo.service.WorkFlowService;
import cc.dfsoft.project.biz.base.common.entity.SystemSet;
import cc.dfsoft.project.biz.base.contract.enums.ContractIsPrintEnum;
import cc.dfsoft.project.biz.base.contract.enums.IsIntelligentConPayEnum;
import cc.dfsoft.project.biz.base.design.dao.DesignInfoDao;
import cc.dfsoft.project.biz.base.design.dao.DispatchInfoDao;
import cc.dfsoft.project.biz.base.design.dao.SurveyInfoDao;
import cc.dfsoft.project.biz.base.design.dto.SurveyInfoQueryReq;
import cc.dfsoft.project.biz.base.design.entity.DesignInfo;
import cc.dfsoft.project.biz.base.design.entity.DispatchInfo;
import cc.dfsoft.project.biz.base.design.entity.SurveyInfo;
import cc.dfsoft.project.biz.base.design.enums.PressureTypeEnum;
import cc.dfsoft.project.biz.base.design.service.DesignInfoService;
import cc.dfsoft.project.biz.base.design.service.SurveyInfoService;
import cc.dfsoft.project.biz.base.maintain.service.AbandonedRecordService;
import cc.dfsoft.project.biz.base.project.dao.*;
import cc.dfsoft.project.biz.base.project.dto.ManageRecordQueryReq;
import cc.dfsoft.project.biz.base.project.entity.*;
import cc.dfsoft.project.biz.base.project.enums.*;
import cc.dfsoft.project.biz.base.project.service.*;
import cc.dfsoft.project.biz.ifs.ResultMessage;
import cc.dfsoft.project.biz.ifs.WebServiceTypeEnum;
import cc.dfsoft.project.biz.ifs.finance.enums.FinanceOperateTypeEnum;
import cc.dfsoft.project.biz.ifs.finance.enums.ResultTypeEnum;
import cc.dfsoft.project.biz.ifs.finance.enums.UpdateTypeEnum;
import cc.dfsoft.project.biz.ifs.finance.service.IFinanceInfoService;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dao.DepartmentDao;
import cc.dfsoft.uexpress.biz.sys.dept.dao.StaffDao;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.biz.sys.dept.entity.Department;
import cc.dfsoft.uexpress.biz.sys.dept.entity.Staff;
import cc.dfsoft.uexpress.biz.sys.dept.enums.DeptDivideEnum;
import cc.dfsoft.uexpress.biz.sys.dept.enums.PostTypeEnum;
import cc.dfsoft.uexpress.biz.sys.dept.service.DepartmentService;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.constant.DataFilerSetUpDto;
import cc.dfsoft.uexpress.common.enums.WorkFlowActionEnum;
import cc.dfsoft.uexpress.common.exception.ExpressException;
import cc.dfsoft.uexpress.common.util.*;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;

@Service
@Transactional(readOnly = true,propagation=Propagation.REQUIRED)
public class SurveyInfoServiceImpl implements SurveyInfoService{

	/** 日志实例 */
	private static Logger logger = LoggerFactory.getLogger(SurveyInfoServiceImpl.class);
	/**勘察信息dao*/
	@Resource
	SurveyInfoDao surveyInfoDao;

	/**管理记录dao*/
	@Resource
	ManageRecordDao managerecorddao;

	@Resource
	ProjectDao projectDao;

	/**业务操作记录*/
	@Resource
	OperateRecordDao operateRecordDao;

	/**业务操作记录服务接口*/
	@Resource
	OperateRecordService operateRecordService;

	/**签字服务接口*/
	@Resource
	SignatureService signatureService;

	/**单据类型DAO处理*/
	@Resource
	DocTypeDao docTypeDao;

	/**管理记录服务接口*/
	@Resource
	ManageRecordService manageRecordService;

	/**单据类型服务接口*/
	@Resource
	DocTypeService docTypeService;

	@Resource
	DesignInfoService designInfoService;

	@Resource
	DesignInfoDao designInfoDao;
	@Resource
	StaffDao staffDao;
	@Resource
	SystemSetDao systemSetDao;

	/**部门Dao*/
	@Resource
	DepartmentDao departmentDao;

	/**工作流服务*/
	@Resource
	WorkFlowService workFlowService;

	/**工程类型*/
	@Resource
	ProjectTypeDao projectTypeDao;

	/**部门服务接口*/
	@Resource
	DepartmentService departmentService;

	/**关联设置*/
	@Resource
	CorrelationService correlationService;

	/**规模*/
	@Resource
	ScaleDetailService scaleDetailService;

	/** 工程接口*/
	@Resource
	ProjectService projectService;


	@Resource
	DispatchInfoDao dispatchInfoDao;
	@Resource
	IFinanceInfoService financeInfoService;
	@Resource
	FestivalService festivalService;
	@Resource
	AbandonedRecordService abandonedRecordService;











	@Override
	public Map<String, Object> querySurveyInfo(SurveyInfoQueryReq surveyInfoQueryReq) throws ParseException {

		return surveyInfoDao.querySurveyInfo(surveyInfoQueryReq);
	}
	private Map<String, Object> querySurveyInfoByTime(SurveyInfoQueryReq surveyInfoQueryReq) throws ParseException {

		//最后一个操作记录的工程记录列表（符合状态的当前工程）
		List<Map<String, Object>> ors=operateRecordDao.getOptRecordByTime(surveyInfoQueryReq.getProjStatus());
		Map<String, Object> map=surveyInfoDao.querySurveyInfo(surveyInfoQueryReq);
		//符合当前状态的工程列表
		List<SurveyInfo> list=(List<SurveyInfo>) map.get("data");
		List<SurveyInfo> listNew=new ArrayList<SurveyInfo>();
		//时间限制（单位天）
		Integer timel=surveyInfoQueryReq.getTimeLimit();
		long secondsLimit=-1l;
		if(timel!=null){
			secondsLimit=timel*24*60*60;
		}
		for(SurveyInfo sio :list){
			for(Map<String, Object> or:ors){
				if(or.get("PROJ_ID").equals(sio.getProjId())){
					//业务操作记录中时间
					Date oldTime=(Date) or.get("OPERATE_TIME");
					logger.info("oldTime=="+String.valueOf(secondsLimit));
					//当前时间
					Date nowTime=projectDao.getDatabaseDate();
					//获取两个日期之间的工作日天数
					long workDays = 0;
					try {
						workDays = FestivalUtil.calLeaveDays(oldTime, nowTime, festivalService.getCacheMap(Constants.WORKDAYS), festivalService.getCacheMap(Constants.HOLIDAYS));
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					long seconds = workDays*24*60*60;
					logger.info("newTime=="+String.valueOf(seconds));
					//用于时限进度展示
					WorkDayDto workDayDto = new WorkDayDto();
					workDayDto.setDaysLimit(String.valueOf(timel));
					workDayDto.setWorkDays(String.valueOf(workDays));
					workDayDto.setHaveDays(String.valueOf(timel-workDays));
					sio.setWorkDayDto(workDayDto);
					//如果当前时间-上个步骤的操作时间大于时间限制段则为超时
					if(secondsLimit>0&&seconds>secondsLimit){
						sio.setOverdue(true);
						continue;
					}
				}
			}

			Project pro=projectDao.get(sio.getProjId());
			if(pro!=null){
				sio.setProjectType(pro.getProjectType());
			}
			listNew.add(sio);

		}

		map.put("data",listNew);
		return map;
	}

	public List<SurveyInfo> findByConnectGasNo(String connectGasNo) {

		return surveyInfoDao.findByConnectGasNo(connectGasNo);
	}

	/**
	 * 推送现场踏勘
	 * @author fuliwei
	 * @createTime 2017-7-25
	 * @param  surveyInfo 勘察信息
	 * @return String
	 * @throws IOException
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED,rollbackFor = {Exception.class})
	public String saveSurveyInfo(SurveyInfo surveyInfo) throws Exception{
		if(StringUtils.isBlank(surveyInfo.getSurveyId())){
			surveyInfo.setSurveyId(IDUtil.getUniqueId(Constants.MODULE_CODE_DESIGN));
		}
		LoginInfo loginInfo = SessionUtil.getLoginInfo();

		//更新工程信息
		Project pro=projectDao.get(surveyInfo.getProjId()); //通过工程id查找Project
		//保存勘察信息
		if(StringUtil.isBlank(surveyInfo.getSurveyer())){
			surveyInfo.setSurveyer(pro.getSurveyer());   // 勘察人姓名
		}
		if(StringUtil.isBlank(surveyInfo.getSurveyerId())){
			surveyInfo.setSurveyerId(pro.getSurveyerId());	// 勘察人姓名id
		}

		if(StringUtils.isNotBlank(surveyInfo.getProjLongitude())){
			Map<String, Object> map = CoordinatesUtil.coordinatesConversion(surveyInfo.getProjLongitude(), surveyInfo.getProjLatitude());

			if(map!=null&&!map.isEmpty()){
				pro.setProjLongitude(map.get("x").toString());
				pro.setProjLatitude(map.get("y").toString());
			}
		}

		surveyInfoDao.saveOrUpdate(surveyInfo);
		String statusId=workFlowService.queryProjStatusId(pro.getCorpId(),pro.getProjectType(),pro.getContributionMode(),WorkFlowActionEnum.SURVEY_RESULT_REGISTER.getActionCode(), true);

		//判断下一步是否是踏勘审核，如果不是，则把造价员id存到工程表
		if(!ProjStatusEnum.TO_APPROVAL.getValue().equals(statusId) && ProjLtypeEnum.CIVILIAN.getValue().equals(pro.getProjectType())){
			pro.setCostMember(pro.getAccepter());
			pro.setCostMemberId(pro.getAccepterId());
		}

		if(surveyInfo.getIsBack().equals("1")){
			statusId = WorkFlowUtil.workFlowStatus("");				//工程状态终止
			pro.setBackReason(surveyInfo.getBackReason());			//退单原因
			pro.setBackRemarks(surveyInfo.getBackRemarks());		//退单备注
			pro.setBackDate(projectDao.getDatabaseDate());			//退单日期
			pro.setFinishedDate(projectDao.getDatabaseDate()); 		//结束日期
			//清空代办人
			pro.setToDoer("");
			//待办事项置为无效
			operateRecordDao.cancelTodo(pro.getProjId());
			//退单的如果是借料工程调用接口删除工程信息，todo:
			if (MaterialFlagEnum.YES.getValue().equals(pro.getMaterialFlag()) && projectService.isToCall(pro.getProjId(),WebServiceTypeEnum.PROJ_ACCEPT_DELETE.getValue())) {
				ResultMessage resultMessage = new ResultMessage();
				//调用nc系统工程信息同步接口
				String res = financeInfoService.synProjectInfoClient(pro.getProjId(), FinanceOperateTypeEnum.PROJ_ACCEPT_DELETE.getValue(), UpdateTypeEnum.INSERT.getValue(),IsIntelligentConPayEnum.OTHER_CON_PAY.getValue());
				JSONObject jsonbean = JSONObject.fromObject(res);
				//返回信息-当接口返回失败时，抛出异常
				resultMessage = (ResultMessage) JSONObject.toBean(jsonbean,ResultMessage.class);
				if(resultMessage!=null && ResultTypeEnum.FAIL.getValue().equals(resultMessage.getRet_type()) && projectService.isSynchronization(pro.getProjId(),WebServiceTypeEnum.PROJ_ACCEPT_DELETE.getValue())){
					//回滚事物
					throw new ExpressException(resultMessage.getRet_type(),resultMessage.getRet_message());
				}
			}
		}else{
			//转交
			if("1".equals(surveyInfo.getProjectChange())){
				Project project = new Project();
				List<ScaleDetail> list = scaleDetailService.findByprojId(pro.getProjId());
				List<ScaleDetail> newList = new ArrayList<ScaleDetail>();
				BeanUtil.copyNotNullProperties(project, pro);
				project.setProjId(IDUtil.getUniqueId(Constants.MODULE_CODE_DESIGN));
				project.setProjLongitude(null);
				project.setProjLatitude(null);
				project.setSurveyer(null);	//勘察人
				project.setSurveyerId(null);
				project.setSurveyDes(null);		//设置勘察结果
				project.setSurveyDate(null);	//设置勘察日期
				project.setDesigner(null);		//设计员
				project.setDesignerId(null);	//设计员id
				project.setDuName(null);		//设计单位
				project.setDuId(null);			//设计单位id
				//通过部门划分去查类型
				Department department=departmentService.queryDepartmentByDivide(DeptDivideEnum.MODIFICATION_GROUP.getValue(),loginInfo.getDeptId());
				if(department!=null){
					project.setDeptId(department.getDeptId());
					project.setDeptName(department.getDeptName());
					project.setRemark(surveyInfo.getRemark());
				}
				Correlation co=correlationService.findByContriubbtionCode("05");
				if(co!=null){
					project.setContributionMode(co.getCorrelatedInfoId());		//出资方式id
					project.setContributionModeDes(co.getCorrelatedInfoDes());	//出资方式
				}
				project.setProjStatusId(WorkFlowActionEnum.DESIGN_DISPATCH.getStatusCode());//待勘察派工
				String projNo = projectService.getProjMaxNo(project.getCorpId(),project.getProjectType(),co.getCorrelatedInfoId());
				if("noneNumber".equals(projNo)){
					return "noneNumber";
				}
				if("1".equals(project.getArea())){//遵义新蒲新区的工程，工程编号前加“新”
					projNo = Constants.ZUNYI_XINPU_PROJNO_PREX+projNo;
				}
				project.setProjNo(projNo);
				project.setArea(StringUtil.isNotBlank(project.getArea())?project.getArea():"");
				if(null!=list&&list.size()>0){
					for(ScaleDetail scaleDetail:list){
						ScaleDetail newsca = new ScaleDetail();
						BeanUtil.copyNotNullProperties(newsca, scaleDetail);
						newsca.setScaleId(IDUtil.getUniqueId(Constants.MODULE_CODE_DESIGN));
						newsca.setProjId(project.getProjId());
						newsca.setProjNo(project.getProjNo());
						newList.add(newsca);
					}
				}
				projectDao.save(project);
				scaleDetailService.batchInsertObjects(newList);
				statusId=WorkFlowActionEnum.ALREADY_HANDED_OVER.getStatusCode();//已移交
			}
		}


		pro.setProjStatusId(statusId); 								//设置工程状态
		pro.setSurveyDes(surveyInfo.getSurveyDes());				//设置勘察结果
		pro.setSurveyDate(surveyInfo.getSurveyDate());				//设置勘察日期
		/*pro.setSurveyer(loginInfo.getStaffName());   // 勘察人姓名
		pro.setSurveyerId(loginInfo.getStaffId());   // 勘察人ID
*/		pro.setDesigner(surveyInfo.getDesigner());	 				//设计员
		pro.setDesignerId(surveyInfo.getDesignerId()); 				//设计员id


		if(StringUtil.isNotBlank(surveyInfo.getSurveyBuilder())){
			pro.setSurveyBuilder(surveyInfo.getSurveyBuilder());    //踏勘可选现场代表
		}
		if(StringUtil.isNotBlank(surveyInfo.getSurveyBuilderId())){
			pro.setSurveyBuilderId(surveyInfo.getSurveyBuilderId()); //踏勘可选现场代表
		}
		Staff staff=staffDao.get(surveyInfo.getDesignerId());
		if(staff!=null){
			Department deparment=departmentDao.get(staff.getCorpId());
			if(deparment!=null){
				pro.setDuName(deparment.getDeptName()); //设计单位
				pro.setDuId(deparment.getDeptId());		//设计单位id
			}
		}
		//将是智能表标记 加入工程表
		if(surveyInfo.getIsIntelligentMeter()!=null){
			pro.setIsIntelligentMeter(surveyInfo.getIsIntelligentMeter());
		}
		pro.setScaleDetails(surveyInfo.getScaleDetails());
		//工程名
		if(StringUtil.isNotBlank(surveyInfo.getProjName())){
			pro.setProjName(surveyInfo.getProjName());
		}
		//工程地点
		if(StringUtil.isNotBlank(surveyInfo.getProjAddr())){
			pro.setProjAddr(surveyInfo.getProjAddr());
		}
		//客户名称
		if(StringUtil.isNotBlank(surveyInfo.getCustName())){
			pro.setCustName(surveyInfo.getCustName());
		}
		//客户联系人
		if(StringUtil.isNotBlank(surveyInfo.getCustContact())){
			pro.setCustContact(surveyInfo.getCustContact());
		}
		//客户联系人
		if(StringUtil.isNotBlank(surveyInfo.getCustId())){
			pro.setCustId(surveyInfo.getCustId());
		}

		if(StringUtils.isNotBlank(surveyInfo.getDesignerId())){
			//将踏勘审核一级操作记录改为设计员审核,查配置
			operateRecordService.updateAboutOperateRecord(pro, StepEnum.SURVEY_RESULT_REGISTER.getValue(), surveyInfo.getDesignerId(), surveyInfo.getDesigner());
		}
        if(StringUtils.isNotBlank(surveyInfo.getSurveyBuilderId())){  //踏勘时选择现场代表更新操作记录
        	operateRecordService.updateConAboutOperateRecord( pro, StepEnum.SURVEY_RESULT_REGISTER.getValue(),surveyInfo.getSurveyBuilderId(), surveyInfo.getSurveyBuilder(),PostTypeEnum.BUILDER.getValue());
        }
		//形成操作记录
		String orId = IDUtil.getUniqueId(Constants.MODULE_CODE_DESIGN);
		String toDoer=operateRecordService.createOperateRecord(orId, surveyInfo.getProjId(), pro.getProjNo(), StepEnum.SURVEY_RESULT_REGISTER.getValue(), StepEnum.SURVEY_RESULT_REGISTER.getMessage(), "");
		pro.setToDoer(toDoer);//待办人
		projectService.acceptTotalSave(pro,false);

		return Constants.OPERATE_RESULT_SUCCESS;
	}

	/**
	 * 保存现场踏勘
	 * @author fuliwei
	 * @createTime 2017-7-25
	 * @param  surveyInfo 勘察信息
	 * @return String
	 * @throws IOException
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED,rollbackFor = {Exception.class})
	public String saveSurvey(SurveyInfo surveyInfo) throws Exception{
		boolean flag = false;
		if(StringUtils.isBlank(surveyInfo.getSurveyId())){
			flag = true;
			surveyInfo.setSurveyId(IDUtil.getUniqueId(Constants.MODULE_CODE_DESIGN));
		}
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		//更新工程信息
		Project pro=projectDao.get(surveyInfo.getProjId());         //通过工程id查找Project
		//保存勘察信息
		surveyInfo.setSurveyer(pro.getSurveyer());   // 勘察人姓名
		surveyInfo.setSurveyerId(pro.getSurveyerId());
		surveyInfo.setSigns(ContractIsPrintEnum.HAVE_NOT_PRINT.getValue());//默认未打印
		if(StringUtils.isNotBlank(surveyInfo.getProjLongitude())){
			Map<String, Object> map = CoordinatesUtil.coordinatesConversion(surveyInfo.getProjLongitude(), surveyInfo.getProjLatitude());

			if(map!=null&&!map.isEmpty()){
				pro.setProjLongitude(map.get("x").toString());
				pro.setProjLatitude(map.get("y").toString());
			}
		}

		surveyInfoDao.saveOrUpdate(surveyInfo);

		try {
			//现场踏勘保存生成设计员通知
			OperateRecord operateRecord = operateRecordService.queryWorkNotice(surveyInfo.getProjNo(),StepEnum.SURVEY_RESULT_REGISTER.getValue(),surveyInfo.getProjectType());
			if(operateRecord != null){
				if(!(operateRecord.getOperateCsr1().contains(surveyInfo.getDesignerId()))){  //判断是否已经生成设计员通知

					String operateCsr1 = ","+surveyInfo.getSurveyerId()+","+ surveyInfo.getDesignerId() + ",";
					String operater ="踏勘员:" +surveyInfo.getSurveyer() + ",设计人员:" + surveyInfo.getDesigner();

					boolean isblank=StringUtil.isNotBlank(surveyInfo.getSurveyBuilderId());
					if (isblank){
						operateCsr1+=surveyInfo.getSurveyBuilderId()+",";
						operater+=",现场代表:" + surveyInfo.getSurveyBuilder();
					}

					boolean notBlank=StringUtil.isNotBlank(surveyInfo.getAccepterId());
					if (notBlank){
						operateCsr1+=surveyInfo.getAccepterId()+",";
						operater+=",受理人:" + surveyInfo.getAccepter();
					}

					operateRecord.setOperateCsr1(operateCsr1); //持久化对象直接set到数据库
					operateRecord.setOperater(operater); //持久化对象直接set到数据库
				}

			}
		} catch (Exception e) {
			//System.err.println(e);
			//System.out.println("现场踏勘环节设计员通知生成失败！");
			logger.error("现场踏勘环节设计员通知生成失败！",e);
		}

		String statusId=pro.getProjStatusId();
		//保存时退单
		if(StringUtils.isNotBlank(surveyInfo.getBackReason())){
			statusId = WorkFlowUtil.workFlowStatus("");				//工程状态终止
			pro.setBackReason(surveyInfo.getBackReason());			//退单原因
			pro.setBackDate(projectDao.getDatabaseDate());			//退单日期
			pro.setBackRemarks(surveyInfo.getBackRemarks());		//退单备注
			pro.setFinishedDate(projectDao.getDatabaseDate()); 		//结束日期
			pro.setProjStatusId(statusId); 							//设置工程状态
			//清空代办人
			pro.setToDoer("");
			//待办事项置为无效
			operateRecordDao.cancelTodo(pro.getProjId());
			String orId = IDUtil.getUniqueId(Constants.MODULE_CODE_DESIGN);
			operateRecordService.createOperateRecord(orId, surveyInfo.getProjId(), pro.getProjNo(), StepEnum.SURVEY_RESULT_REGISTER.getValue(), StepEnum.SURVEY_RESULT_REGISTER.getMessage(), "");
			//退单的如果是借料工程调用接口删除工程信息，todo:
			if (MaterialFlagEnum.YES.getValue().equals(pro.getMaterialFlag()) && projectService.isToCall(pro.getProjId(),WebServiceTypeEnum.PROJ_ACCEPT_DELETE.getValue())) {
				ResultMessage resultMessage = new ResultMessage();
				//调用nc系统工程信息同步接口
				String res = financeInfoService.synProjectInfoClient(pro.getProjId(), FinanceOperateTypeEnum.PROJ_ACCEPT_DELETE.getValue(), UpdateTypeEnum.INSERT.getValue(),IsIntelligentConPayEnum.OTHER_CON_PAY.getValue());
				JSONObject jsonbean = JSONObject.fromObject(res);
				//返回信息-当接口返回失败时，抛出异常
				resultMessage = (ResultMessage) JSONObject.toBean(jsonbean,ResultMessage.class);
				if(resultMessage!=null && ResultTypeEnum.FAIL.getValue().equals(resultMessage.getRet_type()) && projectService.isSynchronization(pro.getProjId(),WebServiceTypeEnum.PROJ_ACCEPT_DELETE.getValue())){
					//回滚事物
					throw new ExpressException(resultMessage.getRet_type(),resultMessage.getRet_message());
				}
			}
		}else{
			//转交
			if("1".equals(surveyInfo.getProjectChange())){
				Project project = new Project();
				List<ScaleDetail> list = scaleDetailService.findByprojId(pro.getProjId());
				List<ScaleDetail> newList = new ArrayList<ScaleDetail>();
				BeanUtil.copyNotNullProperties(project, pro);
				project.setProjId(IDUtil.getUniqueId(Constants.MODULE_CODE_DESIGN));
				project.setProjLongitude(null);
				project.setProjLatitude(null);
				project.setSurveyer(null);	//勘察人
				project.setSurveyerId(null);
				project.setSurveyDes(null);		//设置勘察结果
				project.setSurveyDate(null);	//设置勘察日期
				project.setDesigner(null);		//设计员
				project.setDesignerId(null);	//设计员id
				project.setDuName(null);		//设计单位
				project.setDuId(null);			//设计单位id
				//通过部门划分去查类型
				Department department=departmentService.queryDepartmentByDivide(DeptDivideEnum.MODIFICATION_GROUP.getValue(),loginInfo.getDeptId());
				if(department!=null){
					project.setDeptId(department.getDeptId());
					project.setDeptName(department.getDeptName());
					project.setRemark(surveyInfo.getRemark());
				}
				Correlation co=correlationService.findByContriubbtionCode("05");
				if(co!=null){
					project.setContributionMode(co.getCorrelatedInfoId());		//出资方式id
					project.setContributionModeDes(co.getCorrelatedInfoDes());	//出资方式
				}
				project.setProjStatusId(WorkFlowActionEnum.DESIGN_DISPATCH.getStatusCode());//待勘察派工
				String projNo = projectService.getProjMaxNo(project.getCorpId(),project.getProjectType(),co.getCorrelatedInfoId());
				if("1".equals(project.getArea())){//遵义新蒲新区的工程，工程编号前加“新”
					projNo = Constants.ZUNYI_XINPU_PROJNO_PREX+projNo;
				}
				project.setProjNo(projNo);
				project.setArea(StringUtil.isNotBlank(project.getArea())?project.getArea():"");
				if(null!=list&&list.size()>0){
					for(ScaleDetail scaleDetail:list){
						ScaleDetail newsca = new ScaleDetail();
						BeanUtil.copyNotNullProperties(newsca, scaleDetail);
						newsca.setScaleId(IDUtil.getUniqueId(Constants.MODULE_CODE_DESIGN));
						newsca.setProjId(project.getProjId());
						newsca.setProjNo(project.getProjNo());
						newList.add(newsca);
					}
				}
				projectDao.save(project);
				scaleDetailService.batchInsertObjects(newList);
				statusId=WorkFlowActionEnum.ALREADY_HANDED_OVER.getStatusCode();//已移交
			}
		}
		pro.setProjStatusId(statusId);
		pro.setSurveyDes(surveyInfo.getSurveyDes());				//设置勘察结果
		pro.setSurveyDate(surveyInfo.getSurveyDate());				//设置勘察日期
		/*pro.setSurveyer(loginInfo.getStaffName());   				// 勘察人姓名
		pro.setSurveyerId(loginInfo.getStaffId());   				// 勘察人ID
*/		pro.setDesigner(surveyInfo.getDesigner());	 				//设计员
		pro.setDesignerId(surveyInfo.getDesignerId()); 				//设计员id
		if(StringUtil.isNotBlank(surveyInfo.getSurveyBuilder())){
			pro.setSurveyBuilder(surveyInfo.getSurveyBuilder());    //踏勘可选现场代表
		}
		if(StringUtil.isNotBlank(surveyInfo.getSurveyBuilderId())){
			pro.setSurveyBuilderId(surveyInfo.getSurveyBuilderId()); //踏勘可选现场代表
		}
		Staff staff=staffDao.get(surveyInfo.getDesignerId());
		if(staff!=null){
			Department deparment=departmentDao.get(staff.getCorpId());
			if(deparment!=null){
				pro.setDuName(deparment.getDeptName()); //设计单位
				pro.setDuId(deparment.getDeptId());	//设计单位id
			}
		}
		//将是智能表标记 加入工程表
		if(surveyInfo.getIsIntelligentMeter()!=null){
			pro.setIsIntelligentMeter(surveyInfo.getIsIntelligentMeter());
		}

		pro.setScaleDetails(surveyInfo.getScaleDetails());
		//工程名
		if(StringUtil.isNotBlank(surveyInfo.getProjName())){
			pro.setProjName(surveyInfo.getProjName());
		}
		//工程地点
		if(StringUtil.isNotBlank(surveyInfo.getProjAddr())){
			pro.setProjAddr(surveyInfo.getProjAddr());
		}
		//客户名称
		if(StringUtil.isNotBlank(surveyInfo.getCustName())){
			pro.setCustName(surveyInfo.getCustName());
		}
		//客户联系人
		if(StringUtil.isNotBlank(surveyInfo.getCustContact())){
			pro.setCustContact(surveyInfo.getCustContact());
		}
		//客户联系人
		if(StringUtil.isNotBlank(surveyInfo.getCustId())){
			pro.setCustId(surveyInfo.getCustId());
		}
		projectService.acceptTotalSave(pro,false);

		List<Signature> signs=surveyInfo.getSign();
		if(signs!=null && signs.size()>0){
			for(Signature sign:signs){
				sign.setDataType(SignDataTypeEnum.SCENE_TAZHA.getValue());
			}
			surveyInfo.setSign(signs);
			//签字保存
			signatureService.saveOrUpdateSign("menuId+menuNane+23",surveyInfo.getSign(), surveyInfo.getProjId(), surveyInfo.getSurveyId(), surveyInfo.getClass().getName(), flag);
		}
		return Constants.OPERATE_RESULT_SUCCESS;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> querySurveyInfoForAudit(SurveyInfoQueryReq surveyInfoQueryReq) throws ParseException {

		String grade=null;
		
		/*DocType docType= docTypeDao.findByStepId(StepEnum.CONNECT_GAS_AUDIT.getValue());
		grade=docType==null?"":docType.getGrade();	*/
		LoginInfo loginInfo=SessionUtil.getLoginInfo();
		//SystemSet systemSet=systemSetDao.get("stepId", surveyInfoQueryReq.getStepId());
		SystemSet systemSet=systemSetDao.querySystemSetByStepId(surveyInfoQueryReq.getStepId(),loginInfo.getCorpId());
		if(systemSet!=null&&StringUtil.isNotBlank(systemSet.getTimeSpan())){
			surveyInfoQueryReq.setTimeLimit(Integer.parseInt(systemSet.getTimeSpan()));
		}
		Department department = departmentDao.queryDepartmentByDivide(DeptDivideEnum.CUSTOMER_SERVICE_CENTER.getValue(),loginInfo.getDeptId());
		if(department!=null){
			surveyInfoQueryReq.setDeptId(department.getDeptId());
		}
		Map<String,Object> result = this.querySurveyInfoByTime(surveyInfoQueryReq);
		List<SurveyInfo> data = (List<SurveyInfo>) result.get("data");

		//按步骤id进行查询 获取单据类型
		Project pro=new Project();
		if(data!=null && data.size()>0){
			/**
			 * -1 未审核  0 审核未通过  1 审核通过  2待审核
			 * 若该 审核为三级审核，且一级审核通过、二级审核未通过，三级审核待审核，则键值对：{"level1":"1","level2":"0","level3":"2"};
			 * */
			//遍历循环  设置审核级别
			for(int i = 0;i<data.size();i++){

				pro=projectDao.get(data.get(i).getProjId());
				if(pro != null){
					data.get(i).setProjLtypeDes(pro.getProjectTypeDes());//工程类型描述
					data.get(i).setContributionModeDes(pro.getContributionModeDes());//出自方式描述
				}

				//分公司查询审核级别
				DocType docType = docTypeService.findByStepId(StepEnum.CONNECT_GAS_AUDIT.getValue(),data.get(i).getCorpId(),pro.getProjectType(),pro.getContributionMode());
				if(docType!=null && StringUtils.isNotBlank(docType.getGrade())){
					grade=docType.getGrade();
				}else{
					grade="0";
				}
				//获取数据过滤配置：规则分公司ID_立项部门ID_菜单ID
				//贵安市场部立项的，如果现场踏勘没有现场代表参与，则为审核级别减1
				List<DataFilerSetUpDto> filters = Constants.getDataFilterMapByKey(pro.getCorpId()+"_" +pro.getDeptId()+ "_"+surveyInfoQueryReq.getMenuId());
				if(StringUtil.isBlank(pro.getSurveyBuilderId()) && 1<Integer.valueOf(grade) && filters!=null && filters.size()>0){
					grade = String.valueOf(Integer.parseInt(grade)-1);
				}
				data.get(i).setLevel(grade);// 设置审核总级数（设计信息2级审核）
				Map<String,String> levelBtn = new HashMap();
				//以下循环，遍历出默认应该几级审核，审核状态分别为什么，例如三级审核，则一级待审、二级未审、三级未审{"level1":"2","level2":"-1","level3":"-1"}；
				for(int n=1;n<Integer.parseInt(grade)+1;n++){
					if(n==1){
						levelBtn.put("level"+n, "2");// 待审
					}else{
						levelBtn.put("level"+n, "-1");//未审
					}
					//根据员工ID查找不能审核那一级别
					List<DataFilerSetUpDto> FilterData = Constants.getDataFilterMapByKey(pro.getProjectType()+"_"+loginInfo.getStaffId()+"_"+n+"_"+surveyInfoQueryReq.getMenuId());
					if(FilterData!=null && FilterData.size() > 0){
						levelBtn.put("level"+n, "-1");
					}
				}
				ManageRecordQueryReq mrq = new ManageRecordQueryReq();
				mrq.setBusinessOrderId(data.get(i).getSurveyId());
				mrq.setStepId(StepEnum.CONNECT_GAS_AUDIT.getValue());
				mrq.setProjId(data.get(i).getProjId());
				List<ManageRecord> mrls = managerecorddao.findByStepIdProjIdIsPass(data.get(i).getProjId(),StepEnum.CONNECT_GAS_AUDIT.getValue(),MrResultEnum.PASSED.getValue());
				if(mrls!=null && mrls.size()>0){
					String size = mrls.size()+"";
					if(mrls.size()<Integer.parseInt(grade)){
						//遍历循环，获取审核是否通过
						for(ManageRecord mr:mrls){
							levelBtn.put("level"+mr.getMrAuditLevel(),mr.getMrResult());
						}
						if(mrls.size()<Integer.parseInt(grade)){
							levelBtn.put("level"+(mrls.size()+1), "2");
						}
						//如果工程存在踏勘现场代表：根据配置条件查询数据显示是否可审核按钮
						List<DataFilerSetUpDto> list = Constants.getDataFilterMapByKey(pro.getProjectType()+"_"+loginInfo.getStaffId()+"_"+(mrls.size()+1)+"_"+surveyInfoQueryReq.getMenuId());
						if(list!=null && list.size()>0){
							levelBtn.put("level"+(mrls.size()+1), "-1");
						}
					}
				}
				data.get(i).setMrAuditLevel(JSONSerializer.toJSON(levelBtn).toString());

			}
			//如果是在踏勘审核环节
			if(ProjStatusEnum.TO_APPROVAL.getValue().equals(surveyInfoQueryReq.getProjStatus())){
				//循环处理完的数据
				Iterator<SurveyInfo> it = data.iterator();
				while(it.hasNext()){
					SurveyInfo surveyInfo = it.next();
					JSONObject jb = JSONObject.fromObject(surveyInfo.getMrAuditLevel());
					Map<String, String> map = (Map<String, String>)jb;
					//如果登录者不是设计院的 且是客服中心的
					if(!DeptDivideEnum.DESIGN_INSTITUTE.getValue().equals(loginInfo.getDeptDivide()) && DeptDivideEnum.CUSTOMER_SERVICE_CENTER.getValue().equals(loginInfo.getDeptDivide())){
						//如果记录是一级待审，并且勘察员不是自己，就删了该记录
						if(map.get("level1").equals("2")&&!loginInfo.getStaffId().equals(surveyInfo.getSurveyerId())){
							it.remove();
						}
					}
				}
			}
			result.put("data", data);
		}
		//return ResultUtil.pageResult(data.size(), surveyInfoQueryReq.getDraw(),data);
		return result;
	}

	@Override
	public SurveyInfo detail(String surveyId) {
		SurveyInfo surveyInfo = surveyInfoDao.get(surveyId);
		Project pro = projectDao.get(surveyInfo.getProjId());
		Staff sta = staffDao.get(pro.getSurveyerId());
		surveyInfo.setProject(pro);
		return surveyInfo;
	}


	@Override
	public List<SurveyInfo> findByProjId(String projId) {
		return surveyInfoDao.findByProjId(projId);
	}

	/**
	 * 查询详述
	 * @author fuliwei
	 * @createTime 2017年7月22日
	 * @param
	 * @return
	 */
	@Override
	public SurveyInfo viewSurveyInfo(String projId) {
		SurveyInfo si = new SurveyInfo();
		List<SurveyInfo> lists = surveyInfoDao.findByProjId(projId);
		DispatchInfo dispatchInfo = dispatchInfoDao.findByProjId(projId);
		Project proj = projectDao.get(projId);
		String deptDivide = "-1";

		if(null!=proj&&StringUtil.isNotBlank(proj.getDeptId())){
			Department dep = departmentService.queryDepartment(proj.getDeptId());
			if(null!=dep&&StringUtil.isNoneBlank(dep.getDeptDivide())){
				deptDivide=dep.getDeptDivide();
			}
		}
		//勘察信息已存在
		if(lists != null && lists.size() > 0){
			si = lists.get(0);
			si.setProjAddr(proj.getProjAddr()); 	//工程地点
			si.setCustName(proj.getCustName()); 	//申报单位
			si.setCustPhone(proj.getCustPhone());	//联系电话
			si.setCustContact(proj.getCustContact());//联系人
			si.setCorpName(proj.getCorpName());		//燃气公司
			si.setProjScaleDes(proj.getProjScaleDes());//工程规模
			si.setCorpName(proj.getCorpName());
			si.setProjectType(proj.getProjectType());//工程类型
			si.setProjLtypeDes(proj.getProjectTypeDes());//工程类型描述
			si.setDeptName(proj.getDeptName());//业务部门
			si.setContributionModeDes(proj.getContributionModeDes());
			si.setDivide(deptDivide);
			si.setDeptId(proj.getDeptId());
			si.setAccepter(proj.getAccepter());			//受理人
			si.setAccepterId(proj.getAccepterId());		//受理人id
			si.setProjLatitude(proj.getProjLatitude());  //纬度
			si.setProjLongitude(proj.getProjLongitude());  //经度
			if(StringUtil.isNotBlank(proj.getAccepterId())){
				Staff staff=staffDao.get(proj.getAccepterId());
				si.setAccepterPhone(staff.getPhone());	//受理人联系方式
			}


			if(StringUtils.isNotBlank(proj.getProjectType())){
				ProjectType projectType = projectTypeDao.get(proj.getProjectType());
				if(projectType!=null){
					si.setContractType(projectType.getContractType());
				}
			}
			if(dispatchInfo!=null){
				si.setDisSurveyDate(dispatchInfo.getDisSurveyDate());
			}
			//智能表标记
			if(StringUtils.isNotBlank(proj.getIsIntelligentMeter())){
				si.setIsIntelligentMeter(proj.getIsIntelligentMeter());
			}
			if(StringUtil.isNotBlank(proj.getSurveyBuilder())){
				si.setSurveyBuilder(proj.getSurveyBuilder());    //踏勘可选现场代表
			}
			if(StringUtil.isNotBlank(proj.getSurveyBuilderId())){
				si.setSurveyBuilderId(proj.getSurveyBuilderId()); //踏勘可选现场代表
			}
			return si;
		}
		//勘察信息不存在
		si.setProjName(proj.getProjName()); 		//工程名称
		si.setProjNo(proj.getProjNo()); 			//工程编号
		si.setProjId(proj.getProjId()); 			//工程id
		si.setProjAddr(proj.getProjAddr()); 		//工程地点
		si.setCustName(proj.getCustName()); 		//客户名称
		si.setProjScaleDes(proj.getProjScaleDes());
		si.setCustPhone(proj.getCustPhone());		//联系电话
		si.setCustContact(proj.getCustContact());	//联系人
		si.setCorpId(proj.getCorpId());				//分公司id
		si.setTenantId(proj.getTenantId());
		si.setCorpName(proj.getCorpName());			//分公司名称
		si.setProjectType(proj.getProjectType());//工程类型
		si.setProjLtypeDes(proj.getProjectTypeDes());//工程类型描述
		si.setDeptName(proj.getDeptName());			//业务部门
		si.setContributionModeDes(proj.getContributionModeDes());//出出资类型
		si.setSurveyer(proj.getSurveyer());			//勘察人
		si.setSurveyerId(proj.getSurveyerId());		//勘察人id
		si.setDivide(deptDivide);
		si.setDeptId(proj.getDeptId());
		si.setAccepter(proj.getAccepter());			//受理人
		si.setAccepterId(proj.getAccepterId());		//受理人id
		si.setProjLatitude(proj.getProjLatitude());  //纬度
		si.setProjLongitude(proj.getProjLongitude());  //经度
		if(StringUtils.isNotBlank(proj.getProjectType())){
			ProjectType projectType = projectTypeDao.get(proj.getProjectType());
			if(projectType!=null){
				si.setContractType(projectType.getContractType());
			}
		}
		if(dispatchInfo!=null){
			si.setDisSurveyDate(dispatchInfo.getDisSurveyDate());
		}
		if(StringUtil.isNotBlank(proj.getAccepterId())){
			Staff staff=staffDao.get(proj.getAccepterId());
			si.setAccepterPhone(staff.getPhone());	//受理人联系方式
		}
		if(StringUtil.isNotBlank(proj.getDesigner())){
			si.setDesigner(proj.getDesigner());
		}
		if(StringUtil.isNotBlank(proj.getDesignerId())){
			si.setDesignerId(proj.getDesignerId());
		}
		return si;
	}

	/**
	 * 方案确认通过
	 * @createTime
	 * @param manageRecord
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void auditPass(ManageRecord manageRecord) {
		DocType docType = new DocType();
		if(manageRecord.getPressureType().equals(PressureTypeEnum.DEPRESSION.getValue())){
			docType = docTypeDao.findByStepId(StepEnum.CONNECT_GAS_AUDIT.getValue()+"-"+PressureTypeEnum.DEPRESSION.getValue());
		}else if(manageRecord.getPressureType().equals(PressureTypeEnum.MEDIUM_PRESSURE.getValue())){
			docType = docTypeDao.findByStepId(StepEnum.CONNECT_GAS_AUDIT.getValue()+"-"+PressureTypeEnum.MEDIUM_PRESSURE.getValue());
		}else{
			docType = docTypeDao.findByStepId(StepEnum.CONNECT_GAS_AUDIT.getValue()+"-"+PressureTypeEnum.HIGH_PRESSURE.getValue());
		}
		manageRecordService.auditSave(manageRecord,docType==null?"":docType.getId(),
				docType==null?"":docType.getGrade(),WorkFlowActionEnum.PROGRAMME_AUDIT.getActionCode(),Constants.MODULE_CODE_DESIGN);
		// 自动下委托单，正式设计
		String projId = manageRecord.getProjId();
		Project project = projectDao.get(projId);
		DesignInfo designInfo = designInfoService.queryById(projId);
		if (designInfo == null) {  //没有设计信息，则新建设计信息 
			designInfoService.insertDesignInfo(project);
		}else {   // 如果方案确认后有问题，再次确认则更新委托时间
			designInfo.setOcoDate(projectDao.getDatabaseDate());
			designInfoDao.update(designInfo);
		}
		//保存未通过标记
		if("0".equals(manageRecord.getMrResult())){
			project.setSignBack(Constants.MODULE_CODE_DESIGN);
		}
		//将接气反馈信息会写到工程信息中
		String surveyFeedBack=project.getSurveyFeedBack()==null?"":project.getSurveyFeedBack();
		project.setSurveyFeedBack(surveyFeedBack+manageRecord.getMrAopinion());
		projectDao.update(project);
	}

	@Override
	public boolean rollBackContainsSurvey(String projId,String rollBackReason) {
		SurveyInfo surveyInfo = surveyInfoDao.get("projId", projId);
		if (surveyInfo==null) return false;


		//备份原信息记录
		Map<String,Object> criteriaMap=new HashMap<>();
		String t_projId = Annotations.getFieldGetMethodColumnAnNameVal(SurveyInfo.class, "projId");
		criteriaMap.put(t_projId,projId);
		String tableName = Annotations.getClassTableAnNameVal(SurveyInfo.class);
		String origData = abandonedRecordService.getThisTableOrigData(tableName, criteriaMap);
		abandonedRecordService.saveAbandonedRecord(surveyInfo.getSurveyId(),projId,StepEnum.SURVEY_RESULT_REGISTER.getValue(),rollBackReason,origData);


		//更新踏勘信息
		try {
			surveyInfo.setDuDeputy(null);//设计员签字
			surveyInfo.setEngineering(null);//工程部人员签字
			surveyInfo.setMarket(null);//市场管理部人员签字
			surveyInfo.setSurveyDate(null);//踏勘日期
			surveyInfo.setSurveyDesDate(null);//踏勘结果登记日期
			surveyInfo.setTechSugDate(null);//技术建议填写日期
			surveyInfo.setOtherSugDate(null);//其他部门意见日期
		}catch (Exception e){
			e.printStackTrace();
		}
		surveyInfoDao.saveOrUpdate(surveyInfo);
		return true;

	}


}
