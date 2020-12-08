package cc.dfsoft.project.biz.base.inspection.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cc.dfsoft.project.biz.base.inspection.dao.ProjectChecklistDao;
import cc.dfsoft.project.biz.base.inspection.dao.StrengthTestDao;
import cc.dfsoft.project.biz.base.inspection.dto.ProjectCheckListSTReq;
import cc.dfsoft.project.biz.base.inspection.dto.StrengthTestReq;
import cc.dfsoft.project.biz.base.inspection.entity.ProjectChecklist;
import cc.dfsoft.project.biz.base.inspection.entity.StrengthTest;
import cc.dfsoft.project.biz.base.inspection.service.StrengthTestService;
import cc.dfsoft.project.biz.base.project.dao.AccessoryDao;
import cc.dfsoft.project.biz.base.project.dao.ProjectDao;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.project.enums.AccessorySourceEnum;
import cc.dfsoft.project.biz.base.project.enums.ProjStatusEnum;
import cc.dfsoft.project.biz.base.project.enums.SignDataTypeEnum;
import cc.dfsoft.project.biz.base.project.enums.SignPostEnum;
import cc.dfsoft.project.biz.base.project.enums.SignStateEnum;
import cc.dfsoft.project.biz.base.project.enums.StepEnum;
import cc.dfsoft.project.biz.base.project.service.AccessoryService;
import cc.dfsoft.project.biz.base.project.service.OperateRecordService;
import cc.dfsoft.project.biz.base.project.service.SignNoticeService;
import cc.dfsoft.project.biz.base.subpackage.dao.SubContractDao;
import cc.dfsoft.project.biz.base.subpackage.entity.SubContract;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.util.IDUtil;
/**
 * 强度试验业务实现层
 * @author liaoyq
 *
 */
@Service
@Transactional(readOnly = true,propagation = Propagation.REQUIRED)
public class StrengthTestServiceImpl implements StrengthTestService {

	@Resource
	private StrengthTestDao strengthTestDao;
	
	@Resource
	AccessoryDao accessoryDao;
	
	@Resource
	ProjectDao projectDao;
	@Resource
	AccessoryService accessoryService;
	@Resource
	OperateRecordService operateRecordService;
	
	/**报验单*/
	@Resource
	ProjectChecklistDao projectCheckListDao;
	
	/**签字通知服务*/
	@Resource
	SignNoticeService signNoticeService;
	@Resource
	SubContractDao subContractDao;
	
	@Override
	public Map<String, Object> queryStrengthTest(StrengthTestReq strengthTestReq) {
		return strengthTestDao.queryStrengthTest(strengthTestReq);
	}

	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void saveStrengthTest(ProjectCheckListSTReq checkListSTReq) {
		if(checkListSTReq.getList().size()>0){
			//删除已有记录
			strengthTestDao.deleteByPcId(checkListSTReq.getPcId());
			//批量插入记录数据
			List<StrengthTest> list = checkListSTReq.getList();
			List<StrengthTest> listNew = new ArrayList<StrengthTest>();
			for(StrengthTest st : list){
				//生成stId;
				st.setStId(IDUtil.getUniqueId(Constants.MODULE_CODE_STRENGTH_TEST));
				listNew.add(st);
			}
			strengthTestDao.batchInsertObjects(listNew);
			
		}else{
			strengthTestDao.deleteByPcId(checkListSTReq.getPcId());
		}
		
	}

	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void updatePcIdByRecordsId(String recordsId, String pcId,String projId,String projNo) {
		//先置空之前报验的pcId
		strengthTestDao.updateByPcId(pcId);
		//质空附件表中的报验单ID
		accessoryService.clearBRId(projId,projNo,pcId);
		String [] ids = recordsId.split(",");
		//
		for(String id : ids){
			StrengthTest record = strengthTestDao.get(id);
			if(record!=null){
				strengthTestDao.updatePcIdByRecordId(id,pcId);
				//回写拍照pcId
				accessoryDao.updateBId(pcId,record.getProjId(),id,AccessorySourceEnum.PHOTO_FILE.getValue());
			}
		}
	}

	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void saveRecords(ProjectCheckListSTReq checkListReq) {
		//有记录
		if(checkListReq.getList().size()>0){
			//先删除记录
			deleteRecord(checkListReq);
			//批量保存记录
			List<StrengthTest> listNew = new ArrayList<StrengthTest>();
			for(StrengthTest st : checkListReq.getList()){
				st.setStId(IDUtil.getUniqueId(Constants.MODULE_CODE_STRENGTH_TEST));
				listNew.add(st);
			}
			strengthTestDao.batchInsertObjects(listNew);
			
		}else {
			deleteRecord(checkListReq);
		}
	}

	private void deleteRecord(ProjectCheckListSTReq checkListTBReq){
		//已报验过
		if(StringUtils.isNotBlank(checkListTBReq.getPcId())){
			//有pcId,则根据pcId删除记录
			strengthTestDao.deletePcIdIsNull(checkListTBReq.getPcId());
		}else{
			strengthTestDao.deletePcIdIsNull();
		}
	}
	@Override
	public Map<String, Object> queryRecords(StrengthTestReq req) {
		return strengthTestDao.queryRecords(req);
	}

	@Override
	public StrengthTest viewRecordById(String id) {
		return strengthTestDao.get(id);
	}

	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public String saveRecord(StrengthTest st) {
		if(StringUtils.isBlank(st.getStId())){
			st.setStId(IDUtil.getUniqueId(Constants.MODULE_CODE_STRENGTH_TEST));
		}
		strengthTestDao.saveOrUpdate(st);
		return st.getStId();
	}

	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void deleteRecordById(String stId) {
		strengthTestDao.delete(stId);
	}

	/**
	 * 试压完成推送时，修改工程装填和增加操作记录
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public String finishStrengthTest(String projId) {
		Project project=projectDao.get(projId);
		if(project!=null){//已推送过的工程不再修改工程状态
			//正常流程下，工程状态为施工中
			//并行流程下，工程为待施工并且施工合同已签订或施工合同已审核
			//不是施工中的，则判断工程是否已签订分包合同
			boolean flag = false;
			if(project.getProjStatusId().equals(ProjStatusEnum.DURING_CONSTRUCTION.getValue())){
				flag = true;
			}else if(project.getProjStatusId().equals(ProjStatusEnum.TO_CONSTRUCTION.getValue())
					||project.getProjStatusId().equals(ProjStatusEnum.TO_SIGNED_SUBCONTRACT.getValue())
					||project.getProjStatusId().equals(ProjStatusEnum.TO_AUDIT_AMOUNT.getValue())
					||project.getProjStatusId().equals(ProjStatusEnum.TO_DETERMINE_DISPATCH.getValue())
					||project.getProjStatusId().equals(ProjStatusEnum.TO_DETERMINE_AMOUNT.getValue())
					||project.getProjStatusId().equals(ProjStatusEnum.TO_AUDIT_SUBCONTRACT.getValue())//施工合同审核
					){
					return "noContract";
			}
			//更新工程
			if(flag){
				project.setProjStatusId(ProjStatusEnum.TO_SELF_CHECK.getValue());
				
				//生成操作记录
				String orId = IDUtil.getUniqueId(Constants.MODULE_CODE_CONTRACT);//生成唯一ID
				String todoer=operateRecordService.createOperateRecord(orId, project.getProjId(), project.getProjNo(), StepEnum.DURING_CONSTRUCTION.getValue(), StepEnum.DURING_CONSTRUCTION.getMessage(), "");	
				project.setToDoer(todoer);
				projectDao.update(project);
			}
		}
		
		return Constants.OPERATE_RESULT_SUCCESS;
	}
	
	/**
	 * 保存回调
	 * @author fuliwei
	 * @createTime 2018年1月21日
	 * @param 
	 * @return
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void saveSignNotice(String cwId) {
		String signState = SignStateEnum.ALREADY_SIGN.getValue();
		//现场代表（4）fieldsRepresent
		
		//现场监理（3）suJgj
		
		//施工员（1）builder
		//项目经理（2）projectLeader
		//安全员（2）safetyOfficer
		//质检员（2）constructionQc
		
		if(StringUtils.isNotBlank(cwId)){
			ProjectChecklist list=projectCheckListDao.get(cwId);
			if(list!=null){
				if(StringUtils.isNotBlank(list.getFieldsRepresent())){
					//现场代表通知置为无效
					signNoticeService.saveThisSignNotice(SignPostEnum.BUILDER.getValue(), SignDataTypeEnum.STRENGTH_TEST.getValue(),
							list.getPcId(), list.getProjId(),signState);
				}
				
				
				if(StringUtils.isNotBlank(list.getSuJgj())){
					//现场监理通知置为无效
					signNoticeService.saveThisSignNotice(SignPostEnum.SUJGJ.getValue(), SignDataTypeEnum.STRENGTH_TEST.getValue(),
							list.getPcId(), list.getProjId(),signState);
				}
				
				if(StringUtils.isNotBlank(list.getBuilder())){
					//施工员通知置为无效
					signNoticeService.saveThisSignNotice(SignPostEnum.CONSTRUCTION.getValue(), SignDataTypeEnum.STRENGTH_TEST.getValue(),
							list.getPcId(), list.getProjId(),signState);
				}
				if(StringUtils.isNotBlank(list.getProjectLeader())){
					//项目经理通知置为无效
					signNoticeService.saveThisSignNotice(SignPostEnum.CU_PM.getValue(), SignDataTypeEnum.STRENGTH_TEST.getValue(),
							list.getPcId(), list.getProjId(),signState);
				}
				if(StringUtils.isNotBlank(list.getSafetyOfficer())){
					//安全员通知置为无效
					signNoticeService.saveThisSignNotice(SignPostEnum.SAFTYOFFICER.getValue(), SignDataTypeEnum.STRENGTH_TEST.getValue(),
							list.getPcId(), list.getProjId(),signState);
				}
				
				if(StringUtils.isNotBlank(list.getConstructionQc())){
					//质检员通知置为无效
					signNoticeService.saveThisSignNotice(SignPostEnum.QUALITATIVE_CHECK_MEMBER.getValue(), SignDataTypeEnum.STRENGTH_TEST.getValue(),
							list.getPcId(), list.getProjId(),signState);
				}
			}
		}
		
	}
}
