package cc.dfsoft.project.biz.base.plan.converter;

import cc.dfsoft.project.biz.base.contract.entity.Contract;
import cc.dfsoft.project.biz.base.plan.dto.ConstructionPlanDTO;

public class ConstructionPlanDTOConverter {

	/**
     * 禁用构造函数
     */
    private ConstructionPlanDTOConverter() {
        //禁用构造函数
    }
    
    /**
     * 数据模型转换为页面模型
     * @param contract
     * @return
     */
    public static ConstructionPlanDTO convert(Contract contract) {
        if (contract == null) {
            return null;
        }
        ConstructionPlanDTO dto = new ConstructionPlanDTO();
        dto.setProjId(contract.getProjId());
        dto.setProjNo(contract.getProjNo());
        dto.setProjName(contract.getProjName());
        dto.setProjAddr(contract.getProjAddr());
        dto.setProjScaleDes(contract.getProjScaleDes());
        dto.setContractAmount(contract.getContractAmount());
        dto.setDownPayment(contract.getFirstPayment());
        return dto;
    }
    

}
