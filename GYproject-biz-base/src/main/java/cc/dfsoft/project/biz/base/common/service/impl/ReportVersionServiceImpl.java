package cc.dfsoft.project.biz.base.common.service.impl;


import java.text.ParseException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cc.dfsoft.project.biz.base.common.dao.ReportVersionDao;
import cc.dfsoft.project.biz.base.common.dto.ReportVersionReq;
import cc.dfsoft.project.biz.base.common.entity.ReportVersion;
import cc.dfsoft.project.biz.base.common.service.ReportVersionService;
/**
 * 打印报表版本业务层实现
 * @author liaoyq
 * @createTime 2018年8月13日
 */
@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class ReportVersionServiceImpl implements ReportVersionService {

	@Resource
	ReportVersionDao reportVersionDao;
	/**
	 * 获取打印报表版本列表
	 * @throws ParseException 
	 */
	@Override
	public List<ReportVersion> queryReportVersions(
			ReportVersionReq reportVersionReq) throws ParseException {
		return reportVersionDao.queryReportVersions(reportVersionReq);
	}

}
