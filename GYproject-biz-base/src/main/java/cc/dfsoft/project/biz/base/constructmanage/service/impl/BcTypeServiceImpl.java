package cc.dfsoft.project.biz.base.constructmanage.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cc.dfsoft.project.biz.base.constructmanage.dao.BcTypeDao;
import cc.dfsoft.project.biz.base.constructmanage.entity.BcType;
import cc.dfsoft.project.biz.base.constructmanage.service.BcTypeService;

@Service
@Transactional(readOnly = true,propagation=Propagation.REQUIRED)
public class BcTypeServiceImpl implements BcTypeService {

	/** 通知类型Dao*/
	@Resource
	BcTypeDao bcTypeDao;

	@Override
	public List<BcType> findBcType(String typeId, String relationTypeId) {
		return bcTypeDao.findBcType(typeId,relationTypeId);
	}

	@Override
	public List<BcType> findAllBcType() {
		return bcTypeDao.findAllBcType();
	}
}
