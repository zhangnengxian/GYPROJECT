package cc.dfsoft.project.biz.base.subpackage.dao;

import cc.dfsoft.project.biz.base.subpackage.dto.CostApplyReq;
import cc.dfsoft.project.biz.base.subpackage.entity.CostApply;
import cc.dfsoft.uexpress.common.dao.CommonDao;

import java.text.ParseException;
import java.util.Map;

/**
 * 费用申请
 * @author cui
 *已作废
 */
public interface CostApplyDao extends CommonDao<CostApply,String>{

    /**
     * 费用申请列表
     * @author cui
     * @createTime 2017年12月8日
     * @param
     * @return
     */
    Map<String,Object> queryCostApply(CostApplyReq costApplyReq) throws ParseException;
}
