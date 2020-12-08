package cc.dfsoft.project.biz.base.inspection.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.sql.rowset.serial.SerialException;

import cc.dfsoft.project.biz.base.inspection.entity.CheckList;

/**
 * 报验信息接口
 * @author 王梦远
 * @
 *
 */
public interface CheckListService {
	/**
	 * 根据工程Id查询报验信息
	 */
	public List<CheckList> queryByProjId(String id);
	/**
	 * 质量自检查询
	 * @param projId
	 * @return
	 */
	public Map<String, String> viewSelfInspectionRecordQuqlity(String projId);
	void saveCheckList(List<CheckList> list) throws SerialException, SQLException;
}
