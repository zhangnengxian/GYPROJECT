package cc.dfsoft.project.biz.base.complete.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cc.dfsoft.project.biz.base.complete.dao.CheckItemDao;
import cc.dfsoft.project.biz.base.complete.dto.CheckItemReq;
import cc.dfsoft.project.biz.base.complete.entity.CheckItem;
import cc.dfsoft.project.biz.base.complete.service.CheckItemService;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.util.IDUtil;
@Service
@Transactional(readOnly = true,propagation=Propagation.REQUIRED)
public class CheckItemServiceImpl implements CheckItemService {
	/** 自检项Dao*/
	@Resource
	CheckItemDao checkItemDao;

	@Override
	public List<CheckItem> findByType(String type, String checkType,String corpId) {
		return checkItemDao.findByType(type,checkType,corpId);
	}

	@Override
	public List<CheckItem> findByType(String type) {
		return checkItemDao.findByType(type);
	}

	@Override
	public Map<String, Object> queryCheckItems(CheckItemReq checkItem) {
		return checkItemDao.queryCheckItems(checkItem);
	}

	@Override
	public CheckItem findCheckItem(String id) {
		return checkItemDao.get(id);
	}

	@Override
	public void delCheckItem(String id) {
		checkItemDao.delete(id);
	}

	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void saveCheckItem(CheckItem checkItem) {
		CheckItem c = checkItem;
		if(StringUtils.isBlank(c.getId())){
			c.setId(IDUtil.getUniqueId(Constants.MODULE_CODE_COMPLETE));
		}
		checkItemDao.saveOrUpdate(c);
	}

}
