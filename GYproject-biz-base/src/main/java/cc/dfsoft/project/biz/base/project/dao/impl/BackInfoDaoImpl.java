package cc.dfsoft.project.biz.base.project.dao.impl;

import org.springframework.stereotype.Repository;

import cc.dfsoft.project.biz.base.project.dao.BackInfoDao;
import cc.dfsoft.project.biz.base.project.entity.BackInfo;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;
/**
 * 回退信息备份
 * @author fuliwei
 *
 */
@Repository
public class BackInfoDaoImpl extends NewBaseDAO<BackInfo, String> implements BackInfoDao {

}
