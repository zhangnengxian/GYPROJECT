package cc.dfsoft.project.biz.base.subpackage.service.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cc.dfsoft.project.biz.base.project.enums.ProjStatusEnum;
import cc.dfsoft.project.biz.base.subpackage.dao.SubSafeContractDao;
import cc.dfsoft.project.biz.base.subpackage.dto.SubSafeContractQueryReq;
import cc.dfsoft.project.biz.base.subpackage.entity.SubSafeContract;
import cc.dfsoft.project.biz.base.subpackage.service.SubSafeContractService;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;
import cc.dfsoft.uexpress.common.util.IDUtil;
@Service
@Transactional(readOnly = true,propagation=Propagation.REQUIRED)
public class SubSafeContractServiceImpl extends NewBaseDAO<SubSafeContract,String> implements SubSafeContractService{

	/**安全协议Dao*/
	@Resource
	SubSafeContractDao subSafeContractDao;
	
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public String saveSafeSubContract(SubSafeContract subSafeContract) {
		if(StringUtils.isBlank(subSafeContract.getSsId())){
			subSafeContract.setSsId(IDUtil.getUniqueId(Constants.MODULE_CODE_SUBCONTRACT));
		}
		subSafeContractDao.saveOrUpdate(subSafeContract);
		return Constants.OPERATE_RESULT_SUCCESS;
	}

	@Override
	public SubSafeContract findSubSafeContractByprojId(String projId) throws ParseException {
		return subSafeContractDao.findSubSafeContractByprojId(projId);
	}

	@Override
	public Map<String, Object> querySubSafeContract(SubSafeContractQueryReq subSafeContractQueryReq) throws ParseException{
		List<String> statusList=new ArrayList<String>();
		List<ProjStatusEnum> enums=ProjStatusEnum.getThanValue(ProjStatusEnum.TO_CONSTRUCTION.getValue(), ProjStatusEnum.ALREADY_COMPLETED.getValue());
		for(ProjStatusEnum projStatusEnum:enums){
			statusList.add(projStatusEnum.getValue());
		}
		subSafeContractQueryReq.setProjStuList(statusList);
		//分包合同审核之后添加
		return subSafeContractDao.querySubSafeContract(subSafeContractQueryReq);
	}
}
