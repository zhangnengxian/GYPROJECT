package cc.dfsoft.project.biz.base.maintain.dao.impl;


import cc.dfsoft.project.biz.base.maintain.controller.CollectionUtils;
import cc.dfsoft.project.biz.base.maintain.dao.ProblemDocumentDao;
import cc.dfsoft.project.biz.base.maintain.dto.ProblemDocumentReq;
import cc.dfsoft.project.biz.base.maintain.entity.ProblemDocument;
import cc.dfsoft.project.biz.base.maintain.entity.SysCodeDesc;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;
import cc.dfsoft.uexpress.common.util.MapUtils;
import cc.dfsoft.uexpress.common.util.ResultUtil;
import cc.dfsoft.uexpress.common.util.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Desc: 问题单据数据层
 * @Auther: zhangnx
 * @Date: 2019/1/21 14:43
 * @Version:1.0
 */
@Repository
public class ProblemDocumentDaoImpl extends NewBaseDAO<ProblemDocument, String> implements ProblemDocumentDao {


    /**
     * @MethodDesc: 问题单据查询List
     * @Author zhangnx
     * @Date 2019/1/21 16:51
     */
    @Override
    public Map<String, Object> queryProblemDocumentList(ProblemDocumentReq probDoctReq) throws Exception {
        List<Object> paramList=new ArrayList<>();
        String conditionalSql = conditionalFilter(probDoctReq);

        StringBuilder filterCountSql=new StringBuilder();
        filterCountSql.append("SELECT COUNT(a.PD_ID) FROM PROBLEM_DOCUMENT a where 1=1 ");
        filterCountSql.append(conditionalSql);

        int filterCount = this.getCountBySql(filterCountSql.toString(),paramList.toArray());

        return ResultUtil.pageResult( filterCount, probDoctReq.getDraw(), getProblemDocumentList(probDoctReq));

    }


    @Override
    public List<ProblemDocument> exportExcelList(ProblemDocumentReq probDoctReq) throws Exception {
        return getProblemDocumentList(probDoctReq);
    }


    @Override
    public Map<String, Object> findProblemTypeConut(ProblemDocumentReq probDoctReq) {

        List<Object> paramList=new ArrayList<>();
        String conditionalSql = conditionalFilter(probDoctReq);

        StringBuilder filterCountSql=new StringBuilder();
        filterCountSql.append("SELECT count( DISTINCT a.CORP_ID ) FROM PROBLEM_DOCUMENT a where 1=1  ");
        filterCountSql.append(conditionalSql);
        int filterCount = this.getCountBySql(filterCountSql.toString(),paramList.toArray());


        List<SysCodeDesc> sysCodeDescList = CollectionUtils.getSysCodeDescList("problem_document", "problemType");
        int index=1;

        StringBuilder sql=new StringBuilder();
        sql.append("SELECT b.DEPT_NAME 'corpName',\n");
        for (SysCodeDesc s:sysCodeDescList) {
            sql.append("sum(case when a.PROBLEM_TYPE_CODE='"+s.getCode()+"' then 1 else 0 end) '"+s.getConfigField()+"'");
            if (index<sysCodeDescList.size())sql.append(",");
            sql.append("\n"); index++;
        }

        sql.append(" FROM PROBLEM_DOCUMENT a ,DEPARTMENT b \n WHERE a.CORP_ID=b.DEPT_ID ");
        sql.append(conditionalSql);
        sql.append(" GROUP BY a.CORP_ID ");

        if (probDoctReq.getLength()!=0) {
            sql.append(" LIMIT ").append(probDoctReq.getStart()).append(",").append(probDoctReq.getLength());
        }

        List<Map<String, Object>> listBySql = this.findListBySql(sql.toString(),paramList.toArray());

        return ResultUtil.pageResult( filterCount, probDoctReq.getDraw(), listBySql);

    }

    @Override
    public void deleteProblemDocumentById(String pdId) {
        String hql = "delete ProblemDocument where pdId=?";
        //String hql1 ="DELETE from problem_document where PD_ID=?";
        //super.executeHql(hql);
        this.executeHql(hql, new Object[]{pdId});

    }





    public List<ProblemDocument> getProblemDocumentList(ProblemDocumentReq probDoctReq){
        List<Object> paramList=new ArrayList<>();
        String conditionalSql = conditionalFilter(probDoctReq);
        StringBuilder findHql=new StringBuilder();
        findHql.append(" SELECT\n" +
                "a.PD_ID 'pdId',\n" +
                "a.CORP_ID 'corpId',\n" +
                "a.MENU_ID 'menuId',\n" +
                "a.LEVEL_2_MENU_ID 'level2MenuId',\n" +
                "a.PROBLEM_TYPE_CODE 'problemTypeCode',\n" +
                "a.EMERGENCY_LEVEL_CODE 'emergencyLevelCode',\n" +
                "a.PROBLEM_STATE_CODE 'problemStateCode',\n" +
                "a.PROPOSER 'proposer',\n" +
                "a.PROPOSE_TIME 'proposeTime',\n" +
                "a.PREPROCESSOR 'preprocessor',\n" +
                "a.PRETREATMENT_TIME 'pretreatmentTime',\n" +
                "a.ACTUAL_HANDLER 'actualHandler',\n" +
                "a.ACTUAL_PROCESSING_TIME 'actualProcessingTime',\n" +
                "a.SOLUTION 'solution',\n" +
                "a.REMARKS 'remarks',\n" +
                "a.REGISTRATION_TIME 'registrationTime',\n" +
                "a.PROJNO 'projNo',\n" +
                "a.EDITOR_FIELD 'editorField', \n" +
                "d.DEPT_NAME 'corpName',\n" +
                "b.MENU_NAME 'menuName',\n" +
                "c.MENU_NAME 'level2MenuName' \n" +
                "FROM\n" +
                "PROBLEM_DOCUMENT a ");
        findHql.append(" LEFT JOIN menu b ON a.MENU_ID = b.MENU_ID\n ");
        findHql.append(" LEFT JOIN menu c ON a.LEVEL_2_MENU_ID = c.MENU_ID\n ");
        findHql.append(" LEFT JOIN department d ON a.CORP_ID = d.DEPT_ID\n ");
        findHql.append(" WHERE 1=1 ");
        findHql.append(conditionalSql);
        findHql.append(" ORDER BY a.PD_ID desc ");


        if (probDoctReq.getLength()!=0) {
            findHql.append(" LIMIT ").append(probDoctReq.getStart()).append(",").append(probDoctReq.getLength());
        }
        List<Map<String, Object>> listBySql = this.findListBySql(findHql.toString(),paramList.toArray());

        List<ProblemDocument> objects = MapUtils.convertListMap2ListBean(listBySql, ProblemDocument.class);

        return objects;

    }



    public String conditionalFilter(ProblemDocumentReq probDoctReq){
        StringBuilder whereHql=new StringBuilder();

        if (StringUtil.isNotBlank(probDoctReq.getCorpId())) {
            whereHql.append(" AND a.CORP_ID='"+probDoctReq.getCorpId()+"'");
        }
        if (StringUtil.isNotBlank(probDoctReq.getProblemTypeCode())) {
            whereHql.append(" AND a.PROBLEM_TYPE_CODE='"+probDoctReq.getProblemTypeCode()+"'");
        }
        if (StringUtil.isNotBlank(probDoctReq.getProblemStateCode())) {
            whereHql.append(" AND a.PROBLEM_STATE_CODE='"+probDoctReq.getProblemStateCode()+"'");
        }
        if (StringUtil.isNotBlank(probDoctReq.getEmergencyLevelCode())) {
            whereHql.append(" AND a.EMERGENCY_LEVEL_CODE='"+probDoctReq.getEmergencyLevelCode()+"'");
        }
        if (StringUtil.isNotBlank(probDoctReq.getPreprocessor())) {
            whereHql.append(" AND a.PREPROCESSOR='"+probDoctReq.getPreprocessor()+"'");
        }
        if (StringUtil.isNotBlank(probDoctReq.getActualHandler())) {
            whereHql.append(" AND a.ACTUAL_HANDLER='"+probDoctReq.getActualHandler()+"'");
        }

        if (StringUtil.isNotBlank(probDoctReq.getProposer())) {
            whereHql.append(" AND a.PROPOSER='"+probDoctReq.getProposer()+"'");
        }

        if (StringUtil.isNotBlank(probDoctReq.getProposeTime())) {
            whereHql.append(" AND a.PROPOSE_TIME='"+probDoctReq.getProposeTime()+"'");
        }

        if (StringUtil.isNotBlank(probDoctReq.getPretreatmentTime())) {
            whereHql.append(" AND a.PRETREATMENT_TIME='"+probDoctReq.getPretreatmentTime()+"'");
        }

        if (StringUtil.isNotBlank(probDoctReq.getActualProcessingTime())) {
            whereHql.append(" AND a.ACTUAL_PROCESSING_TIME='"+probDoctReq.getActualProcessingTime()+"'");
        }
        if (StringUtil.isNotBlank(probDoctReq.getMenuId())) {
            whereHql.append(" AND a.MENU_ID='"+probDoctReq.getMenuId()+"'");
        }
        if (StringUtil.isNotBlank(probDoctReq.getLevel2MenuId())) {
            whereHql.append(" AND a.LEVEL_2_MENU_ID='"+probDoctReq.getLevel2MenuId()+"'");
        }
        if (StringUtil.isNotBlank(probDoctReq.getProjNo())) {
            whereHql.append(" AND a.PROJNO LIKE '%"+probDoctReq.getProjNo()+"%'");
        }

        boolean registLtNotBlank = StringUtils.isNotBlank(probDoctReq.getRegistLtTime());
        boolean registGtNotBlank = StringUtils.isNotBlank(probDoctReq.getRegistGtTime());
        if (registLtNotBlank && registGtNotBlank) {
            whereHql.append(" AND a.REGISTRATION_TIME BETWEEN '"+probDoctReq.getRegistLtTime()+"'").append(" AND ");
            whereHql.append("'"+probDoctReq.getRegistGtTime()+"'");
        }

        boolean preLtNotBlank = StringUtils.isNotBlank(probDoctReq.getPreLtTime());
        boolean preGtNotBlank = StringUtils.isNotBlank(probDoctReq.getPreGtTime());
        if (preLtNotBlank && preGtNotBlank) {
            whereHql.append(" AND a.PRETREATMENT_TIME BETWEEN '"+probDoctReq.getPreLtTime()+"'").append(" AND ");
            whereHql.append("'"+probDoctReq.getPreGtTime()+"'");
        }

        boolean actualLtNotBlank = StringUtils.isNotBlank(probDoctReq.getActualLtTime());
        boolean actualGtNotBlank = StringUtils.isNotBlank(probDoctReq.getActualGtTime());
        if (actualLtNotBlank && actualGtNotBlank) {
            whereHql.append(" AND a.ACTUAL_PROCESSING_TIME BETWEEN '"+probDoctReq.getActualLtTime()+"'").append(" AND ");
            whereHql.append("'"+probDoctReq.getActualGtTime()+"'");
        }


        if (StringUtil.isNotBlank(probDoctReq.getCommonField())) {
            whereHql.append(" AND (a.PROPOSER LIKE").append("'%").append(probDoctReq.getCommonField()).append("%'");
            whereHql.append(" OR a.PREPROCESSOR LIKE").append("'%").append(probDoctReq.getCommonField()).append("%'");
            whereHql.append(" OR a.SOLUTION LIKE").append("'%").append(probDoctReq.getCommonField()).append("%'");
            whereHql.append(" OR a.REMARKS LIKE").append("'%").append(probDoctReq.getCommonField()).append("%'");
            whereHql.append(" OR a.PROJNO LIKE").append("'%").append(probDoctReq.getCommonField()).append("%'");
            whereHql.append(" OR a.ACTUAL_HANDLER LIKE").append("'%").append(probDoctReq.getCommonField()).append("%')");
        }
        return whereHql.toString();
    }

}
