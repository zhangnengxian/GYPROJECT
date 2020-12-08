package cc.dfsoft.project.biz.ifs.project.service.impl;

import javax.annotation.Resource;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;

import org.springframework.stereotype.Service;

import cc.dfsoft.project.biz.base.baseinfo.entity.Customer;
import cc.dfsoft.project.biz.base.baseinfo.service.CustomerService;
import cc.dfsoft.project.biz.base.project.dao.ProjectDao;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.project.enums.ProjSourceEnum;
import cc.dfsoft.project.biz.base.project.enums.ProjStatusEnum;
import cc.dfsoft.project.biz.base.project.service.OperateRecordService;
import cc.dfsoft.project.biz.base.project.service.ProjectService;
import cc.dfsoft.project.biz.ifs.finance.enums.ResultTypeEnum;
import cc.dfsoft.project.biz.ifs.finance.service.IFinanceInfoService;
import cc.dfsoft.project.biz.ifs.log.entity.WebserviceLog;
import cc.dfsoft.project.biz.ifs.project.enums.OperateTypeEnum;
import cc.dfsoft.project.biz.ifs.project.service.IProjectAccept;
import cc.dfsoft.project.biz.ifs.project.dto.ProjectAcceptDto;
import cc.dfsoft.project.biz.ifs.project.dto.ProjectAcceptReDto;
import cc.dfsoft.project.biz.ifs.project.dto.ProjectAcceptWholeDto;
import cc.dfsoft.project.biz.ifs.project.dto.ProjectRetDto;
import cc.dfsoft.uexpress.biz.sys.dept.dao.DepartmentDao;
import cc.dfsoft.uexpress.biz.sys.dept.entity.Department;
import cc.dfsoft.uexpress.common.util.StringUtil;
/**
 * 报装接口实现
 * @author liaoyq
 * @createTime 2018年8月16日
 */
@Service
@WebService(endpointInterface="cc.dfsoft.project.biz.ifs.project.service.IProjectAccept",targetNamespace="http://accept.dfsoft.cc/service/impl")
public class ProjectAcceptServiceImpl implements IProjectAccept {
	//保存接口日志用
	@Resource
	IFinanceInfoService iFinanceService;
	@Resource
	CustomerService customerService;
	@Resource
	ProjectService projService;
	@Resource
	OperateRecordService operateRecordService;
	@Resource
	ProjectDao projectDao;
	@Resource
	DepartmentDao departmentDao;
	@Override
	public String projAcceptDone(String userName,String password,String jsonStr) {
		//验收数据
		ProjectAcceptReDto resMsg = new ProjectAcceptReDto();
		ProjectAcceptWholeDto projAcceptInfo = new ProjectAcceptWholeDto();
		if(StringUtil.isBlank(jsonStr)){
			resMsg.setRet_type(ResultTypeEnum.FAIL.getValue());
			resMsg.setRet_message("没有传递报装信息参数！");
		}else{
			try {
				JSONObject object = JSONObject.fromObject(jsonStr);
				projAcceptInfo= (ProjectAcceptWholeDto)JSONObject.toBean(object,ProjectAcceptWholeDto.class);
				resMsg =projAcceptSaveOrUpdate(projAcceptInfo);
			} catch (Exception e) {
				resMsg.setRet_type(ResultTypeEnum.FAIL.getValue());
				resMsg.setRet_message("传递报装信息参数不正确！"+e.getMessage());
			}
			
		}
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		JSONObject json = JSONObject.fromObject(resMsg,jsonConfig);
		//接口日志类
		WebserviceLog webserviceLog = new WebserviceLog();
		webserviceLog.setOperateType(projAcceptInfo.getOperate_type());
		webserviceLog.setLogParams("userName="+userName+"password="+password+"jsonStr="+jsonStr);
		webserviceLog.setServiceType("AC-"+projAcceptInfo.getOperate_type());//报装增加工程
		webserviceLog.setResultType(resMsg.getRet_type());
		webserviceLog.setResultMsg(resMsg.getRet_message());
		webserviceLog.setProjNo(resMsg.getProjectInfo()!=null?resMsg.getProjectInfo().getProj_no():"");
		//保存日志
		iFinanceService.saveWebserviceLog(webserviceLog);
		//返回json字符串
		String resJson = json.toString();
		return resJson;
	}

	/**
	 * 判断参数
	 * 保存报装信息
	 * 返回信息
	 * @author liaoyq
	 * @createTime 2018年8月16日
	 * @param projAcceptInfo
	 * @return
	 */
	private ProjectAcceptReDto projAcceptSaveOrUpdate(ProjectAcceptWholeDto projAcceptInfo){
		ProjectAcceptReDto resMsg = new ProjectAcceptReDto();
		if(projAcceptInfo==null){
			resMsg.setRet_type(ResultTypeEnum.FAIL.getValue());
			resMsg.setRet_message("报装信息参数传递不正确！");
			return resMsg;
		}
		//验证操作类型类型
		if(StringUtil.isBlank(projAcceptInfo.getOperate_type()) && OperateTypeEnum.valueof(projAcceptInfo.getOperate_type())!=null){
			resMsg.setRet_type(ResultTypeEnum.FAIL.getValue());
			resMsg.setRet_message("[operate_type:"+projAcceptInfo.getOperate_type()+"],没有传递操作类型或者操作类型不正确！");
			return resMsg;
		}
		//工程信息
		Project project = new Project();
		//客户信息
		Customer customer = new Customer();
		//受理信息
		ProjectAcceptDto acceptDto = projAcceptInfo.getProjectInfo();
		if(projAcceptInfo.getProjectInfo() == null){
			resMsg.setRet_type(ResultTypeEnum.FAIL.getValue());
			resMsg.setRet_message("[projectInfo:"+projAcceptInfo.getProjectInfo()+"],没有传递该工程受理信息！");
			return resMsg;
		}else{
			acceptDto = projAcceptInfo.getProjectInfo();
		}
		//验证客户id
		if(StringUtil.isBlank(acceptDto.getCust_no())){
			resMsg.setRet_type(ResultTypeEnum.FAIL.getValue());
			resMsg.setRet_message("[cust_no:"+acceptDto.getCust_no()+"],没有传递用户号！");
			return resMsg;
		}else{
			customer.setCustNo(acceptDto.getCust_no());
		}
		//验证联系人
		if(StringUtil.isBlank(acceptDto.getCust_contact())){
			resMsg.setRet_type(ResultTypeEnum.FAIL.getValue());
			resMsg.setRet_message("[cust_contact:"+acceptDto.getCust_contact()+"],没有传递联系人！");
			return resMsg;
		}else{
			project.setCustContact(acceptDto.getCust_contact());
			customer.setCustContcat(acceptDto.getCust_contact());
		}
		//验证联系人电话
		if(StringUtil.isBlank(acceptDto.getCust_phone())){
			resMsg.setRet_type(ResultTypeEnum.FAIL.getValue());
			resMsg.setRet_message("[cust_phone:"+acceptDto.getCust_phone()+"],没有传递联系人联系电话！");
			return resMsg;
		}else{
			project.setCustPhone(acceptDto.getCust_phone());
			customer.setCustPhone(acceptDto.getCust_phone());
		}
		//验证安装地址
		if(StringUtil.isBlank(acceptDto.getProj_addr())){
			resMsg.setRet_type(ResultTypeEnum.FAIL.getValue());
			resMsg.setRet_message("[proj_add:"+acceptDto.getProj_addr()+"],没有传递报装地址！");
			return resMsg;
		}else{
			project.setProjAddr(acceptDto.getProj_addr());
		}
		
		//验证分公司编码
		if(StringUtil.isBlank(acceptDto.getCorp_code())){
			resMsg.setRet_type(ResultTypeEnum.FAIL.getValue());
			resMsg.setRet_message("[corp_code:"+acceptDto.getCorp_code()+"],没有传递公司编码！");
			return resMsg;
		}else{
			//根据分公司编码查询工程系统是否有相应编码对照的分公司信息
			Department department = departmentDao.findByDeptCode(acceptDto.getCorp_code());
			if(department == null || StringUtil.isBlank(department.getDeptId())){
				resMsg.setRet_type(ResultTypeEnum.FAIL.getValue());
				resMsg.setRet_message("[corp_code:"+acceptDto.getCorp_code()+"],工程系统不存在该公司编码！");
				return resMsg;
			}
			project.setCorpId(department.getDeptId());//工程分公司ID
			project.setCorpName(department.getDeptName());//工程分公司名称
			
			customer.setCorpId(department.getDeptId());//客户分公司ID
		}
		//申报单位名称-没有则默认为联系人
		project.setCustName(StringUtil.isNotBlank(acceptDto.getCust_name())?acceptDto.getCust_name():acceptDto.getCust_contact());
		customer.setCustName(StringUtil.isNotBlank(acceptDto.getCust_name())?acceptDto.getCust_name():acceptDto.getCust_contact());
		customer.setAreaCode(StringUtil.isNotBlank(acceptDto.getCorp_code())?acceptDto.getCorp_code():"5201");
		//查询客户表中是否已有该客户，没有则增加客户，已有则返回客户
		customer = customerService.queryOrInsertCustomer(customer);
		if(customer == null ){
			resMsg.setRet_type(ResultTypeEnum.FAIL.getValue());
			resMsg.setRet_message("[customer:"+customer+"],工程系统没有客户信息！");
			return resMsg;
		}
		//申报单位ID
		project.setCustId(customer.getCustId());
		//受理备注
		project.setProjectRemark(StringUtil.isNotBlank(acceptDto.getRemark())?acceptDto.getRemark():"");
		
		//报装来源-默认微信
		project.setProjSource(ProjSourceEnum.valueof(acceptDto.getSource_type())!=null?acceptDto.getSource_type():ProjSourceEnum.WX.getValue());//受理方式
		
		//配置的受理部门信息
		Department deptAccept= departmentDao.findAcceptDept(project.getCorpId());
		if(deptAccept == null || StringUtil.isBlank(deptAccept.getDeptId())){
			resMsg.setRet_type(ResultTypeEnum.FAIL.getValue());
			resMsg.setRet_message("工程系统中没有配置相应的受理部门！");
			return resMsg;
		}else{
			project.setDeptId(deptAccept.getDeptId());//受理部门ID
			project.setDeptName(deptAccept.getDeptName());//受理部门
			project.setDeptTransfer(deptAccept.getDeptId());
			project.setOrgId(deptAccept.getDeptId());//组织ID
		}
		//再组装工程的部分信息并保存报装信息
		project = projService.acceptService(project,acceptDto,projAcceptInfo.getOperate_type());
		if(project.getProjNo().equals("noneNumber")){
			resMsg.setRet_type(ResultTypeEnum.FAIL.getValue());
			resMsg.setRet_message("[projNo:"+project.getProjNo()+"],工程系统没有取得工程编码！");
			return resMsg;
		}
		//工程信息保存成功后，返回信息
		resMsg = getAcceptProjInfo(project);
		
		return resMsg;
	}
	//增加工程成功，返回工程信息
	private ProjectAcceptReDto getAcceptProjInfo(Project project){
		ProjectAcceptReDto resultMessage = new ProjectAcceptReDto();
		Project pro = projectDao.get(project.getProjId());
		if(pro!=null){
			resultMessage.setRet_type(ResultTypeEnum.SUCCUSS.getValue());
			resultMessage.setRet_message("SUCCUSS");
			ProjectRetDto projectInfo = new ProjectRetDto();
			projectInfo.setProj_no(pro.getProjNo());
			projectInfo.setProj_status(ProjStatusEnum.valueof(pro.getProjStatusId())!=null?ProjStatusEnum.valueof(pro.getProjStatusId()).getMessage():"");
			resultMessage.setProjectInfo(projectInfo);
		}else{
			resultMessage.setRet_type(ResultTypeEnum.FAIL.getValue());
			resultMessage.setRet_message("工程系统生成工程失败！");
		}
		return resultMessage;
	}

}
