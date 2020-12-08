package cc.dfsoft.uexpress.biz.sys.dept.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cc.dfsoft.uexpress.biz.sys.dept.dao.LocationSetUpDao;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LocationSetUpQueryReq;
import cc.dfsoft.uexpress.biz.sys.dept.entity.LocationSetUp;
import cc.dfsoft.uexpress.biz.sys.dept.service.LocationSetUpService;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.util.IDUtil;
@Service
@Transactional(propagation = Propagation.REQUIRED)
public class LocationSetUpServiceImpl implements LocationSetUpService{
	
	/**定位设置*/
	@Resource
	LocationSetUpDao locationSetUpDao;
	
	/**
	 * 查询定位设置列表
	 * @author fuliwei
	 * @createTime 2017年2月24日
	 * @param 
	 * @return
	 */
	@Override
	public Map<String, Object> quertLocationSetUpList(LocationSetUpQueryReq req) {
		return locationSetUpDao.quertLocationSetUpList(req);
	}
	
	/**
	 * 保存定位设置
	 * @author fuliwei
	 * @createTime 2017年2月24日
	 * @param 
	 * @return
	 */
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public String saveLocationSetUp(LocationSetUp location) {
		if(StringUtils.isBlank(location.getLsuId())){
			location.setLsuId(IDUtil.getUniqueId(Constants.STANDARD));
			//新增时
			//通过类型查询
			LocationSetUp loc=locationSetUpDao.queryByDeptType(location.getDeptType());
			if(loc!=null){
				return "exist";
			}
		}
		
		
		locationSetUpDao.saveOrUpdate(location);
		return Constants.OPERATE_RESULT_SUCCESS;
	}
	
	/**
	 * 删除定位设置
	 * @author fuliwei
	 * @createTime 2017年2月24日
	 * @param 
	 * @return
	 */
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void delLocationSetUp(String lsuId) {
		locationSetUpDao.delete(lsuId);
	}

}
