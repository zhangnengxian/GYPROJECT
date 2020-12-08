package cc.dfsoft.project.biz.base.subpackage.dao;

import cc.dfsoft.project.biz.base.subpackage.dto.SubAuditReq;
import cc.dfsoft.project.biz.base.subpackage.entity.SubContract;
import cc.dfsoft.uexpress.common.dao.CommonDao;

import java.text.ParseException;
import java.util.Map;

public interface SurveySubContractAuditDao extends CommonDao<SubContract, String> {
    public Map<String, Object> querySubContractAuditList(SubAuditReq subAuditReq) throws ParseException;

    public  Map<String,Object>  getSubContractAuditById(String scId);
}
