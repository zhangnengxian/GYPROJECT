package cc.dfsoft.project.biz.base.annualplan.service.impl;

import cc.dfsoft.project.biz.base.annualplan.dao.AnnualPlanDao;
import cc.dfsoft.project.biz.base.annualplan.dto.AnnualPlanReq;
import cc.dfsoft.project.biz.base.annualplan.entity.AnnualPlan;
import cc.dfsoft.project.biz.base.annualplan.service.AnnualPlanService;
import cc.dfsoft.project.biz.base.design.entity.DesignInfo;
import cc.dfsoft.project.biz.base.design.entity.DrawingsDirectory;
import cc.dfsoft.project.biz.base.project.dao.ProjectDao;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.subpackage.dao.SubContractDao;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.util.IDUtil;
import cc.dfsoft.uexpress.common.util.JsonUtils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 带气工程服务接口实现
 * @author cui
 * @createTime 2017-08-8
 *
 */
@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class AnnualPlanServiceImpl implements AnnualPlanService {

    /**年度计划Dao*/
    @Resource
    AnnualPlanDao annualPlanDao;

    /**工程Dao*/
    @Resource
    ProjectDao projectDao;

    /**分包合同Dao*/
    @Resource
    SubContractDao subContractDao;


    @Override
    public Map<String, Object> queryAnnualPlan(AnnualPlanReq annualPlanReqReq) {
        return annualPlanDao.queryAnnualPlan(annualPlanReqReq);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void saveAnnualPlan(AnnualPlan annualPlan) {
        if (StringUtils.isBlank(annualPlan.getPlanId())) { // 新增
            annualPlan.setPlanId(IDUtil.getUniqueId(Constants.MODULE_CODE_PROJECTAPPROVAL));
        }
        LoginInfo login= SessionUtil.getLoginInfo();
        annualPlan.setAffiliatedCompany(login.getCorpId()); //燃气公司id
        annualPlan.setCorpName(login.getCorpName());		//燃气公司
        annualPlanDao.saveOrUpdate(annualPlan);
    }

    @Override
    public Date getDatabaseDate() {
        return annualPlanDao.getDatabaseDate();
    }
    
    /**
     * 年度计划批量导入
     * @author fuliwei
     * @createTime 2017年8月31日
     * @param 
     * @return
     */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void batInsertCostSum(JSONArray jsonArr) {
		List<AnnualPlan> list=new ArrayList<AnnualPlan>();
		LoginInfo login=SessionUtil.getLoginInfo();
		
		
		for (int i = 0; i < jsonArr.size(); i++) {
			JSONObject jo = jsonArr.getJSONObject(i);
			AnnualPlan dm=JsonUtils.jsonToBean(jo, AnnualPlan.class);
			dm.setAffiliatedCompany(login.getCorpId());
			dm.setCorpName(login.getCorpName());
			dm.setPlanId(IDUtil.getUniqueId(Constants.MODULE_CODE_PROJECTAPPROVAL));//id
			dm.setDeptId(login.getDeptId());
			list.add(dm);
		}
		
		
		/*if(list!=null && list.size()>0){
			for(AnnualPlan ap:list){
				String planNo=ap.getPlanNo();
				annualPlanDao.deleteByPlanNo(planNo,login.getDeptId());
			}
		}*/
		annualPlanDao.batchInsertObjects(list);
	}
	
	/**
	 * 删除年度计划
	 * @author fuliwei
	 * @createTime 2017年10月9日
	 * @param 
	 * @return
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public String deleteAnnualPlan(String planId) {
		//用前度计划id去查工程
		List<Project> list=projectDao.findByPlanId(planId);
		
		if(list!=null && list.size()>0){
			return "already";
		}else{
			annualPlanDao.delete(planId);
			return Constants.OPERATE_RESULT_SUCCESS;
		}
		
	}
}
