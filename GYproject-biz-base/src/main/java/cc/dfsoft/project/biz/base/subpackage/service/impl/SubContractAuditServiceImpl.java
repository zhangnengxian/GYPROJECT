package cc.dfsoft.project.biz.base.subpackage.service.impl;

import cc.dfsoft.project.biz.base.baseinfo.dao.DocTypeDao;
import cc.dfsoft.project.biz.base.baseinfo.dao.SystemSetDao;
import cc.dfsoft.project.biz.base.baseinfo.dto.WorkDayDto;
import cc.dfsoft.project.biz.base.baseinfo.entity.DocType;
import cc.dfsoft.project.biz.base.baseinfo.service.FestivalService;
import cc.dfsoft.project.biz.base.common.entity.SystemSet;
import cc.dfsoft.project.biz.base.design.entity.SurveyInfo;
import cc.dfsoft.project.biz.base.design.service.impl.SurveyInfoServiceImpl;
import cc.dfsoft.project.biz.base.project.dao.ManageRecordDao;
import cc.dfsoft.project.biz.base.project.dao.OperateRecordDao;
import cc.dfsoft.project.biz.base.project.dao.ProjectDao;
import cc.dfsoft.project.biz.base.project.dto.ManageRecordQueryReq;
import cc.dfsoft.project.biz.base.project.entity.ManageRecord;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.project.enums.MrResultEnum;
import cc.dfsoft.project.biz.base.project.enums.ProjStatusEnum;
import cc.dfsoft.project.biz.base.project.enums.StepEnum;
import cc.dfsoft.project.biz.base.project.service.ManageRecordService;
import cc.dfsoft.project.biz.base.subpackage.dao.SurveySubContractAuditDao;
import cc.dfsoft.project.biz.base.subpackage.dto.SubAuditReq;
import cc.dfsoft.project.biz.base.subpackage.entity.SubContract;
import cc.dfsoft.project.biz.base.subpackage.service.SubContractAuditService;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dao.DepartmentDao;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.biz.sys.dept.entity.Department;
import cc.dfsoft.uexpress.biz.sys.dept.enums.DeptDivideEnum;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.constant.DataFilerSetUpDto;
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

import javax.annotation.Resource;

import java.text.ParseException;
import java.util.*;

@Service
public class SubContractAuditServiceImpl implements SubContractAuditService {
    private static Logger logger = LoggerFactory.getLogger(SubContractAuditServiceImpl.class);
    @Autowired
    private SurveySubContractAuditDao surveySubContractAuditDao;

    @Resource
    DepartmentDao departmentDao;

    /**单据类型DAO处理*/
    @Resource
    DocTypeDao docTypeDao;

    @Resource
    SystemSetDao systemSetDao;

    /**业务操作记录*/
    @Resource
    OperateRecordDao operateRecordDao;

    @Resource
    ProjectDao projectDao;

    @Resource
    FestivalService festivalService;

    /**管理记录dao*/
    @Resource
    ManageRecordDao managerecorddao;

    @Override
    public Map<String,Object> getSubContractAuditById(String scId) {
        Map<String,Object> map = surveySubContractAuditDao.getSubContractAuditById(scId);
        //System.out.println(map.get("OPERATE_DATE"));
        return map;
    }

    @Override
    public Map<String, Object> querySubContractAuditList(SubAuditReq subAuditReq) throws ParseException{
        String grade="";

        LoginInfo loginInfo = SessionUtil.getLoginInfo();
        //SystemSet systemSet=systemSetDao.get("stepId", req.getStepId());
        SystemSet systemSet=systemSetDao.querySystemSetByStepId(subAuditReq.getStepId(),loginInfo.getCorpId());

        if(systemSet!=null&& StringUtil.isNotBlank(systemSet.getTimeSpan())){
            subAuditReq.setTimeLimit(Integer.parseInt(systemSet.getTimeSpan()));
        }
        Department department = departmentDao.queryDepartmentByDivide(DeptDivideEnum.ENGINEERING_CONSTRUCTION_CENTER.getValue(),loginInfo.getDeptId());
        if(department!=null){
            subAuditReq.setDeptId(department.getDeptId());
        }
        //最后一个操作记录的工程记录列表（符合状态的当前工程）
        List<Map<String, Object>> ors=operateRecordDao.getOptRecordByTime(subAuditReq.getProjStatusId());
        //获取施工合同审核列表
        Map<String, Object> map= surveySubContractAuditDao.querySubContractAuditList(subAuditReq);
        //符合当前状态的施工合同审核列表
        List<SubContract> list=(List<SubContract>) map.get("data");
        List<SubContract> listNew=new ArrayList<SubContract>();
        //时间限制（单位天）
        Integer timel=subAuditReq.getTimeLimit();
        long secondsLimit=-1l;
        if(timel!=null){
            secondsLimit=timel*24*60*60;
        }
        for(SubContract sio :list){
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
                        // TODO Auto-generated catch block
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
                sio.setContributionMode(pro.getContributionMode());
            }
            listNew.add(sio);
        }
        map.put("data",listNew);
        Map<String,Object> result = map;
        List<SubContract> data = (List<SubContract>) result.get("data");
        //按步骤id进行查询 获取单据类型
        Project pro=new Project();
        //遍历循环  设置审核级别
        for(int i = 0;i<data.size();i++){
        	//查询施工合同配置的审核级别
        	DocType docType = docTypeDao.findByStepId(StepEnum.SUB_CONTRACT_AUDIT.getValue(),data.get(i).getCorpId(),data.get(i).getProjectType(),data.get(i).getContributionMode());
			if(docType!=null && StringUtils.isNotBlank(docType.getGrade())){
				grade=docType.getGrade();
			}else{
				grade="0";
			}
			data.get(i).setLevel(grade);// 设置审核总级数

            Map<String,String> levelBtn = new HashMap();
            //以下循环，遍历出默认应该几级审核，审核状态分别为什么，例如三级审核，则一级待审、二级未审、三级未审{"level1":"2","level2":"-1","level3":"-1"}；
            for(int n=1;n<Integer.parseInt(grade)+1;n++){
                if(n==1){
                    levelBtn.put("level"+n, "2");// 待审
                }else{
                    levelBtn.put("level"+n, "-1");//未审
                }
              //根据员工ID查找不能审核那一级别
				List<DataFilerSetUpDto> FilterData = Constants.getDataFilterMapByKey(data.get(i).getProjectType()+"_"+loginInfo.getStaffId()+"_"+n+"_"+"110618");
				if(FilterData!=null && FilterData.size() > 0){
					levelBtn.put("level"+n, "-1");
				}
            }
            ManageRecordQueryReq mrq = new ManageRecordQueryReq();
            mrq.setBusinessOrderId(data.get(i).getScId());
            mrq.setStepId(StepEnum.CONNECT_GAS_AUDIT.getValue());
            mrq.setProjId(data.get(i).getProjId());
            List<ManageRecord> mrls = managerecorddao.findByStepIdProjIdIsPass(data.get(i).getProjId(),StepEnum.SUB_CONTRACT_AUDIT.getValue(), MrResultEnum.PASSED.getValue());
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
            pro=projectDao.get(data.get(i).getProjId());
            if(pro != null){
                data.get(i).setContributionModeDes(pro.getContributionModeDes());//出自方式描述
            }
            //如果是在踏勘审核环节
            if(ProjStatusEnum.TO_APPROVAL.getValue().equals(subAuditReq.getProjStatusId())){
                //循环处理完的数据
                Iterator<SubContract> it = data.iterator();
                while(it.hasNext()){
                    SubContract surveyInfo = it.next();
                    JSONObject jb = JSONObject.fromObject(surveyInfo.getMrAuditLevel());
                    Map<String, String> mapp = (Map<String, String>)jb;
                    //如果登录者不是设计院的
                    if(!loginInfo.getDeptDivide().equals(DeptDivideEnum.DESIGN_INSTITUTE.getValue())){
                        //如果记录是一级待审，并且勘察员不是自己，就删了该记录
                        if(mapp.get("level1").equals("2")&&!surveyInfo.getScId().equals(loginInfo.getStaffId())){
                            it.remove();
                        }
                    }
                }
            }
            result.put("data", data);
        }

      return ResultUtil.pageResult(data.size(), subAuditReq.getDraw(),data);
    }
}
