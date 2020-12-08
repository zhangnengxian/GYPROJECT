package cc.dfsoft.project.biz.base.complete.service;

import java.text.ParseException;
import java.util.Map;

import cc.dfsoft.project.biz.base.complete.dto.JointAcceptanceApplyReq;
import cc.dfsoft.project.biz.base.complete.dto.JointAcceptanceReq;
import cc.dfsoft.project.biz.base.complete.entity.JointAcceptance;
import cc.dfsoft.project.biz.base.project.dto.ProjectQueryReq;

/**
 * 
 * 描述:联合验收申请service实现类
 * @author liaoyq
 * @createTime 2018年9月9日
 */
public interface JointAcceptanceApplyService {

	/**
	 * 根据工程ID，联合验收ID查询联合验收申请记录
	 * @param projId
	 * @param jaaId
	 * @return
	 */
	JointAcceptance findById(String projId);

	/**
	 * 保存联合验收申请记录
	 * @param jointAcceptanceApply
	 */
	void saveJointAcceptanceApply(JointAcceptance jointAcceptanceApply);

	/**
	 * 查询联合验收审核工程
	 * @param req
	 * @return
	 * @throws ParseException 
	 */
	Map<String, Object> queryJointAcceptanceAudit(ProjectQueryReq req) throws ParseException;


}
