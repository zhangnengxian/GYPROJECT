package cc.dfsoft.project.biz.base.baseinfo.service;

import java.util.Map;

import cc.dfsoft.project.biz.base.baseinfo.dto.ConnectContentReq;
import cc.dfsoft.project.biz.base.baseinfo.entity.VisaQuantities;

/**
 * 签证工程量标准
 * @author fuliwei
 *
 */
public interface VisaQuantitiesService {
	
	/**
	 * 查询签证工程量标准列表
	 * @author fuliwei
	 * @createTime 2017-2-4
	 * @param ConnectContentReq
	 * @return Map<String,Object> 
	 */
	public  Map<String,Object> queryVisaQuanlities(ConnectContentReq connectContentReq);
	
	/**
	 * 删除签证工程量
	 * @author fuliwei
	 * @createTime 2017年2月4日
	 * @param id
	 * @return String
	 */
	public void deleteById(String id);
	
	/**
	 * 保存签证工程量
	 * @author fuliwei
	 * @createTime 2017年2月4日
	 * @param visaQuantities
	 * @return String
	 */
	public String saveVisaQuantities(VisaQuantities visaQuantities);
}
