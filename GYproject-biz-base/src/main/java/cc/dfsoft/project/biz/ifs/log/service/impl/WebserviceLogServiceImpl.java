package cc.dfsoft.project.biz.ifs.log.service.impl;

import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;

import cc.dfsoft.project.biz.base.messagesync.pojo.ResultMsg;
import cc.dfsoft.uexpress.common.util.MapUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fr.web.core.A.SA;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cc.dfsoft.project.biz.base.project.dto.WebserviceLogReq;
import cc.dfsoft.project.biz.ifs.log.dao.WebserviceLogDao;
import cc.dfsoft.project.biz.ifs.log.entity.WebserviceLog;
import cc.dfsoft.project.biz.ifs.log.service.WebserviceLogService;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.util.IDUtil;
import cc.dfsoft.uexpress.common.util.StringUtil;
/**
 * 接口日志服务实现类
 * @author liaoyq
 *
 */
@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class WebserviceLogServiceImpl implements WebserviceLogService {

	@Resource
	WebserviceLogDao webserviceLogDao;

	@Override
	@Transactional(readOnly = false, propagation = Propagation.NOT_SUPPORTED)//NOT_SUPPORTED不开启事物，只要调用接口，都存到日志
	public void saveOrUpdate(WebserviceLog webserviceLog) {
		if(StringUtil.isBlank(webserviceLog.getLogId())){
			webserviceLog.setLogId(IDUtil.getUniqueId(Constants.STANDARD));
		}
		webserviceLogDao.saveOrUpdate(webserviceLog);
	}

	@Override
	public Map<String, Object> queryWebServiceLog(
			WebserviceLogReq webserviceLogReq) {
		return webserviceLogDao.queryWebServiceLog(webserviceLogReq);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.NOT_SUPPORTED)//NOT_SUPPORTED不开启事物，只要调用接口，都存到日志
	public void saveSynchronizedLog(String projNo, String interfaceNo, ResultMsg resultMsg, Map map, String url) {
		WebserviceLog webserviceLog=new WebserviceLog();
		webserviceLog.setLogId(IDUtil.getUniqueId(Constants.STANDARD));
		webserviceLog.setProjNo(projNo);
		webserviceLog.setOperateType(interfaceNo);
		webserviceLog.setServiceType(interfaceNo);
		webserviceLog.setServicePath(url);
		webserviceLog.setLogParams(JSON.toJSONString(map));
		webserviceLog.setLogTime(new Date());

		String body = resultMsg.getMsg();
		if (isJson(body)) {
			Map mapTypes = JSON.parseObject(body);
			if (mapTypes!=null && !mapTypes.isEmpty()){
				webserviceLog.setResultType(String.valueOf(mapTypes.get("ret_type")));
				webserviceLog.setResultMsg(String.valueOf(mapTypes.get("ret_message")));
			}
		}else {
			webserviceLog.setResultType(resultMsg.getCode()+"");
			webserviceLog.setResultMsg(resultMsg.getMsg());
		}
		webserviceLogDao.save(webserviceLog);
	}


	@Override
	public boolean isJson(String string){
		try {
			JSONObject.parseObject(string);
			return  true;
		} catch (Exception e) {
			return false;
		}
	}



}
