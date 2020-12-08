package cc.dfsoft.project.biz.base.constructmanage.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cc.dfsoft.project.biz.base.baseinfo.dao.BusinessPartnersDao;
import cc.dfsoft.project.biz.base.baseinfo.entity.BusinessPartners;
import cc.dfsoft.project.biz.base.constructmanage.dao.BusinessCommunicationDao;
import cc.dfsoft.project.biz.base.constructmanage.dao.NdeRecordDao;
import cc.dfsoft.project.biz.base.constructmanage.entity.BusinessCommunication;
import cc.dfsoft.project.biz.base.constructmanage.entity.NdeRecord;
import cc.dfsoft.project.biz.base.constructmanage.enums.BcStatusEnum;
import cc.dfsoft.project.biz.base.constructmanage.enums.TestResultEnum;
import cc.dfsoft.project.biz.base.constructmanage.service.BusinessCommunicationService;
import cc.dfsoft.project.biz.base.constructmanage.service.NdeRecordService;
import cc.dfsoft.project.biz.base.contract.dao.ContractDao;
import cc.dfsoft.project.biz.base.contract.entity.Contract;
import cc.dfsoft.project.biz.base.project.dao.ProjectDao;
import cc.dfsoft.project.biz.base.project.dao.SignNoticeDao;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.project.entity.SignNotice;
import cc.dfsoft.project.biz.base.project.entity.Signature;
import cc.dfsoft.project.biz.base.project.enums.SignDataTypeEnum;
import cc.dfsoft.project.biz.base.project.enums.SignStateEnum;
import cc.dfsoft.project.biz.base.project.service.SignatureService;
import cc.dfsoft.project.biz.base.subpackage.entity.SubContract;
import cc.dfsoft.project.biz.base.subpackage.service.SubContractService;
import cc.dfsoft.uexpress.biz.sys.dept.dao.DepartmentDao;
import cc.dfsoft.uexpress.biz.sys.dept.dao.StaffDao;
import cc.dfsoft.uexpress.biz.sys.dept.entity.Department;
import cc.dfsoft.uexpress.biz.sys.dept.entity.Staff;
import cc.dfsoft.uexpress.biz.sys.dept.enums.PostTypeEnum;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.util.IDUtil;
import cc.dfsoft.uexpress.common.util.StringUtil;
/**
 * 
 * 描述:无损检测业务实现层
 * @author liaoyq
 * @createTime 2017年9月27日
 */
@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class NdeRecordServiceImpl implements NdeRecordService {

	@Resource
	NdeRecordDao ndeRecordDao;
	@Resource
	ContractDao contractDao;
	@Resource
	SubContractService subContractService;
	@Resource
	SignatureService signatureService;
	
	@Resource
	BusinessPartnersDao businessPartnersDao;
	
	@Resource
	BusinessCommunicationDao businessCommunicationDao;
	
	@Resource
	StaffDao staffDao;
	
	@Resource
	DepartmentDao departmentDao;
	
	@Resource
	BusinessCommunicationService businessCommunicationService;
	@Resource
	ProjectDao projectDao;
	@Resource
	SignNoticeDao signNoticeDao;
	/**
	 * 保存无损检测信息
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public String saveNdeRecord(NdeRecord ndeRecord) throws Exception {
		if(StringUtil.isNotBlank(ndeRecord.getProjId())){
			Project project  = projectDao.get(ndeRecord.getProjId());
			//安装合同
			Contract contract = contractDao.viewContractByprojId(ndeRecord.getProjId());
			if(contract!=null){
				ndeRecord.setConNo(contract.getConNo());//安装合同号
			}
			//分包合同
			SubContract subContract = subContractService.findSubContractByprojId(ndeRecord.getProjId());
			if(subContract!=null){
				ndeRecord.setScNo(subContract.getScNo());//分包合同号
				ndeRecord.setCuName(subContract.getCuName());//分包单位
			}
			boolean flag = false;
			if(StringUtil.isBlank(ndeRecord.getNrId())){
				ndeRecord.setNrId(IDUtil.getUniqueId(Constants.MODULE_CODE_PROCESS));
				flag = true;
			}
			
			
		   //若是返修，则生成一条工作联系函通知单，且只能有一条返修记录
			if(ndeRecord.getTestResult() != null && ndeRecord.getTestResult().equals(TestResultEnum.REPAIRED.getValue())){
				BusinessCommunication businessCommunication = new BusinessCommunication();  //新建实体
				BusinessCommunication bucc = businessCommunicationDao.get(ndeRecord.getBcId());  //通过id查询实体
				businessCommunication.setProjId(ndeRecord.getProjId());  //设置工程ID
				businessCommunication.setProjName(project.getProjName()); //设置工程名称
				businessCommunication.setProjNo(project.getProjNo());  //设置工程编号
				businessCommunication.setBcType("20");  //通知类型为通知
				businessCommunication.setBcTypeDetail("2010"); //通知细类为检测报告
				businessCommunication.setBcStatus(BcStatusEnum.TO_REPLY.getValue());  //1为待回复
				businessCommunication.setBcInitiatorId(bucc.getBcRecipientId());  //设置发起人Id 
				businessCommunication.setBcInitiatorName(bucc.getBcRecipientName());  //设置发起人姓名
				businessCommunication.setBcRecipientId(bucc.getBcInitiatorId());  //设置接收人ID
				businessCommunication.setBcRecipientName(bucc.getBcInitiatorName()); //设置接收人姓名
				businessCommunication.setNoticeDate(businessCommunicationDao.getDatabaseDate());  //设置通知日期
				businessCommunication.setNoticeContent(ndeRecord.getNoticeNo()+"_"+TestResultEnum.REPAIRED.getMessage());  //通知内容
				businessCommunication.setPushFlag("1");  //设置推送状态
				businessCommunication.setMarkOfTestResult(ndeRecord.getNrId());  //将无损检测的nrid和业务沟通中的MarkOFTestResult关联起来
				if(bucc.getBcInitiatorId() != null){ //若不等于null
					Staff staff = staffDao.get(bucc.getBcInitiatorId());
					businessCommunication.setUnitType(staff.getUnitType());  //设置单位类型
					
					businessCommunication.setDeptId(staff.getDeptId());
					Department department = departmentDao.get(staff.getDeptId());
					BusinessPartners businessPartners = businessPartnersDao.get(staff.getDeptId());
					if(department != null ){ //若部门名称不为空
						businessCommunication.setBcRecipientDeptName(department.getDeptName());
					}else if (businessPartners != null){ //若业务合作伙伴名称不为空
						businessCommunication.setBcRecipientDeptName(businessPartners.getUnitName());
					}
					
				}
				if(bucc !=null){
					businessCommunicationDao.evict(bucc); //删除实体
				}
				businessCommunication.setVersion(ndeRecord.getVersion());
				businessCommunicationService.saveBusinessCommunication(businessCommunication); //保存实体
			}
			ndeRecordDao.saveOrUpdate(ndeRecord);
			List<Signature> signs=ndeRecord.getSign();
			if(signs!=null && signs.size()>0){
				for(Signature sign:signs){
					//根据签字fieldName判断在哪一个单据签字通知下
					if("suJgj".equals(sign.getFieldName()) || "suCse".equals(sign.getFieldName()) ){
						sign.setDataType(SignDataTypeEnum.BUS_CON.getValue());
					}else{
						sign.setDataType(SignDataTypeEnum.NDE_RECORD.getValue());
					}
				}
				ndeRecord.setSign(signs);
			}
			//签字保存
			signatureService.saveOrUpdateSign("menuId+menuNane+16",ndeRecord.getSign(), ndeRecord.getProjId(), ndeRecord.getNrId(), ndeRecord.getClass().getName(), flag);
			//业务沟通推送时，增加检测员的签字通知
			if("1".equals(ndeRecord.getPushFlag()) && Constants.BUS_CON.equals(ndeRecord.getMenuId())){
				addCheckerSignNotice(ndeRecord,project);
			}
		}
		return ndeRecord.getNrId();
	}

	/**
	 * 探伤委托推送的时候，生成检测人员的签字通知
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void addCheckerSignNotice(NdeRecord ndeRecord,Project project) {
		SignNotice signNotice=new SignNotice();
		signNotice.setSignNoticeId(IDUtil.getUniqueId(Constants.MODULE_CODE_NOTICE));
		signNotice.setPost(PostTypeEnum.CHECKER.getValue());//职务
		signNotice.setPostName(PostTypeEnum.CHECKER.getMessage());
		signNotice.setDataType(SignDataTypeEnum.NDE_RECORD.getValue()); //资料类型
		signNotice.setDataTypeName(SignDataTypeEnum.NDE_RECORD.getMessage());
		signNotice.setProjId(ndeRecord.getProjId());
		signNotice.setSortNo("1");
		signNotice.setProjNo(project.getProjNo());
		signNotice.setProjName(project.getProjName());
		signNotice.setBusinessOrderId(ndeRecord.getNrId());
		signNotice.setStatus(SignStateEnum.READY_SIGN.getValue());//准备签字
		signNoticeDao.save(signNotice);
	}

	@Override
	public NdeRecord findByBcId(String bcId) {
		return ndeRecordDao.findByBcId(bcId);
	}
	
	/**
	 * 通过工程id查询探伤委托
	 * @author fuliwei
	 * @createTime 2017年12月10日
	 * @param  projId
	 * @return NdeRecord
	 */
	@Override
	public NdeRecord findBypProjId(String projId) {
		return ndeRecordDao.findBypProjId(projId);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void update(NdeRecord nd) {
		ndeRecordDao.update(nd);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void updateByNdId(NdeRecord nd) {
		ndeRecordDao.updateVersionByNdId(nd);
	}
	/**
	 * 保存无损检测信息
	 * @throws Exception 
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void updateNdeRecord(NdeRecord ndeRecord) throws Exception {
		if(ndeRecord!=null){
			ndeRecordDao.saveOrUpdate(ndeRecord);
			List<Signature> signs=ndeRecord.getSign();
			if(signs!=null && signs.size()>0){
				for(Signature sign:signs){
					sign.setDataType(SignDataTypeEnum.NDE_RECORD.getValue());
				}
				ndeRecord.setSign(signs);
			}
			//保存签字
			//签字保存
			signatureService.saveOrUpdateSign("menuId+menuNane+17",ndeRecord.getSign(), ndeRecord.getProjId(), ndeRecord.getNrId(), ndeRecord.getClass().getName(), false);
		}
	}
}
