package cc.dfsoft.project.biz.base.accept.service;

import cc.dfsoft.project.biz.base.accept.entity.RaiseMoney;
import cc.dfsoft.project.biz.base.project.dto.ProjectQueryReq;
import cc.dfsoft.project.biz.base.project.entity.Project;

import java.text.ParseException;
import java.util.Map;

/**
 * @ClassDesc: 提资审核接口
 * @author: zhangnx
 * @date: 10:39 2018/9/14
 * @version: V1.0
 */
public interface RaiseMoneyAuditService {

    //获取待审核工程
    Map<String,Object> getAuditProject(ProjectQueryReq req) throws ParseException;

    //查询工程详细
    Project getProjectById(String projId);
}
