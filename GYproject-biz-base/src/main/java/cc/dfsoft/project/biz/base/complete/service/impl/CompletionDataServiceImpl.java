package cc.dfsoft.project.biz.base.complete.service.impl;

import cc.dfsoft.project.biz.base.budget.dao.ChangeManagementDao;
import cc.dfsoft.project.biz.base.common.dao.ReportVersionDao;
import cc.dfsoft.project.biz.base.common.dto.ReportVersionReq;
import cc.dfsoft.project.biz.base.common.entity.ReportVersion;
import cc.dfsoft.project.biz.base.common.service.ReportVersionService;
import cc.dfsoft.project.biz.base.complete.dao.DivisionalAcceptanceDao;
import cc.dfsoft.project.biz.base.complete.dao.JointAcceptanceDao;
import cc.dfsoft.project.biz.base.complete.dao.PreinspectionDao;
import cc.dfsoft.project.biz.base.complete.dao.SelfInspectionListDao;
import cc.dfsoft.project.biz.base.complete.entity.DivisionalAcceptance;
import cc.dfsoft.project.biz.base.complete.entity.JointAcceptance;
import cc.dfsoft.project.biz.base.complete.entity.Preinspection;
import cc.dfsoft.project.biz.base.complete.entity.SelfInspectionList;
import cc.dfsoft.project.biz.base.complete.enums.AcceptanceTypeEnum;
import cc.dfsoft.project.biz.base.complete.service.CompletionDataService;
import cc.dfsoft.project.biz.base.constructmanage.dao.*;
import cc.dfsoft.project.biz.base.constructmanage.dto.CompletionDataPrintDto;
import cc.dfsoft.project.biz.base.constructmanage.entity.*;
import cc.dfsoft.project.biz.base.constructmanage.enums.ChangeTypeEnum;
import cc.dfsoft.project.biz.base.constructmanage.enums.StageProjectApplicationEnum;
import cc.dfsoft.project.biz.base.constructmanage.service.WorkReportService;
import cc.dfsoft.project.biz.base.contract.dao.ContractDao;
import cc.dfsoft.project.biz.base.contract.entity.Contract;
import cc.dfsoft.project.biz.base.design.enums.DesignChangeStateEnum;
import cc.dfsoft.project.biz.base.inspection.dao.MeasurementDao;
import cc.dfsoft.project.biz.base.inspection.dao.ProjectChecklistDao;
import cc.dfsoft.project.biz.base.inspection.entity.Measurement;
import cc.dfsoft.project.biz.base.inspection.entity.ProjectChecklist;
import cc.dfsoft.project.biz.base.inspection.enums.ProjectCheckListFlagEnum;
import cc.dfsoft.project.biz.base.inspection.enums.ProjectChecklistTypeEnum;
import cc.dfsoft.project.biz.base.plan.dao.ConstructionPlanDao;
import cc.dfsoft.project.biz.base.plan.entity.ConstructionPlan;
import cc.dfsoft.project.biz.base.project.dao.ProjectDao;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.settlement.dao.SettlementDeclarationDao;
import cc.dfsoft.project.biz.base.settlement.entity.SettlementDeclaration;
import cc.dfsoft.project.biz.base.subpackage.dao.SubBudgetDao;
import cc.dfsoft.project.biz.base.subpackage.dao.SubContractDao;
import cc.dfsoft.project.biz.base.subpackage.entity.SubBudget;
import cc.dfsoft.project.biz.base.subpackage.entity.SubContract;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.util.DateUtil;
import cc.dfsoft.uexpress.common.util.JsonDateValueProcessor;
import cc.dfsoft.uexpress.common.util.MoneyConverter;
import cc.dfsoft.uexpress.common.util.StringUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
/**
 * 
 * 描述:竣工资料打印业务实现类
 * @author liaoyq
 * @createTime 2017年9月28日
 */
@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class CompletionDataServiceImpl implements CompletionDataService {

	@Autowired
	ProjectDao projectDao;
	@Resource
	ConstructionPlanDao constructionPlanDao;
	@Resource
	ReportVersionDao reportVersionDao;
	@Resource
	ContractDao contractDao;
	@Resource
	SubContractDao subContractDao;
	@Resource
	SubBudgetDao subBudgetDao;
	@Resource
	SettlementDeclarationDao settlementDeclarationDao;
	@Resource
	ConstructionOrganizationDao constructionOrganizationDao;
	@Resource
	WorkReportDao workReportDao;
	@Resource
	JointAcceptanceDao jointAcceptanceDao;
	@Resource
	SelfInspectionListDao selfInspectionListDao;
	@Resource
	PreinspectionDao preinspectionDao;
	@Resource
	ConstructionWorkDao constructionWorkDao;
	@Resource
	DailyLogDao dailyLogDao;
	@Resource
	ChangeManagementDao changeManagementDao;
	@Resource
	EngineeringVisaDao engineeringVisaDao;
	@Resource
	CompleteReportDao  completeReportDao;
	@Resource
	DivisionalAcceptanceDao divisionalAcceptanceDao;
	@Resource
	MeasurementDao measurementDao;
	@Resource
	ProjectChecklistDao projectChecklistDao;
	@Resource
	WorkReportService workReportService;
	@Resource
	ReportVersionService reportVersionService;
    @Resource
    NdeRecordDao ndeRecordDao;


	@Override
	public List<Object> printCompletionData(String dataTypes, String projId) throws ParseException {
		String [] dataType = dataTypes.split(",");
		String res = "";
		List<Object> list = new ArrayList<Object>();
		if(dataType.length>0){
			Project pro = projectDao.get(projId);
			String corpid = pro.getCorpId();

			for(String type : dataType){
				if(type.equals(Constants.CON_TASK)) {//施工任务单
					res = printData(pro,type,Constants.COMPLETION_DATA,corpid);
					if(res!=null)list.add(res);
					continue;

				}else if(type.equals(Constants.CON_SU_TASK)){  //监理任务单
					res = printData(pro,type,Constants.COMPLETION_DATA,corpid);
					if(res!=null)list.add(res);
					continue;
				}else if(type.equals(Constants.CONTRACT)){  //安装合同
					res = printData(pro,type,Constants.COMPLETION_DATA,corpid);
					if(res!=null)list.add(res);
					continue;

				}else if(type.equals(Constants.SUB_CONTRACT)){//施工合同
					res = printData(pro,type,Constants.COMPLETION_DATA,corpid);
					if(res!=null)list.add(res);
					continue;

				}else if(type.equals(Constants.SU_BUDGET)){//施工预算
					res = printData(pro,type,Constants.COMPLETION_DATA,corpid);
					if(res!=null)list.add(res);
					continue;

				}else if(type.equals(Constants.SETTLEMENT)){ //结算
					res = printData(pro,type,Constants.COMPLETION_DATA,corpid);
					if(res!=null)list.add(res);
					continue;

				}else if(type.equals(Constants.CU_ORGANIZATION)){ //施工组织
					res = printData(pro,type,Constants.COMPLETION_DATA,corpid);
					if(res!=null)list.add(res);
					continue;

				}else if(type.equals(Constants.STARTING_REPORT)){ //开工报告
					res = printData(pro,type,Constants.COMPLETION_DATA,corpid);
					if(res!=null)list.add(res);
					continue;

				}else if(type.equals(Constants.ONE_STOP_ACCEPT)){//一站式打印
					res = printData(pro,type,Constants.COMPLETION_DATA,corpid);
					if(res!=null)list.add(res);
					continue;

				}else if(type.equals(Constants.SELF_CHECK)){//自检表
					res = printData(pro,type,Constants.COMPLETION_DATA,corpid);
					if(res!=null)list.add(res);
					continue;

				}else if(type.equals(Constants.JOINT_ACCEPT)){//联合验收单
					res = printData(pro,type,Constants.COMPLETION_DATA,corpid);
					if(res!=null)list.add(res);
					continue;

				}else if(type.equals(Constants.PRE_INSPECTION)){//预验收单
					res = printData(pro,type,Constants.COMPLETION_DATA,corpid);
					if(res!=null)list.add(res);
					continue;

				}else if(type.equals(Constants.TECHNOLOGY_TELL)){ //会审交底
					List<Object> re = printDataList(pro,type,Constants.COMPLETION_DATA,corpid);
					addObjectList(list,re);
					continue;

				}else if(type.equals(Constants.NDE_RECORD)){ //无损检测
					List<Object> re = printDataList(pro,type,Constants.COMPLETION_DATA,corpid);
					addObjectList(list,re);
					continue;

				}else if(type.equals(Constants.CU_DAIRY)){ //施工日志
					List<Object> re = printDataList(pro,type,Constants.COMPLETION_DATA,corpid);
					addObjectList(list,re);
					continue;

				}else if(type.equals(Constants.CHANGE_MENT)){ //变更记录
					List<Object> re = printDataList(pro,type,Constants.COMPLETION_DATA,corpid);
					addObjectList(list,re);
					continue;

				}else if(type.equals(Constants.VSIA_RECORD)){ //签证记录
					List<Object> re = printDataList(pro,type,Constants.COMPLETION_DATA,corpid);
					addObjectList(list,re);
					continue;

				}else if(type.equals(Constants.COMPLETE_REPORT)){//竣工报告
					List<Object> re = printDataList(pro,type,Constants.COMPLETION_DATA,corpid);
					addObjectList(list,re);
					continue;

				}else if(type.equals(Constants.DIVISIONAL_ACCEPT)){//分部验收单
					List<Object> re = printDataList(pro,type,Constants.COMPLETION_DATA,corpid);
					addObjectList(list,re);
					continue;

				}else if(type.equals(Constants.MEASUREMENT_RECORD)){ //计量记录
					List<Object> re = printDataList(pro,type,Constants.COMPLETION_DATA,corpid);
					addObjectList(list,re);
					continue;

				}else if(type.equals(Constants.ELECTRODE_RECORD)){ //焊条领用
					List<Object> re  = printDataListCheck(pro,type,Constants.COMPLETION_DATA,corpid,ProjectChecklistTypeEnum.ELECTRODE_RECORD.getValue());
					addObjectList(list,re);
					continue;

				}else if(type.equals(Constants.ELECTRODE_BACKING)){//焊条烘烤
					List<Object> re  = printDataListCheck(pro,type,Constants.COMPLETION_DATA,corpid,ProjectChecklistTypeEnum.ELECTRODE_BAKING.getValue());
					addObjectList(list,re);
					continue;

				}else if(type.equals(Constants.GROOVE_RECORD)){//管沟开挖
					List<Object> re  = printDataListCheck(pro,type,Constants.COMPLETION_DATA,corpid,ProjectChecklistTypeEnum.GROOVE_RECORD.getValue());
					addObjectList(list,re);
					continue;

				}else if(type.equals(Constants.PIPELINE_INSTALL)){//管道安装
					List<Object> re  = printDataListCheck(pro,type,Constants.COMPLETION_DATA,corpid,ProjectChecklistTypeEnum.PIPELINE_INSTALL.getValue());
					addObjectList(list,re);
					continue;

				}else if(type.equals(Constants.PIPE_WELDING)){//钢管焊接
					List<Object> re  = printDataListCheck(pro,type,Constants.COMPLETION_DATA,corpid,ProjectChecklistTypeEnum.PIPE_WELDING.getValue());
					addObjectList(list,re);
					continue;

				}else if(type.equals(Constants.PEPIPEWELDING)){//PE管焊接
					List<Object> re  = printDataListCheck(pro,type,Constants.COMPLETION_DATA,corpid,ProjectChecklistTypeEnum.PEPIPE_WELDING.getValue());
					addObjectList(list,re);
					continue;

				}else if(type.equals(Constants.PRESERVATIVE)){//防腐记录
					List<Object> re  = printDataListCheck(pro,type,Constants.COMPLETION_DATA,corpid,ProjectChecklistTypeEnum.PRESERVATIVE.getValue());
					addObjectList(list,re);
					continue;

				}else if(type.equals(Constants.PRESERVATIVE_INPECT)){//防腐检查
					List<Object> re  = printDataListCheck(pro,type,Constants.COMPLETION_DATA,corpid,ProjectChecklistTypeEnum.PRESERVATIVE_INPECT.getValue());
					addObjectList(list,re);
					continue;

				}else if(type.equals(Constants.HIDDEN_WORKS)){//隐蔽工程
					List<Object> re  = printDataListCheck(pro,type,Constants.COMPLETION_DATA,corpid,ProjectChecklistTypeEnum.HIDDEN_WORKS.getValue());
					addObjectList(list,re);
					continue;

				}else if(type.equals(Constants.PURGE)){//吹扫记录
					List<Object> re  = printDataListCheck(pro,type,Constants.COMPLETION_DATA,corpid,ProjectChecklistTypeEnum.PURGE.getValue());
					addObjectList(list,re);
					continue;

				}else if(type.equals(Constants.UNDERGROUND_INPECT)){//埋地检查
					List<Object> re  = printDataListCheck(pro,type,Constants.COMPLETION_DATA,corpid,ProjectChecklistTypeEnum.UNDER_GROUND_INPECT.getValue());
					addObjectList(list,re);
					continue;

				}else if(type.equals(Constants.TRENCH_BACKFILL)){//沟槽回填
					List<Object> re  = printDataListCheck(pro,type,Constants.COMPLETION_DATA,corpid,ProjectChecklistTypeEnum.TRENCH_BACK_FILL.getValue());
					addObjectList(list,re);
					continue;

				}else if(type.equals(Constants.BALL_RECORD)){//通球记录
					List<Object> re  = printDataListCheck(pro,type,Constants.COMPLETION_DATA,corpid,ProjectChecklistTypeEnum.BALL_RECORD.getValue());
					addObjectList(list,re);
					continue;

				}else if(type.equals(Constants.INDOOR_POCKET_WATCH)){//户内挂表
					List<Object> re  = printDataListCheck(pro,type,Constants.COMPLETION_DATA,corpid,ProjectChecklistTypeEnum.INDOOR_POCKET_WATCH.getValue());
					addObjectList(list,re);
					continue;

				}else if(type.equals(Constants.EQUIPMENT_INSTALL)){//设备安装
					List<Object> re  = printDataListCheck(pro,type,Constants.COMPLETION_DATA,corpid,ProjectChecklistTypeEnum.EQUIPMENT_INSTALL.getValue());
					addObjectList(list,re);
					continue;

				}else if(type.equals(Constants.HOTMELT_DOCKING)){//热熔对接
					List<Object> re  = printDataListCheck(pro,type,Constants.COMPLETION_DATA,corpid,ProjectChecklistTypeEnum.HOT_MELT_DOCKING.getValue());
					addObjectList(list,re);
					continue;

				}else if(type.equals(Constants.ANODE_INSTALL)){//阳极安装
					List<Object> re  = printDataListCheck(pro,type,Constants.COMPLETION_DATA,corpid,ProjectChecklistTypeEnum.ANODE_INSTALL.getValue());
					addObjectList(list,re);
					continue;

				}else if(type.equals(Constants.WELD_LINE_INPECT)){//管道焊缝检查
					List<Object> re  = printDataListCheck(pro,type,Constants.COMPLETION_DATA,corpid,ProjectChecklistTypeEnum.WELD_LINE_INPECT.getValue());
					addObjectList(list,re);
					continue;

				}else if(type.equals(Constants.PE_WELD_LINE_INPECT)){//PE管焊缝检查
					List<Object> re  = printDataListCheck(pro,type,Constants.COMPLETION_DATA,corpid,ProjectChecklistTypeEnum.PE_WELD_LINE_INPECT.getValue());
					addObjectList(list,re);
					continue;

				}else if(type.equals(Constants.PIPE_WELD_RECORD)){//焊口记录
					List<Object> re  = printDataListCheck(pro,type,Constants.COMPLETION_DATA,corpid,ProjectChecklistTypeEnum.PIPE_WELD_RECORD.getValue());
					addObjectList(list,re);
					continue;

				}else if(type.equals(Constants.CLEAR_RECORD)){//清扫记录
					List<Object> re  = printDataListCheck(pro,type,Constants.COMPLETION_DATA,corpid,ProjectChecklistTypeEnum.CLEAR_RECORD.getValue());
					addObjectList(list,re);
					continue;

				}else if(type.equals(Constants.STRENTH_TEST)){//强度试验
					List<Object> re  = printDataListCheck(pro,type,Constants.COMPLETION_DATA,corpid,ProjectChecklistTypeEnum.STRENGTH_TEST.getValue());
					addObjectList(list,re);
					continue;
				}
			}

		}
		return list;
	}



	public void addObjectList(List<Object> resultList,List<Object> list){
		if(list!=null && list.size()>0){
			for (Object obj : list){
				resultList.add(obj);
			}
		}
	}












	/**
	 * 整理数据
	 * @return
	 */
	public String printData(Project project, String type,String pid,String corpid) throws ParseException {
		String result = "";
		String arrayStr = "";
		String param = "";
		Map<String,Object> map = Constants.getConsData(pid,type,corpid);
		if(map !=null){
			arrayStr = String.valueOf(map.get("CNVALUE"));
			param = String.valueOf(map.get("RESERVE2"));
		}else{
			//默认或null
			map = Constants.getConsByKeyAndId(pid,type);
			if(map != null){
				arrayStr = String.valueOf(map.get("CNVALUE"));
				param = String.valueOf(map.get("RESERVE2"));
			}else{
				return null;
			}
		}
		if(type.equals(Constants.CON_TASK)){    //施工任务单
			String rvId = "";
			// 根据工程ID查询任务单信息
			ConstructionPlan constructionPlan = constructionPlanDao.findByProjId(project.getProjId());
			net.sf.json.JSONArray jsonArray = net.sf.json.JSONArray.fromObject(arrayStr);
			if (jsonArray != null && jsonArray.size() > 0 && constructionPlan != null) {
				net.sf.json.JSONObject jsonObject = net.sf.json.JSONObject.fromObject(jsonArray.get(0));
				CompletionDataPrintDto dto = (CompletionDataPrintDto) net.sf.json.JSONObject.toBean(jsonObject,
						CompletionDataPrintDto.class);
				// 根据公司ID、菜单ID去查找cpt文件名
				String menuId = type; // 获取菜单
				ReportVersionReq reportVersionReq = new ReportVersionReq();  //创建实体
				if(StringUtils.isNotBlank(menuId) && StringUtils.isNotBlank(constructionPlan.getCorpId())){
					reportVersionReq.setCorpId(constructionPlan.getCorpId());
					reportVersionReq.setMenuId(menuId);
				}
				try {
					List<ReportVersion> versions = reportVersionDao.queryReportVersions(reportVersionReq);  //根据实体reportVersionReq查找报表版本
					Object obj = null;
				if(versions != null && versions.size() > 0){  //判断是否报表版本
					rvId = versions.get(0).getRvId();
					String key = constructionPlan.getCorpId()+"_"+menuId+"_"+rvId;   //组装key值进行报表查找
				    obj = Constants.getSysConfigByKey(key);
				}
					if(obj !=null){
						int beginIndex = dto.getReportlet().indexOf("/");
						int endIndex = dto.getReportlet().lastIndexOf("/");
						String reportlet = dto.getReportlet().substring(beginIndex, endIndex+1);  //截取字符'/'之间的字符串
						dto.setReportlet(reportlet+obj.toString());
					}
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				JSONObject	json = JsonDateValueProcessor.JsonFomatDate(constructionPlan,"yyyy-MM-dd");
				//result = "{reportlet:'" + dto.getReportlet();// + "',planId:'" + constructionPlan.getCpId()+"'}";
				result = "{reportlet:'"+dto.getReportlet()+"',imgUrl:'"+Constants.DISK_PATH+Constants.SIGN_DISK_PATH;

				String str = assemblyParam(param,json);
				if(str != null){
					result  += str+"'}";
					return result;
				}

			}
		}else if(type.equals(Constants.CON_SU_TASK)){    //监理任务单
			String rvId = "";
			// 根据工程ID查询任务单信息
			ConstructionPlan constructionPlan = constructionPlanDao.findByProjId(project.getProjId());
			net.sf.json.JSONArray jsonArray = net.sf.json.JSONArray.fromObject(arrayStr);
			if (jsonArray != null && jsonArray.size() > 0 && constructionPlan != null) {
				net.sf.json.JSONObject jsonObject = net.sf.json.JSONObject.fromObject(jsonArray.get(0));
				CompletionDataPrintDto dto = (CompletionDataPrintDto) net.sf.json.JSONObject.toBean(jsonObject,
						CompletionDataPrintDto.class);
				// 根据公司ID、菜单ID去查找cpt文件名
				String menuId = type; // 获取菜单
				ReportVersionReq reportVersionReq = new ReportVersionReq();  //创建实体
				if(StringUtils.isNotBlank(menuId) && StringUtils.isNotBlank(constructionPlan.getCorpId())){
					reportVersionReq.setCorpId(constructionPlan.getCorpId());
					reportVersionReq.setMenuId(menuId);
				}
				try {
					List<ReportVersion> versions = reportVersionDao.queryReportVersions(reportVersionReq);  //根据实体reportVersionReq查找报表版本
					Object obj = null;
				if(versions != null && versions.size() > 0){  //判断是否报表版本
					rvId = versions.get(0).getRvId();
					String key = constructionPlan.getCorpId()+"_"+menuId+"_"+rvId;   //组装key值进行报表查找
				    obj = Constants.getSysConfigByKey(key);
				}else{//没有版本则根据一下规则获取
					String key = constructionPlan.getCorpId()+"_"+menuId+"_"+constructionPlan.getCorpId();   //组装key值进行报表查找
				    obj = Constants.getSysConfigByKey(key);
				}
					if(obj !=null){
						int beginIndex = dto.getReportlet().indexOf("/");
						int endIndex = dto.getReportlet().lastIndexOf("/");
						String reportlet = dto.getReportlet().substring(beginIndex, endIndex+1);  //截取字符'/'之间的字符串
						dto.setReportlet(reportlet+obj.toString());
					}
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				JSONObject	json = JsonDateValueProcessor.JsonFomatDate(constructionPlan,"yyyy-MM-dd");
				
				//result = "{reportlet:'" + dto.getReportlet();// + "',planId:'" + constructionPlan.getCpId()+"'}";
				result = "{reportlet:'"+dto.getReportlet()+"',imgUrl:'"+Constants.DISK_PATH+Constants.SIGN_DISK_PATH;

				String str = assemblyParam(param,json);
				if(str != null){
					result  += str+"'}";
					return result;
				}
			}
		}else if(type.equals(Constants.CONTRACT)){  //安装合同
			//根据工程ID查询合同信息
			Contract contract = contractDao.viewContractByprojId(project.getProjId());
			JSONArray jsonArray=JSONArray.fromObject(arrayStr);
			if(jsonArray!=null&&jsonArray.size()>0 && contract!=null){
				for(Object obj : jsonArray){
					JSONObject jsonObject=JSONObject.fromObject(obj);
					CompletionDataPrintDto dto = (CompletionDataPrintDto)JSONObject.toBean(jsonObject, CompletionDataPrintDto.class);
					if(dto.getType()!=null && dto.getType().equals(contract.getContractType())){
						result = "{reportlet:'"+dto.getReportlet();//+"',projName:'"+contract.getProjName()+"',conId:'"+contract.getConId()+"',projId:'"+contract.getProjId();
						JSONObject	json = JsonDateValueProcessor.JsonFomatDate(contract,"yyyy-MM-dd");
						String str = assemblyParam(param,json);
						if(str != null){
							result  += str;
						}
						if(contract.getContractAmount()!=null){
							result += "',legalAmount:'"+ MoneyConverter.Num2RMB(contract.getContractAmount().doubleValue());
						}
						if(contract.getFirstPayment()!=null){
							result += "',legalFirstPayment:'"+MoneyConverter.Num2RMB(contract.getFirstPayment().doubleValue());
						}
						if(contract.getSecondPayment()!=null){
							result += "',legalSecondPaymentAmount:'"+MoneyConverter.Num2RMB(contract.getSecondPayment().doubleValue());
						}
						if(contract.getThirdPayment()!=null){
							result += "',legalThirdPaymentAmount:'"+MoneyConverter.Num2RMB(contract.getThirdPayment().doubleValue());
						}
						result += "'}";
						return result;
					}
				}
			}
		}else if(type.equals(Constants.SUB_CONTRACT)){   //施工合同
			//根据工程ID查询合同信息
			SubContract suCon = subContractDao.findSubContractByprojId(project.getProjId());
			if(suCon != null && suCon.getScAmount() != null){  //判断施工合同不为空
				suCon.setLegalAmount(MoneyConverter.Num2RMB(suCon.getScAmount().doubleValue()));
			}
			JSONArray jsonArray=JSONArray.fromObject(arrayStr);
			if(jsonArray!=null&&jsonArray.size()>0 && suCon != null && project!=null){
				for(Object obj : jsonArray){
					JSONObject jsonObject=JSONObject.fromObject(obj);
					CompletionDataPrintDto dto = (CompletionDataPrintDto)JSONObject.toBean(jsonObject, CompletionDataPrintDto.class);
					if(dto.getType()!=null && dto.getType().equals(project.getProjLtypeId())){
						String[] menuIdAndMark = type.split("-"); // 分解施工任务单标识符和菜单ID,从而取得菜单ID
						String menuId = menuIdAndMark[menuIdAndMark.length-1]; // 获取菜单id
						ReportVersionReq reportVersionReq = new ReportVersionReq();  //创建实体
						//若菜单ID、工程类型、公司ID不为空则
						if(StringUtils.isNotBlank(menuId) && StringUtils.isNotBlank(project.getCorpId()) && StringUtils.isNotBlank(project.getProjectType())){
							reportVersionReq.setCorpId(project.getCorpId());
							reportVersionReq.setMenuId(menuId);
							reportVersionReq.setProjType(project.getProjectType());
						}
						try {
							List<ReportVersion> versions = reportVersionDao.queryReportVersions(reportVersionReq);  //根据实体reportVersionReq查找报表版本
							String rvId = null;
							if(versions != null && versions.size() > 0){
								 rvId = versions.get(0).getRvId();
							}
							String key = project.getProjectType()+"_"+project.getCorpId()+"_"+menuId+"_"+rvId;   //组装key值进行报表查找
							Object reportVersion = Constants.getSysConfigByKey(key);
							if(reportVersion !=null){
								//记录特定字符索引
								int beginIndex = dto.getReportlet().indexOf("/");
								int endIndex = dto.getReportlet().lastIndexOf("/");
								String reportlet = dto.getReportlet().substring(beginIndex, endIndex+1);  //截取字符'/'之间的字符串
								dto.setReportlet(reportlet+reportVersion.toString());   //若reportVersion不为空则重新设置报表路径
							}
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							System.err.println("竣工资料打印--施工合同打印版本查询失败!");
						}
						JSONObject	json = JsonDateValueProcessor.JsonFomatDate(suCon,"yyyy-MM-dd");
						result = "{reportlet:'"+dto.getReportlet()+"',imgUrl:'"+Constants.DISK_PATH+Constants.SIGN_DISK_PATH;
						String str = assemblyParam(param,json);
						if(str != null){
							result += str;
						}

						return result+"'}";
					}
				}

			}
		}else if(type.equals(Constants.SU_BUDGET)){  //施工预算
			//根据工程ID查询合同信息
			SubBudget subBudget= subBudgetDao.findByProjId(project.getProjId()); //根据工程ID取得实体
			JSONArray jsonArray=JSONArray.fromObject(arrayStr);
			if(jsonArray!=null&&jsonArray.size()>0 && subBudget!=null && project !=null){
				for(Object obj : jsonArray){
					JSONObject jsonObject=JSONObject.fromObject(obj);
					CompletionDataPrintDto dto = (CompletionDataPrintDto)JSONObject.toBean(jsonObject, CompletionDataPrintDto.class);
					String menuId =type; // 获取菜单id
					String key = project.getContributionMode()+"_"+project.getCorpId()+"_"+menuId;
					ReportVersionReq reportVersionReq = new ReportVersionReq();  //创建实体
					//若菜单ID、工程类型、公司ID不为空则
					if(StringUtils.isNotBlank(menuId) && StringUtils.isNotBlank(project.getCorpId()) && StringUtils.isNotBlank(project.getProjectType())){
						reportVersionReq.setCorpId(project.getCorpId());
						reportVersionReq.setMenuId(menuId);
						reportVersionReq.setProjType(project.getContributionMode());
						SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
						reportVersionReq.setSignDate((subBudget!=null && subBudget.getOperateDate()!=null)?format.format(subBudget.getOperateDate()):"");
					}
					List<ReportVersion> versions = reportVersionDao.queryReportVersions(reportVersionReq);  //根据实体reportVersionReq查找报表版本
					if(versions != null && versions.size() > 0 && StringUtil.isNotBlank(versions.get(0).getRvId())){
						 key =key+"_"+versions.get(0).getRvId();
					}
					Object reportVersion = Constants.getSysConfigByKey(key);
					if(reportVersion !=null){
						//记录特定字符索引
						int beginIndex = dto.getReportlet().indexOf("/");
						int endIndex = dto.getReportlet().lastIndexOf("/");
						String reportlet = dto.getReportlet().substring(beginIndex, endIndex+1);  //截取字符'/'之间的字符串
						dto.setReportlet(reportlet+reportVersion.toString());   //若reportVersion不为空则重新设置报表路径
					}
					result = "{reportlet:'"+dto.getReportlet()+"',imgUrl:'"+Constants.DISK_PATH+Constants.SIGN_DISK_PATH;
					JSONObject	json = JsonDateValueProcessor.JsonFomatDate(subBudget,"yyyy-MM-dd");
					String str = assemblyParam(param,json);
					if(str != null){
						result  += str;
					}
					return result+"'}";

				}
			}
		}else if(type.equals(Constants.SETTLEMENT)){  //工程结算书
			SettlementDeclaration sd= settlementDeclarationDao.findByProjId(project.getProjId());
			JSONArray jsonArray=JSONArray.fromObject(arrayStr);
			if(jsonArray!=null&&jsonArray.size()>0 && sd!=null  && project !=null){
				for(Object obj : jsonArray){
					JSONObject jsonObject=JSONObject.fromObject(obj);
					CompletionDataPrintDto dto = (CompletionDataPrintDto)JSONObject.toBean(jsonObject, CompletionDataPrintDto.class);
					String menuId =type; // 获取菜单id
					String key = project.getContributionMode()+"_"+project.getCorpId()+"_"+menuId;
					ReportVersionReq reportVersionReq = new ReportVersionReq();  //创建实体
					//若菜单ID、工程类型、公司ID不为空则
					if(StringUtils.isNotBlank(menuId) && StringUtils.isNotBlank(project.getCorpId()) && StringUtils.isNotBlank(project.getProjectType())){
						reportVersionReq.setCorpId(project.getCorpId());
						reportVersionReq.setMenuId(menuId);
						reportVersionReq.setProjType(project.getContributionMode());
						SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
						reportVersionReq.setSignDate((sd!=null && sd.getOcoDate()!=null)?format.format(sd.getOcoDate()):"");
					}
					List<ReportVersion> versions = reportVersionDao.queryReportVersions(reportVersionReq);  //根据实体reportVersionReq查找报表版本
					if(versions != null && versions.size() > 0 && StringUtil.isNotBlank(versions.get(0).getRvId())){
						 key =key+"_"+versions.get(0).getRvId();
					}
					Object reportVersion = Constants.getSysConfigByKey(key);
					if(reportVersion !=null){
						//记录特定字符索引
						int beginIndex = dto.getReportlet().indexOf("/");
						int endIndex = dto.getReportlet().lastIndexOf("/");
						String reportlet = dto.getReportlet().substring(beginIndex, endIndex+1);  //截取字符'/'之间的字符串
						dto.setReportlet(reportlet+reportVersion.toString());   //若reportVersion不为空则重新设置报表路径
					}
					result = "{reportlet:'"+dto.getReportlet()+"',imgUrl:'"+Constants.DISK_PATH+Constants.SIGN_DISK_PATH;//+"',projId:'"+sd.getProjId()+"',sdId:'"+sd.getSdId()+"',imgUrl:'"+Constants.DISK_PATH+Constants.SIGN_DISK_PATH+"'}";
					JSONObject	json = JsonDateValueProcessor.JsonFomatDate(sd,"yyyy-MM-dd");
					String str = assemblyParam(param,json);
					if(str != null){
						result  += str;
					}
					return result+"'}";
				}
			}
		}else if(type.equals(Constants.CU_ORGANIZATION)){ //施工组织
			//根据工程ID查询施工组织信息
			List<ConstructionOrganization> coList= constructionOrganizationDao.findbyProjId(project.getProjId());
			ConstructionOrganization co =null;
			if(coList!=null && coList.size()>0){
				co = new ConstructionOrganization();
				co = coList.get(0);
			}
			net.sf.json.JSONArray jsonArray=net.sf.json.JSONArray.fromObject(arrayStr);
			if(jsonArray!=null&&jsonArray.size()>0 && co!=null && project !=null){
				net.sf.json.JSONObject jsonObject=net.sf.json.JSONObject.fromObject(jsonArray.get(0));
				CompletionDataPrintDto dto = (CompletionDataPrintDto)net.sf.json.JSONObject.toBean(jsonObject, CompletionDataPrintDto.class);
				String menuId = type; // 获取菜单id
				String key = project.getProjectType()+"_"+project.getCorpId()+"_"+menuId;
				Object reportVersion = Constants.getSysConfigByKey(key);
				if(reportVersion !=null){
					//记录特定字符索引
					int beginIndex = dto.getReportlet().indexOf("/");
					int endIndex = dto.getReportlet().lastIndexOf("/");
					String reportlet = dto.getReportlet().substring(beginIndex, endIndex+1);  //截取字符'/'之间的字符串
					dto.setReportlet(reportlet+reportVersion.toString());   //若reportVersion不为空则重新设置报表路径
				}
				result += "{reportlet:'"+dto.getReportlet()+"',imgUrl:'" + Constants.DISK_PATH+Constants.SIGN_DISK_PATH;//+"',coId:'" +co.getCoId()+ "',projName:'"+co.getProjName()+"',suName:'"+co.getSuName()+"',imgUrl:'" + Constants.DISK_PATH+Constants.SIGN_DISK_PATH + "'}";
				JSONObject	json = JsonDateValueProcessor.JsonFomatDate(co,"yyyy-MM-dd");
				String str = assemblyParam(param,json);
				if(str != null){
					result  += str;
				}
				return result+"'}";
			}
		}else if(type.equals(Constants.STARTING_REPORT)){  //开工报告
			//根据工程ID查询交底信息
			List<WorkReport> wrList= workReportDao.findByProjId(project.getProjId(), null);
			WorkReport wr =null;
			if(wrList!=null && wrList.size()>0){
				wr =  new WorkReport();
				wr = wrList.get(0);
			}
			net.sf.json.JSONArray jsonArray=net.sf.json.JSONArray.fromObject(arrayStr);
			if(jsonArray!=null&&jsonArray.size()>0 && wr!=null && project !=null){
				net.sf.json.JSONObject jsonObject=net.sf.json.JSONObject.fromObject(jsonArray.get(0));
				CompletionDataPrintDto dto = (CompletionDataPrintDto)net.sf.json.JSONObject.toBean(jsonObject, CompletionDataPrintDto.class);
				String menuId = type; // 获取菜单id
				String key = project.getProjectType()+"_"+project.getCorpId()+"_"+menuId;
				Object reportVersion = Constants.getSysConfigByKey(key);
				//记录特定字符索引
				int beginIndex = dto.getReportlet().indexOf("/");
				int endIndex = dto.getReportlet().lastIndexOf("/");
				String reportlet = dto.getReportlet().substring(beginIndex, endIndex+1);  //截取字符'/'之间的字符串
				if(reportVersion !=null ){
					dto.setReportlet(reportlet+reportVersion.toString());   //若reportVersion不为空则重新设置报表路径
				}else{
					//没有配置-取全局
					String cptUrl = workReportService.findCptUrl(project,Constants.STARTREPORT_MENUID,wr.getPlannedStartDate().toString());
					dto.setReportlet(reportlet+cptUrl);   //若reportVersion不为空则重新设置报表路径
				}
				result += "{reportlet:'"+dto.getReportlet()+"',imgUrl:'" + Constants.DISK_PATH+Constants.SIGN_DISK_PATH;//+"',projName:'"+wr.getProjName()+"',suName:'"+wr.getSuName()+"',projAddr:'"+wr.getProjAddr()
						//+"',constructionUnit:'"+wr.getConstructionUnit()+"',custName:'"+wr.getCustName()+"',corpName='"+wr.getCorpName()+"',wrId:'"+wr.getWrId()
						//+"',imgUrl:'" + Constants.DISK_PATH+Constants.SIGN_DISK_PATH + "'}";
				JSONObject	json = JsonDateValueProcessor.JsonFomatDate(wr,"yyyy-MM-dd");
				String str = assemblyParam(param,json);
				if(str != null){
					result  += str;
				}
				return result+"'}";
			}
		}else if(type.equals(Constants.ONE_STOP_ACCEPT)){  //一站式打印
			//一站式打印
			List<JointAcceptance> wrList= jointAcceptanceDao.findByProjIdAndType(project.getProjId(), AcceptanceTypeEnum.ONE_STOP_ACCEPTANCE.getValue());
			JointAcceptance jointAccept =null;
			if(wrList!=null && wrList.size()>0){
				jointAccept =  new JointAcceptance();
				jointAccept = wrList.get(0);
			}
			net.sf.json.JSONArray jsonArray=net.sf.json.JSONArray.fromObject(arrayStr);
			if(jsonArray!=null&&jsonArray.size()>0 && jointAccept!=null && project !=null){
				net.sf.json.JSONObject jsonObject=net.sf.json.JSONObject.fromObject(jsonArray.get(0));
				CompletionDataPrintDto dto = (CompletionDataPrintDto)net.sf.json.JSONObject.toBean(jsonObject, CompletionDataPrintDto.class);
				String menuId = type; // 获取菜单id
				String key = project.getCorpId()+"_"+project.getProjectType()+"_"+menuId;
				Object reportVersion = Constants.getSysConfigByKey(key);
				if(reportVersion !=null){
					//记录特定字符索引
					int beginIndex = dto.getReportlet().indexOf("/");
					int endIndex = dto.getReportlet().lastIndexOf("/");
					String reportlet = dto.getReportlet().substring(beginIndex, endIndex+1);  //截取字符'/'之间的字符串
					dto.setReportlet(reportlet+reportVersion.toString());   //若reportVersion不为空则重新设置报表路径
				}
				result = "{reportlet:'"+dto.getReportlet()+"',imgUrl:'" + Constants.DISK_PATH+Constants.SIGN_DISK_PATH;//"',projName:'"+jointAccept.getProjName()+"',projId:'"+jointAccept.getProjId()+"',jaId:'"+jointAccept.getJaId()
						//+"',imgUrl:'" + Constants.DISK_PATH+Constants.SIGN_DISK_PATH + "'}";
				JSONObject	json = JsonDateValueProcessor.JsonFomatDate(jointAccept,"yyyy-MM-dd");
				String str = assemblyParam(param,json);
				if(str != null){
					result  += str;
				}
				return result+"'}";
			}
		}else if(type.equals(Constants.SELF_CHECK)){ //自检表
			//自检表
			List<SelfInspectionList> selfList = selfInspectionListDao.findByprojectId(project.getProjId());
			SelfInspectionList selfIn = null;
			if (selfList != null && selfList.size() > 0) {
				selfIn = new SelfInspectionList();
				selfIn = selfList.get(0);
			}
			net.sf.json.JSONArray jsonArray = net.sf.json.JSONArray.fromObject(arrayStr);
			if (jsonArray != null && jsonArray.size() > 0 && selfIn != null && project !=null) {
				net.sf.json.JSONObject jsonObject = net.sf.json.JSONObject.fromObject(jsonArray.get(0));
				CompletionDataPrintDto dto = (CompletionDataPrintDto) net.sf.json.JSONObject.toBean(jsonObject,
						CompletionDataPrintDto.class);
				String menuId = type; // 获取菜单id
				String key = project.getProjectType()+"_"+project.getCorpId()+"_"+menuId;
				Object reportVersion = Constants.getSysConfigByKey(key);
				if(reportVersion !=null){
					//记录特定字符索引
					int beginIndex = dto.getReportlet().indexOf("/");
					int endIndex = dto.getReportlet().lastIndexOf("/");
					String reportlet = dto.getReportlet().substring(beginIndex, endIndex+1);  //截取字符'/'之间的字符串
					dto.setReportlet(reportlet+reportVersion.toString());   //若reportVersion不为空则重新设置报表路径
				}
				result = "{reportlet:'" + dto.getReportlet() + "',path:'" + Constants.DISK_PATH + Constants.SIGN_DISK_PATH;//"',silId:'" + selfIn.getSilId() + "',projId:'"
						//+ selfIn.getProjId() + "',path:'" + Constants.DISK_PATH + Constants.SIGN_DISK_PATH + "'}";
				JSONObject	json = JsonDateValueProcessor.JsonFomatDate(selfIn,"yyyy-MM-dd");
				String str = assemblyParam(param,json);
				if(str != null){
					result  += str;
				}
				return result+"'}";
			}
		}else if(type.equals(Constants.JOINT_ACCEPT)){  //联合验收单
			//联合验收单
			List<JointAcceptance> wrList= jointAcceptanceDao.findByProjIdAndType(project.getProjId(), AcceptanceTypeEnum.JOINT_ACCEPTANCE.getValue());
			JointAcceptance jointAccept =null;
			if(wrList!=null && wrList.size()>0){
				jointAccept =  new JointAcceptance();
				jointAccept = wrList.get(0);
			}
			net.sf.json.JSONArray jsonArray=net.sf.json.JSONArray.fromObject(arrayStr);
			if(jsonArray!=null&&jsonArray.size()>0 && jointAccept!=null && project !=null){
				net.sf.json.JSONObject jsonObject=net.sf.json.JSONObject.fromObject(jsonArray.get(0));
				CompletionDataPrintDto dto = (CompletionDataPrintDto)net.sf.json.JSONObject.toBean(jsonObject, CompletionDataPrintDto.class);
				String menuId = type; // 获取菜单id
				String key = project.getCorpId()+"_"+project.getProjectType()+"_"+menuId;
				Object reportVersion = Constants.getSysConfigByKey(key);
				if(reportVersion !=null){
					//记录特定字符索引
					int beginIndex = dto.getReportlet().indexOf("/");
					int endIndex = dto.getReportlet().lastIndexOf("/");
					String reportlet = dto.getReportlet().substring(beginIndex, endIndex+1);  //截取字符'/'之间的字符串
					dto.setReportlet(reportlet+reportVersion.toString());   //若reportVersion不为空则重新设置报表路径
				}
				result = "{reportlet:'"+dto.getReportlet()+"',imgUrl:'" + Constants.DISK_PATH+Constants.SIGN_DISK_PATH;//+"',projName:'"+jointAccept.getProjName()+"',projId:'"+jointAccept.getProjId()+"',jaId:'"+jointAccept.getJaId()
						//+"',imgUrl:'" + Constants.DISK_PATH+Constants.SIGN_DISK_PATH + "'}";
				JSONObject	json = JsonDateValueProcessor.JsonFomatDate(jointAccept,"yyyy-MM-dd");
				String str = assemblyParam(param,json);
				if(str != null){
					result  += str;
				}
				return result+"'}";
			}
		}else if(type.equals(Constants.PRE_INSPECTION)){  //预验收单
			//预验收单
			String corpId = project.getCorpId();
			Preinspection preinspection = preinspectionDao.findByProjId(project.getProjId());
			List<WorkReport> workReport = workReportDao.findByProjId(project.getProjId(),null);  //开工报告
			net.sf.json.JSONArray jsonArray=net.sf.json.JSONArray.fromObject(arrayStr);
			if(jsonArray!=null&&jsonArray.size()>0 && preinspection!=null && project !=null){
				ReportVersionReq reportVersionReq = new ReportVersionReq();
				List<ReportVersion> versions = new ArrayList<ReportVersion>();
				SimpleDateFormat formatDate=new SimpleDateFormat("yyyy-MM-dd");  //格式化日期格式
				
				String rvId = null;
				Object reportVersion = null;
				reportVersionReq.setMenuId(type);  //菜单ID
				reportVersionReq.setSignDate(formatDate.format(workReport.get(0).getPlannedStartDate()));  //取得计划开始日期
				reportVersionReq.setProjType(project.getProjectType());  //工程类型
				reportVersionReq.setCorpId(corpId);  //公司ID
				try {
					    versions = reportVersionService.queryReportVersions(reportVersionReq);  //先查所属公司是否有配置
					if(versions != null && versions.size() >0){
						rvId = versions.get(0).getRvId(); //取得版本主键
					}else{
						corpId = Constants.START_REPORT_CPT_CORP_MODE;
						reportVersionReq.setCorpId(corpId);  //默认贵阳公司
						versions = reportVersionService.queryReportVersions(reportVersionReq);
						if(versions.size() > 0){
							rvId = versions.get(0).getRvId(); //取得版本主键
						}
					}
					
					String key =project.getProjectType()+"_"+ corpId+ "_" + type+ "_" + rvId;  //根据主键查找报表名称
					reportVersion = Constants.getSysConfigByKey(key);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				net.sf.json.JSONObject jsonObject=net.sf.json.JSONObject.fromObject(jsonArray.get(0));
				CompletionDataPrintDto dto = (CompletionDataPrintDto)net.sf.json.JSONObject.toBean(jsonObject, CompletionDataPrintDto.class);
				if(reportVersion !=null){
					//记录特定字符索引
					int beginIndex = dto.getReportlet().indexOf("/");
					int endIndex = dto.getReportlet().lastIndexOf("/");
					String reportlet = dto.getReportlet().substring(beginIndex, endIndex+1);  //截取字符'/'之间的字符串
					dto.setReportlet(reportlet+reportVersion.toString());   //若reportVersion不为空则重新设置报表路径
				}
				result = "{reportlet:'"+dto.getReportlet()+"',path:'" + Constants.DISK_PATH+Constants.SIGN_DISK_PATH;//+"',piId:'"+preinspection.getPiId()+"',projId:'"+preinspection.getProjId()
				JSONObject	json = JsonDateValueProcessor.JsonFomatDate(preinspection,"yyyy-MM-dd");
				String str = assemblyParam(param,json);
				if(str != null){
					result  += str;
				}
				return result+"'}";
			}
		}
		return null;
	}

	/**
	 * 整理多张单据
	 * @param project
	 * @param type
	 * @param pid
	 * @param corpid
	 * @return
	 */
	public List<Object> printDataList(Project project, String type,String pid,String corpid){
		String result ="";
		String arrayStr = "";
		String param = "";
		Map<String,Object> map = Constants.getConsData(pid,type,corpid);
		if(map !=null){
			arrayStr = String.valueOf(map.get("CNVALUE"));
			param = String.valueOf(map.get("RESERVE2"));
		}else{
			//默认或null
			map = Constants.getConsByKeyAndId(pid,type);
			if(map != null){
				arrayStr = String.valueOf(map.get("CNVALUE"));
				param = String.valueOf(map.get("RESERVE2"));
			}else{
				return null;
			}
		}
	    if(type.equals(Constants.NDE_RECORD)){  //无损检测
	    	//无损检测
			List<Object> list = new ArrayList<Object>();
			//多条无损检测
			List<NdeRecord> ndes = ndeRecordDao.findNdeBypProjId(project.getProjId());
			JSONArray jsonArray=JSONArray.fromObject(arrayStr);
			if(jsonArray !=null && jsonArray.size()>0 && ndes !=null && ndes.size()>0 && project != null){
				JSONObject jsonObject=JSONObject.fromObject(jsonArray.get(0));
				CompletionDataPrintDto dto = (CompletionDataPrintDto)JSONObject.toBean(jsonObject, CompletionDataPrintDto.class);
				String menuId = type; // 获取菜单id
				for(NdeRecord nde : ndes){
					String key = project.getCorpId()+"_"+project.getProjectType()+"_"+menuId;
					Object reportVersion = Constants.getSysConfigByKey(key);
					if(reportVersion !=null){
						//记录特定字符索引
						int beginIndex = dto.getReportlet().indexOf("/");
						int endIndex = dto.getReportlet().lastIndexOf("/");
						String reportlet = dto.getReportlet().substring(beginIndex, endIndex+1);  //截取字符'/'之间的字符串
						dto.setReportlet(reportlet+reportVersion.toString());   //若reportVersion不为空则重新设置报表路径
					}
					result = "{reportlet:'"+dto.getReportlet()+"',imgUrl:'" + Constants.DISK_PATH+Constants.SIGN_DISK_PATH;//+"',projName:'"+cw.getProjName()+"',cwId:'"+cw.getCwId() + "'}";
					JSONObject	json = JsonDateValueProcessor.JsonFomatDate(nde,"yyyy-MM-dd");
					String str = assemblyParam(param,json);
					if(str != null){
						result  += str;
					}
					result += "'}";
					list.add(result);
				}
			}
			return list;
		
	    }else if(type.equals(Constants.TECHNOLOGY_TELL)){//会审交底  
			List<Object> list = new ArrayList<Object>();
			//多条交底信息
			List<ConstructionWork> cws = constructionWorkDao.findByProjId(project.getProjId(), null);
			JSONArray jsonArray=JSONArray.fromObject(arrayStr);
			if(jsonArray!=null&&jsonArray.size()>0 && cws!=null && cws.size()>0 && project !=null){
				JSONObject jsonObject=JSONObject.fromObject(jsonArray.get(0));
				CompletionDataPrintDto dto = (CompletionDataPrintDto)JSONObject.toBean(jsonObject, CompletionDataPrintDto.class);
				String menuId = type; // 获取菜单id
				for(ConstructionWork cw : cws){
					String key = project.getCorpId()+"_"+project.getProjectType()+"_"+menuId;
					Object reportVersion = Constants.getSysConfigByKey(key);
					if(reportVersion !=null){
						//记录特定字符索引
						int beginIndex = dto.getReportlet().indexOf("/");
						int endIndex = dto.getReportlet().lastIndexOf("/");
						String reportlet = dto.getReportlet().substring(beginIndex, endIndex+1);  //截取字符'/'之间的字符串
						dto.setReportlet(reportlet+reportVersion.toString());   //若reportVersion不为空则重新设置报表路径
					}
					result = "{reportlet:'"+dto.getReportlet()+"',imgUrl:'" + Constants.DISK_PATH+Constants.SIGN_DISK_PATH;//+"',projName:'"+cw.getProjName()+"',cwId:'"+cw.getCwId() + "'}";
					JSONObject	json = JsonDateValueProcessor.JsonFomatDate(cw,"yyyy-MM-dd");
					String str = assemblyParam(param,json);
					if(str != null){
						result  += str;
					}
					result += "'}";
					list.add(result);
				}
			}
			return list;
		}else if(type.equals(Constants.CU_DAIRY)){	//施工日志
			List<Object> list = new ArrayList<Object>();
			//多条交底信息
			List<DailyLog> dls = dailyLogDao.findByProjId(project.getProjId());
			//2、使用JSONArray
			JSONArray jsonArray=JSONArray.fromObject(arrayStr);
			if(jsonArray!=null&&jsonArray.size()>0 && dls!=null && dls.size()>0 && project !=null){
				JSONObject jsonObject=JSONObject.fromObject(jsonArray.get(0));
				CompletionDataPrintDto dto = (CompletionDataPrintDto)JSONObject.toBean(jsonObject, CompletionDataPrintDto.class);
				String menuId = type; // 获取菜单id
				for(DailyLog dl : dls){
					String key = project.getCorpId()+"_"+project.getProjectType()+"_"+menuId;
					Object reportVersion = Constants.getSysConfigByKey(key);
					if(reportVersion !=null){
						//记录特定字符索引
						int beginIndex = dto.getReportlet().indexOf("/");
						int endIndex = dto.getReportlet().lastIndexOf("/");
						String reportlet = dto.getReportlet().substring(beginIndex, endIndex+1);  //截取字符'/'之间的字符串
						dto.setReportlet(reportlet+reportVersion.toString());   //若reportVersion不为空则重新设置报表路径
					}
					result = "{reportlet:'"+dto.getReportlet()+ "',imgUrl:'" + Constants.DISK_PATH+Constants.SIGN_DISK_PATH;//+"',projId:'"+dl.getProjId() +"',dlId:'"+dl.getDlId()+ "'}";
					JSONObject	json = JsonDateValueProcessor.JsonFomatDate(dl,"yyyy-MM-dd");
					String str = assemblyParam(param,json);
					if(str != null){
						result  += str;
					}
					result += "'}";
					list.add(result);
				}
			}
			return list;
		}else if(type.equals(Constants.CHANGE_MENT)){//设计变更
			List<Object> list = new ArrayList<Object>();
			//多条变更
			List<ChangeManagement> cms = changeManagementDao.findByProjId(project.getProjId(), DesignChangeStateEnum.ALREADY_FINISHED.getValue(),ChangeTypeEnum.CONSTRUCTION_CHANGE.getValue());
			//2、使用JSONArray
			net.sf.json.JSONArray jsonArray=net.sf.json.JSONArray.fromObject(arrayStr);
			if(jsonArray!=null&&jsonArray.size()>0 && cms!=null && cms.size()>0 && project !=null){
				String menuId = type; // 获取菜单id
				//遍历变更记录
				for(ChangeManagement cm : cms){
					//遍历cpt
					for(Object obj:jsonArray){
						net.sf.json.JSONObject jsonObject=net.sf.json.JSONObject.fromObject(obj);
						CompletionDataPrintDto dto = (CompletionDataPrintDto)net.sf.json.JSONObject.toBean(jsonObject, CompletionDataPrintDto.class);
						//变更记录变更类型与cpt类型相同，
						if(cm.getChangeType()!=null && cm.getChangeType().equals(dto.getType())){
							String key = project.getCorpId()+"_"+project.getProjectType()+"_"+menuId;
							Object reportVersion = Constants.getSysConfigByKey(key);
							if(reportVersion !=null){
								//记录特定字符索引
								int beginIndex = dto.getReportlet().indexOf("/");
								int endIndex = dto.getReportlet().lastIndexOf("/");
								String reportlet = dto.getReportlet().substring(beginIndex, endIndex+1);  //截取字符'/'之间的字符串
								dto.setReportlet(reportlet+reportVersion.toString());   //若reportVersion不为空则重新设置报表路径
							}
							result = "{reportlet:'"+dto.getReportlet()+"',imgUrl:'" + Constants.DISK_PATH+Constants.SIGN_DISK_PATH;//+"',projName:'"+cm.getProjName()+"',projNo:'"+cm.getProjNo()
									//+"',cmDate:'"+cm.getCmDate()+"',cmId:'"+cm.getCmId()
									//+"',imgUrl:'" + Constants.DISK_PATH+Constants.SIGN_DISK_PATH + "'}";
							JSONObject	json = JsonDateValueProcessor.JsonFomatDate(cm,"yyyy-MM-dd");
							String str = assemblyParam(param,json);
							if(str != null){
								result  += str;
							}
							result += "'}";
							list.add(result);
						}
					}
				}
			}
			return list;
		}else if(type.equals(Constants.VSIA_RECORD)){ //签证记录
			List<Object> list = new ArrayList<Object>();
			//多条签证
			List<EngineeringVisa> evs = engineeringVisaDao.findByProjId(project.getProjId(), StageProjectApplicationEnum.PASSED.getValue());
			net.sf.json.JSONArray jsonArray=net.sf.json.JSONArray.fromObject(arrayStr);
			if(jsonArray!=null&&jsonArray.size()>0 && evs!=null && evs.size()>0 && project !=null){
				net.sf.json.JSONObject jsonObject=net.sf.json.JSONObject.fromObject(jsonArray.get(0));
				String menuId = type; // 获取菜单id
				String key = project.getCorpId()+"_"+project.getProjectType()+"_"+menuId;
				Object reportVersion = Constants.getSysConfigByKey(key);
				//遍历签证记录
				for(EngineeringVisa visa : evs){
					CompletionDataPrintDto dto = (CompletionDataPrintDto)net.sf.json.JSONObject.toBean(jsonObject, CompletionDataPrintDto.class);
					if(reportVersion !=null){
						//记录特定字符索引
						int beginIndex = dto.getReportlet().indexOf("/");
						int endIndex = dto.getReportlet().lastIndexOf("/");
						String reportlet = dto.getReportlet().substring(beginIndex, endIndex+1);  //截取字符'/'之间的字符串
						dto.setReportlet(reportlet+reportVersion.toString());   //若reportVersion不为空则重新设置报表路径
					}
					result = "{reportlet:'"+dto.getReportlet()//+"',projName:'"+visa.getProjName()+"',projNo:'"+visa.getProjNo()+"',projId:'"+visa.getProjId()
							//+"',evId:'"+visa.getEvId()+"',custName:'"+visa.getCustName()+"',suName:'"+visa.getSuName()+"',constructionUnit:'"+visa.getConstructionUnit()
							+"',drawUrl1:'" + Constants.DISK_PATH
							+"',imgUrl:'" + Constants.DISK_PATH+Constants.SIGN_DISK_PATH;
					JSONObject	json = JsonDateValueProcessor.JsonFomatDate(visa,"yyyy-MM-dd");
					String str = assemblyParam(param,json);
					if(str != null){
						result  += str;
					}
					result += "'}";
					list.add(result);
				}
			}
			return list;
		}else if(type.equals(Constants.COMPLETE_REPORT)){ //竣工报告
			List<Object> list = new ArrayList<Object>();
			String corpId = project.getCorpId();  //公司ID
			//多条竣工报告
			List<CompleteReport> crs = completeReportDao.findByProjId(project.getProjId());
			net.sf.json.JSONArray jsonArray=net.sf.json.JSONArray.fromObject(arrayStr);
			if(jsonArray!=null&&jsonArray.size()>0 && crs!=null && crs.size()>0 && project !=null){
				ReportVersionReq reportVersionReq = new ReportVersionReq();
				List<ReportVersion> versions = new ArrayList<ReportVersion>();
				SimpleDateFormat formatDate=new SimpleDateFormat("yyyy-MM-dd");  //格式化日期格式
				//遍历变更记录
				for(CompleteReport cr : crs){
					//遍历cpt
					for(Object obj:jsonArray){
						String rvId = null;
						Object reportVersion = null;
						reportVersionReq.setMenuId(type);  //菜单ID
						reportVersionReq.setSignDate(formatDate.format(cr.getRealStartDate()));  //取得计划开始日期
						reportVersionReq.setProjType(project.getProjectType());  //工程类型
						reportVersionReq.setCorpId(corpId);  //公司ID
						try {
							    versions = reportVersionService.queryReportVersions(reportVersionReq);  //先查所属公司是否有配置
							if(versions != null && versions.size() >0){
								rvId = versions.get(0).getRvId(); //取得版本主键
							}else{
								corpId = Constants.START_REPORT_CPT_CORP_MODE;
								reportVersionReq.setCorpId(corpId);  //默认贵阳公司
								versions = reportVersionService.queryReportVersions(reportVersionReq);
								if(versions.size() > 0){
									rvId = versions.get(0).getRvId(); //取得版本主键
								}
							}
							
							String key =project.getProjectType()+"_"+ corpId+ "_" + type+ "_" + rvId;  //根据主键查找报表名称
							reportVersion = Constants.getSysConfigByKey(key);
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						net.sf.json.JSONObject jsonObject=net.sf.json.JSONObject.fromObject(obj);
						CompletionDataPrintDto dto = (CompletionDataPrintDto)net.sf.json.JSONObject.toBean(jsonObject, CompletionDataPrintDto.class);
						if(reportVersion !=null){
							//记录特定字符索引
							int beginIndex = dto.getReportlet().indexOf("/");
							int endIndex = dto.getReportlet().lastIndexOf("/");
							String reportlet = dto.getReportlet().substring(beginIndex, endIndex+1);  //截取字符'/'之间的字符串
							dto.setReportlet(reportlet+reportVersion.toString());   //若reportVersion不为空则重新设置报表路径
						}
						result = "{reportlet:'"+dto.getReportlet()//+"',crId:'"+cr.getCrId()+"',projId:'"+cr.getProjId()
								+"',imgUrl:'" + Constants.DISK_PATH+Constants.SIGN_DISK_PATH;
						JSONObject	json = JsonDateValueProcessor.JsonFomatDate(cr,"yyyy-MM-dd");
						String str = assemblyParam(param,json);
						if(str != null){
							result  += str;
						}
						result += "'}";
						list.add(result);
					}
				}
			}
			return list;
		}else if(type.equals(Constants.DIVISIONAL_ACCEPT)){ //分部验收单
			List<Object> list = new ArrayList<Object>();
			//根据工程ID查询一站式验收单
			List<DivisionalAcceptance> diList=divisionalAcceptanceDao.findByprojectId(project.getProjId());
			net.sf.json.JSONArray jsonArray=net.sf.json.JSONArray.fromObject(arrayStr);
			if(jsonArray!=null&&jsonArray.size()>0 && diList!=null && diList.size()>0 && project !=null){
				net.sf.json.JSONObject jsonObject=net.sf.json.JSONObject.fromObject(jsonArray.get(0));
				CompletionDataPrintDto dto = (CompletionDataPrintDto)net.sf.json.JSONObject.toBean(jsonObject, CompletionDataPrintDto.class);
				String menuId = type; // 获取菜单id
				String key = project.getCorpId()+"_"+project.getProjectType()+"_"+menuId;
				Object reportVersion = Constants.getSysConfigByKey(key);
				if(reportVersion !=null){
					//记录特定字符索引
					int beginIndex = dto.getReportlet().indexOf("/");
					int endIndex = dto.getReportlet().lastIndexOf("/");
					String reportlet = dto.getReportlet().substring(beginIndex, endIndex+1);  //截取字符'/'之间的字符串
					dto.setReportlet(reportlet+reportVersion.toString());   //若reportVersion不为空则重新设置报表路径
				}
				for(DivisionalAcceptance da : diList){
					result = "{reportlet:'"+dto.getReportlet()+"',imgUrl:'" + Constants.DISK_PATH+Constants.SIGN_DISK_PATH;//+"',daId:'"+da.getDaId()+"',projId:'"+da.getProjId() + "'}";
					JSONObject	json = JsonDateValueProcessor.JsonFomatDate(da,"yyyy-MM-dd");
					String str = assemblyParam(param,json);
					if(str != null){
						result  += str;
					}
					result += "'}";
					list.add(result);
				}
			}
			return list;
		}else if(type.equals(Constants.MEASUREMENT_RECORD)){  //计量记录
			List<Object> list = new ArrayList<Object>();
			//计量表信息有多条
			List<Measurement> measurementList = measurementDao.findByProjId(project.getProjId());
			//2、使用JSONArray
			JSONArray jsonArray=JSONArray.fromObject(arrayStr);
			if(jsonArray!=null&&jsonArray.size()>0 && measurementList!=null && measurementList.size()>0 && project !=null){
				JSONObject jsonObject=JSONObject.fromObject(jsonArray.get(0));
				CompletionDataPrintDto dto = (CompletionDataPrintDto)JSONObject.toBean(jsonObject, CompletionDataPrintDto.class);
				String menuId = type; // 获取菜单id
				String key = project.getCorpId()+"_"+project.getProjectType()+"_"+menuId;
				Object reportVersion = Constants.getSysConfigByKey(key);
				if(reportVersion !=null){
					//记录特定字符索引
					int beginIndex = dto.getReportlet().indexOf("/");
					int endIndex = dto.getReportlet().lastIndexOf("/");
					String reportlet = dto.getReportlet().substring(beginIndex, endIndex+1);  //截取字符'/'之间的字符串
					dto.setReportlet(reportlet+reportVersion.toString());   //若reportVersion不为空则重新设置报表路径
				}
				result = "{reportlet:'"+dto.getReportlet()+"',imgUrl:'" + Constants.DISK_PATH+Constants.SIGN_DISK_PATH;//+"',projId:'"+measurementList.get(0).getProjId() + "'}";
				JSONObject	json = JsonDateValueProcessor.JsonFomatDate(measurementList.get(0),"yyyy-MM-dd");
				String str = assemblyParam(param,json);
				if(str != null){
					result  += str;
				}
				result += "'}";
				list.add(result);
			}
			return list;
		}
		return null;
	}

	/**
	 * 整理报验单数据
	 * @param project
	 * @param type
	 * @param pid
	 * @param corpid
	 * @return
	 */
	public List<Object> printDataListCheck(Project project, String type,String pid,String corpid,String pcDesId){
		//需要审核签字的报验单
		List<Map<String,Object>> cons = Constants.getConstantsMapByKey(Constants.AYDIT_INS);
		boolean boo = false;   //默认false,表示加载默认报验单
		Map<String,Object> map = null;
		for(Map<String,Object> m : cons){
			if(pcDesId.equals(String.valueOf(m.get("RESERVE1")))){
				//查询是否有新报验单
				boo = true;
				break;
			}
		}
		//根据工程id和报验单类型、完成状态查询报验单列表
		List<ProjectChecklist> pcList = projectChecklistDao.findByProjIdAndDesId(project.getProjId(),pcDesId, ProjectCheckListFlagEnum.COMPLETED.getValue());
		String result ="";
		String param = "";
		List<Object> list = new ArrayList<Object>();
		if (pcList==null || pcList.size() < 1){  //无报验记录返回null
			 return null;
			 }
		//遍历报验单
		for(ProjectChecklist pc : pcList){
			String reserve1="";  
			if(boo){  //true查找新报表,false查找默认报表
				if(StringUtil.isNotBlank(pc.getFinishedDate())){  //记录完成时间不为空时，根据最早一次完成时间，查找报表
					String [] dates = pc.getFinishedDate().split(",");   //按逗号分隔开完成时间
					if(DateUtil.getDaySub(DateUtil.StringToDate(Constants.UPGRADE_DATE),DateUtil.StringToDate(dates[dates.length-1]))>0){  //判断完成时间是否大于固定时间，若大于则查找新报验单
						reserve1 =Constants.UPGRADE_DATE;   //根据时间查询最新报表
					}
				}
			}
			map = Constants.getConsData(Constants.COMPLETION_DATA,type,reserve1);  //查找报表，根据时间，菜单ID,更新时间
			String arrayStr = "";
			if(map !=null){
				arrayStr = String.valueOf(map.get("CNVALUE"));
				param = String.valueOf(map.get("RESERVE2"));
			}else{
				if(boo){
					map = Constants.getConsData(Constants.COMPLETION_DATA,type,Constants.UPGRADE_DATE);
				}else{
					map = Constants.getConsByKeyAndId(Constants.COMPLETION_DATA,type);
				}
				if(map != null){
					arrayStr = String.valueOf(map.get("CNVALUE"));
					param = String.valueOf(map.get("RESERVE2"));
				}
			}
			
			//使用JSONArray
			JSONArray jsonArray=StringUtils.isNotBlank(arrayStr)?JSONArray.fromObject(arrayStr):new JSONArray();
			JSONObject jsonObject = null;
			CompletionDataPrintDto dto = null;
			JSONObject json = null;
			//遍历cpt
	        if(jsonArray.size() > 1){
	    		for(Object obj:jsonArray){
					 jsonObject=net.sf.json.JSONObject.fromObject(obj);
				     dto = (CompletionDataPrintDto)net.sf.json.JSONObject.toBean(jsonObject, CompletionDataPrintDto.class);
					//记录类型与cpt类型相同
					//cpt的type不为空，则需要记录类型与cpt类型相同
					if(StringUtil.isNotBlank(dto.getType())){//cpt的type不为空，判断
						result = null;   //重置为null
						if(pcDesId.equals(ProjectChecklistTypeEnum.PRESERVATIVE.getValue()) && StringUtil.isNotBlank(pc.getPreservativeType()) && pc.getPreservativeType().equals(dto.getType())){//防腐记录
							 result = "{reportlet:'"+dto.getReportlet()+"',imgUrl:'" + Constants.DISK_PATH+Constants.SIGN_DISK_PATH+"',drawUrl:'"+Constants.DISK_PATH+Constants.DIAGRAM_DISK_PATH;
						}else if(pcDesId.equals(ProjectChecklistTypeEnum.PRESERVATIVE_INPECT.getValue()) && StringUtil.isNotBlank(pc.getPreservativeType()) && pc.getPreservativeType().equals(dto.getType())){//防腐检查
							 result = "{reportlet:'"+dto.getReportlet()+"',imgUrl:'" + Constants.DISK_PATH+Constants.SIGN_DISK_PATH+"',drawUrl:'"+Constants.DISK_PATH+Constants.DIAGRAM_DISK_PATH;
						}else if(pcDesId.equals(ProjectChecklistTypeEnum.PE_WELD_LINE_INPECT.getValue()) && pc.getMeltConnectType().equals(dto.getType())){//pe管焊缝检查
							 result = "{reportlet:'"+dto.getReportlet()+"',imgUrl:'" + Constants.DISK_PATH+Constants.SIGN_DISK_PATH+"',drawUrl:'"+Constants.DISK_PATH+Constants.DIAGRAM_DISK_PATH;
						}else if(pcDesId.equals(ProjectChecklistTypeEnum.STRENGTH_TEST.getValue()) && pc.getStPipeType().equals(dto.getType())){//强度试验
							 result = "{reportlet:'"+dto.getReportlet()+"',imgUrl:'" + Constants.DISK_PATH+Constants.SIGN_DISK_PATH+"',drawUrl:'"+Constants.DISK_PATH+Constants.DIAGRAM_DISK_PATH;
						}
					if(StringUtil.isNotBlank(result)){  //若result不为空则组装报表路径
						json = JsonDateValueProcessor.JsonFomatDate(pc,"yyyy-MM-dd");  //参数对象是必须的，时间格式可以为null，默认为“年-月-日”
	                    String str = assemblyParam(param,json);
						if(str != null){
							result  += str;
						}
						result += "'}";
						list.add(result);
					}
					}
				}
	        }else{
	        	 jsonObject=net.sf.json.JSONObject.fromObject(jsonArray.get(0));
			     dto = (CompletionDataPrintDto)net.sf.json.JSONObject.toBean(jsonObject, CompletionDataPrintDto.class);
			     result = "{reportlet:'"+dto.getReportlet()+"',imgUrl:'" + Constants.DISK_PATH+Constants.SIGN_DISK_PATH+"',drawUrl:'"+Constants.DISK_PATH+Constants.DIAGRAM_DISK_PATH;//+"',projId:'"+pc.getProjId()+"',projName:'"+pc.getProjName()+"',pcId:'"+pc.getPcId()+"',projNo:'"+pc.getProjNo();
					json = JsonDateValueProcessor.JsonFomatDate(pc,"yyyy-MM-dd");
					String str = assemblyParam(param,json);
					if(str != null){
						result  += str;
					}
					result += "'}";
					list.add(result);
	        }
		}
		return list;
	}
	
	
	/**
	 * 组装参数
	 * @param str
	 * @return
	 */
	public String assemblyParam (String str,JSONObject jsonObject){
		try {
			Map map = (Map)jsonObject;
			String param = "";
			JSONObject params = JSONObject.fromObject(str);
			Iterator<String> it = params.keys();
			while(it.hasNext()){
				// 获得key
				String key = it.next();
				param += "',"+key +":'"+map.get(params.getString(key));
			}
			return param;
		}catch (Exception e){
			return null;
		}
	}

}
