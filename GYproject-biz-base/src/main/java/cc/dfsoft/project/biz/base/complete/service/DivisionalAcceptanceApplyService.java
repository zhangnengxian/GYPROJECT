package cc.dfsoft.project.biz.base.complete.service;

import cc.dfsoft.project.biz.base.complete.dto.DivisionalAcceptanceReq;
import cc.dfsoft.project.biz.base.complete.entity.DivisionalAcceptanceApply;
import cc.dfsoft.project.biz.base.project.dto.ProjectQueryReq;

import java.text.ParseException;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestParam;

/**
 * 分部验收申请服务接口
 * @author fuliwei
 *
 */
public interface DivisionalAcceptanceApplyService {
	
	/**
	 * 查询左侧工程列表
	 * @author fuliwei
	 * @createTime 2017年8月9日
	 * @param 
	 * @return
	 */
	public Map<String,Object> queryProject(ProjectQueryReq req)throws ParseException;
	
	/**
	 * 查询详述
	 * @author fuliwei
	 * @createTime 2017年8月9日
	 * @param 
	 * @return
	 */
	public DivisionalAcceptanceApply findById(String projId,String daaId) throws ParseException;
	
	/**
	 * 验收申请保存
	 * @author fuliwei
	 * @createTime 2017年8月9日
	 * @param 
	 * @return
	 */
	public void saveDivisionalAcceptanceApply(DivisionalAcceptanceApply divisionalAcceptanceApply);
	
	/**
	 * 推送到待审核
	 * @author fuliwei
	 * @createTime 2017年8月9日
	 * @param 
	 * @return
	 */
	public void pushDivisionalAcceptanceApply(String daId);
	
	
	/**
	 * 查询分部验收申请列表
	 * @author fuliwei
	 * @createTime 2017年8月9日
	 * @param 
	 * @return
	 */
	public Map<String,Object> queryDivisionalAcceptanceApply(DivisionalAcceptanceReq req)throws ParseException;
	
	/**
	 * 查询分部验收审核列表
	 * @author fuliwei
	 * @createTime 2017年8月7日
	 * @param 
	 * @return
	 */
	public Map<String,Object> queryDivisionalAcceptanceAudit(DivisionalAcceptanceReq req)throws ParseException ;

	void updateDivisionalAcceptanceApply(DivisionalAcceptanceApply divisApply);
	
	/**
	 * 统计分部验收申请记录数
	 *creater wang.hui.jun
	 *@version 2019年11月7日
	 *@param projId
	 *@return null (查询无记录时返回null)
	 *@throws Exception
	 */
	Integer countDivisonalAcceptanceRecord(String projId) throws  Exception;
}
