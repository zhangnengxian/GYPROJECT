package cc.dfsoft.project.biz.base.baseinfo.controller;

import cc.dfsoft.project.biz.base.baseinfo.dto.BankQueryReq;
import cc.dfsoft.project.biz.base.baseinfo.entity.AccountBank;
import cc.dfsoft.project.biz.base.baseinfo.service.BankService;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 开户行
 * @author wangang
 *
 */
@Controller
@RequestMapping(value="/openBank")
public class OpenBankController {
	
	/** 日志实例 */
	private static Logger logger=LoggerFactory.getLogger(OpenBankController.class);
	
	@Resource
	BankService bankService;

	
	/**
	 * 打开主页面
	 * @author wangang
	 *  
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelView=new ModelAndView();
		modelView.setViewName("baseinfo/openBank/openBank");
		return modelView;
	}
	
	/**
	 * @author wangang
	 * 打开右侧页面
	 * @return
	 */
	@RequestMapping(value = "/viewOpenBankPage")
	public ModelAndView viewOpenBankPage() {
		ModelAndView modelView = new ModelAndView();
		modelView.setViewName("baseinfo/openBank/openBankRight");
		return modelView;		
	}
	
	/**
	 * 单位条件查询
	 * @author wangang
	 * @createTime
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/queryOpenBank")
	@ResponseBody
	public Map<String,Object> queryOpenBank(BankQueryReq bankQueryReq){
		try {
			LoginInfo loginInfo = SessionUtil.getLoginInfo();
			if(null!=loginInfo.getCorpId()){
				bankQueryReq.setCorpId(loginInfo.getCorpId());
			}
			Map<String,Object> map=bankService.queryBank(bankQueryReq);
			return map;
		} catch (Exception e) {
			logger.error("开户行信息查询失败！", e);
			return null;
		}
	}
	
	/**
	 * 详述
	 * @author wangang
	 * @createTime 2019-1-2
	 */
	@RequestMapping(value="/viewOpenBank")
	@ResponseBody
	public AccountBank viewOpenBank(@RequestParam(required=true) String id) {
		AccountBank accountBank=bankService.viewOpenBankById(id);
		return accountBank;
	}
	
	/**
	 * 弹出搜索屏
	 * @author wangang
	 * @createTime 2019-1-2
	 * @param  
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/openBankSearchPopPage")
	public ModelAndView openBankSearchPopPage() {
		ModelAndView modelview = new ModelAndView();
		modelview.setViewName("baseinfo/openBank/openBankSearchPopPage");
		return modelview;
	}
	
	/**
	 * 保存开户行信息
	 * @author wangang
	 * @createTime 2019-1-2
	 * @param  
	 * @return 
	 */
	@RequestMapping(value = "/saveOpenBank")
	@ResponseBody
	public String saveOpenBank(@RequestBody(required = true) AccountBank accountBank){
		try {
			return bankService.saveOpenBank(accountBank);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("单位信息保存失败！", e);
		}
		return "false";
	}
	
	/**
	 * 删除开户行信息
	 * @author wangang
	 * @createTime 2019-1-2
	 * 
	 */
	@RequestMapping(value = "/delOpenBank")
	public void delOpenBank(@RequestParam(required=true) String id) {
		try{
			bankService.deleteOpenBank(id);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}

	
	
	
	
	