package cc.dfsoft.project.biz.base.constructmanage.dao;

import cc.dfsoft.project.biz.base.constructmanage.dto.CompleteReportReq;
import cc.dfsoft.project.biz.base.constructmanage.entity.CompleteReport;
import cc.dfsoft.uexpress.common.dao.CommonDao;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author Yuanyx
 *
 */
public interface CompleteReportDao extends CommonDao<CompleteReport,String> {

	/**
	 * 竣工报报告列表查询
	 * @param completeReportReq
	 * @return
	 */
	Map<String, Object> queryCompleteReport(CompleteReportReq completeReportReq);

	/**
	 * 根据竣工报告id查询详述
	 * @param crId
	 * @return
	 */
	CompleteReport findByCrId(String crId);

	void deleteById(String crId);

	/**
	 * 根据工程ID查询竣工报告
	 * @param projId
	 * @return
	 */
	List<CompleteReport> findByProjId(String projId);

	/**
	 * 查询未删除的竣工报告
	 * @author liaoyq
	 * @createTime 2018年6月15日
	 * @param projId
	 * @param cfFlag
	 * @return
	 */
	CompleteReport findByProjId(String projId, String cfFlag);

	/**
	 * 标记删除
	 * @author liaoyq
	 * @createTime 2018年7月27日
	 * @param projId
	 * @param isDel
	 */
	void deleteByProjId(String projId, String isDel);
	
}
