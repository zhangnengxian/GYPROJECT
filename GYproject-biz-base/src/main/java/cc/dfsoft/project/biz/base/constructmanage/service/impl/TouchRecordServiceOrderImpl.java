package cc.dfsoft.project.biz.base.constructmanage.service.impl;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.sql.rowset.serial.SerialException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cc.dfsoft.project.biz.base.constructmanage.dao.ConnectRecordDao;
import cc.dfsoft.project.biz.base.constructmanage.dao.TouchRecordOrderDao;
import cc.dfsoft.project.biz.base.constructmanage.entity.ConnectRecord;
import cc.dfsoft.project.biz.base.constructmanage.entity.ConnectRecordOrder;
import cc.dfsoft.project.biz.base.constructmanage.service.TouchRecordOrderService;
import cc.dfsoft.project.biz.base.plan.dao.ConstructionPlanDao;
import cc.dfsoft.project.biz.base.plan.entity.ConstructionPlan;
import cc.dfsoft.project.biz.base.project.dao.ProjectDao;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.project.enums.ActionEnum;
import cc.dfsoft.project.biz.base.project.enums.ProjStatusEnum;
import cc.dfsoft.project.biz.base.project.enums.StepEnum;
import cc.dfsoft.project.biz.base.project.service.NoticeService;
import cc.dfsoft.project.biz.base.project.service.OperateRecordService;
import cc.dfsoft.project.biz.base.project.service.SignatureService;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.enums.WorkFlowActionEnum;
import cc.dfsoft.uexpress.common.util.IDUtil;
import cc.dfsoft.uexpress.common.util.WorkFlowUtil;

@Service
@Transactional(readOnly = true,propagation=Propagation.REQUIRED)
public class TouchRecordServiceOrderImpl implements TouchRecordOrderService{

	/**碰口记录单dao*/
	@Resource
	TouchRecordOrderDao touchRecordOrderDao;
	
	/**碰口记录dao*/
	@Resource
	ConnectRecordDao connectRecordDao;
	
	/**工程dao*/
	@Resource
	ProjectDao projectDao;
	
	/**工程计划dao*/
	@Resource
	ConstructionPlanDao constructionPlanDao;
	
	/**业务操作记录服务接口*/
	@Resource
	OperateRecordService operateRecordService;
	
	@Resource
	NoticeService noticeService;
	
	@Resource
	SignatureService signatureService;

	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void touchRecordOrderSave(ConnectRecordOrder connectRecordOrder) throws Exception {
		boolean flag = false;
		if(StringUtils.isBlank(connectRecordOrder.getCroId())){
			//新增
			flag = true;
			connectRecordOrder.setCroId(IDUtil.getUniqueId(Constants.MODULE_CODE_PROJECTTOUCH));
		}
		//删除原来的
		connectRecordDao.deleteByCroId(connectRecordOrder.getCroId());
		
		List<ConnectRecord> connectRecords = connectRecordOrder.getConnectRecords();
		if(connectRecords!=null && connectRecords.size()>0){
			for(int i=0;i<connectRecords.size();i++){
				ConnectRecord connectRecord = connectRecords.get(i);
				connectRecord.setCroId(connectRecordOrder.getCroId());
					connectRecord.setCrId(IDUtil.getUniqueId(Constants.MODULE_CODE_PROJECTTOUCH));
				connectRecordDao.saveOrUpdate(connectRecord);
			}
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		if(connectRecordOrder.getInstructTimeDes()!=null){			
			connectRecordOrder.setInstructionTime(sdf.parse(connectRecordOrder.getInstructTimeDes()));
		}
		if(connectRecordOrder.getTouchEndDes()!=null){			
			connectRecordOrder.setTouchEndDate(sdf.parse(connectRecordOrder.getTouchEndDes()));
		}
		touchRecordOrderDao.saveOrUpdate(connectRecordOrder);
		
		signatureService.saveOrUpdateSign("menuId+menuNane+21",connectRecordOrder.getSign(), connectRecordOrder.getProjId(), connectRecordOrder.getCroId(), connectRecordOrder.getClass().getName(),flag);
		
		if(flag){
			//更新工程信息
			Project pro=projectDao.get(connectRecordOrder.getProjId());         //通过工程id查找Project
			
			if(ProjStatusEnum.DURING_CONSTRUCTION.getValue().equals(pro.getProjStatusId())){
				//如果是施工中，则跳转到自检 
				//生成工程状态
				String statusId=WorkFlowUtil.workFlowStatus(WorkFlowActionEnum.DURING_CONSTRUCTION.getActionCode());
				
				pro.setProjStatusId(statusId); 								//设置工程状态
				projectDao.saveOrUpdate(pro);
				//形成操作记录
				String orId = IDUtil.getUniqueId(Constants.MODULE_CODE_PROJECTTOUCH);
				operateRecordService.createOperateRecord(orId, connectRecordOrder.getProjId(), pro.getProjNo(), StepEnum.DURING_CONSTRUCTION.getValue(), StepEnum.DURING_CONSTRUCTION.getMessage(), "");
			}
		}
		
		// 设置通知失效
		String projId = connectRecordOrder.getProjId();
		Date databaseDate = projectDao.getDatabaseDate(); 
		Date tpDate = connectRecordOrder.getTpDate();  //碰口时间
		if (tpDate!=null && databaseDate.after(tpDate)) { 
			noticeService.updateNotice(projId, ActionEnum.TOUCH.getValue());  //设置通知失效
		}
	}

	@Override
	public ConnectRecordOrder touchRecordOrderDetail(String projId) {
		Project project = projectDao.get(projId);
		ConnectRecordOrder connectRecordOrder = new ConnectRecordOrder();
		List<ConnectRecordOrder> connectRecordOrders = touchRecordOrderDao.findbyProjId(projId);
		
		if(connectRecordOrders!=null && connectRecordOrders.size()!=0){
			connectRecordOrder = connectRecordOrders.get(0);
		}
		try {
			List<ConnectRecord> connectRecords = connectRecordDao.queryContent(projId);
		
			if(connectRecords!=null && connectRecords.size()!=0){
				ConnectRecord connectRecord = connectRecords.get(0);
				try {
					connectRecordOrder.setWelder1(connectRecord.getWelder1()); //焊工签字1
					connectRecordOrder.setWelderId1(connectRecord.getWelderId1()); //焊工1
					connectRecordOrder.setWelder2(connectRecord.getWelder2()); //焊工签字2
					connectRecordOrder.setWelderId2(connectRecord.getWelderId2()); //焊工2
					connectRecordOrder.setWelder3(connectRecord.getWelder3()); //焊工签字3
					connectRecordOrder.setWelderId3(connectRecord.getWelderId3()); //焊工3
				} catch (SerialException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				} //焊工签字
			}	
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<ConstructionPlan> constructionPlans = constructionPlanDao.findByProjNo(project.getProjNo());
		if(constructionPlans!=null && constructionPlans.size()!=0){
			ConstructionPlan constructionPlan = constructionPlans.get(0);
			//connectRecordOrder.setConstructionUnit(constructionPlan.getManagementOffice());//施工单位
			connectRecordOrder.setSuName(constructionPlan.getSuName());					   //监理单位
		}
		connectRecordOrder.setProjId(project.getProjId());
		connectRecordOrder.setProjName(project.getProjName());
		connectRecordOrder.setProjAddr(project.getProjAddr());
		return connectRecordOrder;
	}

	
}
