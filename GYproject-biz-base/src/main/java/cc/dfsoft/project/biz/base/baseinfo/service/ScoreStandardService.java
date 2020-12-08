package cc.dfsoft.project.biz.base.baseinfo.service;

import java.text.ParseException;
import java.util.Map;

import cc.dfsoft.project.biz.base.baseinfo.dto.GradeQueryDReq;
import cc.dfsoft.project.biz.base.baseinfo.dto.ScoreStandardQueryReq;
import cc.dfsoft.project.biz.base.baseinfo.entity.ScoreStandard;

/**
 * @author cui
 * @createTime 2016-07-22
 * 评分标准服务接口
 */
public interface ScoreStandardService {

	/**
	 * 条件查询评分标准
	 * @param scoreStandardQueryReq
	 * @return
	 */
	Map<String, Object> queryScoreStandard(ScoreStandardQueryReq scoreStandardQueryReq);

	/**
	 * 修改保存
	 * @param scoreStandard
	 * @return
	 */
	String saveOrUpdateScoreStandard(ScoreStandard scoreStandard);

	/**
	 * 删除评分标准
	 * @param scoreStandard
	 * @return
	 */
	void deleteScoreStandard(String id);
	
	/**
	 * 弹出屏列表查询
	 * @author
	 * @createTime 
	 * @param
	 * @return
	 */
	Map<String, Object> queryGrade(ScoreStandardQueryReq scoreStandardQueryReq);
	
	/**
	 * 批量添加grade
	 * @author 
	 * @createTime 2016-12-07
	 * @param 
	 */
	public String batInsertGrade(GradeQueryDReq qdto) ;
	
}




