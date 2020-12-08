package cc.dfsoft.project.biz.base.project.dao;

import cc.dfsoft.project.biz.base.project.dto.SignNoticeReq;
import cc.dfsoft.project.biz.base.project.entity.SignNoticeStandard;
import cc.dfsoft.uexpress.common.dao.CommonDao;

import java.util.List;
import java.util.Map;
/**
 * 签字通知标准
 * @author fuliwei
 *
 */
public interface SignNoticeStandardDao extends CommonDao<SignNoticeStandard, String>{
	
	/**
	 * 签字标准列表查询
	 * @author fuliwei
	 * @createTime 2018年1月18日
	 * @param 
	 * @return
	 */
	public Map<String,Object> querySignNoticeStandard(SignNoticeReq req);
	
	
	/**
	 * 按职务查询
	 * @author fuliwei
	 * @createTime 2018年1月18日
	 * @param 
	 * @return
	 */
	public List<SignNoticeStandard> queryByPost(String post);
	
	/**
	 * 按职务和单据类型查询
	 * @author fuliwei
	 * @createTime 2018年1月20日
	 * @param 
	 * @return
	 */
	public List<SignNoticeStandard> queryByPostAndDateType(String post,String dateType,String corpId,String projType,String contributionMode);
	
	/**
	 * 按签字顺序和单据类型查询
	 * @author fuliwei
	 * @createTime 2018年1月20日
	 * @param 
	 * @return
	 */
	public List<SignNoticeStandard> queryBySortNoAndDateType(String sortNo,String dateType,String corpId,String projectType,String contributionMode);  //此方法需要修改

	List<SignNoticeStandard> findSignNoticeStandardList(String menuId,String projType,String corpId,String contributionMode);

}
