package cc.dfsoft.project.biz.base.contract.dao.impl;

import org.springframework.stereotype.Repository;
import cc.dfsoft.project.biz.base.contract.dao.ButtonDispalyDao;
import cc.dfsoft.project.biz.base.contract.dao.ProjectCostDao;
import cc.dfsoft.project.biz.base.contract.entity.ButtonDisplay;
import cc.dfsoft.project.biz.base.contract.entity.ProjectCost;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;

@Repository
public class ButtonDispalyDaoImpl extends NewBaseDAO<ButtonDisplay, String> implements ButtonDispalyDao{

}
