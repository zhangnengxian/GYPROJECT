package cc.dfsoft.project.biz.base.contract.dao;

import cc.dfsoft.project.biz.base.contract.dto.IntelligentSupplementReq;
import cc.dfsoft.project.biz.base.contract.entity.IntelligentSupplement;
import cc.dfsoft.uexpress.common.dao.CommonDao;

import java.util.List;
import java.util.Map;

public interface IntelligentSupplementDao extends CommonDao<IntelligentSupplement, String> {
    Map<String, Object> queryIntelligentSupplementList(IntelligentSupplementReq req);

    int countByProjId(String projId);

    Map<String, Object> findById(String id);

    Map<String, Object> queryToAuditSupplement(IntelligentSupplementReq req);

    List<IntelligentSupplement> queryListByisNo(String isNo);


    /**
     * 注释：根据工程编号查询智能表合同补充协议
     * @author liaoyq
     * @createTime 2019年8月26日
     * @param projId
     * @return
     *
     */
	List<IntelligentSupplement> findByProjId(String projId);
}
