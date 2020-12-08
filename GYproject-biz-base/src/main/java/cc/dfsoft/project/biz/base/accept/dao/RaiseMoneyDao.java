package cc.dfsoft.project.biz.base.accept.dao;

import cc.dfsoft.project.biz.base.accept.entity.ProjInfoBack;
import cc.dfsoft.project.biz.base.accept.entity.RaiseMoney;
import cc.dfsoft.project.biz.base.project.entity.AccessoryList;
import cc.dfsoft.uexpress.common.dao.CommonDao;

/**
 * 提资信息
 * @author fuliwei
 *
 */

public interface RaiseMoneyDao extends CommonDao<RaiseMoney, String>{

    void saveRaiseMoney(RaiseMoney raiseMoney);

    void updateById(RaiseMoney raiseMoney);
    
   /***
    * 通过工程id查详述
    * @author fuliwei  
    * @date 2018年9月13日  
    * @version 1.0
    */
   public  RaiseMoney viewByProjId(String projId);
    
}
