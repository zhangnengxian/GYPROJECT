package cc.dfsoft.project.biz.base.maintain.service;

import cc.dfsoft.project.biz.base.maintain.dto.AbandonedRecordReq;
import cc.dfsoft.project.biz.base.maintain.entity.AbandonedRecord;

import java.util.List;
import java.util.Map;

/**
* @Description: 类描述
* @author zhangnx
* @date 2019/8/20 14:33
*@Version:1.0
*/
public interface AbandonedRecordService {

    AbandonedRecord queryAbandonedRecord(AbandonedRecordReq recordReq);

    List<AbandonedRecord> queryAbandonedRecordListByBussId(AbandonedRecordReq recordReq);

    void saveAbandonedRecord(AbandonedRecord abandonedRecord);
    void saveAbandonedRecord(String businessId,String projId,String stepId,String abandonedReason);


    void saveAbandonedRecord(String businessId, String projId, String stepId, String abandonedReason,String originalData);

    void saveAbandonedRecord(String businessId,String projId,String stepId,String abandonedReason,Map<String, Object> backupsMapListData);

     Map<String, Object> getMapBackupData(String tableName,Map<String,Object> criteriaMap);

    boolean deleteData(String tableName,Map<String,Object> criteriaMap);

    boolean delBackupsThisTableRecordAndSignature(String tableName,Map<String,Object> criteriaMap,String businessId,String rollBackReason,String stepId);

    String getThisTableOrigData(String tableName,Map<String,Object> criteriaMap);

    List<Map<String, Object>> queryAbandonedStepIdList(AbandonedRecordReq recordReq);

    boolean delBackupsThisTableRecord(String tableName, Map<String,Object> criteriaMap, String businessId, String rollBackReason, String stepId);

    List<Map<String, Object>> queryThisDBAllTables(String tableComment);

    List<Map<String, Object>> queryColumnsByTableName(String tableName,String comment);

    List<Map<String, Object>> queryThisTableMapListData(String tableName, Map<String, Object> criteriaMap);

    Map<String, Object> queryThisTableDataById(String tableName,Map<String, Object> criteriaMap);

    Map<String, Object> updateThisTableDataById(String tableName, Map<String, Object> criteriaMap, Map<String, Object> whereMap);

    int querythisTableTotalRecord(String tableName, Map<String, Object> criteriaMap);

    boolean delBackupsThisTableSignatureAndNotice(String businessId, String projId, String stepId, String fallbackReason);
}
