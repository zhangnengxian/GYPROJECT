package cc.dfsoft.project.biz.base.project.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fr.third.org.hsqldb.lib.Set;

import cc.dfsoft.project.biz.base.baseinfo.dao.BusinessPartnersDao;
import cc.dfsoft.project.biz.base.baseinfo.entity.BusinessPartners;
import cc.dfsoft.project.biz.base.budget.dao.ChangeManagementDao;
import cc.dfsoft.project.biz.base.budget.dto.BudgetReq;
import cc.dfsoft.project.biz.base.budget.entity.Budget;
import cc.dfsoft.project.biz.base.budget.enums.BudgetTypeEnum;
import cc.dfsoft.project.biz.base.budget.service.BudgetService;
import cc.dfsoft.project.biz.base.change.dao.MaterialChangeDao;
import cc.dfsoft.project.biz.base.change.entity.MaterialChange;
import cc.dfsoft.project.biz.base.change.enums.MCTypeEnum;
import cc.dfsoft.project.biz.base.charge.dao.AccrualsRecordDao;
import cc.dfsoft.project.biz.base.charge.entity.AccrualsRecord;
import cc.dfsoft.project.biz.base.charge.enums.ARStatusEnum;
import cc.dfsoft.project.biz.base.constructmanage.dao.BusinessCommunicationDao;
import cc.dfsoft.project.biz.base.constructmanage.dao.ConstructionOrganizationDao;
import cc.dfsoft.project.biz.base.constructmanage.dao.EngineeringVisaDao;
import cc.dfsoft.project.biz.base.constructmanage.dto.BusinessCommunicationReq;
import cc.dfsoft.project.biz.base.constructmanage.entity.BusinessCommunication;
import cc.dfsoft.project.biz.base.constructmanage.entity.ChangeManagement;
import cc.dfsoft.project.biz.base.constructmanage.entity.ConstructionOrganization;
import cc.dfsoft.project.biz.base.constructmanage.entity.EngineeringVisa;
import cc.dfsoft.project.biz.base.constructmanage.enums.BcSendTypeEnum;
import cc.dfsoft.project.biz.base.constructmanage.enums.BcStatusEnum;
import cc.dfsoft.project.biz.base.constructmanage.enums.ChangeTypeEnum;
import cc.dfsoft.project.biz.base.constructmanage.enums.StageProjectApplicationEnum;
import cc.dfsoft.project.biz.base.contract.enums.IsSignEnum;
import cc.dfsoft.project.biz.base.design.enums.DesignChangeStateEnum;
import cc.dfsoft.project.biz.base.design.enums.UnitTypeEnum;
import cc.dfsoft.project.biz.base.plan.dao.ConstructionPlanDao;
import cc.dfsoft.project.biz.base.plan.entity.ConstructionPlan;
import cc.dfsoft.project.biz.base.plan.enums.ConstructionPlanQueryReq;
import cc.dfsoft.project.biz.base.plan.service.ConstructionPlanService;
import cc.dfsoft.project.biz.base.project.dao.NoticeDao;
import cc.dfsoft.project.biz.base.project.dao.ProjectDao;
import cc.dfsoft.project.biz.base.project.dao.SignNoticeDao;
import cc.dfsoft.project.biz.base.project.dao.StausAndRoleDao;
import cc.dfsoft.project.biz.base.project.dto.OperateRecordQueryReq;
import cc.dfsoft.project.biz.base.project.dto.ProjectQueryReq;
import cc.dfsoft.project.biz.base.project.entity.Notice;
import cc.dfsoft.project.biz.base.project.entity.OperateRecord;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.project.entity.SignNotice;
import cc.dfsoft.project.biz.base.project.entity.StatusAndRole;
import cc.dfsoft.project.biz.base.project.enums.CollectionTypeEnum;
import cc.dfsoft.project.biz.base.project.enums.NoticeMenuEnum;
import cc.dfsoft.project.biz.base.project.enums.NoticeStateEnum;
import cc.dfsoft.project.biz.base.project.enums.NoticeTypeEnum;
import cc.dfsoft.project.biz.base.project.enums.OperateWorkFlowEnum;
import cc.dfsoft.project.biz.base.project.enums.ProjStatusEnum;
import cc.dfsoft.project.biz.base.project.enums.SignStatusEnum;
import cc.dfsoft.project.biz.base.project.service.NoticeService;
import cc.dfsoft.project.biz.base.project.service.OperateRecordService;
import cc.dfsoft.project.biz.base.project.service.SignNoticeStandardService;
import cc.dfsoft.project.biz.base.settlement.dao.DrawingApplyDao;
import cc.dfsoft.project.biz.base.settlement.dto.DrawingApplyReq;
import cc.dfsoft.project.biz.base.settlement.entity.DrawingApply;
import cc.dfsoft.project.biz.base.settlement.enums.AuditResultEnum;
import cc.dfsoft.project.biz.base.settlement.service.DrawingApplyService;
import cc.dfsoft.project.biz.base.subpackage.dto.PaymentApplyReq;
import cc.dfsoft.project.biz.base.subpackage.enums.FeeTypeEnum;
import cc.dfsoft.project.biz.ifs.projectQuery.dto.ProjectDto;
import cc.dfsoft.uexpress.biz.sys.auth.dao.MenuDao;
import cc.dfsoft.uexpress.biz.sys.auth.entity.Menu;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dao.StaffDao;
import cc.dfsoft.uexpress.biz.sys.dept.dao.StaffNoticeRoleDao;
import cc.dfsoft.uexpress.biz.sys.dept.dao.StaffRoleDao;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.biz.sys.dept.entity.Staff;
import cc.dfsoft.uexpress.biz.sys.dept.entity.StaffNoticeRole;
import cc.dfsoft.uexpress.biz.sys.dept.entity.StaffRole;
import cc.dfsoft.uexpress.biz.sys.dept.enums.DeptDivideEnum;
import cc.dfsoft.uexpress.biz.sys.dept.enums.DeptTypeEnum;
import cc.dfsoft.uexpress.biz.sys.dept.enums.PostTypeEnum;
import cc.dfsoft.uexpress.biz.sys.dept.service.StaffService;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.dto.PageSortReq;
import cc.dfsoft.uexpress.common.util.IDUtil;
import cc.dfsoft.uexpress.common.util.StringUtil;
/**
 * 通知
 * @author fuliwei
 *
 */
@Service
@Transactional(readOnly =true ,propagation= Propagation.REQUIRED)
public class NoticeServiceImpl implements NoticeService{
	
	private static Logger logger = LoggerFactory.getLogger(NoticeServiceImpl.class);
	
	/**通知服务接口*/
	@Resource
	NoticeDao noticeDao;
	
	/**通知角色*/
	@Resource
	StaffNoticeRoleDao staffNoticeRoleDao;
	
	/**角色*/
	@Resource
	StaffRoleDao staffRoleDao;
	
	/**状态角色关联*/
	@Resource
	StausAndRoleDao stausAndRoleDao;
	
	/** 员工服务接口 */
	@Resource
	StaffService staffService;
	
	/**工程id*/
	@Resource
	ProjectDao projectDao;
	
	/**业务沟通*/
	@Resource
	BusinessCommunicationDao businessCommunicationDao;
	
	/**员工Dao*/
	@Resource
	StaffDao staffDao;
	
	/**施工计划Dao*/
	@Resource
	ConstructionPlanDao constructionPlanDao;
	
	/**收费Dao*/
	@Resource
	AccrualsRecordDao accrualsRecordDao;
	
	/**菜单*/
	@Resource
	MenuDao menuDao;
	
	/**签字通知*/
	@Resource
	SignNoticeDao signNoticeDao;
	
	/**签证*/
	@Resource
	EngineeringVisaDao engineeringVisaDao;
	@Resource
	DrawingApplyDao drawingApplyDao;
	
	@Resource
	DrawingApplyService drawingApplyService;
	@Resource
	BudgetService budgetService;
	@Resource
	SignNoticeStandardService signNoticeStandardService;
	@Resource
	ConstructionOrganizationDao cuOrganizationDao;
	@Resource 
	ConstructionPlanService constructionPlanService;
	@Resource
	BusinessPartnersDao businessPartnersDao;
	@Resource
	ChangeManagementDao changeManagementDao;
	@Resource
	MaterialChangeDao materialChangeDao;
	@Resource
	OperateRecordService operateRecordService;
	
	/**
	 * 下达通知
	 * @param projName   工程名称
	 * @param actionTime 活动时间
	 * @param actionName 通知活动名称
	 */
	@Override
	@Transactional(readOnly = false , propagation= Propagation.REQUIRED)
	public void saveNotice(String projId ,String projName ,Date actionTime, String actionName , String actionType) {
		if (actionTime!=null  && StringUtils.isNotBlank(projName)) {
			Notice notice = new Notice();
			notice.setNoticeId(IDUtil.getUniqueId(Constants.MODULE_CODE_NOTICE));
			notice.setNoticeTitle(actionName + "通知");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String noticeContent = projName+"工程  于 " + sdf.format(actionTime)+" 组织"+actionName+"，请相关人员准时参加";
			notice.setNoticeContent(noticeContent);
			LoginInfo loginInfo = SessionUtil.getLoginInfo();
			notice.setNoticeStaffId(loginInfo.getStaffId());
			notice.setNoticeStaffName(loginInfo.getStaffName());
			/*notice.setNoticeTime(noticeDao.getDatabaseDate());*/
			notice.setNoticeTime(actionTime);//活动日期
			notice.setGenerateTime(noticeDao.getDatabaseDate());//生成通知日期
			notice.setProjId(projId);
			notice.setActionType(actionType);
			notice.setNoticeState(NoticeStateEnum.EFFECTIVE.getValue());
			//notice.setNoticeType(NoticeTypeEnum.ALL.getValue());
			//notice.setArea(area);
			noticeDao.save(notice);
		}
	}

	/**
	 * 通知查看
	 * @param notice
	 */
	@Override
	public Map<String, Object> queryNotice(PageSortReq pageSortReq){
		
		Map<String, Object> map=noticeDao.queryNotice(pageSortReq);
		Map<String, Object> returnMap=new HashMap<String,Object>();
		
		Date nowTime = noticeDao.getDatabaseDate(); 
		Long nowTimeHms=nowTime.getTime();//当前时间的毫秒数
		logger.info("当前时间的毫秒数.."+nowTimeHms);
		
		List<Notice> list=(List) map.get("data");
		List<Notice> returnList=new ArrayList<Notice>();
		
		if(list !=null && list.size()>0){
			for(int i = 0;i < list.size();i++){
				Notice notice=(Notice) list.get(i);
				Date noticeTime=notice.getNoticeTime();
				if(noticeTime!=null){
					Long noticeTimeHms=noticeTime.getTime();//通知时间毫秒数
					if(nowTimeHms<=noticeTimeHms){			//当前系统时间小于通知时间
						returnList.add(notice);
					}
				}
			}
		}
		returnMap.put("data", returnList);
		return returnMap;
	}
	
	/**
	 * 判断该工程此项活动是否已经下达通知
	 * @param projId
	 * @param actionType
	 * @return
	 */
	@Override
	public boolean checkExist(String projId, String actionType) {
		List list = noticeDao.getNotice(projId, actionType);
		if (list!=null && list.size()>0) {
			return true;
		}
		return false;
	}
	
	/**
	 * 将通知设置为失效
	 * @param projId
	 * @param actionType
	 */
	@Override
	@Transactional(readOnly = false , propagation= Propagation.REQUIRED)
	public void updateNotice(String projId, String actionType) {
		List list = noticeDao.getNotice(projId, actionType);
		if (list !=null  && list.size()>0) {
			Notice notice = (Notice) list.get(0);
			notice.setNoticeState(NoticeStateEnum.NOT_EFFECTIVE.getValue());
			noticeDao.update(notice);
		}
	}
	
	/**
	 * 将签证通知设置为失效
	 * @param evId
	 * @param actionType
	 */
	@Override
	@Transactional(readOnly = false , propagation= Propagation.REQUIRED)
	public void updateEngineeringNotice(String evId, String actionType){
		List list = noticeDao.getEngineeringNotice(evId, actionType);
		if (list !=null  && list.size()>0) {
			Notice notice = (Notice) list.get(0);
			notice.setNoticeState(NoticeStateEnum.NOT_EFFECTIVE.getValue());
			noticeDao.update(notice);
		}
	}

	@Override
	public boolean checkEngineeringExist(String projId, String actionType) {
		// TODO Auto-generated method stub
		return false;
	}
	
	/**
	 * 下达签证通知
	 * @param projName   工程名称
	 * @param actionTime 活动时间
	 * @param actionName 通知活动名称
	 */
	@Override
	@Transactional(readOnly = false , propagation= Propagation.REQUIRED)
	public void saveNotice(String evId, String projId, String projName, Date actionTime, String actionName,
			String actionType,Boolean flag) {
		if (actionTime!=null  && StringUtils.isNotBlank(projName)) {
			Notice notice = new Notice();
			notice.setNoticeId(IDUtil.getUniqueId(Constants.MODULE_CODE_NOTICE));
			notice.setNoticeTitle(actionName + "通知");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String noticeContent = projName+"工程  于 " + sdf.format(actionTime)+" 组织"+actionName+"，请相关人员准时参加";
			notice.setNoticeContent(noticeContent);
			LoginInfo loginInfo = SessionUtil.getLoginInfo();
			notice.setNoticeStaffId(loginInfo.getStaffId());
			notice.setNoticeStaffName(loginInfo.getStaffName());
			notice.setNoticeTime(actionTime);
			notice.setGenerateTime(noticeDao.getDatabaseDate());//生成通知日期
			notice.setProjId(projId);
			notice.setEvId(evId);
			notice.setActionType(actionType);
			notice.setNoticeState(NoticeStateEnum.EFFECTIVE.getValue());
			//notice.setArea(area);
			noticeDao.save(notice);
		}
		
	}
	
	/**
	 * 删除原来的通知	
	 * @author fuliwei
	 * @createTime 2017年2月14日
	 * @param  projId actionType
	 * @return
	 */
	@Override
	@Transactional(readOnly = false , propagation= Propagation.REQUIRED)
	public void deleteByIdAndActionType(String projId, String actionType) {
		 noticeDao.deleteByIdAndActionType(projId, actionType);
	};
	
	/**
	 * 查询工作通知-根据通知角色查询-作废
	 * @author fuliwei
	 * @createTime 2017年7月23日
	 * @param 
	 * @return
	 */
	@Override
	public Map<String, Object> queryWorkNotice(PageSortReq pageSortReq) {
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		StaffRole staffRole = staffRoleDao.queryStaffRoleInfo("", loginInfo.getStaffId());  // 提取员工角色
		
		
		List<Notice> listResult=new ArrayList<Notice>();
		List<Notice> listProjNotice=new ArrayList<Notice>();
		if (staffRole!=null) {
			String roleIds = staffRole.getRoleIds();
			//查询普通通知
			if (roleIds.contains("1021")) {  
				//pageSortReq.setSortInfo(request);
				Map<String,Object> map = this.queryNotice(pageSortReq);
				listResult=(List<Notice>) map.get("data");
			}
		}
		
		//工作通知-//审核通知
		listProjNotice=this.queryStausAndRole();
		
		//通知累加
		if(listProjNotice!=null && listProjNotice.size()>0){
			for(Notice notice:listProjNotice){
				listResult.add(notice);
			}
		}    
		
		
		
		
		
		StaffNoticeRole staffNoticeRole=staffNoticeRoleDao.queryStaffRoleInfo("", loginInfo.getStaffId());
		List<Notice> planNotice=new ArrayList<Notice>();
		if (staffNoticeRole!=null) {
			String roleIds = staffNoticeRole.getRoleIds();
			//施工任务单或施工任务单
			if(roleIds.contains("1040") || roleIds.contains("1041")) {  
				String[] projStatus =new String[]{};
				//待施工预算 到待施工中的任务
				List<ProjStatusEnum> list=ProjStatusEnum.getThanValueNew(ProjStatusEnum.TO_DETERMINE_AMOUNT.getValue(),ProjStatusEnum.TO_CONSTRUCTION.getValue());
				List<String> statusList=new ArrayList<String>();
				
				for(ProjStatusEnum pe:list){
					statusList.add(pe.getValue());
				}
				ProjectQueryReq req=new ProjectQueryReq();
				req.setProjStuList(statusList);
				req.setNoticeType(NoticeTypeEnum.PLAN_NOTICE.getValue());
				planNotice=this.queryPlanNotice(req, statusList);
				
			}
		}
		
		//施工任务单通知累加
		if(planNotice!=null && planNotice.size()>0){
			for(Notice notice:planNotice){
				listResult.add(notice);
			}
		}
		
		
		
		//派遣消息
		List<Notice> dispatchNotice=new ArrayList<Notice>();
		if (staffNoticeRole!=null) {
			String roleIds = staffNoticeRole.getRoleIds();
			if(roleIds.contains("1042")){
				//施工派遣
				dispatchNotice=this.queryDispatchNotice(UnitTypeEnum.CONSTRUCTION_UNIT.getValue());
			}else if(roleIds.contains("1043")){
				//监理派遣
				dispatchNotice=this.queryDispatchNotice(UnitTypeEnum.SUPERVISION_UNIT.getValue());
			}
		}
		
		//派遣消息通知累加
		if(dispatchNotice!=null && dispatchNotice.size()>0){
			for(Notice notice:dispatchNotice){
				listResult.add(notice);
			}
		}
		
		List<Notice> initialPaymentNotice=new ArrayList<Notice>();
		
		if (staffNoticeRole!=null) {
			String roleIds = staffNoticeRole.getRoleIds();
			if(roleIds.contains("1044")){
				//收费通知
				 String deptId = loginInfo.getDeptId().substring(0, loginInfo.getDeptId().length() - DeptTypeEnum.SUBCOMPANY.getInitVal().length());
				 initialPaymentNotice=this.queryAmountNotice(CollectionTypeEnum.INITIAL_PAYMENT.getValue(), ARStatusEnum.TO_BE_CHARGE.getValue(), deptId);
			}
			
		}
		//首付款收费消息通知累加
		if(initialPaymentNotice!=null && initialPaymentNotice.size()>0){
			for(Notice notice:initialPaymentNotice){
				listResult.add(notice);
			}
		}
		
		//智能表首付款收费消息
		List<Notice> intelligentContractNotice=new ArrayList<Notice>();
		if (staffNoticeRole!=null) {
			String roleIds = staffNoticeRole.getRoleIds();
			if(roleIds.contains("1045")){
				 String deptId = loginInfo.getDeptId().substring(0, loginInfo.getDeptId().length() - DeptTypeEnum.SUBCOMPANY.getInitVal().length());
				 intelligentContractNotice=this.queryAmountNotice(CollectionTypeEnum.INTELLIGENT_CONTRACT.getValue(), ARStatusEnum.TO_BE_CHARGE.getValue(), deptId);
			}
			
		}
		
		//智能表首付款收费消息通知累加
		if(intelligentContractNotice!=null && intelligentContractNotice.size()>0){
			for(Notice notice:intelligentContractNotice){
				listResult.add(notice);
			}
		}
		
		
		
		//签字消息
		List<Notice> signNotice=new ArrayList<Notice>();
		if (staffNoticeRole!=null) {
			String roleIds = staffNoticeRole.getRoleIds();
			if(roleIds.contains("1046")){
				signNotice=this.querySignNotice();
			}
		}
		
		//签字消息
		if(signNotice!=null && signNotice.size()>0){
			for(Notice notice:signNotice){
				listResult.add(notice);
			}
		}
				
		
		List<Notice> listResultNew=new ArrayList<Notice>();
		
		
		
		/*//业务沟通通知
		List<Notice> businessCommunicationNotice=this.queryBusinessCommunicationNotice();
		//通知累加
		if(businessCommunicationNotice!=null && businessCommunicationNotice.size()>0){
			for(Notice no:businessCommunicationNotice){
				listResultNew.add(no);
			}
		}*/
		
		
		//修改密码通知
		Notice modiftPasswordNotice=this.queryModifyPassword();
		
		if(modiftPasswordNotice!=null){
			listResultNew.add(modiftPasswordNotice);
		}
		
		
		List<SignNotice> noticeList=signNoticeDao.querySignNotice();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if(noticeList!=null && noticeList.size()>0){
			Notice signNoticeNew=new Notice();
			signNoticeNew.setNoticeTitle("签字通知");
			signNoticeNew.setNoticeContent("您有新的签字通知，请点击待办事项查看！");
			signNoticeNew.setArea(sdf.format(projectDao.getDatabaseDate()));
			signNoticeNew.setNoticeType("3");//业务沟通通知
			listResultNew.add(signNoticeNew);
		}
		
		
		
		
		Map<String, Object> mapResult=new HashMap<String, Object>();
		mapResult.put("data", listResultNew);
		return mapResult;
	};
	
	
	/**
	 * 查询工作通知条数
	 * @author fuliwei
	 * @createTime 2017年7月23日
	 * @param 
	 * @return
	 */
	@Override
	public List<Notice> queryStausAndRole(){
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		//获得当前登录人的所有通知菜单
		Map<String,Object> map = staffService.getStaffRoleMenuList(loginInfo.getTenantId(), loginInfo.getStaffId());
		
		List<Menu> menus=(List<Menu>) map.get("menus");
		Notice notice;
		List<Notice> noticeList=new ArrayList<Notice>();
		StatusAndRole sar=new StatusAndRole();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String menuId;
		if(menus != null && menus.size()>0){
			for(int i=0;i<menus.size();i++){
				menuId=menus.get(i).getMenuId();
				sar=stausAndRoleDao.get(menuId);
				if(sar!=null){
					//用menuId去查工程 条数
					int nums=projectDao.queryWorkNotice(sar.getStatusId());
					if(nums>0){
						ProjStatusEnum em=ProjStatusEnum.valueof(sar.getStatusId());
						if(em!=null){
							notice=new Notice();
							notice.setNoticeTitle("工作通知");
							notice.setNoticeContent("您有  "+nums+" 条  "+ProjStatusEnum.valueof(sar.getStatusId()).getMessage()+" 的工程需要推送，请及时处理！");
							notice.setArea(sdf.format(projectDao.getDatabaseDate()));
							notice.setNoticeType("2");//工作通知
							Menu menu=menuDao.get(menuId);
							notice.setUrl(menu.getUrl());
							noticeList.add(notice);
						}
					}
				}
				
				String [] btns = NoticeMenuEnum.getValues();//审核menuId
				String grade;
				String type;
				for(int j=0;j<btns.length;j++){
					String s=btns[j];
					if(btns[j].equals(menuId)){
						grade=NoticeMenuEnum.valueof(menuId).getGrade();//审核级别 1 2 3 
						type=NoticeMenuEnum.valueof(menuId).getType();  //审核类型 01 踏勘审核 02 图纸审核
						//用审核级别、审核类型 去查notice
						int nums=noticeDao.queryAuditNotice(grade, type);
						String [] menuIDS=menuId.split("-");
						String returnMenuId="";
						Menu menu=new Menu();
						if(menuIDS.length>1){
							returnMenuId=menuIDS[1].substring(0, 6);
							menu=menuDao.get(returnMenuId);
						}
						
						if(nums>0){
							notice=new Notice();
							notice.setNoticeTitle("审核通知");
							notice.setNoticeContent("您有  "+nums+" 条  待"+NoticeMenuEnum.valueof(menuId).getMessage()+" 的工程需要审核，请及时处理！");
							notice.setArea(sdf.format(projectDao.getDatabaseDate()));
							notice.setNoticeType("2");//工作通知
							if(menu!=null && StringUtil.isNotBlank(menu.getMenuId())){
								notice.setUrl(menu.getUrl());
							}
							noticeList.add(notice);
						}
					}
				}
				
			}
		}
		
		return noticeList;
	}
	
	/**
	 * 查询业务沟通的通知
	 * @author fuliwei
	 * @createTime 2017年9月22日
	 * @param 
	 * @return
	 */
	@Override
	public List<Notice> queryBusinessCommunicationNotice() {
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		BusinessCommunicationReq req=new BusinessCommunicationReq();
		req.setBcRecipientId(loginInfo.getStaffId());			//接收人id
		req.setBcSendType(BcSendTypeEnum.RECIPIENT.getValue());	//1：发送，2：接收
		req.setBcStatus(BcStatusEnum.TO_REPLY.getValue());		//1：待通知，2：待回复，3：已回复
		
		Notice notice;
		List<Notice> noticeList=new ArrayList<Notice>();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		//业务沟通通知
		List<BusinessCommunication> list=businessCommunicationDao.queryBusinessCommunicationList(req);
		if(list!=null && list.size()>0){
			for(BusinessCommunication bc:list){
				notice=new Notice();
				notice.setNoticeTitle("业务通知");
				notice.setNoticeContent("工程名称:"+bc.getProjName()+" ,通知内容"+bc.getNoticeContent()+" ,通知人:"+bc.getBcInitiatorName()+" ,发起通知时间:"+bc.getNoticeDate());
				notice.setArea(sdf.format(projectDao.getDatabaseDate()));
				notice.setNoticeType("3");//业务沟通通知
				noticeList.add(notice);
			}
		}
		return noticeList;
	}
	
	/**
	 * 修改密码提示
	 * @author fuliwei
	 * @createTime 2017年9月25日
	 * @param 
	 * @return
	 */
	@Override
	public Notice queryModifyPassword() {
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		Staff staff=staffDao.get(loginInfo.getStaffId());
		String password=staff.getPassword();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if(6>password.length()){
			Notice notice=new Notice();
			notice=new Notice();
			notice.setNoticeTitle("修改通知");
			notice.setNoticeContent("您的密码小于6位，请及时修改！");
			notice.setArea(sdf.format(projectDao.getDatabaseDate()));
			notice.setNoticeType(NoticeTypeEnum.PWD_NOTICE.getValue());//密码修改通知
			return notice;
		}
		return null;
	}
	
	/**
	 * 查询任务单通知
	 * @author fuliwei
	 * @createTime 2017年11月1日
	 * @param 
	 * @return
	 */
	@Override
	public List<Notice> queryPlanNotice(ProjectQueryReq req,List<String> projStatus) {
		
		
		List<ConstructionPlan> list=constructionPlanDao.queryPlanNotice(req,projStatus);
		Notice notice;
		
		List<Notice> returnList=new ArrayList<Notice>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		if(list!=null && list.size()>0){
			for(ConstructionPlan cp:list){
				Project pro=projectDao.get(cp.getProjId());
				notice=new Notice();
				notice.setNoticeTitle("工作通知");
				notice.setSignType(cp.getProjNo());
				notice.setNoticeContent(cp.getProjName());
				notice.setArea(sdf.format(projectDao.getDatabaseDate()));
				notice.setNoticeState(ProjStatusEnum.valueof(pro.getProjStatusId()).getMessage());
				notice.setNoticeType("2");//工作通知
				returnList.add(notice);
			}
		}
		
		
		/*if(returnList!=null && returnList.size()>0){
			if(returnList.size()>5){
				notice=new Notice();
				notice.setNoticeTitle("工作通知");
				notice.setNoticeContent("您有 "+returnList.size()+" 条工程已派工,工程状态为待施工预算到待施工，请查看任务单，提前准备！");
				notice.setArea(sdf.format(projectDao.getDatabaseDate()));
				notice.setNoticeType("2");//工作通知
				returnList=new ArrayList<Notice>();
				returnList.add(notice);
				return returnList;
			}
		}*/
		
		
		return returnList;
	}
	
	
	
	/**
	 * 查询施工派遣或监理派遣任务
	 * @author fuliwei
	 * @createTime 2017年11月21日
	 * @param 
	 * @return
	 */
	@Override
	public List<Notice> queryDispatchNotice(String type) {
		List<ConstructionPlan> list=constructionPlanDao.findNotDispacthPlan(type);
		Notice notice;
		
		List<Notice> returnList=new ArrayList<Notice>();
		
		String content="";
		
		if(UnitTypeEnum.CONSTRUCTION_UNIT.getValue().equals(type)){
			content="施工派遣";
		}else{
			content="监理派遣";
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		
		if(list!=null && list.size()>0){
			for(ConstructionPlan cp:list){
				notice=new Notice();
				notice.setNoticeTitle("工作通知");
				notice.setNoticeContent("工程名称: "+cp.getProjName()+" 的工程需要进行 "+content+"，请及时处理！");
				notice.setArea(sdf.format(projectDao.getDatabaseDate()));
				notice.setNoticeType("2");//工作通知
				returnList.add(notice);
			}
		}
		
		
		
		if(returnList!=null && returnList.size()>0){
			if(returnList.size()>5){
				notice=new Notice();
				notice.setNoticeTitle("工作通知");
				notice.setNoticeContent("您有 "+returnList.size()+" 条工程未"+content+"，请及时处理！");
				notice.setArea(sdf.format(projectDao.getDatabaseDate()));
				notice.setNoticeType("2");//工作通知
				returnList=new ArrayList<Notice>();
				returnList.add(notice);
				return returnList;
			}
		}
		
		
		
		return returnList;
	}
	
	
	/**
	 * 查询预验收通知依据菜单ID
	 * @author wanghuijun
	 * @createTime 2018年10月17日
	 * @param type
	 * @return
	 */
	public List<Notice> queryPreInspection(LoginInfo loginInfo){
		
		Notice notice;
		List<Notice> noticeList=new ArrayList<Notice>();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		//工作通知
		ConstructionPlanQueryReq req = new ConstructionPlanQueryReq();
		req.setProjStatus(ProjStatusEnum.TO_PRE_INSPECTION.getValue());  //查询待预验收的工程
		req.setSuJgjId(loginInfo.getStaffId());
		List<String> statusList=new ArrayList<String>();
        Map<String, Object> map;
		try {
			map = constructionPlanDao.queryConstructionPlan(req);
			 List<ConstructionPlan> list  = (List<ConstructionPlan>)map.get("data");
				if(list!=null && list.size()>0){
					for(ConstructionPlan constructionPlan:list){
						notice=new Notice();
						notice.setNoticeTitle("工作通知");
						notice.setNoticeContent("工程名称:"+constructionPlan.getProjName()+"的工程需要进行预验收");
						notice.setArea(sdf.format(projectDao.getDatabaseDate()));
						notice.setNoticeType("2");//工作通知
						noticeList.add(notice);
					}
				}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       
		return noticeList;

	}
	/**
	 * 查询收费通知
	 * @author fuliwei
	 * @createTime 2017年11月24日
	 * @param 
	 * @return
	 */
	@Override
	public List<Notice> queryAmountNotice(String arType, String arFlag, String deptId) {
		List<AccrualsRecord> list=accrualsRecordDao.findAmountNotice(arType,arFlag,deptId);
		
		String content="";
		
		if(CollectionTypeEnum.INITIAL_PAYMENT.getValue().equals(arType)){
			content="安装合同首付款";
		}else{
			content="智能表合同首付款";
		}
		
		Notice notice;
		
		List<Notice> returnList=new ArrayList<Notice>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if(list!=null && list.size()>0){
			notice=new Notice();
			notice.setNoticeTitle("工作通知");
			notice.setNoticeContent("您有 "+list.size()+" 条工程未收取 "+content+" ，请及时收费登记！");
			notice.setArea(sdf.format(projectDao.getDatabaseDate()));
			notice.setNoticeType("2");//工作通知
			returnList=new ArrayList<Notice>();
			returnList.add(notice);
			return returnList;
		}
		
		return null;
	}
	
	/**
	 * 保存签字通知
	 * @author fuliwei
	 * @createTime 2017年11月28日
	 * @param pro businessOrderId signType
	 * @return void
	 */
	@Override
	@Transactional(readOnly = false , propagation= Propagation.REQUIRED)
	public void saveSignNotice(Project pro, String businessOrderId, String signType,String actionName) {
		Notice notice = new Notice();
		notice.setNoticeId(IDUtil.getUniqueId(Constants.MODULE_CODE_NOTICE));
		notice.setNoticeTitle(actionName + "通知");
		String noticeContent = "工程名称:"+pro.getProjName()+"的工程 "+SignStatusEnum.valueof(signType).getMessage()+",请及时签字";
		notice.setNoticeContent(noticeContent);
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		notice.setNoticeStaffId(loginInfo.getStaffId());
		notice.setNoticeStaffName(loginInfo.getStaffName());
		notice.setGenerateTime(noticeDao.getDatabaseDate());//生成通知日期
		 notice.setNoticeTime(noticeDao.getDatabaseDate());
		notice.setProjId(pro.getProjId());
		notice.setActionType("2");
		notice.setNoticeState(NoticeStateEnum.EFFECTIVE.getValue());
		notice.setSignType(signType);
		notice.setBusinessOrderId(businessOrderId);
		noticeDao.save(notice);
		
	}
	
	/**
	 * 查询签字通知是否下达
	 * @author fuliwei
	 * @createTime 2017年11月28日
	 * @param 
	 * @return
	 */
	@Override
	public boolean checkSignNoticeExist(String businessOrderId, String signType) {
		List list = noticeDao.getSignNotice(businessOrderId, signType);
		if (list!=null && list.size()>0) {
			return true;
		}
		return false;
	}
	
	/**
	 * 设置签字通知无效
	 * @author fuliwei
	 * @createTime 2017年11月28日
	 * @param 
	 * @return
	 */
	@Override
	@Transactional(readOnly = false , propagation= Propagation.REQUIRED)
	public void updateSignNotice(String businessOrderId, String signType) {
		List<Notice> list = noticeDao.getSignNotice(businessOrderId, signType);
		if (list !=null  && list.size()>0) {
			Notice notice = list.get(0);
			notice.setNoticeState(NoticeStateEnum.NOT_EFFECTIVE.getValue());
			noticeDao.saveOrUpdate(notice);
		}
	}
	
	/**
	 * 查询签字通知
	 * @author fuliwei
	 * @createTime 2017年11月28日
	 * @param 
	 * @return
	 */
	@Override
	public List<Notice> querySignNotice() {
		return noticeDao.querySignNotice();
	}
	
	/**
	 * 查询签字通知置为有效
	 * @author fuliwei
	 * @createTime 2018年1月4日
	 * @param 
	 * @return
	 */
	@Override
	@Transactional(readOnly = false , propagation= Propagation.REQUIRED)
	public void updateSignNoticeValid(String businessOrderId, String signType) {
		List<Notice> list = noticeDao.getSignNotice(businessOrderId, signType);
		if (list !=null  && list.size()>0) {
			Notice notice = list.get(0);
			notice.setNoticeState(NoticeStateEnum.EFFECTIVE.getValue());
			noticeDao.update(notice);
		}
	}
	
	/**
	 * 查询工作通知
	 * @author fuliwei
	 * @createTime 2018年1月15日
	 * @param 
	 * @return
	 */
	@Override
	public Map<String, Object> queryWorkNoticeNew(PageSortReq pageSortReq) {
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		StaffRole staffRole = staffRoleDao.queryStaffRoleInfo("", loginInfo.getStaffId());  // 提取员工角色
		
		
		List<Notice> listResult=new ArrayList<Notice>();
		
		List<Notice> listProjNotice=new ArrayList<Notice>();
		if (staffRole!=null) {
			String roleIds = staffRole.getRoleIds();
			//查询普通通知
			if (roleIds.contains("1021")) {  
				//pageSortReq.setSortInfo(request);
				Map<String,Object> map = this.queryNotice(pageSortReq);
				listResult=(List<Notice>) map.get("data");
			}
		}
		
		//工作通知及审核通知
		listProjNotice=this.queryStausAndRole();
		
		//通知累加
		if(listProjNotice!=null && listProjNotice.size()>0){
			for(Notice notice:listProjNotice){
				listResult.add(notice);
			}
		}
		
		//业务沟通通知
		List<Notice> businessCommunicationNotice=this.queryBusinessCommunicationNotice();
		//通知累加
		if(businessCommunicationNotice!=null && businessCommunicationNotice.size()>0){
			for(Notice no:businessCommunicationNotice){
				listResult.add(no);
			}
		}
		
		
		StaffNoticeRole staffNoticeRole=staffNoticeRoleDao.queryStaffRoleInfo("", loginInfo.getStaffId());
		if (staffNoticeRole!=null) {
			String roleIds = staffNoticeRole.getRoleIds();
			
			//派遣消息
			List<Notice> dispatchNotice=new ArrayList<Notice>();
			if(roleIds.contains("1042")){
				//施工派遣
				dispatchNotice=this.queryDispatchNotice(UnitTypeEnum.CONSTRUCTION_UNIT.getValue());
			}else if(roleIds.contains("1043")){
				//监理派遣
				dispatchNotice=this.queryDispatchNotice(UnitTypeEnum.SUPERVISION_UNIT.getValue());
			}
			//派遣消息通知累加
			if(dispatchNotice!=null && dispatchNotice.size()>0){
				for(Notice notice:dispatchNotice){
					listResult.add(notice);
				}
			}
			
			//查询预验收通知,若职务是监理则进行查询
			 if(StringUtils.isNotBlank(loginInfo.getPost()) && loginInfo.getPost().contains(PostTypeEnum.SUJGJ.getValue())){
				 List<Notice> preInspecionNotice = this.queryPreInspection(loginInfo);
					if(preInspecionNotice !=null && preInspecionNotice.size() > 0){
						for (Notice notice : preInspecionNotice) {
							listResult.add(notice);
						}
					} 
			 }
			
			
			List<Notice> initialPaymentNotice=new ArrayList<Notice>();
			if(roleIds.contains("1044")){
				//收费通知
				 String deptId = loginInfo.getDeptId().substring(0, loginInfo.getDeptId().length() - DeptTypeEnum.SUBCOMPANY.getInitVal().length());
				 initialPaymentNotice=this.queryAmountNotice(CollectionTypeEnum.INITIAL_PAYMENT.getValue(), ARStatusEnum.TO_BE_CHARGE.getValue(), deptId);
				//首付款收费消息通知累加
					if(initialPaymentNotice!=null && initialPaymentNotice.size()>0){
						for(Notice notice:initialPaymentNotice){
							listResult.add(notice);
						}
					}
			}
			
			//智能表首付款收费消息
			List<Notice> intelligentContractNotice=new ArrayList<Notice>();
			if(roleIds.contains("1045")){
				 String deptId = loginInfo.getDeptId().substring(0, loginInfo.getDeptId().length() - DeptTypeEnum.SUBCOMPANY.getInitVal().length());
				 intelligentContractNotice=this.queryAmountNotice(CollectionTypeEnum.INTELLIGENT_CONTRACT.getValue(), ARStatusEnum.TO_BE_CHARGE.getValue(), deptId);
				//智能表首付款收费消息通知累加
					if(intelligentContractNotice!=null && intelligentContractNotice.size()>0){
						for(Notice notice:intelligentContractNotice){
							listResult.add(notice);
						}
					}
			}
			
			//变更签证调整
			List<Notice> visaNotice=new ArrayList<Notice>();
			if(roleIds.contains("1051")){
				visaNotice=this.queryEnginnerNotice();
				if(visaNotice!=null && visaNotice.size()>0){
					for(Notice notice:visaNotice){
						listResult.add(notice);
					}
				}
			}
			
			//电子图审核
			List<Notice> drawingNotice=new ArrayList<Notice>();
			if(roleIds.contains("diau")){
				drawingNotice=this.queryDrawingApplyAuditNotice();
				if(drawingNotice!=null && drawingNotice.size()>0){
					for(Notice notice:drawingNotice){
						listResult.add(notice);
					}
				}
			}
			
			//电子图申请
			List<Notice> drawingApplyNotice=new ArrayList<Notice>();
			if(roleIds.contains("1052")){
				drawingApplyNotice=this.queryDrawingApplyNotice();
				if(drawingApplyNotice!=null && drawingApplyNotice.size()>0){
					for(Notice notice:drawingApplyNotice){
						listResult.add(notice);
					}
				}
			}
			
			//待签补充协议
			List<Notice> supContractNotice=new ArrayList<Notice>();
			if(roleIds.contains("10483")){
				supContractNotice=this.querySupContractNotice();
				if(supContractNotice!=null && supContractNotice.size()>0){
					for(Notice notice:supContractNotice){
						listResult.add(notice);
					}
				}
			}
			
			//施工组织重报
			List<Notice> cuOrganizationNotice=new ArrayList<Notice>();
			if(roleIds.contains("1060")){
				cuOrganizationNotice=this.queryCuOrganizationNotice();
				if(cuOrganizationNotice!=null && cuOrganizationNotice.size()>0){
					for(Notice notice:cuOrganizationNotice){
						listResult.add(notice);
					}
				}
			}
			
			//已审核完施工单位还没有确认的签证记录通知
			List<Notice> eVNotice=new ArrayList<Notice>();
			//施工单位预算员
			if(roleIds.contains("1061")){
				eVNotice=this.queryEVNotice();
				if(eVNotice!=null && eVNotice.size()>0){
					for(Notice notice:eVNotice){
						listResult.add(notice);
					}
				}
			}
			
		
			//费用申请通知
			List<Notice> payNotice=new ArrayList<Notice>();
		
			if(roleIds.contains("1062")){
				payNotice=this.queryPayNotice();
				if(payNotice!=null && payNotice.size()>0){
					for(Notice notice:payNotice){
						listResult.add(notice);
					}
				}
			}
			
			//设计变更材料上传通知-设计人员
			List<Notice> changementNotice=new ArrayList<Notice>();
			
			if(loginInfo.getPost().contains(PostTypeEnum.DESIGNER.getValue())){
				changementNotice=this.queryChangementNotice();
				if(changementNotice!=null && changementNotice.size()>0){
					for(Notice notice:changementNotice){
						listResult.add(notice);
					}
				}
			}
			
			//设计变更审核通知
			List<Notice> changementAuditNotice=new ArrayList<Notice>();
			if(roleIds.contains("1063")){
				changementAuditNotice=this.queryChangementAuditNotice();
			}
			if(changementAuditNotice!=null && changementAuditNotice.size()>0){
				for(Notice notice:changementAuditNotice){
					listResult.add(notice);
				}
			}
		}
		Map<String, Object> mapResult=new HashMap<String,Object>();
		if(listResult!=null && listResult.size()>0){
			mapResult.put("recordsTotal", listResult.size());
			mapResult.put("recordsFiltered", listResult.size());
			mapResult.put("draw", pageSortReq.getDraw());
			mapResult.put("data", listResult);
		}else{
			mapResult.put("recordsTotal", 0);
			mapResult.put("recordsFiltered", 0);
			mapResult.put("draw", pageSortReq.getDraw());
			mapResult.put("data", listResult);
		}
		
		return mapResult;
		
	}
	/**
	 * 变更审核通知
	 */
	@Override
	public List<Notice> queryChangementAuditNotice() {
		List<ChangeManagement> list = changeManagementDao.queryChangementAuditNotice(ChangeTypeEnum.CONSTRUCTION_CHANGE.getValue());
		List<Notice> noticeList=new ArrayList<Notice>();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if(list!=null && list.size()>0){
			for(ChangeManagement cm : list){
				//该变更是否已上传材料
				List<MaterialChange> materialChanges = materialChangeDao.findByType(cm.getCmId(),MCTypeEnum.MATERIAL_CHANGE.getValue());
				if(materialChanges==null || materialChanges.size()<1){
					Notice notice=new Notice();
					notice.setNoticeTitle("变更记录审核通知");
					notice.setNoticeContent("工程编号为："+cm.getProjNo()+",变更编号为："+cm.getCmNo()+" 的变更记录需要进行审核，请及时审核！");
					notice.setArea(sdf.format(projectDao.getDatabaseDate()));
					notice.setNoticeType("2");//工作通知
					noticeList.add(notice);
				}
			}
		}
		return noticeList;
	}

	/**
	 * 当前设计人员
	 * @author liaoyq
	 * @createTime 2018年6月28日
	 * @return
	 */
	@Override
	public List<Notice> queryChangementNotice() {
		List<ChangeManagement> list = changeManagementDao.queryChangementNotice();
		List<Notice> noticeList=new ArrayList<Notice>();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if(list!=null && list.size()>0){
			for(ChangeManagement cm : list){
				//该变更是否已上传材料
				List<MaterialChange> materialChanges = materialChangeDao.findByType(cm.getCmId(),MCTypeEnum.MATERIAL_CHANGE.getValue());
				if(materialChanges==null || materialChanges.size()<1){
					Notice notice=new Notice();
					notice.setNoticeTitle("变更记录上传材料通知");
					notice.setNoticeContent("工程编号为："+cm.getProjNo()+",变更编号为："+cm.getCmNo()+" 的变更记录需要进行材料调整，请及时上传变更材料！");
					notice.setArea(sdf.format(projectDao.getDatabaseDate()));
					notice.setNoticeType("2");//工作通知
					noticeList.add(notice);
				}
			}
		}
		return noticeList;
	}

	/**
	 * 查询待费用申请的工程数量
	 * @author liaoyq
	 * @createTime 2018年6月20日
	 * @return
	 */
	@Override
	public List<Notice> queryPayNotice() {
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		PaymentApplyReq paymentApplyReq = new PaymentApplyReq();
		if(DeptDivideEnum.DESIGN_INSTITUTE.getValue().equals(loginInfo.getDeptDivide())){//设计单位设计费
			paymentApplyReq.setFeeType(FeeTypeEnum.DESIGN_FEE.getValue());
		}else{
			BusinessPartners bp =  businessPartnersDao.get(loginInfo.getDeptId());
			if(bp!=null && StringUtil.isNotBlank(bp.getUnitType())){
				if(bp.getUnitType().equals(UnitTypeEnum.SUPERVISION_UNIT.getValue())){//监理单位监理费
					paymentApplyReq.setFeeType(FeeTypeEnum.SU_FEE.getValue());
				}else if(bp.getUnitType().equals(UnitTypeEnum.TEST_UNIT.getValue())){//检测单位检测费
					paymentApplyReq.setFeeType(FeeTypeEnum.CHECK_FEE.getValue());
				}
			}
		}
		List<Notice> noticeList=new ArrayList<Notice>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Map<String, Object> map = constructionPlanService.queryPayProject(paymentApplyReq);
		if(map.get("recordsTotal")!=null && Integer.parseInt(map.get("recordsTotal").toString())>0){
			Notice notice=new Notice();
			notice.setNoticeTitle("费用申请通知");
			notice.setNoticeContent("您有："+map.get("recordsTotal")+"条工程可以进行费用申请，请及时办理！");
			notice.setArea(sdf.format(projectDao.getDatabaseDate()));
			notice.setNoticeType("2");//工作通知
			noticeList.add(notice);
		}
		return noticeList;
	}

	/**
	 * 签证审核通过，施工单位没有确认的签证
	 * @author liaoyq
	 * @createTime 2018年6月13日
	 * @return
	 */
	@Override
	public List<Notice> queryEVNotice() {
		List<EngineeringVisa> list=engineeringVisaDao.queryEVNotice();
		List<Notice> noticeList=new ArrayList<Notice>();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if(list!=null && list.size()>0){
			for(EngineeringVisa ev : list){
				Notice notice=new Notice();
				notice.setNoticeTitle("签证审核金额确认通知");
				notice.setNoticeContent("工程编号为："+ev.getProjNo()+",签证编号为："+ev.getEvNo()+" 的签证审核["+StageProjectApplicationEnum.valueof(ev.getAuditState()).getMessage()+"],审定金额为："+ev.getQuantitiesTotal()+"元,请确认对该签证审核金额是否有异议！");
				notice.setArea(sdf.format(projectDao.getDatabaseDate()));
				notice.setNoticeType("2");//工作通知
				noticeList.add(notice);
			}
		}
		return noticeList;
	}

	/**
	 * 施工组织重报通知
	 * @author liaoyq
	 * @createTime 2018年6月8日
	 * @return
	 */
	@Override
	public List<Notice> queryCuOrganizationNotice() {
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		List<ConstructionOrganization> list=cuOrganizationDao.queryCuOrganizationNotice(loginInfo.getStaffId());
		List<Notice> noticeList=new ArrayList<Notice>();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if(list!=null && list.size()>0){
			for(ConstructionOrganization constructionOrganization : list){
				Notice notice=new Notice();
				notice.setNoticeTitle("施工组织重报通知");
				notice.setNoticeContent("编号为："+constructionOrganization.getProjNo()+"的工程需要重报施工组织，请及时处理！");
				notice.setArea(sdf.format(projectDao.getDatabaseDate()));
				notice.setNoticeType("2");//工作通知
				noticeList.add(notice);
			}
		}
		return noticeList;
	}

	/**
	 * 查询审核通知
	 * @author fuliwei
	 * @createTime 2018年1月15日
	 * @param 
	 * @return
	 */
	@Override
	public Map<String, Object> queryAuditNotice(PageSortReq pageSortReq) {
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		//获得当前登录人的所有通知菜单
		Map<String,Object> map = staffService.getStaffRoleMenuList(loginInfo.getTenantId(), loginInfo.getStaffId());
		
		List<Menu> menus=(List<Menu>) map.get("menus");
		Notice notice;
		List<Notice> noticeList=new ArrayList<Notice>();
		StatusAndRole sar=new StatusAndRole();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String menuId;
		if(menus != null && menus.size()>0){
			for(int i=0;i<menus.size();i++){
				menuId=menus.get(i).getMenuId();
				sar=stausAndRoleDao.get(menuId);
				String [] btns = NoticeMenuEnum.getValues();//审核menuId
				String grade;
				String type;
				for(int j=0;j<btns.length;j++){
					String s=btns[j];
					if(btns[j].equals(menuId)){
						grade=NoticeMenuEnum.valueof(menuId).getGrade();//审核级别 1 2 3 
						type=NoticeMenuEnum.valueof(menuId).getType();  //审核类型 01 踏勘审核 02 图纸审核
						//用审核级别、审核类型 去查notice
						int nums=noticeDao.queryAuditNotice(grade, type);
						
						if(nums>0){
							notice=new Notice();
							notice.setNoticeTitle("审核通知");
							notice.setNoticeContent("您有  "+nums+" 条  待"+NoticeMenuEnum.valueof(menuId).getMessage()+" 的工程需要审核，请及时处理！");
							notice.setArea(sdf.format(projectDao.getDatabaseDate()));
							notice.setNoticeType("2");//工作通知
							noticeList.add(notice);
						}
					}
				}
				
			}
		}
		
		Map<String, Object> mapResult=new HashMap<String,Object>();
		if(noticeList!=null && noticeList.size()>0){
			mapResult.put("recordsTotal", noticeList.size());
			mapResult.put("recordsFiltered", noticeList.size());
			mapResult.put("draw", pageSortReq.getDraw());
			mapResult.put("data", noticeList);
		}else{
			mapResult.put("recordsTotal", 0);
			mapResult.put("recordsFiltered", 0);
			mapResult.put("draw", pageSortReq.getDraw());
			mapResult.put("data", noticeList);
		}
		return mapResult;
	}
	
	/**
	 * 查询派遣通知
	 * @author fuliwei
	 * @createTime 2018年1月22日
	 * @param 
	 * @return
	 */
	@Override
	public Map<String, Object> queryDispatchNotice(PageSortReq pageSortReq) {
		
		
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		List<Notice> listResult=new ArrayList<Notice>();
		StaffNoticeRole staffNoticeRole=staffNoticeRoleDao.queryStaffRoleInfo("", loginInfo.getStaffId());
		List<Notice> planNotice=new ArrayList<Notice>();
		if (staffNoticeRole!=null) {
			String roleIds = staffNoticeRole.getRoleIds();
			//施工任务单或施工任务单
			if(roleIds.contains("1040") || roleIds.contains("1041")) {  
				String[] projStatus =new String[]{};
				//待施工预算 到待施工中的任务
				List<ProjStatusEnum> list=ProjStatusEnum.getThanValueNew(ProjStatusEnum.TO_DETERMINE_AMOUNT.getValue(),ProjStatusEnum.TO_CONSTRUCTION.getValue());
				List<String> statusList=new ArrayList<String>();
				
				for(ProjStatusEnum pe:list){
					statusList.add(pe.getValue());
				}
				ProjectQueryReq req=new ProjectQueryReq();
				req.setProjStuList(statusList);
				req.setNoticeType(NoticeTypeEnum.PLAN_NOTICE.getValue());
				planNotice=this.queryPlanNotice(req, statusList);
				
			}
		}
		
		//施工任务单通知累加
		if(planNotice!=null && planNotice.size()>0){
			for(Notice notice:planNotice){
				listResult.add(notice);
			}
		}
		Map<String, Object> mapResult=new HashMap<String,Object>();
		if(listResult!=null && listResult.size()>0){
			mapResult.put("recordsTotal", listResult.size());
			mapResult.put("recordsFiltered", listResult.size());
			mapResult.put("draw", pageSortReq.getDraw());
			mapResult.put("data", listResult);
		}else{
			mapResult.put("recordsTotal", 0);
			mapResult.put("recordsFiltered", 0);
			mapResult.put("draw", pageSortReq.getDraw());
			mapResult.put("data", listResult);
		}
		return mapResult;
	}
	
	/**
	 * 查询变更签证调整通知
	 * @author fuliwei
	 * @createTime 2018年1月31日
	 * @param 
	 * @return
	 */
	@Override
	public List<Notice> queryEnginnerNotice() {
		List<EngineeringVisa> list=engineeringVisaDao.queryVisaNotice(StageProjectApplicationEnum.TO_BUDGET.getValue());
		
		List<Notice> noticeList=new ArrayList<Notice>();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if(list!=null && list.size()>0){
			Notice notice=new Notice();
			notice.setNoticeTitle("签证通知");
			notice.setNoticeContent("您有  "+list.size()+" 条  待确定金额的签证记录，请及时处理！");
			notice.setArea(sdf.format(projectDao.getDatabaseDate()));
			notice.setNoticeType("2");//工作通知
			noticeList.add(notice);
		}
		return noticeList;
	}

	/**
	 * 电子图审核通知
	 * @author liaoyq
	 * @createTime 2018年3月29日
	 * @return
	 * @throws ParseException 
	 */
	private List<Notice> queryDrawingApplyAuditNotice(){
		DrawingApplyReq req = new DrawingApplyReq();
		req.setAuditState(AuditResultEnum.APPLYING.getValue());
		List<Notice> noticeList=new ArrayList<Notice>();
		try {
			List<DrawingApply> list = drawingApplyDao.queryDrawingApplyNotice(req);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(list!=null && list.size()>0){
				Notice notice=new Notice();
				notice.setNoticeTitle("电子图审核通知");
				notice.setNoticeContent("您有  "+list.size()+" 条  待电子图审核的记录，请及时处理！");
				notice.setArea(sdf.format(projectDao.getDatabaseDate()));
				notice.setNoticeType("2");//工作通知
				noticeList.add(notice);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return noticeList;
	}
	/**
	 * 电子图申请通知
	 * @author liaoyq
	 * @createTime 2018年3月29日
	 * @return
	 * @throws ParseException 
	 */
	private List<Notice> queryDrawingApplyNotice(){
		ProjectQueryReq req = new ProjectQueryReq();
		List<Notice> noticeList=new ArrayList<Notice>();
		try {
			Map<String, Object> map= drawingApplyService.queryProject(req);
			List<Project> list = (List<Project>) map.get("data");
			if(list==null || list.size()<1){
				return noticeList;
			}

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			int i=0;
			if(list!=null && list.size()>0){
				for(Project project : list){
					if(StringUtil.isNotBlank(project.getAuditStatus()) && (project.getAuditStatus().equals(AuditResultEnum.NOT_APPLY.getValue()) || project.getAuditStatus().equals(AuditResultEnum.NOT_PASSED.getValue()))){
						i++;
					}
				}
			}
			if(i>0){
				Notice notice=new Notice();
				notice.setNoticeTitle("电子图审核申请通知");
				notice.setNoticeContent("您有  "+i+" 条  待电子图审核申请的记录，请及时处理！");
				notice.setArea(sdf.format(projectDao.getDatabaseDate()));
				notice.setNoticeType("2");//工作通知
				noticeList.add(notice);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return noticeList;
	}
	/**
	 * 待签补充协议通知
	 * @author liaoyq
	 * @createTime 2018年3月29日
	 * @return
	 * @throws ParseException 
	 */
	private List<Notice> querySupContractNotice(){
		List<Notice> noticeList=new ArrayList<Notice>();
		try {
			BudgetReq req = new BudgetReq();
			req.setIsSignSuContrct(IsSignEnum.HAVE_NOT_SIGN.getValue());//未签订
			//默认待签补充协议
			req.setDesignChangeType(DesignChangeStateEnum.WAIT_SUPPLEMENT_CONTRACT.getValue());
			req.setBudgetTypeId(BudgetTypeEnum.ADJUSTED.getValue());//调整预算
			Map<String, Object> map = budgetService.queryAdjustBudget(req);
			List<Budget> list = (List<Budget>) map.get("data");
			if(list==null || list.size()<1){
				return noticeList;
			}
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			int i=0;
			if(list!=null && list.size()>0){
				Notice notice=new Notice();
				notice.setNoticeTitle("待签补充协议通知");
				notice.setNoticeContent("您有  "+list.size()+" 条  待签订补充协议的记录，请及时处理！");
				notice.setArea(sdf.format(projectDao.getDatabaseDate()));
				notice.setNoticeType("2");//工作通知
				noticeList.add(notice);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return noticeList;
	}

	@Override 
	public List<Menu> getLoginStaffNoticeMenu(){
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		//获得当前登录人的所有角色菜单
		Map<String,Object> map = staffService.getStaffMenuList(loginInfo.getTenantId(), loginInfo.getStaffId());
		List<Menu> menus=(List<Menu>) map.get("menus");
		return menus;
	}
	
	@Override
	public List<String> getNoticeMenuDateType(){
		//获取当前用户角色菜单列表
		List<Menu> menus =  this.getLoginStaffNoticeMenu();
		//存储签字标准dataType
		List<String> sNSDateType = new ArrayList<String>();
		try {
			if(menus!=null){
				for(Menu menu : menus){
					String dataType = signNoticeStandardService.getSNsMenuIdCashMapByKey(menu.getMenuId());
					if(StringUtil.isNotBlank(dataType)){
						sNSDateType.add(dataType);
					}
				}
			}
			return sNSDateType;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	/**
	 * 待办以及签字通知
	 */
	@Override
	public Map<String, Object> queryWorkNotices(PageSortReq pageSortReq) {
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		//StaffRole staffRole = staffRoleDao.queryStaffRoleInfo("", loginInfo.getStaffId());  // 提取员工角色
		List<Notice> listResult=new ArrayList<Notice>();
		//获取待办工作
		OperateRecordQueryReq req = new OperateRecordQueryReq();
		req.setOperaterId(loginInfo.getStaffId());						  //代办人为当前登录人
		req.setModifyStatus(OperateWorkFlowEnum.WAIT_DONE.getValue());//查询代办任务
		Map<String,Object> map = operateRecordService.queryOperateRecordList(req);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if(map!=null){
			List<OperateRecord> orList=(List<OperateRecord>) map.get("data");
			if(orList!=null && orList.size()>0){
				Notice notice=new Notice();
				notice.setNoticeTitle("工作通知");
				notice.setNoticeContent("您有  "+orList.size()+" 条  待办，请及时处理！");
				notice.setArea(sdf.format(projectDao.getDatabaseDate()));
				notice.setNoticeType("2");//工作通知
			}
		}
		
		List<Notice> listResultNew=new ArrayList<Notice>();
		//修改密码通知
		Notice modiftPasswordNotice=this.queryModifyPassword();
		
		if(modiftPasswordNotice!=null){
			listResultNew.add(modiftPasswordNotice);
		}
		
		
		List<SignNotice> noticeList=signNoticeDao.querySignNotice();
		if(noticeList!=null && noticeList.size()>0){
			Notice signNoticeNew=new Notice();
			signNoticeNew.setNoticeTitle("签字通知");
			signNoticeNew.setNoticeContent("您有新的签字通知，请点击待办事项查看！");
			signNoticeNew.setArea(sdf.format(projectDao.getDatabaseDate()));
			signNoticeNew.setNoticeType("3");//业务沟通通知
			listResultNew.add(signNoticeNew);
		}
		Map<String, Object> mapResult=new HashMap<String, Object>();
		mapResult.put("data", listResultNew);
		return mapResult;
	}
}
