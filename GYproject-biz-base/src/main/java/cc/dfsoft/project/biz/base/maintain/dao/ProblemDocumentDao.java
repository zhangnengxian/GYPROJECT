package cc.dfsoft.project.biz.base.maintain.dao;

import cc.dfsoft.project.biz.base.maintain.dto.ProblemDocumentReq;
import cc.dfsoft.project.biz.base.maintain.entity.ProblemDocument;
import cc.dfsoft.uexpress.common.dao.CommonDao;

import java.util.List;
import java.util.Map;

public interface ProblemDocumentDao extends CommonDao<ProblemDocument, String> {



    /**
     * @MethodDesc: 问题单据查询List
     * @Author zhangnx
     * @Date 2019/1/21 16:49
     */
    Map<String, Object> queryProblemDocumentList(ProblemDocumentReq probDoctReq) throws Exception;

    List<ProblemDocument> exportExcelList(ProblemDocumentReq probDoctReq)throws Exception;

    Map<String, Object> findProblemTypeConut(ProblemDocumentReq probDoctReq);

    void deleteProblemDocumentById(String pdId);
}
