package cc.dfsoft.project.biz.base.accept.dao.impl;

import org.springframework.stereotype.Repository;

import cc.dfsoft.project.biz.base.accept.dao.ProjInfoBackDao;
import cc.dfsoft.project.biz.base.accept.entity.ProjInfoBack;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;
/**
 * 工程回退信息
 * @author fuliwei
 *
 */
@Repository
public class ProjInfoBackDaoImpl extends NewBaseDAO<ProjInfoBack,String> implements ProjInfoBackDao{

}
