package cc.dfsoft.project.biz.base.station.controller;

import cc.dfsoft.project.biz.base.baseinfo.dto.CorrelationReq;
import cc.dfsoft.project.biz.base.baseinfo.entity.Correlation;
import cc.dfsoft.project.biz.base.baseinfo.enums.AcceptTypeEnum;
import cc.dfsoft.project.biz.base.baseinfo.enums.BackReasonEnum;
import cc.dfsoft.project.biz.base.baseinfo.enums.CorrelationTypeEnum;
import cc.dfsoft.project.biz.base.baseinfo.enums.ProjectMethodEnum;
import cc.dfsoft.project.biz.base.baseinfo.service.CorrelationService;
import cc.dfsoft.project.biz.base.baseinfo.service.WorkFlowService;
import cc.dfsoft.project.biz.base.project.dto.ProjectQueryReq;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.project.enums.ProjStatusEnum;
import cc.dfsoft.project.biz.base.project.service.RelationShipService;
import cc.dfsoft.project.biz.base.station.dto.StationProceduresDto;
import cc.dfsoft.project.biz.base.station.entity.StationData;
import cc.dfsoft.project.biz.base.station.enums.StationDataTypeEnum;
import cc.dfsoft.project.biz.base.station.service.StationAcceptService;
import cc.dfsoft.project.biz.base.station.service.StationDataService;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.biz.sys.dept.service.DepartmentService;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.enums.WorkFlowActionEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 工程立项
 * @author fuliwei
 *
 */
@Controller
@RequestMapping(value="/stationAccept")
public class StationAcceptController {
	
	/** 日志实例 */
	private static Logger logger=LoggerFactory.getLogger(StationAcceptController.class);

	/**工程类型*/
	@Resource
	RelationShipService relationShipService;

	/**部门服务接口*/
	@Resource
	DepartmentService departmentService;

	/** 关联关系服务接口*/
	@Resource
	CorrelationService correlationService;

	/** 工作流服务接口*/
	@Resource
	WorkFlowService workFlowService;

	/** 场站计划服务接口*/
	@Resource
    StationAcceptService stationAcceptService;

	@Resource
	StationDataService stationDataService;

	/**
	 *  打开主页面
	 * @author fuliwei
	 * @createTime 2017年6月22日
	 * @param 
	 * @return
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelView=new ModelAndView();
		modelView.setViewName("station/stationAccept");
		return modelView;
	}

	/**
	 * 打开右侧详述页面
	 * @author fuliwei
	 * @createTime 2017年6月22日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/viewPage")
	public ModelAndView viewPage() {
		ModelAndView modelview = new ModelAndView();
		//查业务部门
		CorrelationReq req=new CorrelationReq();
		req.setCorType(CorrelationTypeEnum.PROJECT_METHOD.getValue());
		req.setCorrelateInfoId(ProjectMethodEnum.STATION_PROJECT.getValue());
		req.setAcceptType("");
		List<Correlation> department=correlationService.findCorType(req);
		modelview.addObject("department",department);//工程组部门

		//查工程类型
		List<Correlation> list=correlationService.findProjType(CorrelationTypeEnum.DEPT_PROJTYPE.getValue(),department);
		modelview.addObject("projLtype", list);						//工程大类
		modelview.addObject("backReason", BackReasonEnum.values());	//退单原因

		//查所有出资方式
		CorrelationReq req1=new CorrelationReq();
		req1.setCorType(CorrelationTypeEnum.PROJTYPE_CONTRIBUTION.getValue());
		req1.setCorrelateInfoId("");
		req1.setAcceptType(AcceptTypeEnum.OUT_PLAN.getValue());
		List<Correlation> contributionMode=correlationService.findCorType(req1);
		modelview.addObject("contributionMode",contributionMode);	//出资方式
		LoginInfo login= SessionUtil.getLoginInfo();
		modelview.addObject("corpName", login.getCorpName());		//所属公司名称
		//立项资料
		List<StationData> stationDatas = stationDataService.findByType(StationDataTypeEnum.ACCEPT.getValue());
		modelview.addObject("acceptDatas", stationDatas);			//立项资料
		modelview.setViewName("station/stationAcceptRight");
		return modelview;
	}

	/**
	 * 弹出搜索
	 * @author cui
	 * @createTime 2017-8-22
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/searchPopPage")
	public ModelAndView projectSearchPopPage() {
		ModelAndView modelview = new ModelAndView();
		modelview.setViewName("station/stationProjectSearchPopPage");
		return modelview;
	}

	/**
	 * 场站立项保存
	 * @author cui
	 * @createTime 2017-8-22
	 * @param project
	 * @return
	 */
	@RequestMapping(value = "/stationAcceptSave")
	@ResponseBody
	public String stationAcceptSave(@RequestBody(required = true) Project project){
		try {
			LoginInfo login=SessionUtil.getLoginInfo();
			String projStatusId=workFlowService.queryProjStatusId(login.getCorpId(), project.getProjectType(),project.getContributionMode(), WorkFlowActionEnum.STATION_ACCEPT.getActionCode(), true);
			project.setProjStatusId(projStatusId);
			return stationAcceptService.acceptSave(project);
		} catch (Exception e) {
			logger.error("场站立项信息保存失败！", e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}

	/**
	 * 工程列表条件查询
	 * @param projectQueryReq
	 * @author cui
	 * @createTime 2017-08-29
	 * @return
	 */
	@RequestMapping(value = "/queryStationProject")
	@ResponseBody
	public Map<String,Object> queryProject(HttpServletRequest request, ProjectQueryReq projectQueryReq){
		try {
			projectQueryReq.setSortInfo(request);
			List<String> projStuList = new ArrayList<String>();
			projStuList.add(ProjStatusEnum.AUDIT_PROCEDURES.getValue());
			projectQueryReq.setProjStuList(projStuList);//工程状态
			return stationAcceptService.queryAcceptProject(projectQueryReq);
		} catch (Exception e) {
			logger.error("工程信息查询失败！", e);
			return null;
		}
	}

	/**
	 * 场站工程详述
	 * @author cui
	 * @createTime 2017-9-1
	 * @param id 工程id
	 * @return Contract
	 */
	@RequestMapping(value="/viewStationProject")
	@ResponseBody
	public StationProceduresDto viewContract(@RequestParam(required=true) String id){
		try {
			StationProceduresDto s = stationAcceptService.viewStationProject(id);
			return s;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("详述查询失败！", e);
		}
		return null;
	}

}
