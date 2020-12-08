package cc.dfsoft.project.biz.base.baseinfo.service.impl;

import cc.dfsoft.project.biz.base.baseinfo.dao.BankDao;
import cc.dfsoft.project.biz.base.baseinfo.dto.BankQueryReq;
import cc.dfsoft.project.biz.base.baseinfo.entity.AccountBank;
import cc.dfsoft.project.biz.base.baseinfo.service.BankService;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.util.IDUtil;
import cc.dfsoft.uexpress.common.util.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Map;

/**
 * @author cui
 * @createTime 2017-11-1
 */
@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class BankServiceImpl implements BankService {

	@Resource
	BankDao bankDao;

	@Override
	public Map<String, Object> queryBank(BankQueryReq bankQueryReq) {
		return bankDao.queryBank(bankQueryReq);
	}

	@Override
	public AccountBank viewOpenBankById(String id) {
		return bankDao.get(id);
	}

	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public String saveOpenBank(AccountBank accountBank) {
		AccountBank old = bankDao.get("bankNo",accountBank.getBankNo());
		if(null==old){
			if(StringUtils.isBlank(accountBank.getAbId())){
				accountBank.setAbId(IDUtil.getUniqueId(Constants.BASIS));//生成主键ID "基础"下的菜单
			}
			bankDao.saveOrUpdate(accountBank);
			return "success";
		}else{
			return "exist";
		}
	}

	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void deleteOpenBank(String id) {
		bankDao.delete(id);
	}





	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public boolean saveOrUpdateBankAccount(AccountBank accountBank) {
		if(StringUtil.isBlank(accountBank.getAbId())){
			accountBank.setAbId(IDUtil.getUniqueId(Constants.BASIS));//生成主键ID "基础"下的菜单
			accountBank.setBankDate(new Date());
		}

		try {
			bankDao.saveOrUpdate(accountBank);
			return true;
		}catch (Exception e){
			e.printStackTrace();
			return false;
		}
	}

}
