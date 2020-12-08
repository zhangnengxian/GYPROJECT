package cc.dfsoft.project.biz.base.accept.service.impl;

import cc.dfsoft.project.biz.base.accept.dao.RaiseMoneyDao;
import cc.dfsoft.project.biz.base.accept.entity.RaiseMoney;
import cc.dfsoft.project.biz.base.accept.service.RaiseMoneyAplicationService;
import cc.dfsoft.project.biz.base.baseinfo.service.WorkFlowService;
import cc.dfsoft.project.biz.base.project.dao.ProjectDao;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.project.enums.StepEnum;
import cc.dfsoft.project.biz.base.project.service.OperateRecordService;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.enums.WorkFlowActionEnum;
import cc.dfsoft.uexpress.common.util.DateUtil;
import cc.dfsoft.uexpress.common.util.IDUtil;
import cc.dfsoft.uexpress.common.util.StringUtil;
import cc.dfsoft.uexpress.common.util.WorkFlowUtil;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fr.clone.cloning.IDumpCloned;

import javax.annotation.Resource;
import java.util.Date;
import java.util.UUID;
/**
 * @ClassDesc: 提资申请实现类
 * @author: zhangnx
 * @date: 10:35 2018/9/14
 * @version: V1.0
 */
@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class RaiseMoneyApplicationServiceImpl implements RaiseMoneyAplicationService {

    private static Logger logger= LoggerFactory.getLogger(RaiseMoneyApplicationServiceImpl.class);

    @Resource
    RaiseMoneyDao raiseMoneyDao;

    @Resource
    ProjectDao projectDao;

    @Resource
    WorkFlowService workFlowService;

    @Resource
    OperateRecordService operateRecordService;

    /**
     * @methodDesc: 提资申请保存
     * @author: zhangnx
     * @date: 10:36 2018/9/14
     */
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void saveRaiseMoney(RaiseMoney raiseMoney) {

        //根据工程ID查询提资表信息
        RaiseMoney  existProject= raiseMoneyDao.get("projId",raiseMoney.getProjId());

        if (existProject==null){//新增
           //获取主键ID
           raiseMoney.setRaiseMoneyId(IDUtil.getUniqueId(Constants.MODULE_CODE_PROJECTAPPROVAL));
           raiseMoney.setRemark(raiseMoney.getRemark());//备注
           raiseMoney.setApplyDate(raiseMoneyDao.getDatabaseDate());//申请日期
           raiseMoneyDao.saveOrUpdate(raiseMoney);
       }else {//更新
            existProject.setRemark(raiseMoney.getRemark());//备注
            existProject.setUploadFlag(raiseMoney.getUploadFlag());//是否上传提资资料
            existProject.setApplyDate(raiseMoneyDao.getDatabaseDate());//申请日期
            raiseMoneyDao.update(existProject);
       }
    }
    
    /**
     * @methodDesc: 推送更新工程状态
     * @author: zhangnx
     * @date: 10:36 2018/9/14
     */
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void updateProject(RaiseMoney raiseMoney) {
    	
    	//根据工程ID查询提资表信息
        RaiseMoney  existProject= raiseMoneyDao.get("projId",raiseMoney.getProjId());

        if (existProject==null){//新增
           //获取主键ID
           raiseMoney.setRaiseMoneyId(IDUtil.getUniqueId(Constants.MODULE_CODE_PROJECTAPPROVAL));
           raiseMoney.setRemark(raiseMoney.getRemark());				//备注
           raiseMoney.setApplyDate(raiseMoneyDao.getDatabaseDate());//申请日期
           raiseMoneyDao.saveOrUpdate(raiseMoney);

       }else {//更新
            existProject.setRemark(raiseMoney.getRemark());				//备注
            existProject.setUploadFlag(raiseMoney.getUploadFlag());		//是否上传提资资料
            existProject.setApplyDate(raiseMoneyDao.getDatabaseDate());//申请日期
           raiseMoneyDao.update(existProject);
       }
    	
        //根据工程ID查询工程信息
        Project pro=projectDao.get(raiseMoney.getProjId());

        //根据工作流获取下一个状态码：提资审核：10032
        String statusId=workFlowService.queryProjStatusId(pro.getCorpId(), pro.getProjectType(),pro.getContributionMode(), WorkFlowActionEnum.RAISEMONEY_APPLY.getActionCode(), true);
        pro.setProjStatusId(statusId);
       
        //形成操作记录
        String orId = IDUtil.getUniqueId(Constants.MODULE_CODE_PROJECTAPPROVAL);
        String todoer=operateRecordService.createOperateRecord(orId, pro.getProjId(),    pro.getProjNo(), StepEnum.RAISEMONEY_APPLY.getValue(), StepEnum.RAISEMONEY_APPLY.getMessage(), "");
        
        pro.setToDoer(todoer);
        //更新工程
        projectDao.update(pro);
    }
    

    /**
     * @methodDesc: 通过工程查询提资申请信息
     * @author: zhangnx
     * @date: 10:37 2018/9/14
     */
    @Override
    public RaiseMoney queryRaiseMoneyByProjId(String id) {
        try {
            return raiseMoneyDao.get("projId",id);
        }catch (Exception e){
            logger.error("获提资信息失败！", e);
            return null;
        }
    }
    
    /**
     * 保存用户回复
     * @author fuliwei  
     * @date 2018年9月13日  
     * @version 1.0
     */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void saveCustResponse(RaiseMoney raiseMoney) {
		
		Project pro=projectDao.get(raiseMoney.getProjId());
		String statusId=workFlowService.queryProjStatusId(pro.getCorpId(),pro.getProjectType(),pro.getContributionMode(),WorkFlowActionEnum.CUST_RESPONSE.getActionCode(), true);
		//判断是否退单
		/*if(raiseMoney.getIsBack().equals("1")){
			statusId = WorkFlowUtil.workFlowStatus("");				//工程状态终止
			pro.setBackReason(raiseMoney.getBackReason());			//退单原因
			pro.setBackRemarks(raiseMoney.getBackRemarks());		//退单备注
			pro.setBackDate(projectDao.getDatabaseDate());			//退单日期
			pro.setFinishedDate(projectDao.getDatabaseDate()); 		//结束日期
		}*/
		pro.setProjStatusId(statusId); 								//设置工程状态
		
		
		
		RaiseMoney mr=raiseMoneyDao.viewByProjId(raiseMoney.getProjId());
		if(mr==null){
			mr=new RaiseMoney();
			mr.setRaiseMoneyId(IDUtil.getUniqueId(Constants.MODULE_CODE_PROJECTAPPROVAL));
			mr.setProjId(pro.getProjId());
			mr.setProjNo(pro.getProjNo());
		}
		mr.setCustomerResponseInfo(raiseMoney.getCustomerResponseInfo());	//回复信息
		mr.setCustReposeDate(raiseMoneyDao.getDatabaseDate());				//回复时间
		
		raiseMoneyDao.saveOrUpdate(mr);
		
		//生成业务操作记录
		String orId = IDUtil.getUniqueId(Constants.MODULE_CODE_PROJECTAPPROVAL);
        String todoer=operateRecordService.createOperateRecord(orId, pro.getProjId(),pro.getProjNo(), StepEnum.CUST_RESPONSE.getValue(), StepEnum.CUST_RESPONSE.getMessage(), "");
        pro.setToDoer(todoer);	//待办人
        projectDao.saveOrUpdate(pro);
	}
}
