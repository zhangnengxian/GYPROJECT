package cc.dfsoft.project.biz.base.complete.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cc.dfsoft.project.biz.base.accept.dao.ProjectApplicationDao;
import cc.dfsoft.project.biz.base.accept.entity.ProjectApplication;
import cc.dfsoft.project.biz.base.complete.dao.DataFeedbackDao;
import cc.dfsoft.project.biz.base.complete.entity.FilingData;
import cc.dfsoft.project.biz.base.complete.service.DataFeedbackService;
import cc.dfsoft.project.biz.base.plan.dao.ConstructionPlanDao;
import cc.dfsoft.project.biz.base.plan.entity.ConstructionPlan;
import cc.dfsoft.project.biz.base.project.dao.ProjectDao;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.project.enums.StepEnum;
import cc.dfsoft.project.biz.base.project.service.OperateRecordService;
import cc.dfsoft.project.biz.base.project.service.SignatureService;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.enums.WorkFlowActionEnum;
import cc.dfsoft.uexpress.common.util.IDUtil;
import cc.dfsoft.uexpress.common.util.WorkFlowUtil;

@Service
@Transactional(readOnly = true,propagation=Propagation.REQUIRED)
public class DataFeedbackServiceImpl implements DataFeedbackService{
	
	@Resource
	ProjectDao projectDao;
	
	@Resource
	DataFeedbackDao dataFeedbackDao;
	
	@Resource
	ConstructionPlanDao constructionPlanDao;
	
	/**业务操作记录服务接口*/
	@Resource
	OperateRecordService operateRecordService;
	
	@Resource
	SignatureService signatureService;
	
	/**立项申请单信息Dao*/
	@Resource
	ProjectApplicationDao projectApplicationDao;
	
	@Override
	public FilingData jointDetail(String projId) {
		Project project = projectDao.get(projId);
		List<FilingData> filingData = dataFeedbackDao.findByProjNo(project.getProjNo());
		List<ConstructionPlan> constructionPlans = constructionPlanDao.findByProjNo(project.getProjNo());
		FilingData fdData = new FilingData();
		if(filingData.size()>0){
			fdData = filingData.get(0);
		}
		if(constructionPlans !=null && constructionPlans.size()!=0){
			ConstructionPlan constructionPlan = constructionPlans.get(0);
			//fdData.setFdCmo(constructionPlan.getManagementOffice());        //添加施工管理处
		}
		ProjectApplication pa=projectApplicationDao.queryById(projId);
		if(pa!=null){
			fdData.setPaNo(pa.getPaNo());
		}
		return fdData;
	}

	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void saveData(FilingData filingData) throws Exception {
		// TODO Auto-generated method stub
		boolean flag = false;
		if(StringUtils.isBlank(filingData.getFdId())){
			flag = true;
			filingData.setFdId(IDUtil.getUniqueId(Constants.MODULE_CODE_COMPLETE));
		}
		//更新工程信息
		Project pro=projectDao.get(filingData.getProjId());         //通过工程id查找Project
		//生成工程状态
		String statusId=WorkFlowUtil.workFlowStatus(WorkFlowActionEnum.DATA_FEEDBACK.getActionCode());
		//取得登录人信息
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		pro.setProjStatusId(statusId); 								//设置工程状态
		projectDao.saveOrUpdate(pro);
		//保存
		dataFeedbackDao.saveOrUpdate(filingData);
		//形成操作记录
		String orId = IDUtil.getUniqueId(Constants.MODULE_CODE_COMPLETE);
		signatureService.saveOrUpdateSign(filingData.getMenuId(),filingData.getSign(), filingData.getProjId(), filingData.getFdId(), filingData.getClass().getName(), flag);
		//operateRecordService.createOperateRecord(orId, filingData.getProjId(), pro.getProjNo(), StepEnum.DATA_FEEDBACK.getValue(), StepEnum.DATA_FEEDBACK.getMessage(), "");
	}
}
