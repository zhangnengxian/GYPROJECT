package cc.dfsoft.project.biz.base.constructmanage.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cc.dfsoft.project.biz.base.baseinfo.dao.VisaQuantitiesDao;
import cc.dfsoft.project.biz.base.baseinfo.dto.ConnectContentReq;
import cc.dfsoft.project.biz.base.constructmanage.dao.VisaQuantitiesRecordDao;
import cc.dfsoft.project.biz.base.constructmanage.dto.EngineeringVisaQueryReq;
import cc.dfsoft.project.biz.base.constructmanage.service.VisaQuantitiesRecordService;

/**
 * 签证标准记录
 * @author fuliwei
 *
 */
@Service
@Transactional(readOnly = true,propagation=Propagation.REQUIRED)
public class VisaQuantitiesRecordServiceImpl implements VisaQuantitiesRecordService{
	
	/**签证标准记录Dao*/
	@Resource
	VisaQuantitiesRecordDao visaQuantitiesRecordDao;
	
	/**签证标准Dao*/
	@Resource
	VisaQuantitiesDao visaQuantitiesDao;
	
	/**
	 * 签证标准记录列表查询
	 * @author fuliwei
	 * @createTime 2017年2月4日
	 * @param  EngineeringVisaQueryReq
	 * @return Map<String,Object>
	 */
	@Override
	public Map<String, Object> queryVisaQuantitiesRecord(EngineeringVisaQueryReq req) {
		Map<String, Object> map=visaQuantitiesRecordDao.queryVisaQuantitiesRecord(req);
		List list=(List) map.get("data");
		
		if(list!=null && list.size()>0){
			return map;
		}else{
			ConnectContentReq ccReq=new ConnectContentReq();
			Map<String, Object> visaQuantitiesMap =visaQuantitiesDao.queryVisaQuanlities(ccReq);
			return visaQuantitiesMap;
		}
		
	}

}
