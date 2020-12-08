package cc.dfsoft.project.biz.base.annualplan.controller;

import cc.dfsoft.project.biz.base.annualplan.dto.AnnualPlanReq;
import cc.dfsoft.project.biz.base.annualplan.entity.AnnualPlan;
import cc.dfsoft.project.biz.base.annualplan.service.AnnualPlanService;
import cc.dfsoft.project.biz.base.baseinfo.dto.CorrelationReq;
import cc.dfsoft.project.biz.base.baseinfo.entity.Correlation;
import cc.dfsoft.project.biz.base.baseinfo.enums.AcceptTypeEnum;
import cc.dfsoft.project.biz.base.baseinfo.enums.CorrelationTypeEnum;
import cc.dfsoft.project.biz.base.baseinfo.enums.ProjectMethodEnum;
import cc.dfsoft.project.biz.base.baseinfo.service.CorrelationService;
import cc.dfsoft.project.biz.base.project.service.ProjectTypeService;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.util.ExcelXlsxUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 年度计划
 * @author fuliwei
 *
 */
@Controller
@RequestMapping(value="/annualPlan")
public class AnnualPlanController {

	/**
	 * 年度计划服务类
	 */
	@Resource
	AnnualPlanService annualPlanService;

	/** 工程类型接口 */
	@Resource
	ProjectTypeService projectTypeService;

	/** 关联关系服务接口*/
	@Resource
	CorrelationService correlationService;

	/** 日志实例 */
	private static Logger logger = LoggerFactory.getLogger(AnnualPlanController.class);
	
	/**
	 *  打开主页面
	 * @author fuliwei
	 * @createTime 2017年6月25日
	 * @param 
	 * @return
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelView=new ModelAndView();
		modelView.setViewName("annualplan/annualPlan");
		return modelView;
	}
	
	/**
	 * 打开右侧详述页面
	 * @author fuliwei -cui
	 * @createTime 2017年6月25日 2017-8-16
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/viewPage")
	public ModelAndView viewPage() {
		ModelAndView modelview = new ModelAndView();
		//查业务部门
		CorrelationReq req=new CorrelationReq();
		req.setCorType(CorrelationTypeEnum.PROJECT_METHOD.getValue());
		req.setCorrelateInfoId(ProjectMethodEnum.PLAN_PROJECT.getValue());
		req.setAcceptType("");
		
		LoginInfo loginInfo=SessionUtil.getLoginInfo();
		req.setAcceptCorrelatedInfoId(loginInfo.getDeptId());//只用于-立项时设置部门id
		
		List<Correlation> department=correlationService.findCorType(req);
		modelview.addObject("department",department);//工程组部门
		//查工程类型
		List<Correlation> list=correlationService.findProjType(CorrelationTypeEnum.DEPT_PROJTYPE.getValue(),department);
		modelview.addObject("projLtype", list);						//工程大类

		//查所有出资方式
		CorrelationReq req1=new CorrelationReq();
		req1.setCorType(CorrelationTypeEnum.PROJTYPE_CONTRIBUTION.getValue());
		req1.setCorrelateInfoId("");
		req1.setAcceptType(AcceptTypeEnum.INNER_PLAN.getValue());
		List<Correlation> contributionMode=correlationService.findCorType(req1);
		modelview.addObject("contributionMode",contributionMode);	//出资方式

		LoginInfo login= SessionUtil.getLoginInfo();
		modelview.addObject("login", login);		//登录信息
		modelview.setViewName("annualplan/annualPlanRight");
		return modelview;
	}

	/**
	 * @author cui
	 * @createTime 2017-8-8
	 * 查询页面
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/searchPopPage")
	public ModelAndView searchPopPage() {
		ModelAndView modelview = new ModelAndView();
		//查业务部门
		CorrelationReq req=new CorrelationReq();
		req.setCorType(CorrelationTypeEnum.PROJECT_METHOD.getValue());
		req.setCorrelateInfoId(ProjectMethodEnum.PLAN_PROJECT.getValue());
		req.setAcceptType("");
		List<Correlation> department=correlationService.findCorType(req);
		modelview.addObject("department",department);//工程组部门
		//查工程类型
		List<Correlation> list=correlationService.findProjType(CorrelationTypeEnum.DEPT_PROJTYPE.getValue(),department);
		modelview.addObject("projLtype", list);
		modelview.setViewName("annualplan/planSearchPopPage");
		return modelview;
	}

	/**
	 * 年度计划保存
	 * @author cui
	 * @createTime 2017-8-15
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/saveAnnualPlan")
	@ResponseBody
	public String saveAnnualPlan(@RequestBody(required = true) AnnualPlan annualPlan){
		try {
			annualPlanService.saveAnnualPlan(annualPlan);
			return Constants.OPERATE_RESULT_SUCCESS;
		} catch (Exception e) {
			logger.error("年度计划信息保存失败！", e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}

	/**
	 * 年度计划列表查询
	 * @author cui
	 * @createTime 2017-8-7
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/queryAnnualPlan")
	@ResponseBody
	public Map<String,Object> queryAnnualPlan(HttpServletRequest request, AnnualPlanReq annualPlanReq){
		try {
			annualPlanReq.setSortInfo(request);
			return annualPlanService.queryAnnualPlan(annualPlanReq);
		} catch (Exception e) {
			logger.error("年度计划列表查询查询失败！", e);
			return null;
		}
	}
	
	/**
	 * 模板下载
	 * @author fuliwei
	 * @createTime 2017年8月31日
	 * @param 
	 * @return
	 */
	@RequestMapping(value="/exportExcel")
	public void exportExcel(HttpServletResponse response){
		try {
			LoginInfo login=SessionUtil.getLoginInfo();
			String title = login.getCorpName()+"燃气管道建设年度计划";
			String[] excelHeader = {"计划编号","计划名称","项目地点","燃气公司","管径","项目长度(千米)","完成长度(千米)","完成进度","预算价(万元)","已投资(万元)","计划投资(万元)","建设比例"};
			Integer[] headerWidth = {4000,8000,8000,8000,4000,4000,4000,4000,4000,4000,4000,4000};
			Map<String,String> map = new HashMap<String,String>();
			map.put("fileName", "annalPlan");
			map.put("remarkInfoHeight", "0");
			String sheetName="年度计划";
			ExcelXlsxUtil.exportExcel(response, title,sheetName, excelHeader, headerWidth, null, map, null);
		} catch (Exception e) {
			logger.error("模板导出失败",e);
		}
	}
	
	
	/**
	 * 打开文件导入页面
	 * @return
	 */
	@RequestMapping(value = "/importPop")
	public ModelAndView exportPop(String url) {
		ModelAndView modelview = new ModelAndView();
		modelview.addObject("url", url);
		modelview.setViewName("budget/importPop");
		return modelview;
	}
	
	/**
	 * 图纸批量导入
	 * @author fuliwei
	 * @createTime 2016-7-25
	 * @param
	 * @return void
	 */
	@RequestMapping(value = "/importExcel")
	@ResponseBody
	public JSONObject ImportExcel(HttpServletRequest request,String projId,@RequestParam(value = "files[]") MultipartFile files[]) {
		try {
			// {"序号.","计划编号","计划名称","项目地点","燃气公司","管径","项目长度(千米)","完成长度(千米)","完成进度","预算价(万元)","已投资(万元)","计划投资(万元)","建设比例"};
			String[] params = { "planNo", "planName", "planAddr", "affiliatedCompany", "pipeDiameter","pipeLength","finishLength","completionSchedule","investmentScale","alradyInvestment","planInvestment","constructionRatio"};
			JSONArray jsonarr= ExcelXlsxUtil.importExcelJson(files[0], "年度计划", 0, 3, 0, params);			 			 
			 if (null != jsonarr && jsonarr.size() > 0) {
				 annualPlanService.batInsertCostSum(jsonarr);
			}
			String url = request.getRequestURL().toString();
			int i = url.lastIndexOf("/");
			url = url.substring(0, i + 1);
			JSONArray js = new JSONArray();
			JSONObject jso = new JSONObject();
			JSONObject json = new JSONObject();
			jso.put("name", files[0].getOriginalFilename());
			jso.put("size", files[0].getSize());
			jso.put("type", "xlsx/xls");
			jso.put("url", files[0].getOriginalFilename());
			jso.put("thumbnailUrl", files[0].getOriginalFilename());
			jso.put("deleteUrl", url + "deleteImport?fileName="+ files[0].getOriginalFilename());
			jso.put("deleteType", "DELETE");
			js.add(jso);
			json.put("files", js);

			return json;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	/**
	 * 删除年度计划
	 * @author fuliwei
	 * @createTime 2017年10月9日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/deleteAnnualPlan")
	@ResponseBody
	public String deleteAnnualPlan(@RequestBody(required = true) String planId){
		try {
			return annualPlanService.deleteAnnualPlan(planId);
		} catch (Exception e) {
			logger.error("年度计划删除失败！", e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
	
}
