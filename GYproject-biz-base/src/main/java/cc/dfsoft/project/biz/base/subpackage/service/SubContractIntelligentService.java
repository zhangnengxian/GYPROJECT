package cc.dfsoft.project.biz.base.subpackage.service;

import java.util.Map;

import cc.dfsoft.project.biz.base.project.dto.ProjectQueryReq;
import cc.dfsoft.project.biz.base.subpackage.dto.SubConIntelligentReq;
import cc.dfsoft.project.biz.base.subpackage.entity.SubContract;
import cc.dfsoft.project.biz.base.subpackage.entity.SubContractIntelligent;

/**
 * 
 * 描述:智能表分包合同
 * @author liaoyq
 * @createTime 2017年10月11日
 */
public interface SubContractIntelligentService {

	SubContractIntelligent viewSubConIntelligentByProjId(String id);

	String saveSubContractIntelligent(SubContractIntelligent scIntelligent) throws Exception;

	/**
	 * 查询待签智能表分合同的工程列表
	 * @param sciReq
	 * @return
	 */
	Map<String, Object> queryProjects(SubConIntelligentReq sciReq);

}
