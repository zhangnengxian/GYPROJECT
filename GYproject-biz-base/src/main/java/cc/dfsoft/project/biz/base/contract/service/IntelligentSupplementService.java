package cc.dfsoft.project.biz.base.contract.service;

import cc.dfsoft.project.biz.base.contract.dto.IntelligentSupplementReq;
import cc.dfsoft.project.biz.base.contract.entity.IntelligentSupplement;

import java.util.Map;

public interface IntelligentSupplementService {
    Map<String, Object> queryIntelligentSupplementList(IntelligentSupplementReq req);

    Map<String, Object> findById(String id);


    boolean saveOrUpdateintelligentSupplement(IntelligentSupplement intell);

    Map<String, Object> queryToAuditSupplement(IntelligentSupplementReq req);
}
