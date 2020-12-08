package cc.dfsoft.project.biz.base.subpackage.service.impl;

import cc.dfsoft.project.biz.base.subpackage.dao.CostApplyDao;
import cc.dfsoft.project.biz.base.subpackage.dto.CostApplyReq;
import cc.dfsoft.project.biz.base.subpackage.service.CostApplyService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.Map;

/**
 * 费用申请
 * @author cui
 *已作废
 */
@Service
@Transactional(readOnly = true,propagation=Propagation.REQUIRED)
public class CostApplyServiceImpl implements CostApplyService {
	
	/**费用申请Dao*/
	@Resource
	CostApplyDao costApplyDao;


	@Override
	public Map<String, Object> queryCostApply(CostApplyReq costApplyReq) throws ParseException {
		return costApplyDao.queryCostApply(costApplyReq);
	}
}
