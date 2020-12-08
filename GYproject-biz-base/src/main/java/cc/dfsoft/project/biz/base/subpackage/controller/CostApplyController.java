package cc.dfsoft.project.biz.base.subpackage.controller;

import cc.dfsoft.project.biz.base.baseinfo.dao.BusinessPartnersDao;
import cc.dfsoft.project.biz.base.baseinfo.entity.BusinessPartners;
import cc.dfsoft.project.biz.base.constructmanage.enums.StepOutWorkflowEnum;
import cc.dfsoft.project.biz.base.plan.entity.ConstructionPlan;
import cc.dfsoft.project.biz.base.plan.service.ConstructionPlanService;
import cc.dfsoft.project.biz.base.project.dto.ManageRecordQueryReq;
import cc.dfsoft.project.biz.base.project.service.ManageRecordService;
import cc.dfsoft.project.biz.base.project.service.OperateRecordService;
import cc.dfsoft.project.biz.base.project.service.ProjectTypeService;
import cc.dfsoft.project.biz.base.settlement.enums.AuditResultEnum;
import cc.dfsoft.project.biz.base.subpackage.dto.PaymentApplyReq;
import cc.dfsoft.project.biz.base.subpackage.entity.FeeApplyList;
import cc.dfsoft.project.biz.base.subpackage.entity.PaymentApply;
import cc.dfsoft.project.biz.base.subpackage.enums.ApplyFeeTypeEnum;
import cc.dfsoft.project.biz.base.subpackage.enums.AuditUnitTypeEnum;
import cc.dfsoft.project.biz.base.subpackage.enums.FeeTypeEnum;
import cc.dfsoft.project.biz.base.subpackage.service.CostApplyService;
import cc.dfsoft.project.biz.base.subpackage.service.FeeApplyListService;
import cc.dfsoft.project.biz.base.subpackage.service.PaymentApplyService;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.biz.sys.dept.enums.DeptDivideEnum;
import cc.dfsoft.uexpress.biz.sys.dept.enums.DeptTypeEnum;
import cc.dfsoft.uexpress.biz.sys.dept.enums.UnitTypeEnum;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.constant.DataFilerSetUpDto;
import cc.dfsoft.uexpress.common.util.ExcelXlsxUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 费用申请
 * @author cui
 *
 */
@Controller
@RequestMapping(value="/costApply")
public class CostApplyController {
	
	/** 日志实例 */
	private static Logger logger = LoggerFactory.getLogger(CostApplyController.class);
	
	/** 费用申请服务接口 */
	@Resource
	CostApplyService costApplyService;
	
	/**费用申请清单*/
	@Resource
	FeeApplyListService feeApplyListService;
	
	/**付款申请*/
	@Resource
	PaymentApplyService paymentApplyService;
	
	/**业务合作伙伴*/
	@Resource
	BusinessPartnersDao businessPartnersDao;
	
	/**计划服务接口*/
	@Resource
	ConstructionPlanService constructionPlanService;
	@Resource
	ProjectTypeService projTypeService;
	@Resource
	ManageRecordService manageRecordService;
	@Resource
	OperateRecordService operateRecordService;
	
	/**
	 * 打开主页面
	 * @author cui
	 * @createTime 2017年12月8日
	 * @param 
	 * @return
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelView=new ModelAndView();
		modelView.addObject("notThrough",Constants.MODULE_CODE_SUBCONTRACT);
		modelView.addObject("stepId",StepOutWorkflowEnum.PAYMENT_AUDIT.getValue());	
		modelView.setViewName("subcontract/costApply");
		return modelView;
	}
	
	/**
	 * 打开右侧页面
	 * @author cui
	 * @createTime 2017年12月8日
	 * @param 
	 * @return
	 */
	@RequestMapping(value="/viewPage")
	public ModelAndView viewPage(){
		ModelAndView modelView=new ModelAndView();
		modelView.addObject("deptType",DeptTypeEnum.SUBCOMPANY.getValue());
		
		modelView.addObject("checkUnit",UnitTypeEnum.DETECTION_UNIT.getValue());			//检测单位
		modelView.addObject("suUnit",UnitTypeEnum.CONSTRUCTION_CONTROL_UNIT.getValue());	//监理单位
		modelView.addObject("designUnit",DeptDivideEnum.DESIGN_INSTITUTE.getValue());		//设计单位
		
		LoginInfo loginInfo=SessionUtil.getLoginInfo();
		if(DeptDivideEnum.DESIGN_INSTITUTE.getValue().equals(loginInfo.getDeptDivide())){
			//设计院登录
			modelView.addObject("loginUnitType",loginInfo.getDeptDivide());//设计单位
		}
		
		BusinessPartners bp =  businessPartnersDao.get(loginInfo.getDeptId());
		if(bp!=null){
			modelView.addObject("loginUnitType",bp.getUnitType());		//监理或检测单位
			loginInfo.setDeptName(bp.getUnitName());					//loginInfo无法获得当前登录人的部门名称
		}
		
		modelView.addObject("feeType",FeeTypeEnum.values());			//费用类型
		modelView.addObject("loginInfo",loginInfo);						//登录人
		modelView.addObject("auditUnit",AuditUnitTypeEnum.values());	//审核部门
		modelView.setViewName("subcontract/costApplyRight");
		return modelView;
	}
	
	/**
	 * 弹出搜索
	 * @author fuliwei
	 * @createTime 2017年12月11日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/projectSearchPopPage")
	public ModelAndView projectSearchPopPage() {
		ModelAndView modelview = new ModelAndView();
		modelview.addObject("auditResultEnum",AuditResultEnum.values());
		modelview.setViewName("subcontract/costApplySearchPopPage");
		return modelview;
	}
	
	/**
	 * 费用申请查询
	 * @author cui
	 * @createTime 2017年12月9日
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/queryPaymentApply")
	@ResponseBody
	public Map<String,Object> queryPaymentApply(HttpServletRequest request, PaymentApplyReq req){
		try {
			req.setSortInfo(request);
			if(StringUtils.isBlank(req.getApplyFeeType())){
				req.setApplyFeeType(ApplyFeeTypeEnum.THIRD_PARTY_FEE.getValue());//默认为第三方的费用
			}
		    LoginInfo loginInfo = SessionUtil.getLoginInfo();
		    List<DataFilerSetUpDto>  designerUnit = Constants.getDataFilterMapByKey(loginInfo.getCorpId()+"_"+loginInfo.getPost()+"_"+req.getMenuId());
		    if(designerUnit != null && designerUnit.size() > 0){   //设计公司传入申请人ID
		    	req.setApplyerId(loginInfo.getStaffId());
		    }
			Map<String,Object> map= paymentApplyService.queryPaymentApply(req);
			return map;
		} catch (Exception e) {
			logger.error("费用申请信息查询失败！", e);
			return null;
		}
	}
	
	/**
	 * 费用清单列表
	 * @author fuliwei
	 * @createTime 2017年12月10日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/queryFeeApplyList")
	@ResponseBody
	public Map<String,Object> queryFeeApplyList(HttpServletRequest request, PaymentApplyReq req){
		try {
			if(StringUtils.isBlank(req.getPaId())){
				req.setPaId("-1");
			}
			req.setSortInfo(request);
			Map<String,Object> map= feeApplyListService.queryFeeApplyList(req);
			return map;
		} catch (Exception e) {
			logger.error("费用清单列表查询失败！", e);
			return null;
		}
	}
	
	/**
	 * 打开页面
	 * @author fuliwei
	 * @createTime 2017年12月11日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/importPop")
	public ModelAndView exportPop(String url) {
		ModelAndView modelview = new ModelAndView();
		modelview.addObject("url", url);
		modelview.setViewName("subcontract/importApplyListPop");
		return modelview;
	}
	
	/**
	 * 模板下载
	 * @author fuliwei
	 * @createTime 2017年12月10日
	 * @param response
	 * @return void
	 */
	@RequestMapping(value="/exportExcel")
	public void exportExcel(HttpServletResponse response){
		try {
			String title = "费用申请清单";
			String[] excelHeader = {"序号","工程编号","工程名称","申请金额","质保金"};
			Integer[] headerWidth = {1500,6000,8000,8000,8000,5000};
			Map<String,String> map = new HashMap<String,String>();
			map.put("fileName", "feeApply");
			map.put("remarkInfoHeight", "0");
			String sheetName="费用申请";
			ExcelXlsxUtil.exportExcel(response, title,sheetName, excelHeader, headerWidth, null, map, null);
		} catch (Exception e) {
			logger.error("模板导出失败",e);
		}
	}
	
	
	/**
	 * 导入工程列表
	 * @author fuliwei
	 * @createTime 2017年12月10日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/importExcel")
	@ResponseBody
	public JSONObject ImportExcel(HttpServletRequest request,PaymentApplyReq req,@RequestParam(value = "files[]") MultipartFile files[]) {
		try {
			// {"序号.","工程编号","工程名称","工程地点","工程规模","申请金额"};
			String[] params = { "sortNo", "projNo",  "projName", "applyAmount","endAmount"};
			JSONArray jsonarr= ExcelXlsxUtil.importExcelJson(files[0], "费用申请", 0, 3, 0, params);			 			 
			String result="";
			if (null != jsonarr && jsonarr.size() > 0) {
				 result=feeApplyListService.batInsertFeeApplyList(jsonarr,req);
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
			json.put("result", result);
			return json;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 查询详述
	 * @author fuliwei
	 * @createTime 2017年12月10日
	 * @param 
	 * @return
	 */
	@RequestMapping(value="/viewPaymentApply")
	@ResponseBody
	public PaymentApply viewPaymentApply(HttpServletRequest request,@RequestParam(required=true) String id){
		PaymentApply pa=paymentApplyService.findById(id);
		return pa;
	}
	
	/**
	 * 保存费用申请
	 * @author fuliwei
	 * @createTime 2017年12月11日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/savePaymentApply")
	@ResponseBody
	public String savePaymentApply(@RequestBody(required = true) PaymentApplyReq paymentApplyReq){
		try {
			paymentApplyService.saveCostPaymentApply(paymentApplyReq);
			return Constants.OPERATE_RESULT_SUCCESS;
		} catch (Exception e) {
			logger.error("费用申请保存失败！", e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}



	/**
	 * @MethodDes: 作废
	 * @author zhangnx
	 * @date 2019/8/9 13:42
	 */
	@RequestMapping(value = "/cancelPaymentApply")
	@ResponseBody
	public String cancelPaymentApply(@RequestBody(required = true) PaymentApply pa){
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		pa.setDeletePer(loginInfo.getStaffName());
		pa.setDeleteDate(new Date());


		try {
			paymentApplyService.saveUpdate(pa);
			return Constants.OPERATE_RESULT_SUCCESS;
		} catch (Exception e) {
			logger.error("费用申请保存失败！", e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}



	/**
	 * 删除记录
	 * @author fuliwei
	 * @createTime 2017年12月11日
	 * @param 
	 * @return
	 */
	@RequestMapping("/deleteByPaId")
	@ResponseBody
	public String deleteByPaId(String falId){
		try{
			feeApplyListService.deleteByPaId(falId);
			return Constants.OPERATE_RESULT_SUCCESS;
		}catch(Exception e){
			logger.error("申请清单记录删除失败！",e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
	
	/**
	 * 推送付款申请
	 * @author fuliwei
	 * @createTime 2017年8月17日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/pushPaymentApply")
	@ResponseBody
	public String pushPaymentApply(@RequestBody(required = true) String paId){
		try {
			paymentApplyService.pushCostApply(paId);
			return Constants.OPERATE_RESULT_SUCCESS;
		} catch (Exception e) {
			logger.error("付款申请保存失败！", e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
	
	
	/**
	 * 弹出工程选择页面
	 * @author fuliwei
	 * @createTime 2018年6月11日
	 * @param 
	 * @return
	 */
	@RequestMapping(value="/payProjectPop")
	public ModelAndView materialList(){
		ModelAndView modelView=new ModelAndView();
		modelView.addObject("projLtype", projTypeService.queryAllList());
		modelView.setViewName("subcontract/payProjectPop");
		return modelView;
	}
	
	
	/**
	 * 查询工程
	 * @author fuliwei
	 * @createTime 2018年6月11日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/queryPayProject")
	@ResponseBody
	public Map<String,Object> queryGasProject(HttpServletRequest request, PaymentApplyReq paymentApplyReq){
		try {
			paymentApplyReq.setSortInfo(request);
			return constructionPlanService.queryPayProject(paymentApplyReq);
		} catch (Exception e) {
			logger.error("待申请付款工程列表查询查询失败！", e);
			return null;
		}
	}
	
	/**
	 * 以excel文件形式导出工程信息
	 * @author wanghuijun
	 * @createTime 2018年10月15日
	 */
	@RequestMapping(value = "/exportExcelProjInfo/{feeType}")
	public void exportExcelProjInfo(HttpServletResponse response,@PathVariable(value = "feeType") String feeType){
	try {
		String title = "工程列表"; //设置标题
		String[] excelHeader = {"工程编号","工程名称","工程规模","工程地点","结算价"};   //设置每一列名字
		String[] propertyNames = {"projNo","projName","projScaleDes","projAddr","endSettlementCost"}; //属性名和列名相对应
		Integer[] headerWidth = {4000,8000,8000,8000,4000};  //设置每列的宽度
		Map<String,String> map = new HashMap<String,String>(); 
		map.put("fileName", "projectInfo");  //设置文件名
		map.put("remarkInfoHeight", "0"); //设置备注列的高度
		String sheetName="工程信息";  //设置工作表名称
		JSONArray jsonArray = new JSONArray();  //创建json数组
		PaymentApplyReq paymentApplyReq = new PaymentApplyReq();  //创建对象
		paymentApplyReq.setFeeType(feeType);  //费用类型
		paymentApplyReq.setStart(0);
		paymentApplyReq.setLength(0);
		paymentApplyReq.setDraw(0);
		Map<String, Object>	projMap = constructionPlanService.queryPayProject(paymentApplyReq);  //查询工程信息
		
		List<ConstructionPlan> projList = (List<ConstructionPlan>)projMap.get("data");  //将map转换为list
		//判空进行遍历projList
		if(projList !=null && projList.size() > 0){
			for (ConstructionPlan constructionplan : projList) {
				  JSONObject jsonObject = new JSONObject(); //创建json对象
				  jsonObject.put("projNo", constructionplan.getProjNo());
				  jsonObject.put("projName", constructionplan.getProjName());
				  jsonObject.put("projScaleDes",constructionplan.getProjScaleDes());
			      jsonObject.put("projAddr", constructionplan.getProjAddr());
			      jsonObject.put("endSettlementCost", constructionplan.getEndSettlementCost());
			      jsonArray.add(jsonObject);  //将json对象装入json数组中
			}
		}
		ExcelXlsxUtil.exportExcel(response, title,sheetName, excelHeader, headerWidth, propertyNames, map, jsonArray);
		
	} catch (Exception e) {
		// TODO: handle exception
		logger.error("模板导出失败",e);
	}
	}
	
	/**
	 * 以excel文件形式导出费用申请记录（根据请款paId）
	 * @author wanghuijun
	 * @createTime 2018年10月15日
	 * @param response
	 */
	@RequestMapping(value = "/exportExcelFeeApply/{paId}")
	public void exportExcelFeeApply (HttpServletResponse response,@PathVariable(value = "paId") String paId){
		try {
//			LoginInfo login=SessionUtil.getLoginInfo();  //获取当前登录人信息
			String title = "费用申请清单"; //设置标题
			String[] excelHeader = {"请款编号","工程编号","工程名称","请款单位","请款人","请款时间","请款金额","质保金"};   //设置每一列名字
			String[] propertyNames = {"applyNo","projNo","projName","requestFoundsUnit","applyer","applyDate","applyAmount","endAmount"}; //属性名和列名相对应
			Integer[] headerWidth = {4000,4000,8000,8000,4000,4000,4000,4000};  //设置每列的宽度
			Map<String,String> map = new HashMap<String,String>(); 
			map.put("fileName", "feeApply");  //设置文件名
			map.put("remarkInfoHeight", "0"); //设置备注列的高度
			String sheetName="费用申请";  //设置工作表名称
			JSONArray jsonArray = new JSONArray();  //创建json数组
			PaymentApplyReq paymentApplyReq = new PaymentApplyReq();  //创建对象
		    paymentApplyReq.setPaId(paId);   //设置paId的值
			Map<String,Object> feeApplyMap= feeApplyListService.queryFeeApplyList(paymentApplyReq);  //查询请款记录
			PaymentApply paymentApply =  paymentApplyService.findById(paId);  //根据ID申请查询记录
            List<FeeApplyList > feeApplyLists = (List<FeeApplyList>)feeApplyMap.get("data"); //转换为list
           if(feeApplyLists!=null && feeApplyLists.size() >0 && paymentApply !=null){  //判空
        	 //遍历feeApplyMap
        	   for (FeeApplyList feeApplyList : feeApplyLists) {
        		   JSONObject jsonObject = new JSONObject(); //创建json对象
        		   jsonObject.put("applyNo", paymentApply.getApplyNo()); //设置请款编号
        		   jsonObject.put("projNo", feeApplyList.getProjNo());  //设置工程编号
        		   jsonObject.put("projName", feeApplyList.getProjName()); //设置工程名称
        		   String util = paymentApply.getRequestFoundsUnit() !=null? paymentApply.getRequestFoundsUnit():paymentApply.getApplyDeptName();
        		   jsonObject.put("requestFoundsUnit", util);  //设置请款单位名称
        		   jsonObject.put("applyer", paymentApply.getApplyer()); //设置申请人
        		   jsonObject.put("applyDate",paymentApply.getApplyDate()); //设置申请时间
        		   jsonObject.put("applyAmount", feeApplyList.getApplyAmount());   //设置申请金额
        		   jsonObject.put("endAmount", feeApplyList.getEndAmount());  //设置质保金
        		   jsonArray.add(jsonObject);  //将json对象装入json数组中
   			}
           }
			ExcelXlsxUtil.exportExcel(response, title,sheetName, excelHeader, headerWidth, propertyNames, map, jsonArray);
		} catch (Exception e) {
			logger.error("模板导出失败",e);
		}
	}


	/**
	 *  @MethodDesc: 审核记录查询
	 * @Author zhangnx
	 * @Date 2019/09/18 18:21
	 */
	@RequestMapping(value = "/queryManageRecord")
	@ResponseBody
	public Map<String,Object> queryManageRecord(ManageRecordQueryReq req){
		try {
			Map<String, Object> stringObjectMap = manageRecordService.queryManageRecord(req);
			return stringObjectMap;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 *  @MethodDesc: 审核记录查询
	 * @Author zhangnx
	 * @Date 2019/09/18 18:21
	 */
	@RequestMapping(value = "/queryTodoer")
	@ResponseBody
	public String queryTodoer(String businessOrderId){
		try {
			String todoer=operateRecordService.queryTodoer(businessOrderId);
			return todoer;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
