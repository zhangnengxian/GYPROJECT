package cc.dfsoft.project.biz.base.contract.controller;

import cc.dfsoft.project.biz.base.charge.entity.AccrualsRecord;
import cc.dfsoft.project.biz.base.charge.entity.CashFlow;
import cc.dfsoft.project.biz.base.charge.enums.ARFlagEnum;
import cc.dfsoft.project.biz.base.charge.service.AccrualsRecordService;
import cc.dfsoft.project.biz.base.charge.service.ChargeService;
import cc.dfsoft.project.biz.base.contract.entity.Contract;
import cc.dfsoft.project.biz.base.contract.service.ContractService;
import cc.dfsoft.project.biz.base.project.dto.ProjectQueryReq;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.project.enums.ProjStatusEnum;
import cc.dfsoft.project.biz.base.project.enums.StepEnum;
import cc.dfsoft.project.biz.base.project.service.ProjectService;

import cc.dfsoft.uexpress.common.constant.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 收款确认
 * @author fuliwei
 * @date 2019/5/22
*/
@Controller
@RequestMapping(value="/chargeConfirm")
public class ChargeConfirmController {
    /** 日志实例 */
    private static Logger logger= LoggerFactory.getLogger(ChargeConfirmController.class);

    /**工程服务接口*/
    @Resource
    ProjectService projectService;

    @Resource
    ChargeService chargeService;


    @Resource
    ContractService contractService;
    @Resource
    AccrualsRecordService accrualsRecordService;
    /**
     * 主页面
     * @author fuliwei
     * @date 2019/5/22
    */
    @RequestMapping(value="/main")
    public ModelAndView main(){
        ModelAndView modelView=new ModelAndView();
        modelView.setViewName("contract/chargeConfirm");
        return modelView;
    }

    /**
     * 收费确认
     * @author fuliwei
     * @date 2019/5/22
     * @param
     * @return
    */
    @RequestMapping(value="/viewPage")
    public ModelAndView viewPage(){
        ModelAndView modelView=new ModelAndView();
        modelView.setViewName("contract/chargeConfirmRight");
        return modelView;
    }

    /**
     * 弹出搜索
     * @author fuliwei
     * @date 2019/5/22
    */
    @RequestMapping(value = "/constractSearchPopPage")
    public ModelAndView projectSearchPopPage() {
        ModelAndView modelview = new ModelAndView();
        modelview.setViewName("contract/constractSearchPopPage");
        return modelview;
    }

    @RequestMapping(value = "/queryProject")
    @ResponseBody
    public Map<String,Object> queryProject(HttpServletRequest request, ProjectQueryReq projectQueryReq){
        try {
            projectQueryReq.setProjStatusId(ProjStatusEnum.TO_CONFIRM_CHARGE.getValue());//待收款登记
            projectQueryReq.setSortInfo(request);
            projectQueryReq.setStepId(StepEnum.CONFIRM_CHARGE.getValue());
            Map<String,Object> map=projectService.queryProjectByTime(projectQueryReq);
            return map;
        } catch (Exception e) {
            logger.error("工程信息查询失败！", e);
            return null;
        }
    }

    @RequestMapping(value="/viewChargeConfirm")
    @ResponseBody
    public Contract viewChargeConfirm(String id){
        Project pro=projectService.viewProject(id);
        Contract contract = contractService.findByProjId(id);
        contract.setProjectTypeDes(pro.getProjectTypeDes());//工程类型
        contract.setContributionModeDes(pro.getContributionModeDes());//出资方式
        contract.setDeptName(pro.getDeptName());//业务部门
        contract.setSurveyer(pro.getSurveyer());//勘察员
        contract.setProjectType(pro.getProjectType());  //工程类型
        //实收款、实付款
        String cashFlowRemark="已收款：";
        List<CashFlow> cashFlows = chargeService.queryCashFlowByProjIdType(pro.getProjId(), ARFlagEnum.RECEIVE_ACCOUNT.getValue(),contract.getConNo());
        if(cashFlows!=null){
            contract.setCashFlows(cashFlows);
            BigDecimal receiveAmount = new BigDecimal(0);
            for (CashFlow cashFlow : cashFlows) {
                receiveAmount = receiveAmount.add(cashFlow.getCfAmount());//实收金额
            }
            cashFlowRemark += receiveAmount +"元";
        }
        //应收款、预付款
        List<AccrualsRecord> accRecords = accrualsRecordService.findbyProjIdType(pro.getProjId(), pro.getProjNo(), ARFlagEnum.RECEIVE_ACCOUNT.getValue(),contract.getConNo());
        if(accRecords!=null){
            contract.setAccRecords(accRecords);
        }
        contract.setCashFlowRemark(cashFlowRemark);
        return contract;
    }


    @RequestMapping(value = "/saveChargeConfirm")
    @ResponseBody
    public String saveChargeConfirm(@RequestBody(required = true) Contract contract){
        try {
            return contractService.saveChargeConfirm(contract);
        } catch (Exception e) {
            logger.error("确认收款保存失败！", e);
            return Constants.OPERATE_RESULT_FAILURE;
        }
    }




}
