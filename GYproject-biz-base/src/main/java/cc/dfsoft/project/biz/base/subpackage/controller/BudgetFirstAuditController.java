package cc.dfsoft.project.biz.base.subpackage.controller;

import cc.dfsoft.project.biz.base.baseinfo.entity.DocType;
import cc.dfsoft.project.biz.base.baseinfo.service.DocTypeService;
import cc.dfsoft.project.biz.base.charge.service.AccrualsRecordService;
import cc.dfsoft.project.biz.base.contract.entity.Contract;
import cc.dfsoft.project.biz.base.contract.entity.SupplementalContract;
import cc.dfsoft.project.biz.base.contract.service.ContractService;
import cc.dfsoft.project.biz.base.contract.service.SupplementalContractService;
import cc.dfsoft.project.biz.base.project.dao.ManageRecordDao;
import cc.dfsoft.project.biz.base.project.dto.ManageRecordQueryReq;
import cc.dfsoft.project.biz.base.project.dto.ProjectQueryReq;
import cc.dfsoft.project.biz.base.project.entity.ManageRecord;
import cc.dfsoft.project.biz.base.project.enums.ProjLtypeEnum;
import cc.dfsoft.project.biz.base.project.enums.ProjStatusEnum;
import cc.dfsoft.project.biz.base.project.enums.StepEnum;
import cc.dfsoft.project.biz.base.project.service.ManageRecordService;
import cc.dfsoft.project.biz.base.project.service.ProjectService;
import cc.dfsoft.project.biz.base.subpackage.dto.SubQuantitiesReq;
import cc.dfsoft.project.biz.base.subpackage.entity.IntelligentMeterContract;
import cc.dfsoft.project.biz.base.subpackage.enums.CostTypeEnum;
import cc.dfsoft.project.biz.base.subpackage.service.IntelligentMeterContractService;
import cc.dfsoft.project.biz.base.subpackage.service.SubBudgetService;
import cc.dfsoft.project.biz.base.subpackage.service.SubQuantitiesService;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.enums.WorkFlowActionEnum;
import cc.dfsoft.uexpress.common.util.SessionUtils;

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
 * 施工预算初审
 */
@Controller
@RequestMapping("/budgetFirstAudit")
public class BudgetFirstAuditController {
    /** 日志实例 */
    private static Logger logger= LoggerFactory.getLogger(BudgetFirstAuditController.class);

    /**工程服务接口*/
    @Resource
    ProjectService projectService;

    /**分包工程量服务接口*/
    @Resource
    SubQuantitiesService subQuantitiesService;

    /**施工预算服务接口*/
    @Resource
    SubBudgetService subBudgetService;

    @Resource
    ManageRecordService manageRecordService;

    /**单据类型服务接口*/
    @Resource
    DocTypeService docTypeService;
    @Resource
    ContractService contractService;
    @Resource
    SupplementalContractService supplementalContractService;
    @Resource
    IntelligentMeterContractService imContractService;
    @Resource
    AccrualsRecordService accrualsRecordService;


    /**
     * 打开主页面
     * @return
     */
    @RequestMapping(value="/main")
    public ModelAndView main(){
        ModelAndView modelView=new ModelAndView();
        modelView.addObject("projNo", SessionUtils.getProjNo());
        modelView.addObject("curStepId", StepEnum.QUALITIES_JUDGEMENT_FIRST.getValue());
        modelView.setViewName("subcontract/budgetFirstAudit");
        return modelView;
    }
    /**
     * 工程列表条件查询
     * @author
     * @param projectQueryReq
     * @createTime
     * @return Map<String,Object>
     */
    @RequestMapping(value = "/queryProject")
    @ResponseBody
    public Map<String,Object> queryProject(HttpServletRequest request, ProjectQueryReq projectQueryReq){
        try {
            projectQueryReq.setProjStatusId(ProjStatusEnum.TO_AUDIT_AMOUNT_FIRST.getValue());  //待工程量审定
            projectQueryReq.setSortInfo(request);
            Map<String,Object> map=projectService.queryAuditProject(projectQueryReq,"",StepEnum.QUALITIES_JUDGEMENT_FIRST.getValue());
            return map;
        } catch (Exception e) {
            logger.error("工程信息查询失败！", e);
            return null;
        }
    }

    /**
     * 弹出搜索屏
     * @return
     */
    @RequestMapping(value = "/projectSearchPopPage")
    public ModelAndView projectSearchPopPage() {
        ModelAndView modelview = new ModelAndView();
        modelview.addObject("projLtype", ProjLtypeEnum.values());
        modelview.setViewName("subcontract/budgetFirstSearchPopPage");
        return modelview;
    }

    /**
     * 打开审核页面
     * @return
     */
    @RequestMapping(value = "/auditPage")
    public ModelAndView viewAuditPage(HttpServletRequest request) {
        ModelAndView modelView = new ModelAndView();
        String projId = request.getParameter("projId");
        //根据工程id查设计信息
        modelView.addObject("projId",projId);
        modelView.addObject("subBudget", subBudgetService.viewSubBudget(projId));
        //查询安装合同
        Contract contract = contractService.findByProjId(projId);
        modelView.addObject("contract",contract);
        //查询补充协议
        List<SupplementalContract> supCons = supplementalContractService.findByProjId(projId);
        if(supCons!=null && supCons.size()>0){
            BigDecimal totalAmount = new BigDecimal(0);
            for(SupplementalContract supCon :supCons){
                totalAmount = totalAmount.add(supCon.getScAmount());
            }
            modelView.addObject("supConAmount",totalAmount.toString());
        }else{
            modelView.addObject("supConAmount","0");
        }
        //智能表合同款
        IntelligentMeterContract imc = imContractService.findContractByprojId(projId);
        if(imc!=null){
            modelView.addObject("imcAmount",imc.getTotalCost().toString());
        }else{
            modelView.addObject("imcAmount","0");
        }

        modelView.addObject("costType", CostTypeEnum.values());
        modelView.addObject("currentLevel",request.getParameter("currentLevel"));//当前审核级别
        modelView.addObject("isAudit",request.getParameter("isAudit"));			 //是否审核过
        modelView.addObject("auditTime",manageRecordService.getDatabaseDate().getTime());//当前数据库时间
        LoginInfo loginInfo = SessionUtil.getLoginInfo();
        modelView.addObject("loginInfo",loginInfo);											//登录人信息
        modelView.setViewName("subcontract/budgetFirstAuditPage");
        return modelView;
    }

    /**
     * 打开右侧页面修改
     * @return
     */
    @RequestMapping(value = "/viewPage")
    public ModelAndView viewPage() {
        ModelAndView modelview = new ModelAndView();
        modelview.setViewName("subcontract/budgetFirstAuditRight");
        return modelview;
    }

    /**
     * 工程量清单查询
     * @param request
     * @param subQuantitiesReq
     * @return
     */
    @RequestMapping(value = "/querySubQuantities")
    @ResponseBody
    public Map<String, Object> querySubQuantities(HttpServletRequest request,SubQuantitiesReq subQuantitiesReq) {
        try {
            subQuantitiesReq.setSortInfo(request);
            Map<String, Object> map=subQuantitiesService.queryQuantityStandard(subQuantitiesReq);
            return map;
        } catch (Exception e) {
            logger.error("分包单位工程量清单查询失败！", e);
            return null;
        }
    }

    /**
     * 审批历史
     * @param manageRecordQueryReq
     * @return
     */
    @RequestMapping(value = "/queryManageRecord")
    @ResponseBody
    public Map<String,Object> queryManageRecord(ManageRecordQueryReq manageRecordQueryReq){
        try {
            manageRecordQueryReq.setStepId(StepEnum.QUALITIES_JUDGEMENT_FIRST.getValue());
            return manageRecordService.queryManageRecord(manageRecordQueryReq);
        } catch (Exception e) {
            logger.error("管理记录列表查询失败！",e);
            return null;
        }
    }

    /**
     * 保存
     * @param manageRecord
     * @return
     */
    @RequestMapping(value = "/auditSave")
    @ResponseBody
    public  String firstAuditSave(@RequestBody(required = true) ManageRecord manageRecord){
        try  {
        	//先判断当前级别是否已审核，已审核则不能再次审核
        	ManageRecord manageRecordHistory =  manageRecordService.queryManRdHistory(manageRecord.getProjId(),manageRecord.getBusinessOrderId(),StepEnum.QUALITIES_JUDGEMENT_FIRST.getValue(),manageRecord.getMrAuditLevel(),"0");
        	if(manageRecordHistory != null){
        		return "exist";
        	}
            subBudgetService.firstAuditSave(manageRecord,"","", WorkFlowActionEnum.QUALITIES_DISPATCH_FIRST_AUDIT.getActionCode(), Constants.MODULE_CODE_SUBCONTRACT);
            return Constants.OPERATE_RESULT_SUCCESS;
        } catch (Exception e) {
            logger.error("工程量审定保存失败！",e);
            return Constants.OPERATE_RESULT_FAILURE;
        }
    }
}
