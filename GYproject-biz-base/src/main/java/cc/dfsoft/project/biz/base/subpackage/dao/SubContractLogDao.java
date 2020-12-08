package cc.dfsoft.project.biz.base.subpackage.dao;

import cc.dfsoft.project.biz.base.subpackage.entity.SubContractLog;
import cc.dfsoft.uexpress.common.dao.CommonDao;
/**
 * 
 * 描述:分合同日志dao接口层
 * @author liaoyq
 * @createTime 2018年1月23日
 */
public interface SubContractLogDao extends CommonDao<SubContractLog, String>{

	SubContractLog findByModifystate(String modifyState, String orlId);

}
