package cc.dfsoft.project.biz.base.baseinfo.dao;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import cc.dfsoft.project.biz.base.baseinfo.dto.ConnectContentReq;
import cc.dfsoft.project.biz.base.constructmanage.dto.ConnectRecordQueryReq;
import cc.dfsoft.project.biz.base.constructmanage.entity.ConnectContent;
import cc.dfsoft.uexpress.common.dao.CommonDao;

public interface ConnectContentDao extends CommonDao<ConnectContent,String> {

	/**
	 * 碰口内容列表查询
	 * @param connectContentReq
	 * @return
	 * @throws ParseException
	 */
	public Map<String, Object> queryConnectContent(ConnectContentReq connectContentReq) throws ParseException;
	
	/**
	 * 碰口记录查询
	 * @param connectContentReq
	 * @return
	 * @throws ParseException
	 */
	public Map<String, Object> queryUnitContent(ConnectRecordQueryReq connectRecordReq) throws ParseException;
	
	
	/**
	 * 碰口内容记录条数查询，用于生成碰口内容主键ID
	 * @return
	 */
	public String queryNum(String type);

	/**
	 * 碰口内容
	 * @return
	 */
	public List<ConnectContent> queryConnectContent(String type);

}
