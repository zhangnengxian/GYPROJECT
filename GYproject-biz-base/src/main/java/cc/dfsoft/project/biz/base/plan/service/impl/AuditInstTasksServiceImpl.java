package cc.dfsoft.project.biz.base.plan.service.impl;

import java.text.ParseException;
import java.util.*;

import javax.annotation.Resource;

import cc.dfsoft.project.biz.base.charge.dao.CashFlowDao;
import cc.dfsoft.project.biz.base.charge.entity.CashFlow;
import cc.dfsoft.project.biz.base.charge.enums.ARFlagEnum;
import cc.dfsoft.project.biz.base.plan.dao.InstTasksDao;
import cc.dfsoft.project.biz.base.plan.entity.InstTasks;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cc.dfsoft.project.biz.base.baseinfo.entity.DocType;
import cc.dfsoft.project.biz.base.baseinfo.service.DocTypeService;
import cc.dfsoft.project.biz.base.charge.entity.AccrualsRecord;
import cc.dfsoft.project.biz.base.charge.service.AccrualsRecordService;
import cc.dfsoft.project.biz.base.plan.service.AuditInstTasksService;
import cc.dfsoft.project.biz.base.project.dao.ManageRecordDao;
import cc.dfsoft.project.biz.base.project.dao.ProjectDao;
import cc.dfsoft.project.biz.base.project.dao.ProjectSignDao;
import cc.dfsoft.project.biz.base.project.dto.ProjectQueryReq;
import cc.dfsoft.project.biz.base.project.entity.ManageRecord;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.project.entity.ProjectSign;
import cc.dfsoft.project.biz.base.project.enums.IsSignEnum;
import cc.dfsoft.project.biz.base.project.enums.MrResultEnum;
import cc.dfsoft.project.biz.base.project.enums.StepEnum;
import net.sf.json.JSONSerializer;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class AuditInstTasksServiceImpl implements AuditInstTasksService{
	@Resource
	ProjectDao projectDao;
	@Resource
	ProjectSignDao projectSignDao;
	@Resource
	DocTypeService docTypeService;
	@Resource
	ManageRecordDao managerecorddao;
	@Autowired
	AccrualsRecordService accrualsRecordService;

	@Resource
	InstTasksDao instTasksDao;
	@Resource
	CashFlowDao cashFlowDao;

	@Override
	public Map<String, Object> getAuditDataList(ProjectQueryReq req)
			throws ParseException {
		Map<String, Object> result = projectDao.queryProject(req);
		List<Project> data = (List<Project>) result.get("data");
		// 按步骤id进行查询 获取单据类型
		// DocType docType =
		// docTypeDao.findByStepId(StepEnum.PROJECT_PLAN_AUDIT.getValue());
		List<InstTasks> tasksList=new ArrayList<InstTasks>();
		DocType docType = new DocType();
		String grade = "";
		if (data != null && data.size() > 0) {
			/**
			 * -1 未审核 0 审核未通过 1 审核通过 2待审核 若该
			 * 审核为三级审核，且一级审核通过、二级审核未通过，三级审核待审核，则键值对：{"level1":"1","level2":"0",
			 * "level3":"2"};
			 */
			// 遍历循环 设置审核级别
			for (int i = 0; i < data.size(); i++) {

				// 查询所有的有效的
				List<ProjectSign> projectSignList = projectSignDao.findByProjIdAndStatus(data.get(i).getProjId(),
						IsSignEnum.IS_SIGN.getValue());
				tasksList = instTasksDao.findByProjId(data.get(i).getProjId());
				if (projectSignList != null && projectSignList.size() > 0) {
					data.get(i).setIsSpecialSign(IsSignEnum.IS_SIGN.getValue());// 是特殊工程
				}

				Project pro = projectDao.get(data.get(i).getProjId());
				docType = docTypeService.findByStepId(StepEnum.AUDIT_INST_TASKS.getValue(), pro.getCorpId(),
						pro.getProjectType(), pro.getContributionMode());
				if (docType != null && StringUtils.isNotBlank(docType.getGrade())) {
					grade = docType.getGrade();
				} else {
					grade = "3";
				}

				if(tasksList!=null  && tasksList.size()>0){
					Date taskDate = tasksList.get(0).getOrderDate();//下达时间
					data.get(i).setInstTaskDate(taskDate);

					List<CashFlow> cashList = cashFlowDao.queryCashFlowByProjIdType(data.get(i).getProjId(), ARFlagEnum.RECEIVE_ACCOUNT.getValue(),null);
					if(cashList != null && cashList.size() > 0){
						for(CashFlow cf:cashList){
							if(cf.getCfDate()!=null && cf.getCfDate().after(taskDate)){
								//收款时间大于下单时间，则是3级
								grade = String.valueOf(Integer.parseInt(grade));
								break;
							}else{
								//收款时间小于下单时间
								grade = String.valueOf(Integer.parseInt(grade) - 1);
							}
						}
					}
				}

				data.get(i).setLevel(grade); // 设置审核总级数（勘察信息3级审核）

				Map<String, String> levelBtn = new HashMap();
				// 以下循环，遍历出默认应该几级审核，审核状态分别为什么，例如三级审核，则一级待审、二级未审、三级未审{"level1":"2","level2":"-1","level3":"-1"}；
				for (int n = 1; n < Integer.parseInt(grade) + 1; n++) {
					if (n == 1) {
						levelBtn.put("level" + n, "2");// 待审
					} else {
						levelBtn.put("level" + n, "-1");// 未审
					}
				}
				List<ManageRecord> mrls = managerecorddao.findByStepIdProjIdIsPass(data.get(i).getProjId(),
						StepEnum.AUDIT_INST_TASKS.getValue(), MrResultEnum.PASSED.getValue());
				if (mrls != null && mrls.size() > 0) {
					// 遍历循环，获取审核是否通过
					for (ManageRecord mr : mrls) {
						levelBtn.put("level" + mr.getMrAuditLevel(), mr.getMrResult());
					}
					if (mrls.size() < Integer.parseInt(grade)) {
						levelBtn.put("level" + (mrls.size() + 1), "2");
					}
				}
				data.get(i).setMrAuditLevel(JSONSerializer.toJSON(levelBtn).toString());
			}
			result.put("data", data);
		}
		return result;
		
	}
	
}
