package cc.dfsoft.project.biz.base.contract.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fr.stable.plugin.event.PluginChangeListenerPriorityConstants;

import cc.dfsoft.project.biz.base.baseinfo.dao.BusinessPartnersDao;
import cc.dfsoft.project.biz.base.baseinfo.entity.BusinessPartners;
import cc.dfsoft.project.biz.base.complete.controller.JointAcceptancePrintController;
import cc.dfsoft.project.biz.base.complete.enums.AcceptanceTypeEnum;
import cc.dfsoft.project.biz.base.contract.dto.ContractReviewDto;
import cc.dfsoft.project.biz.base.contract.entity.ContractReview;
import cc.dfsoft.project.biz.base.contract.enums.ContractIsPrintEnum;
import cc.dfsoft.project.biz.base.contract.service.ContractReviewService;
import cc.dfsoft.project.biz.ifs.projectQuery.dto.ProjectDto;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.common.constant.Constants;

/**
 * 合同评审打印controller
 * @author wang.hui.jun
 * 2019年7月20日
 *
 */
@Controller
@RequestMapping(value = "/contractReviewPrint")
public class ContractReviewPrintCotroller {

	/** 日志实例 */
	private static Logger logger=LoggerFactory.getLogger(ContractReviewPrintCotroller.class);
	
	@Autowired
	ContractReviewService contractReviewService;
	
	@Autowired
	BusinessPartnersDao businessPartnersDao;
	/**
	 * 打开主页面
	 * @return
	 */
	@RequestMapping(value = "/main")
	public ModelAndView main(){
		ModelAndView modelView=new ModelAndView();
		modelView.addObject("alreadyPrint", ContractIsPrintEnum.ALREADY_PRINT.getValue());//已打印
		modelView.addObject("haveNotPrint", ContractIsPrintEnum.HAVE_NOT_PRINT.getValue());//未打印
		modelView.addObject("imgUrl",Constants.DISK_PATH+Constants.SIGN_DISK_PATH); 	//img url
		modelView.setViewName("contract/contractReviewPrint");
		return modelView;
		
	}
	
	
	/**
	 * 查询合同评审记录
	 * @param httpRequest
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/queryContractReview")
	@ResponseBody
	public Map<String, Object> queryContractReview(HttpServletRequest httpRequest,ContractReviewDto contractReviewReq){
		try {
			 contractReviewReq.setSortInfo(httpRequest);
			Map<String,Object> map = contractReviewService.queryContractReview(contractReviewReq);
			return map;
		} catch (Exception e) {
			logger.error("合同评审查询失败！", e);
			return null;  //返回
		}
		
	}
	
	/**
	 * 根据公司ID,工程类型，菜单ID查找cpt类型
	 * 参数不是必须的
	 * @author wanghuijun
	 * @createTime 2018年9月7日
	 * @param corpId
	 * @param projectType
	 * @param menuId
	 * @return
	 */
	@RequestMapping(value="/queryCptType")
	@ResponseBody
	public String queryCptType (String corpId,String projectType,String menuId){
		String key = corpId+"_"+projectType+"_"+menuId;   //拼接key值进行打印报表
		Object obj = Constants.getSysConfigByKey(key);
		String cptType = "";  
		LoginInfo loginInfo = SessionUtil.getLoginInfo();  //取得当前登录人信息
		if(obj !=null){  //若不为空返回查找到的报表
			cptType = obj.toString();
		}
		if(obj==null){  //若为空则默认加载该公司下的第一种报表
			key = loginInfo.getCorpId()+"_"+11+"_"+"1104041";
		    obj = Constants.getSysConfigByKey(key);
		    if(obj != null){
		    	 cptType = obj.toString();
		    }
		}
		if(obj == null){  //若登录人为第三方人员，默认加载所在第一个公司下的第一个报表
			String detptId = loginInfo.getDeptId(); 
			BusinessPartners businessPartners = businessPartnersDao.get(detptId);
			if(businessPartners!=null){
				String[] bpCorpId = businessPartners.getCorpId().split(",");
				key = bpCorpId[0]+"_"+"11"+"_"+"1104041";
				  obj = Constants.getSysConfigByKey(key);
				    if(obj != null){
				    	 cptType = obj.toString();
				    }
			}
		}
	
		return cptType;
		
	
	}
	
	/**
	 * 合同评审弹窗页面
	 * @return
	 */
	@RequestMapping(value = "/contractReviewSearchPopPage")
	public ModelAndView contractReviewSearchPopPage(){
		ModelAndView modelview = new ModelAndView();
		modelview.setViewName("contract/contractReviewSearchPop");
		return modelview;
	}
	
	
	/**
	 * 标记记录为已经打印状态
	 * @return
	 */
	@RequestMapping(value = "/signPrint")
	@ResponseBody
	public String signPrint(@RequestBody(required = true)String crId){
		try {
			contractReviewService.signPrint(crId);
			return Constants.OPERATE_RESULT_SUCCESS;//返回成功标识符
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("合同评审打印标记失败！", e);
			return Constants.OPERATE_RESULT_FAILURE; //返回操作失败标识符
		}
		
	}
}
