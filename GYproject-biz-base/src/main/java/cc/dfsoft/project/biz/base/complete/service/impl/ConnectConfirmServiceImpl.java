package cc.dfsoft.project.biz.base.complete.service.impl;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cc.dfsoft.project.biz.base.baseinfo.service.WorkFlowService;
import cc.dfsoft.project.biz.base.charge.service.AccrualsRecordService;
import cc.dfsoft.project.biz.base.complete.dao.DataFeedbackDao;
import cc.dfsoft.project.biz.base.complete.dao.FilingDataDao;
import cc.dfsoft.project.biz.base.complete.dto.CompletionTransferReq;
import cc.dfsoft.project.biz.base.complete.entity.FilingData;
import cc.dfsoft.project.biz.base.complete.service.ConnectConfirmService;
import cc.dfsoft.project.biz.base.contract.enums.ContractIsPrintEnum;
import cc.dfsoft.project.biz.base.project.dao.ProjectDao;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.project.enums.StepEnum;
import cc.dfsoft.project.biz.base.project.service.OperateRecordService;
import cc.dfsoft.project.biz.base.project.service.SignatureService;
import cc.dfsoft.project.biz.base.settlement.dao.DrawingApplyDao;
import cc.dfsoft.project.biz.base.settlement.dao.SettlementJonintlySignDao;
import cc.dfsoft.project.biz.base.settlement.entity.DrawingApply;
import cc.dfsoft.project.biz.base.settlement.entity.SettlementJonintlySign;
import cc.dfsoft.project.biz.base.settlement.enums.AuditResultEnum;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.constant.DataFilerSetUpDto;
import cc.dfsoft.uexpress.common.enums.WorkFlowActionEnum;
import cc.dfsoft.uexpress.common.util.IDUtil;

@Service
@Transactional(readOnly = true,propagation=Propagation.REQUIRED)
public class ConnectConfirmServiceImpl implements ConnectConfirmService{
	
	@Resource
	ProjectDao projectDao;
	
	@Resource
	DataFeedbackDao dataFeedbackDao;
	
	@Resource
	FilingDataDao filingDataDao;
	
	/**业务操作记录服务接口*/
	@Resource
	OperateRecordService operateRecordService;
	
	@Resource
	SignatureService signatureService;
	
	/**分包尾款接口*/
	@Resource
	AccrualsRecordService accrualsRecordService;
	
	/**工作流设置*/
	@Resource
	WorkFlowService workFlowService;
	
	/** 会签单Dao*/
	@Resource
	SettlementJonintlySignDao settlementJonintlySignDao;
	
	/** 竣工图审核Dao*/
	@Resource
	DrawingApplyDao drawingApplyDao;
	
	@Override
	public FilingData jointDetail(String projId) {
		Project pro = projectDao.get(projId);
		List<FilingData> filingData = dataFeedbackDao.findByProjNo(pro.getProjNo());
		FilingData fdData = new FilingData();
		if(filingData.size()>0){
			fdData = filingData.get(0);
		}
		fdData.setProjId(pro.getProjId());
		fdData.setProjName(pro.getProjName());
		fdData.setProjAddr(pro.getProjAddr());
		fdData.setProjNo(pro.getProjNo());
		fdData.setCorpId(pro.getCorpId());
		fdData.setProjScaleDes(pro.getProjScaleDes());
		fdData.setContributionModeDes(pro.getContributionModeDes());
		fdData.setDeptName(pro.getDeptName());
		fdData.setProjectTypeDes(pro.getProjectTypeDes());
		fdData.setCorpName(pro.getCorpName());
		fdData.setFdConnectDate(filingDataDao.getDatabaseDate());
		return fdData;
	}
	
	/**
	 * 保存归档资料
	 * @author fuliwei
	 * @createTime 2017年9月1日
	 * @param 
	 * @return
	 */
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public String saveConnect(FilingData filingData) throws ParseException, Exception {
		LoginInfo loginInfo= SessionUtil.getLoginInfo();
		//查询子公司是否配置了需要竣工图审核、结算汇签，由配置，则进行
		List<DataFilerSetUpDto> listData = Constants.getDataFilterMapByKey(loginInfo.getCorpId()+"_"+filingData.getMenuId());
		boolean needFlag = false;
		if(listData!=null && listData.size()>0){
			needFlag = true;
		}
		if(StringUtils.isBlank(filingData.getFdId())){
			filingData.setFdId(IDUtil.getUniqueId(Constants.MODULE_CODE_COMPLETE));
		}
		boolean isSign = false;
		boolean isPass = false;
		List<DrawingApply> list=drawingApplyDao.findByProjId(filingData.getProjId());
		SettlementJonintlySign settlementJonintlySign = settlementJonintlySignDao.findById(filingData.getProjId());
		if(null!=list&&list.size()>0){
			DrawingApply drawingApply = list.get(0);
			if(null!=drawingApply){
				if(AuditResultEnum.PASSED.getValue().equals(drawingApply.getAuditState())){
					isPass=true;
				}
			}
		}
		if(null!=settlementJonintlySign){
			if("1".equals(settlementJonintlySign.getSignStatus())){
				isSign = true;
			}
			
		}
		if(!needFlag || ((isPass&&isSign))){
			//更新工程信息
			Project pro=projectDao.get(filingData.getProjId());         //通过工程id查找Project
			//生成竣工日期
			pro.setFinishedDate(projectDao.getDatabaseDate());
			//生成工程状态
			String statusId=workFlowService.queryProjStatusId(pro.getCorpId(),pro.getProjectType(),pro.getContributionMode(),WorkFlowActionEnum.CONNECT_CONFIRM.getActionCode(), true);
			pro.setToDoer("无");
			pro.setProjStatusId(statusId); 								//设置工程状态
			projectDao.saveOrUpdate(pro);
			
			filingData.setFdAuditor(loginInfo.getStaffId());			//归档操作人
			filingData.setFdDate(dataFeedbackDao.getDatabaseDate());    //归档时间
			//保存
			dataFeedbackDao.saveOrUpdate(filingData);
			//形成操作记录
			String orId = IDUtil.getUniqueId(Constants.MODULE_CODE_COMPLETE);
			operateRecordService.createOperateRecord(orId, filingData.getProjId(), pro.getProjNo(), StepEnum.CONNECT_CONFIRM.getValue(), StepEnum.CONNECT_CONFIRM.getMessage(), "");
			//生成分包尾款收付流水
			//accrualsRecordService.insertAccrualsRecord(pro);
			return Constants.OPERATE_RESULT_SUCCESS;
		}else if(!isPass&&isSign){
			return "notPass";
		}else if(isPass&&!isSign){
			return "notSign";
		}else{
			return "notAll";
		}
		
	}

	@Override
	public Map<String, Object> queryFilingData(CompletionTransferReq completionTransferReq) throws ParseException {
		return filingDataDao.queryFilingData(completionTransferReq);
	}

	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void signConnectConfirmPrint(String projId) {
		FilingData filingData=filingDataDao.findByProjId(projId);
		if(filingData!=null){
			//标记已打印
			filingData.setIsPrint(ContractIsPrintEnum.ALREADY_PRINT.getValue());
			filingDataDao.update(filingData);
		}
		
	}
}
