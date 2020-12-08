package cc.dfsoft.project.biz.base.accept.service;

import cc.dfsoft.project.biz.base.accept.entity.RaiseMoney;
import cc.dfsoft.project.biz.base.project.entity.Project;
/**
 * @ClassDesc: 提资申请接口
 * @author: zhangnx
 * @date: 10:30 2018/9/14
 * @version: V1.0
 */

public interface RaiseMoneyAplicationService {

    //提资申请保存
    void saveRaiseMoney(RaiseMoney raiseMoney);

    //更新工程
    void updateProject(RaiseMoney raiseMoney);

    
    /**
     * 保存用户回复
     * @author fuliwei  
     * @date 2018年9月13日  
     * @version 1.0
     */
    void saveCustResponse(RaiseMoney raiseMoney);

    /**
     * 通过工程id查询提资表
     * @author fuliwei  
     * @date 2018年9月14日  
     * @version 1.0
     */
    RaiseMoney queryRaiseMoneyByProjId(String id);

}
