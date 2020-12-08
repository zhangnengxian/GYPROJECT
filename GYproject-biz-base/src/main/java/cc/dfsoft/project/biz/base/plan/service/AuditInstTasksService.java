package cc.dfsoft.project.biz.base.plan.service;

import java.text.ParseException;
import java.util.Map;

import cc.dfsoft.project.biz.base.plan.enums.ConstructionPlanQueryReq;
import cc.dfsoft.project.biz.base.project.dto.ProjectQueryReq;

public interface AuditInstTasksService {

	
	Map<String,Object> getAuditDataList(ProjectQueryReq req) throws ParseException;
	
}
