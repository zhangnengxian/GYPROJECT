package cc.dfsoft.project.biz.base.accept.dao;

import java.text.ParseException;
import java.util.Map;

import cc.dfsoft.project.biz.base.accept.dto.ProjInfoModifyReq;
import cc.dfsoft.project.biz.base.accept.entity.ProjInfoModify;
import cc.dfsoft.uexpress.common.dao.CommonDao;

public interface ProjInfoModifyDao extends CommonDao<ProjInfoModify,String>{
	
	/**
	 * 查询详述
	 * @author fuliwei
	 * @createTime 2017-01-3
	 * @param id 工程id
	 * @return ProjInfoModify
	 */
	public ProjInfoModify queryById(String projId);
	
	/**
	 * 列表查询
	 * @author fuliwei
	 * @createTime 2017-01-3
	 * @param ProjInfoModifyReq
	 * @return
	 */
	Map<String, Object> queryProModify(ProjInfoModifyReq req)throws ParseException;
}
