package cc.dfsoft.project.biz.base.auditina.service.impl;

import cc.dfsoft.project.biz.base.auditina.dto.AuditInsReq;
import cc.dfsoft.project.biz.base.auditina.service.AuditInsService;
import cc.dfsoft.project.biz.base.baseinfo.dao.SystemSetDao;
import cc.dfsoft.project.biz.base.baseinfo.service.DocTypeService;
import cc.dfsoft.project.biz.base.inspection.dao.ProjectChecklistDao;
import cc.dfsoft.project.biz.base.inspection.entity.ProjectChecklist;
import cc.dfsoft.project.biz.base.project.dao.ManageRecordDao;
import cc.dfsoft.project.biz.base.project.dao.ProjectDao;
import cc.dfsoft.project.biz.base.project.entity.ManageRecord;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.project.enums.MrResultEnum;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.biz.sys.dept.enums.PostTypeEnum;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.constant.DataFilerSetUpDto;
import net.sf.json.JSONSerializer;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AuditInsServiceImpl implements AuditInsService {
    @Autowired
    private ProjectChecklistDao projectChecklistDao;
    @Autowired
    private SystemSetDao systemSetDao;
    @Autowired
    private DocTypeService docTypeService;
    @Autowired
    private ManageRecordDao manageRecordDao;
    @Autowired
    private ProjectDao projectDao;
    /**
     * 获取审核数据
     * @param req
     * @return
     */
    @Override
    public Map<String, Object> getDataList(AuditInsReq req) throws ParseException {
        String grade=null;
        LoginInfo loginInfo = SessionUtil.getLoginInfo();
        //查询数据
        Map<String,Object> result = projectChecklistDao.getDataList(req);
        List<ProjectChecklist> data = (List<ProjectChecklist>) result.get("data");
        Project project = projectDao.get(req.getProjId());
        if (data != null && data.size() > 0) {
        /**
         * -1 未审核 0 审核未通过 1 审核通过 2待审核 若该
         * 审核为三级审核，且一级审核通过、二级审核未通过，三级审核待审核，则键值对：{"level1":"1","level2":"0",
         * "level3":"2"};
         */

            for (int i = 0; i < data.size(); i++) {
                String auditLevel =Constants.AUDIT_INS_LEVEL;
                //查询审核级别
                List<Map<String, Object>> list = Constants.getConstantsMapByKey(Constants.AYDIT_INS);
                String stepId = "";
                String level = "";
                if(list != null && list.size()>0){
                    for(Map<String, Object> m :list){
                        if(String.valueOf(m.get("RESERVE1")).equals( data.get(i).getPcDesId())){
                            stepId = String.valueOf(m.get("CNVALUE"));
                           level = docTypeService.queryAuditLevel(stepId, project.getCorpId(), project.getProjectType(), project.getContributionMode());
                            break;
                        }
                    }
                }
                if(StringUtils.isNotBlank(level)){
                    auditLevel=level;
                }
                data.get(i).setLevel(auditLevel);// 设置审核总级数

                Map<String, String> levelBtn = new HashMap<String, String>();

                for (int n = 1; n < Integer.parseInt(auditLevel) + 1; n++) {
                    if (n == 1) {
                        levelBtn.put("level" + n, "2");// 待审
                    } else {
                        levelBtn.put("level" + n, "-1");// 未审
                    }
                }
                List<ManageRecord> mrls = manageRecordDao.findByStepIdBusIdIsPass(data.get(i).getPcId(), "",MrResultEnum.PASSED.getValue());
                if (mrls != null && mrls.size() > 0) {
                    // 遍历循环，获取审核是否通过
                    for (ManageRecord mr : mrls) {
                        levelBtn.put("level" + mr.getMrAuditLevel(), mr.getMrResult());
                    }
                    if (mrls.size() < Integer.parseInt(auditLevel)) {
                        levelBtn.put("level" + (mrls.size() + 1), "2");
                    }
                }else{
                	//判断现场代表，监理是否有一级审核的权限没有，则置为不可审核
                    //配置规则出资方式_报验单类型_职务_0(菜单ID)
                    String curPost = null;
                    if(loginInfo.getPost().contains(PostTypeEnum.SUJGJ.getValue())){
                    	curPost=PostTypeEnum.SUJGJ.getValue();
                    }else if(loginInfo.getPost().contains(PostTypeEnum.BUILDER.getValue())){
                    	curPost=PostTypeEnum.BUILDER.getValue();
                    }
                	String keys = project.getContributionMode()+"_"+data.get(i).getPcDesId()+"_"+curPost+"_"+Constants.SUCCESS_CODE;
                	List<DataFilerSetUpDto> listFilter = Constants.getDataFilterMapByKey(keys);
                	//有数据则不能审核
					if(listFilter!=null && listFilter.size()>0){
						levelBtn.put("level1", "-1");
					}
                }
                data.get(i).setMrAuditLevel(JSONSerializer.toJSON(levelBtn).toString());
            }
            result.put("data", data);
        }

        return result;
    }
}
