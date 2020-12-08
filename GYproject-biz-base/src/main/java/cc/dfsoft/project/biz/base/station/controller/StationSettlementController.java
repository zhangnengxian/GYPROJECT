package cc.dfsoft.project.biz.base.station.controller;

import cc.dfsoft.project.biz.base.baseinfo.dto.CorrelationReq;
import cc.dfsoft.project.biz.base.baseinfo.entity.Correlation;
import cc.dfsoft.project.biz.base.baseinfo.enums.AcceptTypeEnum;
import cc.dfsoft.project.biz.base.baseinfo.enums.BackReasonEnum;
import cc.dfsoft.project.biz.base.baseinfo.enums.CorrelationTypeEnum;
import cc.dfsoft.project.biz.base.baseinfo.enums.ProjectMethodEnum;
import cc.dfsoft.project.biz.base.baseinfo.service.CorrelationService;
import cc.dfsoft.project.biz.base.constructmanage.service.GraphicProgressService;
import cc.dfsoft.project.biz.base.project.dto.ProjectQueryReq;
import cc.dfsoft.project.biz.base.project.enums.ProjStatusEnum;
import cc.dfsoft.project.biz.base.station.dto.StationProceduresDto;
import cc.dfsoft.project.biz.base.station.entity.StationData;
import cc.dfsoft.project.biz.base.station.enums.StationDataTypeEnum;
import cc.dfsoft.project.biz.base.station.service.StationAcceptService;
import cc.dfsoft.project.biz.base.station.service.StationDataService;
import cc.dfsoft.project.biz.base.station.service.StationSettlementService;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.common.constant.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * 工程结算
 * @author fuliwei
 *
 */
@Controller
@RequestMapping(value="/stationSettlement")
public class StationSettlementController {
	/** 日志实例 */
	private static Logger logger=LoggerFactory.getLogger(StationSettlementController.class);
	
	/**形象进度*/
	@Resource
	GraphicProgressService graphicProgressService;
	@Resource
	StationAcceptService stationAcceptService;
	@Resource
	StationSettlementService stationSettlementService;
	/** 关联关系服务接口*/
	@Resource
	CorrelationService correlationService;
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
		modelView.setViewName("station/stationSettlement");
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
		List<StationData> acceptDatas = stationDataService.findByType(StationDataTypeEnum.ACCEPT.getValue());
		modelview.addObject("acceptDatas", acceptDatas);
		//建审资料
		List<StationData> proceduresDatas = stationDataService.findByType(StationDataTypeEnum.PROCEDURES.getValue());
		modelview.addObject("proceduresDatas", proceduresDatas);
		//施工资料
		List<StationData> contructionDatas = stationDataService.findByType(StationDataTypeEnum.CONTRUCTION.getValue());
		modelview.addObject("contructionDatas", contructionDatas);
		//结算资料
		List<StationData> settlementDatas = stationDataService.findByType(StationDataTypeEnum.SETTLEMENT.getValue());
		modelview.addObject("settlementDatas", settlementDatas);
		//决算资料
		List<StationData> finalAccountDatas = stationDataService.findByType(StationDataTypeEnum.FINAL_ACCOUNT.getValue());
		modelview.addObject("finalAccountDatas", finalAccountDatas);
		modelview.setViewName("station/stationSettlementRight");
		return modelview;
	}

	/**
	 * 查询弹窗 by cui-2017-9-5
	 * @return
	 */
	@RequestMapping(value="/searchPopPage")
	public ModelAndView settleSearchPopPage(){
		ModelAndView modelView=new ModelAndView();
		modelView.setViewName("station/settlementProjectSearchPopPage");
		return modelView;
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
			projStuList.add(ProjStatusEnum.GETSTATION_SETTLEMENT.getValue());
			projectQueryReq.setProjStuList(projStuList);//工程状态
			return stationAcceptService.queryAcceptProject(projectQueryReq);
		} catch (Exception e) {
			logger.error("工程信息查询失败！", e);
			return null;
		}
	}

	/**
	 * 场站结算保存
	 * @author cui
	 * @createTime 2017-8-22
	 * @param stationProceduresDto
	 * @return
	 */
	@RequestMapping(value = "/stationSettlementSave")
	@ResponseBody
	public String stationSettlementSave(@RequestBody(required = true) StationProceduresDto stationProceduresDto){
		try {
			stationSettlementService.stationSettlementSave(stationProceduresDto);
			return Constants.OPERATE_RESULT_SUCCESS;
		} catch (Exception e) {
			logger.error("建审信息保存失败！", e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
}
