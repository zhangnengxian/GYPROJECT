package cc.dfsoft.project.biz.base.budget.service.impl;


import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import cc.dfsoft.project.biz.base.baseinfo.service.WorkFlowService;
import cc.dfsoft.project.biz.base.budget.dao.GovAuditCostDao;
import cc.dfsoft.project.biz.base.budget.entity.GovAuditCost;
import cc.dfsoft.project.biz.base.budget.enums.GovAuditCostTypeEnum;
import cc.dfsoft.project.biz.base.budget.service.GovAuditCostService;
import cc.dfsoft.project.biz.base.charge.service.AccrualsRecordService;
import cc.dfsoft.project.biz.base.constructmanage.dto.UploadResult;
import cc.dfsoft.project.biz.base.contract.dao.ContractDao;
import cc.dfsoft.project.biz.base.contract.entity.Contract;
import cc.dfsoft.project.biz.base.project.dao.AccessoryDao;
import cc.dfsoft.project.biz.base.project.dao.ProjectDao;
import cc.dfsoft.project.biz.base.project.entity.AccessoryList;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.project.enums.StepEnum;
import cc.dfsoft.project.biz.base.project.service.OperateRecordService;
import cc.dfsoft.project.biz.base.settlement.entity.SettlementDeclaration;
import cc.dfsoft.project.biz.base.settlement.service.SettlementAuditService;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.enums.WorkFlowActionEnum;
import cc.dfsoft.uexpress.common.util.FileUtil;
import cc.dfsoft.uexpress.common.util.IDUtil;
import cc.dfsoft.uexpress.common.util.StringUtil;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
/**
 * 
 * 描述:政府审定价业务实现类
 * @author liaoyq
 * @createTime 2017年9月8日
 */
@Service
@Transactional(readOnly = true,propagation=Propagation.REQUIRED)
public class GovAuditCostServiceImpl implements GovAuditCostService {
	
	@Resource
	GovAuditCostDao govAuditCostDao;
	
	@Resource
	ProjectDao projectDao;
	
	@Resource
	WorkFlowService workFlowService;
	
	@Resource
	OperateRecordService operateRecordService;
	
	@Resource
	AccessoryDao accessoryDao;

	/**结算定案表服务接口*/
	@Resource
	SettlementAuditService settlementAuditService;
	
	@Resource
	AccrualsRecordService accrualsRecordService;
	@Resource
	ContractDao contractDao;
	/**
	 * 更新审定价
	 * @author liaoyq
	 * @createtime 2017-9-8
	 * @return gacId 审定价记录ID
	 */
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public String updateGovAUditCost(GovAuditCost govAuditCost) {
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		govAuditCost.setGacStaffId(loginInfo!=null?loginInfo.getStaffId():""); //审定价登记人ID
		govAuditCost.setGacStaffName(loginInfo!=null?loginInfo.getStaffName():"");
		govAuditCost.setAuthorizedCostDate(govAuditCostDao.getDatabaseDate()); //审定价登记时间
		if(StringUtil.isBlank(govAuditCost.getGacId())){
			govAuditCost.setGacId(IDUtil.getUniqueId(Constants.MODULE_CODE_BUDGET));
			govAuditCost.setFlag("0"); //未推送状态
			govAuditCostDao.save(govAuditCost);
		}else{
			govAuditCostDao.updateNotNullProperties(govAuditCost, govAuditCost.getGacId());
		}
		return govAuditCost.getGacId();
	}

	/**
	 * 根据工程ID和审定价类型查询审定价详述
	 * @author liaoyq
	 * @createtime 2017-9-8
	 */
	@Override
	public GovAuditCost queryByProjIdAndType(String projId, String gacType) {
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		GovAuditCost govAuditCost = govAuditCostDao.queryByProjIdAndType(projId,gacType);
		SettlementDeclaration settlementDeclaration = settlementAuditService.findByProjId(projId);
		if(govAuditCost==null){
			govAuditCost = new GovAuditCost();
			govAuditCost.setAuthorizedCostDate(govAuditCostDao.getDatabaseDate());
			govAuditCost.setGacStaffId(loginInfo!=null?loginInfo.getStaffId():""); //审定价登记人ID
			govAuditCost.setGacStaffName(loginInfo!=null?loginInfo.getStaffName():"");
		}
		if(null!=settlementDeclaration){
			govAuditCost.setEndDeclarationCost(settlementDeclaration.getEndDeclarationCost());
		}
		return govAuditCost;
	}
	/**
	 * 保存或更新审定价记录(包含附件上传)
	 * @author liaoyq
	 * @createTime 2017-9-9
	 * @param request
	 * @param uploadResult
	 * @param files 
	 * @return 记录主键ID
	 * @throws IOException 
	 * @throws IllegalStateException 
	 */
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public String updateBudgetFile(HttpServletRequest request,
			UploadResult uploadResult , MultipartFile[] files) throws IllegalStateException, IOException {
		JSONObject jo = new JSONObject();
		GovAuditCost govAuditCost = JSON.parseObject(uploadResult.getResult(), GovAuditCost.class);
		
		String name=govAuditCost.getDrawName();//附件名称
		if(files!=null){
			govAuditCost.setDrawName(files[0].getOriginalFilename());//附件原始名称
		}
		//保存到预算表
		String gacId = this.updateGovAUditCost(govAuditCost);
		if(StringUtil.isBlank(name)){
			List<AccessoryList> accs =accessoryDao.queryAccessoryByBus(govAuditCost.getGacId(), "");
			if(accs!=null&&accs.size()>0){
				if(files!=null){
					FileUtil.deleteFile(Constants.DISK_PATH+accs.get(0).getAlPath());//删除以前的附件
				}
				accessoryDao.delete(accs.get(0));//删除附件
			}
			if(files!=null){
				AccessoryList acc = new AccessoryList();
				String path=FileUtil.uploadFile(request, uploadResult.getAlPath(), files);//路径
				String fileName = files[0].getOriginalFilename();               //文件名全名（例：文件名.png）
				//String filePath= Constants.FIRST_DISK_PATH + path + fileName;
				String filePath= Constants.FIRST_DISK_PATH + path ;
				String name1 = fileName.substring(0,fileName.lastIndexOf("."));//文件去掉格式后的名（从第0位截取，到文件格式(例：“.png“）前的点结束）
				String fileType=fileName.substring(fileName.lastIndexOf(".")+1,fileName.length());//文件格式（例：“.png”）
				LoginInfo loginInfo = SessionUtil.getLoginInfo();
				acc.setAlId(IDUtil.getUniqueId(Constants.FILE_CODE_ACCESS));   //生成附件Id
				acc.setProjId(govAuditCost.getProjId());                                 //项目Id
				acc.setProjNo(govAuditCost.getProjNo());                                 //项目编号
//				acc.setProjLtypeId(co.getProjLtypeId());                       //工程大类
				acc.setAlTypeId(fileType);                                     //附件格式（例：“.png"）
				acc.setStepId(govAuditCost.getStepId());                                 //步骤Id
				acc.setAlName(name1);                                          //附件名称
				acc.setAlPath(filePath);                                       //附件路径
				acc.setAlOperateCsrId(loginInfo.getStaffId());                 //操作人Id
				acc.setAlOperateCsr(loginInfo.getStaffName());                 //操作人姓名
				acc.setAlOperateTime(accessoryDao.getDatabaseDate());          //生成操作时间
//				acc.setSourceType(AccessorySourceEnum.CHANGE_FILE.getValue()); //附件来源类型
				acc.setBusRecordId(govAuditCost.getGacId());                              //业务单Id
				accessoryDao.save(acc);
			}
		}
		return gacId;
	}

	/**
	 * 推送审定价
	 * 1.修改审定价推送状态
	 * 2.修改工程状态
	 * 3.生成操作记录
	 * @author liaoyq
	 * @createTime 2017-9-9
	 * @param id 审定价记录id
	 * @gacType 审定价类型
	 */
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void pushGovAuditCost(GovAuditCost govAuditCost) {
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		String gacId="";//还没有登记审定价
		if(StringUtil.isBlank(govAuditCost.getGacId())){
			//登记审定价
			gacId = this.updateGovAUditCost(govAuditCost);
			govAuditCost.setGacId(gacId);
		}
		//修改审定价的推送状态
		govAuditCost.setFlag("1");
		govAuditCost.setPushStaffId(loginInfo.getStaffId());
		govAuditCost.setPushDate(govAuditCostDao.getDatabaseDate());
		govAuditCostDao.update(govAuditCost);
	    
		//更新工程信息
		Project pro = projectDao.get(govAuditCost.getProjId());
		String statusId = "";
		String stepId = "";
		String orId = "";
		String stepMessage="";
		if(pro!=null){
			//虚拟合同的todo:
			if(GovAuditCostTypeEnum.BUDGET.getValue().equals(govAuditCost.getGacType())){//预算审定价
				//工程状态
				 statusId = workFlowService.queryProjStatusId(pro.getCorpId(), pro.getProjectType(),pro.getContributionMode(), WorkFlowActionEnum.BUDGET_GOV_AUDIT_COST.getActionCode(), true);
				 orId = IDUtil.getUniqueId(Constants.MODULE_CODE_BUDGET);//生成唯一ID
				 stepId = StepEnum.BUDGET_GOV_AUDIT_COST.getValue();
				 stepMessage = StepEnum.BUDGET_GOV_AUDIT_COST.getMessage();
			}else{
				//工程状态
				 statusId = workFlowService.queryProjStatusId(pro.getCorpId(), pro.getProjectType(),pro.getContributionMode(), WorkFlowActionEnum.SETTLEMENT_GOV_AUDIT_COST.getActionCode(), true);
				 orId = IDUtil.getUniqueId(Constants.MODULE_CODE_SETTLEMENT);//生成唯一ID
				 stepId = StepEnum.SETTLEMENT_GOV_AUDIT_COST.getValue();
				 stepMessage = StepEnum.SETTLEMENT_GOV_AUDIT_COST.getMessage();
				 //如果审定价与安装合同价不一致，结算审定价登记后，根据结算审定价修改流水信息
				 Contract contract = contractDao.viewContractByprojId(govAuditCost.getProjId());
				 if(contract!=null && contract.getContractAmount()!=null && govAuditCost.getAuthorizedCost()!=null && govAuditCost.getAuthorizedCost().compareTo(contract.getContractAmount())!=0){
					 accrualsRecordService.updateByGovAuditCost(govAuditCost);
				 }
			}
			pro.setProjStatusId(statusId);	
			
			//形成操作记录
			String todoer=operateRecordService.createOperateRecord(orId, pro.getProjId(), pro.getProjNo(),stepId, stepMessage, "");
			pro.setToDoer(todoer);//待办人
			projectDao.update(pro);
		}
	}

}
