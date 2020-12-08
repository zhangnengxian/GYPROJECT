package cc.dfsoft.project.biz.ifs.material.service.impl;

import java.util.Map;

import javax.annotation.Resource;
import javax.jws.WebService;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import cc.dfsoft.project.biz.base.budget.service.MaterialListService;
import cc.dfsoft.project.biz.ifs.ResultMessage;
import cc.dfsoft.project.biz.ifs.WebServiceTypeEnum;
import cc.dfsoft.project.biz.ifs.finance.enums.ResultTypeEnum;
import cc.dfsoft.project.biz.ifs.finance.service.IFinanceInfoService;
import cc.dfsoft.project.biz.ifs.log.entity.WebserviceLog;
import cc.dfsoft.project.biz.ifs.material.dto.ProjectInfoDTO;
import cc.dfsoft.project.biz.ifs.material.dto.ProjectWholeInoDTO;
import cc.dfsoft.project.biz.ifs.material.service.MaterialService;
import cc.dfsoft.uexpress.common.util.StringUtil;
/**
 * 物资接口
 * @author fuliwei
 *
 */
@Service
@WebService(endpointInterface="cc.dfsoft.project.biz.ifs.material.service.MaterialService",serviceName="materialService",targetNamespace="http://cxf.apache.org/materialService")
public class MaterialServiceImpl implements MaterialService {
	//自定义级别标签
	public static Logger logger=LoggerFactory.getLogger("interfaceinfo");
	
	/**材料清单*/
	@Resource
	MaterialListService materialListService;
	@Resource
	IFinanceInfoService iFinanceService;

	/**
	 * 领用接口
	 * @author fuliwei
	 * @createTime 2017年11月13日
	 * @param 
	 * @return
	 */
	@Override
	public String doOutStore(String usname,String pwd,String materialReceiveStr) {
		ResultMessage resMsg = new ResultMessage();
		ProjectWholeInoDTO paySuccessInfo = new ProjectWholeInoDTO();
		if(StringUtil.isBlank(materialReceiveStr)){
			resMsg.setRet_type(ResultTypeEnum.FAIL.getValue());
			resMsg.setRet_message("没有传递参数！");
		}else{
			//先去掉认证
			/*if(StringUtil.isBlank(usname)){
				resMsg.setRet_type(ResultTypeEnum.FAIL.getValue());
				resMsg.setRet_message("没有传递用户名！");
			}
			
			if(StringUtil.isBlank(pwd)){
				resMsg.setRet_type(ResultTypeEnum.FAIL.getValue());
				resMsg.setRet_message("没有传递密码！");
			}*/
			//String decoder = Base64Util.jdkBase64Decoder(materialReceiveStr);
			
			JSONObject object = JSONObject.fromObject(materialReceiveStr);
			
			paySuccessInfo= (ProjectWholeInoDTO)JSONObject.toBean(object,ProjectWholeInoDTO.class);
			
			resMsg =materialReceiveSuccess(paySuccessInfo);
		}
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		JSONObject json = JSONObject.fromObject(resMsg,jsonConfig);
		
		//接口日志类
		WebserviceLog webserviceLog = new WebserviceLog();
		webserviceLog.setOperateType(paySuccessInfo.getOperate_type());
		webserviceLog.setLogParams(materialReceiveStr);
		webserviceLog.setServiceType(WebServiceTypeEnum.MATERAIL_COLLOR.getValue());//领料单
		webserviceLog.setResultType(resMsg.getRet_type());
		webserviceLog.setResultMsg(resMsg.getRet_message());
		iFinanceService.saveWebserviceLog(webserviceLog);
		logger.info("财务接口调用工程系统领料单接口："+materialReceiveStr,"返回信息："+json);
		return json.toString();
	}
	
	
	
	/**
	 * 修改领用信息
	 * @author fuliwei
	 * @createTime 2017年11月13日
	 * @param 
	 * @return
	 */
	private ResultMessage materialReceiveSuccess(ProjectWholeInoDTO paySuccessInfo){
		
		ResultMessage resMsg = new ResultMessage();
		try {
			if(paySuccessInfo==null){
				resMsg.setRet_type(ResultTypeEnum.FAIL.getValue());
				resMsg.setRet_message("传递参数不正确！");
				return resMsg;
			}
			if(StringUtil.isBlank(paySuccessInfo.getOperate_type())){
				resMsg.setRet_type(ResultTypeEnum.FAIL.getValue());
				resMsg.setRet_message("[operate_type:"+paySuccessInfo.getOperate_type()+",未传递操作类型！]");
				return resMsg;
			}
			ProjectInfoDTO projInfo= paySuccessInfo.getProjinfo();
			
			if(projInfo==null){
				resMsg.setRet_type(ResultTypeEnum.FAIL.getValue());
				resMsg.setRet_message("[projinfo:"+projInfo+",未传递工程信息！]");
				return resMsg;
			}else{
				if(StringUtil.isBlank(projInfo.getProj_no())){
					resMsg.setRet_type(ResultTypeEnum.FAIL.getValue());
					resMsg.setRet_message("[proj_no:"+projInfo.getProj_no()+",没有传递工程编号！]");
					return resMsg;
				}
				/*if(StringUtil.isBlank(projInfo.getChange_id())){
					resMsg.setRet_type(ResultTypeEnum.FAIL.getValue());
					resMsg.setRet_message("[change_id:"+projInfo.getProj_no()+",没有传递工程编号！]");
					return resMsg;
				}*/
			}
			
			Map<String,Object> materialInfoList=paySuccessInfo.getMateriallistinfo();
			if(materialInfoList!=null && !materialInfoList.isEmpty()){
				//材料领用保存方法
				resMsg=materialListService.updateMaterialsReceive(projInfo, materialInfoList,paySuccessInfo.getOperate_type());
			}else{
				resMsg.setRet_type(ResultTypeEnum.FAIL.getValue());
				resMsg.setRet_type("[materiallistinfo:"+materialInfoList+",传递的材料表信息为空！]");
			}
			//返回json字符串
			return resMsg;
		} catch (Exception e) {
			resMsg.setRet_type(ResultTypeEnum.FAIL.getValue());
			resMsg.setRet_message("领料单保存失败！");
			return resMsg;
		}
	}
}
