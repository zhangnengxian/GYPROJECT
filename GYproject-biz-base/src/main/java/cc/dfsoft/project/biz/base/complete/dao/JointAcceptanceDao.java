package cc.dfsoft.project.biz.base.complete.dao;

import cc.dfsoft.project.biz.base.complete.dto.JointAcceptanceReq;
import cc.dfsoft.project.biz.base.complete.entity.JointAcceptance;
import cc.dfsoft.uexpress.common.dao.CommonDao;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

public interface JointAcceptanceDao extends CommonDao<JointAcceptance, String>{
	/**
	 * 联合验收条件查询
	 * @param jointAcceptanceReq
	 * @return
	 */
	Map<String, Object> queryJointAcceptance(JointAcceptanceReq jointAcceptanceReq)throws ParseException;

	/**
	 * 详述
	 * @param projId
	 * @return
	 */
	List<JointAcceptance> findById(String projId);
	/**
	 * 详述
	 * @param projId
	 * @param dataType
	 * @return
	 */
	List<JointAcceptance> findByType(String projId,String dataType);

	/**
	 * 根据工程id和验收类型查询验收信息
	 * @param projId
	 * @param value
	 * @return
	 */
	List<JointAcceptance> findByProjIdAndType(String projId, String value);

    int totalByProjId(String projId);
}
