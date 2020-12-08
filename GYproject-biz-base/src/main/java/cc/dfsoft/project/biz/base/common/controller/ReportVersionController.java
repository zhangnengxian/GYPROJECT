package cc.dfsoft.project.biz.base.common.controller;

import cc.dfsoft.project.biz.base.common.dto.ReportVersionReq;
import cc.dfsoft.project.biz.base.common.entity.ReportVersion;
import cc.dfsoft.project.biz.base.common.service.ReportVersionService;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.project.service.ProjectService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * 报表打印版本控制
 * @author liaoyq
 * @createTime 2018年8月13日
 */
@Controller
@RequestMapping(value="/reportVersion")
public class ReportVersionController {

	@Resource
	ReportVersionService reportVersionService;

	@Resource
	ProjectService projectService;
	/**
	 * 查询版本控制列表
	 * @author liaoyq
	 * @createTime 2018年8月13日
	 * @param key
	 * @return
	 */
	@RequestMapping(value="/queryReportVersion")
	@ResponseBody
	public List<ReportVersion> queryReportVersions(ReportVersionReq reportVersionReq){
		try {

			return reportVersionService.queryReportVersions(reportVersionReq);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * @ Description: 功能描述
	 * @ author zhangnx
	 * @ date 2019/11/7 16:02
	 **/
	@RequestMapping(value="loadReportVersion")
	@ResponseBody
	public List<ReportVersion> loadReportVersion(String projId,String menuId){
		Project project = projectService.findById(projId);
		if (project==null){return null;}
		ReportVersionReq reportVersionReq = new ReportVersionReq();
		reportVersionReq.setProjType(project.getContributionMode());//出资方式
		reportVersionReq.setMenuId(menuId);//施工结算书打印
		reportVersionReq.setCorpId(project.getCorpId());
		try {
			List<ReportVersion> reportVersions = reportVersionService.queryReportVersions(reportVersionReq);
			return reportVersions;
		}catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}
}
