package cc.dfsoft.project.biz.base.project.service;

import java.util.List;

import cc.dfsoft.project.biz.base.project.entity.SignNoticeStandard;

/**
 * 签字通知标准
 * @author fuliwei
 *
 */
public interface SignNoticeStandardService {

	/**
	 * 通过菜单ID查询签字标准配置的dateType
	 * @author liaoyq
	 * @createTime 2018年5月18日
	 * @param key
	 * @return
	 * @throws Exception 
	 */
	String getSNsMenuIdCashMapByKey(String key) throws Exception;
	
	/**
	 * 查询签字标准通过以下参数
	 * @param postType
	 * @param dataTyep
	 * @param corpId
	 * @param projectType
	 * @param contributionMode
	 * @return
	 * @throws Exception
	 */
	public List<SignNoticeStandard>  querySignNoticeStandard(String postType,String dataType,String corpId,String projectType,String contributionMode) throws Exception;

    List<SignNoticeStandard> findSignNoticeStandardList(String menuId, String projId);
}
