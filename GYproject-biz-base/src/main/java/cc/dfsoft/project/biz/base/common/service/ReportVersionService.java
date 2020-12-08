package cc.dfsoft.project.biz.base.common.service;

import java.text.ParseException;
import java.util.List;

import cc.dfsoft.project.biz.base.common.dto.ReportVersionReq;
import cc.dfsoft.project.biz.base.common.entity.ReportVersion;

/**
 * 打印报表版本控制业务层接口
 * @author liaoyq
 * @createTime 2018年8月13日
 */
public interface ReportVersionService {

	/**
	 * 获取打印报表版本控制信息列表
	 * @author liaoyq
	 * @createTime 2018年8月13日
	 * @param reportVersionReq
	 * @return
	 * @throws ParseException 
	 */
	List<ReportVersion> queryReportVersions(ReportVersionReq reportVersionReq) throws ParseException;

}
