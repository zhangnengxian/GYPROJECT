package cc.dfsoft.project.biz.base.messagesync.service.impl;

import cc.dfsoft.project.biz.base.complete.entity.JointAcceptance;
import cc.dfsoft.project.biz.base.complete.entity.Preinspection;
import cc.dfsoft.project.biz.base.complete.entity.SelfInspectionList;
import cc.dfsoft.project.biz.base.complete.service.JointAcceptanceService;
import cc.dfsoft.project.biz.base.complete.service.PreinspectionService;
import cc.dfsoft.project.biz.base.complete.service.SelfInspectionListService;
import cc.dfsoft.project.biz.base.constructmanage.dao.ConstructionWorkDao;
import cc.dfsoft.project.biz.base.constructmanage.entity.ConstructionWork;
import cc.dfsoft.project.biz.base.constructmanage.entity.WorkReport;
import cc.dfsoft.project.biz.base.constructmanage.service.WorkReportService;
import cc.dfsoft.project.biz.base.contract.entity.Contract;
import cc.dfsoft.project.biz.base.contract.service.ContractService;
import cc.dfsoft.project.biz.base.design.entity.DesignInfo;
import cc.dfsoft.project.biz.base.design.service.DesignInfoService;
import cc.dfsoft.project.biz.base.messagesync.hongju.pojo.*;
import cc.dfsoft.project.biz.base.messagesync.pojo.ResultMsg;
import cc.dfsoft.project.biz.base.messagesync.utils.HttpClientService;
import cc.dfsoft.project.biz.base.plan.entity.ConstructionPlan;
import cc.dfsoft.project.biz.base.plan.service.ConstructionPlanService;
import cc.dfsoft.project.biz.base.project.dao.ProjectDao;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.project.entity.Signature;
import cc.dfsoft.project.biz.base.project.service.SignatureService;
import cc.dfsoft.project.biz.base.messagesync.service.SynchronizedService;
import cc.dfsoft.project.biz.base.settlement.entity.SettlementDeclaration;
import cc.dfsoft.project.biz.base.settlement.service.SettlementDeclarationService;
import cc.dfsoft.project.biz.base.subpackage.entity.SubBudget;
import cc.dfsoft.project.biz.base.subpackage.entity.SubContract;
import cc.dfsoft.project.biz.base.subpackage.service.SubBudgetService;
import cc.dfsoft.project.biz.base.subpackage.service.SubContractService;
import cc.dfsoft.project.biz.ifs.ResultMessage;
import cc.dfsoft.project.biz.ifs.log.dao.WebServiceSetDao;
import cc.dfsoft.project.biz.ifs.log.entity.WebServiceSet;
import cc.dfsoft.project.biz.ifs.log.service.WebserviceLogService;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.util.BeanUtil;
import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util.*;

/**
* @Description: 接口处理
* @author zhangnx
* @date 2019/11/22 10:28
*/
@Service
@Transactional(readOnly =true ,propagation= Propagation.REQUIRED)
public class SynchronizedServiceImpl implements SynchronizedService {

    @Resource
    WebServiceSetDao webServiceSetDao;
    @Resource
    ProjectDao projectDao;
    @Resource
    ContractService contractService;
    @Resource
    DesignInfoService designInfoService;
    @Resource
    ConstructionPlanService constructionPlanService;
    @Resource
    ConstructionWorkDao constructionWorkDao;
    @Resource
    SettlementDeclarationService settlementDeclarationService;
    @Resource
    SignatureService signatureService;
    @Resource
    SubBudgetService subBudgetService;
    @Resource
    SubContractService subContractService;
    @Resource
    WorkReportService workReportService;
    @Resource
    SelfInspectionListService selfInspectionListService;
    @Resource
    PreinspectionService preinspectionService;
    @Resource
    JointAcceptanceService jointAcceptanceService;
    @Resource
    WebserviceLogService webserviceLogService;


    private final int successCode=0;
    /**
    * @Description: 同步施工任务单信息（鸿巨）
    * @author zhangnx
    * @param
    * @date 2019/11/28 11:25
    **/
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED,rollbackFor = {Exception.class})
    public ResultMsg callSynchronizedConstructionTask(String projId, String interfaceNo) {

        Project project = projectDao.get(projId);
        if (project==null){return null;}

        String serverUrl = isCall(interfaceNo, project.getCorpId());

        //不是鸿巨公司的工程或者接口未打开时不调用
        if (!isHJCall(project.getCuId()) || StringUtils.isBlank(serverUrl)){
            return new ResultMsg(successCode,"不是鸿巨工程或接口未开启！");
        }

        ProjectInfo projectInfo = getProjectInfo(project.getProjId());
        ConstructionInfo constructionInfo = getConstructionInfo(project.getProjId());

        Map map=new HashMap<String,Object>();
        map.put("operate_type",interfaceNo);//接口编号
        map.put("project",projectInfo);//工程基本信息
        map.put("constructionPlan",constructionInfo);//施工计划信息

        //远程调用返回信息
        String url=Constants.HONGJU_HOST+serverUrl;
        ResultMsg resultMsg = HttpClientService.doPost(url, map, false);

        //保存日志
        webserviceLogService.saveSynchronizedLog(projectInfo.getProjNo(),interfaceNo,resultMsg,map,url);

       return result(resultMsg);
    }





    /**
     * @Description: 同步交底信息（鸿巨）
     * @author zhangnx
     * @param  projId 工程ID
     * @param  cwId 交底ID
     * @date 2019/11/28 13:49
     **/
    @Override
    public ResultMsg callSynchronizedConstructionWork(String projId,String cwId,String interfaceNo) {
        Project project = projectDao.get(projId);
        if (project==null) {return null;}

        String serverUrl = isCall(interfaceNo, project.getCorpId());

        //不是鸿巨公司的工程或者接口未打开时不调用
        if (!isHJCall(project.getCuId()) || StringUtils.isBlank(serverUrl)){
            return new ResultMsg(successCode,"不是鸿巨工程或接口未开启！");
        }
        if (StringUtils.isNotBlank(cwId)){
            ResultMsg resultMsg = constructionWorkCall(cwId, interfaceNo, serverUrl);
            return result(resultMsg);
        }


        ResultMsg resultMsg=new ResultMsg();
        List<ConstructionWork> constructionWorkList = constructionWorkDao.findByProjId(projId);
        if (constructionWorkList!=null && constructionWorkList.size()>0){
            for (ConstructionWork cw:constructionWorkList) {
                 resultMsg = constructionWorkCall(cw.getCwId(), interfaceNo, serverUrl);
            }
        }
        return result(resultMsg);
    }



    private ResultMsg constructionWorkCall(String cwId,String interfaceNo,String serverUrl){
        ConstructionWorkInfo constructionWorkInfo = getConstructionWorkInfo(cwId);
        List<SignInfo> signInfoList=null;
        if (constructionWorkInfo!=null){
            signInfoList = getSignInfoList(constructionWorkInfo.getCwId());
        }

        Map map=new HashMap<String,Object>();
        map.put("operate_type",interfaceNo);//接口编号
        map.put("constructionWork",constructionWorkInfo);//交底信息
        map.put("sign",signInfoList);//签字信息

        //远程调用返回信息
        String url=Constants.HONGJU_HOST+serverUrl;
        ResultMsg resultMsg = HttpClientService.doPost(url, map, false);

        //保存日志
        webserviceLogService.saveSynchronizedLog(constructionWorkInfo.getProjNo(),interfaceNo,resultMsg,map,url);

        return resultMsg;
    }




    /**
    * @Description: 同步开工报告信息（鸿巨）
    * @author zhangnx
    * @param projId 工程ID
    * @date 2019/11/28 13:25
    **/
    @Override
    public ResultMsg callSynchronizedWorkReport(String projId,String interfaceNo) {
        Project project = projectDao.get(projId);
        if (project==null) {return null;}

        String serverUrl = isCall(interfaceNo, project.getCorpId());
        //不是鸿巨公司的工程或者接口未打开时不调用
        if (!isHJCall(project.getCuId()) || StringUtils.isBlank(serverUrl)){
            return new ResultMsg(successCode,"不是鸿巨工程或接口未开启！");
        }

        WorkReportInfo workReportInfo = getWorkReportInfo(projId);
        List<SignInfo> signInfoList =null;
        if (workReportInfo!=null){
            signInfoList = getSignInfoList(workReportInfo.getWrId());
        }
        Map map=new HashMap<String,Object>();
        map.put("operate_type",interfaceNo);//接口编号
        map.put("workReport",workReportInfo);//开工报告信息
        map.put("sign",signInfoList);//签字信息

        //远程调用返回信息
        String url=Constants.HONGJU_HOST+serverUrl;
        ResultMsg resultMsg = HttpClientService.doPost(url, map, false);

        //保存日志
        webserviceLogService.saveSynchronizedLog(workReportInfo.getProjNo(),interfaceNo,resultMsg,map,url);

        return result(resultMsg);
    }



    /**
     * @Description: 同步预结算信息（鸿巨）
     * @author zhangnx
     * @param projId 工程ID
     * @date 2019/11/28 13:50
     **/
    @Override
    public ResultMsg callSynchronizedPreSettlement(String projId,String interfaceNo) {
        Project project = projectDao.get(projId);
        if (project==null) {return null;}

        String serverUrl = isCall(interfaceNo, project.getCorpId());
        //不是鸿巨公司的工程或者接口未打开时不调用
        if (!isHJCall(project.getCuId()) || StringUtils.isBlank(serverUrl)){
            return new ResultMsg(successCode,"不是鸿巨工程或接口未开启！");
        }

        SubBudgetInfo subBudgetInfo = getSubBudgetInfo(projId);
        SettlementDeclarationInfo settlementDeclarationInfo = getSettlementDeclarationInfo(projId);

        Map map=new HashMap<String,Object>();
        map.put("operate_type",interfaceNo);//接口编号
        map.put("subBudget",subBudgetInfo);//预算信息
        map.put("settlementDeclaration",settlementDeclarationInfo);//结算信息

        //远程调用返回信息
        String url=Constants.HONGJU_HOST+serverUrl;
        ResultMsg resultMsg = HttpClientService.doPost(url, map, false);

        //保存日志
        webserviceLogService.saveSynchronizedLog(subBudgetInfo.getProjNo(),interfaceNo,resultMsg,map,url);

        return result(resultMsg);
    }



    /**
     * @Description: 同步施工合同信息（鸿巨）
     * @author zhangnx
     * @param  projId 工程ID
     * @date 2019/11/28 13:53
     **/
    @Override
    public ResultMsg callSynchronizedSubContract(String projId,String interfaceNo) {
        Project project = projectDao.get(projId);
        if (project==null) {return null;}

        String serverUrl = isCall(interfaceNo, project.getCorpId());
        //不是鸿巨公司的工程或者接口未打开时不调用
        if (!isHJCall(project.getCuId()) || StringUtils.isBlank(serverUrl)){
            return new ResultMsg(successCode,"不是鸿巨工程或接口未开启！");
        }

        SubContractInfo subContractInfo = getSubContractInfo(projId);
        Map map=new HashMap<String,Object>();
        map.put("operate_type",interfaceNo);//接口编号
        map.put("subContract",subContractInfo);//施工合同信息

        //远程调用返回信息
        String url=Constants.HONGJU_HOST+serverUrl;
        ResultMsg resultMsg = HttpClientService.doPost(url, map, false);

        //保存日志
        webserviceLogService.saveSynchronizedLog(subContractInfo.getProjNo(),interfaceNo,resultMsg,map,url);

        return result(resultMsg);
    }



    /**
     * @Description: 同步自检信息（鸿巨）
     * @author zhangnx
     * @param projId 工程ID
     * @date 2019/11/28 13:56
     **/
    @Override
    public ResultMsg callSynchronizedSelfCheck(String projId,String interfaceNo) {
        Project project = projectDao.get(projId);
        if (project==null) {return null;}

        String serverUrl = isCall(interfaceNo, project.getCorpId());
        //不是鸿巨公司的工程或者接口未打开时不调用
        if (!isHJCall(project.getCuId()) || StringUtils.isBlank(serverUrl)){
            return new ResultMsg(successCode,"不是鸿巨工程或接口未开启！");
        }

        SelfInspectionListInfo selfInspectionListInfo = getSelfInspectionListInfo(projId);
        List<SignInfo> signInfoList = getSignInfoList(selfInspectionListInfo.getSilId());

        Map map=new HashMap<String,Object>();
        map.put("operate_type",interfaceNo);//接口编号
        map.put("selfInspectionRecord",selfInspectionListInfo);//自检信息
        map.put("sign",signInfoList);//签字信息
        //远程调用返回信息
        String url=Constants.HONGJU_HOST+serverUrl;
        ResultMsg resultMsg = HttpClientService.doPost(url, map, false);

        //保存日志
        webserviceLogService.saveSynchronizedLog(selfInspectionListInfo.getProjNo(),interfaceNo,resultMsg,map,url);

        return result(resultMsg);
    }



    /**
     * @Description: 同步预验收信息（鸿巨）
     * @author zhangnx
     * @param projId 工程ID
     * @date 2019/11/28 14:54
     **/
    @Override
    public ResultMsg callSynchronizedPreinspection(String projId,String interfaceNo) {
        Project project = projectDao.get(projId);
        if (project==null) {return null;}

        String serverUrl = isCall(interfaceNo, project.getCorpId());
        //不是鸿巨公司的工程或者接口未打开时不调用
        if (!isHJCall(project.getCuId()) || StringUtils.isBlank(serverUrl)){
            return new ResultMsg(successCode,"不是鸿巨工程或接口未开启！");
        }

        PreinspectionInfo preinspectionInfo = getPreinspectionInfo(projId);
        List<SignInfo> signInfoList = getSignInfoList(preinspectionInfo.getPiId());

        Map map=new HashMap<String,Object>();
        map.put("operate_type",interfaceNo);//接口编号
        map.put("selfInspectionRecord",preinspectionInfo);//预验收信息
        map.put("sign",signInfoList);//签字信息

        //远程调用返回信息
        String url=Constants.HONGJU_HOST+serverUrl;
        ResultMsg resultMsg = HttpClientService.doPost(url, map, false);

        //保存日志
        webserviceLogService.saveSynchronizedLog(preinspectionInfo.getProjNo(),interfaceNo,resultMsg,map,url);
        return result(resultMsg);
    }





    /**
     * @Description: 同步联合验收信息（鸿巨）
     * @author zhangnx
     * @param  projId 工程ID
     * @date 2019/11/28 14:54
     **/
    @Override
    public ResultMsg callSynchronizedJointAcceptance(String projId,String interfaceNo) {
        Project project = projectDao.get(projId);
        if (project==null) {return null;}

        String serverUrl = isCall(interfaceNo, project.getCorpId());
        //不是鸿巨公司的工程或者接口未打开时不调用
        if (!isHJCall(project.getCuId()) || StringUtils.isBlank(serverUrl)){
            return new ResultMsg(successCode,"不是鸿巨工程或接口未开启！");
        }

        JointAcceptanceInfo jointAcceptanceInfo = getJointAcceptanceInfo(projId);
        List<SignInfo> signInfoList = getSignInfoList(jointAcceptanceInfo.getJaId());

        Map map=new HashMap<String,Object>();
        map.put("operate_type",interfaceNo);//接口编号
        map.put("selfInspectionRecord",jointAcceptanceInfo);//联合验收信息
        map.put("sign",signInfoList);//签字信息

        //远程调用返回信息
        String url=Constants.HONGJU_HOST+serverUrl;
        ResultMsg resultMsg = HttpClientService.doPost(url, map, false);

        //保存日志
        webserviceLogService.saveSynchronizedLog(jointAcceptanceInfo.getProjNo(),interfaceNo,resultMsg,map,url);
        return result(resultMsg);
    }




    private ResultMsg result(ResultMsg resultMsg){
        //状态吗对照cc/dfsoft/project/biz/base/messagesync/statusCode.txt文件
        ResultMsg result=new ResultMsg();
        resultMsg.setCode(resultMsg.getCode());

        if (resultMsg.getCode()<200){
            result.setMsg("临时响应!");
        }else if (resultMsg.getCode()<300){
            if (webserviceLogService.isJson(resultMsg.getMsg())){
                Map mapTypes = JSON.parseObject(resultMsg.getMsg());
                if (mapTypes!=null && !mapTypes.isEmpty()){
                    resultMsg.setCode(Integer.valueOf(mapTypes.get("ret_type")+""));
                    result.setMsg(mapTypes.get("ret_message")+"");
                }
            }
        }else if (resultMsg.getCode()<400){
            result.setMsg("重定向错误，请刷新页面重试!");
        }else if (resultMsg.getCode()<500){
            result.setMsg("请求错误!");
        }else if (resultMsg.getCode()<600){
            result.setMsg("服务器错误!");
        }
        return result;
    }


    /**
    * @ Description: 施工任务信息
    * @ author zhangnx
    * @ date 2019/11/25 9:44
    **/
    private ConstructionInfo getConstructionInfo(String projId){
        ConstructionInfo constructionInfo=new ConstructionInfo();
        ConstructionPlan constructionPlan = constructionPlanService.findByProjId(projId);
        if (constructionPlan!=null) {
            BeanUtil.copyNotNullProperties(constructionInfo,constructionPlan);
        }
        Contract contract = contractService.findByProjId(projId);
        if (contract!=null){
            constructionInfo.setConNo(contract.getConNo());
            constructionInfo.setHousehold(contract.getHousehold());
        }
        DesignInfo designInfo = designInfoService.queryById(projId);
        if (designInfo!=null){
            constructionInfo.setDesignDrawingNo(designInfo.getDesignDrawingNo());
        }
        return constructionInfo;
    }





    /**
    * @ Description: 工程基本信息
    * @ author zhangnx
    * @ date 2019/11/25 9:38
    **/
    private ProjectInfo getProjectInfo(String projId){
        ProjectInfo projectInfo=new ProjectInfo();
        Project project = projectDao.get(projId);
        if (project!=null) {
            BeanUtil.copyNotNullProperties(projectInfo,project);
        }
        return projectInfo;
    }


    /**
    * @ Description: 交底信息
    * @ author zhangnx
    * @ date 2019/11/25 9:54
    **/
    private ConstructionWorkInfo getConstructionWorkInfo(String cwId) {
        ConstructionWorkInfo constructionWorkInfo=new ConstructionWorkInfo();
        ConstructionWork constructionWork = constructionWorkDao.get(cwId);
        if (constructionWork!=null){
            BeanUtil.copyNotNullProperties(constructionWorkInfo,constructionWork);
        }
        return constructionWorkInfo;
    }




    /**
    * @ Description: 获取签字信息
    * @ author zhangnx
    * @ date 2019/11/25 10:10
    **/
    private List<SignInfo> getSignInfoList(String busId) {
        List<SignInfo> signInfoList=new ArrayList<>();
        List<Signature> signatureList = signatureService.findListByBusId(busId);
        if (signatureList!=null && signatureList.size()>0){
            for (Signature s:signatureList) {
                SignInfo signInfo=new SignInfo();
                BeanUtil.copyNotNullProperties(signInfo,s);
                signInfoList.add(signInfo);
            }
        }
        return signInfoList;
    }


    /**
    * @ Description: 施工合同信息
    * @ author zhangnx
    * @ date 2019/11/25 10:59
    **/

    private SubContractInfo getSubContractInfo(String projId) {
        try {
            SubContractInfo subContractInfo=new SubContractInfo();
            SubContract subContract = subContractService.findSubContractByprojId(projId);
            if (subContract!=null){
                BeanUtil.copyNotNullProperties(subContractInfo,subContract);
                return subContractInfo;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }



    /**
    * @ Description: 开工报告信息
    * @ author zhangnx
    * @ date 2019/11/25 11:06
    **/

    private WorkReportInfo getWorkReportInfo(String projId) {
        WorkReportInfo workReportInfo=new WorkReportInfo();
        WorkReport workReport = workReportService.findByProjId(projId);
        if (workReport!=null){
            BeanUtil.copyNotNullProperties(workReportInfo,workReport);
        }
        return workReportInfo;
    }



    /**
    * @ Description: 施工预算信息
    * @ author zhangnx
    * @ date 2019/11/25 11:39
    **/
    private SubBudgetInfo getSubBudgetInfo(String projId) {
        SubBudgetInfo subBudgetInfo=new SubBudgetInfo();
        SubBudget subBudget = subBudgetService.findSubBudgetByProjID(projId);
        if (subBudget!=null){
            BeanUtil.copyNotNullProperties(subBudgetInfo,subBudget);
        }
        return subBudgetInfo;
    }



    /**
    * @ Description: 结算信息
    * @ author zhangnx
    * @ date 2019/11/25 11:39
    **/

    private SettlementDeclarationInfo getSettlementDeclarationInfo(String projId) {
        SettlementDeclarationInfo settlementDeclarationInfo=new SettlementDeclarationInfo();
        SettlementDeclaration settlementDeclaration = settlementDeclarationService.getSettlementDeclaration(projId);
        if (settlementDeclaration!=null){
            BeanUtil.copyNotNullProperties(settlementDeclarationInfo,settlementDeclaration);
        }
        return settlementDeclarationInfo;
    }



    /**
    * @Description: 自检信息
    * @author zhangnx
    * @param projId 工程ID
    * @date 2019/11/28 14:48
    **/
    private SelfInspectionListInfo getSelfInspectionListInfo(String projId) {
        SelfInspectionListInfo selfInspectionListInfo=new SelfInspectionListInfo();
        try {
            SelfInspectionList selfInspectionList = selfInspectionListService.viewSelfInspectionList(projId);
            if (selfInspectionList != null) {//自检项
                BeanUtil.copyNotNullProperties(selfInspectionListInfo, selfInspectionList);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return selfInspectionListInfo;
    }


    /**
    * @Description: 功能描述
    * @author zhangnx
    * @param projId 工程ID
    * @date 2019/11/28 14:50
    **/
    private PreinspectionInfo getPreinspectionInfo(String projId) {
        PreinspectionInfo preinspectionInfo=new PreinspectionInfo();
        try {
            Preinspection preinspection=preinspectionService.findByProjId(projId);
            if (preinspection!=null){//预验收
                BeanUtil.copyNotNullProperties(preinspectionInfo,preinspection);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return preinspectionInfo;
    }


    /**
    * @ Description: 验收信息
    * @ author zhangnx
    * @ date 2019/11/25 14:01
    **/
    private JointAcceptanceInfo getJointAcceptanceInfo(String projId) {
        JointAcceptanceInfo jointAcceptanceInfo=new JointAcceptanceInfo();
        JointAcceptance jointAcceptance=jointAcceptanceService.findByProjId(projId);
        if (jointAcceptance!=null){//联合验收
            BeanUtil.copyNotNullProperties(jointAcceptanceInfo,jointAcceptance);
        }

        return jointAcceptanceInfo;

    }






    /**
   * @ Description: （是否鸿巨）并且（是否调用接口）
   * @ author zhangnx
   * @ date 2019/11/25 9:21
   **/

    private boolean isHJCall(String cuId){
        if (Constants.HONGJU_ID.equals(cuId)){
            return true;
        }
        return false;
    }



    /**
    * @ Description: 是否调用接口
    * @ author zhangnx
    * @ date 2019/11/22 10:55
    **/

    private String isCall(String interfaceNo,String corpId){
        WebServiceSet webServiceSet = webServiceSetDao.queryWebServiceSet(interfaceNo, corpId);
        if (webServiceSet==null){
            webServiceSet=webServiceSetDao.queryWebServiceSet(interfaceNo, Constants.CORP_ID);
        }

        if (webServiceSet!=null && "1".equals(webServiceSet.getWebServiceFlag())){
            return webServiceSet.getServerUrl();
        }
        return "";
    }





}
