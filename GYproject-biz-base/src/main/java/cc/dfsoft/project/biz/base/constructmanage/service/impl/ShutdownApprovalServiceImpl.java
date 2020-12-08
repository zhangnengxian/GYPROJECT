package cc.dfsoft.project.biz.base.constructmanage.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cc.dfsoft.project.biz.base.constructmanage.dao.ShutdownApprovalDao;
import cc.dfsoft.project.biz.base.constructmanage.dto.ShutdownApprovalReq;
import cc.dfsoft.project.biz.base.constructmanage.entity.ShutdownApproval;
import cc.dfsoft.project.biz.base.constructmanage.enums.ShutdownPushStatusEnum;
import cc.dfsoft.project.biz.base.constructmanage.enums.ShutdownStatusEnum;
import cc.dfsoft.project.biz.base.constructmanage.service.ShutdownApprovalService;
import cc.dfsoft.project.biz.base.constructmanage.service.ShutdownRecordService;
import cc.dfsoft.project.biz.base.project.entity.Signature;
import cc.dfsoft.project.biz.base.project.enums.SignDataTypeEnum;
import cc.dfsoft.project.biz.base.project.enums.SignPostEnum;
import cc.dfsoft.project.biz.base.project.enums.SignStateEnum;
import cc.dfsoft.project.biz.base.project.service.SignNoticeService;
import cc.dfsoft.project.biz.base.project.service.SignatureService;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.util.IDUtil;
/**
 * 复工申报业务实现层
 * @author liaoyq
 *
 */
@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class ShutdownApprovalServiceImpl implements ShutdownApprovalService{

	@Resource
	private ShutdownApprovalDao approvalDao;
	
	@Resource 
	private SignatureService signatureService;
	
	@Resource
	private ShutdownRecordService shutdownRecordService;
	

	/**签字通知服务*/
	@Resource
	SignNoticeService signNoticeService;
	
	/**
	 * 根据记录id查询详述
	 */
	@Override
	public ShutdownApproval queryById(String id) {
		return approvalDao.get(id);
	}

	/**
	 * 分页查询记录
	 */
	@Override
	public Map<String, Object> queryList(ShutdownApprovalReq shutdownApprovalReq) {
		Map<String,Object> result = approvalDao.queryShutdownRecord(shutdownApprovalReq);
		if(result!=null){
			List<Map<String, Object>> pcls = (List<Map<String, Object>>) result.get("data");
			if(pcls!=null && pcls.size()>0){
				result.put("data", pcls);
			}
			
		}
		return result;
	}

	
	@Override
	public ShutdownApproval queryBySdrId(String sdrId) {
		return approvalDao.queryBySdrId(sdrId);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void saveShutdownApproval(ShutdownApproval shutdownApproval) throws Exception {
		boolean flag = false;
		shutdownApproval.setPushStatus(ShutdownPushStatusEnum.NO_PUSH.getValue());//推送状态-未推送
		if(StringUtils.isNotBlank(shutdownApproval.getSdaId())){
			approvalDao.update(shutdownApproval);
		}else{//新增
			flag = true;
			shutdownApproval.setSdaId(IDUtil.getUniqueId(Constants.MODULE_CODE_PROCESS));
			approvalDao.save(shutdownApproval);
		}
		
		
		List<Signature> signs=shutdownApproval.getSign();
		if(signs!=null && signs.size()>0){
			for(Signature sign:signs){
				sign.setDataType(SignDataTypeEnum.SHUTDOWN_APROVAL.getValue());
			}
			shutdownApproval.setSign(signs);
		}
		
		//签字保存
		signatureService.saveOrUpdateSign("menuId+menuNane+19",shutdownApproval.getSign(), shutdownApproval.getProjId(), shutdownApproval.getSdaId(), shutdownApproval.getClass().getName(), flag);
		
		//修改停工令状态
		updateShutdownRecordStatus(shutdownApproval);
	}
	
	/**
	 * 修改停工令状态
	 * @param sdaId
	 */
	private void updateShutdownRecordStatus(ShutdownApproval shutdownApproval ){
		//修改停工令的状态为复工申报中
		Integer sdrStatus = Integer.parseInt(ShutdownStatusEnum.APPROVALING.getValue());//复工报审中
		
		boolean res= shutdownRecordService.updateStatusById(shutdownApproval.getSdrId(),sdrStatus);
		System.err.println("修改停工令的状态"+res);
	}

	/**
	 * 推送复工报审（修改推送状态）
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void pushShutdownApprovl(ShutdownApproval shutdownApproval) {
		ShutdownApproval approval = approvalDao.get(shutdownApproval.getSdaId());
		approval.setPushStatus(ShutdownPushStatusEnum.PUSHED.getValue());//设置为已推送状态
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		approval.setPushStaffId(loginInfo.getStaffId());
		approval.setPushDate(approvalDao.getDatabaseDate());
		approvalDao.update(approval);
		
		//复工申报通过，修改停工令的状态为待复工
		Integer sdrStatus = Integer.parseInt(ShutdownStatusEnum.REWORK_ORDER.getValue());
		shutdownRecordService.updateStatusById(shutdownApproval.getSdrId(),sdrStatus);
			
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
			ShutdownApproval shutdownApproval=approvalDao.get(cwId);
			if(shutdownApproval!=null){
				//cuManager项目经理
				//suCes总监
				//custRepresent现场代表
				if(StringUtils.isNotBlank(shutdownApproval.getCuManager())){
					//项目经理通知置为无效
					signNoticeService.saveThisSignNotice(SignPostEnum.CU_PM.getValue(), SignDataTypeEnum.SHUTDOWN_APROVAL.getValue(),
							shutdownApproval.getSdaId(), shutdownApproval.getProjId(),signState);
				}
				if(StringUtils.isNotBlank(shutdownApproval.getSuCes())){
					//总监通知置为无效
					signNoticeService.saveThisSignNotice(SignPostEnum.SUCSE.getValue(), SignDataTypeEnum.SHUTDOWN_APROVAL.getValue(),
							shutdownApproval.getSdaId(), shutdownApproval.getProjId(),signState);
				}
				if(StringUtils.isNotBlank(shutdownApproval.getCustRepresent())){
					//现场代表通知置为无效
					signNoticeService.saveThisSignNotice(SignPostEnum.BUILDER.getValue(), SignDataTypeEnum.SHUTDOWN_APROVAL.getValue(),
							shutdownApproval.getSdaId(), shutdownApproval.getProjId(),signState);
				}
				
			}
		}
	}
}
