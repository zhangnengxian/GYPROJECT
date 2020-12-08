package cc.dfsoft.project.biz.base.baseinfo.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cc.dfsoft.project.biz.base.baseinfo.dao.VisaQuantitiesDao;
import cc.dfsoft.project.biz.base.baseinfo.dto.ConnectContentReq;
import cc.dfsoft.project.biz.base.baseinfo.entity.VisaQuantities;
import cc.dfsoft.project.biz.base.baseinfo.service.VisaQuantitiesService;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.util.IDUtil;
/**
 * 签证工程量标准
 * @author fuliwei
 *
 */
@Service
@Transactional(readOnly = true,propagation=Propagation.REQUIRED)
public class VisaQuantitiesServiceImpl implements VisaQuantitiesService{
	
	@Resource
	VisaQuantitiesDao visaQuantitiesDao;
	
	/**
	 * 查询签证工程量标准列表
	 * @author fuliwei
	 * @createTime 2017-2-4
	 * @param ConnectContentReq
	 * @return Map<String,Object> 
	 */
	@Override
	public Map<String, Object> queryVisaQuanlities(ConnectContentReq connectContentReq) {
		return visaQuantitiesDao.queryVisaQuanlities(connectContentReq);
	}
	
	/**
	 * 删除签证工程量
	 * @author fuliwei
	 * @createTime 2017年2月4日
	 * @param id
	 * @return String
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void deleteById(String id) {
		visaQuantitiesDao.delete(id);
	}
	
	/**
	 * 保存签证工程量
	 * @author fuliwei
	 * @createTime 2017年2月4日
	 * @param visaQuantities
	 * @return String
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public String saveVisaQuantities(VisaQuantities visaQuantities) {
		if(StringUtils.isBlank(visaQuantities.getId())){
			visaQuantities.setId(IDUtil.getUniqueId(Constants.STANDARD));
		}
		visaQuantitiesDao.saveOrUpdate(visaQuantities);
		return Constants.OPERATE_RESULT_SUCCESS;
	}

}
