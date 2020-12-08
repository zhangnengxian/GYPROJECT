package cc.dfsoft.project.biz.base.subpackage.service;

import cc.dfsoft.project.biz.base.subpackage.dto.SubAuditReq;
import cc.dfsoft.project.biz.base.subpackage.entity.SubContract;

import java.text.ParseException;
import java.util.Map;

public interface SubContractAuditService {

    public Map<String,Object> querySubContractAuditList (SubAuditReq subAuditReq) throws ParseException;

    public Map<String,Object> getSubContractAuditById(String scId);
}
