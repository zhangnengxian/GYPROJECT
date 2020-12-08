package cc.dfsoft.project.biz.base.contract.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.sun.org.apache.regexp.internal.recompile;

import cc.dfsoft.project.biz.base.contract.dao.SupplementalContractModifyHistoryDao;
import cc.dfsoft.project.biz.base.contract.dto.SupplementalContractQueryReq;
import cc.dfsoft.project.biz.base.contract.dto.supplementalContractModifyHistoryReq;
import cc.dfsoft.project.biz.base.contract.entity.SupplementalContractModifyhistory;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;
import cc.dfsoft.uexpress.common.util.ResultUtil;

@Repository
public class SupplementalContractModifyHistoryDaompI extends NewBaseDAO<SupplementalContractModifyhistory, String> implements SupplementalContractModifyHistoryDao {
    /**
     * 根据scId查询实体
     */
	@Override
	public Map<String, Object> queryModifyHistory(String scId,supplementalContractModifyHistoryReq modifyhistoryReq) throws Exception {
		// TODO Auto-generated method stub
		Criteria c = super.getCriteria();
		if(StringUtils.isNotBlank(scId)){
			c.add(Restrictions.eq("scId", scId));
		}
	    c.addOrder(Order.asc("signDate"));  // 按照签订日期排序
		int filterCount = this.countByCriteria(c);
			// 根据条件获取查询信息
		List<SupplementalContractModifyhistory> list = this.findBySortCriteria(c,modifyhistoryReq);
			// 返回结果
		return ResultUtil.pageResult(filterCount, modifyhistoryReq.getDraw(),list);
	}

	@Override
	public SupplementalContractModifyhistory findByModifystate(String modifyState,
			String orlId) {
		Criteria c = super.getCriteria();
		if(StringUtils.isNotBlank(modifyState)){
			c.add(Restrictions.eq("modifyState", modifyState));
		}
		if(StringUtils.isNotBlank(orlId)){
			c.add(Restrictions.eq("orlId", orlId));
		}
		int filterCount = this.countByCriteria(c);
			// 根据条件获取查询信息
		List<SupplementalContractModifyhistory> list = this.findByCriteria(c);
		if(list!=null && list.size()>0){
			return list.get(0);
		}
		return null;
	}


}
