package cc.dfsoft.project.biz.base.settlement.service.impl;

import cc.dfsoft.project.biz.base.contract.enums.ContractIsPrintEnum;
import cc.dfsoft.project.biz.base.plan.dao.ConstructionPlanDao;
import cc.dfsoft.project.biz.base.plan.entity.ConstructionPlan;
import cc.dfsoft.project.biz.base.project.dao.ProjectDao;
import cc.dfsoft.project.biz.base.project.dao.ProjectTypeDao;
import cc.dfsoft.project.biz.base.project.dao.SignNoticeDao;
import cc.dfsoft.project.biz.base.project.dto.ProjectQueryReq;
import cc.dfsoft.project.biz.base.project.entity.*;
import cc.dfsoft.project.biz.base.project.enums.ProjStatusEnum;
import cc.dfsoft.project.biz.base.project.enums.SignDataTypeEnum;
import cc.dfsoft.project.biz.base.project.service.SignatureService;
import cc.dfsoft.project.biz.base.settlement.dao.SettlementDeclarationDao;
import cc.dfsoft.project.biz.base.settlement.dao.SettlementJonintlySignDao;
import cc.dfsoft.project.biz.base.settlement.dto.SettlementDeclarationReq;
import cc.dfsoft.project.biz.base.settlement.entity.SettlementDeclaration;
import cc.dfsoft.project.biz.base.settlement.entity.SettlementJonintlySign;
import cc.dfsoft.project.biz.base.settlement.enums.AuditResultEnum;
import cc.dfsoft.project.biz.base.settlement.service.SettlementJonintlySignService;
import cc.dfsoft.project.biz.base.subpackage.dao.SubBudgetDao;
import cc.dfsoft.project.biz.base.subpackage.dao.SubContractDao;
import cc.dfsoft.project.biz.base.subpackage.entity.SubBudget;
import cc.dfsoft.project.biz.base.subpackage.entity.SubContract;
import cc.dfsoft.project.biz.base.subpackage.enums.SubContractMethodEnum;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.util.IDUtil;
import cc.dfsoft.uexpress.common.util.MoneyConverter;
import cc.dfsoft.uexpress.common.util.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/**
 * 结算汇签
 * @author fuliwei
 *
 */
@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class SettlementJonintlySignServiceImpl implements SettlementJonintlySignService {
	
	/**结算汇签*/
	@Resource
	SettlementJonintlySignDao settlementJonintlySignDao;
	
	/**工程Dao*/
	@Resource
	ProjectDao  projectDao;
	
	/**施工计划Dao*/
	@Resource
	ConstructionPlanDao constructionPlanDao;
	
	/**结算*/
	@Resource
	SettlementDeclarationDao settlementDeclarationDao;
	
	/**签字服务接口*/
	@Resource
	SignatureService signatureService;
	
	/**工程类型*/
	@Resource
	ProjectTypeDao projectTypeDao;
	
	/**施工合同*/
	@Resource
	SubContractDao subContractDao;
	
	@Resource
	SubBudgetDao subBudgetDao;

	@Resource
	SignNoticeDao signNoticeDao;
	/**
	 * 查询左侧工程列表
	 * @author fuliwei
	 * @createTime 2017年8月9日
	 * @param 
	 * @return
	 */
	@Override
	public Map<String, Object> queryProject(ProjectQueryReq req) throws ParseException {
		List<String> projStuList = new ArrayList();
		List<ProjStatusEnum> enums = ProjStatusEnum.getThanValueNew(ProjStatusEnum.SETTLEMENT_GOV_AUDIT_COST.getValue(),ProjStatusEnum.CONNECT_CONFIRM.getValue());
		for(ProjStatusEnum projStatusEnum :enums){
			projStuList.add(projStatusEnum.getValue());
		}
		req.setProjStuList(projStuList);//工程状态,终审确认之后-待资料归档之间
		
		
		//如果查历史 1 是已完成
		if(AuditResultEnum.APPLYING.getValue().equals(req.getSignStatus())){
			projStuList=new ArrayList();
			req.setProjStuList(projStuList);
		}
		
		
		Map<String, Object> map=projectDao.queryProjectForJoint(req);
		
		List<Project> list=(List<Project>) map.get("data");
		if(list!=null && list.size()>0){
			for(Project pro:list){
				SettlementJonintlySign si=settlementJonintlySignDao.findById(pro.getProjId());
				if(si!=null){
					pro.setProjectRemark(si.getSignStatus());
				}else{
					pro.setProjectRemark("0");
				}
			}
		}
		map.put("data", list);
		return map;
	}
	
	/**
	 * 结算汇签单列表
	 * @author fuliwei
	 * @createTime 2017年9月26日
	 * @param 
	 * @return
	 */
	@Override
	public Map<String, Object> querySettlementJonintlySign(SettlementDeclarationReq req) throws ParseException {
		//return settlementJonintlySignDao.querySettlementJonintlySign(req);
		Map<String, Object> map=settlementJonintlySignDao.querySettlementJonintlySign(req);
		
		List<SettlementJonintlySign> list=(List<SettlementJonintlySign>) map.get("data");
		
		if(list!=null && list.size()>0){
			for(SettlementJonintlySign settlementJonintlySign:list){
				Project project=projectDao.get(settlementJonintlySign.getProjId());
				if(project!=null){
					settlementJonintlySign.setProjectType(project.getProjectType());
				}
				settlementJonintlySign.setEndDCLegalAmount(MoneyConverter.Num2RMB(settlementJonintlySign.getEndDeclarationCost().doubleValue()));
			}
		}
		
		return map;
	}
	
	/**
	 * 查询详述
	 * @author fuliwei
	 * @createTime 2017年9月26日
	 * @param 
	 */
	@Override
	public SettlementJonintlySign findById(String projId) {
		Project pro=projectDao.get(projId);
		
		SettlementJonintlySign si=settlementJonintlySignDao.findById(projId);//会签
		
		ConstructionPlan cp=constructionPlanDao.viewPlanById(projId);
		
		SettlementDeclaration sd=settlementDeclarationDao.findByProjId(projId);
		
		SubContract scContract=subContractDao.findByProjId(projId);
		SubBudget subBudget = subBudgetDao.findByProjId(projId);
		
		if(si!=null && pro!=null){
			si.setDeptName(pro.getDeptName());
			si.setCorpName(pro.getCorpName());
			si.setProjStatusId(pro.getProjStatusId());
			si.setCuName(cp.getCuName());
			si.setContributionModeDes(pro.getContributionModeDes());
			if(StringUtils.isNotBlank(pro.getProjectType())){
				ProjectType projectType = projectTypeDao.get(pro.getProjectType());
				if(projectType!=null){
					si.setContractType(projectType.getContractType());
				}
			}
			//用户出资，即使合同签订的是甲供主材，也不需要物资公司在结算汇签上签字
			if(scContract!=null && !ContributionMode.CUST_TYPE.equals(pro.getContributionMode())){
				si.setNailMaterial(scContract.getContractMethod());
			}else{
				si.setNailMaterial(SubContractMethodEnum.OTHER.getValue());
			}
			if(subBudget.getCostType().equals("1")){
				si.setBudgetCost(subBudget.getTotalCost());			//施工预算合计清单计价
			}else{
				si.setBudgetCost(subBudget.getTotalQuota());			//施工预算合计清单计价
				
			}
			return si;
		}else if(pro!=null){
			si=new SettlementJonintlySign();
			si.setDeptName(pro.getDeptName());
			si.setCorpName(pro.getCorpName());
			si.setProjName(pro.getProjName());
			si.setProjStatusId(pro.getProjStatusId());
			si.setCuName(cp.getCuName());
			si.setContributionModeDes(pro.getContributionModeDes());
			si.setProjId(pro.getProjId());
			si.setProjectTypeDes(pro.getProjectTypeDes());
			si.setProjNo(pro.getProjNo());
			si.setProjScaleDes(pro.getProjScaleDes());
			si.setCorpId(pro.getCorpId());
			if(sd!=null){
				si.setSendDeclarationCost(sd.getSendDeclarationCost());
				//判空
				if(sd.getEndDeclarationCost()!=null){
					si.setEndDeclarationCost(sd.getEndDeclarationCost());
					si.setEndDCLegalAmount(MoneyConverter.Num2RMB(sd.getEndDeclarationCost().doubleValue()));
				}
			}
			
			if(StringUtils.isNotBlank(pro.getProjectType())){
				ProjectType projectType = projectTypeDao.get(pro.getProjectType());
				if(projectType!=null){
					si.setContractType(projectType.getContractType());
				}
			}
			if(StringUtil.isNotBlank(scContract.getContractMethod())){
				si.setMaterialProvide(scContract.getContractMethod());
			}
			
			//用户出资，即使合同签订的是甲供主材，也不需要物资公司在结算汇签上签字
			if(scContract!=null && !ContributionMode.CUST_TYPE.equals(pro.getContributionMode())){
				si.setNailMaterial(scContract.getContractMethod());
			}else{
				si.setNailMaterial(SubContractMethodEnum.OTHER.getValue());
			}
			if(subBudget.getCostType().equals("1")){
				si.setBudgetCost(subBudget.getTotalCost());			//施工预算合计清单计价
			}else{
				si.setBudgetCost(subBudget.getTotalQuota());			//施工预算合计清单计价
				
			}
			return si;
			
		}
		si=new SettlementJonintlySign();
		return si;
	}



	/**
	 * 结算汇签单保存
	 * @author fuliwei
	 * @createTime 2017年8月9日
	 * @param 
	 * @return
	 */
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void saveSettlementJonintlySign(SettlementJonintlySign settlementJonintlySign) throws Exception {
		boolean flag = false;
		if(StringUtils.isBlank(settlementJonintlySign.getSjsId())){
			flag = true;
			settlementJonintlySign.setSjsId(IDUtil.getUniqueId(Constants.MODULE_CODE_SETTLEMENT));
		}
		settlementJonintlySign.setSignStatus("0");
		settlementJonintlySign.setIsPrint(ContractIsPrintEnum.HAVE_NOT_PRINT.getValue());
		if("1".equals(settlementJonintlySign.getFlag())){
			settlementJonintlySign.setSignStatus("1");
			settlementJonintlySign.setFinishDate(settlementJonintlySignDao.getDatabaseDate());//汇签时间
		}
		
		settlementJonintlySignDao.saveOrUpdate(settlementJonintlySign);
		
		List<Signature> signs=settlementJonintlySign.getSign();
		if(signs!=null && signs.size()>0){
			for(Signature sign:signs){
				sign.setDataType(SignDataTypeEnum.SETTLEMENT_JONINTLY_SIGN.getValue());
			}
			settlementJonintlySign.setSign(signs);
		}
		if (flag){//将终审确认时生成的签字通知的businessId更改为结算会签ID
			signNoticeDao.updateSignNotice(settlementJonintlySign.getSjsId(),settlementJonintlySign.getProjId(),signs.get(0).getDataType());
		}

		//签字保存
		signatureService.saveOrUpdateSign("menuId+menuNane+27",settlementJonintlySign.getSign(), settlementJonintlySign.getProjId(), settlementJonintlySign.getSjsId(), settlementJonintlySign.getClass().getName(), flag);

	}




	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void signSettlementJonintly(String sjsId) {
		SettlementJonintlySign settlementJonintlySign = settlementJonintlySignDao.get(sjsId);
		if(settlementJonintlySign!=null){
			settlementJonintlySign.setIsPrint(ContractIsPrintEnum.ALREADY_PRINT.getValue());
		}
		settlementJonintlySignDao.saveOrUpdate(settlementJonintlySign);
	}

}
