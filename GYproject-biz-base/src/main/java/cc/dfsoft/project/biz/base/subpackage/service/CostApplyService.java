package cc.dfsoft.project.biz.base.subpackage.service;

import cc.dfsoft.project.biz.base.subpackage.dto.CostApplyReq;

import java.text.ParseException;
import java.util.Map;

/**
 * 
 * @author cui
 *已作废
 */
public interface CostApplyService {

    /**
     * 请费用申请列表
     * @author cui on 2017-12-8
     * @param costApplyReq
     * @return
     */
    Map<String,Object> queryCostApply(CostApplyReq costApplyReq) throws ParseException;
}
