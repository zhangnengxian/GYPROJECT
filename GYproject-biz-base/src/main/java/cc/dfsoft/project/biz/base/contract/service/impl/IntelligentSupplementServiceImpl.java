package cc.dfsoft.project.biz.base.contract.service.impl;

import cc.dfsoft.project.biz.base.baseinfo.service.DocTypeService;
import cc.dfsoft.project.biz.base.constructmanage.enums.StepOutWorkflowEnum;
import cc.dfsoft.project.biz.base.contract.dao.IntelligentSupplementDao;
import cc.dfsoft.project.biz.base.contract.dto.IntelligentSupplementReq;
import cc.dfsoft.project.biz.base.contract.entity.IntelligentSupplement;
import cc.dfsoft.project.biz.base.contract.service.IntelligentSupplementService;
import cc.dfsoft.project.biz.base.project.dao.ManageRecordDao;
import cc.dfsoft.project.biz.base.project.dao.ProjectDao;
import cc.dfsoft.project.biz.base.project.entity.ManageRecord;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.project.enums.AuditStatusEnum;
import cc.dfsoft.project.biz.base.project.enums.MrResultEnum;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.constant.DataFilerSetUpDto;
import cc.dfsoft.uexpress.common.util.BeanUtil;
import cc.dfsoft.uexpress.common.util.IDUtil;
import cc.dfsoft.uexpress.common.util.StringUtil;
import net.sf.json.JSONSerializer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Desc: 类描述
 * @Auther: zhangnx
 * @Date: 2019/7/26 18:11
 * @Version:1.0
 */
@Service
@Transactional(readOnly = true,propagation= Propagation.REQUIRED)
public class IntelligentSupplementServiceImpl implements IntelligentSupplementService {

    @Resource
    IntelligentSupplementDao intelligentSupplementDao;
    @Resource
    ProjectDao projectDao;
    @Resource
    DocTypeService docTypeService;
    @Resource
    ManageRecordDao manageRecordDao;


    @Override
    public Map<String, Object> queryIntelligentSupplementList(IntelligentSupplementReq req) {
        Map<String, Object> map = intelligentSupplementDao.queryIntelligentSupplementList(req);

        List<Map<String,Object>> mapList = (List<Map<String, Object>>) map.get("data");
        if (mapList!=null && mapList.size()>0){
            for (Map m:mapList) {
                if (null!=m.get("isStatus")) {
                    m.put("isStatusDes", AuditStatusEnum.getMessageByCode(m.get("isStatus").toString()));
                }
            }
        }
        return map;
    }





    @Override
    public Map<String, Object> findById(String id) {
        return intelligentSupplementDao.findById(id);
    }





    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED,rollbackFor = {Exception.class})
    public boolean saveOrUpdateintelligentSupplement(IntelligentSupplement intell) {

        boolean isIdBlank = StringUtil.isBlank(intell.getIsId());
        if (isIdBlank){//新增
            return saveSupplement(intell);
        }

        if ("5".equals(intell.getFlag())){//修改申请
          return modifyApply(intell);
        }

        if ("2".equals(intell.getFlag())){//推送时判断是否需要审核
            intell.setIsStatus(pushApply(intell));
        }

        if (AuditStatusEnum.PASS.getCode().equals(intell.getIsStatus())){//审核通过
            intell.setIsValid(auditApply(intell));//有效
        }


        IntelligentSupplement intelligentSupplement = intelligentSupplementDao.get(intell.getIsId());
        if (intelligentSupplement!=null) {
            BeanUtil.copyNotNullProperties(intelligentSupplement, intell);
            intelligentSupplementDao.saveOrUpdate(intelligentSupplement);
        }


        if (AuditStatusEnum.PASS.getCode().equals(intell.getIsStatus())){//审核通过时调用接口
            calInterface(intelligentSupplement.getIsId(),intelligentSupplement.getProjId());
        }

        return true;
    }



    public boolean modifyApply(IntelligentSupplement intell) {
        IntelligentSupplement is=new IntelligentSupplement();
        IntelligentSupplement isc = intelligentSupplementDao.get(intell.getIsId());

        BeanUtil.copyNotNullProperties(is, isc);
        is.setIsId(IDUtil.getUniqueId(Constants.MODULE_CODE_CONTRACT));
        is.setIsStatus(AuditStatusEnum.TO_MODIFY.getCode());//待修改
        is.setModifyRemark(intell.getModifyRemark());
        is.setModifyDate(new Date());

        is.setIsValid("0");//无效

        intelligentSupplementDao.save(is);

        return true;
    }



    public String pushApply(IntelligentSupplement intell) {
        Project project = projectDao.get(intell.getProjId());
        if (project!=null){
            DataFilerSetUpDto config = Constants.isConfig(project.getCorpId() + "_" + intell.getMenuId());
            if (config!=null){//如果有配置不需要审核，直接通过
                return AuditStatusEnum.PASS.getCode();
            }else {//需要审核
                return AuditStatusEnum.AUDIT_IN_PROGRESS.getCode();
            }
        }
        return null;
    }


    public boolean saveSupplement(IntelligentSupplement intell) {
        if (intell==null) return false;

        intell.setIsId(IDUtil.getUniqueId(Constants.MODULE_CODE_CONTRACT));
        String imcNo = intell.getImcNo();//协议编号规则
        int count=intelligentSupplementDao.countByProjId(intell.getProjId());
        intell.setIsNo(imcNo.substring(0,imcNo.length()-1)+(count+1));
        intell.setIsValid("1");//有效

        intelligentSupplementDao.saveOrUpdate(intell);
        return true;
    }


    public String auditApply(IntelligentSupplement intell) {

        List<IntelligentSupplement> list=intelligentSupplementDao.queryListByisNo(intell.getIsNo());
        if (list!=null && list.size()>0){
            for (IntelligentSupplement is:list) {
                is.setIsValid("0");//无效
                intelligentSupplementDao.saveOrUpdate(is);
            }
        }
        return "1";
    }







    @Override
    public Map<String, Object> queryToAuditSupplement(IntelligentSupplementReq req) {
        Map<String, Object> map=intelligentSupplementDao.queryToAuditSupplement(req);

        List<Map<String, Object>> mapList = (List<Map<String, Object>>) map.get("data");

        if (mapList!=null && mapList.size()>0){
            for (Map m:mapList) {
                String level="1";
                Project pro = projectDao.get(m.get("projId")!=null?m.get("projId").toString():"");
                if (pro!=null) {
                     level = docTypeService.queryAuditLevel(StepOutWorkflowEnum.INTELLIGENT_SUPPLEMENT_AUDIT.getValue(), pro.getCorpId(), pro.getProjectType(), pro.getContributionMode());
                }

                m.put("level", level);//设置审核总级数

                Map<String,String> levelBtn = new HashMap();
                for(int n=1;n<Integer.parseInt(level)+1;n++){//待审核:2,
                    // -1 未审核  0 审核未通过  1 审核通过  ;
                    if(n==1){
                        levelBtn.put("level"+n, "2");// 待审
                    }else{
                        levelBtn.put("level"+n, "-1");//未审
                    }
                }
                List<ManageRecord> mrList = manageRecordDao.findByStepIdBusinessOrderId(m.get("isId")!=null?m.get("isId").toString():"",StepOutWorkflowEnum.INTELLIGENT_SUPPLEMENT_AUDIT.getValue(), MrResultEnum.PASSED.getValue());
                if (mrList!=null && mrList.size()>0){
                    for (ManageRecord mr:mrList) {//设置已审核通过的按钮
                        levelBtn.put("level"+mr.getMrAuditLevel(),mr.getMrResult());
                    }
                    if(mrList.size()<Integer.parseInt(level)){
                        levelBtn.put("level"+(mrList.size()+1), "2");
                    }
                }
                m.put("mrAuditLevel", JSONSerializer.toJSON(levelBtn).toString());
            }
        }
        return map;
    }













    /**
     * @MethodDesc: 调用接口
     * @Author zhangnx
     * @Date 2019/7/29 15:53
     */
    public void calInterface(String isId,String projId){
        //System.out.println("协议ID:"+isId+" 工程ID:"+projId);
    }



}
