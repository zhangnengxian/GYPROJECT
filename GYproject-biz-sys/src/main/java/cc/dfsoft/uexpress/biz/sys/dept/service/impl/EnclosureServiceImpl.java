package cc.dfsoft.uexpress.biz.sys.dept.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cc.dfsoft.uexpress.biz.sys.dept.dao.EnclosureDao;
import cc.dfsoft.uexpress.biz.sys.dept.entity.Enclosure;
import cc.dfsoft.uexpress.biz.sys.dept.service.EnclosureService;

/**
 * 附件接口实现类
 * @author 
 *
 */
@Service
@Transactional(readOnly = true,propagation=Propagation.REQUIRED)
public class EnclosureServiceImpl implements EnclosureService {
	
	@Resource
	EnclosureDao enclosureDao;
	@Override
	public  List<Enclosure> queryEnclosureByBus(String id,String sourceType) {
		return enclosureDao.queryEnuclosureByBus(id,sourceType);
	}
}
