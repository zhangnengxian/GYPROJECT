package cc.dfsoft.project.biz.base.complete.service;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.batik.css.engine.value.svg.StrokeDasharrayManager;
import org.springframework.orm.hibernate4.HibernateOptimisticLockingFailureException;
import org.springframework.web.bind.annotation.RequestParam;

import cc.dfsoft.project.biz.base.complete.dto.DivisionalAcceptanceReq;
import cc.dfsoft.project.biz.base.complete.entity.DivisionalAcceptance;

/**
 * 分部验收服务接口
 * @author fuliwei
 *
 */
public interface DivisionalAcceptanceService {
	
	/**
	 * 分部验收保存
	 * @author fuliwei
	 * @createTime 2017年8月10日
	 * @param 
	 * @return
	 */
	public void saveDivisionalAcceptance(DivisionalAcceptance divisionalAcceptance) throws Exception;
	
	
	/**
	 * 分部验收列表
	 * @author fuliwei
	 * @createTime 2017年8月10日
	 * @param 
	 * @return
	 */
	public Map<String,Object> queryDivisionalAcceptance(DivisionalAcceptanceReq req)throws ParseException;
	
	/**
	 * 查询详述
	 * @author fuliwei
	 * @createTime 2017年8月10日
	 * @param 
	 * @return
	 */
	public DivisionalAcceptance viewByDaaId(String daaId)throws ParseException ;
	
	
	/**
	 * 标记打印验收申请
	 * @author fuliwei
	 * @createTime 2017年8月14日
	 * @param 
	 * @return
	 */
	public void signDivisionalAcceptance(String daId);

	/**
	 * 根据工程ID查询工程分部验收单信息
	 * @param projId
	 * @return
	 */
	public List<Object> findPrintDataByProjId(String projId,String type);
	
	/**
	 * 保存回调
	 * @author fuliwei
	 * @createTime 2018年1月21日
	 * @param 
	 * @return
	 */
	public void saveSignNotice(String cwId);
	
	/**
	 * 查询分部验收申请列表
	 * @author wanghuijun
	 * @createTime 2018年9月20日
	 * @return
	 */
	public Map<String, Object> queryDivisionalAcceptanceApply(DivisionalAcceptanceReq req) throws Exception;
	
	
	/**
	 * 分部验收申请列表打印标记
	 * @author wanghuijun
	 * @createTime 2018年9月21日
	 * @param daaId
	 * @return
	 */
	public void signDivisionalAcceptancePrint(String daaId) throws Exception;
}
