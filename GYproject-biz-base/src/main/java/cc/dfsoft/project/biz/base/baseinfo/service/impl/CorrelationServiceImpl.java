package cc.dfsoft.project.biz.base.baseinfo.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cc.dfsoft.project.biz.base.baseinfo.dao.CorrelationDao;
import cc.dfsoft.project.biz.base.baseinfo.dto.CorrelationReq;
import cc.dfsoft.project.biz.base.baseinfo.entity.Correlation;
import cc.dfsoft.project.biz.base.baseinfo.enums.AssistProgressTypeEnum;
import cc.dfsoft.project.biz.base.baseinfo.enums.CorrelationTypeEnum;
import cc.dfsoft.project.biz.base.baseinfo.enums.ProjectMethodEnum;
import cc.dfsoft.project.biz.base.baseinfo.service.CorrelationService;
import cc.dfsoft.project.biz.base.constructmanage.enums.StageProjectApplicationEnum;
import cc.dfsoft.project.biz.base.design.enums.DesignChangeStateEnum;
import cc.dfsoft.project.biz.base.project.entity.ContributionMode;
import cc.dfsoft.project.biz.base.project.entity.ProjectType;
import cc.dfsoft.project.biz.base.project.enums.StepEnum;
import cc.dfsoft.project.biz.base.project.service.ContributionModeService;
import cc.dfsoft.project.biz.base.project.service.ProjectTypeService;
import cc.dfsoft.project.biz.base.settlement.enums.AuditResultEnum;
import cc.dfsoft.project.biz.base.withgas.enums.GasProjectStatusEnum;
import cc.dfsoft.uexpress.biz.sys.dept.entity.Department;
import cc.dfsoft.uexpress.biz.sys.dept.enums.BusinessTypeEnum;
import cc.dfsoft.uexpress.biz.sys.dept.service.DepartmentService;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.util.IDUtil;
import cc.dfsoft.uexpress.common.util.StringUtil;
/**
 * 关联关系接口实现
 * @author Yuanyx
 *
 */
@Service
@Transactional(readOnly = true,propagation=Propagation.REQUIRED)
public class CorrelationServiceImpl implements CorrelationService {

	/** 关联关系Dao*/
	@Resource
	CorrelationDao correlationDao;
	/** 部门服务接口*/
	@Resource
	DepartmentService departmentService;
	/** 工程类型服务接口*/
	@Resource
	ProjectTypeService projectTypeService;
	/** 出资方式接口*/
	@Resource
	ContributionModeService contributionModeService;
	@Override
	public Map<String, Object> queryCorrelation(CorrelationReq correlationReq) {
		return correlationDao.queryCorrelation(correlationReq);
	}
	@Override
	public Map<String, List<Map<String, String>>> queryCorrelateInfoId(String corType) {
		Map<String,List<Map<String, String>>> correlationMap = new HashMap<String,List<Map<String, String>>>();
		List<Map<String, String>> correlationInfoList = new ArrayList<Map<String, String>>();
		List<Map<String, String>> correlationedInfoList = new ArrayList<Map<String, String>>();
		
		
		if(corType.equals(CorrelationTypeEnum.PROJECT_METHOD.getValue())){
			for(ProjectMethodEnum e: ProjectMethodEnum.values()) {
				Map<String,String> correlationInfoMap = new HashMap<String,String>();
				correlationInfoMap.put("correlationInfoId", e.getValue());
				correlationInfoMap.put("correlationInfoDes", e.getMessage());
				correlationInfoList.add(correlationInfoMap);
	    	}
			List<Department> departmentList=departmentService.findByBusinessType(BusinessTypeEnum.PROJECT_GROUP.getValue());//工程组
			for(Department department:departmentList){
				Map<String,String> correlationedInfoMap = new HashMap<String,String>();
				correlationedInfoMap.put("correlationedInfoId", department.getDeptId());
				correlationedInfoMap.put("correlationedInfoDes", department.getDeptName());
				correlationedInfoList.add(correlationedInfoMap);
			}
		}else if(corType.equals(CorrelationTypeEnum.DEPT_PROJTYPE.getValue())){
			List<Department> departmentList=departmentService.findByBusinessType(BusinessTypeEnum.PROJECT_GROUP.getValue());//工程组
			for(Department department:departmentList){
				Map<String,String> correlationInfoMap = new HashMap<String,String>();
				correlationInfoMap.put("correlationInfoId", department.getDeptId());
				correlationInfoMap.put("correlationInfoDes", department.getDeptName());
				correlationInfoList.add(correlationInfoMap);
			}
			List<ProjectType> projectTypeList = projectTypeService.queryAllList();
			for(ProjectType projectType:projectTypeList){
				Map<String,String> correlationedInfoMap = new HashMap<String,String>();
				correlationedInfoMap.put("correlationedInfoId", projectType.getProjTypeId());
				correlationedInfoMap.put("correlationedInfoDes", projectType.getProjConstructDes());
				correlationedInfoList.add(correlationedInfoMap);
			}
		}else if(corType.equals(CorrelationTypeEnum.PROJTYPE_CONTRIBUTION.getValue())){
			List<ProjectType> projectTypeList = projectTypeService.queryAllList();
			for(ProjectType projectType:projectTypeList){
				Map<String,String> correlationInfoMap = new HashMap<String,String>();
				correlationInfoMap.put("correlationInfoId", projectType.getProjTypeId());
				correlationInfoMap.put("correlationInfoDes", projectType.getProjConstructDes());
				correlationInfoList.add(correlationInfoMap);
			}
			List<ContributionMode> contributionModeList = contributionModeService.queryAllList();
			for(ContributionMode contributionMode:contributionModeList){
				Map<String,String> correlationedInfoMap = new HashMap<String,String>();
				correlationedInfoMap.put("correlationedInfoId", contributionMode.getId());
				correlationedInfoMap.put("correlationedInfoDes", contributionMode.getContributionDes());
				correlationedInfoList.add(correlationedInfoMap);
			}
		}
		correlationMap.put("correlationInfo", correlationInfoList);
		correlationMap.put("correlationedInfo", correlationedInfoList);
		return correlationMap;
	}
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void saveOrUpdateCorrelation(Correlation correlation) {
		if(!StringUtil.isNotBlank(correlation.getCorId())){
			correlation.setCorId(IDUtil.getUniqueId(Constants.STANDARD));
		}
		correlationDao.saveOrUpdate(correlation);
	}
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public String deleteCorrelation(String id) {
		correlationDao.delete(id);
		return Constants.OPERATE_RESULT_SUCCESS;
	}
	@Override
	public List<Correlation> findCorType(CorrelationReq req){
		return correlationDao.findCorType(req);
	}
	@Override
	public List<Correlation> findProjType(String corType, List<Correlation> department) {
		List<String> list = new ArrayList<String>();
		if(null!=department&&department.size()>0){
			for(Correlation correlation:department){
				list.add(correlation.getCorrelatedInfoId());
			}
		}
		List<Correlation> resultList=correlationDao.findProjType(corType,list);
		
		
		List<Correlation> newList=new ArrayList<Correlation>();
		if(null!=resultList&&resultList.size()>0){
			for(int i = 0;i<resultList.size();i++){
				for(int j = i+1;j<resultList.size();j++){
					if(resultList.get(i).getCorrelatedInfoId().equals(resultList.get(j).getCorrelatedInfoId())){
						continue;
					}else{
						if(j==resultList.size()-1){
							newList.add(resultList.get(i));
						}
					}
					
				}
			}
			newList.add(resultList.get(resultList.size()-1));
		}
		return newList;
	}
	@Override
	public Correlation findCode(String corType, String projType, String contributionModelId,String corpId) {
		return correlationDao.findCode(corType,projType,contributionModelId,corpId);
	}
	
	/**
	 * 通过编号查询
	 * @author fuliwei
	 * @createTime 2017年9月8日
	 * @param 
	 * @return
	 */
	@Override
	public Correlation findByContriubbtionCode(String code) {
		return correlationDao.findByContriubbtionCode(code);
	}
	
	/**
	 * 查询所有的辅助流程
	 * @author fuliwei  
	 * @date 2018年9月21日  
	 * @version 1.0
	 */
	@Override
	public List<Correlation> queryAssistWorkFlow() {
		List<Correlation> returnList=new ArrayList<Correlation>();
		
		Correlation cl;
		
		
		//通气
		GasProjectStatusEnum[] gasStatus=GasProjectStatusEnum.values();
		if(gasStatus!=null && gasStatus.length>0){
			for(GasProjectStatusEnum status: gasStatus){
				cl=new Correlation();
				cl.setCorrelateInfoId(status.getValue());
				cl.setCorrelateInfoDes(status.getMessage());
				cl.setScaleType(AssistProgressTypeEnum.GAS_PROGRESS.getValue());//默认是1
				returnList.add(cl);
			}
		}
		
		//签证
		StageProjectApplicationEnum[] enginStatus=StageProjectApplicationEnum.values();
		
		if(enginStatus!=null && enginStatus.length>0){
			for(StageProjectApplicationEnum status: enginStatus){
				cl=new Correlation();
				cl.setCorrelateInfoId(status.getValue());
				cl.setCorrelateInfoDes(status.getMessage());
				cl.setScaleType(AssistProgressTypeEnum.ENGINEER_PROGRESS.getValue());
				returnList.add(cl);
			}
		}
		
		//用户变更
		DesignChangeStateEnum[] changeStatus=DesignChangeStateEnum.values();
		if(changeStatus!=null && changeStatus.length>0){
			for(DesignChangeStateEnum status: changeStatus){
				cl=new Correlation();
				cl.setCorrelateInfoId(status.getValue());
				cl.setCorrelateInfoDes(status.getMessage());
				cl.setScaleType(AssistProgressTypeEnum.CHANGE_PROGRESS.getValue());
				returnList.add(cl);
			}
			//施工变更
			for(DesignChangeStateEnum status: changeStatus){
				cl=new Correlation();
				cl.setCorrelateInfoId(status.getValue());
				cl.setCorrelateInfoDes(status.getMessage());
				cl.setScaleType(AssistProgressTypeEnum.DESIGN_CHANGE_PROGRESS.getValue());
				returnList.add(cl);
			}
		}
		
		
		//付款
		AuditResultEnum [] payStatus=AuditResultEnum.values();
		if(payStatus!=null && payStatus.length>0){
			for(AuditResultEnum status: payStatus){
				cl=new Correlation();
				cl.setCorrelateInfoId(status.getValue());
				cl.setCorrelateInfoDes(status.getMessage());
				cl.setScaleType(AssistProgressTypeEnum.PAYMENT.getValue());
				returnList.add(cl);
			}
		}
		
		
		//主流程
		StepEnum [] stepStatus=StepEnum.values();
		if(stepStatus!=null && stepStatus.length>0){
			for(StepEnum status: stepStatus){
				cl=new Correlation();
				cl.setCorrelateInfoId(status.getValue());
				cl.setCorrelateInfoDes(status.getMessage());
				cl.setScaleType("0");//默认0为主流程
				returnList.add(cl);
			}
		}
		
		
		return returnList;
	}
}
