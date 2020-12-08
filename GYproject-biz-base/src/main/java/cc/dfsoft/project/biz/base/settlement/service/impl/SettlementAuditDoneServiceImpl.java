package cc.dfsoft.project.biz.base.settlement.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cc.dfsoft.project.biz.base.baseinfo.service.WorkFlowService;
import cc.dfsoft.project.biz.base.contract.service.ContractService;
import cc.dfsoft.project.biz.base.design.dao.DesignInfoDao;
import cc.dfsoft.project.biz.base.design.dto.DesignDispatchDto;
import cc.dfsoft.project.biz.base.project.dao.NoticeDao;
import cc.dfsoft.project.biz.base.project.dao.ProjectDao;
import cc.dfsoft.project.biz.base.project.entity.Notice;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.project.enums.NoticeAuditTypeEnum;
import cc.dfsoft.project.biz.base.project.enums.NoticeMenuEnum;
import cc.dfsoft.project.biz.base.project.enums.NoticeStateEnum;
import cc.dfsoft.project.biz.base.project.enums.StepEnum;
import cc.dfsoft.project.biz.base.project.service.ManageRecordService;
import cc.dfsoft.project.biz.base.project.service.OperateRecordService;
import cc.dfsoft.project.biz.base.settlement.dao.SettlementDeclarationDao;
import cc.dfsoft.project.biz.base.settlement.entity.SettlementDeclaration;
import cc.dfsoft.project.biz.base.settlement.service.SettlementAuditDoneService;
import cc.dfsoft.project.biz.base.subpackage.dao.SubQuantitiesDao;
import cc.dfsoft.project.biz.base.subpackage.dto.SubQuantitiesDto;
import cc.dfsoft.project.biz.base.subpackage.entity.SubQuantities;
import cc.dfsoft.project.biz.base.subpackage.enums.AuditStatusEnum;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.enums.WorkFlowActionEnum;
import cc.dfsoft.uexpress.common.util.IDUtil;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class SettlementAuditDoneServiceImpl implements SettlementAuditDoneService {

	@Resource
	SettlementDeclarationDao settlementDeclarationDao;
	
	/**业务操作记录服务接口*/
	@Resource
	OperateRecordService operateRecordService;

	/**工程dao*/
	@Resource
	ProjectDao projectDao;
	
	@Resource
	ContractService contractService;
	
	@Resource
	SubQuantitiesDao subQuantitiesDao;
	
	@Resource
	DesignInfoDao designInfoDao;
	
	@Resource
	WorkFlowService workFlowService;
	
	@Resource
	ManageRecordService manageRecordService;
	
	/**通知Dao*/
	@Resource
	NoticeDao noticeDao;

	@Override
	public SettlementDeclaration auditStartDetail(String projId) {
		SettlementDeclaration settlementDeclaration = settlementDeclarationDao.findByProjId(projId);
		return settlementDeclaration;
	}

	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void batInsertSubQualities(SubQuantitiesDto qdto) {
		List<SubQuantities> list=new ArrayList<SubQuantities>();
		
		SettlementDeclaration setdec = settlementDeclarationDao.findByProjId(qdto.getProjId());
		if(setdec!=null){
			setdec.setQuantitiesTotal(qdto.getSubmitAmount());
			setdec.setEndDeclarationCost(qdto.getSubmitAmount());
		}
		settlementDeclarationDao.saveOrUpdate(setdec);
		
		//添加分包工程量记录
		if(qdto.getSettlementState().equals("1")){
			for(SubQuantities sq:qdto.getList()){
				//工程量标准
//				sq.setProjId(qdto.getProjId());
//				sq.setProjNo(qdto.getProjNo());
//				sq.setSqId(IDUtil.getUniqueId(Constants.MODULE_CODE_SETTLEMENT));
//				sq.setAuditStatus(AuditStatusEnum.DONE_FIRST.getValue());
				list.add(sq);
			}
			subQuantitiesDao.deleteByProjIdSettlement(qdto.getProjId(),AuditStatusEnum.DONE_FIRST.getValue());
		}else if(qdto.getSettlementState().equals("2")){
			for(SubQuantities sq:qdto.getList()){
				//工程量标准
//				sq.setProjId(qdto.getProjId());
//				sq.setProjNo(qdto.getProjNo());
//				sq.setSqId(IDUtil.getUniqueId(Constants.MODULE_CODE_SETTLEMENT));
//				sq.setAuditStatus(AuditStatusEnum.DONE_SECOND.getValue());
				list.add(sq);
			}
			subQuantitiesDao.deleteByProjIdSettlement(qdto.getProjId(),AuditStatusEnum.DONE_SECOND.getValue());
		}else{
			for(SubQuantities sq:qdto.getList()){
				//工程量标准
//				sq.setProjId(qdto.getProjId());
//				sq.setProjNo(qdto.getProjNo());
//				sq.setSqId(IDUtil.getUniqueId(Constants.MODULE_CODE_SETTLEMENT));
//				sq.setAuditStatus(AuditStatusEnum.DONE_THIED.getValue());
				list.add(sq);
			}
			subQuantitiesDao.deleteByProjIdSettlement(qdto.getProjId(),AuditStatusEnum.DONE_THIED.getValue());
		}
		subQuantitiesDao.batchInsertObjects(list);
	}

	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void update(DesignDispatchDto designDispatchReq) {
		if (StringUtils.isNotBlank(designDispatchReq.getProjId())) {
			// 更新工程信息
			Project pro = projectDao.get(designDispatchReq.getProjId());// 根据Id查询工程
			//存储预算员到工程表
			pro.setSettlementer(designDispatchReq.getSurveyer());//结算员
			pro.setSettlementerId(designDispatchReq.getSurveyerId());//结算员ID
			
			String statusId = workFlowService.queryProjStatusId(pro.getCorpId(),pro.getProjectType(),pro.getContributionMode(),WorkFlowActionEnum.AUDIT_DONE_DISPATCH.getActionCode(), true);
			pro.setProjStatusId(statusId); // 更新工程状态
			pro.setToDoer(designDispatchReq.getSurveyer());	//待办人
			// 更新工程
			projectDao.update(pro);
			// 形成操作记录
			String orId = IDUtil.getUniqueId(Constants.MODULE_CODE_SETTLEMENT);
			//operateRecordService.createOperateRecord(orId, pro.getProjId(), pro.getProjNo(), StepEnum.AUDIT_DONE_DISPATCH.getValue(), StepEnum.AUDIT_DONE_DISPATCH.getMessage(), "");
			//生成操作记录
			operateRecordService.createNextOperateRecord(orId, pro.getProjId(), pro.getProjNo(), StepEnum.AUDIT_DONE_DISPATCH.getValue(), StepEnum.AUDIT_DONE_DISPATCH.getMessage(), "", designDispatchReq.getSurveyerId(), designDispatchReq.getSurveyer());

			 /*//生成审核通知
			 Notice notice=noticeDao.findByProjIdAndType(pro.getProjId(), NoticeAuditTypeEnum.SETTLEMENT_AUDIT.getValue());//结算审核
			 if(notice!=null){
				 notice.setNoticeState(NoticeStateEnum.EFFECTIVE.getValue());//将通知置为有效;
				 notice.setGrade(NoticeMenuEnum.SETTLEMENT_AUDIT1.getGrade());	 //一级审核
				 notice.setNoticeContent(NoticeMenuEnum.SETTLEMENT_AUDIT1.getMessage());//待踏勘审核一级
				 notice.setNoticeTime(noticeDao.getDatabaseDate());
			 }else{
				 notice=new Notice();
				 notice.setNoticeId(IDUtil.getUniqueId(Constants.MODULE_CODE_DESIGN));
				 notice.setNoticeState(NoticeStateEnum.EFFECTIVE.getValue());		//将通知置为有效;
				 notice.setGrade(NoticeMenuEnum.SETTLEMENT_AUDIT1.getGrade());	 		//一级审核
				 notice.setAuditType(NoticeMenuEnum.SETTLEMENT_AUDIT1.getType());
				 notice.setNoticeTime(noticeDao.getDatabaseDate());
				 notice.setNoticeTitle("审核通知");
				 notice.setNoticeContent(NoticeMenuEnum.SETTLEMENT_AUDIT1.getMessage());//待踏勘审核一级
				 notice.setNoticeType("2");
				 notice.setProjId(pro.getProjId());
				 notice.setCorpId(pro.getCorpId());
			 }
			 
			 noticeDao.saveOrUpdate(notice);*/
			
		}
	}
}
