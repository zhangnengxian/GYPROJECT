package cc.dfsoft.project.biz.base.mapper.service.impl;

import cc.dfsoft.project.biz.base.mapper.dao.SendTaskLogDao;
import cc.dfsoft.project.biz.base.mapper.entity.SendTaskLog;
import cc.dfsoft.project.biz.base.mapper.service.SendTaskLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class SendTaskLogServiceImpl implements SendTaskLogService {
    @Autowired
    private SendTaskLogDao sendTaskLogDao;
    @Override
    public void saveLog(SendTaskLog sendTaskLog) {
        sendTaskLogDao.save(sendTaskLog);
    }

    @Override
    public Date getDatabaseTime() {
        return sendTaskLogDao.getDatabaseDate();
    }
}
