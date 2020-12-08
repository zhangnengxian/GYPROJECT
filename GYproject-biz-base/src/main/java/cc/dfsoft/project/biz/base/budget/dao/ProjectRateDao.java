package cc.dfsoft.project.biz.base.budget.dao;

import java.util.List;

import cc.dfsoft.project.biz.base.budget.dto.ProjectRateReq;
import cc.dfsoft.project.biz.base.budget.entity.ProjectRate;
import cc.dfsoft.uexpress.common.dao.CommonDao;

public interface ProjectRateDao extends CommonDao<ProjectRate, String>{
	public List<ProjectRate> queryProjectRate(ProjectRateReq projectRateReq);
}















