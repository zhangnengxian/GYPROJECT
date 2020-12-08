package cc.dfsoft.project.biz.base.subpackage.dao.impl;

import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.subpackage.dao.SurveySubContractAuditDao;
import cc.dfsoft.project.biz.base.subpackage.dto.SubAuditReq;
import cc.dfsoft.project.biz.base.subpackage.entity.SubContract;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.constant.DataFilerSetUpDto;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;
import cc.dfsoft.uexpress.common.util.ResultUtil;
import cc.dfsoft.uexpress.common.util.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
@Repository
public class SurveySubContractAuditDaoImpl extends NewBaseDAO<SubContract, String> implements SurveySubContractAuditDao {

    @Override
    public  Map<String,Object> getSubContractAuditById(String scId) {
        String sql = "SELECT sub_con.SC_ID,sub_con.SC_NO,sub_con.PROJ_ID,sub_con.PROJ_NO,sub_con.PROJ_NAME,sub_con.PROJ_SCALE_DES,sub_con.PROJ_ADDR,sub_con.DEPT_NAME,sub_con.PROJ_COMP_DIRECTOR,"+
                "sub_con.GAS_COM_LEGAL_REPRESENT,sub_con.CU_NAME,sub_con.CU_DIRECTOR,sub_con.CU_PM,sub_con.CONTRACT_METHOD,sub_con.CU_RESPONSIBLE_PERSON,sub_con.CONTRACT_MODE,"+
                "sub_con.SC_SCOPE,sub_con.INCREMENT,DATE_FORMAT(sub_con.SC_PLANNED_START_DATE,'%Y-%m-%d') SC_PLANNED_START_DATE,DATE_FORMAT(sub_con.SC_PLANNED_END_DATE,'%Y-%m-%d') SC_PLANNED_END_DATE,sub_con.PAY_TYPE,sub_con.PAY_RATE,sub_con.SC_TYPE,sub_con.PROGRESS_TYPE,"+
                "sub_con.SC_AMOUNT,sub_con.SC_SIGN_DATE,DATE_FORMAT(sub_con.OPERATE_DATE,'%Y-%m-%d') OPERATE_DATE,projects.CORP_NAME,projects.PROJECT_TYPE_DES,projects.CONTRIBUTION_MODE_DES,projects.DEPT_NAME AS DEPT_NAMES,projects.BUDGET_TOTAL_COST "+
                "FROM sub_contract sub_con  JOIN project projects ON sub_con.PROJ_ID = projects.PROJ_ID "+
                "where sub_con.SC_ID =? ";


        Map<String,Object> maplist = super.findObjectBySql(sql.toString(),new String[]{scId});

        return maplist;
    }

    @Override
    public Map<String, Object> querySubContractAuditList(SubAuditReq subAuditReq) throws ParseException {

        LoginInfo loginInfo = SessionUtil.getLoginInfo();
        boolean notBlank = StringUtil.isNotBlank(loginInfo.getCorpId());
        String corpId=notBlank?loginInfo.getCorpId():loginInfo.getBelongCorpId();
        Criteria c = super.getCriteria();
    	//分公司数据过滤 如
		List<DataFilerSetUpDto> list = Constants.getDataFilterMapByKey(loginInfo.getDeptId()+"_"+"110618");
		if(list!=null && list.size()>0){
			StringBuffer hql = new StringBuffer();
			hql.append("proj_id in (select proj_id from project where 1=1 ");

			if(StringUtils.isNotBlank(list.get(0).getSupSql())){
				hql.append(list.get(0).getSupSql());
			}
			hql.append(")");
			c.add(Restrictions.sqlRestriction(hql.toString()));
		}
		
		
        
        //工程编号
        if(StringUtils.isNotBlank(subAuditReq.getProjNo())){
            c.add(Restrictions.eq("projNo",subAuditReq.getProjNo()));
        }

        //工程状态
        if(StringUtils.isNotBlank(subAuditReq.getProjStatusId())){
            StringBuffer hql = new StringBuffer("from Project where projStatusId = '").append(subAuditReq.getProjStatusId()).append("'")
                    .append(" and corpId in(").append(corpId).append(")");

            List<Project> projects = super.findByHql(hql.toString());
            List<String> projIds = new ArrayList();
            if(projects.size()>0){
                for(Project project:projects){
                    projIds.add(project.getProjId());
                }
                c.add(Restrictions.in("projId",projIds));
            }else{
                // 返回结果
                return ResultUtil.pageResult(0, subAuditReq.getDraw(),new ArrayList());
            }
        }

        //工程名称
        if(StringUtils.isNotBlank(subAuditReq.getProjName())){
            c.add(Restrictions.like("projName","%"+subAuditReq.getProjName()+"%"));
        }
        //用于字符串日期格式转换
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //合同实际签订开始日期
        if(StringUtils.isNotBlank(subAuditReq.getOperateDateStart())){
            c.add(Restrictions.ge("operateDate", sdf.parse(subAuditReq.getOperateDateStart())));
        }
        //合同实际签订结束日期
        if(StringUtils.isNotBlank(subAuditReq.getOperateDateEnd())){
            c.add(Restrictions.le("operateDate", sdf.parse(subAuditReq.getOperateDateEnd())));
        }

        c.addOrder(Order.desc("operateDate"));
        // 数据库根据条件过滤记录数
        int filterCount = this.countByCriteria(c);

        // 根据条件获取查询信息
        List<SubContract> surveyInfoList = this.findBySortCriteria(c, subAuditReq);

        return ResultUtil.pageResult(filterCount, subAuditReq.getDraw(),surveyInfoList);
    }
}
