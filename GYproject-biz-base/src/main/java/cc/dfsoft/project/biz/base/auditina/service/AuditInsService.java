package cc.dfsoft.project.biz.base.auditina.service;

import cc.dfsoft.project.biz.base.auditina.dto.AuditInsReq;

import java.text.ParseException;
import java.util.Map;

public interface AuditInsService {

    public Map<String,Object> getDataList(AuditInsReq req) throws ParseException;
}
