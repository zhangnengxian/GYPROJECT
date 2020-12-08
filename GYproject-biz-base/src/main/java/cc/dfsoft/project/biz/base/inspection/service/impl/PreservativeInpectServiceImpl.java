package cc.dfsoft.project.biz.base.inspection.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cc.dfsoft.project.biz.base.inspection.dao.PreservativeInpectDao;
import cc.dfsoft.project.biz.base.inspection.dao.ProjectChecklistDao;
import cc.dfsoft.project.biz.base.inspection.dto.PreservativeInpectReq;
import cc.dfsoft.project.biz.base.inspection.entity.PreservativeInpect;
import cc.dfsoft.project.biz.base.inspection.entity.ProjectChecklist;
import cc.dfsoft.project.biz.base.inspection.service.PreservativeInpectService;
import cc.dfsoft.project.biz.base.project.enums.SignDataTypeEnum;
import cc.dfsoft.project.biz.base.project.enums.SignPostEnum;
import cc.dfsoft.project.biz.base.project.enums.SignStateEnum;
import cc.dfsoft.project.biz.base.project.service.SignNoticeService;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.util.IDUtil;
/**
 * 防腐检查服务实现层
 * @author liaoyq
 *
 */
@Service
@Transactional(readOnly = true,propagation=Propagation.REQUIRED)
public class PreservativeInpectServiceImpl implements PreservativeInpectService {
	@Resource
	private PreservativeInpectDao preservativeInpectDao;
	
	/**报验单*/
	@Resource
	ProjectChecklistDao projectCheckListDao;


	/**签字通知服务*/
	@Resource
	SignNoticeService signNoticeService;

	
	/**
	 * 保存焊条领用记录
	 * 先删除已有的记录，再插入记录数据
	 * @param request
	 * @param checkListERReq
	 */
	@Override
	@Transactional(readOnly = false,propagation= Propagation.REQUIRED)
	public void savePreservativeInpect(PreservativeInpect preservativeInpect) {
		if(preservativeInpect!=null){
			if(preservativeInpect.getPcId()!=null){
				//生成piId
				if(preservativeInpect.getPiId()==null||"".equals(preservativeInpect.getPiId())){
					preservativeInpect.setPiId(IDUtil.getUniqueId(Constants.MODULE_CODE_PRESERVATIVE_INPECT));
				}
				//保存信息
				preservativeInpectDao.saveOrUpdate(preservativeInpect);
			}
		}
	}

	@Override
	public Map<String, Object> queryPreservativeInpect(
			PreservativeInpectReq preservativeInpect) {
		return preservativeInpectDao.queryPreservativeInpect(preservativeInpect);
	}

	@Override
	public PreservativeInpect queryByPcId(String pcId) {
		
		return preservativeInpectDao.queryByPcId(pcId);
	}
	
	/**
	 * 保存回调
	 * @author fuliwei
	 * @createTime 2018年1月21日
	 * @param 
	 * @return
	 */
	@Override
	@Transactional(readOnly = false,propagation= Propagation.REQUIRED)
	public void saveSignNotice(String cwId) {
		String signState = SignStateEnum.ALREADY_SIGN.getValue();
		//项目经理projectLeader
		//质检员constructionQc
		//施工员builder
		//现场监理suJgj
		//现场代表fieldsRepresent
		if(StringUtils.isNotBlank(cwId)){
			ProjectChecklist list=projectCheckListDao.get(cwId);
			if(list!=null){
				if(StringUtils.isNotBlank(list.getBuilder())){
					//施工员通知置为无效
					signNoticeService.saveThisSignNotice(SignPostEnum.CONSTRUCTION.getValue(), SignDataTypeEnum.PRESERVATIVE_INPECT.getValue(),
							list.getPcId(), list.getProjId(),signState);
				}
				if(StringUtils.isNotBlank(list.getProjectLeader())){
					//项目经理通知置为无效
					signNoticeService.saveThisSignNotice(SignPostEnum.CU_PM.getValue(), SignDataTypeEnum.PRESERVATIVE_INPECT.getValue(),
							list.getPcId(), list.getProjId(),signState);
				}
				if(StringUtils.isNotBlank(list.getConstructionQc())){
					//质检员通知置为无效
					signNoticeService.saveThisSignNotice(SignPostEnum.QUALITATIVE_CHECK_MEMBER.getValue(), SignDataTypeEnum.PRESERVATIVE_INPECT.getValue(),
							list.getPcId(), list.getProjId(),signState);
				}
				if(StringUtils.isNotBlank(list.getSuJgj())){
					//现场监理通知置为无效
					signNoticeService.saveThisSignNotice(SignPostEnum.SUJGJ.getValue(), SignDataTypeEnum.PRESERVATIVE_INPECT.getValue(),
							list.getPcId(), list.getProjId(),signState);
				}
				if(StringUtils.isNotBlank(list.getFieldsRepresent())){
					//现场代表通知置为无效
					signNoticeService.saveThisSignNotice(SignPostEnum.BUILDER.getValue(), SignDataTypeEnum.PRESERVATIVE_INPECT.getValue(),
							list.getPcId(), list.getProjId(),signState);
				}
			}
		}
	}
}
