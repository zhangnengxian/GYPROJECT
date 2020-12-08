package cc.dfsoft.project.biz.base.subpackage.converter;

import cc.dfsoft.project.biz.base.plan.entity.ConstructionPlan;
import cc.dfsoft.project.biz.base.subpackage.dto.SubContractDTO;

/**
 * 数据模型转换器
 *
 */
public final class SubContractConverter {

	/**
     * 禁用构造函数
     */
    private SubContractConverter() {
        //禁用构造函数
    }
    
    
    /**
     * 数据模型转换为页面模型
     * @param constructionPlan
     * @return
     */
    public static SubContractDTO convert(ConstructionPlan constructionPlan) {
        if (constructionPlan == null) {
            return null;
        }
        SubContractDTO dto = new SubContractDTO();
        dto.setProjId(constructionPlan.getProjId());
        dto.setProjNo(constructionPlan.getProjNo());
        dto.setProjName(constructionPlan.getProjName());
        dto.setProjAddr(constructionPlan.getProjAddr());
        dto.setProjScaleDes(constructionPlan.getProjScaleDes());
        dto.setCuId(constructionPlan.getCuId());
        dto.setCuName(constructionPlan.getCuName());
        dto.setDeptId("11");
        dto.setDeptName("贵州燃气集团股份有限公司");
        return dto;
    }
    
}
