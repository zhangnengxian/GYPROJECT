package cc.dfsoft.project.biz.base.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cc.dfsoft.uexpress.common.constant.Constants;

@Controller
@RequestMapping(value="/sysConfig")
public class SysConfigController {
	
	@RequestMapping(value="/getSysConfigByKey")
	public String getSysConfigByKey(String key){
		Object obj = Constants.sysConfigMap.get(key);
		String str = obj.toString();
		return str;
	}
}
