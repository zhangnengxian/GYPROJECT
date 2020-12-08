package cc.dfsoft.project.biz.base.complete.controller;

import cc.dfsoft.project.biz.base.baseinfo.dao.BusinessPartnersDao;
import cc.dfsoft.project.biz.base.baseinfo.entity.BusinessPartners;
import cc.dfsoft.project.biz.base.complete.dto.JointAcceptanceReq;
import cc.dfsoft.project.biz.base.complete.enums.AcceptanceTypeEnum;
import cc.dfsoft.project.biz.base.complete.service.JointAcceptanceService;
import cc.dfsoft.project.biz.base.contract.enums.ContractIsPrintEnum;
import cc.dfsoft.project.biz.base.project.service.ProjectService;
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
import java.util.Map;


@Controller
@RequestMapping(value="/jointAcceptancePrint")
public class JointAcceptancePrintController {
	
	/** 日志实例 */
	private static Logger logger=LoggerFactory.getLogger(JointAcceptancePrintController.class);
	
	/**工程服务接口*/
	@Resource
	ProjectService projectService;
	
	/*联合验收服务接口*/
	@Resource
	JointAcceptanceService jointAcceptanceService;
	
	@Resource
	BusinessPartnersDao businessPartnersDao;
	/**
	 * 打开主页面
	 * @return
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelView=new ModelAndView();
		modelView.addObject("alreadyPrint", ContractIsPrintEnum.ALREADY_PRINT.getValue());//已打印
		modelView.addObject("haveNotPrint", ContractIsPrintEnum.HAVE_NOT_PRINT.getValue());//未打印
		modelView.addObject("imgUrl",Constants.DISK_PATH+Constants.SIGN_DISK_PATH); 	//img url
		modelView.setViewName("complete/jointAcceptancePrint");
		return modelView;
	}
	
	/**
	 * 列表条件查询
	 * @param request
	 * @param jointAcceptanceReq
	 * @return
	 */
	@RequestMapping(value = "/queryJointAcceptance")
	@ResponseBody
	public Map<String,Object> queryJointAcceptancer(HttpServletRequest request,JointAcceptanceReq jointAcceptanceReq){
		try {
			jointAcceptanceReq.setSortInfo(request);
			jointAcceptanceReq.setAcceptanceType(AcceptanceTypeEnum.JOINT_ACCEPTANCE.getValue());//默认加载一站式验收
			Map<String,Object> map = jointAcceptanceService.queryJointAcceptance(jointAcceptanceReq);
			return map;
		} catch (Exception e) {
			logger.error("工程信息查询失败！", e);
			return null;
		}
	}
	/**
	 * 弹屏查询
	 * @return
	 */
	@RequestMapping(value = "/acceptanceSearchPopPage")
	public ModelAndView projectSearchPopPage() {
		ModelAndView modelview = new ModelAndView();
		//modelview.addObject("projLtype", ProjLtypeEnum.values());
		modelview.addObject("isPrint", ContractIsPrintEnum.values());//是否打印
		modelview.setViewName("complete/acceptanceSearchPop");
		return modelview;
	}
	
	/**
	 * 验收单打印标记
	 * @author cui
	 * @createTime 2017-2-15
	 * @param  jaId
	 * @return String 
	 */
	@RequestMapping(value = "/signJointAcceptancePrint")
	@ResponseBody
	public String signJointAcceptancePrint(@RequestBody(required = true) String jaId){
		try {
			jointAcceptanceService.signJointAcceptancePrint(jaId);
			return Constants.OPERATE_RESULT_SUCCESS;
		} catch (Exception e) {
			logger.error("打印合同标记失败！", e);
			return Constants.OPERATE_RESULT_FAILURE;
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
			key = loginInfo.getCorpId()+"_"+11+"_"+"110704";
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
				key = bpCorpId[0]+"_"+"11"+"_"+"110704";
				  obj = Constants.getSysConfigByKey(key);
				    if(obj != null){
				    	 cptType = obj.toString();
				    }
			}
		}
	
		return cptType;
		
	
	}
}
