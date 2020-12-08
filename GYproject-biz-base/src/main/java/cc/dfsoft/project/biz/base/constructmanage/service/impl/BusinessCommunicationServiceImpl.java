package cc.dfsoft.project.biz.base.constructmanage.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.orm.hibernate4.HibernateOptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cc.dfsoft.project.biz.base.baseinfo.dao.BusinessPartnersDao;
import cc.dfsoft.project.biz.base.baseinfo.entity.BusinessPartners;
import cc.dfsoft.project.biz.base.constructmanage.dao.BusinessCommunicationDao;
import cc.dfsoft.project.biz.base.constructmanage.dao.NdeRecordDao;
import cc.dfsoft.project.biz.base.constructmanage.dto.BusinessCommunicationReq;
import cc.dfsoft.project.biz.base.constructmanage.dto.NdeRecordBcDto;
import cc.dfsoft.project.biz.base.constructmanage.entity.BusinessCommunication;
import cc.dfsoft.project.biz.base.constructmanage.entity.NdeRecord;
import cc.dfsoft.project.biz.base.constructmanage.entity.ShutdownApproval;
import cc.dfsoft.project.biz.base.constructmanage.enums.BcSendTypeEnum;
import cc.dfsoft.project.biz.base.constructmanage.enums.BcStatusEnum;
import cc.dfsoft.project.biz.base.constructmanage.service.BusinessCommunicationService;
import cc.dfsoft.project.biz.base.constructmanage.service.NdeRecordService;
import cc.dfsoft.project.biz.base.project.dao.ProjectDao;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.project.enums.SignDataTypeEnum;
import cc.dfsoft.project.biz.base.project.enums.SignPostEnum;
import cc.dfsoft.project.biz.base.project.enums.SignStateEnum;
import cc.dfsoft.project.biz.base.project.service.SignNoticeService;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.biz.sys.dept.service.StaffService;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.constant.DataFilerSetUpDto;
import cc.dfsoft.uexpress.common.util.IDUtil;
import cc.dfsoft.uexpress.common.util.StringUtil;

@Service
@Transactional(readOnly = true,propagation=Propagation.REQUIRED)
public class BusinessCommunicationServiceImpl implements BusinessCommunicationService {

	/** 业务沟通Dao*/
	@Resource
	BusinessCommunicationDao businessCommunicationDao;
	@Resource
	NdeRecordService ndeRecordService;
	@Resource
	StaffService staffService;
	@Resource
	BusinessPartnersDao businessPartnersDao;
	@Resource
	NdeRecordDao ndeRecordDao;
	
	/**签字通知服务*/
	@Resource
	SignNoticeService signNoticeService;
	@Resource
	ProjectDao projectDao;
	
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> queryBusinessCommunication(BusinessCommunicationReq businessCommunicationReq) {
		Map<String, Object> map = businessCommunicationDao.queryBusinessCommunication(businessCommunicationReq);
		List<BusinessCommunication> list = (List<BusinessCommunication>) map.get("data");
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		String loginId = loginInfo.getStaffId();
		for (BusinessCommunication businessCommunication : list) {
			if(loginId.equals(businessCommunication.getBcInitiatorId())){
				businessCommunication.setBcSendType(BcSendTypeEnum.SENDER.getValue());
			}else if(loginId.equals(businessCommunication.getBcRecipientId())){
				businessCommunication.setBcSendType(BcSendTypeEnum.RECIPIENT.getValue());
			}
			//探伤报告
			if(StringUtils.isNotBlank(businessCommunicationReq.getMenuId()) && "2011".equals(businessCommunication.getBcTypeDetail())){
				NdeRecord nr = ndeRecordService.findByBcId(businessCommunication.getBcId());
				if(nr!=null){
					businessCommunication.setNdeRecord(nr);
				}
			}
		}
		return map;
	}

	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public String saveBusinessCommunication(BusinessCommunication businessCommunication) throws Exception {
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		if(StringUtils.isBlank(businessCommunication.getBcId())){
			businessCommunication.setBcId(IDUtil.getUniqueId(Constants.MODULE_CODE_PROCESS));//业务沟通ID
			if(StringUtil.isNotBlank(loginInfo.getCorpId())){
				businessCommunication.setBcInitiatorUnitId(loginInfo.getCorpId());
				businessCommunication.setBcInitiatorUnitName(loginInfo.getCorpName());
			}else{
				BusinessPartners businessPartners = businessPartnersDao.get(loginInfo.getDeptId());
				businessCommunication.setBcInitiatorUnitId(businessPartners.getUnitId());
				businessCommunication.setBcInitiatorUnitName(businessPartners.getUnitName());
			}
		}
		//增加推送状态-由页面传递-默认为待推送状态
		if(StringUtil.isNotBlank(businessCommunication.getPushFlag())){
			businessCommunication.setBcStatus(businessCommunication.getPushFlag());//通知状态
			businessCommunication.setNoticeDate(businessPartnersDao.getDatabaseDate());//委托日期
		}else{
			businessCommunication.setBcStatus(BcStatusEnum.NO_PUSHED.getValue());
		}
		//探伤委托，才增加无损检测
		if(businessCommunication.getNdeRecord()!=null && "2011".equals(businessCommunication.getBcTypeDetail())){
			NdeRecord ndeRecord = businessCommunication.getNdeRecord();
			ndeRecord.setBcId(businessCommunication.getBcId());
			ndeRecord.setPushFlag(businessCommunication.getPushFlag());
			ndeRecordService.saveNdeRecord(ndeRecord);
		}
		//若为空则设置发起人单位类型
		if(StringUtil.isBlank(businessCommunication.getUnitTypeOfInitiator())){  
			businessCommunication.setUnitTypeOfInitiator(loginInfo.getUnitType());
		}
		businessCommunicationDao.saveOrUpdate(businessCommunication);
		
		
		
		return Constants.OPERATE_RESULT_SUCCESS;
	}

	@Override
	public String findReplyDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(businessCommunicationDao.getDatabaseDate());
	}

	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public String replyBusinessCommunication(BusinessCommunication businessCommunication)
			throws Exception {
		businessCommunication.setBcStatus(BcStatusEnum.REPLIED.getValue());
		
		businessCommunicationDao.update(businessCommunication);
		//无损检测通知，保存
		if(businessCommunication.getNdeRecord()!=null && "2010".equals(businessCommunication.getBcTypeDetail()) && StringUtil.isNotBlank(businessCommunication.getMarkOfTestResult())){
			//
			NdeRecord ndeRecordOld = ndeRecordDao.get(businessCommunication.getMarkOfTestResult());
			NdeRecord ndeRecord = businessCommunication.getNdeRecord();
			if(ndeRecordOld!=null && ndeRecord!=null&& ndeRecord.getSign()!=null){
				ndeRecordOld.setSign(ndeRecord.getSign());
				ndeRecordOld.setVersion(ndeRecordOld.getVersion()+1);
				ndeRecordOld.setBcRecipient(StringUtil.isNotBlank(ndeRecord.getBcRecipient())?ndeRecord.getBcRecipient():"");
				ndeRecordService.updateNdeRecord(ndeRecordOld);
			}
		}
		return Constants.OPERATE_RESULT_SUCCESS;
	}

	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public String delBusinessCommunication(String bcId)throws HibernateOptimisticLockingFailureException {
		BusinessCommunication bCommunication = businessCommunicationDao.get(bcId);
		businessCommunicationDao.delete(bCommunication);;
		return Constants.OPERATE_RESULT_SUCCESS;
	}

	@Override
	public NdeRecordBcDto viewByBcId(String bcId) {
		BusinessCommunication bCommunication = businessCommunicationDao.get(bcId);
		if(bCommunication !=null){
			Project project = projectDao.get(bCommunication.getProjId());
			List<DataFilerSetUpDto> list = Constants.getDataFilterMapByKey(project.getCorpId()+"_"+"120214");  //查询配置--该公司是否是现场代表发起无损检测，签字人是否是工程负责人
			if(list != null && list.size() > 0){
				bCommunication.setMark(list.get(0).getSupSql());  
			}
		}
		NdeRecordBcDto bcDto = new NdeRecordBcDto();
		if(bCommunication!=null){
			businessCommunicationDao.evict(bCommunication);//把持久化对象变成托管状态
			bcDto.setbCommunication(bCommunication);
		}
		NdeRecord ndeRecord = ndeRecordService.findByBcId(bcId);
		if(ndeRecord!=null){
			bcDto.setNdeRecord(ndeRecord);
			
		}else if(StringUtil.isNotBlank(bCommunication.getMarkOfTestResult())){//查看返修的无损检测
			NdeRecord ndeRecord1 = ndeRecordDao.get(bCommunication.getMarkOfTestResult());
			bcDto.setNdeRecord(ndeRecord1);
		}
		if(ndeRecord!=null && StringUtil.isBlank(ndeRecord.getNoticeNo())){
			long time = System.currentTimeMillis();
			ndeRecord.setNoticeNo("NO"+"-"+time);  //无损检测通知编号-取当前时间戳为业务通知单编号
		}
		return bcDto;
	}

	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void updateByBcId(BusinessCommunication bc) {
		businessCommunicationDao.updateVersionByBcId(bc);
	}
	
	
	/**
	 * 保存回调
	 * @author fuliwei
	 * @createTime 2018年1月21日
	 * @param 
	 * @return
	 */
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void saveSignNotice(String cwId) {
		String signState = SignStateEnum.ALREADY_SIGN.getValue();
		if(StringUtils.isNotBlank(cwId)){
			NdeRecord ndeRecord=ndeRecordDao.get(cwId);
			if(ndeRecord!=null){
				//suJgj 监理
				//suCse 总监
				//检查人
				if(StringUtils.isNotBlank(ndeRecord.getSuJgj())){
					//监理通知置为无效
					signNoticeService.saveThisSignNotice(SignPostEnum.SUJGJ.getValue(), SignDataTypeEnum.NDE_RECORD.getValue(),
							ndeRecord.getNrId(), ndeRecord.getProjId(),signState);
				}
				if(StringUtils.isNotBlank(ndeRecord.getSuCse())){
					//总监通知置为无效
					signNoticeService.saveThisSignNotice(SignPostEnum.SUCSE.getValue(), SignDataTypeEnum.NDE_RECORD.getValue(),
							ndeRecord.getNrId(), ndeRecord.getProjId(),signState);
				}
				if(StringUtils.isNotBlank(ndeRecord.getSuJgj())){
					//监理通知置为无效
					signNoticeService.saveThisSignNotice(SignPostEnum.SUJGJ.getValue(), SignDataTypeEnum.NDE_RECORD.getValue(),
							ndeRecord.getNrId(), ndeRecord.getProjId(),signState);
				}
			}
		}
	}

	/**
	 * 
	 */
	@Override
	public BusinessCommunication viewByTestResult(String nrId) {
		if(StringUtil.isNotBlank(nrId)){
			return businessCommunicationDao.get("markOfTestResult", nrId);
		}
		return new BusinessCommunication();
	}

	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void updateBuscomNotice(String projId, String origsuJgjId, String currSuJgjId,String currSuJgj) {
		businessCommunicationDao.updateBuscomNotice(projId,origsuJgjId,currSuJgjId,currSuJgj);

	}

}
