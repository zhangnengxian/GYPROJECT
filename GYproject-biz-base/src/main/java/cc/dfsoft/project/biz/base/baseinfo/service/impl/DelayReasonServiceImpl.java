package cc.dfsoft.project.biz.base.baseinfo.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cc.dfsoft.project.biz.base.baseinfo.dao.DelayReasonDao;
import cc.dfsoft.project.biz.base.baseinfo.dto.DealyReasonQueryReq;
import cc.dfsoft.project.biz.base.baseinfo.entity.DelayReason;
import cc.dfsoft.project.biz.base.baseinfo.service.DelayReasonService;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.util.IDUtil;
import cc.dfsoft.uexpress.common.util.StringUtil;

@Service
@Transactional(readOnly = true,propagation=Propagation.REQUIRED)
public class DelayReasonServiceImpl implements DelayReasonService{
	
	@Resource
	DelayReasonDao delayReasonDao;
	
	/**
	 * 查询延期原因列表
	 * @author
	 * @createTime  2016-12-8
	 * @param
	 * @return
	 */
	@Override
	public Map<String, Object> queryDealyReasonList(DealyReasonQueryReq dealyReasonQueryReq) {
		return delayReasonDao.queryDealyReasonList(dealyReasonQueryReq);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void saveOrUpdateDealyReason(DelayReason dealyReason) {
		//新建
		if(StringUtil.isBlank(dealyReason.getDelayReasonId())){
			dealyReason.setDelayReasonId(IDUtil.getUniqueId(Constants.MODULE_CODE_PROJECTSTART));
		}
		//修改
		delayReasonDao.saveOrUpdate(dealyReason);
	}
	
	/**
	 * 删除延期原因
	 * @author
	 * @createTime 2016-12-8
	 * @param
	 * @return
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void delDealyReason(String id) {
		delayReasonDao.delete(id);
	}
	
	/**
	 * 延期列表
	 * @author
	 * @createTime  2016-12-10
	 * @param
	 * @return
	 */
	@Override
	public List<DelayReason> findDelayReason() {
		return delayReasonDao.findDelayReason();
	}

}
