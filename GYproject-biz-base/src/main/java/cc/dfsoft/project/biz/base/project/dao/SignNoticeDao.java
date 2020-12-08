package cc.dfsoft.project.biz.base.project.dao;

import java.util.List;
import java.util.Map;

import cc.dfsoft.project.biz.base.project.entity.SignNotice;
import cc.dfsoft.uexpress.common.dao.CommonDao;


/**
 * 
 * @author sunyul
 * @CreateTime 2017-12-27
 *
 */
public interface SignNoticeDao extends CommonDao<SignNotice, String> {
	
	/**
	 * 查询是否已生成
	 * @author fuliwei
	 * @createTime 2018年1月18日
	 * @param 
	 * @return
	 */
	public SignNotice queryByProjIdAndPost(String projId,String post,String dataType);
	
	/**
	 * 查询是否已生成-业务单id
	 * @author fuliwei
	 * @createTime 2018年1月20日
	 * @param 
	 * @return
	 */
	public SignNotice queryByBusiIdAndPost(String busiId,String post,String dataType);
	
	
	
	/**
	 * 查询该业务单的签字
	 * @author fuliwei
	 * @createTime 2018年1月21日
	 * @param 
	 * @return
	 */
	public List<SignNotice> queryByBusiIdAndDataType(String busiId,String dataType);
	
	/**
	 * 查询签字通知
	 * @author fuliwei
	 * @createTime 2018年1月21日
	 * @param 
	 * @return
	 */
	public List<SignNotice> querySignNotice();
	
	/**
	 * 按签字顺序查-用与保存的回调函数
	 * @author fuliwei
	 * @createTime 2018年1月21日
	 * @param 
	 * @return
	 */
	public List<SignNotice> queryByBusiIdAndSort(String busiId,String sortNo);
	
	/**
	 * 按审核等级查询
	 * @param busiId
	 * @param grade
	 * @return
	 */
	public List<SignNotice> queryByBusiIdAndGrade(String busiId,String grade);

	/**
	 * 删除结算汇签单签字通知信息
	 * @author liaoyq
	 * @createTime 2018年5月18日
	 * @param projId
	 * @param dataType
	 */
	public void deleteByProjIdAndType(String projId, String dataType);
	/**
	 * 删除报验列表签字通知
	 * @author wanghj
	 * @param business_order_id
	 * 
	 */
	public void deleteByBsId (String BsId,String pcDesId);
	
	/**
	 * 通过业务单id将所有的报验审核通知置为无效
	 * @param businessOrderId
	 */
	public void updateInspectionAudit(String businessOrderId);
	
	/**
	 * 通过业务单id将当前的报验审核通知置为无效
	 * @param businessOrderId
	 * @param grade
	 */
	public void updateInspectionThisAudit(String businessOrderId,String grade);

    List<SignNotice> queryListByMultiplePost(String projId, String post, String dataType);

	List<SignNotice> querySignNoticeListBybusinessId(String busId);
	
	/**
	 * 通过业务单ID将签字通知置为已办
	 * @param busId
	 * @throws Exception
	 */
	public void deleteSignNoticeByBsId(String busId) throws  Exception;
		
	

	/**
	 * 
	 * 注释：根据业务单ID和单据类型获取第一顺序签字通知
	 * @author liaoyq
	 * @createTime 2019年5月27日
	 * @param busOrderId
	 * @param value
	 * @return
	 *
	 */
	public List<SignNotice> queryFirstByBusId(String busOrderId, String dataType);

    List<SignNotice> querysignNoticeList(String businessId, String projId, String dataType, String post, String status);

    void updateSignNotice(String sjsId, String projId, String dataType);

    void updateFinishSignaturNotice(String businessOrderId, String dataType, List<String> finishPost);


	/**
	 * 查询计划相关人员消息
	 * @author fuliwei
	 * @date 2019/8/28
	*/
	public List<Map<String, Object>> queryPlanNotice();


	/**
	 * 查询班组通知
	 * @author fuliwei
	 * @date 2019/8/28
	*/
	public List<Map<String, Object>> queryWelderNotice();



	/**
	 * 查询设计员签字通知
	 * @author fuliwei
	 * @date 2019/8/29
	*/
	public List<Map<String, Object>> queryDesignerNotice();

	/**
	 * 查询联合验收相关人员签字通知
	 * @author fuliwei
	 * @date 2019/8/29
	 */
	public List<Map<String, Object>> queryJointNotice(String post);

	/**
	 * 查询总工程师签字-燃气公司总工
	 * @author fuliwei
	 * @date 2019/9/1
	*/
	public List<Map<String, Object>> queryCeneralEngineerNotice();
	/**
	 * 施工单位总工
	 * @author fuliwei
	 * @date 2019/9/2
	*/
	public List<Map<String, Object>> queryCuCeneralEngineerNotice();

	/**
	 * 查询集团组长签字
	 * @author fuliwei
	 * @date 2019/9/1
	*/
	public List<Map<String, Object>> queryGroupLeaderNotice();

	/**
	 * 查询分公司部长
	 * @author fuliwei
	 * @date 2019/9/1
	 */
	public List<Map<String, Object>> queryMinisterNotice();

	/**
	 * 
	 * 注释：根据条件修改签字通知状态：签字通知状态、业务单ID必须传递
	 * @author liaoyq
	 * @createTime 2019年9月18日
	 * @param status 
	 * @param businessOrderId
	 * @param post
	 * @param dataType
	 * @param projId 
	 *
	 */
	public void updateStatusByBusId( String status, String businessOrderId,
			String post, String dataType, String projId);

	/**
	 * 
	 * 注释：根据条件将签字通知置为已签字，工程ID必须传递
	 * @author liaoyq
	 * @createTime 2019年9月18日
	 * @param businessId
	 * @param projId
	 * @param dataType
	 * @param post
	 * @param status
	 *
	 */
	public void updateAlreadySignByprojId(String businessId, String projId,
			String dataType, String post, String status);
}
