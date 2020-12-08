package cc.dfsoft.project.biz.base.subpackage.service.impl;


import java.text.ParseException;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cc.dfsoft.project.biz.base.contract.enums.ContractIsPrintEnum;
import cc.dfsoft.project.biz.base.project.service.OperateRecordService;
import cc.dfsoft.project.biz.base.subpackage.dao.SubSupplyContractDao;
import cc.dfsoft.project.biz.base.subpackage.dto.SubSupplyContractQueryReq;
import cc.dfsoft.project.biz.base.subpackage.entity.SubSupplyContract;
import cc.dfsoft.project.biz.base.subpackage.service.SubSupplyContractService;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.util.IDUtil;

@Service
@Transactional(readOnly = true,propagation=Propagation.REQUIRED)
public class SubSupplyContractServiceImpl implements SubSupplyContractService{

	/**管理记录服务接口*/
	@Resource
	OperateRecordService operateRecordService;
	
	@Resource
	SubSupplyContractDao SubSupplyContractDao;
	
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void saveSubSpplyContract(SubSupplyContract subSupplyContract) {
		if(StringUtils.isBlank(subSupplyContract.getSscId())){
			subSupplyContract.setSscId(IDUtil.getUniqueId(Constants.MODULE_CODE_SUBCONTRACT));
		}
		subSupplyContract.setIsPrint(ContractIsPrintEnum.HAVE_NOT_PRINT.getValue());//未打印
		subSupplyContract.setSscAmount(subSupplyContract.getEndDeclarationCost());
		SubSupplyContractDao.saveOrUpdate(subSupplyContract);
		/*//形成操作记录
		String orId = IDUtil.getUniqueId(Constants.MODULE_CODE_SUBCONTRACT);//生成唯一ID
		operateRecordService.createOperateRecord(orId, subSupplyContract.getProjId(), subSupplyContract.getProjNo(), StepEnum.SUB_CONTRACT.getValue(), StepEnum.SUB_CONTRACT.getMessage(), "");
		return null;*/
	}

	/**
	 * 分包协议列表条件查询
	 * @throws ParseException 
	 */
	@Override
	public Map<String, Object> querySubSupplyContract(SubSupplyContractQueryReq subSupplyContractQueryReq) throws ParseException {
		// TODO Auto-generated method stub
		return SubSupplyContractDao.querySubSupplyContract(subSupplyContractQueryReq);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void signSubAgreementPrint(String projId) {
		SubSupplyContract subSupplyContract=SubSupplyContractDao.findByProjId(projId);
		if(subSupplyContract!=null){
			//标记已打印
			subSupplyContract.setIsPrint(ContractIsPrintEnum.ALREADY_PRINT.getValue());
			SubSupplyContractDao.update(subSupplyContract);
		}
	}
	
}
