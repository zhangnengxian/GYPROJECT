package cc.dfsoft.project.biz.base.project.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cc.dfsoft.project.biz.base.baseinfo.enums.WorkFlowTypeEnum;
import cc.dfsoft.project.biz.base.baseinfo.service.WorkFlowService;
import cc.dfsoft.project.biz.base.project.dao.OperateWorkFlowDao;
import cc.dfsoft.project.biz.base.project.dao.OperateWorkFlowRecordDao;
import cc.dfsoft.project.biz.base.project.dao.ProjectDao;
import cc.dfsoft.project.biz.base.project.dto.OperateWorkFlowReq;
import cc.dfsoft.project.biz.base.project.entity.OperateWorkFlow;
import cc.dfsoft.project.biz.base.project.entity.OperateWorkFlowRecord;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.project.enums.OperateWorkFlowEnum;
import cc.dfsoft.project.biz.base.project.service.OperateWorkFlowRecordService;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.util.IDUtil;

@Service
@Transactional(readOnly = true,propagation=Propagation.REQUIRED)
public class OperateWorkFlowRecordServiceImpl implements OperateWorkFlowRecordService{
	
	@Resource
	OperateWorkFlowRecordDao operateWorkFlowRecordDao;
	
	/**工程*/
	@Resource
	ProjectDao projectDao;

	@Resource
	WorkFlowService workFlowService;
	
	@Resource
	OperateWorkFlowDao operateWorkFlowDao;
	
	@Override
	public Map<String, Object> queryList(OperateWorkFlowReq req) {
		return operateWorkFlowRecordDao.queryList(req);
	}

	@Override
	public List<OperateWorkFlowRecord> queryListByReq(OperateWorkFlowReq req) {
		return operateWorkFlowRecordDao.queryListByReq(req);
	}
	
	/**
	 * 立项时创建操作记录表
	 * @author fuliwei  
	 * @date 2018年9月7日  
	 * @version 1.0
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void cerateOperateWorkFlowRecord(Project pro, String stepId) {
		
		//先查询之前的是否生成过
		OperateWorkFlowReq req=new OperateWorkFlowReq();
		req.setProjId(pro.getProjId());
		List<OperateWorkFlowRecord> oplist=operateWorkFlowRecordDao.queryListByReq(req);
		if(oplist!=null && oplist.size()>0){
			//生成过则删除原来的
			operateWorkFlowRecordDao.batchDeleteObjects(oplist);
		}
		
		//操作标准流程查询条件
		req=new OperateWorkFlowReq();
		req.setCorpId(pro.getCorpId());
		req.setProjectType(pro.getProjectType());
		req.setContributionMode(pro.getContributionMode());
		
		LoginInfo login=SessionUtil.getLoginInfo();
		
		//用最初的stepId 查询他的下一个stepId;
		String nextStepId=workFlowService.queryAssistProgressStatusId(pro.getCorpId(), pro.getProjectType(), pro.getContributionMode(), stepId,
				true, WorkFlowTypeEnum.MAIN_PROGRESS.getValue(), null);
		
		OperateWorkFlowRecord owfr=new OperateWorkFlowRecord();
		List<OperateWorkFlowRecord> owfrList=new ArrayList<OperateWorkFlowRecord>();
		
		List<OperateWorkFlow> list=operateWorkFlowDao.queryListByReq(req);
		if(list!=null && list.size()>0){
			for(OperateWorkFlow owf:list){
				//当前第一步
				if(stepId.equals(owf.getStepId())){
					//已办
					owfr=this.getOperateWorkFlowRecord(pro,owf,OperateWorkFlowEnum.HAVE_DONE.getValue());
					owfr.setOpereater(login.getStaffName());
					owfr.setOpereaterId(login.getStaffId());
					owfr.setOperateTime(operateWorkFlowRecordDao.getDatabaseDate());
					owfrList.add(owfr);
				}else if(nextStepId.equals(owf.getStepId())){
					//当前第二步
					//代办
					owfr=this.getOperateWorkFlowRecord(pro,owf,OperateWorkFlowEnum.WAIT_DONE.getValue());
					owfrList.add(owfr);
				}else{
					//未办
					owfr=this.getOperateWorkFlowRecord(pro,owf,OperateWorkFlowEnum.NOT_DONE.getValue());
					owfrList.add(owfr);
				}
			}
			//批量插入所有的操作记录
			operateWorkFlowRecordDao.batchInsertObjects(owfrList);
		}
	}
	
	
	public OperateWorkFlowRecord getOperateWorkFlowRecord(Project pro,OperateWorkFlow owf,String modifyStatus){
		OperateWorkFlowRecord owfr=new OperateWorkFlowRecord();
		//第一个
		owfr.setOwfrid(IDUtil.getUniqueId(Constants.STANDARD));
		owfr.setProjectType(pro.getProjectType());			//工程类型
		owfr.setContributionMode(pro.getContributionMode());//出资方式
		owfr.setCorpId(pro.getCorpId());
		owfr.setProjId(pro.getProjId());					//工程id
		owfr.setProjNo(pro.getProjNo());					//工程编号
		
		owfr.setProjName(pro.getProjName());
		owfr.setOwfId(owf.getOwfId());						//标准操作流id
		owfr.setOpType(owf.getOpType());
		owfr.setStepId(owf.getStepId());
		owfr.setGrade(owf.getGrade());
		owfr.setOpType(owf.getOpType());
		owfr.setModifyStatus(modifyStatus);			
		owfr.setOpereaterId(owf.getOpereaterId());			//操作人id
		owfr.setOpereater(owf.getOpereater());
		return owfr;
	}
	
	/**
	 * 生成下一个操作通知
	 * @author fuliwei  
	 * @date 2018年9月9日  
	 * @version 1.0
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void createNextOperateRecord(Project pro, String stepId, String operateId, String operater) {
		
		//将当前的置为已办
		OperateWorkFlowReq req=new OperateWorkFlowReq();
		req.setProjId(pro.getProjId());
		req.setStepId(stepId);
		List<OperateWorkFlowRecord> list=operateWorkFlowRecordDao.queryListByReq(req);
		
		LoginInfo login=SessionUtil.getLoginInfo();
		if(list!=null && list.size()>0){
			OperateWorkFlowRecord owfr=list.get(0);
			owfr.setOpereater(login.getStaffName());
			owfr.setOpereaterId(login.getStaffId());
			owfr.setOperateTime(operateWorkFlowRecordDao.getDatabaseDate());
			owfr.setModifyStatus(OperateWorkFlowEnum.HAVE_DONE.getValue());
			operateWorkFlowRecordDao.saveOrUpdate(owfr);
		}
		
		
		//下一个置为待办
		//用最初的stepId 查询他的下一个stepId;
		String nextStepId=workFlowService.queryAssistProgressStatusId(pro.getCorpId(), pro.getProjectType(), pro.getContributionMode(), stepId,
				true, WorkFlowTypeEnum.MAIN_PROGRESS.getValue(), null);
		req.setStepId(nextStepId);
		List<OperateWorkFlowRecord> listOr=operateWorkFlowRecordDao.queryListByReq(req);
		if(listOr!=null && listOr.size()>0){
			OperateWorkFlowRecord ofr=listOr.get(0);
			if(StringUtils.isNotBlank(operateId)){
				//如果是派工环节，如预算派工，则传派工的人员，否则传null
				ofr.setOpereater(operater);		//代办人
				ofr.setOpereaterId(operateId);	//代办人id
			}
			ofr.setModifyStatus(OperateWorkFlowEnum.WAIT_DONE.getValue());
			operateWorkFlowRecordDao.saveOrUpdate(ofr);
		}
	}
}
