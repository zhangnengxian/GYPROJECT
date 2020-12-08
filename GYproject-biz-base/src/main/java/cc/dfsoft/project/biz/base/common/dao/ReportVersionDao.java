package cc.dfsoft.project.biz.base.common.dao;

import java.text.ParseException;
import java.util.List;

import cc.dfsoft.project.biz.base.common.dto.ReportVersionReq;
import cc.dfsoft.project.biz.base.common.entity.ReportVersion;
import cc.dfsoft.uexpress.common.dao.CommonDao;

/**
 * 打印模板版本控制dao接口
 * @author liaoyq
 * @createTime 2018年8月13日
 */
public interface ReportVersionDao extends CommonDao<ReportVersion, String> {

	/**
	 * 获取打印报表版本里列表
	 * @author liaoyq
	 * @createTime 2018年8月13日
	 * @param reportVersionReq
	 * @return
	 * @throws ParseException 
	 */
	List<ReportVersion> queryReportVersions(ReportVersionReq reportVersionReq) throws ParseException;

}
