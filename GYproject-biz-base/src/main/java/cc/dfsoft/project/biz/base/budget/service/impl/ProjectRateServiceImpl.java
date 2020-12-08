package cc.dfsoft.project.biz.base.budget.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import cc.dfsoft.project.biz.base.budget.dao.ProjectRateDao;
import cc.dfsoft.project.biz.base.budget.dto.ProjectRateReq;
import cc.dfsoft.project.biz.base.budget.entity.ProjectRate;
import cc.dfsoft.project.biz.base.budget.service.ProjectRateService;
import cc.dfsoft.uexpress.common.constant.Constants;

@Service
@Transactional(readOnly = true,propagation=Propagation.REQUIRED)
public class ProjectRateServiceImpl implements ProjectRateService {
	
	@Resource
	ProjectRateDao projectRateDao;
	
	@Override
	public List<Map<String,Object>> queryProjectRate(ProjectRateReq projectRateReq) {
           List<ProjectRate> list = projectRateDao.queryProjectRate(projectRateReq);
   		   List listNew=new ArrayList();
   		   BigDecimal a = new BigDecimal(100);
   		   if(null!=list&&list.size()>0){
	   			ProjectRate pr=list.get(0);
	   			Map<String,Object> map=new HashMap<String,Object>();
	   			map.put("id", pr.getRateId());
	   			map.put("name", "储备金费率");
	   			map.put("value",pr.getStore().multiply(a).doubleValue() + "%");
	   			map.put("hid",pr.getStore());
	   			listNew.add(map);
	   			Map<String,Object> map1=new HashMap<String,Object>();
	   			map1.put("id", pr.getRateId());
	   			map1.put("name", "监检费费率");
	   			map1.put("value",pr.getInspection().multiply(a).doubleValue() + "%");
	   			listNew.add(map1);
	   			Map<String,Object> map2=new HashMap<String,Object>();
	   			map2.put("id", pr.getRateId());
	   			map2.put("name", "监理费费率");
	   			map2.put("value",pr.getSupervisor().multiply(a).doubleValue() + "%");
	   			listNew.add(map2);
   			
   		}
   		   return listNew;
	}

	@Override
	public ProjectRate queryProjectRateById(String id) {
		return projectRateDao.get(id);
	}
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public String saveOrUpdateprojectRate( String id, BigDecimal number, String name){
		ProjectRate projectRate = this.queryProjectRateById(id);
		if("储备金费率".equals(name)){
			projectRate.setStore(number);
		}else if("监检费费率".equals(name)){
			projectRate.setInspection(number);
		}else if("监理费费率".equals(name)){
			projectRate.setSupervisor(number);
		}
		projectRateDao.update(projectRate);
		return Constants.OPERATE_RESULT_SUCCESS;
	}

}
