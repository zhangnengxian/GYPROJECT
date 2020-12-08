package cc.dfsoft.project.biz.base.contract.controller;


import cc.dfsoft.project.biz.base.contract.dao.IncrementDao;
import cc.dfsoft.project.biz.base.contract.dto.IntelligentSupplementReq;
import cc.dfsoft.project.biz.base.contract.entity.IntelligentSupplement;
import cc.dfsoft.project.biz.base.contract.service.IntelligentSupplementService;
import cc.dfsoft.project.biz.base.subpackage.dto.IntelligentMeterContractReq;
import cc.dfsoft.project.biz.base.subpackage.entity.IntelligentMeterContract;
import cc.dfsoft.project.biz.base.subpackage.enums.IntelligentMeterConPushEnum;
import cc.dfsoft.project.biz.base.subpackage.service.IntelligentMeterContractService;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.common.util.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * @ClassDesc: 智能表合并补充协议
 * @Author zhangnx
 * @Date 2019/7/27 19:46
 * Version:1.0
 */
@Controller
@RequestMapping(value="intelligentSupplement")
public class IntelligentSupplementController {
	@Resource
	IntelligentMeterContractService intelligentMeterContractService;
	@Resource
	IntelligentSupplementService intelligentSupplementService;
	@Resource
	IncrementDao incrementDao;


	/**
	 * 打开主页面
	 * @return
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(ModelAndView modelView){
		modelView.setViewName("contract/intelligentSupplementMain");
		return modelView;
	}

	/**
	 * 打开右侧智能表合同详述页面
	 * @return
	 */
	@RequestMapping(value = "/contractViewPage")
	public ModelAndView contractViewPage(ModelAndView modelView) {
		SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd");
		modelView.addObject("changeTime",sdf.format(new Date()));   //系统时间
		modelView.addObject("agent", SessionUtil.getLoginInfo().getStaffName());   //放入登录人信息
		modelView.setViewName("contract/intelligentSupplementApplyRight");
		return modelView;
	}


	/**
	 * @MethodDesc: 智能表合同列表查询
	 * @Author zhangnx
	 * @Date 2019/7/26 16:31
	 */
	@RequestMapping(value = "/queryIntelligentContractList")
	@ResponseBody
	public Map<String, Object> queryIntelligentContractList(HttpServletRequest request, IntelligentMeterContractReq req) {
		try {
			req.setFlag(IntelligentMeterConPushEnum.PUSHED.getValue());//已推送的智能表合同
			req.setSortInfo(request);
			req.setModifyStatus("all");
			Map<String, Object> map = intelligentMeterContractService.queryContract(req);
			return map;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}



	/**
	 * @MethodDesc: 智能表合同详细
	 * @Author zhangnx
	 * @Date 2019/7/26 20:33
	 */
	@RequestMapping(value = "/viewContractDetail")
	@ResponseBody
	public IntelligentMeterContract viewProblemDocumentDetail(@RequestParam(required = true) String id) {
		try {
			IntelligentMeterContract intelligentMeterContract= intelligentMeterContractService.getImContract(id);
			return intelligentMeterContract;
		}catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}



	@RequestMapping(value = "/projectSearchPopPage")
	public ModelAndView projectSearchPopPage() {
		ModelAndView modelview = new ModelAndView();
		modelview.addObject("imcPushEnum", IntelligentMeterConPushEnum.values());
		modelview.setViewName("subcontract/intelligentMeterConSearchPopPage");
		return modelview;
	}























	/**
	 * 打开右侧协议详述页面
	 * @return
	 */
	@RequestMapping(value = "/supplementViewPage")
	public ModelAndView supplementViewPage(ModelAndView modelView) {
		SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd");
		modelView.addObject("sysDate",sdf.format(new Date()));   //系统时间
		modelView.addObject("increment",incrementDao.queryAll());//税率
		modelView.addObject("loginName", SessionUtil.getLoginInfo().getStaffName());			//放入登录人信息
		modelView.setViewName("contract/intelligentSupplementRight");
		return modelView;
	}

	/**
	 * @MethodDesc: 协议列表查询
	 * @Author zhangnx
	 * @Date 2019/7/26 17:08
	 */
	@RequestMapping(value = "/queryIntelligentSupplementList")
	@ResponseBody
	public Map<String, Object> queryIntelligentSupplementList(IntelligentSupplementReq req) {
		try {
			Map<String, Object> map = intelligentSupplementService.queryIntelligentSupplementList(req);
			return map;
		}catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}


	/**
	 * @MethodDesc: 协议详细
	 * @Author zhangnx
	 * @Date 2019/7/26 20:33
	 */
	@RequestMapping(value = "/viewSupplementDetail")
	@ResponseBody
	public Map<String, Object> viewSupplementDetail(@RequestParam(required = true) String id) {
		try {
			Map<String, Object> objectMap = intelligentSupplementService.findById(id);
			return objectMap;
		}catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}


	@RequestMapping(value = "/suppModifyPopPage")
	public ModelAndView suppModifyPopPage(ModelAndView modelView) {
		modelView.setViewName("contract/suppModifyPopPage");
		return modelView;
	}











	/**
	 * @MethodDesc: 协议保存或修改
	 * @Author zhangnx
	 * @Date 2019/1/22 14:18
	 */
	@RequestMapping(value = "/saveIntelligentSupplement")
	@ResponseBody
	public boolean saveOrUpdateProblemDocument(@RequestBody IntelligentSupplement intell) {
		if (StringUtil.isBlank(intell.getIsPrint())) intell.setIsPrint("1");
		try {
			intelligentSupplementService.saveOrUpdateintelligentSupplement(intell);
			return true;
		}catch (Exception e){
			e.printStackTrace();
		}
		return false;
	}




}
