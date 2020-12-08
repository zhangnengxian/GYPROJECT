package cc.dfsoft.project.biz.base.subpackage.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cc.dfsoft.project.biz.base.baseinfo.service.ConstructionUnitService;
import cc.dfsoft.project.biz.base.design.controller.SurveyResultRegisterController;
import cc.dfsoft.project.biz.base.design.enums.UnitTypeEnum;
import cc.dfsoft.project.biz.base.project.dto.ProjectQueryReq;
import cc.dfsoft.project.biz.base.project.enums.ProjStatusEnum;
import cc.dfsoft.project.biz.base.project.service.ProjectService;
import cc.dfsoft.project.biz.base.subpackage.dto.SubContractDTO;
import cc.dfsoft.project.biz.base.subpackage.entity.SubSupplyContract;
import cc.dfsoft.project.biz.base.subpackage.service.SubSupplyContractService;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.common.constant.Constants;

@Controller
@RequestMapping(value="/subAgreement")
public class SubAgreementController {
	/** 日志实例 */
	private static Logger logger=LoggerFactory.getLogger(SurveyResultRegisterController.class);

	/**工程服务接口*/
	@Resource
	ProjectService projectService;
	
	@Resource
	ConstructionUnitService constructionUnitService; 
	
	@Resource
	SubSupplyContractService subSupplyContractService;
	
	/**
	 * 跳转主页面
	 * @return
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelView = new ModelAndView();
		modelView.addObject("cuUnitTypeId", UnitTypeEnum.CONSTRUCTION_UNIT.getValue());//分包单位
		modelView.addObject("deptTypeId", UnitTypeEnum.GAS_COMPANY.getValue());//燃气集团
		modelView.setViewName("subcontract/subAgreement");
		return modelView;
	}
	
	/**
	 * 详述
	 * @return
	 */
	@RequestMapping(value="/viewPage")
	public ModelAndView viewPage(){
		ModelAndView modelview = new ModelAndView();
		modelview.addObject("loginInfo",SessionUtil.getLoginInfo());
		modelview.setViewName("subcontract/subAgreementRight");
		return modelview;
	}
	
	/**
	 * 工程列表条件查询
	 * @param request
	 * @param projectQueryReq
	 * @author ht.hu
	 * @return
	 */
	@RequestMapping(value = "/queryProject")
	@ResponseBody
	public Map<String, Object> queryProject(HttpServletRequest request,ProjectQueryReq projectQueryReq){
			try {
				projectQueryReq.setSortInfo(request);
				/*List<String> projStuList = new ArrayList();
				List<ProjStatusEnum> enums = ProjStatusEnum.getThanValue(ProjStatusEnum.SETTLEMENT_REPORT.getValue(),ProjStatusEnum.CONNECT_CONFIRM.getValue());
				for(ProjStatusEnum projStatusEnum :enums){
					projStuList.add(projStatusEnum.getValue());
				}
				Map<String, Object> map;
				map = projectService.queryProjectByParam(projectQueryReq, projStuList);*/
				String[] projStatus = {ProjStatusEnum.SETTLEMENT_REPORT.getValue(),ProjStatusEnum.SETTLEMENT_REPORT_START.getValue(),ProjStatusEnum.AUDIT_DONE_DISPATCH.getValue(),ProjStatusEnum.REPORT_DONE_CONFIRM.getValue(),ProjStatusEnum.CONNECT_CONFIRM.getValue()};
				Map<String,Object> map = projectService.queryProjectView(projectQueryReq,projStatus);
				return map;
			} catch (ParseException e) {
				logger.error("工程信息查询失败！", e);
				return null;
			}
	}
	
	/**
	 * 右侧详述信息
	 * @author ht.hu
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/viewSubContract")
	@ResponseBody
	public SubContractDTO viewSubContract(@RequestParam(required=true) String id){
		try {
			return constructionUnitService.viewSubAgreementContract(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 保存
	 * @author ht.hu
	 * @param subSupplyContract
	 * @return
	 */
	@RequestMapping(value="/saveSubAgreement")
	@ResponseBody
	public String saveSubAgreement(@RequestBody SubSupplyContract subSupplyContract){
		try {
			subSupplyContractService.saveSubSpplyContract(subSupplyContract);
			return Constants.OPERATE_RESULT_SUCCESS;
		} catch (Exception e) {
			logger.error("分包补充协议签订区信息保存失败！",e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
}
