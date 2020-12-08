package cc.dfsoft.project.biz.base.complete.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sun.org.apache.xalan.internal.xsltc.compiler.sym;

import cc.dfsoft.project.biz.base.baseinfo.dao.BusinessPartnersDao;
import cc.dfsoft.project.biz.base.baseinfo.entity.BusinessPartners;
import cc.dfsoft.project.biz.base.complete.dto.DivisionalAcceptanceReq;
import cc.dfsoft.project.biz.base.complete.service.DivisionalAcceptanceService;
import cc.dfsoft.project.biz.base.contract.enums.ContractIsPrintEnum;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.common.constant.Constants;


/**
 * 分部验收申请打印controller
 * @author wanghuijun
 * @createTime 2018年9月20日
 */
@Controller
@RequestMapping(value = "/divisionalAcceptanceApplyPrint")
public class DivisionalAcceptanceApplyPrintController {
  
	/** 日志实例 */
	private static Logger logger=LoggerFactory.getLogger(DivisionalAcceptanceApplyPrintController.class);
	
	@Resource
	DivisionalAcceptanceService divisionalAcceptanceService;
	
	@Resource
	BusinessPartnersDao businessPartnersDao;
	/**
	 * 打开主页面
	 * @author wanghuijun
	 * @createTime 2018年9月20日
	 * @return
	 */
	@RequestMapping(value = "/main")
	public ModelAndView main(){
		ModelAndView modelAndView = new  ModelAndView();  
		modelAndView.addObject("alreadyPrint", ContractIsPrintEnum.ALREADY_PRINT.getValue());//已打印
		modelAndView.addObject("haveNotPrint", ContractIsPrintEnum.HAVE_NOT_PRINT.getValue());//未打印
		modelAndView.addObject("imgUrl",Constants.DISK_PATH+Constants.SIGN_DISK_PATH); 	//图片路径
		modelAndView.setViewName("complete/divisionalAcceptanceApplyPrint");
		return modelAndView;
		
	}
	
	/**
	 * 查询分部验收申请列表
	 * @author wanghuijun
	 * @createTime 2018年9月20日
	 * @return
	 */
	@RequestMapping(value = "/queryDivisionalAcceptanceApply")
	@ResponseBody
	public Map<String, Object> queryDivisionalAcceptanceApply(HttpServletRequest request,DivisionalAcceptanceReq req){
		try {
			req.setSortInfo(request);
			Map<String, Object>  map = divisionalAcceptanceService.queryDivisionalAcceptanceApply(req);
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("分部验收申请打印列表查询失败！");
			return null;
		}
		
		
	}
	
	/**
	 * 根据工程类型，菜单id,公司ID,查询分部验收打印报表
	 * @author wanghuijun
	 * @createTime 2018年9月20日
	 * @param menuId
	 * @param projId
	 * @param projectType
	 * @return
	 */
	@RequestMapping(value = "/queryCptType")
	@ResponseBody
	public String queryCptType(String menuId,String corpId,String projectType){
		try {
			
			String key = corpId+"_"+projectType+"_"+menuId;   //拼接key值进行打印报表
			Object obj = Constants.getSysConfigByKey(key);
			String cptType = "undefinde";  
			LoginInfo loginInfo = SessionUtil.getLoginInfo();  //取得当前登录人信息
			if(obj !=null){  //若不为空返回查找到的报表
				cptType = obj.toString();
			}
			if(obj==null){  //若为空则默认加载该公司下的第一种报表
				key = loginInfo.getCorpId()+"_"+11+"_"+"1107201";
			    obj = Constants.getSysConfigByKey(key);
			    if(obj != null){
			    	 cptType = obj.toString();
			    }
			}
			if(obj == null){  //若登录人为第三方人员，默认加载所在第一个公司下的第一个右侧页面
				String detptId = loginInfo.getDeptId();  //得到当前登录人部门ID
				BusinessPartners businessPartners = businessPartnersDao.get(detptId);
				if(businessPartners!=null){
					String[] bpCorpId = businessPartners.getCorpId().split(",");
					key = bpCorpId[0]+"_"+"11"+"_"+"1107201";
					  obj = Constants.getSysConfigByKey(key);
					    if(obj != null){
					    	 cptType = obj.toString();
					    }
				}
			}
			return cptType;
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("分部验收申请报表查询失败!",e);
			return "undefinde";
		}
		
	}
	
	/**
	 * 打开弹窗搜索页面
	 * @author wanghuijun
	 * @createTime 2018年9月21日
	 * @return
	 */
	@RequestMapping(value = "/researchPage")
    public ModelAndView researchPage(){
    	ModelAndView modelAndView = new ModelAndView();
    	modelAndView.addObject("isPrint", ContractIsPrintEnum.values());//是否打印
    	modelAndView.setViewName("complete/researchPage");
    	return modelAndView;
    } 
	
	/**
	 * 分部验收申请列表打印标记
	 * @author wanghuijun
	 * @createTime 2018年9月21日
	 * @param daaId
	 * @return
	 */
	@RequestMapping(value = "/signDivisionalAcceptancePrint")
	@ResponseBody
	public String signDivisionalAcceptancePrint(@RequestParam(required = true) String daaId){
		try {
			divisionalAcceptanceService.signDivisionalAcceptancePrint(daaId);
			return Constants.OPERATE_RESULT_SUCCESS;
		}catch(ClassNotFoundException e){
			logger.error("分部验收申请列表打印标记失败!",e);
			e.printStackTrace();
			return Constants.OPERATE_RESULT_FAILURE;
		}
		catch (Exception e) {
			logger.error("分部验收申请列表打印标记失败!",e);
			e.printStackTrace();
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
}
