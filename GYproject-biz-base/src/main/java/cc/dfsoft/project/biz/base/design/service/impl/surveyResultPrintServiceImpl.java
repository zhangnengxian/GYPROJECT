package cc.dfsoft.project.biz.base.design.service.impl;

import cc.dfsoft.project.biz.base.contract.enums.ContractIsPrintEnum;
import cc.dfsoft.project.biz.base.design.dao.SurveyInfoDao;
import cc.dfsoft.project.biz.base.design.dto.SurveyInfoQueryReq;
import cc.dfsoft.project.biz.base.design.entity.SurveyInfo;
import cc.dfsoft.project.biz.base.design.service.surveyResultPrintService;
import cc.dfsoft.uexpress.common.constant.Constants;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fr.fs.web.service.ServerConfigGetInfoAction;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.Map;

@Service
public class surveyResultPrintServiceImpl implements surveyResultPrintService {
    /**勘察信息dao*/
    @Resource
    SurveyInfoDao surveyInfoDao;
    @Override
    public Map<String, Object> getSurveyResult(SurveyInfoQueryReq surveyInfoQueryReq) throws ParseException {

        return surveyInfoDao.getSurveyInfoList(surveyInfoQueryReq);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public String sign(String id) {
    	SurveyInfo  surveyInfo = surveyInfoDao.get(id);  //根据踏勘ID查找出记录
    	if(surveyInfo !=null){
    		surveyInfo.setSigns(ContractIsPrintEnum.ALREADY_PRINT.getValue());  //置为已打印
    	}
        return Constants.OPERATE_RESULT_SUCCESS;
    }

}
