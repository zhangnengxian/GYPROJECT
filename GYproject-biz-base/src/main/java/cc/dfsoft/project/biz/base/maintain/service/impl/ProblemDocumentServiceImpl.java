package cc.dfsoft.project.biz.base.maintain.service.impl;


import cc.dfsoft.project.biz.base.maintain.dao.ProblemDocumentDao;
import cc.dfsoft.project.biz.base.maintain.dto.ProblemDocumentReq;
import cc.dfsoft.project.biz.base.maintain.entity.ProblemDocument;
import cc.dfsoft.project.biz.base.maintain.service.ProblemDocumentService;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.util.IDUtil;
import cc.dfsoft.uexpress.common.util.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Desc: 问题单据业务层
 * @Auther: zhangnx
 * @Date: 2019/1/21 14:40
 * @Version:1.0
 */
@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class ProblemDocumentServiceImpl implements ProblemDocumentService {


    @Resource
    ProblemDocumentDao problemDocumentDao;

    /**
     * @MethodDesc: 功能描述
     * @Author zhangnx
     * @Date 2019/1/21 16:50
     */
    @Override
    public Map<String, Object> queryProblemDocumentList(ProblemDocumentReq probDoctReq) {
        try {
          return   problemDocumentDao.queryProblemDocumentList(probDoctReq);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }



    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED,rollbackFor = {Exception.class})
    public boolean saveOrUpdateProblemDocument(ProblemDocument problemDoc) {
        if (problemDoc==null)return false;

        boolean isBlank = StringUtil.isBlank(problemDoc.getPdId());

        if (isBlank){//新增
            problemDoc.setPdId(IDUtil.getUniqueId(Constants.MODULE_CODE_DESIGN));
            problemDoc.setRegistrationTime(new Date());
        }

        try {
            problemDocumentDao.saveOrUpdate(problemDoc);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }



    }

    @Override
    public ProblemDocument viewProblemDocumentDetail(String pdId) {
        return problemDocumentDao.get(pdId);
    }

    @Override
    public List<ProblemDocument> exportExcelList(ProblemDocumentReq probDoctReq) {
        try {
            return problemDocumentDao.exportExcelList(probDoctReq);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public Map<String, Object> findProblemTypeConut(ProblemDocumentReq probDoctReq) {
        try {
            return problemDocumentDao.findProblemTypeConut(probDoctReq);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    @Transactional(readOnly = false,propagation=Propagation.REQUIRED)
    public boolean deleteProblemDocumentById(String id) {
        try {
           problemDocumentDao.delete(id);
           //problemDocumentDao.deleteProblemDocumentById(id);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

}
