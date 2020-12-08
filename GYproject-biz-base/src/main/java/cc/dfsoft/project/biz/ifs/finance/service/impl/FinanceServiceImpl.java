package cc.dfsoft.project.biz.ifs.finance.service.impl;

import javax.annotation.Resource;
import javax.jws.WebService;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import cc.dfsoft.project.biz.base.charge.entity.CashFlow;
import cc.dfsoft.project.biz.base.charge.service.ChargeService;
import cc.dfsoft.project.biz.ifs.ResultMessage;
import cc.dfsoft.project.biz.ifs.WebServiceTypeEnum;
import cc.dfsoft.project.biz.ifs.finance.dto.PaymentSuccessInfoDTO;
import cc.dfsoft.project.biz.ifs.finance.enums.ResultTypeEnum;
import cc.dfsoft.project.biz.ifs.finance.service.FinanceService;
import cc.dfsoft.project.biz.ifs.finance.service.IFinanceInfoService;
import cc.dfsoft.project.biz.ifs.log.entity.WebserviceLog;
import cc.dfsoft.uexpress.common.util.StringUtil;
/**
 * 
 * 描述:工程管理系统提供的财务接口实现类
 * @author liaoyq
 * @createTime 2017年11月15日
 */
@Service
@WebService(endpointInterface="cc.dfsoft.project.biz.ifs.finance.service.FinanceService",serviceName="financeService",targetNamespace="http://cxf.apache.org/financeService")
public class FinanceServiceImpl implements FinanceService {
	//自定义级别标签
	public static Logger logger=LoggerFactory.getLogger("interfaceinfo");

	@Resource
	ChargeService chargeService;

	@Resource
	IFinanceInfoService iFinanceService;
	/**
	 * 付款成功后的接口，需要提供给用友系统进行调用
	 * 根据用友传递的参数，修改实付流水的状态
	 * @param paymentStr json字符串
	 * @return 返回信息给用友
	 */
	public String paymentDone(String userName,String password,String paymentStr)
	{
		ResultMessage resMsg = new ResultMessage();
		PaymentSuccessInfoDTO paySuccessInfo = new PaymentSuccessInfoDTO();
		if(StringUtil.isBlank(paymentStr)){
			resMsg.setRet_type(ResultTypeEnum.FAIL.getValue());
			resMsg.setRet_message("没有传递付款单信息参数！");
		}else{
			JSONObject object = JSONObject.fromObject(paymentStr);
			paySuccessInfo= (PaymentSuccessInfoDTO)JSONObject.toBean(object,PaymentSuccessInfoDTO.class);
			resMsg =payMentSuccess(paySuccessInfo);
		}
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		JSONObject json = JSONObject.fromObject(resMsg,jsonConfig);
		logger.info("调用付款成功接口："+paymentStr,"返回信息："+json.toString());

		//接口日志类
		WebserviceLog webserviceLog = new WebserviceLog();
		webserviceLog.setOperateType(paySuccessInfo.getOperate_type());
		webserviceLog.setLogParams(JSONObject.fromObject(paySuccessInfo).toString());
		webserviceLog.setServiceType(WebServiceTypeEnum.PAY_MONEY_SUCCESS.getValue());//领料单
		webserviceLog.setResultType(resMsg.getRet_type());
		webserviceLog.setResultMsg(resMsg.getRet_message());
		
		iFinanceService.saveWebserviceLog(webserviceLog);
		//返回json字符串
		return json.toString();
	}
	/**
	 * 根据付款成功后的参数修改应付记录状态
	 * @param decoder
	 * @return
	 */
	private ResultMessage payMentSuccess(PaymentSuccessInfoDTO paySuccessInfo){
		ResultMessage resMsg = new ResultMessage();
		CashFlow cashFlow = new CashFlow();
		//更新付款单信息
		boolean resFlag = false;
		if(paySuccessInfo==null){
			resMsg.setRet_type(ResultTypeEnum.FAIL.getValue());
			resMsg.setRet_message("付款单参数传递不正确！");
			return resMsg;
		}
		if(StringUtil.isBlank(paySuccessInfo.getPro_no())){
			resMsg.setRet_type(ResultTypeEnum.FAIL.getValue());
			resMsg.setRet_message("[pro_no]:"+paySuccessInfo.getPro_no()+",没有传递工程编号！");
			return resMsg;
		}else{
			cashFlow.setProjNo(paySuccessInfo.getPro_no());
		}
		if(StringUtil.isBlank(paySuccessInfo.getBill_id())){
			resMsg.setRet_type(ResultTypeEnum.FAIL.getValue());
			resMsg.setRet_message("[bill_id]:"+paySuccessInfo.getBill_id()+",没有传递付款单ID！");
			return resMsg;
		}else{
			cashFlow.setCfId(paySuccessInfo.getBill_id());
		}
		if(StringUtil.isBlank(paySuccessInfo.getOperate_type())){
			resMsg.setRet_type(ResultTypeEnum.FAIL.getValue());
			resMsg.setRet_message("[operate_type]:"+paySuccessInfo.getBill_id()+",没有传递操作类型！");
			return resMsg;
		}else{
			try {
				//CashFlow c = chargeService.queryById(cashFlow.getCfId(),cashFlow.getProjNo());
				CashFlow c = chargeService.queryById(cashFlow.getCfId(),null);//只要付款单ID即可
				if(c==null){
					resMsg.setRet_type(ResultTypeEnum.FAIL.getValue());
					resMsg.setRet_message("[bill_id]:"+paySuccessInfo.getBill_id()+",不存在该付款单ID！");
					return resMsg;
				}else{
				//更新付款单信息
					cashFlow.setResultFlag(ResultTypeEnum.SUCCUSS.getValue());
					resFlag = chargeService.modifyCashFlowById(cashFlow);
				}
			} catch (Exception e) {
				logger.info("修改付款成功状态失败！", e.getMessage());
			}
		}
		
		if(resFlag){
			resMsg.setRet_type(ResultTypeEnum.SUCCUSS.getValue());
			resMsg.setRet_message("已成功修改付款单的状态！");
		}else{
			resMsg.setRet_type(ResultTypeEnum.FAIL.getValue());
			resMsg.setRet_message("修改付款单的状态失败！");
		}
		return resMsg;
	}
}
