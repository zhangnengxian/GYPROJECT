package cc.dfsoft.project.biz.base.complete.service.impl;

import java.sql.Clob;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.print.DocFlavor.STRING;

import org.apache.commons.lang3.StringUtils;
import org.springframework.orm.hibernate4.HibernateOptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fr.report.core.A.r;

import cc.dfsoft.project.biz.base.baseinfo.enums.AssistProgressTypeEnum;
import cc.dfsoft.project.biz.base.baseinfo.enums.WorkFlowTypeEnum;
import cc.dfsoft.project.biz.base.complete.dao.DivisionalAcceptanceApplyDao;
import cc.dfsoft.project.biz.base.complete.dao.DivisionalAcceptanceDao;
import cc.dfsoft.project.biz.base.complete.dto.DivisionalAcceptanceReq;
import cc.dfsoft.project.biz.base.complete.entity.DivisionalAcceptance;
import cc.dfsoft.project.biz.base.complete.entity.DivisionalAcceptanceApply;
import cc.dfsoft.project.biz.base.complete.enums.AcceptanceAtateEnum;
import cc.dfsoft.project.biz.base.complete.enums.CompletionDataPrintEnum;
import cc.dfsoft.project.biz.base.complete.service.DivisionalAcceptanceService;
import cc.dfsoft.project.biz.base.constructmanage.dto.CompletionDataPrintDto;
import cc.dfsoft.project.biz.base.contract.dao.ContractDao;
import cc.dfsoft.project.biz.base.contract.entity.Contract;
import cc.dfsoft.project.biz.base.contract.enums.ContractIsPrintEnum;
import cc.dfsoft.project.biz.base.plan.dao.ConstructionPlanDao;
import cc.dfsoft.project.biz.base.plan.entity.ConstructionPlan;
import cc.dfsoft.project.biz.base.project.dao.ProjectDao;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.project.entity.Signature;
import cc.dfsoft.project.biz.base.project.enums.SignDataTypeEnum;
import cc.dfsoft.project.biz.base.project.enums.SignPostEnum;
import cc.dfsoft.project.biz.base.project.enums.SignStateEnum;
import cc.dfsoft.project.biz.base.project.service.OperateRecordService;
import cc.dfsoft.project.biz.base.project.service.SignNoticeService;
import cc.dfsoft.project.biz.base.project.service.SignatureService;
import cc.dfsoft.project.biz.base.settlement.enums.AuditResultEnum;
import cc.dfsoft.project.biz.base.subpackage.dao.SubContractDao;
import cc.dfsoft.project.biz.base.subpackage.entity.SubContract;
import cc.dfsoft.uexpress.biz.sys.dept.entity.Staff;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.util.IDUtil;

/**
 * 分部验收服务接口实现
 * @author fuliwei
 *
 */
@Service
@Transactional(readOnly = true,propagation=Propagation.REQUIRED)
public class DivisionalAcceptanceServiceImpl implements DivisionalAcceptanceService {
	
	/**分部验收申请*/
	@Resource
	DivisionalAcceptanceDao divisionalAcceptanceDao;
	
	/**签字服务接口*/
	@Resource
	SignatureService signatureService;
	
	/**工程Dao*/
	@Resource
	ProjectDao projectDao;
	
	/**施工计划Dao*/
	@Resource
	ConstructionPlanDao constructionPlanDao;
	
	/**施工合同Dao*/
	@Resource
	ContractDao contractDao;
	
	/**分包合同Dao*/
	@Resource
	SubContractDao subContractDao;
	
	/**分部验收申请*/
	@Resource
	DivisionalAcceptanceApplyDao divisionalAcceptanceApplyDao;
	/**签字通知服务*/
	@Resource
	SignNoticeService signNoticeService;
	@Resource
	OperateRecordService operateRecordService;
	
	/**
	 * 分部验收保存
	 * @author fuliwei
	 * @createTime 2017年8月10日
	 * @param 
	 * @return
	 */
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void saveDivisionalAcceptance(DivisionalAcceptance divisionalAcceptance) throws Exception {
		
		boolean flag = false;
		if(StringUtils.isBlank(divisionalAcceptance.getDaId())){
			flag = true;
			divisionalAcceptance.setDaId(IDUtil.getUniqueId(Constants.MODULE_CODE_COMPLETE));
		}
		divisionalAcceptance.setIsPrint(ContractIsPrintEnum.HAVE_NOT_PRINT.getValue());					//未打印
		
		if("1".equals(divisionalAcceptance.getFlag())){
			//保存
			DivisionalAcceptanceApply daa=divisionalAcceptanceApplyDao.get(divisionalAcceptance.getDaaId());//分部验收申请
			daa.setAcceptanceState(AcceptanceAtateEnum.ALREADY_ACCEPTANCE.getValue());						//已验收
			divisionalAcceptanceApplyDao.saveOrUpdate(daa);
			divisionalAcceptance.setAcceptanceState(AcceptanceAtateEnum.ALREADY_ACCEPTANCE.getValue());	//已验收
			Project project = projectDao.get(daa.getProjId());
			//分部验收待办通知
			operateRecordService.cerateCurOperateRecord(project,daa.getAuditState(), WorkFlowTypeEnum.ASSIST_PROGRESS.getValue(), AssistProgressTypeEnum.DIVISIONAL_ACCEPT.getValue(), divisionalAcceptance.getDaaId(),new Staff(),null,false);
		
		}else{
			divisionalAcceptance.setAcceptanceState(AcceptanceAtateEnum.NOT_ACCEPTANCE.getValue());		//未验收
		}
		//将分布验收时间保存到工程的竣工时间
		//分部验收日期存到工程表的竣工日期去掉-自检推送日期为竣工日期
		//@dateTime 219-3-15 liaoyq
		/*Project proj=projectDao.get(divisionalAcceptance.getProjId());
		if(proj.getCompletedDate()==null){
		proj.setCompletedDate(divisionalAcceptance.getAcceptanceDate());
		}
		projectDao.save(proj);*/
		
		//cuSpdPrincipal;				//现场代表
		//duDeputy;						//设计代表
		//suFieldJgj;					//现场监理
		//managementQae;					//施工员
		//Clob organizationMan;				//组织人
		//custCenterSign;               //客服签字
		//transCompanySign;             //输配签字
		//measurementSign;              //计量所签字
		
		
		if(StringUtils.isNotBlank(divisionalAcceptance.getCuSpdPrincipal())
				&& StringUtils.isNotBlank(divisionalAcceptance.getDuDeputy())
				&& StringUtils.isNotBlank(divisionalAcceptance.getSuFieldJgj())
				&& StringUtils.isNotBlank(divisionalAcceptance.getManagementQae())
				&& StringUtils.isNotBlank(divisionalAcceptance.getOrganizationMan())
				&& StringUtils.isNotBlank(divisionalAcceptance.getCustCenterSign())
				&& StringUtils.isNotBlank(divisionalAcceptance.getTransCompanySign())
				&& StringUtils.isNotBlank(divisionalAcceptance.getMeasurementSign())){
				//所有通知置为已签字
				signNoticeService.updateAllSignState(SignDataTypeEnum.DIVISIONAL_ACCEPTANCE.getValue(),divisionalAcceptance.getDaId());
		 }/*else{
			 if(StringUtils.isNotBlank(divisionalAcceptance.getCuSpdPrincipal())
						&& StringUtils.isNotBlank(divisionalAcceptance.getDuDeputy())
						&& StringUtils.isNotBlank(divisionalAcceptance.getSuFieldJgj())
						&& StringUtils.isNotBlank(divisionalAcceptance.getManagementQae())
						&& StringUtils.isBlank(divisionalAcceptance.getOrganizationMan())
						&& StringUtils.isNotBlank(divisionalAcceptance.getCustCenterSign())
						&& StringUtils.isNotBlank(divisionalAcceptance.getTransCompanySign())
						&& StringUtils.isNotBlank(divisionalAcceptance.getMeasurementSign())){
				 //生成监理下一个
				 signNoticeService.saveSignNotice(SignPostEnum.SUJGJ.getValue(), SignDataTypeEnum.DIVISIONAL_ACCEPTANCE.getValue(),
						 divisionalAcceptance.getDaId(), divisionalAcceptance.getProjId());
			 }
		 }
		*/
		
		
		
		
		
		
		divisionalAcceptanceDao.saveOrUpdate(divisionalAcceptance);
		
		List<Signature> signs=divisionalAcceptance.getSign();
		if(signs!=null && signs.size()>0){
			for(Signature sign:signs){
				sign.setDataType(SignDataTypeEnum.DIVISIONAL_ACCEPTANCE.getValue());
			}
			divisionalAcceptance.setSign(signs);
		}
		//签字保存
		signatureService.saveOrUpdateSign("menuId+memuName+1",divisionalAcceptance.getSign(), divisionalAcceptance.getProjId(), divisionalAcceptance.getDaId(), divisionalAcceptance.getClass().getName(), flag);
	}
	

	/**
	 * 分部验收列表
	 * @author fuliwei
	 * @createTime 2017年8月10日
	 * @param 
	 * @return
	 */
	@Override
	public Map<String, Object> queryDivisionalAcceptance(DivisionalAcceptanceReq req) throws ParseException {

		Map<String, Object> map=divisionalAcceptanceDao.queryDivisionalAcceptance(req);
		
		List<DivisionalAcceptance> list=(List<DivisionalAcceptance>) map.get("data");
		
		if(list!=null && list.size()>0){
			for(DivisionalAcceptance divisionalAcceptance:list){
				
				
					Project project=projectDao.get(divisionalAcceptance.getProjId());
					if(project!=null){
						divisionalAcceptance.setProjectType(project.getProjectType());
						divisionalAcceptance.setCorpId(project.getCorpId());
					}
				
			}
		}
		
		return map;
	}

	
	/**
	 * 查询详述
	 * @author fuliwei
	 * @createTime 2017年8月10日
	 * @param 
	 * @return
	 * @throws ParseException 
	 */
	@Override
	public DivisionalAcceptance viewByDaaId(String daaId) throws ParseException {
		DivisionalAcceptance da=divisionalAcceptanceDao.viewByDaaId(daaId);
		DivisionalAcceptanceApply daa=divisionalAcceptanceApplyDao.get(daaId);
		Project pro=projectDao.get(daa.getProjId());
		ConstructionPlan cp=constructionPlanDao.viewPlanById(daa.getProjId());        //计划
		Contract con=contractDao.viewContractByprojId(daa.getProjId());
		SubContract scon=subContractDao.findSubContractByprojId(daa.getProjId());     //分包合同
		if(da!=null && pro!=null&& cp!=null){
			da.setCorpName(pro.getCorpName());
			da.setDeptName(pro.getDeptName());
			da.setContributionModeDes(pro.getContributionModeDes());
			da.setProjectTypeDes(pro.getProjectTypeDes());
			
			
			
			da.setBuilder(cp.getBuilder());//现场代表
			da.setSuRepresentative(cp.getSuJgj());//监理代表
			da.setCuPm(cp.getCuPm());//项目经理
			da.setManagementQae1(cp.getManagementQae());//施工员
			da.setDuDesigner(pro.getDesigner());//设计员
			da.setManagementWacf(cp.getManagementWacf());//材料员
			da.setSaftyOfficer(cp.getSaftyOfficer());//安全员
			da.setTechnician(cp.getTechnician());//
		}else{
			
			if( pro!=null&& cp!=null){
				da=new DivisionalAcceptance();
				da.setProjId(pro.getProjId());
				da.setProjNo(pro.getProjNo());
				da.setProjName(pro.getProjName());
				da.setProjAddr(pro.getProjAddr());
				da.setProjScaleDes(pro.getProjScaleDes());
				da.setCorpName(pro.getCorpName());
				da.setDeptName(pro.getDeptName());
				da.setContributionModeDes(pro.getContributionModeDes());
				da.setProjectTypeDes(pro.getProjectTypeDes());
				
				da.setBuilder(cp.getBuilder());//现场代表
				da.setSuJgj(cp.getSuJgj());//监理代表
				da.setCuPm(cp.getCuPm());//项目经理
				da.setManagementQae1(cp.getManagementQae());//施工员
				da.setDuDesigner(pro.getDesigner());//设计员
				da.setManagementWacf(cp.getManagementWacf());//材料员
				da.setSaftyOfficer(cp.getSaftyOfficer());//安全员
				da.setTechnician(cp.getTechnician());//
			}
			
			
			
			
		}
		/*//角色
		if(da!=null&&cp!=null){
			
				da.setBuilder(cp.getBuilder());//现场代表
				da.setSuRepresentative(cp.getSuRepresentative());//监理代表
				da.setCuPm(cp.getCuPm());//项目经理
				da.setManagementQae1(cp.getManagementQae());//施工员
				da.setDuDesigner(cp.getDuDesigner());//设计员
				da.setManagementWacf(cp.getManagementWacf());//材料员
				da.setSaftyOfficer(cp.getSaftyOfficer());//安全员
				da.setTechnician(cp.getTechnician());//

		}
		//工程
		if(pro!=null){
			da=new DivisionalAcceptance();
			da.setProjId(pro.getProjId());
			da.setProjNo(pro.getProjNo());
			da.setProjName(pro.getProjName());
			da.setProjAddr(pro.getProjAddr());
			da.setProjScaleDes(pro.getProjScaleDes());
			da.setCorpName(pro.getCorpName());
			da.setDeptName(pro.getDeptName());
			da.setContributionModeDes(pro.getContributionModeDes());
			da.setProjectTypeDes(pro.getProjectTypeDes());
		}*/
		
		//施工计划
		if(cp!=null){
			da.setSuName(cp.getSuName());
			da.setCuName(cp.getCuName());
		}
		
		//合同
		if(con!=null){
			da.setConNo(con.getConNo());
		}
		
		//分包合同
		if(scon!=null){
			da.setScNo(scon.getScNo());            
		}
		da.setDaaId(daa.getDaaId());
	   if(StringUtils.isBlank(da.getThisAcceptanceContent())){
		   da.setThisAcceptanceContent(daa.getThisAcceptanceContent());    
	   }
		return da;
	}

	/**
	 * 标记打印验收申请
	 * @author fuliwei
	 * @createTime 2017年8月14日
	 * @param 
	 * @return
	 */
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void signDivisionalAcceptance(String daId) {
		DivisionalAcceptance da=divisionalAcceptanceDao.get(daId);
		if(da!=null){
			//标记已打印
			da.setIsPrint(ContractIsPrintEnum.ALREADY_PRINT.getValue());
			divisionalAcceptanceDao.update(da);
		}
	}

	/**
	 * 根据工程id查询分部验收单信息
	 * 并返回报表信息
	 * @author liaoyq
	 * @createtime 2017-11-06
	 */
	@Override
	public List<Object> findPrintDataByProjId(String projId,String type) {
		String result ="";
		List<Object> list = new ArrayList<Object>();
		//根据工程ID查询一站式验收单
		List<DivisionalAcceptance> diList=divisionalAcceptanceDao.findByprojectId(projId);
		Project project = projectDao.get(projId);  //根据工程ID取得实体
		String arrayStr = CompletionDataPrintEnum.DIVISIONAL_ACCEPT.getCptUrl();
		//2、使用JSONArray
		net.sf.json.JSONArray jsonArray=net.sf.json.JSONArray.fromObject(arrayStr);
		if(jsonArray!=null&&jsonArray.size()>0 && diList!=null && diList.size()>0 && project !=null){
			net.sf.json.JSONObject jsonObject=net.sf.json.JSONObject.fromObject(jsonArray.get(0));
			CompletionDataPrintDto dto = (CompletionDataPrintDto)net.sf.json.JSONObject.toBean(jsonObject, CompletionDataPrintDto.class);
			String[] menuIdAndMark = type.split("-"); // 分解施工任务单标识符和菜单ID,从而取得菜单ID
			String menuId = menuIdAndMark[menuIdAndMark.length-1]; // 获取菜单id
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
				result = "{reportlet:'"+dto.getReportlet()+"',daId:'"+da.getDaId()+"',projId:'"+da.getProjId()+"',imgUrl:'" + Constants.DISK_PATH+Constants.SIGN_DISK_PATH + "'}";
				list.add(result);
			}
		}
		return list;
	}


	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void saveSignNotice(String cwId) {
		String signState = SignStateEnum.ALREADY_SIGN.getValue();
		if(StringUtils.isNotBlank(cwId)){
			DivisionalAcceptance da=divisionalAcceptanceDao.get(cwId);
			if(da!=null){
				if(StringUtils.isNotBlank(da.getOrganizationMan())){
						//组织人通知置为无效
						signNoticeService.saveThisSignNotice(SignPostEnum.TECHNICIAN.getValue(), SignDataTypeEnum.DIVISIONAL_ACCEPTANCE.getValue(),
								da.getDaId(), da.getProjId(),signState);
				}
			}
			
		}
		
	}

	/**
	 * 查询分部验收申请列表
	 * @author wanghuijun
	 * @createTime 2018年9月20日
	 * @return
	 */
	@Override
	public Map<String, Object> queryDivisionalAcceptanceApply(DivisionalAcceptanceReq req) throws Exception {
		// TODO Auto-generated method stub
		req.setAuditState(AuditResultEnum.APPLYING.getValue()+" or AUDIT_STATE = "+AuditResultEnum.PASSED.getValue());  //拼接字符串进行条件查询为了不影响其他的代码
	    Map<String , Object> map = divisionalAcceptanceApplyDao.queryDivisionalAcceptanceApply(req);
	    List<DivisionalAcceptanceApply> list = (List<DivisionalAcceptanceApply>)map.get("data");
	    if(list.size() >0 && list != null){
	       for (DivisionalAcceptanceApply divisionalAcceptanceApply : list) {
	    	   Project project = projectDao.get(divisionalAcceptanceApply.getProjId());
	    	   divisionalAcceptanceApply.setProjectType(project.getProjectType());
			
		}
	    }
	    return map;
		
	}

	/**
	 * 分部验收申请列表打印标记
	 * @author wanghuijun
	 * @createTime 2018年9月21日
	 * @param daaId
	 * @return
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void signDivisionalAcceptancePrint(String daaId) throws Exception,ClassNotFoundException {
		DivisionalAcceptanceApply divisionalAcceptanceApply = divisionalAcceptanceApplyDao.get(daaId);  //根据daaId取得实体信息
		if(divisionalAcceptanceApply != null){
			divisionalAcceptanceApply.setIsPrint(ContractIsPrintEnum.ALREADY_PRINT.getValue());
			divisionalAcceptanceApplyDao.saveOrUpdate(divisionalAcceptanceApply);  //更新实体
		}else{
			throw new ClassNotFoundException("找不到实体异常!");
		}
		
	}
	

}
