package cc.dfsoft.project.biz.base.constructmanage.dao;

import cc.dfsoft.project.biz.base.constructmanage.dto.EngineeringVisaQueryReq;
import cc.dfsoft.project.biz.base.constructmanage.entity.EngineeringVisa;
import cc.dfsoft.uexpress.common.dao.CommonDao;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

public interface EngineeringVisaDao extends CommonDao<EngineeringVisa, String>{
	
	/**
	 * 签证列表条件查询
	 * @author cui
	 * @createTime 2016-07-25
	 * @param engineeringVisaQueryReq 查询条件dto
	 * @return Map<String,Object>
	 * @throws ParseException 
	 */
	Map<String,Object> queryEngineeringVisa(EngineeringVisaQueryReq engineeringVisaQueryReq) throws ParseException;
	
//	EngineeringVisa queryByprojId(String projId);
	public void updateVisaState(String id);
	
	/**
	 * 签证记录时效性（定时任务）
	 * 未处理的签证记录,签证日期距离当前时间超过三天且无业务代表的标记作废
	 * @author pengtt
	 * @createTime 2016-08-25
	 * */
	public void updateEngineeringVisaState();

	/**
	 * 查询制定类型的签证累计金额
	 * @param evType
	 * @return
	 */
	BigDecimal getTotalCostByType(String projId, String evType);

	/**
	 * 查询自增签证编号
	 * @author cui
	 * @createTime 2017-9-9
	 * @return String
	 */
    String getMaxEvNo(String date,String projId);

    /**
     * 根据工程ID查询所有签证记录
     * @param projId
     * @param auditState 
     * @return
     */
	List<EngineeringVisa> findByProjId(String projId, String auditState);
	
	/**
	 * 查询签证通知
	 * @author fuliwei
	 * @createTime 2018年1月31日
	 * @param 
	 * @return
	 */
	public List<EngineeringVisa> queryVisaNotice(String status) ;

	/**
	 * 施工单位预算员查看未对签证审核金额确认的通知
	 * @author liaoyq
	 * @createTime 2018年6月13日
	 * @return
	 */
	List<EngineeringVisa> queryEVNotice();

	/**
	 * 
	 * 注释：查询未作废未审核完成的签证
	 * @author liaoyq
	 * @createTime 2018年12月7日
	 * @param id
	 * @return
	 *
	 */
	List<EngineeringVisa> noCancelEngineeringVisa(String id);

	/**
	 * 
	 * 注释：根据公司ID获取超期的签证记录
	 * @author liaoyq
	 * @createTime 2019年7月30日
	 * @param corpId
	 * @param limitTime
	 * @return
	 *
	 */
	List<Object[]> queryOverdueEngineeringVisa(String corpId,
			String limitTime);

	/**
	 * 
	 * 注释：自动审核后清空crontabDate
	 * @author liaoyq
	 * @createTime 2019年7月31日
	 * @param evId
	 *
	 */
	void clearCrontabDateById(String evId);

    List<EngineeringVisa> queryCrontabDateNotnull();
}
