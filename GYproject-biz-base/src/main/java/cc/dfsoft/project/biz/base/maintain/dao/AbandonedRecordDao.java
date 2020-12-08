package cc.dfsoft.project.biz.base.maintain.dao;

import cc.dfsoft.project.biz.base.maintain.dto.AbandonedRecordReq;
import cc.dfsoft.project.biz.base.maintain.entity.AbandonedRecord;
import cc.dfsoft.uexpress.common.dao.CommonDao;

import java.util.List;
import java.util.Map;

/**
 * @Desc: 类描述
 * @Auther: zhangnx
 * @Date: 2019/3/25 22:15
 * @Version:1.0
 */
public interface AbandonedRecordDao extends CommonDao<AbandonedRecord, String> {

    List<Map<String, Object>> resultMapListBycolumns(String tableName,Map<String,Object> criteriaMap);

    boolean deleteData(String tableName, Map<String,Object> criteriaMap);

    List<Map<String, Object>> queryCommentByTable(String tableName,String comment);

    Map<String, Object> queryTableComment(String tableName);

    List<Map<String, Object>> queryAbandonedStepIdList(AbandonedRecordReq recordReq);

    List<Map<String, Object>> queryThisDBAllTables(String tableComment);

    List<AbandonedRecord> queryAbandonedRecordList(AbandonedRecordReq recordReq);

    Map<String, Object> queryPrimaryKeyByTable(String tableName);

    Map<String, Object> updateThisTableDataById(String tableName, Map<String, Object> criteriaMap, Map<String, Object> whereMap);

    int querythisTableTotalRecord(String tableName, Map<String, Object> criteriaMap);
}
