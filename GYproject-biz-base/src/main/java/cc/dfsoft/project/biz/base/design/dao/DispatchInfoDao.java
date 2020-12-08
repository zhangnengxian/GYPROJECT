package cc.dfsoft.project.biz.base.design.dao;

import cc.dfsoft.project.biz.base.design.entity.DispatchInfo;
import cc.dfsoft.uexpress.common.dao.CommonDao;

public interface DispatchInfoDao extends CommonDao<DispatchInfo, String>{

    /**
     * @auther cui
     * 根据工程Id查派工信息
     * @param projId
     * @return
     */
    DispatchInfo findByProjId(String projId);
}
