package cc.dfsoft.project.biz.base.accept.service.impl;

import cc.dfsoft.project.biz.base.accept.service.AccepAuditService;
import cc.dfsoft.project.biz.base.baseinfo.dao.SystemSetDao;
import cc.dfsoft.project.biz.base.baseinfo.dto.WorkDayDto;
import cc.dfsoft.project.biz.base.baseinfo.entity.DocType;
import cc.dfsoft.project.biz.base.baseinfo.service.DocTypeService;
import cc.dfsoft.project.biz.base.baseinfo.service.FestivalService;
import cc.dfsoft.project.biz.base.common.entity.SystemSet;
import cc.dfsoft.project.biz.base.project.dao.ManageRecordDao;
import cc.dfsoft.project.biz.base.project.dao.OperateRecordDao;
import cc.dfsoft.project.biz.base.project.dao.ProjectDao;
import cc.dfsoft.project.biz.base.project.dto.ManageRecordQueryReq;
import cc.dfsoft.project.biz.base.project.dto.ProjectQueryReq;
import cc.dfsoft.project.biz.base.project.entity.ManageRecord;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.project.enums.MrResultEnum;
import cc.dfsoft.project.biz.base.project.enums.ProjStatusEnum;
import cc.dfsoft.project.biz.base.project.enums.StepEnum;
import cc.dfsoft.project.biz.base.subpackage.entity.SubContract;
import cc.dfsoft.project.biz.ifs.finance.service.IFinanceInfoService;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dao.DepartmentDao;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.biz.sys.dept.entity.Department;
import cc.dfsoft.uexpress.biz.sys.dept.enums.DeptDivideEnum;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.util.FestivalUtil;
import cc.dfsoft.uexpress.common.util.ResultUtil;
import cc.dfsoft.uexpress.common.util.StringUtil;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.*;

@Service
@Transactional(readOnly = true,propagation= Propagation.REQUIRED)
public class AccepAuditServiceImpl implements AccepAuditService{

    /** 日志实例 */
    private static Logger logger = LoggerFactory.getLogger(AccepAuditServiceImpl.class);
    @Autowired
    private SystemSetDao systemSetDao;
    @Autowired
    private DepartmentDao departmentDao;
    @Autowired
    private ProjectDao projectDao;
    @Autowired
    private DocTypeService docTypeService;
    @Autowired
    private ManageRecordDao managerecorddao;
    @Autowired
    private OperateRecordDao operateRecordDao;
    @Autowired
    private FestivalService festivalService;

    @SuppressWarnings("unchecked")
	@Override
    public Map<String, Object> getAccepAuditList(ProjectQueryReq req) throws ParseException {
        String grade=null;
        LoginInfo loginInfo = SessionUtil.getLoginInfo();
        //SystemSet systemSet=systemSetDao.get("stepId", req.getStepId());
        SystemSet systemSet=systemSetDao.querySystemSetByStepId(req.getStepId(),loginInfo.getCorpId());
        if(systemSet!=null&& StringUtil.isNotBlank(systemSet.getTimeSpan())){
            req.setTimeLimit(Integer.parseInt(systemSet.getTimeSpan()));
        }
       
        Map<String,Object> result = this.getProjectAudit(req);
        List<Project> data = (List<Project>) result.get("data");
        //按步骤id进行查询 获取单据类型
        Project pro=new Project();
        if(data!=null && data.size()>0){
            /**
             * -1 未审核  0 审核未通过  1 审核通过  2待审核
             * 若该 审核为三级审核，且一级审核通过、二级审核未通过，三级审核待审核，则键值对：{"level1":"1","level2":"0","level3":"2"};
             * */
            //遍历循环  设置审核级别
            for(int i = 0;i<data.size();i++){

                pro=projectDao.get(data.get(i).getProjId());
                if(pro != null){
                    data.get(i).setContributionModeDes(pro.getContributionModeDes());//出自方式描述
                }

                //分公司查询审核级别
                DocType docType = docTypeService.findByStepId(StepEnum.PROJECT_ACCEPT_AUDIT.getValue(),data.get(i).getCorpId(),pro.getProjectType(),pro.getContributionMode());
                if(docType!=null && StringUtils.isNotBlank(docType.getGrade())){
                    grade=docType.getGrade();
                }else{
                    grade="0";
                }

                data.get(i).setLevel(grade);// 设置审核总级数（设计信息2级审核）
                Map<String,String> levelBtn = new HashMap();
                //以下循环，遍历出默认应该几级审核，审核状态分别为什么，例如三级审核，则一级待审、二级未审、三级未审{"level1":"2","level2":"-1","level3":"-1"}；
                for(int n=1;n<Integer.parseInt(grade)+1;n++){
                    if(n==1){
                        levelBtn.put("level"+n, "2");// 待审
                    }else{
                        levelBtn.put("level"+n, "-1");//未审
                    }
                }
                ManageRecordQueryReq mrq = new ManageRecordQueryReq();
                //业务的ID
                mrq.setBusinessOrderId(data.get(i).getProjId());
                mrq.setStepId(StepEnum.PROJECT_ACCEPT_AUDIT.getValue());
                mrq.setProjId(data.get(i).getProjId());
                //审核信息
                List<ManageRecord> mrls = managerecorddao.findByStepIdProjIdIsPass(data.get(i).getProjId(),StepEnum.PROJECT_ACCEPT_AUDIT.getValue(), MrResultEnum.PASSED.getValue());
                if(mrls!=null && mrls.size()>0){
                    String size = mrls.size()+"";
                    if(mrls.size()<Integer.parseInt(grade)){
                        //遍历循环，获取审核是否通过
                        for(ManageRecord mr:mrls){
                            levelBtn.put("level"+mr.getMrAuditLevel(),mr.getMrResult());
                        }
                        if(mrls.size()<Integer.parseInt(grade)){
                            levelBtn.put("level"+(mrls.size()+1), "2");
                        }
                    }
                }
                data.get(i).setMrAuditLevel(JSONSerializer.toJSON(levelBtn).toString());

            }
            //如果是在受理审核环节
//            if(ProjStatusEnum.TO_APPROVAL.getValue().equals(req.getProjStatusId())){
//                //循环处理完的数据
//                Iterator<Project> it = data.iterator();
//                while(it.hasNext()){
//                    Project surveyInfo = it.next();
//                    JSONObject jb = JSONObject.fromObject(surveyInfo.getMrAuditLevel());
//                    Map<String, String> mapp = (Map<String, String>)jb;
//                    //如果登录者不是设计院的
//                    if(!loginInfo.getDeptDivide().equals(DeptDivideEnum.GAS_COMPANY.getValue())){
//                        //如果记录是一级待审，并且勘察员不是自己，就删了该记录
//                        if(mapp.get("level1").equals("2")&&!surveyInfo.getProjId().equals(loginInfo.getStaffId())){
//                            it.remove();
//                        }
//                    }
//                }
//            }
            result.put("data", data);
        }
        //return ResultUtil.pageResult(data.size(), req.getDraw(),data);
        return result;
    }

    /**
     * 查询整理数据
     * @param req
     * @return
     * @throws ParseException
     */
    private Map<String, Object> getProjectAudit(ProjectQueryReq req) throws ParseException {
        //最后一个操作记录的工程记录列表（符合状态的当前工程）
        List<Map<String, Object>> ors=operateRecordDao.getOptRecordByTime(req.getProjStatus());
        //返回的结果Map
        Map<String, Object> map= projectDao.getAccepAuditList(req);
        List<Project> list = (List<Project>) map.get("data");
        //需要返回的结果集中data
        List<Project> listNew=new ArrayList<Project>();
        //时间限制（单位天）
        Integer timel=req.getTimeLimit();
        long secondsLimit=-1l;
        if(timel!=null){
            secondsLimit=timel*24*60*60;
        }
        for(Project sio :list){
            for(Map<String, Object> or:ors){
                if(or.get("PROJ_ID").equals(sio.getProjId())){
                    //业务操作记录中时间
                    Date oldTime=(Date) or.get("OPERATE_TIME");
                    logger.info("oldTime=="+String.valueOf(secondsLimit));
                    //当前时间
                    Date nowTime=projectDao.getDatabaseDate();
                    //获取两个日期之间的工作日天数
                    long workDays = 0;
                    try {
                        workDays = FestivalUtil.calLeaveDays(oldTime, nowTime, festivalService.getCacheMap(Constants.WORKDAYS), festivalService.getCacheMap(Constants.HOLIDAYS));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    long seconds = workDays*24*60*60;
                    logger.info("newTime=="+String.valueOf(seconds));
                    //用于时限进度展示
                    WorkDayDto workDayDto = new WorkDayDto();
                    workDayDto.setDaysLimit(String.valueOf(timel));
                    workDayDto.setWorkDays(String.valueOf(workDays));
                    workDayDto.setHaveDays(String.valueOf(timel-workDays));
                    sio.setWorkDayDto(workDayDto);
                    //如果当前时间-上个步骤的操作时间大于时间限制段则为超时
                    if(secondsLimit>0&&seconds>secondsLimit){
                        sio.setOverdue(true);
                        continue;
                    }
                }
            }

            Project pro=projectDao.get(sio.getProjId());
            if(pro!=null){
                sio.setProjectType(pro.getProjectType());
            }
            listNew.add(sio);

        }
        map.put("data",listNew);
        return map;
    }

    @Override
    public Project getProjectById(String projId) {
        return projectDao.get(projId);
    }
}
