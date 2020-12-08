package cc.dfsoft.project.biz.base.accept.service.impl;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cc.dfsoft.project.biz.base.accept.service.ScaleDetailCommissionService;
import cc.dfsoft.project.biz.base.project.dao.ScaleDetailDao;
import cc.dfsoft.project.biz.base.project.dto.ScaleDetailQueryReq;

@Service
@Transactional(readOnly = true,propagation=Propagation.REQUIRED)
public class ScaleDetaleCommissionServiceImpl implements ScaleDetailCommissionService {
	
	/**工程规模明细dao*/
	@Resource
	ScaleDetailDao scaleDetailDao;

	@Override
	public Map<String, Object> queryScaleDetail(ScaleDetailQueryReq scaleDetailQueryReq) throws ParseException {
		Map<String,Object> result = new HashMap();
		result = scaleDetailDao.queryScaleDetail(scaleDetailQueryReq);
		return result;
	}


}
