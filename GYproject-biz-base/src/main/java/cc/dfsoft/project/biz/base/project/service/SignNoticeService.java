package cc.dfsoft.project.biz.base.project.service;

import java.util.List;
import java.util.Map;

import com.fr.third.org.apache.poi.hssf.record.formula.functions.Sign;

import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.project.entity.SignNotice;
import cc.dfsoft.project.biz.base.project.entity.Signature;
import cc.dfsoft.project.biz.base.project.enums.SignDataTypeEnum;
import cc.dfsoft.uexpress.common.dto.PageSortReq;

/**
 * 
 * @author sunyul
 * @createTime 2017-12-27
 *
 */
public interface SignNoticeService {
	
	/**
	 * 下计划时批量生成签字
	 * @author fuliwei
	 * @createTime 2018年1月18日
	 * @param 
	 * @return
	 */
	public void insertSignNotice(String projId,String post,String name,String genarateType);
	
	/**
	 * 查询是否已生成
	 * @author fuliwei
	 * @createTime 2018年1月18日
	 * @param 
	 * @return
	 */
	public SignNotice queryByProjIdAndPost(String projId,String post,String dataType);
	
	
	/**
	 * 调用方法 生成签字通知
	 * @author fuliwei
	 * @createTime 2018年1月20日
	 * @param 
	 * @return
	 */
	public void saveSignNotice(String post,String dateType,String businessOrderId,String projId);
	
	/**
	 * 本次的置为无效
	 * @author fuliwei
	 * @createTime 2018年1月21日
	 * @param 
	 * @return
	 */
	public void saveThisSignNotice(String post,String dateType,String businessOrderId,String projId, String signStat);
	
	/**
	 * 查找下一个签字
	 * @author fuliwei
	 * @createTime 2018年1月21日
	 * @param 
	 * @return
	 */
	public void saveNextSignNotice(String dateType,String businessOrderId,String projId,String sortNo , List<Signature> signs);
	
	
	/**
	 * 将所有的签字通知置为已签字
	 * @author fuliwei
	 * @createTime 2018年1月21日
	 * @param 
	 * @return
	 */
	public void updateAllSignState(String dataType,String businessOrderId);
	
	/**
	 * 通知置为无效
	 * @author fuliwei
	 * @createTime 2018年1月21日
	 * @param 
	 * @return
	 */
	public void updateSignNotice(String post,String dateType,String businessOrderId,String projId);
	
	/**
	 * 查询签字通知
	 * @author fuliwei
	 * @createTime 2018年1月15日
	 * @param 
	 * @return
	 */
	public Map<String, Object> querySignNotice(PageSortReq pageSortReq);
	
	/**
	 * 新方法
	 * @author fuliwei
	 * @createTime 2018年1月21日
	 * @param 
	 * @return
	 */
	public void saveSignNoticeNew(String dataType,String businessOrderId,String projId,String post,  List<Signature> signs);

	/**
	 * 删除结算汇签单签字通知信息
	 * @author liaoyq
	 * @createTime 2018年5月18日
	 * @param projId
	 * @param value
	 */
	public void deleteByProjIdAndType(String projId, String value);
	/**
	 * 删除报验列表签字通知
	 * @author wanghj
	 * @param business_order_id
	 * 
	 */
	public void deleteByBsId (String BsId,String pcDesId);

	/**
	 * 根据当前业务单id和当前签字顺序，把当前的通知置为无效，且生成下一个通知
	 * @param businessOrderId
	 * @param soryNo
	 */
	public void createNextInspectionAuditNotice(String businessOrderId,String grade,String projId);

	/**
	 * 
	 * 注释：激活或者生成该单据的第一签字顺序签字通知
	 * @author liaoyq
	 * @createTime 2019年5月27日
	 * @param busOrderId
	 * @param jointAcceptance 
	 * @param project
	 *
	 */
	public void createFirstNotice(String busOrderId, SignDataTypeEnum jointAcceptance, Project project);

    boolean signNoticeSetInvalid(String businessId, String projId, String dataType, String post, String status);

    void updateFinishSignaturNotice(String businessOrderId, List<Signature> signs);
	/**
	 * 查询联合验收相关人员签字通知
	 * @author fuliwei
	 * @date 2019/8/29
	 * @param
	 * @return
	 */
	public List<Map<String, Object>> queryJointNotice(String post);

	/**
	 * 查询总工程师签字
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
	 * 查询分公司部长
	 * @author fuliwei
	 * @date 2019/9/1
	 */
	public List<Map<String, Object>> queryMinisterNotice();
}
