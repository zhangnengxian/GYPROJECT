package cc.dfsoft.project.biz.base.common.service.impl;

import cc.dfsoft.project.biz.base.common.dao.TSysConstantsDao;
import cc.dfsoft.project.biz.base.common.entity.TSysConstants;
import cc.dfsoft.project.biz.base.common.service.TSysConstantsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class TSysConstantsServiceImpl implements TSysConstantsService{

    @Resource
    TSysConstantsDao tSysConstantsDao;

    @Override
    public TSysConstants findTSysConstantsById(String id) {
        try {
            return tSysConstantsDao.get(id);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }






}
