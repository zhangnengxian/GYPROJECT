package cc.dfsoft.project.biz.base.maintain.service.impl;

import cc.dfsoft.project.biz.base.maintain.dao.AbandonedRecordDao;
import cc.dfsoft.project.biz.base.maintain.dto.AbandonedRecordReq;
import cc.dfsoft.project.biz.base.maintain.entity.AbandonedRecord;
import cc.dfsoft.project.biz.base.maintain.service.AbandonedRecordService;
import cc.dfsoft.project.biz.base.project.dao.ManageRecordDao;
import cc.dfsoft.project.biz.base.project.dao.SignNoticeDao;
import cc.dfsoft.project.biz.base.project.entity.Signature;
import cc.dfsoft.project.biz.base.project.enums.StepEnum;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.util.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Desc: 类描述
 * @Auther: zhangnx
 * @Date: 2019/3/25 22:14
 * @Version:1.0
 */

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class AbandonedRecordServiceImpl implements AbandonedRecordService {

    @Resource
    AbandonedRecordDao abandonedRecordDao;
    @Resource
    SignNoticeDao signNoticeDao;


    /**
    * @Description: 回退历史查询（前台显示）
    * @author zhangnx
    * @date 2019/8/26 15:35
    */
    @Override
    public AbandonedRecord queryAbandonedRecord(AbandonedRecordReq recordReq) {
        List<AbandonedRecord> arList = abandonedRecordDao.queryAbandonedRecordList(recordReq);
        if (arList==null || arList.size()<1) return null;
        AbandonedRecord abRd=new AbandonedRecord();
        StringBuilder signData=new StringBuilder();
        StringBuilder signSql=new StringBuilder();
        for (AbandonedRecord ar:arList) {
            String abandonedReason=ar.getOperatingTime()+"\n"+ar.getAbandonedReason()+"\n";
            if (StringUtils.isNotBlank(abRd.getAbandonedReason())){
                abRd.setAbandonedReason(abRd.getAbandonedReason()+abandonedReason);
            }else{
                abRd.setAbandonedReason(abandonedReason);
            }
            abRd.setOperator(ar.getOperator());
            abRd.setOperatingTime(ar.getOperatingTime());
            if (ar.getStepId().contains("_SIGN")){
                signData.append(ar.getAbandonedData());
                signSql.append(ar.getInsertSql());
            }else {
                abRd.setAbandonedData(StringUtil.isNotBlank(abRd.getAbandonedData())?abRd.getAbandonedData()+ar.getAbandonedData():ar.getAbandonedData());
                abRd.setInsertSql(StringUtil.isNotBlank(abRd.getInsertSql())?abRd.getInsertSql()+ar.getInsertSql():ar.getInsertSql());
            }

        }
        abRd.setAbandonedData(StringUtil.isNotBlank(abRd.getAbandonedData())?abRd.getAbandonedData()+signData:signData.toString());
        abRd.setInsertSql(StringUtil.isNotBlank(abRd.getInsertSql())?abRd.getInsertSql()+signSql:signSql.toString());

        return abRd;
    }





    @Override
    public List<AbandonedRecord> queryAbandonedRecordListByBussId(AbandonedRecordReq recordReq) {
        return abandonedRecordDao.queryAbandonedRecordList(recordReq);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void saveAbandonedRecord(AbandonedRecord abandonedRecord) {
        LoginInfo loginInfo = SessionUtil.getLoginInfo();
        abandonedRecord.setAbId(IDUtil.getUniqueId(Constants.MODULE_CODE_DESIGN));
        abandonedRecord.setOperator(loginInfo.getStaffName());
        abandonedRecord.setOperatingTime(new Date());
        abandonedRecordDao.save(abandonedRecord);
    }


    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void saveAbandonedRecord(String businessId,String projId,String stepId,String abandonedReason,Map<String, Object> backupsMapListData) {
        this.saveAbandonedRecordInfo(businessId,projId,stepId,abandonedReason,null,backupsMapListData);

    }


    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void saveAbandonedRecord(String businessId, String projId, String stepId, String abandonedReason,String originalData) {
        this.saveAbandonedRecordInfo(businessId,projId,stepId,abandonedReason,originalData,null);
    }


    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void saveAbandonedRecord(String businessId, String projId, String stepId, String abandonedReason) {
        this.saveAbandonedRecordInfo(businessId,projId,stepId,abandonedReason,null,null);
    }



    private void saveAbandonedRecordInfo(String businessId, String projId, String stepId, String abandonedReason,String originalData,Map<String, Object> backupsMapListData){
        AbandonedRecord abrd=new AbandonedRecord();


        if (StringUtil.isNotBlank(stepId) && stepId.contains("_SIGN")){ ;
            if (stepId.lastIndexOf("_")>0) {
                abrd.setStepName(StepEnum.getNameByCode(stepId.substring(0, stepId.lastIndexOf("_"))) + " (签字)");
            }
        }else {
            abrd.setStepName(StepEnum.getNameByCode(stepId));
        }


        abrd.setAbId(IDUtil.getUniqueId(Constants.MODULE_CODE_DESIGN));
        abrd.setBusinessId(businessId);
        abrd.setProjId(projId);
        abrd.setStepId(stepId);
        abrd.setOperator(SessionUtil.getLoginInfo().getStaffName());
        abrd.setOperatingTime(new Date());
        abrd.setAbandonedReason(abandonedReason);
        abrd.setAbandonedData(originalData);

        if (backupsMapListData!=null) {
            Object abandonedData = backupsMapListData.get("abandonedData");
            if (abandonedData != null) {
                abrd.setAbandonedData(abandonedData.toString());
            }
            Object insertSql = backupsMapListData.get("insertSql");
            if (insertSql != null) {
                abrd.setInsertSql(insertSql.toString());
            }
        }

        abandonedRecordDao.save(abrd);
    }



    private Map<String, Object> getMapData(String tableName,Map<String,Object> criteriaMap) {
        List<Map<String, Object>> mapList = abandonedRecordDao.resultMapListBycolumns(tableName,criteriaMap);
        if (mapList!=null && mapList.size()>0) {
            return mapList.get(0);
        }
        return null;
    }


    /**
    * @Description: 获取table的原数据作备份
    * @author zhangnx
    * @date 2019/8/26 15:32
    */
    @Override
    public String getThisTableOrigData(String tableName,Map<String,Object> criteriaMap) {


        Map<String, Object> mapBackupData = getMapBackupData(tableName,criteriaMap);
        if (mapBackupData!=null){
            return mapBackupData.get("abandonedData").toString();
        }
        return null;
    }



    @Override
    public List<Map<String, Object>> queryAbandonedStepIdList(AbandonedRecordReq recordReq) {

        List<Map<String, Object>> mapList= abandonedRecordDao.queryAbandonedStepIdList(recordReq);

        List<Map<String, Object>> stepIds=new ArrayList<>();

        if (mapList==null && mapList.size()<1) return null;

        for (Map<String, Object> map:mapList) {
            String stepId = map.get("stepId").toString();
            if (stepId.contains("_SIGN")){
                continue;
            }
            stepIds.add(map);
        }

        return stepIds;
    }




    /**
    * @Description: 删除并备份签字信息、删除磁盘签字图片、签字通知置无效
    * @author zhangnx
    * @date 2019/8/25 13:16
    */
    @Override
    public boolean delBackupsThisTableSignatureAndNotice(String businessId, String projId, String stepId, String rollBackReason) {

        String signTable=Annotations.getClassAnVal(Signature.class,"Table","name");
        String t_businessId = Annotations.getFieldGetMethodAnVal(Signature.class, "businessOrderId", "Column", "name");

        Map<String,Object> criteriaMap=new HashMap<>();
        criteriaMap.put(t_businessId,businessId);

        List<Map<String, Object>> mapList = abandonedRecordDao.resultMapListBycolumns(signTable, criteriaMap);
        if (mapList==null || mapList.size()<1) return true;



        //删除磁盘签字图片
        String t_imgUrl = Annotations.getFieldGetMethodAnVal(Signature.class, "imgUrl", "Column", "name");
        for (Map<String, Object> map:mapList) {
            for (Object key:map.keySet()) {
                String img_url=map.get(t_imgUrl)!=null?map.get(t_imgUrl).toString():"";
                if (StringUtil.isNotBlank(img_url) && !img_url.contains(Constants.SIGN_PCTURE_PATH)){//不包含电子签名
                    FileUtil.deleteFile(Constants.DISK_PATH+Constants.SIGN_DISK_PATH+img_url);//删除磁盘签字图片
                    break;
                }
            }
        }

        //删除并备份签字信息
        Map<String, Object> backupsMapListData = this.getMapBackupData(signTable,criteriaMap);

        this.deleteData(signTable, criteriaMap);
        this.saveAbandonedRecord(businessId,projId, stepId+"_SIGN",rollBackReason,backupsMapListData);


        try {//签字通知置无效
            signNoticeDao.deleteSignNoticeByBsId(businessId);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return true;
    }




    /**
    * @Description: 获取table的备份数据，和insert语句
    * @author zhangnx
    * @date 2019/8/26 15:32
    */
    @Override
    public Map<String, Object> getMapBackupData(String tableName,Map<String,Object> criteriaMap) {

        List<Map<String, Object>> mapList = abandonedRecordDao.resultMapListBycolumns(tableName,criteriaMap);

        Map<String, Object> tableComment=abandonedRecordDao.queryTableComment(tableName);
        if (mapList==null || mapList.size()<1) return null;

        Map<String,Object> backupData=new HashMap<>();
        StringBuilder backupsSql=new StringBuilder();
        StringBuilder origData=new StringBuilder();

        for (Map<String, Object> map:mapList) {

            String cloumn="";
            String cloumnValue="";
            for (Object o:map.keySet()) {
                if (map.get(o)!=null && StringUtil.isNotBlank(map.get(o).toString())) {
                    cloumn += o + ",\t";
                    cloumnValue +="'"+map.get(o)+"',\t";
                }
            }


            if (cloumn.lastIndexOf(",")>0) {//去掉最后一个逗号
                cloumn = cloumn.substring(0, cloumn.lastIndexOf(","));
            }
            if (cloumnValue.lastIndexOf(",")>0) {//去掉最后一个逗号
                cloumnValue = cloumnValue.substring(0, cloumnValue.lastIndexOf(","));
            }


            //拼接成插入sql语句
            StringBuilder insertSql=new StringBuilder();
            insertSql.append("INSERT INTO ").append(tableName).append("(").append(cloumn).append(")");
            insertSql.append("VALUES (").append(cloumnValue).append(")");


            //客户端显示历史数据时去掉签字(签字的字符串显示得太多)
            StringBuilder removeSignData = new StringBuilder();
           if (StringUtil.isNotBlank(cloumn) && StringUtil.isNotBlank(cloumnValue)) {
               String[] clValsplits = cloumnValue.split(",\t");
               String[] clsplits = cloumn.split(",\t");


               List<Map<String, Object>> commentList = abandonedRecordDao.queryCommentByTable(tableName,null);
               for (int i = 0; i <clValsplits.length ; i++) {
                   if (i<clsplits.length) {
                       if (StringUtil.isNotBlank(clValsplits[i]) && !clValsplits[i].contains("data:image/svg+xml")) {

                           if (commentList != null && commentList.size() > 0) {
                               for (Map<String, Object> commentMap : commentList) {


                                   if (clsplits[i].equals(commentMap.get("columnName").toString())) {
                                       removeSignData.append(commentMap.get("columnComment"));
                                       break;
                                   }
                               }
                           }
                           removeSignData.append("(" + clsplits[i] + ")").append("  ： ").append(clValsplits[i]).append(";\n");
                       }
                   }
               }
           }

           if (tableComment!=null){
               origData.append("\n").append(splitMark()).append(tableComment.get("tableComment")).append(splitMark()).append("\n\n");
               backupsSql.append("\n\n #").append(splitMark()).append(tableComment.get("tableComment")).append(splitMark()).append("\n\n");
           }

            backupsSql.append(insertSql).append(";");
            origData.append(removeSignData);
        }
        backupData.put("abandonedData",origData);
        backupData.put("insertSql",backupsSql);

        return backupData;
    }




    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public boolean deleteData(String tableName, Map<String,Object> criteriaMap) {
        return abandonedRecordDao.deleteData(tableName,criteriaMap);
    }




    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public boolean delBackupsThisTableRecordAndSignature(String tableName,Map<String,Object> criteriaMap,String businessId,String rollBackReason,String stepId) {
       String projId="";
        if (criteriaMap!=null){
            for (Object key:criteriaMap.keySet()) {
                projId=criteriaMap.get(key)+"";
            }
        }

        //删除并备份签字信息、磁盘签字图片、签字通知
        this.delBackupsThisTableSignatureAndNotice(businessId,projId,stepId,rollBackReason);

        //删除并备份tableName信息
        Map<String, Object> mapData = this.getMapData(tableName,criteriaMap);
        if (mapData==null) return true;

        Map<String, Object> backupsMapData = this.getMapBackupData(tableName,criteriaMap);
        this.deleteData(tableName,criteriaMap);
        this.saveAbandonedRecord(businessId,projId,stepId ,rollBackReason,backupsMapData);

        return true;
    }



    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public boolean delBackupsThisTableRecord(String tableName,Map<String,Object> criteriaMap, String businessId, String rollBackReason, String stepId) {

        if (criteriaMap==null) return true;
        String projId="";
        for (Object key:criteriaMap.keySet()) {
            projId=criteriaMap.get(key)+"";
            break;
        }

        Map<String, Object> mapData = this.getMapData(tableName,criteriaMap);
        if (mapData==null) return true;

        Map<String, Object> backupsMapData = this.getMapBackupData(tableName,criteriaMap);

        this.deleteData(tableName,criteriaMap);
        this.saveAbandonedRecord(businessId,projId,stepId ,rollBackReason,backupsMapData);

        return true;
    }




    @Override
    public List<Map<String, Object>> queryThisDBAllTables(String tableComment) {
        List<Map<String, Object>> mapList = abandonedRecordDao.queryThisDBAllTables(tableComment);
        if (mapList==null || mapList.size()<1) return null;

        List<Map<String, Object>> resultList=new ArrayList<>();
        for (Map<String, Object> map:mapList) {
            Object tableC = map.get("tableComment");
            if (tableC!=null && StringUtils.isNotBlank(tableC.toString().trim())){
                resultList.add(map);
            }
        }
        return resultList;
    }




    @Override
    public List<Map<String, Object>> queryColumnsByTableName(String tableName,String comment) {
       return abandonedRecordDao.queryCommentByTable(tableName,comment);
    }

    @Override
    public List<Map<String, Object>> queryThisTableMapListData(String tableName, Map<String, Object> criteriaMap) {
        List<Map<String, Object>> mapList = abandonedRecordDao.resultMapListBycolumns(tableName, criteriaMap);

        Map<String, Object> primaryKeyMap=abandonedRecordDao.queryPrimaryKeyByTable(tableName);
        String primaryKey="";
        if (primaryKeyMap!=null){
            primaryKey=primaryKeyMap.get("primaryKey")+"";
        }

        if (mapList==null && mapList.size()<1) return null;
        for (Map<String, Object> map: mapList) {
            map.put("primaryKey",primaryKey);
        }
        return mapList;
    }

    @Override
    public Map<String, Object> queryThisTableDataById(String tableName,Map<String, Object> criteriaMap) {
        List<Map<String, Object>> dataList = abandonedRecordDao.resultMapListBycolumns(tableName, criteriaMap);
        if (dataList!=null && dataList.size()>0){
            return dataList.get(0);
        }

        return null;
    }



    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Map<String, Object> updateThisTableDataById(String tableName, Map<String, Object> criteriaMap, Map<String, Object> whereMap) {

        Map<String, Object> origDataMap = this.queryThisTableDataById(tableName, whereMap);//更新前的数据

        Map<String, Object> map = abandonedRecordDao.updateThisTableDataById(tableName, criteriaMap, whereMap);//更新中

        Map<String, Object> newDataMap = this.queryThisTableDataById(tableName, whereMap);//更新后的数据


        List<Map<String, Object>> columnsList = this.queryColumnsByTableName(tableName, null);//查询表字段信息
        String diffData = MapUtils.getDiffDataBetweenFrontAndBack(origDataMap, newDataMap, columnsList, null);//获取更新前后不同的数据

        AbandonedRecordReq req=new AbandonedRecordReq();
        if (StringUtils.isNotBlank(diffData)) {//保存更改日志
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String formatDate = sdf.format(new Date());
            diffData=formatDate+"修改内容为：\n"+diffData+"\n";

            for (Object key : whereMap.keySet()) {
                Object o = whereMap.get(key);
                req.setBusinessId(o+"");
                List<AbandonedRecord> abList = abandonedRecordDao.queryAbandonedRecordList(req);
                if (abList!=null && abList.size()>0){
                    for (AbandonedRecord ab:abList) {
                        ab.setAbandonedData(ab.getAbandonedData()+"\n"+diffData);
                        abandonedRecordDao.saveOrUpdate(ab);
                    }
                }else {
                    this.saveAbandonedRecord(o != null ? o.toString() : "", tableName, StepEnum.DATATABLE_INFO.getValue(), "表信息修改", diffData);
                }
                break;
            }
        }
        return map;
    }

    @Override
    public int querythisTableTotalRecord(String tableName, Map<String, Object> criteriaMap) {
        return abandonedRecordDao.querythisTableTotalRecord(tableName,criteriaMap);
    }



    public String splitMark(){
        String splitMark="**";
        for (int i = 0; i <2 ; i++) {splitMark+="**";}
        return splitMark;
    }




}




















