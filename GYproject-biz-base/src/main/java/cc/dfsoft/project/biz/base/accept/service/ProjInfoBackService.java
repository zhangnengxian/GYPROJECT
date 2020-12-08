package cc.dfsoft.project.biz.base.accept.service;

import java.text.ParseException;
import java.util.Map;

import cc.dfsoft.project.biz.base.accept.dto.ProjInfoModifyReq;

/**
 * 工程回退信息
 * @author fuliwei
 *
 */
public interface ProjInfoBackService {
	
	/**
	 * 列表查询
	 * @author fuliwei
	 * @createTime 2017年11月16日
	 * @param 
	 * @return
	 */
	Map<String, Object> queryProModify(ProjInfoModifyReq req)throws ParseException;
}
