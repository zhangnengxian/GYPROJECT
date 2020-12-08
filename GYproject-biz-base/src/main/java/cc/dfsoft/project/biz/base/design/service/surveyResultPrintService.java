package cc.dfsoft.project.biz.base.design.service;

import cc.dfsoft.project.biz.base.design.dto.SurveyInfoQueryReq;

import java.text.ParseException;
import java.util.Map;

public interface surveyResultPrintService {
    /**
     * 查询需要打印的踏勘列表
     * @param
     * @return
     */
    public Map<String,Object> getSurveyResult(SurveyInfoQueryReq surveyInfoQueryReq) throws ParseException;

    /**
     * 打印标记
     * @param id
     */
    public String sign(String id);
}
