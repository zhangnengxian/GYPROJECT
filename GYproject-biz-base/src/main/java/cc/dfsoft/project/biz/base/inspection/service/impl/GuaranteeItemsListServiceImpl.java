package cc.dfsoft.project.biz.base.inspection.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cc.dfsoft.project.biz.base.inspection.dao.GuaranteeItemsListDao;
import cc.dfsoft.project.biz.base.inspection.dto.ThreadingPipeReq;
import cc.dfsoft.project.biz.base.inspection.service.GuaranteeItemsListService;

/**
 * 保证项目
 * @author fuliwei
 *
 */
@Service
@Transactional(readOnly = true,propagation=Propagation.REQUIRED)
public class GuaranteeItemsListServiceImpl implements GuaranteeItemsListService{
	
	/**保证项目Dao*/
	@Resource
	GuaranteeItemsListDao guaranteeItemsListDao;
	
	/**
	 * 保证项目列表查询
	 * @author fuliwei
	 * @createTime 2017-2-7
	 * @param ThreadingPipeReq 查询条件dto
	 * @return Map<String, Object>
	 */
	@Override
	public Map<String, Object> queryGuaranteeItemsList(ThreadingPipeReq req) {
		return guaranteeItemsListDao.queryGuaranteeItemsList(req);
	}

}
