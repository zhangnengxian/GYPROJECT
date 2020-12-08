package cc.dfsoft.project.biz.base.accept.service;

import java.text.ParseException;
import java.util.Map;

import cc.dfsoft.project.biz.base.accept.dto.ProjInfoModifyReq;
import cc.dfsoft.project.biz.base.accept.entity.ProjInfoModify;

public interface ProjInfoModifyService {
	
	/**
	 * 更正保存
	 * @author fulw
	 * @createTime 2017-01-3
	 * @param request
	 * @param cust
	 * @return
	 */
	public void saveModify(ProjInfoModify info);
	
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
