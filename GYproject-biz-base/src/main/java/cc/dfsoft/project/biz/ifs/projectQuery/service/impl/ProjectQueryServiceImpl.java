package cc.dfsoft.project.biz.ifs.projectQuery.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.jws.WebParam;
import javax.jws.WebService;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;

import org.springframework.stereotype.Service;

import cc.dfsoft.project.biz.base.baseinfo.enums.BackReasonEnum;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.project.enums.ProjStatusEnum;
import cc.dfsoft.project.biz.base.project.service.ProjectService;
import cc.dfsoft.project.biz.ifs.finance.enums.ResultTypeEnum;
import cc.dfsoft.project.biz.ifs.finance.service.IFinanceInfoService;
import cc.dfsoft.project.biz.ifs.log.entity.WebserviceLog;
import cc.dfsoft.project.biz.ifs.project.enums.OperateTypeEnum;
import cc.dfsoft.project.biz.ifs.projectQuery.dto.ProjectDto;
import cc.dfsoft.project.biz.ifs.projectQuery.dto.ProjectQueryDto;
import cc.dfsoft.project.biz.ifs.projectQuery.dto.ProjectQueryReDto;
import cc.dfsoft.project.biz.ifs.projectQuery.dto.ProjectQueryWholeDto;
import cc.dfsoft.project.biz.ifs.projectQuery.service.IProjectQuery;
import cc.dfsoft.uexpress.biz.sys.dept.dao.StaffDao;
import cc.dfsoft.uexpress.biz.sys.dept.entity.Staff;
import cc.dfsoft.uexpress.common.util.StringUtil;
/**
 * 
 * @author liaoyq
 * @createTime 2018年8月16日
 */
@Service
@WebService(endpointInterface="cc.dfsoft.project.biz.ifs.projectQuery.service.IProjectQuery",targetNamespace="http://query.dfsoft.cc/service/impl")
public class ProjectQueryServiceImpl implements IProjectQuery {
	//保存接口日志用
	@Resource
	IFinanceInfoService iFinanceService;
	
	@Resource
	ProjectService projService;
	@Resource
	StaffDao staffDao;

	@Override
	public String projQueryDone( String userName,String password,String jsonStr) {
		//验收数据
		ProjectQueryReDto resMsg = new ProjectQueryReDto();
		ProjectQueryWholeDto projInfo = new ProjectQueryWholeDto();
		if(StringUtil.isBlank(jsonStr)){
			resMsg.setRet_type(ResultTypeEnum.FAIL.getValue());
			resMsg.setRet_message("没有传递查询信息参数！");
		}else{
			try {
				JSONObject object = JSONObject.fromObject(jsonStr);
				projInfo= (ProjectQueryWholeDto)JSONObject.toBean(object,ProjectQueryWholeDto.class);
				resMsg =queryProject(projInfo);
			} catch (Exception e) {
				resMsg.setRet_type(ResultTypeEnum.FAIL.getValue());
				resMsg.setRet_message("传递查询参数不正确！"+e.getMessage());
			}
			
		}
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		JSONObject json = JSONObject.fromObject(resMsg,jsonConfig);
		//接口日志类
		WebserviceLog webserviceLog = new WebserviceLog();
		webserviceLog.setOperateType(projInfo.getOperate_type());
		webserviceLog.setLogParams("userName="+userName+"password="+password+"jsonStr="+jsonStr);
		webserviceLog.setServiceType("AC-"+(StringUtil.isNotBlank(projInfo.getOperate_type())?projInfo.getOperate_type():OperateTypeEnum.QUERY.getValue()));//查询报装查询
		webserviceLog.setResultType(resMsg.getRet_type());
		webserviceLog.setResultMsg(resMsg.getRet_message());
		webserviceLog.setProjNo(resMsg.getProjectInfo()!=null?resMsg.getProjectInfo().getProj_no():"");
		iFinanceService.saveWebserviceLog(webserviceLog);
		
		//返回json字符串
		String resJson = json.toString();
		return resJson;
	}
	//根据工程编号查询工程信息
	private ProjectQueryReDto queryProject (ProjectQueryWholeDto queryWholeDto){
		ProjectQueryReDto resMsg = new ProjectQueryReDto();
		if(queryWholeDto==null){
			resMsg.setRet_type(ResultTypeEnum.FAIL.getValue());
			resMsg.setRet_message("传递要查询的工程信息不正确！");
			return resMsg;
		}
		//验证操作类型类型
		if(StringUtil.isBlank(queryWholeDto.getOperate_type()) && OperateTypeEnum.valueof(queryWholeDto.getOperate_type())!=null){
			resMsg.setRet_type(ResultTypeEnum.FAIL.getValue());
			resMsg.setRet_message("[operate_type:"+queryWholeDto.getOperate_type()+"],没有传递操作类型或者操作类型不正确！");
			return resMsg;
		}
		
		//工程编号
		ProjectQueryDto projectQueryDto = new ProjectQueryDto();
		if(queryWholeDto.getProjectInfo() == null){
			resMsg.setRet_type(ResultTypeEnum.FAIL.getValue());
			resMsg.setRet_message("[projectInfo:"+queryWholeDto.getProjectInfo()+"],没有传递该工程受理信息！");
			return resMsg;
		}else{
			projectQueryDto = queryWholeDto.getProjectInfo();
		}
		if(StringUtil.isBlank(projectQueryDto.getProj_no())){
			resMsg.setRet_type(ResultTypeEnum.FAIL.getValue());
			resMsg.setRet_message("[proj_no:"+projectQueryDto.getProj_no()+"],没有传递工程编号！");
			return resMsg;
		}
		//工程信息
		List<Project> projects = projService.findByProjNo(projectQueryDto.getProj_no());
		
		if(projects==null || projects.size()<1){
			resMsg.setRet_type(ResultTypeEnum.FAIL.getValue());
			resMsg.setRet_message("[proj_no:"+projectQueryDto.getProj_no()+"],工程系统没有查询到该工程！");
			return resMsg;
		}
		//返回工程信息
		ProjectDto projectDto = new ProjectDto();
		projectDto.setProj_no(projects.get(0).getProjNo());
		projectDto.setProj_name(projects.get(0).getProjName());
		projectDto.setProj_addr(projects.get(0).getProjAddr());
		projectDto.setProj_scale_des(projects.get(0).getProjScaleDes());
		if(StringUtil.isNotBlank(projects.get(0).getSurveyer())){
			Staff staff = staffDao.get(projects.get(0).getSurveyerId());
			projectDto.setSurveyer(staff.getStaffName());
			projectDto.setSurveyer_phone(staff.getPhone());
		}
		projectDto.setProj_status(ProjStatusEnum.valueof(projects.get(0).getProjStatusId())!=null?ProjStatusEnum.valueof(projects.get(0).getProjStatusId()).getMessage():"");
		//备注
		if(StringUtil.isNotBlank(projects.get(0).getBackReason()) && BackReasonEnum.valueof(projects.get(0).getBackReason())!=null){
			projectDto.setRemarks(BackReasonEnum.valueof(projects.get(0).getBackReason()).getMessage());
			if(projects.get(0).getBackReason().equals(BackReasonEnum.OTHERS.getValue())){
				projectDto.setRemarks(StringUtil.isNotBlank(projects.get(0).getBackRemarks())?projects.get(0).getBackRemarks():"");
			}
		}
		
		resMsg.setProjectInfo(projectDto);
		resMsg.setRet_type(ResultTypeEnum.SUCCUSS.getValue());
		resMsg.setRet_message("SUCCUSS");
		return resMsg;
	}
}
