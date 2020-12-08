package cc.dfsoft.project.biz.base.budget.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestBody;

import cc.dfsoft.project.biz.base.budget.dto.ProjectRateReq;
import cc.dfsoft.project.biz.base.budget.entity.ProjectRate;

public interface ProjectRateService {
	public List<Map<String,Object>> queryProjectRate(ProjectRateReq projectRateReq);
	
	public ProjectRate queryProjectRateById(String id);
	
	public String saveOrUpdateprojectRate( String id, BigDecimal number, String name);
}
