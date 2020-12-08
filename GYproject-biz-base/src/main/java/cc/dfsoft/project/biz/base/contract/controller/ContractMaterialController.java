package cc.dfsoft.project.biz.base.contract.controller;

import cc.dfsoft.project.biz.base.baseinfo.service.CustomerService;
import cc.dfsoft.project.biz.base.charge.enums.ARFlagEnum;
import cc.dfsoft.project.biz.base.charge.enums.ARStatusEnum;
import cc.dfsoft.project.biz.base.charge.service.AccrualsRecordService;
import cc.dfsoft.project.biz.base.contract.dao.IncrementDao;
import cc.dfsoft.project.biz.base.contract.dto.ContractQueryReq;
import cc.dfsoft.project.biz.base.contract.entity.Contract;
import cc.dfsoft.project.biz.base.contract.entity.PayType;
import cc.dfsoft.project.biz.base.contract.enums.ContractIsPassEnum;
import cc.dfsoft.project.biz.base.contract.enums.ContractMethodEnum;
import cc.dfsoft.project.biz.base.contract.enums.ContractTypeEnum;
import cc.dfsoft.project.biz.base.contract.enums.MaterialIsPassRegisterEnum;
import cc.dfsoft.project.biz.base.contract.service.ContractService;
import cc.dfsoft.project.biz.base.contract.service.PayTypeService;
import cc.dfsoft.project.biz.base.contract.service.impl.ContractServiceImpl;
import cc.dfsoft.project.biz.base.project.dao.ProjectDao;
import cc.dfsoft.project.biz.base.project.dto.ProjectQueryReq;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.project.enums.CollectionTypeEnum;
import cc.dfsoft.project.biz.base.project.enums.ProjLtypeEnum;
import cc.dfsoft.project.biz.base.project.enums.ProjStatusEnum;
import cc.dfsoft.project.biz.base.project.enums.StepEnum;
import cc.dfsoft.project.biz.base.project.service.ProjectService;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.biz.sys.dept.entity.Department;
import cc.dfsoft.uexpress.biz.sys.dept.enums.DeptDivideEnum;
import cc.dfsoft.uexpress.biz.sys.dept.service.DepartmentService;
import cc.dfsoft.uexpress.common.constant.Constants;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import java.util.List;
import java.util.Map;
/**
 * 施工合同
 * @author wmy
 *
 */
@Controller
@RequestMapping(value="/contractMaterial")
public class ContractMaterialController {
	
	/** 日志实例 */
	private static Logger logger=LoggerFactory.getLogger(ContractMaterialController.class);
	
	/**工程服务接口*/
	@Resource
	ProjectService projectService;
	
	@Resource
	ContractService contractService;
	
	@Resource
	AccrualsRecordService accrualsRecordService;
	
	@Resource
	CustomerService customerService;
	
	@Resource
	PayTypeService payTypeService;

	@Resource
	ProjectDao projectDao;
	
	@Resource
	DepartmentService departmentService;
	
	@Resource
	IncrementDao incrementDao;
	/**
	 * 打开主页面
	 * @return
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelView=new ModelAndView();
		modelView.setViewName("contract/contractMaterial");
		return modelView;
	}
	
	/**
	 * 打开右侧详述页面
	 * @return
	 */
	@RequestMapping(value = "/viewPage")
	public ModelAndView viewPage() {
		ModelAndView modelview = new ModelAndView();
		modelview.addObject("customerServiceSenter", DeptDivideEnum.CUSTOMER_SERVICE_CENTER.getValue());//客服中心
		modelview.addObject("modificationGroup", DeptDivideEnum.MODIFICATION_GROUP.getValue());//改管组
		modelview.addObject("payType", payTypeService.findByContractType(null,null));//付款方式
		modelview.addObject("contractMethod",ContractMethodEnum.values());//承包方式
		modelview.addObject("contractType",ContractTypeEnum.values());//合同类型
		modelview.addObject("loginInfo",SessionUtil.getLoginInfo());//放入登录人信息
		modelview.addObject("sysDate",projectDao.getDatabaseDate());//系统时间
		modelview.addObject("increment",incrementDao.queryAll());//税率
		modelview.setViewName("contract/contractMaterialRight");
		return modelview;
		
	}
	
	/**
	 * 弹出搜索屏
	 * @author fuliwei
	 * @createTime 2016-6-28
	 * @param  
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/constractSearchPopPage")
	public ModelAndView projectSearchPopPage() {
		ModelAndView modelview = new ModelAndView();
		//modelview.addObject("projLtype", ProjLtypeEnum.values());
		modelview.addObject("materialIsRegister",MaterialIsPassRegisterEnum.values());//待收、已收
		modelview.setViewName("contract/constractMaterialSearchPopPage");
		return modelview;
	}
	
	/**
	 * 安装合同列表查询
	 * @author fuliwei
	 * @createTime
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/queryProject")
	@ResponseBody
	public Map<String,Object> queryProject(HttpServletRequest request,ContractQueryReq contractQueryReq){
		try {
			contractQueryReq.setSortInfo(request);
			contractQueryReq.setIsPass(ContractIsPassEnum.ALREADY_PASS.getValue());
			Map<String,Object> map=contractService.queryPassContract(contractQueryReq);
			return map;
		} catch (Exception e) {
			logger.error("工程信息查询失败！", e);
			return null;
		}
	}
	
	/**
	 * 详述
	 * @author fuliwei
	 * @createTime 2016-6-28
	 * @param id 工程id
	 * @return Project
	 */
	@RequestMapping(value="/viewContract")
	@ResponseBody
	public Contract viewContract(@RequestParam(required=true) String id){
		Project pro=projectService.viewProject(id);
		Contract contract = contractService.findByProjId(id);
		contract.setProjectTypeDes(pro.getProjectTypeDes());//工程类型
		contract.setContributionModeDes(pro.getContributionModeDes());//出资方式
		contract.setDeptName(pro.getDeptName());//业务部门
		contract.setSurveyer(pro.getSurveyer());//勘察员
		//LoginInfo loginInfo = SessionUtil.getLoginInfo();
		//contract.setConAgent(loginInfo.getStaffName());
		Department dept = departmentService.queryDepartment(pro.getDeptId());
		if(null!=dept){
			contract.setDeptDivide(dept.getDeptDivide());
		}
		return contract;
	}
	
	/**
	 * 保存合同的物资登记
	 * @author fuliwei
	 * @createTime 2016-6-28
	 * @param  contract 合同
	 * @return String 
	 */
	@RequestMapping(value = "/saveContract")
	@ResponseBody
	public String saveContract(@RequestBody(required = true) Contract contract){
		try {
			return contractService.saveMaterialContract(contract);
		} catch (Exception e) {
			logger.error("合同签订区信息保存失败！", e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
}
