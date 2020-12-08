package cc.dfsoft.project.biz.ifs.finance.service.impl;

import cc.dfsoft.project.biz.base.baseinfo.dao.CorrelationDao;
import cc.dfsoft.project.biz.base.baseinfo.dao.CustomerDao;
import cc.dfsoft.project.biz.base.baseinfo.dto.CorrelationReq;
import cc.dfsoft.project.biz.base.baseinfo.dto.IncrementReg;
import cc.dfsoft.project.biz.base.baseinfo.entity.BusinessPartners;
import cc.dfsoft.project.biz.base.baseinfo.entity.Correlation;
import cc.dfsoft.project.biz.base.baseinfo.entity.Customer;
import cc.dfsoft.project.biz.base.baseinfo.service.BusinessPartnersService;
import cc.dfsoft.project.biz.base.baseinfo.service.CustomerService;
import cc.dfsoft.project.biz.base.charge.entity.CashFlow;
import cc.dfsoft.project.biz.base.charge.enums.ArContractTypeEnum;
import cc.dfsoft.project.biz.base.charge.service.ChargeService;
import cc.dfsoft.project.biz.base.complete.dao.IgniteDao;
import cc.dfsoft.project.biz.base.complete.dao.JointAcceptanceDao;
import cc.dfsoft.project.biz.base.complete.entity.Ignite;
import cc.dfsoft.project.biz.base.complete.entity.JointAcceptance;
import cc.dfsoft.project.biz.base.constructmanage.dao.BusinessCommunicationDao;
import cc.dfsoft.project.biz.base.constructmanage.dao.CompleteReportDao;
import cc.dfsoft.project.biz.base.constructmanage.dao.WorkReportDao;
import cc.dfsoft.project.biz.base.constructmanage.entity.BusinessCommunication;
import cc.dfsoft.project.biz.base.constructmanage.entity.CompleteReport;
import cc.dfsoft.project.biz.base.constructmanage.entity.WorkReport;
import cc.dfsoft.project.biz.base.contract.dao.ContractDao;
import cc.dfsoft.project.biz.base.contract.dao.IncrementDao;
import cc.dfsoft.project.biz.base.contract.dao.SupplementalContractDao;
import cc.dfsoft.project.biz.base.contract.dto.SupplementalContractQueryReq;
import cc.dfsoft.project.biz.base.contract.entity.Contract;
import cc.dfsoft.project.biz.base.contract.entity.Increment;
import cc.dfsoft.project.biz.base.contract.entity.SupplementalContract;
import cc.dfsoft.project.biz.base.contract.enums.IsIntelligentConPayEnum;
import cc.dfsoft.project.biz.base.design.dao.DesignInfoDao;
import cc.dfsoft.project.biz.base.design.enums.UnitTypeEnum;
import cc.dfsoft.project.biz.base.plan.dao.ConstructionPlanDao;
import cc.dfsoft.project.biz.base.plan.entity.ConstructionPlan;
import cc.dfsoft.project.biz.base.project.dao.ContributionModeDao;
import cc.dfsoft.project.biz.base.project.dao.ProjectDao;
import cc.dfsoft.project.biz.base.project.dao.ScaleDetailDao;
import cc.dfsoft.project.biz.base.project.entity.ContributionMode;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.project.entity.ProjectType;
import cc.dfsoft.project.biz.base.project.enums.CollectionTypeEnum;
import cc.dfsoft.project.biz.base.project.enums.ProjStatusEnum;
import cc.dfsoft.project.biz.base.settlement.dao.SettlementDeclarationDao;
import cc.dfsoft.project.biz.base.settlement.entity.SettlementDeclaration;
import cc.dfsoft.project.biz.base.subpackage.dao.IntelligentMeterContractDao;
import cc.dfsoft.project.biz.base.subpackage.dao.SubContractDao;
import cc.dfsoft.project.biz.base.subpackage.dao.SubContractIntelligentDao;
import cc.dfsoft.project.biz.base.subpackage.dao.SubSupplyContractDao;
import cc.dfsoft.project.biz.base.subpackage.entity.IntelligentMeterContract;
import cc.dfsoft.project.biz.base.subpackage.entity.SubContract;
import cc.dfsoft.project.biz.base.subpackage.entity.SubContractIntelligent;
import cc.dfsoft.project.biz.ifs.ResultMessage;
import cc.dfsoft.project.biz.ifs.finance.dto.*;
import cc.dfsoft.project.biz.ifs.finance.enums.FinanceContractTypeEnum;
import cc.dfsoft.project.biz.ifs.finance.enums.IncreamentEnum;
import cc.dfsoft.project.biz.ifs.finance.enums.ResultTypeEnum;
import cc.dfsoft.project.biz.ifs.finance.service.FinanceService;
import cc.dfsoft.project.biz.ifs.finance.service.IFinanceInfoService;
import cc.dfsoft.project.biz.ifs.log.dao.WebserviceLogDao;
import cc.dfsoft.project.biz.ifs.log.entity.WebserviceLog;
import cc.dfsoft.project.biz.ifs.log.service.WebserviceLogService;
import cc.dfsoft.uexpress.biz.sys.dept.dao.DepartmentDao;
import cc.dfsoft.uexpress.biz.sys.dept.entity.Department;
import cc.dfsoft.uexpress.biz.sys.dept.entity.Staff;
import cc.dfsoft.uexpress.biz.sys.dept.service.StaffService;
import cc.dfsoft.uexpress.common.util.StringUtil;
import nc.bd.itf.customer.service.BusinessException;
import nc.bd.itf.customer.service.IContractItfLocator;
import nc.bd.itf.customer.service.IContractItfPortType;
import nc.bd.itf.payBill.service.IPayBillLocator;
import nc.bd.itf.payBill.service.IPayBillPortType;
import nc.bd.itf.receiptBill.service.IReceiptBillLocator;
import nc.bd.itf.receiptBill.service.IReceiptBillPortType;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.jws.WebParam;
import javax.xml.rpc.ServiceException;
import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
/**
 * 
 * 描述:调用用友财务接口，组装参数
 * @author liaoyq
 * @createTime 2017年11月15日
 */
@Service
public class FinanceInfoServiceImpl implements IFinanceInfoService{
	//自定义级别标签
	public static Logger logger=LoggerFactory.getLogger("interfaceinfo");
	
	private String operateType;
	
	@Resource
	ProjectDao projectDao;
	
	@Resource
	ContractDao contractDao;
	
	@Resource
	CustomerDao customerDao;
	
	@Resource
	ScaleDetailDao scaleDetailDao;
	
	@Resource
	SubContractDao subContractDao;
	
	@Resource
	SupplementalContractDao supplementalContractDao;
	
	@Resource
	SubSupplyContractDao subSupplyContractDao;
	
	@Resource
	SettlementDeclarationDao settlementDeclarationDao;
	@Resource
	FinanceService financeService;
	
	/**收付接口接口*/
	@Resource
	ChargeService chargeService;
	
	@Resource
	SubContractIntelligentDao scIntelligentDao;
	@Resource
	ConstructionPlanDao constructionPlanDao;
	
	@Resource
	DepartmentDao departmentDao;
	
	@Resource
	IntelligentMeterContractDao intelligentMeterContractDao;
	
	@Resource
	SubContractIntelligentDao subConIntelligentDao;
	
	@Resource
	StaffService staffService;
	
	@Resource
	CorrelationDao correlationDao;
	
	@Resource
	CustomerService customerService;
	
	@Resource
	WorkReportDao workReportDao;
	
	@Resource
	BusinessPartnersService businessPartnersService;
	
	@Resource
	JointAcceptanceDao jointAcceptanceDao;
	
	@Resource
	CompleteReportDao completeReportDao;
	
	@Resource
	IncrementDao incrementDao;
	@Resource
	IgniteDao igniteDao;
	@Resource
	DesignInfoDao designInfoDao;
	@Resource
	ContributionModeDao contributionModeDao;
	@Resource
	BusinessCommunicationDao businessCommunicationDao;
	@Resource
	WebserviceLogService webserviceLogService;
	@Resource
	WebserviceLogDao webserviceLogDao;
	
	public String sayHello(@WebParam(name="userName")String username)
	{
		return "hello " + username;
	}
	
	
	/**
	 * 同步工程信息的客户端实现功能
	 * @param proID  工程ID
	 * @param opType 操作类型-NC接口匹配
	 * @param contractType 合同类型
	 * @param isIntelligent  智能表标记
	 * @return
	 */
	public String synProjectInfoClient(String proID,String opType,String contractType,String isIntelligent) throws ParseException
	{
		//合同税率
		String strIncrement = "0";
		
		//初始化信息同步的dto
		ProjectWholeInoDTO projectWholeInfoDto = new ProjectWholeInoDTO();
		
		//操作类型
		projectWholeInfoDto.setOperate_type(opType);
		operateType = opType;
		//更新方式-不需要
		projectWholeInfoDto.setUpdate_type("");
				
		//获取工程对象
		Project pro = projectDao.get(proID);
		System.err.println(pro+"proj");
		
		ProjectInfoDTO projectDTO = new ProjectInfoDTO();
		
		//项目类型-编码
		projectDTO.setProject_type(this.getProjectTypeCode(pro,isIntelligent));
		//projectDTO.setProject_type("01");
		
		//出资方式-编码
		projectDTO.setPay_model(this.getProjectCMode(pro.getContributionMode()));
		
		//项目地址
		projectDTO.setPrj_adds(pro.getProjAddr());
		
		//项目状态
		projectDTO.setPro_status(pro.getProjStatusDes());
		
		//地区编码-无
		projectDTO.setArea_code(StringUtil.isNotBlank(pro.getArea())?pro.getArea():"");
		
		//分公司编号
		Department dept = departmentDao.get(pro.getCorpId());
		projectDTO.setCompany_code(dept!=null?dept.getDeptOutCode():"");
		
		//项目开始时间-受理时间
		projectDTO.setBegin_date(pro.getAcceptDate().toString());
		
		//竣工日期
		if(pro.getCompletedDate()!=null)
			projectDTO.setEnd_date(pro.getCompletedDate().toString());
		
		//客户信息
		Customer customer = customerDao.get(StringUtil.isNotBlank(pro.getCustId())?pro.getCustId():"");
		//客户名称
		//不存在客户,则默认取燃气公司为客户
		Department department = departmentDao.get(pro.getCorpId());
		projectDTO.setCust_name((customer!=null && StringUtil.isNotBlank(customer.getCustName()))?customer.getCustName():department.getDeptName());
							
		//客户编码
		//projectDTO.setCust_code(pro.getCustId());
		String custCode = this.getUnitCode(pro.getCustId(), UnitTypeEnum.CUSTOMER_UNIT.getValue());
		projectDTO.setCust_code(StringUtil.isNotBlank(custCode)?custCode:department.getDeptOutCode());
		
		//客户基本分类（1集团内 2集团外 3个人 4其他）-无
		projectDTO.setCust_class("");
		//法人名称--无
		projectDTO.setLegal(StringUtil.isNotBlank(pro.getCustContact())?pro.getCustContact():"");
		projectDTO.setTel(StringUtil.isNotBlank(pro.getCustPhone())?pro.getCustPhone():"");
			
		//企业地址
		projectDTO.setCompany_adds((customer!=null&&StringUtil.isNotBlank(customer.getUnitAddress()))?customer.getUnitAddress():"");
				
		//银行账号
		projectDTO.setAccount_no((customer!=null&&StringUtil.isNotBlank(customer.getAccount()))?customer.getAccount():"");
		//账号名称-无
		projectDTO.setAccount_name("");
		//账户性质(0公司 1个人)-无
		projectDTO.setAccount_xz("");
		//银行类别-无
		projectDTO.setBank_lb("");
		//开户银行
		projectDTO.setAccount_bank("");
		
		
		//定义安装合同
		ConstractInfoDTO constractInfoDTO = new ConstractInfoDTO();
		//定义分包合同
		ConsConstractInfoDTO consConstractInfoDTO = new ConsConstractInfoDTO();
		//查询结算表的信息
		SettleInfoDTO settleInfoDTO = new SettleInfoDTO();
		
		if(isIntelligent.equals(IsIntelligentConPayEnum.INTELLIGENT_CON_PAY.getValue())){
			//智能表安装合同
			constractInfoDTO = queryIntelligentContractInfo(pro);
			//智能表分包合同
			consConstractInfoDTO = querySubIntelligentContract(pro);
		}else{
			if(StringUtil.isNotBlank(contractType) && contractType.equals(FinanceContractTypeEnum.SUPPLE_CONTRACT.getValue())){
				//安装合同-补充协议
				constractInfoDTO = querySupplementContract(pro);
			}else{
				//安装合同
				constractInfoDTO = queryContractInfo(pro);
				//分包合同
				consConstractInfoDTO = querySubContract(proID);
				//结算信息
				settleInfoDTO = querySettlement(pro);
			}
			
		}
		
		//安装合同结果集初始化
		if(constractInfoDTO != null && StringUtil.isNotBlank(constractInfoDTO.getContract_code())){
			projectWholeInfoDto.setConstractInfo(constractInfoDTO);
			//项目编号
			projectDTO.setPro_no(constractInfoDTO.getContract_code());
			//项目名称
			if(isIntelligent.equals(IsIntelligentConPayEnum.INTELLIGENT_CON_PAY.getValue()) || contractType.equals(FinanceContractTypeEnum.SUPPLE_CONTRACT.getValue())){
				//智能表合同、补充协议工程名称拼接工程编号
				projectDTO.setPro_name(pro.getProjName()+"("+pro.getProjNo()+")");
			}else{
				projectDTO.setPro_name(pro.getProjName());
			}
		}else{
			projectDTO.setPro_no(pro.getProjNo());
			projectDTO.setPro_name(pro.getProjName());
		}
		//分包合同初始化
		if(consConstractInfoDTO != null)
			projectWholeInfoDto.setCons_constractInfo(consConstractInfoDTO);
		
		//结算信息初始化
		if(settleInfoDTO != null)
			projectWholeInfoDto.setSettleInfo(settleInfoDTO);
		
		//设计单位
		Department duDept = departmentDao.get("1112");//设计院ID
		projectDTO.setDesign_unit(duDept!=null?pro.getDuName():"");
		projectDTO.setDesign_code(duDept!=null?duDept.getDeptOutCode():"");
		//工程启用状态-
		projectDTO.setPro_enabled(true);
		if(ProjStatusEnum.TERMINATION.getValue().equals(pro.getProjStatusId())){
			//项目终止
			projectDTO.setPro_enabled(false);
		}
		
		//组装工程信息
		projectWholeInfoDto.setProjectInfo(projectDTO);
		
		String result = this.webserviceCall(projectWholeInfoDto);
		//接口日志类
		WebserviceLog webserviceLog = new WebserviceLog();
		JSONObject jsonbean = JSONObject.fromObject(result);
		//返回信息
		ResultMessage resultMessage = (ResultMessage) JSONObject.toBean(jsonbean,ResultMessage.class);
		webserviceLog.setOperateType(operateType);
		webserviceLog.setLogParams(JSONObject.fromObject(projectWholeInfoDto).toString());
		webserviceLog.setServiceType(operateType);//工程信息同步
		webserviceLog.setResultType(resultMessage.getRet_type());
		webserviceLog.setResultMsg(resultMessage.getRet_message());
		webserviceLog.setProjId(pro.getProjId());
		webserviceLog.setProjNo(pro.getProjNo());
		//调用接口传递数据
		IContractItfLocator customerInfo = new IContractItfLocator();
		webserviceLog.setServicePath(customerInfo.getIContractItfSOAP11port_httpAddress());
		saveWebserviceLog(webserviceLog);
		
		return result;
	}
	
	/**
	 * 组装智能表分包合同信息
	 * @param pro
	 * @return
	 */
	private ConsConstractInfoDTO querySubIntelligentContract(Project pro) {
		ConsConstractInfoDTO consConstractInfoDTO = new ConsConstractInfoDTO();
		SubContractIntelligent subContractIntelligent = subConIntelligentDao.findContractByprojId(pro.getProjId());
		
		if(subContractIntelligent!=null){
			//合同协议号
			consConstractInfoDTO.setWork_code(subContractIntelligent.getItScNo());
			
			//智能表分包单位名称--智能表工程的结算如何确定，todo：
			consConstractInfoDTO.setWork_unit(subContractIntelligent.getsPartyName());
			consConstractInfoDTO.setWork_unit_code(getUnitCode(subContractIntelligent.getsPartyId(), UnitTypeEnum.INTELLIGENT_METER_UNIT.getValue()));
			
			//智能表施工合同金额
			consConstractInfoDTO.setWork_money(subContractIntelligent.getTotalCost().toString());
			//施工任务单-与主合同一致
			ConstructionPlan conPlan = constructionPlanDao.findByProjId(pro.getProjId());
			if(conPlan!=null){
				//监理单位
				consConstractInfoDTO.setSupervision_unit(StringUtil.isNotBlank(conPlan.getSuName())?conPlan.getSuName():"");
				consConstractInfoDTO.setSupervision_code(StringUtil.isNotBlank(conPlan.getSuId())?getUnitCode(conPlan.getSuId(), UnitTypeEnum.SUPERVISION_UNIT.getValue()):"");
				//施工任务时间-调整（取开工报告）
				//SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				//consConstractInfoDTO.setWork_date(simple.format(conPlan.getCpArriveDate()));
				
			}
			//开工报告
			List<WorkReport> workReports = workReportDao.findByProjId(pro.getProjId(),null);
			if (workReports!=null && workReports.size()>0) {
				WorkReport report = workReports.get(0);
				//施工任务时间-开工报告的实际开工日期
				SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				consConstractInfoDTO.setWork_date(simple.format(report.getPlannedStartDate()));
			}
			
			//备注
			consConstractInfoDTO.setWork_remark("");
			//税率-无
			consConstractInfoDTO.setShuilv(StringUtil.isNotBlank(subContractIntelligent.getIncrement())?getShuilvCode(subContractIntelligent.getIncrement()):getShuilvCode(IncreamentEnum.INCREAMENT_THR.getValue()));
		}
		return consConstractInfoDTO;
	}

	/**
	 * 组装智能表安装合同
	 * @param pro
	 * @return
	 */
	private ConstractInfoDTO queryIntelligentContractInfo(Project pro) {
		ConstractInfoDTO constractInfoDto = new ConstractInfoDTO();
		//查询对应的安装合同信息
		IntelligentMeterContract contract = intelligentMeterContractDao.findContractByprojId(pro.getProjId());
		if(contract != null){
			//合同协议号
			constractInfoDto.setContract_code(contract.getImcNo());
			
			//合同户数量
			constractInfoDto.setTotal_num(contract.getInstallNums());
			//签订时间
			SimpleDateFormat simple1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			constractInfoDto.setContract_date(simple1.format(contract.getImcSignDate()));
			
			//安装合同金额
			BigDecimal bigTemp = contract.getTotalCost();
			if(bigTemp == null)bigTemp = BigDecimal.ZERO;
			constractInfoDto.setContract_money(String.valueOf(bigTemp));
			
			//税率
			constractInfoDto.setShuilv1(this.getShuilvCode(contract.getIncrement()));
			//安装合同备注
			constractInfoDto.setContract_remark("");
			//每户金额
			constractInfoDto.setUnit_money(contract.getUnitCost().toString());
		}
		return constractInfoDto;
	}

	/**
	 * 查询安装合同信息，组装安装合同信息
	 * @param proID
	 * @param isIntelligent
	 */
	private ConstractInfoDTO queryContractInfo(Project pro) {
		ConstractInfoDTO constractInfoDto = new ConstractInfoDTO();
		//查询对应的安装合同信息
		Contract contract = contractDao.viewContractByprojId(pro.getProjId());
		if(contract != null)
		{
			//合同协议号
			constractInfoDto.setContract_code(contract.getConNo());
			
			//合同户数量,首先判断一下工程类型，如果为居民户，则为户数，否则为1
			if(pro.getProjectType().equals(ProjectType.PROJECTTYPE_RESIDENT))
			{
				constractInfoDto.setTotal_num(contract.getHousehold());
			}
			else
			{
				constractInfoDto.setTotal_num("1");
			}
			//签订时间
			SimpleDateFormat simple1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if (contract.getSignDate()!=null) {
				constractInfoDto.setContract_date(simple1.format(contract.getSignDate()));
			}
			
			//安装合同金额
			BigDecimal bigTemp = contract.getContractAmount();
			if(bigTemp == null)bigTemp = BigDecimal.ZERO;
			constractInfoDto.setContract_money(String.valueOf(bigTemp));
			
			//税率
			constractInfoDto.setShuilv1(this.getShuilvCode(contract.getIncrement()));
			//安装合同备注
			constractInfoDto.setContract_remark(contract.getRemark()!=null?contract.getRemark():"");
			//每户金额
			constractInfoDto.setUnit_money(contract.getHoseAmount()!=null?contract.getHoseAmount().toString():"0");
		}
		return constractInfoDto;
	}

	/**
	 * 查询对应的施工合同
	 * @param proID
	 * @param subContractList
	 */
	private ConsConstractInfoDTO querySubContract(String proID) throws ParseException
	{
		SubContract subContract = subContractDao.findSubContractByprojId(proID);
		
		if(subContract == null) return null;
		
		ConsConstractInfoDTO consConstractInfoDto = new ConsConstractInfoDTO();
		
		
		//合同协议号
		consConstractInfoDto.setWork_code(subContract.getScNo());
		
		//施工单位名称
		consConstractInfoDto.setWork_unit(subContract.getCuName());
		consConstractInfoDto.setWork_unit_code(getUnitCode(subContract.getCuId(),UnitTypeEnum.CONSTRUCTION_UNIT.getValue()));
		
		//施工合同金额
		consConstractInfoDto.setWork_money(subContract.getScAmount().toString());
		
		ConstructionPlan conPlan = constructionPlanDao.findByProjId(proID);
		//监理单位
		if(conPlan!=null){
			//施工任务时间
			//SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			//consConstractInfoDto.setWork_date(simple.format(conPlan.getCpArriveDate()));
			//监理单位
			consConstractInfoDto.setSupervision_unit(StringUtil.isNotBlank(conPlan.getSuName())?conPlan.getSuName():"");
			consConstractInfoDto.setSupervision_code(StringUtil.isNotBlank(conPlan.getSuId())?getUnitCode(conPlan.getSuId(),UnitTypeEnum.SUPERVISION_UNIT.getValue()):"");
		}
		//开工报告
		List<WorkReport> workReports = workReportDao.findByProjId(proID,null);
		if (workReports!=null && workReports.size()>0) {
			WorkReport report = workReports.get(0);
			//施工任务时间-开工报告的实际开工日期
			SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			consConstractInfoDto.setWork_date(simple.format(report.getPlannedStartDate()));
		}
		//备注
		consConstractInfoDto.setWork_remark(subContract.getRemark()!=null?subContract.getRemark():"");
		//税率-无
		consConstractInfoDto.setShuilv(StringUtil.isNotBlank(subContract.getIncrement())?getShuilvCode(subContract.getIncrement()):getShuilvCode(IncreamentEnum.INCREAMENT_THR.getValue()));
		
		return consConstractInfoDto;
	}
	
	/**
	 * 查询安装合同对应的补充协议
	 * @param proID
	 * @param contractList
	 */
	private ConstractInfoDTO querySupplementContract(Project pro) throws ParseException
	{
		//调用接口查询安装合同的补充协议记录
		SupplementalContractQueryReq req = new SupplementalContractQueryReq();
		req.setProjId(pro.getProjId());
		Map map = supplementalContractDao.querySupplementalContract(req);
		
		List<SupplementalContract>listSuppleContract = (List)map.get("data");
		
		if(listSuppleContract != null || listSuppleContract.size()>0)
		{
			SupplementalContract supplement  = listSuppleContract.get(0);
			ConstractInfoDTO constractInfoDto = new ConstractInfoDTO();
			//补充协议号
			constractInfoDto.setContract_code(supplement.getScNo());
			//合同户数量,首先判断一下工程类型，如果为居民户，则为户数，否则为1
			if(pro.getProjectType().equals(ProjectType.PROJECTTYPE_RESIDENT))
			{
				constractInfoDto.setTotal_num(supplement.getHouseNum()!=null?supplement.getHouseNum().toString():"0");
			}else{
				constractInfoDto.setTotal_num("1");
			}
			//每户金额
			constractInfoDto.setUnit_money(supplement.getHouseAmount()!=null?supplement.getHouseAmount().toString():"");
			//签订时间
			SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			constractInfoDto.setContract_date(simple.format(supplement.getSignDate()));
			
			//安装合同金额
			BigDecimal bigTemp = supplement.getScAmount();
			if(bigTemp == null)bigTemp = BigDecimal.ZERO;
			constractInfoDto.setContract_money(String.valueOf(bigTemp));
			//合同备注
			constractInfoDto.setContract_remark("");
			String increment = "";
			//税率
			//如果补充协议没有税率，默认为主合同的税率
			if(StringUtil.isBlank(supplement.getIncrement())){
				Contract contract = contractDao.viewContractByprojId(pro.getProjId());
				if(contract!=null){
					increment = contract.getIncrement();
				}
			}else{
				increment = supplement.getIncrement();
			}
			constractInfoDto.setShuilv1(getShuilvCode(increment));
			return constractInfoDto;
		}
		return null;
	}
	
	/**
	 * 查询结算单信息
	 * @param proID
	 */
	private SettleInfoDTO querySettlement(Project pro) throws ParseException
	{
		//调用接口查询结算单记录
		SettlementDeclaration settlement = settlementDeclarationDao.findByProjId(pro.getProjId());
		
		if(settlement == null)
		{
			return null;
		}
		
		SettleInfoDTO settleInfoDto = new SettleInfoDTO();
		
		//结算金额-结算审定价
		settleInfoDto.setSettle_money(settlement.getEndDeclarationCost()!=null?settlement.getEndDeclarationCost().toString():"0");
		
		//破路费
		settleInfoDto.setBreak_road_money(settlement.getAuxiliaryMaterialAmount()!=null?settlement.getAuxiliaryMaterialAmount().toString():"0");
		//其他费用
		BigDecimal otherCost = new BigDecimal("0");
		if(settlement.getManagementCost()!=null){//协调费
			otherCost = otherCost.add(settlement.getManagementCost());
		}
		if(settlement.getRecoveryCost()!=null){//恢复费
			otherCost = otherCost.add(settlement.getRecoveryCost());
		}
		if(settlement.getJeevesCost()!=null){//占道费
			otherCost = otherCost.add(settlement.getJeevesCost());
		}
		if(settlement.getCompensateCost()!=null){//占赔偿费
			otherCost = otherCost.add(settlement.getCompensateCost());
		}
		settleInfoDto.setOther_money(otherCost.toString());
		//验收时间-联合验收时间
		SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List<JointAcceptance> acceptances= jointAcceptanceDao.findByProjIdAndType(pro.getProjId(),null);
		if(acceptances!=null && acceptances.size()>0){
			settleInfoDto.setCheck_date(simple.format(acceptances.get(0).getJaDate()));
		}
		//竣工时间
		//竣工报告填写的实际竣工日期
		List<CompleteReport> crs = completeReportDao.findByProjId(pro.getProjId());
		if(crs!=null && crs.size()>0){
			settleInfoDto.setComplet_date(simple.format(crs.get(0).getRealEndDate()));
		}
		
		//交付时间todo:
		List<Ignite> ignites = igniteDao.findDeliveryTimeByProjId(pro.getProjId());
		if(ignites!=null && ignites.size()>0){
			settleInfoDto.setReady_date(ignites.get(0).getDeliveryTime()!=null?simple.format(ignites.get(0).getDeliveryTime()):null);
		}
		
		//检测单位-委托单
		List<BusinessCommunication> list = businessCommunicationDao.queryByProjIdAndTypeDetail(pro.getProjId(),"2011");
		if(list!=null && list.size()>0){
			settleInfoDto.setInspection_unit(list.get(0).getBcRecipientDeptName());
			//检测单位CODE-业务合作伙伴中
			settleInfoDto.setInspection_code(getUnitCode(list.get(0).getDeptId(),UnitTypeEnum.TEST_UNIT.getValue()));
		}
		return settleInfoDto;
	}
	
	/**
	 * 收款功能客户端实现功能
	 * 工程系统收款登记时，将实收信息存入财务系统
	 * @param project
	 * @param cashFlow
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public String gatherClient(Project project,CashFlow cashFlow,String updateType,String serviceType)
	{
		ResultMessage resultMessage = new ResultMessage();
		//接口收款单信息
		ReceiptBillInfoDTO receiptBillInfo = new ReceiptBillInfoDTO();
		//没有传递参数信息
		if(project == null || cashFlow == null){ 
			resultMessage.setRet_type(ResultTypeEnum.OTHER.getValue());
			resultMessage.setRet_message("没有传递工程信息或收款单信息");
		}else{
		
			//生成收款DTO对象
			GatherDTO gatherDTO = new GatherDTO();
			
			//客户名称
			//gatherDTO.setCust_code(project.getCustId());
			gatherDTO.setCust_code(this.getUnitCode(project.getCustId(),UnitTypeEnum.CUSTOMER_UNIT.getValue()));
			
			//项目类型
			gatherDTO.setProject_type(this.getProjectTypeCode(project,cashFlow.getContractType()));
			
			//合同编号
			//有智能表和安装合同
			ConstractInfoDTO constractInfoDTO = queryConstract(cashFlow);
			//项目编号
			gatherDTO.setPro_code(constractInfoDTO.getContract_code());
			//合同编号
			gatherDTO.setContract_code(constractInfoDTO.getContract_code());
			
			//收款金额
			gatherDTO.setMoney(cashFlow.getCfAmount().toString());
			
			//收费员编号
			gatherDTO.setOper_code(cashFlow.getStaff().getStaffId());
			
			Staff staff = staffService.getStaff(cashFlow.getStaff().getStaffId());
			//收费员名称
			gatherDTO.setOper_name(staff.getStaffName());
			
			Department department = departmentDao.get(cashFlow.getDepartment().getDeptId());
			//收费部门名称
			gatherDTO.setDept_name(department.getDeptName());
			
			//收费部门编号-
			gatherDTO.setDept_code(department.getDeptOutCode());
			
			//单据日期
			SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(cashFlow.getCashDate()!=null)
				gatherDTO.setBill_date(simple.format(cashFlow.getCashDate()));
			//分公司编号
			Department dept = departmentDao.get(project.getCorpId());
			gatherDTO.setCompany_code(dept!=null?dept.getDeptOutCode():"");
			
			//备注
			gatherDTO.setRemark(StringUtil.isNotBlank(cashFlow.getRemark())?cashFlow.getRemark():cashFlow.getCfTypeDes());
			
			//收款性质
			gatherDTO.setReceiptType(CollectionTypeEnum.valueof(cashFlow.getCfType()).getCode());
			//税率
			gatherDTO.setShuilv(this.getShuilvCode(cashFlow.getIncrement()));
			//收款流水ID
			gatherDTO.setBill_id(cashFlow.getCfId());
			
			//操作类型
			receiptBillInfo.setOperate_type(updateType);
			operateType = updateType;
			//修改类型
			//receiptBillInfo.setUpdate_type(updateType);
			receiptBillInfo.setReceiptInfo(gatherDTO);
			
			//调用收款单接口
			String strRet = this.webserviceCallReceiptBill(receiptBillInfo);
			
			JSONObject jsonbean = JSONObject.fromObject(strRet);
			resultMessage = (ResultMessage) JSONObject.toBean(jsonbean,ResultMessage.class);
		}
		//接口日志类
		WebserviceLog webserviceLog = new WebserviceLog();
		webserviceLog.setOperateType(operateType);
		webserviceLog.setLogParams(JSONObject.fromObject(receiptBillInfo).toString());
		webserviceLog.setServiceType(serviceType);
		webserviceLog.setResultType(resultMessage.getRet_type());
		webserviceLog.setResultMsg(resultMessage.getRet_message());
		webserviceLog.setProjId(project.getProjId());
		webserviceLog.setProjNo(project.getProjNo());
		//接口访问地址
		IReceiptBillLocator customerInfo = new IReceiptBillLocator();
		webserviceLog.setServicePath(customerInfo.getIReceiptBillSOAP11port_httpAddress());
		this.saveWebserviceLog(webserviceLog);
		return JSONObject.fromObject(resultMessage).toString();
	}
	/**
	 * 根据实收流水合同类型查询安装合同号
	 * @param cashFlow
	 * @return
	 */
	private ConstractInfoDTO queryConstract(CashFlow cashFlow) {
		ConstractInfoDTO conInfo = new ConstractInfoDTO();
		//智能表合同
		if(ArContractTypeEnum.INTELLIGENCE.getValue().equals(cashFlow.getContractType())){
			IntelligentMeterContract imc = intelligentMeterContractDao.findContractByprojId(cashFlow.getProjId());
			if(imc == null)
				return null;
			conInfo.setContract_code(imc.getImcNo());
			//补充协议的收款,补充协议号作为工程编号收款
		}else if(ArContractTypeEnum.SUP_CONTRACT.getValue().equals(cashFlow.getContractType())){
			SupplementalContract spContract = supplementalContractDao.get("scNo", cashFlow.getConNo());
			if(spContract == null)
				return null;
			conInfo.setContract_code(spContract.getScNo());
		}else{//其他合同
			Contract contract = contractDao.viewContractByprojId(cashFlow.getProjId());
			if(contract == null)
				return null;
			conInfo.setContract_code(contract.getConNo());
		}
		return conInfo;
	}

	/**
	 * 付款功能客户端实现功能
	 * @param project
	 * @param cashFlow
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public String paymentClient(Project project,CashFlow cashFlow,String updateType,String serviceType)
	{
		ResultMessage resultMessage = new ResultMessage();
		//付款单接口信息组装
		PayBillInfoDTO payBillInfoDTO = new PayBillInfoDTO();
		//没有传递参数信息
		if(project == null || cashFlow == null){ 
			resultMessage.setRet_type(ResultTypeEnum.OTHER.getValue());
			resultMessage.setRet_message("没有传递工程信息或收款单信息");
		}else{
			//付款单信息
			PaymentDTO paymentDTO = new PaymentDTO();
			
			//付款单ID
			paymentDTO.setBill_id(cashFlow.getCfId());
			//项目编号 
			paymentDTO.setPro_no(project.getProjNo());
			//项目名称
			paymentDTO.setPrj_name(project.getProjName());
			//智能表传递的工程编号和名称需要作拼接
			if(ArContractTypeEnum.INTELLIGENCE.getValue().equals(cashFlow.getContractType())){
				//智能表安装合同信息
				ConstractInfoDTO constractInfoDTO = queryIntelligentContractInfo(project);
				//项目编号
				paymentDTO.setPro_no(constractInfoDTO.getContract_code());
				//项目名称
				paymentDTO.setPrj_name(project.getProjName()+constractInfoDTO.getContract_code());
			}
			
			//分包合同信息-智能表分包合同和施工单位分包合同
			ConsConstractInfoDTO consConstract = querySubContract(cashFlow);
			//合同编号-施工合同
			paymentDTO.setContract_code(consConstract.getWork_code());
			//合同金额
			paymentDTO.setContract_money(consConstract.getWork_money());
			
			//付款单位--设计费-设计单位，监理费-监理单位，检测费-检测单位，其他-施工单位
			if(cashFlow.getCfType().equals(CollectionTypeEnum.DESIGN_FEE.getValue())){//设计单位
				paymentDTO.setWork_unit(project.getDuName());
				Department department = departmentDao.get(project.getDuId());
				paymentDTO.setWork_unit_code(department.getDeptOutCode());
			}else if(cashFlow.getCfType().equals(CollectionTypeEnum.SU_FEE.getValue())){//监理单位
				ConstructionPlan conPlan = constructionPlanDao.findByProjId(project.getProjId());
				if(conPlan!=null && StringUtil.isNotBlank(conPlan.getSuId())){
					paymentDTO.setWork_unit(conPlan.getSuName());
					paymentDTO.setWork_unit_code(getUnitCode(conPlan.getSuId(), UnitTypeEnum.INSPECTION_UNIT.getType()));
				}
			}else if(cashFlow.getCfType().equals(CollectionTypeEnum.CHECK_FEE.getValue())){//检测单位
				List<BusinessCommunication> list = businessCommunicationDao.queryByProjIdAndTypeDetail(project.getProjId(), "2011");
				if(list!=null && list.size()>0){
					paymentDTO.setWork_unit(list.get(0).getBcRecipientDeptName());
					//检测单位CODE-业务合作伙伴中
					paymentDTO.setWork_unit_code(getUnitCode(list.get(0).getDeptId(),UnitTypeEnum.TEST_UNIT.getValue()));
				}
			}else{//施工单位
				paymentDTO.setWork_unit(consConstract.getWork_unit());
				paymentDTO.setWork_unit_code(consConstract.getWork_code());
			}
			
			
			//施工日期-计划
			SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			//开工报告
			List<WorkReport> workReports = workReportDao.findByProjId(project.getProjId(),null);
			if (workReports!=null && workReports.size()>0) {
				WorkReport report = workReports.get(0);
				//施工任务时间-开工报告的实际开工日期
				paymentDTO.setWork_date(simple.format(report.getPlannedStartDate()));
			}
			
			//施工金额-付款单金额
			paymentDTO.setWork_money(cashFlow.getCfAmount().toString());
			
			//付款账号
			paymentDTO.setPay_bankno(cashFlow.getCashAccount());
			
			//付款部门编码-
			Department department = departmentDao.get(cashFlow.getDepartment().getDeptId());
			paymentDTO.setDept_code(department.getDeptOutCode());
			//收款人员编码
			paymentDTO.setOper_code(cashFlow.getStaff().getStaffId());
			
			Staff staff = staffService.getStaff(cashFlow.getStaff().getStaffId());
			//收款人员姓名
			paymentDTO.setOper_name(staff.getStaffName());
			
			//税率
			paymentDTO.setShuilv(this.getShuilvCode(cashFlow.getIncrement()));
			
			//付款性质
			paymentDTO.setPay_type(CollectionTypeEnum.valueof(cashFlow.getCfType()).getCode());
			
			//组织编码
			Department dept = departmentDao.get(project.getCorpId());
			paymentDTO.setOrg_code(dept!=null?dept.getDeptOutCode():"");
			
			payBillInfoDTO.setOperate_type(updateType);
			operateType = updateType;
					
			payBillInfoDTO.setPayInfo(paymentDTO);
			//调用友接口
			String strRet = this.webserviceCallPayBill(payBillInfoDTO);
			JSONObject jsonbean = JSONObject.fromObject(strRet);
			resultMessage = (ResultMessage) JSONObject.toBean(jsonbean,ResultMessage.class);
		}
		//接口日志类
		WebserviceLog webserviceLog = new WebserviceLog();
		webserviceLog.setOperateType(operateType);
		webserviceLog.setLogParams(JSONObject.fromObject(payBillInfoDTO).toString());
		//webserviceLog.setServiceType("3");//工程信息同步
		webserviceLog.setServiceType(serviceType);//工程信息同步
		webserviceLog.setResultType(resultMessage.getRet_type());
		webserviceLog.setResultMsg(resultMessage.getRet_message());
		webserviceLog.setProjId(project.getProjId());
		webserviceLog.setProjNo(project.getProjNo());
		//调用接口传递数据
		IPayBillLocator customerInfo = new IPayBillLocator();
		webserviceLog.setServicePath(customerInfo.getIPayBillSOAP11port_httpAddress());
		this.saveWebserviceLog(webserviceLog);
		//返回结果
		return JSONObject.fromObject(resultMessage).toString();
	}
	/**
	 * 根据实际收付流水合同类型查询分包合同号和金额
	 * @param project
	 * @param cashFlow
	 * @return
	 */
	private ConsConstractInfoDTO querySubContract(CashFlow cashFlow) {
		ConsConstractInfoDTO conDTO = new ConsConstractInfoDTO();
		if(ArContractTypeEnum.INTELLIGENCE.getValue().equals(cashFlow.getContractType())){//智能表分合同
			 SubContractIntelligent  scIntelligent= scIntelligentDao.findContractByprojId(cashFlow.getProjId());
			 if(scIntelligent==null)
				 return null;
			 conDTO.setWork_code(scIntelligent.getItScNo());//合同编号
			 conDTO.setWork_money(scIntelligent.getTotalCost().toString());//合同金额
			 conDTO.setWork_unit(scIntelligent.getsPartyName());//分包单位
		}else{//其他合同
			 SubContract subContract = subContractDao.findByProjId(cashFlow.getProjId());
			 if(subContract==null)
				 return null;
			 conDTO.setWork_code(subContract.getScNo());
			 conDTO.setWork_money(subContract.getScAmount().toString());
			 conDTO.setWork_unit(subContract.getCuName());//分包单位
		}
		return conDTO;
	}
	
	/**
	 * 调用WebService接口
	 * @param <T>
	 * @return
	 */
	/*private <T> String webservicesCall(T t,String clientAddr)
	{
		
		//调用WebService接口，传数据到用友系统中；
		JaxWsProxyFactoryBean jwpfb = new JaxWsProxyFactoryBean();  
		jwpfb.setServiceClass(FinanceService.class);  //webwervice
		jwpfb.setAddress(clientAddr);  				  //访问地址
		//用友的接口类
		FinanceService hw = (FinanceService) jwpfb.create();  
		System.err.println(hw);
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		
		JSONObject json = JSONObject.fromObject(t,jsonConfig);
		System.err.println("接口----");
		logger.info("同步工程信息：" + json.toString());
		
		//将数据进行加密
		String encodeStr = Base64Util.jdkBase64Encoder(json.toString());
		//调用接口传递数据
		String strRet = hw.synProjectInfo(encodeStr);
		System.err.println(strRet+"--------------strRet");
		
		//解密返回数据
		return Base64Util.jdkBase64Decoder(strRet);
	}*/
	/**
	 * 调用WebService接口
	 * 用友工程信息同步接口
	 * @param <T>
	 * @return
	 * @throws ServiceException 
	 * @throws RemoteException 
	 * @throws BusinessException 
	 */
	private <T> String webserviceCall(T t) 
	{
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		JSONObject json = JSONObject.fromObject(t,jsonConfig);
		logger.info("工程信息同步接口参数：" + json.toString());
		System.err.println("json"+json.toString());
		try {
			//调用接口传递数据
			IContractItfLocator customerInfo = new IContractItfLocator();
			IContractItfPortType infoPortType;
			infoPortType = customerInfo.getIContractItfSOAP11port_http();
			String result = infoPortType.doCcontractAdd("username", "pwd", json.toString());
			logger.info("工程信息同步接口调用成功，返回参数：" + result);
			return result;
		} catch (Exception e) {
			System.err.println(e.getMessage());
			logger.info("工程信息同步接口调用失败",e.getMessage());
		}
		ResultMessage message = new ResultMessage();
		message.setRet_type(ResultTypeEnum.FAIL.getValue());
		message.setRet_message("工程信息同步接口调用失败");
		return JSONObject.fromObject(message).toString();
	}
	
	/**
	 * 调用WebService接口
	 * 用友收款单接口
	 * @param <T>
	 * @return
	 * @throws ServiceException 
	 * @throws RemoteException 
	 * @throws BusinessException 
	 */
	private <T> String webserviceCallReceiptBill(T t) 
	{
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		JSONObject json = JSONObject.fromObject(t,jsonConfig);
		System.err.println("json"+json.toString());
		logger.info("收款单接口传递参数：" + json.toString());
		try {
			//调用接口传递数据
			IReceiptBillLocator customerInfo = new IReceiptBillLocator();
			IReceiptBillPortType infoPortType;
			infoPortType = customerInfo.getIReceiptBillSOAP11port_http();
			String result = infoPortType.doReceiptBill("username", "pwd", json.toString());
			logger.info("收款单接口调用成功，返回参数：" + result);
			return result;
		} catch (Exception e) {
			System.err.println(e.getMessage());
			logger.info("收款单接口调用失败",e.getMessage());
		}
		ResultMessage message = new ResultMessage();
		message.setRet_type(ResultTypeEnum.FAIL.getValue());
		message.setRet_message("收款单接口调用失败");
		return JSONObject.fromObject(message).toString();
	}
	/**
	 * 调用WebService接口
	 * 用友付款单接口
	 * @param <T>
	 * @return
	 * @throws ServiceException 
	 * @throws RemoteException 
	 * @throws BusinessException 
	 */
	private <T> String webserviceCallPayBill(T t) 
	{
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		JSONObject json = JSONObject.fromObject(t,jsonConfig);
		logger.info("付款单接口传递参数：" + json.toString());
		try {
			//调用接口传递数据
			IPayBillLocator customerInfo = new IPayBillLocator();
			IPayBillPortType infoPortType;
			infoPortType = customerInfo.getIPayBillSOAP11port_http();
			String result = infoPortType.doPayBill("username", "pwd", json.toString());
			logger.info("付款单接口调用成功，返回参数：" + result);
			return result;
		} catch (Exception e) {
			System.err.println(e.getMessage());
			logger.info("付款单接口调用失败",e.getMessage());
		}
		ResultMessage message = new ResultMessage();
		message.setRet_type(ResultTypeEnum.FAIL.getValue());
		message.setRet_message("付款单接口调用失败");
		return JSONObject.fromObject(message).toString();
	}
	
	//查询项目类型编码:按照工程类型和出资方式编码
	private String getProjectTypeCode(Project proj, String isIntelligent){
		if(proj==null){
			return null;
		}
		//含智能表的工程
		if(StringUtil.isNotBlank(proj.getIsIntelligentMeter()) && proj.getIsIntelligentMeter().equals("1") &&"1".equals(isIntelligent)){
			return "02";
		}
		CorrelationReq correlationReq = new CorrelationReq();
		correlationReq.setCorrelateInfoId(proj.getProjectType());
		correlationReq.setCorrelatedInfoId(proj.getContributionMode());
		List<Correlation> list = correlationDao.findCorType(correlationReq);
		if(list!=null && list.size()>0){
			return list.get(0).getContributionCode();
		}
		return null;
	}
	
	//查询税率编码
	private String getShuilvCode(String shuilv){
		if(shuilv != null){
			IncrementReg incrementReg = new IncrementReg();
			incrementReg.setIncrement(shuilv);
			List<Increment> list = incrementDao.queryIncrement(incrementReg);
			if(list!=null && list.size()>0){
				return list.get(0).getIncrementCode();
			}
		}
		return null;
	}
	//查询单位编码
	public String getUnitCode(String unitId,String unitType){
		//单位ID
		if(StringUtil.isBlank(unitId)){
			return null;
		}
		//单位类型
		if(StringUtil.isBlank(unitType)){
			return null;
		}
		//客户单位
		if(unitType.equals(UnitTypeEnum.CUSTOMER_UNIT.getValue())){
			Customer customer = customerService.queryCustomerById(unitId);
			return customer!=null?customer.getCustCode():"";
		}else{//其他单位
			BusinessPartners partners = businessPartnersService.viewBusinessPartnersById(unitId);
			return partners!=null?partners.getUnitCode():"";
		}
	}
	

	/**
	 * 获取出资方式编码
	 * @param contributionMode
	 * @return
	 */
	public String getProjectCMode(String contributionModeId) {
		ContributionMode mode = contributionModeDao.get(contributionModeId);
		if(mode!=null){
			return mode.getCode();
		}
		return "";
	}
	
	/**
	 * 增加接口调用日志
	 * @param webserviceLog
	 */
	public void saveWebserviceLog(WebserviceLog webserviceLog){
		webserviceLog.setLogTime(webserviceLogDao.getDatabaseDate());
		webserviceLogService.saveOrUpdate(webserviceLog);
	}

}
