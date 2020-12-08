package cc.dfsoft.project.biz.base.contract.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.gexin.rp.sdk.http.utils.Constant;

import cc.dfsoft.project.biz.base.baseinfo.dao.DocTypeDao;
import cc.dfsoft.project.biz.base.baseinfo.dao.SystemSetDao;
import cc.dfsoft.project.biz.base.baseinfo.entity.DocType;
import cc.dfsoft.project.biz.base.baseinfo.enums.AssistProgressTypeEnum;
import cc.dfsoft.project.biz.base.baseinfo.enums.WorkFlowTypeEnum;
import cc.dfsoft.project.biz.base.baseinfo.service.DocTypeService;
import cc.dfsoft.project.biz.base.budget.dao.BudgetDao;
import cc.dfsoft.project.biz.base.budget.dao.ChangeManagementDao;
import cc.dfsoft.project.biz.base.budget.entity.Budget;
import cc.dfsoft.project.biz.base.charge.service.AccrualsRecordService;
import cc.dfsoft.project.biz.base.common.entity.SystemSet;
import cc.dfsoft.project.biz.base.constructmanage.entity.ChangeManagement;
import cc.dfsoft.project.biz.base.constructmanage.enums.StepOutWorkflowEnum;
import cc.dfsoft.project.biz.base.contract.dao.ContractDao;
import cc.dfsoft.project.biz.base.contract.dao.SupplementalContractDao;
import cc.dfsoft.project.biz.base.contract.dao.SupplementalContractModifyHistoryDao;
import cc.dfsoft.project.biz.base.contract.dto.SupplementalContractQueryReq;
import cc.dfsoft.project.biz.base.contract.dto.supplementalContractModifyHistoryReq;
import cc.dfsoft.project.biz.base.contract.entity.Contract;
import cc.dfsoft.project.biz.base.contract.entity.SupplementalContract;
import cc.dfsoft.project.biz.base.contract.entity.SupplementalContractModifyhistory;
import cc.dfsoft.project.biz.base.contract.enums.ContractIsPrintEnum;
import cc.dfsoft.project.biz.base.contract.enums.IsSignEnum;
import cc.dfsoft.project.biz.base.contract.enums.ModifyStateEnum;
import cc.dfsoft.project.biz.base.contract.enums.ModifyStatusEnum;
import cc.dfsoft.project.biz.base.contract.enums.OperateTypeEnum;
import cc.dfsoft.project.biz.base.contract.enums.supplementalContractIsAuditEnum;
import cc.dfsoft.project.biz.base.contract.service.SupplementalContractService;
import cc.dfsoft.project.biz.base.design.enums.DesignChangeStateEnum;
import cc.dfsoft.project.biz.base.project.dao.ManageRecordDao;
import cc.dfsoft.project.biz.base.project.dao.NoticeDao;
import cc.dfsoft.project.biz.base.project.dao.OperateRecordDao;
import cc.dfsoft.project.biz.base.project.dao.ProjectDao;
import cc.dfsoft.project.biz.base.project.dto.ManageRecordQueryReq;
import cc.dfsoft.project.biz.base.project.entity.ManageRecord;
import cc.dfsoft.project.biz.base.project.entity.Notice;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.project.enums.MrResultEnum;
import cc.dfsoft.project.biz.base.project.enums.NoticeAuditTypeEnum;
import cc.dfsoft.project.biz.base.project.enums.NoticeMenuEnum;
import cc.dfsoft.project.biz.base.project.enums.NoticeStateEnum;
import cc.dfsoft.project.biz.base.project.service.ManageRecordService;
import cc.dfsoft.project.biz.base.project.service.OperateRecordLogService;
import cc.dfsoft.project.biz.base.project.service.OperateRecordService;
import cc.dfsoft.project.biz.ifs.material.service.MaterialNcService;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.biz.sys.dept.entity.Staff;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.util.BeanUtil;
import cc.dfsoft.uexpress.common.util.IDUtil;
import cc.dfsoft.uexpress.common.util.StringUtil;
import net.sf.json.JSONSerializer;

@Service
@Transactional(readOnly = true,propagation=Propagation.REQUIRED)
public class SupplementalContractServiceImpl implements SupplementalContractService{
	
	@Resource
	ContractDao contractDao;
	
	@Resource
	SupplementalContractDao supplmentalContractDao;
	
	/**收付流水*/
	@Resource
	AccrualsRecordService accrualsRecordService;
	
	@Resource
	MaterialNcService materialNcService;
	@Resource
	OperateRecordService operateRecordService;
	
	/** 预算Dao */
	@Resource
	BudgetDao budgetdao;
	
	/** 工程Dao */
	@Resource
	ProjectDao projectDao;
	/**设计变更Dao*/
	@Resource
	ChangeManagementDao changeManagementDao;
	/**通知Dao*/
	@Resource
	NoticeDao noticeDao;
	/**单据类型DAO处理*/
	@Resource
	DocTypeDao docTypeDao;
	/**业务操作记录*/
	@Resource
	OperateRecordDao operateRecordDao;
	@Resource
	ManageRecordDao managerecorddao;
	@Resource
	SystemSetDao systemSetDao;
	@Resource
	SupplementalContractDao supplementalContractDao;
	@Resource
	ManageRecordService manageRecordService;
	@Resource
	SupplementalContractModifyHistoryDao supplementalContractModifyHistoryDao; 
	@Resource
	OperateRecordLogService operateRecordLogService;
	
	@Resource
	DocTypeService docTypeService;
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public String saveSupplementalContract(SupplementalContract supplementalContract) throws Exception {
		if(StringUtil.isBlank(supplementalContract.getScId())){
			supplementalContract.setScId(IDUtil.getUniqueId(Constants.MODULE_CODE_CONTRACT)); //生成主键ID
		}
		Project pro=projectDao.get(supplementalContract.getProjId());
		if("1".equals(supplementalContract.getFlag())){  //推送补充协议，1表示推送
			List<Map<String,Object>> consList = Constants.getConstantsMapByKey(Constants.SUPPLEMENTAL_CONTRACT_AUDIT);
			String status = "";
			if(consList != null && consList.size()>0){
				for(Map<String,Object> m :consList){
					if(pro.getCorpId().equals(String.valueOf(m.get("ID")))){
						status = String.valueOf(m.get("CNVALUE"));
						break;
					}
				}
			}
			if("".equals(status)){   //需审核
				supplementalContract.setIsAudit(supplementalContractIsAuditEnum.ALREADY_SIGN.getValue());//标记为待审核
				supplmentalContractDao.saveOrUpdate(supplementalContract);//保存协议
			}else if(supplementalContractIsAuditEnum.HAVE_PASS.getValue().equals(status)){  //无需审核
				manageRecordService.supplementalContractSave(supplementalContract);  //推送时无需审核直接生成流水和调用接口
			}
		}else{
			supplmentalContractDao.saveOrUpdate(supplementalContract);//保存协议
		}
		
		//flag是标识，1是推送
		if("1".equals(supplementalContract.getFlag())){
			//推送的
			//标记已签订
			Budget budget= budgetdao.get(supplementalContract.getBudgetId());
			budget.setIsSignSuContrct(IsSignEnum.ALRRADY_SIGN.getValue());//标记已签订
			budgetdao.update(budget);
			//补充协议签订推送时，通过预算id去查找设计变更，将变更状态修改为"已签订补充协议"
			ChangeManagement changeManagement=changeManagementDao.get(budget.getCmId());//获得设计变更
			changeManagement.setDesignChangeType(DesignChangeStateEnum.ALREADY_SIGN_SUPPLEMENT.getValue());
			changeManagementDao.update(changeManagement);
			//生成流水
			/*if(pro != null){
				String arId = IDUtil.getUniqueId(Constants.MODULE_CODE_COST);
				accrualsRecordService.insertAccrualsRecord(pro,arId, CollectionTypeEnum.SUPPLEMENTAL_CONTRACT.getValue(),Integer.parseInt(ARFlagEnum.RECEIVE_ACCOUNT.getValue()), supplementalContract.getScAmount(),null);
			}*/
			//待办todo
			operateRecordService.cerateCurOperateRecord(pro,changeManagement.getDesignChangeType(), WorkFlowTypeEnum.ASSIST_PROGRESS.getValue(), AssistProgressTypeEnum.CHANGE_PROGRESS.getValue(),changeManagement.getCmId(),new Staff(),null,false);
		
			//如果需要审核,生成通知
			if(supplementalContractIsAuditEnum.ALREADY_SIGN.getValue().equals(supplementalContract.getIsAudit())){
				changeManagement.setDesignChangeType(DesignChangeStateEnum.WAIT_AUDIT.getValue());
				//补充协议审核待办todo
				operateRecordService.cerateCurOperateRecord(pro,changeManagement.getDesignChangeType(), WorkFlowTypeEnum.ASSIST_PROGRESS.getValue(), AssistProgressTypeEnum.CHANGE_PROGRESS.getValue(),changeManagement.getCmId(),new Staff(),"1",true);
			}
		}
		return Constants.OPERATE_RESULT_SUCCESS;
	}
	
	@Override
	public List<SupplementalContract> findByScNo(String scNo) {
		return supplmentalContractDao.findByScNo(scNo);
	}
	/**
	 * 补充协议列表条件查询
	 */
	@Override
	public Map<String, Object> querySupplementalContract(SupplementalContractQueryReq supplementalContractQueryReq)
			throws ParseException {
		//return supplementalContractDao.querySupplementalContract(supplementalContractQueryReq);
		Map<String, Object> map=supplementalContractDao.querySupplementalContract(supplementalContractQueryReq);
		
		List<SupplementalContract> list=(List<SupplementalContract>) map.get("data");
		
		if(list!=null && list.size()>0){
			for(SupplementalContract supplementalContract:list){
					Project project=projectDao.get(supplementalContract.getProjId());
					if(project!=null){
						supplementalContract.setProjectType(project.getProjectType());
					}
				
			}
		}
		return map;
	}
	
	/**
	 * 通过预算id查询补充协议
	 * @author fuliwei
	 * @createTime 2017-1-25
	 * @param budgetId
	 * @return SupplementalContract
	 */
	@Override
	public SupplementalContract viewSupplementalContract(String budgetId) {
		LoginInfo login=SessionUtil.getLoginInfo();
		SupplementalContract sc=supplmentalContractDao.viewSupplementalContract(budgetId);
		Budget budget= budgetdao.get(budgetId);
		Project pro =null;
		Contract con = null;
		if(budget != null){
			 pro=projectDao.get(budget.getProjId());
		}
		if(sc != null && pro!=null && budget!=null){
			sc.setProjectTypeDes(pro.getProjectTypeDes());
			sc.setContributionModeDes(pro.getContributionModeDes());
			sc.setDeptName(pro.getDeptName());
			sc.setIsSignSuContrct(budget.getIsSignSuContrct());
			return sc;
		}else{
			if(budget != null){
				 con=contractDao.viewContractByprojId(budget.getProjId());
			}
			if(budget != null && pro != null && con != null){
				sc=new SupplementalContract();
				sc.setProjId(pro.getProjId());//工程id
				sc.setBudgetId(budgetId);
				sc.setProjNo(pro.getProjNo());//工程编号
				sc.setProjName(pro.getProjName());//工程名称
				sc.setProjAddr(pro.getProjAddr());//工程地点
				sc.setProjScaleDes(pro.getProjScaleDes());//工程规模
				sc.setConAgent(login.getStaffName());//经办人
				sc.setScAmount(budget.getBudgetTotalCost());//协议价款
				sc.setProjectTypeDes(pro.getProjectTypeDes());
				sc.setContributionModeDes(pro.getContributionModeDes());
				sc.setDeptName(pro.getDeptName());
				sc.setCustName(con.getCustName());//客户名称
				sc.setCustPhone(con.getCustPhone());//客户联系方式
				sc.setGasComp(con.getGasComp());//燃气公司
				sc.setGasCompPhone(con.getGasCompPhone());
				sc.setConNo(con.getConNo());//合同编号
				sc.setConId(con.getConId());//合同ID
				List <SupplementalContract> list =this.findByConID(con.getConId());
				if (null!=list) {
					sc.setScNo(con.getConNo().substring(0, con.getConNo().length()-1)+(list.size()+1));
				}
				sc.setScType(con.getContractType());
				sc.setIsPrint(ContractIsPrintEnum.HAVE_NOT_PRINT.getValue());
				return sc;
			}
		}
		return null;
	}

	@Override
	public List<SupplementalContract> findByConID(String conId) {
		return supplmentalContractDao.findByConID(conId);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public String signSupplementalContract(String scId) {
		SupplementalContract supplmentalContrac=supplmentalContractDao.get(scId);
		if(supplmentalContrac!=null){
			//标记已打印
			supplmentalContrac.setIsPrint(ContractIsPrintEnum.ALREADY_PRINT.getValue());
			supplmentalContractDao.update(supplmentalContrac);
		}
		return Constants.OPERATE_RESULT_SUCCESS;
	}
	@Override
	public Map<String, Object> querySupplementalforAudit(SupplementalContractQueryReq supplementalContractReq) throws ParseException {
		  SystemSet systemSet=systemSetDao.get("stepId", supplementalContractReq.getStepId());
		  if(systemSet!=null&&StringUtil.isNotBlank(systemSet.getTimeSpan())){
			  supplementalContractReq.setTimeLimit(Integer.parseInt(systemSet.getTimeSpan()));
		  }
		  supplementalContractReq.setIsAudit("1");//推送过的
		  Map<String,Object> result = supplementalContractDao.querySupplementalContract(supplementalContractReq);
		  //按步骤id进行查询 获取单据类型
		  //DocType docType = docTypeDao.findByStepId(StepOutWorkflowEnum.SUPPLEMENTAL_CONTRACT_AUDIT.getValue());
		 // String grade = docType==null?"":docType.getGrade();
		  String grade="";
		  List<SupplementalContract> data = (List<SupplementalContract>) result.get("data");
		  if(data!=null && data.size()>0){

			/**
			 * -1 未审核  0 审核未通过  1 审核通过  2待审核
			 * 若该 审核为三级审核，且一级审核通过、二级审核未通过，三级审核待审核，则键值对：{"level1":"1","level2":"0","level3":"2"};
			 * */
			//遍历循环  设置审核级别
			for(int i = 0;i<data.size();i++){
				Project pro = projectDao.get(data.get(i).getProjId());
				data.get(i).setProjectTypeDes(pro.getProjectTypeDes());
				data.get(i).setContributionModeDes(pro.getContributionModeDes());
				
				DocType docType = docTypeService.findByStepId(StepOutWorkflowEnum.SUPPLEMENTAL_CONTRACT_AUDIT.getValue(),pro.getCorpId(),pro.getProjectType(),pro.getContributionMode());
				if(docType!=null && StringUtils.isNotBlank(docType.getGrade())){
					grade=docType.getGrade();
				}else{
					grade="0";
				}
				data.get(i).setLevel(grade);// 设置审核总级数（合同审核2级审核）
				Map<String,String> levelBtn = new HashMap();
				//以下循环，遍历出默认应该几级审核，审核状态分别为什么，例如三级审核，则一级待审、二级未审、三级未审{"level1":"2","level2":"-1","level3":"-1"}；
				for(int n=1;n<Integer.parseInt(grade)+1;n++){
					if(n==1){
						levelBtn.put("level"+n, "2");// 待审
					}else{
						levelBtn.put("level"+n, "-1");//未审
					}
				}
				ManageRecordQueryReq mrq = new ManageRecordQueryReq();
				mrq.setBusinessOrderId(data.get(i).getScId());
				mrq.setStepId(StepOutWorkflowEnum.SUPPLEMENTAL_CONTRACT_AUDIT.getValue());
				mrq.setProjId(data.get(i).getProjId());
				//List<ManageRecord> mrls  = (List<ManageRecord>) managerecorddao.queryManageRecord(mrq).get("data");
				List<ManageRecord> mrls = managerecorddao.findByStepIdBusinessOrderId(data.get(i).getScId(),StepOutWorkflowEnum.SUPPLEMENTAL_CONTRACT_AUDIT.getValue(),MrResultEnum.PASSED.getValue());
				if(mrls!=null && mrls.size()>0){
					//遍历循环，获取审核是否通过
					for(ManageRecord mr:mrls){
						levelBtn.put("level"+mr.getMrAuditLevel(),mr.getMrResult());
					}
					if(mrls.size()<Integer.parseInt(grade)){
						levelBtn.put("level"+(mrls.size()+1), "2");
					}
				}
				data.get(i).setMrAuditLevel(JSONSerializer.toJSON(levelBtn).toString());
			}
			result.put("data", data);
		
		}
		return result;
	}

	@Override
	public SupplementalContract findByScId(String scId) {
		return supplementalContractDao.get(scId);
		
	}

	/**
	 * 已审核的补充协议
	 */
	@Override
	public List<SupplementalContract> findByProjId(String projId) {
		return supplementalContractDao.findByProjId(projId);
	}
    /**
     * 查询已签补充协议
     */
	@Override
	public Map<String, Object> queryAgreementList(SupplementalContractQueryReq supplementalContract)
			throws ParseException {
		// TODO Auto-generated method stub
		Map<String, Object> map = supplementalContractDao.queryAgreementList(supplementalContract);
		return map;
	}
  /**
   * 通过补充协议scId查询详述
   */
	@Override
	public SupplementalContract searchSupplementalContract(String scId) throws Exception{
		// TODO Auto-generated method stub
		//通过scId得到实体
		SupplementalContract supplementalContract = supplementalContractDao.get(scId);
		//通过budgetId得到实体
		Budget budget= budgetdao.get(supplementalContract.getBudgetId());
		//通过工程Id得到实体
		Project pro=projectDao.get(budget.getProjId());
		if(supplementalContract != null && pro!=null && budget!=null){
			supplementalContract.setProjectTypeDes(pro.getProjectTypeDes());
			supplementalContract.setContributionModeDes(pro.getContributionModeDes());
			supplementalContract.setDeptName(pro.getDeptName());
			supplementalContract.setIsSignSuContrct(budget.getIsSignSuContrct());
			return supplementalContract;
		}
		return null;
	}
    /**
     * 保存修改后的补充协议
     * wang.hui.jun
     * 2018.6.15
     */
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public String saveSupplementalAgreement(SupplementalContract supplementalContract) throws ParseException { 
	    // TODO Auto-generated method stub
    	SupplementalContract suc = supplementalContractDao.get(supplementalContract.getScId());  //取得实体
		//生成操作日志
		StringBuffer operateContent= new StringBuffer("补充协议修改,");
		if((suc.getScAmount()!=null)){
			if(suc.getScAmount().equals((supplementalContract.getScAmount()))){
				operateContent.append("协议价款不变");
			}else{
				operateContent.append("协议价款由").append(suc.getScAmount().toString()).append("修改为").append(supplementalContract.getScAmount().toString())
						.append("");
			}
		}else{
			if(supplementalContract.getScAmount()!=null){
				operateContent.append("协议价款为").append(supplementalContract.getScAmount().toString());
			}
		}
			
		String orlId = IDUtil.getUniqueId(Constants.MODULE_CODE_CONTRACT);//生成唯一ID
		operateRecordLogService.createOperateRecordLog(orlId,OperateTypeEnum.SUPPLECON_MODIFY.getValue(),suc.getScId(),operateContent.toString());	
		//生成合同日志
		List<SupplementalContractModifyhistory> logList = new ArrayList<SupplementalContractModifyhistory>();
		//修改前补充协议日志
		SupplementalContractModifyhistory supplConLogBefore = new SupplementalContractModifyhistory();  //创建补充协议日志
		BeanUtil.copyNotNullProperties(supplConLogBefore,suc);  //进行对象转换
		if(StringUtils.isBlank(supplConLogBefore.getScmId())){  //如果scmId为空
			supplConLogBefore.setScmId(IDUtil.getUniqueId(Constants.MODULE_CODE_CONTRACT));  //生成主键scmId
		}
		supplConLogBefore.setModifyState(ModifyStateEnum.BEFOR_MODIFY.getValue());//修改前标记
		supplConLogBefore.setOrlId(orlId);
		logList.add(supplConLogBefore);
		//修改后补充协议
		SupplementalContractModifyhistory supplConLogAfter = new SupplementalContractModifyhistory();
		BeanUtil.copyNotNullProperties(supplConLogAfter, supplementalContract);
		supplConLogAfter.setScmId(IDUtil.getUniqueId(Constants.MODULE_CODE_CONTRACT));
		supplConLogAfter.setOrlId(orlId);
		supplConLogAfter.setModifyState(ModifyStateEnum.AFTER_MODIFY.getValue());//修改后标记
		logList.add(supplConLogAfter);
		//生成补充协议修改日志
		supplementalContractModifyHistoryDao.batchInsertObjects(logList);
		Project pro = projectDao.get(supplementalContract.getProjId());
		//supplmentalContractDao.evict(suc); //删除对象,避免session同时保存标示符相同的对象在update或者save时出错。
		List<Map<String,Object>> consList = Constants.getConstantsMapByKey(Constants.AGREEMENT_MODIFY_AUDIT);
		String status = "";
		if(consList != null && consList.size()>0){
			for(Map<String,Object> m :consList){
				if(pro.getCorpId().equals(String.valueOf(m.get("ID")))){
					status = String.valueOf(m.get("CNVALUE"));
					break;
				}
			}
		}
		if("".equals(status)){  //需审核
			supplementalContract.setModifyStatus(ModifyStatusEnum.TO_AUDIT.getValue());
		}else if(ModifyStatusEnum.AUDIT_PASS.getValue().equals(status)){  //无需审核
			try {
				manageRecordService.supplementalContractModifySave(supplementalContract);
				return Constants.OPERATE_RESULT_SUCCESS;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.err.println("补充协议推送保存时失败！");
				return Constants.OPERATE_RESULT_FAILURE;
			}
		}
        //推送协议时,添加审核通知
        if(supplementalContract.getFlag().equals("1") && !(ModifyStatusEnum.AUDIT_PASS.getValue().equals(status))){
			//待审核状态
			if(ModifyStatusEnum.TO_AUDIT.getValue().equals(supplementalContract.getModifyStatus())){
				managerecorddao.batUpdateHistoryRecord(supplementalContract.getProjId(),StepOutWorkflowEnum.SUPPLEMENTAL_CONTRACT_MODIFY_AUDIT.getValue()); //推送补充协议修改审核时，之前的审核应置为无效
				//生成审核待办todo:
				Staff staff = new Staff();
				operateRecordService.cerateCurOperateRecord(pro, StepOutWorkflowEnum.SUPPLEMENTAL_CONTRACT_MODIFY_AUDIT.getValue(), WorkFlowTypeEnum.ASSIST_PROGRESS.getValue(), AssistProgressTypeEnum.SUPPL_CONTRAT_UPDATE.getValue(),supplementalContract.getScId(),staff,"1",true);
				
			}
        }
        //更新协议
        supplmentalContractDao.update(supplementalContract);
	    return Constants.OPERATE_RESULT_SUCCESS;
      }

	@Override
	public Map<String, Object> queryModifyHistory(String scId,supplementalContractModifyHistoryReq modifyhistoryReq) throws Exception {
		Map map = supplementalContractModifyHistoryDao.queryModifyHistory(scId,modifyhistoryReq);
		
	   List<SupplementalContractModifyhistory> list=(List<SupplementalContractModifyhistory>) map.get("data");
	   SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		if(list!=null && list.size()>0){
			for (int i = 0; i <list.size(); i++) {
			  String dateString = formatter.format(list.get(i).getSignDate());
			  list.get(i).setFromatDate(dateString);
			}
		}
		return map;
	}
    /**
     * 查询补充协议修改列表
     */
	@Override
	public Map<String, Object> querySupplementalModify(SupplementalContractQueryReq suppleConReq) throws Exception {
		// TODO Auto-generated method stub
		 SystemSet systemSet=systemSetDao.get("stepId",suppleConReq.getStepId());
		  if(systemSet!=null && StringUtil.isNotBlank(systemSet.getTimeSpan())){
			  suppleConReq.setTimeLimit(Integer.parseInt(systemSet.getTimeSpan()));
		  }
		  //suppleConReq.setIsAudit("1");
		  suppleConReq.setModifyStatus(ModifyStatusEnum.TO_AUDIT.getValue());//待修改审核的
		  Map<String,Object> result = supplementalContractDao.querySupplementalModify(suppleConReq);
		  //按步骤id进行查询 获取单据类型
		  //DocType docType = docTypeDao.findByStepId(StepOutWorkflowEnum.SUPPLEMENTAL_CONTRACT_MODIFY_AUDIT.getValue());
		  //String grade = docType==null?"":docType.getGrade();   //取得审核级别
		  String grade="";
		  List<SupplementalContract> data = (List<SupplementalContract>) result.get("data");
		  if(data!=null && data.size()>0){

			/**
			 * -1 未审核  0 审核未通过  1 审核通过  2待审核
			 * 若该 审核为三级审核，且一级审核通过、二级审核未通过，三级审核待审核，则键值对：{"level1":"1","level2":"0","level3":"2"};
			 * */
			//遍历循环  设置审核级别
			for(int i = 0;i<data.size();i++){
				Project pro = projectDao.get(data.get(i).getProjId());
				data.get(i).setProjectTypeDes(pro.getProjectTypeDes());
				data.get(i).setContributionModeDes(pro.getContributionModeDes());
				
				DocType docType = docTypeService.findByStepId(StepOutWorkflowEnum.SUPPLEMENTAL_CONTRACT_MODIFY_AUDIT.getValue(),pro.getCorpId(),pro.getProjectType(),pro.getContributionMode());
				if(docType!=null && StringUtils.isNotBlank(docType.getGrade())){
					grade=docType.getGrade();
				}else{
					grade="0";
				}
				
				data.get(i).setLevel(grade);	//设置审核总级数（合同审核2级审核）
				Map<String,String> levelBtn = new HashMap();
				//以下循环，遍历出默认应该几级审核，审核状态分别为什么，例如三级审核，则一级待审、二级未审、三级未审{"level1":"2","level2":"-1","level3":"-1"}；
				for(int n=1;n<Integer.parseInt(grade)+1;n++){
					if(n==1){
						levelBtn.put("level"+n, "2");// 待审
					}else{
						levelBtn.put("level"+n, "-1");//未审
					}
				}
				ManageRecordQueryReq mrq = new ManageRecordQueryReq();
				mrq.setBusinessOrderId(data.get(i).getScId());
				mrq.setStepId(StepOutWorkflowEnum.SUPPLEMENTAL_CONTRACT_MODIFY_AUDIT.getValue());
				mrq.setProjId(data.get(i).getProjId());
				//List<ManageRecord> mrls  = (List<ManageRecord>) managerecorddao.queryManageRecord(mrq).get("data");
				List<ManageRecord> mrls = managerecorddao.findByStepIdBusinessOrderId(data.get(i).getScId(),StepOutWorkflowEnum.SUPPLEMENTAL_CONTRACT_MODIFY_AUDIT.getValue(),MrResultEnum.PASSED.getValue());
				if(mrls!=null && mrls.size()>0){
					//遍历循环，获取审核是否通过
					for(ManageRecord mr:mrls){
						levelBtn.put("level"+mr.getMrAuditLevel(),mr.getMrResult());
					}
					if(mrls.size()<Integer.parseInt(grade)){
						levelBtn.put("level"+(mrls.size()+1), "2");
					}
				}
				data.get(i).setMrAuditLevel(JSONSerializer.toJSON(levelBtn).toString());
			}
			result.put("data", data);
		
		}
		return result;
	}
	
}
