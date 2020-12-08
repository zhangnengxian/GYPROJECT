package cc.dfsoft.project.biz.base.constructmanage.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import oracle.net.aso.a;
import oracle.net.aso.d;

import org.apache.batik.css.engine.value.css2.SrcManager;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cc.dfsoft.project.biz.base.constructmanage.dao.ShutdownApprovalDao;
import cc.dfsoft.project.biz.base.constructmanage.dao.ShutdownRecordDao;
import cc.dfsoft.project.biz.base.constructmanage.dto.ShutdownRecordReq;
import cc.dfsoft.project.biz.base.constructmanage.entity.ShutDownRecord;
import cc.dfsoft.project.biz.base.constructmanage.entity.ShutdownApproval;
import cc.dfsoft.project.biz.base.constructmanage.enums.ShutdownPushStatusEnum;
import cc.dfsoft.project.biz.base.constructmanage.enums.ShutdownStatusEnum;
import cc.dfsoft.project.biz.base.constructmanage.enums.ShutdownTypeEnum;
import cc.dfsoft.project.biz.base.constructmanage.service.ShutdownRecordService;
import cc.dfsoft.project.biz.base.inspection.entity.ProjectChecklist;
import cc.dfsoft.project.biz.base.project.dao.SignNoticeDao;
import cc.dfsoft.project.biz.base.project.service.SignNoticeService;
import cc.dfsoft.project.biz.base.project.service.SignatureService;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.util.IDUtil;
/**
 * 停复工业务实现层
 * @author liaoyq
 *
 */
@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class ShutdownRecordServiceImpl implements ShutdownRecordService {
	@Resource
	private ShutdownRecordDao shutdownRecordDao;
	
	@Resource
	private SignatureService signatureService;
	
	@Resource
	private ShutdownApprovalDao shutdownApprovalDao;
	
	@Resource
	SignNoticeDao signNoticeDao;

	@Override
	public Map<String, Object> queryShutdownRecord(
			ShutdownRecordReq shutdownRecordReq) {
		Map<String,Object> result = shutdownRecordDao.queryShutdownRecord(shutdownRecordReq);
		if(result!=null){
			List<ShutDownRecord> pcls = (List<ShutDownRecord>) result.get("data");
			if(pcls!=null && pcls.size()>0){
				for(int i=0;i<pcls.size();i++){
					ShutDownRecord pcl = pcls.get(i);
				}
				result.put("data", pcls);
			}
			
		}
		return result;
	}

	@Override
	public ShutDownRecord queryById(String id) {
		return shutdownRecordDao.queryById(id);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public String saveShutdownRecord(ShutDownRecord shutDownRecord) throws Exception {
		boolean flag = false;
		//增加
		shutDownRecord.setPushStatus(ShutdownPushStatusEnum.NO_PUSH.getValue());
		if(StringUtils.isNotBlank(shutDownRecord.getSdrId())){
			shutdownRecordDao.updateNotNullProperties(shutDownRecord, shutDownRecord.getSdrId());
		}else{
			flag = true;
			shutDownRecord.setSdrId(IDUtil.getUniqueId(Constants.MODULE_CODE_PROCESS));
			if(!StringUtils.isNotBlank(shutDownRecord.getSdrNo())){
				shutDownRecord.setSdrNo(getShutdownNo());
			}
			shutdownRecordDao.save(shutDownRecord);
		}
		//签字保存
		signatureService.saveOrUpdateSign("menuId+menuNane+20",shutDownRecord.getSign(), shutDownRecord.getProjId(), shutDownRecord.getSdrId(), shutDownRecord.getClass().getName(), flag);
		return shutDownRecord.getSdrId();
	}
	
	public String getShutdownNo() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String shutdownNo = Constants.SHUTDOWN_NO_CODE + sdf.format(shutdownRecordDao.getDatabaseDate());
		//System.err.println("shutdownNo"+shutdownNo);
		return shutdownNo;
	}

	@Override
	public Integer queryByCondition(ShutDownRecord shutDownRecord) {
		return shutdownRecordDao.queryByCondition(shutDownRecord);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public boolean updateStatusById(String sdrId,Integer sdrStatus) {
		 return shutdownRecordDao.updateStatusById(sdrId,sdrStatus);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void updateStatusBySdrNo(String sdrNo, String sdrType ,Integer sdrStatus) {
		shutdownRecordDao.updateStatusBySdrNo(sdrNo,sdrType,sdrStatus);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void pushShutdownRecord(ShutDownRecord shutDownRecord) {
		ShutDownRecord downRecord = shutdownRecordDao.get(shutDownRecord.getSdrId());
		if(downRecord!=null){
			downRecord.setPushStatus(ShutdownPushStatusEnum.PUSHED.getValue());
			
			//停工令推送
			if(downRecord.getSdrType().equals(ShutdownTypeEnum.SHUTDOWN.getValue())){
				downRecord.setSdrStatus(Integer.parseInt(ShutdownStatusEnum.REWORK_APPROVAL.getValue()));//待复工报审状态
				//增加停工报审基本信息
				ShutdownApproval approval = new ShutdownApproval();
				approval.setSdaId(IDUtil.getUniqueId(Constants.MODULE_CODE_PROCESS));
				approval.setSdrId(downRecord.getSdrId()); //停工ID
				approval.setSdrNo(downRecord.getSdrNo()); //停工编号
				approval.setProjId(downRecord.getProjId());//工程ID
				approval.setPushStatus(ShutdownPushStatusEnum.NO_PUSH.getValue());
				shutdownApprovalDao.save(approval);
			}else{
				downRecord.setSdrStatus(Integer.parseInt(ShutdownStatusEnum.REWORK_ORDERED.getValue()));//已复工状态
				//修改停工令的状态为已复工
				shutdownRecordDao.updateStatusBySdrNo(downRecord.getSdrNo(),ShutdownTypeEnum.SHUTDOWN.getValue(), Integer.parseInt(ShutdownStatusEnum.REWORK_ORDERED.getValue()));
				//复工令推送 todo:
			}
			
			LoginInfo loginInfo = SessionUtil.getLoginInfo();
			downRecord.setPushStaffId(loginInfo.getStaffId());
			downRecord.setPushDate(shutdownRecordDao.getDatabaseDate());
			shutdownRecordDao.update(downRecord);
		}
		
	}
	
	/**
	 * 删除停复工记录
	 * create person wanghuijun
	 * 2019年5月31
	 * @return
	 */
	@Override
	@Transactional(readOnly = false,propagation = Propagation.REQUIRED)
	public String deleteSDRecordBySDRId(ShutDownRecord shutDownRecord) throws Exception {
		// TODO Auto-generated method stub
		shutdownRecordDao.deleteSDRecordBySDRId(shutDownRecord);
		signNoticeDao.deleteSignNoticeByBsId(shutDownRecord.getSdrId());  //删除签字通知
		return Constants.OPERATE_RESULT_SUCCESS;
	}

}
