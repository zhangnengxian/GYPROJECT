package cc.dfsoft.project.biz.base.constructmanage.service;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import cc.dfsoft.project.biz.base.constructmanage.dto.ConnectRecordQueryReq;
import cc.dfsoft.project.biz.base.constructmanage.entity.ConnectContent;

/**
 * 碰口记录service
 * @author cui
 * @createTime 2016-08-03
 *
 */
public interface TouchRecordService {

	/**
	 * 碰口记录要求内容查询方法
	 * @author cui
	 * @createTime 2016-08-04
	 * @param connectContentQueryReq 查询条件dto
	 * @return Map<String,Object>
	 * @throws ParseException 
	 */
	Map<String, Object> queryConnectContent(ConnectRecordQueryReq connectRecordReq) throws ParseException;
	
	/**
	 * 碰口内容查询方法
	 * @author cui
	 * @createTime 2016-08-04
	 * @param ConnectRecordQueryReq 查询条件dto
	 * @return Map<String,Object>
	 * @throws ParseException 
	 */
	Map<String, Object> queryConnectRecord(ConnectRecordQueryReq connectRecordReq) throws ParseException;

	List<ConnectContent> queryConnectContent(String type) throws ParseException;

}
