package cc.dfsoft.project.biz.base.subpackage.dao;

import java.util.List;
import java.util.Map;

import cc.dfsoft.project.biz.base.project.dto.ProjectQueryReq;
import cc.dfsoft.project.biz.base.subpackage.dto.SubConIntelligentReq;
import cc.dfsoft.project.biz.base.subpackage.entity.SubContractIntelligent;
import cc.dfsoft.uexpress.common.dao.CommonDao;

/**
 * 
 * 描述:智能表分合同Dao接口层
 * @author liaoyq
 * @createTime 2017年10月11日
 */
public interface SubContractIntelligentDao extends CommonDao<SubContractIntelligent, String> {

	/**
	 * 根据工程ID查询合同信息
	 * @param id
	 * @return
	 */
	SubContractIntelligent findContractByprojId(String id);

	/**
	 * 根据合同编号查询合同信息
	 * @param itScNo
	 * @return
	 */
	List<SubContractIntelligent> findByScNo(String itScNo);

	/**
	 * 分页查询待签智能表分合同的工程
	 * @param SubConIntelligentReq
	 * @return
	 */
	Map<String, Object> queryContractBySql(SubConIntelligentReq req);

}
