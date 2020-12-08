package cc.dfsoft.project.biz.base.maintain.dao.impl;

import cc.dfsoft.project.biz.base.maintain.dao.AbandonedRecordDao;
import cc.dfsoft.project.biz.base.maintain.dto.AbandonedRecordReq;
import cc.dfsoft.project.biz.base.maintain.entity.AbandonedRecord;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;
import cc.dfsoft.uexpress.common.util.StringUtil;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Desc: 类描述
 * @Auther: zhangnx
 * @Date: 2019/3/25 22:16
 * @Version:1.0
 */
@Repository
public class AbandonedRecordDaoImpl extends NewBaseDAO<AbandonedRecord, String> implements AbandonedRecordDao {



    @Override
    public List<AbandonedRecord> queryAbandonedRecordList(AbandonedRecordReq recordReq) {
        Criteria criteria = super.getCriteria();
        if (StringUtil.isNotBlank(recordReq.getBusinessId())) {
            criteria.add(Restrictions.eq("businessId", recordReq.getBusinessId()));
        }
        if (StringUtil.isNotBlank(recordReq.getProjId())) {
            criteria.add(Restrictions.eq("projId", recordReq.getProjId()));
        }
        if (StringUtil.isNotBlank(recordReq.getStepId())) {
            criteria.add(Restrictions.like("stepId", recordReq.getStepId()+"%"));
        }
        criteria.addOrder(Order.desc("abId"));

       return this.findBySortCriteria(criteria, recordReq);
    }




    /**
    * @Description: 查询表主键
    * @author zhangnx
    * @date 2019/8/28 9:10
    */
    @Override
    public Map<String, Object> queryPrimaryKeyByTable(String tableName) {
        List<Object> paramList=new ArrayList<>();
        StringBuilder findSql=new StringBuilder();
        findSql.append("SELECT COLUMN_NAME 'primaryKey' FROM INFORMATION_SCHEMA.KEY_COLUMN_USAGE WHERE TABLE_NAME = ?  ");
        paramList.add(tableName);

        List<Map<String, Object>> mapList = this.findListBySql(findSql.toString(), paramList.toArray());

        if (mapList!=null && mapList.size()>0){
            return mapList.get(0);
        }
        return null;
    }

    /**
    * @Description: 根据条件更新表数据
    * @author zhangnx
    * @date 2019/8/28 9:10
    */
    @Override
    public Map<String, Object> updateThisTableDataById(String tableName, Map<String, Object> criteriaMap, Map<String, Object> whereMap) {

        List<Object> paramList=new ArrayList<>();
        StringBuilder updateSql=new StringBuilder();
        updateSql.append("UPDATE ").append(tableName).append(" SET ");

        if (criteriaMap ==null || whereMap==null) return null;

        for (Object key:criteriaMap.keySet()) {
            updateSql.append(key).append("= ? ").append(",");
            paramList.add(criteriaMap.get(key));
        }

        int i = updateSql.lastIndexOf(",");
        StringBuilder executeSql=new StringBuilder();
        if (i>0){
            executeSql.append(updateSql.substring(0, i));
        }
        executeSql.append(" WHERE ");

        for (Object key:whereMap.keySet()) {
            executeSql.append(key).append(" = ? ");
            paramList.add(whereMap.get(key));
        }

        Map<String ,Object> resultMap=new HashMap<>();
        try {
            this.executeSql(executeSql.toString(),paramList.toArray());
            resultMap.put("msg","更新成功！");
            return resultMap;
        }catch (Exception e){
            e.printStackTrace();
            resultMap.put("msg","更新失败！");
            return resultMap;
        }
    }

    @Override
    public int querythisTableTotalRecord(String tableName, Map<String, Object> criteriaMap) {

        if (criteriaMap!=null)criteriaMap.remove("limit");

        List<Object> paramList=new ArrayList<>();
        StringBuilder findSql=new StringBuilder();
        findSql.append("SELECT COUNT(*) 'total' FROM ").append(tableName).append(" WHERE 1=1 ");

        if (criteriaMap !=null) {
            for (Object key : criteriaMap.keySet()) {
                findSql.append(" AND ").append(key).append("= ? ");
                paramList.add(criteriaMap.get(key));
            }
        }
        return this.getCountBySql(findSql.toString(),paramList.toArray());
    }


    /**
    * @Description: 根据条件查询表数据
    * @author zhangnx
    * @date 2019/8/28 9:09
    */
    @Override
    public List<Map<String, Object>> resultMapListBycolumns(String tableName,Map<String,Object> criteriaMap) {
        Map<String,Object> limitMap=null;
        if (criteriaMap!=null) {
            limitMap= (Map<String, Object>) criteriaMap.get("limit");
            criteriaMap.remove("limit");
        }

        List<Object> paramList=new ArrayList<>();
        StringBuilder findSql=new StringBuilder();
       findSql.append("SELECT * FROM ").append(tableName).append(" WHERE 1=1 ");



       if (criteriaMap !=null) {//条件
           for (Object key : criteriaMap.keySet()) {
               findSql.append(" AND ").append(key).append("= ? ");
               paramList.add(criteriaMap.get(key));
           }
       }



        Map<String, Object> keyMap = this.queryPrimaryKeyByTable(tableName);
        if (keyMap!=null){//主键降序
            for (Object key:keyMap.keySet()) {
                findSql.append(" ORDER BY ").append(keyMap.get(key)).append(" DESC ");
                break;
            }

        }


        String fallSql="";
        if (limitMap !=null) {//分页查询
            findSql.append(" LIMIT ");
            for (Object key : limitMap.keySet()) {
                findSql.append(limitMap.get(key)).append(",");
            }
            fallSql= findSql.substring(0, findSql.lastIndexOf(","));
        }else {
            findSql.append(" LIMIT 18");
            fallSql=findSql.toString();
        }


        try {

            List<Map<String, Object>> mapList = this.findListBySql(fallSql, paramList.toArray());
            return mapList;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }



    /**
    * @Description: 根据条件删除表数据
    * @author zhangnx
    * @date 2019/8/28 9:09
    */
    @Override
    public boolean deleteData(String tableName,Map<String,Object> criteriaMap) {
        List<Object> paramList=new ArrayList<>();
        StringBuilder delSql=new StringBuilder();
        delSql.append(" DELETE FROM ").append(tableName).append(" WHERE ");

        if (criteriaMap==null ) return true;
        int i=0;
        for (Object key:criteriaMap.keySet()) {
            if (i==0){
                delSql.append(key).append("= ? ");
            }else {
                delSql.append(" AND ").append(key).append("= ? ");
            }
            paramList.add(criteriaMap.get(key));
            i++;
        }

        try {
            this.executeSql(delSql.toString(),paramList.toArray());
        }catch (Exception e){
            e.printStackTrace();
        }

        return true;
    }

    /**
    * @Description: 字段名和字段注释查询
    * @author zhangnx
    * @date 2019/8/28 9:08
    */
    @Override
    public List<Map<String, Object>> queryCommentByTable(String tableName,String comment) {
        List<Object> paramList=new ArrayList<>();
        StringBuilder findSql=new StringBuilder();
        findSql.append("SELECT COLUMN_NAME 'columnName', COLUMN_COMMENT 'columnComment',DATA_TYPE 'dataType' FROM INFORMATION_SCHEMA.COLUMNS  WHERE TABLE_NAME = ? ");
        paramList.add(tableName);
        if (StringUtils.isNotBlank(comment)){
            findSql.append("AND COLUMN_COMMENT LIKE ?");
            paramList.add("%"+comment+"%");
        }

        try {
            return this.findListBySql(findSql.toString(), paramList.toArray());
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }


    /**
    * @Description: 查表注释
    * @author zhangnx
    * @date 2019/8/28 9:07
    */
    @Override
    public Map<String, Object> queryTableComment(String tableName) {
        List<Object> paramList=new ArrayList<>();
        StringBuilder findSql=new StringBuilder();
        findSql.append("SELECT TABLE_COMMENT 'tableComment' FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = ? ");
        paramList.add(tableName);

        List<Map<String, Object>> mapList = this.findListBySql(findSql.toString(), paramList.toArray());
        if (mapList!=null && mapList.size()>0){
            return mapList.get(0);
        }
        return null;
    }

    @Override
    public  List<Map<String, Object>> queryAbandonedStepIdList(AbandonedRecordReq recordReq) {
        List<Object> paramList=new ArrayList<>();
        StringBuilder findSql=new StringBuilder();
        findSql.append("SELECT STEP_ID 'stepId',STEP_NAME 'stepName' FROM ABANDONED_RECORD  WHERE 1=1 ");

        if (StringUtils.isNotBlank(recordReq.getProjId())){
            findSql.append("AND PROJ_ID = ?");
            paramList.add(recordReq.getProjId());
        }
        if (StringUtils.isNotBlank(recordReq.getBusinessId())){
            findSql.append("AND BUSINESS_ID = ?");
            paramList.add(recordReq.getBusinessId());
        }

        if (StringUtil.isNotBlank(recordReq.getStepId())){
            findSql.append(" AND STEP_ID= ? ");
            paramList.add(recordReq.getStepId());
        }
        findSql.append("GROUP BY STEP_ID");

        List<Map<String, Object>> mapList = this.findListBySql(findSql.toString(), paramList.toArray());
        return mapList;
    }




    /**
    * @Description: 查询数据库基表
    * @author zhangnx
    * @date 2019/8/28 9:06
    */
    @Override
    public List<Map<String, Object>> queryThisDBAllTables(String tableComment) {
        List<Object> paramList=new ArrayList<>();
        StringBuilder findSql=new StringBuilder();
        findSql.append("SELECT TABLE_NAME 'tableName', TABLE_COMMENT 'tableComment' FROM INFORMATION_SCHEMA. TABLES WHERE TABLE_SCHEMA = DATABASE () AND TABLE_TYPE = 'BASE TABLE'  ");
       if (StringUtil.isNotBlank(tableComment)){
           findSql.append("AND TABLE_COMMENT like ?");
           paramList.add("%"+tableComment+"%");
       }
        List<Map<String, Object>> mapList = this.findListBySql(findSql.toString(), paramList.toArray());
        return mapList;
    }





}
