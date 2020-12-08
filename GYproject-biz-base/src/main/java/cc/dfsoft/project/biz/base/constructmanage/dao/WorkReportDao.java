package cc.dfsoft.project.biz.base.constructmanage.dao;

import java.util.List;

import cc.dfsoft.project.biz.base.constructmanage.entity.WorkReport;
import cc.dfsoft.project.biz.base.project.entity.OperateRecord;
import cc.dfsoft.uexpress.common.dao.CommonDao;
/**
 * 开工报告Dao
 * @author Administrator
 *
 */
public interface WorkReportDao extends CommonDao<WorkReport,String> {

	List<WorkReport> findByProjId(String projId,String dataType);
	
	/**
	 * 用工程id和操作步骤查操作记录
	 * @author
	 * @createTime 2016-11-24
	 * @param
	 * @return
	 */
	public List<OperateRecord> getOr(String projId,String StepId,String modifyStatus); 
	
}
