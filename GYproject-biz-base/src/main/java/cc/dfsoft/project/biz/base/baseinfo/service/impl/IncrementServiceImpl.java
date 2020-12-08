package cc.dfsoft.project.biz.base.baseinfo.service.impl;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cc.dfsoft.project.biz.base.baseinfo.dto.IncrementReg;
import cc.dfsoft.project.biz.base.baseinfo.service.IncrementService;
import cc.dfsoft.project.biz.base.contract.dao.IncrementDao;
import cc.dfsoft.project.biz.base.contract.entity.Increment;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.util.IDUtil;;

@Service
@Transactional(readOnly = true,propagation=Propagation.REQUIRED)
public class IncrementServiceImpl  implements IncrementService {
	/**
	 * 查询税率信息
	 */
@Resource
IncrementDao incrementDao;

 /**
  * 根据id查询详细信息
  */
@Override
    public Increment viewIncrementDetailById(String id) {
	// TODO Auto-generated method stub
	 return incrementDao.get(id);
  }
/**
 * 根据id删除税率
 * @param id
 */

@Override
@Transactional(propagation=Propagation.SUPPORTS,readOnly=true)
public void deleteIncrement(String id) {
	// TODO Auto-generated method stub
		incrementDao.delete(id);
}
/**
 * 保存税率信息
 */

@Override
@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
public void saveIncrement(Increment increment) {
	//BusinessPartners businessPartnersResult=businessPartners;
		if(StringUtils.isBlank(increment.getInId())){
			increment.setInId(IDUtil.getUniqueId(Constants.MODULE_CODE_INCRE));//生成主键ID 税率Id值
			
		}/*else{ 
			businessPartnersResult = businessPartnersDao.get(businessPartners.getUnitId());
			businessPartnersResult.setUnitName(businessPartners.getUnitName());
			businessPartnersResult.setShortName(businessPartners.getShortName());
			businessPartnersResult.setUnitDirector(businessPartners.getUnitDirector());
			businessPartnersResult.setUnitPhone(businessPartners.getUnitPhone());
			businessPartnersResult.setUnitMobile(businessPartners.getUnitMobile());
			businessPartnersResult.setUnitFoundDate(businessPartners.getUnitFoundDate());
			businessPartnersResult.setUnitQualification(businessPartners.getUnitQualification());
			businessPartnersResult.setUnitType(businessPartners.getUnitType());
		}*/
//		if(StringUtils.isBlank(increment.getIncrementCode())){
//			increment.setIncrementCode(increment.getIncrementCode());
//		}
		incrementDao.saveOrUpdate(increment);
}
@Override
public Map<String, Object> queryIncrementInfo(IncrementReg incrementReg){
	return incrementDao.queryIncrementInfo(incrementReg);
}
/**
 * 查询税率列表
 * @author liaoyq
 * @createTime 2019-04-08
 */
@Override
public List<Increment> queryAllList(IncrementReg incrementReg) {
	return incrementDao.queryIncrement(incrementReg);
}


}
