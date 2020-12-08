package cc.dfsoft.project.biz.ifs.material.service.impl;


import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.xml.rpc.ServiceException;

import nc.bd.itf.customer.service.BusinessException;
import nc.bd.itf.gzrq.material.MaterielAppLocator;
import nc.bd.itf.gzrq.material.MaterielAppPortType;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cc.dfsoft.project.biz.base.baseinfo.dao.CustomerDao;
import cc.dfsoft.project.biz.base.budget.dao.MaterialListDao;
import cc.dfsoft.project.biz.base.budget.dto.MaterialListQueryReq;
import cc.dfsoft.project.biz.base.budget.dto.MaterialListReq;
import cc.dfsoft.project.biz.base.budget.entity.MaterialList;
import cc.dfsoft.project.biz.base.change.dao.MaterialChangeDao;
import cc.dfsoft.project.biz.base.change.dto.MaterialChangeReq;
import cc.dfsoft.project.biz.base.change.entity.MaterialChange;
import cc.dfsoft.project.biz.base.design.enums.UnitTypeEnum;
import cc.dfsoft.project.biz.base.plan.dao.ConstructionPlanDao;
import cc.dfsoft.project.biz.base.plan.entity.ConstructionPlan;
import cc.dfsoft.project.biz.base.project.dao.ProjectDao;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.subpackage.dao.SubContractDao;
import cc.dfsoft.project.biz.base.subpackage.entity.SubContract;
import cc.dfsoft.project.biz.ifs.ResultMessage;
import cc.dfsoft.project.biz.ifs.finance.enums.ResultTypeEnum;
import cc.dfsoft.project.biz.ifs.finance.service.IFinanceInfoService;
import cc.dfsoft.project.biz.ifs.log.entity.WebserviceLog;
import cc.dfsoft.project.biz.ifs.material.dto.MaterialListInfoDTO;
import cc.dfsoft.project.biz.ifs.material.dto.ProjectInfoDTO;
import cc.dfsoft.project.biz.ifs.material.dto.ProjectWholeInoDTO;
import cc.dfsoft.project.biz.ifs.material.enums.MaterialOperateTypeEnum;
import cc.dfsoft.project.biz.ifs.material.enums.MaterialTableTypeEnum;
import cc.dfsoft.project.biz.ifs.material.service.MaterialNcService;
import cc.dfsoft.uexpress.biz.sys.dept.dao.DepartmentDao;
import cc.dfsoft.uexpress.biz.sys.dept.entity.Department;
import cc.dfsoft.uexpress.common.util.StringUtil;


@Service
public class MaterialNcServiceImpl implements MaterialNcService {
	
	//自定义级别标签
	public static Logger logger=LoggerFactory.getLogger("interfaceinfo");
	
	private String operateType;
	
	/**工程服务接口*/
	@Resource
	ProjectDao projectDao;
	
	/**客户服务接口*/
	@Resource
	CustomerDao customerDao;
	
	/**施工计划服务接口*/
	@Resource
	ConstructionPlanDao constructionPlanDao;
	
	/**材料清单*/
	@Resource
	MaterialListDao materialListDao;
	
	/**材料接口*/
	@Resource
	MaterialChangeDao materialChangeDao;
	
	/**分包合同号*/
	@Resource
	SubContractDao subContractDao;
	
	@Resource
	IFinanceInfoService iFinanceService;
	
	@Resource
	DepartmentDao departmentDao;
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public String synProjectInfoClient(String proID, String businessId, String changeType, String materialType,String updateType,String serviceType)
			throws ParseException {
		//初始化信息同步的dto
		ProjectWholeInoDTO projectWholeInfoDto = new ProjectWholeInoDTO();
		
		operateType = updateType;
		if(MaterialOperateTypeEnum.CHANGE.getValue().equals(updateType)){
			//材料变更也走增加，新增物料计划
			updateType = MaterialOperateTypeEnum.INSERT.getValue();
		}
		projectWholeInfoDto.setOperate_type(updateType);
		//获取工程对象
		Project pro = projectDao.get(proID);
		
		ConstructionPlan conPlan = constructionPlanDao.findByProjId(proID);
		
		SubContract sub=subContractDao.findByProjId(proID);
		
		ProjectInfoDTO projectDTO = new ProjectInfoDTO();
		
		projectDTO.setProj_no(pro.getProjNo());
		projectDTO.setProj_name(pro.getProjName());//工程名称
		projectDTO.setProj_id(pro.getProjId());
		projectDTO.setProj_addr(pro.getProjAddr());
		String custCode=iFinanceService.getUnitCode(pro.getCustId(), UnitTypeEnum.CUSTOMER_UNIT.getValue());
		if(StringUtil.isBlank(custCode)){//取燃气公司编码
			Department department = departmentDao.get(pro.getCorpId());
			if(department!=null){
				custCode = department.getDeptOutCode();
			}
		}
		projectDTO.setCust_code(custCode);//客户编码
		if(conPlan!=null){
			projectDTO.setUnit_code(iFinanceService.getUnitCode(conPlan.getCuId(),UnitTypeEnum.CONSTRUCTION_UNIT.getValue()));//施工单位-编码
			projectDTO.setUnit_name(conPlan.getCuName());//施工单位名称
		}
		if(sub!=null){
			projectDTO.setWork_code(sub.getScNo());		//施工合同号
		}else{
			projectDTO.setWork_code(pro.getProjNo());
		}
		projectDTO.setChange_id(StringUtil.isNotBlank(businessId)?businessId:String.valueOf(projectDao.getDatabaseDate().getTime()));
		projectWholeInfoDto.setProjinfo(projectDTO);
		
		
		List<MaterialListInfoDTO> list=new ArrayList<MaterialListInfoDTO>();
		
		//list=this.findById(proID,changeType,businessId);
		//判断是主材表还是变更材料表
		if(MaterialTableTypeEnum.MATERIAL.getValue().equals(changeType)){
			//主材表
			list=this.findById(proID,changeType,businessId);
			
		}else{
			list=this.findByIdAndType(proID,businessId);
		}
		
		
		Map<String,Object>  map=new HashMap<String,Object>();
		
		
		if(list!=null && list.size()>0){
			for(int i=0;i<list.size();i++){
				int j=i+1;
				map.put("materiallist"+j, list.get(i));
			}
			projectWholeInfoDto.setMateriallistinfo(map);
			
			String result="";
			result = this.webserviceCall(projectWholeInfoDto);
			//接口日志类
			WebserviceLog webserviceLog = new WebserviceLog();
			JSONObject jsonbean = JSONObject.fromObject(result);
			ResultMessage resultMessage = (ResultMessage) JSONObject.toBean(jsonbean,ResultMessage.class);
			webserviceLog.setOperateType(operateType);
			webserviceLog.setLogParams(JSONObject.fromObject(projectWholeInfoDto).toString());
			webserviceLog.setServiceType(serviceType);//物料计划单
			webserviceLog.setResultType(resultMessage.getRet_type());
			webserviceLog.setResultMsg(resultMessage.getRet_message());
			webserviceLog.setProjId(pro.getProjId());
			webserviceLog.setProjNo(pro.getProjNo());
			
			iFinanceService.saveWebserviceLog(webserviceLog);
			return result;
		}else{
			ResultMessage resultMessage = new ResultMessage();
			resultMessage.setRet_type(ResultTypeEnum.SUCCUSS.getValue());
			resultMessage.setRet_message("没有变更材料，不调用接口");
			return JSONObject.fromObject(resultMessage).toString();
		}
		
	}
	
	private List<MaterialListInfoDTO> findById(String proID, String changeType,String businessId) {
		List<MaterialListInfoDTO> returnList=new ArrayList<MaterialListInfoDTO>();
		MaterialListInfoDTO mid;
		MaterialListReq req=new MaterialListReq();
		req.setProjId(proID);
		if(changeType.equals(MaterialTableTypeEnum.CHANGE_MATERIAL.getValue())){
			req.setCmId(businessId);
		}
		req.setFlag("1");//物资公司购买
		List<MaterialList> listMaterial=materialListDao.queryMaterials(req);
		if(listMaterial!=null && listMaterial.size()>0){
			for(MaterialList mc:listMaterial){
				mid=new MaterialListInfoDTO();
					mid.setMapping_guize(mc.getMaterialSpec());			//物料规格
					mid.setMaterial_name(mc.getMaterialName());				//材料名称

					mid.setMaterial_num(mc.getMaterialNum().toString()); 	//设计总量
					mid.setMaterial_table_type(changeType);						//主材表
					mid.setMaterial_unit(mc.getMaterialUnit());				//单位
					mid.setPrimary_key(mc.getMaterialId());					//材料表主键
					returnList.add(mid);
			}
		}
		
		return returnList;
	}

		//根据工程id查主材表-弃用
		public List<MaterialListInfoDTO> findById(String projId){
			
			List<MaterialListInfoDTO> returnList=new ArrayList<MaterialListInfoDTO>();
			MaterialListInfoDTO mid;
			
			List<MaterialList> listMaterial=materialListDao.queryMaterialListById(projId);
			if(listMaterial!=null && listMaterial.size()>0){
				for(MaterialList mc:listMaterial){

					mid=new MaterialListInfoDTO();
					mid.setMapping_guize(mc.getMaterialSpec());			//物料规格
					mid.setMaterial_name(mc.getMaterialName());				//材料名称

					mid.setMaterial_num(mc.getMaterialNum().toString()); 	//设计总量
					mid.setMaterial_table_type(MaterialTableTypeEnum.MATERIAL.getValue());						//主材表
					mid.setMaterial_unit(mc.getMaterialUnit());				//单位
					mid.setPrimary_key(mc.getMaterialId());					//材料表主键
					returnList.add(mid);
				}
			}
			
			return returnList;
			
		}
		
		/**
		 * 查变更、签证材料表
		 * @author fuliwei
		 * @createTime 2017年11月13日
		 * @param businessId 补充协议表ID
		 * @return
		 */
		public List<MaterialListInfoDTO> findByIdAndType(String projId,String businessId){
			List<MaterialListInfoDTO> returnList=new ArrayList<MaterialListInfoDTO>();
			
			MaterialChangeReq req=new MaterialChangeReq();
			req.setProjId(projId);
			req.setCmId(businessId);
			req.setFlag("1");//物资公司购买
			List<MaterialChange> listMaterialChange =materialChangeDao.queryMaterialChangeList(req);
			
			MaterialListInfoDTO mid;
			req.setProjId(projId);
			if(listMaterialChange!=null && listMaterialChange.size()>0){
				for(MaterialChange mc:listMaterialChange){
					mid=new MaterialListInfoDTO();
					mid.setMapping_guize(mc.getMaterialSpec());			     //材料规格
					mid.setMaterial_name(mc.getMaterialName());		    //材料名称
					mid.setMaterial_num(mc.getAdjustQuantity().toString()); //调整量
					mid.setMaterial_table_type(MaterialTableTypeEnum.CHANGE_MATERIAL.getValue());						//变更材料表
					mid.setMaterial_unit(mc.getMaterialUnit());				//单位
					mid.setPrimary_key(mc.getMaterialId());//变更材料表ID
					returnList.add(mid);
				}
			}
			
			return returnList;
		}
		/**
		 * 调用WebService接口
		 * @param <T>
		 * @return
		 * @throws ServiceException 
		 * @throws RemoteException 
		 * @throws BusinessException 
		 */
		private <T> String webserviceCall(T t) 
		{
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
			JSONObject json = JSONObject.fromObject(t,jsonConfig);
			logger.info("物料计划单接口传递的数据：" + json.toString());
			System.err.println("json"+json.toString());
			try {
				//调用WebService接口，传数据到用友系统中；
				MaterielAppLocator customerInfo = new MaterielAppLocator();
				MaterielAppPortType infoPortType;
				infoPortType = customerInfo.getMaterielAppSOAP11port_http();
				//调用接口传递数据
				String result = infoPortType.doMaterAppBillAdd("username", "pwd", json.toString());
				logger.info("物料计划单接口调用成功，返回的信息：" + result);
				System.err.println(result);
				return result;
			} catch (Exception e) {
				System.err.println(e.getMessage());
				logger.info("物料计划单接口调用失败",e.getMessage());
			}
			ResultMessage message = new ResultMessage();
			message.setRet_type(ResultTypeEnum.FAIL.getValue());
			message.setRet_message("调用友物料计划单接口失败");
			return JSONObject.fromObject(message).toString();
		}
}
