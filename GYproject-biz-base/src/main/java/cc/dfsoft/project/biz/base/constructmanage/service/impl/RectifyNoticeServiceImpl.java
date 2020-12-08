package cc.dfsoft.project.biz.base.constructmanage.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import cc.dfsoft.project.biz.base.baseinfo.enums.AssistProgressTypeEnum;
import cc.dfsoft.project.biz.base.baseinfo.enums.WorkFlowTypeEnum;
import cc.dfsoft.project.biz.base.baseinfo.service.WorkFlowService;
import cc.dfsoft.project.biz.base.withgas.enums.GasProjectStatusEnum;
import cc.dfsoft.uexpress.common.enums.WorkFlowActionEnum;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cc.dfsoft.project.biz.base.constructmanage.dao.RectifyNoticeDao;
import cc.dfsoft.project.biz.base.constructmanage.dto.RectifyNoticeReq;
import cc.dfsoft.project.biz.base.constructmanage.entity.RectifyNotice;
import cc.dfsoft.project.biz.base.constructmanage.enums.RnStatusEnum;
import cc.dfsoft.project.biz.base.constructmanage.service.RectifyNoticeService;
import cc.dfsoft.project.biz.base.project.dao.ProjectDao;
import cc.dfsoft.project.biz.base.project.entity.Project;
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
 * 描述:整改通知业务实现
 * @author liaoyq
 * @createTime 2017年8月4日
 */
@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class RectifyNoticeServiceImpl implements RectifyNoticeService {

	/** 整改通知dao*/
	@Resource
	RectifyNoticeDao rectifyNoticeDao;
	/** 签字服务接口*/
	@Resource
	SignatureService signatureService;
	/**签字通知服务*/
	@Resource
	SignNoticeService signNoticeService;
	
	@Resource
	ProjectDao projectDao;

	@Resource
	WorkFlowService workFlowService;
	@Override
	public Map<String, Object> queryRectifyNotice(
			RectifyNoticeReq rectifyNoticeReq) throws ParseException {
		Map<String, Object> map = rectifyNoticeDao.queryRectifyNotice(rectifyNoticeReq);
		List<RectifyNotice> list = (List<RectifyNotice>)map.get("data");
		if(list != null && list.size() > 0){
			for (RectifyNotice rectifyNotice : list) {
				Project project = projectDao.get(rectifyNotice.getProjId());
				rectifyNotice.setCorpId(project.getCorpId());
			}
		}
		return map;
	}

	@Override
	public RectifyNotice viewRectifyNotice(String id) {
		return rectifyNoticeDao.get(id);
	}

	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public String saveRectifiyNotice(RectifyNotice rectifyNotice) throws Exception {
		boolean flag = false;
		if(StringUtils.isBlank(rectifyNotice.getRnId())){//新增
			flag = true;
			rectifyNotice.setRnId(IDUtil.getUniqueId(Constants.MODULE_CODE_PROCESS));//设置唯一id 施工过程过程
			LoginInfo loginInfo = SessionUtil.getLoginInfo();
			rectifyNotice.setRnCreateStaffId(loginInfo.getStaffId());
			rectifyNotice.setRnNo(getRnNo());
			rectifyNotice.setRnCreateStaff(loginInfo.getStaffName());
			rectifyNotice.setRnCreateTime(rectifyNoticeDao.getDatabaseDate());
			rectifyNotice.setCorpId(loginInfo.getCorpId());
			rectifyNotice.setCorpName(loginInfo.getCorpName());
			rectifyNotice.setDeptId(loginInfo.getDeptId());
			rectifyNotice.setTenantId(loginInfo.getTenantId());
			rectifyNotice.setRnDate(rectifyNoticeDao.getDatabaseDate());
			rectifyNotice.setRnStatus(RnStatusEnum.TO_PUSH.getValue());
			//todo:目前未存储
			//rectifyNotice.setOrgId(); 
		}
		rectifyNoticeDao.saveOrUpdate(rectifyNotice);
		
		List<Signature> signs=rectifyNotice.getSign();
		if(signs!=null && signs.size()>0){
			for(Signature sign:signs){
				sign.setDataType(SignDataTypeEnum.RECTIFY_NOTICE_BACK.getValue());
			}
			rectifyNotice.setSign(signs);
			signatureService.saveOrUpdateSign("menuId+menuNane+19",rectifyNotice.getSign(), rectifyNotice.getProjId(), rectifyNotice.getRnId(), rectifyNotice.getClass().getName(),flag);
			
		}
		
		return rectifyNotice.getRnId();
	}
	
	//整改通知编号
	public String getRnNo() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String rnNo = Constants.RECTIFY_NOTICE_NO_CODE + sdf.format(rectifyNoticeDao.getDatabaseDate());
		//System.err.println("rnNo"+rnNo);
		return rnNo;
	}

	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public String pushRectifyNotice(String rnId) {
		RectifyNotice rectifyNotice = rectifyNoticeDao.get(rnId);

		if(rectifyNotice!=null){
			Project project = projectDao.get(rectifyNotice.getProjId());
			//更新为下一个状态
			String status=workFlowService.queryAssistProgressStatusId(project.getCorpId(), null, null, rectifyNotice.getRnStatus(),
					true, WorkFlowTypeEnum.ASSIST_PROGRESS.getValue(), AssistProgressTypeEnum.RECTIFY_NOTICE.getValue());
			if(WorkFlowActionEnum.CONTRACT_END.getActionCode().equals(status)){
				rectifyNotice.setRnStatus(RnStatusEnum.TO_BACK.getValue());
			}else{
				rectifyNotice.setRnStatus(status);
			}
			rectifyNoticeDao.update(rectifyNotice);
		}
		return Constants.OPERATE_RESULT_SUCCESS;
	}

	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public String replyRectifyNotice(String rnId) {
		RectifyNotice rectifyNotice = rectifyNoticeDao.get(rnId);
		if(rectifyNotice!=null){
			//申请单状态更新为待回复
			rectifyNotice.setRnStatus(RnStatusEnum.BACKED.getValue());
			rectifyNoticeDao.update(rectifyNotice);
		}
		return Constants.OPERATE_RESULT_SUCCESS;
	}
	
	/**
	 * 保存回调
	 */
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void saveSignNotice(String cwId) {
		String signState = SignStateEnum.ALREADY_SIGN.getValue();
		RectifyNotice rectifyNotice = rectifyNoticeDao.get(cwId);
		if(rectifyNotice!=null){ 
			//cuPleaderBack项目经理
			//suJgjBack 现场监理
			if(StringUtils.isNotBlank(rectifyNotice.getCuPleaderBack())){
				//项目经理为无效
				signNoticeService.saveThisSignNotice(SignPostEnum.CU_PM.getValue(), SignDataTypeEnum.RECTIFY_NOTICE_BACK.getValue(),
						rectifyNotice.getRnId(), rectifyNotice.getProjId(),signState);
			}
			if(StringUtils.isNotBlank(rectifyNotice.getSuJgjBack())){
				//监理为无效
				signNoticeService.saveThisSignNotice(SignPostEnum.SUJGJ.getValue(), SignDataTypeEnum.RECTIFY_NOTICE_BACK.getValue(),
						rectifyNotice.getRnId(), rectifyNotice.getProjId(),signState);
			}
		}
	}

	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public String deletById(String rnId) {
		rectifyNoticeDao.delete(rnId);
		return Constants.OPERATE_RESULT_SUCCESS;
	}
	
}
