package cc.dfsoft.project.biz.base.station.controller;

import cc.dfsoft.project.biz.base.baseinfo.dto.CorrelationReq;
import cc.dfsoft.project.biz.base.baseinfo.entity.Correlation;
import cc.dfsoft.project.biz.base.baseinfo.enums.AcceptTypeEnum;
import cc.dfsoft.project.biz.base.baseinfo.enums.BackReasonEnum;
import cc.dfsoft.project.biz.base.baseinfo.enums.CorrelationTypeEnum;
import cc.dfsoft.project.biz.base.baseinfo.enums.ProjectMethodEnum;
import cc.dfsoft.project.biz.base.baseinfo.service.CorrelationService;
import cc.dfsoft.project.biz.base.station.dto.StationProceduresDto;
import cc.dfsoft.project.biz.base.station.entity.StationData;
import cc.dfsoft.project.biz.base.station.enums.StationDataTypeEnum;
import cc.dfsoft.project.biz.base.station.service.StationDataService;
import cc.dfsoft.project.biz.base.station.service.StationProceduresService;
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
import java.util.List;


/**
 * 建审手续
 * @author fuliwei
 *
 */
@Controller
@RequestMapping(value="/stationProcedures")
public class StationProceduresController {

	/** 关联关系服务接口*/
	@Resource
	CorrelationService correlationService;
	@Resource
	StationProceduresService stationProceduresService;
	@Resource
	StationDataService stationDataService;

	/** 日志实例 */
	private static Logger logger=LoggerFactory.getLogger(StationProceduresController.class);

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
		modelView.setViewName("station/stationProcedures");
		return modelView;
	}
	
	/**
	 * 打开右侧详述页面
	 * @author cui
	 * @createTime 2017-8-22
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
		modelview.setViewName("station/stationProceduresRight");
		return modelview;
	}

	/**
	 * 场站建审保存
	 * @author cui
	 * @createTime 2017-8-22
	 * @param stationProceduresDto
	 * @return
	 */
	@RequestMapping(value = "/stationProceduresSave")
	@ResponseBody
	public String stationAcceptSave(@RequestBody(required = true) StationProceduresDto stationProceduresDto){
		try {
			stationProceduresService.stationProceduresSave(stationProceduresDto);
			return Constants.OPERATE_RESULT_SUCCESS;
		} catch (Exception e) {
			logger.error("建审信息保存失败！", e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
}
