package cc.dfsoft.project.biz.base.accept.service;

import cc.dfsoft.project.biz.base.project.dto.ProjectQueryReq;
import cc.dfsoft.project.biz.base.project.entity.Project;

import java.text.ParseException;
import java.util.Map;

public interface AccepAuditService {

    public Map<String,Object> getAccepAuditList(ProjectQueryReq req) throws ParseException;

    public Project getProjectById(String projId);
}
