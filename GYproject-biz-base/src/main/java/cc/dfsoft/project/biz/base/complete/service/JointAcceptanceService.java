package cc.dfsoft.project.biz.base.complete.service;

import cc.dfsoft.project.biz.base.complete.dto.JointAcceptanceReq;
import cc.dfsoft.project.biz.base.complete.entity.JointAcceptance;

import java.text.ParseException;
import java.util.Map;

public interface JointAcceptanceService {

	/**
	 * 保存联合验收单
	 * @param jointAcceptance
	 */
	String saveJoint(JointAcceptance jointAcceptance) throws Exception;

	/**
	 * 联合验收单详述
	 * @param id
	 */
	JointAcceptance jointDetail(String id);

	/**
	 * 联合验收单条件查询
	 * @param jointAcceptanceReq
	 * @return
	 */
	Map<String, Object> queryJointAcceptance(JointAcceptanceReq jointAcceptanceReq)throws ParseException;

	/**
	 * 保存联合验收单（生成状态）
	 * @param jointAcceptance
	 */	
	void entJoint(JointAcceptance jointAcceptance)throws Exception;
	
	/**
	 * 联合验收单详述
	 * @param id
	 * @param dataType
	 * @return
	 */
	JointAcceptance jointDetailByType(String projId,String dataType) throws ParseException;

	/**
	 * 标记验收单打印
	 * @param jaId
	 * @return
	 */
	void signJointAcceptancePrint(String jaId);
	
	/**
	 * 推送一站式验收	
	 * @author fuliwei
	 * @createTime 2017年9月5日
	 * @param 
	 * @return
	 */
	void entOneStopAcceptance(JointAcceptance jointAcceptance);
	
	/**
	 * 保存一站式验收
	 * @author fuliwei
	 * @createTime 2017年9月5日
	 * @param 
	 * @return
	 */
	void saveOneStopAcceptance(JointAcceptance jointAcceptance) throws Exception;

	/**
	 * 根据工程ID、验收类型 查询报验单信息
	 * 并返回报表信息
	 * @param projId
	 * @return
	 */
	String findPrintDataByProjId(String projId, String acceptType,String type);

	/**
	 * 根据联合验收id查询报验单信息
	 * 并返回报表信息
	 * @param jaId
	 * @return
	 */
    JointAcceptance jointView(String jaId);
    /**
	 * 保存回调
	 * @author fuliwei
	 * @createTime 2018年1月21日
	 * @param 
	 * @return
	 */
	public void saveSignNotice(String cwId);
	
	
	/**
	 * 根据jaId查标记记录为已打印
	 * @author wanghuijun
	 * @createTime 2018年9月25日
	 * @param jaId
	 * @return
	 * @throws Exception
	 */
	public void signDivisionalAcceptancePrint(String jaId) throws Exception;
	
	/**20190807
	 * 王会军
	 * 签字完成时，将工作通知置为无效，另生成
	 * 通知，通知相关人员签字已完成
	 * @param businId
	 * @param projId
	 * @param dataType
	 * @param menuId
	 * @param corpId
	 * @param projectType
	 * @param contributionMode
	 * @throws Exception
	 */
	void finshSignCreateNotice (String projId, String businId, String menuId, String stepId, String dataType) throws Exception;

    boolean rollBackContainsJointAcceptance(String projId, String fallbackReason);

    JointAcceptance findByProjId(String projId);
}
