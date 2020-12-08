package cc.dfsoft.project.biz.base.baseinfo.dao.impl;

import cc.dfsoft.project.biz.base.baseinfo.dao.BankDao;
import cc.dfsoft.project.biz.base.baseinfo.dto.BankQueryReq;
import cc.dfsoft.project.biz.base.baseinfo.entity.AccountBank;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;
import cc.dfsoft.uexpress.common.util.ResultUtil;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class BankDaoImpl extends NewBaseDAO<AccountBank,String> implements BankDao {

	@Override
	public Map<String, Object> queryBank(BankQueryReq bankQueryReq) {
		
		 Criteria c = super.getCriteria();
		 //开户行
		 if(StringUtils.isNotBlank(bankQueryReq.getBankAdress())){
			 c.add(Restrictions.like("bankAdress","%"+bankQueryReq.getBankAdress()+"%"));
		 }
		//开户名称
		if(StringUtils.isNotBlank(bankQueryReq.getBankName())){
			c.add(Restrictions.like("bankName","%"+bankQueryReq.getBankName()+"%"));
		}
		 if (StringUtils.isNotBlank(bankQueryReq.getCorpId())){
		 	c.add(Restrictions.like("corpId","%"+bankQueryReq.getCorpId()+"%"));
		 }
		if (StringUtils.isNotBlank(bankQueryReq.getBankNo())){
			c.add(Restrictions.eq("bankNo",bankQueryReq.getBankNo()));
		}
		// 数据库根据条件过滤记录数
		int filterCount = this.countByCriteria(c);

		// 根据条件获取查询信息
		List<AccountBank> bankList = this.findBySortCriteria(c, bankQueryReq);
		
		// 返回结果
		return ResultUtil.pageResult( filterCount, bankQueryReq.getDraw(),bankList);
	}
	
}
