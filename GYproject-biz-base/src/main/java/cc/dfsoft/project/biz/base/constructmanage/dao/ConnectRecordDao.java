package cc.dfsoft.project.biz.base.constructmanage.dao;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import cc.dfsoft.project.biz.base.constructmanage.dto.ConnectRecordQueryReq;
import cc.dfsoft.project.biz.base.constructmanage.entity.ConnectRecord;
import cc.dfsoft.uexpress.common.dao.CommonDao;

public interface ConnectRecordDao extends CommonDao<ConnectRecord,String>{

	
	/**
	 * 碰口记录查询
	 * @param connectRecordReq
	 * @return
	 * @throws ParseException
	 */
	Map<String, Object> queryUnitContent(ConnectRecordQueryReq connectRecordReq) throws ParseException;

	/**
	 * 项目id碰口记录查询
	 * @param projId
	 * @return
	 * @throws ParseException
	 */
	List<ConnectRecord> queryContent(String projId) throws ParseException;

	/**
	 * 根据碰口记录单id删除碰口记录
	 * @author cui
	 * @createTime 2016-08-31
	 * @param croId 碰口记录单id
	 */
	void deleteByCroId(String croId);;

	

}
