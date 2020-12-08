package cc.dfsoft.project.biz.base.contract.controller;

import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import cc.dfsoft.project.biz.base.contract.dao.IncrementDao;
import cc.dfsoft.project.biz.base.contract.enums.ContractTypeEnum;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.common.dto.JsonDateValueProcessor;
import cc.dfsoft.uexpress.common.util.SessionUtils;
import cc.dfsoft.uexpress.common.util.StringUtil;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.commons.lang3.StringUtils;
import org.bouncycastle.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cc.dfsoft.project.biz.base.contract.dto.SupplementalContractQueryReq;
import cc.dfsoft.project.biz.base.contract.entity.SupplementalContract;
import cc.dfsoft.project.biz.base.contract.service.SupplementalContractService;
import cc.dfsoft.project.biz.base.project.dto.ManageRecordQueryReq;
import cc.dfsoft.project.biz.base.project.entity.ManageRecord;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.project.enums.MrResultEnum;
import cc.dfsoft.project.biz.base.project.service.ManageRecordService;
import cc.dfsoft.project.biz.base.project.service.ProjectService;
import cc.dfsoft.project.biz.ifs.ResultMessage;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.exception.ExpressException;
import cc.dfsoft.project.biz.base.baseinfo.entity.DocType;
import cc.dfsoft.project.biz.base.baseinfo.service.DocTypeService;
import cc.dfsoft.project.biz.base.constructmanage.enums.StepOutWorkflowEnum;
import cc.dfsoft.project.biz.base.contract.dto.SupplementalContractQueryReq;

/**
 * 补充协议修改审核
 * @author wang.hui.jun
 *
 */
@Controller
@RequestMapping(value = "/supplementContractModifyAudit")
public class SupplementContractModifyAuditController {
	/** 日志实例 */
	private static Logger logger=LoggerFactory.getLogger(ContractAuditController.class);
	
	@Resource
	ManageRecordService manageRecordService;
	@Resource
	SupplementalContractService supplementalContractService;
	@Resource
	DocTypeService	docTypeService;
	
	@Resource
	ProjectService projectService;

	@Resource
	IncrementDao incrementDao;
 	/**
	 * 打开主页面
	 */
	@RequestMapping(value = "/main")
	public ModelAndView main(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("stepId", StepOutWorkflowEnum.SUPPLEMENTAL_CONTRACT_AUDIT.getValue());
		modelAndView.addObject("projNo", SessionUtils.getProjNo());
		modelAndView.setViewName("contract/supplementalContractModifyAudit");
		return modelAndView;
		
	}
	/**
	 * 查询待审核协议列表
	 */
	 @RequestMapping(value = "/querySupplementalModify")
	 @ResponseBody
	public Map<String, Object> querySupplementalModify(HttpServletRequest request,SupplementalContractQueryReq suppleConReq){
		 try{
			 suppleConReq.setSortInfo(request);
			 suppleConReq.setTimeLimit(StepOutWorkflowEnum.SUPPLEMENTAL_CONTRACT_MODIFY_AUDIT.getStepDay());//设置限制时间
			 suppleConReq.setStepId(StepOutWorkflowEnum.SUPPLEMENTAL_CONTRACT_MODIFY_AUDIT.getValue());//设置步骤
			 Map<String,Object> map = supplementalContractService.querySupplementalModify(suppleConReq); //查询补充协议修改历史
			 return map;
		 }catch(Exception e){
			logger.error("审核列表查询失败"); 
			return null;
		 }
	 }
	 /**
	  * 弹出搜索屏
	  */
	 @RequestMapping(value= "/searchPopPage")
	 public ModelAndView searchPopPage(){
		 ModelAndView modelAndView = new ModelAndView();
		 modelAndView.setViewName("contract/supplementalContractAuditSearchPage");
		return modelAndView;
		 
	 }
		/**
		* 打开审核屏
		* @return ModelAndView
		*/
		@RequestMapping(value="/auditPage")
		public ModelAndView audit(HttpServletRequest request){
			String corpId = request.getParameter("corpId");
			if (StringUtils.isEmpty(corpId)){//默认为当前用户分公司ID
				LoginInfo loginInfo=SessionUtil.getLoginInfo();
				corpId=loginInfo.getCorpId();
			}
			ModelAndView modelView=new ModelAndView();
			modelView.addObject("currentLevel",request.getParameter("currentLevel"));//当前审核级别
			modelView.addObject("isAudit",request.getParameter("isAudit"));			 //是否审核过
			modelView.addObject("loginInfo",SessionUtil.getLoginInfo());			 //放入登录人信息
			modelView.addObject("auditTime",manageRecordService.getDatabaseDate().getTime());	//当前数据库时间
			modelView.addObject("mrResult",MrResultEnum.values());					 //审核结果
			modelView.addObject("scId",request.getParameter("scId"));				 //合同id
			SupplementalContract supplementContract=supplementalContractService.findByScId(request.getParameter("scId"));
			modelView.addObject("projId",supplementContract.getProjId());
			modelView.addObject("projNo",supplementContract.getProjNo());
			modelView.addObject("corpId",corpId);//分公司ID
			modelView.setViewName("contract/supplementalModifyAuditPage");
			return modelView;
		}
		/**
		 * @author wmy
		 * 协议详述
		 * @return
		 */
		@RequestMapping(value = "/viewPage")
		public ModelAndView viewPage(String scId,String projId, String corpId, String menuId) throws Exception {

			Project project = projectService.queryProjectById(projId);
			//详细页面：工程类型+分公司ID+菜单ID
			String key = project.getProjectType()+"_"+project.getCorpId()+"_"+menuId;
			Object obj = Constants.getSysConfigByKey(key);
			String viewUrl = "";
			if(obj !=null){
				viewUrl = obj.toString();
			}else{
				viewUrl = "supplementalContractRight";
			}

			//每户金额：分公司ID+菜单ID
			String houseAmountKey = project.getCorpId()+"_"+menuId;
			Object houseAmountObj = Constants.getSysConfigByKey(houseAmountKey);

			SupplementalContract supplementalContract = supplementalContractService.searchSupplementalContract(scId);
			JSONObject scJsonStr = new JSONObject();
			if (supplementalContract!=null){
				JsonConfig jsonConfig = new JsonConfig();
				jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());//处理JSONObject序列化后的Date类型
				scJsonStr=JSONObject.fromObject(supplementalContract,jsonConfig);//将java对象转换为json对象
			}

			ModelAndView modelview = new ModelAndView();
			modelview.addObject("resident", ContractTypeEnum.RESIDENT.getValue());	//合同类型-居民户
			modelview.addObject("sysDate",manageRecordService.getDatabaseDate());   //系统时间
			modelview.addObject("stepId",StepOutWorkflowEnum.SUPPLEMENTAL_CONTRACT_AUDIT.getValue());
			modelview.addObject("increment",incrementDao.queryAll());//税率
			modelview.addObject("houseAmount",houseAmountObj);//每户金额
			modelview.addObject("scJsonStr",scJsonStr);//补充协议详细
			modelview.setViewName("contract/"+viewUrl);
			return modelview;
		}
		/**
		 * 协议审核保存
		 * @author wmy
		 * @createTime 2016-07-11
		 * @param manageRecord
		 * @return
		 */
		@RequestMapping(value = "/auditSave")
		@ResponseBody
		public ResultMessage auditSave(@RequestBody(required = true) ManageRecord manageRecord){
			ResultMessage resultMessage = new ResultMessage();
			try {
				//返回值为"true"，则不是审核通过
				String result =  manageRecordService.supplementalContractModifyAuditSave(manageRecord,"","",StepOutWorkflowEnum.SUPPLEMENTAL_CONTRACT_MODIFY_AUDIT.getValue());
				resultMessage.setRet_type("0");
				resultMessage.setRet_message(result);
				return resultMessage;
			} catch (ExpressException e) {
				logger.error("合同审核失败！",e);
				resultMessage.setRet_type("1");
				resultMessage.setRet_message(e.getMessage());
				return resultMessage;
			} catch (Exception e) {
				logger.error("合同审核失败！",e);
				resultMessage.setRet_type("-1");
				resultMessage.setRet_message(Constants.OPERATE_RESULT_FAILURE);
				return resultMessage;
			}
			
		}
		/**
		 * 查询审核历史
		 */
		@RequestMapping(value = "/queryManageRecord")
		@ResponseBody
		public Map<String, Object> queryManageRecord(ManageRecordQueryReq manageRecordQueryReq){
			try {
				manageRecordQueryReq.setStepId(StepOutWorkflowEnum.	SUPPLEMENTAL_CONTRACT_MODIFY_AUDIT.getValue());	//合同审核
				return manageRecordService.queryManageRecord(manageRecordQueryReq);
			} catch (Exception e) {
				logger.error("审核历史查询失败！",e);
				return null;
			}
		}
}
