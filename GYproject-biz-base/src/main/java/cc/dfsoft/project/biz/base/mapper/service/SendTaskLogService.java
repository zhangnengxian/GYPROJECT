package cc.dfsoft.project.biz.base.mapper.service;

import cc.dfsoft.project.biz.base.mapper.entity.SendTaskLog;

import java.util.Date;

public interface SendTaskLogService {

    public void saveLog(SendTaskLog sendTaskLog);

    public Date getDatabaseTime();
}
