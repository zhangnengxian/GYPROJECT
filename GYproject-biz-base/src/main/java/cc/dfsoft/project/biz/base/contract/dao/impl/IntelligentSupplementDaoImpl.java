package cc.dfsoft.project.biz.base.contract.dao.impl;

import cc.dfsoft.project.biz.base.contract.dao.IntelligentSupplementDao;
import cc.dfsoft.project.biz.base.contract.dto.IntelligentSupplementReq;
import cc.dfsoft.project.biz.base.contract.entity.IntelligentSupplement;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.constant.DataFilerSetUpDto;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;
import cc.dfsoft.uexpress.common.util.ResultUtil;
import cc.dfsoft.uexpress.common.util.StringUtil;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Desc: 类描述
 * @Auther: zhangnx
 * @Date: 2019/7/26 18:12
 * @Version:1.0
 */
@Repository
public class IntelligentSupplementDaoImpl extends NewBaseDAO<IntelligentSupplement, String> implements IntelligentSupplementDao {




    @Override
    public Map<String, Object> queryIntelligentSupplementList(IntelligentSupplementReq req) {
        List<Object> paramList=new ArrayList<>();

        StringBuilder filterCountSql=new StringBuilder();
        filterCountSql.append("SELECT count(a.IS_ID) FROM intelligent_supplement a, intelligent_meter_contract b ");
        filterCountSql.append(" WHERE a.PROJ_ID = b.PROJ_ID ");
        filterCountSql.append(" AND a.IS_ID = ( SELECT max( b.IS_ID ) FROM intelligent_supplement b WHERE b.IS_NO = a.IS_NO )");
        filterCountSql.append(conditionalFilter(req));
        int filterCount = this.getCountBySql(filterCountSql.toString(),paramList.toArray());


        StringBuilder sql=new StringBuilder();
        sql.append("SELECT\n" +
                     queryColumn()+",\n"+
                    "b.PROJ_NO 'projNo',\n" +
                    "b.PROJ_NAME 'projName'\n");
        sql.append("FROM intelligent_supplement a, intelligent_meter_contract b ");
        sql.append("WHERE a.PROJ_ID = b.PROJ_ID ");
        sql.append(" AND a.IS_ID = ( SELECT max( b.IS_ID ) FROM intelligent_supplement b WHERE b.IS_NO = a.IS_NO )");
        sql.append(conditionalFilter(req));

        List<Map<String, Object>> listBySql = this.findListBySql(sql.toString(),paramList.toArray());
       return ResultUtil.pageResult( filterCount, req.getDraw(), listBySql);
    }





    @Override
    public int countByProjId(String projId) {
        List<Object> paramList=new ArrayList<>();
        String sql="SELECT COUNT(A.IS_ID) FROM INTELLIGENT_SUPPLEMENT A WHERE A.PROJ_ID=?";
        paramList.add(projId);
        return this.getCountBySql(sql,paramList.toArray());
    }








    @Override
    public Map<String, Object> findById(String id) {
        List<Object> paramList=new ArrayList<>();
        StringBuilder sql=new StringBuilder();

        sql.append(" SELECT\n" +
                            queryColumn()+",\n"+
                            "b.IMC_NO 'imcNo',\n" +
                            "c.PROJ_NO 'projNo',\n" +
                            "c.PROJ_NAME 'projName',\n" +
                            "c.PROJ_ADDR 'projAddr',\n" +
                            "c.PROJECT_TYPE_DES 'projectTypeDes',\n" +
                            "c.CONTRIBUTION_MODE_DES 'contributionModeDes',\n" +
                            "c.DEPT_NAME 'deptName',\n" +
                            "c.PROJ_SCALE_DES 'projScaleDes',\n" +
                            "c.CUST_NAME 'custName',\n" +
                            "c.CUST_PHONE 'custPhone',\n" +
                            "c.CORP_NAME 'corpName'\n" +
                  "FROM intelligent_supplement a,\n" +
                        "intelligent_meter_contract b,\n" +
                        "project c\n" +
                "WHERE a.PROJ_ID = b.PROJ_ID AND a.PROJ_ID = c.PROJ_ID ").append(" AND a.IS_ID =?");

                 paramList.add(id);

        Map<String, Object> objectBySql = this.findObjectBySql(sql.toString(), paramList.toArray());

        return objectBySql;
    }






    @Override
    public Map<String, Object> queryToAuditSupplement(IntelligentSupplementReq req) {
        List<Object> paramList=new ArrayList<>();

        String whereHql = conditionalFilter(req);

        StringBuilder filterCountSql=new StringBuilder();
        filterCountSql.append("SELECT count( a.IS_ID )  FROM intelligent_supplement a, project b WHERE a.PROJ_ID = b.PROJ_ID");
        filterCountSql.append(whereHql);
        int filterCount = this.getCountBySql(filterCountSql.toString(),paramList.toArray());


        StringBuilder sql=new StringBuilder();
        sql.append("SELECT\n" +
                        queryColumn()+",\n"+
                        "b.PROJ_NO 'projNo',\n" +
                        "b.PROJ_NAME 'projName',\n" +
                        "b.PROJ_ADDR 'projAddr',\n" +
                        "b.PROJECT_TYPE_DES 'projectTypeDes',\n" +
                        "b.CONTRIBUTION_MODE_DES 'contributionModeDes',\n" +
                        "b.DEPT_NAME 'deptName',\n" +
                        "b.PROJ_SCALE_DES 'projScaleDes',\n" +
                        "b.CUST_NAME 'custName',\n" +
                        "b.CUST_PHONE 'custPhone',\n" +
                        "b.CORP_NAME 'corpName'\n" +
                "FROM intelligent_supplement a, project b \n" +
                "WHERE a.PROJ_ID = b.PROJ_ID ");
        sql.append(whereHql);


        List<Map<String, Object>> listBySql = this.findListBySql(sql.toString(),paramList.toArray());
        return ResultUtil.pageResult( filterCount, req.getDraw(), listBySql);
    }







    @Override
    public List<IntelligentSupplement> queryListByisNo(String isNo) {
        Criteria c = super.getCriteria();
        if(StringUtils.isNotBlank(isNo)){
            c.add(Restrictions.eq("isNo",isNo));
            return this.findByCriteria(c);
        }
       return null;
    }
















    public String queryColumn(){//补充协议表的查询所有列
        String columnStr= "a.IS_ID 'isId',\n" +
                        "a.IS_NO 'isNo',\n" +
                        "a.IS_AMOUNT 'isAmount', \n" +
                        "a.INVOICE_TYPE 'invoiceType',\n" +
                        "a.INCREMENT 'increment',\n" +
                        "a.IS_PRINT 'isPrint',\n" +
                        "a.IS_STATUS 'isStatus', \n" +
                        "a.PAY_TYPE 'payType',\n" +
                        "a.PAYMENT_RATIO1 'paymentRatio1',\n" +
                        "a.FIRST_PAYMENT 'firstPayment', \n" +
                        "a.PAYMENT_RATIO2 'paymentRatio2',\n" +
                        "a.SECOND_PAYMENT 'secondPayment',\n" +
                        "a.PAYMENT_RATIO3 'paymentRatio3',\n" +
                        "a.THRID_PAYMENT 'thridPayment', \n" +
                        "a.CHANGE_REASONS 'changeReasons',\n" +
                        "a.SIGN_DATE 'signDate', \n" +
                        "a.CHANGE_TIME 'changeTime', \n" +
                        "a.AGENT 'agent',\n" +
                        "a.MODIFY_REMARK 'modifyRemark', \n" +
                        "a.MODIFY_DATE 'modifyDate', \n" +
                        "a.IS_VALID 'isValid', \n" +
                        "a.REMARK 'remark',\n" +
                        "a.IMC_ID 'imcId',\n" +
                        "a.PROJ_ID 'projId' \n";
        return columnStr;
    }








    public String conditionalFilter(IntelligentSupplementReq req){

        StringBuilder whereHql=new StringBuilder();
        LoginInfo loginInfo = SessionUtil.getLoginInfo();


        if (StringUtil.isNotBlank(req.getIsStatus())) {
            whereHql.append(" AND a.IS_STATUS='"+req.getIsStatus()+"'");
        }

        if (StringUtil.isNotBlank(req.getIsNo())) {
            whereHql.append(" AND a.IS_NO='"+req.getIsNo()+"'");
        }

        if (StringUtil.isNotBlank(req.getIsPrint())) {
            whereHql.append(" AND a.IS_PRINT='"+req.getIsPrint()+"'");
        }
        if (StringUtil.isNotBlank(req.getIsValid())) {
            whereHql.append(" AND a.IS_VALID='"+req.getIsValid()+"'");
        }

        if (StringUtil.isNotBlank(req.getProjId())) {
            whereHql.append(" AND b.PROJ_ID='"+req.getProjId()+"'");
        }
        if (StringUtil.isNotBlank(req.getProjNo())) {
            whereHql.append(" AND b.PROJ_NO='"+req.getProjNo()+"'");
        }
        if (StringUtil.isNotBlank(req.getProjName())) {
            whereHql.append(" AND b.PROJ_NAME like '%"+req.getProjName()+"%'");
        }

        DataFilerSetUpDto config = Constants.isConfig(loginInfo.getDeptId() + "_" + req.getMenuId());
        if (config!=null && StringUtil.isNotBlank(config.getSupSql())){//有配置根据配置查询
            whereHql.append(config.getSupSql());
            return whereHql.toString();
        }

        whereHql.append(" AND b.CORP_ID like'"+loginInfo.getCorpId()+"%'");
        whereHql.append(" AND b.DEPT_ID like'"+loginInfo.getDeptId()+"%'");

        return whereHql.toString();
    }





    /**
     * 根据工程ID查询智能表补充协议（有效的）
     */
	@Override
	public List<IntelligentSupplement> findByProjId(String projId) {
		Criteria c = super.getCriteria();
		 c.add(Restrictions.ne("isStatus","-1"));
		 if(StringUtils.isNotBlank(projId)){
			 c.add(Restrictions.eq("projId",projId));
			 return this.findByCriteria(c);
		 }
		return null;
	}

}
