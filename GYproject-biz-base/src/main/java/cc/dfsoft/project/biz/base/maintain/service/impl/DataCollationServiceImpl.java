package cc.dfsoft.project.biz.base.maintain.service.impl;

import cc.dfsoft.project.biz.base.maintain.dao.AbandonedRecordDao;
import cc.dfsoft.project.biz.base.maintain.dao.DataCollationDao;
import cc.dfsoft.project.biz.base.maintain.dto.AbandonedRecordReq;
import cc.dfsoft.project.biz.base.maintain.entity.AbandonedRecord;
import cc.dfsoft.project.biz.base.maintain.entity.MenuDocument;
import cc.dfsoft.project.biz.base.maintain.service.AbandonedRecordService;
import cc.dfsoft.project.biz.base.maintain.service.DataCollationService;
import cc.dfsoft.project.biz.base.project.dao.SignNoticeDao;
import cc.dfsoft.project.biz.base.project.entity.Signature;
import cc.dfsoft.project.biz.base.project.enums.StepEnum;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.util.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
 * @Desc: 类描述
 * @Auther: zhangnx
 * @Date: 2019/3/25 22:14
 * @Version:1.0
 */

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class DataCollationServiceImpl implements DataCollationService {
    @Resource
    DataCollationDao dataCollationDao;


    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void saveUpdateThisTableData(MenuDocument menuDocument) {
        MenuDocument md = dataCollationDao.get(menuDocument.getMenuId());
        if (md!=null){
            md.setDocument(menuDocument.getDocument());
            dataCollationDao.saveOrUpdate(md);
        }else {
            dataCollationDao.save(menuDocument);
        }
    }

    @Override
    public MenuDocument queryThisTableData(MenuDocument menuDocument) {
        return dataCollationDao.get(menuDocument.getMenuId());
    }
}




















