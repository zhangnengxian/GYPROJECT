package cc.dfsoft.project.biz.base.project.service.impl;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cc.dfsoft.project.biz.base.baseinfo.dao.DelayReasonDao;
import cc.dfsoft.project.biz.base.baseinfo.dao.DocTypeDao;
import cc.dfsoft.project.biz.base.baseinfo.dao.SystemSetDao;
import cc.dfsoft.project.biz.base.baseinfo.entity.DelayReason;
import cc.dfsoft.project.biz.base.baseinfo.entity.DocType;
import cc.dfsoft.project.biz.base.baseinfo.service.DocTypeService;
import cc.dfsoft.project.biz.base.common.entity.SystemSet;
import cc.dfsoft.project.biz.base.constructmanage.enums.StepOutWorkflowEnum;
import cc.dfsoft.project.biz.base.project.dao.ApplyDelayDao;
import cc.dfsoft.project.biz.base.project.dao.ManageRecordDao;
import cc.dfsoft.project.biz.base.project.dao.ProjectDao;
import cc.dfsoft.project.biz.base.project.dto.ApplyDelayReq;
import cc.dfsoft.project.biz.base.project.entity.ApplyDelay;
import cc.dfsoft.project.biz.base.project.entity.ManageRecord;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.project.enums.MrResultEnum;
import cc.dfsoft.project.biz.base.project.service.ApplyDelayService;
import cc.dfsoft.project.biz.base.settlement.enums.AuditResultEnum;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.util.IDUtil;
import cc.dfsoft.uexpress.common.util.StringUtil;
import net.sf.json.JSONSerializer;

/**
 * 延期申请接口实现类
 * @author 
 *
 */
@Service
@Transactional(readOnly = true,propagation=Propagation.REQUIRED)
public class ApplyDelayServiceImpl implements ApplyDelayService {
	
	/**延期申请*/
	@Resource
	ApplyDelayDao applyDelayDao;
	
	/**延期原因*/
	@Resource
	DelayReasonDao delayReasonDao;
	
	/**工程Dao*/
	@Resource
	ProjectDao projectDao;
	
	/**业务单类型*/
	@Resource
	DocTypeDao docTypeDao;
	
	/**系统设置*/
	@Resource
	SystemSetDao systemSetDao;
	
	/**审核记录*/
	@Resource
	ManageRecordDao manageRecordDao;
	
	/**部门审核级别*/
	@Resource
	DocTypeService docTypeService;
	
	/**
	 * 延期保存
	 * @author fuliwei
	 * @createTime 2017年9月1日
	 * @param 
	 * @return
	 */
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void saveApplyDelay(ApplyDelay applyDelay) {
		if(StringUtils.isBlank(applyDelay.getAdId())){
			applyDelay.setAdId(IDUtil.getUniqueId(Constants.MODULE_CODE_PROJECTAPPROVAL));
		}
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		applyDelay.setOptTime(applyDelayDao.getDatabaseDate()); //操作时间
		applyDelay.setAdOperator(loginInfo.getStaffName());		//操作人名字
		applyDelay.setAdOperatorId(loginInfo.getStaffId());		//操作人id
		applyDelay.setAuditState(AuditResultEnum.APPLYING.getValue());//审核状态
		
		
		Project pro=projectDao.get(applyDelay.getProjId());
		if(pro!=null){
			applyDelay.setProjName(pro.getProjName());
			applyDelay.setProjAddr(pro.getProjAddr());
			applyDelay.setProjScaleDes(pro.getProjScaleDes());
			applyDelay.setContributionModeDes(pro.getContributionModeDes());
			applyDelay.setDeptName(pro.getDeptName());
			applyDelay.setProjectTypeDes(pro.getProjectTypeDes());
			applyDelay.setCorpName(pro.getCorpName());
			applyDelay.setCorpId(pro.getCorpId());
		}
		
		applyDelayDao.saveOrUpdate(applyDelay);
	}

	@Override
	public List<ApplyDelay> findADelayByStepId(String stepId,String projId) {
		return applyDelayDao.findADelayByStepId(stepId,projId);
		
	}
	
	/**
	 * 延期申请列表查询
	 * @author fuliwei
	 * @createTime 2017年8月31日
	 * @param 
	 * @return
	 */
	@Override
	public Map<String, Object> queryApplyDelay(ApplyDelayReq req) throws ParseException {
		Map<String, Object> map=applyDelayDao.queryApplyDelay(req);
		
		List<ApplyDelay> list=(List<ApplyDelay>) map.get("data");
		if(list!=null && list.size()>0){
			for(ApplyDelay ad:list){
				DelayReason dr=delayReasonDao.get(ad.getDelayReason());
				if(dr!=null){
					ad.setDelayReasonDes(dr.getDelayReasonDes());
				}
			}
			
		}
		map.put("data", list);
		return map;
	}
	
	/**
	 * 查询详述
	 * @author fuliwei
	 * @createTime 2017年9月1日
	 * @param 
	 * @return
	 */
	@Override
	public ApplyDelay findById(String adId) {
		ApplyDelay ad=applyDelayDao.get(adId);
		DelayReason dr=delayReasonDao.get(ad.getDelayReason());
		if(dr!=null){
			ad.setDelayReasonDes(dr.getDelayReasonDes());
		}
		return ad;
	}
	
	
	/**
	 * 延期审核列表查询
	 * @author fuliwei
	 * @createTime 2017年8月31日
	 * @param 
	 * @return
	 */
	@Override
	public Map<String, Object> queryApplyDelayAudit(ApplyDelayReq req) throws ParseException {
		String grade=null;
		//DocType docType= docTypeDao.findByStepId(StepOutWorkflowEnum.AUDIT_DELAY_APPLY.getValue());
		//grade=docType==null?"":docType.getGrade();	
		
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
        //SystemSet systemSet=systemSetDao.get("stepId", req.getStepId());
        SystemSet systemSet=systemSetDao.querySystemSetByStepId(req.getStepId(),loginInfo.getCorpId());
		
		if(systemSet!=null&&StringUtil.isNotBlank(systemSet.getTimeSpan())){
			req.setTimeLimit(Integer.parseInt(systemSet.getTimeSpan()));
		}
		
		Map<String, Object> result= applyDelayDao.queryApplyDelay(req);
		List<ApplyDelay> data = (List<ApplyDelay>) result.get("data");
		
		Project pro=new Project();
		if(data!=null && data.size()>0){
			for(int i = 0;i<data.size();i++){
				ApplyDelay da=data.get(i);
				
				Project project=projectDao.get(data.get(i).getProjId());
				if(project!=null){
					data.get(i).setProjectType(project.getProjectType());			//工程类型
					data.get(i).setContributionMode(project.getContributionMode()); //出资方式
				}
				
				
				DocType docType = docTypeService.findByStepId(StepOutWorkflowEnum.AUDIT_DELAY_APPLY.getValue(),data.get(i).getCorpId(),data.get(i).getProjectType(),data.get(i).getContributionMode());
				if(docType!=null && StringUtils.isNotBlank(docType.getGrade())){
					grade=docType.getGrade();
				}else{
					grade="0";
				}
				data.get(i).setLevel(grade);// 设置审核总级数（设计信息2级审核）
				
				DelayReason dr=delayReasonDao.get(data.get(i).getDelayReason());
				if(dr!=null){
					data.get(i).setDelayReasonDes(dr.getDelayReasonDes());
				}
				
				Map<String,String> levelBtn = new HashMap();
				//以下循环，遍历出默认应该几级审核，审核状态分别为什么，例如三级审核，则一级待审、二级未审、三级未审{"level1":"2","level2":"-1","level3":"-1"}；
				for(int n=1;n<Integer.parseInt(grade)+1;n++){
					if(n==1){
						levelBtn.put("level"+n, "2");// 待审
					}else{
						levelBtn.put("level"+n, "-1");//未审
					}
				}
				List<ManageRecord> mrls = manageRecordDao.findByStepIdBusinessOrderId(data.get(i).getAdId(),StepOutWorkflowEnum.AUDIT_DELAY_APPLY.getValue(),MrResultEnum.PASSED.getValue());
				if(mrls!=null && mrls.size()>0){
					String size = mrls.size()+"";
					/*if(mrls.size()<Integer.parseInt(grade)){*/
						//遍历循环，获取审核是否通过
						for(ManageRecord mr:mrls){
							levelBtn.put("level"+mr.getMrAuditLevel(),mr.getMrResult());
						}
						if(mrls.size()<Integer.parseInt(grade)){
							levelBtn.put("level"+(mrls.size()+1), "2");
						}
					/*}*/
				}
				data.get(i).setMrAuditLevel(JSONSerializer.toJSON(levelBtn).toString());
			}
			result.put("data", data);
		}
		return result;
	}
	

}
