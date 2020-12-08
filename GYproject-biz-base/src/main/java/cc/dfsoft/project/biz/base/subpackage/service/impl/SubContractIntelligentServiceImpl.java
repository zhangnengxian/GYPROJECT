package cc.dfsoft.project.biz.base.subpackage.service.impl;

import cc.dfsoft.project.biz.base.charge.enums.ARFlagEnum;
import cc.dfsoft.project.biz.base.charge.enums.ArContractTypeEnum;
import cc.dfsoft.project.biz.base.charge.service.AccrualsRecordService;
import cc.dfsoft.project.biz.base.contract.dao.PayTypeDao;
import cc.dfsoft.project.biz.base.contract.entity.PayType;
import cc.dfsoft.project.biz.base.contract.enums.IsIntelligentConPayEnum;
import cc.dfsoft.project.biz.base.plan.dao.ConstructionPlanDao;
import cc.dfsoft.project.biz.base.project.dao.ProjectDao;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.project.enums.CollectionTypeEnum;
import cc.dfsoft.project.biz.base.project.service.OperateRecordService;
import cc.dfsoft.project.biz.base.project.service.ProjectService;
import cc.dfsoft.project.biz.base.subpackage.dao.IntelligentMeterContractDao;
import cc.dfsoft.project.biz.base.subpackage.dao.SubContractDao;
import cc.dfsoft.project.biz.base.subpackage.dao.SubContractIntelligentDao;
import cc.dfsoft.project.biz.base.subpackage.dto.SubConIntelligentReq;
import cc.dfsoft.project.biz.base.subpackage.entity.IntelligentMeterContract;
import cc.dfsoft.project.biz.base.subpackage.entity.SubContract;
import cc.dfsoft.project.biz.base.subpackage.entity.SubContractIntelligent;
import cc.dfsoft.project.biz.base.subpackage.service.SubContractIntelligentService;
import cc.dfsoft.project.biz.ifs.ResultMessage;
import cc.dfsoft.project.biz.ifs.WebServiceTypeEnum;
import cc.dfsoft.project.biz.ifs.finance.enums.FinanceContractTypeEnum;
import cc.dfsoft.project.biz.ifs.finance.enums.FinanceOperateTypeEnum;
import cc.dfsoft.project.biz.ifs.finance.enums.ResultTypeEnum;
import cc.dfsoft.project.biz.ifs.finance.service.IFinanceInfoService;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.exception.ExpressException;
import cc.dfsoft.uexpress.common.util.IDUtil;
import cc.dfsoft.uexpress.common.util.StringUtil;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.expression.ParseException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/**
 * 
 * 描述:智能表分合同业务实现类
 * @author liaoyq
 * @createTime 2017年10月11日
 */
@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class SubContractIntelligentServiceImpl implements
		SubContractIntelligentService {

	@Resource
	SubContractIntelligentDao subContractIntelligentDao;
	@Resource
	ConstructionPlanDao constructionPlanDao;
	@Resource
	ProjectDao projectDao;
	@Resource
	IntelligentMeterContractDao imcDao;
	@Resource
	OperateRecordService operateRecordService;
	@Resource
	PayTypeDao payTypeDao;
	@Resource
	AccrualsRecordService accrualsRecordService;
	@Resource
	SubContractDao subContractDao;
	@Resource
	ProjectService projectService;
	@Resource
	IFinanceInfoService financeInfoService;
	
	@Override
	public SubContractIntelligent viewSubConIntelligentByProjId(String id) {
		try {
			Project project = projectDao.get(id);
			List<PayType> payTypes = payTypeDao.findByProjType(project);
			SubContractIntelligent scIntelligent= subContractIntelligentDao.findContractByprojId(id);
			//施工计划
			//ConstructionPlan constructionPlan = constructionPlanDao.viewPlanById(id);
			if(null == scIntelligent){
				scIntelligent = new SubContractIntelligent();
				scIntelligent.setProjId(project.getProjId());//工程Id
				scIntelligent.setProjNo(project.getProjNo());//工程no
				scIntelligent.setProjName(project.getProjName());//工程名称 
				scIntelligent.setProjAddr(project.getProjAddr());//工程地址 
				scIntelligent.setProjContent(project.getProjScaleDes());//工程内容  
				//scIntelligent.setItScNo(project.getProjNo());//默认工程编号-可修改
				scIntelligent.setProjScaleDes(project.getProjScaleDes());//程 规模 
				
				scIntelligent.setDepartmentName(StringUtil.isNotBlank(SessionUtil.getLoginInfo().getDeptName())?SessionUtil.getLoginInfo().getDeptName():"");//业务部门获取当前用户所在部门-信息中心智能表组
				
				scIntelligent.setContributionModeDes(project.getContributionModeDes());
				scIntelligent.setProjectTypeDes(project.getProjectTypeDes());
				//安装户数
				IntelligentMeterContract imc = imcDao.findContractByprojId(id);
				if(imc!=null&&StringUtil.isNotBlank(imc.getInstallNums())){
					scIntelligent.setInstallNums(imc.getInstallNums());//安装户数 
				}
				
				/*if(constructionPlan!=null){
					scIntelligent.setfPartyRepresent(constructionPlan.getBuilder()!=null?constructionPlan.getBuilder():"");//甲方现场代表
					scIntelligent.setfPartySuJgj(constructionPlan.getSuRepresentative()!=null?constructionPlan.getSuRepresentative():"");//甲方总监代表
					
				}*/
				scIntelligent.setScPlannedStartDate("同燃气建设工程施工同步");
				scIntelligent.setScPlannedEndDate("同燃气建设工程施工同步");
				scIntelligent.setCorpId(project.getCorpId());                     //发包人
				scIntelligent.setCorpName(project.getCorpName());				  //
				scIntelligent.setSignDate(subContractIntelligentDao.getDatabaseDate());
				SubContract subContract = subContractDao.findByProjId(id);
				if(subContract!=null && StringUtil.isNotBlank(subContract.getIncrement())){//税率
					scIntelligent.setIncrement(subContract.getIncrement());
				}
			}
			if(StringUtil.isNotBlank(project.getIsIntelligentMeter())){ //是否是智能表
				scIntelligent.setIsIntelligentMeter(project.getIsIntelligentMeter());
			}
			//发包 经办人
			scIntelligent.setfPartyConAgent(SessionUtil.getLoginInfo().getStaffName()!=null?SessionUtil.getLoginInfo().getStaffName():"");
			if(null != payTypes){
				scIntelligent.setPayTypes(payTypes);
			}
			/*if(constructionPlan!=null){
				scIntelligent.setScPlannedStartDate(constructionPlan.getPlannedStartDate());//开工日期
				//竣工日期
				if(constructionPlan.getPlannedStartDate()!=null&&StringUtil.isNotBlank(constructionPlan.getProjTimeLimit())){
					scIntelligent.setScPlannedEndDate(DateUtil.addDay(constructionPlan.getPlannedStartDate(), Integer.parseInt(constructionPlan.getProjTimeLimit())));
				}
				scIntelligent.setProjTimeLimit(constructionPlan.getProjTimeLimit());
			}
			scIntelligent.setFirstRate(new BigDecimal(PayTypeSCIntelligentEnum.SC_INTELLIGENT_RATE.getMessage()));*/
			return scIntelligent;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 保存智能表分合同信息
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
	public String saveSubContractIntelligent(SubContractIntelligent scIntelligent) throws Exception {
		if(StringUtil.isBlank(scIntelligent.getItScId()) && this.findByScNo(scIntelligent.getItScNo()).size()>0){
			return "exist";
		}
		if(StringUtils.isBlank(scIntelligent.getItScId())){
			scIntelligent.setItScId(IDUtil.getUniqueId(Constants.MODULE_CODE_SUBCONTRACT));
		}
		subContractIntelligentDao.saveOrUpdate(scIntelligent);
		
		if(scIntelligent.getFlag().equals("1")){
			Project pro = projectDao.get(scIntelligent.getProjId());
			/*//生成操作记录
			String orId = IDUtil.getUniqueId(Constants.MODULE_CODE_CONTRACT);//生成唯一ID
			operateRecordService.createOperateRecord(orId, scIntelligent.getProjId(), scIntelligent.getProjNo(), StepOutWorkflowEnum.INTELLIGENT_SUB_CONTRACT.getValue(), StepOutWorkflowEnum.INTELLIGENT_SUB_CONTRACT.getMessage(), "");	
			*/
			//生成应付流水
			if (StringUtils.isNotBlank(scIntelligent.getPayType())) {
				//合同首付款
				accrualsRecordService.insertAccrualsRecord(pro,IDUtil.getUniqueId(Constants.MODULE_CODE_COST),CollectionTypeEnum.SU_INTELLIGENT_CONTRACT.getValue(),
						Integer.valueOf(ARFlagEnum.ACCOUNTS_PAY.getValue()) , scIntelligent.getFirstPayment(),ArContractTypeEnum.INTELLIGENCE.getValue(),scIntelligent.getItScNo());
				//阶段款2
				accrualsRecordService.insertAccrualsRecord(pro,IDUtil.getUniqueId(Constants.MODULE_CODE_COST),CollectionTypeEnum.STAGE_SU_INTELLIGENT_CONTRACT.getValue(),
						Integer.valueOf(ARFlagEnum.ACCOUNTS_PAY.getValue()) ,scIntelligent.getSecondPayment(),ArContractTypeEnum.INTELLIGENCE.getValue(),scIntelligent.getItScNo());
				//阶段款3
				if(scIntelligent.getPayType().equals("2")){
					accrualsRecordService.insertAccrualsRecord(pro,IDUtil.getUniqueId(Constants.MODULE_CODE_COST),CollectionTypeEnum.STAGE_SU_INTELLIGENT_CONTRACT.getValue(),
							Integer.valueOf(ARFlagEnum.ACCOUNTS_PAY.getValue()) , scIntelligent.getThirdPayment(),ArContractTypeEnum.INTELLIGENCE.getValue(),scIntelligent.getItScNo());
				}
			}
			//保存智能表分包合同时，调用财务的接口，同步信息todo:
			if(projectService.isToCall(scIntelligent.getProjId(),WebServiceTypeEnum.SUBCONTRACT_SIGN.getValue())){
				String msg = financeInfoService.synProjectInfoClient(scIntelligent.getProjId(), FinanceOperateTypeEnum.SUBCONTRACT_SIGN.getValue(), FinanceContractTypeEnum.CONTRACT.getValue(),IsIntelligentConPayEnum.INTELLIGENT_CON_PAY.getValue());
				ResultMessage resultMessage = new ResultMessage();
				JSONObject jsonbean = JSONObject.fromObject(msg);
				//返回信息-当接口返回失败时，抛出异常
				resultMessage = (ResultMessage) JSONObject.toBean(jsonbean,ResultMessage.class);
				if(resultMessage!=null && ResultTypeEnum.FAIL.getValue().equals(resultMessage.getRet_type()) && projectService.isSynchronization(scIntelligent.getProjId(),WebServiceTypeEnum.SUBCONTRACT_SIGN.getValue())){
					//回滚事物
					throw new ExpressException(resultMessage.getRet_type(),resultMessage.getRet_message());
				}
			}
			
		}
		return Constants.OPERATE_RESULT_SUCCESS;
	}

	public  List<SubContractIntelligent> findByScNo(String itScNo) {
		return subContractIntelligentDao.findByScNo(itScNo);
	}

	@Override
	public Map<String, Object> queryProjects(SubConIntelligentReq req) {
		Map<String, Object> map = subContractIntelligentDao.queryContractBySql(req);
		List<Map<String, Object>> objs= (List<Map<String, Object>>) map.get("data");
		List<Project> projs = new ArrayList<Project>();
		if(objs!=null && objs.size()>0){
			for(Map<String, Object> obj:objs){
				Project project = new Project();
				project.setProjId(obj.get("projId").toString());
				project.setProjName(obj.get("projName").toString());
				project.setProjNo(obj.get("projNo").toString());
				project.setProjStatusId(obj.get("projStatusId")!=null?obj.get("projStatusId").toString():"");
				project.setFlag(obj.get("subFlag")!=null?obj.get("subFlag").toString():"");
				project.setProjectType(obj.get("projectType").toString());
				projs.add(project);
			}
			map.put("data", projs);
		}
		return map;
	}
}
