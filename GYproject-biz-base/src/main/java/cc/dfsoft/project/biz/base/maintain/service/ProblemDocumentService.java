package cc.dfsoft.project.biz.base.maintain.service;

import cc.dfsoft.project.biz.base.maintain.dto.ProblemDocumentReq;
import cc.dfsoft.project.biz.base.maintain.entity.ProblemDocument;

import java.util.List;
import java.util.Map;

/**
 * @ClassDesc: 问题单据
 * @Author zhangnx
 * @Date 2019/1/21 14:45
 * Version:1.0
 */
public interface ProblemDocumentService {

    Map<String, Object> queryProblemDocumentList(ProblemDocumentReq probDoctReq);

    boolean saveOrUpdateProblemDocument(ProblemDocument problemDoc);

    ProblemDocument viewProblemDocumentDetail(String pdId);

    List<ProblemDocument> exportExcelList(ProblemDocumentReq probDoctReq);

    Map<String, Object> findProblemTypeConut(ProblemDocumentReq probDoctReq);

    boolean deleteProblemDocumentById(String pdId);

}
